package com.ntu.ir.main;

import java.io.Serializable;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;

import com.ntu.ir.bean.TagPosting;
import com.ntu.ir.parser.ParserFunction2;

import scala.Tuple2;

public class FilterDriver2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7746018008847553650L;
	private SparkSession spark;
	private String tag;
	private String enrichTag;
	private String inputFilePath;

	public void init(String tag, String inputFilePath) {
		this.enrichTag = "&lt;" + tag + "&gt;";
		this.tag = tag;
		spark = SparkSession.builder().appName("Java Spark Filter").master("local")
				.config("spark.serializer", "org.apache.spark.serializer.KryoSerializer").getOrCreate();
		this.inputFilePath = inputFilePath;

	}

	public void run() {

		ParserFunction2 parser2 = new ParserFunction2();
		parser2.init();
		JavaRDD<TagPosting> tagposts = spark.read().textFile(inputFilePath).javaRDD().filter(e -> e.length() > 10)
				.map(parser2);
		tagposts.persist(StorageLevel.MEMORY_AND_DISK_SER());
		JavaRDD<TagPosting> javaQ = tagposts.filter(e -> {
			if (e == null || e.getPostTypeId() == null || e.getTags() == null) {
				return false;
			}
			return e.getPostTypeId().equals("1") && e.getTags().contains(enrichTag);
		});

		javaQ.map(e -> e.getRow()).saveAsTextFile("../" + tag + "Q");

		@SuppressWarnings("rawtypes")
		Map<Object, Object> javaQidMap = javaQ.mapToPair(e -> new Tuple2(e.getId(), 1)).collectAsMap();
		
		System.gc();

		// Broadcast<Map<Object, Object>> jqmb =
		// spark.sparkContext().broadcast(javaQidMap);
		JavaRDD<TagPosting> javaA = tagposts.filter(e -> {
			if (e == null || e.getPostTypeId() == null) {
				return false;
			}
			return e.getPostTypeId().equals("2") && javaQidMap.containsKey(e.getParentId());
		});

		tagposts.unpersist();
		javaA.map(e -> e.getRow()).saveAsTextFile("../" + tag + "A");

	}

}

package com.ntu.ir.main;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

public class FilterDriver {

	private SparkSession spark;

	public void init() {
		spark = SparkSession.builder().appName("Java Spark Filter").master("local").getOrCreate();

	}

	public void run() {

		JavaRDD<String> rows = spark.read().textFile("../1000.xml").javaRDD().filter(e -> e.contains("&lt;scala&gt;"));
		rows.saveAsTextFile("../scalaQonly.xml");

	}

}

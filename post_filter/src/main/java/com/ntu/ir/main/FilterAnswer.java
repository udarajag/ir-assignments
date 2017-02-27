package com.ntu.ir.main;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

public class FilterAnswer {
	
	public static void main(String[] args) {
		
	}
	
	public void run(){
		SparkSession spark = SparkSession.builder().appName("Java Spark Filter").master("local").getOrCreate();
		JavaRDD<String> rows = spark.read().textFile("../javaQ.xml").javaRDD().filter(e -> e.contains("&lt;java&gt;"));
		rows.saveAsTextFile("../javaQonly.xml");
	}

}

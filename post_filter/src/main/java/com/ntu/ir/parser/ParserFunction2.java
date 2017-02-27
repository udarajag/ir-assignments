package com.ntu.ir.parser;

import org.apache.spark.api.java.function.Function;

import com.ntu.ir.bean.TagPosting;

public class ParserFunction2 implements Function<String, TagPosting> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705776716700880820L;
	private CustomParser<TagPosting> customParser;

	public void init() {

		customParser = new CustomParser<>(TagPosting.class, "row", "/>","\" ");
	}


	@Override
	public TagPosting call(String row) throws Exception {
		return customParser.parse(row);
	}

}

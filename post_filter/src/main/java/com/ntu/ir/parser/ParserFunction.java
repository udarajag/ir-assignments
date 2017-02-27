package com.ntu.ir.parser;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.spark.api.java.function.PairFunction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ntu.ir.bean.TagPosting;

import scala.Tuple2;

public class ParserFunction implements PairFunction<String, String, TagPosting> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705776716700880820L;
	private DocumentBuilderFactory dbc;
	private DocumentBuilder dbuilder;

	public void init() {

		dbc = DocumentBuilderFactory.newInstance();
		try {
			dbuilder = dbc.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Tuple2<String, TagPosting> call(String row) throws Exception {

		TagPosting post = new TagPosting();
		try {
			Document doc = dbuilder.parse(new InputSource(new StringReader(row)));
			NodeList nl = doc.getElementsByTagName("row");
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				post.setId(e.getAttribute("Id"));
				post.setTags(e.getAttribute("Tag"));
				post.setPostTypeId(e.getAttribute("PostTypeId"));
			}
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return new Tuple2<>(post.getId(), post);
	}

}

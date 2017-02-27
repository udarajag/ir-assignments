package com.ntu.ir.parser;

import java.io.Serializable;
import java.lang.reflect.Field;

public class CustomParser<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3887876300427681636L;
	private Class<T> clazz;
	private String startTag;
	private String endTag;
	private String regex;

	public CustomParser(Class<T> clazz, String startTag, String endTag,String regex) {
		this.clazz = clazz;
		this.startTag = startTag;
		this.endTag = endTag;
		this.regex = regex;
	}

	public T parse(String node) throws InstantiationException, IllegalAccessException, SecurityException {
		T t = clazz.newInstance();
		String[] tags = node.split(regex);
		if (tags == null || tags.length == 0)
			return null;
		for (String tag : tags) {
			tag = tag.trim();
			if (tag == null || tag.isEmpty())
				continue;
			if (endTag.equals(tag))
				continue;
			else {
				String target = "<" + startTag;
				if(tag.contains("<row") ){
					tag = tag.replace(target, "").trim();
				}
				String[] valuePair = tag.split("=");
				String tagKey = valuePair[0];
				tagKey = tagKey.trim();
				char lowerCase = Character.toLowerCase(tagKey.charAt(0));
				String tagField = lowerCase + tagKey.substring(1);
				Field declaredField = null;
				try {
					declaredField = clazz.getDeclaredField(tagField);
				} catch (NoSuchFieldException e) {
					declaredField = null;
				}
				if (declaredField == null)
					continue;
				declaredField.setAccessible(true);
				String valueAs = valuePair[1];
				String value = valueAs.substring(1, valueAs.length());
				declaredField.set(t, value);
			}

		}
		Field declaredField = null;
		try {
			declaredField = clazz.getDeclaredField(startTag);
			if (declaredField != null) {
				declaredField.setAccessible(true);
				declaredField.set(t, node);
			}
		} catch (NoSuchFieldException e) {
			declaredField = null;
		}
		return t;

	}

}

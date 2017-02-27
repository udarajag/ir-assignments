package com.ntu.ir.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ntu.ir.bean.TagPosting;

public class CustomParserTest {

	private CustomParser<TagPosting> parser;

	@Before
	public void setUp() throws Exception {
		parser = new CustomParser<>(TagPosting.class, "row", "/>","\" ");
	}

	@Test
	public void testCustomParser()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String row = " <row Id=\"89\" PostTypeId=\"2\" ParentId=\"79\" "
				+ " CreationDate=\"2008-08-01T14:41:01.110\" Score=\"4\" "
				+ " Body=\"&lt;p&gt;I've had problems with JavaHL in Eclipse Ganymede, when it worked fine in Eclipse Europa. "
				+ " I'm not sure how Aptana is different, but try either upgrading JavaHL or switching "
				+ " to the pure-java SVNKit implementation within the Subclipse config.&lt;/p&gt;&#xA;\" "
				+ "OwnerDisplayName=\"Ted Dziuba\" " + " LastActivityDate=\"2008-08-01T14:41:01.110\""
				+ " CommentCount=\"0\" />";
		TagPosting tp = parser.parse(row);

		assertEquals("89", tp.getId());
		assertEquals("79", tp.getParentId());
		assertEquals("2", tp.getPostTypeId());
		assertEquals(row, tp.getRow());

		row = " <row Id=\"66\" PostTypeId=\"1\" AcceptedAnswerId=\"4521\" "
				+ "CreationDate=\"2008-08-01T13:20:46.890\" Score=\"54\" ViewCount=\"3955\" "
				+ "Body=\"&lt;p&gt;How do you page through a collection in LINQ given that you have "
				+ " a &lt;code&gt;startIndex&lt;/code&gt; and a &lt;code&gt;count&lt;/code&gt;?&lt;/p&gt;&#xA;\" "
				+ "OwnerUserId=\"17\" LastEditorUserId=\"1039608\" LastEditorDisplayName=\"Keith\" "
				+ "LastEditDate=\"2012-05-04T09:03:54.213\" LastActivityDate=\"2016-09-27T06:10:13.900\" "
				+ "Title=\"Paging a collection with LINQ\" Tags=\"&lt;linq&gt;&lt;.net-3.5&gt;\" "
				+ "AnswerCount=\"4\" CommentCount=\"0\" FavoriteCount=\"5\" />";
		tp = parser.parse(row);
		assertEquals("66", tp.getId());
		assertNull(tp.getParentId());
		assertEquals("1", tp.getPostTypeId());
		assertEquals("&lt;linq&gt;&lt;.net-3.5&gt;", tp.getTags());
		assertEquals(row, tp.getRow());
	}

	@Test
	public void testParse() {
		// fail("Not yet implemented");
	}

}

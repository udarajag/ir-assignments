package com.ntu.ir.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ntu.ir.bean.TagPosting;

public class ParserFunctionTest {

	@Test
	public void test() throws Exception {
		ParserFunction2 parser = new ParserFunction2();
		parser.init();
		String row = "<row Id=\"89\" PostTypeId=\"2\" ParentId=\"79\" "
				+ " CreationDate=\"2008-08-01T14:41:01.110\" Score=\"4\" "
				+ " Body=\"&lt;p&gt;I've had problems with JavaHL in Eclipse Ganymede, when it worked fine in Eclipse Europa. "
				+ " I'm not sure how Aptana is different, but try either upgrading JavaHL or switching "
				+ " to the pure-java SVNKit implementation within the Subclipse config.&lt;/p&gt;&#xA;\" "
				+ "OwnerDisplayName=\"Ted Dziuba\" " + " LastActivityDate=\"2008-08-01T14:41:01.110\""
				+ " CommentCount=\"0\" />";
		//System.out.print("\"Hello\"");
		TagPosting post = parser.call(row);
		assertEquals(89, post.getId());
	}

}

package com.ntu.ir.bean;

import java.util.Date;

public class Posting {

	private long id;

	private long postType;

	private long acceptedAnswerId;

	private long score;

	private Date creationDate;

	private String body;

	private long commentCount;

	private int answerCount;

	private String title;

	private long viewCount;

	private long ownerUserId;

	private long parentId;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public long getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostType() {
		return postType;
	}

	public void setPostType(long postType) {
		this.postType = postType;
	}

	public long getAcceptedAnswerId() {
		return acceptedAnswerId;
	}

	public void setAcceptedAnswerId(long acceptedAnswerId) {
		this.acceptedAnswerId = acceptedAnswerId;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	// <row Id="89" PostTypeId="2" ParentId="79"
	// CreationDate="2008-08-01T14:41:01.110"
	// Score="4"
	// Body="&lt;p&gt;I've had problems with JavaHL in Eclipse Ganymede, when it
	// worked fine in Eclipse Europa.
	// I'm not sure how Aptana is different, but try either upgrading JavaHL or
	// switching to the
	// pure-java SVNKit implementation within the Subclipse
	// config.&lt;/p&gt;&#xA;"
	// OwnerDisplayName="Ted Dziuba" LastActivityDate="2008-08-01T14:41:01.110"
	// CommentCount="0" />
	// <row Id="66" PostTypeId="1" AcceptedAnswerId="4521"
	// CreationDate="2008-08-01T13:20:46.890" Score="54" ViewCount="3955"
	// Body="&lt;p&gt;How do you page through a collection in LINQ given that
	// you have a &lt;code&gt;startIndex&lt;/code&gt; and a
	// &lt;code&gt;count&lt;/code&gt;?&lt;/p&gt;&#xA;"
	// OwnerUserId="17" LastEditorUserId="1039608" LastEditorDisplayName="Keith"
	// LastEditDate="2012-05-04T09:03:54.213"
	// LastActivityDate="2016-09-27T06:10:13.900"
	// Title="Paging a collection with LINQ" Tags="&lt;linq&gt;&lt;.net-3.5&gt;"
	// AnswerCount="4" CommentCount="0" FavoriteCount="5" />

}

package com.kh.jsp.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	private int replyNo;
	private String replyContent;
	private int replyBoardNo;
	private int replyWriter;
	private Date createDate;
	private String status;

	public static Reply insertCreateReply(String replyContent , int replyBoardNo , int replyWriter) {

		Reply r = new Reply();
	
		r.setReplyContent(replyContent);
		r.setReplyBoardNo(replyBoardNo);
		r.setReplyWriter(replyWriter);
		
		return r;
	}
	
	
	public static Reply selectCreateReply(int replyWriter, String replyContent, Date createDate) {

		Reply r = new Reply();

		r.setReplyWriter(replyWriter);
		r.setReplyContent(replyContent);
		r.setCreateDate(createDate);

		return r;
	}
	
	
	
}

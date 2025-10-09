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
public class Board {

	private int boardNo;
	private int boardType;
	private int categoryNo;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private String memberName;
	private int count;
	private Date createDate;
	private String status;

	public static Board selectCreateBoard(int boardId, int categoryNo, String boardTitle, String memberName, int count,
			Date create_Date) {

		Board b = new Board();

		b.setBoardNo(boardId);
		b.setCategoryNo(categoryNo);
		b.setBoardTitle(boardTitle);
		b.setMemberName(memberName);
		b.setCount(count);
		b.setCreateDate(create_Date);

		return b;

	}
	
	
	public static Board selectCreateDetailBoard(int boardId, int categoryNo, String boardTitle, int boardWriter, String memberName, int count,
			Date create_Date) {
		
		Board b = new Board();

		b.setBoardNo(boardId);
		b.setCategoryNo(categoryNo);
		b.setBoardTitle(boardTitle);
		b.setBoardWriter(boardWriter);
		b.setMemberName(memberName);
		b.setCount(count);
		b.setCreateDate(create_Date);

		return b;

	}

	public static Board insertBoard(int categoryNo, String boardTitle, String boardContent, int boardWriter) {

		Board b = new Board();
		b.setCategoryNo(categoryNo);
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);
		b.setBoardWriter(boardWriter);

		return b;

	}

	public static Board updateCreateBoard(int boardNo, int categoryNo, String boardTitle, String boardContent) {
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setCategoryNo(categoryNo);
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);

		return b;

	}


}

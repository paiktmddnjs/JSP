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
	private int count;
	private Date createDate;
	private String status;

	public static Board selectCreateBoard(int boardId, int categoryNo, 
			String boardTitle, int boardWriter, int count, Date create_Date) {
		
		Board b = new Board();
		
		b.setBoardNo(boardId);
		b.setCategoryNo(categoryNo);
		b.setBoardTitle(boardTitle);
		b.setBoardWriter(boardWriter);
		b.setCount(count);
		b.setCreateDate(create_Date);

		return b;

	}
}

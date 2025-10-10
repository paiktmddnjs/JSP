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
	public class FileUpload {
		
		private int boardNo;
		private int fileNo;
		private int fileBoardNo;
		private String fileOriginalName;
		private String fileChangeName;
		private String filePath;
		private Date uploadDate;
		private String fileLevel;
		private String status;
	

		public static FileUpload insertFile(String fileOriginalName, String filePath) {
			
			FileUpload f = new FileUpload();
		
			f.setFileOriginalName(fileOriginalName);
			f.setFilePath(filePath);
		
			return f;

		}
		
		public static FileUpload selectCreateFile(int boardNo, String fileOriginalName) {
			
			FileUpload f = new FileUpload();
			
			f.setBoardNo(boardNo);
			f.setFileOriginalName(fileOriginalName);
			
			return f;
			
		}

}

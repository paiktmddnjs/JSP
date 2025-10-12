package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jsp.model.dao.FileDao;

import com.kh.jsp.model.vo.FileUpload;

public class FileService {

	public int insertFile(int boardNo, FileUpload f) {

		Connection conn = getConnection();

		int result = new FileDao().insertFile(boardNo, f, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}

	public List<FileUpload> getFilesByBoardNo(int boardNo) {

		Connection conn = getConnection();

		List<FileUpload> result = new FileDao().selectFile(boardNo, conn);

		if (result != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}

	public int updateFile(FileUpload updateFile) {

		Connection conn = getConnection();

		int result = new FileDao().updateFile(updateFile, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public FileUpload selectFileForUpdate(int boardNo) {

		Connection conn = getConnection();
		
		FileUpload result = new FileDao().selectFileForUpdate(boardNo, conn);

		if (result != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}

}

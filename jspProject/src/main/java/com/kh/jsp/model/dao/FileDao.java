package com.kh.jsp.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jsp.common.JDBCTemplate;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.FileUpload;

public class FileDao {

	private Properties prop = new Properties();

	public FileDao() {
		super();

		try (InputStream is = JDBCTemplate.class.getClassLoader().getResourceAsStream("db/sql/file-mapper.xml")) {
			if (is == null) {
				throw new IOException("Resource not found: /db/sql/file-mapper.xml");
			}
			prop.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertFile(int boardNo, FileUpload f, Connection conn) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertFile");

		try {
			// 테이블르 컬럼에 맞게 적절히 매핑하는 것이다.
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			pstmt.setString(2, f.getFileOriginalName());
			pstmt.setString(3, f.getFilePath());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public List<FileUpload> selectFile(int boardNo, Connection conn) {

		List<FileUpload> fileList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFileByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				FileUpload file = FileUpload.selectCreateFile(rset.getInt("BOARD_NO"), rset.getString("ORIGIN_NAME"));

				fileList.add(file);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return fileList;

	}

	public int updateFile(FileUpload ud, Connection conn) {

		System.out.println("orignalName: " + ud.getFileOriginalName());
		System.out.println("filePath: " + ud.getFilePath());
		System.out.println("refBno: " + ud.getBoardNo());
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFile");

		try {
			// 테이블르 컬럼에 맞게 적절히 매핑하는 것이다.
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ud.getFileOriginalName());
			pstmt.setString(2, ud.getFilePath());
			pstmt.setInt(3, ud.getBoardNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public FileUpload selectFileForUpdate(int boardNo, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FileUpload file = null; // 함수 시작 시 선언
		String sql = prop.getProperty("selectFileByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			if (rset.next()) { // rs가 다음 행으로 이동 가능한지 확인
				file = FileUpload.selectCreateFile(rset.getInt("BOARD_NO"), rset.getString("ORIGIN_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return file;
	}
	
	public int insertFileUpload( FileUpload at, Connection conn) {
		//새로운 Attachment -> insert -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("insertAttachment");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileOriginalName());
			pstmt.setString(2, at.getFileChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}

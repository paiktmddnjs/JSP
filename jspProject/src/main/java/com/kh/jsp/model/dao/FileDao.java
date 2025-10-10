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

		String path = JDBCTemplate.class.getResource("/db/sql/file-mapper.xml").getPath();

		try (InputStream is = JDBCTemplate.class.getResourceAsStream("/db/sql/file-mapper.xml")) {
			if (is == null) {
				throw new IOException("Resource not found");
			}
			prop.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	public int insertFile(int boardNo ,FileUpload f, Connection conn) {
		
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
	
	
	
	public List<FileUpload> selectFile(int boardNo , Connection conn) {
		
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
	
	
}

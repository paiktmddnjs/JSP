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
import com.kh.jsp.model.vo.Reply;

public class ReplyDao {

	
	private Properties prop = new Properties();

	public ReplyDao() {
		super();

		String path = JDBCTemplate.class.getResource("/db/sql/reply-mapper.xml").getPath();

		try (InputStream is = JDBCTemplate.class.getResourceAsStream("/db/sql/reply-mapper.xml")) {
			if (is == null) {
				throw new IOException("Resource not found");
			}
			prop.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertReply(Reply r, Connection conn) {
		// insert -> 처리된 행 수 -> 반환

		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");

		try {
			// 테이블르 컬럼에 맞게 적절히 매핑하는 것이다.
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getReplyContent()); // 제목
			pstmt.setInt(2, r.getReplyBoardNo()); // 내용
			pstmt.setInt(3, r.getReplyWriter()); // 
		
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	public List<Reply> selectReply(int boardNo, Connection conn) {

		List<Reply> replyList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReply");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Reply reply = Reply.selectCreateReply(rset.getInt("REPLY_WRITER"), rset.getString("REPLY_CONTENT"),
						rset.getDate("CREATE_DATE"));
				// 리스트에 추가하는 등 후속 처리
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return replyList;
	}
	
}

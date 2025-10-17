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
import com.kh.jsp.model.vo.PageInfo;
import com.kh.jsp.model.vo.Reply;

public class BoardDao {

	private Properties prop = new Properties();

	public BoardDao() {
		super();

		String path = JDBCTemplate.class.getResource("/db/sql/board-mapper.xml").getPath();

		try (InputStream is = JDBCTemplate.class.getResourceAsStream("/db/sql/board-mapper.xml")) {
			if (is == null) {
				throw new IOException("Resource not found");
			}
			prop.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectBoard(Connection conn, PageInfo pi) {

		List<Board> boardList = new ArrayList<>();
		PreparedStatement pstmt = null; // SQL 실행을 위한 객체
		ResultSet rset = null; // SQL결과 저장 객체
		String sql = prop.getProperty("selectBoard");

		try {

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt = conn.prepareStatement(sql); // SQL문을 컴파일하고 실행 준비 상태를 저장
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery(); // SQL을 실행하고 그 결과를 저장

			while (rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setCategoryNo(rset.getInt("CATEGORY_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setMemberName(rset.getString("MEMBER_NAME"));
				b.setCount(rset.getInt("COUNT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return boardList;
	}

	public int insertBoard(Board b, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("insertBoard");
		String boardNoSql = prop.getProperty("getBoardNo"); // 이 쿼리가 "SELECT SEQ_BNO.CURRVAL FROM DUAL" 인지 확인하세요.

		try {
			// 게시글 INSERT
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getCategoryNo());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardWriter());

			result = pstmt.executeUpdate();

			if (result > 0) {
				// 시퀀스 번호 조회
				try (PreparedStatement seqStmt = conn.prepareStatement(boardNoSql);
						ResultSet rsSeq = seqStmt.executeQuery()) {

					if (rsSeq.next()) { // rs.next() 필수
						int generatedBoardNo = rsSeq.getInt(1);
						b.setBoardNo(generatedBoardNo);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			// 필요하다면 rs도 close() 해주세요
		}

		return result;
	}

	public Board selectDetailBoard(int boardNo, Connection conn) {

		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectBoardByNo");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				board = Board.selectCreateDetailBoard(rset.getInt("BOARD_NO"), rset.getInt("CATEGORY_NO"),
						rset.getString("BOARD_TITLE"), rset.getInt("BOARD_WRITER"), rset.getString("MEMBER_NAME"),
						rset.getInt("COUNT"), rset.getDate("CREATE_DATE"));
				// boardContent 컬럼도 필요하면 추가하세요
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	public boolean incrementViewCount(int boardNo, Connection conn) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("incrementViewCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
	}

	public int selectAllBoardCount(Connection conn) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllBoardCount");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				listCount = rset.getInt("Count");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public int updateBoard(Board b, Connection conn) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, b.getCategoryNo());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public boolean deleteBoard(int BoardNo, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardNo);

			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return false;

	}

	public int insertReply(Connection conn, Reply r) {
		// 새로운 Reply -> insert -> int(1 또는 0)

		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getReplyBoardNo());
			pstmt.setInt(3, r.getReplyWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Board> selectThumnailList(Connection conn) {
		// select -> ResultSet(여러개) -> ArrayList
		ArrayList<Board> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectThumnailList");

		try {

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setCount(rset.getInt("COUNT"));
				b.setThumbnailImg(rset.getString("THUMBNAIL_IMG"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

}

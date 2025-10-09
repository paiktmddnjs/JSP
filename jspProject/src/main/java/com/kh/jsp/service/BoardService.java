package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Board;


public class BoardService {

	public List<Board> getBoardList() {
		Connection conn = getConnection();

		List<Board> result = new BoardDao().selectBoard(conn);
		if (!result.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();

		int result = new BoardDao().insertBoard(b, conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public Board getBoardByNo(int bn) {

		Connection conn = getConnection();

		Board result = new BoardDao().selectDetailBoard(bn, conn);
		if (result != null) {
			// 조회 성공 시 트랜잭션 커밋
			commit(conn);
		} else {
			// 실패하거나 게시글 없음
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public boolean incrementViewCount(int bn) {
		Connection conn = getConnection();

		boolean result = new BoardDao().incrementViewCount(bn, conn);
		if (result) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}
	
	
	
	public Board updateBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().updateBoard(b, conn);
		
		Board updateBoard = null;
		if (result > 0) {
			commit(conn);
			 updateBoard = new BoardDao().selectDetailBoard(b.getBoardNo(), conn);
		} else {
			rollback(conn);

		}
		close(conn);
		return updateBoard;
		
	}
	
	public boolean deleteBoard(int BoardNo) {
	Connection conn = getConnection();
		
		boolean result = new BoardDao().deleteBoard(BoardNo, conn);
		
		if(result) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
}

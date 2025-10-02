package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.dao.MemberDao;
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
}

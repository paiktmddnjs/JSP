package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.dao.ReplyDao;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Reply;

public class ReplyService {

	
	public int insertReply(Reply r) {
		Connection conn = getConnection();

		int result = new ReplyDao().insertReply(r, conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public List<Reply> getReplyList(int boardNo) {
		
		Connection conn = getConnection();

		List<Reply> result = new ReplyDao().selectReply(boardNo , conn);
		
		if (!result.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
}

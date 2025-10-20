package com.kh.mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.Template;
import com.kh.mybatis.model.vo.PageInfo;
import com.kh.mybatis.model.dao.BoardDao;
import com.kh.mybatis.model.vo.Attachment;
import com.kh.mybatis.model.vo.Board;

public class BoardService {
	private BoardDao boardDao = new BoardDao();

	public int selectAllBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();

		int listCount = boardDao.selectAllBoardCount(sqlSession);

		sqlSession.close();

		return listCount;
	}

	public int selectAllBoardCount(HashMap<String, String> searchMap) {
		SqlSession sqlSession = Template.getSqlSession();

		int listCount = boardDao.selectAllBoardCount(sqlSession);

		sqlSession.close();

		return listCount;
	}

	public Board selectBoardByBoardNo(int boardNo) {

		SqlSession sqlSession = Template.getSqlSession();

		Board result = boardDao.selectBoardByBoardNo(sqlSession, boardNo);

		sqlSession.close();

		return result;

	}

	public ArrayList<Board> selectAllBoard(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();

		ArrayList<Board> list = boardDao.selectAllBoard(sqlSession, pi);

		sqlSession.close();

		return list;
	}
	
	
	public ArrayList<Board> selectAllBoard(PageInfo pi , HashMap<String, String> searchMap) {
		SqlSession sqlSession = Template.getSqlSession();

		ArrayList<Board> list = boardDao.selectAllBoard(sqlSession, pi ,searchMap);

		sqlSession.close();

		return list;
	}
	
	

	public int insertBoard(Board b, Attachment at) {

		SqlSession sqlSession = Template.getSqlSession();

		b.setBoardType(1);
		int result = boardDao.insertBoard(sqlSession, b);

		if (at != null) {
			result *= boardDao.insertAttachment(sqlSession, at);
		}

		sqlSession.close();

		return result;
	}

	public int increaseCount(int boardNo) {

		SqlSession sqlSession = Template.getSqlSession();

		int result = new BoardDao().increaseCount(sqlSession, boardNo);

		sqlSession.close();
		return result;
	}

	public Attachment selectAttachment(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();

		Attachment at = new BoardDao().selectAttachment(sqlSession, boardNo);

		sqlSession.close();
		return at;
	}

}
package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.bo")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ListController() {
		super();
	
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// board목록을 가져와서 응답페이지로 전달
		List<Board> boardList = new BoardService().getBoardList();

		// 게시판 목록을 request에 저장
		request.setAttribute("boardList", boardList);


		// 포워딩
		request.getRequestDispatcher("/WEB-INF/views/board/listView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
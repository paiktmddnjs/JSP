package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.PageInfo;
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
		
		
		//---------------- 페이징 처리 ---------------------
		
		int currentPage = request.getParameter("cpage") != null ? Integer.parseInt(request.getParameter("cpage")) : 1; // 지금 보여줄 페이지( 사용자가 요청한 페이지)
		int listCount = new BoardService().selectAllBoardCount(); // 현재 총 게시글 수
		int pageLimit = 5; // 페이지 버튼을 몇개 보여줄 것인갓
		int boardLimit = 5; // 한 페이지에 데이터를 몇개 보여줄 것인가?

		// board목록을 가져와서 응답페이지로 전달

		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit);
		
		
		// board목록을 가져와서 응답페이지로 전달
		List<Board> boardList = new BoardService().getBoardList(pi);

		// 게시판 목록을 request에 저장
		request.setAttribute("boardList", boardList);
		request.setAttribute("pi", pi);

		// 포워딩
		request.getRequestDispatcher("/WEB-INF/views/board/listView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
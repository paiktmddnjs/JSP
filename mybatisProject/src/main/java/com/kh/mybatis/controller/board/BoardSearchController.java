package com.kh.mybatis.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.mybatis.model.vo.Board;
import com.kh.mybatis.model.vo.PageInfo;
import com.kh.mybatis.service.BoardService;

@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardSearchController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String condition = request.getParameter("condition"); // writer | title | content
		String keyWord = request.getParameter("keyWord"); // 사용자가 입력한 검색어

		
		HashMap<String, String> searchMap = new HashMap<>();
		searchMap.put("keyWord", keyWord);
		searchMap.put("condition", condition);
		
		//---------------------------------- 페이징 처리 -----------------------------------
		
		int currentPage = request.getParameter("cpage") != null ? Integer.parseInt(request.getParameter("cpage")) : 1; // 지금
																														// 보여줄																														// 페이지(사용자가
																														// 요청	
		int listCount = new BoardService().selectAllBoardCount();// 현재 총 게시글 수

		PageInfo pi = new PageInfo(currentPage, listCount, 5, 5);

		ArrayList<Board> list = new BoardService().selectAllBoard(pi, searchMap);

		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("/WEB-INF/views/board/listView.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

/**
 * Servlet implementation class ThumbnailListController
 */
@WebServlet("/list.th")
public class ThumbnailListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThumbnailListController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Board> list = new BoardService().selectThumnailList();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/thumbnailListView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

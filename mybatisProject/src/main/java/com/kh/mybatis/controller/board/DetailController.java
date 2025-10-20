package com.kh.mybatis.controller.board;

import java.io.IOException;

import com.kh.mybatis.model.vo.Attachment;
import com.kh.mybatis.model.vo.Board;
import com.kh.mybatis.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailController
 */
@WebServlet("/detail.bo")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//board정보를 조회해서 detailView.jsp을 응답
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		BoardService boardService = new BoardService();
		
		//board의 조회수 1 증가
		int result = boardService.increaseCount(boardNo);
		Board board = boardService.selectBoardByBoardNo(boardNo);
		
		System.out.println(result);
		System.out.println(board);
		if(result > 0 && board != null) {
			
			//at조회 -> request담기
			Attachment at = boardService.selectAttachment(board.getBoardNo());
			
			request.setAttribute("board", board);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("/WEB-INF/views/board/detailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "정상적인 접근이 아닙니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		doGet(request, response);
	}

}
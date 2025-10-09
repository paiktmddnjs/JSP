package com.kh.jsp.controller.board;

import java.io.IOException;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/myDetailView.bo")
public class DetailUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DetailUpdateView() {
        super();
      
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) {
			session.setAttribute("alertMsg", "로그인을 먼저 하십시요!.");
			response.sendRedirect(request.getContextPath());
		return;
		}
		
		  // 게시글 번호 받아오기
	    String boardNoStr = request.getParameter("boardNo");
	    if (boardNoStr == null || boardNoStr.trim().isEmpty()) {
	        // 적절한 에러 처리
	        throw new ServletException("게시글 번호가 전달되지 않았습니다.");
	    }
	    int boardNo = Integer.parseInt(boardNoStr);

	    // 게시글 조회하는 서비스 호출 (예시)
	    Board board = new BoardService().getBoardByNo(boardNo);
	    request.setAttribute("board", board);

	    // 포워딩
	    request.getRequestDispatcher("/WEB-INF/views/board/updateForm.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
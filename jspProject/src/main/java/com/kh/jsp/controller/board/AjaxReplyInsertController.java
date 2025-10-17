package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.jsp.model.vo.Member;
import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.BoardService;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	
    public AjaxReplyInsertController() {
        super();
        
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 보내준 정보를 받아서 Reply 저장 -> int 그대로 반환
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String replyContent = request.getParameter("content");
		int memberNo = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberNo();
		
		Reply r = new Reply();
		r.setReplyBoardNo(boardNo);
		r.setReplyContent(replyContent);
		r.setReplyWriter(memberNo);
		
		int result = new BoardService().insertReply(r);
		response.getWriter().print(result);
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

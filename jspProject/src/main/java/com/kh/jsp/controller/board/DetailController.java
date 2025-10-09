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
import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.BoardService;
import com.kh.jsp.service.ReplyService;

/**
 * Servlet implementation class EnrollFromController
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
			String boardNoStr = request.getParameter("boardNo");
	        int boardNo = Integer.parseInt(boardNoStr);

	        // 게시물 조회 서비스 호출 (예: BoardService)
	        BoardService service = new BoardService();
	        Board board = service.getBoardByNo(boardNo);

	        if (board != null) {
	            // 조회수 증가 처리
	            service.incrementViewCount(boardNo);
	            // 조회수 반영 후 다시 조회 또는 바로 업데이트 필요

	            // 게시물 데이터를 request에 담아서 전달
	            request.setAttribute("board", board);
	            
	            
	            List<Reply> replyList = new ReplyService().getReplyList(boardNo);

	    		// 게시판 목록을 request에 저장
	    		request.setAttribute("replyList", replyList);

	            
	            // 상세페이지 포워딩
	            request.getRequestDispatcher("/WEB-INF/views/board/detailView.jsp").forward(request, response);
	        } else {
	            // 게시물이 존재하지 않으면 리스트로 이동 또는 에러 페이지 이동
	        	request.getSession().setAttribute("alertMsg", "게시글이 존재하지않습니다!");
	            response.sendRedirect(request.getContextPath() + "/list.bo");
	        }
	    }
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
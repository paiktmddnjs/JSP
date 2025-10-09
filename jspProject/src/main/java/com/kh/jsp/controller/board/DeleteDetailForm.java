package com.kh.jsp.controller.board;

import java.io.IOException;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete.bo")
public class DeleteDetailForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDetailForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		if (loginMember == null) {
			request.setAttribute("errorMsg", "로그인을 먼저 하시오!.");
			response.sendRedirect(request.getContextPath() + "/menubar.jsp");
			return;
		}
		
		
		String boardNoStr = request.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		Board existingBoard = new BoardService().getBoardByNo(boardNo);

		if (existingBoard == null) {
		    // 게시글 없거나 조회 실패
		    request.setAttribute("errorMsg", "존재하지 않는 게시물입니다.");
		    request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		    return;
		}

		// 현재 로그인한 사용자의 ID 또는 회원번호 비교
		int currentUserId = existingBoard.getBoardWriter(); // 또는 getMemberNo()

		if (loginMember.getMemberNo() != currentUserId) {
		    // 권한 없음
		    request.setAttribute("errorMsg", "권한이 없습니다.");
		    request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		    return;
		}
		


		boolean deleteSuccess = new BoardService().deleteBoard(boardNo);

		if (deleteSuccess) {
			// 삭제 성공 시 목록 페이지 이동
			session.setAttribute("alertMsg", "성공적으로 삭제하였습니다.");
			response.sendRedirect(request.getContextPath() + "/list.bo");

		} else {
			// 실패 시 실패 메시지 또는 다른 처리
			request.setAttribute("errorMsg", "존재하지 않는 게시물입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
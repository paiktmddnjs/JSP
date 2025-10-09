package com.kh.jsp.controller.reply;

import java.io.IOException;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.BoardService;
import com.kh.jsp.service.ReplyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/insert.re")
public class insertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public insertReplyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		String boardNoStr = request.getParameter("boardNo");

		if (boardNoStr == null || boardNoStr.trim().isEmpty()) {
			// 적절한 조치: 오류 메시지 보여주기, 또는 이전 페이지로 이동
			request.setAttribute("errorMsg", "게시글 번호가 전달되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
			return;
		}

		int boardNo = Integer.parseInt(boardNoStr);
		Board currentBoard = new BoardService().getBoardByNo(boardNo);

		if (loginMember == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/menubar.jsp");
			return;
		}
		// member 추가

		// 전달받은 데이터를 추출
		String replyContent = request.getParameter("replyContent");
		if (replyContent == null || replyContent.trim().isEmpty()) {
			// 적절한 조치: 오류 메시지 보여주기, 또는 이전 페이지로 이동
			request.setAttribute("errorMsg", "내용이 전달되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
			return;
		}

		int currentBoardNo = currentBoard.getBoardNo();
		int replyWriter = loginMember.getMemberNo();

		// String[] -> string

		Reply r = Reply.insertCreateReply(replyContent, currentBoardNo, replyWriter);

		int result = new ReplyService().insertReply(r);

		if (result > 0) {

			request.getSession().setAttribute("alertMsg", "댓글을 등록하였습니다!");
			response.sendRedirect(request.getContextPath() + "/detail.bo?boardNo=" + boardNo);
		} else {
			request.setAttribute("errorMsg", "댓글등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");

		}
	}

}
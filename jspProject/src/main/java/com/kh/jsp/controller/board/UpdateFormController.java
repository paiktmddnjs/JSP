package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;



@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 1024 * 1024 * 10,
	    maxRequestSize = 1024 * 1024 * 15
	)


@WebServlet("/update.bo")
public class UpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateFormController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (loginMember == null) {
		    // 세션에 회원 정보 없음
			request.setAttribute("errorMsg", "로그인을 먼저 하세요!");
		    response.sendRedirect(request.getContextPath() + "/menubar.jsp");
		    return;
		}
		
		
		String boardNoStr = request.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		String categoryStr = request.getParameter("category");
		int category = Integer.parseInt(categoryStr);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 파일 업로드 처리도 필요 (Commons FileUpload 등 사용)
		// DB 업데이트 수행

		
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
		
		
		
		
		Board updateBoard = Board.updateCreateBoard(boardNo, category, title, content);

		updateBoard = new BoardService().updateBoard(updateBoard);
		

		if (updateBoard == null) {
			request.setAttribute("errorMsg", "상세게시판 수정에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		} else {
			
			session.setAttribute("updateBoard", updateBoard);
			session.setAttribute("alertMsg", "성공적으로 수정되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/list.bo");
		}
	}

}

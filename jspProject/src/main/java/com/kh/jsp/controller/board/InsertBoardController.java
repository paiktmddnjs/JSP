package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.FileUpload;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;
import com.kh.jsp.service.FileService;

/**
 * Servlet implementation class ListController
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 20 // 20MB
)

@WebServlet("/insert.bo")
public class InsertBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertBoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청 파라미터 추출
		String categoryNoStr = request.getParameter("category");

		if (categoryNoStr == null || categoryNoStr.trim().isEmpty()) {
			// 기본값 지정하거나 에러 처리
			// 예) 기본값으로 0 할당
			categoryNoStr = "10";
		}

		int categoryNo = Integer.parseInt(categoryNoStr);
		String boardTitle = request.getParameter("title");
		String boardContent = request.getParameter("content");

		// 로그인한 사용자 정보 가져오기 (세션에서 Member 객체 받아오기)
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		if (loginMember == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/menubar.jsp");
			return;
		}

		// 작성자 번호 또는 닉네임 등 필요 정보
		int writerNo = loginMember.getMemberNo(); // 또는 적절한 필드 사용

		// 게시글 객체 생성
		Board b = Board.insertBoard(categoryNo, boardTitle, boardContent, writerNo);

		// 게시글 등록 서비스 호출
		int result = new BoardService().insertBoard(b);

		if (result > 0) {

			int boardNo = b.getBoardNo();

			Part filePart = request.getPart("upfile");
			String fileName = null;
			try {
				if (filePart != null && filePart.getSize() > 0) {
					fileName = getFileName(filePart);

					if (fileName == null) {

						System.out.println("파일이름이 널값입니다!");
					}else {
						System.out.println(fileName);
					}

					String uploadPath = getServletContext().getRealPath("/upload");
					;
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists())
						uploadDir.mkdirs();

					String filePath = uploadPath + File.separator + fileName;
					filePart.write(filePath);
					// 파일 이름이나 경로를 저장하는 로직 필요
					FileUpload f = FileUpload.insertFile(fileName, uploadPath);
					f.setFileBoardNo(boardNo);

					int fileResult = new FileService().insertFile(boardNo, f);

					if (fileResult > 0) {
						request.getSession().setAttribute("alertMsg", "파일첨부 성공!!");

					} else
						request.getSession().setAttribute("alertMsg", "파일첨부 실패 ㅠㅠ");

				}
			} catch (IOException e) {
				e.printStackTrace();
				request.getSession().setAttribute("alertMsg", "파일 업로드에 실패했습니다. 다시 시도해주세요.");
				request.getRequestDispatcher("/WEB-INF/views/board/enrollForm.jsp").forward(request, response);
			}

			request.getSession().setAttribute("alertMsg", "성공적으로 글쓰기를 완료하였습니다.");
			response.sendRedirect(request.getContextPath() + "/list.bo");
		} else {
			// 실패 시 에러 페이지 또는 다시 작성 페이지
			request.setAttribute("errorMsg", "게시글 작성 실패");
			request.getRequestDispatcher("/WEB-INF/views/board/enrollFrom.jsp").forward(request, response);
		}

	}

	private String getFileName(Part part) {
		String header = part.getHeader("content-disposition");
		if (header != null) {
			// 헤더 내용을 분리
			String[] elements = header.split(";");
			for (String element : elements) {
				element = element.trim();
				if (element.startsWith("filename")) {
					// filename="fileName" 형태이기 때문에 '='로 분리
					String[] namePair = element.split("=", 2);
					if (namePair.length == 2) {
						String filename = namePair[1].trim().replace("\"", "");
						return filename;
					}
				}
			}
		}
		return null;
	}

}
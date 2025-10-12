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

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 15)

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
		Part filePart = request.getPart("upfile");

		String fileName = null;

		try {

			if (filePart != null && filePart.getSize() > 0) {

				// ----------- 존재하는 파일이 없기에 새로 추가 -----------
				
				FileUpload selectFile = FileUpload.selectFileForUpdate(boardNo);
				System.out.println("조회된 파일 : " + selectFile);

				if (selectFile.getFileOriginalName() == null) {

					fileName = getFileName(filePart);

				
					String uploadPath = getServletContext().getRealPath("/upload");

					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists())
						uploadDir.mkdirs();

					String filePath = uploadPath + File.separator + fileName;
					filePart.write(filePath);
					// 파일 이름이나 경로를 저장하는 로직 필요
					FileUpload f = FileUpload.insertFile(fileName, uploadPath);

					int fileResult = new FileService().insertFile(boardNo, f);
					
					if (fileResult <= 0) {
					
						request.setAttribute("errorMsg", "파일 추가에 실패!");
						request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
						return;
					}

				}

				// --------- 존재하는 파일에 대한 수정 -----------
				
				else if (selectFile.getFileOriginalName() != null) {

					fileName = getFileName(filePart);


					String uploadPath = getServletContext().getRealPath("/upload");

					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists())
						uploadDir.mkdirs();

					String filePath = uploadPath + File.separator + fileName;
					filePart.write(filePath);
					// 파일 이름이나 경로를 저장하는 로직 필요

					FileUpload updateFile = FileUpload.updateCreateFile(boardNo, fileName, uploadPath);

					int fileResult = new FileService().updateFile(updateFile);
					System.out.println("fileResult: " + fileResult);
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute("alertMsg", "파일 업로드에 실패했습니다. 다시 시도해주세요.");
			request.getRequestDispatcher("/WEB-INF/views/board/enrollForm.jsp").forward(request, response);
		}

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

	private String getFileName(Part part) {
		// 1. 'content-disposition' 헤더 정보를 가져옵니다.(메타 데이터에서)
		String header = part.getHeader("content-disposition");

		// 2. 헤더가 null이 아니면 계속 진행
		if (header != null) {
			// 3. 헤더를 세미콜론(;) 기준으로 분리해서 각 요소를 배열에 저장
			String[] elements = header.split(";");

			// 4. 분리한 각 요소에 대해서 반복
			for (String element : elements) {
				// 요소 좌우 공백 제거
				element = element.trim();

				// 5. 만약 요소가 "filename"으로 시작하면 해당 요소가 파일 이름 정보를 담고 있다고 판단
				if (element.startsWith("filename")) {

					// 6. 'filename="somefile.txt"' 형태에서 '=' 기준으로 두 부분으로 분리
					String[] namePair = element.split("=", 2);

					// 7. 배열 크기 확인 후 두 번째 요소(= 파일 이름 부분)를 가져옴
					if (namePair.length == 2) {
						// 8. 파일명에서 앞뒤 공백 제거하고, 쌍따옴표(") 문자 제거
						String filename = namePair[1].trim().replace("\"", "");

						// 9. 파일명 반환
						return filename;
					}
				}
			}
		}

		// 헤더가 없거나 filename을 찾지 못한 경우 null 반환
		return null;
	}

}

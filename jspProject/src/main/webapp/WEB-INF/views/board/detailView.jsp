<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 상세보기</title>

<style>
.board-container {
	max-width: 1000px;
	margin: 50px auto;
	padding: 2rem;
}

.board-card {
	background: white;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	padding: 2rem;
	margin-bottom: 2rem;
}

.board-card h2 {
	text-align: center;
	color: #333;
	margin-bottom: 2rem;
	padding-bottom: 1rem;
	border-bottom: 2px solid #4b89fc;
}

.detail-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 2rem;
}

.detail-table th, .detail-table td {
	padding: 1rem;
	border: 1px solid #e0e0e0;
}

.detail-table th {
	background-color: #f8f9fa;
	font-weight: 500;
	color: #555;
	width: 120px;
	text-align: center;
}

.detail-table td {
	background-color: white;
}

.content-area {
	min-height: 200px;
	padding: 1rem;
	line-height: 1.6;
}

.button-group {
	display: flex;
	justify-content: center;
	gap: 0.5rem;
	margin-top: 2rem;
}

.reply-section {
	background: white;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	padding: 2rem;
}

.reply-table {
	width: 100%;
	border-collapse: collapse;
}

.reply-table th, .reply-table td {
	padding: 1rem;
	border: 1px solid #e0e0e0;
}

.reply-table thead {
	background-color: #f8f9fa;
}

.reply-table thead th {
	font-weight: 500;
	color: #555;
	text-align: center;
}

.reply-table textarea {
	width: 100%;
	padding: 0.5rem;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 0.95rem;
	font-family: "Noto Sans KR", sans-serif;
	resize: none;
}

.reply-table tbody td {
	text-align: center;
}

.reply-table tbody tr:hover {
	background-color: #f8f9fa;
}

.reply-btn {
	width: 100%;
	height: 100%;
	min-height: 80px;
}
</style>
</head>
<body onload="init(${board.boardNo})"> <!-- 서버측에서 boarNo을 받아와서 해당 번호의 상세페이지를 보여준다. -->

	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />

	<div class="board-container">
		<div class="board-card">
			<h2>일반게시글 상세보기</h2>

			<table class="detail-table">
				<tr>
					<th>카테고리</th>
					<td><c:choose>
							<c:when test="${board.categoryNo == 10}">공통</c:when>
							<c:when test="${board.categoryNo == 20}">운동</c:when>
							<c:when test="${board.categoryNo == 30}">등산</c:when>
							<c:when test="${board.categoryNo == 40}">게임</c:when>
							<c:when test="${board.categoryNo == 50}">낚시</c:when>
							<c:when test="${board.categoryNo == 60}">요리</c:when>
							<c:when test="${board.categoryNo == 70}">기타</c:when>
						</c:choose></td>
					<th>제목</th>
					<td colspan="3">${board.boardTitle}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${board.memberName}</td>
					<th>작성일</th>
					<td>${board.createDate}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<div class="content-area">${board.boardContent}</div>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><c:choose>
							<c:when test="${not empty files}">
								<c:forEach var="file" items="${files}">
									<a
										href="${pageContext.request.contextPath}/upload/${file.fileOriginalName}"
										target="_blank"> ${file.fileOriginalName}</a>
									<br />
								</c:forEach>
							</c:when>
							<c:otherwise>
            첨부파일이 없습니다.
        </c:otherwise>
						</c:choose></td>
				</tr>
			</table>

			<div class="button-group">
				<a href="${pageContext.request.contextPath}/list.bo"
					class="btn btn-primary">목록가기</a> <a
					href="${pageContext.request.contextPath}/myDetailView.bo?boardNo=${board.boardNo}"
					class="btn btn-warning">수정하기</a> <a
					href="${pageContext.request.contextPath}/delete.bo?boardNo=${board.boardNo}"
					class="btn btn-danger">삭제하기</a>
			</div>
		</div>

		<div class="reply-section">
			<table class="reply-table">
				<thead>
					<tr>
						<th width="120">댓글작성</th>
<<<<<<< HEAD
						<form action="${pageContext.request.contextPath}/insert.re" method="post">
=======
						<form action="${pageContext.request.contextPath}/insert.re"
							method="post" style="display: inline;">
>>>>>>> b2d295c5e22bcce19af462d10822554ab94f1070
							<input type="hidden" name="boardNo" value="${board.boardNo}">
							<td><textarea id="reply-content" name="replyContent"
									cols="50" rows="3"></textarea></td>
							<td width="100">
								<button type="submit" class="btn btn-primary reply-btn">댓글등록</button>
							</td>
						</form>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="reply" items="${replyList}">
						<tr>
							<td>${reply.replyWriter}</td>
							<td style="text-align: left;">${reply.replyContent}</td>
							<td>${reply.createDate}</td>
						</tr>
					</c:forEach>

					<c:if test="${empty replyList}">
						<tr>
							<td colspan="3">등록된 댓글이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
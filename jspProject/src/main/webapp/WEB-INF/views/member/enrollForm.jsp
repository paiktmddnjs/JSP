<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
        .container{
            padding: 58px 24px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            gap: 24px; 
        }

        .container table tr{
            height: 52px;
        }

    </style>

</head>
<body>
    <jsp:include page="/WEB-INF/views/common/menubar.jsp" />

    <div class="container">
        <h2>회원가입</h2>
        <form action="${pageContext.request.contextPath}/insert.me" method="post">
            <table>
                <tr>
                    <td><input type="text" class="form-control" name="userId" required placeholder="아이디 입력..."></td>
                    <td><button type="button" class="btn btn-primary" onclick="">중복확인</button></td>
                </tr>
                <tr>
                    <td><input type="password" class="form-control" name="userPwd" required placeholder="비밀번호 입력..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="password" class="form-control" required placeholder="비밀번호 확인..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="userName" required placeholder="이름 입력..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="phone" placeholder="전화번호 입력..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="email" placeholder="이메일 입력..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="address" placeholder="주소 입력..."></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label>
                            <input type="checkbox" name="interest" value="sports">
                            운동
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="hiking">
                            등산
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="fishing">
                            낚시
                        </label>
                        <br>
                        <label>
                            <input type="checkbox" name="interest" value="cooking">
                            요리
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="gaming">
                            게임
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="movie">
                            영화
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="etc">
                            기타
                        </label>
                    </td>
                </tr>
            </table>

            <br><br>

            <div align="center">
                <input type="submit" class="btn btn-primary" value="회원가입">
                <input type="reset" class="btn btn-primary" value="다시입력">
            </div>
        </form>
    </div>
</body>
</html>
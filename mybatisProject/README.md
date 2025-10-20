# 🚀 프로젝트명 (Project Name)
MyBatis를 이용한 이전 웹페이지의 재구현

<br>
<br>

## 📘 개요 (Overview)
- MyBatis 구조로 변경
<br>
<br>

## 🧱 기술 스택 (Tech Stack)
| 구분 | 사용 기술 |
|------|------------|
| Frontend | HTML, CSS, JavaScript, JSP , MyBatis |
| Backend | Java (Servlet, JDBC)|
| Server| Apache Tomcat |
| Database | Oracle |
| Tools | Eclipse, Git, GitHub |

## 🛠️ 설치 및 실행 (Installation & Run)
# 1. 프로젝트 클론
git clone https://github.com/paiktmddnjs/JSP/tree/main/mybatisProject

# 2. 이클립스(Eclipse)에서 Import
- File > Import > Existing Projects into Workspace
- 복제한 프로젝트 폴더 선택 후 Import

# 3. 데이터베이스(Oracle) 설정
- Oracle 실행 후 데이터베이스 및 테이블 생성
- src/main/webapp/WEB-INF/classes/sql 폴더 내 SQL 스크립트 실행
- JDBC 연결 정보(application.properties 또는 JDBCTemplate.java) 수정

# 4. Tomcat 서버 설정
- Eclipse > Servers > New > Server > Apache Tomcat 선택
- 프로젝트를 서버에 Add 후 실행

# 5. 웹 애플리케이션 실행
- 브라우저에서 접속
http://localhost:8080/프로젝트명


<br>
<br>

## 📂 프로젝트 구조 (Directory Structure)

<pre>
📦mybatisProject
 ┣ 📂.settings
 ┃ ┣ 📜.jsdtscope
 ┃ ┣ 📜org.eclipse.core.resources.prefs
 ┃ ┣ 📜org.eclipse.jdt.core.prefs
 ┃ ┣ 📜org.eclipse.wst.common.component
 ┃ ┣ 📜org.eclipse.wst.common.project.facet.core.xml
 ┃ ┣ 📜org.eclipse.wst.jsdt.ui.superType.container
 ┃ ┗ 📜org.eclipse.wst.jsdt.ui.superType.name
 ┣ 📂resources
 ┃ ┣ 📂mappers
 ┃ ┃ ┣ 📜attachment-mapper.xml
 ┃ ┃ ┣ 📜board-mapper.xml
 ┃ ┃ ┗ 📜member-mapper.xml
 ┃ ┗ 📜mybatis-config.xml
 ┣ 📂src
 ┃ ┗ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂kh
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mybatis
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Template.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardSearchController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DetailController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜InsertBoardController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ListController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LogoutController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Attachment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Board.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PageInfo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberService.java
 ┃ ┃ ┗ 📂webapp
 ┃ ┃ ┃ ┣ 📂META-INF
 ┃ ┃ ┃ ┃ ┗ 📜MANIFEST.MF
 ┃ ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┣ 📂WEB-INF
 ┃ ┃ ┃ ┃ ┣ 📂classes
 ┃ ┃ ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂kh
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mybatis
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Template.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardSearchController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DetailController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜InsertBoardController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ListController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LogoutController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Attachment.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Board.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PageInfo.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardService.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberService.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mappers
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜attachment-mapper.xml
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜board-mapper.xml
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜member-mapper.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜mybatis-config.xml
 ┃ ┃ ┃ ┃ ┣ 📂lib
 ┃ ┃ ┃ ┃ ┃ ┣ 📜commons-fileupload2-core-2.0.0-M4.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜commons-fileupload2-jakarta-2.0.0-M1.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜commons-io-2.20.0.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜jakarta.servlet.jsp.jstl-3.0.1.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜jakarta.servlet.jsp.jstl-api-3.0.1.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜lombok.jar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mybatis-3.5.19.jar
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ojdbc17.jar
 ┃ ┃ ┃ ┃ ┣ 📂views
 ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜detailView.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜enrollFrom.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜listView.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thumbnailDetailView.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thumbnailEnrollForm.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thumbnailListView.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜updateForm.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜error.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜menubar.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜enrollForm.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜myPage.jsp
 ┃ ┃ ┃ ┃ ┣ 📜.gitignore
 ┃ ┃ ┃ ┃ ┗ 📜web.xml
 ┃ ┃ ┃ ┗ 📜index.jsp
 ┣ 📜.classpath
 ┗ 📜.project
</pre>

<br>
<br>
## 🌟 주요 기능 (Key Features)
✅ 회원가입 / 로그인 / 로그아웃 기능
✅ 게시글 등록, 조회, 수정, 삭제 (CRUD)
✅ Oracle DB 연동을 통한 데이터 관리
✅ MVC 패턴 기반 구조로 모듈화된 개발
✅ JSP include를 통한 공통 레이아웃 구성

<br>
<br>


## 📸 화면 미리보기 (Preview)

| 기능 | 미리보기 |
|------|-----------|
| 로그인 화면 | ![Login Page](./assets/login.gif) |
| 회원가입 화면 | ![Register Page](./assets/register.png) |
| 게시판 목록 | ![Board List](./assets/board-list.jpg) |
| 게시글 작성 | ![Post Write](./assets/post-write.gif) |

<br>
<br>

## 💡 학습 포인트 (Learning Points)

- JSP & Servlet 기반 MVC 구조 설계 방법 학습
- JDBC를 통한 데이터베이스 연결 및 SQL 처리 로직 구현
- Tomcat 서버를 활용한 배포 및 실행 환경 이해
- JSP 내 JSTL / EL 사용으로 동적 페이지 구현
- 재구현 하면서 MyBatis의 구조에 대해 익히기
- 
<br>
<br>

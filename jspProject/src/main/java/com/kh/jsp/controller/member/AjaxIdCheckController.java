package com.kh.jsp.controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.jsp.service.MemberService;




@WebServlet("/AjaxIdCheckController")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
    public AjaxIdCheckController() {
        super();
      
    }

    
    
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String checkId = request.getParameter("checkId");
		
		 int count = new MemberService().idCheck(checkId);
		
		 if(count > 0) { // 회원이 존재
			 response.getWriter().print("NNNNN");
			 

		 } else { //호원이 존재하지 않음
			 
			 response.getWriter().print("NNNNY");
			 
		 }
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		doGet(request, response);
	}

}

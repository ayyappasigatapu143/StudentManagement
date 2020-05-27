package com.student.management.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.student.management.models.User;
import com.student.management.service.CommonUserService;
import com.student.management.util.CommonUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		String jsonResponse =CommonUtil.readJsonResponse(request);
	     User user =new Gson().fromJson(jsonResponse,User.class);
	     response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     User userExists =new CommonUserService().checkValidUser(user); 
	     if(userExists!=null) {
	    	 HttpSession session = request.getSession();
		     session.setAttribute("user", userExists.getUsername());
			 session.setAttribute("token",session.getId());	     
			 session.setMaxInactiveInterval(7 * 24 * 3600);// 
			 Cookie cookie = new Cookie("JSESSIONID", session.getId());
	
			cookie.setMaxAge(7 * 24 * 3600);//
			cookie.setPath("/");
			response.addCookie(cookie);
	    	 JSONObject json =new JSONObject();
	    	 json.put("roles",userExists.getRole());
	         json.put("status","SUCCESS");
	         json.put("current_user", userExists.getUsername());
	         json.put("token",session.getId());
             
	         out.print(new Gson().toJson(json));
	  	     
	     }
	     else {
	    	 JSONObject json =new JSONObject();
	         json.put("status","Incorrect username or password");
	  	     out.print(new Gson().toJson(json));
	     }

	}

}

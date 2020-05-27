package com.student.management.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.student.management.models.RoomDetails;
import com.student.management.service.CommonUserService;
import com.student.management.util.CommonUtil;

/**
 * Servlet implementation class RoomDetailsController
 */
@WebServlet("/roomDetails")
public class RoomDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		String jsonResponse =CommonUtil.readJsonResponse(request);
		JSONObject jsonObject =new JSONObject(jsonResponse);
		 //String current_user=String.valueOf(jsonObject.get("current_user"));
	      String token=	String.valueOf(jsonObject.get("token"));
	      HttpSession session =request.getSession();
	      if(token!=null) {
	    	  List<RoomDetails> roomDetails=new CommonUserService().getRoomDetails();
	    	  JSONObject json =new JSONObject();
	    	  json.put("status","SUCCESS");
	    	  json.put("tableList",roomDetails);
	    	  out.print(new Gson().toJson(json));
	    	  /*
				 * if(token.equalsIgnoreCase(String.valueOf(session.getAttribute("token")))) {
				 * JSONObject json =new JSONObject(); json.put("status","INVALID_TOKEN");
				 * out.print(new Gson().toJson(json)); } else { List<RoomDetails>
				 * roomDetails=new CommonUserService().getRoomDetails(); JSONObject json =new
				 * JSONObject(); json.put("status","SUCCESS");
				 * json.put("tableList",roomDetails); out.print(new Gson().toJson(json)); }
				 */
	      }	
	     
	      
	}
}

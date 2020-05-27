package com.student.management.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.student.management.models.RoomDetails;
import com.student.management.models.User;
import com.student.management.service.CommonUserService;
import com.student.management.util.CommonUtil;

/**
 * Servlet implementation class AddRoomController
 */
@WebServlet("/addRoom")
public class AddRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoomController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String jsonResponse = CommonUtil.readJsonResponse(request);
		RoomDetails roomDetails = new Gson().fromJson(jsonResponse, RoomDetails.class);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		  User user =new CommonUserService().getUserDetails(roomDetails.getUsername());
		  if(user!=null) {
			  roomDetails.setUsername(user.getUsername());
			  roomDetails.setUserId(user.getUserId());
		  }
		String result = new CommonUserService().addNewRoom(roomDetails);
		JSONObject json = new JSONObject();
		json.put("status", result);
		out.print(new Gson().toJson(json));
	}
}

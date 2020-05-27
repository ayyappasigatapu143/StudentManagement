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
import com.student.management.service.CommonUserService;
import com.student.management.util.CommonUtil;

/**
 * Servlet implementation class UpdateRoomController
 */
@WebServlet("/updateRoom")
public class UpdateRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateRoomController() {
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
		String result = new CommonUserService().updateRoomDetails(roomDetails);
		JSONObject json = new JSONObject();
		json.put("status", result);
		out.print(new Gson().toJson(json));
	}

}

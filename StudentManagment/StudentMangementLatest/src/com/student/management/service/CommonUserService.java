package com.student.management.service;

import java.util.List;

import com.student.management.models.RoomDetails;
import com.student.management.models.User;
import com.student.management.repository.UserRepository;

public class CommonUserService {

	public String createuser(User user) {
		    String status =new UserRepository().createUser(user);    
		
		return null!=status?status:"Insertion failed";
	}

	public User checkValidUser(User user) {
		    
		return new UserRepository().checkValidUser(user);
	}

	public List<RoomDetails> getRoomDetails() {
		return  new UserRepository().getRoomDetails();
	}

	public String addNewRoom(RoomDetails roomDetails) {
		String status =  new UserRepository().addNewRoom(roomDetails);
		return null!=status?status:"Insertion failed";
	}

	public List<RoomDetails> deleteRoom(RoomDetails roomDetails) {
		// TODO Auto-generated method stub
		List<RoomDetails> roomDetailsList =new UserRepository().deleteRoom(roomDetails);
		return roomDetailsList;
	}

	public RoomDetails getRoomDetails(RoomDetails roomDetails) {
		// TODO Auto-generated method stub
		return new UserRepository().getRoomDetailsByRoomId(roomDetails);
	}

	public String updateRoomDetails(RoomDetails roomDetails) {
		String status =  new UserRepository().editRoomDetails(roomDetails);
		return null!=status?status:"Insertion failed";
	}

	public List<RoomDetails> getRoomDetailsAVailable() {
		return  new UserRepository().getRoomDetailsAvailable();
	}

	public User getUserDetails(String username) {
		// TODO Auto-generated method stub
		return  new UserRepository().getUserDetails(username);
	}
}

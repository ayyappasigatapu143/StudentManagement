function registerUser() {
	debugger;
	event.preventDefault();
	var response;
	var elements = document.querySelector("form");
	var selectedRole = document.getElementById("roles");
	var results = selectedRole.options[selectedRole.selectedIndex].text;

	var data = {
		firstName : document.getElementById("fname").value,
		lastName : document.getElementById("lname").value,
		username : document.getElementById("uid").value,
		password : document.getElementById("pass").value,
		phone : document.getElementById("phon").value,
		address : document.getElementById("comment").value,
		role : results
	};
	var studentRegister = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);
			if (response.map.status === 'SUCCESS') {
				document.getElementById("result").innerHTML = "User created successfully";
			} else if (response.map.status === 'USER_ALREADY_EXISTS') {
				document.getElementById("error").innerHTML = "user already exists with id : "
						+ document.getElementById("uid").value;

				window.location.href = "http://localhost:8081/StudentMangementLatest/login";
			} else {
				document.getElementById("error").innerHTML = "user not created";
			}
		}
	};

	xhttp.open("POST", "http://localhost:8081/StudentMangementLatest/register",
			true);
	xhttp.send(studentRegister);
	return false;
}

function loginUser() {
	setTimeout("preventBack()", 0);
	debugger;
	event.preventDefault();
	var response;
	var elements = document.querySelector("form");
	var data = {

		username : document.getElementById("uname").value,
		password : document.getElementById("upass").value
	};
	var studentLogin = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);

			if (response.map.status === 'SUCCESS') {
				localStorage.setItem("current_user", response.map.current_user);
				localStorage.setItem("token", response.map.token);
				window.history.forward();
				if (response.map.roles === 'Admin') {
					window.location.href = "http://localhost:8081/StudentMangementLatest/dashboard.jsp";
				} else {
					window.location.href = "http://localhost:8081/StudentMangementLatest/studentDashboard.html";
				}

			}
			/*
			 * else if(response.map.status==='USER_ALREADY_EXISTS'){
			 * document.getElementById("error").innerHTML="user already exists
			 * with id : "+document.getElementById("uid").value; } else{
			 * document.getElementById("error").innerHTML="user not created"; }
			 */
		}
	};

	xhttp.open("POST", "http://localhost:8081/StudentMangementLatest/login",
			true);
	xhttp.send(studentLogin);
	return false;
}

function preventBack() {
	window.history.forward();
}

function getRoomDetails() {
	clearAllMessages();
	document.getElementById("resultRoom").innerHTML = "";
	document.getElementById("editRoom").style.display = "none";

	debugger;
	event.preventDefault();
	var response;
	var elements = document.querySelector("form");
	var data = {
		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user")
	};
	var getRooms = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);

			if (response.map.status === 'SUCCESS') {
				document.getElementById("roomTable").removeAttribute("style");
				document.getElementById("roomAdd").style.display = "none";
				var table = document.getElementById("roomTable");
				console.log(table.tBodies.tbody);
				for (var j = 0; j < table.rows.length; j++) {
					if (j == 0) {

					} else {
						table.deleteRow(j);
					}

				}
				var tbody = document.createElement("tbody");

				for (var i = 0; i < response.map.tableList.myArrayList.length; i++) {
					var trow = document.createElement("tr");

					var td1 = document.createElement("td");
					td1.innerHTML = response.map.tableList.myArrayList[i].map.userId;
					var td2 = document.createElement("td");
					td2.innerHTML = response.map.tableList.myArrayList[i].map.username;
					var td3 = document.createElement("td");
					td3.innerHTML = response.map.tableList.myArrayList[i].map.roomId;
					var td4 = document.createElement("td");
					td4.innerHTML = response.map.tableList.myArrayList[i].map.roomType;
					var td5 = document.createElement("td");
					td5.innerHTML = response.map.tableList.myArrayList[i].map.roomLocation;
					var td6 = document.createElement("td");
					td6.innerHTML = response.map.tableList.myArrayList[i].map.roomStatus;
					var td7 = document.createElement("td");
					td7.innerHTML = response.map.tableList.myArrayList[i].map.monthlyCharges;
					var td8 = document.createElement("td");
					td8.innerHTML = response.map.tableList.myArrayList[i].map.paymentStatus;
					var td9 = document.createElement("td");
					td9.innerHTML = " <button class='addBtn' onclick='loadUserDetailsPage("
							+ response.map.tableList.myArrayList[i].map.roomId
							+ ");'>Edit</button> &nbsp;<button class='deleteBtn' onclick='deleteRoom("
							+ response.map.tableList.myArrayList[i].map.roomId
							+ ");'>Delete</button>";
					trow.appendChild(td1);
					trow.appendChild(td2);
					trow.appendChild(td3);
					trow.appendChild(td4);
					trow.appendChild(td5);
					trow.appendChild(td6);
					trow.appendChild(td7);
					trow.appendChild(td8);
					trow.appendChild(td9);
					tbody.appendChild(trow);
				}
				table.appendChild(tbody);
				document.getElementById("roomTable").style.display = "block";
			}

		}
	};

	xhttp.open("POST",
			"http://localhost:8081/StudentMangementLatest/roomDetails", true);
	xhttp.send(getRooms);
	return false;
}

function addNewRoom() {
	clearAllMessages();
	document.getElementById("resultRoom").innerHTML = "";
	document.getElementById("editRoom").style.display = "none";
	debugger;
	document.getElementById("roomTable").style.display = "none";
	document.getElementById("roomAdd").removeAttribute("style");
	var roomStatusSelected = document.getElementById("rStatus");
	var roomStatus = roomStatusSelected.options[roomStatusSelected.selectedIndex].text;

	var paymentStatusSelected = document.getElementById("pStatus");
	var paymentStatus = paymentStatusSelected.options[paymentStatusSelected.selectedIndex].text;

	var data = {

		roomId : document.getElementById("roomId").value,
		roomType : document.getElementById("roomTyp").value,
		roomLocation : document.getElementById("rlocation").value,
		roomStatus : roomStatus,
		monthlyCharges : document.getElementById("mCharges").value,
		paymentStatus : paymentStatus,
		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user")
	};
	var studentLogin = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);

			if (response.map.status === 'SUCCESS') {
				document.getElementById("resultRoom").innerHTML = "Room Added Successfully";
			} else {
				document.getElementById("errorsRoom").innerHTML = "Room Adding failed";
			}
		}
	};

	xhttp.open("POST", "http://localhost:8081/StudentMangementLatest/addRoom",
			true);
	xhttp.send(studentLogin);
	return false;

}

function deleteRoom(value) {
	clearAllMessages();
	document.getElementById("editRoom").style.display = "none";
	document.getElementById("resultRoom").innerHTML = "";
	/* document.getElementById("roomTable").deleteRow(value); */
	debugger;
	var data = {

		roomId : value,
		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user")
	};
	var studentLogin = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {

			if (this.responseText == '' || this.responseText == undefined) {
				var table = document.getElementById("roomTable");
				table.deleteRow(1);
			} else {
				response = JSON.parse(this.responseText);
				if (response.map.status === 'SUCCESS') {
					var table = document.getElementById("roomTable");
					console.log(table.tBodies.tbody);
					for (var j = table.rows.length - 1; j >= 0; j--) {
						if (j == 0) {

						} else {
							table.deleteRow(j);
						}

					}
					var tbody = document.createElement("tbody");

					for (var i = 0; i < response.map.tableList.myArrayList.length; i++) {
						var trow = document.createElement("tr");

						var td1 = document.createElement("td");
						td1.innerHTML = response.map.tableList.myArrayList[i].map.userId;
						var td2 = document.createElement("td");
						td2.innerHTML = response.map.tableList.myArrayList[i].map.username;
						var td3 = document.createElement("td");
						td3.innerHTML = response.map.tableList.myArrayList[i].map.roomId;
						var td4 = document.createElement("td");
						td4.innerHTML = response.map.tableList.myArrayList[i].map.roomType;
						var td5 = document.createElement("td");
						td5.innerHTML = response.map.tableList.myArrayList[i].map.roomLocation;
						var td6 = document.createElement("td");
						td6.innerHTML = response.map.tableList.myArrayList[i].map.roomStatus;
						var td7 = document.createElement("td");
						td7.innerHTML = response.map.tableList.myArrayList[i].map.monthlyCharges;
						var td8 = document.createElement("td");
						td8.innerHTML = response.map.tableList.myArrayList[i].map.paymentStatus;
						var td9 = document.createElement("td");
						td9.innerHTML = " <button class='addBtn' onclick='loadUserDetailsPage("
								+ response.map.tableList.myArrayList[i].map.roomId
								+ ");'>Edit</button> &nbsp;<button class='deleteBtn' onclick='deleteRoom("
								+ response.map.tableList.myArrayList[i].map.roomId
								+ ");'>Delete</button>";
						trow.appendChild(td1);
						trow.appendChild(td2);
						trow.appendChild(td3);
						trow.appendChild(td4);
						trow.appendChild(td5);
						trow.appendChild(td6);
						trow.appendChild(td7);
						trow.appendChild(td8);
						trow.appendChild(td9);
						tbody.appendChild(trow);
					}
					table.appendChild(tbody);
					document.getElementById("roomTable").style.display = "block";
				} else {
					document.getElementById("errorsRoom").innerHTML = "Room Deletion failed";
				}
			}
		}
	};

	xhttp.open("POST",
			"http://localhost:8081/StudentMangementLatest/deleteRoom", true);
	xhttp.send(studentLogin);
	return false;

}

function loadUserDetailsPage(value) {
	clearAllMessages();

	document.getElementById("roomTable").style.display = "none";
	document.getElementById("editRoom").style.display = "block";
	document.getElementById("roomAdd").style.display = "none";
	document.getElementById("resultRoom").innerHTML = "";

	var data = {

		roomId : value,
		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user")
	};
	var studentLogin = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {

			response = JSON.parse(this.responseText);
			if (response.map.status === 'SUCCESS') {
				document.getElementById("roomIds").value = response.map.roomDetails.roomId;
				document.getElementById("roomTyps").value = response.map.roomDetails.roomType;
				document.getElementById("rlocations").value = response.map.roomDetails.roomLocation;
				document.getElementById("rStatuss").value = response.map.roomDetails.roomStatus;
				document.getElementById("mChargess").value = response.map.roomDetails.monthlyCharges;
				document.getElementById("pStatuss").value = response.map.roomDetails.paymentStatus;

			} else {
				document.getElementById("errorsRoom").innerHTML = "Room Deletion failed";
			}

		}
	};

	xhttp.open("POST", "http://localhost:8081/StudentMangementLatest/editRoom",
			true);
	xhttp.send(studentLogin);
	return false;
}

function editRoomDetails() {
	debugger;
	clearAllMessages();
	var roomStatusSelected = document.getElementById("rStatuss");
	var roomStatus = roomStatusSelected.options[roomStatusSelected.selectedIndex].text;

	var paymentStatusSelected = document.getElementById("pStatuss");
	var paymentStatus = paymentStatusSelected.options[paymentStatusSelected.selectedIndex].text;

	var data = {

		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user"),
		roomId : document.getElementById("roomIds").value,
		roomType : document.getElementById("roomTyps").value,
		roomLocation : document.getElementById("rlocations").value,
		roomStatus : roomStatus,
		monthlyCharges : document.getElementById("mChargess").value,
		paymentStatus : paymentStatus
	};
	var studentLogin = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {

			response = JSON.parse(this.responseText);
			if (response.map.status === 'SUCCESS') {
				document.getElementById("resultRooms").innerHTML = "Updated Sucessfully";

			} else {
				document.getElementById("errorsRooms").innerHTML = "Room Deletion failed";
			}

		}
	};

	xhttp.open("POST",
			"http://localhost:8081/StudentMangementLatest/updateRoom", true);
	xhttp.send(studentLogin);
	return false;

}
function clearAllMessages() {
	document.getElementById("resultRooms").innerHTML = "";
	document.getElementById("errorsRooms").innerHTML = "";
	document.getElementById("resultRoom").innerHTML = "";
	document.getElementById("resultRoom").innerHTML = "";
	document.getElementById("resultRooms").innerHTML = "";
	document.getElementById("resultRooms").innerHTML = "";
}

function getRoomDetailsStudent() {

	debugger;
	event.preventDefault();
	var response;
	var elements = document.querySelector("form");
	var data = {
		token : localStorage.getItem("token"),
		current_user : localStorage.getItem("current_user")
	};
	var getRooms = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);

			if (response.map.status === 'SUCCESS') {

				var table = document.getElementById("studentRoomTable");
				console.log(table.tBodies.tbody);
				for (var j = 0; j < table.rows.length; j++) {
					if (j == 0) {

					} else {
						table.deleteRow(j);
					}

				}
				var tbody = document.createElement("tbody");

				for (var i = 0; i < response.map.tableList.myArrayList.length; i++) {
					var trow = document.createElement("tr");

					var td3 = document.createElement("td");
					td3.innerHTML = response.map.tableList.myArrayList[i].map.roomId;
					var td4 = document.createElement("td");
					td4.innerHTML = response.map.tableList.myArrayList[i].map.roomType;
					var td5 = document.createElement("td");
					td5.innerHTML = response.map.tableList.myArrayList[i].map.roomLocation;
					var td6 = document.createElement("td");
					td6.innerHTML = response.map.tableList.myArrayList[i].map.roomStatus;
					var td7 = document.createElement("td");
					td7.innerHTML = response.map.tableList.myArrayList[i].map.monthlyCharges;
					var td8 = document.createElement("td");
					td8.innerHTML = response.map.tableList.myArrayList[i].map.paymentStatus;
					var td9 = document.createElement("td");
					td9.innerHTML = " <button class='addBtn' onclick='loadUserDetailsPage("
							+ response.map.tableList.myArrayList[i].map.roomId
							+ ");'>Available</button>";

					trow.appendChild(td3);
					trow.appendChild(td4);
					trow.appendChild(td5);
					trow.appendChild(td6);
					trow.appendChild(td7);
					trow.appendChild(td8);
					trow.appendChild(td9);
					tbody.appendChild(trow);
				}
				table.appendChild(tbody);
				document.getElementById("studentRoomTable").style.display = "block";
			}

		}
	};

	xhttp.open("POST",
			"http://localhost:8081/StudentMangementLatest/studentRoomDetails",
			true);
	xhttp.send(getRooms);
	return false;
}

function logout() {
	window.location.href = "http://localhost:8081/StudentMangementLatest/";
}

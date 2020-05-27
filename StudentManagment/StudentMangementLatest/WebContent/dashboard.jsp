<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
 <style type="text/css" ></style>
 <link rel="stylesheet" href="styles.css" type="text/css">
 <script src="main.js"></script>
<style>
    @import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  text-decoration: none;
  font-family: 'Josefin Sans', sans-serif;
}

body{
   background-color: #f3f5f9;
}

.wrapper{
  display: flex;
  position: relative;
}

.wrapper .sidebar{
  width: 200px;
  height: 100%;
  background: #4b4276;
  padding: 30px 0px;
  position: fixed;
}

.wrapper .sidebar h2{
  color: #fff;
  text-transform: capitalize;
  text-align: center;
  margin-bottom: 30px;
}

.wrapper .sidebar ul li{
  padding: 15px;
  border-bottom: 1px solid #bdb8d7;
  border-bottom: 1px solid rgba(0,0,0,0.05);
  border-top: 1px solid rgba(255,255,255,0.05);
}    

.wrapper .sidebar ul li a{
  color: #bdb8d7;
  display: block;
}

.wrapper .sidebar ul li a .fas{
  width: 25px;
}

.wrapper .sidebar ul li:hover{
  background-color: #594f8d;
}
    
.wrapper .sidebar ul li:hover a{
  color: #fff;
}
 
.wrapper .sidebar .social_media{
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
}

.wrapper .sidebar .social_media a{
  display: block;
  width: 40px;
  background: #594f8d;
  height: 40px;
  line-height: 45px;
  text-align: center;
  margin: 0 5px;
  color: #bdb8d7;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.wrapper .main_content{
  width: 100%;
  margin-left: 200px;
}

.wrapper .main_content .header{
  padding: 20px;
  background: #fff;
  color: white;
  border-bottom: 1px solid #e0e4e8;
  width: 100%;
  height: 58px;
  background-color: #4b4276;
}

.wrapper .main_content .info{
  margin: 20px;
  color: #717171;
  line-height: 25px;
}

.wrapper .main_content .info div{
  margin-bottom: 20px;
}

.signoff{
    float: right;
}
.signoff a{
    color: white;
}
@media (max-height: 500px){
  .social_media{
    display: none !important;
  }
}

.caret{
    display: inline-block;
    width: 0;
    height: 0;
    margin-left: 2px;
    vertical-align: middle;
    border-top: 4px dashed;
    border-top: 4px solid\9;
    border-right: 4px solid transparent;
    border-left: 4px solid transparent;
}

.toggleChild{
    background-color: white;
    width: 25px;
    height: 4px;
    margin-bottom: 3px;
    cursor: pointer;
}

.togleMain{
    margin-left: 10px;
}

#closeBtn{
    float: right;
    color: white;
    background-color: #4b4276;
    border: 1px solid #4b4276;
    /* width: 30px; */
    font-size: 19px;
    margin-right: 16px;
}

.addRoomFrom {
	width: 396px;
	margin-left: 85px;
}
.addCursor{
 cursor:pointer;	
}

</style>

<script>

    window.onload=function(){
        document.getElementsByClassName('toggleChild')[0].style.display='none';
        document.getElementsByClassName('toggleChild')[1].style.display='none';
        document.getElementsByClassName('toggleChild')[2].style.display='none';
    }
    function displayToggleDiv(){
        document.getElementsByClassName('toggleChild')[0].style.display='none';
     document.getElementsByClassName('toggleChild')[1].style.display='none';
       document.getElementsByClassName('toggleChild')[2].style.display='none';
       document.getElementsByClassName('sidebar')[0].style.display='block';
    }
    
     function hideToggleDiv(){
         debugger;
         
            document.getElementsByClassName('sidebar')[0].style.display='none';
          document.getElementsByClassName('header')[0].style.width="119%";
          document.getElementsByClassName('header')[0].style.marginLeft="-199px";  
          
           
        //   document.getElementsByClassName('header')[0].style.width="119%";
        //   document.getElementsByClassName('header')[0].style.marginLeft="-199px"; 
         
        //   document.getElementsByClassName('sidebar')[0].style.display='none';
        //   document.getElementsByClassName('header')[0].style.width="119%";
        //   document.getElementsByClassName('header')[0].style.marginLeft="-199px";
          
  document.getElementsByClassName('toggleChild')[0].style.display='block';
  document.getElementsByClassName('toggleChild')[1].style.display='block';
   document.getElementsByClassName('toggleChild')[2].style.display='block'
}

     
    
</script>

</head>
<body>

<div class="wrapper">
      
    <div class="sidebar">
            <button id="closeBtn" aria-label="Close Account Info Modal Box" onclick="hideToggleDiv()">close</button>
        <h2></h2>
        <ul>
            <li><a href="#"  class="addCursor"><i class="fas fa-home"></i>Home</a></li>
            <li><a href="#" onclick="addNewRoom();" class="addCursor"><i class="fas fa-shopping-cart" ></i>Add Room</a></li>
            <li><a href="#" onclick="getRoomDetails();" class="addCursor"><i class="fas fa-address-card"></i>View Room</a></li>
           
        </ul> 
        
    </div>
    <div class="main_content">
        <div class="header ">
            <div class="togleMain">
                <div class="toggleChild"  onclick="displayToggleDiv()"></div>
                <div class="toggleChild"  onclick="displayToggleDiv()"></div>
                <div class="toggleChild"  onclick="displayToggleDiv()"></div>
            </div>
            <span class="signoff"><a href="javascript:void(0)" onclick="logout();">Sign Out <span class="caret"></span></a> </span></div>  
        <div class="info">
       <div class="secondDiv">
        <table id="roomTable" style="display:none">
      <thead class="tableHead">
          <tr><td class="tdStyle">User Id</td>
          <td class="tdStyle"> User Name</td>
          <td class="tdStyle">Room Id</td>
          <td class="tdStyle">Room Type</td>
          <td class="tdStyle">Room Location</td>
          <td class="tdStyle">Room Status</td>
          <td class="tdStyle">Monthly Charges</td>
          <td class="tdStyle">Payment Status</td>
          <td class="tdStyle">Action</td>
        </tr>
          
      </thead>
      <tbody>
         
      </tbody>    
</table>
        <form   class="addRoomFrom" id="roomAdd" method="post" onsubmit="return  addNewRoom();" style="display:none;">
              
                <h2 class="form-heading">Add Room</h2>
             <div class="registerInput">
                 <input type="text" name="roomId" placeholder="Room Id" class="firstname inputStyle" id="roomId" required>               
                </div>
                <div class="registerInput">
                 <input type="text" name="roomType" placeholder="Room Type" class="firstname inputStyle" id="roomTyp" required>               
                </div>
                <div class="registerInput">
         
                 <input type="text" name="roomLocation" placeholder="roomLocation" class="lastname inputStyle" id="rlocation" required> 
                </div>
                <div class="registerInput">
                   <select name="roomStatus" class="role" id="rStatus" required>
                    <option >Please Select</option>
                        <option value="AVAILABLE">AVAILABLE</option>
                        <option value="BOOKED">BOOKED</option>
                    </select>
                   </div>
                   <div class="registerInput">
                    <input type="text" name="monthlyCharges" placeholder="Monthly Charges" class="password inputStyle" id="mCharges"  required>               
                   </div>
                   <div class="registerInput">
                    <select name="paymentStatus" class="role"  id="pStatus" required>
                    <option >Please Select</option>
                        <option value="NA">NA</option>
                        <option value="PENDING">PENDING</option>
                        <option value="PAID">PAID</option>
                    </select>
                   </div>
                  
                  <div class="registerInput">
                      <button type='submit' class='submitBtn'>Add Room</button>
                  </div>
                   
            </form>
            <span id="resultRoom"  style="color: green;font-family: sans-serif;
	padding: 39px 86px;"></span>
                   <span id="errorsRoom" style="color: red;
	font-family: sans-serif;
	padding: 39px 86px;" ></span>
	
	
	<form   class="addRoomFrom" id="editRoom" method="post" onsubmit="return  editRoomDetails();" style="display:none;">
              
                <h2 class="form-heading">Edit Room</h2>
             <div class="registerInput">
                 <input type="text" name="roomId" placeholder="Room Id" class="firstname inputStyle" id="roomIds" required>               
                </div>
                <div class="registerInput">
                 <input type="text" name="roomType" placeholder="Room Type" class="firstname inputStyle" id="roomTyps" required>               
                </div>
                <div class="registerInput">
         
                 <input type="text" name="roomLocation" placeholder="roomLocation" class="lastname inputStyle" id="rlocations" required> 
                </div>
                <div class="registerInput">
                  <select name="roomStatus" class="role" id="rStatuss" required>
                    <option >Please Select</option>
                        <option value="AVAILABLE">AVAILABLE</option>
                        <option value="BOOKED">BOOKED</option>
                    </select>
                   </div>
                   <div class="registerInput">
                    <input type="text" name="monthlyCharges" placeholder="Monthly Charges" class="password inputStyle" id="mChargess"  required>               
                   </div>
                   <div class="registerInput">
                   <select name="paymentStatus" class="role"  id="pStatuss" required>
                    <option >Please Select</option>
                        <option value="NA">NA</option>
                        <option value="PENDING">PENDING</option>
                        <option value="PAID">PAID</option>
                    </select>
                   
                   </div>
                   
                  <div class="registerInput">
                      <button type='submit' class='submitBtn'>Edit Room Details</button>
                  </div>
                   
            </form>
            <span id="resultRooms"  style="color: green;font-family: sans-serif;padding: 39px 86px;"></span>
             <span id="errorsRooms" style="color: red;font-family: sans-serif;padding: 39px 86px;" ></span>
	
        </div>
        </div>
    </div>
</div>

</body>
</html>
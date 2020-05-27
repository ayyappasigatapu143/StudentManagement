	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="styles.css" type="text/css">
       <script src="main.js"></script>
      <style type="text/css">
      .inputStyle{
     display: block;
    width:93%;
    height: auto;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-bottom: 9px;
    }
    
    
    .textAreaInput{
    display: block;
    width: 93%;
    height: auto;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
     background-color: #fff;
     background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
}

#result {
	color: green;
	font-family: sans-serif;
	padding: 39px 86px;
}

#errors {
	color: red;
	font-family: sans-serif;
	padding: 39px 86px;
}
      </style>
</head>
<body>
<div class="registerBox" >
            <form   class="studentForm" id="createStudent" method="post" onsubmit="return  registerUser();">
              
                <h2 class="form-heading">Register Here</h2>
            
                <div class="registerInput">
                 <input type="text" name="firstname" placeholder="First Name" class="firstname inputStyle" id="fname" required>               
                </div>
                <div class="registerInput">
         
                 <input type="text" name="lastname" placeholder="Last Name" class="lastname inputStyle" id="lname" required> 
                </div>
                <div class="registerInput">
                    <input type="text" name="userId" placeholder="User Id" class="userId inputStyle" id="uid" required>               
                   </div>
                   <div class="registerInput">
                    <input type="password" name="password" placeholder="Password" class="password inputStyle" id="pass"  required>               
                   </div>
                   <div class="registerInput">
                    <input type="text" name="phone" placeholder="phone" class="phoneNumber inputStyle" id="phon" required>               
                   </div>
                   <div class="registerInput">
                    <textarea class="textAreaInput" name="address" id="comment" placeholder="Address"  required> </textarea>
                   </div>
                   <div class="registerInput">
                    <select name="role" class="role" id="roles" required>
                    <option >Please Select</option>
                        <option value="Admin">Admin</option>
                        <option value="Student">Student</option>
                    </select>
                   </div>
                  <div class="registerInput">
                      <button type='submit' class='submitBtn'>Register</button>
                  </div>
                   
            </form>
               <div class="dsvsdbsd">
               <a href="">Back to Login</a>
                     <span id="result" ></span>
                   <span id="errors" ></span>
                   
                  </div>
            
        </div>
          
           
</body>
</html>
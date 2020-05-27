<html>
    <head>
 <link rel="stylesheet" href="styles.css" type="text/css">
  <script src="main.js"></script>
    </head>

    <body>
            <div class="loginBox">
            <form   method="post"  class="studentForm" onsubmit="return loginUser();">
                <h2 class="form-heading">Login Form</h2>
                <div class="form-input">
                 <input type="text" name="username" id="uname" placeholder="user name" class="username inputStyle">               
                </div>
                <div class="form-input">
         
                 <input type="password" name="password"  id="upass" placeholder="password" class="password inputStyle"> 
                </div>
                  <div class="form-input">
                      <button type='submit' class='submitBtn'>Login</button>
                  </div>
            </form>
                <div class="forgotDiv">
                    <a href="javascript:void(0)" class='linkStyle'>Forgot password ?</a>
                    <br>
                    <a href="register.jsp" class="linkStyle">Create account</a>
                </div>
            
            
        </div>
    </body>
</html>
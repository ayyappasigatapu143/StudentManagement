<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="styles.css" type="text/css">
  <style>
    .tableHead{
        color: #fff;
    background-color: #343a40;
    }
    .tdStyle{
        padding: 10px 25px;
    font-family: sans-serif;
    }
    table >tbody >tr >td{
        padding: 9px 26px;
    color: black;
    background-color: #f3f5f9;
    font-family: initial;
}

.deleteBtn{
    color: #fff;
    background-color: #d9534f;
    border-color: #d43f3a;
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

.addBtn{
    color: #fff;
    background-color: #5cb85c;
    border-color: #5cb85c;
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
   
}
</style>

</head>
<body>
<table>
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
          <tr>
              <td>bdfmbodfmbd</td>
              <td>sdsdobso</td>
              <td>nionion</td>
              <td>inionio</td>
              <td>ionoinon</td>
              <td>ionoinon</td>
              <td>10000</td>
              <td>dvdsmvskd</td>
              <td><button class="addBtn" onclick="loadUserDetailsPage();">Add</button> &nbsp;<button class="deleteBtn">Delete</button></td>
          </tr>
      </tbody>    
</table>
</body>
</html>
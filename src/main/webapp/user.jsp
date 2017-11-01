<%@page import="com.daniel.model.User"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Add new user</title>
</head>
<body>
    <%
       User user = (User)request.getAttribute("user");
       if(request.getParameter("action").equals("insert")){
     %>
       <form method="POST" action='UserController' name="frmAddUser">
        User ID : <input type="text" readonly="readonly" name="userid"
             /> <br /> 
        First Name : <input
            type="text" name="firstName"
             /> <br /> 
        Last Name : <input
            type="text" name="lastName"
             /> <br /> 
        DOB : <input  type="date" name="dob"
             /> <br /> 
        Email : <input type="text" name="email"
             /> <br /> 
        
        Login : <input type="text" name="login"
             /> <br /> 
        
        Password : <input type="password" name="pass"
             /> <br /> 
        
        
        <input type="submit" value="Submit" />
    </form>
     <%
           
       }else{
      %>
        <form method="POST" action='UserController' name="frmAddUser">
        User ID : <input type="text" readonly="readonly" name="userid"
            value="<%=user.getUserid()%>" /> <br /> 
        First Name : <input
            type="text" name="firstName"
            value="<%=user.getFirstName()%>" /> <br /> 
        Last Name : <input
            type="text" name="lastName"
            value="<%=user.getLastName()%>" /> <br /> 
        DOB : <input type="date" name="dob" value="<%=user.getDob()%>" /> <br /> 
        Email : <input type="text" name="email"
            value="<%=user.getEmail()%>" /> <br /> 
        
        <input
            type="submit" value="Submit" />
    </form>
      
      <%
        
       }
    %>
   
</body>
</html>
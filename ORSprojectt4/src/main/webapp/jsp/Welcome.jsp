<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome View</title>
</head>
<body>
	<form action="<%=ORSView.WELCOME_CTL%>">
        <%@ include file="Header.jsp"%>
                    <h1 align="Center">
                    <br>
                    <br>                    
                    	<font style="" size="10px" color="black">Namaste</font>&#128591<br>
                    	<br>
                        <font size="10px" color="purple">Welcome to ORS </font>
                    </h1>
        
                    <%
                    UserBean beanUserBean = (UserBean) session.getAttribute("user");
                        if (beanUserBean != null) {
                            if (beanUserBean.getRoleId() == RoleBean.STUDENT) {
                    %>
        
                    <h2 align="Center">
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your Marksheet </a>
                    </h2>
                     
                     <%
                            }
                        }
                     %>
                
                </form>
	
	<%@ include file = "Footer.jsp" %>	
</body>
</html>
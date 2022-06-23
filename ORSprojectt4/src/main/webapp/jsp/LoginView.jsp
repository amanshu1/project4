<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="in.com.rays.ORSprojectt4.servlet.LoginCtl"%>
<%@page import="in.com.rays.ORSprojectt4.bean.UserBean"%>
<%@page import="in.com.rays.ORSprojectt4.utility.DataUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>

<head>
<meta charset="ISO-8859-1">
<title>login page</title>
</head>
<body>

    <form action="<%=ORSView.LOGIN_CTL%>" method="post">
    <%@ include file="Header.jsp"%>
    
    <input type="hidden" name="URI" value="<%= session.getAttribute("uri") %>">

        <jsp:useBean id="bean" class="in.com.rays.ORSprojectt4.bean.UserBean"
            scope="request"></jsp:useBean>

        
         <div align="center" >
            <h1>Login</h1>

            <H2>
            <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>                
            </H2>
            <H2>
            <font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font>
            </H2>
            <%
			String msg =(String) request.getAttribute("fcmessage");
            if(msg!= null){ 
            %>
            <h1 align="center"><font style="color: red"><%=msg %></font>
            <%} %></h1>
		</div>
              
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table align="center">
                <tr>
                <!-- <div class="footer"> -->
                     <th align="left" >LoginId<span style="color: red">*</span> </th>
                    <td><input type="text" name="login" size=20 placeholder="Enter Login Id"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password<span style="color: red">*</span></th>
                    <td><input type="password"  name="password" size=20 placeholder="Enter password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                        <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" style="width:79px" name="operation"
                        value="<%=LoginCtl.OP_SIGN_IN %>" > &nbsp; <input type="submit"
                      style="width:79px" name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" size=10 > &nbsp;</td>
                </tr>
                <tr><th></th>
                <td align="center"><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget My Password</b></a>&nbsp;</td>
            </tr>
            </table>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>
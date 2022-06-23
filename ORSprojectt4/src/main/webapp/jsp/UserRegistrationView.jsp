<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.UserRegistrationCtl"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.com.rays.ORSprojectt4.utility.HTMLUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.DataUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<body>
 	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
	 <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="./js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.com.rays.ORSprojectt4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>User Registration</h1>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table>

                <tr>
                    <th align="left">First Name<span style="color: red">*</span></th>
                    <td><input type="text" name="firstName" placeholder="Enter FirstName"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name<span style="color: red">*</span></th>
                    <td><input type="text" name="lastName" placeholder="Enter Last Name"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
                        
                   	<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId<span style="color: red">*</span></th>
                    <td><input type="text" name="login"
                        placeholder="Must be Email ID"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
                    <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password<span style="color: red">*</span></th>
                    <td><input type="password" name="password" placeholder="Enter Password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                    <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password<span style="color: red">*</span></th>
                    <td><input type="password" name="confirmPassword" placeholder="Re-enter Password"
                        value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
                    <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Gender<span style="color: red">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>

                    </td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
                </tr>

                <tr>
                    
					<th align="left">Date Of Birth<span style="color: red">*</span></th>
					<td><input type="text" name="dob" readonly="readonly" id="datepicker" size="20" placeholder="Select a Date"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </td>
					<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        &nbsp; <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP %>">
                        <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
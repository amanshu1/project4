<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.ChangePasswordCtl"%>
<%@page import="in.com.rays.ORSprojectt4.utility.DataUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Change Password</title></head>
</head>
<body>
	 <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.com.rays.ORSprojectt4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Change Password</h1>


            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
			 <H3>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H3>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>



                <tr>
                    <th align="center">Old Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="oldPassword" size=30 placeholder="Enter Old Password Here"
                        value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
                                      </td> 
                    <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
                </tr>

                <tr>
                    <th align="center">New Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="newPassword" size=30 placeholder="Enter New Password Here"
                        value=<%=DataUtility
                    .getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("newPassword")))%>> 
                                     </td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
                </tr>

                <tr>
                    <th align="center">Confirm Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="confirmPassword" size = 30 placeholder="Confirm New Password Here"
                        value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
                      </td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
                </tr>

                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>"> <input type="submit"
                        name="operation" value="<%= ChangePasswordCtl.OP_SAVE%>"> &nbsp;
                        <input type="submit"
                        name="operation" value="<%= ChangePasswordCtl.OP_RESET%>"></td>
                </tr>

            </table>
           
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>
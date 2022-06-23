<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.UserCtl"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.BaseCtl"%>
<%@page import="in.com.rays.ORSprojectt4.utility.DataUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.HTMLUtility"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>
<html>

<body>
    <form action="<%=ORSView.USER_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        <jsp:useBean id="bean" class="in.com.rays.ORSprojectt4.bean.UserBean"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("roleList");
        %>

        <center>
            <h1>Add User</h1>

            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
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
                    <td><input type="text" name="firstName" placeholder="Enter first name" size="20"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name<span style="color: red">*</span></th>
                    <td><input type="text" name="lastName" placeholder="Enter last name" size="20"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
                    <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId<span style="color: red">*</span></th>
                    <td><input type="text" name="login" placeholder="Enter Login ID" size="20"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"
                        <%=(bean.getId() > 0) ? "readonly" : ""%>></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                   <th align="left">Password<span style="color: red">*</span></th>
                    <td><input type="password" name="password" placeholder="Enter Password" size="20"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password<span style="color: red">*</span></th>
                    <td><input type="password" name="confirmPassword" placeholder="Re-enter password" size="20"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",
                    request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Gender<span style="color: red">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> 
                        <%=htmlList%>
                    </td>
                    <td style="position : fixed;"><font style="position: fixed;" color="red">
							<%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
					
                </tr>
                <tr>
                	
                   <th align="left">Role<span style="color: red">*</span></th>
                  <td>  <%=HTMLUtility.getList("roleId",String.valueOf(bean.getRoleId()),l)%></td>
                  <td style="position : fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font>
					</td>
                </tr>
                <tr>
					<th align="left">Mobile No. <span style="color: red">*</span></th>

					<td><input type="text" name="mobileno" size="20" maxlength="10"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
				</tr>
                <tr>
                    <th align="left">Date Of Birth (mm/dd/yyyy)<span style="color: red">*</span></th>
                   <td><input type="text" name="dob" id="datepicker" size="20"
						placeholder="Enter Date Of Birth" readonly="readonly"
						
						value="<%=DataUtility.getDateString(bean.getDob())%>">
						</td> <td style="position : fixed;"><font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
                        name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
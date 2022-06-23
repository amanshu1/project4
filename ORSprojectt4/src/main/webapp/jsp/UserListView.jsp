<%@page import="in.com.rays.ORSprojectt4.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.UserListCtl"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>
<html>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>UserListView</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="benn" class="in.com.rays.ORSprojectt4.bean.UserBean"
		scope="request"></jsp:useBean>

	<center>
		<h1>User List</h1>
		<h3>
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
		</h3>
		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

			<table width="100%">
				<tr>
					<td align="center"><label>FirstName :</label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						&emsp; <label>LoginId:</label> <input type="text" name="login"
						value="<%=ServletUtility.getParameter("login", request)%>">
						&emsp; <input type="submit" name="operation"
						value="<%=UserListCtl.OP_SEARCH%>"> <input type="submit"
						name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" cellspacing="0" cellpadding="10">
				<tr>
					<th><input type="checkbox" name="select" id="select_all">S.No.</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>LoginId</th>
					<th>Role</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Edit</th>
				</tr>


				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<UserBean> it = list.iterator();
					while (it.hasNext()) {
						UserBean bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>" <%if (bean.getRoleId() == 1) {%>
						disabled="disabled" <%}%>>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<%
						if (bean.getRoleId() == 1) {
					%><td>Admin</td>
					<%
						} else if (bean.getRoleId() == 2) {
					%><td>Student</td>
					<%
						} else if (bean.getRoleId() == 3) {
					%><td>College</td>
					<%
						} else if (bean.getRoleId() == 4) {
					%><td>Kiosk</td>
					<%
						} else if (bean.getRoleId() == 5) {
					%><td>Faculty</td>
					<%
						}
					%>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%><td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>
					<%
						UserModel model = new UserModel();
						if (list.size() < pageSize || model.nextPK() - 1 == benn.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>
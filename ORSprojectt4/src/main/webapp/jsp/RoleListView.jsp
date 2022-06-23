<%@page import="in.com.rays.ORSprojectt4.model.RoleModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.RoleListCtl"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.BaseCtl"%>
<%@page import="in.com.rays.ORSprojectt4.bean.RoleBean"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.com.rays.ORSprojectt4.servlet.ORSView"%>

<html>
<script src="<%= ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%= ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script> 
<head>
<meta charset="ISO-8859-1">
<title>RoleListView</title>
</head>

<jsp:useBean id="benn" class="in.com.rays.ORSprojectt4.bean.RoleBean"
	scope="request"></jsp:useBean>

<%@include file="Header.jsp"%>

<center>
	<h1>Role List</h1>
	<h3>
		<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
	</h3>
	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
		<table width="100%">
			<tr>
				<td align="center"><label>Name :</label> <input type="text"
					name="name"
					value="<%=ServletUtility.getParameter("name", request)%>">
					&nbsp; <input type="submit" name="operation"
					value="<%=RoleListCtl.OP_SEARCH%>"> <input type="submit"
					name="operation" value="<%=RoleListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<table border="1" width="100%" cellspacing="0" cellpadding="10">
			<tr>
				<th><input type="checkbox" name="select" id="select_all">S.No.</th>
				<th>S.No.</th>
				<th>Id</th>
				<th>Name</th>
				<th>Descriptiop</th>
				<th>Edit</th>
			</tr>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<RoleBean> it = list.iterator();
				while (it.hasNext()) {
					RoleBean bean = it.next();
			%>
			<tr>
				<td><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId() %>">
				<td><%=index++%></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDescription()%></td>
				<td><a href="RoleCtl?id=<%=bean.getId()%>">Edit</a></td>
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
					value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
				<%
					} else {
				%>
				<td><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
				<%
					}
				%>
				<td><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_DELETE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_NEW%>"></td>
				<%
					RoleModel model = new RoleModel();
					if (list.size() < pageSize || model.nextPK() - 1 == benn.getId()) {
				%>
				<td align="right"><input type="submit" name="operation"
					disabled="disabled" value="<%=RoleListCtl.OP_NEXT%>"></td>
				<%
					} else {
				%>
				<td align="right"><input type="submit" name="operation"
					value="<%=RoleListCtl.OP_NEXT%>"></td>
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
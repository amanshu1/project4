<%@page import="in.com.rays.ORSprojectt4.model.CollegeModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.CollegeListCtl"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="in.com.rays.ORSprojectt4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>CollegeListView</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="benn"
		class="in.com.rays.ORSprojectt4.bean.CollegeBean" scope="request"></jsp:useBean>
	<center>
		<h1>College List</h1>

		<h3>
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
		</h3>


		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">

			<table width="100%">
				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						<label>City :</label> <input type="text" name="city"
						value="<%=ServletUtility.getParameter("city", request)%>">
						<input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=CollegeListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%" cellspacing="0" cellpadding="10">
				<tr>
					<th><input type="checkbox" name="select" id="select_all">S.No.</th>
					<th>ID.</th>
					<th>Name.</th>
					<th>Address.</th>
					<th>State.</th>
					<th>City.</th>
					<th>PhoneNo.</th>
					<th>Edit</th>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<CollegeBean> it = list.iterator();

					while (it.hasNext()) {

						CollegeBean bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getState()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getPhoneNo()%></td>
					<td><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEW%>"></td>
					<td></td>
					<%
						CollegeModel model = new CollegeModel();
						if (list.size() < pageSize || model.nextPK() - 1 == benn.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>

</body>
</html>
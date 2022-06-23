<%@page import="in.com.rays.ORSprojectt4.utility.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.com.rays.ORSprojectt4.servlet.MarksheetMeritListCtl"%>
<%@page import="in.com.rays.ORSprojectt4.utility.ServletUtility"%>
<%@page import="in.com.rays.ORSprojectt4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Marksheet Merit List</title></head>
</head>
<body>
	<%@include file="Header.jsp"%>
    <center>
        <h1>Marksheet Merit List</h1>
 
        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
            <br>
            <table border="1" width="100%" cellspacing = "0" cellpadding="10">
            
                <tr>

                    <th>Rank</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>total</th>
                    <th>percentage</th>

                </tr>
            
               
               
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
					
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                        
                        int phy= DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
    					int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
    					int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
    					int total= phy + chem + math;
    					int percentage =(total * 100)/300;
    					
                %>
                <tr align="center">

                    <td><%=index++%></td>
                    <td><%=bean.getRollNo()%></td>
                    <td><%=bean.getName()%></td>
                    <td><%=bean.getPhysics()%></td>
                    <td><%=bean.getChemistry()%></td>
                    <td><%=bean.getMaths()%></td>
                    <td><%=total %></td>
                    <td><%=percentage %> % </td>

                </tr>

                <%
                    }
                %>
            </table>
            <table>
                <tr>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <br><br><br><br><br><br>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>
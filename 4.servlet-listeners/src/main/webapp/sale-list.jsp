<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.jdc.shop.model.entity.Voucher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sale History</title>
<link rel="stylesheet" href="resources/sale-list-style.css" />
</head>
<body>

	
	<%!
		String dateTimeFormatter(LocalDateTime date) {
			return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
	%>
	
	<%
		@SuppressWarnings("unchecked")
		List<Voucher> list = (List<Voucher>) request.getAttribute("data");
	%>

	<div class="container">
		<h1>Sale History</h1>

		<table class="table">
			<tr>
				<th>Voucher ID</th>
				<th>Sale Date Time</th>
				<th>Customer Name</th>
				<th>Item Count</th>
				<th>Sale Total</th>
				<th>Voucher Detail</th>
			</tr>

			<%
				for(Voucher v : list) {
			%>
				<tr>
					<td><%=v.getId() %></td>
					<td><%=dateTimeFormatter(v.getTime()) %></td>
					<td><%=v.getCustomer() %></td>
					<td><%=v.getItemCount() %></td>
					<td><%=v.getTotal() %></td>
					<td>
						<a href="sale-details?id=<%=v.getId()%>"  class="button">Voucher Detail</a>
					</td>
				</tr>
			<%
				}
			%>
		</table>
					
		<div class="home">
			<a href="index.jsp">Home</a>
		</div>
		
	</div>
</body>
</html>
<%@page import="com.jdc.shop.model.entity.SaleItem"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.jdc.shop.model.entity.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sale Detail</title>
<link rel="stylesheet" href="resources/sale-details-style.css">
</head>
<body>

	<%!
	String dateTimeFormatter(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}		
	%>
	
	<%
		Voucher voucher = (Voucher) request.getAttribute("voucher");
	%>
	
	
	<div class="container">
	
		<h1 class="header">Sale Detail</h1>
		
		<!-- Sale Summary -->
		<table class="table1">
			<tr>
				<td>Voucher ID</td>
				<td><%=voucher.getId() %></td>
			</tr>
			<tr>
				<td>Customer Name</td>
				<td><%=voucher.getCustomer() %></td>
			</tr>
			<tr>
				<td>Sale Date Time</td>
				<td><%=dateTimeFormatter(voucher.getTime()) %></td>
			</tr>
		</table>
		
		<!-- Sale Item Table -->

		<table class="table">
			<tr>
				<th>Product</th>
				<th>Category</th>
				<th>Unit Price</th>
				<th>Qty</th>
				<th>Total</th>
			</tr>
			
			<%
				for(SaleItem item : voucher.getSales()) {
			%>
			
				<tr>
					<td><%=item.getProduct().getName() %></td>
					<td><%=item.getProduct().getCategory() %></td>
					<td><%=item.getUnitPrice() %></td>
					<td><%=item.getCount() %></td>
					<td><%=item.getTotal() %></td>
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
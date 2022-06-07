<%@page import="java.text.DecimalFormat"%>
<%@page import="com.jdc.shop.model.ShoppingCart"%>
<%@page import="com.jdc.shop.model.entity.SaleItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Cart</title>
<link rel="stylesheet" href="resources/mycart-style.css">
</head>
<body>

	<div class="container">

		<h1 class="header">My Cart</h1>

		<p class="title">Item Detail in Shopping Cart.</p>
				
		<div class="home">
			<a href="index.jsp">Home</a>
		</div>
		
		<%!
		String format(int data) {
			return new DecimalFormat("#,##0").format(data);
		}
		%>
		
		<%
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		%>

		<table class="table">
			<tr>
				<th>Product</th>
				<th>Category</th>
				<th>Unit Price</th>
				<th></th>
				<th>Quantity</th>
				<th></th>
				<th>Total</th>
			</tr>

			<% for(SaleItem item : cart.getItems()) {
			%>
			<tr>
				<td><%=item.getProduct().getName()%></td>
				<td><%=item.getProduct().getCategory()%></td>
				<td><%=item.getUnitPrice()%></td>
				<td>
					<a href="cart-minus?product=<%=item.getProduct().getId() %>" class="button">-</a>
				</td>
				<td><%=item.getCount()%></td>
				<td>
					<a href="cart-plus?product=<%=item.getProduct().getId() %>" class="button">+</a>
				</td>
				<td><%=item.getTotal() %></td>
			</tr>

			<% } %>
			
			<tr>
				<td colspan="4" class="col-total">Total</td>
				<td><%=cart.itemCount() %></td>
				<td></td>
				<td><%=format(cart.total()) %></td>
			</tr>
		</table>
		
		<div class="user-input-form">
			<form action="checkout" method="post">
				<label for="name">User Name* :</label>
				<input type="text" name="customer" id="name" placeholder="Enter Customer Name"/>
				<button class="submit-button" type="submit">Check Out</button>
			</form>
		</div>

	</div>
</body>
</html>
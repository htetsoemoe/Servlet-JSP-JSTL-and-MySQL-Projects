<%@page import="com.jdc.shop.model.ShoppingCart"%>
<%@page import="com.jdc.shop.model.entity.Product"%>
<%@page import="com.jdc.shop.model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listener Demo</title>
<link rel="stylesheet" href="resources/style.css">
</head>
<body>


	<div class="container">
	
		<h1 class="header">Product List</h1>
		
		<div class="show-cart-container">
			<span class="shopping-cart-lable">Shopping Cart :</span>
			
			<a class="shopping-cart" href="cart-show">
				<img src="resources/shopping-cart.svg" class="shopping-cart-photo" />
				<%ShoppingCart cart = (ShoppingCart) session.getAttribute("cart"); %>
				<span class="badge"><%= null == cart ? 0 : cart.itemCount() %></span>
			</a>
		</div>
		
		<div class="clear-cart">
			<%
				if(null != cart && cart.itemCount() > 0) {
			%>
				<a href="cart-clear">Clear Shopping Cart</a>
			<%
				}
			%>
		</div>

		<%
		ProductModel model = (ProductModel) application.getAttribute("products");
		%>
		
		<table class="table">
			<tr>
				<th>ID</th>
				<th>Category</th>
				<th>Name</th>
				<th>Price</th>
				<th></th>
			</tr>

			<%
			for (int i = 0; i < model.getList().size(); i++) {
				Product p = model.getList().get(i);
			%>

			<tr>
				<td><%=p.getId()%></td>
				<td><%=p.getCategory()%></td>
				<td><%=p.getName()%></td>
				<td><%=p.getPrice()%></td>
				<td><a class="card-add" href="cart-add?product=<%=p.getId()%>">Add to Cart</a></td>
			</tr>

			<%
			}
			%>

		</table>
		
		<div class="sale-history">
			<a href="sale-history">Sale History</a>
		</div>
	</div>
</body>
</html>
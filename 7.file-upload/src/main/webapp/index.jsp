<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.jdc.upload.Sale"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload</title>
<style type="text/css">
	body {
		margin: 0;
		min-height: 100vh;
		padding: 4rem;
	}
	
	form {
		margin-bottom: 30px;
	}
	
	table {
		width: 80%;
		border-collapse: collapse;
	}
	
	table, tr, th, td {
		border: 1px solid black;
	}
	
	.photos {
		margin-top: 10px;
		display: flex;
		flex-wrap: wrap;
	}
	
	.photos img {
		margin-right: 15px;
		margin-bottom: 15px;
	}
}
	
</style>
</head>
<body>
	<h1>File Upload</h1>
	<form action="upload" method="post" enctype="multipart/form-data">
		<label for="">Select File</label> 
		<input type="file" name="uploadFile" />
		<button type="submit">Upload</button>
	</form>

	<%
	@SuppressWarnings("unchecked")
	List<Sale> sale = (List<Sale>) request.getAttribute("list");

	if (null != sale && !sale.isEmpty()) {
	%>

	<table>
		<thead>
			<tr>
				<th>Category</th>
				<th>Product</th>
				<th>Price</th>
				<th>Count</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Sale item : sale) {
			%>
			<tr>
				<td><%=item.getCategory()%></td>
				<td><%=item.getProduct()%></td>
				<td><%=item.getPrice()%></td>
				<td><%=item.getCount()%></td>
				<td><%=item.getTotal() %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<%
	}
	%>
	
	<hr />
	
	<h1>Photo Upload</h1>
	
	<form action="image-upload" method="post" enctype="multipart/form-data">
		<label >Photo</label>
		<input type="file" accept="image/*" multiple="multiple" name="imageFile"/>
		<button type="submit">Upload Image</button>
	</form>
	
	<div class="photos">
		<%if(null != request.getAttribute("photos")) {
			@SuppressWarnings("unchecked")
			List<String> photos = (List<String>) request.getAttribute("photos");
			
			for(String photo : photos) {
		%>
			<img src="<%=getImagePath(photo)%>" alt="uploadPhoto" width="200px" height="200px"/>
		<%
			} // close of for
		} // close of if
		%>
		
		<%!
			String getImagePath(String image) {
				return getServletContext().getContextPath().concat("/images/").concat(image);	
			}		
		%>
	</div>
	
	<hr />
	<h1>File Download</h1>
	<a href="download">File Download</a>
</body>
</html>
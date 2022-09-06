package com.jdc.upload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownload extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var products = """
				[
					{"Name":"Honda Click 125", "Price": "$500"},
					{"Name":"Honda Scoopy 125", "Price": "$450"},
					{"Name":"Honda Tricker 250", "Price": "$1500"},
					{"Name":"Honda Tour Bike 500", "Price": "$10000"}
				]
				""";
		
		resp.setHeader("Content-Type", "application/json");
		resp.setHeader("Content-Disposition", "attachment; filename=\"products.json\"");
		
		resp.getWriter().append(products);
	}

}

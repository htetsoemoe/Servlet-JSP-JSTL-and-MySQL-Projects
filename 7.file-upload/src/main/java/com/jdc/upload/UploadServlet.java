package com.jdc.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var part = req.getPart("uploadFile");

		var list = new ArrayList<String>();

		if (null != part) {
			try (var reader = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
				String line = null;

				while (null != (line = reader.readLine())) {
					list.add(line);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		req.setAttribute("list", list.stream().skip(1).map(Sale::new).toList());
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}

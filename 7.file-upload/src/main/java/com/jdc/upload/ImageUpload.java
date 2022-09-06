package com.jdc.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image-upload")
@MultipartConfig
public class ImageUpload extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var parts = req.getParts();
		var list = new ArrayList<String>();
		
		if (null != parts) {
			
			for(var part : parts) {
				if (part.getContentType().startsWith("image")) {
					var fileName = part.getSubmittedFileName(); // return upload file's name (e.g, bike1.jpg)
					list.add(fileName);
					
					var saveFile = Path.of(getServletContext().getRealPath("images"), part.getSubmittedFileName());
					//D:\Java Servlet JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\7.file-upload\images\bike1.jpg
					System.out.println(saveFile.toString());
					Files.copy(part.getInputStream(), saveFile, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
		
		req.setAttribute("photos", list);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	/**
	 * Single Photo Upload
	 * 
	 * var part = req.getPart("imageFile");
		
		if (null != part) {
			var file = Path.of(getServletContext().getRealPath("images"), part.getSubmittedFileName(), part.getSubmittedFileName());
			Files.copy(part.getInputStream(), file);
			req.setAttribute("photo", part.getSubmittedFileName());
		}
		
	 */
}

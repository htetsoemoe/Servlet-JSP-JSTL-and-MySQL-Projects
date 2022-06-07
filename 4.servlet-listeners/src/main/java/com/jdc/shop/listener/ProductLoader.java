package com.jdc.shop.listener;

import java.io.BufferedReader;
import java.io.FileReader;

import com.jdc.shop.model.ProductModel;
import com.jdc.shop.model.entity.Product;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ProductLoader implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Create Product Model
		var model = new ProductModel();

		// Read File
		String filePath = sce.getServletContext().getRealPath("/WEB-INF/product.txt");

		try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {

			String line = null;

			while (null != (line = input.readLine())) {
				var array = line.split("\t");
				model.add(new Product(array[1], array[0], Integer.parseInt(array[2])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Add Product Model to Application Scope
		sce.getServletContext().setAttribute("products", model);
	}
}

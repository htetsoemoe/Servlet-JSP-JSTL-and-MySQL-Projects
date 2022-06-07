package com.jdc.shop.listener;

import com.jdc.shop.model.SaleModel;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SalesLoader implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		var model = SaleModel.getSaleModel();
		sce.getServletContext().setAttribute("sale.model", model);
	}
}

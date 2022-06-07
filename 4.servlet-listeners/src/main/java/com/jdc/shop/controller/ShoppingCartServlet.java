package com.jdc.shop.controller;

import java.io.IOException;

import com.jdc.shop.model.ProductModel;
import com.jdc.shop.model.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/cart-add", 
			"/cart-show", 
			"/cart-clear", 
			"/cart-minus",
			"/cart-plus"
})
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()){
			case "/cart-add":
				addToCart(req, resp);
				break;
			case "/cart-show":
				getServletContext().getRequestDispatcher("/my-cart.jsp").forward(req, resp);
				break;
			case "/cart-clear":
				clear(req, resp);
				break;
			case "/cart-minus":
			case "/cart-plus":
				changeCartItemCount(req, resp);
				break;
		}
		
	}
	
	private void changeCartItemCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// get product id from request scope
		var productId = req.getParameter("product");
		
		//get action from request scope
		var plus = "/cart-plus".equals(req.getServletPath());
		
		// get shopping cart from session scope
		var cart = (ShoppingCart) req.getSession().getAttribute("cart");
		
		// Update Shopping Cart Model
		cart.changeItemCount(plus, Integer.parseInt(productId));
		
		var link = req.getContextPath().concat("/my-cart.jsp");
		resp.sendRedirect(link);
	}

	private void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var session = req.getSession();
		
		if (null != session) {
			var cart = (ShoppingCart) session.getAttribute("cart");
			cart.clear();
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get Product id
		var strId = req.getParameter("product");
		
		// get Product model from application scope
		var productModel = (ProductModel) getServletContext().getAttribute("products");
		
		// find product from product model
		var product = productModel.findById(Integer.parseInt(strId));
		
		// get shopping cart from session scope
		var session = req.getSession(true);
		var cart = (ShoppingCart) session.getAttribute("cart");
		
		if (null == cart) {
			// create shopping cart
			cart = ShoppingCart.generate();
			// add cart to session scope
			session.setAttribute("cart", cart);
		}
		
		// add product to cart, which exists in session scope
		cart.add(product);
		
		// forward to index.jsp
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}

package com.jdc.shop.model;

import java.io.Serializable;
import java.util.List;

import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.SaleItem;

// If you want to use this type in Session Scope, you must extends Serializable Interface
public interface ShoppingCart extends Serializable{
	
	void add(Product product);
	
	void clear();
	
	int itemCount();
	
	int total();
	
	List<SaleItem> getItems();
	
	void changeItemCount(boolean flag, int productId);
	
	public static ShoppingCart generate() {
		return new ShoppingCartImpl();
	}
}

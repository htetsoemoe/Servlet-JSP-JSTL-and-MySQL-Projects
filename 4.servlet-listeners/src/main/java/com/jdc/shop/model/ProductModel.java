package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.Product;

public class ProductModel {
	private List<Product> list;
	
	public ProductModel() {
		list = new ArrayList<Product>();
	}
	
	public void add(Product p) {
		list.add(p);
		p.setId(list.size());
	}
	
	public List<Product> getList() {
		return new ArrayList<Product>(list);
	}

	public Product findById(int id) {
		for(Product p : list) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
}

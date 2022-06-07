package com.jdc.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Voucher implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private LocalDateTime time;
	private String customer;

	private List<SaleItem> sales;

	public Voucher() {
		sales = new ArrayList<SaleItem>();
	}
	
	public int getItemCount() {
		int count = 0;
		
		for(SaleItem s : sales) {
			count += s.getCount();
		}
		
		return count;
	}
	
	public int getTotal() {
		
		int total = 0;
		
		for(SaleItem s : sales) {
			total += s.getTotal();
		}
		
		return total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<SaleItem> getSales() {
		return sales;
	}

	public void setSales(List<SaleItem> sales) {
		this.sales = sales;
	}

}

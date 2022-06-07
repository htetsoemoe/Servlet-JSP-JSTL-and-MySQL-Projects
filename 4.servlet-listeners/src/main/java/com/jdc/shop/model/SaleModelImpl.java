package com.jdc.shop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.SaleItem;
import com.jdc.shop.model.entity.Voucher;

public class SaleModelImpl implements SaleModel{
	
	private List<Voucher> vouchers;
	
	public SaleModelImpl() {
		vouchers = new ArrayList<Voucher>();
	}

	@Override
	public List<Voucher> getSaleHistory() {
		return new ArrayList<Voucher>(vouchers);
	}

	@Override
	public Voucher findById(int id) {
		for(var voucher : vouchers) {
			if(voucher.getId() == id) {
				return voucher;
			}
		}
		return null;
	}

	@Override
	public int createVoucher(String customer, List<SaleItem> saleItems) {
		var voucher = new Voucher();
		voucher.setCustomer(customer);
		voucher.setSales(saleItems);
		voucher.setTime(LocalDateTime.now());
		
		voucher.setId(vouchers.size() + 1);
		vouchers.add(voucher);
		
		return voucher.getId();
	}



}

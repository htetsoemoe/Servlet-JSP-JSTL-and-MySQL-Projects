package com.jdc.shop.model;

import java.util.List;

import com.jdc.shop.model.entity.SaleItem;
import com.jdc.shop.model.entity.Voucher;

public interface SaleModel {
	
	static SaleModel getSaleModel() {
		return new SaleModelImpl();
	}

	List<Voucher> getSaleHistory();

	Voucher findById(int id);

	int createVoucher(String customer, List<SaleItem> saleItems);
}

package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.SaleItem;

class ShoppingCartImpl implements ShoppingCart{

	private static final long serialVersionUID = 1L;
	
	private List<SaleItem> items;
	
	public ShoppingCartImpl() {
		items = new ArrayList<SaleItem>();
	}

	@Override
	public void add(Product product) {
		// Checks sale item is already exists in shopping cart using product id
		var item = findItemByProduct(product.getId());
		
		// There is no sale item in shopping cart, creates one sale item
		if(null == item) {
			item = new SaleItem();
			item.setProduct(product);
			items.add(item); // adds sale item to shopping cart's sale item list
		}
		
		// If sale item is already exists in shopping cart, add one 
		item.addOne();
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public int itemCount() {
		int count = 0;
		for (SaleItem saleItem : items) {
			count += saleItem.getCount();
		}
		return count;
	}

	@Override
	public int total() {
		int total = 0;
		for (SaleItem saleItem : items) {
			total += saleItem.getTotal();
		}
		return total;
	}
	
	private SaleItem findItemByProduct(int productId) {
		
		for (SaleItem saleItem : items) {
			if(saleItem.getProduct().getId() == productId) {
				return saleItem;
			}
		}
		
		return null;
	}

	@Override
	public List<SaleItem> getItems() {
		return new ArrayList<SaleItem>(items);
	}

	@Override
	public void changeItemCount(boolean flag, int productId) {
		var saleItem = findItemByProduct(productId);
		
		if (null != saleItem) {
			saleItem.changeCount(flag);
			
			if (saleItem.getCount() == 0) {
				items.remove(saleItem); // removes sale item from shopping cart
			}
		}
	}

}

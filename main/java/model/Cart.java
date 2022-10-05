package model;

import java.util.ArrayList;
import java.util.List;

//Cart: chứa thông tin về 1 đơn hàng hiện tại
public class Cart {
	private List<Product> items;
	private int size;

	public Cart() {
		items = new ArrayList<>();
		size = 0;
	}
	
	public List<Product> getItems() {
		return items;
	}
	
	public int getSize() {
		return size;
	}
	
	public double getAmount( ) {
		double s = 0;
		for(Product x : items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s*100.0)/100.0;
	}
	
	// them san pham vao gio hang
	public void add(Product ci) {
		// neu ci da ton tai trong items thi chi can tang Number cua ci trong items len 1
		for(Product x : items) {
			if(ci.getId() == x.getId()) {
				x.setNumber(x.getNumber() + 1);
				size ++;
				return;
			}
		}
		items.add(ci);
		size ++;
	}
	
	// xoa san pham co id khoi gio hang
	public void remove(int id) {
		for(Product x : items) {
			if(id == x.getId()) {
				size = size - x.getNumber();
				items.remove(x);				
				return;
			}
		}
	}
	
	// giam so luong 1 san pham o gio hang
	public void descreaseOne(int id) {
		for(Product x : items) {
			// neu san pham co id ton tai trong gio hang
			if (id == x.getId()) {
				// neu so luong sp lon hon thi giam so number di 1
				if(x.getNumber() > 1) {
					x.setNumber(x.getNumber() - 1);
				} else {
					items.remove(x);
				}
				size --;
				return;
			}
		}
	}
}

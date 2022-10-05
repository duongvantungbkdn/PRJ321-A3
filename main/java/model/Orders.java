package model;

import java.util.Date;
import java.util.List;

//Orders lớp: chứa thông tin về một đơn hàng,
//bao gồm danh sách sản phẩm của đơn hàng, thông tin người mua hàng
public class Orders {
	private int orderId;
	private float price; //total amount of order
	private int status;
	private Date orderDate;
	private String address; // buyer's address
	private String phoneNumber; // buyer's phonrNumber
	private String userMail; // buyer's email
	private String discount;
	private List<ProductOrders> lp;
	private Date receivedDate;
	
	public Orders(int orderId, float price, int status, Date orderDate, String address, String phoneNumber,
			String userMail, List<ProductOrders> lp, Date receivedDate) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userMail = userMail;
		this.lp = lp;
		this.receivedDate = receivedDate;
	}

	public Orders(int status, String address, String phoneNumber, String userMail, String discount, Date receivedDate) {
		super();
		this.status = status;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userMail = userMail;
		this.discount = discount;
		this.receivedDate = receivedDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public List<ProductOrders> getLp() {
		return lp;
	}

	public void setLp(List<ProductOrders> lp) {
		this.lp = lp;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}	
	
}

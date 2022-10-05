package model;

public class User {
	private String email;
	private String name;
	private String phone;
	private String address;
	private String pass;
	private int role;
	
	public User(String email, String name, String phone, String address, String pass, int role) {
		super();
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.pass = pass;
		this.role = role;
	}

	public User(String email, String name, String phone, String address, int role) {
		super();
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	public User(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPass() {
		return pass;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "mail=" + email + " pass=" + pass + " address=" + address + " phone=" + phone;
	}
}

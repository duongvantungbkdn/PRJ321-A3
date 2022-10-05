package model;

//Product lớp: chứa thông tin của một sản phẩm trên website. 
public class Product {
	private int id;
	private String name;
	private String description;
	private String src;
	private String type;
	private String brand; //product's category
	private int number;
	private float price;
	
	public Product() {
		super();
	}

	public Product(int id, String name, String description, String src, String type, String brand, int number,
			float price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.src = src;
		this.type = type;
		this.brand = brand;
		this.number = number;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "id=" + id + " name=" + name + " number=" + number +" type=" + type + " brand=" + brand + " price=" + price;
	}
	
}

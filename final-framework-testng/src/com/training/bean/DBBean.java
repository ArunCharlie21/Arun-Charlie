package com.training.bean;

/**
 * 
 * @author Naveen
 * @see this class shall get the bean data 
 */
public class DBBean {
	private String url; 
	private String driver; 
	private String userName; 
	private String password;
	private String productname;
	private String metatag;
	private String model;
	private String price;
	private String quantity;
	private String quantity1;
	private String price1;
	
	public DBBean(){}
	
	public DBBean(String url, String driver, String userName, String password, String productname, String metatag, String model, String price, String quantity, String quantity1, String price1) {
		super();
		this.url = url;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
		this.productname = productname;
		this.metatag = metatag;
		this.model = model;
		this.price = price;
		this.quantity = quantity;
		this.quantity1 = quantity1;
		this.price1 = price1;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProductName() {
		return productname;
	}

	public void setProductName(String productname) {
		this.productname = productname;
	}
	
	public String getMetatag() {
		return metatag;
	}

	public void setMetatag(String metatag) {
		this.metatag = metatag;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	
}

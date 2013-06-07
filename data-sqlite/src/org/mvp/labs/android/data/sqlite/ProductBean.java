package org.mvp.labs.android.data.sqlite;

public class ProductBean {

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public float getProductprice() {
		return productprice;
	}

	public void setProductprice(float productprice) {
		this.productprice = productprice;
	}

	public int id;
	public String productname="";
	public float productprice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

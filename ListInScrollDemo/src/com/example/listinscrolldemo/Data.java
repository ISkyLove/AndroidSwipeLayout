package com.example.listinscrolldemo;

public class Data {
	private String name;
	private String nowPrice;
	private String up;
	private String down;
	private String num;
	private String detail;
	public Data(String name, String nowPrice, String up, String down,
			String num, String detail) {
		super();
		this.name = name;
		this.nowPrice = nowPrice;
		this.up = up;
		this.down = down;
		this.num = num;
		this.detail = detail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public String getDown() {
		return down;
	}
	public void setDown(String down) {
		this.down = down;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}

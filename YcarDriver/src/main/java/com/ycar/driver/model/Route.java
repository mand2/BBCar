package com.ycar.driver.model;

public class Route {
	private int d_idx;
	private char type;
	private String place;
	
	
	
	public Route(int d_idx, char type, String place) {
		this.d_idx = d_idx;
		this.type = type;
		this.place = place;
	}
	
	public Route() {
	}

	public int getD_idx() {
		return d_idx;
	}
	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "Route [d_idx=" + d_idx + ", type=" + type + ", place=" + place + "]";
	}
	
	
}

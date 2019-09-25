package com.ycar.reservation.domain;

public class SearchCarpool {

	private String date; //s-shortforsearch 날짜검색
	private String time; //시간검색 
	private String startPoint; //시작지검색 
	private String endPoint; //도착지검색
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	
	
	@Override
	public String toString() {
		return "SearchCarpool [date=" + date + ", time=" + time + ", startPoint=" + startPoint + ", endPoint="
				+ endPoint + "]";
	}
	

}
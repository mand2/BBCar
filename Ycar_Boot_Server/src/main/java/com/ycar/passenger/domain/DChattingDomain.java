package com.ycar.passenger.domain;

import java.util.Date;

public class DChattingDomain {

	private int dr_idx;
	private String r_confirm;
	private int p_idx;
	private Date date;
	private String d_starttime;
	private String d_endtime;
	private String d_startpoint;
	private String d_endpoint;
	private String d_commute;
	private int d_fee;
	private int d_distance;
	private String p_nickname;

	/**
	 * @param dr_idx
	 * @param r_confirm
	 * @param p_idx
	 * @param date
	 * @param d_starttime
	 * @param d_endtime
	 * @param d_startpoint
	 * @param d_endpoint
	 * @param d_commute
	 * @param d_fee
	 * @param d_distance
	 * @param p_nickname
	 */
	public DChattingDomain(int dr_idx, String r_confirm, int p_idx, Date date, String d_starttime, String d_endtime,
			String d_startpoint, String d_endpoint, String d_commute, int d_fee, int d_distance, String p_nickname) {
		this.dr_idx = dr_idx;
		this.r_confirm = r_confirm;
		this.p_idx = p_idx;
		this.date = date;
		this.d_starttime = d_starttime;
		this.d_endtime = d_endtime;
		this.d_startpoint = d_startpoint;
		this.d_endpoint = d_endpoint;
		this.d_commute = d_commute;
		this.d_fee = d_fee;
		this.d_distance = d_distance;
		this.p_nickname = p_nickname;
	}

	public int getDr_idx() {
		return dr_idx;
	}

	public void setDr_idx(int dr_idx) {
		this.dr_idx = dr_idx;
	}

	public String getR_confirm() {
		return r_confirm;
	}

	public void setR_confirm(String r_confirm) {
		this.r_confirm = r_confirm;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getD_starttime() {
		return d_starttime;
	}

	public void setD_starttime(String d_starttime) {
		this.d_starttime = d_starttime;
	}

	public String getD_endtime() {
		return d_endtime;
	}

	public void setD_endtime(String d_endtime) {
		this.d_endtime = d_endtime;
	}

	public String getD_startpoint() {
		return d_startpoint;
	}

	public void setD_startpoint(String d_startpoint) {
		this.d_startpoint = d_startpoint;
	}

	public String getD_endpoint() {
		return d_endpoint;
	}

	public void setD_endpoint(String d_endpoint) {
		this.d_endpoint = d_endpoint;
	}

	public String getD_commute() {
		return d_commute;
	}

	public void setD_commute(String d_commute) {
		this.d_commute = d_commute;
	}

	public int getD_fee() {
		return d_fee;
	}

	public void setD_fee(int d_fee) {
		this.d_fee = d_fee;
	}

	public int getD_distance() {
		return d_distance;
	}

	public void setD_distance(int d_distance) {
		this.d_distance = d_distance;
	}

	public String getP_nickname() {
		return p_nickname;
	}

	public void setP_nickname(String p_nickname) {
		this.p_nickname = p_nickname;
	}

}

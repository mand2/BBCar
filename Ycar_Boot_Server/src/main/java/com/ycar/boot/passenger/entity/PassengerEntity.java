package com.ycar.boot.passenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 탑승자 table
@Entity
@Table(name = "PASSENGER")
public class PassengerEntity {

	@Id
	@Column
	private int p_idx;
	@Column
	private String p_option;
	@Column
	private String nickname;

	public String getP_option() {
		return p_option;
	}

	public void setP_option(String p_option) {
		this.p_option = p_option;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "PassengerEntity [p_idx=" + p_idx + ", p_option=" + p_option + ", nickname=" + nickname + "]";
	}

}

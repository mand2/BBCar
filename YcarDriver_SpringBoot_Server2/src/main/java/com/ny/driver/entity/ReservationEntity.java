package com.ny.driver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RESERVATION")
@SecondaryTables({
	@SecondaryTable(name = "D_CARPOOL", pkJoinColumns = @PrimaryKeyJoinColumn(name = "dr_idx")),
	@SecondaryTable(name = "DRIVER", pkJoinColumns = @PrimaryKeyJoinColumn(name = "d_idx")),
	@SecondaryTable(name = "PASSENGER", pkJoinColumns = @PrimaryKeyJoinColumn(name = "p_idx"))
})
//@Getter
//@Setter
public class ReservationEntity {
	
	@Id
	@Column(length = 10)
	private long r_idx;
	
	@Column(length = 1, nullable = false)
	private String r_confirm;
	
	@Column(table = "D_CARPOOL")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	private Date d_date;
	
	@Column(length = 255, nullable = false, table = "D_CARPOOL")
	private String d_startpoint;
	
	@Column(length = 255, nullable = false, table = "D_CARPOOL")
	private String d_endpoint;

	@Override
	public String toString() {
		return "ReservationEntity [r_idx=" + r_idx + ", r_confirm=" + r_confirm + ", d_date=" + d_date
				+ ", d_startpoint=" + d_startpoint + ", d_endpoint=" + d_endpoint + "]";
	}

	public long getR_idx() {
		return r_idx;
	}

	public void setR_idx(long r_idx) {
		this.r_idx = r_idx;
	}

	public String getR_confirm() {
		return r_confirm;
	}

	public void setR_confirm(String r_confirm) {
		this.r_confirm = r_confirm;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
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
	
	
	
}

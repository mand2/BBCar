package com.ycar.boot.passenger.domain;

public class PerMemoDomain {
	private int m_idx;
	private String context;

	/**
	 * @param m_idx
	 * @param context
	 */
	public PerMemoDomain(int m_idx, String context) {
		super();
		this.m_idx = m_idx;
		this.context = context;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}

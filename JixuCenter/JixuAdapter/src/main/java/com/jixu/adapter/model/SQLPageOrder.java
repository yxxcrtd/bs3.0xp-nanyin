package com.jixu.adapter.model;

public class SQLPageOrder {
	//待执行的sql语句
	private String sql;

	//startnum和endnum拜访的顺序	oracle先<再>有明显的性能优化
	private int[] orders;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int[] getOrders() {
		return orders;
	}

	public void setOrders(int[] orders) {
		this.orders = orders;
	}
}

package com.joyintech.tams.invest.common.risktrialrds.dto;

public class ExprNode {

	private String value;
	private ExprNode left;
	private ExprNode right;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ExprNode getLeft() {
		return left;
	}
	public void setLeft(ExprNode left) {
		this.left = left;
	}
	public ExprNode getRight() {
		return right;
	}
	public void setRight(ExprNode right) {
		this.right = right;
	}
}

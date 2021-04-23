/*
 * 文件名：ValidMessage.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月5日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.validation;

/**
 * 验证消息<br>
 * 画面输入值验证，验证不通过时统一使用该消息进行显示
 *
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see ValidMessage
 * @since 1.0
 */
public class ValidMessage implements java.io.Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2690334027530168383L;

	/**
	 * jsp画面定义的元素ID或name
	 */
	private String field;

	/**
	 *
	 */
	private String message;

	/**
	 * 行ID（只有grid表格时使用）
	 */
	private String rowId;

	/**
	 * 排序
	 */
	private int order;

	/**
	 * grid列名
	 */
	private String column;

	/**
	 * 组件名（在组件内部做业务逻辑验证时需要指定该参数）
	 */
	private String componentName;

	/**
	 * 获取 field
	 *
	 * @return field.
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置 field
	 *
	 * @param field
	 *            field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 获取 message
	 *
	 * @return message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置 message
	 *
	 * @param message
	 *            message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	public ValidMessage() {
		super();
	}
	
	/**
	 * @param field
	 *            画面元素ID
	 * @param message
	 *            错误消息
	 */
	public ValidMessage(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	/**
	 * @param field
	 *            画面元素ID
	 * @param message
	 *            错误消息
	 * @param componentName
	 *            组件名称
	 */
	public ValidMessage(String field, String message, String componentName) {
		super();
		this.field = field;
		this.message = message;
		this.componentName = componentName;
	}

	/**
	 * @param message
	 *            错误消息
	 */
	public ValidMessage(String message) {
		super();
		this.message = message;
	}

	/**
	 * 获取 rowId
	 * 
	 * @return rowId.
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * 设置 rowId
	 * 
	 * @param rowId
	 *            rowId
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	/**
	 * 获取 column
	 * 
	 * @return column.
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * 设置 column
	 * 
	 * @param column
	 *            column
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * 获取 order
	 * 
	 * @return order.
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 设置 order
	 * 
	 * @param order
	 *            order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName
	 *            the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

}

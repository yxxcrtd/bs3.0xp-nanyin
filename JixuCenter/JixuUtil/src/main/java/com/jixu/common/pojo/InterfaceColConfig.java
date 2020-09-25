package com.jixu.common.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/** 
 * @ClassName 		InterfaceSourceColCfg
 * @author  		Pan Wei 
 * @Date 			2016年11月24日 下午11:45:26 	
 * @Description		[InterfaceSource(接口配置总表),配置了接口的基本信息,与InterfaceColConfig(配置基础列和增强列的计算方式)和InterfaceMsgColMap(配置如何把增强列匹配到对应的消息字段)一起把输入或者输出的数据源数据转换成符合需求的数据格式]  
 */

@Entity 
@Table(name="ADPT_InterfaceColConfig") 
public class InterfaceColConfig implements Serializable{

	private static final long serialVersionUID = -1601293247228447530L;
	private int id;
	/**
	 * 列名
	 */
	private String colname;
	
	/**
	 * 列类型
	 * 1.基础列
	 * 2.增强列
	 */
	private int colType;	
	
	/**
	 * 第几列
	 */
	private int colNO;
	
	/**
	 * 起始位
	 */
	private int colStartNO;
	
	/**
	 * 结束字符
	 */
	private int colEndNo;
	
	/**
	 * 导入功能:	1.	基础列: 验证函数
	 * 			2.	增强列: 功能函数
	 * 
	 * 导出功能:	1.	基础列: 选择的数据源列名
	 * 			2.	增强列: 功能函数
	 * 
	 */
	private String funcClass;
	
	/**
	 * 功能参数(支持表达式)
	 */
	private String funcParams;
	
	/**
	 * 待转换的字符
	 */
	private String charFrom;
	
	/**
	 * 转换字符
	 */
	private String charTo;
	
	private String filterFuncClass;
	
	private String filterFieldValue;
	
	private int filterType;

	/**
	 * 执行的顺序
	 */
	private int orderNum;
	
	/**
	 * 接口源ID
	 */
	private InterfaceSource interfacesource;
	
	public InterfaceColConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public InterfaceColConfig(int id) {
		this.id=id;
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.tool.GenCustomIntIDByDate", parameters={
			@Parameter(name = "seq_name", value = "ADPT_InterfaceColCfg_ID_SEQ")})
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="colname")
	public String getColname() {
		return colname;
	}
	
	public void setColname(String colname) {
		this.colname = colname;
	}

	@Column(name="colType")
	public int getColType() {
		return colType;
	}
	
	public void setColType(int colType) {
		this.colType = colType;
	}

	@Column(name="colNO")
	public int getColNO() {
		return colNO;
	}

	public void setColNO(int colNO) {
		this.colNO = colNO;
	}

	@Column(name="colStartNO")
	public int getColStartNO() {
		return colStartNO;
	}

	public void setColStartNO(int colStartNO) {
		this.colStartNO = colStartNO;
	}

	@Column(name="colEndNo")
	public int getColEndNo() {
		return colEndNo;
	}
	
	public void setColEndNo(int colEndNo) {
		this.colEndNo = colEndNo;
	}
	
	@Column(name="funcClass")
	public String getFuncClass() {
		return funcClass;
	}
	public void setFuncClass(String funcClass) {
		this.funcClass = funcClass;
	}

	@Column(name="funcParams")
	public String getFuncParams() {
		return funcParams;
	}

	public void setFuncParams(String funcParams) {
		this.funcParams = funcParams;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="InterfacesourceID")
	public InterfaceSource getInterfacesource() {
		return interfacesource;
	}

	public void setInterfacesource(InterfaceSource interfacesource) {
		this.interfacesource = interfacesource;
	}

	@Column(name="charFrom")
	public String getCharFrom() {
		return charFrom;
	}
	
	public void setCharFrom(String charFrom) {
		this.charFrom = charFrom;
	}

	@Column(name="charTo")
	public String getCharTo() {
		return charTo;
	}
	
	public void setCharTo(String charTo) {
		this.charTo = charTo;
	}

	@Column(name="filterFuncClass")
	public String getFilterFuncClass() {
		return filterFuncClass;
	}

	public void setFilterFuncClass(String filterFuncClass) {
		this.filterFuncClass = filterFuncClass;
	}

	@Column(name="filterFieldValue")
	public String getFilterFieldValue() {
		return filterFieldValue;
	}

	public void setFilterFieldValue(String filterFieldValue) {
		this.filterFieldValue = filterFieldValue;
	}

	@Column(name="filterType")
	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	@Column(name="OrderNum")
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}

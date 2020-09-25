package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		InterfaceSourceColCfg
 * @author  		Pan Wei 
 * @Date 			2016年11月24日 下午11:45:26 	
 * @Description		[]  
 */

@Entity 
@Table(name="Adpt_AdapterColConfig") 
public class AdapterColConfig implements Serializable{

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
	 * 数据源ID
	 */
	private int dataSetID;
	
	public AdapterColConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public AdapterColConfig(int id) {
		this.id=id;
	}
	
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_AdapterColConfig_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
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

	@Column(name="dataSetID")
	public int getDataSetID() {
		return dataSetID;
	}

	public void setDataSetID(int dataSetID) {
		this.dataSetID = dataSetID;
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

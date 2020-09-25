package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		BasicDataSet
 * @author  		Wang Zhongyuan 
 * @Date 			2018年12月5日 18:25
 * @Description		[基础数据集表]  
 */

@Entity 
@Table(name="ADPT_DataSetColTemp") 
public class DataSetColTemp {
	/**
	 * 列id
	 */
	private int id;
	
	/**
	 * 列名称
	 */
	private String colname;;
	
	/**
	 * 列类型
	 * 1.基础列
	 * 2.增强列
	 */
	private int colType;
	
	
	private String funcClass;
	
	/**
	 * 功能参数(支持表达式)
	 */
	private String funcParams;
	
	/**
	 * 数据集id
	 */
	private int dataSetID;
	
	
	
	
	/**
	 * 获取基础数据集id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_DataSetColTemp_ID_SEQ", allocationSize=1, initialValue=1)
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
}

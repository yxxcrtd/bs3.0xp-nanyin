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
@Table(name="ADPT_BasicDataSet") 
public class BasicDataSet {
	/**
	 * 基础数据集id
	 */
	private int id;
	
	/**
	 * 基础数据集名称
	 */
	private String name;
	
	/**
	 * 数据库id
	 */
	private int dataSourceId;
	
	/**
	 * 基础数据集sql消息
	 */
	private String basicDataSql;
	
	
	
	/**
	 * 获取基础数据集id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_BasicDataSet_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 基础数据集名称
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 数据库id
	 */
	@Column(name="dataSourceId")
	public int getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(int dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	/**
	 * 基础数据集sql消息
	 */
	@Column(name="basicDataSql")
	public String getBasicDataSql() {
		return basicDataSql;
	}

	public void setBasicDataSql(String basicDataSql) {
		this.basicDataSql = basicDataSql;
	}
	
}

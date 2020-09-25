package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		Adapter
 * @author  		Wang Zhongyuan 
 * @Date 			2018年12月5日 18:25
 * @Description		[适配器表]  
 */

@Entity 
@Table(name="ADPT_Adapter") 
public class Adapter {
	/**
	 * 适配器id
	 */
	private int id;
	
	/**
	 * 适配器名称
	 */
	private String name;
	
	/**
	 * 基本数据集id
	 */
	private int basicDataSetId;
	
	/**
	 * 联合数据集id
	 */
	private int unionDataSetId;
	
	/**
	 * 适配器sql消息
	 */
	private String adapterSql;
	
	
	/**
	 * 获取适配器id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_Adapter_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 适配器名称
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 基础数据集id
	 */
	@Column(name="basicDataSetId")
	public int getBasicDataSetId() {
		return basicDataSetId;
	}

	public void setBasicDataSetId(int basicDataSetId) {
		this.basicDataSetId = basicDataSetId;
	}

	/**
	 * 联合数据集id
	 */
	@Column(name="unionDataSetId")
	public int getUnionDataSetId() {
		return unionDataSetId;
	}

	public void setUnionDataSetId(int unionDataSetId) {
		this.unionDataSetId = unionDataSetId;
	}

	/**
	 * 适配器sql消息
	 */
	@Column(name="adapterSql")
	public String getAdapterSql() {
		return adapterSql;
	}

	public void setAdapterSql(String adapterSql) {
		this.adapterSql = adapterSql;
	}

}

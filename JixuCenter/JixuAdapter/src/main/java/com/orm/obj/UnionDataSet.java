package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		UnionDataSet
 * @author  		Wang Zhongyuan 
 * @Date 			2018年12月5日 18:25
 * @Description		[联合数据集表]  
 */

@Entity 
@Table(name="ADPT_UnionDataSet") 
public class UnionDataSet {
	
	/**
	 * 联合数据集id
	 */
	private int id;
	
	/**
	 * 联合数据集名称
	 */
	private String name;
	
	/**
	 * 基础数据集id
	 */
	private int basicDataSetId;
	
	
	/**
	 * 获取联合数据集id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_UnionDataSet_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 联合数据集名称
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

}

package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		PortfolioClassIDMap
 * @author  		Pan Wei 
 * @Date 			2017年1月3日 下午11:10:40 	
 * @Description		[投资组合和它下面的子组合之间的map对应关系]  
 */

@Entity 
@Table(name="JXS_PortfolioClassIDMap") 
public class PortfolioClassIDMap extends BasicEntity {
	private int id;
	private String classID;
	private String portID;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="PortfolioClassIDMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="classID")
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	@Column(name="portID")
	public String getPortID() {
		return portID;
	}
	public void setPortID(String portID) {
		this.portID = portID;
	}
	
}

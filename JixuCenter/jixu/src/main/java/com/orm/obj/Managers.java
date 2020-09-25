package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		Managers
 * @author  		Pan Wei 
 * @Date 			2016年9月8日 下午10:53:16 	
 * @Description		[经理表,在做证券交易时,需要录入交易经理。在同一个投资组合,不同的经理交易会生成不同的头寸]  
 */

@Entity 
@Table(name="JXS_Managers") 
public class Managers extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -6717004148230741322L;
	private int id;
	private String shortCode;
	private String managerName;
	private int managerGroup;
	private int active;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="Managers_ID_SEQ", allocationSize=1, initialValue=13)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="shortCode")
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	@Column(name="managerName")
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	@Column(name="active")
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	@Column(name="managerGroupCode")
	public int getManagerGroup() {
		return managerGroup;
	}

	public void setManagerGroup(int managerGroup) {
		this.managerGroup = managerGroup;
	}
}

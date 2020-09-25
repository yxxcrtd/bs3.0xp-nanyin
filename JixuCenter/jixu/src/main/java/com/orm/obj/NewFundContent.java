package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		NewFundContent
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午1:19:50 	
 * @Description		[新基金上线功能,所有新基金/老基金的上线配置都在该表的newFundContent字段]  
 */

@Entity 
@Table(name="JXS_NewFundContent") 
public class NewFundContent extends BasicEntity {
	private int id;
	private int newFundID;
	private String newFundContent;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="NewFundContent_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="NewFundID")
	public int getNewFundID() {
		return newFundID;
	}
	
	public void setNewFundID(int newFundID) {
		this.newFundID = newFundID;
	}
	
	@Column(name="NewFundContent", length=10000)
	@Type(type="org.hibernate.type.MaterializedClobType")
	public String getNewFundContent() {
		return newFundContent;
	}
	
	public void setNewFundContent(String newFundContent) {
		this.newFundContent = newFundContent;
	}
}

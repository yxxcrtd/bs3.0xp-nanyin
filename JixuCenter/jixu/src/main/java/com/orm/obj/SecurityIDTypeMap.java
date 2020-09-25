package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SecurityIDTypeMap
 * @author  		Pan Wei 
 * @Date 			2017年2月8日 下午5:36:32 	
 * @Description		[尚未使用该表]  
 */

@Entity 
@Table(name="JXS_SecurityIDTypeMap")
public class SecurityIDTypeMap extends BasicEntity {
	private int id;
	private IssueInfo secID;
	private SecurityIDType secIDType;
	private String aliasName;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SecurityIDTypeMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="secID")
	public IssueInfo getSecID() {
		return secID;
	}
	public void setSecID(IssueInfo secID) {
		this.secID = secID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="secIDType")
	public SecurityIDType getSecIDType() {
		return secIDType;
	}
	public void setSecIDType(SecurityIDType secIDType) {
		this.secIDType = secIDType;
	}
	
	@Column(name="AliasName")
	public String getAliasName() {
		return aliasName;
	}
	
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}

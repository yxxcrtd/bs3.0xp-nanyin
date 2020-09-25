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
 * @ClassName 		InterfaceMsgColMap
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[执行消息里的字段，与基础列/增强列(interfaceID)的映射关系。最后基础列/增强列的结果值，来替换执行消息里的字段]  
 */

@Entity 
@Table(name="ADPT_InterfaceMsgColMap") 
public class InterfaceMsgColMap implements Serializable{

	private static final long serialVersionUID = 7188765150509793043L;
	private int id;
	private String msgCol;
	private InterfaceSource interfaceSource;
	private InterfaceColConfig colConfig;
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.tool.GenCustomIntIDByDate", parameters={
			@Parameter(name = "seq_name", value = "ADPT_InterfaceMsgColMap_ID_SEQ")})
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="msgCol")
	public String getMsgCol() {
		return msgCol;
	}
	public void setMsgCol(String msgCol) {
		this.msgCol = msgCol;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interfaceID")
	public InterfaceSource getInterfaceSource() {
		return interfaceSource;
	}
	public void setInterfaceSource(InterfaceSource interfaceSource) {
		this.interfaceSource = interfaceSource;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="colConfigID")
	public InterfaceColConfig getColConfig() {
		return colConfig;
	}
	public void setColConfig(InterfaceColConfig colConfig) {
		this.colConfig = colConfig;
	}
}

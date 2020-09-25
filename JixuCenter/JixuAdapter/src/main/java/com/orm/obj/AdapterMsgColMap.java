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
 * @ClassName 		InterfaceMsgColMap
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[执行消息里的字段，与基础列/增强列(interfaceID)的映射关系。最后基础列/增强列的结果值，来替换执行消息里的字段]  
 */

@Entity 
@Table(name="ADPT_AdapterMsgColMap") 
public class AdapterMsgColMap implements Serializable{

	private static final long serialVersionUID = 718876515050979304L;
	private int id;
	private String msgCol;
	private int adapterID;
	private int adacolConfigID;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_AdapterMsgColMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
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
	
	@Column(name="adapterID")
	public int getAdapterID() {
		return adapterID;
	}
	public void setAdapterID(int adapterID) {
		this.adapterID = adapterID;
	}
	
	@Column(name="adacolConfigID")
	public int getAdacolConfigID() {
		return adacolConfigID;
	}
	public void setAdacolConfigID(int adacolConfigID) {
		this.adacolConfigID = adacolConfigID;
	}
}

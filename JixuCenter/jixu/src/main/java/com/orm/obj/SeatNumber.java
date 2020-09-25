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
 * @ClassName 		RoleType
 * @author  		Pan Wei 
 * @Date 			2016年12月31日 下午11:45:11 	
 * @Description		[该表是席位表,用于计算席位费功能]  
 */

@Entity 
@Table(name="JXS_SEATNUMBER") 
public class SeatNumber extends BasicEntity {
	private int id;
	private String brokerID;
	private String seatNumberAcct;
	private String seatNumberName;
	private String seatNumberDesc;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SeatNumber_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="brokerID")
	public String getBrokerID() {
		return brokerID;
	}
	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}
	
	@Column(name="seatNumberAcct")
	public String getSeatNumberAcct() {
		return seatNumberAcct;
	}
	public void setSeatNumberAcct(String seatNumberAcct) {
		this.seatNumberAcct = seatNumberAcct;
	}
	
	@Column(name="seatNumberName")
	public String getSeatNumberName() {
		return seatNumberName;
	}
	public void setSeatNumberName(String seatNumberName) {
		this.seatNumberName = seatNumberName;
	}
	
	@Column(name="seatNumberDesc")
	public String getSeatNumberDesc() {
		return seatNumberDesc;
	}
	public void setSeatNumberDesc(String seatNumberDesc) {
		this.seatNumberDesc = seatNumberDesc;
	}
}

package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

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
 * @ClassName 		BkInteRatio
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[银行的利息是会变动的，这里记录了银行利率]  
 */

@Entity 
@Table(name="JXB_BK_INTE_RATIO") 
public class BkInteRatio extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private String comments;
	private Date enddate;
	private double price;
	private int rateformula;
	private Date startdate;

	// many to one
	private BkInteAcct bkInteAcct;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkInteRatio_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="ENDDATE")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="RATEFORMULA")
	public int getRateformula() {
		return rateformula;
	}

	public void setRateformula(int rateformula) {
		this.rateformula = rateformula;
	}

	@Column(name="STARTDATE")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BK_INTE_ID")
	public BkInteAcct getBkInteAcct() {
		return bkInteAcct;
	}

	public void setBkInteAcct(BkInteAcct bkInteAcct) {
		this.bkInteAcct = bkInteAcct;
	}
}

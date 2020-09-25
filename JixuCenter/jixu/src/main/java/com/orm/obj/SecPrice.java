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
import com.jixu.common.pojo.InterfaceSource;

/** 
 * @ClassName 		SecPrice
 * @author  		Pan Wei 
 * @Date 			2016年9月3日 下午11:02:51 	
 * @Description		[证券价格]  
 */

@Entity 
@Table(name="JXM_SEC_PRICE") 
public class SecPrice extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private IssueInfo cusip;
	private Date price_date;
	private double price;
	private Date lst_update;
	private InterfaceSource sourceID;
	private int priceto;
	private double yield;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SecPrice_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSIP")
	public IssueInfo getCusip() {
		return cusip;
	}
	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}
	
	@Column(name="PRICE_DATE")
	public Date getPrice_date() {
		return price_date;
	}
	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}
	
	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="LST_UPDATE")
	public Date getLst_update() {
		return lst_update;
	}
	public void setLst_update(Date lst_update) {
		this.lst_update = lst_update;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sourceID")
	public InterfaceSource getSourceID() {
		return sourceID;
	}
	public void setSourceID(InterfaceSource sourceID) {
		this.sourceID = sourceID;
	}
	
	@Column(name="priceto")
	public int getPriceto() {
		return priceto;
	}
	public void setPriceto(int priceto) {
		this.priceto = priceto;
	}
	
	@Column(name="yield")
	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
}

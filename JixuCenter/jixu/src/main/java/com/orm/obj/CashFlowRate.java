package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		CashFlowRate
 * @author  		Pan Wei 
 * @Date 			2017年5月27日 下午10:56:52 	
 * @Description		[现金流配置的利率重置] 
 */

@Entity 
@Table(name="JXS_CashFlowRate") 
public class CashFlowRate extends BasicEntity {
	
	private int id;
	
	/**
	 * 现金流ID
	 */
	private String cfid="0";
	
	/**
	 * 日期
	 */
	private Date cfDate;
	
	/**
	 *对应该日期下利率
	 */
	private double cfRate;
	
	
	/**
	 * 获取 id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="CashFlowRate_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="cfid")
	public String getCfid() {
		return cfid;
	}

	public void setCfid(String cfid) {
		this.cfid = cfid;
	}


	@Column(name="cfDate")
	public void setCfDate(Date cfDate) {
		this.cfDate = cfDate;
	}

	public void setCfRate(double cfRate) {
		this.cfRate = cfRate;
	}

	
	@Column(name="cfRate")
	public double getCfRate() {
		return cfRate;
	}

	public Date getCfDate() {
		return cfDate;
	}

}

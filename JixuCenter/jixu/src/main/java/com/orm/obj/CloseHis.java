package com.orm.obj;

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
 * @ClassName 		CloseHis
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[投资组合关帐历史表]  
 */

@Entity 
@Table(name="JXJ_CLOSEHIS") 
public class CloseHis extends BasicEntity{
	private int closeid;
	
	//投资组合
	private PortfolioHdr portfolio;
	
	//已关帐日期
	private Date closeDate;
	
	//上一个关帐日期
	private Date preClose;
	
	//关帐日期
	private Date closeTime;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="CloseHis_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="closeid")
	public int getCloseid() {
		return closeid;
	}

	public void setCloseid(int closeid) {
		this.closeid = closeid;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="closeDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name="preClose")
	public Date getPreClose() {
		return preClose;
	}

	public void setPreClose(Date preClose) {
		this.preClose = preClose;
	}

	@Column(name="closeTime")
	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
}

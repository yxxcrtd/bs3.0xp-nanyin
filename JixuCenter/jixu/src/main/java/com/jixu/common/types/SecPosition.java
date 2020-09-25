package com.jixu.common.types;

import java.util.Date;
import java.util.List;

import com.orm.obj.TranClo;
import com.orm.obj.TranFeeFollowClo;

/** 
 * @ClassName 		SecPosition
 * @author  		Pan Wei 
 * @Date 			2018年6月7日 上午10:11:03 	
 * @Description		[证券头寸，clo头寸，浮动的费用list]  
 */

public class SecPosition {
	private String portnum;
	
	private String cusip;
	
	private int lotnum;
	
	private Date asOfDate;
	
	private TranClo tranClo;
	
	private List<TranFeeFollowClo> feeFollowList;

	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public TranClo getTranClo() {
		return tranClo;
	}

	public void setTranClo(TranClo tranClo) {
		this.tranClo = tranClo;
	}

	public List<TranFeeFollowClo> getFeeFollowList() {
		return feeFollowList;
	}

	public void setFeeFollowList(List<TranFeeFollowClo> feeFollowList) {
		this.feeFollowList = feeFollowList;
	}
}

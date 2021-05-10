package com.joyintech.tams.valuation.bokdetailidupdate.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class BokDetailIdUpdate003Dto extends BaseEntity{
		//业务明细代码
		private String busiId;
		
		//业务明细类型
		private String busiDetailType;
		
		//理财子产品起息日
		private Date vdate;
		
		//收益类型
		private String profitType;
		
		//投资目的
		private String invAim;
		
		//币种
		private String ccy;
		
		//调整前资金账户号
		private String acctNoOld;
		
		//调整前账务明细代码
		private String bokdetailIdOld;
			
		//账套代码
		private String booksetId;

		public String getBusiId() {
			return busiId;
		}

		public void setBusiId(String busiId) {
			this.busiId = busiId;
		}

		public String getBusiDetailType() {
			return busiDetailType;
		}

		public void setBusiDetailType(String busiDetailType) {
			this.busiDetailType = busiDetailType;
		}

		public Date getVdate() {
			return vdate;
		}

		public void setVdate(Date vdate) {
			this.vdate = vdate;
		}

		public String getProfitType() {
			return profitType;
		}

		public void setProfitType(String profitType) {
			this.profitType = profitType;
		}

		public String getInvAim() {
			return invAim;
		}

		public void setInvAim(String invAim) {
			this.invAim = invAim;
		}

		public String getCcy() {
			return ccy;
		}

		public void setCcy(String ccy) {
			this.ccy = ccy;
		}

		public String getAcctNoOld() {
			return acctNoOld;
		}

		public void setAcctNoOld(String acctNoOld) {
			this.acctNoOld = acctNoOld;
		}

		public String getBokdetailIdOld() {
			return bokdetailIdOld;
		}

		public void setBokdetailIdOld(String bokdetailIdOld) {
			this.bokdetailIdOld = bokdetailIdOld;
		}

		public String getBooksetId() {
			return booksetId;
		}

		public void setBooksetId(String booksetId) {
			this.booksetId = booksetId;
		}
		
		
}

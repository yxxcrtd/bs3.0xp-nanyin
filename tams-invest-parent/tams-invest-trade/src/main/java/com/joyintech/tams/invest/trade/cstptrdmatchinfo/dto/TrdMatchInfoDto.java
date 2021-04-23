package com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.common.base.entity.TrdPledgeMatchInfo;

public class TrdMatchInfoDto extends TrdMatchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3971359987060238953L;
	
	private List<TrdPledgeMatchInfo> trdPledgeMatchInfoList;

	public List<TrdPledgeMatchInfo> getTrdPledgeMatchInfoList() {
		return trdPledgeMatchInfoList;
	}

	public void setTrdPledgeMatchInfoList(List<TrdPledgeMatchInfo> trdPledgeMatchInfoList) {
		this.trdPledgeMatchInfoList = trdPledgeMatchInfoList;
	}

}

/**  
 * @Project     tams-app  
 * @File        ChbBankMonthReportModelDto.java  
 * @Package     com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.dao  
 * @Version     V1.0  
 * @Date        2019年8月3日 下午2:57:33  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */

package com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * @Description 银行类公开市场业务一级交易商月报表实体类
 * @ClassName ChbBankMonthReportModelDto
 * @Date 2019年8月3日 下午2:57:33
 * @Author 高健 Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 */

@PageId("PalOpenMarketDealersMonthly001")
public class ChbBankMonthReportModelDto extends FinProduct{

	/**  
	 * @Description 序列化ID  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:11:28  
	 * @Fields serialVersionUID   
	 */  
	    
	private static final long serialVersionUID = 6834727576171992864L;

	/**
	 * @Description 机构名称
	 * @Author 高健
	 * @Date 2019年8月3日 下午3:03:03
	 * @Fields orgname
	 */
	private String orgname;

	/**
	 * @Description 统计日期
	 * @Author 高健
	 * @Date 2019年8月3日 下午3:03:43
	 * @Fields date
	 */
	private String date;

	/**
	 * @Description 资产维度
	 * @Author 高健
	 * @Date 2019年8月3日 下午3:04:08
	 * @Fields assets
	 */
	private List<ChbMonthReportViewModelDto> assets;

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @return orgname  
	 */
	
	public String getOrgname() {
		return orgname;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @param orgname orgname  
	 */
	
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @return date  
	 */
	
	public String getDate() {
		return date;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @param date date  
	 */
	
	public void setDate(String date) {
		this.date = date;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @return assets  
	 */
	
	public List<ChbMonthReportViewModelDto> getAssets() {
		return assets;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:04:40  
	 * @param assets assets  
	 */
	
	public void setAssets(List<ChbMonthReportViewModelDto> assets) {
		this.assets = assets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assets == null) ? 0 : assets.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((orgname == null) ? 0 : orgname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChbBankMonthReportModelDto other = (ChbBankMonthReportModelDto) obj;
		if (assets == null) {
			if (other.assets != null)
				return false;
		} else if (!assets.equals(other.assets))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orgname == null) {
			if (other.orgname != null)
				return false;
		} else if (!orgname.equals(other.orgname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChbBankMonthReportModelDto [orgname=" + orgname + ", date=" + date + ", assets=" + assets + "]";
	}
	
	
}

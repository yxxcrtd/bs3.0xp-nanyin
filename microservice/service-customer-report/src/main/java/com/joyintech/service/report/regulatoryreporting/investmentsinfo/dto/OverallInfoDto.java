/**  
 * @Project     service-customer-report  
 * @File        OverallInfoDto.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto  
 * @Version     V1.0  
 * @Date        2019年8月7日 下午3:26:03  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**  
 * @Description  表内外投资业务总体情况表实体类
 * @ClassName   OverallInfoDto  
 * @Date        2019年8月7日 下午3:26:03  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Component
public class OverallInfoDto {
	
	/**  
	 * @Description 报表类型  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:27:21  
	 * @Fields reportType   
	 */  
	private String reportType;
	
	/**  
	 * @Description 属性代码  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:27:30  
	 * @Fields propertyCode   
	 */  
	private String propertyCode;
	
	/**  
	 * @Description 期末余额  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:03  
	 * @Fields balance   
	 */  
	private BigDecimal balance;
	/**
	 * 备注
	 */
    private String remark;
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @return reportType  
	 */
	
	public String getReportType() {
		return reportType;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @param reportType reportType  
	 */
	
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @return propertyCode  
	 */
	
	public String getPropertyCode() {
		return propertyCode;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @param propertyCode propertyCode  
	 */
	
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @return balance  
	 */
	
	public BigDecimal getBalance() {
		return balance;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:28:19  
	 * @param balance balance  
	 */
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

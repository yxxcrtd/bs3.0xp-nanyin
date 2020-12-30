/**  
 * @Project     service-customer-report  
 * @File        RemainingMaturityDto.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto  
 * @Version     V1.0  
 * @Date        2019年8月6日 下午3:00:09  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**  
 * @Description 剩余期限实体类  
 * @ClassName   RemainingMaturityDto  
 * @Date        2019年8月6日 下午3:00:09  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Component
public class RemainingMaturityDto {
	/**  
	 * @Description 剩余期限  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:46:15  
	 * @Fields eDays   
	 */  
	private String eDays;
	
	/**  
	 * @Description 金额  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:47:39  
	 * @Fields prinamt   
	 */  
	private BigDecimal prinamt;
	
	/**  
	 * @Description 属性代码（资产或者产品）  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:48:04  
	 * @Fields assetId   
	 */  
	private String attributeId;
	
	/**  
	 * @Description 属性类别  
	 * @Author      高健  
	 * @Date        2019年8月6日 上午10:52:13  
	 * @Fields assetType2   
	 */  
	private String attributeType;

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @return eDays  
	 */
	
	public String geteDays() {
		return eDays;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @param eDays eDays  
	 */
	
	public void seteDays(String eDays) {
		this.eDays = eDays;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @return prinamt  
	 */
	
	public BigDecimal getPrinamt() {
		return prinamt;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @param prinamt prinamt  
	 */
	
	public void setPrinamt(BigDecimal prinamt) {
		this.prinamt = prinamt;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @return attributeId  
	 */
	
	public String getAttributeId() {
		return attributeId;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @param attributeId attributeId  
	 */
	
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @return attributeType  
	 */
	
	public String getAttributeType() {
		return attributeType;
	}

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月6日 下午3:05:11  
	 * @param attributeType attributeType  
	 */
	
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result + ((attributeType == null) ? 0 : attributeType.hashCode());
		result = prime * result + ((eDays == null) ? 0 : eDays.hashCode());
		result = prime * result + ((prinamt == null) ? 0 : prinamt.hashCode());
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
		RemainingMaturityDto other = (RemainingMaturityDto) obj;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (attributeType == null) {
			if (other.attributeType != null)
				return false;
		} else if (!attributeType.equals(other.attributeType))
			return false;
		if (eDays == null) {
			if (other.eDays != null)
				return false;
		} else if (!eDays.equals(other.eDays))
			return false;
		if (prinamt == null) {
			if (other.prinamt != null)
				return false;
		} else if (!prinamt.equals(other.prinamt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RemainingMaturityDto [eDays=" + eDays + ", prinamt=" + prinamt + ", attributeId=" + attributeId
				+ ", attributeType=" + attributeType + "]";
	}
	
	
}

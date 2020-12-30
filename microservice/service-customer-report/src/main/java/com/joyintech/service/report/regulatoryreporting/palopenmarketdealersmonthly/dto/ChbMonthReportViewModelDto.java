/**  
 * @Project     tams-app  
 * @File        ChbMonthReportViewModelDto.java  
 * @Package     com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.dao  
 * @Version     V1.0  
 * @Date        2019年8月3日 下午3:02:38  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**  
 * @Description 资产维度实体类  
 * @ClassName   ChbMonthReportViewModelDto  
 * @Date        2019年8月3日 下午3:02:38  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Component
public class ChbMonthReportViewModelDto implements Serializable{
	 /**  
	 * @Description 序列化ID  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:08:34  
	 * @Fields serialVersionUID   
	 */  
	private static final long serialVersionUID = 7150371211716640342L;
	
	/**  
	 * @Description 统计维度  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:07:30  
	 * @Fields vcode   
	 */  
	
	private String vcode;
	/**  
	 * @Description 统计值  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:07:37  
	 * @Fields value   
	 */  
	private BigDecimal value;
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:08:57  
	 * @return vcode  
	 */
	
	public String getVcode() {
		return vcode;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:08:57  
	 * @param vcode vcode  
	 */
	
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:08:57  
	 * @return value  
	 */
	
	public BigDecimal getValue() {
		return value;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:08:57  
	 * @param value value  
	 */
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((vcode == null) ? 0 : vcode.hashCode());
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
		ChbMonthReportViewModelDto other = (ChbMonthReportViewModelDto) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (vcode == null) {
			if (other.vcode != null)
				return false;
		} else if (!vcode.equals(other.vcode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChbMonthReportViewModelDto [vcode=" + vcode + ", value=" + value + "]";
	}
	
}

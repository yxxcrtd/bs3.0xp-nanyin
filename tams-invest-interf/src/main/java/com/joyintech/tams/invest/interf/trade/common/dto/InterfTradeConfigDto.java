package com.joyintech.tams.invest.interf.trade.common.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 交易接口配置DTO
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfTradeConfigDto
 * @since 1.0
 */
public class InterfTradeConfigDto extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 接口编码
     */
    private String interfCode;

    /**
     * 接口名称
     */
    private String interfName;

    /**
     * 接口数据来源
     */
    private String interfSource;
    
    /**
     * 接口系统
     */
    private String interfSystem;
    
    /**
     *接口状态(1:启用,0:禁用,)
     */
    private Integer interfStatus;
    
    /**
     * 业务服务
     */
    private String businessService;
    
    /**
     * 业务方法
     */
    private String businessMethod;

	public String getInterfCode() {
		return interfCode;
	}

	public void setInterfCode(String interfCode) {
		this.interfCode = interfCode;
	}

	public String getInterfName() {
		return interfName;
	}

	public void setInterfName(String interfName) {
		this.interfName = interfName;
	}

	public String getInterfSource() {
		return interfSource;
	}

	public void setInterfSource(String interfSource) {
		this.interfSource = interfSource;
	}

	public String getInterfSystem() {
		return interfSystem;
	}

	public void setInterfSystem(String interfSystem) {
		this.interfSystem = interfSystem;
	}

	public Integer getInterfStatus() {
		return interfStatus;
	}

	public void setInterfStatus(Integer interfStatus) {
		this.interfStatus = interfStatus;
	}

	public String getBusinessService() {
		return businessService;
	}

	public void setBusinessService(String businessService) {
		this.businessService = businessService;
	}

	public String getBusinessMethod() {
		return businessMethod;
	}

	public void setBusinessMethod(String businessMethod) {
		this.businessMethod = businessMethod;
	}

	@Override
	public String toString() {
		return "InterfTradeConfigDto [interfCode=" + interfCode + ", interfName=" + interfName + ", interfSource="
				+ interfSource + ", interfSystem=" + interfSystem + ", interfStatus=" + interfStatus
				+ ", businessService=" + businessService + ", businessMethod=" + businessMethod + "]";
	}

}

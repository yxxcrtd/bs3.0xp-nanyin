package com.joyintech.tams.invest.interf.trade.common.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 接口服务配置DTO
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfServiceConfigDto
 * @since 1.0
 */
public class InterfServiceConfigDto extends BaseEntity{

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
     * 接口类型(RECEIVE:接收,SEND:发送)
     */
    private String interfType;
    
    /**
     * 接口系统
     */
    private String interfSystem;
    
    /**
     *接口状态(1:启用,0:禁用,)
     */
    private Integer interfStatus;
    
    /**
     * 接口备注
     */
    private String interfRemark;
    
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

	public String getInterfType() {
		return interfType;
	}

	public void setInterfType(String interfType) {
		this.interfType = interfType;
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

	public String getInterfRemark() {
		return interfRemark;
	}

	public void setInterfRemark(String interfRemark) {
		this.interfRemark = interfRemark;
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
		return "InterfServiceConfigDto [interfCode=" + interfCode + ", interfName=" + interfName + ", interfType="
				+ interfType + ", interfSystem=" + interfSystem + ", interfStatus=" + interfStatus + ", interfRemark="
				+ interfRemark + ", businessService=" + businessService + ", businessMethod=" + businessMethod + "]";
	}

}

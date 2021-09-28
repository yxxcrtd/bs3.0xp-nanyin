package com.joyintech.tams.invest.interf.trade.common.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 接口服务日志DTO
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfServiceLogDto
 * @since 1.0
 */
public class InterfServiceLogDto extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
    private String id;
    
    /**
     * 接口编码
     */
    private String interfCode;

    /**
     * 接口名称
     */
    private String interfName;

    /**
     *  接口类型(RECEIVE:接收,SEND:发送)
     */
    private String interfType;
    
    /**
     * 接口系统
     */
    private String interfSystem;
    
    /**
     *接口状态(1:新建,2:处理中,3:处理成功,4:处理失败)
     */
    private Integer interfStatus;

    /**
     * 接口消息体
     */
    private String interfMsgbody;
    
    /**
     * 接口备注
     */
    private String interfRemark;
    
    /**
     * 接口执行信息
     */
    private String executeInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getInterfMsgbody() {
		return interfMsgbody;
	}

	public void setInterfMsgbody(String interfMsgbody) {
		this.interfMsgbody = interfMsgbody;
	}

	public String getInterfRemark() {
		return interfRemark;
	}

	public void setInterfRemark(String interfRemark) {
		this.interfRemark = interfRemark;
	}

	public String getExecuteInfo() {
		return executeInfo;
	}

	public void setExecuteInfo(String executeInfo) {
		this.executeInfo = executeInfo;
	}

	@Override
	public String toString() {
		return "InterfServiceLogDto [id=" + id + ", interfCode=" + interfCode + ", interfName=" + interfName
				+ ", interfType=" + interfType + ", interfSystem=" + interfSystem + ", interfStatus=" + interfStatus
				+ ", interfMsgbody=" + interfMsgbody + ", interfRemark=" + interfRemark + ", executeInfo=" + executeInfo
				+ "]";
	}

}

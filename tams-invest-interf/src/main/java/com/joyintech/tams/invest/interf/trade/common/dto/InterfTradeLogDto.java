package com.joyintech.tams.invest.interf.trade.common.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.interf.trade.common.Result;

/**
 * 交易接口日志DTO
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see Result
 * @since 1.0
 */
public class InterfTradeLogDto extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
    private String id;

    /**
     * 接口名称
     */
    private String interfName;

    /**
     * 接口编码
     */
    private String interfCode;

    /**
     * 接口数据来源
     */
    private String interfSource;
    
    /**
     *接口状态(1:新建,2:处理中,3:处理成功,4:处理失败)
     */
    private Integer interfStatus;

    /**
     * 接口系统
     */
    private String interfSystem;

    /**
     * 接口消息体
     */
    private String interfMsg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterfName() {
		return interfName;
	}

	public void setInterfName(String interfName) {
		this.interfName = interfName;
	}

	public String getInterfCode() {
		return interfCode;
	}

	public void setInterfCode(String interfCode) {
		this.interfCode = interfCode;
	}

	public String getInterfSource() {
		return interfSource;
	}

	public void setInterfSource(String interfSource) {
		this.interfSource = interfSource;
	}

	public Integer getInterfStatus() {
		return interfStatus;
	}

	public void setInterfStatus(Integer interfStatus) {
		this.interfStatus = interfStatus;
	}

	public String getInterfSystem() {
		return interfSystem;
	}

	public void setInterfSystem(String interfSystem) {
		this.interfSystem = interfSystem;
	}

	public String getInterfMsg() {
		return interfMsg;
	}

	public void setInterfMsg(String interfMsg) {
		this.interfMsg = interfMsg;
	}

	@Override
	public String toString() {
		return "InterfTradeLogDto [id=" + id + ", interfName=" + interfName + ", interfCode=" + interfCode
				+ ", interfSource=" + interfSource + ", interfStatus=" + interfStatus + ", interfSystem=" + interfSystem
				+ ", interfMsg=" + interfMsg + "]";
	}

}

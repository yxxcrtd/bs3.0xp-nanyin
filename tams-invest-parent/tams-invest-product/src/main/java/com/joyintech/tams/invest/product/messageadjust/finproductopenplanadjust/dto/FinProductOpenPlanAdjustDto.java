/*
 * 文件名：FinProductOpenPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 信息调整管理_开放计划查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjustDto
 * @since 1.0
 */
public class FinProductOpenPlanAdjustDto extends FinOpenPlan implements ProcessEntity{
    
    /**
     * 产品代码
     */
    private String finprodMarketId;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 计划类型
     */
    private String scheduleType;
    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 scheduleType
     * @return scheduleType.
     */
    public String getScheduleType() {
        return scheduleType;
    }

    /**
     * 设置 scheduleType
     * @param scheduleType scheduleType
     */
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleTypeStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.申赎开放类型, this.scheduleType);
    }
    
    /**
     * 序号
     */
    private String num;


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}
}

/*
 * 文件名：RskEventFormulaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rskevent.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;


/**
 * 风控事项关联参数配置-公式配置-Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月10日
 * @see RskEventFormulaDto
 * @since 1.0
 */
@PageId("RskEventFormula")
public class RskEventFormulaDto extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7007815810390645988L;

    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 操作符
     */
    private String oprType;

    /**
     * 计算对象
     */
    private String relItem;

    /**
     * 获取 参数代码
     * 
     * @return 参数代码.
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * 
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    /**
     * 获取 操作符
     * 
     * @return 操作符.
     */
    public String getOprType() {
        return oprType;
    }

    /**
     * 设置 操作符
     * 
     * @param oprType 操作符
     */
    public void setOprType(String oprType) {
        this.oprType = oprType;
    }

    /**
     * 获取 计算对象
     * 
     * @return 计算对象.
     */
    public String getRelItem() {
        return relItem;
    }

    /**
     * 设置 计算对象
     * 
     * @param relItem 计算对象
     */
    public void setRelItem(String relItem) {
        this.relItem = relItem;
    }
}

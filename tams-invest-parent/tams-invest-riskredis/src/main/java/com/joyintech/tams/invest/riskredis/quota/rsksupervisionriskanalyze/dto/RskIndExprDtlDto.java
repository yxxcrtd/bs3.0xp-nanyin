/*
 * 文件名：RskIndExprDtlDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 监控值计算明细
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月28日
 * @see RskIndExprDtlDto
 * @since 1.0
 */
public class RskIndExprDtlDto extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5411322228127434745L;

    /**
     * 参数代码
     */
    private String para;

    /**
     * 参数名称
     */
    private String paraStr;

    /**
     * 操作符
     */
    private String oper;

    /**
     * 参数值
     */
    private String paraVal;

    /**
     * 参数列表
     */
    private List<RskIndExprDtlDto> paramList;

    /**
     * 获取 参数代码
     * 
     * @return 参数代码.
     */
    public String getPara() {
        return para;
    }

    /**
     * 设置 参数代码
     * 
     * @param para 参数代码
     */
    public void setPara(String para) {
        this.para = para;
    }

    /**
     * 获取 参数名称
     * 
     * @return 参数名称.
     */
    public String getParaStr() {
        return paraStr;
    }

    /**
     * 设置 参数名称
     * 
     * @param paraStr 参数名称
     */
    public void setParaStr(String paraStr) {
        this.paraStr = paraStr;
    }

    /**
     * 获取 操作符
     * 
     * @return 操作符.
     */
    public String getOper() {
        return oper;
    }

    /**
     * 设置 操作符
     * 
     * @param oper 操作符
     */
    public void setOper(String oper) {
        this.oper = oper;
    }

    /**
     * 获取 参数值
     * 
     * @return 参数值.
     */
    public String getParaVal() {
        return paraVal;
    }

    /**
     * 设置 参数值
     * 
     * @param paraVal 参数值
     */
    public void setParaVal(String paraVal) {
        this.paraVal = paraVal;
    }

    /**
     * 获取 参数列表
     * 
     * @return 参数列表.
     */
    public List<RskIndExprDtlDto> getParamList() {
        return paramList;
    }

    /**
     * 设置 参数列表
     * 
     * @param paramList 参数列表
     */
    public void setParamList(List<RskIndExprDtlDto> paramList) {
        this.paramList = paramList;
    }
}

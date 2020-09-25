/*
 * 文件名：Rsk007InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk008.entity;

import java.io.Serializable;
import java.util.List;

/**
 * RSK007接口-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk008InEntity
 * @since 1.0
 */
public class Rsk008InEntity implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 487559159048332291L;

    /**
     * 事件代码
     */
    private String eventId;

    /**
     * 产品代码
     */
    private String portfolioCode;

    /**
     * 委托开始日
     */
    private String startDate;

    /**
     * 委托结束日
     */
    private String endDate;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 投资经理
     */
    private String manager;

    /**
     * 经办人
     */
    private String createUser;

    /**
     * 复核人
     */
    private String approveUser;

    /**
     * 资产列表
     */
    private List<Rsk008AssetEntity> rskCalcList;

    /**
     * 取得 事件代码
     * 
     * @return 事件代码
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 设置 事件代码
     * 
     * @param eventId 事件代码
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    public String getPortfolioCode() {
        return this.portfolioCode;
    }

    /**
     * 设置 产品代码
     * 
     * @param portfolioCode 产品代码
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    /**
     * 取得 委托开始日
     * 
     * @return 委托开始日
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * 设置 委托开始日
     * 
     * @param startDate 委托开始日
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 委托结束日
     * 
     * @return 委托结束日
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * 设置 委托结束日
     * 
     * @param endDate 委托结束日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 交易方向
     * 
     * @return 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     * 
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 取得 投资经理
     * 
     * @return 投资经理
     */
    public String getManager() {
        return this.manager;
    }

    /**
     * 设置 投资经理
     * 
     * @param manager 投资经理
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * 取得 经办人
     * 
     * @return 经办人
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 经办人
     * 
     * @param createUser 经办人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 取得 复核人
     * 
     * @return 复核人
     */
    public String getApproveUser() {
        return this.approveUser;
    }

    /**
     * 设置 复核人
     * 
     * @param approveUser 复核人
     */
    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    /**
     * 获取 rskCalcList
     * 
     * @return rskCalcList.
     */
    public List<Rsk008AssetEntity> getRskCalcList() {
        return rskCalcList;
    }

    /**
     * 设置 rskCalcList
     * 
     * @param rskCalcList rskCalcList
     */
    public void setRskCalcList(List<Rsk008AssetEntity> rskCalcList) {
        this.rskCalcList = rskCalcList;
    }
}

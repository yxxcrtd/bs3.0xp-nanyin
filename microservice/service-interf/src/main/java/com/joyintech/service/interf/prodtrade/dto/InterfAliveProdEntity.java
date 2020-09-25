/*
 * 文件名：InterfAliveProdEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodtrade.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;

/**
 * 存续期模块接口输入类
 * 
 * @param <L> 参数
 * @author zhangrui1
 * @version 1.0 2018年10月31日
 * @see InterfAliveProdEntity
 * @since 1.0
 */
public class InterfAliveProdEntity<L extends InterfProdLadderDetail> {

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 销售开始日
     */
    private String startdate;

    /**
     * 销售截止日
     */
    private String enddate;

    /**
     * 平台日期
     */
    private String cDate;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层信息
     */
    private List<L> lvlList;

    /**
     * 获取 分层信息
     * 
     * @return lvlList 分层信息.
     */
    public List<L> getLvlList() {
        return lvlList;
    }

    /**
     * 获取是否分层
     * 
     * @return 是否分层
     * @see
     * @since 1.0
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置是否分层
     * 
     * @param isLay 是否分层
     * @see
     * @since 1.0
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 分层信息
     * 
     * @param lvlList 分层信息.
     */
    public void setLvlList(List<L> lvlList) {
        this.lvlList = lvlList;
    }

    /**
     * 获取 prodid
     * 
     * @return prodid.
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 prodid
     * 
     * @param prodid prodid
     */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    /**
     * 获取 startdate
     * 
     * @return startdate.
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 startdate
     * 
     * @param startdate startdate
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 enddate
     * 
     * @return enddate.
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 enddate
     * 
     * @param enddate enddate
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * 获取 cDate
     * 
     * @return cDate.
     */
    public String getcDate() {
        return cDate;
    }

    /**
     * 设置 cDate
     * 
     * @param cDate cDate
     */
    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    /**
     * 获取循环次数
     * 
     * @return 循环次数
     * @see
     * @since 1.0
     */
    public int getListCnt() {
        return lvlList == null ? 0 : lvlList.size();
    }
}

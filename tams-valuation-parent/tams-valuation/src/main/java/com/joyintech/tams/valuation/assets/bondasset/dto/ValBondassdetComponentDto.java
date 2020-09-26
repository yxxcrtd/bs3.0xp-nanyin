/*
 * 文件名：ValBondassdetComponentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qujianzhong
 * @version 1.0 2018年10月31日
 * @see ValBondassdetComponentDto
 * @since 1.0
 */
public class ValBondassdetComponentDto {

    /**
     * 行权信息列表
     */
    List<ValBondassetXQDto> xqList;
    
    /**
     * 报价信息列表
     */
    List<ValBondassetBJDto> bjList;
    
    /**
     * 付息计划信息列表
     */
    List<ValBondassetFXDto> fxList;
    
    /**
     * 还本计划信息列表
     */
    List<ValBondassetHBDto> hbList;

    
    /**
     * 获取 xqList
     * @return xqList.
     */
    public List<ValBondassetXQDto> getXqList() {
        if (xqList == null) {
            xqList = new ArrayList<ValBondassetXQDto>();
        }
        return xqList;
    }

    
    /**
     * 设置 xqList
     * @param xqList xqList
     */
    public void setXqList(List<ValBondassetXQDto> xqList) {
        this.xqList = xqList;
    }

    
    /**
     * 获取 bjList
     * @return bjList.
     */
    public List<ValBondassetBJDto> getBjList() {
        if (bjList == null) {
            bjList = new ArrayList<ValBondassetBJDto>();
        }
        return bjList;
    }

    
    /**
     * 设置 bjList
     * @param bjList bjList
     */
    public void setBjList(List<ValBondassetBJDto> bjList) {
        this.bjList = bjList;
    }

    
    /**
     * 获取 fxList
     * @return fxList.
     */
    public List<ValBondassetFXDto> getFxList() {
        if (fxList == null) {
            fxList = new ArrayList<ValBondassetFXDto>();
        }
        return fxList;
    }

    
    /**
     * 设置 fxList
     * @param fxList fxList
     */
    public void setFxList(List<ValBondassetFXDto> fxList) {
        this.fxList = fxList;
    }

    
    /**
     * 获取 hbList
     * @return hbList.
     */
    public List<ValBondassetHBDto> getHbList() {
        if (hbList == null) {
            hbList = new ArrayList<ValBondassetHBDto>();
        }
        return hbList;
    }

    
    /**
     * 设置 hbList
     * @param hbList hbList
     */
    public void setHbList(List<ValBondassetHBDto> hbList) {
        this.hbList = hbList;
    }
}

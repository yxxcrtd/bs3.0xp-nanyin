/*
 * 文件名：InterfPayhsrcsecinfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.common.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinStdsecConverseRadio;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfo;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;

/**
 *  wind债券资产DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see InterfPayhsrcsecinfoDto
 * @since 1.0
 */
public class InterfPayhsrcsecinfoDto extends InterfPayhsrcsecinfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    private String finprodId;
    
    /**
     * 金融产品信息List
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    /**
     * 债券估值行情list
     */
    private List<MstBondValuationInfo> mstBondValuationInfoList;
    
    /**
     * 债券市场行情list
     */
    private List<MstBondMarketInfo> mstBondMarketInfoList;
    
    /**
     * 回购标准券折算率list
     */
    private List<FinStdsecConverseRadio> finStdsecConverseRadioList;
    
    private List<String> secidList;

    
    /**
     * 获取 secidList
     * @return secidList.
     */
    public List<String> getSecidList() {
        return secidList;
    }

    
    /**
     * 设置 objIdList
     * @param objIdList objIdList
     */
    public void setSecidList(List<String> secidList) {
        this.secidList = secidList;
    }


    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }


    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }


    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }


	public List<MstBondValuationInfo> getMstBondValuationInfoList() {
		return mstBondValuationInfoList;
	}


	public void setMstBondValuationInfoList(List<MstBondValuationInfo> mstBondValuationInfoList) {
		this.mstBondValuationInfoList = mstBondValuationInfoList;
	}


	public List<MstBondMarketInfo> getMstBondMarketInfoList() {
		return mstBondMarketInfoList;
	}


	public void setMstBondMarketInfoList(List<MstBondMarketInfo> mstBondMarketInfoList) {
		this.mstBondMarketInfoList = mstBondMarketInfoList;
	}


	public List<FinStdsecConverseRadio> getFinStdsecConverseRadioList() {
		return finStdsecConverseRadioList;
	}


	public void setFinStdsecConverseRadioList(List<FinStdsecConverseRadio> finStdsecConverseRadioList) {
		this.finStdsecConverseRadioList = finStdsecConverseRadioList;
	}
    
    

}

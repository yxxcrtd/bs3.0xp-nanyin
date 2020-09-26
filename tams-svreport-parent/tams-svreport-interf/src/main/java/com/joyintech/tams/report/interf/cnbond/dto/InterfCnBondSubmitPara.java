/*
 * 文件名：InterfCnBondSubmitPara.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.cnbond.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中债报送接口参数
 * 
 * @author lihuadong
 * @version 1.0 2017年5月19日
 * @see InterfCnBondSubmitPara
 * @since 1.0
 */
public class InterfCnBondSubmitPara {

    /**
     * 报送类型
     */
    private String dealType;

    /**
     * 报送信息
     */
    private List<Map<String, CnBondInterfItem>> infos;

    /**
     * 报送日期
     */
    private Date sysDate;

    /**
     * 报送流水号
     */
    private List<String> registSeq = new ArrayList<String>();

    /**
     * 报送流水号+识别标识
     */
    private Map<String,String> registSeqKeyMap=new HashMap<String,String>();
    
    public Map<String, String> getRegistSeqKeyMap() {
		return registSeqKeyMap;
	}

	public void setRegistSeqKeyMap(Map<String, String> registSeqKeyMap) {
		this.registSeqKeyMap = registSeqKeyMap;
	}

	/**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 业务确认日
     */
    private Date equityConfDate;
    
    /**
     * 获取 dealType
     * 
     * @return dealType.
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * 设置 dealType
     * 
     * @param dealType dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    /**
     * 获取 infos
     * 
     * @return infos.
     */
    public List<Map<String, CnBondInterfItem>> getInfos() {
        return infos;
    }

    /**
     * 设置 infos
     * 
     * @param infos infos
     */
    public void setInfos(List<Map<String, CnBondInterfItem>> infos) {
        this.infos = infos;
    }

    /**
     * 获取 sysDate
     * 
     * @return sysDate.
     */
    public Date getSysDate() {
        return sysDate;
    }

    /**
     * 设置 sysDate
     * 
     * @param sysDate sysDate
     */
    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 获取 registSeq
     * 
     * @return registSeq.
     */
    public List<String> getRegistSeq() {
        return registSeq;
    }

    /**
     * 设置 registSeq
     * 
     * @param registSeq registSeq
     */
    public void setRegistSeq(List<String> registSeq) {
        this.registSeq = registSeq;
    }

    /**
     * 获取 registBankCode
     * 
     * @return registBankCode.
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 registBankCode
     * 
     * @param registBankCode registBankCode
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }

	public Date getEquityConfDate() {
		return equityConfDate;
	}

	public void setEquityConfDate(Date equityConfDate) {
		this.equityConfDate = equityConfDate;
	}

}

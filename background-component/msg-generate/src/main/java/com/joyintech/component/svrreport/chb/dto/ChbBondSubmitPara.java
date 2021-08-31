/*
 * 文件名：ChbBondSubmitPara.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月23日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中债报送参数
 * @description 
 * @author 王波
 * @version 1.0 2020年9月23日
 * @see ChbBondSubmitPara
 * @since 1.0
 */
public class ChbBondSubmitPara {

    /**
     * 附件列表
     */
    private List<String> attachPathList;

    /**
     * 报送类型
     */
    private String dealType;

    /**
     * 中债报送编码
     */
    private String dealCode;

    /**
     * 报送信息
     */
    private List<Map<String, CnBondInterfItemDto>> infos;

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

    public List<String> getAttachPathList() {
        return attachPathList;
    }

    public void setAttachPathList(List<String> attachPathList) {
        this.attachPathList = attachPathList;
    }

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

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
        this.dealCode = dealCode;
    }

    /**
     * 获取 infos
     * 
     * @return infos.
     */
    public List<Map<String, CnBondInterfItemDto>> getInfos() {
        return infos;
    }

    /**
     * 设置 infos
     * 
     * @param infos infos
     */
    public void setInfos(List<Map<String, CnBondInterfItemDto>> infos) {
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

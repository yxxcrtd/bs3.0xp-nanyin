/*
 * 文件名：ValProdSchemeConfig001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 产品对账方案配置Dto
 * 
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig002Dto
 * @since 1.0
 */
public class ValProdSchemeConfig002Dto extends BaseEntity {

    private static final long serialVersionUID = 6649853635321290239L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 产品类型
     */
    private String acctMode;

    /**
     * 原方案Id
     */
    private String orgSchId;

    /**
     * 原方案名称
     */
    private String orgSchName;

    /**
     * 当前方案代码
     */
    private String nowSchId;

    /**
     * 当前方案名称
     */
    private String nowSchName;
    
    /**
     * 账套方案代码
     */
    private String booksetSchemeId;

    /**
     * 获取 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 账套简称
     * 
     * @return 账套简称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套简称
     * 
     * @param booksetName 账套简称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 收益类型
     * 
     * @return 收益类型
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 收益类型
     * 
     * @param acctMode 收益类型
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    /**
     * 获取 原方案Id
     * 
     * @return 原方案Id
     */
    public String getOrgSchId() {
        return orgSchId;
    }

    /**
     * 设置 原方案Id
     * 
     * @param orgSchId 原方案Id
     */
    public void setOrgSchId(String orgSchId) {
        this.orgSchId = orgSchId;
    }

    /**
     * 获取 原方案名称
     * 
     * @return 原方案名称
     */
    public String getOrgSchName() {
        return orgSchName;
    }

    /**
     * 设置 原方案名称
     * 
     * @param orgSchName 原方案名称
     */
    public void setOrgSchName(String orgSchName) {
        this.orgSchName = orgSchName;
    }

    /**
     * 获取 当前方案Id
     * 
     * @return 当前方案Id
     */
    public String getNowSchId() {
        return nowSchId;
    }

    /**
     * 设置 当前方案Id
     * 
     * @param nowSchId 当前方案Id
     */
    public void setNowSchId(String nowSchId) {
        this.nowSchId = nowSchId;
    }

    /**
     * 获取 当前方案名称
     * 
     * @return 当前方案名称
     */
    public String getNowSchName() {
        return nowSchName;
    }

    /**
     * 设置 当前方案名称
     * 
     * @param nowSchName 当前方案名称
     */
    public void setNowSchName(String nowSchName) {
        this.nowSchName = nowSchName;
    }
    
    public String getAcctModeFmt(){
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.acctMode))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.acctMode):this.acctMode;
//        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.acctMode);
    }

    
    /**
     * 获取 账套方案代码
     * @return 账套方案代码
     */
    public String getBooksetSchemeId() {
        return booksetSchemeId;
    }

    
    /**
     * 设置 账套方案代码
     * @param booksetSchemeId 账套方案代码
     */
    public void setBooksetSchemeId(String booksetSchemeId) {
        this.booksetSchemeId = booksetSchemeId;
    }
    
    

}

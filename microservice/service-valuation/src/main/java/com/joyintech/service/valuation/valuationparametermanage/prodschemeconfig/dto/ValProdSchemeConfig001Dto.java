/*
 * 文件名：ValProdSchemeConfig001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 产品对账方案Dto
 * 
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig001Dto
 * @since 1.0
 */
public class ValProdSchemeConfig001Dto extends BaseEntity {

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
     * 收益类型
     */
    private String acctMode;

    /**
     * 方案类型
     */
    private String schemeType;

    /**
     * 方案名称
     */
    private String schName;

    /**
     * 账套模板Id
     */
    private String booksetIdTmpl;

    /**
     * 方案代码
     */
    private String schId;

    /**
     * 账套模板名称
     */
    private String booksetNameTmpl;
    
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
     * 获取 产品类型
     * 
     * @return 产品类型
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 产品类型
     * 
     * @param acctMode 产品类型
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    /**
     * 获取 方案类型
     * 
     * @return 方案类型
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 方案类型
     * 
     * @param schemeType 方案类型
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    /**
     * 获取 方案名称
     * 
     * @return 方案名称
     */
    public String getSchName() {
        return schName;
    }

    /**
     * 设置 方案名称
     * 
     * @param schName 方案名称
     */
    public void setSchName(String schName) {
        this.schName = schName;
    }

    /**
     * 获取 方案代码
     * 
     * @return 方案代码
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schId 方案代码
     */
    public void setSchId(String schId) {
        this.schId = schId;
    }

    /**
     * 获取 账套模板
     * 
     * @return 账套模板
     */
    public String getBooksetIdTmpl() {
        return booksetIdTmpl;
    }

    /**
     * 设置 账套模板
     * 
     * @param booksetIdTmpl 账套模板
     */
    public void setBooksetIdTmpl(String booksetIdTmpl) {
        this.booksetIdTmpl = booksetIdTmpl;
    }

    /**
     * 获取 账套模板名称
     * 
     * @return 账套模板名称
     */
    public String getBooksetNameTmpl() {
        return booksetNameTmpl;
    }

    /**
     * 设置 账套模板名称
     * 
     * @param booksetNameTmpl 账套模板名称
     */
    public void setBooksetNameTmpl(String booksetNameTmpl) {
        this.booksetNameTmpl = booksetNameTmpl;
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

/*
 * 文件名：SecuritiesAccountInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.dto;

import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;

/**
 * 证券账户
 * @author fanshuanghu
 * @version 1.0 2018年9月11日
 * @see SecuritiesAccountInfoDto
 * @since 1.0
 */
public class SecuritiesAccountInfoDto extends StlSecAcctInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6981663179653524334L;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 证券账号
     */
    private String acctName;
    
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }
    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    public String getRegistOrgStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, super.getRegistOrg());
    }
    
    public String getSecAcctSourceStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.账户来源, super.getSecAcctSource());
    }
    /**
     * 证券账号不能为空
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="SecuritiesAccountInfo.secAcctId",groups = {ValidateGroupEdit.class})
    public String getSecAcctId(){
        return super.getSecAcctId();
    }
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctName() {
        return acctName;
    }
    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    
    
}

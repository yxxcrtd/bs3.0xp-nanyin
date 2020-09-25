/*
 * 文件名：TrdStandardBond002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.entrust.etrstandardbond.validator.EtrStandardBond002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("EtrStandardBond002")
@ValidLogic(targetClass = EtrStandardBond002DtoValidator.class, groups = ValidateGroupEdit.class)
public class EtrStandardBond002Dto extends EtrStandardBond001Dto implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 934652225044201805L;
    
    
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getSecAcctId() {
        return super.getSecAcctId();
    }
    
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getFinProdId() {
        return super.getFinProdId();
    }
    
    /**
     * 质押券列表信息
     */
    List<EtrStandardBond002Dto> pledgeBondList = new ArrayList<>();

    /**
     * 可质押券列表信息
     */
    List<EtrStandardBond002Dto> avaPledgeBondList;
    
    private String tradeId;
    
    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
    
    public List<EtrStandardBond002Dto> getPledgeBondList() {
        return pledgeBondList;
    }

    
    public void setPledgeBondList(List<EtrStandardBond002Dto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }

    
    public List<EtrStandardBond002Dto> getAvaPledgeBondList() {
        return avaPledgeBondList;
    }

    
    public void setAvaPledgeBondList(List<EtrStandardBond002Dto> avaPledgeBondList) {
        this.avaPledgeBondList = avaPledgeBondList;
    }
    
    
    
}

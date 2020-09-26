/*
 * 文件名：ValOptInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.validator.ValOptInfo001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 场外期权标的Dto
 * 
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see ValOptInfo001Dto
 * @since 1.0
 */
@PageId("ValOptInfo002")
@ValidLogic(targetClass = ValOptInfo001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValOptInfo001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 选中数据
     */
    private transient List<ValOptInfo001Dto> listValOptInfo001Dto;
    
    /**
     * 交易编号
     */
    @PrimaryKey(value = Field.PK_1)
    private String finprodId;
    
    /**
     * 期权标的类型
     */
    private String finprodType2;
    
    /**
     * 资产类型
     */
    private String type6;
    
    /**
     * 起息日
     */
    private String vdate;
    
    /**
     * 到期日
     */
    private String mdate;
    
    /**
     * 交易方向
     */
    private String extPs;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 期限
     */
    private String term;
    
    /**
     * 合同编号
     */
    private String contractNo;
    
    /**
     * 合同名称
     */
    private String contractName;
    
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 日历名称
     */
    private String calendarName;
    
    /**
     * 数量
     */
    private String prin;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    /**
     * 登记托管机构
     */
    private String registOrg;
    
    /**
     * 期权费类型
     */
    private String optFeePayType;
    
    /**
     * 期权费率
     */
    private String optFeeRate;
    
    /**
     * 获取 交易编号
     * 
     * @return 交易编号
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 交易编号
     * 
     * @param finprodId 交易编号
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 期权标的类型
     * 
     * @return 期权标的类型
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 期权标的类型
     * 
     * @param finprodType2 期权标的类型
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
    
    public String getFinprodType2Fmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, finprodType2))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, finprodType2):finprodType2;
    }
    
    /**
     * 获取 资产类型
     * 
     * @return 资产类型
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 资产类型
     * 
     * @param type6 资产类型
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }
    
    public String getType6Fmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.结构类型, type6))?
    			SysDataUtil.getNameFromDataDict(DictCode.结构类型, type6):type6;
    }
    
    /**
     * 获取 起息日
     * 
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }
    
    /**
     * 获取 到期日
     * 
     * @return 到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
    
    /**
     * 获取 交易方向
     * 
     * @return 交易方向
     */
    public String getExtPs() {
        return extPs;
    }

    /**
     * 设置 交易方向
     * 
     * @param extPs 交易方向
     */
    public void setExtPs(String extPs) {
        this.extPs = extPs;
    }
    
    public String getExtPsFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易方向, extPs))?
    			SysDataUtil.getNameFromDataDict(DictCode.交易方向, extPs):extPs;
    }
    
    /**
     * 获取 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
    			SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }
    
    /**
     * 获取 期限
     * 
     * @return 期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 期限
     * 
     * @param term 期限
     */
    public void setTerm(String term) {
        this.term = term;
    }
    
    /**
     * 获取 合同编号
     * 
     * @return 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 合同编号
     * 
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    
    /**
     * 获取 合同名称
     * 
     * @return 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置 合同名称
     * 
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    
    /**
     * 获取 交易日历
     * 
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * 
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }
    
    /**
     * 获取 日历名称
     * 
     * @return 日历名称
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 日历名称
     * 
     * @param calendarName 日历名称
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    
    /**
     * 获取 数量
     * 
     * @return 数量
     */
    public String getPrin() {
        return prin;
    }

    /**
     * 设置 数量
     * 
     * @param prin 数量
     */
    public void setPrin(String prin) {
        this.prin = prin;
    }
    
    /**
     * 获取 交易场所
     * 
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * 
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }
    
    public String getTradeMarketFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket))?
    			SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket):tradeMarket;
    }
    
    /**
     * 获取 登记托管机构
     * 
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * 
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }
    
    /**
     * 获取 期权费类型
     * 
     * @return 期权费类型
     */
    public String getOptFeePayType() {
        return optFeePayType;
    }

    /**
     * 设置 期权费类型
     * 
     * @param optFeePayType 期权费类型
     */
    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }
    
    public String getOptFeePayTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.期权费支付类型, optFeePayType))?
    			SysDataUtil.getNameFromDataDict(DictCode.期权费支付类型, optFeePayType):optFeePayType;
    }
    
    /**
     * 获取 期权费率
     * 
     * @return 期权费率
     */
    public String getOptFeeRate() {
        return optFeeRate;
    }

    /**
     * 设置 期权费率
     * 
     * @param optFeeRate 期权费率
     */
    public void setOptFeeRate(String optFeeRate) {
        this.optFeeRate = optFeeRate;
    }
    
    /**
     * 获取 操作类型
     * 
     * @return 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型
     * 
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 选中记录
     * 
     * @return 选中记录
     */
    public List<ValOptInfo001Dto> getListValOptInfo001Dto() {
        return listValOptInfo001Dto;
    }

    /**
     * 设置 选中记录
     * 
     * @param listValOptInfo001Dto 选中记录
     */
    public void setListValOptInfo001Dto(List<ValOptInfo001Dto> listValOptInfo001Dto) {
        this.listValOptInfo001Dto = listValOptInfo001Dto;
    }

}

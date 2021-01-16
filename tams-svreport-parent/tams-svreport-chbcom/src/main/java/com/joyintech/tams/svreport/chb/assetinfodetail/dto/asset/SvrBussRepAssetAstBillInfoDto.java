/*
 * 文件名：BussRepAssetAstBillDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetAstBill;

import java.math.BigDecimal;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see SvrBussRepAssetAstBillInfoDto
 * @since 1.0
 */
public class SvrBussRepAssetAstBillInfoDto extends RepAssetAstBill{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String type6;
    
    private String type7;
    
    /**
     * 资产/负债类别
     */
    private String repAssetType;
    
    /**
     * 交易流通场所
     */
    private String repTradeMarket;

    /**
     * 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String repPrivatefundFlag;

    /**
     * 公共PPP
     */
    private String repPppflag;

    /**
     * 是否小微企业信贷资产流转和收益权转让
     */
    private String repSmallcredit;

    /**
     * 是否投向投向市场化债转股相关产品的部分
     */
    private String repBondtostockFlag;

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;

    
    /**
     * 获取 repAssetType
     * @return repAssetType.
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    
    /**
     * 设置 repAssetType
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    
    /**
     * 获取 repTradeMarket
     * @return repTradeMarket.
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    
    /**
     * 设置 repTradeMarket
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }


    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }


    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }


    
    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }


    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }
    
    //数量
    @Length(fieldName = "BussRepAssetAstBillInfo.repNum", max = 6, groups = {ValidateGroupEdit.class})
    public Integer getRepNum(){
        return super.getRepNum();
    }
    
    //最长剩余期限(天)
    @Length(fieldName = "BussRepAssetAstBillInfo.repMaxRemainTerm", max = 5, groups = {ValidateGroupEdit.class})
    public Integer getRepMaxRemainTerm(){
        return super.getRepMaxRemainTerm();
    }
    
    //最短剩余期限(天)
    @Length(fieldName = "BussRepAssetAstBillInfo.repMinRemainTerm", max = 5, groups = {ValidateGroupEdit.class})
    public Integer getRepMinRemainTerm(){
        return super.getRepMinRemainTerm();
    }
    
    //加权剩余期限(天)
    @Amount(fieldName = "BussRepAssetAstBillInfo.repRemainTerm", maxIntBit = 5,maxDecBit = 2, groups = {ValidateGroupEdit.class})
    public BigDecimal getRepRemainTerm(){
        return super.getRepRemainTerm();
    }
    
    //贴现利率
    @Amount(fieldName = "BussRepAssetAstBillInfo.repDiscRate", maxIntBit = 3,maxDecBit = 5  , groups = {ValidateGroupEdit.class})
    public BigDecimal getRepDiscRate(){
        return super.getRepDiscRate();
    }

    public String getRepPrivatefundFlag() {
        return repPrivatefundFlag;
    }

    public void setRepPrivatefundFlag(String repPrivatefundFlag) {
        this.repPrivatefundFlag = repPrivatefundFlag;
    }

    public String getRepPppflag() {
        return repPppflag;
    }

    public void setRepPppflag(String repPppflag) {
        this.repPppflag = repPppflag;
    }

    public String getRepSmallcredit() {
        return repSmallcredit;
    }

    public void setRepSmallcredit(String repSmallcredit) {
        this.repSmallcredit = repSmallcredit;
    }

    public String getRepBondtostockFlag() {
        return repBondtostockFlag;
    }

    public void setRepBondtostockFlag(String repBondtostockFlag) {
        this.repBondtostockFlag = repBondtostockFlag;
    }

    public String getRepPrivateprdFlag() {
        return repPrivateprdFlag;
    }

    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag;
    }
}

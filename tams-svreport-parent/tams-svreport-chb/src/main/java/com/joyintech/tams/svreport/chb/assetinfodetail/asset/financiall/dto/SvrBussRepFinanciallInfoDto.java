/*
 * 文件名：BussFinBondRepInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.financiall.dto;

import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

/**
 *  债券类中债报送DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussRepFinanciallInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = SvrBussFinBondRepInfoValidator.class , groups = {ValidateGroupEdit.class})
public class SvrBussRepFinanciallInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;
    
    /**
     * 二级资产类型
     */
    private String type6;
    
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
     * 人行机构规模
     */
    private String repInstitSize;

    /**
     * 受让形式
     */
    private String repAssignee;

    /**
     * 交易场所
     */
    private String tradeMarket;

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
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }


    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
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


    @Length(fieldName = "BussFinBondRepInfo.repRegistorgComment", max = 256, groups = {ValidateGroupEdit.class})
    public String getRepRegistorgComment(){
        return super.getRepRegistorgComment();
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

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getRepInstitSize() {
        return repInstitSize;
    }

    public void setRepInstitSize(String repInstitSize) {
        this.repInstitSize = repInstitSize;
    }

    public String getRepAssignee() {
        return repAssignee;
    }

    public void setRepAssignee(String repAssignee) {
        this.repAssignee = repAssignee;
    }
}

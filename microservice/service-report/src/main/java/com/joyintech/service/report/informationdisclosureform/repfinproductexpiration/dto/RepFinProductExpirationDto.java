/*
 * 文件名：RepFinProductEstablishDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月7日
 * @see RepFinProductExpirationDto
 * @since 1.0
 */
@Component
public class RepFinProductExpirationDto extends FinProduct{
    
    private String prodName;
    private String prodId;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private List<RepFinProductExpirationDto> selectedItem;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 母金融产品代码
     */
    private String pFinprodId;
    
    /**
     * 文件模板类型
     */
    private String fileType;
    
    /**
     * 披露文档代码
     */
    private String revealFileId;
    
    /**
     * 销售部门
     */
    private String department;
    
    /**
     * 市场代码----产品代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品全称----产品名称
     */
    private String finprodName;
    
    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;//DateUtil.format()
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 客户实际收益率（年）
     */
    private long yield;
    
    /**
     * 托管费率（年）
     */
    private long trustFee;
    
    /**
     * 销售手续费率（年）
     */
    private long saleFee;
    
    /**
     * 币值
     */
    private String ccy;
    
    /**
     * 投资标的比例
     */
    private String investTargetRatio;
    
    /**
     * 收益标识
     */
    private String profitFlag;
    
    /**
     * 产品风险等级
     */
    private String repRiskLevel;
    
    /**
     * 产品兑付日
     */
    private Date payDay;
    
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RepFinProductExpirationDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RepFinProductExpirationDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    
    /**
     * 获取 pFinprodId
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    
    /**
     * 设置 pFinprodId
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    
    /**
     * 获取 fileType
     * @return fileType.
     */
    public String getFileType() {
        return fileType;
    }

    
    /**
     * 设置 fileType
     * @param fileType fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
    /**
     * 获取 revealFileId
     * @return revealFileId.
     */
    public String getRevealFileId() {
        return revealFileId;
    }

    
    /**
     * 设置 revealFileId
     * @param revealFileId revealFileId
     */
    public void setRevealFileId(String revealFileId) {
        this.revealFileId = revealFileId;
    }

    
    /**
     * 获取 department
     * @return department.
     */
    public String getDepartment() {
        return department;
    }

    
    /**
     * 设置 department
     * @param department department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    
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

    
    /**
     * 获取 yield
     * @return yield.
     */
    public long getYield() {
        return yield;
    }



    
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(long yield) {
        this.yield = yield;
    }



    
    /**
     * 获取 trustFee
     * @return trustFee.
     */
    public long getTrustFee() {
        return trustFee;
    }



    
    /**
     * 设置 trustFee
     * @param trustFee trustFee
     */
    public void setTrustFee(long trustFee) {
        this.trustFee = trustFee;
    }



    
    /**
     * 获取 saleFee
     * @return saleFee.
     */
    public long getSaleFee() {
        return saleFee;
    }



    
    /**
     * 设置 saleFee
     * @param saleFee saleFee
     */
    public void setSaleFee(long saleFee) {
        this.saleFee = saleFee;
    }



    
    /**
     * 获取 payDay
     * @return payDay.
     */
    public Date getPayDay() {
        return payDay;
    }



    
    /**
     * 设置 payDay
     * @param payDay payDay
     */
    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }



    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    /**
     * 获取 investTargetRatio
     * @return investTargetRatio.
     */
    public String getInvestTargetRatio() {
        return investTargetRatio;
    }

    
    /**
     * 设置 investTargetRatio
     * @param investTargetRatio investTargetRatio
     */
    public void setInvestTargetRatio(String investTargetRatio) {
        this.investTargetRatio = investTargetRatio;
    }

    
    /**
     * 获取 profitFlag
     * @return profitFlag.
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    
    /**
     * 设置 profitFlag
     * @param profitFlag profitFlag
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag;
    }

    
    /**
     * 获取 repRiskLevel
     * @return repRiskLevel.
     */
    public String getRepRiskLevel() {
        return repRiskLevel;
    }

    
    /**
     * 设置 repRiskLevel
     * @param repRiskLevel repRiskLevel
     */
    public void setRepRiskLevel(String repRiskLevel) {
        this.repRiskLevel = repRiskLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(String arg0) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorkflowId(String arg0) {
        // TODO Auto-generated method stub
        
    }



    
    /**
     * 获取 prodName
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }



    
    /**
     * 设置 prodName
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }



    
    /**
     * 获取 prodId
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }



    
    /**
     * 设置 prodId
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    
}

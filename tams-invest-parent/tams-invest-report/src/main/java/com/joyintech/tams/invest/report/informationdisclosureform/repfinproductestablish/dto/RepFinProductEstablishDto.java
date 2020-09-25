/*
 * 文件名：RepFinProductEstablishDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.validator.RepFinProductEstablishValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月7日
 * @see RepFinProductEstablishDto
 * @since 1.0
 */
@PageId("RepFinProductEstablish001")
@ValidLogic(targetClass = RepFinProductEstablishValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class RepFinProductEstablishDto extends FinProduct implements ProcessEntity{
    /*temp.FINPROD_ID,    
    temp.BRANCH,              
    temp.P_FINPROD_ID,        
    temp.REVEAL_FILE_ID,      
    temp.FILE_TYPE,           
    temp.SALE_CHANNEL,        
    temp.FINPROD_MARKET_ID,   
    temp.FINPROD_NAME,        
    temp.VDATE,               
    temp.MDATE,              
    temp.PORTFOLIO_ID*/
//    private List<String> finprodIdList;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -418422803531512393L;
    /**
     * 
     */
    private List<RepFinProductEstablishDto> selectedItem;
    
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
    
    private String fileTypeStr;
    
    /**
     * 披露文档代码
     */
    @NotEmpty(fieldName = "RepFinProductEstablish001.revealFileId", groups = {ValidateGroupDelete.class})
    private String revealFileId;
    
    /**
     * 销售部门
     */
    private String department;
    
    private String departmentStr;
    
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
    
    private String vdateStr;

    /**
     * 到期日
     */
    private Date mdate;//DateUtil.format()
    
    private String mdateStr;
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 托管费率
     */
    private long trustFee;
    
    private String trustFeeStr;
    
    /**
     * 销售手续费率
     */
    private long saleFee;
    
    private String saleFeeStr;
    
    /**
     * 预期收益率
     */
    private long yield;
    
    private String yieldStr;
    
    /**
     * 币值
     */
    private String ccy;
    private String ccyStr;

    
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
    
    private String prodRiskLevel;
    
    private String prodName;
    private String prodId;
    
    /**
     * 获取 prodRiskLevel
     * @return prodRiskLevel.
     */
    public String getProdRiskLevel() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品风险等级,this.getRepRiskLevel());
    }



    
    /**
     * 设置 prodRiskLevel
     * @param prodRiskLevel prodRiskLevel
     */
    public void setProdRiskLevel(String prodRiskLevel) {
        this.prodRiskLevel = prodRiskLevel;
    }



    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RepFinProductEstablishDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RepFinProductEstablishDto> selectedItem) {
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
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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
     * 获取 fileTypeStr
     * @return fileTypeStr.
     */
    public String getFileTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.文件类型, this.fileType);
    }



    
    /**
     * 设置 fileTypeStr
     * @param fileTypeStr fileTypeStr
     */
    public void setFileTypeStr(String fileTypeStr) {
        this.fileTypeStr = fileTypeStr;
    }



    
    /**
     * 获取 departmentStr
     * @return departmentStr.
     */
    public String getDepartmentStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售部门, this.department);
    }



    
    /**
     * 设置 departmentStr
     * @param departmentStr departmentStr
     */
    public void setDepartmentStr(String departmentStr) {
        this.departmentStr = departmentStr;
    }



    
    /**
     * 获取 vdateStr
     * @return vdateStr.
     */
    public String getVdateStr() {
        return DateUtil.format(this.vdate);
    }



    
    /**
     * 设置 vdateStr
     * @param vdateStr vdateStr
     */
    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }



    
    /**
     * 获取 mdateStr
     * @return mdateStr.
     */
    public String getMdateStr() {
        return DateUtil.format(this.mdate);
    }



    
    /**
     * 设置 mdateStr
     * @param mdateStr mdateStr
     */
    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }



    
    /**
     * 获取 trustFeeStr
     * @return trustFeeStr.
     */
    public String getTrustFeeStr() {
        return Long.toString(this.trustFee);
    }



    
    /**
     * 设置 trustFeeStr
     * @param trustFeeStr trustFeeStr
     */
    public void setTrustFeeStr(String trustFeeStr) {
        this.trustFeeStr = trustFeeStr;
    }



    
    /**
     * 获取 saleFeeStr
     * @return saleFeeStr.
     */
    public String getSaleFeeStr() {
        return Long.toString(this.saleFee);
    }



    
    /**
     * 设置 saleFeeStr
     * @param saleFeeStr saleFeeStr
     */
    public void setSaleFeeStr(String saleFeeStr) {
        this.saleFeeStr = saleFeeStr;
    }



    
    /**
     * 获取 yieldStr
     * @return yieldStr.
     */
    public String getYieldStr() {
        return Long.toString(this.yield);
    }



    
    /**
     * 设置 yieldStr
     * @param yieldStr yieldStr
     */
    public void setYieldStr(String yieldStr) {
        this.yieldStr = yieldStr;
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
    
    public String getProfitType(){
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型,this.getProfitFlag());
    }




    
    /**
     * 获取 ccyStr
     * @return ccyStr.
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }




    
    /**
     * 设置 ccyStr
     * @param ccyStr ccyStr
     */
    public void setCcyStr(String ccyStr) {
        this.ccyStr = ccyStr;
    }
    
}

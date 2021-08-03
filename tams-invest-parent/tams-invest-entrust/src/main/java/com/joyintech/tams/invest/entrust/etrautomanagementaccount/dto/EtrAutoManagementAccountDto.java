package com.joyintech.tams.invest.entrust.etrautomanagementaccount.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustWw;
import com.joyintech.tams.common.base.entity.EtrEntrustWwDetail;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrautomanagementaccount.validator.EtrAutoManagementAccountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@PageId("EtrAutoManagementAccount002")
@ValidLogic(targetClass = EtrAutoManagementAccountValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class EtrAutoManagementAccountDto extends EtrEntrustWw implements ProcessEntity {
    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    
    /**
     * 指令到期日
     */
    private Date mdate;

    public String getCloseWay() {
        return CloseWay;
    }

    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }

    /**
     * 选中的数据
     */
    private transient List<EtrAutoManagementAccountDto> selectedItem;

    public List<EtrAutoManagementAccountDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtrAutoManagementAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    private List<EtrEntrustWwDetail> etrEntrustWwDetailList;

    public List<EtrEntrustWwDetail> getEtrEntrustWwDetailList() {
        return etrEntrustWwDetailList;
    }

    public void setEtrEntrustWwDetailList(List<EtrEntrustWwDetail> etrEntrustWwDetailList) {
        this.etrEntrustWwDetailList = etrEntrustWwDetailList;
    }

    private String entrustId;
    private String entrustStatus;
    private String entrustStatusStr;
    private String portfolioId;
    private String finprodMarketId;
    private String type6;
    private String type6Str;
    private String type7;
    private String type7Str;
    private Date entrustDate;
    private String entrustDateStr;
    private String ps;
    private String psStr;
    private BigDecimal entrustAmt;
    private BigDecimal entrustNum;
    private String finprodType2;
    private BigDecimal portfolioAmt;
    private String portfolioName;
    private String portfolioIdChange;
    private BigDecimal subjectNum;
    private String assetType;

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getPortfolioIdChange() {
        return portfolioIdChange;
    }

    public void setPortfolioIdChange(String portfolioIdChange) {
        this.portfolioIdChange = portfolioIdChange;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(BigDecimal subjectNum) {
        this.subjectNum = subjectNum;
    }

    /**
     * 投资人员Dto
     */
    @Valid
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;

    public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
        return bussEtrInvestPersonInfoDto;
    }

    public void setBussEtrInvestPersonInfoDto(BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
        this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
    }

    public BigDecimal getPortfolioAmt() {
        return portfolioAmt;
    }

    public void setPortfolioAmt(BigDecimal portfolioAmt) {
        this.portfolioAmt = portfolioAmt;
    }

    @Override
    public String getFinprodType2() {
        return finprodType2;
    }

    @Override
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    @Override
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getEntrustId() {
        return entrustId;
    }

    @Override
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Override
    public String getEntrustStatus() {
        return entrustStatus;
    }

    public String getEntrustStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, this.getEntrustStatus());
    }

    @Override
    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    @Override
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "EtrAutoManagementAccount002.portfolioId" ,groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return portfolioId;
    }

    @Override
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_3)
    @NotEmpty(fieldName = "EtrAutoManagementAccount002.finprodId" ,groups = {ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public String getType6Str() {
    	/*
        if(StringUtil.equals (DictCodeListExt1.金融产品类型_投管用_债券.getValue (),this.getFinprodType2 ())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等,this.getType6 ());
        }else if(StringUtil.equals (DictCodeListExt1.金融产品类型_投管用_基金.getValue (),this.getFinprodType2 ())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型,this.getType6 ());
        }else {
            return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类,this.getType6 ());
        }*/
    	return   SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类,this.getType6 ());
    }

    public String getType7() {
        return type7;
    }

    public void setType7(String type7) {
        this.type7 = type7;
    }

    public String getType7Str() {
       /*
        if(StringUtil.equals (DictCodeListExt1.金融产品类型_投管用_债券.getValue (),this.getFinprodType2 ())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND二级分类_含债券二级分类等,this.getType7 ());
        }else if(StringUtil.equals (DictCodeListExt1.金融产品类型_投管用_基金.getValue (),this.getFinprodType2 ())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产二级类型,this.getType7 ());
        }
        return null;*/
    	return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类,this.getType7 ());
    }

    @Override
    public Date getEntrustDate() {
        return entrustDate;
    }

    @Override
    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getEntrustDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        String sd = DateUtil.format (this.getEntrustDate ());
        return sd;
    }

    @Override
    @NotEmpty(fieldName = "EtrAutoManagementAccount002.ps" ,groups = {ValidateGroupEdit.class})
    public String getPs() {
        return ps;
    }

    @Override
    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getPs());
    }

    @Override
    @NotEmpty(fieldName = "EtrAutoManagementAccount002.entrustAmt" ,groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    @Override
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

	public BigDecimal getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
    
    
}

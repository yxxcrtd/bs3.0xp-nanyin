/*
 * 文件名：FinProductBmQuotationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.bmquotation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.validate.FinProductBmQuotationValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import net.bytebuddy.implementation.bind.annotation.Super;

/**
 * 产品业绩基准调整
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationDto
 * @since 1.0
 */
@PageId("FinProductBmQuotation002")
@ValidLogic(targetClass = FinProductBmQuotationValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class FinProductBmQuotationDto extends FinBaseRuleInfo implements ProcessEntity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2084623942522414943L;

	/**
     * 业绩基准组件
     */
    @Valid
    private BussBenchMarkInfoDto bussBenchMarkInfoDto;
    /**
     * 金融产品代码
     */
  private String finprodId; 
    
    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
     */
    private String finprodMarketId;
    /**
     * 金融产品类型
     */
    private String finprodType2;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 生效日-起始日
     */
    private Date sDate;
    /**
     * 生效日-结束日
     */
    private Date eDate;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 浮动利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatRate;
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<FinProductBmQuotationDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    
    private String lowLimitRate;
    /**
     * 操作类型，用于区分新建代办或是编辑代办
     */
    private String opType;
    /**
     * 母产品代码
     */
    private String pFinprodId;
    /**
     * 产品款型
     */
    private String prodStyle;
    // 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;
    /**
     * 产品处理模式
     */
    private String dealMode;
    private String floatRateStr;
    /**
     * 投资周期
     */
    private String investmentCycle;
    
    
    public String getLowLimitRate() {
		return lowLimitRate;
	}


	public void setLowLimitRate(String lowLimitRate) {
		this.lowLimitRate = lowLimitRate;
	}


	public String getSendStatus() {
		return sendStatus;
	}


	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}


	public String getSendRemark() {
		return sendRemark;
	}


	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}


	public BussBenchMarkInfoDto getBussBenchMarkInfoDto() {
        return bussBenchMarkInfoDto;
    }

    
    public void setBussBenchMarkInfoDto(BussBenchMarkInfoDto bussBenchMarkInfoDto) {
        this.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
    }

    /**
     * 取得 重置规则，期初、期末等
     * @return 重置规则，期初、期末等
     */
    public String getResetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.重置规则, super.getResetType());
    }
    
    /**
     * 生效日
     * {@inheritDoc}
     */
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "FinProductBmQuotation001.effDate", groups = {ValidateGroupEdit.class})
    public String getEffDateStr(){
        return DateUtil.format(super.getEffDate());
    }
    /**
     * 业绩基准代码
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "FinProductBmQuotation001.finprodId", max = 32, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_1)
    public String getFinprodId() {
    	  return super.getFinprodId();
    }
    
  /* public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    } */

    @Length(fieldName = "FinProductBmQuotation001.finprodMarketId", max = 20, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinProductBmQuotation001.finprodMarketId", groups = {ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    @Length(fieldName = "FinProductBmQuotation001.finprodAbbr", max = 50, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinProductBmQuotation001.finprodAbbr", groups = {ValidateGroupEdit.class})
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    /**
     * @return Date
     */
    public Date getsDate() {
        return sDate;
    }


    /**
     * @param sDate sDate
     */
    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }


    /**
     * @return Date
     */
    public Date geteDate() {
        return eDate;
    }


    /**
     * @param eDate eDate
     */
    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }
    
    public Date getVdate() {
        return vdate;
    }


    
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }


    
    public Date getMdate() {
        return mdate;
    }


    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }


    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public BigDecimal getFloatRate() {
        return floatRate;
    }
    
    public String getFinprodType2() {
        return finprodType2;
    }


    
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }


    public void setFloatRate(BigDecimal floatRate) {
        this.floatRate = floatRate;
    }


    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public List<FinProductBmQuotationDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<FinProductBmQuotationDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    @Length(fieldName = "FinProductBmQuotation001.remark", max = 200, groups = {
        ValidateGroupEdit.class})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRateTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.利率类型, super.getRateType());
    }


    
    public String getOpType() {
        return opType;
    }

    
    public void setOpType(String opType) {
        this.opType = opType;
    }


	public String getpFinprodId() {
		return pFinprodId;
	}


	public void setpFinprodId(String pFinprodId) {
		this.pFinprodId = pFinprodId;
	}


	public String getProdStyle() {
		return prodStyle;
	}


	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}
	public String getStatusStr() {
	       return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
		}
	 public String getSendStatusStr() {
	        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
	    }


	public String getFloatRateStr() {
		return floatRateStr;
	}


	public void setFloatRateStr(String floatRateStr) {
		this.floatRateStr = floatRateStr;
	}


	public String getDealMode() {
		return dealMode;
	}


	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}


	public String getInvestmentCycle() {
		return investmentCycle;
	}


	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
	 
}

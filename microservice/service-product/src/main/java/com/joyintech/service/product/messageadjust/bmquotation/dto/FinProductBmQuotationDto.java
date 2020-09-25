/*
 * 文件名：FinProductBmQuotationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.bmquotation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.constant.DictCode;

/**
 * 产品业绩基准调整
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationDto
 * @since 1.0
 */

public class FinProductBmQuotationDto extends FinBaseRuleInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 业绩基准组件
     */
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
     * 收益类型
     */
    private String profitType;
    
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
     * 浮动利率
     */
    private BigDecimal floatRate;
    
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
    /**
     * 母金融产品代码
     */
    private String pFinprodId;
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 发送状态
     */
    private String sendStatus;
    /**
     * 发送备注
     */
    private String sendRemark;
    /**
     * 投资周期
     */
    private String investmentCycle;
    /**
     * 处理模式
     */
    private String dealMode;
    
    private String lowLimitRate;
    
    
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


    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }



    
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }



    
    public String getProfitType() {
        return profitType;
    }



    
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }



    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    
    
    
    public Date getsDate() {
        return sDate;
    }


    
    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }


    
    public Date geteDate() {
        return eDate;
    }

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



    public BigDecimal getFloatRate() {
        return floatRate;
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
	 public String getSendStatusStr() {
	        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
	    }



	public String getInvestmentCycle() {
		return investmentCycle;
	}



	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}



	public String getDealMode() {
		return dealMode;
	}



	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	 
}

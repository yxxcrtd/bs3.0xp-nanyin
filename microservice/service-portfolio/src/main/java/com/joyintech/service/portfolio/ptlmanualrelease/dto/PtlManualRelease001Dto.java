/*
 * 文件名：PtlCashFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlmanualrelease.dto;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;

/**
 * 手动释放头寸 
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月10日
 * @see PtlManualRelease001Dto
 * @since 1.0
 */
public class PtlManualRelease001Dto extends TrdProductDeal {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 有效状态
     */
    private String releaseStatus;
    /**
     * 有效状态
     */
    private String releaseStatusText;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 回购类型
     */   
    private String repoType;
    
    
    /**
     * 期限
     */   
    private String termDays;
    
    
    /**
     * 债券代码
     */   
    private String secId;

    /**
     * 债券名称
     */   
    private String secName;
    

    /**
     *  券面总额（万元）
     */
    private BigDecimal allPrice;


    /**
     *  折算比例（%）
     */
    private String rate;
    
  
    /**
     *  证券托管户
     */
    private String acctId;
   
    /**
     *  证券托管户
     */
    private String acctName;
    
    /**
     *  首期结算金额
     */
    private BigDecimal prinAmt;
    
    
    /**
     *  到期结算金额
     */
    private BigDecimal mtradeAmt;
 
    
    private  BigDecimal pledgeNum;
    
    /**
     * 元素Id
     */
    private String elementId;
    
	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	
    public String getPortfolioId() {
        return portfolioId;
    }
    
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    public String getPortfolioName() {
        return portfolioName;
    }


    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }


	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getRepoType() {
		return repoType;
	}

	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}

	public String getTermDays() {
		return termDays;
	}

	public void setTermDays(String termDays) {
		this.termDays = termDays;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public BigDecimal getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReleaseStatusText() {
	   if (StringUtil.isNotEmpty(this.getReleaseStatus())) {
		   releaseStatusText = SysDataUtil.getNameFromDataDict(DictCode.释放状态, this.getReleaseStatus());
	     }
		return releaseStatusText;
	}

	public void setReleaseStatusText(String releaseStatusText) {
		this.releaseStatusText = releaseStatusText;
	}
	   /**
  * 选中列表
  */
 private List<PtlManualRelease001Dto> selectedItem;
 /**
  * 获取 selectedItem
  * @return selectedItem.
  */
 public List<PtlManualRelease001Dto> getSelectedItem() {
     return selectedItem;
 }

 
 /**
  * 设置 selectedItem
  * @param selectedItem selectedItem
  */
 public void setSelectedItem(List<PtlManualRelease001Dto> selectedItem) {
     this.selectedItem = selectedItem;
 }

public BigDecimal getPrinAmt() {
	return prinAmt;
}

public void setPrinAmt(BigDecimal prinAmt) {
	this.prinAmt = prinAmt;
}

public BigDecimal getMtradeAmt() {
	return mtradeAmt;
}

public void setMtradeAmt(BigDecimal mtradeAmt) {
	this.mtradeAmt = mtradeAmt;
}

public String getElementId() {
	return elementId;
}

public void setElementId(String elementId) {
	this.elementId = elementId;
}

public BigDecimal getPledgeNum() {
	return pledgeNum;
}

public void setPledgeNum(BigDecimal pledgeNum) {
	this.pledgeNum = pledgeNum;
}
   
}

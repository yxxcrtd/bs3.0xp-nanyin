/*
 * 文件名：FlowRiskFactroRManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年8月21日
 * @see FlowRiskStressTestView002Dto
 * @since 1.0
 */
public class FlowRiskStressTestView002Dto extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String flowTestId;//压力测试编号
	private String testName;//压力测试名称
	private String portfolioName;//组合名称
	private String portfolioId;//组合名称
	private String finprodId;//产品编号
    private String finprodName;//产品名称
    private String finprodAmt;//产品规模（基准日）
    private String purOfNetOutflow;//申赎净流出（基准日）
    private String cashBal;//现金余额（基准日）
    private String leverRate;//杠杆率（基准日）
    private String openMode;//开放类型
    
    private String oneLast;//一级流动性储备余额
    private String twoLast;//二级流动性储备余额
    private String inCount;//资金流入合计-资产端
    private String outCount;//资金流出合计-资产端
    
    private String remark;//备注
    private String sceneIdAndsceneName;//压力测试编号和名称
    
    //轻度
    private String ligValSceBre;//现金缺口
    private BigDecimal ligValRiskSlowBreOne;//一级缓释后缺口
    private BigDecimal ligValRiskSlowBre;//二级缓释后缺口
    private String ligValFlowMonBre;//流动储备/现金缺口
    private BigDecimal ligValLeverRate;//杠杆率
    //中度
    private BigDecimal medValSceBre;//情景缺口
    private BigDecimal medValRiskSlowBreOne;//一级缓释后缺口
    private BigDecimal medValRiskSlowBre;//二级缓释后缺口
    private String medValFlowMonBre;//流动储备/现金缺口
    private BigDecimal medValLeverRate;//杠杆率
    //重度
    private BigDecimal heaValSceBre;//情景缺口
    private BigDecimal heaValRiskSlowBreOne;//一级缓释后缺口
    private BigDecimal heaValRiskSlowBre;//二级缓释后缺口
    private String heaValFlowMonBre;//流动储备/现金缺口
    private BigDecimal heaValLeverRate;//杠杆率
    
    private String baseDate;//压测基准日
    private String startTime;//压测执行时间
    private String testStartDate;//压测开始日
    private String testEndDate;//压测结束日
    
    
    
	
    /**
	 * @return the oneLast
	 */
	public String getOneLast() {
		return oneLast;
	}

	/**
	 * @param oneLast the oneLast to set
	 */
	public void setOneLast(String oneLast) {
		this.oneLast = oneLast;
	}

	/**
	 * @return the twoLast
	 */
	public String getTwoLast() {
		return twoLast;
	}

	/**
	 * @param twoLast the twoLast to set
	 */
	public void setTwoLast(String twoLast) {
		this.twoLast = twoLast;
	}

	/**
	 * @return the inCount
	 */
	public String getInCount() {
		return inCount;
	}

	/**
	 * @param inCount the inCount to set
	 */
	public void setInCount(String inCount) {
		this.inCount = inCount;
	}

	/**
	 * @return the outCount
	 */
	public String getOutCount() {
		return outCount;
	}

	/**
	 * @param outCount the outCount to set
	 */
	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the sceneIdAndsceneName
	 */
	public String getSceneIdAndsceneName() {
		return sceneIdAndsceneName;
	}

	/**
	 * @param sceneIdAndsceneName the sceneIdAndsceneName to set
	 */
	public void setSceneIdAndsceneName(String sceneIdAndsceneName) {
		this.sceneIdAndsceneName = sceneIdAndsceneName;
	}

	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName 要设置的 testName
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return cashBal
	 */
	public String getCashBal() {
		return cashBal;
	}

	/**
	 * @param cashBal 要设置的 cashBal
	 */
	public void setCashBal(String cashBal) {
		this.cashBal = cashBal;
	}

	/**
	 * @return leverRate
	 */
	public String getLeverRate() {
		return leverRate;
	}

	/**
	 * @param leverRate 要设置的 leverRate
	 */
	public void setLeverRate(String leverRate) {
		this.leverRate = leverRate;
	}

	/**
	 * @return ligValRiskSlowBreOne
	 */
	public BigDecimal getLigValRiskSlowBreOne() {
		return ligValRiskSlowBreOne;
	}

	/**
	 * @param ligValRiskSlowBreOne 要设置的 ligValRiskSlowBreOne
	 */
	public void setLigValRiskSlowBreOne(BigDecimal ligValRiskSlowBreOne) {
		this.ligValRiskSlowBreOne = ligValRiskSlowBreOne;
	}

	/**
	 * @return medValRiskSlowBreOne
	 */
	public BigDecimal getMedValRiskSlowBreOne() {
		return medValRiskSlowBreOne;
	}

	/**
	 * @param medValRiskSlowBreOne 要设置的 medValRiskSlowBreOne
	 */
	public void setMedValRiskSlowBreOne(BigDecimal medValRiskSlowBreOne) {
		this.medValRiskSlowBreOne = medValRiskSlowBreOne;
	}

	/**
	 * @return heaValRiskSlowBreOne
	 */
	public BigDecimal getHeaValRiskSlowBreOne() {
		return heaValRiskSlowBreOne;
	}

	/**
	 * @param heaValRiskSlowBreOne 要设置的 heaValRiskSlowBreOne
	 */
	public void setHeaValRiskSlowBreOne(BigDecimal heaValRiskSlowBreOne) {
		this.heaValRiskSlowBreOne = heaValRiskSlowBreOne;
	}

	/**
	 * @return flowTestId
	 */
	public String getFlowTestId() {
		return flowTestId;
	}

	/**
	 * @param flowTestId 要设置的 flowTestId
	 */
	public void setFlowTestId(String flowTestId) {
		this.flowTestId = flowTestId;
	}

	/**
	 * @return portfolioId
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * @param portfolioId 要设置的 portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * @return baseDate
	 */
	public String getBaseDate() {
		return baseDate;
	}

	/**
	 * @param baseDate 要设置的 baseDate
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	/**
	 * @return testStartDate
	 */
	public String getTestStartDate() {
		return testStartDate;
	}

	/**
	 * @param testStartDate 要设置的 testStartDate
	 */
	public void setTestStartDate(String testStartDate) {
		this.testStartDate = testStartDate;
	}

	/**
	 * @return testEndDate
	 */
	public String getTestEndDate() {
		return testEndDate;
	}

	/**
	 * @param testEndDate 要设置的 testEndDate
	 */
	public void setTestEndDate(String testEndDate) {
		this.testEndDate = testEndDate;
	}

	/**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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
     * 获取 finprodAmt
     * @return finprodAmt.
     */
    public String getFinprodAmt() {
        return finprodAmt;
    }
    
    /**
     * 设置 finprodAmt
     * @param finprodAmt finprodAmt
     */
    public void setFinprodAmt(String finprodAmt) {
        this.finprodAmt = finprodAmt;
    }
    
    /**
     * 获取 purOfNetOutflow
     * @return purOfNetOutflow.
     */
    public String getPurOfNetOutflow() {
        return purOfNetOutflow==null?"0":purOfNetOutflow;
    }
    
    /**
     * 设置 purOfNetOutflow
     * @param purOfNetOutflow purOfNetOutflow
     */
    public void setPurOfNetOutflow(String purOfNetOutflow) {
        this.purOfNetOutflow = purOfNetOutflow;
    }
    
    /**
     * 获取 ligValSceBre
     * @return ligValSceBre.
     */
    public String getLigValSceBre() {
        return ligValSceBre;
    }
    
    /**
     * 设置 ligValSceBre
     * @param ligValSceBre ligValSceBre
     */
    public void setLigValSceBre(String ligValSceBre) {
        this.ligValSceBre = ligValSceBre;
    }
    
    /**
     * 获取 ligValRiskSlowBre
     * @return ligValRiskSlowBre.
     */
    public BigDecimal getLigValRiskSlowBre() {
        return ligValRiskSlowBre;
    }
    
    /**
     * 设置 ligValRiskSlowBre
     * @param ligValRiskSlowBre ligValRiskSlowBre
     */
    public void setLigValRiskSlowBre(BigDecimal ligValRiskSlowBre) {
        this.ligValRiskSlowBre = ligValRiskSlowBre;
    }
    
    /**
     * 获取 ligValFlowMonBre
     * @return ligValFlowMonBre.
     */
    public String getLigValFlowMonBre() {
        return ligValFlowMonBre;
    }
    
    /**
     * 设置 ligValFlowMonBre
     * @param ligValFlowMonBre ligValFlowMonBre
     */
    public void setLigValFlowMonBre(String ligValFlowMonBre) {
        this.ligValFlowMonBre = ligValFlowMonBre;
    }
    
    /**
     * 获取 ligValLeverRate
     * @return ligValLeverRate.
     */
    public BigDecimal getLigValLeverRate() {
        return ligValLeverRate;
    }
    
    /**
     * 设置 ligValLeverRate
     * @param ligValLeverRate ligValLeverRate
     */
    public void setLigValLeverRate(BigDecimal ligValLeverRate) {
        this.ligValLeverRate = ligValLeverRate;
    }
    
    /**
     * 获取 medValSceBre
     * @return medValSceBre.
     */
    public BigDecimal getMedValSceBre() {
        return medValSceBre;
    }
    
    /**
     * 设置 medValSceBre
     * @param medValSceBre medValSceBre
     */
    public void setMedValSceBre(BigDecimal medValSceBre) {
        this.medValSceBre = medValSceBre;
    }
    
    /**
     * 获取 medValRiskSlowBre
     * @return medValRiskSlowBre.
     */
    public BigDecimal getMedValRiskSlowBre() {
        return medValRiskSlowBre;
    }
    
    /**
     * 设置 medValRiskSlowBre
     * @param medValRiskSlowBre medValRiskSlowBre
     */
    public void setMedValRiskSlowBre(BigDecimal medValRiskSlowBre) {
        this.medValRiskSlowBre = medValRiskSlowBre;
    }
    
    /**
     * 获取 medValFlowMonBre
     * @return medValFlowMonBre.
     */
    public String getMedValFlowMonBre() {
        return medValFlowMonBre;
    }
    
    /**
     * 设置 medValFlowMonBre
     * @param medValFlowMonBre medValFlowMonBre
     */
    public void setMedValFlowMonBre(String medValFlowMonBre) {
        this.medValFlowMonBre = medValFlowMonBre;
    }
    
    /**
     * 获取 medValLeverRate
     * @return medValLeverRate.
     */
    public BigDecimal getMedValLeverRate() {
        return medValLeverRate;
    }
    
    /**
     * 设置 medValLeverRate
     * @param medValLeverRate medValLeverRate
     */
    public void setMedValLeverRate(BigDecimal medValLeverRate) {
        this.medValLeverRate = medValLeverRate;
    }
    
    /**
     * 获取 heaValSceBre
     * @return heaValSceBre.
     */
    public BigDecimal getHeaValSceBre() {
        return heaValSceBre;
    }
    
    /**
     * 设置 heaValSceBre
     * @param heaValSceBre heaValSceBre
     */
    public void setHeaValSceBre(BigDecimal heaValSceBre) {
        this.heaValSceBre = heaValSceBre;
    }
    
    /**
     * 获取 heaValRiskSlowBre
     * @return heaValRiskSlowBre.
     */
    public BigDecimal getHeaValRiskSlowBre() {
        return heaValRiskSlowBre;
    }
    
    /**
     * 设置 heaValRiskSlowBre
     * @param heaValRiskSlowBre heaValRiskSlowBre
     */
    public void setHeaValRiskSlowBre(BigDecimal heaValRiskSlowBre) {
        this.heaValRiskSlowBre = heaValRiskSlowBre;
    }
    
    /**
     * 获取 heaValFlowMonBre
     * @return heaValFlowMonBre.
     */
    public String getHeaValFlowMonBre() {
        return heaValFlowMonBre;
    }
    
    /**
     * 设置 heaValFlowMonBre
     * @param heaValFlowMonBre heaValFlowMonBre
     */
    public void setHeaValFlowMonBre(String heaValFlowMonBre) {
        this.heaValFlowMonBre = heaValFlowMonBre;
    }
    
    /**
     * 获取 heaValLeverRate
     * @return heaValLeverRate.
     */
    public BigDecimal getHeaValLeverRate() {
        return heaValLeverRate;
    }
    
    /**
     * 设置 heaValLeverRate
     * @param heaValLeverRate heaValLeverRate
     */
    public void setHeaValLeverRate(BigDecimal heaValLeverRate) {
        this.heaValLeverRate = heaValLeverRate;
    }
    /**
     * 获取 openMode
     * @return openMode.
     */
	public String getOpenMode() {
		return openMode;
	}
	 /**
     * 设置 openMode
     * @param openMode openMode
     */
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
    
    
	
    
}

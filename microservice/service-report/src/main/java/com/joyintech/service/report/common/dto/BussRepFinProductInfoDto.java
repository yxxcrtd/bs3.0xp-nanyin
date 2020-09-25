/*
 * 文件名：BussRepFinProductInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdDeclare;

/**
 * 产品中债报备信息
 * 
 * @author 张亮
 * @version 1.0 2018年9月21日
 * @see BussRepFinProductInfoDto
 * @since 1.0
 */
public class BussRepFinProductInfoDto extends RepProdDeclare {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3615036090702766860L;
    
    
    // 基础信息传入的属性 start
    // 募集起始日
    private Date repRaiseVdate;
    // 募集结束日
    private Date repRaiseEnddate;
    // 产品起始日期
    private Date repPrdVdate;
    // 产品终止日期
    private Date repPrdMdate;
    // 预计客户最高年收益率
    private BigDecimal repExpMaxYield;
    // 预计客户最低年收益率
    private BigDecimal repExpMinYield;
    // 节假日是否开放
    private String repHolidayOnFlag;
    
    /**
     * 开放模式
     */
    private String repOpenMode;
    
    /**
     * 层级类别
     */
    private String repGradeType;
    
    /**
     * 是否为结构化（分级）产品
     */
    private String repIsConstracte;
    /**
     * 系统参数，是否合并
     */
    private String isMerger;
    
    
    public String getRepGradeType() {
		return repGradeType;
	}


	public void setRepGradeType(String repGradeType) {
		this.repGradeType = repGradeType;
	}


	public String getRepIsConstracte() {
		return repIsConstracte;
	}


	public void setRepIsConstracte(String repIsConstracte) {
		this.repIsConstracte = repIsConstracte;
	}


	public String getRepOpenMode() {
		return repOpenMode;
	}


	public void setRepOpenMode(String repOpenMode) {
		this.repOpenMode = repOpenMode;
	}


	public Date getRepRaiseVdate() {
        return repRaiseVdate;
    }

    
    public void setRepRaiseVdate(Date repRaiseVdate) {
        this.repRaiseVdate = repRaiseVdate;
    }

    
    public Date getRepRaiseEnddate() {
        return repRaiseEnddate;
    }

    
    public void setRepRaiseEnddate(Date repRaiseEnddate) {
        this.repRaiseEnddate = repRaiseEnddate;
    }

    
    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    
    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    
    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    
    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    
    public BigDecimal getRepExpMaxYield() {
        return repExpMaxYield;
    }

    
    public void setRepExpMaxYield(BigDecimal repExpMaxYield) {
        this.repExpMaxYield = repExpMaxYield;
    }

    
    public BigDecimal getRepExpMinYield() {
        return repExpMinYield;
    }

    
    public void setRepExpMinYield(BigDecimal repExpMinYield) {
        this.repExpMinYield = repExpMinYield;
    }

    
    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    
    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag;
    }

    // 基础信息传入的属性 end
    
    /**
     * 平均预计客户年收益率
     */
    private BigDecimal repExpAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期
     */
    private Short repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 平均开放次数（年化）
     */
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;
    

    public BigDecimal getRepExpAvgYield() {
        return repExpAvgYield;
    }

    public void setRepExpAvgYield(BigDecimal repExpAvgYield) {
        this.repExpAvgYield = repExpAvgYield;
    }

    public String getRepYieldComment() {
        return repYieldComment;
    }

    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment;
    }

    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle;
    }

    public Short getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    public void setRepOpenCircleDays(Short repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    public String getRepRuleComment() {
        return repRuleComment;
    }

    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment;
    }

    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss;
    }

    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark;
    }


	public String getIsMerger() {
		return isMerger;
	}


	public void setIsMerger(String isMerger) {
		this.isMerger = isMerger;
	}
    
}

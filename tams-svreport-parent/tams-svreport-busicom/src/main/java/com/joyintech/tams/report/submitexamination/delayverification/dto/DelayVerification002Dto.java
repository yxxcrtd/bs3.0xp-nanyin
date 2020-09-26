/*
 * 文件名：DelayVerification002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月26日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitexamination.delayverification.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年9月26日
 * @see DelayVerification002Dto
 * @since 1.0
 */
public class DelayVerification002Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -772805366118692155L;
    /**
     * 未报送数量
     */
    private String unSubmitNum;
    /**
     * 总数量
     */
    private String total;
    /**
     * 合格率
     */
    private BigDecimal rate;
    
    /**
     * 数据的类型
     */
    private String pageType;
    
    /**
     * 编码
     */
    private String  code;
    /**
     * 报表名称
     */
    private String repName;
    /**
     * 报送状态
     */
    private String submitStatus;
    /**
     * 报送日期
     */
    private String submitTime;
    /**
     * 报送截止日期
     */
    private String submitDeadline;
    
    /**
     * 获取 code
     * @return code.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 设置 code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 获取 repName
     * @return repName.
     */
    public String getRepName() {
        return repName;
    }
    
    /**
     * 设置 repName
     * @param repName repName
     */
    public void setRepName(String repName) {
        this.repName = repName;
    }
    
    /**
     * 获取 submitStatus
     * @return submitStatus.
     */
    public String getSubmitStatus() {
        return submitStatus;
    }
    
    /**
     * 设置 submitStatus
     * @param submitStatus submitStatus
     */
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }
    
    /**
     * 获取 submitTime
     * @return submitTime.
     */
    public String getSubmitTime() {
        return submitTime;
    }
    
    /**
     * 设置 submitTime
     * @param submitTime submitTime
     */
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
    
    /**
     * 获取 submitDeadline
     * @return submitDeadline.
     */
    public String getSubmitDeadline() {
        return submitDeadline;
    }
    
    /**
     * 设置 submitDeadline
     * @param submitDeadline submitDeadline
     */
    public void setSubmitDeadline(String submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

	public String getUnSubmitNum() {
		return unSubmitNum;
	}

	public void setUnSubmitNum(String unSubmitNum) {
		this.unSubmitNum = unSubmitNum;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
    
    
}

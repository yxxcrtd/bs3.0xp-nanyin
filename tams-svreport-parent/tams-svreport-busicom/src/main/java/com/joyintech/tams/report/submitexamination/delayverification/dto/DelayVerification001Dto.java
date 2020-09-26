/*
 * 文件名：DelayVerification001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
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
 * @version 1.0 2017年9月25日
 * @see DelayVerification001Dto
 * @since 1.0
 */
public class DelayVerification001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6322534072316652791L;
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
     * 获取 unSubmitNum
     * @return unSubmitNum.
     */
    public String getUnSubmitNum() {
        return unSubmitNum;
    }
    
    /**
     * 设置 unSubmitNum
     * @param unSubmitNum unSubmitNum
     */
    public void setUnSubmitNum(String unSubmitNum) {
        this.unSubmitNum = unSubmitNum;
    }
    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }
    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    

    
    /**
     * 获取 total
     * @return total.
     */
    public String getTotal() {
        return total;
    }

    
    /**
     * 设置 total
     * @param total total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    
    /**
     * 获取 pageType
     * @return pageType.
     */
    public String getPageType() {
        return pageType;
    }

    
    /**
     * 设置 pageType
     * @param pageType pageType
     */
    public void setPageType(String pageType) {
        this.pageType = pageType;
    }
}

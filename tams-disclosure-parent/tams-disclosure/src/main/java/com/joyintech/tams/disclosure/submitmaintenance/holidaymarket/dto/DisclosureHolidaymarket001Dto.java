/*
 * 文件名：Holidaymarket001Dto.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：zyf
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.disclosure.submitmaintenance.holidaymarket.dto;

import java.util.Date;

import com.joyintech.tams.disclosure.common.common.base.TableRow;


/**
 * 〈一句话功能简述〉
 * 
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see DisclosureHolidaymarket001Dto
 * @since 1.0
 */
public class DisclosureHolidaymarket001Dto extends TableRow{

    /**
     * 数据状态
     */
    private String status;
    
    /**
     * 日期
     */
    private Date busiDate;
    
    /**
     * 交易市场
     */
    private String marketCode;
    
    /**
     * 是否节假日
     */
    private String isHoliday;
    
    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 经办人
     */
    private String agent;
    
    /**
     * 获取 agent
     * @return agent.
     */
    public String getAgent() {
        return agent;
    }


    
    /**
     * 设置 agent
     * @param agent agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }


    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    /**
     * 获取 busiDate
     * @return busiDate.
     */
    public Date getBusiDate() {
        return busiDate;
    }

    
    /**
     * 设置 busiDate
     * @param busiDate busiDate
     */
    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    
    /**
     * 获取 marketCode
     * @return marketCode.
     */
    public String getMarketCode() {
        return marketCode;
    }

    
    /**
     * 设置 marketCode
     * @param marketCode marketCode
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    
    /**
     * 获取 isHoliday
     * @return isHoliday.
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    
    /**
     * 设置 isHoliday
     * @param isHoliday isHoliday
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    
    /**
     * 获取 deptCode
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    
    /**
     * 设置 deptCode
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
      
    
}

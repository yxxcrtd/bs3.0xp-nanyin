/*
 * 文件名：ChbDeadLine001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.common.dto;

import java.util.Date;

import com.joyintech.tams.disclosure.common.common.base.TableRow;

/**
 * 节假日复制dto（报送时点用）
 * @author zuoyinyi
 * @version 1.0 2018年5月22日
 * @see ChbDeadLine001Dto
 * @since 1.0
 */
public class ChbDeadLine001Dto extends TableRow{

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

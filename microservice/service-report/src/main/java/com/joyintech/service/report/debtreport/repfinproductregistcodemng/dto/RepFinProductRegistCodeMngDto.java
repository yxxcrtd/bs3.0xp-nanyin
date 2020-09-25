/*
 * 文件名：RepFinProductRegistCodeMngDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductregistcodemng.dto;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngDto
 * @since 1.0
 */
public class RepFinProductRegistCodeMngDto extends PaPrdIssueSchedule{
    
    /**
     * 投资者类型
     */
    private String repInvestorType;

    
    /**
     * 获取 repInvestorType
     * @return repInvestorType.
     */
    public String getRepInvestorType() {
        return repInvestorType;
    }

    
    /**
     * 设置 repInvestorType
     * @param repInvestorType repInvestorType
     */
    public void setRepInvestorType(String repInvestorType) {
        this.repInvestorType = repInvestorType;
    }
    
    

}

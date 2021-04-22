package com.joyintech.service.portfolio.ptlmanagementinfo.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @Classname PtlAuthInfoDto
 * @Description 组合权限Dto
 * @author zhuhanlu 
 * @date 2020/12/17 11:35 
 * @version 1.0 
 */
public class PtlAuthInfoDto extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioCode;

    /**
     * 用户代码
     */
    private String userId;

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

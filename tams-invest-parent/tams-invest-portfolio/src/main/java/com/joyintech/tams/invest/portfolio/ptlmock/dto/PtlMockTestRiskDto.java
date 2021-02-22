/*
 * 文件名：PtlMockTestRiskDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年6月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmock.dto;

import com.joyintech.tams.common.base.entity.MockTestRsk;

/**
 * 模拟试算风险
 * 
 * @author 裴家俊
 * @version 1.0 2020/06/23
 * @see PtlMockTestRiskDto
 * @since 1.0
 */

public class PtlMockTestRiskDto extends MockTestRsk {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6568035942591602028L;

    private String kriType;

    private String kriExpr;

    
    public String getKriType() {
        return kriType;
    }

    
    public void setKriType(String kriType) {
        this.kriType = kriType;
    }

    
    public String getKriExpr() {
        return kriExpr;
    }

    
    public void setKriExpr(String kriExpr) {
        this.kriExpr = kriExpr;
    }
}

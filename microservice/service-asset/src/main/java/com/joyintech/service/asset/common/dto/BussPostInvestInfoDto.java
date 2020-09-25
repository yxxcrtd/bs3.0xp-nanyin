/*
 * 文件名：BussPostInvestInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import com.joyintech.tams.common.base.entity.FinPostInvestInfo;

/**
 * 投后信息DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussPostInvestInfoDto
 * @since 1.0
 */
public class BussPostInvestInfoDto extends FinPostInvestInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资产类别
     */
    private String type6;
    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }
    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
}

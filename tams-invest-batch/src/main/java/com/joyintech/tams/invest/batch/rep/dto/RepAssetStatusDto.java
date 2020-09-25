/*
 * 文件名：RepAssetStatusDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.rep.dto;


import java.util.Date;

/**
 * 更新中债资产报送状态
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月15日
 * @see RepAssetStatusDto
 * @since 1.0
 */
public class RepAssetStatusDto {
    private Date updateTime;

    public RepAssetStatusDto(){
        this.updateTime = new Date();
    }


    public Date getUpdateTime(Object obj) {
        return this.updateTime;
    }
}

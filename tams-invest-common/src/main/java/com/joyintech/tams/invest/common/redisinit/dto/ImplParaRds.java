/*
 * 文件名：ImplRiskRds.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.tams.common.base.entity.RskBusinessRedisConfig;

/**
 * 刷新redis参数实现类
 * @author 裴家俊
 * @version 1.0 2019年10月2日
 * @see ImplParaRds
 * @since 1.0
 */
public class ImplParaRds extends RskBusinessRedisConfig implements IParaRds {

    /**
     * 条件字段(资产代码或组合代码之类的)
     */
    private String field;

    @Override
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

/*
 * 文件名：IPtlRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.prodinfo.dto;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

import java.util.Date;
import java.util.List;

/**
 * 规则接口
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see IPtlRule
 * @since 1.0
 */
public interface IPtlRule {

    /**
     * 规则编号
     * @return
     */
    public String getRuleId();

    /**
     * 是否排除关联表已有的组合
     * @return
     */
    default String getRemoveHasPort(){return BussinessDictCodeListRds.是否_否.getValue();};

}

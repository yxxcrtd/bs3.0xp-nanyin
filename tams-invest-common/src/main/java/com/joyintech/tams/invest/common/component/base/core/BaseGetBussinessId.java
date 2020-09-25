/*
 * 文件名：BussGetBussinessIdService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.component.base.core;

import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * 业务编号共通组件
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年9月5日
 * @see BaseGetBussinessId
 * @since 1.0
 */
public interface BaseGetBussinessId {
    /**
     * 生成业务编号
     * 
     * @param parameter 共通业务类型
     * @return 业务编号
     * @throws Exception
     */
    String buildBussinessCode(BussinessCodeParameter parameter);
}

/*
 * 文件名：ValSysParaMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.val.dao;

import org.springframework.stereotype.Repository;

/**
 * 获取系统参数
 * @author zhangwei
 * @version 1.0 2019年2月1日
 * @see ValSysParaMapper
 * 
 * @since 1.0
 */
@Repository
public interface ValSysParaMapper {

    String selectParamValue(String paraCode);
}

/*
 * 文件名：SysInstRegBankCode002Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode002Dto;

/**
 * 机构登记银行代码新建修改画面用mapper
 *
 * @author 刘二通
 * @version 1.0 2018年5月18日
 * @see SysInstRegBankCode002Mapper
 * @since 1.0
 */
@Repository
public interface SysInstRegBankCode002Mapper {

    /**
     * 查找数据库中已经存在的数据条数
     * 登记银行代码相同
     *
     * @param paramMap 参数
     * @return 数据条数
     * @see
     * @since 1.0
     */
    int selectRegistBankCodeDataCount(Map<String, Object> paramMap);

    /**
     * 查询画面显示数据
     *
     * @param paramMap 参数map
     * @return 画面显示数据
     * @see
     * @since 1.0
     */
    SysInstRegBankCode002Dto selectPageData(Map<String, Object> paramMap);
}

/*
 * 文件名：SysInstRegBankCode001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;

/**
 * 机构登记银行代码管理画面用mapper
 *
 * @author 刘二通
 * @version 1.0 2018年5月18日
 * @see SysInstRegBankCode001Mapper
 * @since 1.0
 */
@Repository
public interface SysInstRegBankCode001Mapper {

    /**
     * 机构登记银行代码列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<SysInstRegBankCode001Dto> queryForPage(Map<String, Object> param);

    /**
     * 查找导入文件中的数据在数据库中已经存在的数据条数
     *
     * @param keylist 数据列表
     * @return 数据条数
     * @see
     * @since 1.0
     */
    int selectDataCount(@Param("keylist") List<SysInstRegBankCode001Dto> keylist);
    /**
     * 机构登记银行代码列表查询,银行模式
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    SysInstRegBankCode001Dto queryForPageWithMode(Map<String, Object> param);

}

/*
 * 文件名：BaseCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.tams.common.base.entity.PtlCapacctInfo;

import org.springframework.stereotype.Repository;

/**
 * 
 * 产品端共通验证
 * @author weipeng
 * @version 1.0 2018年5月10日
 * @see BaseCommonMapper
 * @since 1.0
 */
@Repository
public interface BaseCommonMapper {


    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     * @see
     * @since 1.0
     */
    Date getNowSysDate();

    /**
     * 上传文件表里获取文件路径
     *
     * @param fileNo 文件代码
     * @return 文件路径
     * @see
     * @since 1.0
     */
    String getFilePathByCode(String fileNo);

    /**
     * 更新平台日
     * @param date 更新的平台日
     * @see
     * @since 1.0
     */
    void updatePlateDate(Date date);
    
    /**
     * 更新平台日
     * @see
     * @since 1.0
     */
    void updateSysdate();
    
    /**
     * 根据採番名採番
     *
     * @param param 採番参数
     * @return 採番结果
     * @see
     */
    Long getSequenceIndexData(Map<String, String> param);

    /**
     * 获取产品组下的账套
     * ADD BY WUJIE ON 20190521
     * @param params
     * @return
     */
    List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params);

    /**
     * 获取账套下的分层代码
     * ADD BY WUJIE ON 20190521
     * @param booksetIds
     * @return
     */
    List<String> getBooksetIdbyLayeringInfo(Map<String, Object> map);

    /**
     * 获取子账套代码
     * @param map
     * @return
     */
    List<String> getBooksetIdSon(Map<String, Object> map);
    
    /**
     * 查询金融产品账户信息Ex
     * @param paramMap 参数
     * @return 金融产品账户信息
     * @see
     * @since 1.0
     */
    PtlCapacctInfo selectProdAcctData(Map<String, Object> paramMap);
}

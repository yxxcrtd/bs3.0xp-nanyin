/*
 * 文件名：BaseCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dao.BaseCommonMapper;

/**
 * 共通Service
 *
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see BaseCommonService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseCommonService extends BaseService<ITableRow> {

    /**
     * CommonMapper
     */
    @Autowired
    private BaseCommonMapper commonMapper;


    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public String getSysDateString() {
        return DateUtil.format(commonMapper.getNowSysDate());
    }

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public Date getSysDate() {
        return commonMapper.getNowSysDate();
    }
    
    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public void updateSysdate() {
        commonMapper.updateSysdate();
    }
    /**
     * 根据採番名採番
     *
     * @param sequenceType 採番序列类型
     * @return 採番值
     * @see
     */
    @SuppressWarnings("unchecked")
    public String getSequenceIndexData(DataIndexSequenceType sequenceType) {
        Map<String, String> param = new HashMap<>();
        String databaseType = (String)((Map<String, Object>)CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Object.class)).get(
            GlobalConstant.COM_PROP_DATABASE_TYPE);

        databaseType = StringUtil.defaultString(databaseType, "DB2");

        param.put("databaseType", databaseType);
        param.put("sequenceName", sequenceType.getSequenceName());
        Long result = commonMapper.getSequenceIndexData(param);

        String sequenceResult = String.format("%018d", result);

        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
        String shortName = sequenceType.getShortName();
        sequenceResult = shortName + sequenceResult;

        return sequenceResult;
    }

    /**
     * 根据採番名採番，长度为length
     *
     * @param sequenceType 採番序列类型
     * @param length 生成的长度
     * @return 採番值
     * @see
     */
    @SuppressWarnings("unchecked")
    public String getSequenceIndexData(DataIndexSequenceType sequenceType, int length) {
        length = Math.abs(length);
        String formatter = "%0" + length + "d";
        Map<String, String> param = new HashMap<>();
        String databaseType = (String)((Map<String, Object>)CacheUtil.getAppCache(
            GlobalConstant.SYS_PARA_KEY, Object.class)).get(GlobalConstant.COM_PROP_DATABASE_TYPE);

        databaseType = StringUtil.defaultString(databaseType, "DB2");

        param.put("databaseType", databaseType);
        param.put("sequenceName", sequenceType.getSequenceName());
        Long result = commonMapper.getSequenceIndexData(param);

        String sequenceResult = String.format(formatter, result);
        if (length != 18) {
            return sequenceResult;
        }
        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
        String shortName = sequenceType.getShortName();
        sequenceResult = shortName + sequenceResult;

        return sequenceResult;
    }

    /**
     * 获取产品组下的账套
     * ADD BY WUJIE ON 21090521
     * @param object
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params) {
        List<SysBatchGrpPrdlink> list = commonMapper.findAllProduct(params);
        return list;
    }

    /**
     * 根据账套代码获取分层代码
     * ADD BY WUJIE ON 20190521
     * @param bookSetIds
     * @return
     */
    public List<String> getBooksetIdbyLayeringInfo(List<String> bookSetIds){
        Map<String,Object> map = new HashMap<>();
        map.put("booksetIds", bookSetIds);
        List<String> busiIds = commonMapper.getBooksetIdbyLayeringInfo(map);
        return busiIds;
    }

    /**
     * 根据账套代码获取子账套代码
     * @param bookSetIds
     * @return
     */
    public List<String> getBooksetIdSon(List<String> bookSetIds){
        Map<String,Object> map = new HashMap<>();
        map.put("booksetIds", bookSetIds);
        List<String> busiIds = commonMapper.getBooksetIdSon(map);
        return busiIds;
    }

}

/*
 * 文件名：InvestTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.system.systemsqlquery.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.disclosure.system.systemsqlquery.dao.DisSystemSqlQuery001Mapper;
import com.joyintech.tams.disclosure.system.systemsqlquery.dto.DisSystemSqlQuery001Dto;


/**
 * 投资交易查询-service
 * 
 * @author yangjian
 * @version 1.0 2018年7月25日
 * @see SystemSqlQuery001Service
 * @since 1.0
 */
@Service
public class DisSystemSqlQuery001Service extends CommonExportService<DisSystemSqlQuery001Dto> {
    @Autowired
    private DisSystemSqlQuery001Mapper mapper;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    public List<LinkedHashMap<String, Object>> selectAllData(String sqlText) {
        return mapper.selectAllData(sqlText);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    public int insertAllData(String sqlText) {
        return mapper.insertAllData(sqlText);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    public int deleteAllData(String sqlText) {
        return mapper.deleteAllData(sqlText);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    public int updateAllData(String replace) {
        return mapper.updateAllData(replace);
    }

}

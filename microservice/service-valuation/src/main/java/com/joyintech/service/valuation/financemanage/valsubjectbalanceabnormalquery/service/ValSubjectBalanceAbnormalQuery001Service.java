/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.dao.ValSubjectBalanceAbnormalQuery001Mapper;
import com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.dto.ValSubjectBalanceAbnormalQuery001Dto;

/**
 * 到期产品科目余额异常查询-service
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Service
 * @since 1.0
 */
@Service("backValSubjectBalanceAbnormalQuery001Service")
public class ValSubjectBalanceAbnormalQuery001Service extends CommonExportService<ValSubjectBalanceAbnormalQuery001Dto> {

    @Autowired
    private ValSubjectBalanceAbnormalQuery001Mapper valSubjectBalanceAbnormalQuery001Mapper;

    public Page<ValSubjectBalanceAbnormalQuery001Dto> queryPageList(int page, int length, Map<String, Object> params)
        throws Exception {
        String booksetId = (String)params.get("booksetId");
        String[] booksetIdArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetIdArr = booksetId.split(",");
        }
        params.put("booksetIdArr", booksetIdArr);
        return super.queryForPage(
            () -> valSubjectBalanceAbnormalQuery001Mapper.queryPageList(params));
    }

    public List<ValSubjectBalanceAbnormalQuery001Dto> getAllList(Map<String, Object> params) {
        String booksetId = (String)params.get("booksetId");
        String[] booksetIdArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetIdArr = booksetId.split(",");
        }
        params.put("booksetIdArr", booksetIdArr);
        return valSubjectBalanceAbnormalQuery001Mapper.queryPageList(params);
    }
}

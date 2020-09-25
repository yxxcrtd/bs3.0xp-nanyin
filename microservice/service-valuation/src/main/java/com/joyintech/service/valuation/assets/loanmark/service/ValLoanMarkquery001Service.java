/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.loanmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.assets.loanmark.dao.ValLoanMarkquery001Mapper;
import com.joyintech.service.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;

/**
 * 拆借标的查询
 * 
 * @author huwei
 * @version 1.0 2018年10月19日
 * @see ValLoanMarkquery001Service
 * @since 1.0
 */
@Service(value="serviceValLoanMarkquery001Service")
@Transactional(rollbackFor = Exception.class)
public class ValLoanMarkquery001Service extends CommonExportService<ValLoanMarkquery001Dto> {

    /**
     * ValLoanMarkquery001Mapper
     */
    @Autowired
    private ValLoanMarkquery001Mapper mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ValLoanMarkquery001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {
        return super.queryForPageForSelectReturn(page, length, () -> mapper.queryPageList(params));
    }

    /**
     * 根据条件查询所有
     * 
     * @param params params
     * @return List List
     * @throws Exception Exception
     */
    public List<ValLoanMarkquery001Dto> findAll(Map<String, Object> params){
        List<ValLoanMarkquery001Dto> list = mapper.queryPageList(params);
        return list;
    }
}

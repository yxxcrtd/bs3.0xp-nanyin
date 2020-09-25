/*
 * 文件名：CommonSearchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlinveststrategypool.dao.PtlCommonSearchMapper;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangJin
 * @version 1.0 2019年10月14日
 * @see PtlCommonSearchService
 * @since 1.0
 */
@Service
public class PtlCommonSearchService extends BaseService {

    @Autowired
    private PtlCommonSearchMapper ptlCommonSearchMapper;

    /**
     * 资产分类2多选下拉框加载
     * 
     * @param length
     * @param page
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType2(int page, int length,
                                               Map<String, Object> pageParam) {
        String initValue = (String)pageParam.get("initValue");
        pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
        return super.queryForPage(page, length,
            () -> ptlCommonSearchMapper.queryMulType2(pageParam));
    }

    /**
     * 资产分类3多选下拉框加载
     * 
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType3(int page, int length,
                                               Map<String, Object> pageParam) {
        String type2 = (String)pageParam.get("type2");
        pageParam.put("mulType2", StringUtil.splitToList(GlobalConstant.COMMA, type2));
        String initValue = (String)pageParam.get("initValue");
        pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
        Page<SelectOptionDto> pages = super.queryForPage(page, length,
            () -> ptlCommonSearchMapper.queryMulType3(pageParam));
        return pages;
    }

    /**
     * 资产分类4多选下拉框加载
     * 
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType4(int page, int length,
                                               Map<String, Object> pageParam) {
        String type3 = (String)pageParam.get("type3");
        List<String> mulType3 = StringUtil.splitToList(GlobalConstant.COMMA, type3);
        pageParam.put("mulType3", mulType3);
        String initValue = (String)pageParam.get("initValue");
        pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
        return super.queryForPage(page, length,
            () -> ptlCommonSearchMapper.queryMulType4(pageParam));
    }

    /**
     * 资产分类5多选下拉框加载
     * 
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType5(int page, int length,
                                               Map<String, Object> pageParam) {
        String type4 = (String)pageParam.get("type4");
        List<String> mulType4 = StringUtil.splitToList(GlobalConstant.COMMA, type4);
        pageParam.put("mulType4", mulType4);
        String initValue = (String)pageParam.get("initValue");
        pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
        return super.queryForPage(page, length,
            () -> ptlCommonSearchMapper.queryMulType5(pageParam));

    }

}

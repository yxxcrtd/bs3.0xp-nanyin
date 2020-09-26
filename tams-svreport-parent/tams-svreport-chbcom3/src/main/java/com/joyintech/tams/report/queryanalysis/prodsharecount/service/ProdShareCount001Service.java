/*
 * 文件名：ProdShareCount001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.queryanalysis.prodsharecount.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dao.ProdShareCount001Mapper;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;

/**
 * 产品份额统计service
 * @author zuoyinyi
 * @version 1.0 2018年6月29日
 * @see ProdShareCount001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdShareCount001Service extends CommonExportService<ProdShareCount001Dto>{

    /**
     * 产品份额统计mapper
     */
    @Autowired
    private ProdShareCount001Mapper prodShareCount001Mapper;
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProdShareCount001Service.class);
    /**
     * 页面查询
     * @param pageParam 查询参数
     * @return 结果列表
     * @see
     * @since 1.0
     */
    public Page<ProdShareCount001Dto> searchPage(Map<String, Object> pageParam) {
//        String prdRegistCode = (String)pageParam.get("prdRegistCode");
//        if (StringUtil.isNotBlank(prdRegistCode)) {
//            pageParam.put("prdRegistCodeList",
//                StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
//        }
        formatSearchCon(pageParam);
        int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<ProdShareCount001Dto> page = queryForPage(
        		pagenum,legth,()->prodShareCount001Mapper.searchPage(pageParam));
        this.formatSearchResult(page.getRoot());
        return page;
    }

    /**
     * 导出数据来源
     * @param parameters 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<ProdShareCount001Dto> setDataList(Map<String, Object> map) {
        List<ProdShareCount001Dto> resultList = new ArrayList<>();
        String keyList = (String)map.get("keyList");
        if(StringUtil.isNotEmpty(keyList)){
            try {
                resultList = JsonUtils.json2List(keyList, ProdShareCount001Dto.class);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        else{
            formatSearchCon(map);
            resultList = prodShareCount001Mapper.searchPage(map);
            this.formatSearchResult(resultList);
        }
        return resultList;
    }

    /**
     * 格式化查询条件
     *
     * @param map 查询条件
     * @see
     * @since 1.0
     */
    private void formatSearchCon(Map<String, Object> map) {
        // 产品登记编码
        String prdRegistCode = (String)map.get("prdRegistCode");
        if (StringUtil.isNotBlank(prdRegistCode)) {
            map.put("prdRegistCodeList",
                StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
        }

        // 查询日
        String holdDateStart = (String)map.get("holdDateStart");
        if (StringUtil.isNotBlank(holdDateStart)) {
            // 查询日开始日前一天
            map.put("holdDateStartLastDay", DateUtil.addDay(holdDateStart, -1));
        }
        String holdDateEnd = (String)map.get("holdDateEnd");
        if (StringUtil.isNotBlank(holdDateEnd)) {
            // 查询日结束日前一天
            map.put("holdDateEndLastDay", DateUtil.addDay(holdDateEnd, -1));
        }
    }

    /**
     * 格式化查询结果
     *
     * @param resultList 查询结果
     * @see
     * @since 1.0
     */
    private void formatSearchResult(List<ProdShareCount001Dto> resultList) {
        if (StringUtil.isEmptyList(resultList)) {
            return;
        }

        for (ProdShareCount001Dto prodShareCount001Dto : resultList) {
            prodShareCount001Dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种_中债,
                prodShareCount001Dto.getCcy()));
        }
    }
}

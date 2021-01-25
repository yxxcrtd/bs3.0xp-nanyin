package com.joyintech.service.risk.creditmanagement.cemonitor.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditmanagement.cemonitor.dao.CeMonitorReportMapper;
import com.joyintech.service.risk.creditmanagement.cemonitor.dto.CreditLinesDealDto;

/**
 * @description: RskSupervisionRiskAnalyzeService
 * @author: 裴家俊
 * @create: 2018-11-01 14:38
 **/
@Service("backCeMonitorReportService")
public class CeMonitorReportService extends BaseService<CreditLinesDealDto> {
    /**
     * 授信额度监测mapper
     */
    @Autowired
    private CeMonitorReportMapper ceMonitorReportMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<CreditLinesDealDto> selectOrgForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if (pageParam.get("customerId") != null && StringUtil.isNotEmpty((String)pageParam.get("customerId"))) {
            String[] customerIdList = pageParam.get("customerId").toString().split(",");
            pageParam.put("customerIdList", Arrays.asList(customerIdList));
        }
        pageParam.put("sysDate", DateUtil.getSysDate());
    	if(StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"),"root")) {
            List<CreditLinesDealDto> resultList = ceMonitorReportMapper.selectOrgForPage(pageParam);
            Page<CreditLinesDealDto> pagelist = new Page<>();
            pagelist.setRoot(resultList);
            return pagelist;
        }
        return super.queryForPage(page, length, () -> ceMonitorReportMapper.selectOrgForPage(pageParam));
    }
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<CreditLinesDealDto> selectAssetForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if (pageParam.get("customerId") != null && StringUtil.isNotEmpty((String)pageParam.get("customerId"))) {
            String[] customerIdList = pageParam.get("customerId").toString().split(",");
            pageParam.put("customerIdList", Arrays.asList(customerIdList));
        }
        pageParam.put("sysDate", DateUtil.getSysDate());
        return super.queryForPage(page, length, () -> ceMonitorReportMapper.selectAssetForPage(pageParam));
    }
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<CreditLinesDealDto> selectAssetGroupForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if (pageParam.get("customerId") != null && StringUtil.isNotEmpty((String)pageParam.get("customerId"))) {
            String[] customerIdList = pageParam.get("customerId").toString().split(",");
            pageParam.put("customerIdList", Arrays.asList(customerIdList));
        }
        pageParam.put("sysDate", DateUtil.getSysDate());
        return super.queryForPage(page, length, () -> ceMonitorReportMapper.selectAssetGroupForPage(pageParam));
    }
}

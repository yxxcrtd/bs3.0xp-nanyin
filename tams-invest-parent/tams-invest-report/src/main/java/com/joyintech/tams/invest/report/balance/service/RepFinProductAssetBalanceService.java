/*
 * 文件名：RepFinProductAssetBalanceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.balance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CommonAlgorithmUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.balance.dto.RepFinProductAssetBalanceDto;

/**
 * 资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月5日
 * @see RepFinProductAssetBalanceService
 * @since 1.0
 */
@Service
public class RepFinProductAssetBalanceService extends CommonExportService<RepFinProductAssetBalanceDto>
{
    @Autowired
    private IPlatformDate iPlatformDate; 
    
    public Page<RepFinProductAssetBalanceDto> selectForPage(Map<String, Object> pageParam) {
        pageParam = handleMap(pageParam);
        return  restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductAssetBalance/findPage",
            pageParam, RepFinProductAssetBalanceDto.class);
        
    }
    
    public List<RepFinProductAssetBalanceDto> selectAllList(Map<String, Object> pageParam){
        pageParam = handleMap(pageParam);
        return  restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductAssetBalance/selectAllList",
            pageParam, RepFinProductAssetBalanceDto.class);
    }
    
    
    private Map<String,Object> handleMap(Map<String,Object> pageParam){
        pageParam.put("sysDate", DateUtil.getSysDate());
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
        }else{
            pageParam.remove("portfolioId");
        }
        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.remove("finprodId");
        }
        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.remove("finprodId");
        }
        if (StringUtil.isNotEmpty(pageParam.get("finprodType2"))) {
            pageParam.put("finprodType2", StringUtil.splitToList(null, pageParam.get("finprodType2").toString()));
        }else{
            pageParam.remove("finprodType2");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type4"))) {
            pageParam.put("type4", StringUtil.splitToList(null, pageParam.get("type4").toString()));
        }else{
            pageParam.remove("type4");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type5"))) {
            pageParam.put("type5", StringUtil.splitToList(null, pageParam.get("type5").toString()));
        }else{
            pageParam.remove("type5");
        }
        if (StringUtil.isNotEmpty(pageParam.get("deptCode"))) {
            pageParam.put("deptCode", StringUtil.splitToList(null, pageParam.get("deptCode").toString()));
        }else{
            pageParam.remove("deptCode");
        }
        return pageParam;
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/balance/conf/export_conf2.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepFinProductAssetBalanceDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    private Predicate<RepFinProductAssetBalanceDto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            return true;
        });
    }
    
    public List<RepFinProductAssetBalanceDto> selectAllListSum(Map<String, Object> pageParam)
        throws Exception {
        List<RepFinProductAssetBalanceDto> root = super.queryForTree(() -> selectSum(pageParam),
            RepFinProductAssetBalanceDto::getId, RepFinProductAssetBalanceDto::getParentInfo,
            RepFinProductAssetBalanceDto::getPortfolioName, getFilter(pageParam)).getRoot();
        List<CommonAlgorithmUtil.TreeNode<RepFinProductAssetBalanceDto>> tree = CommonAlgorithmUtil.gridToTree(
            root);
        ArrayList<RepFinProductAssetBalanceDto> list = new ArrayList<>();
        CommonAlgorithmUtil.treeDepthFirstTraversal(tree, (i, v) -> {
            v.setPortfolioName(
                IntStream.range(0, i).boxed().map((x) -> "").collect(Collectors.joining())
                          + v.getPortfolioName());
            list.add(v);
        });
        return list;
    }
    
    public List<RepFinProductAssetBalanceDto> selectSum(Map<String, Object> pageParam){
        return  restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductAssetBalance/selectAllListSum",
            pageParam, RepFinProductAssetBalanceDto.class);
    }
}

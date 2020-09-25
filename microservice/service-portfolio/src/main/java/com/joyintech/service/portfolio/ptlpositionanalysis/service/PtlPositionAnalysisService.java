/*
 * 文件名：PtlPositionAnalysisService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlpositionanalysis.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CommonAlgorithmUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlpositionanalysis.dao.PtlPositionAnalysisMapper;
import com.joyintech.service.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis001Dto;
import com.joyintech.service.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis002Dto;
import com.joyintech.service.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis003Dto;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 组合持仓分析
 * @author wanghb
 * @version 1.0 2019年1月25日
 * @see PtlPositionAnalysisService
 * @since 1.0
 */
@Service
public class PtlPositionAnalysisService extends BaseService<PtlPositionAnalysis001Dto> {
    /**
     * 定义债券质押查询mapper
     */
    @Autowired
    private PtlPositionAnalysisMapper ptlPositionAnalysisMapper;

    /**
     * 组合持仓明细查询
     *
     * @param page      初始
     * @param length    长度
     * @param pageParam 条件
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlPositionAnalysis001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(pageParam.get("assetType"))) {
            pageParam.put("assetTypeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("assetType")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if (StringUtil.isNotEmpty(pageParam.get("prodManager"))) {
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end 
        pageParam.put("cash", "现金");
        // 多级法人改造 add by jingqiongqiong 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        if (StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"), "root")) {
            List<PtlPositionAnalysis001Dto> resultList = ptlPositionAnalysisMapper.select(pageParam);
            countSumAndCalPercent(resultList);
            Page<PtlPositionAnalysis001Dto> pagelist = new Page<>();
            pagelist.setRoot(resultList);
            return pagelist;
        }
        Page<PtlPositionAnalysis001Dto> list = super.queryForPage(page, length, () -> ptlPositionAnalysisMapper.select(pageParam));
        return list;
    }
    private void countSumAndCalPercent(List<PtlPositionAnalysis001Dto> resultList) {
        BigDecimal sum = BigDecimal.ZERO;
        if(!StringUtil.isEmptyList(resultList)){
            for (PtlPositionAnalysis001Dto temp : resultList) {
                if(temp.getMarketValue()!=null){
                    sum = sum.add(temp.getMarketValue());
                }
                if(temp.getTdyIntincexp()!=null){
                    sum = sum.add(temp.getTdyIntincexp());
                }
            }
            //总额不能为0
            if(BigDecimal.ZERO.compareTo(sum)!=0){
                for (PtlPositionAnalysis001Dto temp : resultList) {
                    BigDecimal marketAndTdyIntince = BigDecimal.ZERO;
                    if(temp.getMarketValue()!=null){
                        marketAndTdyIntince = marketAndTdyIntince.add(temp.getMarketValue());
                    }
                    if(temp.getTdyIntincexp()!=null){
                        marketAndTdyIntince = marketAndTdyIntince.add(temp.getTdyIntincexp());
                    }
                    temp.setTdyAmtRation(ArithmeticUtil.round(marketAndTdyIntince.multiply(new BigDecimal(100)).divide(sum,6, BigDecimal.ROUND_HALF_UP),2
                            , RoundingMode.HALF_UP));
                    //较上一日变化
                    temp.setYstAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(temp.getTdyAmtRation(),temp.getYstAmtRationTemp()),2,RoundingMode.HALF_UP));
                    //较本月初变化
                    temp.setMonthAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(temp.getTdyAmtRation(),temp.getMonthAmtRationTemp()),2,RoundingMode.HALF_UP));
                    //较本年初变化
                    temp.setYearAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(temp.getTdyAmtRation(),temp.getYearAmtRationTemp()),2,RoundingMode.HALF_UP));
                }
            }
        }
    }
    /**
     * 组合持仓明细查询
     *
     * @param pageParam 条件
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<PtlPositionAnalysis001Dto> selectAllData(Map<String, Object> pageParam)
            throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(pageParam.get("assetType"))) {
            pageParam.put("assetTypeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("assetType")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if (StringUtil.isNotEmpty(pageParam.get("prodManager"))) {
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end
        // 多级法人改造 add by jingqiongqiong 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        List<PtlPositionAnalysis001Dto> root = super.queryForTree(() -> ptlPositionAnalysisMapper.selectAll(pageParam),
                PtlPositionAnalysis001Dto::getChildInfo, PtlPositionAnalysis001Dto::getParentInfo,
                PtlPositionAnalysis001Dto::getPortfolioName, getFilter(pageParam)).getRoot();
        List<CommonAlgorithmUtil.TreeNode<PtlPositionAnalysis001Dto>> tree = CommonAlgorithmUtil.gridToTree(
                root);
        ArrayList<PtlPositionAnalysis001Dto> list = new ArrayList<>();
        CommonAlgorithmUtil.treeDepthFirstTraversal(tree, (i, v) -> {
            v.setPortfolioName(
                    IntStream.range(0, i).boxed().map((x) -> "　").collect(Collectors.joining())
                            + v.getPortfolioName() == null ? "" : v.getPortfolioName());
            list.add(v);
        });
        return list;
    }

    /**
     * 筛选
     *
     * @param pageParam 条件
     * @return 结果
     * @see
     * @since 1.0
     */
    private Predicate<PtlPositionAnalysis001Dto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            String portfolioId = (String) pageParam.get("portfolioId");
            String assetType = (String) pageParam.get("assetType");
            if (StringUtil.isNotEmpty(portfolioId)) {
                if (!StringUtil.contains(item.getPortfolioId(), portfolioId)) {
                    return false;
                }
            }
            if (StringUtil.isNotEmpty(assetType)) {
                if (!StringUtil.contains(item.getFinprodType2(), assetType)) {
                    return false;
                }
            }
            return true;
        });
    }

    /**
     * 组合持仓汇总查询
     *
     * @param pageParam 条件
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<PtlPositionAnalysis002Dto> selectAssetForList(Map<String, Object> pageParam)
            throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(pageParam.get("assetType"))) {
            pageParam.put("assetTypeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("assetType")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if (StringUtil.isNotEmpty(pageParam.get("prodManager"))) {
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end
        pageParam.put("cash", "现金");
        //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
        List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
        pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        List<PtlPositionAnalysis002Dto> list = new ArrayList<>();
        if (StringUtil.equals(pageParam.get("sex"), "01")) {
            list = ptlPositionAnalysisMapper.selectAsset(pageParam);
        } else {
            list = ptlPositionAnalysisMapper.selectAssetPierce(pageParam);
        }

        return list == null ? new ArrayList<>() : list;
    }

    /**
     * 组合的联想下拉框事件
     *
     * @param map 输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortFolioInfo(Map<String, Object> map) {
        return ptlPositionAnalysisMapper.getPortFolioInfo(map);
    }

    /**
     * 导出查询list。
     *
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<PtlPositionAnalysis001Dto> selectForExport(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(pageParam.get("assetType"))) {
            pageParam.put("assetTypeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("assetType")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if (StringUtil.isNotEmpty(pageParam.get("prodManager"))) {
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end
        List<PtlPositionAnalysis001Dto> resultList = new ArrayList<>();
        pageParam.put("parentId", "root");
        pageParam.put("cash", "现金");
        // 多级法人改造 add by jingqiongqiong 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        Page<PtlPositionAnalysis001Dto> pageRoot = super.queryForPage(page, length, () -> ptlPositionAnalysisMapper.selectForExport(pageParam));
        List<PtlPositionAnalysis001Dto> listRoot = pageRoot.getRoot();
        for (PtlPositionAnalysis001Dto entity : listRoot) {
            if (StringUtil.equals(entity.getParentInfo(), "root")) {
                resultList.add(entity);
                BigDecimal sum1 = BigDecimal.ZERO;
                pageParam.put("parentId", entity.getChildInfo());
                if (StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"), "root")) {
                    List<PtlPositionAnalysis001Dto> resultList1 = ptlPositionAnalysisMapper.select(pageParam);
                    if(!StringUtil.isEmptyList(resultList1)){
                        for(PtlPositionAnalysis001Dto dto:resultList1){
                            if(dto.getMarketValue()!=null){
                                sum1 = sum1.add(dto.getMarketValue());
                            }
                            if(dto.getTdyIntincexp()!=null){
                                sum1= sum1.add(dto.getTdyIntincexp());
                            }
                        }
                    }
                }
                List<PtlPositionAnalysis001Dto> listRoot2 = ptlPositionAnalysisMapper.selectForExport(pageParam);
                for (PtlPositionAnalysis001Dto entity2 : listRoot2) {
                    if (StringUtil.equals(entity2.getParentInfo(), entity.getChildInfo())) {
                       countSumAndCalPercentDto(entity2,sum1);
                        resultList.add(entity2);
                        String par=entity2.getChildInfo();
                        BigDecimal sum2 = BigDecimal.ZERO;
                        pageParam.put("parentId",par);
                        List<PtlPositionAnalysis001Dto> resultList2 = ptlPositionAnalysisMapper.select(pageParam);
                        if(!StringUtil.isEmptyList(resultList2)){
                            for(PtlPositionAnalysis001Dto dto1:resultList2){
                                if(dto1.getMarketValue()!=null){
                                    sum2 = sum2.add(dto1.getMarketValue());
                                }
                                if(dto1.getTdyIntincexp()!=null){
                                    sum2= sum2.add(dto1.getTdyIntincexp());
                                }
                            }
                        }
                        for (PtlPositionAnalysis001Dto entity3 : listRoot2) {
                            if (StringUtil.equals(entity2.getChildInfo(), entity3.getParentInfo())) {
                                countSumAndCalPercentDto(entity3,sum2);
                                resultList.add(entity3);
                            }
                        }
                    }
                }
            }
        }
        pageRoot.setRoot(resultList);
        return pageRoot;
    }

    //add by xueying 修改导出数据与查询数据不一致问题 20200731
    private void countSumAndCalPercentDto(PtlPositionAnalysis001Dto dto,BigDecimal sum) {
            //总额不能为0
            if(BigDecimal.ZERO.compareTo(sum)!=0){
                    BigDecimal marketAndTdyIntince = BigDecimal.ZERO;
                    if(dto.getMarketValue()!=null){
                        marketAndTdyIntince = marketAndTdyIntince.add(dto.getMarketValue());
                    }
                    if(dto.getTdyIntincexp()!=null){
                        marketAndTdyIntince = marketAndTdyIntince.add(dto.getTdyIntincexp());
                    }
                    dto.setTdyAmtRation(ArithmeticUtil.round(marketAndTdyIntince.multiply(new BigDecimal(100)).divide(sum,6, BigDecimal.ROUND_HALF_UP),2
                            , RoundingMode.HALF_UP));
                    //较上一日变化
                    dto.setYstAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(dto.getTdyAmtRation(),dto.getYstAmtRationTemp()),2,RoundingMode.HALF_UP));
                    //较本月初变化
                    dto.setMonthAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(dto.getTdyAmtRation(),dto.getMonthAmtRationTemp()),2,RoundingMode.HALF_UP));
                    //较本年初变化
                    dto.setYearAmtRation(ArithmeticUtil.round(ArithmeticUtil.doSub(dto.getTdyAmtRation(),dto.getYearAmtRationTemp()),2,RoundingMode.HALF_UP));
            }
        }

    /**
     * 获取动态列-》组合持仓分析报表
     *
     * @param param 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<TargetDetail> getDataTableCols(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", param);
        map.put("isDefault", DictCodeList.是否_是.getValue());
        return ptlPositionAnalysisMapper.getDataTableCols(map);
    }
    // 获取默认显示列
    public List<TargetDetail> getDefaultTableCols(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("isDefault", DictCodeList.是否_是.getValue());
        return ptlPositionAnalysisMapper.getDefaultTableCols(map);
    }

    /**
     * 指标设置树结构数据-》组合持仓分析报表
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<TargetDetail> getAllTreeNode(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", param);
        map.put("isDefault", DictCodeList.是否_是.getValue());
        return ptlPositionAnalysisMapper.getAllTreeNode(map);
    }

    /**
     * 根据groupby 条件获取对象下的分类
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<PtlPositionAnalysis003Dto> getFinpordType(Map<String, Object> pageParam) {
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        return ptlPositionAnalysisMapper.getFinpordType(pageParam);
    }


    public List<TargetDetail> getTableColumn() {
        return ptlPositionAnalysisMapper.getTableColumn();

    }
}

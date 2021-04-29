/*
 * 文件名：FinMdateAdjustment002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.api.constant.BussinessJixuConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductOpenPlanService;
import com.joyintech.tams.component.business.prod.BussFinProductPayPlanService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductOpenPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.component.business.report.prod.BussRepFinProductInfoService;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustment001Dto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticPlanDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.service.FinProductAnapleroticInfo002Service;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.tams.valuation.api.service.ValInterfCommonServiceApi;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品到期日新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年11月5日
 * @see FinMdateAdjustment002Service
 * @since 1.0
 */
@Service
public class FinMdateAdjustment002Service extends BaseService<FinMdateAdjustment001Dto> implements BaseProcessService<FinMdateAdjustment001Dto> {

    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    /*guyunfei 20191111 金融产品扩展表字段迁移 start*/
//    @Autowired
//    private PaFinProductMapper paFinProductMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    /*guyunfei 20191111 金融产品扩展表字段迁移 end*/

    @Autowired
    private PrdMdateAdjustMapper prdMdateAdjustMapper;

    @Autowired
    private RepProdDeclareMapper repProdDeclareMapper;

    @Autowired
    private RepProdIssueMapper repProdIssueMapper;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    /**
     * 开放计划组件
     */
    @Autowired
    private BussFinProductOpenPlanService bussFinProductOpenPlanService;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild002Service finProductBuild002Service;

    /**
     * 付息计划组件
     */
    @Autowired(required = false)
    private BussFinProductPayPlanService bussFinProductPayPlanService;
    
    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFeeInfoService bussFinProductFeeInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private ValInterfCommonServiceApi valInterfCommonServiceApi;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussRepFinProductInfoService bussRepFinProductInfoService;
    
    @Autowired
    private FinProductAnapleroticInfo002Service finProductAnapleroticInfo002Service;

    /**
     * 产品到期日新建管理产品代码下拉框查询
     * 
     * @param param 入参
     * @return 产品代码下拉框查询
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAdjustProdIdList(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryAdjustProdIdList",
            param, SelectOptionDto.class);
    }

    /**
     * 根据金融产品代码查询关联信息
     * 
     * @param finprodId 金融产品代码
     */
    public List<FinMdateAdjustment001Dto> queryRelateInfoList(String finprodId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryRelateInfoList?finprodId=" + finprodId,
            FinMdateAdjustment001Dto.class);
    }

    /**
     * 根据金融产品代码查询到期日调整信息
     * 
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     * @see
     * @since 1.0
     */
    public FinMdateAdjustment001Dto queryAdjustInfoByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryAdjustInfoByFinprodId?finprodId=" + finprodId,
            FinMdateAdjustment001Dto.class);
    }

    /**
     * 根据金融产品代码和调整批次查询到期日调整信息
     * 
     * @param finprodId 金融产品代码
     * @param adjustNum 调整批次
     * @return 到期日调整信息
     * @see
     * @since 1.0
     */
    public FinMdateAdjustment001Dto queryAdjustInfoByPrimaryKey(String finprodId,
                                                                String adjustNum) {
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        /*guyunfei 20191111 金融产品扩展表字段迁移 start*/
        //PaFinProduct paFinProduct = paFinProductMapper.selectByPrimaryKey(finprodId);
        FinProductAdd paFinProduct = finProductAddMapper.selectByPrimaryKey(finprodId);
        /*guyunfei 20191111 金融产品扩展表字段迁移 end*/
        
        PrdMdateAdjustKey key = new PrdMdateAdjustKey();
        key.setFinprodId(finprodId);
        key.setAdjustNum(Long.parseLong(adjustNum));
        PrdMdateAdjust prdMdateAdjust = prdMdateAdjustMapper.selectByPrimaryKey(key);
        FinMdateAdjustment001Dto finMdateAdjustment001Dto = new FinMdateAdjustment001Dto();
        if (prdMdateAdjust != null) {
            try {
                BeanUtil.copyProperties(finMdateAdjustment001Dto, prdMdateAdjust);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            finMdateAdjustment001Dto.setFinprodAbbr(
//                    finProduct != null ? finProduct.getFinprodAbbr() : null);
            if(finProduct != null){
	            finMdateAdjustment001Dto.setFinprodAbbr(finProduct.getFinprodAbbr());
	            finMdateAdjustment001Dto.setProfitType(finProduct.getProfitType());
	            finMdateAdjustment001Dto.setVdate(finProduct.getVdate());
	            finMdateAdjustment001Dto.setFinprodMarketId(finProduct.getFinprodMarketId());
            }
            
            if(paFinProduct != null){
                finMdateAdjustment001Dto.setDealMode(paFinProduct.getDealMode());
            }

            return finMdateAdjustment001Dto;
        }
        return null;
    }

    /**
     * 根据金融产品代码和调整批次查询到期日调整信息
     * 
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     * @see
     * @since 1.0
     */
    public FinMdateAdjustment001Dto queryFinProductByPrimaryKey(String finprodId) {
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        /*guyunfei 20191111 金融产品扩展表字段迁移 start*/
        //PaFinProduct paFinProduct = paFinProductMapper.selectByPrimaryKey(finprodId);
        FinProductAdd paFinProduct = finProductAddMapper.selectByPrimaryKey(finprodId);
        /*guyunfei 20191111 金融产品扩展表字段迁移 end*/
        if (finProduct != null) {
            FinMdateAdjustment001Dto finMdateAdjustment001Dto = new FinMdateAdjustment001Dto();
            finMdateAdjustment001Dto.setFinprodId(finProduct.getFinprodId());
            finMdateAdjustment001Dto.setFinprodAbbr(finProduct.getFinprodAbbr());
            finMdateAdjustment001Dto.setFinprodMarketId(finProduct.getFinprodMarketId());
            finMdateAdjustment001Dto.setMdateUnadjust(finProduct.getMdate());
            finMdateAdjustment001Dto.setLdateUnadjust(finProduct.getLiquidationDate());
            finMdateAdjustment001Dto.setProfitType(finProduct.getProfitType());
            if(paFinProduct!=null){
            	finMdateAdjustment001Dto.setDealMode(paFinProduct.getDealMode());
            }
            finMdateAdjustment001Dto.setVdate(finProduct.getVdate());
            return finMdateAdjustment001Dto;
        }

        return null;
    }

    /**
     * 查询组合到期日
     * 
     * @param finprodId 金融产品代码
     * @return 组合到期日
     * @see
     * @since 1.0
     */
    public String queryPortFolioMdateByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryPortFolioMdateByFinprodId?finprodId=" + finprodId,
            String.class);
    }

    /**
     * 查询是否有后续交易
     * 
     * @param finprodId 金融产品代码
     * @param mdate 调整后到期日
     * @return 交易个数
     * @see
     * @since 1.0
     */
    public int queryDealCountByFinprodId(String finprodId, String mdate) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryDealCountByFinprodId?finprodId=" + finprodId
                                         + "&mdate=" + mdate,
            Integer.class);
    }

    /**
     * 母产品代码查询分层子产品信息 <功能详细描述>
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProduct> queryLayeredChildProductByProdid(String finprodId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinMdateAdjustment/queryLayeredChildProductByProdid?finprodId=" + finprodId,
            FinProduct.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void doComplete(FinMdateAdjustment001Dto entity, OperationType type) {
        /*
         * List<PrdMdateAdjust> prdMdateAdjust=entity.getSelectedItem();
         *
         * for(PrdMdateAdjust dto:prdMdateAdjust){ // 登录费用品种信息 bussCommonMapperDelegateService.updateByPrimaryKey(MstFeeinfoMapper.class, dto); }
         */
        // 新建
        if (StringUtil.equals(type.toString(), "NEW")) {
            if (StringUtil.isNotEmpty(entity.getAdjustNum())) {
                entity.setAdjustNum(entity.getAdjustNum() + 1);
            }
            else {
                entity.setAdjustNum(new Long(1));
            }
        	// 登录产品到期日调整信息
            prdMdateAdjustMapper.insert(entity);
        }else{
        	// 登录产品到期日调整信息
            prdMdateAdjustMapper.updateByPrimaryKeySelective(entity);
        }
        
        // 更新账户维护费计提结束日 qz begin
        Map<String,Object> feeMap = new HashMap<>();
        feeMap.put("booksetId", entity.getFinprodId());
        feeMap.put("mDate", DateUtil.format(DateUtil.addDay(entity.getMdate(), -1)));
        restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/updateRecord", feeMap,
				Integer.class);
        // 更新账户维护费计提结束日 qz end

        // 4.2 到期日调整后，自动更新产品信息：产品到期日、产品期限（天）、期限品种。
        // 更新项目：
        // 到期日：画面.调整后到期日
        // 实际到期日：画面.调整后到期日
        // 产品期限：画面.调整后到期日 - 隐藏.起息日([产品起息日]非空时，否则为空）
        // 更新条件：
        // 金融产品代码：隐藏.金融产品代码
        FinProductBuild002Dto finProductBuild002Dto = finProductBuild002Service.queryProdInfoByFinprodId(entity.getFinprodId());
        FinProduct record = new FinProduct();
        record.setMdate(entity.getMdate());
        record.setActmdate(entity.getMdate());
        record.setLiquidationDate(entity.getLdate());
        record.setTermDays(DateUtil.getBetweenDayNumber(finProductBuild002Dto.getVdate(), entity.getMdate()));
        record.setFinprodId(entity.getFinprodId());
        //到期日调整时，更新update_time
        record.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getAdjustType(), DictCodeListExt2.调整类型_提前终止.getValue())
            && StringUtil.equals(finProductBuild002Dto.getIsSus(), DictCodeList.是否_是.getValue())) {
            record.setIsSus(DictCodeList.是否_否.getValue());
        }
        finProductMapper.updateByPrimaryKeySelective(record);
        // 判断是否分层
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), finProductBuild002Dto.getIsLay())) {
            List<FinProduct> layeredFinProductlist = this.queryLayeredChildProductByProdid(entity.getFinprodId());
            // 更新分层子产品信息：产品到期日、产品期限（天）、期限品种。
            layeredFinProductlist.forEach(layeredFinProduct -> {
                record.setFinprodId(layeredFinProduct.getFinprodId());
                finProductMapper.updateByPrimaryKeySelective(record);

                //add by jianxx 调整产品到期日，同步调整批次信息中到期日 start
                List<FinTradeProduct> finTradeProducts = finTradeProductMapper.selectByFinprodId(layeredFinProduct.getFinprodId());
                for (FinTradeProduct finTradeProduct : finTradeProducts) {
                    finTradeProduct.setMdate(record.getMdate());
                    if(finTradeProduct!=null){
                        finTradeProductMapper.updateByPrimaryKeySelective(finTradeProduct);
                    }
                }
                //add by jianxx 调整产品到期日，同步调整批次信息中到期日 end
            });
        }
        // add by zlp 20190318 start 到期日调整同步修改组合到期日
        PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(finProductBuild002Dto.getPtlId());
        // 组合表插入
        if (ptlPortfolio != null) {
            ptlPortfolioMapper.deleteByPrimaryKey(finProductBuild002Dto.getPtlId());
            // 更新最新的到期日到组合表
            ptlPortfolio.setMdate(entity.getMdate());
            ptlPortfolioMapper.insert(ptlPortfolio);
        }
        // add by zlp 20190318 end
        // 根据产品代码查找旧的计提计划和计提规则（计提结束日发生改变，计提规则和计提计划也会发生改变）
        /*
         * FinFloatCountingRuleKey countKey = new FinFloatCountingRuleKey(); for (FinMdateAdjustment001Dto finMdateAdjustDto : finMdateAdjustment001Dto) {
         * countKey.setCashId(finMdateAdjustDto.getCashId()); countKey.setEffDate(finProductBuild002Dto.getVdate());//产品起息日 FinFloatCountingRule countingRule=
         * bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinFloatCountingRuleMapper.class, countKey,FinFloatCountingRule.class);
         * countingRule.seteAccrDate(finMdateAdjustDto.geteAccrDate());//从页面获取更改过的计提结束日
         * bussCommonMapperDelegateService.updateByPrimaryKeySelective(FinFloatCountingRuleMapper.class,countingRule); }
         */

        // 产品申报报送信息 产品发行报送信息
        // 更新项目： 更新项目：
        // 产品期限 画面.调整后到期日 - 隐藏.起息日([产品起息日]非空时，否则为空） 产品终止日期 画面.调整后到期日
        // ->按照上面获取的值和数据字典之间做换算 更新人 从用户缓存中取得用户名
        // 更新人 从用户缓存中取得用户名 更新时间 系统时间
        // 更新时间 系统时间 更新条件：
        // 更新条件： 金融产品代码：隐藏.金融产品代码
        // 金融产品代码：隐藏.金融产品代码
        RepProdDeclare repProdDeclare = new RepProdDeclare();
        BussRepFinProductInfoDto bussRepFinProductInfoDto = new BussRepFinProductInfoDto();
        bussRepFinProductInfoDto.setOperationType(finProductBuild002Dto.getOperationType());
        bussRepFinProductInfoDto.setProdVdate(finProductBuild002Dto.getVdate());
        bussRepFinProductInfoDto.setProdMdate(finProductBuild002Dto.getMdate());
        // 查询开放计划信息
        List<BussFinProductOpenPlanDto> bussFinProductOpenInfoDtoList = bussFinProductOpenPlanService.queryOpenPlanInfoByFinprodId(
            entity.getFinprodId());// add by hujinin
        if (!StringUtil.isEmptyList(bussFinProductOpenInfoDtoList)) {
            BussFinProductOpenPlanDto bussFinProductOpenInfoDto = bussFinProductOpenInfoDtoList.get(0);// 申赎不同频 add zhangwiqi
            bussRepFinProductInfoDto.setOpenMode(bussFinProductOpenInfoDto.getOpenMode());
            bussRepFinProductInfoDto.setOpenCycle(
                bussFinProductOpenInfoDto.getOpenCycle() != null ? bussFinProductOpenInfoDto.getOpenCycle().toString() : null);
            bussRepFinProductInfoDto.setOpenCycleUnit(bussFinProductOpenInfoDto.getOpenCycleUnit());
        }
        repProdDeclare.setRepTerm(bussRepFinProductInfoService.calcProductTermInfo(bussRepFinProductInfoDto));
        repProdDeclare.setRepFinprodId(entity.getFinprodId());
        repProdDeclareMapper.updateByPrimaryKeySelective(repProdDeclare);

        RepProdIssue repProdIssue = new RepProdIssue();
        repProdIssue.setRepPrdMdate(entity.getMdate());
        repProdIssue.setRepFinprodId(entity.getFinprodId());
        repProdIssueMapper.updateByPrimaryKeySelective(repProdIssue);

        // 根据新的产品到期日和开放规则生成新的开放计划；
        List<FinOpenPlan> openPlanList = new ArrayList<FinOpenPlan>();
        // 历史开放计划
        List<FinOpenPlan> openPlanList_old = bussFinProductOpenPlanService.queryOpenPlanListByFinprodId(entity.getFinprodId());
        
        /** 将开放计划和开放规则分组,申赎不同频时申购开放计划和赎回计划分开生成**/
        if (!StringUtil.isEmptyList(openPlanList_old) && !StringUtil.isEmptyList(bussFinProductOpenInfoDtoList)) {
            Map<String,List<FinOpenPlan>> openPlanMap = openPlanList_old.stream().collect(
                JoyinCollectors.groupingBy(x -> x.getCashId() + GlobalConstant.UNDER_LINE + x.getOpenType()));
            
            Map<String,List<BussFinProductOpenPlanDto>> openRuleMap = bussFinProductOpenInfoDtoList.stream().collect(
                JoyinCollectors.groupingBy(x -> x.getCashId() + GlobalConstant.UNDER_LINE + x.getOpenType()));
            
            for(String key:openPlanMap.keySet()) {
                List<FinOpenPlan> finOpenPlanList = openPlanMap.get(key);
                List<BussFinProductOpenPlanDto> finFinOpenRuleList  = openRuleMap.get(key);
                if(!StringUtil.isEmptyList(finOpenPlanList)&&!StringUtil.isEmptyList(finFinOpenRuleList)) {
                    BussFinProductOpenPlanDto bussFinProductOpenInfoDto = finFinOpenRuleList.get(0); 
                    // update by leipeng 开放式产品有首次开放日，提前终止日小于
                    // 首次开放日，结果开放计划不变。预期只会生成到终止日的开放计划
                    if (StringUtil.isNotEmpty(bussFinProductOpenInfoDto.getFirstOpenDate())) {
                        if (entity.getMdate().compareTo(DateUtil.addDay(bussFinProductOpenInfoDto.getFirstOpenDate(), 1)) < 0) {
                            bussFinProductOpenInfoDto.setFirstOpenDate(entity.getMdate());
                        }
                    }

                    if (entity.getMdate().compareTo(finProductBuild002Dto.getMdate()) < 0) {
                        // 向前调整
                        for (FinOpenPlan finOpenPlan : finOpenPlanList) {
                            if (finOpenPlan.getOpenMdate().compareTo(entity.getMdate()) <= 0) {
                                openPlanList.add(finOpenPlan);
                            }
                        }
                    }
                    else {
                        // 向后调整
                        Date vdate = finProductBuild002Dto.getVdate();
                        openPlanList.addAll(finOpenPlanList);
                        // 获取最后一条计划的起息日
                        vdate = finOpenPlanList.get(finOpenPlanList.size() - 1).getOpenVdate();
                        bussFinProductOpenInfoDto.setFinprodId(entity.getFinprodId());
                        bussFinProductOpenInfoDto.setDealMode(finProductBuild002Dto.getDealMode());
                        bussFinProductOpenInfoDto.setIsCycle(finProductBuild002Dto.getIsCycle());
                        bussFinProductOpenInfoDto.setCloseTerm(finProductBuild002Dto.getCloseDays());
                        bussFinProductOpenInfoDto.setVdate(finProductBuild002Dto.getVdate());
                        bussFinProductOpenInfoDto.setMdate(entity.getMdate());
                        bussFinProductOpenInfoDto.setTradCalender(finProductBuild002Dto.getCalendarId());
                        bussFinProductOpenInfoDto.setRaiseStrDate(finProductBuild002Dto.getFirstSaleVdate());
                        bussFinProductOpenInfoDto.setRaiseEndDate(finProductBuild002Dto.getFirstSaleMdate());
                        bussFinProductOpenInfoDto.setPurConfDays(finProductBuild002Dto.getPurSpeed());

                        List<FinOpenPlan> openPlanList_new = bussFinProductOpenPlanService.genOpenPlanList(bussFinProductOpenInfoDto);// 新生产的开放计划
                        //add by wm周期型产品开放计划申购计划，故起类型默认为申购开放 start 2019-10-17
                        String openTypeTemp = "";
                        if(StringUtil.equals(finProductBuild002Dto.getDealMode(), DictCodeList.处理模式_周期型.getValue())){
                            openTypeTemp = DictCodeList.申赎开放类型_申购开放.getValue();
                        }
                        else{
                            openTypeTemp = bussFinProductOpenInfoDto.getOpenType();
                        }
                        //add by wm周期型产品开放计划申购计划，故起类型默认为申购开放 end 2019-11-07
                        for (FinOpenPlan finOpenPlan : openPlanList_new) {
                            if (finOpenPlan.getOpenVdate().compareTo(vdate) > 0) {
                                finOpenPlan.setCashId(bussFinProductOpenInfoDto.getCashId());
                                //modify by wm周期型产品开放计划申购计划，故起类型默认为申购开放 start 2019-10-17
                                //finOpenPlan.setOpenType(DictCodeList.申赎开放类型_申赎开放.getValue());
                                finOpenPlan.setOpenType(openTypeTemp);
                                //modify by wm周期型产品开放计划申购计划，故起类型默认为申购开放 end 2019-10-17
                                finOpenPlan.setOpenDateStrUn(finOpenPlan.getOpenDateStr());
                                finOpenPlan.setOpenDateEndUn(finOpenPlan.getOpenDateEnd());
                                finOpenPlan.setOpenVdateUn(finOpenPlan.getOpenVdate());
                                finOpenPlan.setOpenMdateUn(finOpenPlan.getOpenMdate());
                                finOpenPlan.setFinprodId(entity.getFinprodId());
                                finOpenPlan.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
                                finOpenPlan.setOpenStatus(DictCodeList.开放状态_开放.getValue());
                                openPlanList.add(finOpenPlan);
                            }
                        }
                    }
                }
            }
            
            BussFinProductOpenPlanDto bussFinProductOpenPlanDto = new BussFinProductOpenPlanDto();
            bussFinProductOpenPlanDto.setFinOpenPlanList(openPlanList);
            finProductBuild002Dto.setBussFinProductOpenPlanDto(bussFinProductOpenPlanDto);
        }
        
        // 根据新的产品到期日和付息规则生成新的付息计划
        List<FinCashPlan> payPlanList = new ArrayList<FinCashPlan>();
        List<FinCashPlan> payPlanList_old = bussFinProductPayPlanService.queryPayPlanListByFinprodId(entity.getFinprodId());

        if (!StringUtil.isEmptyList(payPlanList_old)) {
            BussFinProductPayPlanDto bussFinProductPayPlanInfoDto = bussFinProductPayPlanService.queryPayPlanInfoByFinprodId(
                entity.getFinprodId(), DateUtil.format(finProductBuild002Dto.getVdate()));
            // 向前调整
            if (entity.getMdate().compareTo(finProductBuild002Dto.getMdate()) < 0) {
                boolean mdateFlag = true;
                for (FinCashPlan finCashPlan : payPlanList_old) {
                    if (finCashPlan.getMdate().compareTo(entity.getMdate()) <= 0) {
                        payPlanList.add(finCashPlan);

                        if (finCashPlan.getMdate().compareTo(entity.getMdate()) == 0) {
                            mdateFlag = false;
                        }
                    }
                    else if (mdateFlag) {// 大于画面到期日，且mdateFlag为true.
                        finCashPlan.setMdate(entity.getMdate());
                        finCashPlan.setMdateUnadjust(entity.getMdate());

                        // 交易日历取得
                        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(entity.getMdate(),
                            DateUtil.addDay(entity.getMdate(), 8), finProductBuild002Dto.getCalendarId());

                        Date payDate = DateUtil.parse(AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(entity.getMdate()),
                            bussFinProductPayPlanInfoDto.getPayTdayRule(), dealCalendarHolidayList));

                        finCashPlan.setPayDate(payDate);
                        finCashPlan.setPayDateUnadjust(payDate);
                        finCashPlan.setUpdateUser(LoginUserUtil.getUserName());
                        finCashPlan.setUpdateTime(new Date());
                        payPlanList.add(finCashPlan);
                        break;
                    }
                }
            }
            // 向后调整
            else {
                payPlanList.addAll(payPlanList_old);
                // 获取最后一条计划的起息日
                Date mdate = payPlanList_old.get(payPlanList_old.size() - 1).getMdate();
                Long cashNum = payPlanList_old.get(payPlanList_old.size() - 1).getCashNum();
                bussFinProductPayPlanInfoDto.setVdate(finProductBuild002Dto.getVdate());
                bussFinProductPayPlanInfoDto.setMdate(entity.getMdate());
                bussFinProductPayPlanInfoDto.setWinupDate(entity.getMdate());
                bussFinProductPayPlanInfoDto.setCalendarId(finProductBuild002Dto.getCalendarId());
                List<FinCashPlan> cashPlanList_pay = new ArrayList<>();
                //货币型产品分红规则为申购同频时，则取开放计划 modiby by wm 2020-01-13 start
                if(StringUtil.equals(DictCodeList.分红规则_申购同频.getValue(), bussFinProductPayPlanInfoDto.getPayCycle()) && 
                		StringUtil.isNotEmpty(finProductBuild002Dto.getBussFinProductOpenPlanDto())){
                    for (FinOpenPlan finOpenPlan : finProductBuild002Dto.getBussFinProductOpenPlanDto().getFinOpenPlanList()) {
                    	FinCashPlan finCashPlan = new FinCashPlan();
                    	finCashPlan.setVdate(finOpenPlan.getOpenDateStr());
                        finCashPlan.setMdate(finOpenPlan.getOpenDateEnd());
                        finCashPlan.setPayDate(finOpenPlan.getOpenVdate());
                        finCashPlan.setMdateUnadjust(finOpenPlan.getOpenDateEnd());
                        cashPlanList_pay.add(finCashPlan);
                    }
                }
                else{
                    cashPlanList_pay = bussFinProductPayPlanService.getProductInterestPlanData(
                            bussFinProductPayPlanInfoDto);// 新生产的付息计划
                }
                //货币型产品分红规则为申购同频时，则取开放计划 modiby by wm 2020-01-13 end
                boolean tempFlag = true;
                for (FinCashPlan finCashPlan : cashPlanList_pay) {
                    if (finCashPlan.getMdate().compareTo(mdate) > 0) {
                        finCashPlan.setCashId(bussFinProductPayPlanInfoDto.getCashId());
                        finCashPlan.setCashNum(cashNum + 1);
                        finCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                        if (tempFlag) {
                            finCashPlan.setVdate(mdate);
                            finCashPlan.setVdateUnadjust(mdate);
                            tempFlag = false;
                        }
                        else {
                            finCashPlan.setVdateUnadjust(finCashPlan.getVdate());
                        }
                        finCashPlan.setMdateUnadjust(finCashPlan.getMdate());
                        finCashPlan.setPayDateUnadjust(finCashPlan.getPayDate());
                        finCashPlan.setFinprodId(entity.getFinprodId());
                        finCashPlan.setFinprodType("");
                        finCashPlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                        finCashPlan.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
                        payPlanList.add(finCashPlan);

                        cashNum++ ;
                    }
                }
            }
            BussFinProductPayPlanDto bussFinProductPayPlanDto = new BussFinProductPayPlanDto();
            bussFinProductPayPlanDto.setCashPlanList(payPlanList);
            finProductBuild002Dto.setBussFinProductPayPlanDto(bussFinProductPayPlanDto);
        }

        // 根据新的产品到期日和付费规则生成新的付费计划。
        BussFinProductFeeInfoDto bussFinProductFeeInfoDto = new BussFinProductFeeInfoDto();
        List<BussFinProductFeeInfoDto> feeInfoList = bussFinProductFeeInfoService.queryFeeInfoByFinprodIdEx(
            entity.getFinprodId());

        for (BussFinProductFeeInfoDto feeInfo : feeInfoList) {
            List<FinCashPlan> feePlanList = new ArrayList<FinCashPlan>();
            List<FinCashPlan> feePlanList_old = bussFinProductFeeInfoService.queryFeePlanListByFinprodId(feeInfo.getFinprodId(),
                feeInfo.getFeeId());

            if (!StringUtil.isEmptyList(feePlanList_old)) {
                // 向前调整
                if (entity.getMdate().compareTo(finProductBuild002Dto.getMdate()) < 0) {
                    boolean mdateFlag = true;
                    for (FinCashPlan finCashPlan : feePlanList_old) {
                        if (finCashPlan.getMdate().compareTo(entity.getMdate()) <= 0) {
                            feePlanList.add(finCashPlan);

                            if (finCashPlan.getMdate().compareTo(entity.getMdate()) == 0) {
                                mdateFlag = false;
                            }
                        }
                        else if (mdateFlag) {
                            finCashPlan.setMdate(entity.getMdate());
                            finCashPlan.setMdateUnadjust(entity.getMdate());

                            // 交易日历取得
                            List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(entity.getMdate(),
                                DateUtil.addDay(entity.getMdate(), 8), finProductBuild002Dto.getCalendarId());
                            // 支付日根据营业日准则进行调整
                            Date tradePaymentDay = DateUtil.parse(AdjustedTradeDayCal.getAdjustedTradeDay(
                                DateUtil.format(entity.getMdate()), feeInfo.getPayFeeTradeRule(), dealCalendarHolidayList));

                            finCashPlan.setPayDate(tradePaymentDay);
                            finCashPlan.setPayDateUnadjust(tradePaymentDay);
                            finCashPlan.setUpdateUser(LoginUserUtil.getUserName());
                            finCashPlan.setUpdateTime(new Date());
                            feePlanList.add(finCashPlan);
                            break;
                        }
                    }
                }
                // 向后调整
                else {
                    feePlanList.addAll(feePlanList_old);
                    // 获取最后一条计划的起息日
                    Date mdate = feePlanList_old.get(feePlanList_old.size() - 1).getMdate();
                    Long cashNum = feePlanList_old.get(feePlanList_old.size() - 1).getCashNum();
                    feeInfo.setDealCalId(finProductBuild002Dto.getCalendarId());
                    feeInfo.setVdate(finProductBuild002Dto.getVdate());
                    feeInfo.setMdate(entity.getMdate());
                    feeInfo.setProfitType(finProductBuild002Dto.getProfitType());
                    //将计提结束日默认修改为到期日-1，否则第一次展期付费计划不修改
                    feeInfo.seteAccrDate(DateUtil.addDay(entity.getMdate(), -1));
                    List<FinCashPlan> feePlanList_new = bussFinProductFeeInfoService.generatePayFeePlan(feeInfo);// 新生产的付费计划
                    boolean tempFlag = true;
                    for (FinCashPlan finCashPlan : feePlanList_new) {
                        if (finCashPlan.getMdate().compareTo(mdate) > 0) {
                            finCashPlan.setCashId(feeInfo.getCashId());
                            finCashPlan.setCashNum(cashNum + 1);
                            finCashPlan.setCashType(DictCodeList.现金流类型_费用支付.getValue());
                            if (tempFlag) {
                                finCashPlan.setVdate(mdate);
                                finCashPlan.setVdateUnadjust(mdate);

                                tempFlag = false;
                            }
                            else {
                                finCashPlan.setVdateUnadjust(finCashPlan.getVdate());
                            }
                            finCashPlan.setMdateUnadjust(finCashPlan.getMdate());
                            finCashPlan.setPayDateUnadjust(finCashPlan.getPayDate());
                            //当费用挂在分档子产品上时，产品代码为分档子产品代码
                            if(StringUtil.isNotEmpty(feeInfo.getFinprodId())
                            		&&!StringUtil.equals(feeInfo.getFinprodId(), entity.getFinprodId())){
                            	finCashPlan.setFinprodId(feeInfo.getFinprodId());
                            }
                            else {
                                finCashPlan.setFinprodId(entity.getFinprodId());
                            }
                            finCashPlan.setFinprodType("");
                            finCashPlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                            finCashPlan.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
                            feePlanList.add(finCashPlan);

                            cashNum++ ;
                        }
                    }
                }
                feeInfo.setFinCashPlanList(feePlanList);
            }
        }

        bussFinProductFeeInfoDto.setBussFinProductFeeInfoDtoList(feeInfoList);
        finProductBuild002Dto.setBussFinProductFeeInfoDto(bussFinProductFeeInfoDto);
      //合版本业绩回补相关 start wangcheng 20191028
      //根据新的产品到期日和回补规则生成新的回补计划。
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("portfolioId", entity.getFinprodId());
        
        List<FinProductAnapleroticInfoDto> anapleroticInfoList  =  restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/selectForlist", map,
                FinProductAnapleroticInfoDto.class);
                 
 	       for (FinProductAnapleroticInfoDto finProductAnapleroticInfoDto : anapleroticInfoList) {
 	    	   map.put("anapleroticId", finProductAnapleroticInfoDto.getAnapleroticId());
 	    	   List<FinProductAnapleroticPlanDto> anapleroticPlan = new ArrayList<FinProductAnapleroticPlanDto>();
 	    	   List<FinProductAnapleroticPlanDto> anapleroticPlan_old =   restTemplate.getForList(
 	    			  ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/selectAnapleroticPlan?anapleroticId=" + finProductAnapleroticInfoDto.getAnapleroticId(), FinProductAnapleroticPlanDto.class);
			if (!StringUtil.isEmptyList(anapleroticPlan_old)) {
				// yzg 回补计划生成 start 20200103
				if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(), finProductBuild002Dto.getOperationType())) {
					if (!StringUtil.equals(DictCodeList.开放模式_高频开放.getValue(),
							finProductAnapleroticInfoDto.getOpenMode())) {
						List<Date> busiDate = new ArrayList<Date>();
						BussFinProductOpenPlanDto bussFinProductOpenPlanDto = finProductBuild002Dto
								.getBussFinProductOpenPlanDto();
						if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
								finProductBuild002Dto.getProfitType())) {
							if (bussFinProductOpenPlanDto != null
									&& !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())) {
								for (FinOpenPlan plan : bussFinProductOpenPlanDto.getFinOpenPlanList()) {
									addDateToList(busiDate, plan.getOpenNdate());
								}
							}
						} else if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
								finProductBuild002Dto.getProfitType())) {
							if (bussFinProductOpenPlanDto != null
									&& !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())) {
								for (FinOpenPlan plan : bussFinProductOpenPlanDto.getFinOpenPlanList()) {
									addDateToList(busiDate, plan.getOpenDateEnd());
								}
							}
						}
						Date fVdate = anapleroticPlan_old.get(0).getAnapleroticCycleStartDate();
						Date anapleroticDate = anapleroticPlan_old.get(0).getAnapleroticDate();
						//addDateToList(busiDate, entity.getMdate());
						addDateToList(busiDate, anapleroticDate);
						//addDateToList(busiDate, entity.getMdate());
						addDateToList(busiDate, fVdate);
						Date startDate = busiDate.get(0);
						for (Date endDate : busiDate) {
							if (startDate.compareTo(endDate) == 0) {
								startDate = endDate;
								continue;
							}
							if (endDate.compareTo(anapleroticDate) < 0) {
								startDate = endDate;
								continue;
							}
							if (endDate.compareTo(entity.getMdate()) > 0) {
								startDate = endDate;
								continue;
							}
							FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = new FinProductAnapleroticPlanDto();
							// finProductAnapleroticPlanDto.setCashId(cashId_floatFee);
							finProductAnapleroticPlanDto.setAnapleroticDate(endDate);
							// 现金流计提计划.计提开始日 = 画面回补计划列表.回补周期开始日
							finProductAnapleroticPlanDto.setAnapleroticCycleStartDate(startDate);
							// 现金流计提计划.计提结束日 = 画面回补计划列表.回补周期结束日
							finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(endDate);
							// 现金流计提计划.调整前结束日 = 画面回补计划列表.回补周期结束日
							finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(endDate);
							finProductAnapleroticPlanDto
									.setCashId(anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getCashId());
							finProductAnapleroticPlanDto
									.setValDate(finProductAnapleroticPlanDto.getAnapleroticCycleEndDate());
							anapleroticPlan.add(finProductAnapleroticPlanDto);
							startDate = endDate;
						}
					}
				} else {
					if(entity.getMdate().compareTo(finProductBuild002Dto.getMdate()) > 0) {
						//展期增加一条回补计划
						anapleroticPlan.addAll(anapleroticPlan_old);
						FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = new FinProductAnapleroticPlanDto();
						//回补日
						finProductAnapleroticPlanDto.setAnapleroticDate(entity.getMdate());
						//回补周期开始日 封闭式展期回补开始日等于上一回补结束日
						finProductAnapleroticPlanDto
								.setAnapleroticCycleStartDate(anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getAnapleroticCycleEndDate());
						//回补周期结束日
						finProductAnapleroticPlanDto
								.setAnapleroticCycleEndDate(entity.getMdate());
						finProductAnapleroticPlanDto
								.setCashId(anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getCashId());
						finProductAnapleroticPlanDto.setValDate(finProductAnapleroticPlanDto.getAnapleroticCycleEndDate());
						anapleroticPlan.add(finProductAnapleroticPlanDto);
					}else {
						for(FinProductAnapleroticPlanDto finProductAnapleroticPlanDto_old : anapleroticPlan_old) {
							if(DateUtil.compareDate(entity.getMdate(), finProductAnapleroticPlanDto_old.getAnapleroticCycleEndDate()) > 0) {
								anapleroticPlan.add(finProductAnapleroticPlanDto_old);
							}else {
								//提前终止生成一条回补计划
								FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = new FinProductAnapleroticPlanDto();
								//回补日
								finProductAnapleroticPlanDto.setAnapleroticDate(entity.getMdate());
								//回补周期开始日
								finProductAnapleroticPlanDto
										.setAnapleroticCycleStartDate(finProductAnapleroticPlanDto_old.getAnapleroticCycleStartDate());
								//回补周期结束日
								finProductAnapleroticPlanDto
										.setAnapleroticCycleEndDate(entity.getMdate());
								finProductAnapleroticPlanDto
										.setCashId(finProductAnapleroticPlanDto_old.getCashId());
								finProductAnapleroticPlanDto.setValDate(finProductAnapleroticPlanDto.getAnapleroticCycleEndDate());
								anapleroticPlan.add(finProductAnapleroticPlanDto);
								break;
							}
						}
					}
				}
				finProductAnapleroticInfoDto.setFinProductAnapleroticPlanList(anapleroticPlan);
				// yzg 回补计划生成 end 20200103

				// 向前调整
				
				/*if (entity.getMdate().compareTo(finProductBuild002Dto.getMdate()) < 0) {
	    			   boolean mdateFlag = true;
	    			   for (FinProductAnapleroticPlanDto finProductAnapleroticPlanDto : anapleroticPlan_old) {
	    				   if (finProductAnapleroticPlanDto.getAnapleroticCycleEndDate().compareTo(entity.getMdate()) <= 0) {
	    					   anapleroticPlan.add(finProductAnapleroticPlanDto);
	                            
	                            if (finProductAnapleroticPlanDto.getAnapleroticCycleEndDate().compareTo(entity.getMdate()) == 0){
	                                mdateFlag = false;
	                            }
	                        }
	                        else if (mdateFlag){
	                        	finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(entity.getMdate());
	                        	finProductAnapleroticPlanDto.setValDate(entity.getMdate());
	                            
	                            // 交易日历取得
	                            List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(entity.getMdate(),
	                                DateUtil.addDay(entity.getMdate(), 8), finProductBuild002Dto.getCalendarId());
	                            // 支付日根据营业日准则进行调整
	                            Date tradePaymentDay = DateUtil.parse(
	                                AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(entity.getMdate()),
	                                		finProductAnapleroticInfoDto.getCycleTdayRule(), dealCalendarHolidayList));
	                            
	                            finProductAnapleroticPlanDto.setAnapleroticDate(tradePaymentDay);
	                            //finProductAnapleroticPlanDto.setPayDateUnadjust(tradePaymentDay);
	                            finProductAnapleroticPlanDto.setUpdateUser(LoginUserUtil.getUserName());
	                            finProductAnapleroticPlanDto.setUpdateTime(new Date());
	                            anapleroticPlan.add(finProductAnapleroticPlanDto);
	                            break;
	                        }
						
					}
	    		   }else{

//	    			   anapleroticPlan.addAll(anapleroticPlan_old);
	                    // 获取最后一条计划的起息日
	                    Date vdate = anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getAnapleroticCycleStartDate();
	                    Date mdate = anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getAnapleroticCycleEndDate();
	                    //第一条
	                    Date fVdate =  anapleroticPlan_old.get(0).getAnapleroticCycleStartDate();
	                    Date fMdate =  anapleroticPlan_old.get(0).getAnapleroticCycleEndDate();
	                    Date anapleroticDate =   anapleroticPlan_old.get(0).getAnapleroticDate();
	                    String  cashId = anapleroticPlan_old.get(anapleroticPlan_old.size() - 1).getCashId();
	                    finProductAnapleroticInfoDto.setCalendarId(finProductBuild002Dto.getCalendarId());
	                    finProductAnapleroticInfoDto.setAnapleroticEndDate(entity.getMdate());
	                    finProductAnapleroticInfoDto.setFirstAnapleroticDate(anapleroticDate);
	                    finProductAnapleroticInfoDto.setFirstAnapleroticCycleEndDate(fMdate);
	                    // 到期日调整 产品展期时，由于 首次回补周期开始日没有set值 导致 后面调用重新生成回补计划的方法返回一个空集合 具体见 FinProductAnapleroticInfo002Service 274行  add by wangcheng 20191108 start
	                    finProductAnapleroticInfoDto.setFirstAnapleroticCycleBegDate(fVdate);
	                    // 到期日调整 产品展期时，由于 首次回补周期开始日没有set值 导致 后面调用重新生成回补计划的方法返回一个空集合 具体见 FinProductAnapleroticInfo002Service 274行  add by wangcheng 20191108 end
	                    finProductAnapleroticInfoDto.setAnapleroticFrequency(finProductAnapleroticInfoDto.getAnapleroticFrequency());
	                    List<FinProductAnapleroticPlanDto> anapleroticPlan_new = finProductAnapleroticInfo002Service.calcProductAnapleroticPlan(finProductAnapleroticInfoDto);// 新生产的回补计划
//	                    boolean tempFlag = true;
	                    for (FinProductAnapleroticPlanDto anapleroticPlan1 : anapleroticPlan_new) {
//	                        if (anapleroticPlan1.getAnapleroticCycleStartDate().compareTo(vdate) > 0) {
//	                        	anapleroticPlan1.setCashId(cashId);
//	                            //finCashPlan.setCashNum(cashNum + 1);
//	                        	//anapleroticPlan1.setCashType(BussinessDictCodeList.现金流一级类型_回补.getValue());
//	                            if (tempFlag){
//	                            	anapleroticPlan1.setAnapleroticCycleStartDate(mdate);
//	                            	//anapleroticPlan1.setVdateUnadjust(mdate);
//	                                
//	                                tempFlag = false;
//	                            }
//	                            else{
//	                            	//anapleroticPlan1.setVdateUnadjust(anapleroticPlan1.getAnapleroticCycleStartDate());
//	                            }
//	                            anapleroticPlan1.setValDate(anapleroticPlan1.getAnapleroticCycleEndDate());
//	                            //finCashPlan.setPayDateUnadjust(finCashPlan.getPayDate());
//	                            anapleroticPlan1.setFinprodId(entity.getFinprodId());
//	                           // finCashPlan.setFinprodType("");
//	                            //finCashPlan.setFinprodType2(
//	                             //   BussinessDictCodeList.金融产品类型_投管用_理财产品.getValue());
//	                         //   finCashPlan.setBranch(Long.parseLong(
//	                            //    CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
//	                            anapleroticPlan.add(anapleroticPlan1);
//	                            
//	                           
//	                        }
	                    	anapleroticPlan1.setCashId(cashId);
	                    	anapleroticPlan1.setValDate(anapleroticPlan1.getAnapleroticCycleEndDate());
	                    	anapleroticPlan.add(anapleroticPlan1);
	                    }
	    	       }
				 
				 finProductAnapleroticInfoDto.setFinProductAnapleroticPlanList(anapleroticPlan);*/
			}
          }
 	   finProductBuild002Dto.setFinProductAnapleroticTabInfoDtoList(anapleroticInfoList);
 	   //合版本业绩回补相关 end wangcheng 20191028
        // 将selectItem参数（计提计划使用）保存进finProductBuild002Dto，参数统一处理
        finProductBuild002Dto.setPrdMdateAdjust(entity);

        restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/updateProdPlanList",
            finProductBuild002Dto, Integer.class);
        // 调用接口，修改账套起始日和结束日
        valInterfCommonServiceApi.updateProdMdate(finProductBuild002Dto.getPtlId(), DictCodeListExt1.金融产品类型_投资组合.getValue(), 0l,
            entity.getMdate());
        valInterfCommonServiceApi.updateProdMdate(entity.getFinprodId(), DictCodeListExt1.金融产品类型_理财产品.getValue(), 0l,
            entity.getMdate());
    }
    
    /**
	 *
	 * 按降序向列表添加 日期
	 * @param list 日期列表
	 * @param date 日期
	 * @see
	 * @since 1.0
	 */
	private void  addDateToList(List<Date> list, Date date){
	    if(date == null){
	        return;
	    }
	    for(int i=0;i<list.size();i++){
	        if(list.get(i).compareTo(date)==0){
	            return;
	        }
	        if(list.get(i).compareTo(date)>0){
	            list.add(i,date);
	            return;
           }
	    }
	    list.add(date);
	}

    /**
     * 获取下一个工作日 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getNextWorkDay(String mdate, String calendarId) {
        if (StringUtil.isEmpty(mdate)) {
            return "";
        }
        if (!StringUtil.isNotEmpty(calendarId)) {
            calendarId = "01";
        }
        Date mDate = DateUtil.parse(mdate);
        // 交易日历取得
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(mDate, DateUtil.addDay(mDate, 8),
            calendarId);
        return AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.addDay(mdate, 1), DictCodeList.营业日准则_向后调整.getValue(),
            dealCalendarHolidayList);
    }
}
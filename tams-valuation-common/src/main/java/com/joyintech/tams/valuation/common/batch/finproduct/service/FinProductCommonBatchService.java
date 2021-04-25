/*
 * 文件名：FinProductCommonBatchService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokFinProductValuationParaMapper;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.BokFinProductValuationPara;
import com.joyintech.tams.common.base.entity.BokFinProductValuationParaKey;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.MstShareLqratio;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.common.base.entity.MstValFx;
import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.batch.finproduct.dao.FinProductCommonMapper;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanEx;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinProductValuationDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.InvAimDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.MarketPriceDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.PledgeInfoDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SchemeDetailDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPayRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinClawbackCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardAccrsche;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardCalRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardCalRuleDetail;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;

/**
 * 标的类共通方法
 * 
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see FinProductCommonBatchService
 * @since 1.0searchInvAim
 */
@Service
public class FinProductCommonBatchService {

    /**
     * 标的类共同mapper
     */
    @Autowired
    private FinProductCommonMapper finProductCommonMapper;

    @Autowired
    private BokFinProductValuationParaMapper bokFinProductValuationParaKeyMapper;

    /**
     * 百元券面共通查询方法
     *
     * @param finprodId 标的代码
     * @param happenDate 发生日
     * @return faceValue 处理后的百元券面
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public BigDecimal getFaceValue(String finprodId, Date happenDate) {
        BigDecimal faceValue = new BigDecimal("100");
        BigDecimal payedFaceValue = new BigDecimal("0");
        // 增加查询类型Dto
        TargetSearchDto searchDto = new TargetSearchDto();
        searchDto.setQueryType(ValDictCodeList.标的_现金流计划.getValue());
        searchDto.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
        List<TargetSearchDto> listSearchDto = new ArrayList<>();
        // 放入list中传参
        listSearchDto.add(searchDto);
        // 获取现金流计划结果集
        List<TargetCashPlan> listDto = (List<TargetCashPlan>)this.getTargetInfo(finprodId,
                listSearchDto).get(searchDto);
        if(null!=listDto){
            for (TargetCashPlan plan : listDto) {
                // 计息结束日 <= 发生日
                if (DateUtil.compareDate(plan.getMdate(), happenDate) <= 0) {
                    payedFaceValue = ArithmeticUtil.doAdd(payedFaceValue, plan.getPrinAmt());
                }
            }
        }
        faceValue = ArithmeticUtil.subtract(faceValue, payedFaceValue);
        return faceValue;
    }

    /**
     * 公用方法：标的类共通方法 - 标的信息查询
     * 
     * @param finprodId 标的代码【必输项】
     * @param branch 分支修 可空
     * @param queryCriteria 查询类型【必输项】
     * @return Map<TargetSearchDto, Object> 返回值获取，直接用查询实体类作为key值获取。
     * @see
     * @since 1.0
     */
    public Map<TargetSearchDto, Object> getTargetInfo(String finprodId,
                                                      List<TargetSearchDto> queryCriteria) {

        // 定义返回值
        Map<TargetSearchDto, Object> returnMap = new HashMap<TargetSearchDto, Object>();

        // 循环查询类型list,进行获取数据
        for (TargetSearchDto svo : queryCriteria) {
            // 获取当前查询类型
            String queryTypeNow = svo.getQueryType();

            // 设置sql查询参数map
            Map<String, Object> paMap = new HashMap<String, Object>();
            paMap.put("finprodId", finprodId);// 标的代码
            paMap.put("finprodType", svo.getFinprodType());// 标的代码
            paMap.put("cashId", svo.getCashId());// 现金流代码
            paMap.put("cashType1", svo.getCashType1());// 现金流一级类型
            paMap.put("cashType2", svo.getCashType2());// 现金流二级类型
            paMap.put("payStatus", svo.getPayStatus());// 现金流计划支付标志
            paMap.put("effdateStart", svo.getEffdateStart());// 生效日期 - 开始
            paMap.put("effdateEnd", svo.getEffdateEnd());// 生效日期 - 结束
            if (svo.getBranch() == null) {
                svo.setBranch(BookConstant.DEFALUT_BRANCH);
            }
            paMap.put("branch", svo.getBranch());
            // 根据传递的查询类型，获取不同的标的数据
            if (ValDictCodeList.标的_信息.getValue().equals(queryTypeNow)) {
                // 获取标的基本信息
                List<TargetFinProduct> targetFinProductList = finProductCommonMapper.getTargetFinProduct(paMap);
                TargetFinProduct targetFinProduct = null;
                if (targetFinProductList != null && targetFinProductList.size() > 0) {
                	targetFinProduct = targetFinProductList.get(0);
                }
                // 查询结果塞入map中
                returnMap.put(svo, targetFinProduct);

            }
            else if (ValDictCodeList.标的_现金流计划.getValue().equals(queryTypeNow)) {
                // 查询标的现金流计划list
                List<TargetCashPlan> targetCashPlanList = finProductCommonMapper.getTargetCashPlanList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, targetCashPlanList);

            }
            else if (ValDictCodeList.标的_现金流支付规则.getValue().equals(queryTypeNow)) {
                // 查询标的现金流支付规则
                List<TargetCashPayRule> TargetCashPayRuleList = finProductCommonMapper.getTargetCashPayRuleList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, TargetCashPayRuleList);

            }
            else if (ValDictCodeList.标的_现金流计算规则.getValue().equals(queryTypeNow)) {
                // 查询标的现金流计算规则list
                List<TargetCashCalcRule> targetCashCalcRuleList = finProductCommonMapper.getTargetCashCalcRuleList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, targetCashCalcRuleList);

            }
            else if (ValDictCodeList.标的_业绩报酬计提计划.getValue().equals(queryTypeNow)) {
                // 查询标的业绩报酬计提计划list
                List<TargetPerfRewardAccrsche> targetPerfRewardAccrscheList = finProductCommonMapper.getTargetPerfRewardAccrscheList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, targetPerfRewardAccrscheList);

            }
            else if (ValDictCodeList.标的_业绩报酬计提规则.getValue().equals(queryTypeNow)) {
                // 查询标的业绩报酬规则 list
                List<TargetPerfRewardRule> targetPerfRewardRuleList = finProductCommonMapper.getTargetPerfRewardRuleList(paMap);

                // 查询结果塞入map中
                returnMap.put(svo, targetPerfRewardRuleList);
            }
            else if (ValDictCodeList.标的_业绩报酬计算规则.getValue().equals(queryTypeNow)) {
                // 查询标的业绩报酬规则 list
                List<TargetPerfRewardCalRule> targetPerfRewardCalRule = finProductCommonMapper.getTargetPerfRewardCalRuleList(paMap);

                // 循环业绩报酬规则 list，获取每个对应的标的业绩报酬规则明细
                for (TargetPerfRewardCalRule dto : targetPerfRewardCalRule) {
                    // 获取对应的明细List
                    List<TargetPerfRewardCalRuleDetail> details = finProductCommonMapper.getTargetPerfRewardRuleDetailList(
                        dto.getCashId(), dto.getEffDate());
                    // 将明显塞入实体类中
                    dto.setTargetPerfRewardCalRuleDetailList(details);
                }

                // 查询结果塞入map中
                returnMap.put(svo, targetPerfRewardCalRule);
            }
            /** modify by dongjun on 20190219 remark:标的_业绩回补计算规则 start**/
            else if (ValDictCodeList.标的_业绩回补计算规则.getValue().equals(queryTypeNow)) {
                // 查询标的_业绩报酬计算规则 list
                List<TargetFinClawbackCalcRule> targetFinCawlbackCalcRuleList = finProductCommonMapper.getTargetFinCLawbackList(paMap);
                // 循环标的_业绩回补计算规则 list，获取每个对应的标的业绩报酬规则明细
                for (TargetFinClawbackCalcRule dto : targetFinCawlbackCalcRuleList) {
                    // 获取对应的明细List
                    List<TargetPerfRewardCalRuleDetail> details = finProductCommonMapper.getTargetPerfRewardRuleDetailList(
                        dto.getCashId(), dto.getEffDate());
                    // 将明显塞入实体类中
                    dto.setTargetPerfRewardCalRuleDetailList(details);
                }
                
                // 查询结果塞入map中
                returnMap.put(svo, targetFinCawlbackCalcRuleList);
            }
            /** modify by dongjun on 20190219 remark:标的_业绩回补计算规则 end**/

        }

        return returnMap;
    }

    /**
     * 公共方法：标的类共通方法-查询交易信息List
     * 
     * @param finprodId 金融产品代码【必输项】
     * @param finprodType 金融产品类型【必输项】
     * @param branch 分支序号
     * @return 查询结果集(List<交易信息Dto>）
     * @see
     * @since 1.0
     */
    public List<TradeInfoDto> getTradeInfoDtoList(String finprodId, String finprodType,
                                                  Long branch) {
        return finProductCommonMapper.getTradeInfoDtoList(finprodId, finprodType, branch);
    }

    /**
     * 公共方法：标的类共通方法-利率行情信息查询
     * 
     * @param selectDate 查询日期
     * @param intRateId 行情代码
     * @return MAP<行情代码，利率行情信息>
     * @see
     * @since 1.0
     */
    public Map<String, MstIntRateInfo> getValIntRateInfo(Date selectDate, String intRateId) {
        // 定义返回值
        Map<String, MstIntRateInfo> resultMap = new HashMap<>();
        // 利率行情信息查询
        List<MstIntRateInfo> valIntRateInfoList = finProductCommonMapper.getValIntRateInfo(
            selectDate, intRateId);
        if (valIntRateInfoList != null && valIntRateInfoList.size() > 0) {
            for (MstIntRateInfo valIntRateInfo : valIntRateInfoList) {
                // 取生效日小于查询日期的生效日最大的记录
                resultMap.put(valIntRateInfo.getIntRateId(), valIntRateInfo);
            }
        }

        return resultMap;
    }

    /**
     * 估值方案查询
     * 
     * @param happenDate 发生日
     * @param booksetId 账套代码
     * @param secType 证券品种
     * @param schemeType 方案类型
     * @param invAim 投资目的
     * @param secDetailType 证券细分类
     * @param tradeMarket 交易市场
     * @param
     * @return 估值方案结果集
     * @see
     * @since 1.0
     */
    public List<SchemeDetailDto> getSchemeDetail(Date happenDate, String booksetId, String secType,
                                                 String schemeType, String tradeMarket,
                                                 String secDetailType, String invAim) {
        if (DictCodeListExt1.配置方案类型_估值方案.getValue().equals(schemeType)) {
            // 查询产品估值方案
            List<SchemeDetailDto> list = finProductCommonMapper.getSchemeDetail(happenDate,
                secType, booksetId, schemeType, tradeMarket, secDetailType, invAim);

            // 若查询出的产品估值方案为空，则需查询默认的估值方案
            if (CollectionUtils.isEmpty(list)) {
                // 若list为空，则查询默认的估值方案
                list = finProductCommonMapper.getSchemeDetailDefault(happenDate,
                    DictCodeList.是否_是.getValue(), booksetId, secType, tradeMarket, secDetailType,
                    invAim);
            }
            return list;
        } else if (DictCodeListExt1.配置方案类型_影子定价方案.getValue().equals(schemeType)) {
            // 查询产品估值方案
            List<SchemeDetailDto> list = finProductCommonMapper.getShadowPriceDetail(happenDate,
                secType, booksetId, schemeType, tradeMarket, secDetailType);

            // 若查询出的产品估值方案为空，则需查询默认的估值方案
            if (CollectionUtils.isEmpty(list)) {
                // 若list为空，则查询默认的估值方案
                list = finProductCommonMapper.getShadowPriceDetailDefault(happenDate,
                    DictCodeList.是否_是.getValue(), booksetId, secType, tradeMarket, secDetailType, schemeType);
            }
            return list;
        }
        return null;
    }

    /**
     * 处理获取最新的市价 <一句话功能简述> <功能详细描述>
     * 
     * @param list
     * @return
     * @see
     * @since 1.0
     */
//    private List<SchemeDetailDto> getLatestScheme(List<SchemeDetailDto> list) {
//        Map<String, SchemeDetailDto> map = new HashMap<>();
//        list.forEach(item -> {
//            String key = ValStringUtil.getNotNullStr(item.getTradeMarket()) + GlobalConstant.COMMA
//                         + ValStringUtil.getNotNullStr(item.getSecDetailType())
//                         + GlobalConstant.COMMA + ValStringUtil.getNotNullStr(item.getInvAim());
//            if (!map.containsKey(key)) {
//                map.put(key, item);
//            }
//        });
//        return new ArrayList<>(map.values());
//    }

    /**
     * 市价查询-计划类净值型
     * 
     * @param happenDate 发生日
     * @param finProdId 金融产品代码
     * @return BigDecimal 市价
     * @see
     * @since 1.0
     */
    public MarketPriceDto getFundMarketPriceForPlanAsset(Date happenDate, String finProdId, String booksetId) {

        // 定义变量
        // BigDecimal estimateValue = null;// 变量.估值(默认为null)
        MarketPriceDto marketPriceDto = new MarketPriceDto(); // 变量.市价Dto
        String marketCode = "";// 变量.市场代码
        Date queryDate = happenDate;// 变量.查询日期

        // 获取标的信息
        // 查询类型
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        TargetSearchDto targetSearchDto = new TargetSearchDto();
        targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
        queryCriteria.add(targetSearchDto);
        // 调用共通方法-【标的类共通方法设计】
        Map<TargetSearchDto, Object> targetInfo = this.getTargetInfo(finProdId, queryCriteria);
        // 标的信息
        TargetFinProduct targetFinProduct = (TargetFinProduct)targetInfo.get(targetSearchDto);

        // 变量.市场代码 = 标的信息.市场代码
        if (targetFinProduct != null) {
            marketCode = targetFinProduct.getFinprodMarketId();
        }
        
        // 调共通方法-【详细设计-标的类共通方法设计】-估值方案查询SHEET
        List<SchemeDetailDto> listSchemeDetailDto = this.getSchemeDetail(happenDate, booksetId,
            DictCodeListExt1.证券品种_计划类资产_净值型.getValue(), DictCodeListExt1.配置方案类型_估值方案.getValue(),
            null, null, null);
        if (CollectionUtils.isEmpty(listSchemeDetailDto) || listSchemeDetailDto.get(0) == null) {
            return marketPriceDto;
        }
        SchemeDetailDto schemeDetailDto = listSchemeDetailDto.get(0);
        // if(估值方案.估值方法=市值法)
        if (DictCodeListExt1.估值方法_市值法.getValue().equals(
            schemeDetailDto.getValuationMethod())) {
            if(DictCodeListExt1.估值日期_行情日减1.getValue().equals(
                schemeDetailDto.getValuationDate())){
                queryDate = DateUtil.addDay(happenDate, -1);
            }
            if(DictCodeListExt1.估值日期_行情日.getValue().equals(
                schemeDetailDto.getValuationDate())){
                queryDate = happenDate;
            }
            if(DictCodeListExt1.估值日期_行情日加1.getValue().equals(
                schemeDetailDto.getValuationDate())){
                queryDate = DateUtil.addDay(happenDate, 1);
            }
            // 获取场外基金估值行情DTO
            List<FinProductValuationDto> finProductValuationDtoList = finProductCommonMapper.getFundValuationOutsideForWithIn(
                queryDate, marketCode, DictCodeListExt1.金融产品类型_计划类资产.getValue());
            if (finProductValuationDtoList != null && finProductValuationDtoList.size() > 0) {
                // estimateValue = finProductValuationDtoList.get(0).getValuation();
                marketPriceDto.setPrice(finProductValuationDtoList.get(0).getValuation());
                marketPriceDto.setValDate(finProductValuationDtoList.get(0).getValDate());
            }

        }
        //modify by zhangquan 20191219 计划类资产(净值型)-公允价值变动计算时的行情位数与估值方案中设置的位数不一致。
        setQuotaDigit(schemeDetailDto.getQuotaDigit(), marketPriceDto, schemeDetailDto.getPriceTailMode());
        return marketPriceDto;
    }
 
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param quotaDigit
     * @param marketPriceDto
     * @see
     * @since 1.0
     */
    public void setQuotaDigit(BigDecimal quotaDigit, MarketPriceDto marketPriceDto, String priceTailMode) {
        // 按照行情位数调用金额共通方法设置有效小数位（高精度）
        if (quotaDigit != null) {
            RoundingMode roundingMode = null;
            if(DictCodeList.估值舍入规则_四舍五入.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.HALF_UP;
            }
            else if(DictCodeList.估值舍入规则_舍位.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.DOWN;
            }
            else if(DictCodeList.估值舍入规则_进位.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.UP;
            }
            marketPriceDto.setPrice(ArithmeticUtil.round(marketPriceDto.getPrice(),
                quotaDigit.intValue(), roundingMode));
        }
    }
    
    /**
     * 小数位数以及尾数处理
     * @param quotaDigit
     * @param marketPriceDto
     * @see
     * @since 1.0
     */
    public BigDecimal setQuotaDigit2(BigDecimal quotaDigit, BigDecimal marketPrice, String priceTailMode) {
        // 按照行情位数调用金额共通方法设置有效小数位（高精度）
        if (quotaDigit != null) {
            RoundingMode roundingMode = null;
            if(DictCodeList.估值舍入规则_四舍五入.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.HALF_UP;
            }
            else if(DictCodeList.估值舍入规则_舍位.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.DOWN;
            }
            else if(DictCodeList.估值舍入规则_进位.getValue().equals(priceTailMode)){
                roundingMode = RoundingMode.UP;
            }
            return ArithmeticUtil.round(marketPrice,
                quotaDigit.intValue(), roundingMode);
        }
        else{
            return marketPrice;
        }
    }

    /**
     * 公用方法：标的类共通方法 - 付息截止日判断
     * 
     * @param sDate 收息日【必输项】
     * @param finprodId 金融产品代码【必输项】
     * @return 是否付息截止日（Boolean）true:是 false:否
     * @see
     * @since 1.0
     */
    public FinCashPlanDto payDeadlineJudgment(String finprodId, Date sDate) {
        // 付息截止返回结果
        FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
        // 截止日查询
        List<FinCashPlanEx> list = finProductCommonMapper.queryFinCashPlan(finprodId, sDate);
        // 2.判断是否付息计划
        if (list != null && list.size() > 0) {
            for (FinCashPlanEx var : list) {
                // if(计划明细.现金流一级类型 = "利息")
                if (DictCodeListExt1.现金流一级类型_利息.getValue().equals(var.getCashType1())) {
                    // 出参.是否付息截止日 = true;
                    finCashPlanDto.setfDeadline(true);
                    // 出参.现金流计划表DTO = 计划明细
                    finCashPlanDto.setFinCashPlanEx(var);
                    return finCashPlanDto;
                }
            }
        }
        // 出参.是否付息截止日 = false
        finCashPlanDto.setfDeadline(false);
        // 出参.现金流计划表DTO = 变量.计划列表.get(0)
        finCashPlanDto.setFinCashPlanEx(
            list == null || list.size() == 0 ? new FinCashPlanEx() : list.get(0));

        return finCashPlanDto;
    }

    /**
     * 当前付息区间查询
     * 
     * @param busiId 业务明细代码
     * @param happenDate 发生日
     * @return
     * @see
     * @since 1.0
     */
    public List<TargetCashPlan> getCurrentIntRange(String busiId, Date happenDate) {
        // 现金流类型 = 利息
        String cashType = DictCodeListExt1.现金流一级类型_利息.getValue();
        return finProductCommonMapper.getCurrentIntRange(happenDate, busiId, cashType);
    }

    /**
     * 当前未还本金
     * 
     * @param happenDate 发生日
     * @param printAmt 本金
     * @param finprodId 标的代码
     * @return 市价净价
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public BigDecimal getUnpaidPrinAmt(Date happenDate, String finprodId, BigDecimal printAmt) {
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        TargetSearchDto dto = new TargetSearchDto();
        dto.setCashType1("01");
        dto.setQueryType(ValDictCodeList.标的_现金流计划.getValue());
        queryCriteria.add(dto);
        // 获取标的-现金流计划
        BigDecimal allPayAmt = BigDecimal.ZERO;
        Map<TargetSearchDto, Object> map = getTargetInfo(finprodId, queryCriteria);

        List<TargetCashPlan> targetCashPlanList = (List<TargetCashPlan>)map.get(dto);
        if (targetCashPlanList.size() > 0) {
            for (TargetCashPlan plan : targetCashPlanList) {
                if (plan.getMdate().before(happenDate)) {
                    allPayAmt = allPayAmt.add(plan.getPrinAmt());
                }
            }
        }
        printAmt = printAmt.subtract(allPayAmt);
        return printAmt;
    }

    /**
     * 账务明细代码信息查询
     * 
     * @param bokdetailId 账务明细代码
     * @param booksetId 套账代码
     * @return
     * @see
     * @since 1.0
     */
    public List<BokDetailId> bookdetailInforQuery(String booksetId, String bokdetailId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("booksetId", booksetId);
        map.put("bokdetailId", bokdetailId);
        return finProductCommonMapper.bookdetailInforQuery(map);
    }

    /**
     * 最大分支序号查询
     * 
     * @param finprodId 标的代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public Long queryValFinProductDealMaxBranch(String finprodId, String finprodType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        return finProductCommonMapper.queryValFinProductDealMaxBranch(map);
    }

    /**
     * 当前计划对应分支序号查询
     * 
     * @param finprodId 标的代码
     * @param finprodType 金融产品类型
     * @param branchType 分支类型
     * @param mdate 到期日
     * @return
     * @see
     * @since 1.0
     */
    public Long queryValFinProductDealBranchNow(String finprodId, String finprodType,
                                                String branchType, Date mdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("branchType", branchType);
        map.put("mdate", mdate);
        return finProductCommonMapper.queryValFinProductDealBranchNow(map);
    }

    /**
     * 下一行权计划查询 <功能详细描述>
     * 
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @param exerciseDate 计划行权日
     * @param exerciseType 行权方式
     * @return 下一行权计划
     * @see
     * @since 1.0
     */
    public FinExercisePlan queryFinExercisePlan(String finprodId, String finprodType,
                                                Date exerciseDate, String exerciseType) {
        FinExercisePlan finExercisePlan = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("exerciseDate", exerciseDate);
        map.put("exerciseType", exerciseType);
        List<FinExercisePlan> listFinExercisePlan = finProductCommonMapper.queryFinExercisePlan(
            map);
        if (listFinExercisePlan != null && listFinExercisePlan.size() > 0) {
            finExercisePlan = listFinExercisePlan.get(0);
        }
        return finExercisePlan;
    }

    /**
     * 核算主体查询 <功能详细描述>
     * 
     * @param finprodId
     * @param finprodType
     * @param exerciseDate
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct queryAcctSubject(String booksetId) {
        return finProductCommonMapper.queryAcctSubject(booksetId,
            DictCodeListExt1.金融产品类型_理财产品.getValue());
    }

    /**
     * 标的类共通方法设计 - 估值方案特殊资产配置查询
     * 
     * @param happenDate 发生日
     * @param bokSetId 账套代码
     * @param schemetype 方案类型
     * @param finprodId 资产代码
     * @return
     * @see
     * @since 1.0
     */
    public List<SchemeDetailDto> getSpecialSchemeDetail(Date happenDate, String bokSetId,
                                                        String schemetype, String finprodId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("happenDate", happenDate);
        map.put("bokSetId", bokSetId);
        map.put("schemetype", schemetype);
        map.put("finprodId", finprodId);

        List<SchemeDetailDto> spcList = finProductCommonMapper.getSpecialSchemeDetail(map);

        return spcList;
    }

    /**
     * 质押品信息查询 <功能详细描述>
     * 
     * @param finprodId
     * @param finprodType
     * @return
     * @see
     * @since 1.0
     */
    public List<PledgeInfoDto> getPledgeInfo(String finprodId, String finprodType) {
        return finProductCommonMapper.getPledgeInfo(finprodId, finprodType);
    }

    /**
     * 估值方案查询
     * 
     * @param happenDate 发生日
     * @param booksetId 账套代码
     * @param secType 证券品种
     * @param finprodId 金融产品代码
     * @param invAim 投资目的
     * @return 估值方案结果集
     * @see
     * @since 1.0
     */
    public InvAimDto searchInvAim(Date happenDate, String booksetId, String secType,
                                  String finprodId, String invAim) {
        // 投资目的
        InvAimDto invAimDto = new InvAimDto();
        List<SchemeDetailDto> schemeDetails = this.getSpecialSchemeDetail(happenDate, booksetId,
            DictCodeListExt1.配置方案类型_估值方案.getValue(), finprodId);
        if (!StringUtil.isEmptyList(schemeDetails)) {
            invAimDto.setInvAim(schemeDetails.get(0).getInvAim());
            invAimDto.setSchemeCnt(schemeDetails.size());
        }
        if (StringUtil.isEmpty(invAimDto.getInvAim())) {
            // 2.查询基本信息----调用共通方法-【标的类共通方法设计】
            List<TargetSearchDto> queryCriteria = new ArrayList<>();
            // 定义查询对象targetSearchDto，queryType设值RST_FINPRODUCT
            TargetSearchDto targetSearchDto = new TargetSearchDto();
            targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
            queryCriteria.add(targetSearchDto);
            // 调用共通方法查询标的信息C---targetFinProduct
            Map<TargetSearchDto, Object> targetSearchMap = this.getTargetInfo(finprodId,
                queryCriteria);
            // 基本信息
            TargetFinProduct targetFinProduct = (TargetFinProduct)targetSearchMap.get(
                targetSearchDto);
            List<SchemeDetailDto> listSchemeDetailDto = this.getSchemeDetail(happenDate, booksetId,
                secType, DictCodeListExt1.配置方案类型_估值方案.getValue(), targetFinProduct.getTradeMarket(),
                targetFinProduct.getType2(), invAim);
            if (!StringUtil.isEmptyList(listSchemeDetailDto)) {
                invAimDto.setInvAim(listSchemeDetailDto.get(0).getInvAim());
                invAimDto.setSchemeCnt(listSchemeDetailDto.size());
            }
        }
        return invAimDto;
    }


    /**
     * 标的类共通方法设计 - 汇率查询
     *
     * @param bookDate 日期
     * @param bookSetId 账套代码
     * @param offreCny 报价币种
     * @param basicCny 基础币种
     * @return
     * @see
     * @since 1.0
     */
    public List<CurrencyMarketDto> getCurrencyMarket(Date bookDate, String bookSetId, String offreCny,
                                                     String basicCny) {
        // 定义变量
        // 变量.估值方案DTO
        List<SchemeDetailDto> schemeDetailist = null;
        // 查询日期
        String serchDate = null;
        // 小数位数
        BigDecimal scale = null;
        // 小数位数处理 add by yanya on 20191219
        RoundingMode roundingMode = RoundingMode.HALF_UP;

        // 估值来源
        String valuationSource = null;
        // 变量-估值市价
        String valuationPrice = null;
        // 估值日期：行情日
        Date valuationDate = null;
        // 返回值List
        List<CurrencyMarketDto> currencyList = new ArrayList<CurrencyMarketDto>();
        // 实体类
        CurrencyMarketDto currencyMarketDto = new CurrencyMarketDto();

        if (basicCny.equals(offreCny)) {
            currencyMarketDto.setClosePrice(new BigDecimal(1));
            currencyMarketDto.setCurrencyDate(null);
            currencyList.add(currencyMarketDto);
            return currencyList;
        }
        // 调共通方法-【详细设计-标的类共通方法设计】-估值方案查询SHEET
        schemeDetailist = getSchemeDetail(bookDate, bookSetId, DictCodeListExt1.证券品种_换汇.getValue(),
                DictCodeListExt1.配置方案类型_估值方案.getValue(), null, null, null);

        if (schemeDetailist != null && schemeDetailist.size() > 0) {
            serchDate =schemeDetailist.get(0).getValuationDate();
            // 估值日期：行情日-1
            if(DictCodeListExt1.估值日期_行情日减1.getValue().equals(serchDate)){
                valuationDate = DateUtil.addDay(bookDate, -1);
            }
            // 估值日期：行情日
            if(DictCodeListExt1.估值日期_行情日.getValue().equals(serchDate)){
                valuationDate =bookDate;
            }
            // 估值日期：行情日+1
            if(DictCodeListExt1.估值日期_行情日加1.getValue().equals(serchDate)){
                valuationDate =DateUtil.addDay(bookDate, 1);
            }
            scale = schemeDetailist.get(0).getQuotaDigit();
            valuationSource = schemeDetailist.get(0).getValuationSource();
            valuationPrice = schemeDetailist.get(0).getValuationPrice();
            // 小数位数处理 add by yanya on 20191219
            String schemePriceTailMode = schemeDetailist.get(0).getPriceTailMode();
            if(DictCodeList.估值舍入规则_舍位.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.DOWN;
            }
            else if(DictCodeList.估值舍入规则_进位.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.UP;
            }
            else if(DictCodeList.估值舍入规则_四舍五入.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.HALF_UP;
            }
        } else {
            return null;
        }
        // 查询汇率行情信息
        /** modify by yanya on 20200214 人民币兑其他币种采用求倒方式 start**/
        //定义变量 是否求倒数 = 否
        boolean isReciprocal = false;//是否倒数 =  否
        //IF 报价币种 != 数据字典.币种.人民币 && 基础币种 == 数据字典.币种.人民币
//        if(!DictCodeList.币种_人民币_CNY.getValue().equals(offreCny)
//                && DictCodeList.币种_人民币_CNY.getValue().equals(basicCny)) {
//            //基础币种 = 报价币种	//DictCodeList.币种_人民币_CNY.getValue()
//            basicCny = offreCny;
//            //报价币种 = 数据字典.币种.人民币
//            offreCny = DictCodeList.币种_人民币_CNY.getValue();
//            //变量.是否求倒数 = 是
//            isReciprocal = true;
//        }
        /** modify by yanya on 20200214 人民币兑其他币种采用求倒方式 end**/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicCny", basicCny);
        map.put("offreCny", offreCny);
        map.put("valuation", valuationSource);
        map.put("serchDate", valuationDate);
        List<CurrencyMarketDto> spcList = finProductCommonMapper.getExchangeRate(map);
        if (schemeDetailist==null || schemeDetailist.size()==0) {
            return null;
        }
        if(spcList == null || spcList.size() == 0) {
            /** modify by yanya on 20200214 查询不到互换币种 start**/
            map.put("basicCny", offreCny);
            map.put("offreCny", basicCny);
            spcList = finProductCommonMapper.getExchangeRate(map);
            isReciprocal = true;
            /** modify by yanya on 20200214 查询不到互换币种 end**/
            if(spcList == null || spcList.size() == 0) {
                return null;
            }
        }
        // 定义 变量.计算价格
        BigDecimal calPrice = null;
        //变量.估值市价 = 开盘价
        if (DictCodeList.估值市价_开盘价.getValue().equals(valuationPrice)) {
            calPrice = spcList.get(0).getOpenPrice();
        }
        //变量.估值市价 = 收盘价
        if (DictCodeList.估值市价_收盘价.getValue().equals(valuationPrice)) {
            calPrice = spcList.get(0).getClosePrice();
        }
        //变量.估值市价 = 最高价
        if (DictCodeList.估值市价_最高价.getValue().equals(valuationPrice)) {
            calPrice = spcList.get(0).getHighPrice();
        }
        //变量.估值市价 = 最低价
        if (DictCodeList.估值市价_最低价.getValue().equals(valuationPrice)) {
            calPrice = spcList.get(0).getLowPrice();
        }
        //变量.估值市价 = 中间价
        if (DictCodeList.估值市价_中间价.getValue().equals(valuationPrice)) {
            calPrice = spcList.get(0).getMidPrice();
        }

        // 出参.汇率 = （变量.计算价格/查询汇率行情.开盘价） 按照变量.小数位数以及尾数处理方式处理小数位。
        // BigDecimal closePrice = ArithmeticUtil.multiply(calPrice,new BigDecimal(spcList.get(0).getQuotUnit()));
        BigDecimal closePrice = BigDecimal.ONE;
        //IF 变量.是否求倒数 == 是
        if(isReciprocal) {
            closePrice = ArithmeticUtil.divide(
                            new BigDecimal(1),
                            ArithmeticUtil.divide(
                                ArithmeticUtil.divide(calPrice, BigDecimal.ONE, scale.intValue(), roundingMode),
                                new BigDecimal(spcList.get(0).getQuotUnit()),
                                NumberEnum.ALL_PRECISION.getCode()),
                            NumberEnum.HIGH_PREC_NUM.getCode());
        }
        else {
            closePrice = ArithmeticUtil.divide(ArithmeticUtil.divide(calPrice, BigDecimal.ONE, scale.intValue(), roundingMode),new BigDecimal(spcList.get(0).getQuotUnit()),NumberEnum.HIGH_PREC_NUM.getCode());
        }

        // 出参.汇率日期 = 查询汇率行情.交易日
        Date currencyDate = spcList.get(0).getTradeDate();
        currencyMarketDto.setClosePrice(closePrice);
        currencyMarketDto.setCurrencyDate(currencyDate);
        currencyList.add(currencyMarketDto);
        return currencyList;
    }
    

    /**
     * 标的类共通方法设计 - 利率互换行情查询
     *
     * @param happenDate 日期
     * @param bookSetId 账套代码
     * @param finprodId 金融产品代码
     * @return MstValIrs
     * @see
     * @since 1.0
     */
    public MstValIrs getInterestRateSwap(Date happenDate, String bookSetId, String finprodId) {
        //1、定义变量
    	// 查询日期
        Date serchDate = null;
        // 估值来源
        String datasourceType = null;
        // 小数位数
        BigDecimal scale = null;
        //2. 调共通方法-【详细设计-标的类共通方法设计】-估值方案查询SHEET
        List<SchemeDetailDto> schemeDetailist = getSchemeDetail(happenDate, bookSetId, DictCodeListExt2.证券品种_利率互换.getValue(),
                DictCodeListExt1.配置方案类型_估值方案.getValue(), null, null, null);
        // 变量.估值方案DTO
        SchemeDetailDto schemeDetailDto = new SchemeDetailDto();
    	//3、查询估值信息
        if (schemeDetailist != null && schemeDetailist.size() > 0) {
            schemeDetailDto = schemeDetailist.get(0);
//            // 估值方法 = 市值法
//            if(DictCodeListExt1.估值方法_市值法.getValue().equals(schemeDetailDto.getValuationMethod())){
                //变量.查询日期 = 参数.日期
                serchDate = happenDate;
                //变量.小数位数 = 变量.估值方案DTO.行情位数
                scale = schemeDetailDto.getQuotaDigit();
                //变量.数据来源 = 变量.估值方案DTO.估值来源
                datasourceType = schemeDetailDto.getValuationSource();   
                //3.1、查询汇率行情信息：
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("datasourceType", datasourceType);
                map.put("finprodId2", finprodId);
                map.put("valDate", serchDate);
                
                MstValIrs MstValIrs = finProductCommonMapper.getCurrencyMarket(map);
                if(MstValIrs == null){
                      return null;
                }
                //按照变量.小数位数以及尾数处理方式处理小数位。
//                MstValIrs.setValuation(ArithmeticUtil.round(MstValIrs.getValuation(),scale.intValue(),null));
                MstValIrs.setValuation(setQuotaDigit2(scale, MstValIrs.getValuation(), schemeDetailDto.getPriceTailMode()));
                return MstValIrs;
//            }
//            else{
//                return null; 
//            }
        }else{
        	return null;
        }
    }
    
    /**
     * 标的类共通方法设计 - 外汇行情查询
     *
     * @param happenDate 发生日期
     * @param bookSetId 账套代码
     * @param finprodId 金融产品代码
     * @return MstValFx
     * @see
     * @since 1.0
     */
    public MstValFx getForeignExcMarket(Date happenDate, String bookSetId, String finprodId) {    	
        //1、定义变量
    	// 查询日期
        Date serchDate = null;
        // 估值来源
        String datasourceType = null;
        // 小数位数
        BigDecimal scale = null;

        // 小数位数处理 add by yanya on 20191219
        RoundingMode roundingMode = null;

        // 变量.估值方案DTO
        List<SchemeDetailDto> schemeDetailist = null;
        
        //2. 调共通方法-【详细设计-标的类共通方法设计】-估值方案查询SHEET
        schemeDetailist = getSchemeDetail(happenDate, bookSetId, DictCodeListExt2.证券品种_外汇.getValue(),
                DictCodeListExt1.配置方案类型_估值方案.getValue(), null, null, null);
        
    	//3、查询估值信息
        if (schemeDetailist != null && schemeDetailist.size() > 0) {
        	//变量.查询日期 = 参数.日期
        	serchDate = happenDate;
        	//变量.小数位数 = 变量.估值方案DTO.行情位数
        	scale = schemeDetailist.get(0).getQuotaDigit();
        	//变量.数据来源 = 变量.估值方案DTO.估值来源
        	datasourceType = schemeDetailist.get(0).getValuationSource();
            // 小数位数处理 add by yanya on 20191219
            String schemePriceTailMode = schemeDetailist.get(0).getPriceTailMode();
            if(DictCodeList.估值舍入规则_舍位.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.DOWN;
            }
            else if(DictCodeList.估值舍入规则_进位.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.UP;
            }
            else if(DictCodeList.估值舍入规则_四舍五入.getValue().equals(schemePriceTailMode)) {
                roundingMode = RoundingMode.HALF_UP;
            }
        }else{
        	return null;
        }
        //3.1、查询汇率行情信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("datasourceType", datasourceType);
        map.put("finprodId2", finprodId);
        map.put("valDate", serchDate);
        
        MstValFx mstValFx = finProductCommonMapper.getForeignMarket(map); 
		if(mstValFx == null){
			  return null;
		}
        //按照变量.小数位数以及尾数处理方式处理小数位。增加去估值方案中的小数位数处理方式 modify by yanya on 20191219
        mstValFx.setValuation(ArithmeticUtil.round(mstValFx.getValuation(),scale.intValue(), roundingMode));
        
    	return mstValFx;
    }
    
    /**
     * 标的类共通方法设计 - 股票行情查询
     *
     * @param searchDate 查询日期
     * @param bookSetId 账套代码
     * @param sharesId 股票代码
     * @param tradeMarket 交易场所
     * @param invAim 投资目的
     * @param secType 证券品种
     * @return Map<String, Object>
     * @see
     * @since 1.0
     */
	public Map<String, Object> getStockMarket(Date searchDate, String bookSetId, String sharesId, String tradeMarket,
			String invAim) {
		// 1、定义变量
		// 查询日期
		Date serchDate = null;
		// 行情日期
		String optionDate = null;
		// 数据来源
		String datasourceType = null;
		// 小数位数
		BigDecimal scale = null;
		// 小数位数处理方式
		String priceTailMode = null;
		// 变量.估值市价
		String valuationPrice = null;
		// 估值方法
		String valuationMethod = null;
		// 定义 变量.计算价格
		BigDecimal calPrice = null;
		//投资目的默认FVTPL
        invAim = "FVTPL";

		// 变量.估值方案DTO
		List<SchemeDetailDto> schemeDetailist = null;

		// 2. 调共通方法-【详细设计-标的类共通方法设计】-估值方案查询SHEET
		schemeDetailist = getSchemeDetail(searchDate, bookSetId, DictCodeListExt2.证券品种_股票.getValue(),
				DictCodeListExt1.配置方案类型_估值方案.getValue(),tradeMarket,null,invAim);
		// 3、查询估值信息
		if (schemeDetailist != null && schemeDetailist.size() > 0) {
			optionDate = schemeDetailist.get(0).getValuationDate();
			// 变量.估值方案DTO.估值日期 = 【数据字典】行情日-1
			if (DictCodeListExt1.估值日期_行情日减1.getValue().equals(optionDate)) {
				// 变量.查询日期 = 参数.查询日期-1
				serchDate = DateUtil.addDay(searchDate, -1);
			}
			// 变量.估值方案DTO.估值日期 = 【数据字典】行情日
			if (DictCodeListExt1.估值日期_行情日.getValue().equals(optionDate)) {
				// 变量.查询日期 = 参数.查询日期
				serchDate = searchDate;
			}
			// 变量.估值方案DTO.估值日期 = 【数据字典】行情日+1
			if (DictCodeListExt1.估值日期_行情日加1.getValue().equals(optionDate)) {
				// 变量.查询日期 = 参数.查询日期+1
				serchDate = DateUtil.addDay(searchDate, 1);
			}
			// 变量.估值方案 = 变量.估值方案DTO.估值方案
			SchemeDetailDto schemeDetailDto = schemeDetailist.get(0);
			// 变量.小数位数 = 变量.估值方案DTO.行情位数
			scale = schemeDetailDto.getQuotaDigit();
			// 变量.数据来源 = 变量.估值方案DTO.估值来源
			datasourceType = schemeDetailDto.getValuationSource();
			// 变量.估值市价 = 变量.估值方案DTO.估值第一市价
			valuationPrice = schemeDetailDto.getValuationPrice();
			// 变量.估值方法= 变量.估值方案DTO.估值方法
			valuationMethod = schemeDetailDto.getValuationMethod();
			// 变量.小数位数处理方式 = 变量.估值方案DTO.小数位尾数处理方式
			priceTailMode = schemeDetailDto.getPriceTailMode();
		} else {
			return null;
		}
		// 4、计算处理
		MstSharePrice mstSharePrice = new MstSharePrice();
		// 定义返回Map
		Map<String, Object> mapreturn = new HashMap<String, Object>();
		if (DictCodeListExt1.估值方法_市值法.getValue().equals(valuationMethod)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sharesId", sharesId);
			map.put("dealDate", serchDate);
			// 股票市场行情查询
			mstSharePrice = finProductCommonMapper.getMstSharePrice(map);		 
			if(mstSharePrice == null){
			  return null;
			}
			// 估值日期
			Date valDate = mstSharePrice.getDealDate();
			// 变量.估值市价 = 收盘价
			if (DictCodeList.估值市价_收盘价.getValue().equals(valuationPrice)) {
				calPrice = mstSharePrice.getClosePrice();
			}
			// 变量.估值市价 = 平均价
			if (DictCodeList.估值市价_平均价.getValue().equals(valuationPrice)) {
				calPrice = mstSharePrice.getAvgPrice();
			}
			// 变量.估值市价 = 最高价
			if (DictCodeList.估值市价_最高价.getValue().equals(valuationPrice)) {
				calPrice = mstSharePrice.getHighPrice();
			}
			// 变量.估值市价 = 最低价
			if (DictCodeList.估值市价_最低价.getValue().equals(valuationPrice)) {
				calPrice = mstSharePrice.getLowPrice();
			}
//			// 变量.估值市价 = 中间价
//			if (DictCodeList.估值市价_买一价.getValue().equals(valuationPrice)) {
//				calPrice = mstSharePrice.getLowPrice();
//			}
//			// 变量.估值市价 = 中间价
//			if (DictCodeList.估值市价_卖一价.getValue().equals(valuationPrice)) {
//				calPrice = mstSharePrice.getLowPrice();
//			}
	        //按照变量.小数位数以及尾数处理方式处理小数位。 
	        calPrice = setQuotaDigit2(scale, calPrice, priceTailMode);
			mapreturn.put("valuation", calPrice);
			mapreturn.put("valDate", valDate);
			mapreturn.put("scale", scale);
		} else {
			mapreturn.put("valuation", new BigDecimal(0));
			mapreturn.put("valDate", null);
			mapreturn.put("scale", null);
		}

		return mapreturn;
	}
	
	
    /**
     * 标的类共通方法设计 - 流通性受限股票行情查询
     *
     * @param searchDate 查询日期
     * @param bookSetId 账套代码
     * @param sharesId 股票代码
     * @param tradeMarket 交易场所
     * @param invAim 投资目的
     * @return Map<String, Object>
     * @see
     * @since 1.0
     */
	public Map<String, Object> getStockLiqInd(Date searchDate, String bookSetId, String sharesId, String tradeMarket,
			String invAim) {
		
		// 定义股票行情查询Map
		Map<String, Object> mapStockMarket = new HashMap<String, Object>();
		// 1. 调共通方法-【股票行情查询】-估值方案查询SHEET
		mapStockMarket = getStockMarket(searchDate,bookSetId,sharesId,tradeMarket,invAim);	
		//上述方法返回为空， return null
		if(CollectionUtils.isEmpty(mapStockMarket)){
			return null;			
		}		
		MstShareLqratio mstShareLqratio = new MstShareLqratio();		
		//2.受限股流动性折扣查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sharesId", sharesId);
		map.put("valDate", mapStockMarket.get("valDate"));
		mstShareLqratio = finProductCommonMapper.getMstShareLqratio(map);
		
		//定义返回的Map
		Map<String, Object> mapr = new HashMap<String, Object>();
		//3.处理结果值
		if(mstShareLqratio ==null){
			//出参.股票价格 = 变量.股票价格
			mapr.put("valuation", mapStockMarket.get("valuation"));
			//出参.估值日期 = 变量.估值日期
			mapr.put("valDate", mapStockMarket.get("valDate"));			
		}else{		
			//出参.股票价格 = 1返回的变量.股票价格 * (1- 2查询的受限股流动性折扣.流动性折扣)
			mapr.put("valuation",ArithmeticUtil.multiply((BigDecimal)mapStockMarket.get("valuation"),mstShareLqratio.getRatio()));
			//出参.估值日期 = 变量.估值日期
			mapr.put("valDate", mapStockMarket.get("valDate"));			
		}
	return 	mapr;
	}
	
	
	/**
	 * 市价查询-场外期权
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param happenDate 发生日
	 * @param finProdId  金融产品代码
	 * @return
	 * @see
	 * @since 1.0
	 */
    public MstValIrs getFundMarketPriceForOtc(Date happenDate, String finProdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("happenDate", happenDate);
        map.put("finprodId", finProdId);
        List<MstValIrs> mstValIrsList = finProductCommonMapper.getFundMarketPriceForOtc(map);
        if(StringUtil.isEmptyList(mstValIrsList)) {
            return null;
        }
        MstValIrs result = mstValIrsList.get(0);
        return result;
    }

    /**
     * 摊销设置日获取
     * <一句话功能简述>
     * <功能详细描述>
     * @param rstBaseinfo 资产信息
     * @param booksetId  账套信息
     * @return
     * @see
     * @since 1.0
     */
    public TargetFinProduct getExeDate(TargetFinProduct rstBaseinfo,String booksetId){

        /* 判断债券日终计算摊销时，优先级为账套加资产维度>资产维度>账套维度(暂时不存在 )   add wangwei1  2019/03/02 */
        BokFinProductValuationParaKey bokFinProductValuationParaKey=new BokFinProductValuationParaKey();
        bokFinProductValuationParaKey.setBoksetId(booksetId);
        bokFinProductValuationParaKey.setFinprodId(rstBaseinfo.getFinprodId());
        bokFinProductValuationParaKey.setBranch(InterfCommonConstant.分支序号_默认);
        BokFinProductValuationPara bokFinProductValuationPara= bokFinProductValuationParaKeyMapper.selectByPrimaryKey(bokFinProductValuationParaKey);
       //2.查询结果不为空，重新赋值含权债摊销日
        if(bokFinProductValuationPara !=null){
            rstBaseinfo.setExeDate(bokFinProductValuationPara.getExeDateType());
        }
        /* 判断债券日终计算摊销时，优先级为账套加资产维度>资产维度>账套维度(暂时不存在 )   end wangwei1  2019/03/02 */
        return rstBaseinfo;

    }
}

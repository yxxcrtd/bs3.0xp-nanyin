/*
 * 文件名：FinProductDurDealService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.durdeal.dao.FinProductDurDealMapper;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealCashCalcRuleDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDetailDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealProdDto;
import com.joyintech.service.product.durdeal.dto.InterfProdInfoDto;
import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 产品存续期交易查询微服务Service
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDealService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service("backFinProductDurDealService")
public class FinProductDurDealService extends BaseService {

    /**
     * FinProductDurDealMapper
     */
    @Autowired
    private FinProductDurDealMapper finProductDurDealMapper;

    @Autowired
    private PlatformDateImpl platformDateImpl;

    /**
     * BaseGetBussinessId
     */
    @Autowired(required = false)
    private BaseGetBussinessId baseGetBussinessId;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> paramMap) {
        paramMap.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return finProductDurDealMapper.queryProdIdInfo(paramMap);
    }

    /**
     * 查询产品存续期交易信息
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 产品存续期交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<FinProductDurDealDto> selectForPage(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("JYFX_CPRG", DictCodeList.交易方向_产品认购.getValue());
        pageParam.put("JYFX_CPSG", DictCodeList.交易方向_产品申购.getValue());
        pageParam.put("JYFX_CPSH", DictCodeList.交易方向_产品赎回.getValue());
        pageParam.put("JYFX_CPDQ", DictCodeList.交易方向_产品到期.getValue());
        pageParam.put("JYFX_ZJFX", DictCodeList.交易方向_中间付息.getValue());
        pageParam.put("JYFX_CPTQZZ", DictCodeList.交易方向_提前终止.getValue());
        pageParam.put("JYFX_CPWYSH", DictCodeList.交易方向_产品违约赎回.getValue());
        pageParam.put("JYFX_CPTQSH", DictCodeList.交易方向_产品提前赎回.getValue());
        pageParam.put("JYFX_CPXJFH", DictCodeList.交易方向_产品现金分红.getValue());
        pageParam.put("JYFX_CPHLZT", DictCodeList.交易方向_产品红利转投.getValue());
        pageParam.put("JYFX_FEQZ", DictCodeList.交易方向_份额强增.getValue());
        pageParam.put("JYFX_FEQJ", DictCodeList.交易方向_份额强减.getValue());
        pageParam.put("JYFX_CPCQDJ", DictCodeList.交易方向_产品除权登记.getValue());
        pageParam.put("JYFX_CPFF", DictCodeList.交易方向_产品付费.getValue());
        //ZGBUG-333【增值税缴纳】增值税缴纳成功，不应在产品存续期交易，显示一条有效数据 guyunfei 20200311 start
        pageParam.put("JYFX_SFJN",DictCodeList.交易方向_税费缴税.getValue());
        //ZGBUG-333【增值税缴纳】增值税缴纳成功，不应在产品存续期交易，显示一条有效数据 guyunfei 20200311 end
        pageParam.put("FINPROD_TYPE2_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("FINPROD_TYPE2_LCZCP", DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        pageParam.put("TRADE_STATUS_CS", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_CX", DictCodeListExt1.交易状态_撤销.getValue());
        pageParam.put("YES_NO_N", DictCodeList.是否_否.getValue());
        pageParam.put("YES", DictCodeList.是否_是.getValue());
        pageParam.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("statusValidN", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("PROFIT_TYPE_SY", DictCodeList.收益类型_收益型.getValue());
        pageParam.put("PROFIT_TYPE_JZ", DictCodeList.收益类型_净值型.getValue());
        // 一体化对应etliu start
        pageParam.put("CASH_TYPE_1_LX", DictCodeListExt1.现金流一级类型_利息.getValue());
        pageParam.put("CASH_TYPE_2_LX", DictCodeListExt1.现金流二级类型_利息.getValue());
        // 一体化对应etliu end
        //add by wm 增加分档产品的产品类型 start 2019-07-13
        pageParam.put("FINPROD_TYPE2_LCCPFD", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        //add by wm 增加分档产品的产品类型 end 2019-07-13
        // 1.3检索项目(当日交易数据)
        @SuppressWarnings("unchecked")
        Page<FinProductDurDealDto> resultPage = this.queryForPage(page, length,
            () -> finProductDurDealMapper.selectMainData(pageParam));

        if (!StringUtil.isEmptyList(resultPage.getRoot())) {
            this.genProdIdListMap(resultPage.getRoot(), pageParam);

            // 1.1检索项目 (收益型产品收益率 查询现金流计算规则表数据)
            List<FinProductDurDealCashCalcRuleDto> finCashCalcRuleData = finProductDurDealMapper.selectFinCashCalcRuleData(
                pageParam);

            // 2.1检索项目 (净值型/货币型产品 每日估值数据)
            String cashDateBegin = (String) pageParam.get("cashDateBegin");
            if (StringUtil.isNotBlank(cashDateBegin)) {
                pageParam.put("cashDateBeginEarly", DateUtil.addDay(cashDateBegin, -10));
            }
            // 查询产品头寸数据
            List<BokPositionPrd> bokPositionPrdDataList = finProductDurDealMapper.selectBokPositionPrdData(pageParam);
            //根据totalTradeId查询交易明细信息
            
            List<TrdProductDeal> trdProductList = finProductDurDealMapper.selectTrdProductDealByTotalTradId(pageParam);
            
            // 查询场外金融产品估值数据
            List<FinProductValuation> finProductValuationDataList = finProductDurDealMapper.selectFinProductValuationData(pageParam);

            // 1.2检索项目（当日总份额）
            List<FinProductDurDealDto> totalDataList = finProductDurDealMapper.selectTotalData(
                pageParam);
            Map<String, List<FinProductDurDealDto>> totalDataMap = totalDataList.stream().collect(
                JoyinCollectors.groupingBy(
                    x -> x.getPortfolioId() + GlobalConstant.UNDER_LINE + x.getFinprodId()));
            
            Map<String,List<FinProductDurDealDto>> resultMap = resultPage.getRoot().stream().collect(
                JoyinCollectors.groupingBy(x -> x.getPortfolioId() + GlobalConstant.UNDER_LINE + x.getFinprodId()
                + GlobalConstant.UNDER_LINE + x.getCashDate()));
            //汇总 查询结果：已产品+现金流日期来汇总 add by chendijun 2019/10/24
            resultPage.setRoot(this.genResultPageRoot(resultMap));

            String totalDataKey;
            String totalDataLastDayKey;
            Map<String, BigDecimal> tempTotalDataMap = new HashMap<String, BigDecimal>();
            
            for (FinProductDurDealDto resultDto : resultPage.getRoot()) {
                // 设置收益率
//                this.genYield(resultDto, finCashCalcRuleData, bokPositionPrdDataList, finProductValuationDataList);
                this.genYield(resultDto, finCashCalcRuleData, bokPositionPrdDataList, finProductValuationDataList, trdProductList);

                // 设置总份额
                totalDataKey = resultDto.getPortfolioId() + GlobalConstant.UNDER_LINE
                               + resultDto.getFinprodId();
                totalDataLastDayKey = resultDto.getPortfolioId() + GlobalConstant.UNDER_LINE
                    + resultDto.getFinprodId() + GlobalConstant.UNDER_LINE
                    + DateUtil.addDay(resultDto.getCashDate(), -1);
                
                String totalDataCurrDayKey = resultDto.getPortfolioId() + GlobalConstant.UNDER_LINE
                    + resultDto.getFinprodId() + GlobalConstant.UNDER_LINE + resultDto.getCashDate();

                if (tempTotalDataMap.containsKey(totalDataLastDayKey)) {
                    // 如果昨天的数据已经统计好，直接用昨天的总份额加上当天的数据
                    BigDecimal lastDayTotalQty = tempTotalDataMap.get(totalDataLastDayKey);

                    if (totalDataMap.containsKey(totalDataKey)) {
                        List<FinProductDurDealDto> prodTotalDataList = totalDataMap.get(totalDataKey);

                        List<FinProductDurDealDto> currDayDataList = prodTotalDataList.stream().filter(
                            x -> StringUtil.equals(totalDataCurrDayKey,
                                x.getPortfolioId() + GlobalConstant.UNDER_LINE + x.getFinprodId()
                                                                        + GlobalConstant.UNDER_LINE
                                                                        + x.getCashDate())).collect(Collectors.toList());

                        BigDecimal currDayQty = this.calcOneDayQty(currDayDataList);

                        resultDto.setTdyTotalQty(ArithmeticUtil.doAdd(lastDayTotalQty, currDayQty));
                    }

                }
                else {
                    // 基于所有数据，计算到当前循环的这一天的总份额
                    if (totalDataMap.containsKey(totalDataKey)) {
                        List<FinProductDurDealDto> prodTotalDataList = totalDataMap.get(totalDataKey);
                        List<FinProductDurDealDto> totalDataUtilNowList = prodTotalDataList.stream().filter(
                            x -> DateUtil.compareDate(x.getCashDate(),
                                resultDto.getCashDate()) <= 0).collect(Collectors.toList());

                        BigDecimal tdyTotalQty = this.calcOneDayQty(totalDataUtilNowList);
                        resultDto.setTdyTotalQty(tdyTotalQty);
                    }
                }

                tempTotalDataMap.put(totalDataCurrDayKey, resultDto.getTdyTotalQty());
            }


            //上面的计算导致 排序异常  重写排序。  add by wj 20200901  /TISHKBS-1043
            String key = (String)pageParam.get("key");
            String order = (String)pageParam.get("order");

            if(!StringUtil.isEmpty(key) && !StringUtil.isEmpty(order) ){

                boolean isAsc = true;
                if(StringUtil.equals(order,"desc")){
                    isAsc = false;
                }
                List<FinProductDurDealDto> list = resultPage.getRoot();
                ListUtils.sort(list,isAsc,key);

                resultPage.setRoot(list);
            }

        }

        return resultPage;
    }

    @SuppressWarnings("unchecked")
	public Page<FinProductDurDealDto> findDetailPage(int page, int length,
    		 									Map<String, Object> pageParam)
    	throws Exception {
    	pageParam.put("JYFX_CPRG", DictCodeList.交易方向_产品认购.getValue());
        pageParam.put("JYFX_CPSG", DictCodeList.交易方向_产品申购.getValue());
        pageParam.put("JYFX_CPSH", DictCodeList.交易方向_产品赎回.getValue());
        pageParam.put("JYFX_CPDQ", DictCodeList.交易方向_产品到期.getValue());
        pageParam.put("JYFX_CPTQZZ", DictCodeList.交易方向_提前终止.getValue());
        pageParam.put("JYFX_CPWYSH", DictCodeList.交易方向_产品违约赎回.getValue());
        pageParam.put("JYFX_CPTQSH", DictCodeList.交易方向_产品提前赎回.getValue());
        pageParam.put("JYFX_XJFH", DictCodeList.交易方向_产品现金分红.getValue());
        pageParam.put("JYFX_CPHLZT", DictCodeList.交易方向_产品红利转投.getValue());
        pageParam.put("JYFX_FEQZ", DictCodeList.交易方向_份额强增.getValue());
        pageParam.put("JYFX_FEQJ", DictCodeList.交易方向_份额强减.getValue());
        pageParam.put("JYFX_CPCQDJ", DictCodeList.交易方向_产品除权登记.getValue());
        pageParam.put("JYFX_CPFF", DictCodeList.交易方向_产品付费.getValue());
        //ZGBUG-333【增值税缴纳】增值税缴纳成功，不应在产品存续期交易，显示一条有效数据 guyunfei 20200311 start
        pageParam.put("JYFX_SFJN",DictCodeList.交易方向_税费缴税.getValue());
        //ZGBUG-333【增值税缴纳】增值税缴纳成功，不应在产品存续期交易，显示一条有效数据 guyunfei 20200311 end
        pageParam.put("JYFX_ZJFX", DictCodeList.交易方向_中间付息.getValue());
        pageParam.put("FINPROD_TYPE2_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("TRADE_STATUS_CS", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("YES_NO_N", DictCodeList.是否_否.getValue());
        pageParam.put("PROFIT_TYPE_SY", DictCodeList.收益类型_收益型.getValue());
        pageParam.put("PROFIT_TYPE_JZ", DictCodeList.收益类型_净值型.getValue());

        return this.queryForPage(page, length,
        		() -> finProductDurDealMapper.selectDetailData(pageParam));
    }

    /**
     * 查询产品存续期交易明细数据
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 产品存续期交易明细数据
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<FinProductDurDealDetailDto> findDetailData(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("JYFX_CPRG", DictCodeList.交易方向_产品认购.getValue());
        pageParam.put("JYFX_CPSG", DictCodeList.交易方向_产品申购.getValue());
        pageParam.put("JYFX_CPSH", DictCodeList.交易方向_产品赎回.getValue());
        pageParam.put("JYFX_CPDQ", DictCodeList.交易方向_产品到期.getValue());
        pageParam.put("JYFX_ZJFX", DictCodeList.交易方向_中间付息.getValue());
        pageParam.put("JYFX_CPHLZT", DictCodeList.交易方向_产品红利转投.getValue());
        pageParam.put("JYFX_FEQZ", DictCodeList.交易方向_份额强增.getValue());
        pageParam.put("JYFX_FEQJ", DictCodeList.交易方向_份额强减.getValue());
        pageParam.put("IS_CANCEL_N", DictCodeList.是否_否.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());

        String profitType = (String) pageParam.get("profitType");
        String isLay = (String) pageParam.get("isLay");
        String yield = (String) pageParam.get("yield");

        Page<FinProductDurDealDetailDto> resultPage = null;
        if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), profitType)) {
            // 收益型产品
            if (!StringUtil.equals(DictCodeList.是否_是.getValue(), isLay)) {
                // 非分层产品
                resultPage = this.queryForPage(page, length,
                    () -> finProductDurDealMapper.selectYqNotFcProdDetailData(pageParam));
            }
            else {
                // 分层产品
                resultPage = this.queryForPage(page, length,
                    () -> finProductDurDealMapper.selectYqFcProdDetailData(pageParam));
            }
        }
        else {
            // 净值型/货币型产品
            resultPage = this.queryForPage(page, length,
                () -> finProductDurDealMapper.selectNotYqProdDetailData(pageParam));
        }

        if (!StringUtil.isEmptyList(resultPage.getRoot())) {
            for (FinProductDurDealDetailDto resultDto : resultPage.getRoot()) {
                resultDto.setYield(yield);
            }
        }

        return resultPage;
    }

    /**
     * 查询出开放类型为‘开放式’且处理模式为'日添利','智能日添利','货币型','净值型'
     * 且到期日大于平台日的产品代码、金融产品代码、销售部门、处理模式
     *
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public List<FinProductDurDealProdDto> selectTargetProdData() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ISSUE_MODE_01", DictCodeList.开放类型_开放式.getValue());
        List<String> dealModeList = new ArrayList<String>();
        dealModeList.add(BussinessDictCodeList.处理模式_日添利.getValue());
        dealModeList.add(BussinessDictCodeList.处理模式_智能日添利.getValue());
        dealModeList.add(BussinessDictCodeList.处理模式_货币型.getValue());
        dealModeList.add(BussinessDictCodeList.处理模式_净值型.getValue());
        paramMap.put("dealModeList", dealModeList);
        paramMap.put("platDate", platformDateImpl.getSysDate());

        return finProductDurDealMapper.selectTargetProdData(paramMap);
    }

    /**
     * 查询产品申赎信息接口表数据
     *
     * @return 产品申赎信息接口表数据
     * @see
     * @since 1.0
     */
    public List<InterfProdInfoDto> selectInterfProdInfoData() {
        // 查询产品申赎信息接口表数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String platDate = DateUtil.format(platformDateImpl.getSysDate(), DateUtil.DEFAULT_OTHER_FORMAT);
        paramMap.put("platDate", platDate);

        return finProductDurDealMapper.selectInterfProdInfoData(paramMap);
    }

    /**
     * 查询已同步交易数据
     *
     * @return 已同步交易数据
     * @see
     * @since 1.0
     */
    public List<TrdFinProductDealTotal> selectSynchroedTradeData() {
        // 查询产品申赎信息接口表数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String platDate = DateUtil.format(platformDateImpl.getSysDate(), DateUtil.DEFAULT_OTHER_FORMAT);
        paramMap.put("platDate", platDate);

        return finProductDurDealMapper.selectSynchroedTradeData(paramMap);
    }

    /**
     * 设置收益率/单位净值/万份收益
     *
     * @param resultDto 查询结果dto
     * @param finCashCalcRuleData 收益型产品收益率 查询现金流计算规则表数据
     * @param bokPositionPrdDataList 产品头寸数据
     * @param finProductValuationDataList 场外金融产品估值数据
     * @see
     * @since 1.0
     */
    private void genYield(FinProductDurDealDto resultDto,
                          List<FinProductDurDealCashCalcRuleDto> finCashCalcRuleData,
                          List<BokPositionPrd> bokPositionPrdDataList,
                          List<FinProductValuation> finProductValuationDataList,
                          List<TrdProductDeal> trdProductList) {
        if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), resultDto.getProfitType())) {
            // 预期收益型产品
            if (StringUtil.isEmptyList(finCashCalcRuleData)) {
                return;
            }

            if (!StringUtil.equals(DictCodeList.是否_是.getValue(), resultDto.getIsLay())) {
                // 预期收益型不分层产品，取本产品对应的现金流计算规则表.生效利率（当前最新值）
                List<FinProductDurDealCashCalcRuleDto> tempList = finCashCalcRuleData.stream().filter(
                    x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
                         && DateUtil.compareDate(resultDto.getCashDate(),
                             x.getEffDate()) >= 0).collect(Collectors.toList());
                if (!StringUtil.isEmptyList(tempList)) {
                    resultDto.setYield(String.valueOf(tempList.get(tempList.size() - 1).getYield()));
                }
            }
            else {
                // 预期收益型分层产品，取本产品的子产品的现金流计算规则表.生效利率（当前最新值）
                List<FinProductDurDealCashCalcRuleDto> sonProdDataList = finCashCalcRuleData.stream().filter(
                    x -> StringUtil.equals(resultDto.getFinprodId(), x.getpFinprodId())
                         && DateUtil.compareDate(resultDto.getCashDate(),
                             x.getEffDate()) >= 0).collect(Collectors.toList());

                if (!StringUtil.isEmptyList(sonProdDataList)) {
                    Map<String, String> sonProdIdMap = sonProdDataList.stream().collect(
                        JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getFinprodId()));
                    String result = StringUtil.EMPTY;
                    for (String sonFinProId : sonProdIdMap.keySet()) {
                        List<FinProductDurDealCashCalcRuleDto> tempList = sonProdDataList.stream().filter(
                            x -> StringUtil.equals(sonFinProId, x.getFinprodId())).collect(
                                Collectors.toList());
                        if (!StringUtil.isEmptyList(tempList)) {
                            if (StringUtil.isNotBlank(result)) {
                                result = result + GlobalConstant.COMMA;
                            }
                            result = result + String.valueOf(tempList.get(tempList.size() - 1).getYield());
                        }
                    }
                    resultDto.setYield(result);
                }
            }
        }
        else {
        	//modify by wm 2019-07-19 按照需求要求修改取值 start 
        	/**
        	 * 1.净值型产品取交易中的净值
        	 * 2.货币型产品取现金流日期最新的万份收益
        	 */
    		if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), resultDto.getProfitType())) {
    			if (!StringUtil.isEmptyList(trdProductList)) {
    	    		String yieldTmp = StringUtil.EMPTY;
    				List<TrdProductDeal> tempDealList = trdProductList.stream().filter(
                          x -> StringUtil.equals(resultDto.getTradeId(), x.getTotleTradeId()))
    						.collect(Collectors.toList());
    				for (TrdProductDeal trdProductDeal : tempDealList) {
    					 if (StringUtil.isNotBlank(yieldTmp)) {
    						 yieldTmp = yieldTmp + GlobalConstant.COMMA;
                         }
    					 if(StringUtil.isNotEmpty(trdProductDeal.getUnitCprice())){
        					 yieldTmp = yieldTmp + String.valueOf(trdProductDeal.getUnitCprice());
    					 }
					}
    				resultDto.setYield(yieldTmp);
    			}else {
    			    Map param = new HashMap();
                    param.put("finprodid",resultDto.getFinprodId());
                    param.put("ps",resultDto.getPs());
                    param.put("confirmDate",resultDto.getConfirmDate());
                    BigDecimal yield = finProductDurDealMapper.selectYield(param);
                    resultDto.setYield(yield == null ? null : yield.toString());
                }
    		}
    		else{
    			if (!StringUtil.isEmptyList(bokPositionPrdDataList)) {
    				List<BokPositionPrd> tempBokPositionPrdList  = bokPositionPrdDataList.stream().filter(
                          x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
                          && DateUtil.compareDate(resultDto.getCashDate(),
                              x.getCdate()) >= 0).collect(Collectors.toList());
    				if(!StringUtil.isEmptyList(tempBokPositionPrdList)){
    					BokPositionPrd tempBokPositionPrd = tempBokPositionPrdList.get(tempBokPositionPrdList.size() - 1);
    					resultDto.setYield(String.valueOf(tempBokPositionPrd.getTdyYield()));
    				}
    			}
    		}
//            // 净值型/货币型产品
//            List<BokPositionPrd> tempBokPositionPrdList = null;
//            if (!StringUtil.isEmptyList(bokPositionPrdDataList)) {
//                if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), resultDto.getProfitType())) {
//                    tempBokPositionPrdList = bokPositionPrdDataList.stream().filter(
//                        x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
//                        && DateUtil.compareDate(resultDto.getCashDate(),
//                            x.getCdate()) >= 0).collect(Collectors.toList());
//                }
//                else {
//                    tempBokPositionPrdList = bokPositionPrdDataList.stream().filter(
//                        x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
//                        && DateUtil.compareDate(resultDto.getCashDate(),
//                            x.getCdate()) > 0).collect(Collectors.toList());
//                }
//            }
//            List<FinProductValuation> tempFinProductValuationDataList = null;
//            if (!StringUtil.isEmptyList(finProductValuationDataList)) {
//                if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), resultDto.getProfitType())) {
//                    tempFinProductValuationDataList = finProductValuationDataList.stream().filter(
//                        x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
//                        && DateUtil.compareDate(resultDto.getCashDate(),
//                            x.getValDate()) >= 0).collect(Collectors.toList());
//                }
//                else {
//                    tempFinProductValuationDataList = finProductValuationDataList.stream().filter(
//                        x -> StringUtil.equals(resultDto.getFinprodId(), x.getFinprodId())
//                        && DateUtil.compareDate(resultDto.getCashDate(),
//                            x.getValDate()) > 0).collect(Collectors.toList());
//                }
//            }
//
//            BokPositionPrd tempBokPositionPrd = null;
//            if (!StringUtil.isEmptyList(tempBokPositionPrdList)) {
//                tempBokPositionPrd = tempBokPositionPrdList.get(tempBokPositionPrdList.size() - 1);
//            }
//            FinProductValuation tempFinProductValuation = null;
//            if (!StringUtil.isEmptyList(tempFinProductValuationDataList)) {
//                tempFinProductValuation = tempFinProductValuationDataList.get(tempFinProductValuationDataList.size() - 1);
//            }
//            if (tempBokPositionPrd != null && tempFinProductValuation != null) {
//                if (DateUtil.compareDate(tempBokPositionPrd.getCdate(), tempFinProductValuation.getValDate()) >= 0) {
//                    resultDto.setYield(String.valueOf(tempBokPositionPrd.getTdyYield()));
//                }
//                else {
//                    resultDto.setYield(String.valueOf(tempFinProductValuation.getValuation()));
//                }
//            }
//            else if (tempBokPositionPrd != null && tempBokPositionPrd.getTdyYield() != null) {
//                resultDto.setYield(String.valueOf(tempBokPositionPrd.getTdyYield()));
//            }
//            else if (tempFinProductValuation != null) {
//                resultDto.setYield(String.valueOf(tempFinProductValuation.getValuation()));
//            }
        	//modify by wm 2019-07-19 按照需求要求修改取值 end 
        }

        // 如果收益率/单位净值/万份收益为空，且为产品起息日当天，则设置为默认值1
        if (StringUtil.isEmpty(resultDto.getYield())
            && DateUtil.isSameDate(resultDto.getCashDate(), resultDto.getProdVdate())) {
            resultDto.setYield("1");
        }
    }

    /**
     * 根据查询结果获取产品列表（分产品类型）
     *
     * @param resultList 查询结果
     * @param pageParam 参数map
     * @see
     * @since 1.0
     */
    private void genProdIdListMap(List<FinProductDurDealDto> resultList, Map<String, Object> pageParam) {
        if (StringUtil.isEmptyList(resultList)) {
            return;
        }

        // 所有产品
        Map<String, String> allProdDataMap = resultList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getProfitType()));
        if (allProdDataMap.size() < 1000) {
            pageParam.put("finprodIdList", new ArrayList<String>(allProdDataMap.keySet()));
        }

        // 收益型产品
        List<FinProductDurDealDto> syProdList = resultList.stream().filter(
            x -> StringUtil.equals(DictCodeList.收益类型_收益型.getValue(),
                x.getProfitType())).collect(Collectors.toList());
        Map<String, String> syProdDataMap = syProdList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getProfitType()));
        if (syProdDataMap.size() < 1000) {
            pageParam.put("syFinprodIdList", new ArrayList<String>(syProdDataMap.keySet()));
        }

        // 净值型/货币型产品
        List<FinProductDurDealDto> notSyProdList = resultList.stream().filter(
            x -> !StringUtil.equals(DictCodeList.收益类型_收益型.getValue(),
                x.getProfitType())).collect(Collectors.toList());
        Map<String, String> notSyProdDataMap = notSyProdList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getProfitType()));
        if (notSyProdDataMap.size() < 1000) {
            pageParam.put("notSyFinprodIdList", new ArrayList<String>(notSyProdDataMap.keySet()));
        }
        //将tradeId放入map中
        List<String> tradeIdList = new ArrayList<String>();
        for (FinProductDurDealDto finProductDurDealDto : resultList) {
        	tradeIdList.add(finProductDurDealDto.getTradeId());
		}
        pageParam.put("tradeIdList", tradeIdList);
    }
    /**
     * 计算某一天，某一只产品当天的总份额
     *
     * @param oneDayOneProdTotalDataList 某一天，某一只产品当天的交易数据
     * @return 某一天，某一只产品当天的总份额
     * @see
     * @since 1.0
     */
    private BigDecimal calcOneDayQty(List<FinProductDurDealDto> oneDayOneProdTotalDataList) {
        if (StringUtil.isEmptyList(oneDayOneProdTotalDataList)) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.ZERO;
        for (FinProductDurDealDto tradeDto : oneDayOneProdTotalDataList) {
            if (StringUtil.equals(DictCodeList.交易方向_产品到期.getValue(), tradeDto.getPs())) {
                result = ArithmeticUtil.subtract(result, tradeDto.getShareAmout());
            }
            else if (StringUtil.equals(DictCodeList.交易方向_产品赎回.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_份额强减.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_提前终止.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_产品违约赎回.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_产品提前赎回.getValue(), tradeDto.getPs())) {
                result = ArithmeticUtil.subtract(result, tradeDto.getShareAmout());
            }
            else if (StringUtil.equals(DictCodeList.交易方向_产品认购.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_产品申购.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_份额强增.getValue(), tradeDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_产品红利转投.getValue(), tradeDto.getPs())) {
//                || StringUtil.equals(DictCodeList.交易方向_产品认购.getValue(), tradeDto.getPs())
//                || StringUtil.equals(DictCodeList.交易方向_产品认购.getValue(), tradeDto.getPs())
                result = ArithmeticUtil.doAdd(result, tradeDto.getShareAmout());
            }
        }

        return result;
    }
    
    /**
     * 将最终返回结果按产品+现金流日期汇总展示
     * <一句话功能简述>
     * <功能详细描述>
     * @param resultMap
     * @return
     * @see
     * @since 1.0
     */
    private List<FinProductDurDealDto> genResultPageRoot(Map<String,List<FinProductDurDealDto>> resultMap) {
        List<FinProductDurDealDto> list  = new ArrayList<FinProductDurDealDto>();
        for(String key:resultMap.keySet()){
            FinProductDurDealDto finProductDurDealDto = new FinProductDurDealDto(); 
            BeanUtil.copyPropertiesIgnoreNull(finProductDurDealDto,resultMap.get(key).get(0));
            //认申购金额、份额汇总
            BigDecimal tdyBuyMoneyTotal = BigDecimal.ZERO;
            BigDecimal tdyBuyQtyTotal = BigDecimal.ZERO;
            //赎回金额、份额汇总
            BigDecimal tdyRedMoneyTotal = BigDecimal.ZERO;
            BigDecimal tdyRedQtyTotal = BigDecimal.ZERO;
            //红利 金额、份额汇总
            BigDecimal tdyCarriedInterestTotal = BigDecimal.ZERO;
            BigDecimal tdyCarriedMoneyTotal = BigDecimal.ZERO;
            BigDecimal tdyCarriedQtyTotal = BigDecimal.ZERO;
            //强增 金额、份额汇总
            BigDecimal tdyForceIncreaseMoneyTotal = BigDecimal.ZERO;
            BigDecimal tdyForceIncreaseQtyTotal = BigDecimal.ZERO;
            //强减 金额、份额汇总
            BigDecimal tdyForceReduceMoneyTotal = BigDecimal.ZERO;
            BigDecimal tdyForceReduceQtyTotal = BigDecimal.ZERO;
     
            for(FinProductDurDealDto dto :resultMap.get(key)){
                tdyBuyMoneyTotal = ArithmeticUtil.doAdd(tdyBuyMoneyTotal, dto.getTdyBuyMoney());
                tdyBuyQtyTotal = ArithmeticUtil.doAdd(tdyBuyQtyTotal, dto.getTdyBuyQty());
                
                tdyRedMoneyTotal = ArithmeticUtil.doAdd(tdyRedMoneyTotal, dto.getTdyRedMoney());
                tdyRedQtyTotal = ArithmeticUtil.doAdd(tdyRedQtyTotal, dto.getTdyRedQty());
                
                tdyCarriedInterestTotal = ArithmeticUtil.doAdd(tdyCarriedInterestTotal, dto.getTdyCarriedInterest());
                tdyCarriedMoneyTotal = ArithmeticUtil.doAdd(tdyCarriedMoneyTotal, dto.getTdyCarriedMoney());
                tdyCarriedQtyTotal = ArithmeticUtil.doAdd(tdyCarriedQtyTotal, dto.getTdyCarriedQty());
                
                tdyForceIncreaseMoneyTotal = ArithmeticUtil.doAdd(tdyForceIncreaseMoneyTotal, dto.getTdyForceIncreaseMoney());
                tdyForceIncreaseQtyTotal = ArithmeticUtil.doAdd(tdyForceIncreaseQtyTotal, dto.getTdyForceIncreaseQty());
                
                tdyForceReduceMoneyTotal = ArithmeticUtil.doAdd(tdyForceReduceMoneyTotal, dto.getTdyForceReduceMoney());
                tdyForceReduceQtyTotal = ArithmeticUtil.doAdd(tdyForceReduceQtyTotal, dto.getTdyForceReduceQty());
            }
            
            finProductDurDealDto.setTdyBuyMoney(tdyBuyMoneyTotal);
            finProductDurDealDto.setTdyBuyQty(tdyBuyQtyTotal);
            
            finProductDurDealDto.setTdyRedMoney(tdyRedMoneyTotal);
            finProductDurDealDto.setTdyRedQty(tdyRedQtyTotal);
            
            finProductDurDealDto.setTdyCarriedInterest(tdyCarriedInterestTotal);
            finProductDurDealDto.setTdyCarriedMoney(tdyCarriedMoneyTotal);
            finProductDurDealDto.setTdyCarriedQty(tdyCarriedQtyTotal);
            
            finProductDurDealDto.setTdyForceIncreaseMoney(tdyForceIncreaseMoneyTotal);
            finProductDurDealDto.setTdyForceIncreaseQty(tdyForceIncreaseQtyTotal);
            
            finProductDurDealDto.setTdyForceReduceMoney(tdyForceReduceMoneyTotal);
            finProductDurDealDto.setTdyForceReduceQty(tdyForceReduceQtyTotal);
            
            list.add(finProductDurDealDto);
        }
        return list;
        
    }
}

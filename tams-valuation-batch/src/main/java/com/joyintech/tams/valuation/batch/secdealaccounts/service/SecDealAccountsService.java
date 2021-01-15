/*
 * 文件名：SecDealAccountsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.secdealaccounts.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.calculation.entity.DealTypesFeeDto;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.batch.common.sect1handle.entity.DealHappenAmtDto;
import com.joyintech.tams.valuation.batch.common.sect1handle.entity.SecT1HandleParamterDto;
import com.joyintech.tams.valuation.batch.common.sect1handle.entity.SecT1HandleResultDto;
import com.joyintech.tams.valuation.batch.common.sect1handle.service.SecT1HandleService;
import com.joyintech.tams.valuation.batch.secdealaccounts.dao.SecDealAccountsMapper;
import com.joyintech.tams.valuation.batch.secdealaccounts.dto.DealParamterDto;
import com.joyintech.tams.valuation.batch.secdealaccounts.dto.StockCostDto;
import com.joyintech.tams.valuation.batch.secdealaccounts.processor.SecDealAccountsProcessor;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.service.BookAccountCommonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 债券交易账务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月4日
 * @see SecDealAccountsProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecDealAccountsService {

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 标的共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;
    

    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 计算类共通
     */
    @Autowired
    private CalculationCommonService calculationCommonService;

    /**
     * 债券共通
     */
    @Autowired
    private SecT1HandleService secT1HandleService;
    
    /**ADD BY WUJIE ON 20191111 START**/
    @Autowired
    private SecDealAccountsMapper secDealAccountsMapper;
    
    @Autowired
    private BookAccountCommonService bookAccountCommonService;
    
//    @Autowired
//    private  InterfCommonService interfCommonService;
    /**ADD BY WUJIE ON 20191111 END**/
    

    /**
     * process 数据处理
     * 
     * @param param1 参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param1.get(
            GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }
        // reader传递的参数的明细信息
        List<FinProductDealInfoComDTO> acctEntityList = acctEntity.getListFinProductDealInfoComDTO();
        // 没有明细
        if (acctEntityList == null || acctEntityList.size() == 0) {
            return null;
        }
        // 定义返回值
        BookGenerateResult returnResult = new BookGenerateResult();
        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        // 1.数据准备
        // 1.1 变量定义
        DealParamterDto param = new DealParamterDto();
        // 变量.成本科目余额
        param.setVarCostSubjectAmt(BigDecimal.ZERO);

        // 变量.成本科目数量
        param.setVarCostSubjectNum(BigDecimal.ZERO);

        // 变量.应计利息科目余额
        param.setVarLxSubjectAmt(BigDecimal.ZERO);

        // 变量.公允价值变动科目余额
        param.setVarGySubjectAmt(BigDecimal.ZERO);

        // 变量.溢折价科目余额
        param.setVarYzjSubjectAmt(BigDecimal.ZERO);

        // 变量.其他应收款-利息科目余额 = MAP（KEY：分支序号，VALUE:科目余额）
        param.setVarQtyskxSubjectAmt(new HashMap<>());
        
        // 变量.收息已结清份额 = MAP（KEY：分支序号，VALUE:科目余额）
        param.setCleanShareMap(new HashMap<>());
		
		 /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合
        param.setRetryDateMap(new HashMap<>());
        /**ADD BY WUJIE ON 20191216 END**/

        /** modify by yanya on 20191231 增加本位币处理 start**/
        String standardCcyNow = acctEntity.getStandardCcy();
        /** modify by yanya on 20191231 增加本位币处理 end**/

        // 2.调用共通查询发生日前一日余额
        quarySubjectBalance(acctEntity, param);

        // 2.6 T+1交易变量定义
        SecT1HandleParamterDto paramT1 = new SecT1HandleParamterDto();
        paramT1.setCbSubjectBalance(BigDecimal.ZERO);
        paramT1.setYjlxSubjectBalance(BigDecimal.ZERO);
        paramT1.setGyjzbdSubjectBalance(BigDecimal.ZERO);
        paramT1.setZyjSubjectBalance(BigDecimal.ZERO);
        paramT1.setNumBalance(BigDecimal.ZERO);
        paramT1.setT1BuyDeal(new HashMap<>());
        paramT1.setT1SellDeal(new HashMap<>());

        // 3.处理交易
        // 3.1 投资目的 = 公允价值的情况
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            quaryFvtpl(returnResult, acctEntity, param, paramT1);
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            // 3.2投资目的 = 摊余成本的情况
            quaryAc(returnResult, acctEntity, param, paramT1);
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            // 3.2投资目的 = 摊余成本的情况
            quaryFvoci(returnResult, acctEntity, param, paramT1);
        }

        return returnResult;
    }

    /**
     * 获取标的信息
     * 
     * @param dealNow 交易明细
     * @return TargetFinProduct
     * @see
     * @since 1.0
     */
    private TargetFinProduct getTargetFinProduct(FinProductDealInfoComDTO dealNow) {
        // 1.2.标的类金融产品信息
        // 查询条件
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        // 标的信息条件
        TargetSearchDto searchBaseinfo = new TargetSearchDto();
        searchBaseinfo.setQueryType(ValDictCodeList.标的_信息.getValue());
        queryCriteria.add(searchBaseinfo);

        Map<TargetSearchDto, Object> targetSearchMap = finProductCommonService.getTargetInfo(
            dealNow.getFinprodId(), queryCriteria);

        // 变量.标的信息 = 上述共通返回标的信息
        TargetFinProduct rstBaseinfo = (TargetFinProduct)targetSearchMap.get(searchBaseinfo);
        return rstBaseinfo;
    }

    /**
     * 公允价值的情况场景处理
     * 
     * @param returnResult 场景返回结果
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtpl(BookGenerateResult returnResult, FinProductDealInfoResult acctEntity,
                            DealParamterDto param, SecT1HandleParamterDto paramT1) {

        // 账套核算参数查询
        // 定义账套参数查询参数项list
        List<String> paramCodeList = new ArrayList<String>();
        paramCodeList.add(ValDictCodeList.账套配置表参数项_债券交易费用损益计入方式.getValue());// 债券交易费用损益计入方式
        Map<String, Object> getValBokBooksetExtMap = accountingCommonService.getValBokBooksetExtForMap(
            acctEntity.getBooksetId(), paramCodeList);
        // 定义 变量.费用损益计入方式
        String varSecDealFeeSubject = (String)getValBokBooksetExtMap.get(
            ValDictCodeList.账套配置表参数项_债券交易费用损益计入方式.getValue());// 债券交易费用损益计入方式

        // 交易明细 ：交易类账务处理结果.交易明细列表
        for (FinProductDealInfoComDTO dealNow : acctEntity.getListFinProductDealInfoComDTO()) {
            TargetFinProduct rstBaseinfo = getTargetFinProduct(dealNow);
            if (rstBaseinfo == null) {
                return;
            }
            // 交易方向 = "买入"
            if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
            	quaryFvtplApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
            			varSecDealFeeSubject);
            } else if(DictCodeList.交易方向_申购确认.getValue().equals(dealNow.getPs())){
            	quaryFvtplPurchaseConfirmation(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
            			varSecDealFeeSubject);
            }
            else if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())) {
                quaryFvtplBuy(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    varSecDealFeeSubject);
            }
            else if (DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
                quaryFvtplSell(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    varSecDealFeeSubject);
            }
//            else if (DictCodeList.交易方向_行权.getValue().equals(dealNow.getPs())) {
//                quaryFvtplSell(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
//                    varSecDealFeeSubject);
//            }
            else if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                // quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_收息到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplIntAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // quaryFvtplRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplRedAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeListExt1.交易方向_债转股.getValue().equals(dealNow.getPs())){
            	quaryFvtplDebtToEquity(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
//            else if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
//            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
//            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
//                quaryFvtplApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
//                    varSecDealFeeSubject);
//            }
            else if (DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())) {
                quaryFvtplRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    varSecDealFeeSubject, DictCodeListTableId.会计场景代码_公允价值损益类债券_行权回售.getValue());
            }
            else if (DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())) {
                quaryFvtplRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    varSecDealFeeSubject, DictCodeListTableId.会计场景代码_公允价值损益类债券_行权赎回.getValue());
            }
            //交易方向_应收款项转入 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转入.getValue().equals(dealNow.getPs())) {
                this.getReceivablesIn(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            //交易方向_应收款项转出 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转出.getValue().equals(dealNow.getPs())) {
                this.getReceivablesOut(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
        }
    }

    /**
     * 公允价值类的情况场景处理(债转股)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtplDebtToEquity(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param) {
    	// 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_债转股.getValue());

        // 调用 共通【股票成本处理共通】  
        StockCostDto stockCostDto = this.stockCostProcessingCommon(dealNow.getTradeId(), dealNow.getBooksetId());
        // accountSceneMap设置
        //股票账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,stockCostDto.getBokDetailId());
        //股票核算分类
        accountSceneMap.put(BookConstant.SHARE_ORG_VALCLASS,stockCostDto.getType2());
        
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,stockCostDto.getBokDetailId());
        
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");
        
        // accountAmountMap设置如下：																		
        // 定义 	变量.股票成本  =	0	
        BigDecimal stockCost = BigDecimal.ZERO;
        // 定义 	变量.股票数量  =  0		
        BigDecimal stockNum = BigDecimal.ZERO;
        // 定义 	变量.冲减公允价值变动	 = 0	
        BigDecimal varCjgyjzbdSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.冲减应计利息 = 0		
        BigDecimal varCjyslxSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.冲减数量 = 交易份额	
        BigDecimal varCjslSubjectNum = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 定义 	变量.冲减成本 = 0		
        BigDecimal varCjcbSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.买卖价差 = 0			
        BigDecimal varMmjc =  BigDecimal.ZERO;
        
        // 变量.股票成本 = DTO.股票成本																									
        stockCost =  stockCostDto.getCost();
        // 变量.股票数量 = DTO.股票数量																														
        stockNum = 	stockCostDto.getNum();																															
        // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjcbSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarCostSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																
        // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjgyjzbdSubjectAmt =  ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarGySubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																	
        // 变量.冲减应计利息  = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjyslxSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply( param.getVarLxSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																	
        // 股票成本   <-	变量.股票成本																							
        accountAmountMap.put(BookConstant.COST_SHARE, stockCost);																																	
        // 成本金额  <-	变量.冲减成本																							
        accountAmountMap.put(BookConstant.COST_BOND, varCjcbSubjectAmt);																													
        // 应计利息  <-	变量.冲减应计利息																							
        accountAmountMap.put(BookConstant.INTAMT, varCjyslxSubjectAmt);		
        // 实收金额  <-  交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
        // 变量.买卖价差	= 交易明细.交易金额 + 变量.股票成本 - (变量.冲减成本 +变量.冲减应计利息) 																				
        varMmjc = ArithmeticUtil.doSub(
        					ArithmeticUtil.doAdd(dealNow.getTradeAmt(), stockCost),
        					ArithmeticUtil.doAdd(varCjcbSubjectAmt,varCjyslxSubjectAmt));
        // if（变量.买卖价差>= 0）
        if(varMmjc.compareTo(BigDecimal.ZERO) >= 0){
        	// 买卖价差（贷） <-	变量.买卖价差			
        	accountAmountMap.put(BookConstant.NETAMT_C,
                    ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        } else {
        	// 买卖价差（借）<-	变量.买卖价差*-1				
        	 accountAmountMap.put(BookConstant.NETAMT_D, ArithmeticUtil.round(
                     ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }
		// if(变量.冲减公允价值变动 >= 0 )	
        if (varCjgyjzbdSubjectAmt.compareTo(BigDecimal.ZERO) >= 0) {
        	// 公允价值变动(贷)   <-		变量.冲减公允价值变动						
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(varCjgyjzbdSubjectAmt, NumberEnum.MONEY));
            // 冲减公允价值变动(借)	 <-		变量.冲减公允价值变动		
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                    ArithmeticUtil.round(varCjgyjzbdSubjectAmt, NumberEnum.MONEY));
        }
        else {
        	// 公允价值变动(借)   <-		变量.冲减公允价值变动 * -1							
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjgyjzbdSubjectAmt, new BigDecimal(-1)), NumberEnum.MONEY));
        	// 冲减公允价值变动(贷)   <-		变量.冲减公允价值变动 * -1		
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(varCjgyjzbdSubjectAmt, new BigDecimal(-1)), NumberEnum.MONEY));
        }
      		
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.公允价值变动(贷)_本位币 = round(变量.公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 公允价值变动贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(varCjgyjzbdSubjectAmt, 汇率),
//                NumberEnum.MONEY);
        //定义		变量.冲减公允价值变动（借)_本位币 = round(变量.变量.冲减公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 冲减公允价值变动借本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(ArithmeticUtil.round(varCjgyjzbdSubjectAmt, NumberEnum.MONEY), 汇率),
//                NumberEnum.MONEY);
        /** 定义 变量.买卖价差_本位币	=	(股票成本_本位币 + 实收金额_本位币 + 变量.冲减公允价值变动（借)_本位币)
        - (成本金额_本位币 + 应计利息_本位币 + 变量.公允价值变动(贷)_本位币) **/
        BigDecimal 买卖价差1 = ArithmeticUtil.doAdd(accountAmountBCCYAMTMap.get(BookConstant.COST_SHARE + BookConstant.BCCYAMT),
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT));
        BigDecimal 买卖价差2 = ArithmeticUtil.doAdd(accountAmountBCCYAMTMap.get(BookConstant.COST_BOND + BookConstant.BCCYAMT),
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));
        BigDecimal 买卖价差 = ArithmeticUtil.doSub(买卖价差1, 买卖价差2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币 <-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.NETAMT_C  + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D  + BookConstant.BCCYAMT, standardCcyNow);
        }
        else {
            //买卖价差（借）_本位币 <-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.NETAMT_D  + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D  + BookConstant.BCCYAMT, standardCcyNow);
        }

        /** modify by yanya on 20191231 增加本位币处理 end**/
        
        // 基础科目性质												
        Map<String,Map<String,Object>> accountNatrueMap = new HashMap<>();												
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();												
        accountNatrueBranchMap.put(BookConstant.HAPPEN_NUMBER,stockNum);												
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.股票_成本.getValue(),accountNatrueBranchMap);												
        ad.setAccountNatrueMap(accountNatrueMap);	
        
        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        
        											

        // 全局变量的更新																								
        // 变量.成本科目余额 	 =	变量.成本科目余额 - 变量.冲减成本		
        	param.setVarCostSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarCostSubjectAmt(),varCjcbSubjectAmt));
        // 变量.成本科目数量    =	变量.成本科目数量 - 变量.冲减数量			
        	param.setVarCostSubjectNum(
        			ArithmeticUtil.doSub(param.getVarCostSubjectNum(),varCjslSubjectNum));
        // 变量.应计利息科目余额	 =	变量.应计利息科目余额 - 变量.冲减应计利息	
        	param.setVarLxSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarLxSubjectAmt(),varCjyslxSubjectAmt));
        // 变量.公允价值变动科目余额     =	变量.公允价值变动科目余额 - 变量.冲减公允价值变动														
        	param.setVarGySubjectAmt(
        			ArithmeticUtil.doSub(param.getVarGySubjectAmt(),varCjgyjzbdSubjectAmt));
	}

	/**
     * 公允价值的情况场景处理(申购确认)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvtplPurchaseConfirmation(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param,
			SecT1HandleParamterDto paramT1, String varSecDealFeeSubject) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_申购确认.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 发生数量	 <-	交易明细.交易份额	
        accountAmountMap.put(BookConstant.ACCQUA, 
        		ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.MONEY));
        // 成本金额     <-	交易明细.交易本金	
        accountAmountMap.put(BookConstant.FACEVALUE, 
        		ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
        // 交易金额     <-	交易明细.全价总额				
        accountAmountMap.put(BookConstant.FACEVALUE,
                ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));
        
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //成本金额_本位币						<-		成本金额 * 变量.汇率
        BigDecimal 成本金额本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.FACEVALUE), 汇率),
                NumberEnum.MONEY);
        accountAmountBCCYAMTMap.put(BookConstant.FACEVALUE + BookConstant.BCCYAMT, 成本金额本位币);
        //交易金额						<-		成本金额_本位币

        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        /** modify by yanya on 20191231 增加本位币处理 end**/
        
        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 	 =	变量.成本科目余额 + 交易明细.交易本金	
        param.setVarCostSubjectAmt(
        		ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),dealNow.getPrinAmt()));
        // 变量.成本科目数量    =	变量.成本科目数量 + 交易明细.交易份额										
        param.setVarCostSubjectNum(
                ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
	}

	/**
     * 公允价值的情况场景处理(还本到账)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtplRedAcc(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                  FinProductDealInfoComDTO dealNow,
                                  FinProductDealInfoResult acctEntity, DealParamterDto param) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_公允价值损益类债券还本.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + DictCodeList.交易方向_还本到账.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_摊余成本类债券还本.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + DictCodeList.交易方向_还本到账.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_综合收益类债券还本.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "综合收益类债券资产" + DictCodeList.交易方向_还本到账.getCodeName() + "交易登账");
        }

        // 资金账户类型
        accountSceneMap.put(BookConstant.ACCT_TYPE, dealNow.getAcctType());
        // accountSceneMap设置
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));

        // accountAmountMap设置
        // 交易金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if(dealNow.getTradeDate().compareTo(dealNow.getMdate()) < 0 &&
                (DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                        || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            accountAmountMap.put(BookConstant.DEALAMT_C,
                    ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));
//            accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
//                    bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
            accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                    bookCommonLogic.getValBokDetailId(dealNow.getMyAcctNo(), null, dealNow.getAcctType(),dealNow.getCcy()));
        }else{
            // 交易本金 <- 交易明细.全价总额
            accountAmountMap.put(BookConstant.RECEITAMT,
                    ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));
        }
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //交易金额_本位币 <-	交易本金_本位币 + 实付通道费_本位币
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if(dealNow.getTradeDate().compareTo(dealNow.getMdate()) < 0 &&
                (DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                        || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            BigDecimal 交易金额本位币 = ArithmeticUtil.doAdd(
                    accountAmountBCCYAMTMap.get(BookConstant.DEALAMT_C + BookConstant.BCCYAMT),//交易本金_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位币
            );
            accountAmountMap.put(BookConstant.DEALAMT_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(交易金额本位币, NumberEnum.MONEY));
        }else{
            BigDecimal 交易金额本位币 = ArithmeticUtil.doAdd(
                    accountAmountBCCYAMTMap.get(BookConstant.RECEITAMT + BookConstant.BCCYAMT),//交易本金_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位币
            );
            accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(交易金额本位币, NumberEnum.MONEY));
        }
        accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);
        // 基础科目性质
        Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();
        accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(),
            accountNatrueBranchMap);
        ad.setAccountNatrueMap(accountNatrueMap);
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
    }

    /**
     * 公允价值的情况场景处理(收息到账)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtplIntAcc(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                  FinProductDealInfoComDTO dealNow,
                                  FinProductDealInfoResult acctEntity, DealParamterDto param) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_公允价值损益类债券收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + DictCodeList.交易方向_收息到账.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_摊余成本类债券收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + DictCodeList.交易方向_收息到账.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_公允价值综合收益类债券收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "综合收益类债券资产" + DictCodeList.交易方向_收息到账.getCodeName() + "交易登账");
        }

        // accountSceneMap设置

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 定义 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
        BigDecimal qtyskxSubjectAmt = param.getVarQtyskxSubjectAmt().get(
            String.valueOf(dealNow.getBranch()));
        
        // //定义 变量.收息已结清份额汇总  = 变量.收息已结清份额MAP.get(交易明细.分支序号)
        BigDecimal totalShareAmtClean = param.getCleanShareMap().get(
            String.valueOf(dealNow.getBranch()));

        // 查询交易的到期日对应的应计利息,从以下代码移出来（if (qtyskxSubjectAmt == null) {） modify by yanya on 20191218
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(dealNow.getBooksetId());
        searchDao.setBokDetailId(dealNow.getBokDetailId());
        searchDao.setHappenDate(dealNow.getHappenDate());
        searchDao.setBookDate(dealNow.getBookDate());
        searchDao.setTaskPri(dealNow.getTaskPri());
        searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue())));
        searchDao.setBranch(dealNow.getBranch());
        List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        
        SubjectBalance subjectBalance = new SubjectBalance();
        subjectBalance.setBalance(BigDecimal.ZERO);
        if(!StringUtil.isEmptyList(listSubjectBalance)) {
            subjectBalance = listSubjectBalance.get(0);
//            totalShareAmtClean = subjectBalance.getShareAmtClean();
        }
        // if（变量.其他应收款-利息科目余额 == null）
        if (totalShareAmtClean == null) {
            // 变量.收息已结清份额汇总 = 变量.收息明细返回的科目余额.收息已结清份额累计;
            totalShareAmtClean = subjectBalance.getShareAmtClean();
            param.getCleanShareMap().put(String.valueOf(dealNow.getBranch()),
                totalShareAmtClean);
        }
        
        if (qtyskxSubjectAmt == null) {
            //if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.应计利息余额 = 科目余额
            qtyskxSubjectAmt = subjectBalance.getBalance();
            param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()),
                    qtyskxSubjectAmt);
            //}
        }
        
        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));

        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // accountAmountMap设置
        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));


        // 定义 变量.应计利息 = 0
        BigDecimal varInterestAccBal = BigDecimal.ZERO;
        // 交易明细.是否结清 = "否"
        if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
            // 变量.应计利息 = 交易明细.全价总额
            varInterestAccBal = dealNow.getFpriceAmt();
        }
        else {
            /** modify by yanya on 20191203 收息到账如果是全部结清、且计提份额存在值 && 账套参数.是否计提份额占比收息 = '01' 情况下，需要计算下占比**/
            //获取账套参数
            List<BokBooksetExt> listBooksetExt = accountingCommonService.getValBokBooksetExt(dealNow.getBooksetId(),
                    Arrays.asList(ValDictCodeList.账套配置表参数项_计提份额占比收息.getValue()));
            //if(交易明细.计提份额 != 0){
            if(dealNow.getAccrNum() != null && BigDecimal.ZERO.compareTo(dealNow.getAccrNum()) != 0
             && !StringUtil.isEmptyList(listBooksetExt)
                    && DictCodeListExt3.计提份额占比收息_按占比收息.getValue().equals(listBooksetExt.get(0).getParamValue())) {
                BigDecimal accountSubjectNum = accountingCommonService.findBondActualCostNum(dealNow.getBooksetId(), dealNow.getBookDate(),
                		DateUtil.addDay(dealNow.getMdate() ,-1), dealNow.getBokDetailId(), dealNow.getTradeMarket(),
                        dealNow.getInvAim(), null);

                /**ADD BY WUJIE ON 20200318 START**/
                BigDecimal 份额差额 = ArithmeticUtil.doSub(accountSubjectNum,dealNow.getAccrNum(),totalShareAmtClean);
                if(ArithmeticUtil.equals(份额差额, BigDecimal.ZERO)){
                    // 变量.应计利息 = 变量.其他应收款-利息科目余额
                    varInterestAccBal = qtyskxSubjectAmt;
                }
                else{
                /**ADD BY WUJIE ON 20200318 END**/
                	
                	//变量.应计利息 = 变量.其他应收款-利息科目余额 * 交易明细.计提份额 / (变量.账目科目数量 - 变量.收息已结清份额汇总)
                    varInterestAccBal = ArithmeticUtil.divide(ArithmeticUtil.multiply(qtyskxSubjectAmt, dealNow.getAccrNum()),
                            ArithmeticUtil.subtract(accountSubjectNum, totalShareAmtClean));
                
                /**ADD BY WUJIE ON 20200318 START**/
                }
                // 更新 收息已结清份额
                totalShareAmtClean = ArithmeticUtil.round(ArithmeticUtil.doAdd(dealNow.getAccrNum(), totalShareAmtClean), NumberEnum.MONEY);
                /**ADD BY WUJIE ON 20200318 END**/
            }
            else {
                // 变量.应计利息 = 变量.其他应收款-利息科目余额
                varInterestAccBal = qtyskxSubjectAmt;
            }
        }
        //ccountAmountMap再设置如下：收息已结清份额 <- 交易明细.计提份额 + 收息已结清份额汇总
        // 更新 收息已结清份额
        totalShareAmtClean = ArithmeticUtil.round(ArithmeticUtil.doAdd(dealNow.getAccrNum(), totalShareAmtClean), NumberEnum.MONEY);
        accountAmountMap.put(BookConstant.SHARE_AMT_CLEAN, totalShareAmtClean);
        // 应计利息 <-变量.应计利息
        accountAmountMap.put(BookConstant.INTAMT,
            ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY));

        // 交易明细.全价总额 - 变量.应计利息 >= 0
        BigDecimal varTradeAmt = ArithmeticUtil.subtract(dealNow.getFpriceAmt(),
            varInterestAccBal);
        if (varTradeAmt.compareTo(BigDecimal.ZERO) >= 0) {
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
            if(dealNow.getTradeDate().compareTo(dealNow.getMdate()) < 0 &&
                    (DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                            || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
                // 利息差额(贷) <-交易明细.全价总额 - 变量.应计利息
                accountAmountMap.put(BookConstant.DEALAMT_C,
                        ArithmeticUtil.round(varTradeAmt, NumberEnum.MONEY));
//                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
//                        bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                        bookCommonLogic.getValBokDetailId(dealNow.getMyAcctNo(), null, dealNow.getAcctType(),dealNow.getCcy()));
            }else{
                // 利息差额(贷) <-交易明细.全价总额 - 变量.应计利息
                accountAmountMap.put(BookConstant.NETAMT_C,
                        ArithmeticUtil.round(varTradeAmt, NumberEnum.MONEY));
            }
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        }
        else {
            // 利息差额(借) <- 交易明细.全价总额 - 变量.应计利息 * -1
            accountAmountMap.put(BookConstant.NETAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varTradeAmt, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下：
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.利息差额_本位币	 = 实收金额_本位币 + 实付通道费_本位 - 应计利息_本位币
        BigDecimal 利息差额本位币 = ArithmeticUtil.doSub(
                ArithmeticUtil.doAdd(
                    accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//实收金额_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位
                ),
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));//应计利息_本位币
        //if（变量.买卖价差_本位币>= 0）
        if(利息差额本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
            if(dealNow.getTradeDate().compareTo(dealNow.getMdate()) < 0 &&
                    (DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                            || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
                //利息差额（借）_本位币	<-	变量.利息差额_本位币
                accountAmountMap.put(BookConstant.DEALAMT_C + BookConstant.BCCYAMT,
                        ArithmeticUtil.round(利息差额本位币, NumberEnum.MONEY));
                accountCurrencyMap.put(BookConstant.DEALAMT_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
            }else{
                //利息差额（借）_本位币	<-	变量.利息差额_本位币
                accountAmountMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT,
                        ArithmeticUtil.round(利息差额本位币, NumberEnum.MONEY));
                accountCurrencyMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
            }
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        }
        else {
            //利息差额（贷）_本位币	<-	变量.利息差额_本位币*-1
            accountAmountMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(利息差额本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);
        // 基础科目性质
        Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();
        accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(),
            accountNatrueBranchMap);
        ad.setAccountNatrueMap(accountNatrueMap);
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        qtyskxSubjectAmt = ArithmeticUtil.round(qtyskxSubjectAmt, NumberEnum.MONEY);
        varInterestAccBal = ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 变量.应计利息
        qtyskxSubjectAmt = ArithmeticUtil.subtract(qtyskxSubjectAmt, varInterestAccBal);
        // 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
        param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);
        // 将变更后的收息已结清份额更新到变量.收息已结清份额MAP中
        param.getCleanShareMap().put(String.valueOf(dealNow.getBranch()), totalShareAmtClean);
    }

    /**
     * 公允价值的情况场景处理(卖出)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvtplSell(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                FinProductDealInfoComDTO dealNow,
                                FinProductDealInfoResult acctEntity, DealParamterDto param,
                                SecT1HandleParamterDto paramT1, String varSecDealFeeSubject) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_卖出.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + DictCodeList.交易方向_卖出.getCodeName() + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        Date settleDate = null;
        /** modify by yanyan 在途日期根据设计调整 20200102 start**/
        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
            //settleDate = dealNow.getSettleDate();
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else if(DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket())) {
            //在途日期		<-		交易明细.交易日 + 1
            settleDate = DateUtil.addDay(dealNow.getTradeDate(), 1);
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else {
            //accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        /** modify by yanyan 在途日期根据设计调整 20200102 end **/

        // accountAmountMap设置
        // 变量.冲减公允价值变动
        BigDecimal cjGyJzBd = BigDecimal.ZERO;
        // 变量.冲减应计利息
        BigDecimal cjYjLx = BigDecimal.ZERO;
        // 变量.冲减数量
        BigDecimal cjSl = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 变量.冲减成本
        BigDecimal cjCb = BigDecimal.ZERO;
        // 变量.利息差额
        BigDecimal lxCe = BigDecimal.ZERO;
        // 变量.买卖价差
        BigDecimal mmJc = BigDecimal.ZERO;

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
                dealNow, param, paramT1, settleDate);
            // 变量.冲减成本 = 变量.T+1处理结果集.成本科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            cjCb = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getCbSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减公允价值变动 = 变量.T+1处理结果集.公允价值变动科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            cjGyJzBd = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getGyjzbdSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            cjYjLx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
        }
        else {
            // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjCb = ArithmeticUtil.multiply(param.getVarCostSubjectAmt(), cjSl);
            cjCb = ArithmeticUtil.divide(cjCb, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjGyJzBd = ArithmeticUtil.multiply(param.getVarGySubjectAmt(), cjSl);
            cjGyJzBd = ArithmeticUtil.divide(cjGyJzBd, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());

            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYjLx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
        }
        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            cjYjLx = dealNow.getIntAmt();
        }
        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 实收金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

        // 定义变量.买卖价差
        BigDecimal varMmPrice = BigDecimal.ZERO;

        // 变量.费用损益计入方式 = '计入投资收益'情况
        if (DictCodeList.债券交易费用损益计入方式_计入投资收益.getValue().equals(varSecDealFeeSubject)) {
            // 变量.买卖价差 <- 0
            varMmPrice = BigDecimal.ZERO;
        }
        else {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            BigDecimal VAR_STAMPTAX_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getStampDuty(),
                dealTypesFeeDto.getStampDutyForDealPay());
            accountAmountMap.put(BookConstant.STAMPTAX_OUT, VAR_STAMPTAX_OUT);
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            BigDecimal VAR_HANDFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getBrokerage(),
                dealTypesFeeDto.getBrokerageForDealPay());
            accountAmountMap.put(BookConstant.HANDFEE_OUT, VAR_HANDFEE_OUT);
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            BigDecimal VAR_CERTIFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getCertificateFee(),
                dealTypesFeeDto.getCertificateFeeForDealPay());
            accountAmountMap.put(BookConstant.CERTIFEE_OUT, VAR_CERTIFEE_OUT);
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            BigDecimal VAR_SETTLEFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getClearingFee(),
                dealTypesFeeDto.getClearingFeeForDealPay());
            accountAmountMap.put(BookConstant.SETTLEFEE_OUT, VAR_SETTLEFEE_OUT);
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            BigDecimal VAR_COMMISSION_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getCommission(),
                dealTypesFeeDto.getCommissionForDealPay());
            accountAmountMap.put(BookConstant.COMMISSION_OUT, VAR_COMMISSION_OUT);
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            BigDecimal VAR_TRADEFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getTransactionFee(),
                dealTypesFeeDto.getTransactionFeeForDealPay());
            accountAmountMap.put(BookConstant.TRADEFEE_OUT, VAR_TRADEFEE_OUT);
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            BigDecimal VAR_SETTLEMENTFEE_OUT = ArithmeticUtil.doAdd(
                dealTypesFeeDto.getSettlementFee(), dealTypesFeeDto.getSettlementFeeForDealPay());
            accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT, VAR_SETTLEMENTFEE_OUT);

            // 变量.买卖价差 <- 印花税支出 + 经手费支出 + 证管费支出 + 结算费支出 + 佣金支出 + 交易手续费支出 + 结算手续费支出
            varMmPrice = ArithmeticUtil.doAdd(VAR_STAMPTAX_OUT, VAR_HANDFEE_OUT, VAR_CERTIFEE_OUT,
                VAR_SETTLEFEE_OUT, VAR_COMMISSION_OUT, VAR_TRADEFEE_OUT, VAR_SETTLEMENTFEE_OUT);
        }

        // 成本金额
        accountAmountMap.put(BookConstant.FACEVALUE, ArithmeticUtil.round(cjCb, NumberEnum.MONEY));
        // 应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(cjYjLx, NumberEnum.MONEY));
        // 利息差额
        lxCe = ArithmeticUtil.subtract(dealNow.getIntAmt(), cjYjLx);

        // 变量.买卖价差 = 变量.买卖价差 + 交易明细.交易金额 - 变量.冲减成本 - 应计利息 - 变量.交易各种费用.不随交易费用 + 变量.交易各种费用.通道费
        mmJc = ArithmeticUtil.subtract(ArithmeticUtil.doAdd(varMmPrice, dealNow.getTradeAmt()),
            cjCb);
        mmJc = ArithmeticUtil.subtract(mmJc, cjYjLx);
        mmJc = ArithmeticUtil.subtract(mmJc, dealTypesFeeDto.getNoFeeAmt());
        mmJc = ArithmeticUtil.doAdd(mmJc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        mmJc = ArithmeticUtil.subtract(mmJc, lxCe);

        // 变量.利息差额 >= 0
        if (lxCe.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <-变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(lxCe, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(lxCe, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (mmJc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(mmJc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(mmJc, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));

        /** add by yanya on 20200228 通道费账务明细代码**/
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, dealTypesFeeDto.getChlFeeCode());

        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 变量.冲减公允价值变动 >= 0
        if (cjGyJzBd.compareTo(BigDecimal.ZERO) >= 0) {
            // 公允价值变动(贷) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY));
            // 冲减公允价值变动(借) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY));
        }
        else {
            // 公允价值变动(借) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyJzBd, new BigDecimal(-1)), NumberEnum.MONEY));
            // 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyJzBd, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //本位币金额元素都放到金额元素map中
        accountCurrencyMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(ArithmeticUtil.round(lxCe, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义 变量.公允价值变动(贷)_本位币 = round(变量.公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 公允价值变动贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY), 汇率),
//                NumberEnum.MONEY);
        //定义 变量.变量.冲减公允价值变动（借)_本位币 = round(变量.变量.冲减公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 冲减公允价值变动借本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY), 汇率),
//                NumberEnum.MONEY);
        /** 定义 变量.买卖价差_本位币	=	(交易金额_本位币 + 变量.变量.冲减公允价值变动（借)_本位币 + 实付通道费_本位币 + 结算手续费支出 + 印花税支出_本位币
                + 经手费支出_本位币 + 证管费支出_本位币 + 结算费支出_本位币 + 佣金支出_本位币 + 交易手续费支出)
        - 	(成本金额_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 + 变量.公允价值变动(贷)_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//交易金额_本位币
                //冲减公允价值变动借本位币,//冲减公允价值变动（借)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT),//实付通道费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT + BookConstant.BCCYAMT),//结算费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT + BookConstant.BCCYAMT),//印花税支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT + BookConstant.BCCYAMT),//经手费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT + BookConstant.BCCYAMT),//证管费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT + BookConstant.BCCYAMT),//结算手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT + BookConstant.BCCYAMT),//佣金支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT + BookConstant.BCCYAMT));//交易手续费支出_本位币
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                //公允价值变动贷本位币,//变量.公允价值变动(贷)_本位币
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));//利息金额_本位币
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币 <- 变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（贷）_本位币<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        cjCb = ArithmeticUtil.round(cjCb, NumberEnum.MONEY);
        cjSl = ArithmeticUtil.round(cjSl, NumberEnum.QTY);
        cjYjLx = ArithmeticUtil.round(cjYjLx, NumberEnum.MONEY);
        cjGyJzBd = ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), cjCb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), cjSl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), cjYjLx));
        // 变量.公允价值变动科目余额 = 变量.公允价值变动科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.subtract(param.getVarGySubjectAmt(), cjGyJzBd));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生公允价值变动 -= 变量.冲减公允价值变动
            paramT1.setGyjzbdSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getGyjzbdSubjectBalance(), cjGyJzBd));
            // 变量.T+1发生应计利息 -= 变量.冲减应计利息
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getYjlxSubjectBalance(), cjYjLx));
            // 变量.T+1发生成本 -= 变量.冲减成本
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getCbSubjectBalance(), cjCb));
            // 变量.T+1发生数量 -= 变量.冲减数量
            paramT1.setNumBalance(ArithmeticUtil.subtract(paramT1.getNumBalance(), cjSl));
            // 变量.发生额集 = 变量.T+1买入交易.get（交易明细.交割日）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1SellDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1SellDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生公允价值变动 += 变量.冲减公允价值变动
            dealHappenAmtDto.setHappenGujzbd(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenGujzbd(), cjGyJzBd));
            // 变量.发生额集.发生应计利息 += 变量.冲减应计利息
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), cjYjLx));
            // 变量.发生额集.发生成本 += 变量.冲减成本
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), cjCb));
            // 变量.发生额集.发生数量 += 变量.冲减数量
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), cjSl));
            paramT1.getT1SellDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
    }

    /**
     * 公允价值的情况场景处理(买入)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvtplBuy(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                               FinProductDealInfoComDTO dealNow,
                               FinProductDealInfoResult acctEntity, DealParamterDto param,
                               SecT1HandleParamterDto paramT1, String varSecDealFeeSubject) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 成本金额
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
        // 利息金额
        accountAmountMap.put(BookConstant.INTAMT,
            ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));
        // 交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        // accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());

        // 变量.费用损益计入方式 = '计入投资收益'情况
        if (DictCodeList.债券交易费用损益计入方式_计入投资收益.getValue().equals(varSecDealFeeSubject)) {
            // 交易费用 <- 变量.交易各种费用.交易费用
            accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            accountAmountMap.put(BookConstant.STAMPTAX_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getStampDuty(), dealTypesFeeDto.getStampDutyForDealPay()));
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            accountAmountMap.put(BookConstant.HANDFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getBrokerage(), dealTypesFeeDto.getBrokerageForDealPay()));
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            accountAmountMap.put(BookConstant.CERTIFEE_OUT,
                ArithmeticUtil.doAdd(dealTypesFeeDto.getCertificateFee(),
                    dealTypesFeeDto.getCertificateFeeForDealPay()));
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            accountAmountMap.put(BookConstant.SETTLEFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getClearingFee(), dealTypesFeeDto.getClearingFeeForDealPay()));
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            accountAmountMap.put(BookConstant.COMMISSION_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getCommission(), dealTypesFeeDto.getCommissionForDealPay()));
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            accountAmountMap.put(BookConstant.TRADEFEE_OUT,
                ArithmeticUtil.doAdd(dealTypesFeeDto.getTransactionFee(),
                    dealTypesFeeDto.getTransactionFeeForDealPay()));
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getSettlementFee(), dealTypesFeeDto.getSettlementFeeForDealPay()));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);

        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        /** 交易金额_本位币	<-	(印花税支出_本位币 + 经手费支出_本位币 + 证管费支出_本位币 + 结算费支出_本位币 + 佣金支出_本位币 + 交易手续费支出
         + 结算手续费支出 + 成本金额_本位币 + 利息金额_本位币)
         -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
         + 结算手续费_本位币)
         **/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT + BookConstant.BCCYAMT),//印花税支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT + BookConstant.BCCYAMT),//经手费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT + BookConstant.BCCYAMT),//证管费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT + BookConstant.BCCYAMT),//结算费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT + BookConstant.BCCYAMT),//佣金支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT + BookConstant.BCCYAMT),//交易手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT + BookConstant.BCCYAMT),//结算手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));//利息金额_本位币

        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT));//结算手续费_本位币
        BigDecimal 交易金额本位币 = ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2);
        accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(交易金额本位币, NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20191231 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 =变量.成本科目余额 + 交易明细.交易本金
        param.setVarCostSubjectAmt(
            ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(), dealNow.getPrinAmt()));
        // 变量.成本科目数量=变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.应计利息科目余额=变量.应计利息科目余额 + 交易明细.利息总额
        param.setVarLxSubjectAmt(
            ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), dealNow.getIntAmt()));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生应计利息 += 交易明细.利息总额
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getYjlxSubjectBalance(), dealNow.getIntAmt()));
            // 变量.T+1发生成本 += 交易明细.交易本金
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getCbSubjectBalance(), dealNow.getPrinAmt()));
            // 变量.T+1发生数量 += 交易明细.交易份额
            paramT1.setNumBalance(
                ArithmeticUtil.doAdd(paramT1.getNumBalance(), dealNow.getShareAmt()));

            // 变量.发生额集 = 变量.T+1买入交易.get（场景元素.在途日期）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1BuyDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1BuyDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生应计利息 += 交易明细.利息总额
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), dealNow.getIntAmt()));
            // 变量.发生额集.发生成本 += 交易明细.交易本金
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), dealNow.getPrinAmt()));
            // 变量.发生额集.发生数量 += 交易明细.交易份额
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), dealNow.getShareAmt()));
            paramT1.getT1BuyDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
    }

    /**
     * 公允价值的情况场景处理(申购)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvtplApply(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                 FinProductDealInfoComDTO dealNow,
                                 FinProductDealInfoResult acctEntity, DealParamterDto param,
                                 SecT1HandleParamterDto paramT1, String varSecDealFeeSubject) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 成本金额
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
        // 利息金额
        accountAmountMap.put(BookConstant.INTAMT,
            ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));
        // 交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        // accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());

        // 变量.费用损益计入方式 = '计入投资收益'情况
        if (DictCodeList.债券交易费用损益计入方式_计入投资收益.getValue().equals(varSecDealFeeSubject)) {
            // 交易费用 <- 变量.交易各种费用.交易费用
            accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            accountAmountMap.put(BookConstant.STAMPTAX_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getStampDuty(), dealTypesFeeDto.getStampDutyForDealPay()));
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            accountAmountMap.put(BookConstant.HANDFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getBrokerage(), dealTypesFeeDto.getBrokerageForDealPay()));
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            accountAmountMap.put(BookConstant.CERTIFEE_OUT,
                ArithmeticUtil.doAdd(dealTypesFeeDto.getCertificateFee(),
                    dealTypesFeeDto.getCertificateFeeForDealPay()));
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            accountAmountMap.put(BookConstant.SETTLEFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getClearingFee(), dealTypesFeeDto.getClearingFeeForDealPay()));
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            accountAmountMap.put(BookConstant.COMMISSION_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getCommission(), dealTypesFeeDto.getCommissionForDealPay()));
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            accountAmountMap.put(BookConstant.TRADEFEE_OUT,
                ArithmeticUtil.doAdd(dealTypesFeeDto.getTransactionFee(),
                    dealTypesFeeDto.getTransactionFeeForDealPay()));
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT, ArithmeticUtil.doAdd(
                dealTypesFeeDto.getSettlementFee(), dealTypesFeeDto.getSettlementFeeForDealPay()));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        /** 交易金额_本位币	<-		(印花税支出_本位币 + 经手费支出_本位币 + 证管费支出_本位币 + 结算费支出_本位币 + 佣金支出_本位币 + 交易手续费支出_本位币
                + 结算手续费支出_本位币 + 成本金额_本位币 + 利息金额_本位币)
                -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币)**/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT + BookConstant.BCCYAMT),//印花税支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT + BookConstant.BCCYAMT),//经手费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT + BookConstant.BCCYAMT),//证管费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT + BookConstant.BCCYAMT),//结算费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT + BookConstant.BCCYAMT),//佣金支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT + BookConstant.BCCYAMT),//交易手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT + BookConstant.BCCYAMT),//结算手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));//利息金额_本位币

        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT));//结算手续费_本位币
        BigDecimal 交易金额本位币 = ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2);
        accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(交易金额本位币, NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种

        /** modify by yanya on 20191231 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 =变量.成本科目余额 + 交易明细.交易本金
        param.setVarCostSubjectAmt(
            ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(), dealNow.getPrinAmt()));
        // 变量.成本科目数量=变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.应计利息科目余额=变量.应计利息科目余额 + 交易明细.利息总额
        param.setVarLxSubjectAmt(
            ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), dealNow.getIntAmt()));

    }

    /**
     * 公共场景赋值
     * 
     * @param accountSceneMap 场景要素 map
     * @param dealNow 金融产品交易信息
     * @param rstBaseinfo 标的信息
     * @see
     * @since 1.0
     */
    private void getAccountSceneMap(Map<String, Object> accountSceneMap,
                                    FinProductDealInfoComDTO dealNow,
                                    TargetFinProduct rstBaseinfo) {
        // 境内外
        accountSceneMap.put(BookConstant.BLN_AREA, rstBaseinfo.getBlnArea());
        // 分类1
        accountSceneMap.put(BookConstant.TYPE_1, rstBaseinfo.getType1());
        // 分类2
        accountSceneMap.put(BookConstant.TYPE_2, rstBaseinfo.getType2());
        // 分类3
        accountSceneMap.put(BookConstant.TYPE_3, rstBaseinfo.getType3());
        // 分类4
        accountSceneMap.put(BookConstant.TYPE_4, rstBaseinfo.getType4());
        // 分类5
        accountSceneMap.put(BookConstant.TYPE_5, rstBaseinfo.getType5());
        // 分类6
        accountSceneMap.put(BookConstant.TYPE_6, rstBaseinfo.getType6());
        // 交易编号
        accountSceneMap.put(BookConstant.TRADE_ID, dealNow.getTradeId());

        // 冲正标识
        accountSceneMap.put(BookConstant.OFFSET_FLAG, DictCodeList.冲正标识_正常交易.getValue());

        if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_申购确认.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())
            || DictCodeListExt1.交易方向_债转股.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.QTY));
        }
        else {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER, BigDecimal.ZERO);
        }
        if ((DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
        		&& !DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode()))
            || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
            if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
                accountSceneMap.put(BookConstant.ONWAY_DATE, dealNow.getTradeDate().compareTo(
                    dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate());
            }
            /** modify by yanya else if（交易明细.交易场所 = "上交所" OR 交易明细.交易场所 = "深交所"）**/
            else if(DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket())) {
                //在途日期				<-		交易明细.交易日 + 1
                accountSceneMap.put(BookConstant.ONWAY_DATE, DateUtil.addDay(dealNow.getTradeDate(), 1));
            }
            else {
                //accountSceneMap.put(BookConstant.ONWAY_DATE, DateUtils.addDays(dealNow.getTradeDate(), 1));
                /** modify by yanya 在途日期	<-	交易明细.交易日 == 交割日？Null：交割日	**/
                accountSceneMap.put(BookConstant.ONWAY_DATE, dealNow.getTradeDate().compareTo(
                        dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate());
            }
        }

        if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())) {
            // 登记托管机构 <- 变量.标的信息.登记托管机构
            accountSceneMap.put(BookConstant.REGIST_ORG, rstBaseinfo.getRegistOrg());
        }
    }

    /**
     * 调用共通查询发生日前一日余额
     * 
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quarySubjectBalance(FinProductDealInfoResult acctEntity, DealParamterDto param) {
        // 科目余额查询参数
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(acctEntity.getBooksetId());
        searchDao.setBokDetailId(acctEntity.getBokDetailId());
        searchDao.setHappenDate(acctEntity.getHappenDate());
        searchDao.setBookDate(acctEntity.getBookDate());
        searchDao.setTaskPri(acctEntity.getListFinProductDealInfoComDTO().get(0).getTaskPri());
        // 2.1.成本类科目余额
        List<SubjectBalance> listSubjectBalance = new ArrayList<SubjectBalance>();
        if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类成本.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类成本.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }

        if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.成本科目余额 = 科目余额
            param.setVarCostSubjectAmt(listSubjectBalance.get(0).getBalance());
            // 变量.成本科目数量 = 数量
            param.setVarCostSubjectNum(listSubjectBalance.get(0).getNum());
        }

        // 2.2.应计利息类科目余额
        List<SubjectBalance> listSubjectBalanceInt = new ArrayList<SubjectBalance>();
        if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue())));
            listSubjectBalanceInt = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue())));
            listSubjectBalanceInt = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue())));
            listSubjectBalanceInt = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }

        if (listSubjectBalanceInt != null && listSubjectBalanceInt.size() > 0) {
            // 变量.应计利息余额 = 科目余额
            param.setVarLxSubjectAmt(listSubjectBalanceInt.get(0).getBalance());
        }

        // 2.3.公允价值变动类科目余额
        List<SubjectBalance> listSubjectBalanceFV = new ArrayList<SubjectBalance>();
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue())));
            listSubjectBalanceFV = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue())));
            listSubjectBalanceFV = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }

        if (listSubjectBalanceFV != null && listSubjectBalanceFV.size() > 0) {
            // 变量.应计利息余额 = 科目余额
            param.setVarGySubjectAmt(listSubjectBalanceFV.get(0).getBalance());
        }

        // 2.4.溢折价类科目余额
        List<SubjectBalance> listSubjectBalanceEX = new ArrayList<SubjectBalance>();
        if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue())));
            listSubjectBalanceEX = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue())));
            listSubjectBalanceEX = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        if (listSubjectBalanceEX != null && listSubjectBalanceEX.size() > 0) {
            // 变量.溢折价科目余额 = 科目余额
            param.setVarYzjSubjectAmt(listSubjectBalanceEX.get(0).getBalance());
        }

    }

    /**
     * 摊余成本的情况场景处理
     * 
     * @param returnResult 场景返回结果
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryAc(BookGenerateResult returnResult, FinProductDealInfoResult acctEntity,
                         DealParamterDto param, SecT1HandleParamterDto paramT1) {
        // 交易明细 ：交易类账务处理结果.交易明细列表
        for (FinProductDealInfoComDTO dealNow : acctEntity.getListFinProductDealInfoComDTO()) {
            TargetFinProduct rstBaseinfo = getTargetFinProduct(dealNow);
            // 交易方向 = "买入"
            if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
            	quaryAcApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            } 
            else if(DictCodeList.交易方向_申购确认.getValue().equals(dealNow.getPs())){
            	quaryAcPurchaseConfirmation(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            } 
            else if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())) {
                quaryAcBuy(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            }
            else if (DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
                quaryAcSell(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            }
            else if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                // quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_收息到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplIntAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // quaryAcRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplRedAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if(DictCodeListExt1.交易方向_债转股.getValue().equals(dealNow.getPs())){
            	quaryAcDebtToEquity(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
//            else if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
//            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
//            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
//                quaryAcApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
//            }
            else if (DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())) {
                quaryAcRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    DictCodeListTableId.会计场景代码_摊余成本类债券_行权回售.getValue());
            }
            else if (DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())) {
                quaryAcRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    DictCodeListTableId.会计场景代码_摊余成本类债券_行权赎回.getValue());
            }
            //交易方向_应收款项转入 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转入.getValue().equals(dealNow.getPs())) {
                this.getReceivablesIn(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            //交易方向_应收款项转出 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转出.getValue().equals(dealNow.getPs())) {
                this.getReceivablesOut(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
        }
    }
    
    /**
     * 摊余成本的情况场景处理(债转股)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param  param
     * @see
     * @since 1.0
     */
    private void quaryAcDebtToEquity(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param) {
    	// 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_债转股.getValue());

        // 调用 共通【股票成本处理共通】  
        StockCostDto stockCostDto = this.stockCostProcessingCommon(dealNow.getTradeId(), dealNow.getBooksetId());
        
        // accountSceneMap设置
        //股票账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,stockCostDto.getBokDetailId());
        //股票核算分类
        accountSceneMap.put(BookConstant.SHARE_ORG_VALCLASS,stockCostDto.getType2());
        
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");
        
        // accountAmountMap设置如下：																		
        // 定义 	变量.股票成本  =	0	
        BigDecimal stockCost = BigDecimal.ZERO;
        // 定义 	变量.股票数量  =  0		
        BigDecimal stockNum = BigDecimal.ZERO;
        // 定义 	变量.冲减溢折价	 = 0	
        BigDecimal varCjOverflowPrice = BigDecimal.ZERO;
        // 定义 	变量.冲减应计利息 = 0		
        BigDecimal varCjyslxSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.冲减数量 = 交易份额	
        BigDecimal varCjslSubjectNum = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 定义 	变量.冲减成本 = 0		
        BigDecimal varCjcbSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.买卖价差 = 0			
        BigDecimal varMmjc =  BigDecimal.ZERO;
        
        // 变量.股票成本 = DTO.股票成本																									
        stockCost =  stockCostDto.getCost();
        // 变量.股票数量 = DTO.股票数量																														
        stockNum = 	stockCostDto.getNum();																															
        // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjcbSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarCostSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																
        // 变量.冲减溢折价 = 变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjOverflowPrice =  ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																	
        // 变量.冲减应计利息  = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjyslxSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply( param.getVarLxSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 		
        
        // if（变量.标的信息.息票品种为零息或贴现时）
        if(DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies()) || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())){
        	// 变量.冲减应计利息   = 	0	
        	varCjyslxSubjectAmt = BigDecimal.ZERO;
        }
        // 变量.买卖价差	= 交易明细.交易金额 + 变量.股票成本 - (变量.冲减成本 + 变量.冲减溢折价 ) -变量.冲减应计利息 																				
        varMmjc = ArithmeticUtil.doSub(
        					ArithmeticUtil.doAdd(dealNow.getTradeAmt(), stockCost),
        					ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(varCjcbSubjectAmt,varCjOverflowPrice),varCjyslxSubjectAmt));
        
        // 股票成本   <-	变量.股票成本																							
        accountAmountMap.put(BookConstant.COST_SHARE, stockCost);																																	
        // 实收金额  <-  交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
        // 面值  <-	变量.冲减成本																							
        accountAmountMap.put(BookConstant.COST_BOND, varCjcbSubjectAmt);																													
        // 应计利息  <-	变量.冲减应计利息																							
        accountAmountMap.put(BookConstant.INTAMT, varCjyslxSubjectAmt);																															
        
        // if（变量.买卖价差>= 0）
        if(varMmjc.compareTo(BigDecimal.ZERO) >= 0){
        	// 买卖价差（贷） <-	变量.买卖价差			
        	accountAmountMap.put(BookConstant.NETAMT_C,
                    ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        } else {
        	// 买卖价差（借）<-	变量.买卖价差*-1				
        	 accountAmountMap.put(BookConstant.NETAMT_D, ArithmeticUtil.round(varMmjc.negate(), NumberEnum.MONEY));
        }
		// if（变量.冲减溢折价>= 0）	
        if (varCjOverflowPrice.compareTo(BigDecimal.ZERO) >= 0) {
        	// 冲减溢折价（贷）  <-	变量.冲减溢折价				
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjOverflowPrice, NumberEnum.MONEY));
        }
        else {
        	 // 冲减溢折价（借）  <-	变量.冲减溢折价*-1								
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(varCjOverflowPrice.negate(), NumberEnum.MONEY));
        	
        }
      		
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varCjOverflowPrice, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义 变量.买卖价差_本位币	=	(股票成本_本位币 + 实收金额_本位币) - (面值_本位币 + 应计利息_本位币 + 变量.冲减溢折价（贷）_本位币)
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.COST_SHARE + BookConstant.BCCYAMT),//股票成本_本位币
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)//实收金额_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.COST_BOND + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),//应计利息_本位币
                溢折价贷本位币//变量.冲减溢折价（贷）_本位币
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币 <-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.NETAMT_C  + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_C  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.NETAMT_D  + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 基础科目性质												
        Map<String,Map<String,Object>> accountNatrueMap = new HashMap<>();												
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();												
        accountNatrueBranchMap.put(BookConstant.HAPPEN_NUMBER,stockNum);												
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.股票_成本.getValue(),accountNatrueBranchMap);												
        ad.setAccountNatrueMap(accountNatrueMap);	

        
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);
        
       
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        
        											

        // 全局变量的更新																								
        // 变量.成本科目余额 	 =	变量.成本科目余额 - 变量.冲减成本		
        	param.setVarCostSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarCostSubjectAmt(),varCjcbSubjectAmt));
        // 变量.成本科目数量    =	变量.成本科目数量 - 变量.冲减数量			
        	param.setVarCostSubjectNum(
        			ArithmeticUtil.doSub(param.getVarCostSubjectNum(),varCjslSubjectNum));
        // 变量.应计利息科目余额	 =	变量.应计利息科目余额 - 变量.冲减应计利息	
        	param.setVarLxSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarLxSubjectAmt(),varCjyslxSubjectAmt));
        // 变量.溢折价科目余额  =	变量.溢折价科目余额 - 变量.冲减溢折价											
        	param.setVarYzjSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarYzjSubjectAmt(),varCjOverflowPrice));
	
	}

	/**
     * 摊余成本的情况场景处理(申购确认)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param  param
     * @param paramT1
     * @see
     * @since 1.0
     */
    private void quaryAcPurchaseConfirmation(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param,
			SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_申购确认.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 定义		变量.溢折价 = 0		
        BigDecimal overflowPrice = BigDecimal.ZERO;
       
       
        // 交易金额  <-  交易明细.全价总额
        accountAmountMap.put(BookConstant.DEALAMT,
                ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));
        // 面值  <-  交易明细.交易份额 * 交易明细.单位面值											
        accountAmountMap.put(BookConstant.FACEVALUE,
                ArithmeticUtil.round(
                		ArithmeticUtil.multiply(dealNow.getShareAmt(),dealNow.getParValue()),
                		NumberEnum.MONEY));
        
       // 变量.溢折价 = 交易明细.全价总额 - 交易明细.利息总额 - （交易明细.交易份额 * 交易明细.单位面值）
        overflowPrice =  ArithmeticUtil.doSub(
        		ArithmeticUtil.doSub(dealNow.getFpriceAmt(),dealNow.getIntAmt()),
        		ArithmeticUtil.multiply(dealNow.getShareAmt(),dealNow.getParValue()));
        
        // if（变量.溢折价 >= 0）	
        if(BigDecimal.ZERO.compareTo(overflowPrice) <= 0){
        	// 溢折价(借) <-	变量.溢折价	
        	accountAmountMap.put(BookConstant.DSCINC_D,overflowPrice);
        } else {
        	// 溢折价(贷) <-	变量.溢折价*-1
        	accountAmountMap.put(BookConstant.DSCINC_C,overflowPrice.negate());
        }
        
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.溢折价（借）_本位币 = round(变量.溢折价,共通金额小数位) * 变量.汇率;保留金额元素小数位数
        BigDecimal 溢折价借本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(overflowPrice, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //交易金额_本位币	<-		变量.溢折价（借）_本位币 + 面值_本位币
        BigDecimal 交易金额本位币 = ArithmeticUtil.round(ArithmeticUtil.doAdd(
                溢折价借本位币,//变量.溢折价（借）_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT)// 面值_本位币
        ), NumberEnum.MONEY);
        accountAmountMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, 交易金额本位币);
        accountCurrencyMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
    	// 变量.成本科目余额   =	变量.成本科目余额 + 交易明细.交易份额 * 交易明细.单位面值	
        param.setVarCostSubjectAmt(
        		ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
        							ArithmeticUtil.multiply(dealNow.getShareAmt(),
        													dealNow.getParValue())));
    	// 变量.成本科目数量   =	变量.成本科目数量 + 交易明细.交易份额			
        param.setVarCostSubjectNum(
        		ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
    	// 变量.溢折价科目余额   =  变量.溢折价科目余额  + 变量.溢折价	
        param.setVarYzjSubjectAmt(
        		ArithmeticUtil.doAdd(param.getVarYzjSubjectAmt(),overflowPrice));
        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(交易明细.交易日，true)
        param.getRetryDateMap().put(dealNow.getTradeDate(), true);
        /**ADD BY WUJIE ON 20191216 END**/
	}

	/**
     * 摊余成本的情况场景处理(买入)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryAcBuy(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                            FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity,
                            DealParamterDto param, SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 交易金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varMz = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varMz, NumberEnum.MONEY));

        // 定义 变量.溢折价 = 0
        BigDecimal varZyj = BigDecimal.ZERO;
        // 定义 变量.应计利息 = 0
        BigDecimal varYjlx = BigDecimal.ZERO;

        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.应计利息 = 0
            varYjlx = BigDecimal.ZERO;
            // 变量.溢折价 = 交易明细.全价总额 - （交易明细.交易份额 * 交易明细.单位面值） + 变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(),
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 变量.应计利息 =交易明细.利息总额
            varYjlx = dealNow.getIntAmt();
            // 变量.溢折价 = 交易明细.全价总额 - 交易明细.利息总额 - （交易明细.交易份额 * 交易明细.单位面值）+变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getIntAmt());
            varZyj = ArithmeticUtil.subtract(varZyj,
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        // 应计利息 <- 变量.应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));

        // 变量.溢折价 >= 0
        if (varZyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 溢折价(借) <- 变量.溢折价
            accountAmountMap.put(BookConstant.DSCINC_D,
                ArithmeticUtil.round(varZyj, NumberEnum.MONEY));
        }
        else {
            // 溢折价(贷) <- 变量.溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varZyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.溢折价（借）_本位币 = round(变量.溢折价,共通金额小数位) * 变量.汇率;	保留金额元素小数位数
        BigDecimal 溢折价借本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varZyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /** 交易金额_本位币		<-		变量.溢折价（借）_本位币 + 面值_本位币 + 应计利息_本位币
                -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币) **/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(
                溢折价借本位币,//变量.溢折价（借）_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//应计利息_本位币
        );
        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT)//结算手续费_本位币
        );
        accountAmountMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT,
                ArithmeticUtil.round(ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2), NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varZyj = ArithmeticUtil.round(varZyj, NumberEnum.MONEY);
        varYjlx = ArithmeticUtil.round(varYjlx, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易份额 * 交易明细.单位面值
        param.setVarCostSubjectAmt(ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
            ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue())));
        // 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 + 变量.溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doAdd(param.getVarYzjSubjectAmt(), varZyj));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 + 变量.应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), varYjlx));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生利息调整 += 变量.溢折价
            paramT1.setZyjSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getZyjSubjectBalance(), varZyj));
            // 变量.T+1发生应计利息 += 交易明细.利息总额
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getYjlxSubjectBalance(), dealNow.getIntAmt()));
            // 变量.T+1发生成本 += 面值
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getCbSubjectBalance(), varMz));
            // 变量.T+1发生数量 += 交易明细.交易份额
            paramT1.setNumBalance(
                ArithmeticUtil.doAdd(paramT1.getNumBalance(), dealNow.getShareAmt()));

            // 变量.发生额集 = 变量.T+1买入交易.get（交易明细.交割日）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1BuyDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1BuyDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生利息调整 += 变量.溢折价
            dealHappenAmtDto.setHappenLxtz(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), varZyj));
            // 变量.发生额集.发生应计利息 += 交易明细.利息总额
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), dealNow.getIntAmt()));
            // 变量.发生额集.发生成本 += 面值
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), varMz));
            // 变量.发生额集.发生数量 += 交易明细.交易份额
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), dealNow.getShareAmt()));
            paramT1.getT1BuyDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
        
        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(场景元素.在途日期 == Null?交易明细.交易日：场景元素.在途日期，true)
        param.getRetryDateMap().put(accountSceneMap.get(BookConstant.ONWAY_DATE)== null?dealNow.getTradeDate():(Date)accountSceneMap.get(BookConstant.ONWAY_DATE), true);
        /**ADD BY WUJIE ON 20191216 END**/
    }

    /**
     * 摊余成本的情况场景处理(申购)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryAcApply(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                              FinProductDealInfoComDTO dealNow,
                              FinProductDealInfoResult acctEntity, DealParamterDto param,
                              SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 交易金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varMz = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varMz, NumberEnum.MONEY));

        // 定义 变量.溢折价 = 0
        BigDecimal varZyj = BigDecimal.ZERO;
        // 定义 变量.应计利息 = 0
        BigDecimal varYjlx = BigDecimal.ZERO;

        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.应计利息 = 0
            varYjlx = BigDecimal.ZERO;
            // 变量.溢折价 = 交易明细.全价总额 - （交易明细.交易份额 * 交易明细.单位面值） + 变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(),
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 变量.应计利息 =交易明细.利息总额
            varYjlx = dealNow.getIntAmt();
            // 变量.溢折价 = 交易明细.全价总额 - 交易明细.利息总额 - （交易明细.交易份额 * 交易明细.单位面值）+变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getIntAmt());
            varZyj = ArithmeticUtil.subtract(varZyj,
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        // 应计利息 <- 变量.应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));

        // 变量.溢折价 >= 0
        if (varZyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 溢折价(借) <- 变量.溢折价
            accountAmountMap.put(BookConstant.DSCINC_D,
                ArithmeticUtil.round(varZyj, NumberEnum.MONEY));
        }
        else {
            // 溢折价(贷) <- 变量.溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varZyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率), NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.溢折价（借）_本位币 = round(变量.溢折价,共通金额小数位) * 变量.汇率;保留金额元素小数位数
        BigDecimal 溢折价借本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varZyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**交易金额_本位币						<-		变量.溢折价（借）_本位币 + 面值_本位币 + 应计利息_本位币
                -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币)**/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(
                溢折价借本位币,//变量.溢折价（借）_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//应计利息_本位币
        );
        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT)//结算手续费_本位币
        );
        accountAmountMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT,
                ArithmeticUtil.round(ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2), NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varZyj = ArithmeticUtil.round(varZyj, NumberEnum.MONEY);
        varYjlx = ArithmeticUtil.round(varYjlx, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易份额 * 交易明细.单位面值
        param.setVarCostSubjectAmt(ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
            ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue())));
        // 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 + 变量.溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doAdd(param.getVarYzjSubjectAmt(), varZyj));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 + 变量.应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), varYjlx));

        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(交易明细.交易日，true)
        param.getRetryDateMap().put(dealNow.getTradeDate(), true);
        /**ADD BY WUJIE ON 20191216 END**/
    }

    /**
     * 摊余成本的情况场景处理(卖出)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryAcSell(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                             FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity,
                             DealParamterDto param, SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_卖出.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + DictCodeList.交易方向_卖出.getCodeName() + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        Date settleDate = null;
        /** modify by yanyan 在途日期根据设计调整 20200102 start**/
        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
            //settleDate = dealNow.getSettleDate();
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else if(DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket())) {
            //在途日期		<-		交易明细.交易日 + 1
            settleDate = DateUtil.addDay(dealNow.getTradeDate(), 1);
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else {
            //accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        /** modify by yanyan 在途日期根据设计调整 20200102 end **/
        // accountAmountMap设置
        // 定义 变量.冲减溢折价 = 0
        BigDecimal varCjzyj = BigDecimal.ZERO;
        // 定义 变量.冲减应计利息 = 0
        BigDecimal varCjyjlx = BigDecimal.ZERO;
        // 定义 变量.冲减成本 = 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varCjcb = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        // 定义 变量.冲减数量 = 交易明细.交易份额
        BigDecimal varCjsl = dealNow.getShareAmt();
        // 定义 变量.利息差额 = 0
        BigDecimal varLxce = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
                dealNow, param, paramT1, settleDate);
            // 变量.冲减溢折价 = 变量.T+1处理结果集.溢折价科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            varCjzyj = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getZyjSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            varCjyjlx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
        }
        else {
            // 变量.冲减溢折价 = 变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            varCjzyj = ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(), dealNow.getShareAmt());
            varCjzyj = ArithmeticUtil.divide(varCjzyj, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());

            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            varCjyjlx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
        }
        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            varCjyjlx = dealNow.getIntAmt();
        }
        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.冲减应计利息 = 0
            varCjyjlx = BigDecimal.ZERO;
        }

        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 变量.利息差额 = 交易明细.利息总额 - 变量.冲减应计利息
        // if（!变量.标的信息.息票品种为零息或贴现时）
        if (!DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            && !DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            varLxce = ArithmeticUtil.subtract(dealNow.getIntAmt(), varCjyjlx);
        }
        // 变量.买卖价差 =交易明细.交易金额 - (变量.冲减成本 + 变量.冲减溢折价 ) - 变量.冲减应计利息 - 变量.交易各种费用.不随交易费用 +
        // 变量.交易各种费用.通道费
        varMmjc = ArithmeticUtil.subtract(dealNow.getTradeAmt(),
            ArithmeticUtil.doAdd(varCjcb, varCjzyj));
        varMmjc = ArithmeticUtil.subtract(varMmjc, varCjyjlx);
        varMmjc = ArithmeticUtil.subtract(varMmjc, dealTypesFeeDto.getNoFeeAmt());
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        varMmjc = ArithmeticUtil.subtract(varMmjc, varLxce);

        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varCjcb, NumberEnum.MONEY));
        // 应计利息 <- 变量.冲减应计利息
        accountAmountMap.put(BookConstant.INTAMT,
            ArithmeticUtil.round(varCjyjlx, NumberEnum.MONEY));

        // 变量.利息差额 >= 0
        if (varLxce.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <- 变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(varLxce, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varLxce, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.冲减溢折价>= 0
        if (varCjzyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 剩余溢折价（贷） <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY));
        }
        else {
            // 剩余溢折价（借） <- 变量.冲减溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjzyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        /** add by yanya on 20200228 通道费账务明细代码**/
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, dealTypesFeeDto.getChlFeeCode());
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varLxce, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币									 =	(交易金额_本位币 + 实付通道费_本位币)
                - 	(面值_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 + 冲减溢折价（贷）_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币)**/
        BigDecimal 买卖价差1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//交易金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位币
        );
        BigDecimal 买卖价差2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                溢折价贷本位币,//冲减溢折价（贷）_本位币
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//利息金额_本位币
        );
        BigDecimal 买卖价差 = ArithmeticUtil.doSub(买卖价差1, 买卖价差2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varCjcb = ArithmeticUtil.round(varCjcb, NumberEnum.MONEY);
        varCjsl = ArithmeticUtil.round(varCjsl, NumberEnum.QTY);
        varCjyjlx = ArithmeticUtil.round(varCjyjlx, NumberEnum.MONEY);
        varCjzyj = ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), varCjcb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), varCjsl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), varCjyjlx));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.溢折价科目余额
        param.setVarYzjSubjectAmt(ArithmeticUtil.subtract(param.getVarYzjSubjectAmt(), varCjzyj));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生利息调整 -= 变量.冲减溢折价
            paramT1.setZyjSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getZyjSubjectBalance(), varCjzyj));
            // 变量.T+1发生应计利息 -= 变量.冲减应计利息
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getYjlxSubjectBalance(), varCjyjlx));
            // 变量.T+1发生成本 -= 变量.冲减成本
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getCbSubjectBalance(), varCjcb));
            // 变量.T+1发生数量 -= 变量.冲减数量
            paramT1.setNumBalance(ArithmeticUtil.subtract(paramT1.getNumBalance(), varCjsl));
            // 变量.发生额集 = 变量.T+1买入交易.get（交易明细.交割日）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1SellDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1SellDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生利息调整 += 变量.冲减溢折价
            dealHappenAmtDto.setHappenLxtz(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), varCjzyj));
            // 变量.发生额集.发生应计利息 += 变量.冲减应计利息
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), varCjyjlx));
            // 变量.发生额集.发生成本 += 变量.冲减成本
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), varCjcb));
            // 变量.发生额集.发生数量 += 变量.冲减数量
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), varCjsl));
            paramT1.getT1SellDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
    }

    /**
     * 综合收益的情况场景处理
     * 
     * @param returnResult 场景返回结果
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvoci(BookGenerateResult returnResult, FinProductDealInfoResult acctEntity,
                            DealParamterDto param, SecT1HandleParamterDto paramT1) {
        // 交易明细 ：交易类账务处理结果.交易明细列表
        for (FinProductDealInfoComDTO dealNow : acctEntity.getListFinProductDealInfoComDTO()) {
            TargetFinProduct rstBaseinfo = getTargetFinProduct(dealNow);
            // 交易方向 = "买入"
            if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
            	quaryFvociApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            } else if(DictCodeList.交易方向_申购确认.getValue().equals(dealNow.getPs())){
            	quaryFvociPurchaseConfirmation(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            } 
            else if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())) {
                quaryFvociBuy(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            }
            else if (DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
                quaryFvociSell(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
            }
            else if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                // quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_收息到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplIntAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // quaryFvociRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本到账.getValue().equals(dealNow.getPs())) {
                quaryFvtplRedAcc(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if(DictCodeListExt1.交易方向_债转股.getValue().equals(dealNow.getPs())){
            	quaryFvociDebtToEquity(rstBaseinfo, returnResult, dealNow, acctEntity, param);

            }
//            else if (DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
//            		|| (DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode())
//            				&& DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs()))) {
//                quaryFvociApply(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1);
//            }
            else if (DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())) {
                quaryFvociRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    DictCodeListTableId.会计场景代码_综合收益类债券_行权回售.getValue());
            }
            else if (DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())) {
                quaryFvociRight(rstBaseinfo, returnResult, dealNow, acctEntity, param, paramT1,
                    DictCodeListTableId.会计场景代码_综合收益类债券_行权赎回.getValue());
            }
            //交易方向_应收款项转入 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转入.getValue().equals(dealNow.getPs())) {
                this.getReceivablesIn(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            //交易方向_应收款项转出 add by yanya on 20191226
            else if (DictCodeList.交易方向_应收款项转出.getValue().equals(dealNow.getPs())) {
                this.getReceivablesOut(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            
        }
    }

    /**
     * 综合收益的情况场景处理(债转股)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param 
     * @see
     * @since 1.0
     */
    private void quaryFvociDebtToEquity(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param) {
    	// 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_债转股.getValue());

        // 调用 共通【股票成本处理共通】  
        StockCostDto stockCostDto = this.stockCostProcessingCommon(dealNow.getTradeId(), dealNow.getBooksetId());
        
        // accountSceneMap设置
        //股票账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,stockCostDto.getBokDetailId());
        //股票核算分类
        accountSceneMap.put(BookConstant.SHARE_ORG_VALCLASS,stockCostDto.getType2());
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");
        
        // accountAmountMap设置如下：																		
        // 定义 	变量.股票成本  =	0	
        BigDecimal stockCost = BigDecimal.ZERO;
        // 定义 	变量.股票数量  =  0		
        BigDecimal stockNum = BigDecimal.ZERO;
        // 定义   变量.冲减公允价值变动   = 0	
        BigDecimal varCjgyjzbdSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.冲减溢折价	 = 0	
        BigDecimal varCjOverflowPrice = BigDecimal.ZERO;
        // 定义 	变量.冲减应计利息 = 0		
        BigDecimal varCjyslxSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.冲减数量 = 交易份额	
        BigDecimal varCjslSubjectNum = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 定义 	变量.冲减成本 = 0		
        BigDecimal varCjcbSubjectAmt = BigDecimal.ZERO;
        // 定义 	变量.买卖价差 = 0			
        BigDecimal varMmjc =  BigDecimal.ZERO;
        
        // 变量.股票成本 = DTO.股票成本																									
        stockCost =  stockCostDto.getCost();
        // 变量.股票数量 = DTO.股票数量																														
        stockNum = 	stockCostDto.getNum();																															
        // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjcbSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarCostSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 
        // 变量.冲减公允价值变动	= 变量.公允价值变动科目余额 * 交易明细.交易份额*/变量.成本科目数量																			
        varCjgyjzbdSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarGySubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 		
        // 变量.冲减溢折价 = 变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjOverflowPrice =  ArithmeticUtil.divide(
        		ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 																																	
        // 变量.冲减应计利息  = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量																														
        varCjyslxSubjectAmt = ArithmeticUtil.divide(
        		ArithmeticUtil.multiply( param.getVarLxSubjectAmt(),dealNow.getShareAmt()), 
        		param.getVarCostSubjectNum()); 		
        
        // if（变量.标的信息.息票品种为零息或贴现时）
        if(DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies()) || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())){
        	// 变量.冲减应计利息   = 	0	
        	varCjyslxSubjectAmt = BigDecimal.ZERO;
        }
        // 变量.买卖价差	= (交易明细.交易金额 + 变量.股票成本) - (变量.冲减成本 + 变量.冲减溢折价 +变量.冲减应计利息） 																				
        varMmjc = ArithmeticUtil.doSub(
        					ArithmeticUtil.doAdd(dealNow.getTradeAmt(), stockCost),
        					ArithmeticUtil.doAdd(
        							ArithmeticUtil.doAdd(varCjcbSubjectAmt,varCjOverflowPrice),
        							varCjyslxSubjectAmt));
        
        // 股票成本   <-	变量.股票成本																							
        accountAmountMap.put(BookConstant.COST_SHARE, stockCost);																																	
        // 实收金额  <-  交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
        // 面值  <-	变量.冲减成本																							
        accountAmountMap.put(BookConstant.COST_BOND, varCjcbSubjectAmt);																													
        // 应计利息  <-	变量.冲减应计利息																							
        accountAmountMap.put(BookConstant.INTAMT, varCjyslxSubjectAmt);																															
        
        // if（变量.买卖价差>= 0）
        if(varMmjc.compareTo(BigDecimal.ZERO) >= 0){
        	// 买卖价差（贷） <-	变量.买卖价差			
        	accountAmountMap.put(BookConstant.NETAMT_C,
                    ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        } else {
        	// 买卖价差（借）<-	变量.买卖价差*-1				
        	 accountAmountMap.put(BookConstant.NETAMT_D, ArithmeticUtil.round(varMmjc.negate(), NumberEnum.MONEY));
        }
		// if（变量.冲减溢折价>= 0）	
        if (varCjOverflowPrice.compareTo(BigDecimal.ZERO) >= 0) {
        	// 冲减溢折价（贷）  <-	变量.冲减溢折价				
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjOverflowPrice, NumberEnum.MONEY));
        }
        else {
        	 // 冲减溢折价（借）  <-	变量.冲减溢折价*-1								
            accountAmountMap.put(BookConstant.DSCINC_D, 
            	ArithmeticUtil.round(varCjOverflowPrice.negate(), NumberEnum.MONEY));
        }
        // if(变量.冲减公允价值变动 >= 0 )
        if(varCjgyjzbdSubjectAmt.compareTo(BigDecimal.ZERO) >= 0){
        	// 公允价值变动(贷) <-变量.冲减公允价值变动	
        	accountAmountMap.put(BookConstant.FLOAT_C,
                    ArithmeticUtil.round(varCjgyjzbdSubjectAmt, NumberEnum.MONEY));
        	// 冲减公允价值变动(借) <-变量.冲减公允价值变动						
        	accountAmountMap.put(BookConstant.FLOAT_1_D,
                    ArithmeticUtil.round(varCjgyjzbdSubjectAmt, NumberEnum.MONEY));
        } else {
        	// 公允价值变动(借) <- 变量.冲减公允价值变动 * -1		
        	 accountAmountMap.put(BookConstant.FLOAT_D, 
        			 ArithmeticUtil.round(varCjgyjzbdSubjectAmt.negate(), NumberEnum.MONEY));
        	// 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1							
        	 accountAmountMap.put(BookConstant.FLOAT_1_C, 
        			 ArithmeticUtil.round(varCjgyjzbdSubjectAmt.negate(), NumberEnum.MONEY));
        }
		
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 冲减溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varCjOverflowPrice, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币	=	(股票成本_本位币 + 实收金额_本位币)
                - (成本金额_本位币 + 应计利息_本位币 + 变量.冲减溢折价（贷）_本位币)**/
        BigDecimal 买卖价差本位币1 =  ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.COST_SHARE + BookConstant.BCCYAMT),//股票成本_本位币
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)//实收金额_本位币
        );
        BigDecimal 买卖价差本位币2 =  ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.COST_BOND + BookConstant.BCCYAMT),//成本金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),//应计利息_本位币
                冲减溢折价贷本位币//变量.冲减溢折价（贷）_本位币)
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（贷）_本位币 <-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/
        
        // 基础科目性质												
        Map<String,Map<String,Object>> accountNatrueMap = new HashMap<>();												
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();												
        accountNatrueBranchMap.put(BookConstant.HAPPEN_NUMBER,stockNum);												
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.股票_成本.getValue(),accountNatrueBranchMap);												
        ad.setAccountNatrueMap(accountNatrueMap);
        
        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        
       												

        // 全局变量的更新																								
        // 变量.成本科目余额 	 =	变量.成本科目余额 - 变量.冲减成本		
        	param.setVarCostSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarCostSubjectAmt(),varCjcbSubjectAmt));
        // 变量.成本科目数量    =	变量.成本科目数量 - 变量.冲减数量			
        	param.setVarCostSubjectNum(
        			ArithmeticUtil.doSub(param.getVarCostSubjectNum(),varCjslSubjectNum));
        // 变量.应计利息科目余额	 =	变量.应计利息科目余额 - 变量.冲减应计利息	
        	param.setVarLxSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarLxSubjectAmt(),varCjyslxSubjectAmt));
        // 变量.溢折价科目余额  =	变量.溢折价科目余额 - 变量.冲减溢折价											
        	param.setVarYzjSubjectAmt(
        			ArithmeticUtil.doSub(param.getVarYzjSubjectAmt(),varCjOverflowPrice));
        // 变量.公允价值变动科目余额 =	变量.公允价值变动科目余额 - 变量.冲减公允价值变动															
        	param.setVarGySubjectAmt(
        			ArithmeticUtil.doSub(param.getVarGySubjectAmt(),varCjgyjzbdSubjectAmt));
	
	}

	/**
     * 综合收益的情况场景处理(申购确认)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param 
     * @see
     * @since 1.0
     */
    private void quaryFvociPurchaseConfirmation(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
			FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param,
			SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_申购确认.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 成本金额  <- 交易明细.交易本金
        accountAmountMap.put(BookConstant.FACEVALUE,dealNow.getPrinAmt());
        // 交易金额  <- 交易明细.全价总额		
        accountAmountMap.put(BookConstant.FACEVALUE,dealNow.getFpriceAmt());
        
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //成本金额_本位币 <- 成本金额 * 变量.汇率
        BigDecimal 成本金额本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.FACEVALUE), 汇率),
                NumberEnum.MONEY);
        accountAmountMap.put(BookConstant.FACEVALUE + BookConstant.BCCYAMT, 成本金额本位币);
        accountCurrencyMap.put(BookConstant.FACEVALUE + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        //交易金额 <-	成本金额_本位币
        accountAmountMap.put(BookConstant.FACEVALUE + BookConstant.BCCYAMT, 成本金额本位币);
        accountCurrencyMap.put(BookConstant.FACEVALUE + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
    	// 变量.成本科目余额   =	变量.成本科目余额 + 交易明细.交易成本	
        param.setVarCostSubjectAmt(
        		ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),dealNow.getPrinAmt()));
    	// 变量.成本科目数量   =	变量.成本科目数量 + 交易明细.交易份额			
        param.setVarCostSubjectNum(
        		ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
   
        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(交易明细.交易日，true)
        param.getRetryDateMap().put(dealNow.getTradeDate(), true);
        /**ADD BY WUJIE ON 20191216 END**/
	}

	/**
     * 综合收益的情况场景处理(买入)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvociBuy(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                               FinProductDealInfoComDTO dealNow,
                               FinProductDealInfoResult acctEntity, DealParamterDto param,
                               SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 交易金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varMz = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varMz, NumberEnum.MONEY));

        // 定义 变量.溢折价 = 0
        BigDecimal varZyj = BigDecimal.ZERO;
        // 定义 变量.应计利息 = 0
        BigDecimal varYjlx = BigDecimal.ZERO;

        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.应计利息 = 0
            varYjlx = BigDecimal.ZERO;
            // 变量.溢折价 = 交易明细.全价总额 - （交易明细.交易份额 * 交易明细.单位面值） - 变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(),
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 变量.应计利息 =交易明细.利息总额
            varYjlx = dealNow.getIntAmt();
            // 变量.溢折价 = 交易明细.全价总额 - 交易明细.利息总额 - （交易明细.交易份额 * 交易明细.单位面值）-变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getIntAmt());
            varZyj = ArithmeticUtil.subtract(varZyj,
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        // 应计利息 <- 变量.应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));

        // 变量.溢折价 >= 0
        if (varZyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 溢折价(借) <- 变量.溢折价
            accountAmountMap.put(BookConstant.DSCINC_D,
                ArithmeticUtil.round(varZyj, NumberEnum.MONEY));
        }
        else {
            // 溢折价(贷) <- 变量.溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varZyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.溢折价（借）_本位币 = round(变量.溢折价,共通金额小数位) * 变量.汇率;
        BigDecimal 溢折价借本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varZyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**交易金额_本位币						<-		变量.溢折价（借）_本位币 + 面值_本位币 + 应计利息_本位币
                -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币)**/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(
                溢折价借本位币,//变量.溢折价（借）_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//应计利息_本位币
        );
        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT)//结算手续费_本位币
        );
        accountAmountMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT,
                ArithmeticUtil.round(ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2), NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varZyj = ArithmeticUtil.round(varZyj, NumberEnum.MONEY);
        varYjlx = ArithmeticUtil.round(varYjlx, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易份额 * 交易明细.单位面值
        param.setVarCostSubjectAmt(ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
            ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue())));
        // 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 + 变量.溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doAdd(param.getVarYzjSubjectAmt(), varZyj));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 + 变量.应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), varYjlx));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生利息调整 += 变量.溢折价
            paramT1.setZyjSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getZyjSubjectBalance(), varZyj));
            // 变量.T+1发生应计利息 += 交易明细.利息总额
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getYjlxSubjectBalance(), dealNow.getIntAmt()));
            // 变量.T+1发生成本 += 面值
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.doAdd(paramT1.getCbSubjectBalance(), varMz));
            // 变量.T+1发生数量 += 交易明细.交易份额
            paramT1.setNumBalance(
                ArithmeticUtil.doAdd(paramT1.getNumBalance(), dealNow.getShareAmt()));

            // 变量.发生额集 = 变量.T+1买入交易.get（交易明细.交割日）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1BuyDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1BuyDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生利息调整 += 变量.溢折价
            dealHappenAmtDto.setHappenLxtz(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), varZyj));
            // 变量.发生额集.发生应计利息 += 交易明细.利息总额
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), dealNow.getIntAmt()));
            // 变量.发生额集.发生成本 += 面值
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), varMz));
            // 变量.发生额集.发生数量 += 交易明细.交易份额
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), dealNow.getShareAmt()));
            paramT1.getT1BuyDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(场景元素.在途日期 == Null?交易明细.交易日：场景元素.在途日期，true)
        param.getRetryDateMap().put(accountSceneMap.get(BookConstant.ONWAY_DATE)== null?dealNow.getTradeDate():(Date)accountSceneMap.get(BookConstant.ONWAY_DATE), true);
        /**ADD BY WUJIE ON 20191216 END**/

    }

    /**
     * 综合收益的情况场景处理(申购)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvociApply(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                 FinProductDealInfoComDTO dealNow,
                                 FinProductDealInfoResult acctEntity, DealParamterDto param,
                                 SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_买入.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // accountAmountMap设置
        // 交易各类费用处理
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 交易金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varMz = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varMz, NumberEnum.MONEY));

        // 定义 变量.溢折价 = 0
        BigDecimal varZyj = BigDecimal.ZERO;
        // 定义 变量.应计利息 = 0
        BigDecimal varYjlx = BigDecimal.ZERO;

        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.应计利息 = 0
            varYjlx = BigDecimal.ZERO;
            // 变量.溢折价 = 交易明细.全价总额 - （交易明细.交易份额 * 交易明细.单位面值） - 变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(),
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        else {
            // 变量.应计利息 =交易明细.利息总额
            varYjlx = dealNow.getIntAmt();
            // 变量.溢折价 = 交易明细.全价总额 - 交易明细.利息总额 - （交易明细.交易份额 * 交易明细.单位面值）-变量.交易各种费用.交易费用
            varZyj = ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getIntAmt());
            varZyj = ArithmeticUtil.subtract(varZyj,
                ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue()));
            varZyj = ArithmeticUtil.doAdd(varZyj, dealTypesFeeDto.getFeeAmt());
        }
        // 应计利息 <- 变量.应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));

        // 变量.溢折价 >= 0
        if (varZyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 溢折价(借) <- 变量.溢折价
            accountAmountMap.put(BookConstant.DSCINC_D,
                ArithmeticUtil.round(varZyj, NumberEnum.MONEY));
        }
        else {
            // 溢折价(贷) <- 变量.溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varZyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.溢折价（借）_本位币 = round(变量.溢折价,共通金额小数位) * 变量.汇率;
        BigDecimal 溢折价借本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varZyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**交易金额_本位币						<-		变量.溢折价（借）_本位币 + 面值_本位币 + 应计利息_本位币
                -（印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币) **/
        BigDecimal 交易金额本位币1 = ArithmeticUtil.doAdd(
                溢折价借本位币,//变量.溢折价（借）_本位币
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//应计利息_本位币
        );
        BigDecimal 交易金额本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT)//结算手续费_本位币
        );
        accountAmountMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT,
                ArithmeticUtil.round(ArithmeticUtil.doSub(交易金额本位币1, 交易金额本位币2), NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT  + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varZyj = ArithmeticUtil.round(varZyj, NumberEnum.MONEY);
        varYjlx = ArithmeticUtil.round(varYjlx, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易份额 * 交易明细.单位面值
        param.setVarCostSubjectAmt(ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
            ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue())));
        // 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
        param.setVarCostSubjectNum(
            ArithmeticUtil.doAdd(param.getVarCostSubjectNum(), dealNow.getShareAmt()));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 + 变量.溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doAdd(param.getVarYzjSubjectAmt(), varZyj));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 + 变量.应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doAdd(param.getVarLxSubjectAmt(), varYjlx));

        /**ADD BY WUJIE ON 20191216 START**/
        //变量.重算日期集合.put(交易明细.交易日，true)
        param.getRetryDateMap().put(dealNow.getTradeDate(), true);
        /**ADD BY WUJIE ON 20191216 END**/
    }

    /**
     * 综合收益的情况场景处理(卖出)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvociSell(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                FinProductDealInfoComDTO dealNow,
                                FinProductDealInfoResult acctEntity, DealParamterDto param,
                                SecT1HandleParamterDto paramT1) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_卖出.getValue());

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + DictCodeList.交易方向_卖出.getCodeName() + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        Date settleDate = null;
        /** modify by yanyan 在途日期根据设计调整 20200102 start**/
        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
            //settleDate = dealNow.getSettleDate();
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else if(DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket())) {
            //在途日期		<-		交易明细.交易日 + 1
            settleDate = DateUtil.addDay(dealNow.getTradeDate(), 1);
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        else {
            //accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
            //在途日期	<-	交易明细.交易日 == 交割日？Null：交割日
            settleDate = dealNow.getTradeDate().compareTo(dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate();
            accountSceneMap.put(BookConstant.ONWAY_DATE, settleDate);
        }
        /** modify by yanyan 在途日期根据设计调整 20200102 end **/

        // accountAmountMap设置
        // 定义 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额/变量.成本科目数量
        BigDecimal varCjgyjzbd = ArithmeticUtil.divide(
            ArithmeticUtil.multiply(param.getVarGySubjectAmt(), dealNow.getShareAmt()),
            param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
        // 定义 变量.冲减溢折价 = 0
        BigDecimal varCjzyj = BigDecimal.ZERO;
        // 定义 变量.冲减应计利息 = 0
        BigDecimal varCjyjlx = BigDecimal.ZERO;
        // 定义 变量.冲减成本 = 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal varCjcb = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        // 定义 变量.冲减数量 = 交易明细.交易份额
        BigDecimal varCjsl = dealNow.getShareAmt();
        // 定义 变量.利息差额 = 0
        BigDecimal varLxce = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
                dealNow, param, paramT1, settleDate);
            // 变量.冲减公允价值变动 = 变量.T+1处理结果集..公允价值变动科目余额 * 交易明细.交易份额*/变量.T+1处理结果集..成本科目数量
            varCjgyjzbd = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getGyjzbdSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减溢折价 = 变量.T+1处理结果集.溢折价科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            varCjzyj = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getZyjSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
            varCjyjlx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
                    dealNow.getShareAmt()),
                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
        }
        else {

            // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额*/变量.成本科目数量
            varCjgyjzbd = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarGySubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());

            // 变量.冲减溢折价 = 变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            varCjzyj = ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(), dealNow.getShareAmt());
            varCjzyj = ArithmeticUtil.divide(varCjzyj, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());

            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            varCjyjlx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
        }
        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            varCjyjlx = dealNow.getIntAmt();
        }
        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.冲减应计利息 = 0
            varCjyjlx = BigDecimal.ZERO;
        }

        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));

        /** add by yanya on 20200228 通道费账务明细代码**/
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, dealTypesFeeDto.getChlFeeCode());

        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 变量.利息差额 = 交易明细.利息总额 - 变量.冲减应计利息
        // if（!变量.标的信息.息票品种为零息或贴现时）
        if (!DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            && !DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            varLxce = ArithmeticUtil.subtract(dealNow.getIntAmt(), varCjyjlx);
        }
        // 变量.买卖价差 = 交易明细.交易金额 - (变量.冲减成本 + 变量.冲减溢折价 ) - 变量.冲减应计利息 - 变量.交易各种费用.不随交易费用 +
        // 变量.交易各种费用.通道费
        varMmjc = ArithmeticUtil.subtract(dealNow.getTradeAmt(),
            ArithmeticUtil.doAdd(varCjcb, varCjzyj));
        varMmjc = ArithmeticUtil.subtract(varMmjc, varCjyjlx);
        varMmjc = ArithmeticUtil.subtract(varMmjc, dealTypesFeeDto.getNoFeeAmt());
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        varMmjc = ArithmeticUtil.subtract(varMmjc, varLxce);

        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varCjcb, NumberEnum.MONEY));
        // 应计利息 <- 变量.冲减应计利息
        accountAmountMap.put(BookConstant.INTAMT,
            ArithmeticUtil.round(varCjyjlx, NumberEnum.MONEY));

        // 变量.利息差额 >= 0
        if (varLxce.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <- 变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(varLxce, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varLxce, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.冲减溢折价>= 0
        if (varCjzyj.compareTo(BigDecimal.ZERO) >= 0) {
            // 剩余溢折价（贷） <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY));
        }
        else {
            // 剩余溢折价（借） <- 变量.冲减溢折价*-1
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjzyj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.冲减公允价值变动 >= 0
        if (varCjgyjzbd.compareTo(BigDecimal.ZERO) >= 0) {
            // 公允价值变动(贷) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(varCjgyjzbd, NumberEnum.MONEY));
            // 冲减公允价值变动(借) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                ArithmeticUtil.round(varCjgyjzbd, NumberEnum.MONEY));
        }
        else {
            // 公允价值变动(借) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjgyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
            // 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjgyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varLxce, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 冲减溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币		=	(交易金额_本位币 + 实付通道费_本位币)
                - 	(面值_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 + 冲减溢折价（贷）_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币)**/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//交易金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                冲减溢折价贷本位币,//冲减溢折价（贷）_本位币
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//利息金额_本位币
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        varCjcb = ArithmeticUtil.round(varCjcb, NumberEnum.MONEY);
        varCjsl = ArithmeticUtil.round(varCjsl, NumberEnum.QTY);
        varCjyjlx = ArithmeticUtil.round(varCjyjlx, NumberEnum.MONEY);
        varCjzyj = ArithmeticUtil.round(varCjzyj, NumberEnum.MONEY);
        varCjgyjzbd = ArithmeticUtil.round(varCjgyjzbd, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), varCjcb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), varCjsl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), varCjyjlx));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.溢折价科目余额
        param.setVarYzjSubjectAmt(ArithmeticUtil.subtract(param.getVarYzjSubjectAmt(), varCjzyj));
        // 变量.公允价值变动科目余额 = 变量.公允价值变动科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.subtract(param.getVarGySubjectAmt(), varCjgyjzbd));

        // if（场景元素.在途日期 != 空）
        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生公允价值变动 -= 变量.冲减公允价值变动
            paramT1.setGyjzbdSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getGyjzbdSubjectBalance(), varCjgyjzbd));
            // 变量.T+1发生利息调整 -= 变量.冲减溢折价
            paramT1.setZyjSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getZyjSubjectBalance(), varCjzyj));
            // 变量.T+1发生应计利息 -= 变量.冲减应计利息
            paramT1.setYjlxSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getYjlxSubjectBalance(), varCjyjlx));
            // 变量.T+1发生成本 -= 变量.冲减成本
            paramT1.setCbSubjectBalance(
                ArithmeticUtil.subtract(paramT1.getCbSubjectBalance(), varCjcb));
            // 变量.T+1发生数量 -= 变量.冲减数量
            paramT1.setNumBalance(ArithmeticUtil.subtract(paramT1.getNumBalance(), varCjsl));
            // 变量.发生额集 = 变量.T+1买入交易.get（交易明细.交割日）
            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1SellDeal().get(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
                dealHappenAmtDto = new DealHappenAmtDto();
                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1买入交易.get（交易明细.交割日,变量.发生额集）
                paramT1.getT1SellDeal().put(
                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                    dealHappenAmtDto);
            }
            // 变量.发生额集.发生公允价值变动 += 变量.冲减公允价值变动
            dealHappenAmtDto.setHappenGujzbd(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenGujzbd(), varCjgyjzbd));
            // 变量.发生额集.发生利息调整 += 变量.冲减溢折价
            dealHappenAmtDto.setHappenLxtz(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), varCjzyj));
            // 变量.发生额集.发生应计利息 += 变量.冲减应计利息
            dealHappenAmtDto.setHappenYjlx(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), varCjyjlx));
            // 变量.发生额集.发生成本 += 变量.冲减成本
            dealHappenAmtDto.setHappenCb(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), varCjcb));
            // 变量.发生额集.发生数量 += 变量.冲减数量
            dealHappenAmtDto.setHappenNum(
                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), varCjsl));
            paramT1.getT1SellDeal().put(
                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
                dealHappenAmtDto);
        }
    }

    /**
     * 债券T+1方法 <功能详细描述>
     * 
     * @param rstBaseinfo
     * @param acctEntity
     * @param dealNow
     * @param param
     * @param paramT1
     * @return
     * @see
     * @since 1.0
     */
    private SecT1HandleResultDto doSecT1Handle(TargetFinProduct rstBaseinfo,
                                               FinProductDealInfoResult acctEntity,
                                               FinProductDealInfoComDTO dealNow,
                                               DealParamterDto param,
                                               SecT1HandleParamterDto paramT1, Date settleDate) {
        // 调用共通债券T+1处理
        SecT1HandleParamterDto secT1HandleParamterDto = new SecT1HandleParamterDto();
        // 数量余额 变量.成本科目余额 - 变量.T+1发生成本
        secT1HandleParamterDto.setNumBalance(
            ArithmeticUtil.subtract(param.getVarCostSubjectNum(), paramT1.getNumBalance()));
        // 成本类科目余额 变量.成本科目数量 - 变量.T+1发生数量
        secT1HandleParamterDto.setCbSubjectBalance(
            ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), paramT1.getCbSubjectBalance()));
        // 应计利息科目余额 变量.应计利息科目余额 - 变量.T+1发生应计利息
        secT1HandleParamterDto.setYjlxSubjectBalance(
            ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), paramT1.getYjlxSubjectBalance()));
        // 公允价值变动类科目余额 变量.公允价值变动科目余额 - 变量.T+1发生公允价值变动
        secT1HandleParamterDto.setGyjzbdSubjectBalance(ArithmeticUtil.subtract(
            param.getVarGySubjectAmt(), paramT1.getGyjzbdSubjectBalance()));
        // 溢折价类科目余额 变量.溢折价科目余额 - 变量.T+1发生利息调整
        secT1HandleParamterDto.setZyjSubjectBalance(
            ArithmeticUtil.subtract(param.getVarYzjSubjectAmt(), paramT1.getZyjSubjectBalance()));
        // 投资目的 交易类账务处理结果.投资目的
        secT1HandleParamterDto.setInvAim(acctEntity.getInvAim());
        // 交易日 交易明细.交易日
        secT1HandleParamterDto.setDealDate(dealNow.getTradeDate());
        // 交割日 交易明细.交割日
        secT1HandleParamterDto.setSettleDate(settleDate);
        // 业务明细代码 交易类账务处理结果.业务明细代码
        secT1HandleParamterDto.setBusiId(dealNow.getFinprodId());
        // 是否零息/贴现 变量.标的信息.息票品种为零息或贴现时 为是，否则 为否
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            secT1HandleParamterDto.setNotLxtx(true);
        }
        else {
            secT1HandleParamterDto.setNotLxtx(false);
        }
        // 账套代码 交易类账务处理结果.账套代码
        secT1HandleParamterDto.setBooksetId(acctEntity.getBooksetId());
        // T+1买入交易 变量.T+1买入交易
        secT1HandleParamterDto.setT1BuyDeal(paramT1.getT1BuyDeal());
        // T+1卖出交易 变量.T+1卖出交易
        secT1HandleParamterDto.setT1SellDeal(paramT1.getT1SellDeal());
        // 交易场所
        secT1HandleParamterDto.setTradeMarket(rstBaseinfo.getTradeMarket());
        /**ADD BY WUJIE ON 20191218 START**/
        secT1HandleParamterDto.setBokDetailId(acctEntity.getBokDetailId());
        
        secT1HandleParamterDto.setRetryDateMap(param.getRetryDateMap());
        
        /**ADD BY WUJIE ON 20191218 END**/
        return secT1HandleService.doSecT1Handle(secT1HandleParamterDto);
    }

    /**
     * 公允价值类的情况场景处理(行权回售/行权赎回)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvtplRight(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                 FinProductDealInfoComDTO dealNow,
                                 FinProductDealInfoResult acctEntity, DealParamterDto param,
                                 SecT1HandleParamterDto paramT1, String varSecDealFeeSubject,
                                 String tableId) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 定义 变量.在途日期
//        Date onwayDate = null;
//        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
//        	onwayDate = dealNow.getSettleDate();
//        }
//        else {
//            accountSceneMap.put(BookConstant.ONWAY_DATE,
//            onwayDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
//        }
        // accountAmountMap设置
        // 变量.冲减公允价值变动
        BigDecimal cjGyJzBd = BigDecimal.ZERO;
        // 变量.冲减应计利息
        BigDecimal cjYjLx = BigDecimal.ZERO;
        // 变量.冲减数量
        BigDecimal cjSl = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 变量.冲减成本
        BigDecimal cjCb = BigDecimal.ZERO;
        // 变量.利息差额
        BigDecimal lxCe = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;
        // if（场景元素.在途日期 != 空）
//        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
//            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
//                dealNow, param, paramT1, onwayDate);
            // 变量.冲减成本 = 变量.T+1处理结果集.成本科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjCb = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getCbSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减公允价值变动 = 变量.T+1处理结果集.公允价值变动科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjGyJzBd = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getGyjzbdSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjYjLx = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
//        }
//        else {
            // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjCb = ArithmeticUtil.multiply(param.getVarCostSubjectAmt(), cjSl);
            cjCb = ArithmeticUtil.divide(cjCb, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjGyJzBd = ArithmeticUtil.multiply(param.getVarGySubjectAmt(), cjSl);
            cjGyJzBd = ArithmeticUtil.divide(cjGyJzBd, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYjLx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
//        }

        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        // if （【系统参数】.冲减应计利息方式 != null && 【系统参数】.冲减应计利息方式 = 数据字典.冲减应计利息方式.按实付利息冲减）
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            // 变量.冲减应计利息 = 交易明细.利息总额
            cjYjLx = dealNow.getIntAmt();
        }
        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 交易费用 <- 变量.交易各种费用.交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

        // 变量.费用损益计入方式 = '计入投资收益'情况
        if (DictCodeList.债券交易费用损益计入方式_计入投资收益.getValue().equals(varSecDealFeeSubject)) {
            // 变量.买卖价差 <- 0
            varMmjc = BigDecimal.ZERO;
        }
        else {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            BigDecimal VAR_STAMPTAX_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getStampDuty(),
                dealTypesFeeDto.getStampDutyForDealPay());
            accountAmountMap.put(BookConstant.STAMPTAX_OUT, VAR_STAMPTAX_OUT);
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            BigDecimal VAR_HANDFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getBrokerage(),
                dealTypesFeeDto.getBrokerageForDealPay());
            accountAmountMap.put(BookConstant.HANDFEE_OUT, VAR_HANDFEE_OUT);
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            BigDecimal VAR_CERTIFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getCertificateFee(),
                dealTypesFeeDto.getCertificateFeeForDealPay());
            accountAmountMap.put(BookConstant.CERTIFEE_OUT, VAR_CERTIFEE_OUT);
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            BigDecimal VAR_SETTLEFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getClearingFee(),
                dealTypesFeeDto.getClearingFeeForDealPay());
            accountAmountMap.put(BookConstant.SETTLEFEE_OUT, VAR_SETTLEFEE_OUT);
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            BigDecimal VAR_COMMISSION_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getCommission(),
                dealTypesFeeDto.getCommissionForDealPay());
            accountAmountMap.put(BookConstant.COMMISSION_OUT, VAR_COMMISSION_OUT);
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            BigDecimal VAR_TRADEFEE_OUT = ArithmeticUtil.doAdd(dealTypesFeeDto.getTransactionFee(),
                dealTypesFeeDto.getTransactionFeeForDealPay());
            accountAmountMap.put(BookConstant.TRADEFEE_OUT, VAR_TRADEFEE_OUT);
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            BigDecimal VAR_SETTLEMENTFEE_OUT = ArithmeticUtil.doAdd(
                dealTypesFeeDto.getSettlementFee(), dealTypesFeeDto.getSettlementFeeForDealPay());
            accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT, VAR_SETTLEMENTFEE_OUT);

            // 变量.买卖价差 <- 印花税支出 + 经手费支出 + 证管费支出 + 结算费支出 + 佣金支出 + 交易手续费支出 + 结算手续费支出
            varMmjc = ArithmeticUtil.doAdd(VAR_STAMPTAX_OUT, VAR_HANDFEE_OUT, VAR_CERTIFEE_OUT,
                VAR_SETTLEFEE_OUT, VAR_COMMISSION_OUT, VAR_TRADEFEE_OUT, VAR_SETTLEMENTFEE_OUT);
        }

        // 成本金额值 <- 变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE, ArithmeticUtil.round(cjCb, NumberEnum.MONEY));
        // 应计利息 <- 变量.冲减应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(cjYjLx, NumberEnum.MONEY));

        // 利息差额 = 交易明细.利息总额 - 变量.冲减应计利息
        lxCe = ArithmeticUtil.subtract(dealNow.getIntAmt(), cjYjLx);

        // 变量.买卖价差 = 变量.买卖价差 + 交易明细.交易金额 - 变量.冲减成本 - 变量.交易各种费用.不随交易费用 + 各种变量.交易各种费用.通道费
        // -变量.冲减应计利息 - 变量.利息差额
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealNow.getTradeAmt());
        varMmjc = ArithmeticUtil.subtract(varMmjc, cjCb);
        varMmjc = ArithmeticUtil.subtract(varMmjc, dealTypesFeeDto.getNoFeeAmt());
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        varMmjc = ArithmeticUtil.doSub(varMmjc, cjYjLx, lxCe);

        // 变量.利息差额 >= 0
        if (lxCe.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <- 变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(lxCe, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(lxCe, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 变量.冲减公允价值变动 >= 0
        if (cjGyJzBd.compareTo(BigDecimal.ZERO) >= 0) {
            // 公允价值变动(贷) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY));
            // 冲减公允价值变动(借) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                ArithmeticUtil.round(cjGyJzBd, NumberEnum.MONEY));
        }
        else {
            // 公允价值变动(借) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyJzBd, new BigDecimal(-1)), NumberEnum.MONEY));
            // 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyJzBd, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(ArithmeticUtil.round(lxCe, NumberEnum.MONEY), 汇率), NumberEnum.MONEY);
        //定义 变量.公允价值变动(贷)_本位币 = round(变量.公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 公允价值变动贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(cjGyJzBd, 汇率), NumberEnum.MONEY);
        //定义 变量.变量.冲减公允价值变动（借)_本位币 = round(变量.变量.冲减公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 冲减公允价值变动借本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(cjGyJzBd, 汇率), NumberEnum.MONEY);

        /**定义 变量.买卖价差_本位币 =	(交易金额_本位币 + 变量.变量.冲减公允价值变动（借)_本位币 + 实付通道费_本位币 + 结算手续费支出 + 印花税支出_本位币
                + 经手费支出_本位币 + 证管费支出_本位币 + 结算费支出_本位币 + 佣金支出_本位币 + 交易手续费支出_本位币)
        - 	(成本金额_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 + 变量.公允价值变动(贷)_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//交易金额_本位币
                //冲减公允价值变动借本位币,//冲减公允价值变动（借)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT),//实付通道费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT + BookConstant.BCCYAMT),//结算手续费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT + BookConstant.BCCYAMT),//印花税支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT + BookConstant.BCCYAMT),//经手费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT + BookConstant.BCCYAMT),//证管费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT + BookConstant.BCCYAMT),//结算费支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT + BookConstant.BCCYAMT),//佣金支出_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT + BookConstant.BCCYAMT));//交易手续费支出_本位币
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                //公允价值变动贷本位币,//变量.公允价值变动(贷)_本位币
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)); //利息金额_本位币
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币 <- 变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（贷）_本位币<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), cjCb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), cjSl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), cjYjLx));
        // 变量.公允价值变动科目余额 = 变量.公允价值变动科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.subtract(param.getVarGySubjectAmt(), cjGyJzBd));

    }

    /**
     * 摊余成本类的情况场景处理(行权回售/行权赎回)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryAcRight(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                              FinProductDealInfoComDTO dealNow,
                              FinProductDealInfoResult acctEntity, DealParamterDto param,
                              SecT1HandleParamterDto paramT1, String tableId) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 定义 变量.在途日期
//        Date onwayDate = null;
//        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
//        	onwayDate = dealNow.getSettleDate();
//        }
//        else {
//            accountSceneMap.put(BookConstant.ONWAY_DATE,
//            onwayDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
//        }
        // accountAmountMap设置
        // 变量.冲减溢折价
        BigDecimal cjYzj = BigDecimal.ZERO;
        // 变量.冲减应计利息
        BigDecimal cjYjLx = BigDecimal.ZERO;
        // 变量.冲减数量 = 交易明细.交易份额
        BigDecimal cjSl = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 变量.冲减成本 = 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal cjCb = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        // 变量.利息差额
        BigDecimal lxCe = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;
        // if（场景元素.在途日期 != 空）
//        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
//            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
//                dealNow, param, paramT1, onwayDate);
            // 变量.冲减成本 = 变量.T+1处理结果集.成本科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjCb = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getCbSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减溢折价 = 变量.T+1处理结果集.溢折价科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjYzj = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getZyjSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjYjLx = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
//        }
//        else {
            // 变量.冲减成本 = 变量.成本科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjCb = ArithmeticUtil.multiply(param.getVarCostSubjectAmt(), cjSl);
            cjCb = ArithmeticUtil.divide(cjCb, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减溢折价 = 变量.变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYzj = ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(), cjSl);
            cjYzj = ArithmeticUtil.divide(cjYzj, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYjLx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
//        }

        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        // if （【系统参数】.冲减应计利息方式 != null && 【系统参数】.冲减应计利息方式 = 数据字典.冲减应计利息方式.按实付利息冲减）
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            // 变量.冲减应计利息 = 交易明细.利息总额
            cjYjLx = dealNow.getIntAmt();
        }
        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.冲减应计利息 = 0
            cjYjLx = BigDecimal.ZERO;
        }
        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());

        // if（!变量.标的信息.息票品种为零息或贴现时）
        if (!(DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
              || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies()))) {
            // 变量.利息差额 = 交易明细.利息总额 - 变量.冲减应计利息
            lxCe = ArithmeticUtil.subtract(dealNow.getIntAmt(), cjYjLx);
        }
        // 变量.买卖价差 = 交易明细.交易金额 - (变量.冲减成本 + 变量.冲减溢折价 ) - 变量.冲减应计利息 - 变量.交易各种费用.不随交易费用
        // + 各种变量.交易各种费用.通道费 - 变量.利息差额
        varMmjc = ArithmeticUtil.subtract(dealNow.getTradeAmt(),
            ArithmeticUtil.doAdd(cjCb, cjYzj));
        varMmjc = ArithmeticUtil.subtract(varMmjc, cjYjLx);
        varMmjc = ArithmeticUtil.subtract(varMmjc, dealTypesFeeDto.getNoFeeAmt());
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        varMmjc = ArithmeticUtil.subtract(varMmjc, lxCe);

        // 交易费用 <- 变量.交易各种费用.交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE, ArithmeticUtil.round(cjCb, NumberEnum.MONEY));
        // 应计利息 <- 变量.冲减应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(cjYjLx, NumberEnum.MONEY));

        // 变量.利息差额 >= 0
        if (lxCe.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <- 变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(lxCe, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(lxCe, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        // if（变量.冲减溢折价>= 0）
        if (cjYzj.compareTo(BigDecimal.ZERO) >= 0) {
        	// 冲减溢折价(贷) <- 变量.冲减溢折价*-1
        	accountAmountMap.put(BookConstant.DSCINC_C,
                    ArithmeticUtil.round(cjYzj, NumberEnum.MONEY));
        }
        else {
            // 冲减溢折价(借) <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(cjYzj, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(lxCe, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 冲减溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(cjYzj, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币	 =	(实收金额_本位币 + 实付通道费_本位币)
                - 	(面值_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 + 冲减溢折价（贷）_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//实收金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//实付通道费_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//面值_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                /**工单号：YHZGCPB20200612003 【债券行权回售，账务原币或本币随机一方不平；】 合并东莞代码   modify by 李志 20200702 start */
                冲减溢折价贷本位币,//冲减溢折价（贷）_本位币
                /**工单号：YHZGCPB20200612003 【债券行权回售，账务原币或本币随机一方不平；】合并东莞代码   modify by 李志 20200702 end */
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)//利息金额_本位币
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.doSub(param.getVarCostSubjectAmt(), cjCb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.doSub(param.getVarCostSubjectNum(), cjSl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doSub(param.getVarLxSubjectAmt(), cjYjLx));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doSub(param.getVarYzjSubjectAmt(), cjYzj));

        // if（场景元素.在途日期 != 空）
//        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生利息调整 -= 变量.冲减溢折价
//            paramT1.setZyjSubjectBalance(
//                ArithmeticUtil.doSub(paramT1.getZyjSubjectBalance(), cjYzj));
            // 变量.T+1发生应计利息 -= 变量.冲减应计利息
//            paramT1.setYjlxSubjectBalance(
//                ArithmeticUtil.doSub(paramT1.getYjlxSubjectBalance(), cjYjLx));
            // 变量.T+1发生成本 -= 变量.冲减成本
//            paramT1.setCbSubjectBalance(ArithmeticUtil.doSub(paramT1.getCbSubjectBalance(), cjCb));
            // 变量.T+1发生数量 -= 变量.冲减数量
//            paramT1.setNumBalance(ArithmeticUtil.doSub(paramT1.getNumBalance(), cjSl));

            // 变量.发生额集 = 变量.T+1买入交易.get（场景元素.在途日期）
//            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1BuyDeal().get(
//                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
//            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
//                dealHappenAmtDto = new DealHappenAmtDto();
//                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1卖出交易.put（场景元素.在途日期,变量.发生额集）
//                paramT1.getT1BuyDeal().put(
//                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
//                    dealHappenAmtDto);
//            }
            // 变量.发生额集.发生利息调整 += 变量.冲减溢折价
//            dealHappenAmtDto.setHappenLxtz(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), cjYzj));
            // 变量.发生额集.发生应计利息 += 变量.冲减应计利息
//            dealHappenAmtDto.setHappenYjlx(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), cjYjLx));
            // 变量.发生额集.发生成本 += 变量.冲减成本
//            dealHappenAmtDto.setHappenCb(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), cjCb));
            // 变量.发生额集.发生数量 += 变量.冲减数量
//            dealHappenAmtDto.setHappenNum(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), cjSl));
//            paramT1.getT1BuyDeal().put(
//                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
//                dealHappenAmtDto);
//        }

    }

    /**
     * 综合收益类的情况场景处理(行权回售/行权赎回)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @param param varSecDealFeeSubject 费用损益计入方式
     * @see
     * @since 1.0
     */
    private void quaryFvociRight(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                 FinProductDealInfoComDTO dealNow,
                                 FinProductDealInfoResult acctEntity, DealParamterDto param,
                                 SecT1HandleParamterDto paramT1, String tableId) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
            "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                      + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 定义 变量.在途日期
//        Date onwayDate = null;
//        if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
//        	onwayDate = dealNow.getSettleDate();
//        }
//        else {
//            accountSceneMap.put(BookConstant.ONWAY_DATE,
//            onwayDate = DateUtils.addDays(dealNow.getTradeDate(), 1));
//        }
        // accountAmountMap设置
        // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额/变量.成本科目数量
        BigDecimal cjGyjzbd = ArithmeticUtil.divide(
            ArithmeticUtil.multiply(param.getVarGySubjectAmt(), dealNow.getShareAmt()),
            param.getVarCostSubjectAmt());
        // 变量.冲减溢折价
        BigDecimal cjYzj = BigDecimal.ZERO;
        // 变量.冲减应计利息
        BigDecimal cjYjLx = BigDecimal.ZERO;
        // 变量.冲减数量 = 交易明细.交易份额
        BigDecimal cjSl = dealNow.getShareAmt() == null ? BigDecimal.ZERO : dealNow.getShareAmt();
        // 变量.冲减成本 = 交易明细.交易份额 * 交易明细.单位面值
        BigDecimal cjCb = ArithmeticUtil.multiply(dealNow.getShareAmt(), dealNow.getParValue());
        // 变量.利息差额
        BigDecimal lxCe = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;
        // if（场景元素.在途日期 != 空）
//        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 调用共通债券T+1处理
//            SecT1HandleResultDto secT1HandleResultDto = doSecT1Handle(rstBaseinfo, acctEntity,
//                dealNow, param, paramT1, onwayDate);
            // 变量.冲减公允价值变动 = 变量.T+1处理结果集..公允价值变动科目余额 * 交易明细.交易份额*/变量.T+1处理结果集.成本科目数量
//            cjGyjzbd = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getGyjzbdSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减溢折价 = 变量.T+1处理结果集.溢折价科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjYzj = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getZyjSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.T+1处理结果集.应计利息科目余额 * 交易明细.交易份额 / 变量.T+1处理结果集.成本科目数量
//            cjYjLx = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(secT1HandleResultDto.getYjlxSubjectBalance(),
//                    dealNow.getShareAmt()),
//                secT1HandleResultDto.getNumBalance(), NumberEnum.MONEY.getCode());
//        }
//        else {
            // 变量.冲减公允价值变动 = 变量.公允价值变动科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjGyjzbd = ArithmeticUtil.multiply(param.getVarGySubjectAmt(), cjSl);
            cjGyjzbd = ArithmeticUtil.divide(cjGyjzbd, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减溢折价 = 变量.变量.溢折价科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYzj = ArithmeticUtil.multiply(param.getVarYzjSubjectAmt(), cjSl);
            cjYzj = ArithmeticUtil.divide(cjYzj, param.getVarCostSubjectNum(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减应计利息 = 变量.应计利息科目余额 * 交易明细.交易份额 / 变量.成本科目数量
            cjYjLx = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(param.getVarLxSubjectAmt(), dealNow.getShareAmt()),
                param.getVarCostSubjectNum(), NumberEnum.MONEY.getCode());
//        }

        // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
        String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE");
        // if （【系统参数】.冲减应计利息方式 != null && 【系统参数】.冲减应计利息方式 = 数据字典.冲减应计利息方式.按实付利息冲减）
        if (StringUtils.isNotEmpty(offsetIntamtMode)
            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
            // 变量.冲减应计利息 = 交易明细.利息总额
            cjYjLx = dealNow.getIntAmt();
        }
        // 变量.标的信息.息票品种为零息或贴现时
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            // 变量.冲减应计利息 = 0
            cjYjLx = BigDecimal.ZERO;
        }
        // if（!变量.标的信息.息票品种为零息或贴现时）
        if (!(DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
              || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies()))) {
            // 变量.利息差额 = 交易明细.利息总额 - 变量.冲减应计利息
            lxCe = ArithmeticUtil.subtract(dealNow.getIntAmt(), cjYjLx);
        }
        // 费用处理共通
        DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
            dealNow.getValTradeFeeDetailList(), dealNow.getProdId(), dealNow.getPortfolioId());
        // 变量.买卖价差 = 交易明细.交易金额 - (变量.冲减成本 + 变量.冲减溢折价 ) - 变量.冲减应计利息 - 变量.交易各种费用.不随交易费用
        // + 各种变量.交易各种费用.通道费 - 变量.利息差额
        varMmjc = ArithmeticUtil.subtract(dealNow.getTradeAmt(),
            ArithmeticUtil.doAdd(cjCb, cjYzj));
        varMmjc = ArithmeticUtil.subtract(varMmjc, cjYjLx);
        varMmjc = ArithmeticUtil.subtract(varMmjc, dealTypesFeeDto.getNoFeeAmt());
        varMmjc = ArithmeticUtil.doAdd(varMmjc, dealTypesFeeDto.getChlFee(),
            dealTypesFeeDto.getChlGlFee(), dealTypesFeeDto.getChlXsFee(),
            dealTypesFeeDto.getChlTgFee());
        varMmjc = ArithmeticUtil.subtract(varMmjc, lxCe);

        // 交易费用 <- 变量.交易各种费用.交易费用
        accountAmountMap.put(BookConstant.FEE, dealTypesFeeDto.getFeeAmt());
        // 印花税
        accountAmountMap.put(BookConstant.STAMPTAX, dealTypesFeeDto.getStampDuty());
        // 经手费
        accountAmountMap.put(BookConstant.HANDFEE, dealTypesFeeDto.getBrokerage());
        // 证管费
        accountAmountMap.put(BookConstant.CERTIFEE, dealTypesFeeDto.getCertificateFee());
        // 结算费
        accountAmountMap.put(BookConstant.SETTLEFEE, dealTypesFeeDto.getClearingFee());
        // 佣金
        accountAmountMap.put(BookConstant.COMMISSION, dealTypesFeeDto.getCommission());
        // 交易手续费
        accountAmountMap.put(BookConstant.TRADEFEE, dealTypesFeeDto.getTransactionFee());
        // 结算手续费
        accountAmountMap.put(BookConstant.SETTLEMENTFEE, dealTypesFeeDto.getSettlementFee());
        // 实付通道费 <-变量.交易各种费用.通道费
        accountAmountMap.put(BookConstant.CHLAMT,
            ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
        // 实付通道管理费 <- 变量.交易各种费用.通道管理费
        // 实付通道销售费 <- 变量.交易各种费用.通道销售费
        // 实付通道托管费 <- 变量.交易各种费用.通道托管费

        // 实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT,
            ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
        // 面值 <- 变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE, ArithmeticUtil.round(cjCb, NumberEnum.MONEY));
        // 应计利息 <- 变量.冲减应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(cjYjLx, NumberEnum.MONEY));

        // 变量.利息差额 >= 0
        if (lxCe.compareTo(BigDecimal.ZERO) >= 0) {
            // 利息差额（贷） <- 变量.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C,
                ArithmeticUtil.round(lxCe, NumberEnum.MONEY));
        }
        else {
            // 利息差额（借） <-变量.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(lxCe, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        // if（变量.冲减溢折价>= 0）
        if (cjYzj.compareTo(BigDecimal.ZERO) >= 0) {
        	// 冲减溢折价(贷) <- 变量.冲减溢折价*-1
        	accountAmountMap.put(BookConstant.DSCINC_C,
                    ArithmeticUtil.round(cjYzj, NumberEnum.MONEY));
        }
        else {
            // 冲减溢折价(借) <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(cjYzj, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        
        // if(变量.冲减公允价值变动 >= 0 )
        // 变量.冲减公允价值变动 >= 0
        if (cjGyjzbd.compareTo(BigDecimal.ZERO) >= 0) {
            // 公允价值变动(贷) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(cjGyjzbd, NumberEnum.MONEY));
            // 冲减公允价值变动(借) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                ArithmeticUtil.round(cjGyjzbd, NumberEnum.MONEY));
        }
        else {
            // 公允价值变动(借) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
            // 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(cjGyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // accountAmountMap再设置如下
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, dealTypesFeeDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, dealTypesFeeDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE,
            dealTypesFeeDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE,
            dealTypesFeeDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION,
            dealTypesFeeDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE,
            dealTypesFeeDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE,
            dealTypesFeeDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        // 通道管理费账务明细代码 <- 变量.交易各种费用.通道管理费账务明细代码
        // 通道销售费账务明细代码 <- 变量.交易各种费用.通道销售费账务明细代码
        // 通道托管费账务明细代码 <- 变量.交易各种费用.通道托管费账务明细代码

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(lxCe, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        //定义		变量.冲减溢折价（贷）_本位币 = round(变量.冲减溢折价,共通金额小数位) * 变量.汇率
        BigDecimal 冲减溢折价贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(cjGyjzbd, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币	 =	(交易金额_本位币  + 实付通道费_本位币)
        - 	(成本金额_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
                + 结算手续费_本位币 +  变量.利息差额(贷)_本位币 + 利息金额_本位币 + 冲减溢折价（贷）_本位币)**/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//交易金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//应计利息_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额_本位币
                accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT),//结算手续费_本位币
                利息差额贷本位币,//变量.利息差额(贷)_本位币
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),//利息金额_本位币
                冲减溢折价贷本位币//冲减溢折价（贷）_本位币)
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        if (买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.doSub(param.getVarCostSubjectAmt(), cjCb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.doSub(param.getVarCostSubjectNum(), cjSl));
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
        param.setVarLxSubjectAmt(ArithmeticUtil.doSub(param.getVarLxSubjectAmt(), cjYjLx));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.doSub(param.getVarYzjSubjectAmt(), cjYzj));
        // 变量.公允价值变动科目余额 = 变量.公允价值变动科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.doSub(param.getVarGySubjectAmt(), cjGyjzbd));

        // if（场景元素.在途日期 != 空）
//        if (accountSceneMap.get(BookConstant.ONWAY_DATE) != null) {
            // 变量.T+1发生公允价值变动 -= 变量.冲减公允价值变动
//            paramT1.setGyjzbdSubjectBalance(
//                ArithmeticUtil.doSub(paramT1.getGyjzbdSubjectBalance(), cjGyjzbd));
            // 变量.T+1发生利息调整 -= 变量.冲减溢折价
//            paramT1.setZyjSubjectBalance(
//                ArithmeticUtil.doSub(paramT1.getZyjSubjectBalance(), cjYzj));
            // 变量.T+1发生应计利息 -= 变量.冲减应计利息
//            paramT1.setYjlxSubjectBalance(
//                ArithmeticUtil.doSub(paramT1.getYjlxSubjectBalance(), cjYjLx));
            // 变量.T+1发生成本 -= 变量.冲减成本
//            paramT1.setCbSubjectBalance(ArithmeticUtil.doSub(paramT1.getCbSubjectBalance(), cjCb));
            // 变量.T+1发生数量 -= 变量.冲减数量
//            paramT1.setNumBalance(ArithmeticUtil.doSub(paramT1.getNumBalance(), cjSl));

            // 变量.发生额集 = 变量.T+1买入交易.get（场景元素.在途日期）
//            DealHappenAmtDto dealHappenAmtDto = paramT1.getT1BuyDeal().get(
//                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)));
            // if（变量.发生额集 = null）
//            if (dealHappenAmtDto == null) {
                // 初始化 变量.发生额集
//                dealHappenAmtDto = new DealHappenAmtDto();
//                dealHappenAmtDto.setHappenCb(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenGujzbd(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenLxtz(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenNum(BigDecimal.ZERO);
//                dealHappenAmtDto.setHappenYjlx(BigDecimal.ZERO);
                // 变量.T+1卖出交易.put（场景元素.在途日期,变量.发生额集）
//                paramT1.getT1BuyDeal().put(
//                    DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
//                    dealHappenAmtDto);
//            }
            // 变量.发生额集.发生公允价值变动 += 变量.冲减公允价值变动
//            dealHappenAmtDto.setHappenGujzbd(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenGujzbd(), cjGyjzbd));
            // 变量.发生额集.发生利息调整 += 变量.冲减溢折价
//            dealHappenAmtDto.setHappenLxtz(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenLxtz(), cjYzj));
            // 变量.发生额集.发生应计利息 += 变量.冲减应计利息
//            dealHappenAmtDto.setHappenYjlx(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenYjlx(), cjYjLx));
            // 变量.发生额集.发生成本 += 变量.冲减成本
//            dealHappenAmtDto.setHappenCb(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenCb(), cjCb));
            // 变量.发生额集.发生数量 += 变量.冲减数量
//            dealHappenAmtDto.setHappenNum(
//                ArithmeticUtil.doAdd(dealHappenAmtDto.getHappenNum(), cjSl));
//            paramT1.getT1BuyDeal().put(
//                DateUtil.format((Date)accountSceneMap.get(BookConstant.ONWAY_DATE)),
//                dealHappenAmtDto);
//        }
    }

    
    /**
     * 股票成本处理共通
     *
     * @param tradeId 交易编号
     * @return  DTO(股票成本)
     * @see
     * @since 1.0
     */
	public StockCostDto stockCostProcessingCommon(String tradeId, String booksetId) {
		// 定义 变量.债转股_股票交易数据								
		List<StockCostDto> trdFinProductBarter = new  ArrayList<StockCostDto>();
		// 定义 变量.股票成本								
		StockCostDto stockCost = new StockCostDto();
		// 查询计算股票成本所需数据元素
		trdFinProductBarter = secDealAccountsMapper.queryStockCostNeedElement(tradeId);
	
		if(StringUtil.isNotEmpty(trdFinProductBarter)){
			/**MODIFY BY WUJIE ON 20191111 START
			for(StockCostDto item : trdFinProductBarter){
				// 变量.股票成本.成本 = 变量.债转股_股票交易数据.交易本金		按金额小数位进行处理		
				stockCost.setCost(item.getPrinAmt()); 
				ArithmeticUtil.round(stockCost.getCost(), NumberEnum.MONEY);
				// 变量.股票成本.数量 =  变量.债转股_股票交易数据.转股数		按份额小数位进行处理	
				stockCost.setNum(item.getShareAmt());
				ArithmeticUtil.round(stockCost.getNum(), NumberEnum.MONEY);
				
				// 定义 变量.账务明细代码DTO
				BokDetailId bokDetailId= new BokDetailId();
				// 变量.账务明细代码DTO.业务明细代码 = 变量.债转股_股票交易数据.金融产品代码											
				bokDetailId.setBokDetailId(item.getFinprodId());
				// 变量.账务明细代码DTO.业务明细类型 = 变量.债转股_股票交易数据.金融产品类型											
				bokDetailId.setBusiDetailType(item.getFinprodType());																								
				// 调用共通设计->【详细设计-接口共通方法设计.xlsx】->生成MD5账务明细代码SHEET																						
				// 变量.账务明细代码DTO.账务明细代码 = DTO								
				bokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
				// 变量.股票成本.账务明细代码 = 变量.账务明细代码DTO					
				stockCost.setBokDetailId(bokDetailId.getBokDetailId());
			}
			*/
			stockCost = trdFinProductBarter.get(0);
//			// 定义 变量.账务明细代码DTO
//			BokDetailId bokDetailId= interfCommonService.settingDetailSubjectNoTaiL(stockCost.getFinprodId(), stockCost.getFinprodType(), null, null);
//			if(bokDetailId != null){
//				bokDetailId = new BokDetailId();
//				bokDetailId.setDetailSubjectNoTail(stockCost.getFinprodId());
//				bokDetailId.setDetailSubnameTail(stockCost.getFinprodId());
//			}
			BokDetailId bokDetailId = new BokDetailId();
			// 变量.账务明细代码DTO.账套代码= 变量.债转股_股票交易数据.账套代码
			bokDetailId.setBooksetId(booksetId);
			// 变量.账务明细代码DTO.业务明细代码 = 变量.债转股_股票交易数据.金融产品代码											
			bokDetailId.setBusiId(stockCost.getFinprodId());
			// 变量.账务明细代码DTO.业务明细类型 = 变量.债转股_股票交易数据.金融产品类型											
			bokDetailId.setBusiDetailType(stockCost.getFinprodType());																								
			// 调用共通设计->【详细设计-接口共通方法设计.xlsx】->生成MD5账务明细代码SHEET																						
			// 变量.账务明细代码DTO.账务明细代码 = DTO								
			bokDetailId.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
			
			// 变量.股票成本.账务明细代码 = 变量.账务明细代码DTO					
			stockCost.setBokDetailId(bokDetailId);
			/**MODIFY BY WUJIE ON 20191111 END**/
			
		}
		return stockCost;
	}


    /**
     * 获取应收款应计利息-期待方向
     * @param happenDate
     * @param bookDate
     * @param bokDetailId
     * @param branch
     * @param booksetId
     * @param baseSubjectNatrue
     * @param taskPri
     * @param expBalanceDirect
     * @return
     */
	private BigDecimal getReceivablesSubjectBalanceExpectDirection(Date happenDate, Date bookDate, String bokDetailId,
                Long branch, String booksetId, String baseSubjectNatrue, Long taskPri, String expBalanceDirect) {
        BigDecimal lxSubjectAmt = BigDecimal.ZERO;
        //查询交易的到期日对应的应计利息
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        //发生日 = 交易类账务处理结果.发生日
        searchDao.setHappenDate(happenDate);
        //入账日 = 交易类账务处理结果.入账日
        searchDao.setBookDate(bookDate);
        //账务明细代码 = 交易类账务处理结果.账务明细代码
        searchDao.setBokDetailId(bokDetailId);
        //分支序号 = 交易明细.分支序号
        searchDao.setBranch(branch);
        //账套代码 = 交易类账务处理结果.账套代码
        searchDao.setBooksetId(booksetId);
        //基础科目性质 = 其他应收款-利息
        searchDao.setBaseSubjectNatureList(Arrays.asList(baseSubjectNatrue));
        //任务优先级 = 批处理参数.任务优先级
        searchDao.setTaskPri(taskPri);
        //期待余额方向 = 借方
        List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(searchDao, expBalanceDirect);
        if(!StringUtil.isEmptyList(listSubjectBalance)) {
            //变量.其他应收款-利息科目余额 =	上述共通返回的科目余额
            lxSubjectAmt = listSubjectBalance.get(0).getBalance();
        }
        return lxSubjectAmt;
    }

    /**
     * 应收款项转入
     * @param rstBaseinfo
     * @param returnResult
     * @param dealNow
     * @param acctEntity
     * @param param
     */
    private void getReceivablesIn(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult, FinProductDealInfoComDTO dealNow,
                                  FinProductDealInfoResult acctEntity, DealParamterDto param) {
        //初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        //ad.会计场景代码="应收款项转仓(转入)"
        ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转入.getValue());
        // accountSceneMap设置
        // 备注
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "公允价值损益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
                acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }

        // 定义 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
        BigDecimal qtyskxSubjectAmt = param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch()));
        //if（变量.其他应收款-利息科目余额 == null）
        if(qtyskxSubjectAmt == null) {
            //查询交易的到期日对应的应计利息
            qtyskxSubjectAmt = this.getReceivablesSubjectBalanceExpectDirection(dealNow.getHappenDate(), dealNow.getBookDate(),
                    dealNow.getBokDetailId(), dealNow.getBranch(), dealNow.getBooksetId(),
                    DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getTaskPri(), DictCodeList.余额方向_借方.getValue());
            //变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
            param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);
        }
        // accountAmountMap设置
        //应收款项-利息 <- 交易明细.应计利息总额
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));
        //逾期款项-本金 <-交易明细.交易本金
        accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
        //实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //实收金额_本位币	<-	应收款项-利息_本位币 + 逾期款项-本金_本位币
        BigDecimal 实收金额本位币 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),//应收款项-利息_本位币
                accountAmountBCCYAMTMap.get(BookConstant.COST + BookConstant.BCCYAMT)//逾期款项-本金_本位币
        );
        accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT,
                ArithmeticUtil.round(实收金额本位币 ,NumberEnum.MONEY));
        accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        /** modify by yanya on 20200102 增加本位币处理 end**/

        //全局变量的更新
        //变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 + 金额元素.应收款项 - 利息
        qtyskxSubjectAmt  = ArithmeticUtil.doAdd(qtyskxSubjectAmt, accountAmountMap.get(BookConstant.INTAMT));
        //将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
        param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);
        //基础科目性质
        Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();
        accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
        // accountNatrueMap.put(基础科目性质.其他应收款_本金, accountNatrueBranchMap);
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
        //accountNatrueMap.put(基础科目性质.其他应收款_利息, accountNatrueBranchMap);
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
        ad.setAccountNatrueMap(accountNatrueMap);
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

    }

    /**
     * 应收款项转仓转出
     * @param rstBaseinfo
     * @param returnResult
     * @param dealNow
     * @param acctEntity
     * @param param
     */
    private void getReceivablesOut(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult, FinProductDealInfoComDTO dealNow,
                                   FinProductDealInfoResult acctEntity, DealParamterDto param) {
        //初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);

        /** add by yanya on 20200117 增加类型：资产类型、境内外、分类1、分类2 start**/
        // 境内外 = 变量.交易信息.境内外
        accountSceneMap.put(BookConstant.BLN_AREA, rstBaseinfo.getBlnArea());
        //资产类型
        accountSceneMap.put(BookConstant.ASSET_TYPE, rstBaseinfo.getFinprodType());
        //分类1
        accountSceneMap.put(BookConstant.TYPE_1, rstBaseinfo.getType1());
        //分类2
        accountSceneMap.put(BookConstant.TYPE_2, rstBaseinfo.getType2());
        /** add by yanya on 20200117 增加类型：资产类型、境内外、分类1、分类2 end**/


        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        //ad.会计场景代码="应收款项转仓(转入)"
        ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转出.getValue());
        // accountSceneMap设置
        // 备注
        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "公允价值损益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
                acctEntity.getInvAim())) {
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                    "综合收益类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");
        }

        // 定义 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
        BigDecimal qtyskxSubjectAmt = param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch()));
        //if（变量.其他应收款-利息科目余额 == null）
        if(qtyskxSubjectAmt == null) {
            //查询交易的到期日对应的应计利息
            qtyskxSubjectAmt = this.getReceivablesSubjectBalanceExpectDirection(dealNow.getHappenDate(), dealNow.getBookDate(),
                    dealNow.getBokDetailId(), dealNow.getBranch(), dealNow.getBooksetId(),
                    DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getTaskPri(), DictCodeList.余额方向_借方.getValue());
            //变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
            param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);
        }
        // accountAmountMap设置
        //定义		变量.应计利息
        BigDecimal 应计利息 = BigDecimal.ZERO;
        //定义		变量.轧差金额
        BigDecimal 轧差金额 = BigDecimal.ZERO;
        //if（交易明细.是否结清 = "否"）
        if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
            //变量.应计利息	<-		交易明细.应计利息总额
            应计利息 = dealNow.getIntAmt();
            //变量.轧差金额	<-		0
            轧差金额 = BigDecimal.ZERO;
        }
        else {
            //变量.应计利息					<-		变量.其他应收款-利息科目余额
            应计利息 = qtyskxSubjectAmt;
            //变量.轧差金额 = 交易明细.应计利息总额 - 变量.应计利息
            轧差金额 = ArithmeticUtil.doSub(dealNow.getIntAmt(), qtyskxSubjectAmt);
            //if  变量.轧差金额 >= 0
            if(轧差金额 != null && 轧差金额.compareTo(BigDecimal.ZERO) >= 0) {
                //轧差金额(贷)					<-		变量.利息差额
                accountAmountMap.put(BookConstant.NETAMT_C,
                        ArithmeticUtil.round(轧差金额, NumberEnum.MONEY));
            }
            else {
                //轧差金额(借)					<-		变量.利息差额*-1
                accountAmountMap.put(BookConstant.NETAMT_D,
                        ArithmeticUtil.round(ArithmeticUtil.multiply(轧差金额, new BigDecimal(-1)), NumberEnum.MONEY));
            }

        }

        //应收款项-利息 <- 应计利息
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(应计利息, NumberEnum.MONEY));
        //逾期款项-本金 <-交易明细.交易本金
        accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
        //实收金额 <- 交易明细.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        Date happenDateNow = acctEntity.getHappenDate();//发生日
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        //循环本币金额元素，赋值本币
        for (String key : accountAmountBCCYAMTMap.keySet()) {
            accountCurrencyMap.put(key, standardCcyNow);// value值塞入当前交易币种
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.轧差金额_本位币	 = 	实收金额_本位币 - (应收款项-利息_本位币 + 逾期款项-本金)
        BigDecimal 轧差金额本位币 = ArithmeticUtil.doSub(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),//实收金额_本位币
                ArithmeticUtil.doAdd(
                        accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),//应收款项-利息_本位币
                        accountAmountBCCYAMTMap.get(BookConstant.COST + BookConstant.BCCYAMT)//逾期款项-本金
                )
        );
        //if（变量.买卖价差_本位币>= 0）
        if(轧差金额本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //轧差金额（贷）_本位币 <- 变量.轧差金额_本位币
            accountAmountMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(轧差金额本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            // 轧差金额（借）_本位币	<-	变量.轧差金额_本位币 * -1
            accountAmountMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(轧差金额本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        //全局变量的更新
        //变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 金额元素.应收款项 - 利息
        qtyskxSubjectAmt  = ArithmeticUtil.doSub(qtyskxSubjectAmt, accountAmountMap.get(BookConstant.INTAMT));
        //将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
        param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);
        //基础科目性质
        Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
        Map<String, Object> accountNatrueBranchMap = new HashMap<>();
        accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
        // accountNatrueMap.put(基础科目性质.其他应收款_本金, accountNatrueBranchMap);
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
        //accountNatrueMap.put(基础科目性质.其他应收款_利息, accountNatrueBranchMap);
        accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
        ad.setAccountNatrueMap(accountNatrueMap);
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
    }

}

/*
 * 文件名：SecDealPreprocessingBookService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.secdealpreprocessingbook.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.google.common.collect.Lists;
import com.joyintech.tams.constant.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.common.accounting.entity.BookLatestInfoDto;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.batch.common.sect1handle.entity.SecT1HandleParamterDto;
import com.joyintech.tams.valuation.batch.secdealaccounts.dto.DealParamterDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * 债券交易账务预处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月4日
 * @see SecDealPreprocessingBookService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecDealPreprocessingBookService {

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
        // 交易明细 ：交易类账务处理结果.交易明细列表
        for (FinProductDealInfoComDTO dealNow : acctEntity.getListFinProductDealInfoComDTO()) {
            TargetFinProduct rstBaseinfo = getTargetFinProduct(dealNow);
            if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                quaryFvtplRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
        }
    }

    /**
     * 公允价值的情况场景处理(还本到期)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtplRed(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
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

        /**add by taoran 20200109 start**/
        // 汇率
        BigDecimal changeRate = BigDecimal.ONE;
        if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
            // 调用共通汇率查询
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    acctEntity.getStandardCcy(), dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                changeRate = currencyMarketList.get(0).getClosePrice();
            }
        }
        /**add by taoran 20200109 end**/
        
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_还本到期.getValue());

        // accountSceneMap设置
        // 备注
        if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())) {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + DictCodeList.交易方向_还本.getCodeName() + "交易登账");
        }
        else {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + DictCodeList.交易方向_到期.getCodeName() + "交易登账");
        }

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                ArithmeticUtil.round(param.getVarCostSubjectNum(), NumberEnum.QTY));
        }

        // 变量.结转日成本科目余额
        BigDecimal varCbye = BigDecimal.ZERO;
        // 变量.结转日成本科目数量
        BigDecimal varCbsl = BigDecimal.ZERO;
        // 科目余额查询参数
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(acctEntity.getBooksetId());
        searchDao.setBokDetailId(acctEntity.getBokDetailId());
        searchDao.setHappenDate(addDate(dealNow.getMdate(), -1));
        searchDao.setBookDate(acctEntity.getBookDate());
        searchDao.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue())));
        List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.结转日成本科目余额 = 科目余额
            varCbye = listSubjectBalance.get(0).getBalance();
            // 定义 变量.结转日成本科目数量 = 数量
            varCbsl = listSubjectBalance.get(0).getNum();
        }

        // 结转日公允价值变动科目余额
        BigDecimal varGyjzbdye = BigDecimal.ZERO;
        searchDao.setBaseSubjectNatureList(new ArrayList<String>(
            Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue())));
        List<SubjectBalance> listSubjectBalanceEx = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalanceEx != null && listSubjectBalanceEx.size() > 0) {
            // 变量.结转日公允价值变动科目余额 = 科目余额
            varGyjzbdye = listSubjectBalanceEx.get(0).getBalance();
        }
        /**add by taoran 20200110 start**/
        //if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(rstBaseinfo.getTradeMarket())) {
    	if (!DictCodeListExt1.估值交易市场_上交所.getValue().equals(rstBaseinfo.getTradeMarket()) && 
    			!DictCodeListExt1.估值交易市场_深交所.getValue().equals(rstBaseinfo.getTradeMarket())) {
		/**add by taoran 20200110 start**/
            // 买入场景
            // 调用账务共通设计->【凭证最新账务查询】
            List<BookLatestInfoDto> listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(),
                true, dealNow, acctEntity);
            // LOOP 变量.买入账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 -= 变量.账务明细.发生数量
                        varCbsl = ArithmeticUtil.subtract(varCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 -= 变量.账务明细.原币发生额
                        varCbye = ArithmeticUtil.subtract(varCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX公允价值变动的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.结转日公允价值变动科目余额 -= 变量.账务明细.原币发生额
                            varGyjzbdye = ArithmeticUtil.subtract(varGyjzbdye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.结转日公允价值变动科目余额 += 变量.账务明细.原币发生额
                            varGyjzbdye = ArithmeticUtil.doAdd(varGyjzbdye, var.getoHappenAmt());
                        }
                    }
                }
            }
            // 卖出场景
            // 调用账务共通设计->【凭证最新账务查询】
            listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(), false, dealNow, acctEntity);
            // LOOP 变量.卖出账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 += 变量.账务明细.发生数量
                        varCbsl = ArithmeticUtil.doAdd(varCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 += 变量.账务明细.原币发生额
                        varCbye = ArithmeticUtil.doAdd(varCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX公允价值变动的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.结转日公允价值变动科目余额 -= 变量.账务明细.原币发生额
                            varGyjzbdye = ArithmeticUtil.subtract(varGyjzbdye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.结转日公允价值变动科目余额 += 变量.账务明细.原币发生额
                            varGyjzbdye = ArithmeticUtil.doAdd(varGyjzbdye, var.getoHappenAmt());
                        }
                    }
                }
            }
        }
        // 变量.应计利息 = 0
        BigDecimal varYjlx = BigDecimal.ZERO;
        // if（变量.标的信息.息票品种为零息或贴现时）
        if (DictCodeList.息票品种_零息.getValue().equals(rstBaseinfo.getCouponSpecies())
            || DictCodeList.息票品种_贴现.getValue().equals(rstBaseinfo.getCouponSpecies())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue())));
            List<SubjectBalance> listSubjectBalanceYj = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
            if (listSubjectBalanceYj != null && listSubjectBalanceYj.size() > 0) {
                // 变量.结转日公允价值变动科目余额 = 科目余额
                varYjlx = listSubjectBalanceYj.get(0).getBalance();
            }
        }

        // accountAmountMap设置
        // 变量.冲减公允价值变动
        BigDecimal carCjgyjzbd = BigDecimal.ZERO;
        // 变量.冲减成本
        BigDecimal carCjcb = BigDecimal.ZERO;
        // 变量.冲减数量
        BigDecimal carCjsl = BigDecimal.ZERO;
        // 变量.买卖价差
        BigDecimal carMmjc = BigDecimal.ZERO;
        //变量本金 = 变量.成本科目数量 * 交易明细.单位净价
        BigDecimal varPrinAmt = ArithmeticUtil.multiply(varCbsl, dealNow.getUnitCprice());
        //变量.交易金额 = 变量.成本科目数量 * 交易明细.单位净价
        BigDecimal varDealAmt = ArithmeticUtil.multiply(varCbsl, dealNow.getUnitCprice());
        
        // 交易方向 = "到期"
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 变量.冲减公允价值变动 = 变量.公允价值科目余额
            carCjgyjzbd = param.getVarGySubjectAmt();
            // 变量.冲减成本 = 变量.成本科目余额
            carCjcb = param.getVarCostSubjectAmt();
            // 变量.冲减数量 = 变量.成本科目数量
            carCjsl = varCbsl;
        }
        else {
            // 变量.冲减公允价值变动 =变量.结转日公允价值变动科目余额 * 变量.交易成本/（变量.结转日成本科目数量*交易明细.单位面值）
            carCjgyjzbd = ArithmeticUtil.multiply(varGyjzbdye, varPrinAmt);
            carCjgyjzbd = ArithmeticUtil.divide(carCjgyjzbd, varCbsl,
                NumberEnum.HIGH_PREC_NUM.getCode());
            carCjgyjzbd = ArithmeticUtil.divide(carCjgyjzbd, dealNow.getParValue(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减成本 = 变量.结转日成本科目余额 * 变量.交易成本/（变量.结转日成本科目数量*交易明细.单位面值）
            carCjcb = ArithmeticUtil.multiply(varCbye, varPrinAmt);
            carCjcb = ArithmeticUtil.divide(carCjcb, varCbsl,
                NumberEnum.HIGH_PREC_NUM.getCode());
            carCjcb = ArithmeticUtil.divide(carCjcb, dealNow.getParValue(),
                NumberEnum.MONEY.getCode());
        }
        // 变量.买卖价差 = 交易明细.交易本金 - 变量.冲减成本
        carMmjc = ArithmeticUtil.subtract(varPrinAmt, carCjcb);
        carMmjc = ArithmeticUtil.subtract(carMmjc, varYjlx);
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("prodId", dealNow.getProdId());
            map.put("finprodId", dealNow.getFinprodId());
            map.put("mdate", acctEntity.getBookDate());
            map.put("ps", DictCodeList.交易方向_还本到账.getValue());
            map.put("secManageAcctId", dealNow.getSecManageAcctId());
            map.put("chlId", dealNow.getChlId());
            map.put("invAim", dealNow.getInvAim());
            Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
            if(StringUtil.isNotEmpty(mapDeal)
                    && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT_D,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
                // 账务明细代码1->交易类账务处理结果.账务明细代码
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                        bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
            }else{
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
            }
        }else{
            // 交易金额 <-交易明细.交易金额
            accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
        }
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end

        // 成本金额 <-变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(carCjcb, NumberEnum.MONEY));

        // 变量.买卖价差>= 0
        if (carMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C,
                ArithmeticUtil.round(carMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(carMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // 变量.冲减公允价值变动 >= 0
        if (carCjgyjzbd.compareTo(BigDecimal.ZERO) >= 0) {
            // 公允价值变动(贷) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_C,
                ArithmeticUtil.round(carCjgyjzbd, NumberEnum.MONEY));
            // 冲减公允价值变动(借) <-变量.冲减公允价值变动
            accountAmountMap.put(BookConstant.FLOAT_1_D,
                ArithmeticUtil.round(carCjgyjzbd, NumberEnum.MONEY));
        }
        else {
            // 公允价值变动(借) <-变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(carCjgyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
            // 冲减公允价值变动(贷) <- 变量.冲减公允价值变动 * -1
            accountAmountMap.put(BookConstant.FLOAT_1_C, ArithmeticUtil.round(
                ArithmeticUtil.multiply(carCjgyjzbd, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));
        
        /**add by taoran 20200109 start**/
        // 定义 本币金额元素Map
        Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
        for(String key : accountAmountMap.keySet()){
        	bccyAmountMap.put(key + BookConstant.BCCYAMT, ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
        }
        accountAmountMap.putAll(bccyAmountMap);
        /** modify by yanya on 20200116 买卖价差计本位币重新计算，交易金额不需要 start**/
//        //	交易金额_本位币	<-交易金额_本位币 - 成本金额_本位币 - 应收利息_本位币
//        accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
//        		accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)));
        //定义 变量.买卖价差_本位币
        BigDecimal varMmjcBccy = BigDecimal.ZERO;
        if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("prodId", dealNow.getProdId());
            map.put("finprodId", dealNow.getFinprodId());
            map.put("mdate", acctEntity.getBookDate());
            map.put("ps", DictCodeList.交易方向_还本到账.getValue());
            map.put("secManageAcctId", dealNow.getSecManageAcctId());
            map.put("chlId", dealNow.getChlId());
            map.put("invAim", dealNow.getInvAim());
            Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
            if(StringUtil.isNotEmpty(mapDeal)
                    && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 应收利息_本位币
                varMmjcBccy = ArithmeticUtil.doSub(
                        accountAmountMap.get(BookConstant.DEALAMT_D + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));
            }else{
                //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 应收利息_本位币
                varMmjcBccy = ArithmeticUtil.doSub(
                        accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));
            }
        }else {
            //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 应收利息_本位币
            varMmjcBccy = ArithmeticUtil.doSub(
                    accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                    accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),
                    accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));
        }
        //if（买卖价差_本位币>= 0）
        if(varMmjcBccy.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币  <- 变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, varMmjcBccy);
        }
        else {
            //买卖价差（借）_本位币   <- 变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT,
                    ArithmeticUtil.multiply(varMmjcBccy, new BigDecimal(-1)));
        }
        /** modify by yanya on 20200116 买卖价差计本位币重新计算，交易金额不需要 end**/
        //本位币	<- 计提类账务处理结果.本位币
        accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
        /**add by taoran 20200109 end**/
        
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
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
        carCjcb = ArithmeticUtil.round(carCjcb, NumberEnum.MONEY);
        carCjsl = ArithmeticUtil.round(carCjsl, NumberEnum.QTY);
        carCjgyjzbd = ArithmeticUtil.round(carCjgyjzbd, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), carCjcb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), carCjsl));
        // 变量.公允价值变动科目余额 = 变量.溢折价科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.subtract(param.getVarGySubjectAmt(), carCjgyjzbd));
    }

    /**
     * 公允价值的情况场景处理(收息)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvtplInt(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
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
        
        /**add by taoran 20200109 start**/
        // 汇率
        BigDecimal changeRate = BigDecimal.ONE;
        if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
        	// 调用共通汇率查询
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    acctEntity.getStandardCcy(), dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                changeRate = currencyMarketList.get(0).getClosePrice();
            }
        }
        /**add by taoran 20200109 end**/
        
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());

        if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_公允价值损益类债券_收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + DictCodeList.交易方向_收息.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + DictCodeList.交易方向_收息.getCodeName() + "交易登账");
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            acctEntity.getInvAim())) {
            ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_收息.getValue());
            // 备注
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "综合收益类债券资产" + DictCodeList.交易方向_收息.getCodeName() + "交易登账");
        }
        // accountSceneMap设置
        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 科目余额查询参数
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(acctEntity.getBooksetId());
        searchDao.setBokDetailId(acctEntity.getBokDetailId());
        searchDao.setHappenDate(addDate(dealNow.getMdate(), -1));
        searchDao.setBookDate(acctEntity.getBookDate());

        // 查询交易的到期日对应的应计利息
        BigDecimal varInterestAccBal = BigDecimal.ZERO;
        List<SubjectBalance> listSubjectBalance = new ArrayList<SubjectBalance>();
        if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(dealNow.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(dealNow.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }
        else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
            dealNow.getInvAim())) {
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
        }

        if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.应计利息余额 = 科目余额
            varInterestAccBal = listSubjectBalance.get(0).getBalance();
        }
    	/**add by taoran 20200110 start**/
        //if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(rstBaseinfo.getTradeMarket())) {
    	if (!DictCodeListExt1.估值交易市场_上交所.getValue().equals(rstBaseinfo.getTradeMarket()) && 
    			!DictCodeListExt1.估值交易市场_深交所.getValue().equals(rstBaseinfo.getTradeMarket())) {
		/**add by taoran 20200110 start**/
            // 买入场景
            // 调用账务共通设计->【凭证最新账务查询】
            List<BookLatestInfoDto> listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(),
                true, dealNow, acctEntity);
            // LOOP 变量.买入账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX应计利息的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算应计利息余额 -= 变量.账务明细.原币发生额
                            varInterestAccBal = ArithmeticUtil.subtract(varInterestAccBal,
                                var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算应计利息余额 += 变量.账务明细.原币发生额
                            varInterestAccBal = ArithmeticUtil.doAdd(varInterestAccBal,
                                var.getoHappenAmt());
                        }
                    }
                }
            }
            // 卖出场景
            // 调用账务共通设计->【凭证最新账务查询】
            listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(), false, dealNow, acctEntity);
            // LOOP 变量.卖出账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX应计利息的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算应计利息余额 -= 变量.账务明细.原币发生额
                            varInterestAccBal = ArithmeticUtil.subtract(varInterestAccBal,
                                var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算应计利息余额 += 变量.账务明细.原币发生额
                            varInterestAccBal = ArithmeticUtil.doAdd(varInterestAccBal,
                                var.getoHappenAmt());
                        }
                    }
                }
            }
        }
        // 查询交易的到期日是否为付息截止日
        boolean checkFlg = finProductCommonService.payDeadlineJudgment(dealNow.getFinprodId(),
            dealNow.getMdate()).isfDeadline();
        if (!checkFlg) {
            BigDecimal faceValue = finProductCommonService.getFaceValue(dealNow.getFinprodId(),
                dealNow.getMdate());
            // 变量.应计利息 =变量.应计利息 * (交易明细.单位面值—上述共通返回 百元券面面值) / 交易明细.单位面值
            varInterestAccBal = ArithmeticUtil.multiply(varInterestAccBal,
                ArithmeticUtil.subtract(dealNow.getParValue(), faceValue));
            varInterestAccBal = ArithmeticUtil.divide(varInterestAccBal, dealNow.getParValue(),
                NumberEnum.HIGH_PREC_NUM.getCode());
        }
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                        || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("prodId", dealNow.getProdId());
            map.put("finprodId", dealNow.getFinprodId());
            map.put("mdate", acctEntity.getBookDate());
            map.put("ps", DictCodeList.交易方向_收息到账.getValue());
            map.put("secManageAcctId", dealNow.getSecManageAcctId());
            map.put("chlId", dealNow.getChlId());
            map.put("invAim", dealNow.getInvAim());
            Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
            if(StringUtil.isNotEmpty(mapDeal)
                    && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                accountAmountMap.put(BookConstant.DEALAMT_D,
                        ArithmeticUtil.round((BigDecimal) mapDeal.get("TRADEAMT"), NumberEnum.MONEY));
                accountAmountMap.put(BookConstant.INTAMT_C,
                        ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY));
                BigDecimal varTradeAmt = ArithmeticUtil.subtract((BigDecimal) mapDeal.get("TRADEAMT"),
                        ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY));
                if (varTradeAmt.compareTo(BigDecimal.ZERO) >= 0) {
                    // 利息差额(贷) <-交易明细.全价总额 - 变量.应计利息
                    accountAmountMap.put(BookConstant.NETAMT_C,
                            ArithmeticUtil.round(varTradeAmt, NumberEnum.MONEY));
                }else{
                    // 利息差额(借) <- 交易明细.全价总额 - 变量.应计利息 * -1
                    accountAmountMap.put(BookConstant.NETAMT_D, ArithmeticUtil.round(
                            ArithmeticUtil.multiply(varTradeAmt, new BigDecimal(-1)), NumberEnum.MONEY));
                }
                // 账务明细代码1->交易类账务处理结果.账务明细代码
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                        bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
            }else{
                accountAmountMap.put(BookConstant.INTAMT,
                        ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY));
            }
        }else{
            // accountAmountMap设置
            // 应计利息
            accountAmountMap.put(BookConstant.INTAMT,
                    ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY));
        }
        /**add by taoran 20200109 start**/
        // 	应计利息_本位币	<- 应计利息 * 变量.汇率
        accountAmountMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, ArithmeticUtil.multiply(
        		NumberEnum.MONEY.getCode(), null, varInterestAccBal, changeRate));
        /**add by taoran 20200109 end**/
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        /**add by taoran 20200109 start**/
        accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
        /**add by taoran 20200109 end**/
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
        varInterestAccBal = ArithmeticUtil.round(varInterestAccBal, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.应计利息
        param.setVarLxSubjectAmt(
            ArithmeticUtil.subtract(param.getVarLxSubjectAmt(), varInterestAccBal));
        // 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
        if (param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch())) == null) {
            searchDao.setHappenDate(dealNow.getHappenDate());
            searchDao.setBranch(dealNow.getBranch());
            searchDao.setTaskPri(dealNow.getTaskPri());
            searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                Arrays.asList(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue())));
            listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDao, DictCodeList.余额方向_借方.getValue());
            BigDecimal addInt = varInterestAccBal;
            if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                addInt = ArithmeticUtil.doAdd(addInt, listSubjectBalance.get(0).getBalance());
            }
            param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()), addInt);
        }
        else {
            param.getVarQtyskxSubjectAmt().put(String.valueOf(dealNow.getBranch()),
                ArithmeticUtil.doAdd(
                    param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch())),
                    varInterestAccBal));
        }

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
            || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.QTY));
        }
        else {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER, BigDecimal.ZERO);
        }
        if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
            || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
            if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
                accountSceneMap.put(BookConstant.ONWAY_DATE, dealNow.getTradeDate().compareTo(
                    dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate());
            }
            else {
                accountSceneMap.put(BookConstant.ONWAY_DATE,
                    DateUtils.addDays(dealNow.getTradeDate(), 1));
            }
        }
        // 是否累计余额->是
        accountSceneMap.put(BookConstant.IS_ACC_BALANCE, DictCodeList.是否_是.getValue());
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
            if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                quaryAcRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
        }
    }

    /**
     * 摊余成本的情况场景处理(还本到期)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryAcRed(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                            FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity,
                            DealParamterDto param) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();
        
        /**add by taoran 20200109 start**/
        // 汇率
        BigDecimal changeRate = BigDecimal.ONE;
        if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
        	// 调用共通汇率查询
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    acctEntity.getStandardCcy(), dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                changeRate = currencyMarketList.get(0).getClosePrice();
            }
        }
        /**add by taoran 20200109 end**/
        
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_摊余成本类债券_还本到期.getValue());

        // accountSceneMap设置
        // 备注
        if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())) {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + DictCodeList.交易方向_还本.getCodeName() + "交易登账");
        }
        else {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + DictCodeList.交易方向_到期.getCodeName() + "交易登账");
        }

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                ArithmeticUtil.round(param.getVarCostSubjectNum(), NumberEnum.QTY));
        }

        // 变量.结转日成本科目余额
        BigDecimal varZjrCbye = BigDecimal.ZERO;
        // 变量.结转日成本科目数量
        BigDecimal varZjrCbsl = BigDecimal.ZERO;

        // 科目余额查询参数
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(dealNow.getBooksetId());
        searchDao.setBokDetailId(dealNow.getBokDetailId());
        searchDao.setHappenDate(addDate(dealNow.getMdate(), -1));
        searchDao.setBookDate(dealNow.getBookDate());
        searchDao.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类成本.getValue())));
        List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.结转日成本科目余额 = 科目余额
            varZjrCbye = listSubjectBalance.get(0).getBalance();
            // 变量.结转日成本科目数量 = 数量
            varZjrCbsl = listSubjectBalance.get(0).getNum();
        }

        // 结转日溢折价科目余额
        BigDecimal varZjrYzjye = BigDecimal.ZERO;
        searchDao.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue())));
        List<SubjectBalance> listSubjectBalanceEx = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalanceEx != null && listSubjectBalanceEx.size() > 0) {
            // 变量.结转日溢折价科目余额 = 科目余额
            varZjrYzjye = listSubjectBalanceEx.get(0).getBalance();
        }
        /**add by taoran 20200110 start**/
        //if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(rstBaseinfo.getTradeMarket())) {
    	if (!DictCodeListExt1.估值交易市场_上交所.getValue().equals(rstBaseinfo.getTradeMarket()) && 
    			!DictCodeListExt1.估值交易市场_深交所.getValue().equals(rstBaseinfo.getTradeMarket())) {
		/**add by taoran 20200110 start**/
            // 买入场景
            // 调用账务共通设计->【凭证最新账务查询】
            List<BookLatestInfoDto> listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(),
                true, dealNow, acctEntity);
            // LOOP 变量.买入账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 -= 变量.账务明细.发生数量
                        varZjrCbsl = ArithmeticUtil.subtract(varZjrCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 -= 变量.账务明细.原币发生额
                        varZjrCbye = ArithmeticUtil.subtract(varZjrCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX利息调整的账务明细 )
                    if (DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算摊销溢折价科目余额 -= 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.subtract(varZjrYzjye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算摊销溢折价科目余额 += 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.doAdd(varZjrYzjye, var.getoHappenAmt());
                        }
                    }
                }
            }
            // 卖出场景
            // 调用账务共通设计->【凭证最新账务查询】
            listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(), false, dealNow, acctEntity);
            // LOOP 变量.卖出账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 += 变量.账务明细.发生数量
                        varZjrCbsl = ArithmeticUtil.doAdd(varZjrCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 += 变量.账务明细.原币发生额
                        varZjrCbye = ArithmeticUtil.doAdd(varZjrCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX利息调整的账务明细 )
                    if (DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算摊销溢折价科目余额 -= 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.subtract(varZjrYzjye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算摊销溢折价科目余额 += 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.doAdd(varZjrYzjye, var.getoHappenAmt());
                        }
                    }
                }
            }
        }
        //设计变更 增加参数：还本是否冲减摊销，济宁银行个性化
        // 定义 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 交易明细.交易成本/（变量.成本科目数量*交易明细.单位面值）
        BigDecimal varCjyzj = BigDecimal.ZERO;
        Map<String, Object>  booksetExt = accountingCommonService.getValBokBooksetExtForMap(acctEntity.getBooksetId(),
                Lists.newArrayList(new String[] {ValDictCodeList.账套配置表参数项_还本是否冲减摊销.getValue()}));
        if(StringUtil.equals(booksetExt.get(ValDictCodeList.账套配置表参数项_还本是否冲减摊销.getValue()), DictCodeList.是否_是.getValue())){
            varCjyzj = ArithmeticUtil.multiply(varZjrYzjye, dealNow.getPrinAmt());
            varCjyzj = ArithmeticUtil.divide(varCjyzj, param.getVarCostSubjectNum(),
                    NumberEnum.HIGH_PREC_NUM.getCode());
            varCjyzj = ArithmeticUtil.divide(varCjyzj, dealNow.getParValue(),
                    NumberEnum.MONEY.getCode());
        }
        // 定义 变量.冲减成本 = 0
        BigDecimal varCjcb = BigDecimal.ZERO;
        // 定义 变量.冲减数量 = 0
        BigDecimal varCjsl = BigDecimal.ZERO;
        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;
        //变量本金 = 变量.成本科目数量 * 交易明细.单位净价
        BigDecimal varPrinAmt = ArithmeticUtil.multiply(varZjrCbsl, dealNow.getUnitCprice());
        //变量.交易金额 = 变量.成本科目数量 * 交易明细.单位净价
        BigDecimal varDealAmt = ArithmeticUtil.multiply(varZjrCbsl, dealNow.getUnitCprice());
        
        // 交易方向 = "到期"
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 变量.冲减溢折价 = 变量.溢折价科目余额
            varCjyzj = param.getVarYzjSubjectAmt();
            // 变量.冲减成本 = 变量.成本科目余额
            varCjcb = param.getVarCostSubjectAmt();
            // 变量.冲减数量 = 变量.成本科目数量
            varCjsl = varZjrCbsl;

        }
        else {
            if(StringUtil.equals(booksetExt.get(ValDictCodeList.账套配置表参数项_还本是否冲减摊销.getValue()), DictCodeList.是否_是.getValue())){
                // 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 交易明细.交易成本/（变量.成本科目数量*交易明细.单位面值）
                varCjyzj = ArithmeticUtil.multiply(varZjrYzjye, varPrinAmt);
                varCjyzj = ArithmeticUtil.divide(varCjyzj, varZjrCbsl,
                        NumberEnum.HIGH_PREC_NUM.getCode());
                varCjyzj = ArithmeticUtil.divide(varCjyzj, dealNow.getParValue(),
                        NumberEnum.MONEY.getCode());
            }
            // 变量.冲减成本 = 变量.结转日成本科目余额 * 交易明细.交易成本/（变量.成本科目数量*交易明细.单位面值）
            varCjcb = ArithmeticUtil.multiply(varZjrCbye, varPrinAmt);
            varCjcb = ArithmeticUtil.divide(varCjcb, varZjrCbsl,
                NumberEnum.HIGH_PREC_NUM.getCode());
            varCjcb = ArithmeticUtil.divide(varCjcb, dealNow.getParValue(),
                NumberEnum.MONEY.getCode());

        }
        // 变量.买卖价差 = 交易明细.交易本金 - （变量.冲减成本+变量.冲减溢折价）
        varMmjc = ArithmeticUtil.subtract(varPrinAmt,
            ArithmeticUtil.doAdd(varCjcb, varCjyzj));
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("prodId", dealNow.getProdId());
            map.put("finprodId", dealNow.getFinprodId());
            map.put("mdate", acctEntity.getBookDate());
            map.put("ps", DictCodeList.交易方向_还本到账.getValue());
            map.put("secManageAcctId", dealNow.getSecManageAcctId());
            map.put("chlId", dealNow.getChlId());
            map.put("invAim", dealNow.getInvAim());
            Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
            if(StringUtil.isNotEmpty(mapDeal)
                    && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT_D,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
                // 账务明细代码1->交易类账务处理结果.账务明细代码
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                        bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
            }else{
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
            }
        }else{
            // 交易金额 <- 交易明细.交易金额
            accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
        }
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        // 面值 <-变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varCjcb, NumberEnum.MONEY));

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

        // 变量.冲减溢折价 >= 0
        if (varCjyzj.compareTo(BigDecimal.ZERO) >= 0) {
            // 剩余溢折价(贷) <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjyzj, NumberEnum.MONEY));
        }
        else {
            // 剩余溢折价(借) <- 变量.冲减溢折价 * -1
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjyzj, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        
        /**add by taoran 20200109 start**/
        // 定义 本币金额元素Map
        Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
        for(String key : accountAmountMap.keySet()){
        	bccyAmountMap.put(key + BookConstant.BCCYAMT, ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
        }
        accountAmountMap.putAll(bccyAmountMap);
        //定义 变量.买卖价差_本位币	
        BigDecimal varMmjcBccy = BigDecimal.ZERO;
        // 变量.冲减溢折价 >= 0
        if (varCjyzj.compareTo(BigDecimal.ZERO) >= 0) {
            if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                    || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("prodId", dealNow.getProdId());
                map.put("finprodId", dealNow.getFinprodId());
                map.put("mdate", acctEntity.getBookDate());
                map.put("ps", DictCodeList.交易方向_还本到账.getValue());
                map.put("secManageAcctId", dealNow.getSecManageAcctId());
                map.put("chlId", dealNow.getChlId());
                map.put("invAim", dealNow.getInvAim());
                Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
                if(StringUtil.isNotEmpty(mapDeal)
                        && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                    //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                    varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT_D + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
                }else{
                    //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                    varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
                }
            }else{
                //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
            }
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        }
        else {
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
            if(dealNow.getTradeDate().compareTo(dealNow.getMdate()) < 0 &&
                    (DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                            || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
                //变量.买卖价差_本位币  =	交易金额_本位币 - 面值_本位币 + 剩余溢折价(借)_本位币
                varMmjcBccy = ArithmeticUtil.doAdd(ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT_C + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT)), accountAmountMap.get(BookConstant.DSCINC_D + BookConstant.BCCYAMT));
            }else {
                //变量.买卖价差_本位币  =	交易金额_本位币 - 面值_本位币 + 剩余溢折价(借)_本位币
                varMmjcBccy = ArithmeticUtil.doAdd(ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT)), accountAmountMap.get(BookConstant.DSCINC_D + BookConstant.BCCYAMT));
            }
            //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 end
        }
        if(varMmjcBccy.compareTo(BigDecimal.ZERO) > 0) {
        	//买卖价差（贷）_本位币  <- 变量.买卖价差_本位币							
        	accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, varMmjcBccy);
        }
        else {
        	//买卖价差（借）_本位币   <- 变量.买卖价差_本位币*-1
        	accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, varMmjcBccy.negate());
        }
        //本位币	<- 计提类账务处理结果.本位币
        accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
        /**add by taoran 20200109 end**/
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
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
        varCjcb = ArithmeticUtil.round(varCjcb, NumberEnum.MONEY);
        varCjsl = ArithmeticUtil.round(varCjsl, NumberEnum.QTY);
        varCjyzj = ArithmeticUtil.round(varCjyzj, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), varCjcb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), varCjsl));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.subtract(param.getVarYzjSubjectAmt(), varCjyzj));
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
            if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                quaryFvtplInt(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                quaryFvociRed(rstBaseinfo, returnResult, dealNow, acctEntity, param);
            }
        }
    }

    /**
     * 综合收益的情况场景处理(还本到期)
     * 
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易明细
     * @param acctEntity acctEntity
     * @param param param
     * @see
     * @since 1.0
     */
    private void quaryFvociRed(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
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
        
        /**add by taoran 20200109 start**/
        // 汇率
        BigDecimal changeRate = BigDecimal.ONE;
        if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
        	// 调用共通汇率查询
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    acctEntity.getStandardCcy(), dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                changeRate = currencyMarketList.get(0).getClosePrice();
            }
        }
        /**add by taoran 20200109 end**/
        
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(DictCodeListTableId.会计场景代码_综合收益类债券_还本到期.getValue());

        // accountSceneMap设置
        // 备注
        if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())) {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "综合收益类债券资产" + DictCodeList.交易方向_还本.getCodeName() + "交易登账");
        }
        else {
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "综合收益类债券资产" + DictCodeList.交易方向_到期.getCodeName() + "交易登账");
        }

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                ArithmeticUtil.round(param.getVarCostSubjectNum(), NumberEnum.QTY));
        }

        // 科目余额查询参数
        SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
        searchDao.setBooksetId(dealNow.getBooksetId());
        searchDao.setBokDetailId(dealNow.getBokDetailId());
        searchDao.setHappenDate(addDate(dealNow.getMdate(), -1));
        searchDao.setBookDate(dealNow.getBookDate());
        searchDao.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类成本.getValue())));
        // 变量.结转日成本科目余额
        BigDecimal varZjrCbye = BigDecimal.ZERO;
        // 变量.结转日成本科目数量
        BigDecimal varZjrCbsl = BigDecimal.ZERO;
        List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
            // 变量.结转日成本科目余额 = 科目余额
            varZjrCbye = listSubjectBalance.get(0).getBalance();
            varZjrCbsl = listSubjectBalance.get(0).getNum();
        }

        // 结转日溢折价科目余额
        BigDecimal varZjrYzjye = BigDecimal.ZERO;
        searchDao.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue())));
        List<SubjectBalance> listSubjectBalanceEx = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalanceEx != null && listSubjectBalanceEx.size() > 0) {
            // 变量.结转日溢折价科目余额 = 科目余额
            varZjrYzjye = listSubjectBalanceEx.get(0).getBalance();
        }

        // 结转日公允价值变动科目余额
        BigDecimal varZjrGyjzbdye = BigDecimal.ZERO;
        searchDao.setBaseSubjectNatureList(new ArrayList<String>(
            Arrays.asList(DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue())));
        List<SubjectBalance> listSubjectBalanceGy = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDao, DictCodeList.余额方向_借方.getValue());
        if (listSubjectBalanceGy != null && listSubjectBalanceGy.size() > 0) {
            // 变量.结转日溢折价科目余额 = 科目余额
            varZjrGyjzbdye = listSubjectBalanceGy.get(0).getBalance();
        }
        /**add by taoran 20200110 start**/
        //if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(rstBaseinfo.getTradeMarket())) {
    	if (!DictCodeListExt1.估值交易市场_上交所.getValue().equals(rstBaseinfo.getTradeMarket()) && 
    			!DictCodeListExt1.估值交易市场_深交所.getValue().equals(rstBaseinfo.getTradeMarket())) {
		/**add by taoran 20200110 start**/
            // 买入场景
            // 调用账务共通设计->【凭证最新账务查询】
            List<BookLatestInfoDto> listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(),
                true, dealNow, acctEntity);
            // LOOP 变量.买入账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 -= 变量.账务明细.发生数量
                        varZjrCbsl = ArithmeticUtil.subtract(varZjrCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 -= 变量.账务明细.原币发生额
                        varZjrCbye = ArithmeticUtil.subtract(varZjrCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX公允价值变动的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算公允价值变动科目余额 -= 变量.账务明细.原币发生额
                            varZjrGyjzbdye = ArithmeticUtil.subtract(varZjrGyjzbdye,
                                var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算公允价值变动科目余额 += 变量.账务明细.原币发生额
                            varZjrGyjzbdye = ArithmeticUtil.doAdd(varZjrGyjzbdye, var.getoHappenAmt());
                        }
                    }
                    // if(变量.账务明细.基础科目性质 = XX利息调整的账务明细 )
                    if (DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算摊销溢折价科目余额 -= 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.subtract(varZjrYzjye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算摊销溢折价科目余额 += 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.doAdd(varZjrYzjye, var.getoHappenAmt());
                        }
                    }
                }
            }
            // 卖出场景
            // 调用账务共通设计->【凭证最新账务查询】
            listBookLatestInfoDto = getBookLatestInfo(dealNow.getInvAim(), false, dealNow, acctEntity);
            // LOOP 变量.卖出账务明细列表 -> 变量.账务明细
            if (listBookLatestInfoDto != null && listBookLatestInfoDto.size() > 0) {
                for (BookLatestInfoDto var : listBookLatestInfoDto) {
                    // if(变量.账务明细.基础科目性质 = XX成本的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.摊余成本类成本.getValue().equals(
                            var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类成本.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.结转日成本科目数量 += 变量.账务明细.发生数量
                        varZjrCbsl = ArithmeticUtil.doAdd(varZjrCbsl, var.getHappenNumber());
                        // 变量.结转日成本科目余额 += 变量.账务明细.原币发生额
                        varZjrCbye = ArithmeticUtil.doAdd(varZjrCbye, var.getoHappenAmt());
                    }
                    // if(变量.账务明细.基础科目性质 = XX公允价值变动的账务明细 )
                    if (DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算公允价值变动科目余额 -= 变量.账务明细.原币发生额
                            varZjrGyjzbdye = ArithmeticUtil.subtract(varZjrGyjzbdye,
                                var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算公允价值变动科目余额 += 变量.账务明细.原币发生额
                            varZjrGyjzbdye = ArithmeticUtil.doAdd(varZjrGyjzbdye, var.getoHappenAmt());
                        }
                    }
                    // if(变量.账务明细.基础科目性质 = XX利息调整的账务明细 )
                    if (DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue().equals(
                        var.getBaseSubjectNature())
                        || DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue().equals(
                            var.getBaseSubjectNature())) {
                        // 变量.账务明细.借贷方向为借时
                        if (DictCodeList.借贷方向_借.getValue().equals(var.getCdFlag())) {
                            // 变量.当日计算摊销溢折价科目余额 -= 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.subtract(varZjrYzjye, var.getoHappenAmt());
                        }
                        else {
                            // 变量.当日计算摊销溢折价科目余额 += 变量.账务明细.原币发生额
                            varZjrYzjye = ArithmeticUtil.doAdd(varZjrYzjye, var.getoHappenAmt());
                        }
                    }
                }
            }
        }
        // 定义 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 交易明细.交易成本/（变量.成本科目数量*交易明细.单位面值）
        BigDecimal varCjyzj = BigDecimal.ZERO;
        // 定义 变量.冲减成本 = 0
        BigDecimal varCjcb = BigDecimal.ZERO;
        // 定义 变量.冲减数量 = 0
        BigDecimal varCjsl = BigDecimal.ZERO;
        // 定义 变量.冲减公允价值变动
        BigDecimal varCjgyjzbd = BigDecimal.ZERO;
        // 定义 变量.买卖价差
        BigDecimal varMmjc = BigDecimal.ZERO;
        // 变量.交易成本 = 变量.结转日成本科目数量 * 交易明细.单位净价
        BigDecimal varPrinAmt = ArithmeticUtil.multiply(varZjrCbsl, dealNow.getUnitCprice());
        //变量.交易金额 = 变量.成本科目数量 * 交易明细.单位净价
        BigDecimal varDealAmt = ArithmeticUtil.multiply(varZjrCbsl, dealNow.getUnitCprice());
        
        // 交易方向 = "到期"
        if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
            // 变量.冲减公允价值变动 = 变量.公允价值科目余额
            varCjgyjzbd = param.getVarGySubjectAmt();
            // 变量.冲减溢折价 = 变量.溢折价科目余额
            varCjyzj = param.getVarYzjSubjectAmt();
            // 变量.冲减成本 = 变量.成本科目余额
            varCjcb = param.getVarCostSubjectAmt();
            // 变量.冲减数量 = 变量.成本科目数量
            varCjsl = varZjrCbsl;

        }
        else {
            // 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 变量.交易成本/（变量.结转日成本科目数量*交易明细.单位面值）
            Map<String, Object>  booksetExt = accountingCommonService.getValBokBooksetExtForMap(acctEntity.getBooksetId(),
                    Lists.newArrayList(new String[] {ValDictCodeList.账套配置表参数项_还本是否冲减摊销.getValue()}));
          if(StringUtil.equals(booksetExt.get(ValDictCodeList.账套配置表参数项_还本是否冲减摊销.getValue()), DictCodeList.是否_是.getValue())){
              varCjyzj = ArithmeticUtil.multiply(varZjrYzjye, varPrinAmt);
              varCjyzj = ArithmeticUtil.divide(varCjyzj, varZjrCbsl,
                      NumberEnum.HIGH_PREC_NUM.getCode());
              varCjyzj = ArithmeticUtil.divide(varCjyzj, dealNow.getParValue(),
                      NumberEnum.MONEY.getCode());
           }
            // 变量.冲减成本 = 变量.结转日成本科目余额 * 变量.交易成本/（变量.结转日成本科目数量*交易明细.单位面值）
            varCjcb = ArithmeticUtil.multiply(varZjrCbye, varPrinAmt);
            varCjcb = ArithmeticUtil.divide(varCjcb, varZjrCbsl,
                NumberEnum.HIGH_PREC_NUM.getCode());
            varCjcb = ArithmeticUtil.divide(varCjcb, dealNow.getParValue(),
                NumberEnum.MONEY.getCode());
            // 变量.冲减公允价值变动 = 变量.结转日公允价值变动科目余额 * 变量.交易成本/（变量.结转日成本科目数量*交易明细.单位面值）
            varCjgyjzbd = ArithmeticUtil.multiply(varZjrGyjzbdye, varPrinAmt);
            varCjgyjzbd = ArithmeticUtil.divide(varCjgyjzbd, varZjrCbsl,
                NumberEnum.HIGH_PREC_NUM.getCode());
            varCjgyjzbd = ArithmeticUtil.divide(varCjgyjzbd, dealNow.getParValue(),
                NumberEnum.MONEY.getCode());
        }
        // 变量.买卖价差    = 交易明细.交易本金 - （变量.冲减成本+变量.冲减溢折价）                           
        varMmjc = ArithmeticUtil.doSub(varPrinAmt, varCjcb, varCjyzj);
        //修复交易所债券还本到期交易支持提前收款 by jqq 20201217 start
        if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("prodId", dealNow.getProdId());
            map.put("finprodId", dealNow.getFinprodId());
            map.put("mdate", acctEntity.getBookDate());
            map.put("ps", DictCodeList.交易方向_还本到账.getValue());
            map.put("secManageAcctId", dealNow.getSecManageAcctId());
            map.put("chlId", dealNow.getChlId());
            map.put("invAim", dealNow.getInvAim());
            Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
            if(StringUtil.isNotEmpty(mapDeal)
                    && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT_D,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
                // 账务明细代码1->交易类账务处理结果.账务明细代码
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2,
                        bookCommonLogic.getValBokDetailId(dealNow.getFinprodId(), dealNow.getBokDetailId(), dealNow.getFinprodType(),dealNow.getCcy()));
            }else{
                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                        ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
            }
        }else{
            // 交易金额 <- 交易明细.交易金额
            accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
        }
        // 面值 <-变量.冲减成本
        accountAmountMap.put(BookConstant.FACEVALUE,
            ArithmeticUtil.round(varCjcb, NumberEnum.MONEY));

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

        // 变量.冲减溢折价 >= 0
        if (varCjyzj.compareTo(BigDecimal.ZERO) >= 0) {
            // 剩余溢折价(贷) <- 变量.冲减溢折价
            accountAmountMap.put(BookConstant.DSCINC_C,
                ArithmeticUtil.round(varCjyzj, NumberEnum.MONEY));
        }
        else {
            // 剩余溢折价(借) <- 变量.冲减溢折价 * -1
            accountAmountMap.put(BookConstant.DSCINC_D, ArithmeticUtil.round(
                ArithmeticUtil.multiply(varCjyzj, new BigDecimal(-1)), NumberEnum.MONEY));
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
        
        /**add by taoran 20200109 start**/
        // 定义 本币金额元素Map
        Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
        for(String key : accountAmountMap.keySet()){
        	bccyAmountMap.put(key + BookConstant.BCCYAMT, ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
        }
        accountAmountMap.putAll(bccyAmountMap);
        //定义 变量.买卖价差_本位币	
        BigDecimal varMmjcBccy = BigDecimal.ZERO;
        // 变量.冲减溢折价 >= 0
        if (varCjyzj.compareTo(BigDecimal.ZERO) >= 0) {
            if((DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                    || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket()))){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("prodId", dealNow.getProdId());
                map.put("finprodId", dealNow.getFinprodId());
                map.put("mdate", acctEntity.getBookDate());
                map.put("ps", DictCodeList.交易方向_还本到账.getValue());
                map.put("secManageAcctId", dealNow.getSecManageAcctId());
                map.put("chlId", dealNow.getChlId());
                map.put("invAim", dealNow.getInvAim());
                Map<String, Object> mapDeal=finProductCommonService.selectHBFXData(map);
                if(StringUtil.isNotEmpty(mapDeal)
                        && ((Date)mapDeal.get("TRADEDATE")).compareTo(((Date)mapDeal.get("MDATE")))<0){
                    //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                    varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT_D + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
                }else{
                    //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                    varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
                }
            }else{
                //	变量.买卖价差_本位币	= 交易金额_本位币 - 面值_本位币 - 剩余溢折价(贷)_本位币
                varMmjcBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT),
                        accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT), accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT));
            }
        }
        else {
        	//变量.买卖价差_本位币  =	交易金额_本位币 - 面值_本位币 + 剩余溢折价(借)_本位币
        	varMmjcBccy = ArithmeticUtil.doAdd(ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT), 
        			accountAmountMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT)), accountAmountMap.get(BookConstant.DSCINC_D + BookConstant.BCCYAMT));
        }
        if(varMmjcBccy.compareTo(BigDecimal.ZERO) > 0) {
        	//买卖价差（贷）_本位币  <- 变量.买卖价差_本位币							
        	accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, varMmjcBccy);
        }
        else {
        	//买卖价差（借）_本位币   <- 变量.买卖价差_本位币*-1
        	accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, varMmjcBccy.negate());
        }
        //本位币	<- 计提类账务处理结果.本位币
        accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
        /**add by taoran 20200109 end**/
        
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
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
        varCjcb = ArithmeticUtil.round(varCjcb, NumberEnum.MONEY);
        varCjsl = ArithmeticUtil.round(varCjsl, NumberEnum.QTY);
        varCjyzj = ArithmeticUtil.round(varCjyzj, NumberEnum.MONEY);
        varCjgyjzbd = ArithmeticUtil.round(varCjgyjzbd, NumberEnum.MONEY);
        // 全局变量的更新
        // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
        param.setVarCostSubjectAmt(ArithmeticUtil.subtract(param.getVarCostSubjectAmt(), varCjcb));
        // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
        param.setVarCostSubjectNum(ArithmeticUtil.subtract(param.getVarCostSubjectNum(), varCjsl));
        // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
        param.setVarYzjSubjectAmt(ArithmeticUtil.subtract(param.getVarYzjSubjectAmt(), varCjyzj));
        // 变量.公允价值变动科目余额 = 变量.公允价值变动科目余额 - 变量.冲减公允价值变动
        param.setVarGySubjectAmt(ArithmeticUtil.subtract(param.getVarGySubjectAmt(), varCjgyjzbd));
    }

    /**
     * 日期加减
     * 
     * @param nowDate 传递时间
     * @param days 加减天数
     * @return Date
     * @see
     * @since 1.0
     */
    private Date addDate(Date nowDate, int days) {
        // 获取日历实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date day = calendar.getTime();

        return day;
    }

    /**
     * 凭证最新账务查询 <功能详细描述>
     * 
     * @param invAim 投资目的
     * @param isBuyDeal 是否是买入交易
     * @param dealNow 交易明细
     * @param acctEntity read结果
     * @return
     * @see
     * @since 1.0
     */
    private List<BookLatestInfoDto> getBookLatestInfo(String invAim, boolean isBuyDeal,
                                                      FinProductDealInfoComDTO dealNow,
                                                      FinProductDealInfoResult acctEntity) {
        String tableId = null;
        List<String> baseSubjectNatureList = new ArrayList<String>();
        // 买入场景
        if (isBuyDeal) {
            if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(
                acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_公允价值损益类债券_买入.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue());
            }
            else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_摊余成本类债券_买入.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue());
            }
            else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
                acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_综合收益类债券_买入.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue());
            }
        }
        else {
            if (DictCodeListExt1.投资目的_公允价值变动计入当期损益.getValue().equals(
                acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_公允价值损益类债券_卖出.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue());
            }
            else if (DictCodeListExt1.投资目的_摊余成本类.getValue().equals(acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_摊余成本类债券_卖出.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue());
            }
            else if (DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue().equals(
                acctEntity.getInvAim())) {
                tableId = DictCodeListTableId.会计场景代码_综合收益类债券_卖出.getValue();
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类成本.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue());
                baseSubjectNatureList.add(DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue());
            }
        }
        // 调用账务共通设计->【凭证最新账务查询】
        List<BookLatestInfoDto> listBookLatestInfoDto = accountingCommonService.getBookLatestInfo(
            acctEntity.getBooksetId(), acctEntity.getBookDate(), acctEntity.getBokDetailId(),
            baseSubjectNatureList, tableId, null, null, addDate(dealNow.getMdate(), -1), null,
            null);
        return listBookLatestInfoDto;
    }
}

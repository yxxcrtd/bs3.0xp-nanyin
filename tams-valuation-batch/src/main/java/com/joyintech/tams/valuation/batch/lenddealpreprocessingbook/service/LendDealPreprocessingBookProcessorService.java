/*
 * 文件名：LendDealPreprocessingBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.batch.lenddealpreprocessingbook.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 拆借预交易账务处理 process逻辑处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月9日
 * @see LendDealPreprocessingBookProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendDealPreprocessingBookProcessorService {

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 共通方法service
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param) {
        //获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param.get(GlobalConstant.READER_RESULT);
        
        // 若reader传递的参数或交易明细为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListFinProductDealInfoComDTO() == null || acctEntity.getListFinProductDealInfoComDTO().isEmpty()) {
            return null;
        }

        // 定义此process的返回变量
        BookGenerateResult rtnResult = new BookGenerateResult();

        // 账务明细若为空赋初始值
        if (rtnResult.getValBokBookdetailBatch() == null) {
            rtnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");

        TreeNode bookSetTreeNodeNow = acctEntity.getBookSetTreeNode();// 会计场景

        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 交易基础信息列表

        // 循环 交易基础信息列表 进行逻辑处理
        for (FinProductDealInfoComDTO dealDto : listFinProductDealInfoComDTONow) {
            //交易明细.金融产品类型 != “拆出”
            if (!DictCodeListExt1.金融产品类型_拆出.getValue().equals(dealDto.getFinprodType())&&!DictCodeListExt1.金融产品类型_同业借款.getValue().equals(dealDto.getFinprodType())) {
                continue;
            }
            
            // 变量 - 交易信息（标的交易信息）
            TradeInfoDto dealInfoForTarget = this.getTargetInfo(dealDto.getFinprodId(),dealDto.getFinprodType(),dealDto.getBranch());
            // 保证获取的标的交易信息不为空
            if (dealInfoForTarget == null) {
                dealInfoForTarget = new TradeInfoDto();
            }

            // 组装记账元素
            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();

            // 设置账务引擎参数
            ad.setTreeNode(bookSetTreeNodeNow);// 塞入记账场景树节点 = 会计场景

            // 记账场景要素map塞值,(调用共通方法-交易类场景元素赋值)
            accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealDto);
            accountSceneMap.put(BookConstant.TYPE_1, dealInfoForTarget.getType1());// 分类1 - 变量.交易信息.分类1
            accountSceneMap.put(BookConstant.TYPE_2, dealInfoForTarget.getType2());// 分类2 - 变量.交易信息.分类2
            // 是否累计余额->是
            accountSceneMap.put(BookConstant.IS_ACC_BALANCE, DictCodeList.是否_是.getValue());
            
            // 根据交易方向，设置不同的记账元素
            if (DictCodeList.交易方向_收息.getValue().equals(dealDto.getPs())) {
                // ad.会计场景代码="拆放同业（同业借款）-中间收息"
                ad.setTableId(DictCodeListTableId.会计场景代码_拆放同业_中间收息.getValue());
                
                accountSceneMap.put(BookConstant.BOOK_REMARK, "拆放同业(同业借款)中间付息交易");// 备注
                
                //(1) 查询交易的到期日对应的应计利息
                BigDecimal varIntAmt = BigDecimal.ZERO;
                SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
                searchDao.setBooksetId(acctEntity.getBooksetId());
                searchDao.setBokDetailId(acctEntity.getBokDetailId());
                searchDao.setHappenDate(DateUtil.addDay(dealDto.getMdate(), -1));
                searchDao.setBookDate(acctEntity.getBookDate());
//                searchDao.setTaskPri(dealDto.getTaskPri());
                searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                    Arrays.asList(DictCodeBaseSubjectNatrue.应计利息.getValue())));
                //MODIFY JINMAODONG 2018-08-28 删除分支序号
//                searchDao.setBranch(dealDto.getBranch());
                List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                    searchDao, DictCodeList.余额方向_借方.getValue());
                if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                    // 变量.应计利息                  =   上述共通返回的科目余额
                    varIntAmt = listSubjectBalance.get(0).getBalance();
                }
                
                //交易金额  <- 变量.应计利息
                accountAmountMap.put(BookConstant.INTAMT,
                    ArithmeticUtil.round(varIntAmt, NumberEnum.MONEY));
                
                // 基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealDto.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(),
                    accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);

            }
            else if(DictCodeList.交易方向_还本.getValue().equals(dealDto.getPs()) || DictCodeList.交易方向_到期.getValue().equals(dealDto.getPs())){
                //ad.会计场景代码="拆放同业-到期"
                ad.setTableId(DictCodeListTableId.会计场景代码_拆放同业_到期.getValue());
                accountSceneMap.put(BookConstant.BOOK_REMARK, "拆放同业(同业借款)还本到期交易");// 备注
                
                //调用公用方法【科目余额查询-期待方向】
                SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
                searchDao.setBooksetId(acctEntity.getBooksetId());
                searchDao.setBokDetailId(acctEntity.getBokDetailId());
                searchDao.setHappenDate(DateUtil.addDay(dealDto.getMdate(), -1));
                searchDao.setBookDate(acctEntity.getBookDate());
//                searchDao.setTaskPri(dealDto.getTaskPri());
                searchDao.setBaseSubjectNatureList(new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.成本.getValue())));
                //MODIFY JINMAODONG 2018-08-28 删除分支序号
//              searchDao.setBranch(dealDto.getBranch());
                List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(searchDao, DictCodeList.余额方向_借方.getValue());
                
                //定义 变量.结转日成本科目余额 = 科目余额
                BigDecimal jzrkmye = BigDecimal.ZERO;
                //定义 变量.结转日成本科目数量 = 数量
                BigDecimal jzrkmsl = BigDecimal.ZERO;
                
                if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                    //变量.结转日成本科目余额 = 科目余额
                    jzrkmye = listSubjectBalance.get(0).getBalance();
                    //变量.结转日成本科目数量 = 数量
                    jzrkmsl = listSubjectBalance.get(0).getNum();
                }
                
                //变量.交易份额 = 变量.成本科目数量 * 交易明细.单位净价
                BigDecimal jyFe = ArithmeticUtil.multiply(jzrkmsl,dealDto.getUnitCprice());
                //变量.冲减成本 = 变量.结转日成本科目余额 * 变量.交易份额/变量.成本科目数量
                BigDecimal cjCb = ArithmeticUtil.divide(ArithmeticUtil.multiply(jzrkmye,jyFe),jzrkmsl);
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, jyFe);
                //本金金额 = 变量.冲减成本
                accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(cjCb, NumberEnum.MONEY));
                // 基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealDto.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(),
                    accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
            }
            

            // 记账金额元素币种信息塞值
            // 因为与记账金额元素map的key及数量一致，故采用循环
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, dealDto.getCcy());// 币种 - 交易明细.交易币种
            }

            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            rtnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        }

        return rtnResult;
    }

    /**
     * 调用共通方法：[标的类共通方法-查询交易信息]，获取标的交易信息
     * 
     * @param finprodId 金融产品代码【必输项】
     * @param finprodType 金融产品类型【必输项】
     * @param branch 分支序号
     * @return ValFinProductDeal 标的查询
     * @see
     * @since 1.0
     */
    private TradeInfoDto getTargetInfo(String finprodId, String finprodType, Long branch) {
        // 定义返回值 - 标的交易信息
        TradeInfoDto tradeInfoDtoForReturn = null;

        // 调用共通方法：[标的类共通方法 - 交易信息查询]，获取标的交易信息
        List<TradeInfoDto> tradeInfoDtoList = finProductCommonService.getTradeInfoDtoList(finprodId, finprodType, branch);

        // 当查询结果不为空
        if (tradeInfoDtoList != null && tradeInfoDtoList.size() > 0) {
            // 默认取第一条标的信息
            tradeInfoDtoForReturn = tradeInfoDtoList.get(0);
        }

        return tradeInfoDtoForReturn;
    }


}

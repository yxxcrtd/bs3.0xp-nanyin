/*
 * 文件名：AccruedFeePayProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.accruedfeepay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * TA计提式费用支付批处理 process相关业务处理
 * 
 * @author niuqianyu
 * @version 1.0 2018年6月11日
 * @see AccruedFeePayProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AccruedFeePayProcessorService {

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 记账公共类：bookCommonLogic
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 标的查询共通方法
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * process 数据处理
     * 
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param1.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数或金融产品交易信息列表为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListFinProductDealInfoComDTO() == null || acctEntity.getListFinProductDealInfoComDTO().isEmpty()) {
            return null;
        }

        // 定义返回值
        BookGenerateResult returnResult = new BookGenerateResult();

        // 获取待处理任务信息
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param1.get("BATCHINFO");

        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String finprodIdNow = acctEntity.getBokDetailId();// 账务明细代码
        // String invAimNow = acctEntity.getInvAim(); // 投资目的
        TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 会计场景
        // Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        // Date bookDateNow = acctEntity.getBookDate();// 入账日期
        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO();// 金融产品交易信息list

        // 获取标的信息.金融产品类型
        TargetFinProduct targetFinProduct = this.getTargetFinProduct(finprodIdNow);
        // 交易信息.分类1
        String type1Now = targetFinProduct.getType1();
        // 交易信息.分类2
        String type2Now = targetFinProduct.getType2();

        // 循环金融产品交易信息list,进行逻辑处理
        for (FinProductDealInfoComDTO dealNow : listFinProductDealInfoComDTONow) {
            // 获取当前 金融产品类型
            String finprodTypeNow = dealNow.getFinprodType();
            // 获取当前 金融产品 现金流是否结清
            String isClean = dealNow.getIsClean();
            // [待处理交易].[交易金额]
            BigDecimal jyje = dealNow.getTradeAmt() == null ? BigDecimal.ZERO : dealNow.getTradeAmt();

            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();

            // 共通元素设值 - 设置账务引擎参数
            ad.setTreeNode(bookSetTreeNodNow);// 塞入记账场景树节点 = 会计场景

            // 记账场景要素map塞值,(调用共通方法-交易类场景元素赋值)
            accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
            accountSceneMap.put(BookConstant.TYPE_1, type1Now);// 分类1 - 变量.交易信息.分类1
            accountSceneMap.put(BookConstant.TYPE_2, type2Now);// 分类2 - 变量.交易信息.分类2
            accountSceneMap.put(BookConstant.MDATE,dealNow.getMdate()); //结息日 - 到期日

            // 根据金融产品类型生成不同的记账凭证数据
            if (DictCodeListExt1.现金流二级类型_固定管理费.getValue().equals(finprodTypeNow)) {// 【固定管理费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_固定资产管理费支付.getValue());
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "固定管理费交易登账");// 凭证说明

            }
            else if (DictCodeListExt1.现金流二级类型_销售费.getValue().equals(finprodTypeNow)) {// 【销售服务费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_销售服务费支付.getValue());// ad.会计场景代码 = 净值型产品销售服务费;
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "销售服务费交易登账");// 凭证说明

            }
            else if (DictCodeListExt1.现金流二级类型_托管费.getValue().equals(finprodTypeNow)) {// 【托管费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_托管费支付.getValue());// ad.会计场景代码 = 净值型产品托管费;
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "固定托管费交易登账");// 凭证说明

            }
            else if (DictCodeListExt1.现金流二级类型_浮动管理费.getValue().equals(finprodTypeNow)) {// 【浮动管理费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_浮动管理费支付.getValue());// ad.会计场景代码 = 净值型产品浮动管理费;
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "固定浮动费交易登账");// 凭证说明

            }
            else if (DictCodeListExt1.现金流二级类型_代销费.getValue().equals(finprodTypeNow)) {// 【代销费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_代销费支付.getValue());// ad.会计场景代码 = 代销费;
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "代销费交易登账");// 凭证说明

            }
            else if (DictCodeListExt1.现金流二级类型_外包服务费.getValue().equals(finprodTypeNow)) {// 【外包服务费】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_产品_外包服务费支付.getValue());// ad.会计场景代码 = 外包服务费;
                // 记账场景要素map塞值
                accountSceneMap.put(BookConstant.BOOK_REMARK, "外包服务费交易登账");// 凭证说明

            }
            // 根据是否结清状态，进行数据处理
            if (DictCodeList.是否_是.getValue().equals(isClean)) {// 已结清
                // 定义当前基础科目性质
                List<String> baseSubjectNature = new ArrayList<>();
                if (DictCodeListExt1.现金流二级类型_固定管理费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付管理人报酬.getValue());
                }
                else if (DictCodeListExt1.现金流二级类型_销售费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付销售服务费.getValue());
                }
                else if (DictCodeListExt1.现金流二级类型_托管费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付托管费.getValue());
                }
                else if (DictCodeListExt1.现金流二级类型_浮动管理费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付超额管理费.getValue());
                }
                else if (DictCodeListExt1.现金流二级类型_代销费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付代销费.getValue());
                }
                else if (DictCodeListExt1.现金流二级类型_外包服务费.getValue().equals(finprodTypeNow)) {
                    baseSubjectNature.add(DictCodeBaseSubjectNatrue.应付外包服务费.getValue());
                }
                // 设置科目余额汇总查询参数 Dto
                SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
                searchDto.setBooksetId(booksetIdNow);// 账套代码
                searchDto.setBokDetailId(finprodIdNow);// 账务明细代码
                searchDto.setHappenDate(DateUtil.addDay(dealNow.getMdate(), -1));// 发生日
                searchDto.setBookDate(dealNow.getBookDate());// 入账日
                searchDto.setTaskPri(dealNow.getTaskPri());// 任务优先级
                searchDto.setBaseSubjectNatureList(baseSubjectNature);// 基础科目性质
                // 获取科目余额 -【调用共通方法：科目余额汇总查询】
                Map<String, BigDecimal> feeBaseMap = accountingCommonService.getSubjectBalanceCollection(searchDto, DictCodeList.借贷方向_贷.getValue(),null);
                // 获取科目余额
                BigDecimal kmye = ArithmeticUtil.round(feeBaseMap.get(dealNow.getCcy()), NumberEnum.MONEY);
                // 科目余额  - 调用金额共通方法设置有效小数位
                kmye = ArithmeticUtil.round(kmye, NumberEnum.MONEY);
                
                // 金额元素.交易金额 - 交易金额
                accountAmountMap.put("DEALAMT", jyje);
                // 金额元素.应计费用 - 科目余额
                accountAmountMap.put("ACCOUNTS_FEE", kmye);
                // 金额元素.费用调整 = 交易金额 - 科目余额
                accountAmountMap.put("MARGINAL_COST", ArithmeticUtil.doSub(jyje, kmye));
                // 金额元素.实付费用 = 交易金额
                accountAmountMap.put("REAL_PAYMEET", jyje);

            }
            else {// 未结清
                  // 金额元素.交易金额 - 交易金额
                accountAmountMap.put("DEALAMT", jyje);
                // 金额元素.应计费用 = 交易金额
                accountAmountMap.put("ACCOUNTS_FEE", jyje);
                // 金额元素.实付费用 = 交易金额
                accountAmountMap.put("REAL_PAYMEET", jyje);
            }

            // 记账金额元素币种信息塞值
            // 因为与记账金额元素map的key及数量一致，故采用循环
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
            }

            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        }

        return returnResult;
    }

    /**
     * 获取标的基本信息
     * 
     * @param bokDetailId 账务明细代码
     * @return TargetFinProduct 标的基本信息
     * @see
     * @since 1.0
     */
    private TargetFinProduct getTargetFinProduct(String bokDetailId) {
        // 定义查询list
        List<TargetSearchDto> targetSearchDtoList = new ArrayList<TargetSearchDto>();
        // 定义查询类型 实体类
        TargetSearchDto targetSearchDto = new TargetSearchDto();
        targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
        // 查询实体类塞入查询实体类List中
        targetSearchDtoList.add(targetSearchDto);

        // 获取标的信息
        Map<TargetSearchDto, Object> targetMap = finProductCommonService.getTargetInfo(bokDetailId, targetSearchDtoList);
        TargetFinProduct targetFinProduct = (TargetFinProduct)targetMap.get(targetSearchDto);

        // 确保不为null
        if (targetFinProduct == null) {
            targetFinProduct = new TargetFinProduct();
        }

        return targetFinProduct;
    }
}

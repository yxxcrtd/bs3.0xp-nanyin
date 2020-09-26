/*
 * 文件名：BookDetailSubjectAllProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.service.BookDetailCompareIntoProcessorService;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.dto.BookDetailSubjectAllReadResult;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceAll;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 更新账务明细（历史表）科目余额批处理 - processor相关业务处理
 *
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailCompareIntoProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectAllProcessorService {


    /**
     * AccountingCommonService
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

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
        BookDetailSubjectAllReadResult acctEntity = (BookDetailSubjectAllReadResult)param.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }
        // 获取从reader中拿到的值
        // 账套代码
        String booksetIdNow = acctEntity.getBooksetId();
        // 账务详细代码
        String bokDetailIdNow = acctEntity.getBokDetailId();
        // 入账日
        Date bookDateNow = acctEntity.getBookDate();
        // 基础科目性质
        String baseSubjectNature = acctEntity.getBaseSubjectNature();
        // 该账套下的账务明细代码
        List<ValBokBookdetailBatch> sortListNow = acctEntity.getValBokBookdetailList();
        // 以【账套代码 -业务明细代码 - 明细科目号】为维度，重置【原币余额】和【数量余额】。
        String balFlag = null;// 科目余额方向
        BigDecimal subjectBalanceBookDate = BigDecimal.ZERO;// 原币科目余额（入账日）
        BigDecimal subjectBalanceBookDateb = BigDecimal.ZERO;// 本位币科目余额（入账日）
        BigDecimal numAmtBookDate = BigDecimal.ZERO;// 数量余额（入账日）
        String balFlagBookDate = null;// 余额方向（入账日）
        // 获取科目余额方向
        String subjectNo = sortListNow.get(0).getSubjectNo();
        balFlag = accountingCommonService.getBalFlag(subjectNo, booksetIdNow);
        // 基础科目性质参数
        List<String> baseSubjectNatureList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(baseSubjectNature)) {
            baseSubjectNatureList.add(baseSubjectNature);
        }
        // 获取历史科目余额(发生日）
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetIdNow);
        searchDto.setBokDetailId(bokDetailIdNow);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 获取历史科目余额（入账日）
        // 发生日传空，入账日传参
        searchDto.setHappenDate(null);//发生日设为null
        searchDto.setBookDate(bookDateNow);//入账日 = 变量.入账日
        //分支序号
        searchDto.setBranch(sortListNow.get(0).getBranch());
        List<SubjectBalanceAll> subjectBalanceAllBookDateList = accountingCommonService.getLatestSubjectBalanceAll(
                searchDto);
        SubjectBalanceAll subjectBalance = new SubjectBalanceAll();
        if (subjectBalanceAllBookDateList != null && subjectBalanceAllBookDateList.size() > 0) {
            subjectBalance = subjectBalanceAllBookDateList.get(0);
            //变量.原币科目余额（入账日） = 明细科目结果（入账日）.原币科目余额;
            subjectBalanceBookDate = subjectBalance.getOAmtBookDate();
            //变量.本币科目余额（入账日） = 明细科目结果（入账日）.本币科目余额;
            subjectBalanceBookDateb = subjectBalance.getBAmtHappenDate();
            //变量.数量余额（入账日） = 明细科目结果（入账日）.数量余额;
            numAmtBookDate = subjectBalance.getNumAmtBookDate();
            //变量.余额方向（入账日） = 明细科目结果（入账日）.余额方向
            balFlagBookDate = subjectBalance.getBalFlagBookDate();
        }

        //排序
//        Collections.sort(sortListNow, new Comparator<ValBokBookdetailBatch>() {
//            @Override
//            public int compare(ValBokBookdetailBatch p1, ValBokBookdetailBatch p2) {
//                if(p1.getBookOrder().compareTo(p2.getBookOrder()) > 0){
//                    return 1;
//                }
//                else if (p1.getBookOrder().compareTo(p2.getBookOrder()) < 0) {
//                    return -1;
//                }
//                else {
//                    return 0;
//                }
//            }
//        });

        for (ValBokBookdetailBatch item : sortListNow) {

            // 账务明细.原币余额（入账日） == null
            if (item.getoAmtBookDate() == null) {
                // if 变量.实际余额方向 = null
                if (StringUtils.isEmpty(balFlagBookDate)) {
                    balFlagBookDate = item.getCdFlag();
                }
                // 调用共通方法->【详细设计-批处理账务相关共通方法设计】-【余额方向处理_发生额加余额】原币
                Map<String, Object> mapBookDate = accountingCommonService.balanceDirectDealAccrualAddBalance(
                        balFlag, balFlagBookDate, subjectBalanceBookDate, numAmtBookDate,
                        item.getCdFlag(),
                        item.getoHappenAmt(),
                        item.getHappenNumber(),
                        subjectBalanceBookDateb,
                        item.getbHappenAmt()
                );
                // 调用共通方法->【详细设计-批处理账务相关共通方法设计】-【余额方向处理_发生额加余额】本币
//                Map<String, Object> mapBookDateb = accountingCommonService.balanceDirectDealAccrualAddBalance(
//                    balFlag, balFlagBookDate, subjectBalanceBookDateb, numAmtBookDate,
//                    item.getCdFlag(),
//                    item.getbHappenAmt(),
//                    item.getHappenNumber());
                // 调用共通方法【账务类共通方法设计-余额方向处理_期待方向SHEET】获取
//    			Map<String, Object> directionInfo = accountingCommonService.balanceDirectDealExpectDirection(
//    					balFlagBookDate, (String) mapBookDateb.get("balDirect"),
//    					(BigDecimal) mapBookDateb.get("balance"), new BigDecimal(0));
                //原币余额
                item.setoAmtBookDate(ArithmeticUtil.round((BigDecimal)mapBookDate.get("balance"), NumberEnum.MONEY));
                //本币余额
                item.setbAmtBookDate(ArithmeticUtil.round((BigDecimal)mapBookDate.get("benbalance"), NumberEnum.MONEY));
                item.setNumAmtBookDate(ArithmeticUtil.round((BigDecimal)mapBookDate.get("num"), NumberEnum.MONEY));
                item.setBalFlagBookDate((String)mapBookDate.get("balDirect"));

                balFlagBookDate = (String)mapBookDate.get("balDirect");
                subjectBalanceBookDate = (BigDecimal)mapBookDate.get("balance");
                numAmtBookDate = (BigDecimal)mapBookDate.get("num");
                //本币余额
                subjectBalanceBookDateb = (BigDecimal)mapBookDate.get("benbalance");
            }
        }
        // // 记账凭证生成结果定义
        BookGenerateResult returnResult = new BookGenerateResult();
        // // 塞入账务明细数据
        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }
        // // 塞入账务明细数据
        returnResult.setValBokBookdetailBatch(sortListNow);
        return returnResult;
    }

}

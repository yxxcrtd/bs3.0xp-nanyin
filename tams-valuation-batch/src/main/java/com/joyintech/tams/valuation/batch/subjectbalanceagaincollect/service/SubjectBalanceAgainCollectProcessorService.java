/*
 * 文件名：SubjectBalanceCollectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dao.SubjectBalanceAgainCollectMapper;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BokBalanceAgainCollectLogDto;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BooksetTodoDto;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.SubjectBalanceBatch;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.SubjectBalanceGenerateResult;
import com.joyintech.tams.valuation.common.auxiliary.service.OperationAuxiliaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.BokSubject;

/**
 * 科目余额汇总Processor逻辑Service
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectBalanceAgainCollectProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SubjectBalanceAgainCollectProcessorService {

    /**
     * 科目余额重汇Mapper
     */
    @Autowired
    private SubjectBalanceAgainCollectMapper subjectBalanceAgainCollectMapper;

    /**
     * 批处理账务相关公用方法
     */
    @Autowired
    private AccountingCommonService accountingCommonService;
    /**
     * 调用公用方法
     */
    @Autowired
    private OperationAuxiliaryService operationAuxiliaryService;

    /**
     * process 数据处理
     *
     * @param params1 参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public SubjectBalanceGenerateResult execute(Map<String, Object> params1) {
        // 获取reader返回值
        BooksetTodoDto acctEntity = (BooksetTodoDto) params1.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }
        // 进行批处理需要将结果集封装到result
        SubjectBalanceGenerateResult result = new SubjectBalanceGenerateResult();
        //定义 变量.汇总开始日 = 批处理任务执行信息.汇总开始日
        Date startBookDate = acctEntity.getStartBookDate();
        //定义 变量.汇总余额信息List	List<SubjectBalanceBatch>
        List<SubjectBalanceBatch> 汇总余额信息List = new ArrayList<>();
        //定义 变量.科目余额MAP key:科目号 value:余额明细
        Map<String, SubjectBalance> 科目余额MAP = new HashMap<>();
        //定义 变量.科目MAP
        Map<String, BokSubject> 科目MAP = new HashMap<>();
        //定义 变量.科目余额重汇信息记录DTO
        BokBalanceAgainCollectLogDto logDto = new BokBalanceAgainCollectLogDto();
        //2.1 查询汇总开始日前一日的科目余额
        // 调用共通设计->【详细设计-运维辅助工具-共通方法.xlsx】->科目余额查询共通方法SHEET
        List<SubjectBalance> bokBalanceData = operationAuxiliaryService.getBokBalanceData(acctEntity.getBooksetId(), DateUtil.addDay(startBookDate, -1));
        //将上述查询的结果组装成 【变量.科目余额MAP】，KEY:科目号，VALUE 对应科目号查询DTO
        科目余额MAP = bokBalanceData.stream().collect(Collectors.toMap(SubjectBalance::getSubjectNo, item -> item));
        //2.2 查询科目号信息并进行封装
        List<BokSubject> bokSubjects = subjectBalanceAgainCollectMapper.querySubject(acctEntity.getBooksetId());
        科目MAP = bokSubjects.stream().collect(Collectors.toMap(BokSubject::getSubjectNo, item -> item));
        //2.3 科目余额循环进行汇总
        do {
            //定义 变量.当日发生凭证List
            List<SubjectBalance> 当日发生凭证List = new ArrayList<>();
            //2.3.1 查询发生日当天最新的凭证:  调用共通设计->【详细设计-运维辅助工具-共通方法.xlsx】->凭证最新账务查询共通SHEET
            当日发生凭证List = operationAuxiliaryService.getVoucherLatestAccountingData(acctEntity.getBooksetId(), startBookDate, startBookDate);
            //2.3.2 三级科目余额的汇总
            //LOOP 变量.当日发生凭证List -> 变量.余额明细
            //调用【科目余额汇总处理】方法，参照【详细设计-账务类共通方法设计.xlxs的【科目余额汇总处理】sheet】
            for (SubjectBalance balance : 当日发生凭证List
            ) {
                accountingCommonService.collectSubjectBalance(balance, balance.getSubjectNo(),
                        balance.getSubjectLevel(), 科目余额MAP, 科目MAP, DictCodeList.是否_是.getValue());
            }
            //END LOOP
            //定义 变量.本次汇总余额List
            List<SubjectBalanceBatch> 本次汇总余额List = new ArrayList<>();
            //2.3.3 返回结果数据处理
            //定义 变量.上一次科目余额Map
            Map<String, SubjectBalance> 上一次科目余额Map = new HashMap<>();
            //LOOP 变量.科目余额MAP.keySet() 科目余额KEY
            for (String key : 科目余额MAP.keySet()) {
                //科目余额KEY

                //定义 变量.余额明细 = 变量.科目余额MAP.get(科目余额KEY)
                SubjectBalance entryValue = 科目余额MAP.get(key);
                //IF 变量.余额明细.入账日期 IS NULL
                if (!StringUtil.isNotEmpty(entryValue.getBookDate())) {
                    //变量.余额明细.入账日期 = 变量.汇总开始日
                    entryValue.setBookDate(startBookDate);
                }
                //END IF
                //定义 变量.封装结果DTO SubjectBalanceBatch
                SubjectBalanceBatch subjectBalanceBatch = new SubjectBalanceBatch(entryValue);
                //变量.汇总余额信息List.add(变量.封装结果DTO)
                汇总余额信息List.add(subjectBalanceBatch);
                //IF 变量.封装结果DTO.余额日期 = 变量.汇总开始日
                if (subjectBalanceBatch.getBalanceDate().compareTo(startBookDate) == 0) {
                    entryValue = getSubjectBalance(entryValue);
                    entryValue.setBalanceDate(startBookDate);
                    //变量.上一次科目余额Map.put(科目余额KEY,变量.余额明细)
                    上一次科目余额Map.put(key,entryValue);
                }
                //变量.科目余额MAP.put(科目余额KEY,变量.余额明细)
                //科目余额MAP.put(key,entryValue);
            }//END LOOP
            //变量.科目余额MAP =	变量.上一次科目余额Map
            科目余额MAP.clear();
            科目余额MAP = 上一次科目余额Map;
            //变量.汇总开始日 += 1
            startBookDate = DateUtil.addDay(startBookDate, 1);
        }//while(变量.汇总开始日<= 批处理任务执行信息.汇总结束日)
        while (DateUtil.compareDate(startBookDate, acctEntity.getEndBookDate()) <= 0);
        //变量.科目余额重汇信息记录DTO												  赋值
        //账套代码												批处理任务执行信息.账套代码
        logDto.setBooksetId(acctEntity.getBooksetId());
        //重汇结束日												批处理任务执行信息.汇总结束日
        logDto.setCdate(acctEntity.getEndBookDate());
        //返回值：
        //变量.汇总余额信息List
        result.setSubjectBalanceBatchs(汇总余额信息List);
        //变量.科目余额重汇信息记录DTO
        result.setBokBalanceAgainCollectLogDto(logDto);
        return result;
    }

    /**
     *
     * @param subjectBalance
     * @return
     */
    public SubjectBalance getSubjectBalance(SubjectBalance subjectBalance){
        /**
         * 变量.余额明细			-------------------------------         赋值
         * 余额日期										                变量.余额明细.入账日期
         * 入账日期										                NULL
         * 原币当日发生额										        0
         * 原币当日发生额贷方										    0
         * 原币当日发生额借方										    0
         * 原币结转当日发生额										    0
         * 原币结转当日发生额贷方										0
         * 原币结转当日发生额借方										0
         * 本位币当日发生额										        0
         * 本位币当日发生额贷方										    0
         * 本位币当日发生额借方										    0
         * 本位币结转当日发生额										    0
         * 本位币结转当日发生额贷方										0
         * 本位币结转当日发生额借方										0
         * 原币申购未实现平准金借										0
         * 原币赎回未实现平准金借										0
         * 原币申购未实现平准金贷										0
         * 原币赎回未实现平准金贷										0
         * 本位币申购未实现平准金借										0
         * 本位币赎回未实现平准金借										0
         * 本位币申购未实现平准金贷										0
         * 本位币赎回未实现平准金贷										0
         */
        //余额日期
        subjectBalance.setBalanceDate(subjectBalance.getBookDate());
        //入账日期
        subjectBalance.setBookDate(null);
        //原币当日发生额
        subjectBalance.setTdyOAmt(BigDecimal.ZERO);
        //原币当日发生额贷方
        subjectBalance.setTdyOCAmt(BigDecimal.ZERO);
        //原币当日发生额借方
        subjectBalance.setTdyODAmt(BigDecimal.ZERO);
        //原币结转当日发生额
        subjectBalance.setTdyOCoAmt(BigDecimal.ZERO);
        //原币结转当日发生额贷方
        subjectBalance.setTdyOCoCAmt(BigDecimal.ZERO);
        //原币结转当日发生额借方
        subjectBalance.setTdyOCoDAmt(BigDecimal.ZERO);
        //本位币当日发生额
        subjectBalance.setTdyBAmt(BigDecimal.ZERO);
        //本位币当日发生额贷方
        subjectBalance.setTdyBCAmt(BigDecimal.ZERO);
        //本位币当日发生额借方
        subjectBalance.setTdyBDAmt(BigDecimal.ZERO);
        //本位币结转当日发生额
        subjectBalance.setTdyBCoAmt(BigDecimal.ZERO);
        //本位币结转当日发生额贷方
        subjectBalance.setTdyBCoCAmt(BigDecimal.ZERO);
        //本位币结转当日发生额借方
        subjectBalance.setTdyBCoDAmt(BigDecimal.ZERO);
        //原币申购未实现平准金借
        subjectBalance.setTdyPurODUlamt(BigDecimal.ZERO);
        //原币赎回未实现平准金借
        subjectBalance.setTdyRedODUlamt(BigDecimal.ZERO);
        //原币申购未实现平准金贷
        subjectBalance.setTdyPurOCUlamt(BigDecimal.ZERO);
        //原币赎回未实现平准金贷
        subjectBalance.setTdyRedOCUlamt(BigDecimal.ZERO);
        //本位币申购未实现平准金借
        subjectBalance.setTdyPurBDUlamt(BigDecimal.ZERO);
        //本位币赎回未实现平准金借
        subjectBalance.setTdyRedBDUlamt(BigDecimal.ZERO);
        //本位币申购未实现平准金贷
        subjectBalance.setTdyPurBCUlamt(BigDecimal.ZERO);
        //本位币赎回未实现平准金贷
        subjectBalance.setTdyRedBCUlamt(BigDecimal.ZERO);
        return subjectBalance;
    }
}

/*
 * 文件名：SubjectBalanceCollectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.bankpositioncollect.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.BooksetTodoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCodeList; 
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.batch.psbc.bankcalbook.dto.BokBankPositionDto;
import com.joyintech.tams.batch.psbc.bankcalbook.service.BokBankPositionService;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dao.BankPositionCollectMapper;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dto.BankPositionGenerateResult;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dto.BankSubjectBalanceBatch;
import com.joyintech.tams.common.base.entity.BokSubject;

/**
 * 银行账科目余额汇总Processor逻辑Service
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankPositionCollectProcessorService {

    /**
     * 科目余额汇总Mapper
     */
    @Autowired
    private BankPositionCollectMapper bankPositionCollectMapper;

    /**
     * 银行账账务相关公用方法
     */
    @Autowired
    private BokBankPositionService bokBankPositionService;
    
    /**
     * process 数据处理
     * 
     * @param params1 参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BankPositionGenerateResult execute(Map<String, Object> params1) {
        // 获取reader返回值
        BooksetTodoDto acctEntity = (BooksetTodoDto)params1.get(GlobalConstant.READER_RESULT);
        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }
        // 进行批处理需要将结果集封装到result
        BankPositionGenerateResult result = new BankPositionGenerateResult();
        // 定义全局变量-账套代码 booksetId
        String booksetId = acctEntity.getBooksetId();
        // 定义全局变量--入账日
        Date bookDate = acctEntity.getBookDate();
        // 定义全局变量--余额日期(入账日-1)
        Date balanceDate = DateUtil.addDay(bookDate, -1);
        // 定义全局变量--底层科目级别
//        Long bottomLevel = 99L;

        // 1.昨日余额查询
        List<BokBankPositionDto> listBalances = bankPositionCollectMapper.queryYesterdayBankPosition(balanceDate);
        // 昨日余额Map数据封装
        Map<String, BokBankPositionDto> bankPositionMap = new HashMap<>();
        //将上述查询的结果组装成 【变量.科目余额MAP】，KEY:科目号 ，VALUE 对应科目号查询DTO
        listBalances.forEach(item -> {
            bankPositionMap.put(item.getFinprodId()+"@"+item.getSubjectNo(), item);
        });

        //2当日凭证查询
        List<BokBankPositionDto> listVouchers = bankPositionCollectMapper.queryVoucherToday(bookDate,booksetId,
            BookConstant.MAX_SUBJECT_LEVEL);
        // 查询科目号
        List<BokSubject> listSubjects = bankPositionCollectMapper.querySubject(booksetId);
        // 科目号封装
        Map<String, BokSubject> subjectsMap = new HashMap<>();
        listSubjects.forEach(item -> {
            subjectsMap.put(item.getSubjectNo(), item);
        });
         
        // 4.三级科目余额汇总
        // 上述步骤2的查询结果 -> 变量.余额明细
        for (BokBankPositionDto bankPosition : listVouchers) {
            bokBankPositionService.collectBokBankPosition(bankPosition, bankPosition.getSubjectNo(),
                bankPosition.getSubjectLevel(), subjectsMap, bankPositionMap, DictCodeList.是否_是.getValue());
        }

        // 重新定义listBalances
        listBalances = new ArrayList<>();
        // 将科目余额Map转换为List
        for(String key : bankPositionMap.keySet()){
            listBalances.add(bankPositionMap.get(key));
        }
        listBalances.stream().forEach(item->item.setBalanceDate(bookDate));
        if (!CollectionUtils.isEmpty(listBalances)) {
            // 暂时传入账套代码，后期待确定
            result.setProdId(acctEntity.getProdId());
            // 封装结果集
            List<BankSubjectBalanceBatch> listBalanceBatchs = new ArrayList<>();
            listBalances.forEach(item -> { 
                listBalanceBatchs.add(new BankSubjectBalanceBatch(item));
            });
            result.setSubjectBalanceBatchs(listBalanceBatchs);
        } 
        result.setProdId(acctEntity.getBooksetId());
        return result;
    }
}

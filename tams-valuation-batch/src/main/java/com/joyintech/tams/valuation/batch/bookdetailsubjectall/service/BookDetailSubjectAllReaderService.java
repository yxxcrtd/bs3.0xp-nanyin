/*
 * 文件名：BookDetailSubjectAllReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.bookdetailcompareinto.service.BookDetailCompareIntoReaderService;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.dao.BookDetailSubjectAllMapper;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.dto.BookDetailSubjectAllReadResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 更新账务明细（历史表）科目余额批处理 - reader相关业务处理
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailCompareIntoReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectAllReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectAllMapper bookDetailSubjectAllMapper;

    /**
     * 获取数据库数据
     * 
     * @param params 传递参数
     * @return List<BookDetailSubjectAmtReadResult>
     * @see
     * @since 1.0
     */
    public List<BookDetailSubjectAllReadResult> execute(Map<String, Object> params) {

        // 定义reader返回list
        List<BookDetailSubjectAllReadResult> resultList = new ArrayList<BookDetailSubjectAllReadResult>();
        // 定义查询出未处理的list
        List<ValBokBookdetailBatch> valBokBookdetailBatchList = new ArrayList<ValBokBookdetailBatch>();
        valBokBookdetailBatchList = bookDetailSubjectAllMapper.getValBokBookdetailBatch(params);
        
        Map<String, BookDetailSubjectAllReadResult> map = new HashMap<>();
        String key = null;
        BookDetailSubjectAllReadResult result = null;
        ValBokBookdetailBatch valBokBookdetailBatch = null;
        List<ValBokBookdetailBatch> valBokBookdetailList = null;
        for (ValBokBookdetailBatch item : valBokBookdetailBatchList) {
            key = item.getBooksetId() + GlobalConstant.COMMA
                    + item.getBokDetailId() + GlobalConstant.COMMA
//				+ item.getBaseSubjectNature() + GlobalConstant.COMMA 
                    + item.getBookDate() + GlobalConstant.COMMA
                    + item.getoCcy();
            if (map.containsKey(key)) {
                map.get(key).getValBokBookdetailList().add(item);
            }
            else {
                result = new BookDetailSubjectAllReadResult();
                valBokBookdetailList = new ArrayList<>();
                valBokBookdetailBatch = new ValBokBookdetailBatch();
                // 基础科目性质
                valBokBookdetailBatch.setBaseSubjectNature(item.getBaseSubjectNature());
                // 原币余额 发生日
                valBokBookdetailBatch.setoAmtHappenDate(item.getoAmtHappenDate());
                // 本币余额 发生日
                valBokBookdetailBatch.setbAmtHappenDate(item.getbAmtHappenDate());
                // 余额数量 发生日
                valBokBookdetailBatch.setNumAmtHappenDate(item.getNumAmtHappenDate());
                // 余额方向 发生日
                valBokBookdetailBatch.setBalFlagHappenDate(item.getBalFlagHappenDate());
                // 原币余额 入账日
                valBokBookdetailBatch.setoAmtBookDate(item.getbAmtBookDate());
                // 余额数量 入账日
                valBokBookdetailBatch.setNumAmtBookDate(item.getNumAmtBookDate());
                // 余额方向 入账日
                valBokBookdetailBatch.setBalFlagBookDate(item.getBalFlagBookDate());
                // 科目号
                valBokBookdetailBatch.setSubjectNo(item.getSubjectNo());
                // 流水
                valBokBookdetailBatch.setSeqno(item.getSeqno());
                // 账套名称
                valBokBookdetailBatch.setBooksetId(item.getBooksetId());
                //原币发生额
                valBokBookdetailBatch.setoHappenAmt(item.getoHappenAmt());
                //本币发生额
                valBokBookdetailBatch.setbHappenAmt(item.getbHappenAmt());
                //发生数量
                valBokBookdetailBatch.setHappenNumber(item.getHappenNumber());
                //借贷方向
                valBokBookdetailBatch.setCdFlag(item.getCdFlag());
                //处理序列
                valBokBookdetailBatch.setBookOrder(item.getBookOrder());
                //分支序号
                valBokBookdetailBatch.setBranch(item.getBranch());
                //原币币种
                valBokBookdetailBatch.setoCcy(item.getoCcy());
                //本币币种
                valBokBookdetailBatch.setbCcy(item.getbCcy());
                valBokBookdetailList.add(valBokBookdetailBatch);
                result.setValBokBookdetailList(valBokBookdetailList);
                // 账套代码
                result.setBooksetId(item.getBooksetId());
                // 账务明细代码
                result.setBokDetailId(item.getBokDetailId());
                // 基础科目性质
//                result.setBaseSubjectNature(item.getBaseSubjectNature());
                // 发生日
                result.setHappenDate(item.getHappenDate());
                // 入账日
                result.setBookDate(item.getBookDate());
                map.put(key, result);
            }
        }
        for(String itemkey:map.keySet()){
            resultList.add(map.get(itemkey));
        }
        return resultList;
    }

}

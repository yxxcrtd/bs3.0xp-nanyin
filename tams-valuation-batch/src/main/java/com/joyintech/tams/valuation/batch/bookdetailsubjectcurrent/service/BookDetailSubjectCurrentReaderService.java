/*
 * 文件名：BookDetailSubjectCurrentReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.service.BookDetailCompareIntoReaderService;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dao.BookDetailSubjectCurrentMapper;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCurrentReadResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 更新账务明细（当前表表）科目余额批处理 - reader相关业务处理
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailCompareIntoReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCurrentReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectCurrentMapper bookDetailSubjectCurrentMapper;

//    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    /**
     * 获取数据库数据
     * 
     * @return List<BookDetailSubjectCurrentReadResult>
     * @see
     * @since 1.0
     */

    public List<BookDetailSubjectCurrentReadResult> execute(Map<String, Object> param) {

        // 定义reader返回list
        List<BookDetailSubjectCurrentReadResult> resultList = new ArrayList<BookDetailSubjectCurrentReadResult>();
        // 定义查询出未处理的list
        List<ValBokBookdetailBatch> valBokBookdetailBatchList = new ArrayList<ValBokBookdetailBatch>();
        // 根据任务代码，获取跑批范围信息list（批处理待处理任务表）
        List<BokBatchTaskTodo> listBatchTaskTodo = bookCommonLogic.getTaskTodoScopeInfo(param);

        // 循环跑批范围，获取跑批范围里的账套id（利用set去重）
        List<String> bookSetIds = new ArrayList<String>();
        for (BokBatchTaskTodo voNow : listBatchTaskTodo) {
            // 当账套有值，塞入账套id
            if (voNow.getBooksetId() != null && voNow.getBooksetId().length() > 0){
                bookSetIds.add(voNow.getBooksetId());
            }
        }
        // TODO wangzhiang
        bookDetailSubjectCurrentMapper.updateBookDetailCurrent(bookSetIds,
            DictCodeList.凭证类型_手工.getValue());
        valBokBookdetailBatchList = bookDetailSubjectCurrentMapper.getValBokBookdetailBatch(param);

        Map<String, BookDetailSubjectCurrentReadResult> map = new HashMap<>();
        String key = null;
        BookDetailSubjectCurrentReadResult result = null;
        ValBokBookdetailBatch valBokBookdetailBatch = null;
        List<ValBokBookdetailBatch> valBokBookdetailList = null;
//        logger.info("【调查】original size " + valBokBookdetailBatchList.size());
        for (ValBokBookdetailBatch item : valBokBookdetailBatchList) {
            key = item.getBooksetId() + GlobalConstant.COMMA
                    + item.getBokDetailId() + GlobalConstant.COMMA
//					+ item.getBaseSubjectNature() + GlobalConstant.COMMA 
                    + item.getHappenDate() + GlobalConstant.COMMA
                    + item.getBookDate() + GlobalConstant.COMMA
                    + item.getoCcy();
//			logger.info("【调查】" + item.getSeqno() + "| key = " + key);
            if (map.containsKey(key)) {
                Long taskPri = map.get(key).getTaskPri();
                //相同科目性质的明细，用较小的那条优先级
                map.get(key).setTaskPri(taskPri.compareTo(item.getTaskPri()) <= 0 ? taskPri : item.getTaskPri());
                map.get(key).getValBokBookdetailList().add(item);
            }
            else {
                result = new BookDetailSubjectCurrentReadResult();
                valBokBookdetailList = new ArrayList<>();
                valBokBookdetailBatch = new ValBokBookdetailBatch();
                // 基础科目性质
                valBokBookdetailBatch.setBaseSubjectNature(item.getBaseSubjectNature());
                // 原币余额 发生日
                valBokBookdetailBatch.setoAmtHappenDate(item.getoAmtHappenDate());
                //原币币种 
                valBokBookdetailBatch.setoCcy(item.getoCcy());
                //本位币币种
                valBokBookdetailBatch.setbCcy(item.getbCcy());
                // 本币余额 发生日
                valBokBookdetailBatch.setbAmtHappenDate(item.getbAmtHappenDate());
                // 余额数量 发生日
                valBokBookdetailBatch.setNumAmtHappenDate(item.getNumAmtHappenDate());
                // 余额方向 发生日
                valBokBookdetailBatch.setBalFlagHappenDate(item.getBalFlagHappenDate());
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
                //账务明细代码
                valBokBookdetailBatch.setBokDetailId(item.getBokDetailId());
                valBokBookdetailList.add(valBokBookdetailBatch);
                result.setValBokBookdetailList(valBokBookdetailList);
                result.setBooksetId(item.getBooksetId());
                // 账务明细代码
                result.setBokDetailId(item.getBokDetailId());
//                // 基础科目性质
//                result.setBaseSubjectNature(item.getBaseSubjectNature());
                // 发生日
                result.setHappenDate(item.getHappenDate());
                // 入账日
                result.setBookDate(item.getBookDate());
                //任务优先级
                result.setTaskPri(item.getTaskPri());
                map.put(key, result);
            }
        }
        for(String itemkey:map.keySet()){
            resultList.add(map.get(itemkey));
        }
        return resultList;
    }

}

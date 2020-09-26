/*
 * 文件名：ManualVoucherProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.manualvoucher.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.manualvoucher.dao.ManualVoucherMapper;
import com.joyintech.tams.valuation.batch.manualvoucher.dto.ManualVoucherReaderResult;
import com.joyintech.tams.valuation.batch.manualvoucher.dto.TradVouchDealDetailDto;
import com.joyintech.tams.valuation.batch.manualvoucher.dto.TradVouchDealDto;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;

/**
 * 手工凭证交易批处理 processor
 * 
 * @author chenggaoling
 * @version 1.0 2018年8月16日
 * @see ManualVoucherProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ManualVoucherProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {
    
    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;
    
    /**
     * 手工凭证交易批处理DAO
     */
    @Autowired
    private ManualVoucherMapper manualVoucherMapper;
    
    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        ManualVoucherReaderResult entity = (ManualVoucherReaderResult)items;
        // 取得手工凭证交易编号
        List<String> tradIdList = new ArrayList<>();
        // 手工凭证交易Map
        Map<String, TradVouchDealDto> dealMap = new HashMap<String, TradVouchDealDto>();
        for (TradVouchDealDto tradVouchDeal : entity.getTradVouchDealList()) {
            tradIdList.add(tradVouchDeal.getTradeid());
            dealMap.put(tradVouchDeal.getTradeid(), tradVouchDeal);
        }

        // 取得手工凭证交易明细
        List<TradVouchDealDetailDto> detailList = manualVoucherMapper.getTradVouchDealDetailList(tradIdList);
        // 根据交易明细封装返回结果
        BookGenerateResult result = new BookGenerateResult();
        List<ValBokBookdetailBatch> bookDetailList = new ArrayList<>();
        ValBokBookdetailBatch bookDetail = null;
        for (TradVouchDealDetailDto detail : detailList) {
            bookDetail = new ValBokBookdetailBatch();

            // 流水 <- 自动生成，规则为BOOK + "YYYYMMDD"+20位序列号
            bookDetail.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
            // 账套代码 
            bookDetail.setBooksetId(entity.getBooksetId());
            // 场景代码 
            bookDetail.setTableId("0");
            // 交易编号
            bookDetail.setTradeId(detail.getTradeId());
            // 业务明细代码
            bookDetail.setBusiId(detail.getBusiId());
            // 任务代码
            SysBatchTask batchtask = (SysBatchTask) this.getParams().get("BATCHTASK");
            bookDetail.setTaskId(batchtask.getTaskId());
            // 任务优先级
            bookDetail.setTaskPri(batchtask.getTaskPri());
            // 发生日期 
            bookDetail.setHappenDate(entity.getHappenDate());
            // 入账日期 
            bookDetail.setBookDate(entity.getBookDate());
            // 账套日期
            bookDetail.setBooksetDate(entity.getBooksetDate());
            //基础科目性质 关联科目表查询
            bookDetail.setBaseSubjectNature(detail.getBaseSubjectNature());
            // 账务明细代码 
            bookDetail.setBokDetailId(detail.getBokDetailId());
            // 分支序号
            bookDetail.setBranch(detail.getBranch());
            // 科目号
            bookDetail.setSubjectNo(detail.getSubjectNo());
            // 四级科目号
            if (detail.getSubjectNo() != null && detail.getDetailSubjectNoTail() != null) {
                bookDetail.setDetailSubjectNo(
                    ValStringUtil.getNotNullStr(bookDetail.getSubjectNo())
                    + ValStringUtil.getNotNullStr(detail.getDetailSubjectNoTail()));
            }
            else {
                bookDetail.setDetailSubjectNo("");
            }
            // 借贷方向
            bookDetail.setCdFlag(detail.getCdFlag());
            // 原币
            bookDetail.setoCcy(detail.getCcy());
            // 原币发生额
            bookDetail.setoHappenAmt(detail.getoHappenAmt());
            // 本位币
            bookDetail.setbCcy(entity.getStandardCcy());
            // 本位币发生额
            // bookDetail.setbHappenAmt(detail.getoHappenAmt());
            bookDetail.setbHappenAmt(detail.getbHappenAmt());
            // 发生数量 
            bookDetail.setHappenNumber(detail.getHappenNumber());
            // 凭证编号
            bookDetail.setBookId(dealMap.get(bookDetail.getTradeId()).getBookid());
            // 凭证说明
            bookDetail.setBookRemark(dealMap.get(bookDetail.getTradeId()).getBookremark());
            // 冲正标识
            bookDetail.setOffsetFlag(dealMap.get(bookDetail.getTradeId()).getOffsetflag());
            // 凭证类型
            bookDetail.setBookType(DictCodeList.凭证类型_手工.getValue());
            // 处理序列 
            bookDetail.setBookOrder(
                new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
            // 0金额是否处理
            bookDetail.setIsexeZeroAmt(DictCodeList.是否_是.getValue());
            // 添加到list中
            bookDetailList.add(bookDetail);
        }
        result.setProdId(entity.getBooksetId() + "手工凭证批处理");
        result.setValBokBookdetailBatch(bookDetailList);
        
        return result;
    }

}

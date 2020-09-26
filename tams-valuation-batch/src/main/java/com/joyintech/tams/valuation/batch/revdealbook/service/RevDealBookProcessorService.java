/*
 * 文件名：BookDetailCompareIntoProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.accounting.entity.BookLatestInfoDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.revdealbook.dto.RevDealBookDetailDto;
import com.joyintech.tams.valuation.batch.revdealbook.dto.RevDealBookReaderDto;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 账务明细比较合并批处理 - processor相关业务处理
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see RevDealBookProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RevDealBookProcessorService {

    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

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
        RevDealBookReaderDto acctEntity = (RevDealBookReaderDto)param1.get(
            GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }
        // 记账凭证生成结果定义
        BookGenerateResult returnResult = new BookGenerateResult();
        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        // 定义数据处理后的账务明细数据list
        List<ValBokBookdetailBatch> rightDetailList = new ArrayList<ValBokBookdetailBatch>();

        for (RevDealBookDetailDto rev : acctEntity.getListRevDealBookDetailDto()) {
            
            //凭证最新账务查询
            List<BookLatestInfoDto> listInfo = null;
            //传递参数不能为空
            if(rev.getBooksetId()!=null && rev.getBookDate()!=null && rev.getpTradeId()!=null && rev.getpTradeId().length()>0){
                listInfo = accountingCommonService.getBookLatestInfo(rev.getBooksetId(), rev.getBookDate(),null, null, null, rev.getpTradeId(), null, null, null, null);
            }             
            
            if (listInfo != null && listInfo.size() > 0) {
                for (BookLatestInfoDto info : listInfo) {
                    ValBokBookdetailBatch dto = new ValBokBookdetailBatch();
                    // 复制BEAN
                    BeanUtil.copyPropertiesIgnoreNull(dto, info);
                    // 流水 自动生成
                    dto.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
                    // 入账日期 DTO.入账日期
                    dto.setBookDate(rev.getBookDate());
                    // 账套日期 DTO.账套日期
                    dto.setBooksetDate(rev.getBooksetDate());
                    // 原币发生额 变量.原账务明细（当前表）.原币发生额 *-1
                    dto.setoHappenAmt(
                        ArithmeticUtil.multiply(info.getoHappenAmt(), new BigDecimal(-1)));
                    // 本位币发生额 变量.原账务明细（当前表）.本位币发生额 *-1
                    dto.setbHappenAmt(
                        ArithmeticUtil.multiply(info.getbHappenAmt(), new BigDecimal(-1)));
                    // 发生数量 变量.原账务明细（当前表）.发生数量 *-1
                    dto.setHappenNumber(
                        ArithmeticUtil.multiply(info.getHappenNumber(), new BigDecimal(-1)));
                    // 反向流水号
                    dto.setrSeqno(null);
                    // 处理序列
                    dto.setBookOrder(
                        new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
                    // 余额方向（发生日）
                    dto.setBalFlagHappenDate(null);
                    // 原币余额（发生日）
                    dto.setoAmtHappenDate(null);
                    // 本位币余额（发生日）
                    dto.setbAmtHappenDate(null);
                    // 数量余额（发生日）
                    dto.setNumAmtHappenDate(null);
                    // 余额方向（入账日）
                    dto.setBalFlagBookDate(null);
                    // 原币余额（入账日）
                    dto.setoAmtBookDate(null);
                    // 本位币余额（入账日）
                    dto.setbAmtBookDate(null);
                    // 数量余额（入账日）
                    dto.setNumAmtBookDate(null);
                    // 创建时间 当前时间
                    dto.setCreateTime(new Date());
                    // 更新时间 当前时间
                    dto.setUpdateTime(new Date());
                    // 冲正标识【数据字典.冲销标志.冲销交易】
                    dto.setOffsetFlag(DictCodeList.冲正标识_冲销交易.getValue());
                    
                    rightDetailList.add(dto);
                }
            }
        }

        // 塞入账务明细数据
        returnResult.setValBokBookdetailBatch(rightDetailList);

        return returnResult;
    }

}

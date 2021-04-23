/*
 * 文件名：BookGenerateResultCommonWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bookgenerateresult;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokStatDetPosiitonMapper;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.dao.AccountingMethodMapper;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.common.batch.losscommon.service.LossCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 记账凭证返回实体类 - 公用方法写入 (针对账务明细当前表) 包括 插入【记账凭证主表信息】 和 【插入记账凭证明细表信息(当前表)】
 * 
 * @author yangjian
 * @version 1.0 2018年5月9日
 * @see BookGenerateResultForCurrentCommonWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookGenerateResultForCurrentCommonWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;

    @Autowired
    private AccountingMethodMapper accountingMethodMapper;

    /**
     * 账务汇总表（账套）表,数据库操作
     */
    @Autowired
    private BokStatDetPosiitonMapper bokSummaryTableMapper;
    
    /**
     * 减值相关共通
     */
    @Autowired
    private LossCommonService lossCommonService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 记账凭证数据写入数据库(针对账务明细当前表) {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<BookGenerateResult> resultList = (List<BookGenerateResult>)items;
//        Object t = this.getParams() == null ? null: this.getParams().get("BATCHTASK");
//        SysBatchTask task = null;
//        if (t != null) {
//            task = (SysBatchTask)this.getParams().get("BATCHTASK");
//            logger.error(
//                "【调查】当前表Writer:[name = " + task.getBeanName() + "| id = " + task.getTaskId()
//                        + "| pri = " + task.getTaskPri() + "| count = " + resultList.size() + "]");
//        }
//        
        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResult bookGenerateResult : resultList) {
//            if (task != null){
//            logger.error(
//                "【调查】当前表Writer明细:[name = " + task.getBeanName() + "| id = " + task.getTaskId()
//                + "| pri = " + task.getTaskPri() + "| count = " + bookGenerateResult.getValBokBookdetailBatch().size() + bookGenerateResult.getValBokBookdetailBatch() + "]");
//            }
            // 插入记账凭证主表信息 和 插入记账凭证明细表信息
            bookAccountGenLogic.insertKeepAccount(bookGenerateResult);
        }

        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResult bookGenerateResult : resultList) {
            // 插入【账务汇总表（账套）】表
           this.insertBokSummary(bookGenerateResult.getValBokStatDetPosiitonBatch());
        }
        /**ADD BY CAOGUO ON 20201016 减值相关修改 START **/
        // 第五步：循环数据，插入【统计分析明细表（资产减值）】数据
        for(BookGenerateResult bookGenerateResult : resultList){
            lossCommonService.insertBokStatLossPosiitonList(bookGenerateResult.getBokStatLossPosiitonList());
        }
        /**ADD BY CAOGUO ON 20201016 END**/

    }

    /**
     * 将数据，插入【账务汇总表（账套）】表
     * @param bokSummaryList
     * @return
     * @see
     * @since 1.0
     */
    private int insertBokSummary(List<BokStatDetPosiiton> bokSummaryList){
        int sucessNum = 0;
        //数据存在
        if(bokSummaryList!=null && !bokSummaryList.isEmpty()){
            //循环列表进行插入
            for(BokStatDetPosiiton vo: bokSummaryList){

                // update by: 当重复跑某天的批，出现序列不同的【统计分析明细表（账务明细）】重复数据。 故重写删除，删除主键不包含序列。 yangjian 2019/06/17============beign
                //判断当前数据是否存在，若存在直接进行删除(因为有序列存在，理论上是不会有重复的。 故为了方便，发现存在的直接进行删除操作。)
//                BokStatDetPosiiton bokSummaryTable = bokSummaryTableMapper.selectByPrimaryKey(vo);
//                if(bokSummaryTable!=null){
//                    bokSummaryTableMapper.deleteByPrimaryKey(vo);
//                }

                //不包含【处理序列】的主键删除
                accountingMethodMapper.deleteBokStatDetPosiiton(vo);
                // update by: 当重复跑某天的批，出现序列不同的【统计分析明细表（账务明细）】重复数据。 故重写删除，删除主键不包含序列。 yangjian 2019/06/17============end
                //插入
                sucessNum = sucessNum + bokSummaryTableMapper.insert(vo);
            }
        }
        return sucessNum;
    }
    
}

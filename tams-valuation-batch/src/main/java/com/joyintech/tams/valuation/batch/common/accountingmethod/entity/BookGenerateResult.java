/*
 * 文件名：BookGenerateResult.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.entity;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账凭证生成结果
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see BookGenerateResult
 * @since 1.0
 */
public class BookGenerateResult extends NeedToWriteEntity {

    /**
     * 交易编号
     */
    // delete:交易编号去除，后期不需要按交易编号分类。 yangjian 2018.05.09
    // private String tradeId;

    /**
     * 账务明细（当前表）
     */
    private List<ValBokBookdetailBatch> valBokBookdetailBatch = new ArrayList<ValBokBookdetailBatch>();

    /**
     * 当日统计分析账务明细汇总信
     */
    private List<BokStatDetPosiiton> valBokStatDetPosiitonBatch = new ArrayList<BokStatDetPosiiton>();

    /**
     * 记账凭证主错误表信息
     */
    // private BokKeepaccountErr bokKeepaccountErr;
    /**
     * 统计分析明细表（资产减值）
     */
    private List<BokStatLossPosiiton> bokStatLossPosiitonList = new ArrayList<BokStatLossPosiiton>();

    public List<BokStatLossPosiiton> getBokStatLossPosiitonList() {
        return bokStatLossPosiitonList;
    }

    public void setBokStatLossPosiitonList(List<BokStatLossPosiiton> bokStatLossPosiitonList) {
        this.bokStatLossPosiitonList = bokStatLossPosiitonList;
    }
    /**
     * 默认构造函数
     */
    public BookGenerateResult() {
        super();
    }

    /**
     * @param tradeId 交易编号
     */
    public BookGenerateResult(String tradeId) {
        super();
    }

    /**
     * 获取 valBokBookdetailBatch
     * 
     * @return valBokBookdetailBatch.
     */
    public List<ValBokBookdetailBatch> getValBokBookdetailBatch() {
        return valBokBookdetailBatch;
    }

    /**
     * 设置 valBokBookdetailBatch
     * 
     * @return valBokBookdetailBatch.
     */
    public void setValBokBookdetailBatch(List<ValBokBookdetailBatch> valBokBookdetailBatch) {
        this.valBokBookdetailBatch = valBokBookdetailBatch;
    }

    /**
     * 获取valBokStatDetPosiitonBatch
     * @return
     */
    public List<BokStatDetPosiiton> getValBokStatDetPosiitonBatch() {
        return valBokStatDetPosiitonBatch;
    }

    /**
     * 设置valBokStatDetPosiitonBatch
     * @param valBokStatDetPosiitonBatch
     */
    public void setValBokStatDetPosiitonBatch(List<BokStatDetPosiiton> valBokStatDetPosiitonBatch) {
        this.valBokStatDetPosiitonBatch = valBokStatDetPosiitonBatch;
    }
}

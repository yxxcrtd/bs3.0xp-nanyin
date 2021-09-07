/*
 * 文件名：BookGenerateResultWithBokSummary.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.entity;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.*;

/**
 * 记账凭证生成结果(带【账务汇总表（账套）】 列表,带账务明细辅助表)的
 * 
 * @author yangjian
 * @version 1.0 2018年9月19日
 * @see BookGenerateResultWithBokSummary
 * @since 1.0
 */
public class BookGenerateResultWithBokSummary extends BookGenerateResult {

    /**
     * 统计分析账务明细汇总信息---【账务汇总表（账套）】 列表
     */
    private List<BokStatDetPosiiton> bokSummaryList = new ArrayList<BokStatDetPosiiton>();

    /**
     * 统计【账务明细辅助表】数据
     */
    private List<BokBookdetailCurrentExt> bokBookdetailCurrentExtList = new ArrayList<BokBookdetailCurrentExt>();
    
    /**
     * 行情价格警告信息
     */
    private List<BokBatchMarketwarningInfo> bokBatchMarketwarningInfoList = new ArrayList<BokBatchMarketwarningInfo>();

    /**
     * 资产行情信息
     */
    private List<BokAssetMarketInfo> bokAssetMarketInfoList = new ArrayList<BokAssetMarketInfo>();

    /**
     * 减值模型ECL结果
     */
    private ModEclResult modEclResult;


    public List<BokStatDetPosiiton> getBokSummaryList() {
        return bokSummaryList;
    }

    public void setBokSummaryList(List<BokStatDetPosiiton> bokSummaryList) {
        this.bokSummaryList = bokSummaryList;
    }

    /**
     * 获取 bokBookdetailCurrentExtList
     * 
     * @return bokBookdetailCurrentExtList.
     */
    public List<BokBookdetailCurrentExt> getBokBookdetailCurrentExtList() {
        return bokBookdetailCurrentExtList;
    }

    /**
     * 设置 bokBookdetailCurrentExtList
     * 
     * @param bokBookdetailCurrentExtList bokBookdetailCurrentExtList
     */
    public void setBokBookdetailCurrentExtList(List<BokBookdetailCurrentExt> bokBookdetailCurrentExtList) {
        this.bokBookdetailCurrentExtList = bokBookdetailCurrentExtList;
    }

	public List<BokBatchMarketwarningInfo> getBokBatchMarketwarningInfoList() {
		return bokBatchMarketwarningInfoList;
	}

	public void setBokBatchMarketwarningInfoList(List<BokBatchMarketwarningInfo> bokBatchMarketwarningInfoList) {
		this.bokBatchMarketwarningInfoList = bokBatchMarketwarningInfoList;
	}

    /**
     * @return 资产行情信息
     */
    public List<BokAssetMarketInfo> getBokAssetMarketInfoList() {
        return bokAssetMarketInfoList;
    }

    /**
     * 设置 资产行情信息
     *
     * @param bokAssetMarketInfoList 资产行情信息
     */
    public void setBokAssetMarketInfoList(List<BokAssetMarketInfo> bokAssetMarketInfoList){
        this.bokAssetMarketInfoList = bokAssetMarketInfoList;
    }

    /**
     * @return 减值模型ECL结果
     */
    public ModEclResult getModEclResult() {
        return modEclResult;
    }

    /**
     * 设置 减值模型ECL结果
     *
     * @param modEclResult 减值模型ECL结果
     */
    public void setModEclResult(ModEclResult modEclResult) {
        this.modEclResult = modEclResult;
    }
}

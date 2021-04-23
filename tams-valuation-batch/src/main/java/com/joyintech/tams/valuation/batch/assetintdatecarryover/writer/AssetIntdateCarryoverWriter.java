/*
 * 文件名：AssetIntdateCarryoverWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverDeal;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;

/**
 * 资产理论结息日结转-writer
 * 
 * @author chengzhong
 * @version 1.0 2018年5月9日
 * @see AssetIntdateCarryoverWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private BookCommonLogic bookCommonLogic;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    /**
     * 记账凭证数据写入数据库(针对账务明细当前表) {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<AssetIntdateCarryoverResult> resultList = (List<AssetIntdateCarryoverResult>)items;

        // 循环记账凭证list，对每个记账凭证进行操作
        for (AssetIntdateCarryoverResult bookGenerateResult : resultList) {
            
            //交易类金融产品信息
            List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();
            //金融产品交易
            List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
            //估值核算交易扩展表
        //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
            List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
            //入账信息表
            List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
            //入账账务明细表
            List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
            //账务明细代码表
            List<BokDetailId> listBokDetailId = new ArrayList<BokDetailId>();
            
            for (AssetIntdateCarryoverDeal deal : bookGenerateResult.getAssetIntdateCarryoverDealList()) {
                if(deal.getValTradeFinProduct()!=null){
                    listValTradeFinProduct.add(deal.getValTradeFinProduct());
                }
                if(deal.getValFinProductDeal()!=null){
                    listValFinProductDeal.add(deal.getValFinProductDeal());
                }
                if(deal.getValFinProductDealExt()!=null){
                    listValFinProductDealExt.add(deal.getValFinProductDealExt());
                }
                if(deal.getValDealAccounting()!=null){
                    listValDealAccounting.add(deal.getValDealAccounting());
                }
                if(deal.getValDealAccountingDetail()!=null){
                    listValDealAccountingDetail.add(deal.getValDealAccountingDetail());
                }
                if(deal.getBokDetailId()!=null){
                    listBokDetailId.add(deal.getBokDetailId());
                }
            }
            
            // 入账信息表 数据插入
            bookCommonLogic.insertValDealAccounting(listValDealAccounting);
            // assetIntdateCarryoverMapper.insertValDealAccounting(listValDealAccounting);

            // 入账账务明细表 数据插入
            bookCommonLogic.insertValDealAccountingDetail(listValDealAccountingDetail);
            // assetIntdateCarryoverMapper.insertValDealAccountingDetail(listValDealAccountingDetail);

            // 金融产品交易表 数据插入
            bookCommonLogic.insertValFinProductDeal(listValFinProductDeal);
            // assetIntdateCarryoverMapper.insertValFinProductDeal(listValFinProductDeal);

            // 估值核算交易扩展表 数据插入
            bookCommonLogic.insertValFinProductDealExt(listValFinProductDealExt);
            // assetIntdateCarryoverMapper.insertValFinProductDealExt(listValFinProductDealExt);

            // 估值核算账务明细表 数据插入 add by yanya on 20201217
            bookCommonLogic.insertBokDetailId(listBokDetailId);

            // 交易类金融产品表 数据插入
            /**MODIFY BY WUJIE ON 20190521 START
             bookCommonLogic.insertValTradeFinProduct(listValTradeFinProduct);
             **/
            List<FinTradeProduct> rstlist = new ArrayList<FinTradeProduct>();
            Map<String,FinTradeProduct> rstMap = new HashMap<String,FinTradeProduct>();
            for(FinTradeProduct fp :listValTradeFinProduct){
                String key = fp.getFinprodId()+fp.getFinprodType()+fp.getBranch();
                FinTradeProduct rstObject = finTradeProductMapper.selectByPrimaryKey(fp);
                if(rstObject==null){
                    if(rstMap.get(key)==null){
                        rstlist.add(fp);
                    }
                    rstMap.put(key, fp);
                }
            }
            bookCommonLogic.insertValTradeFinProduct(rstlist);
            /**MODIFY BY WUJIE ON 20190521 END**/
        }

    }


}

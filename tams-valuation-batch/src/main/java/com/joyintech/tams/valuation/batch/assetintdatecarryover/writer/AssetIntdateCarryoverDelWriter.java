/*
 * 文件名：BookGenerateResultCommonWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dao.AssetIntdateCarryoverMapper;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 资产理论结息日结转-STEP1
 * 
 * @author chengzhong
 * @version 1.0 2018年5月9日
 * @see AssetIntdateCarryoverDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 资产理论结息日结转MAP
     */
    @Autowired
    private AssetIntdateCarryoverMapper assetIntdateCarryoverMapper;

    /**
     * 记账凭证数据写入数据库(针对账务明细当前表) {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取task传递参数
        Map<String,Object> params = this.getParams();
        List<String> listPs = new ArrayList<>();
        listPs.add(DictCodeList.交易方向_收息.getValue());
        listPs.add(DictCodeList.交易方向_还本.getValue());
//        listPs.add(DictCodeList.交易方向_债券派息.getValue());
//        listPs.add(DictCodeList.交易方向_债券派本.getValue());
        listPs.add(DictCodeList.交易方向_到期.getValue());
        /**modify by wujie on 20210107 start**/
        listPs.add(DictCodeList.交易方向_卖空债券结息.getValue());
        listPs.add(DictCodeList.交易方向_卖空债券还本.getValue());
        /**modify by wujie on 20210107 end**/
     /*   listPs.add(DictCodeList.交易方向_卖空债券结息.getValue());
        listPs.add(DictCodeList.交易方向_卖空债券还本.getValue());*/
        params.put("listPs", listPs);
        params.put("genType", DictCodeListExt1.生成方式_批处理生成.getValue());
        params.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
        params.put("busiType26", DictCodeList.业务类型_拆借预交易.getValue());
        params.put("finprodType21", DictCodeListExt1.金融产品类型_同业借款.getValue());
        List<String> tradeList = assetIntdateCarryoverMapper.queryDelDeal(params);

        if (tradeList != null && tradeList.size() > 0) {
            //金融产品交易删除
            assetIntdateCarryoverMapper.delValFinProductDeal(tradeList);
            //估值核算交易扩展表删除
            assetIntdateCarryoverMapper.delValFinProductDealExt(tradeList);
            //入账明细表删除
            assetIntdateCarryoverMapper.delValDealAccounting(tradeList);
            //入账账务明细表删除
            assetIntdateCarryoverMapper.delValDealAccountingDetail(tradeList);
            //账务明细代码表删除
            assetIntdateCarryoverMapper.delBokDetailId(tradeList);
        }
    }

}

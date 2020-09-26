/*
 * 文件名：BookGenerateResultCommonWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryoverdeal.writer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dao.AssetIntdateCarryoverMapper;

/**
 * 资产理论结息日结转-STEP1
 * 
 * @author chengzhong
 * @version 1.0 2018年5月9日
 * @see AssetIntdateCarryoverDealDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDealDelWriter extends BaseWriter<NeedToWriteEntity> {

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
//        List<String> booksetIdList = (List<String>)this.getParams().get(GlobalConstant.BOOKSETS);
        
    	/**ADD BY WUJIE ON 20191229 START remark:只删除拆出生成的拆借预处理交易**/
    	Map<String,Object> parmasMap = this.getParams();
    	parmasMap.put("finprodType", DictCodeListExt1.金融产品类型_拆出.getValue());
    	/**ADD BY WUJIE ON 20191229 END**/
    	
    	
        List<String> tradeList = assetIntdateCarryoverMapper.queryDelDealTransaction(parmasMap);

        if (tradeList != null && tradeList.size() > 0) {
            //金融产品交易删除
            assetIntdateCarryoverMapper.delValFinProductDeal(tradeList);
            //估值核算交易扩展表删除
            assetIntdateCarryoverMapper.delValFinProductDealExt(tradeList);
            //入账明细表删除
            assetIntdateCarryoverMapper.delValDealAccounting(tradeList);
            //入账账务明细表删除
            assetIntdateCarryoverMapper.delValDealAccountingDetail(tradeList);
        }
    }

}

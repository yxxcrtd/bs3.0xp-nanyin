/*
 * 文件名：MoneyTaAutoCalDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 27, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.writer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.moneytaautocal.dao.MoneyTaAutoCalCommonMapper;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 27, 2019
 * @see MoneyTaAutoCalDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalDelWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private MoneyTaAutoCalCommonMapper moneyTaAutoCalCommonMapper;
    
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        
        Map<String, Object> map = Maps.newHashMap();
        map.put("taskId", this.getParams().get("taskId"));
        map.put("booksetDate", this.getParams().get(BookConstant.BOOKSET_DATE));
        map.put("happenDate", this.getParams().get(BookConstant.HAPPEN_DATE));
        map.put("bookDate", this.getParams().get(BookConstant.BOOK_DATE));
        map.put("genType", DictCodeListExt1.生成方式_批处理生成.getValue());
        map.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
        map.put("busiType", Arrays.asList(new String[]{DictCodeList.业务类型_货币型TA交易.getValue()}));
        map.put("seqNo", this.getParams().get("seqNo"));
        
        List<String> tradeList = moneyTaAutoCalCommonMapper.selectTradeId(map);
        
        if (CollectionUtils.isNotEmpty(tradeList)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("tradeList", tradeList);
            params.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
            
            // 金融产品扩展表
            moneyTaAutoCalCommonMapper.delTrdProductDeal(params);
            // 金融产品扩展表
            moneyTaAutoCalCommonMapper.delProductDealExt(params);
            // 入账明细扩展表
            moneyTaAutoCalCommonMapper.delBokDealAccounting(params);
            // 入账明细扩展表
            moneyTaAutoCalCommonMapper.delBokDealAccountingDetail(params);
        }
        
    }

}

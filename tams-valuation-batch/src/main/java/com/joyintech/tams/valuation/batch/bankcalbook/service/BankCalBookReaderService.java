/*
 * 文件名：BankCalBookReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.bankcalbook.dao.BankCalBookMapper;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BankCalBookComResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 银行账计提账务处理 ReaderService
 * 
 * @author zhangwei
 * @version 1.0 Apr 10, 2019
 * @see BankCalBookReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankCalBookReaderService {

    /**
     * 记账公共类 TODO
     */
//    @Autowired
//    private BookCommonLogic bookCommonLogic;

    @Autowired
    private BankCalBookMapper bankCalBookMapper;

    public List<BankCalBookComResult> execute(Map<String, Object> param) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("seqNo", param.get("seqNo"));
        map.put("booksetDate", param.get(BookConstant.BOOKSET_DATE));
        map.put("bookDate", param.get(BookConstant.BOOK_DATE));
        map.put("happenDate", param.get(BookConstant.HAPPEN_DATE));
        map.put("taskId", param.get("taskId"));
        map.put("booksetType", DictCodeList.账套类别_银行账套.getValue());

        // 1. 银行账账套处理
        // 查询对象
        BokBookset bokBookset = bankCalBookMapper.selectBankBookset(map);

        // 2. 处理对象查询
        // 产品列表 组装数据
        List<BankCalBookComResult> listResult = Lists.newArrayList();
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        map.put("profitFlag", DictCodeListExt1.收益标识_非保本浮动收益型.getValue());
        listResult = bankCalBookMapper.selectFinProduct(map);
        if (CollectionUtils.isNotEmpty(listResult)) {
            listResult.forEach(item -> {
                item.setBooksetId(bokBookset.getBooksetId());
                item.setBookDate((Date)param.get(BookConstant.BOOK_DATE));
                item.setHappenDate((Date)param.get(BookConstant.HAPPEN_DATE));
            });
        }
        
        return listResult;
    }

}

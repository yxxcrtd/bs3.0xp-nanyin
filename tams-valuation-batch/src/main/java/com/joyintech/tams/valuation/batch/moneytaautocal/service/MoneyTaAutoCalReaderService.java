/*
 * 文件名：MoneyTaAutoCalReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.moneytaautocal.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.moneytaautocal.dao.MoneyTaAutoCalCommonMapper;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.BokAcctLayInfoDto;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.BokBookDetilDto;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.MoneyTaAutoCalResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 货币型产品自动结转处理
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalReaderService {

    @Autowired
    private MoneyTaAutoCalCommonMapper moneyTaAutoCalReaderMapper;
    
    /**
     * {@inheritDoc}
     */
    public List<MoneyTaAutoCalResult> execute(Map<String, Object> param) {
        
        List<MoneyTaAutoCalResult> listResult = Lists.newArrayList();
        
        Map<String, Object> map = Maps.newHashMap();
        map.put("paramCode", ValDictCodeList.账套配置表参数项_货币型产品自动红利再投.getValue());
        map.put("paramValue", "02"); // 01：当日自动结转，不生成交易，02：次日生成交易，不自动结转，99：不处理
        map.put("taskId", param.get("taskId"));
        map.put("booksetDate", param.get(BookConstant.BOOKSET_DATE));
        map.put("happenDate", param.get(BookConstant.HAPPEN_DATE));
        map.put("bookDate", param.get(BookConstant.BOOK_DATE));
        map.put("seqNo", param.get("seqNo"));
        /**ADD BY WUJIE ON 20191112 START**/
        map.put("profitType", DictCodeList.收益类型_货币型.getValue());
        /**ADD BY WUJIE ON 20191112 END**/
        List<BokAcctLayInfoDto> listBokAcctLayInfoDto = moneyTaAutoCalReaderMapper.selectLayInfo(map);
        
        if (CollectionUtils.isEmpty(listBokAcctLayInfoDto)) {
            return listResult;
        }
        
        MoneyTaAutoCalResult result = new MoneyTaAutoCalResult();
//        result.setBooksetId(listBokAcctLayInfoDto.get(0).getBooksetId());
        result.setBooksetDate((Date)param.get(BookConstant.BOOKSET_DATE));
        result.setHappenDate((Date)param.get(BookConstant.HAPPEN_DATE));
        result.setBookDate((Date)param.get(BookConstant.BOOK_DATE));
//        result.setStandardCcy(listBokAcctLayInfoDto.get(0).getStandardCcy());
        List<BokBookDetilDto> listDto = listBokAcctLayInfoDto.stream().map(item -> {
            BokBookDetilDto newDto = new BokBookDetilDto();
            BeanUtils.copyProperties(item, newDto);
            return newDto;
        }).collect(Collectors.toList());
        result.setListBokBookDetilDto(listDto);
        
        listResult.add(result);
        
        return listResult;
    }
    
}

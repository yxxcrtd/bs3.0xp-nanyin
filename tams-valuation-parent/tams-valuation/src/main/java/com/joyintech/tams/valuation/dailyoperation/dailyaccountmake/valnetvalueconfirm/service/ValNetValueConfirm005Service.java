/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service;



import com.joyintech.framework.export.CommonExportService;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao.ValNetValueConfirm005Mapper;

import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm005Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ValNetValueConfirm005Service extends CommonExportService {
    @Autowired
    private ValNetValueConfirm005Mapper valNetValueConfirm005Mapper;


    public Page<SelectOptionDto> findProduct(int page, int length,Map<String,Object> param){
        return this.queryForPage(page, length,
                () -> valNetValueConfirm005Mapper.findProduct(param));
    }
        public List<ValNetValueConfirm005Dto> getInfo(Map<String,Object> map) {
            List<ValNetValueConfirm005Dto> list = valNetValueConfirm005Mapper.getInfo(map);
            return list;
        }
    public List<ValNetValueConfirm005Dto> getInfo1(Map<String,Object> map) {
        List<ValNetValueConfirm005Dto> list = valNetValueConfirm005Mapper.getInfo1(map);
        return list;
    }
    
}

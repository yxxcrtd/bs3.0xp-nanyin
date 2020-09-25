/*
 * 文件名：InterfNetBonusController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.i190011.entity.Interf190011OutEntity;
import com.joyintech.service.interf.i190011.service.Interf190011Service;
import com.joyintech.service.interf.ta.dto.SendBonusDto;

/**
 * 净值分红信息查询接口（190011）入口
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月21日
 * @see InterfNetBonusController
 * @since 1.0
 */
public class InterfNetBonusController {

    /**
     * 净值分红信息查询接口（190011）-详细处理
     */
    @Autowired
    private Interf190011Service interfService;

    @RequestMapping("/tzConsumer/ta/searchNetbonus")
    public List<SendBonusDto> searchNetbonus(@RequestBody SendBonusDto dto) {
        String uuid = IDGenerator.generate();
        Map<String, Object> param = new HashMap<>();
        param.put(InterfConstant.UUID, uuid);
        param.put(InterfConstant.PROD_INFO, dto);
        // 返回结果
        return doResult(dto, interfService.sendRequest(param));
    }

    /**
     * 处理返回画面的结果
     * 
     * @param dto 查询条件
     * @param entity 查询结果
     * @return 返回画面的结果
     */
    private List<SendBonusDto> doResult(SendBonusDto dto, Interf190011OutEntity entity) {
        List<SendBonusDto> resultList = new ArrayList<>();
        if (!interfService.isHandleOK()) {
            dto.setHandleOK(false);
            dto.setErrMsg(interfService.getErrMsg());
            resultList.add(dto);
        }
        else {
            for (Interf190011OutEntity item : entity.getArrList()) {
                // 销售商代码
                item.setVendorCode(dto.getVendorCode());
                // 查询起始日
                item.setQueryStartDate(dto.getQueryStartDate());
                // 查询起始日
                item.setQueryEndDate(dto.getQueryEndDate());
                // 产品代码
                item.setProdId(entity.getProdId());
                // 母产品代码
                item.setmProdId(entity.getmProdId());
                // 产品期次
                item.setProdBatchNo(entity.getProdBatchNo());
                // 页码
                item.setPageNo(entity.getPageNo());
                // 页大小
                item.setPageSize(entity.getPageSize());
                SendBonusDto resultDto = new SendBonusDto();
                BeanUtil.copyPropertiesIgnoreNull(resultDto, item);
                resultList.add(resultDto);
            }
        }

        return resultList;
    }
}

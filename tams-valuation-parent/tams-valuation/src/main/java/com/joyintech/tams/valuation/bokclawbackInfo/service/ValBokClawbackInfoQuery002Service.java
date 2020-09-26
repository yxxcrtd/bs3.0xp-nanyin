/*
 * 文件名：ValBokClawbackInfoQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.bokclawbackInfo.dao.ValBokClawbackInfoQuery002Mapper;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery002Dto;

/**
 * 回补信息查询
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery002Service
 * @since 1.0
 */
@Service
public class ValBokClawbackInfoQuery002Service extends BaseService<ValBokClawbackInfoQuery002Dto> {

    @Autowired
    private ValBokClawbackInfoQuery002Mapper valBokClawbackInfoQuery002Mapper;

    /**
     * 查询记账凭证
     * @param pageParam
     * @return
     */
    public ValBokClawbackInfoQuery002Dto findBokVouch(Map<String, Object> map) {
        return valBokClawbackInfoQuery002Mapper.selectBokVouch(map);
    }

}

/*
 * 文件名：ValBokClawbackInfoQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.bokclawbackInfo.dao.ValBokClawbackInfoQuery001Mapper;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 回补信息查询
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery001Service
 * @since 1.0
 */
@Service
public class ValBokClawbackInfoQuery001Service extends BaseService<ValBokClawbackInfoQuery001Dto> {

    @Autowired
    private ValBokClawbackInfoQuery001Mapper valBokClawbackInfoQuery001Mapper;

    /**
     * 检索账套代码列表（
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId(Map<String, Object> map) {
        return valBokClawbackInfoQuery001Mapper.getBooksetId(map);
    }
    
    /**
     * 分页查询回补信息
     * @param map
     * @return
     * @throws Exception
     */
    public Page<ValBokClawbackInfoQuery001Dto> findPage(Map<String, Object> map)
        throws Exception {
    	map.put("busiType", DictCodeList.业务类型_产品回补交易.getValue());
    	map.put("anapleroticType02", DictCodeList.回补方式_自动回补.getValue());
    	map.put("anapleroticType01", DictCodeList.回补方式_手动回补.getValue());
        return super.queryForPage(() -> valBokClawbackInfoQuery001Mapper.selectBokClawbackInfo(map));
    }

}

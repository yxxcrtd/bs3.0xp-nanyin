/*
 * 文件名：TrdRepoTradeService003.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.service;

import java.util.Map;

import com.joyintech.service.common.select.dao.BussChannelCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 通道共通组件-微服务Service
 */
@SuppressWarnings("rawtypes")
@Service
public class BussChannelCommonService  extends BaseService {

    @Autowired
    private BussChannelCommonMapper bussChannelCommonMapper;

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getChannelId(int page, int length, Map<String, Object> params) {
        params.put("Y", DictCodeList.是否_是.getValue());
        params.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return super.queryForPageForSelectReturn(page, length, ()->bussChannelCommonMapper.getChannelId(params));
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getYcxhkChannelId(int page, int length, Map<String, Object> params) {
        params.put("Y", DictCodeList.是否_是.getValue());
        params.put("HKFS_YCXHK", DictCodeListExt2.划款方式_一次性划款.getValue());
        params.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return super.queryForPageForSelectReturn(page, length, ()->bussChannelCommonMapper.getYcxhkChannelId(params));
    }

	@SuppressWarnings("unchecked")
	public Page<SelectOptionDto> getChannelIdAndCollectType(int page, int length, Map<String, Object> params) {
		params.put("Y", DictCodeList.是否_是.getValue());
        params.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return super.queryForPageForSelectReturn(page, length, ()->bussChannelCommonMapper.getChannelIdAndCollectType(params));
	}
}

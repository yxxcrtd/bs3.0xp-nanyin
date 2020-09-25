/*
 * 文件名：BussEtrInvestPersonInfoImplService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年10月18日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.impl;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.trade.BussChannelCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通道相关共通
 * 
 * @author Johnnie
 * @version 1.0 2019年3月26日
 * @see BussChannelCommonImplService
 * @since 1.0
 */
@Service
public class BussChannelCommonImplService implements BussChannelCommonService {

    @Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public Page<SelectOptionDto> getChannelId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
            pageParam, SelectOptionDto.class);
    }

    @Override
    public List<SelectOptionDto> getChannelIdForAsset(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getChannelIdForAsset",
            pageParam, SelectOptionDto.class);
    }

    @Override
    public Page<SelectOptionDto> getYcxhkChannelId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getYcxhkChannelId",
            pageParam, SelectOptionDto.class);
    }

	@Override
	public Page<SelectOptionDto> getChannelIdAndCollectType(Map<String, Object> pageParam) {
		return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelIdAndCollectType",
	            pageParam, SelectOptionDto.class);
	}

	@Override
	public List<SelectOptionDto> getChannelIdAndCollectTypeForAsset(Map<String, Object> pageParam) {
		return restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getChannelIdAndCollectTypeForAsset",
	            pageParam, SelectOptionDto.class);
	}

    @Override
    public Page<SelectOptionDto> getSecManageAcctId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getSecManageAcctId",
                pageParam, SelectOptionDto.class);
    }
}

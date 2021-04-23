/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrexchangetrade.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade002Dto;

/**
 *  交易所指令交易（债券股票回购基金场内指令交易）
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrExchangeTrade002Service
 * @since 1.0
 */
@Service
public class EtrExchangeTrade002Service extends CommonExportService<EtrExchangeTrade002Dto> {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param param 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
	public Page<EtrExchangeTrade002Dto> findPositionsPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findPositionsPage", param,
						EtrExchangeTrade002Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	/**
	 * 分页查询债券质押持仓信息
	 * 
	 * @param param
	 *            查询参数
	 * @return 分页结果
	 * @see
	 * @since 1.0
	 */
	public Page<EtrExchangeTrade002Dto> findZqzyPositionsPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findZqzyPositionsPage", param,
						EtrExchangeTrade002Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

    /**
     * 查询证券代码联想框文本框
     *
     * @param parameters 画面参数
     * @return 证券代码联想框文本框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSecAcctId(Map<String, Object> parameters) {
        Page<SelectOptionDto> tempList = restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/getSecAcctId",
            parameters, SelectOptionDto.class);
        return tempList;
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @param pageParam 页面参数
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    public Map<String, String> loadSecNum(Map<String, Object> pageParam) {
        Map<String, String> map = (Map<String, String>)restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/loadSecNum",
                pageParam, Map.class);
        return map;
    }
}

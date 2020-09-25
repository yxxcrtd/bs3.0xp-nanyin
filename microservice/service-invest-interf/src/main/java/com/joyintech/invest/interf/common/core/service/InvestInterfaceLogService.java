/*
 * 文件名：InterfCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月17日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.invest.interf.common.core.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.invest.interf.common.core.dao.InvestInterfStsMapper;
import com.joyintech.invest.interf.common.core.entity.InterfStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 * 接口共通
 * 
 * @author 王波
 * @version 1.0 2020年1月17日
 * @see InvestInterfaceLogService
 * @since 1.0
 */
@Service
public class InvestInterfaceLogService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InvestInterfStsMapper investInterfStsMapper;

    /**
     * restTemplate
     */
    @Autowired
    private JoyinRestTemplate restTemplate;


	/**
	 * @ 获取日志列表
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<InterfStatus> selectForPage(int page, int length, Map<String, Object> dataMap) {
		return super.queryForPage(page, length, () -> investInterfStsMapper.getLogList(dataMap));
	}
}

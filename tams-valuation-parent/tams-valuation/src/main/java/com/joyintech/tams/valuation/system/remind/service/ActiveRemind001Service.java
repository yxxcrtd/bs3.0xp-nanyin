/*
 * 文件名：ActiveRemind001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ww
 * 修改时间：2019年02月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.system.remind.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.service.ActiveReminderSubService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.system.remind.dao.ActiveRemind001Mapper;
import com.joyintech.tams.valuation.system.remind.dto.ActiveRemind001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ww
 * @version 1.0 2019年02月27日
 * @see ActiveRemind001Service
 * @since 1.0
 */
@Service
public class ActiveRemind001Service extends BaseService<ActiveRemind001Dto> implements ActiveReminderSubService {

    /**
     * 获取mapper对象
     */
    @Autowired
    private ActiveRemind001Mapper activeRemind001Mapper;
    /**
     * 
     * 查询所有提醒条数
     * <功能详细描述>
     * @param param 
     * @return count
     * @see
     * @since 1.0
     */
    @Override
	public int getAllCountOfData() {
		Map<String, Object> param = new HashMap<>();
		return  activeRemind001Mapper.findDetailCount(null);
	}
    
    /**
     * 
     * 查询所有提醒
     * <功能详细描述>
     * @param param 
     * @return page<ActiveRemind001Dto>yhc
     * @see
     * @since 1.0
     */
	public Page<ActiveRemind001Dto> findDetail(Map<String, Object> param) throws Exception {
		 return super.queryForPage(ActiveRemind001Mapper.class, "findDetail", param);
	}


	
}

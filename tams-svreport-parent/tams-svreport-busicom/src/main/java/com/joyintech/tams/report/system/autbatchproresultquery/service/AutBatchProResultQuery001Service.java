/*
 * 文件名：AutBatchProResultQuery001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.autbatchproresultquery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.system.autbatchproresultquery.dao.AutBatchProResultQuery001Mapper;
import com.joyintech.tams.report.system.autbatchproresultquery.dto.AutBatchProResultQuery001Dto;

/**
 * 自动报送批处理结果查询Service
 * @author zuoyinyi
 * @version 1.0 2017年11月14日
 * @see AutBatchProResultQuery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AutBatchProResultQuery001Service extends BaseService<AutBatchProResultQuery001Dto>{
	@Autowired
	private AutBatchProResultQuery001Mapper autBatchProResultQuery001Mapper;
    /**
     * 查询自动批处理日志结果
     * @param map 查询条件
     * @return 结果列表（分页）
     * @see
     * @since 1.0
     */
    public Page<AutBatchProResultQuery001Dto> queryBatchProResult(Map<String, Object> map){
    	int pagenum = Integer.parseInt(map.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(map.get(GlobalConstant.PARAM_LENGTH).toString());
    	Page<AutBatchProResultQuery001Dto> page=super.queryForPage(
    			pagenum,legth,()->autBatchProResultQuery001Mapper.queryBatchProResult(map));
    	for (AutBatchProResultQuery001Dto dto : page.getRoot()) {
    		dto.setTaskName(BatchBeanName.getBatchNameByTaskId(dto.getTaskId()));			
		}
        return page;
       
    }

}

/*
 * 文件名：ValBooksetDateSwitch003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dao.ValBooksetDateSwitch003Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch003Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 账套日期管理 - 批量修改
 * 
 * @author zhangwei
 * @version 1.0 2019年2月18日
 * @see ValBooksetDateSwitch003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValBooksetDateSwitch003Service extends CommonExportService<ValBooksetDateSwitch003Dto> implements BaseProcessService<ValBooksetDateSwitch003Dto> {

    @Autowired
    private ValBooksetDateSwitch003Mapper mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public List<ValBooksetDateSwitch003Dto> findList(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBooksetDateSwitch003/findList", params, ValBooksetDateSwitch003Dto.class);
//        return mapper.queryPageList(params);
    }

    @Override
    public void doComplete(ValBooksetDateSwitch003Dto entity, OperationType type) {
       
        if (OperationType.MODIFY.equals(type)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBooksetDateSwitch003/modify", entity, Integer.class);
//            mapper.updateByPrimaryKeySelective(entity);
        }
    }

}

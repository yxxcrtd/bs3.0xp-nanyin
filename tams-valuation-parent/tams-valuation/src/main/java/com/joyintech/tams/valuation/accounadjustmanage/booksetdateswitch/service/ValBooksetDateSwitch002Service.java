/*
 * 文件名：CustomerReportConfig002Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.system.customerreport.config.service.CustomerReportConfig002Service;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 报表定义新建Service
 * 
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValBooksetDateSwitch002Service extends CommonExportService<ValBooksetDateSwitch002Dto> implements BaseProcessService<ValBooksetDateSwitch002Dto> {

    /**
     * CustomerReportConfig002Mapper
     */
    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    @Override
    public void doComplete(ValBooksetDateSwitch002Dto entity, OperationType type) {
        // 获取账套日期
        // BokBooksetDate valBokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(
        // entity.getBooksetId());
        // // 为空不更新
        // if (valBokBooksetDate == null) {
        // return;
        // }
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBooksetDateSwitch002/update" , entity,
            Integer.class);
//        BokBooksetDate valBokBooksetDate = new BokBooksetDate();
//        valBokBooksetDate.setBooksetId(entity.getBooksetId());
//        valBokBooksetDate.setCreateUser(entity.getCreateUser());
//        valBokBooksetDate.setUpdateTime(entity.getUpdateTime());
//        valBokBooksetDate.setUpdateUser(entity.getUpdateUser());
//        // 之前已经校验过是否为空，此处不需再次校验
//        valBokBooksetDate.setBooksetDate(DateUtil.parse(entity.getBooksetDate()));
//        bokBooksetDateMapper.updateByPrimaryKey(valBokBooksetDate);
    }

}

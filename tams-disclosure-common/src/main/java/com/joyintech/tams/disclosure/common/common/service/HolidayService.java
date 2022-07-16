/*
 * 文件名：HolidayService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：杨智
 * 修改时间：2018年2月6日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.common.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.disclosure.common.common.dao.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service("disHolidayService")
@Transactional
public class HolidayService extends BaseService {

    @Autowired
    private HolidayMapper holidayMapper;

    public Date getLastWorkDay(Map<String, Object> params) {
        return holidayMapper.getLastWorkDay(params);
    }

    public Date getNextWorkDay(Map<String, Object> params) {
        return holidayMapper.getNextWorkDay(params);
    }
}

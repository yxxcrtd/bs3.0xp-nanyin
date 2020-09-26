package com.joyintech.tams.svreport.interf.dis.calendarmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.dis.common.DisFileInterfCommonService;


/**
 * 日历管理信息数据，外部数据文件接口入口定义类
 * 
 * @author ChuFengJie
 * @version 1.0 2019年5月8日
 * @see DisRepCashYieldFileInterfService
 * @since 1.0
 */
@Service
public class DisCalendarManagementFileInterfService extends AbstractFileImpService {
    
    @Autowired
    private DisCalendarManagementService disCalendarManagementService;
    
    @Autowired
    private DisFileInterfCommonService disFileInterfCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoByInterf(String currentDate) {
        String successFlag = disFileInterfCommonService.readFileAndImportDb(currentDate, disCalendarManagementService, InterfDefine.日历管理信息数据
        		, DictCodeListExt2.上游接口文件类型_日历管理信息);
        return successFlag;
    }
}


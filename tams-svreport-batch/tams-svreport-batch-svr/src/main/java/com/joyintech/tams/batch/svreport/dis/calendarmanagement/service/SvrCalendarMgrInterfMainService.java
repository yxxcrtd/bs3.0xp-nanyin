/*
 * 文件名：SvrCalendarMgrInterfMainService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月13日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */

package com.joyintech.tams.batch.svreport.dis.calendarmanagement.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.svreport.interf.dis.calendarmanagement.service.DisCalendarManagementFileInterfService;

/**
 * 日历管理信息数据接入批处理
 * 
 * @author ZhangYangFei
 * @version 1.0 2019年5月13日
 * @see SvrCalendarMgrInterfMainService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SvrCalendarMgrInterfMainService extends AutoSendCBBaseService {
    
    @Autowired
    private DisCalendarManagementFileInterfService disCalendarManagementFileInterfService;

    /**
     * 接入数据
     * @param batchDate
     * @param prodIdList
     * @return
     * @see
     * @since 1.0
     */
    public HashMap<String, List<String>> generateData(Date batchDate,List<String> prodIdList) {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.日历管理信息数据接入);
        logInfoBatchStart();
        HashMap<String, List<String>> mapPara = new HashMap<String, List<String>>();
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.日历管理信息数据.getCode());
        if (interfConfig == null) {
            logInfo("CHB_INTERF_CONFIG表中无配置项！");
        }
        String contentSrc = interfConfig.getContentSrc();
        // 内容来源-文件
        if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            String currentDate = DateUtil.format(batchDate);
            disCalendarManagementFileInterfService.getInfoByInterf(currentDate);
        }
        logInfoBatchEnd();
        return mapPara;
    }
}

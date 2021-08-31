/*
 * 文件名：InterfLogUtil.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年09月29日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.util;

import cn.hutool.core.bean.BeanUtil;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.InterfLog;
import org.n3r.idworker.Sid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2020年09月29日
 * @see InterfLogUtil
 * @since 1.0
 */
public class InterfLogUtil {

    /**
     * 生成日志
     *
     * @param log 日志信息
     * @param interfType 接口类型
     * @param taskStatus 接口状态
     * @param isWarning 是否需要预警
     * @param errMessage 错误信息
     * @param flag 结束标识
     * @return
     * @see
     * @since 1.0
     */
    public static List<InterfLog> createLogList(InterfLog log, String interfType, String taskStatus,
                                          String isWarning, String errMessage, Boolean flag) {
        List<InterfLog> interfLogList = new ArrayList<InterfLog>();

        InterfLog newLog = new InterfLog();
        BeanUtil.copyProperties(log, newLog);
        // 接口流水号
        newLog.setLogId(Sid.nextShort(20));
        newLog.setCreateTime(new Date());
        newLog.setTaskStatus(taskStatus);
        newLog.setIsneedwarning(isWarning);
        newLog.setInterfType(interfType);
        newLog.setErrMessage(errMessage);
        interfLogList.add(newLog);

        if (flag) {
            InterfLog endLog = new InterfLog();
            BeanUtil.copyProperties(newLog, endLog);
            endLog.setLogId(Sid.nextShort(20));
            endLog.setCreateTime(new Date());
            endLog.setInterfType(DictCodeList.接口类型_结束.getValue());
            interfLogList.add(endLog);
        }

        return interfLogList;
    }
}

/*
 * 文件名：SettleTransResultDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月06日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.basicdata.dataimport.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleTransResult;
import com.joyintech.tams.common.base.entity.SettleTransResultWithBLOBs;
import com.joyintech.tams.constant.DictCodeListStl;

/**
 * @author 王波
 * @date 2020年08月06日
 * @see SettleTransResultDto
 * @since 1.0
 */
public class SettleTransResultDto extends SettleTransResultWithBLOBs {
    // 数据记录
    private String execMsg;

    // 业务日期
    private String importDateFmt;

    // 操作日期
    private String createTimeFmt;

    // 开始时间
    private String startTimeFmt;

    // 结束时间
    private String endTimeFmt;

    // 耗时
    private Long lastTime;

    public String getStartTimeFmt() {
        return DateUtil.format(getStartTime(), "HH:mm:ss SSS");
    }

    public void setStartTimeFmt(String startTimeFmt) {
        this.startTimeFmt = startTimeFmt;
    }

    public String getEndTimeFmt() {
        return DateUtil.format(getEndTime(), "HH:mm:ss SSS");
    }

    public void setEndTimeFmt(String endTimeFmt) {
        this.endTimeFmt = endTimeFmt;
    }

    public Long getLastTime() {
        if (getEndTime()!= null && getStartTime() != null) {
            return (getEndTime().getTime() - getStartTime().getTime())/1000;
        }
        return null;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public String getCreateTimeFmt() {
        return DateUtil.format(getCreateTime(), DateUtil.DEFAULT_DATE_FORMAT_EN);
    }

    public void setCreateTimeFmt(String createTimeFmt) {
        this.createTimeFmt = createTimeFmt;
    }

    public String getImportDateFmt() {
        return DateUtil.format(getImportDate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
    }

    public void setImportDateFmt(String importDateFmt) {
        this.importDateFmt = importDateFmt;
    }

    public String getExecMsg() {
        if (StringUtil.equals(getTaskResult(), DictCodeListStl.批处理执行结果_正常结束.getValue())) {
            return getSuccessMsg();
        }
        else if (StringUtil.equals(getTaskResult(), DictCodeListStl.批处理执行结果_异常结束.getValue())) {
            return getFailureMsg();
        }
        else if (StringUtil.equals(getTaskResult(), DictCodeListStl.批处理执行结果_警告.getValue())) {
            return getWarningMsg();
        }
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }
}

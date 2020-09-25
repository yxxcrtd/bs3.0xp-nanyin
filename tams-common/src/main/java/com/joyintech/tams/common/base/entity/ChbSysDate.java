package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbSysDate extends BaseEntity {
    /**
     * 系统时间
     */
    private Date curDate;

    /**
     * 取得 系统时间
     * @return 系统时间
     */
    public Date getCurDate() {
        return curDate;
    }

    /**
     * 设置 系统时间
     * @param curDate 系统时间
     */
    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
}
package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokAcctLayeringInfo extends BokAcctLayeringInfoKey {
    /**
     * 分层名称
     */
    private String layeringName;

    /**
     * null
     */
    private String fLayeringId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 级别序号
     */
    private String lev;

    /**
     * 当前级别
     */
    private Long curLev;

    /**
     * 下级最新序号
     */
    private Long sBranch;

    /**
     * 取得 分层名称
     * @return 分层名称
     */
    public String getLayeringName() {
        return layeringName;
    }

    /**
     * 设置 分层名称
     * @param layeringName 分层名称
     */
    public void setLayeringName(String layeringName) {
        this.layeringName = layeringName == null ? null : layeringName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getfLayeringId() {
        return fLayeringId;
    }

    /**
     * 设置 null
     * @param fLayeringId null
     */
    public void setfLayeringId(String fLayeringId) {
        this.fLayeringId = fLayeringId == null ? null : fLayeringId.trim();
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 级别序号
     * @return 级别序号
     */
    public String getLev() {
        return lev;
    }

    /**
     * 设置 级别序号
     * @param lev 级别序号
     */
    public void setLev(String lev) {
        this.lev = lev == null ? null : lev.trim();
    }

    /**
     * 取得 当前级别
     * @return 当前级别
     */
    public Long getCurLev() {
        return curLev;
    }

    /**
     * 设置 当前级别
     * @param curLev 当前级别
     */
    public void setCurLev(Long curLev) {
        this.curLev = curLev;
    }

    /**
     * 取得 下级最新序号
     * @return 下级最新序号
     */
    public Long getsBranch() {
        return sBranch;
    }

    /**
     * 设置 下级最新序号
     * @param sBranch 下级最新序号
     */
    public void setsBranch(Long sBranch) {
        this.sBranch = sBranch;
    }
}
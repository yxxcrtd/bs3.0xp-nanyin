package com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dto;

import com.joyintech.framework.base.BaseEntity;

public class RepTaskDeatil extends BaseEntity {
    /**
     * 定时日期
     */
    private String taskdate;

    /**
     * 定时状态,0运行,1待运行
     */
    private String taskflag;

    /**
     * null
     */
    private String flag;

    /**
     * 取得 定时日期
     * @return 定时日期
     */
    public String getTaskdate() {
        return taskdate;
    }

    /**
     * 设置 定时日期
     * @param taskdate 定时日期
     */
    public void setTaskdate(String taskdate) {
        this.taskdate = taskdate == null ? null : taskdate.trim();
    }

    /**
     * 取得 定时状态,0运行,1待运行
     * @return 定时状态,0运行,1待运行
     */
    public String getTaskflag() {
        return taskflag;
    }

    /**
     * 设置 定时状态,0运行,1待运行
     * @param taskflag 定时状态,0运行,1待运行
     */
    public void setTaskflag(String taskflag) {
        this.taskflag = taskflag == null ? null : taskflag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 null
     * @param flag null
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}
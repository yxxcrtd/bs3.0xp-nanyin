package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskIsupFixRecode extends RskIsupFixRecodeKey {
    /**
     * 主动提醒日期
     */
    private Date notifyDate;

    /**
     * 固定值
     */
    private String content;
 
    /**
     * 取得 主动提醒日期
     * @return 主动提醒日期
     */
    public Date getNotifyDate() {
        return notifyDate;
    }

    /**
     * 设置 主动提醒日期
     * @param notifyDate 主动提醒日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    /**
     * 取得 固定值
     * @return 固定值
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 固定值
     * @param content 固定值
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
  
}
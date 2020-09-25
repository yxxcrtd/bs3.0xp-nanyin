package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class MstDates extends BaseEntity {
    /**
     * null
     */
    private Date dates;

    /**
     * 取得 null
     * @return null
     */
    public Date getDates() {
        return dates;
    }

    /**
     * 设置 null
     * @param dates null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDates(Date dates) {
        this.dates = dates;
    }
}
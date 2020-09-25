package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstValOpKey extends BaseEntity {
    /**
     * null
     */
    private String finprodId2;

    /**
     * null
     */
    private Date valDate;

    /**
     * 取得 null
     * @return null
     */
    public String getFinprodId2() {
        return finprodId2;
    }

    /**
     * 设置 null
     * @param finprodId2 null
     */
    public void setFinprodId2(String finprodId2) {
        this.finprodId2 = finprodId2 == null ? null : finprodId2.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 null
     * @param valDate null
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }
}
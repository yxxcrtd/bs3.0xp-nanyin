package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbMstExchangeInfoKey extends BaseEntity {
    /**
     * 原币种
     */
    private String ccyOriginal;

    /**
     * 折算币种
     */
    private String ccyConvert;

    /**
     * 公布日期
     */
    private Date publishDate;

    /**
     * 取得 原币种
     * @return 原币种
     */
    public String getCcyOriginal() {
        return ccyOriginal;
    }

    /**
     * 设置 原币种
     * @param ccyOriginal 原币种
     */
    public void setCcyOriginal(String ccyOriginal) {
        this.ccyOriginal = ccyOriginal == null ? null : ccyOriginal.trim();
    }

    /**
     * 取得 折算币种
     * @return 折算币种
     */
    public String getCcyConvert() {
        return ccyConvert;
    }

    /**
     * 设置 折算币种
     * @param ccyConvert 折算币种
     */
    public void setCcyConvert(String ccyConvert) {
        this.ccyConvert = ccyConvert == null ? null : ccyConvert.trim();
    }

    /**
     * 取得 公布日期
     * @return 公布日期
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置 公布日期
     * @param publishDate 公布日期
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
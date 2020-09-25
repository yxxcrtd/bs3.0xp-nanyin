package com.joyintech.service.valuation.bokdetailidupdate.dto;


import com.joyintech.framework.base.BaseEntity;

/**
 * @author tanxuan
 * @createTime 2019-10-10
 */
public class BokDetailIdUpdate002Dto extends BaseEntity {

    /**
     * 原账务明细代码
     */
    private String oldBokDetailId;
    
    /**
     * 新账务明细代码
     */
    private String  newBokDetailId;
    
    /**
     * 原账务明细代码
     */
    private String booksetId;

    /**
     * 取得 原账务明细代码
     * @return 原账务明细代码
     */
    public String getOldBokDetailId() {
        return oldBokDetailId;
    }

    /**
     * 设置 原账务明细代码
     * @param oldBokDetailId 原账务明细代码
     */
    public void setOldBokDetailId(String oldBokDetailId) {
        this.oldBokDetailId = oldBokDetailId == null ? null : oldBokDetailId.trim();
    }
    
    /**
     * 取得 新账务明细代码
     * @return 新账务明细代码
     */
    public String getNewBokDetailId() {
        return newBokDetailId;
    }

    /**
     * 设置 新账务明细代码
     * @param newBokDetailId 新账务明细代码
     */
    public void setNewBokDetailId(String newBokDetailId) {
        this.newBokDetailId = newBokDetailId == null ? null : newBokDetailId.trim();
    }
    
    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param oldBokDetailId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }
}

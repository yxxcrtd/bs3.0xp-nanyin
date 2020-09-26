/*
 * 文件名：SubjectBalanceKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 科目余额KEY
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see SubjectBalanceKey
 * @since 2.0
 */
public class SubjectBalanceKey extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    /**
     * 账套代码
     */
    private String booksetId;
//    /**
//     * 明细科目号
//     */
//    private String detailSubjectNo;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 原币
     */
    private String oCcy;
    /**
     * 发生日
     */
    private String happenDate;
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((booksetId == null) ? 0 : booksetId.hashCode());
//        result = prime * result + ((detailSubjectNo == null) ? 0 : detailSubjectNo.hashCode());
        result = prime * result + ((bokDetailId == null) ? 0 : bokDetailId.hashCode());
        result = prime * result + ((happenDate == null) ? 0 : happenDate.hashCode());
        result = prime * result + ((oCcy == null) ? 0 : oCcy.hashCode());
        return result;
    }
    /**
     * 重写equals方法，map中直接key比对时需要重写
     */
    @Override
    public boolean equals(Object obj) {
        boolean flag = true;
        //代替return过多，用do while做处理,循环一次
        do {
            if (this == obj){
                flag = true;
                break;
            }
                
            if (obj == null){
                flag = false;
                break;
            }
                
            if (getClass() != obj.getClass()){
                flag = false;
                break;
            }
               
            SubjectBalanceKey other = (SubjectBalanceKey) obj;
            if (booksetId == null) {
                if (other.booksetId != null){
                    flag = false;
                    break;
                }
                   
            } 
            else if (!booksetId.equals(other.booksetId)){
                flag = false;
                break;
            }
                
            if (bokDetailId == null) {
                if (other.bokDetailId != null){
                    flag = false;
                    break;
                }
            } 
            else if (!bokDetailId.equals(other.bokDetailId)){
                flag = false;
                break;
            }
               
            if (happenDate == null) {
                if (other.happenDate != null){
                    flag = false;
                    break;
                }
                    
            } 
            else if (!happenDate.equals(other.happenDate)){
                flag = false;
                break;
            }
               
            if (oCcy == null) {
                if (other.oCcy != null){
                    flag = false;
                    break;
                }
                    
            } 
            else if (!oCcy.equals(other.oCcy)){
                flag = false;
                break;
            }
        } while (false);
        return flag;
    }
    /**
     * 获取  账套代码
     * @return  账套代码.
     */
    public String getBooksetId() {
        return booksetId;
    }
    /**
     * 设置 账套代码
     * @param booksetId  账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
//    /**
//     * 获取 明细科目号
//     * @return 明细科目号.
//     */
//    public String getDetailSubjectNo() {
//        return detailSubjectNo;
//    }
//    /**
//     * 设置 明细科目号
//     * @param detailSubjectNo 明细科目号
//     */
//    public void setDetailSubjectNo(String detailSubjectNo) {
//        this.detailSubjectNo = detailSubjectNo;
//    }
    /**
     * 取得 原币
     * 
     * @return 原币
     */
    public String getOCcy() {
        return oCcy;
    }

    /**
     * 设置 原币
     * 
     * @param oCcy
     *            原币
     */
    public void setOCcy(String oCcy) {
        this.oCcy = oCcy;
    }
    
    /**
     * 获取 发生日期
     * @return 发生日期.
     */
    public String getHappenDate() {
        return happenDate;
    }
    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }
    
    /**
     * 获取 账务明细代码
     * @return 账务明细代码.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }
    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

}

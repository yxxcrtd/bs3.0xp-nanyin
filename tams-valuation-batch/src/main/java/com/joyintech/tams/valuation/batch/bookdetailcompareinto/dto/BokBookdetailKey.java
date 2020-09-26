/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * key
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BokBookdetailKey
 * @since 1.0
 */
public class BokBookdetailKey extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 交易编号
     */
    private String assetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 分支序号，无分支序号的存0
     */
    private Long branch;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 原币
     */
    private String oCcy;


    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 assetId
     * 
     * @return assetId.
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 assetId
     * 
     * @param assetId assetId
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * 获取 happenDate
     * 
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 happenDate
     * 
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取 bokDetailId
     * 
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 bokDetailId
     * 
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * 获取 tableId
     * 
     * @return tableId.
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 tableId
     * 
     * @param tableId tableId
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 baseSubjectNature
     * 
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 baseSubjectNature
     * 
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     * 获取 cdFlag
     * 
     * @return cdFlag.
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 cdFlag
     * 
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    
    
    /**
     * 获取 bookId
     * @return bookId.
     */
    public String getBookId() {
        return bookId;
    }

    
    /**
     * 设置 bookId
     * @param bookId bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getoCcy() {
        return oCcy;
    }

    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((booksetId == null) ? 0 : booksetId.hashCode());
        result = prime * result + ((happenDate == null) ? 0 : happenDate.hashCode());
        result = prime * result + ((bokDetailId == null) ? 0 : bokDetailId.hashCode());
        result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
        result = prime * result + ((branch == null) ? 0 : branch.hashCode());
        result = prime * result + ((assetId == null) ? 0 : assetId.hashCode());
        result = prime * result + ((baseSubjectNature == null) ? 0 : baseSubjectNature.hashCode());
        result = prime * result + ((cdFlag == null) ? 0 : cdFlag.hashCode());
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((oCcy == null) ? 0 : oCcy.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BokBookdetailKey other = (BokBookdetailKey)obj;
        if (booksetId == null) {
            if (other.booksetId != null)
                return false;
        }
        else if (!booksetId.equals(other.booksetId))
            return false;
        if (happenDate == null) {
            if (other.happenDate != null)
                return false;
        }
        else if (!happenDate.equals(other.happenDate))
            return false;
        if (bokDetailId == null) {
            if (other.bokDetailId != null)
                return false;
        }
        else if (!bokDetailId.equals(other.bokDetailId))
            return false;
        if (tableId == null) {
            if (other.tableId != null)
                return false;
        }
        else if (!tableId.equals(other.tableId))
            return false;
        if (branch == null) {
            if (other.branch != null)
                return false;
        }
        else if (!branch.equals(other.branch))
            return false;
        if (assetId == null) {
            if (other.assetId != null)
                return false;
        }
        else if (!assetId.equals(other.assetId))
            return false;
        if (baseSubjectNature == null) {
            if (other.baseSubjectNature != null)
                return false;
        }
        else if (!baseSubjectNature.equals(other.baseSubjectNature))
            return false;
        if (cdFlag == null) {
            if (other.cdFlag != null)
                return false;
        }
        else if (!cdFlag.equals(other.cdFlag))
            return false;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        }
        else if (!bookId.equals(other.bookId))
            return false;
        if (oCcy == null) {
            if (other.oCcy != null)
                return false;
        }
        else if (!oCcy.equals(other.oCcy))
            return false;
        return true;
    }


}

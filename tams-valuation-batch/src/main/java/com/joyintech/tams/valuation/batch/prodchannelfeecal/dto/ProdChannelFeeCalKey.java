/*
 * 文件名：FinProductDealInfoKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

/**
 * 理财产品通道费key 〈功能详细描述〉
 *
 * @author chengzhong
 * @version 1.0 2018年5月5日
 * @see ProdChannelFeeCalKey
 * @since 1.0
 */
public class ProdChannelFeeCalKey {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 账套日期
     */
    private Date booksetDate;
    
    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 分支序号
     */
    private Long branch;
    
   /**
    * 账务明细代码
    */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;


	/**
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
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
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
    
    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy == null ? null : standardCcy.trim();
    }
    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }
    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 bokDetailId
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 bokDetailId
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((booksetId == null) ? 0 : booksetId.hashCode());
        result = prime * result + ((happenDate == null) ? 0 : happenDate.hashCode());
        result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
        result = prime * result + ((booksetDate == null) ? 0 : booksetDate.hashCode());
        result = prime * result + ((bookDate == null) ? 0 : bookDate.hashCode());
        result = prime * result + ((standardCcy == null) ? 0 : standardCcy.hashCode());
        result = prime * result + ((branch == null) ? 0 : branch.hashCode());
        result = prime * result + ((bokDetailId == null) ? 0 : bokDetailId.hashCode());
        result = prime * result + ((busiId == null) ? 0 : busiId.hashCode());
        result = prime * result + ((busiDetailType == null) ? 0 : busiDetailType.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProdChannelFeeCalKey other = (ProdChannelFeeCalKey)obj;
        if (booksetId == null) {
            if (other.booksetId != null) {
                return false;
            }
        }
        else if (!booksetId.equals(other.booksetId)) {
            return false;
        }
        if (happenDate == null) {
            if (other.happenDate != null) {
                return false;
            }
        }
        else if (!happenDate.equals(other.happenDate)) {
            return false;
        }
        if (taskId == null) {
            if (other.taskId != null) {
                return false;
            }
        }
        else if (!taskId.equals(other.taskId)) {
            return false;
        }
        if (booksetDate == null) {
            if (other.booksetDate != null) {
                return false;
            }
        }
        else if (!booksetDate.equals(other.booksetDate)) {
            return false;
        }
        if (bookDate == null) {
            if (other.bookDate != null) {
                return false;
            }
        }
        else if (!bookDate.equals(other.bookDate)) {
            return false;
        }
        if (standardCcy == null) {
            if (other.standardCcy != null) {
                return false;
            }
        }
        else if (!standardCcy.equals(other.standardCcy)) {
            return false;
        }
        if (branch == null) {
            if (other.branch != null) {
                return false;
            }
        }
        else if (!branch.equals(other.branch)) {
            return false;
        }
        if (bokDetailId == null) {
            if (other.bokDetailId != null) {
                return false;
            }
        }
        else if (!bokDetailId.equals(other.bokDetailId)) {
            return false;
        }
        if (busiId == null) {
            if (other.busiId != null) {
                return false;
            }
        }
        else if (!busiId.equals(other.busiId)) {
            return false;
        }
        if (busiDetailType == null) {
            if (other.busiDetailType != null) {
                return false;
            }
        }
        else if (!busiDetailType.equals(other.busiDetailType)) {
            return false;
        }
        return true;
    }

}

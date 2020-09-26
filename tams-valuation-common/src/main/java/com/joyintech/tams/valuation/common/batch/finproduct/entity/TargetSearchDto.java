/*
 * 文件名：TargetSearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.util.Date;

/**
 * 标的查询 - 查询类型dto （已重写hashCode 和 equals方法）
 *
 * @author yangjian
 * @version 1.0 2018年5月30日
 * @see TargetSearchDto
 * @since 1.0
 */
public class TargetSearchDto {

    /**
     * 查询类型【必输项】 - 参考枚举类【ValDictCodeList.标的_XX.getValue()】【例如：ValDictCodeList.标的_信息.getValue()】
     */
    private String queryType;
    /**
     * 金融产品代码 - 【非必输项】
     */
    private String finprodId;
    /**
     * 金融产品类型 【非必输项】
     */
    private String finprodType;

    /**
     * 现金流代码 - 【非必输项】
     */
    private String cashId;

    /**
     * 现金流一级类型 - 【非必输项】
     */
    private String cashType1;

    /**
     * 现金流二级类型 - 【非必输项】
     */
    private String cashType2;

    /**
     * 现金流计划支付标志 - 【非必输项】
     */
    private String payStatus;

    /**
     * 生效日期 - 开始 - 【非必输项】
     */
    private Date effdateStart;

    /**
     * 生效日期 - 结束 - 【非必输项】
     */
    private Date effdateEnd;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((branch == null) ? 0 : branch.hashCode());
        result = prime * result + ((cashId == null) ? 0 : cashId.hashCode());
        result = prime * result + ((cashType1 == null) ? 0 : cashType1.hashCode());
        result = prime * result + ((cashType2 == null) ? 0 : cashType2.hashCode());
        result = prime * result + ((effdateEnd == null) ? 0 : effdateEnd.hashCode());
        result = prime * result + ((effdateStart == null) ? 0 : effdateStart.hashCode());
        result = prime * result + ((payStatus == null) ? 0 : payStatus.hashCode());
        result = prime * result + ((queryType == null) ? 0 : queryType.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
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
        TargetSearchDto other = (TargetSearchDto)obj;
        if (branch == null) {
            if (other.branch != null) {
                return false;
            }
        }
        else if (!branch.equals(other.branch)) {
            return false;
        }
        if (cashId == null) {
            if (other.cashId != null) {
                return false;
            }
        }
        else if (!cashId.equals(other.cashId)) {
            return false;
        }
        if (cashType1 == null) {
            if (other.cashType1 != null) {
                return false;
            }
        }
        else if (!cashType1.equals(other.cashType1)) {
            return false;
        }
        if (cashType2 == null) {
            if (other.cashType2 != null) {
                return false;
            }
        }
        else if (!cashType2.equals(other.cashType2)) {
            return false;
        }
        if (effdateEnd == null) {
            if (other.effdateEnd != null) {
                return false;
            }
        }
        else if (!effdateEnd.equals(other.effdateEnd)) {
            return false;
        }
        if (effdateStart == null) {
            if (other.effdateStart != null) {
                return false;
            }
        }
        else if (!effdateStart.equals(other.effdateStart)) {
            return false;
        }
        if (payStatus == null) {
            if (other.payStatus != null) {
                return false;
            }
        }
        else if (!payStatus.equals(other.payStatus)) {
            return false;
        }
        if (queryType == null) {
            if (other.queryType != null) {
                return false;
            }
        }
        else if (!queryType.equals(other.queryType)) {
            return false;
        }
        return true;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    public String getCashType1() {
        return cashType1;
    }

    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    public String getCashType2() {
        return cashType2;
    }

    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getEffdateStart() {
        return effdateStart;
    }

    public void setEffdateStart(Date effdateStart) {
        this.effdateStart = effdateStart;
    }

    public Date getEffdateEnd() {
        return effdateEnd;
    }

    public void setEffdateEnd(Date effdateEnd) {
        this.effdateEnd = effdateEnd;
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
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

}

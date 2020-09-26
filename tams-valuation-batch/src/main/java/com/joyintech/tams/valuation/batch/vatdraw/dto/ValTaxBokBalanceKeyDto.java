/*
 * 文件名：ValTaxBokBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dto;

/**
 * 增值税明细余额Key Dto
 * 
 * @author weipeng
 * @version 1.0 2018年8月2日
 * @see ValTaxBokBalanceKeyDto
 * @since 1.0
 */
public class ValTaxBokBalanceKeyDto {

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 确认标识
     */
    private String taxPType;

    /**
     * 发生场景
     */
    private String incomeSource;

    /**
     * 增值税类型
     */
    private String vatType;

    /**
     * 分支序号，无分支序号的存0
     */
    private Long branch;

    /**
     * 重写hashcode方法
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bokDetailId == null) ? 0 : bokDetailId.hashCode());
        result = prime * result + ((incomeSource == null) ? 0 : incomeSource.hashCode());
        result = prime * result + ((taxPType == null) ? 0 : taxPType.hashCode());
        result = prime * result + ((vatType == null) ? 0 : vatType.hashCode());
        result = prime * result + ((branch == null) ? 0 : branch.hashCode());
        return result;
    }

    /**
     * 重写equals方法，方便实体equals比较
     */
    @Override
    public boolean equals(Object obj) {
        boolean flag = true;
        do {
            if (this == obj) {
                flag = true;
                break;
            }

            if (obj == null) {
                flag = false;
                break;
            }

            if (getClass() != obj.getClass()) {
                flag = false;
                break;
            }

            ValTaxBokBalanceKeyDto other = (ValTaxBokBalanceKeyDto)obj;
            if (bokDetailId == null) {
                if (other.bokDetailId != null) {
                    flag = false;
                    break;
                }

            }
            else if (!bokDetailId.equals(other.bokDetailId)) {
                flag = false;
                break;
            }

            if (incomeSource == null) {
                if (other.incomeSource != null) {
                    flag = false;
                    break;
                }

            }
            else if (!incomeSource.equals(other.incomeSource)) {
                flag = false;
                break;
            }

            if (taxPType == null) {
                if (other.taxPType != null) {
                    flag = false;
                    break;
                }

            }
            else if (!taxPType.equals(other.taxPType)) {
                flag = false;
                break;
            }
            if (vatType == null) {
                if (other.vatType != null) {
                    flag = false;
                    break;
                }

            }
            else if (!vatType.equals(other.vatType)) {
                flag = false;
                break;
            }
            if (branch == null) {
                if (other.branch != null) {
                    flag = false;
                    break;
                }

            }
            else if (!branch.equals(other.branch)) {
                flag = false;
                break;
            }

        }
        while (false);
        return flag;
    }

    /**
     * 取得 账务明细代码
     * 
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * 取得 增值税类型
     * 
     * @return 增值税类型
     */
    public String getVatType() {
        return vatType;
    }

    /**
     * 设置 增值税类型
     * 
     * @param vatType 增值税类型
     */
    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    /**
     * 取得 确认标识
     * 
     * @return 确认标识
     */
    public String getTaxPType() {
        return taxPType;
    }

    /**
     * 设置 确认标识
     * 
     * @param taxPType 确认标识
     */
    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    /**
     * 取得 发生场景
     * 
     * @return 发生场景
     */
    public String getIncomeSource() {
        return incomeSource;
    }

    /**
     * 设置 发生场景
     * 
     * @param incomeSource 发生场景
     */
    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

}

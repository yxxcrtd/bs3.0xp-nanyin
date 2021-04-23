/*
 * 文件名：SubjectBalanceSearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 科目余额类查询参数Dto
 * 
 * @author weipeng
 * @version 1.0 2018年6月12日
 * @see SubjectBalanceSearchDto
 * @since 1.0
 */
public class SubjectBalanceSearchDto {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 账务明细代码
     */
    private List<String> bokDetailIdList;

    /**
     * 发生日（必填）
     */
    private Date happenDate;

    /**
     * 入账日（必填）
     */
    private Date bookDate;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 投资目的集合
     */
    private List<String> invAimList;

    /**
     * 基础科目性质集合
     */
    private List<String> baseSubjectNatureList;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 科目类型集合
     */
    private List<String> subjectTypeList;

    /**
     * 在途日期
     */
    private Date onwayDate;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 分支序号特殊处理Flag
     */
    private String branchHandleFlag;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 处理序列
     */
    private BigDecimal bookOrder;
    /**
     * 特殊核算模型
     */
    private String specialAccount; 
    /**
     * 原位币
     */
    private String oCcy; 
    
    
    /**
     * 获取 处理序列
     * @return bookOrder.
     */
    public BigDecimal getBookOrder() {
        return bookOrder;
    }

    
    /**
     * 设置 处理序列
     * @param bookOrder 处理序列
     */
    public void setBookOrder(BigDecimal bookOrder) {
        this.bookOrder = bookOrder;
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
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
     * 获取 bokDetailIdList
     * @return bokDetailIdList.
     */
    public List<String> getBokDetailIdList() {
        return bokDetailIdList;
    }


    
    /**
     * 设置 bokDetailIdList
     * @param bokDetailIdList bokDetailIdList
     */
    public void setBokDetailIdList(List<String> bokDetailIdList) {
        this.bokDetailIdList = bokDetailIdList;
    }


    /**
     * 取得 发生日（必填）
     * 
     * @return 发生日（必填）
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日（必填）
     * 
     * @param happenDate 发生日（必填）
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日（必填）
     * 
     * @return 入账日（必填）
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日（必填）
     * 
     * @param bookdate 入账日（必填）
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 业务明细代码
     * 
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * 
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 取得 投资目的集合
     * 
     * @return 投资目的集合
     */
    public List<String> getInvAimList() {
        return invAimList;
    }

    /**
     * 设置 投资目的集合
     * 
     * @param invAimList 投资目的集合
     */
    public void setInvAimList(List<String> invAimList) {
        this.invAimList = invAimList;
    }

    /**
     * 取得 基础科目性质集合
     * 
     * @return 基础科目性质集合
     */
    public List<String> getBaseSubjectNatureList() {
        return baseSubjectNatureList;
    }

    /**
     * 设置 基础科目性质集合
     * 
     * @param baseSubjectNatureList 基础科目性质集合
     */
    public void setBaseSubjectNatureList(List<String> baseSubjectNatureList) {
        this.baseSubjectNatureList = baseSubjectNatureList;
    }

    /**
     * 取得 场景代码
     * 
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * 
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 取得 科目类型集合
     * 
     * @return 科目类型集合
     */
    public List<String> getSubjectTypeList() {
        return subjectTypeList;
    }

    /**
     * 设置 科目类型集合
     * 
     * @param subjectTypeList 科目类型集合
     */
    public void setSubjectTypeList(List<String> subjectTypeList) {
        this.subjectTypeList = subjectTypeList;
    }

    /**
     * 取得 在途日期
     * 
     * @return 在途日期
     */
    public Date getOnwayDate() {
        return onwayDate;
    }

    /**
     * 设置 在途日期
     * 
     * @param onwayDate 在途日期
     */
    public void setOnwayDate(Date onwayDate) {
        this.onwayDate = onwayDate;
    }

    /**
     * 取得 分支序号
     * 
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * 
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 分支序号特殊处理Flag
     * 
     * @return 分支序号特殊处理Flag.
     */
    public String getBranchHandleFlag() {
        return branchHandleFlag;
    }

    /**
     * 设置 分支序号特殊处理Flag
     * 
     * @param branchHandleFlag 分支序号特殊处理Flag
     */
    public void setBranchHandleFlag(String branchHandleFlag) {
        this.branchHandleFlag = branchHandleFlag;
    }

    /**
     * 获取 subjectNo
     * 
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 subjectNo
     * 
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 设置任务优先级
     * 
     * @param taskPri taskPri
     */
    public Long getTaskPri() {
        return taskPri;
    }

    /**
     * 设置任务优先级
     * 
     * @param taskPri taskPri
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }


    
    /**
     * 获取 specialAccount
     * @return specialAccount.
     */
    public String getSpecialAccount() {
        return specialAccount;
    }


    
    /**
     * 设置 specialAccount
     * @param specialAccount specialAccount
     */
    public void setSpecialAccount(String specialAccount) {
        this.specialAccount = specialAccount;
    }


	public String getoCcy() {
		return oCcy;
	}


	public void setoCcy(String oCcy) {
		this.oCcy = oCcy;
	}
    

}

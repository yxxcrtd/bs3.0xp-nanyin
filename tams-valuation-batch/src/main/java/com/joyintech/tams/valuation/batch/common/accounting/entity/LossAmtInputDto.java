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
 * 减值金额计算输入DTO
 * 
 * @author caoguo
 * @version 1.0 2018年6月12日
 * @see LossAmtInputDto
 * @since 1.0
 */
public class LossAmtInputDto {

    /**
     * 本息标识
     */
    private String flag;

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
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 分支序号
     */
    private Long branch;

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
     * 业务明细代码
     */
    private String busiType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 发生金额(冲减理论金额)
     */
    private BigDecimal happenAmt;

    /**
     * 减值准备余额
     */
    private BigDecimal lossBalAmt;

    /**
     * 当前EAD金额
     */
    private BigDecimal EADAmt;

    /**
     * 应收款余额
     */
    private BigDecimal receAmt;

    /**
     * 减值日理论利息余额（仅利息需要）
     */
    private BigDecimal lossAccrDateBalAmt;

    /**
     * 减值日EAD金额
     */
    private BigDecimal lossAccrDateEADAmt;

    /**
     * 发生份额
     */
    private BigDecimal happenNum;

    /**
     * 总份额
     */
    private BigDecimal totalNum;
    
    /**
     * 总面值
     */
    private BigDecimal totalMz;
    
    /**
     * 溢折价科目余额
     */
    private BigDecimal varYzjSubjectAmt;
    

    /**
     * 获取 flag
     * 
     * @return flag.
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 flag
     * 
     * @param flag flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

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
     * 获取 bokDetailIdList
     * 
     * @return bokDetailIdList.
     */
    public List<String> getBokDetailIdList() {
        return bokDetailIdList;
    }

    /**
     * 设置 bokDetailIdList
     * 
     * @param bokDetailIdList bokDetailIdList
     */
    public void setBokDetailIdList(List<String> bokDetailIdList) {
        this.bokDetailIdList = bokDetailIdList;
    }

    /**
     * 获取 taskPri
     * 
     * @return taskPri.
     */
    public Long getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 taskPri
     * 
     * @param taskPri taskPri
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
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
     * 获取 bookDate
     * 
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 bookDate
     * 
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 busiType
     * 
     * @return busiType.
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 busiType
     * 
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 invAim
     * 
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 invAim
     * 
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 happenAmt
     * 
     * @return happenAmt.
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 happenAmt
     * 
     * @param happenAmt happenAmt
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 获取 lossBalAmt
     * 
     * @return lossBalAmt.
     */
    public BigDecimal getLossBalAmt() {
        return lossBalAmt;
    }

    /**
     * 设置 lossBalAmt
     * 
     * @param lossBalAmt lossBalAmt
     */
    public void setLossBalAmt(BigDecimal lossBalAmt) {
        this.lossBalAmt = lossBalAmt;
    }

    /**
     * 获取 eADAmt
     * 
     * @return eADAmt.
     */
    public BigDecimal getEADAmt() {
        return EADAmt;
    }

    /**
     * 设置 eADAmt
     * 
     * @param eADAmt eADAmt
     */
    public void setEADAmt(BigDecimal eADAmt) {
        EADAmt = eADAmt;
    }

    /**
     * 获取 receAmt
     * 
     * @return receAmt.
     */
    public BigDecimal getReceAmt() {
        return receAmt;
    }

    /**
     * 设置 receAmt
     * 
     * @param receAmt receAmt
     */
    public void setReceAmt(BigDecimal receAmt) {
        this.receAmt = receAmt;
    }

    /**
     * 获取 lossAccrDateBalAmt
     * 
     * @return lossAccrDateBalAmt.
     */
    public BigDecimal getLossAccrDateBalAmt() {
        return lossAccrDateBalAmt;
    }

    /**
     * 设置 lossAccrDateBalAmt
     * 
     * @param lossAccrDateBalAmt lossAccrDateBalAmt
     */
    public void setLossAccrDateBalAmt(BigDecimal lossAccrDateBalAmt) {
        this.lossAccrDateBalAmt = lossAccrDateBalAmt;
    }

    /**
     * 获取 lossAccrDateEADAmt
     * 
     * @return lossAccrDateEADAmt.
     */
    public BigDecimal getLossAccrDateEADAmt() {
        return lossAccrDateEADAmt;
    }

    /**
     * 设置 lossAccrDateEADAmt
     * 
     * @param lossAccrDateEADAmt lossAccrDateEADAmt
     */
    public void setLossAccrDateEADAmt(BigDecimal lossAccrDateEADAmt) {
        this.lossAccrDateEADAmt = lossAccrDateEADAmt;
    }

    /**
     * 获取 happenNum
     * 
     * @return happenNum.
     */
    public BigDecimal getHappenNum() {
        return happenNum;
    }

    /**
     * 设置 happenNum
     * 
     * @param happenNum happenNum
     */
    public void setHappenNum(BigDecimal happenNum) {
        this.happenNum = happenNum;
    }

    /**
     * 获取 totalNum
     * 
     * @return totalNum.
     */
    public BigDecimal getTotalNum() {
        return totalNum;
    }

    /**
     * 设置 totalNum
     * 
     * @param totalNum totalNum
     */
    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    
    /**
     * 获取 totalMz
     * @return totalMz.
     */
    public BigDecimal getTotalMz() {
        return totalMz;
    }

    
    /**
     * 设置 totalMz
     * @param totalMz totalMz
     */
    public void setTotalMz(BigDecimal totalMz) {
        this.totalMz = totalMz;
    }

	public BigDecimal getVarYzjSubjectAmt() {
		return varYzjSubjectAmt;
	}

	public void setVarYzjSubjectAmt(BigDecimal varYzjSubjectAmt) {
		this.varYzjSubjectAmt = varYzjSubjectAmt;
	}
    
    
    
}

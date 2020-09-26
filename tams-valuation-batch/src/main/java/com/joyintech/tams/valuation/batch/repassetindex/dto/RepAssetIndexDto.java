package com.joyintech.tams.valuation.batch.repassetindex.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;


/**
 * 资产指标表dto
 * 
 * @author dongxiangyang	
 * @version 1.0 2018年6月7日
 * @see RepAssetIndexDto
 * @since 1.0
 */
public class RepAssetIndexDto extends NeedOperateEntity implements Serializable{

	
	 private static final long serialVersionUID = 1L;
        
	    public RepAssetIndexDto() {
	        
	    }
	    public RepAssetIndexDto(String taskId) {
	        this.taskId = taskId;
	    }

	    public RepAssetIndexDelProcessorDto toResult(){
	        return new RepAssetIndexDelProcessorDto(taskId);
	    }
	    
	    /**
	     * 任务代码
	     */
	    private String taskId;
	    /**
	     * 获取 taskId
	     * @return taskId.
	     */
	    public String getTaskId() {
	        return taskId;
	    }

	    
	    /**
	     * 设置 taskId
	     * @param taskId taskId
	     */
	    public void setTaskId(String taskId) {
	        this.taskId = taskId;
	    }
	/**
	 * 流水号
	 */
	private String   seqNo;
	
	/**
	 * 账套代码
	 */
	private String   booksetId ;
	

	/**
	 * 估值日
	 */
	private Date   valueDate;
	
	/**
	 * 业务明细代码
	 */
	private String   busiId;
	
	/**
	 * 业务明细类型
	 */
	private String   busiType;
	
	/**
	 * 业务明细简称
	 */
	private String   busiAbbr;
	
	/**
	 * 业务明细名称
	 */
	private String   busiName;
	
	/**
	 * 资产细分类
	 */
	private String   bondType;
	
	/**
	 * 资产细分类排序号
	 */
	private String   bondTypeOrder;
	
	/**
	 * 资产细分类(汉字)
	 */
	private String   bondTypeZ;
	
	/**
	 * 投资目的
	 */
	private String   invAim;
	
	/**
	 * 当前持仓
	 */
	private BigDecimal   posAmt;
	
	/**
	 * 计提利率
	 */
	private BigDecimal   intRate;
	
	/**
	 * 日摊销收益率
	 */
	private BigDecimal  dailydscYield;
	
	/**
	 * 当日发生应计利息
	 */
	private BigDecimal   intincexpAdd;
	
	/**
	 * 当日累计应计利息余额
	 */
	private BigDecimal   tdyIntincexp;
	
	/**
	 * 起息日
	 */
	private Date   vDate;
	
	/**
	 * 到期日
	 */
	private Date   mDate;
	
	/**
	 * 当日份额
	 */
	private BigDecimal  tdyPosition;
	
	/**
	 * 当日成本
	 */
	private BigDecimal   tdyCostAmt;
	
	/**
	 * 当日公允价值变动
	 */
	private BigDecimal  tdyFloatIngpl;
	
	/**
	 * 剩余期限
	 */
	private BigDecimal   endDays1;
	
	/**
	 * 剩余存续期限
	 */
	private BigDecimal   endDays2;
	
	/**
	 * 剩余期限用成本
	 */
	private BigDecimal   endDaysCostAmt;
	
	/**
	 * 资产负债标识
	 */
	private String   alFlag;
	
	/**
	 * 剩余存续期
	 */
	private BigDecimal   surPlusTerm;
	
	/**
	 * 偏离金额
	 */
	private BigDecimal   deviationAmt;
	
	/**
	 * 创建人
	 */
	private String   createUser;
	
	/**
	 * 创建部门
	 */
	private String   createDept;
	
	/**
	 * 创建时间
	 */
	private Date   createTime;
	
	/**
	 * 更新人
	 */
	private String   updateUser;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}


	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiAbbr() {
		return busiAbbr;
	}

	public void setBusiAbbr(String busiAbbr) {
		this.busiAbbr = busiAbbr;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBondType() {
		return bondType;
	}

	public void setBondType(String bondType) {
		this.bondType = bondType;
	}

	public String getBondTypeOrder() {
		return bondTypeOrder;
	}

	public void setBondTypeOrder(String bondTypeOrder) {
		this.bondTypeOrder = bondTypeOrder;
	}

	public String getBondTypeZ() {
		return bondTypeZ;
	}

	public void setBondTypeZ(String bondTypeZ) {
		this.bondTypeZ = bondTypeZ;
	}

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public BigDecimal getPosAmt() {
		return posAmt;
	}

	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	public BigDecimal getIntRate() {
		return intRate;
	}

	public void setIntRate(BigDecimal intRate) {
		this.intRate = intRate;
	}

	public BigDecimal getDailydscYield() {
		return dailydscYield;
	}

	public void setDailydscYield(BigDecimal dailydscYield) {
		this.dailydscYield = dailydscYield;
	}

	public BigDecimal getIntincexpAdd() {
		return intincexpAdd;
	}

	public void setIntincexpAdd(BigDecimal intincexpAdd) {
		this.intincexpAdd = intincexpAdd;
	}

	public BigDecimal getTdyIntincexp() {
		return tdyIntincexp;
	}

	public void setTdyIntincexp(BigDecimal tdyIntincexp) {
		this.tdyIntincexp = tdyIntincexp;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public BigDecimal getTdyPosition() {
		return tdyPosition;
	}

	public void setTdyPosition(BigDecimal tdyPosition) {
		this.tdyPosition = tdyPosition;
	}

	public BigDecimal getTdyCostAmt() {
		return tdyCostAmt;
	}

	public void setTdyCostAmt(BigDecimal tdyCostAmt) {
		this.tdyCostAmt = tdyCostAmt;
	}

	public BigDecimal getTdyFloatIngpl() {
		return tdyFloatIngpl;
	}

	public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
		this.tdyFloatIngpl = tdyFloatIngpl;
	}

	public BigDecimal getEndDays1() {
		return endDays1;
	}

	public void setEndDays1(BigDecimal endDays1) {
		this.endDays1 = endDays1;
	}

	public BigDecimal getEndDays2() {
		return endDays2;
	}

	public void setEndDays2(BigDecimal endDays2) {
		this.endDays2 = endDays2;
	}

	public BigDecimal getEndDaysCostAmt() {
		return endDaysCostAmt;
	}

	public void setEndDaysCostAmt(BigDecimal endDaysCostAmt) {
		this.endDaysCostAmt = endDaysCostAmt;
	}

	public String getAlFlag() {
		return alFlag;
	}

	public void setAlFlag(String alFlag) {
		this.alFlag = alFlag;
	}

	public BigDecimal getSurPlusTerm() {
		return surPlusTerm;
	}

	public void setSurPlusTerm(BigDecimal surPlusTerm) {
		this.surPlusTerm = surPlusTerm;
	}

	public BigDecimal getDeviationAmt() {
		return deviationAmt;
	}

	public void setDeviationAmt(BigDecimal deviationAmt) {
		this.deviationAmt = deviationAmt;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	

	
	
	
}

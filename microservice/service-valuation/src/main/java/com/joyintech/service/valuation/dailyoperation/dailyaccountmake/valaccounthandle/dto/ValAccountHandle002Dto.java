package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.GridRowDto;
/**
 * 批检信息表
 * @author dongxiangyang	
 * @version 1.0 2018年11月9日
 * @see ValAccountHandle002Dto
 * @since 1.0
 */
public class ValAccountHandle002Dto extends BaseEntity implements GridRowDto{
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 入账日期
     */
    private Date bookDate;
    /**
     * 发生日期
     */
    private Date happenDate;
    /**
     * 账套日期
     */
    private Date booksetDate;
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 业务明细代码
     */
    private String bokDetailId;
    
    /**
     * 日志信息ID
     */
    private String seqNo;
    
    /**
     * 任务代码
     */
    private String taskId;
    /**
     * 检查日期
     */
    private Date checkDate;
    /**
     * 日志信息
     */
    private String logInfo;
    /**
     * 批检类型
     */
    private String checkType;
    
    /**
     *显示日期 
     */
    private String pageDate;
    
    /**
     * 账套显示日期
     * 
     */
    private String bookPDate;
    
    
	public String getBookPDate() {
		return bookPDate;
	}
	public void setBookPDate(String bookPDate) {
		this.bookPDate = bookPDate;
	}
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public Date getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	public Date getBooksetDate() {
		return booksetDate;
	}
	public void setBooksetDate(Date booksetDate) {
		this.booksetDate = booksetDate;
	}
	public String getInvAim() {
		return invAim;
	}
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}
	public String getBokDetailId() {
		return bokDetailId;
	}
	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	
	public String getPageDate() {
		return pageDate;
	}
	public void setPageDate(String pageDate) {
		this.pageDate = pageDate;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRowId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setId(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLeaf(boolean flag) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setOrder(int i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setParentId(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRowId(String s) {
		// TODO Auto-generated method stub
		
	}
}

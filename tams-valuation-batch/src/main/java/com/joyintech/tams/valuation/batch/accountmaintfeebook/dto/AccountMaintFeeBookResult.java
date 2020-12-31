package com.joyintech.tams.valuation.batch.accountmaintfeebook.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

public class AccountMaintFeeBookResult extends NeedOperateEntity{
	/**
     * 计提基础信息list
     */
    private List<AccountMaintFeeBookDto> listAccountMaintFeeBookReaderDto = new ArrayList<AccountMaintFeeBookDto>();
    
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 会计场景
     */
    private TreeNode bookSetTreeNode;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

	public List<AccountMaintFeeBookDto> getListAccountMaintFeeBookReaderDto() {
		return listAccountMaintFeeBookReaderDto;
	}

	public void setListAccountMaintFeeBookReaderDto(List<AccountMaintFeeBookDto> listAccountMaintFeeBookReaderDto) {
		this.listAccountMaintFeeBookReaderDto = listAccountMaintFeeBookReaderDto;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public TreeNode getBookSetTreeNode() {
		return bookSetTreeNode;
	}

	public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
		this.bookSetTreeNode = bookSetTreeNode;
	}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getBaseSubjectNature() {
		return baseSubjectNature;
	}

	public void setBaseSubjectNature(String baseSubjectNature) {
		this.baseSubjectNature = baseSubjectNature;
	}

}

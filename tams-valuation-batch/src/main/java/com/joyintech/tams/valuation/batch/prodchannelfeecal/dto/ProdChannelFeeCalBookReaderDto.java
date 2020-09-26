/*
 * 文件名：ProfitDistributionReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.framework.json.CustomDateDeserialize;

/**
 * 理财产品通道费查询明细
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see ProdChannelFeeCalBookReaderDto
 * @since 1.0
 */
public class ProdChannelFeeCalBookReaderDto extends NeedOperateEntity {

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
     * 会计场景
     */
    private TreeNode bookSetTreeNode;

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
     * 子账套代码
     */
    private String booksetIdSon;
    
    /**
     * 子账套代码List
     */
    private List<String> booksetIdSonList;
    
    /**
     * 任务优先级
     */
    private Long taskPri;
    
	/**
	 * @return the taskPri
	 */
	public Long getTaskPri() {
		return taskPri;
	}

	/**
	 * @param taskPri the taskPri to set
	 */
	public void setTaskPri(Long taskPri) {
		this.taskPri = taskPri;
	}

	/**
     * 取得 子账套代码List
     * @return 子账套代码List
     */
	public List<String> getBooksetIdSonList() {
		return booksetIdSonList;
	}

    /**
     * 设置 子账套代码List
     * @param booksetIdSon 子账套代码List
     */
	public void setBooksetIdSonList(List<String> booksetIdSonList) {
		this.booksetIdSonList = booksetIdSonList;
	}

	/**
     * 取得 子账套代码
     * @return 子账套代码
     */
    public String getBooksetIdSon() {
		return booksetIdSon;
	}

    /**
     * 设置 子账套代码
     * @param booksetIdSon 子账套代码
     */
	public void setBooksetIdSon(String booksetIdSon) {
		this.booksetIdSon = booksetIdSon;
	}

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

    /**
     * 获取 bookSetTreeNode
     * @return bookSetTreeNode.
     */
    public TreeNode getBookSetTreeNode() {
        return bookSetTreeNode;
    }

    /**
     * 设置 bookSetTreeNode
     * @param bookSetTreeNode bookSetTreeNode
     */
    public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
        this.bookSetTreeNode = bookSetTreeNode;
    }
    
    
}

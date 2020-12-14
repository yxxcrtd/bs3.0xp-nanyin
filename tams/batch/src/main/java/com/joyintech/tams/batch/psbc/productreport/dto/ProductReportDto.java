/*
 * 文件名：ValBatchTaskTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.batch.psbc.productreport.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

import java.util.Date;

/**
 * 待处理账套Dto
 *
 * @author weipeng
 * @version 1.0 2019年3月15日
 * @see ProductReportDto
 * @since 1.0
 */
public class ProductReportDto extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 发生日
     */
    private Date happenDate;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 金融产品代码（核算分层信息.业务明细代码）
     */
    private String finprodId;

    /**
     * 金融产品类型（核算分层信息.业务明细类型）
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 分层代码
     */
    private String layeringId;

    /**
     * 会计场景
     */
    private TreeNode bookSetTreeNode;

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
     * 取得 账套日期
     *
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     *
     * @param booksetDate 账套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日
     *
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     *
     * @param bookDate 入账日
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 发生日
     *
     * @return 发生日
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日
     *
     * @param happenDate 发生日
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取 本位币
     *
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     *
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    /**
     * 获取 任务代码
     *
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     *
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取 金融产品代码（核算分层信息.业务明细代码）
     *
     * @return 金融产品代码（核算分层信息.业务明细代码）
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码（核算分层信息.业务明细代码）
     *
     * @param finprodId 金融产品代码（核算分层信息.业务明细代码）
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 金融产品类型（核算分层信息.业务明细类型）
     *
     * @return 金融产品类型（核算分层信息.业务明细类型）
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（核算分层信息.业务明细类型）
     *
     * @param finprodType 金融产品类型（核算分层信息.业务明细类型）
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 分支序号
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
     * 获取 账务明细代码
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
     * 获取 分层代码
     *
     * @return 分层代码
     */
    public String getLayeringId() {
        return layeringId;
    }

    /**
     * 设置 分层代码
     *
     * @param layeringId 分层代码
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }

    /**
     * 获取 会计场景
     *
     * @return 会计场景
     */
    public TreeNode getBookSetTreeNode() {
        return bookSetTreeNode;
    }

    /**
     * 设置 会计场景
     *
     * @param bookSetTreeNode 会计场景
     */
    public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
        this.bookSetTreeNode = bookSetTreeNode;
    }

}

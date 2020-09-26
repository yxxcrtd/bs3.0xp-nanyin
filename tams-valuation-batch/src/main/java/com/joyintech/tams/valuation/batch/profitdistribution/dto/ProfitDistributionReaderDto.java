/*
 * 文件名：ProfitDistributionReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

/**
 * 利润分配查询明细
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see ProfitDistributionReaderDto
 * @since 1.0
 */
public class ProfitDistributionReaderDto extends NeedOperateEntity {

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
     * 任务优先级
     */
    private Long taskPri;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 分支序号
     */
    private long branch;
    
   /**
    * 账务明细代码
    */
    private String bokDetailId;
    
    /**
     * 分层代码
     */
    private String layeringId;
    
        
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



    /**
     * 获取 taskPri
     * @return taskPri.
     */
    public Long getTaskPri() {
        return taskPri;
    }


    
    /**
     * 设置 taskPri
     * @param taskPri taskPri
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
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
    public long getBranch() {
        return branch;
    }




    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(long branch) {
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
     * 获取 layeringId
     * @return layeringId.
     */
    public String getLayeringId() {
        return layeringId;
    }




    
    /**
     * 设置 layeringId
     * @param layeringId layeringId
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
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

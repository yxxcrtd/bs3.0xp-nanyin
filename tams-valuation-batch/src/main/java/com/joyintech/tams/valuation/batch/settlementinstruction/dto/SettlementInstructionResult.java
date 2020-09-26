/*
 * 文件名：SettlementInstructionResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.settlementinstruction.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

/**
 * 结算指令明细记录DTO
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月8日
 * @see SettlementInstructionResult
 * @since 1.0
 */
public class SettlementInstructionResult  extends NeedOperateEntity  {
    /**
     * 任务代码
     */
    private String taskId;           
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 发生日期
     */
    private Date happenDate;
    /**
     * 入账日期
     */
    private Date bookDate;
    /**
     * 账套日期
     */
    private Date booksetDate;
    /**
     * 指令类型
     */
    private String orderType;
    /**
     * 财务场景
     */
    private TreeNode bookSetTreeNode;
    /**
     * 指令编码
     */
    private String orderId;
    /**
     * 交易基本信息列表
     */
    private List<SettlementInstructionDTO> listSettlementInstructionDTO = new ArrayList<SettlementInstructionDTO>();
    
    /**
     *  任务代码
     *  @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     *  任务代码
     *  @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     *  取得 账套代码
     *  @return 账套代码
     */
    public String getBooksetId() {
       return booksetId;
    }

    /**
     *  设置 账套代码
     *  @param booksetId 账套代码
     */
    public void setBooksetId( String booksetId)  {
       this.booksetId = booksetId;
    }

    /**
     *  取得 账务明细代码
     *  @return 账务明细代码
     */
    public String getBokDetailId() {
       return bokDetailId;
    }

    /**
     *  设置 账务明细代码
     *  @param bokDetailId 账务明细代码
     */
    public void setBokDetailId( String bokDetailId)  {
       this.bokDetailId = bokDetailId;
    }

    /**
     *  取得 账套日期
     *  @return 账套日期
     */
    public Date getBooksetDate() {
       return booksetDate;
    }

    /**
     *  设置 账套日期
     *  @param booksetDate 账套日期
     */
    public void setBooksetDate( Date booksetDate)  {
       this.booksetDate = booksetDate;
    }

    /**
     *  取得 发生日期
     *  @return 发生日期
     */
    public Date getHappenDate() {
       return happenDate;
    }

    /**
     *  设置 发生日期
     *  @param happenDate 发生日期
     */
    public void setHappenDate( Date happenDate)  {
       this.happenDate = happenDate;
    }

    /**
     *  取得 入账日期
     *  @return 入账日期
     */
    public Date getBookDate() {
       return bookDate;
    }

    /**
     *  设置 入账日期
     *  @param bookDate 入账日期
     */
    public void setBookDate( Date bookDate)  {
       this.bookDate = bookDate;
    }

    
    public String getOrderType() {
        return orderType;
    }

    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    
    public TreeNode getBookSetTreeNode() {
        return bookSetTreeNode;
    }

    
    public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
        this.bookSetTreeNode = bookSetTreeNode;
    }

    
    public List<SettlementInstructionDTO> getListSettlementInstructionDTO() {
        return listSettlementInstructionDTO;
    }

    
    public void setListSettlementInstructionDTO(List<SettlementInstructionDTO> listSettlementInstructionDTO) {
        this.listSettlementInstructionDTO = listSettlementInstructionDTO;
    }

    
    public String getOrderId() {
        return orderId;
    }

    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

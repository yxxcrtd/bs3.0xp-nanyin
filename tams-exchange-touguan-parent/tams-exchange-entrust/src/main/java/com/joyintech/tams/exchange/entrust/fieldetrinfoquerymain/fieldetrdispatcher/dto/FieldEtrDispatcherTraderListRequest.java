package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 交易员列表查询
 * @author
 * @version 1.0 2021年10月29日
 * @see FieldEtrDispatcherTraderListRequest
 * @since 1.0
 */
@ApiModel(value="交易员查询展示列表",description="交易员查询展示列表")
public class FieldEtrDispatcherTraderListRequest {

    /**
     * 用户名
     **/
    @ApiModelProperty(name="userName",value="用户名",example="字符串")
    private String userName;

    /**
     * 真实姓名
     **/
    @ApiModelProperty(name="realName",value="真实姓名",example="字符串")
    private String realName;

    /**
     * 已执行的指令笔数
     **/
    @ApiModelProperty(name="finishedWork",value="已执行的指令笔数",example="50")
    private String finishedWork;

    /**
     * 待执行的指令笔数
     **/
    @ApiModelProperty(name="unfinishedWork",value="待执行的指令笔数",example="5")
    private String unfinishedWork;

    /**
     * 全部的指令笔数
     **/
    @ApiModelProperty(name="totalWork",value="全部的指令笔数",example="60")
    private String totalWork;

    /**
     * 获取用户名
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取真实姓名
     **/
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     **/
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取已执行的指令笔数
     **/
    public String getFinishedWork() {
        return finishedWork;
    }

    /**
     * 设置已执行的指令笔数
     **/
    public void setFinishedWork(String finishedWork) {
        this.finishedWork = finishedWork;
    }

    /**
     * 获取待执行的指令笔数
     **/
    public String getUnfinishedWork() {
        return unfinishedWork;
    }

    /**
     * 设置待执行的指令笔数
     **/
    public void setUnfinishedWork(String unfinishedWork) {
        this.unfinishedWork = unfinishedWork;
    }

    /**
     * 获取全部的指令笔数
     **/
    public String getTotalWork() {
        return totalWork;
    }

    /**
     * 设置全部的指令笔数
     **/
    public void setTotalWork(String totalWork) {
        this.totalWork = totalWork;
    }
}


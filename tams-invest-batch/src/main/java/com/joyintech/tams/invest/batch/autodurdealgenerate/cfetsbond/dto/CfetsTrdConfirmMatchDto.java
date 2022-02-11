/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 银行间交易成交确认
 * 下行成交匹配关系Dto
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmMatchDto
 * @since 1.0
 */
@ApiModel(value="执行交易与Cstp匹配数据",description="执行交易与Cstp匹配数据")
public class CfetsTrdConfirmMatchDto {
    @ApiModelProperty(name="seqno",value="流水号",example="字符串1")
    private String seqno;
    @ApiModelProperty(name="cstpTradeId",value="下行交易编号",example="字符串1")
    private String cstpTradeId;
    @ApiModelProperty(name="exeId",value="成交编号",example="字符串1")
    private String exeId;
    @ApiModelProperty(name="trdId",value="成交确认编号",example="字符串1")
    private String trdId;
    @ApiModelProperty(name="preTradeId",value="报价编号/登记编号",example="字符串1")
    private String preTradeId;
    @ApiModelProperty(name="matchType",value="匹配方式，手工匹配，自动匹配",example="字符串1")
    private String matchType;
    @ApiModelProperty(name="matchStatus",value="匹配状态，已匹配",example="字符串1")
    private String matchStatus;
    @ApiModelProperty(name="trdConfStatus",value="成交确认状态",example="字符串1")
    private String trdConfStatus;
    @ApiModelProperty(name="createUser",value="创建人",example="字符串1")
    private String createUser;
    @ApiModelProperty(name="createTime",value="创建时间",example="字符串1")
    private Date createTime;
    @ApiModelProperty(name="createDept",value="创建部门",example="字符串1")
    private String createDept;
    @ApiModelProperty(name="updateUser",value="修改人",example="字符串1")
    private String updateUser;
    @ApiModelProperty(name="updateTime",value="修改时间",example="字符串1")
    private Date updateTime;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getCstpTradeId() {
        return cstpTradeId;
    }

    public void setCstpTradeId(String cstpTradeId) {
        this.cstpTradeId = cstpTradeId;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getPreTradeId() {
        return preTradeId;
    }

    public void setPreTradeId(String preTradeId) {
        this.preTradeId = preTradeId;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getTrdConfStatus() {
        return trdConfStatus;
    }

    public void setTrdConfStatus(String trdConfStatus) {
        this.trdConfStatus = trdConfStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
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

    public String getTrdId() {
        return trdId;
    }

    public void setTrdId(String trdId) {
        this.trdId = trdId;
    }
}

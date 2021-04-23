package com.joyintech.tams.valuation.common.auxiliary.dto;

/**
 * 脚本生成Dto
 * add by zwq 2021-1-16
 */
public class GenerateSqlDto {
    private String dealResult;  // 处理结果
    private String dealMessage; // 处理信息描述
    private String sql;         // 执行sql

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getDealMessage() {
        return dealMessage;
    }

    public void setDealMessage(String dealMessage) {
        this.dealMessage = dealMessage;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

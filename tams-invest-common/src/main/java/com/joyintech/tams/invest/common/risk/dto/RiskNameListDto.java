package com.joyintech.tams.invest.common.risk.dto;

import java.util.Map;

import com.joyintech.tams.common.base.entity.FinProduct;

public class RiskNameListDto extends FinProduct {

    /**
     * 名单代码
     */
    private String listId;

    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 组织机构代码/统一社会信用代码
     */
    private String insitituteCode;

    /**
     * 名单主体类型，资产代码、托管人、
     */
    private String listDetailIdType;

    /**
     * 名单类型，白名单、黑名单
     */
    private String listType;

    /**
     * 名单主体类型，机构名单、资产名单
     */
    private String listDetailType;

    //是否通过名单验证
    private  boolean isPass;

    //无标的传参
    private String inputValue1;
    private String inputValue2;
    private String inputValue3;

    //交易对手
    private String counterId;

    /**
     * 输出
     */
    private Map<String,Object> errorMap;

    /**
     * 不通过的白名单id
     * @return
     */
    private  String nopassListId;

    /**
     * 不通过的黑名单id
     * @return
     */
    private String nopassBlackListId;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInsitituteCode() {
        return insitituteCode;
    }

    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode;
    }

    public String getListDetailIdType() {
        return listDetailIdType;
    }

    public void setListDetailIdType(String listDetailIdType) {
        this.listDetailIdType = listDetailIdType;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getListDetailType() {
        return listDetailType;
    }

    public void setListDetailType(String listDetailType) {
        this.listDetailType = listDetailType;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public String getInputValue1() {
        return inputValue1;
    }

    public void setInputValue1(String inputValue1) {
        this.inputValue1 = inputValue1;
    }

    public String getInputValue2() {
        return inputValue2;
    }

    public void setInputValue2(String inputValue2) {
        this.inputValue2 = inputValue2;
    }

    public String getInputValue3() {
        return inputValue3;
    }

    public void setInputValue3(String inputValue3) {
        this.inputValue3 = inputValue3;
    }

    public Map<String, Object> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, Object> errorMap) {
        this.errorMap = errorMap;
    }

    public String getNopassListId() {
        return nopassListId;
    }

    public void setNopassListId(String nopassListId) {
        this.nopassListId = nopassListId;
    }

    public String getNopassBlackListId() {
        return nopassBlackListId;
    }

    public void setNopassBlackListId(String nopassBlackListId) {
        this.nopassBlackListId = nopassBlackListId;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }
}

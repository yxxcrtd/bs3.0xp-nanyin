package com.joyintech.tams.invest.portfolio.ptlcashflow.dto;

public class PartyInfoDto {

    /**
     * 参与人角色
     */
    private String PARTY_ROLE;
    /**
     * 参与人名称
     */
    private String PARTY_NAME;
    /**
     * 参与人联系方式
     */
    private String PARTY_CONTACT_INFO;

    public String getPARTY_ROLE() {
        return PARTY_ROLE;
    }

    public void setPARTY_ROLE(String PARTY_ROLE) {
        this.PARTY_ROLE = PARTY_ROLE;
    }

    public String getPARTY_NAME() {
        return PARTY_NAME;
    }

    public void setPARTY_NAME(String PARTY_NAME) {
        this.PARTY_NAME = PARTY_NAME;
    }

    public String getPARTY_CONTACT_INFO() {
        return PARTY_CONTACT_INFO;
    }

    public void setPARTY_CONTACT_INFO(String PARTY_CONTACT_INFO) {
        this.PARTY_CONTACT_INFO = PARTY_CONTACT_INFO;
    }
}

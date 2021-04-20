package com.joyintech.service.bankuplink.common.dto;

/**
 * @author zhangnian
 * @ClassName 交易员明细
 * @description TODO
 * @date 2021/1/23 0023 13:25
 **/
public class NoPartySubID {

    private String partySubIDType;

    private String partySubID;

    public String getPartySubIDType() {
        return partySubIDType;
    }

    public void setPartySubIDType(String partySubIDType) {
        this.partySubIDType = partySubIDType;
    }

    public String getPartySubID() {
        return partySubID;
    }

    public void setPartySubID(String partySubID) {
        this.partySubID = partySubID;
    }
}


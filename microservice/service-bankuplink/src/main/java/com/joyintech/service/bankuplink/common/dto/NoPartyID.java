package com.joyintech.service.bankuplink.common.dto;

import java.util.List;

/**
 * @author zhangnian
 * @ClassName 交易员信息
 * @description TODO
 * @date 2021/1/23 0023 13:22
 **/
public class NoPartyID {

    private String partyRole;

    private String partyID;

    private List<NoPartyID> noPartyIDs;

    private List<NoPartySubID> noPartySubIDs;

    private List<NoContactInfo> noContactInfos;

    private List<NoRelatedPartydDetail> noRelatedPartydDetails;

    /**
     * 跨托管托管账户
     */
    private String counterdepositaccountnumberKtg;
    public String getPartyRole() {
        return partyRole;
    }

    public void setPartyRole(String partyRole) {
        this.partyRole = partyRole;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public List<NoPartyID> getNoPartyIDs() {
        return noPartyIDs;
    }

    public void setNoPartyIDs(List<NoPartyID> noPartyIDs) {
        this.noPartyIDs = noPartyIDs;
    }

    public List<NoPartySubID> getNoPartySubIDs() {
        return noPartySubIDs;
    }

    public void setNoPartySubIDs(List<NoPartySubID> noPartySubIDs) {
        this.noPartySubIDs = noPartySubIDs;
    }

    public List<NoContactInfo> getNoContactInfos() {
        return noContactInfos;
    }

    public void setNoContactInfos(List<NoContactInfo> noContactInfos) {
        this.noContactInfos = noContactInfos;
    }

    public List<NoRelatedPartydDetail> getNoRelatedPartydDetails() {
        return noRelatedPartydDetails;
    }

    public void setNoRelatedPartydDetails(List<NoRelatedPartydDetail> noRelatedPartydDetails) {
        this.noRelatedPartydDetails = noRelatedPartydDetails;
    }

    public String getCounterdepositaccountnumberKtg() {
        return counterdepositaccountnumberKtg;
    }

    public void setCounterdepositaccountnumberKtg(String counterdepositaccountnumberKtg) {
        this.counterdepositaccountnumberKtg = counterdepositaccountnumberKtg;
    }
}


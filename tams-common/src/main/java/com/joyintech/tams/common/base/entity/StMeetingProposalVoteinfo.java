package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class StMeetingProposalVoteinfo extends StMeetingProposalVoteinfoKey {
    /**
     * 股东身份，A股股东、优先股、恢复表决
权优先股
     */
    private String vrole;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 股权登记日
     */
    private Date regdate;

    /**
     * 取得 股东身份，A股股东、优先股、恢复表决
权优先股
     * @return 股东身份，A股股东、优先股、恢复表决
权优先股
     */
    public String getVrole() {
        return vrole;
    }

    /**
     * 设置 股东身份，A股股东、优先股、恢复表决
权优先股
     * @param vrole 股东身份，A股股东、优先股、恢复表决
权优先股
     */
    public void setVrole(String vrole) {
        this.vrole = vrole == null ? null : vrole.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 股权登记日
     * @return 股权登记日
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 设置 股权登记日
     * @param regdate 股权登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
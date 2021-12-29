package com.joyintech.tams.settle.batch.datagather.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.util.Arrays;
import java.util.Date;

/**
 * @author zznn
 * @date 2021/10/29
 */
public class EtrEntrustDto extends EtrEntrust {


    /**
     * 证券账户代码
     */
    private String secAcctId;

    /**
     * 交易货币账户
     */
    private String dealCurAcct;

    /**
     * 指令明细表tradeId
     */
    private String tradeId;

    /**
     * 首次交收日
     */
    private Date vpayDate;

    /**
     * 取得 首次交收日
     * @return 首次交收日
     */
    public Date getVpayDate() {
        return vpayDate;
    }

    /**
     * 设置 首次交收日
     * @param vpayDate 首次交收日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVpayDate(Date vpayDate) {
        this.vpayDate = vpayDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getDealCurAcct() {
        return dealCurAcct;
    }

    public void setDealCurAcct(String dealCurAcct) {
        this.dealCurAcct = dealCurAcct;
    }

    public String getBusiKey(){
        return StringUtil.joinString(Arrays.asList(getSecAcctId(),getDealCurAcct(),getFinprodId(), DateUtil.format(getEntrustDate()),getTradePlat(),getPs()),"_");
    }

    public String getBusiKeyWithoutDate(){
        return StringUtil.joinString(Arrays.asList(getSecAcctId(),getDealCurAcct(),getFinprodId(), getTradePlat(),getPs()),"_");
    }
}


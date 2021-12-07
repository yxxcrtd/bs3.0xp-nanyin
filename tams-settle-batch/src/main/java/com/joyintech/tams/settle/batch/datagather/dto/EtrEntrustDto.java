package com.joyintech.tams.settle.batch.datagather.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.util.Arrays;

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
}


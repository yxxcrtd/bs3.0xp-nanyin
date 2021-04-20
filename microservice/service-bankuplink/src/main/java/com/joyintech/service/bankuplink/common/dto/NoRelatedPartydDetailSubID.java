package com.joyintech.service.bankuplink.common.dto;

/**
 * 跨托管机构实体明细
 * @auther zhangnian
 * @date 2021/3/24
 */
public class NoRelatedPartydDetailSubID {

    private String relatedPartydDetailSubID;

    private String relatedPartydDetailSubType;

    public String getRelatedPartydDetailSubID() {
        return relatedPartydDetailSubID;
    }

    public void setRelatedPartydDetailSubID(String relatedPartydDetailSubID) {
        this.relatedPartydDetailSubID = relatedPartydDetailSubID;
    }

    public String getRelatedPartydDetailSubType() {
        return relatedPartydDetailSubType;
    }

    public void setRelatedPartydDetailSubType(String relatedPartydDetailSubType) {
        this.relatedPartydDetailSubType = relatedPartydDetailSubType;
    }
}


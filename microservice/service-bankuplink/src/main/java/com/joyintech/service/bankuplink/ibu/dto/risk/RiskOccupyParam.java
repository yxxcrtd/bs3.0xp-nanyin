package com.joyintech.service.bankuplink.ibu.dto.risk;

import java.util.List;

/**
 * create By MAXX on 2020/5/12
 */
public class RiskOccupyParam {
    private List<ReqRiskOccupy> rskList;
    private String assetType;
    private String eventType;
    /**
     * 是否需要风险检查（是：Y 否：N）
     */
    private String isNeedRiskCheck;

    public List<ReqRiskOccupy> getRskList() {
        return rskList;
    }

    public void setRskList(List<ReqRiskOccupy> rskList) {
        this.rskList = rskList;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * 获取 isNeedRiskCheck
     * @return isNeedRiskCheck.
     */
    public String getIsNeedRiskCheck() {
        return isNeedRiskCheck;
    }

    /**
     * 设置 isNeedRiskCheck
     * @param isNeedRiskCheck isNeedRiskCheck
     */
    public void setIsNeedRiskCheck(String isNeedRiskCheck) {
        this.isNeedRiskCheck = isNeedRiskCheck;
    }
}

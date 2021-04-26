package com.joyintech.tams.invest.trade.api;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/8/6
 * @see PositionUpdateParam
 * @since 1.0
 */
public class PositionUpdateParam {

    private String assetType2;

    private List<String> tradeIdList;

    private List<String> totalTradeIdList;

    private String revokeReason;

    private String updateUser;

    private String status;

    /**
     * 业务类型
     */
    private String busiType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTradeIdList() {
        return tradeIdList;
    }

    public void setTradeIdList(List<String> tradeIdList) {
        this.tradeIdList = tradeIdList;
    }

    public List<String> getTotalTradeIdList() {
        return totalTradeIdList;
    }

    public void setTotalTradeIdList(List<String> totalTradeIdList) {
        this.totalTradeIdList = totalTradeIdList;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

	public String getAssetType2() {
		return assetType2;
	}

	public void setAssetType2(String assetType2) {
		this.assetType2 = assetType2;
	}
}

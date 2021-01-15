package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import com.joyintech.tams.constant.DictCodeListExt2;


public enum ServerMenu{

    资产基本信息_债券信息("svrBussFinBondRepInfoDto", DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue(), "rep"),
    资产基本信息_存款类信息("svrBussRepDepositInfoDto", DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue(), "rep"),
    资产基本信息_拆放同业及买入返售("svrBussRepDlRepoInfoDto", DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue(), "rep")

    ;

    /**
     * 资产实体类
     * @param ServerId   资产基本信息在AssetInfoDetailDto里面命名-必须一致
     * @param AssetType   当前资产类锁包含的资产负债类别(资产负债二级类别的上级)
     * @param flagStr      (是否包含字段修改.反射时候会进行删除再与chb_asset_info进行比对)
     */
    ServerMenu(String ServerId, String AssetType, String flagStr) {
        this.ServerId = ServerId;
        this.AssetType = AssetType;
        this.flagStr = flagStr;
    }
    private String ServerId;
    private String AssetType;
    private String flagStr;

    public String getServerId() {
        return ServerId;
    }

    public void setServerId(String serverId) {
        ServerId = serverId;
    }

    public String getAssetType() {
        return AssetType;
    }

    public void setAssetType(String assetType) {
        AssetType = assetType;
    }

    public String getFlagStr() {
        return flagStr;
    }

    public void setFlagStr(String flagStr) {
        this.flagStr = flagStr;
    }



    /**
     * 获取所有资产类型
     * @return
     */
    public static ServerMenu[] getAllAsset() {
        return ServerMenu.values();
    }


}

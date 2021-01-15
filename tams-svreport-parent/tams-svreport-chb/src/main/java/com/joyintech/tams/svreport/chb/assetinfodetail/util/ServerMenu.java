package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import com.joyintech.tams.constant.DictCodeListExt2;


public enum ServerMenu{

    资产基本信息_债券信息("svrBussFinBondRepInfoDto", DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue(), "rep"),
    资产基本信息_存款类信息("svrBussRepDepositInfoDto", DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue(), "rep"),
//    存款类资产中债报送("svrBussRepDlRepoInfoDto", DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue(), "rep")
     资产基本信息_同业拆放拆入类信息("svrBussRepPlacementInfoDto", DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue(), null),
     资产基本信息_金融衍生品类信息("svrBussRepFinanciallInfoDto", DictCodeListExt2.资产_负债类别_金融衍生品.getValue(), null),
     资产基本信息_QDII拆出逆回购类信息("svrBussRepQDIIdemolitionInfoDto", DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue(), null),
     资产基本信息_QDII结构性票据类信息("svrBussRepQDIIStructureInfoDto", DictCodeListExt2.资产_负债类别_QDII结构性票据类资产.getValue(), null),
     资产基本信息_贵金属商品类信息("svrBussRepNobleMetalInfoDto", DictCodeListExt2.资产_负债类别_商品类资产.getValue(), null),
     资产基本信息_另类资产类信息("svrBussRepOthKindInfoDto", DictCodeListExt2.资产_负债类别_另类资产.getValue(), null),
     资产基本信息_其它资产类信息("svrBussRepOtherInfoDto", DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue(), null),

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

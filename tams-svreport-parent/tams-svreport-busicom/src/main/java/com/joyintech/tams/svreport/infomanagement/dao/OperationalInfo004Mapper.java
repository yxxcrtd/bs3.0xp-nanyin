package com.joyintech.tams.svreport.infomanagement.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationalInfo004Mapper {


    String getFilterIdByTable(String tableName);

    List<String> getBaseInfoIds(String tableName);

    /*add by  xuyang 徽商 20201202根据产品代码查询资产要素*/
    ChbAssetInfo selectChbAssetInfoByAssetCode(String assetCode);

}

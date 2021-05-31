package com.joyintech.tams.svreport.infomanagement.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;

@Repository
public interface OperationalInfo004Mapper {


    String getFilterIdByTable(String tableName);

    List<String> getBaseInfoIds(String tableName);

    /*add by  xuyang 徽商 20201202根据产品代码查询资产要素*/
    ChbAssetInfo selectChbAssetInfoByAssetCode(String assetCode);
    
    /*add by  leipeng 查询产品登记编码数据*/
    String queryPrdRegisCode(String regCode);

}

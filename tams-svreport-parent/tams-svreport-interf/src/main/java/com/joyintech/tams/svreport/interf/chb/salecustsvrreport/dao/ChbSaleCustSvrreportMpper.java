package com.joyintech.tams.svreport.interf.chb.salecustsvrreport.dao;

import com.joyintech.tams.common.base.entity.SaleCustSvrreport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbSaleCustSvrreportMpper {

    /**
     * 合并数据（入库）
     * @param insertList
     * @return
     */
    int mergeInfoList(@Param("newDataList") List<SaleCustSvrreport> insertList);

//    /**
//     * 根据行内资产负债编码 查询 人行分类
//     * @param asset_code
//     * @return
//     */
//    Map<String, String> selectPbcAssetType(String asset_code);
}

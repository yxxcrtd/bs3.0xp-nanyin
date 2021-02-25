package com.joyintech.tams.svreport.interf.chb.salecustsvrreport.dao;

import com.joyintech.tams.common.base.entity.SaleCustSvrreport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbSaleCustSvrreportMpper {

    /**
     * 合并数据（入库）
     * @param subList
     * @return
     */
    int mergeInfoList(List<SaleCustSvrreport> subList);

//    /**
//     * 根据行内资产负债编码 查询 人行分类
//     * @param asset_code
//     * @return
//     */
//    Map<String, String> selectPbcAssetType(String asset_code);
}

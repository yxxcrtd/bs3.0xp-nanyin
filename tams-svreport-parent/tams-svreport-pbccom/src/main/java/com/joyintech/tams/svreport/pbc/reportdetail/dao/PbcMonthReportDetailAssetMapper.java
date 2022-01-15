package com.joyintech.tams.svreport.pbc.reportdetail.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailAsset;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PbcMonthReportDetailAssetMapper {
    List<PbcMonthReportDetailAsset> selectDetailList(Map<String, Object> map);

    List<SelectOptionDto> getAssetCode(Map<String, Object> map);

    int insertBatch(List<PbcMonthReportDetailAsset> list);

    /**
     * 根据资产代码查询资产相关信息
     *
     * @param assetCode 资产代码
     * @return 资产信息
     */
    List<ChbAssetInfo> selectChbAssetInfoByAssetCodeList(@Param("assetCodeList") Set<String> assetCode);

    List<ChbTypeMappingEx> getInvestorType();

    int deleteDetail(String term);


    List<SelectOptionDto> getCounterList(Map<String,Object> map);

    List<MstCustomerInfoAdd> getCustomerList(Map<String,Object> map);
}
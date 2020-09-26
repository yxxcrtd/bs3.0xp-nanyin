package com.joyintech.tams.svreport.interf.chb.loandetailinfo.dao;

import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbLoanDetailInfoInterfMapper {

    int mergeInfoList(@Param("newDataList") List<ChbLoanDetailInfo> insertList);

    Map<String,String> selectPbcAssetType(@Param("assetCode") String assetCode);
}

package com.joyintech.tams.svreport.interf.chb.loandetailinfo.dao;

import com.joyintech.tams.common.base.entity.ChbLoanDetailContractDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SvrLoanDetailContractMapper {

    int mergeLoanDetailContract(@Param("list") List<ChbLoanDetailContractDto> list);


    void deleteAllData();
    List<String> selectAllLoanAssetCode();
    List<ChbLoanDetailContractDto> selectLoanDetailContractByAssetCodeList(@Param("assetCodeList")List<String> assetCodeList);
    List<ChbLoanDetailContractDto> selectLoanDetailContractByAssetCodeListNoCny(@Param("assetCodeList")List<String> assetCodeList);
}
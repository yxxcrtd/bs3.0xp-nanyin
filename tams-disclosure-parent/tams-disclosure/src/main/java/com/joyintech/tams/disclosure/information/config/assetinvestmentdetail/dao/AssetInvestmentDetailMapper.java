package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto.AssetInvestmentDetail001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface AssetInvestmentDetailMapper {
    List<AssetInvestmentDetail001Dto> select(Map<String, Object> pageParam);

    AssetInvestmentDetail001Dto selectByPrimaryKey(Map map);

    int deleteByKey(AssetInvestmentDetail001Dto dto);

    int insert(AssetInvestmentDetail001Dto assetInvestmentDetail001Dto);

    int insertHis(AssetInvestmentDetail001Dto assetInvestmentDetail001Dto);

    int updateByKey(AssetInvestmentDetail001Dto dto);

    List<SelectOptionDto> getRegistNo(Map<String, Object> map);

    String selectByProdRegistNo(String prodRegistNo);

    List<SelectOptionDto> findProdId(Map<String, Object> map);

    List<SelectOptionDto> getRegistNoList(Map<String, String> map);

    List<SelectOptionDto> getProdIdlist(Map<String, String> map);


    List<SelectOptionDto> getRePord(Map<String, Object> pageParam);

    HashMap findProdName(String booksetId);

    List<SelectOptionDto> findSubjectNo(Map<String, Object> pageParam);


    List<SelectOptionDto> getProdId1(Map<String, Object> map);

    List<AccountAmount001Dto> selectBalanceList(@Param("list") List<AccountAmount001Dto> list);
}

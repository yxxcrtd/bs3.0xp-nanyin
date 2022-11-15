package com.joyintech.tams.disclosure.information.config.hugeinfopro.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HugeInfoProMapper {
    List<HugeInfoPro001Dto> select(Map<String, Object> pageParam);

    HugeInfoPro001Dto selectByPrimaryKey(String prodRegistNo);

    int deleteByKey(String redemptionId);

    int insert(HugeInfoPro001Dto hugeInfoPro001Dto);

    int updateByKey(HugeInfoPro001Dto dto);


    String selectByProdRegistNo(Map<String, Object> map);


    List<SelectOptionDto> getRegistNoList(Map<String, String> map);

    List<SelectOptionDto> getProdIdlist(Map<String, String> map);


    HugeInfoPro001Dto selectByBusPK(Map map);

    int updateByBusKey(HugeInfoPro001Dto existDataDto);

    List<SelectOptionDto> getProdId(Map<String, Object> map);

    int isRelate(@Param("prodRegistNo") String prodRegistNo, @Param("prodId")String prodId);

    List<HugeInfoPro001Dto> selectHugeInfoList(@Param("list") List<HugeInfoPro001Dto> list);

    List<HugeInfoPro001Dto> selectHugeInfoByProdId(Map<String, Object> map);
    String selectProdRegistNo(String prodId);
}

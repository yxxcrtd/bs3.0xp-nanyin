package com.joyintech.tams.disclosure.information.config.profailureinfo.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProFailureInfoMapper {
    List<ProFailureInfo001Dto> select(Map<String, Object> pageParam);

    ProFailureInfo001Dto selectByPrimaryKey(String prodRegistNo);

    ProFailureInfo001Dto selectOne(@Param("prodRegistNo") String prodRegistNo,@Param("nocreateReason") String nocreateReason,@Param("date") String date,@Param("dataStatus") String dataStatus);

    int deleteByKey(ProFailureInfo001Dto dto);

    int delete(ProFailureInfo001Dto dto);

    int insert(ProFailureInfo001Dto proFailureInfo001Dto);

    int updateByKey(ProFailureInfo001Dto dto);

    List<SelectOptionDto> getRegistNo(Map<String, Object> map);

    String selectByProdRegistNo(String prodRegistNo);

    List<SelectOptionDto> getProdId(Map<String, Object> map);

    List<SelectOptionDto> getRegistNoList(Map<String, String> map);

    List<SelectOptionDto> getProdIdlist(Map<String, String> map);


    List<SelectOptionDto> getRePord(Map<String, Object> pageParam);

    int hasRepeatedData(ProFailureInfo001Dto dto);

    List<ProFailureInfo001Dto> isRepeated(ProFailureInfo001Dto dto);

    ProFailureInfo001Dto isRepeated2(ProFailureInfo001Dto dto);

    List<ProFailureInfo001Dto> selectFailureInfoList(@Param("list") List<ProFailureInfo001Dto> list);
}

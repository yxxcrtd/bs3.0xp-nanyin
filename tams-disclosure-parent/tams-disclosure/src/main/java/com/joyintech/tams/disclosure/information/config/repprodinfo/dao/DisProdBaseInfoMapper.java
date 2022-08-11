package com.joyintech.tams.disclosure.information.config.repprodinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface DisProdBaseInfoMapper  {

    int deleteByPrimaryKey(String prodId);

    int insert(ProdInfoDto record);

    int insertSelective(ProdInfoDto record);

    int insertBasicByBasicInfo(ProdInfoDto record);

    ProdInfoDto selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ProdInfoDto record);

    int updateByPrimaryKey(ProdInfoDto record);

    List<ProdInfoDto> searchProdList(Map<String, Object> params);

    ProdInfoDto selectBySonProdId(String prodId);


    ProdInfoDto selectByCommonProdId(String prodId);


    ProdInfoDto selectByProdStatus(@Param("prodId") String prodId,@Param("status") String status);

    void updateIssuingStatus(ProdInfoDto dto);

    List<ProdInfoDto> queryForList(String[] split);

    List<ProdInfoDto> selectListByPprodId(@Param("prodId") String prodId);

    int updateRaiseAmtByPprodId(ProdInfoDto record);

    List<SelectOptionDto> queryPprodIds(Map<String, Object> paramMap);

}

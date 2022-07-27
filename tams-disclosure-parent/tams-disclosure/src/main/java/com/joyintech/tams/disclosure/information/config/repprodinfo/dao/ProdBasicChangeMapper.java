package com.joyintech.tams.disclosure.information.config.repprodinfo.dao;

import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdBasicChangeMapper {
    int deleteByPrimaryKey(ProdBasicChangeDto key);

    int insert(ProdBasicChangeDto record);

    int insertSelective(ProdBasicChangeDto record);

    int insertHisByBasicInfo(ProdBasicChangeDto record);

    ProdBasicChangeDto selectByPrimaryKey(ProdBasicChangeDto key);

    int updateByPrimaryKeySelective(ProdBasicChangeDto record);

    int updateByPrimaryKey(ProdBasicChangeDto record);


    List<ProdBasicChangeDto> selectListByProdId(String prodId);

    int deleteByProdId(String prodId);


}
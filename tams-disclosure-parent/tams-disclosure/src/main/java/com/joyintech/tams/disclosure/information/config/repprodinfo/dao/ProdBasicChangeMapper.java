package com.joyintech.tams.disclosure.information.config.repprodinfo.dao;

import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdBasicChangeMapper {

    int deleteByPrimaryKey(String uuid);

    int insert(ProdBasicChangeDto record);

    int insertSelective(ProdBasicChangeDto record);

    int insertBasicByBasicInfo(ProdBasicChangeDto record);

    ProdBasicChangeDto selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ProdBasicChangeDto record);

    int updateByPrimaryKey(ProdBasicChangeDto record);

    List<ProdBasicChangeDto> selectListByProdId(String prodId);

    int deleteByProdId(String prodId);


}
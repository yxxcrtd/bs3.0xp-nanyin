package com.joyintech.service.product.common.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.BussFinProductInvestInfoDto;

@Repository
public interface PrdInvestParamMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(BussFinProductInvestInfoDto record);

    BussFinProductInvestInfoDto selectByPrimaryKey(String finprodId);

    int updateByPrimaryKey(BussFinProductInvestInfoDto record);
}
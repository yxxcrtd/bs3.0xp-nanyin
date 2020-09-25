package com.joyintech.tams.common.base.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.ChbLoanDetailContractDto;

public interface ChbLoanDetailContractDtoMapper {
    int insert(ChbLoanDetailContractDto record);

    int insertSelective(ChbLoanDetailContractDto record);
    List<ChbLoanDetailContractDto> selectAllValid();
}
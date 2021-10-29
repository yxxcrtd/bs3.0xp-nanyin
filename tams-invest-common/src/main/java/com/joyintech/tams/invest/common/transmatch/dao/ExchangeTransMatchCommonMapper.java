package com.joyintech.tams.invest.common.transmatch.dao;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeTransMatchCommonMapper {

    /**
     * 查询委托信息--根据idlIST
     * @param entrustIdList
     * @return
     */
    List<ExchangeTransMatchCommonEntrustDto> getEtrEntrustByIdList(List<String> entrustIdList);

}

package com.joyintech.tams.invest.batch.resaleregautodeal.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResaleRegAutoDealMapper {

    List<String> listTotleTradeId(Map<String,Object> param);

    int deleteMatchInfoByPreTradeId(@Param("totleTradeId")String totleTradeId);

    int deleteDealTotal(@Param("totleTradeId") String totleTradeId);

    int deletePaDealTotal(@Param("totleTradeId") String totleTradeId);

    int deleteDeal(@Param("totleTradeId") String totleTradeId);

    int deletePadeal(@Param("totleTradeId") String totleTradeId);

    int deleteDealAdd(@Param("totleTradeId") String totleTradeId);
}

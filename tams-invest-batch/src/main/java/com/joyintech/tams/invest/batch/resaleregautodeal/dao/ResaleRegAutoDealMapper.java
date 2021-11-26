package com.joyintech.tams.invest.batch.resaleregautodeal.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResaleRegAutoDealMapper {

    List<String> listTotleTradeId(Map<String,Object> param);
}

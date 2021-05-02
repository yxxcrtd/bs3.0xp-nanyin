package com.joyintech.tams.invest.common.pagemenusplit.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PageMenuSplitMapper {
    List<String> selectDictItemList(Map<String, Object> paramMap);
}

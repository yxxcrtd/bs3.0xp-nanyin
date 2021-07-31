package com.joyintech.service.risk.fundgoal.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.fundgoal.dto.FundGoalDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FundGoalMapper {
    List<SelectOptionDto> getFinProdMarketIdList(Map<String, Object> param);

    void deleteByPrimaryKey(FundGoalDto entity);

    void insert(FundGoalDto entity);

    void updateByPrimaryKeySelective(FundGoalDto entity);

    List<FundGoalDto> selectForPage(Map<String, Object> param);

    FundGoalDto selectPrimaryKey(Map<String, Object> param);

    Map<String,Object> getCalendarId(Map<String, Object> param);
}

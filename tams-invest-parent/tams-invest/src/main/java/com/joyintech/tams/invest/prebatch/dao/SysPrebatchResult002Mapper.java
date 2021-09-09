package com.joyintech.tams.invest.prebatch.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResultDetail001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysPrebatchResult002Mapper {


    List<SelectOptionDto> queryPortfolioIdInfo(Map<String, Object> pageParam);

    List<SelectOptionDto> queryFinprodIdInfo(Map<String, Object> pageParam);

    List<SysPrebatchResultDetail001Dto> findPage(Map<String, Object> pageParam);
}

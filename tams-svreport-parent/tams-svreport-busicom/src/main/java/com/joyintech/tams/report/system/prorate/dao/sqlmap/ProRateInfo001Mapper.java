package com.joyintech.tams.report.system.prorate.dao.sqlmap;

import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProRateInfo001Mapper {

    List<ProRateInfo001Dto> queryForPage(Map<String, Object> param);
}

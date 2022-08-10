package com.joyintech.tams.disclosure.information.config.repreportcheck.dao;

import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dto.ReportCheckDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportCheckMapper {
    int deleteByPrimaryKey(String reportCheckId);

    int insert(ReportCheckDto record);

    int insertSelective(ReportCheckDto record);

    int insertBasicByBasicInfo(ReportCheckDto record);

    ReportCheckDto selectByPrimaryKey(String reportCheckId);

    int updateByPrimaryKeySelective(ReportCheckDto record);

    int updateByPrimaryKey(ReportCheckDto record);

    List<ReportCheckDto> searchList(Map<String, Object> params);


    List<RepDataCheck> getRepDataCheckList();

}
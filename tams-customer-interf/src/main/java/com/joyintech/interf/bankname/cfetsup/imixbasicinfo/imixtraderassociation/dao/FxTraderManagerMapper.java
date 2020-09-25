package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxTraderManagerMapper {

    List<FxTraderManagerDto> selectPageData(Map para);

    int deleteByPrimaryKey(String seqno);

    int insert(FxTraderManagerDto record);

    FxTraderManagerDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKey(FxTraderManagerDto record);

    List<SuggestDto> getUser(Map map);

    int findCountByStaffid(FxTraderManagerDto dto);

    int findCountByCstptraderid(FxTraderManagerDto dto);
}
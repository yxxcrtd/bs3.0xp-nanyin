package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dao;

import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dto.FxQuoteLogInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxQuoteLogInfo001Mapper {

    List<FxQuoteLogInfoDto> selectForPage(Map<String,Object> map);
}

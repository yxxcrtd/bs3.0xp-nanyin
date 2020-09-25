package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dao;

import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dto.FxQuoteSendInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxQuoteSendInfo001Mapper {

    List<FxQuoteSendInfoDto> selectForPage(Map<String,Object> map);


}

package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dao.FxQuoteLogInfo001Mapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dto.FxQuoteLogInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FxQuoteLogInfo001Service extends CommonExportService<FxQuoteLogInfoDto> {


    @Autowired
    private FxQuoteLogInfo001Mapper fxQuoteLogInfo001Mapper;

    /**
     * 查询
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<FxQuoteLogInfoDto> selectForPage(Map<String,Object> pageParam) {

        return super.queryForPage(()-> fxQuoteLogInfo001Mapper.selectForPage(pageParam));

    }





}

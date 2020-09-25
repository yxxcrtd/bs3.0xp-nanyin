package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dao.FxQuoteSendInfo001Mapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dto.FxQuoteSendInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class FxQuoteSendInfo001Service extends CommonExportService<FxQuoteSendInfoDto> {

    @Autowired
    private FxQuoteSendInfo001Mapper fxQuoteSendInfo001Mapper;


    /**
     * 查询
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<FxQuoteSendInfoDto> selectForPage(Map<String,Object> pageParam) {
        Object nbquoteidObj = pageParam.get("nbquoteid");
        if(null != nbquoteidObj){
            pageParam.put("nbquoteidList", StringUtil.splitToList(GlobalConstant.COMMA,(String)nbquoteidObj));
        }else {
            pageParam.put("nbquoteidList", new ArrayList<>());
        }

        return super.queryForPage(()-> fxQuoteSendInfo001Mapper.selectForPage(pageParam));

    }


}

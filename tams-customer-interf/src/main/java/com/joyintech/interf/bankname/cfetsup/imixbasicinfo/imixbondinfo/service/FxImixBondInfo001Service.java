package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dao.FxImixBondInfo001Mapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dto.FxImixBondInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class FxImixBondInfo001Service extends CommonExportService<FxImixBondInfoDto> {

    @Autowired
    private FxImixBondInfo001Mapper FxImixBondInfo001Mapper;


    /**
     * 查询
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<FxImixBondInfoDto> selectForPage(Map<String,Object> pageParam) {
        Object secidObj = pageParam.get("secid");
        if(null != secidObj){
            pageParam.put("secidList", StringUtil.splitToList(GlobalConstant.COMMA,(String)secidObj));
        }else {
            pageParam.put("secidList", new ArrayList<>());
        }
        return super.queryForPage(()-> FxImixBondInfo001Mapper.selectForPage(pageParam));

    }

    /**
     *
     *
     * @param map
     * @return 债券代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> querySecId(Map<String,Object> map) {

        return super.queryForPageForSelectReturn(()-> FxImixBondInfo001Mapper.querySecId(map));
    }



}

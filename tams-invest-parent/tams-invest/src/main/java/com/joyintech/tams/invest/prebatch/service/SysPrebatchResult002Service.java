package com.joyintech.tams.invest.prebatch.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.prebatch.dao.SysPrebatchResult002Mapper;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResult001Dto;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResultDetail001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class SysPrebatchResult002Service extends CommonExportService<SysPrebatchResultDetail001Dto> {

    @Autowired
    private SysPrebatchResult002Mapper sysPrebatchResult002Mapper;

    public Page<SelectOptionDto> queryPortfolioIdInfo(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        return  this.queryForPageForSelectReturn(page, length, ()-> sysPrebatchResult002Mapper.queryPortfolioIdInfo(pageParam));

    }

    public  Page<SelectOptionDto> queryFinprodIdInfo(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        return  this.queryForPageForSelectReturn(page, length, ()-> sysPrebatchResult002Mapper.queryFinprodIdInfo(pageParam));

    }

    public Page<SysPrebatchResultDetail001Dto> findPage(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
        }else{
            pageParam.put("portfolioId",new ArrayList<>());
        }

        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.put("finprodId",new ArrayList<>());
        }

        return  this.queryForPageForSelectReturn(page, length, ()-> sysPrebatchResult002Mapper.findPage(pageParam));

    }
}

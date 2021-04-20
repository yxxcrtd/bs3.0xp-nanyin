package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProrateService extends BaseService<ProRateInfo001Dto> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ProRateInfo001Dto> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/findPage", paramMap,
                ProRateInfo001Dto.class);
    }
}

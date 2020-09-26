package com.joyintech.tams.valuation.selectbokdetailcurrent.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.selectbokdetailcurrent.dto.SelectBokdetailCurrent001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class SelectBokdetailCurrent001Service extends CommonExportService<SelectBokdetailCurrent001Dto> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<SelectBokdetailCurrent001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/SelectBokdetailCurrent001/findPage",
    			params, SelectBokdetailCurrent001Dto.class);
    }
    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<SelectBokdetailCurrent001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/SelectBokdetailCurrent001/queryAllList",
                params, SelectBokdetailCurrent001Dto.class);
    }
}

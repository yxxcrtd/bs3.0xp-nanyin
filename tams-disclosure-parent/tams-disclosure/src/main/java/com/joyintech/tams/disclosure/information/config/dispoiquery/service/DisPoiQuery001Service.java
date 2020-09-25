package com.joyintech.tams.disclosure.information.config.dispoiquery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * poi查询sql管理Service
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisPoiQuery001Service extends CommonExportService<DisPoiQuery001Dto> {


    /**
     * 分页查询事件
     *
     * @param param
     * @return
     */
    public Page<DisPoiQuery001Dto> searchPage(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/DisPoiQuery001/searchPage",
                param, DisPoiQuery001Dto.class);
    }

    /**
     * 获取关联编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> queryPoiIdList(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/DisPoiQuery001/queryPoiIdList",
                param, SelectOptionDto.class);
    }


    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    public void doDelete(DisPoiQuery001Dto dto) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/DisPoiQuery001/doDelete",
                dto, Integer.class);
    }


}

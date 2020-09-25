package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 报表子表Service
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManageChildren001Service extends CommonExportService<PoiReportModelManageChildren001Dto> {


    /**
     * 分页查询事件
     *
     * @param param
     * @return
     */
    public Page<PoiReportModelManageChildren001Dto> searchPage(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManageChildren001/searchPage",
                param, PoiReportModelManageChildren001Dto.class);
    }

    /**
     * 获取模板编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> queryTabNumList(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManageChildren001/queryTabNumList",
                param, SelectOptionDto.class);
    }

    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    public void doDelete(PoiReportModelManageChildren001Dto dto) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManageChildren001/doDelete",
                dto, Integer.class);
    }
}

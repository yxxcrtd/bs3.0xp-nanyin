package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * 报表子表新建Service
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManageChildren002Service extends BaseService<PoiReportModelManageChildren001Dto> {

    /**
     * 根据主键查询报表子表
     *
     * @param param
     * @return
     */
    public PoiReportModelManageChildren001Dto selectById(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManageChildren001/selectById",
                param, PoiReportModelManageChildren001Dto.class);
    }

    /**
     * 保存提交
     *
     * @param dto
     */
    public void doSave(PoiReportModelManageChildren001Dto dto) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/PoiReportModelManageChildren001/doSave", dto, Integer.class);
    }
}

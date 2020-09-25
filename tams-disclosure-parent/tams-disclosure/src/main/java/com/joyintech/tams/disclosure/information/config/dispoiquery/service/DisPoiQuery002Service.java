package com.joyintech.tams.disclosure.information.config.dispoiquery.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * poi查询sql管理新建Service层
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisPoiQuery002Service extends BaseService<DisPoiQuery001Dto> {

    /**
     * 根据主键查询poi查询sql
     *
     * @param param
     * @return
     */
    public DisPoiQuery001Dto selectById(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/DisPoiQuery001/selectById",
                param, DisPoiQuery001Dto.class);
    }

    /**
     * 保存提交
     *
     * @param dto
     */
    public void doSave(DisPoiQuery001Dto dto) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/DisPoiQuery001/doSave", dto, Integer.class);
    }

}

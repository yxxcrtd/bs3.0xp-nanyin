package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistAccounting001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ValAssistAccounting001Service extends CommonExportService<ValAssistAccounting001Dto>  {

    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValAssistAccounting001Dto> findPage(Map<String, Object> pageParam) {

        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssistAccounting001/findPage",
                pageParam,ValAssistAccounting001Dto.class);
    }

    /**
     * 查询所有账套 <br/>
     * 用于所属账套控件初期化
     *
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findBooksets() {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValAssistAccounting001/findBooksets",null, SelectOptionDto.class);
    }

    /**
     * 不分页
     *
     * @param params
     * @return
     */
    public List<ValAssistAccounting001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssistAccounting001/queryPageList", params,
                ValAssistAccounting001Dto.class);
    }


}

package com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FinProductLossStage002Service extends CommonExportService<FinProductLossStage001Dto> {

    /**
     * 新建
     */
    public int saveFinProLosSta(FinProductLossStage001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage002/saveFinProLosSta",
                dto, Integer.class);
    }

    /**
     * 编辑
     */
    public int editFinProLosSta(FinProductLossStage001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage002/editFinProLosSta",
                dto, Integer.class);
    }

    /**
     * 更新失效日期
     */
    public int updateInvalidDate(FinProductLossStage001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage002/updateInvalidDate",
                dto, Integer.class);
    }
}

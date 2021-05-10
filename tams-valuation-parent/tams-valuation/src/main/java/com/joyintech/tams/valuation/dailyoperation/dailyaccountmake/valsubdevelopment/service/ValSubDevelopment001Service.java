package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValSubDevelopment001Service extends CommonExportService<ValSubDevelopment001Dto> {

    /**
     * 查询账套模板
     */
    public Page<SelectOptionDto> selectBooksetId(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/selectBooksetId",
                param, SelectOptionDto.class);
    }

    /**
     * 查询带扩展科目号
     * @return
     */
    public Page<SelectOptionDto> selectThirdSubjectId(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/selectThirdSubjectId",
                param, SelectOptionDto.class);
    }

    // 删除业务明细科目表(BOK_BUSS_DETSUB)
    public int deleteBokBussDetSub(Map<String, Object> param){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/deleteBokBussDetSub",
                param,Integer.class);
    }

    // 插入删除的数据
    public int insertBokBussDetSub(BokBussDetsub sub){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/insertBokBussDetSub",
                sub,Integer.class);
    }

    // 更新账务明细代码表(BOK_DETAIL_ID)
    public int updateBokDetailId(BokBookdetailCurrentDto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/updateBokDetailId",
                dto,Integer.class);
    }
    // 更新账务明细历史表(BOK_BOOKDETAIL_ALL)
    public int updateBokBookDetailAll(BokBookdetailCurrentDto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/updateBokBookDetailAll",
                dto,Integer.class);
    }
    // 更新账务明细当前表(BOK_BOOKDETAIL_CURRENT)
    public int updateBokBookDetailCurrent(BokBookdetailCurrentDto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubDevelopment001/updateBokBookDetailCurrent",
                dto,Integer.class);
    }
}

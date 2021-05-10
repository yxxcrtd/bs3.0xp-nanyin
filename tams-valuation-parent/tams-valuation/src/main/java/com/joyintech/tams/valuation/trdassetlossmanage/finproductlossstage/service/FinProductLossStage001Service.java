package com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import org.springframework.stereotype.Service;
import com.joyintech.framework.select.SelectOptionDto;

import java.util.List;
import java.util.Map;

@Service
public class FinProductLossStage001Service extends CommonExportService<FinProductLossStage001Dto> {

    /**
     * 查询画面
     * @param params
     * @return
     * @throws Exception
     */
    public Page<FinProductLossStage001Dto> queryPageList(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/findPage",
                params, FinProductLossStage001Dto.class);
    }

    /**
     * 查询所有数据，不进行分页
     */
    public List<FinProductLossStage001Dto> getAllList(Map<String, Object> params) throws Exception{
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/getAllList",
                params, FinProductLossStage001Dto.class);
    }

    /**
     * 查询资产名称
     */
    public Page<SelectOptionDto> getFinprodName(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/getFinprodName",
                param, SelectOptionDto.class);
    }

    /**
     * 查询账套日期
     * @param dto
     * @return
     */
    public FinProductLossStage001Dto getBooksetDate(FinProductLossStage001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/getBooksetDate",
                dto, FinProductLossStage001Dto.class);
    }

    /**
     * 删除
     */
    public int deleteFinProLosSta(FinProductLossStage001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/deleteFinProLosSta",
                dto, Integer.class);
    }

    /**
     * 查询上一条有效的的资产减值阶段对象
     */
    public List<FinProductLossStage001Dto> getPrevValidObj(FinProductLossStage001Dto dto){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/getPrevValidObj",
                dto, FinProductLossStage001Dto.class);
    }

    /**
     * 查询下一条有效的的资产减值阶段对象
     */
    public List<FinProductLossStage001Dto> getNextValidObj(FinProductLossStage001Dto dto){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/FinProductLossStage001/getNextValidObj",
                dto, FinProductLossStage001Dto.class);
    }
}

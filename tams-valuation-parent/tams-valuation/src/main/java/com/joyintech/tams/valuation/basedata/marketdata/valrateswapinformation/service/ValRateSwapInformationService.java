package com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
//import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
//import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.dto.ValRateSwapInformationDto;
import com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValRateSwapInformationService extends CommonExportService<ValRateSwapInformationDto> implements BaseProcessService<ValRateSwapInformationDto> {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     *@title: buildExportParam
     *@description: 导出
     *@author: junYin
     *@date: 2019/06/04 0:31
     *@param: * @param exportParam 1
     *@return: void
     *@throws:
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/valuation/basedata/marketdata/valrateswapinformation/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<ValRateSwapInformationDto> selectPageData(Map<String, Object> selectParam) {
        return selectByParams(selectParam);
    }

    /**
     * 工作流完成
     * @param entity
     * @param type
     */
    @Override
    public void doComplete(ValRateSwapInformationDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        String operType = "";
        if (OperationType.DELETE.equals(type)) {
            operType = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            operType = "insert";
        }
        else {
            operType = "update";
        }
        restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapInformation/" + operType, entity, ValRateSwapInformationDto.class);

    }


    /**
     * 工作流发起时调用
     */
    @Override
    public void doInsert(ValRateSwapInformationDto entity, OperationType type) {
        System.out.println("-------------------");
    }

    /**
     *
     * 流程修改时调用
     */
    @Override
    public void doUpdate(ValRateSwapInformationDto dto){

    }
    /**
     *@title: check
     *@description: 校验方法
     *@author: junYin
     *@date: 2019/06/04 0:29
     *@param: * @param dto 1
     *@return: java.util.List<com.joyintech.framework.validation.ValidMessage>
     *@throws:
     */
    public boolean check(ValRateSwapInformationDto dto) {
        boolean flag = false;
        dto.setDataTime(DictCodeListExt2.利率互换曲线时刻_16_30.getValue());
        List<ValYieldCurveInfoDto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapInformation/check", dto, ValYieldCurveInfoDto.class);
        if (list != null && list.size() > 0){
            flag = true;
        }
        return flag;
    }

    public Page<ValRateSwapInformationDto> selectByParams(Map<String, Object> map) {
        map.put("dataTime",DictCodeListExt2.利率互换曲线时刻_16_30.getValue());
        List<ValRateSwapInformationDto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapInformation/selectByParams", map, ValRateSwapInformationDto.class);
        Page<ValRateSwapInformationDto> page = new Page<>();
        page.setCode(0);
        page.setRoot(list);
        page.setPage(1);
        if (list.size() == 0){
            page.setTotal(0);
        }else {
            page.setTotal(1);
        }
        page.setRecords(list.size());
        return page;
    }
    //验证是否存在流程中数据
    public List<ValidMessage> checkIsInWfl(ValRateSwapInformationDto dto) {
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()){
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("ValRateSwapInformation002");
            parameter.setBusinessDataPk1(dto.getCurveCode());
            parameter.setBusinessDataPk2(dto.getCurveDateStr());
            boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
            if (isInWfl){
                errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0007",dto.getCurveCode(),dto.getCurveDateStr())));
            }
        }
        return errorList;
    }
    
    /**
     *@title: getCurveType
     *@description: 获取利率互换曲线类型（名称）
     *@author: 王贵奇
     *@date: 2019/09/27
     *@return: Page<SelectOptionDto>
     *@throws:
     */
	public Page<SelectOptionDto> getCurveType(Map<String, Object> pageParam) {
		return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValRateSwapInformation/getCurveType"
                , pageParam, SelectOptionDto.class);
	}
}

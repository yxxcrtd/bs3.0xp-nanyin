package com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.dto.ComRateSwapInformationDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComRateSwapInformationService extends CommonExportService<ComRateSwapInformationDto> implements BaseProcessService<ComRateSwapInformationDto> {

//    @Autowired
//    private MstWorkflowCommonService mstWorkflowCommonService;

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
        exportParam.config("/com/joyintech/tams/common/basicdata/marketdata/rateswapinformation/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<ComRateSwapInformationDto> selectPageData(Map<String, Object> selectParam) {
        return selectByParams(selectParam);
    }

    /**
     * 工作流完成
     * @param entity
     * @param type
     */
    @Override
    public void doComplete(ComRateSwapInformationDto entity, OperationType type) {
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
        restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComRateSwapInformation/" + operType, entity, ComRateSwapInformationDto.class);

    }


    /**
     * 工作流发起时调用
     */
    @Override
    public void doInsert(ComRateSwapInformationDto entity, OperationType type) {
        System.out.println("-------------------");
    }

    /**
     *
     * 流程修改时调用
     */
    @Override
    public void doUpdate(ComRateSwapInformationDto dto){

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
    public boolean check(ComRateSwapInformationDto dto) {
        boolean flag = false;
        dto.setDataTime(DictCodeListExt2.利率互换曲线时刻_16_30.getValue());
        List<ComYieldCurveInfoDto> list = restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComRateSwapInformation/check", dto, ComYieldCurveInfoDto.class);
        if (list != null && list.size() > 0){
            flag = true;
        }
        return flag;
    }

    public Page<ComRateSwapInformationDto> selectByParams(Map<String, Object> map) {
        map.put("dataTime",DictCodeListExt2.利率互换曲线时刻_16_30.getValue());
        List<ComRateSwapInformationDto> list = restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComRateSwapInformation/selectByParams", map, ComRateSwapInformationDto.class);
        Page<ComRateSwapInformationDto> page = new Page<>();
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
    //modify jinmaodong 此处代码冗余，是在微服务中进行判断重复
    //验证是否存在流程中数据
//    public List<ValidMessage> checkIsInWfl(RateSwapInformationDto dto) {
//        List<ValidMessage> errorList = new ArrayList<>();
//        if (WorkFlowUtil.isNotFromToDoList()){
//            WflValidInputParameter parameter = new WflValidInputParameter();
//            parameter.setPageId("RateSwapInformation002");
//            parameter.setBusinessDataPk1(dto.getCurveCode());
//            parameter.setBusinessDataPk2(dto.getCurveDateStr());
//            boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
//            if (isInWfl){
//                errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0007",dto.getCurveCode(),dto.getCurveDateStr())));
//            }
//        }
//        return errorList;
//    }

    /**
     *@title: getCurveType
     *@description: 获取利率互换曲线类型（名称）
     *@author: 王贵奇
     *@date: 2019/09/27
     *@return: Page<SelectOptionDto>
     *@throws:
     */
	public Page<SelectOptionDto> getCurveType(Map<String, Object> pageParam) {
		return restTemplate.postForPage(ServiceApplication.CommonBasicDataService
                + "/ComRateSwapInformation/getCurveType"
                , pageParam, SelectOptionDto.class);
	}
}

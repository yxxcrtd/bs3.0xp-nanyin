package com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.service;

//import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.dto.ComRateSwapInformationDto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 1000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "行情单次最多导入1000条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        String totalMsg = "";
        List<List<String >> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 7) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要7列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);
                    //品种 必填
                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("品种 必填！");
                    }
                    //曲线类型 必填
                    if (!StringUtil.isNotEmpty(rowData.get(1))) {
                        errorMsg.append("曲线类型 必填！");
                    }
                    //日期 必填
                    if (!StringUtil.isNotEmpty(rowData.get(2))) {
                        errorMsg.append("日期 必填！");
                    }
                    //标准期限 必填
                    if (!StringUtil.isNotEmpty(rowData.get(3))) {
                        errorMsg.append("标准期限 必填！");
                    }
                    //报买% 必填
                    if (!StringUtil.isNotEmpty(rowData.get(4))) {
                        errorMsg.append("报买% 必填！");
                    }
                    //均值% 必填
                    if (!StringUtil.isNotEmpty(rowData.get(5))) {
                        errorMsg.append("均值% 必填！");
                    }
                    //报卖% 必填
                    if (!StringUtil.isNotEmpty(rowData.get(6))) {
                        errorMsg.append("报卖% 必填！");
                    }
                    if (StringUtil.isNotEmpty(errorMsg.toString())){
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第"+(i+1)+"行数据："+errorMsg.toString());
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())){
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //品种、曲线类型、日期、标准期限，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0))
                                && StringUtil.equals(list1.get(1), list2.get(1))
                                && StringUtil.equals(list1.get(2), list2.get(2))
                                && StringUtil.equals(list1.get(3), list2.get(3))
                        )) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat){
                        repeatNum++;
                    }else {
                        jsonArrays.add(list1);
                    }
                }
            }
        }
        if (repeatNum > 0){
            totalMsg += "导入文件存在"+String.valueOf(repeatNum)+"条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    /**
     * 每日基金行情
     * @param tempList
     * @return
     */
    public ComRateSwapInformationDto getValNetAssetQuotationInfoListData(String tempList) {
        ComRateSwapInformationDto dto = new ComRateSwapInformationDto();
        // 行情信息列表
        List<ComRateSwapInformationDto> comRateSwapInformationDtoList = new ArrayList<>();
        //报买% 01
        List<ComRateSwapInformationDto> comRateSwapInformationDtoList01 = new ArrayList<>();
        //均值% 03
        List<ComRateSwapInformationDto> comRateSwapInformationDtoList03 = new ArrayList<>();
        //报卖% 02
        List<ComRateSwapInformationDto> comRateSwapInformationDtoList02 = new ArrayList<>();
        dto.setSelectedItem(comRateSwapInformationDtoList);
        List<String> objects = JsonUtils.json2Object(tempList,List.class);

        //报买% 01
        for (int i = 0; i < objects.size(); i++) {
//            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            List<String> rowData= JsonUtils.json2Object(objects.get(i),List.class);
            //报买% 01【4】 收益率%
            if (StringUtil.isNotEmpty(rowData.get(4))){
                ComRateSwapInformationDto valFundMarketInfo001dto01 = new ComRateSwapInformationDto();
                Map<String, Object> pageParams = new HashMap<>();
                // 品种【0】
                if (StringUtil.isNotEmpty(rowData.get(0))) {
                    if (StringUtil.equals("SHIBOR3M", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBOR3M.IR");
                    } else if (StringUtil.equals("SHIBORON", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBORON.IR");
                    } else {
                        pageParams.put("baseIntCode", rowData.get(0).toString());
                    }
                }
                // 曲线类型【1】 确定 定盘/收盘 [01:定盘曲线  02:收盘曲线]
                if (StringUtil.isNotEmpty(rowData.get(1))) {
                    pageParams.put("curveIntType", this.getCode(rowData.get(1).toString()));
                }
                // 报买% 01
                pageParams.put("priceType", DictCodeListExt2.价格类型_报买.getValue());
                Page<SelectOptionDto> selectOptionDtoPage01 = this.getCurveType(pageParams);
                List<SelectOptionDto> root01 = selectOptionDtoPage01.getRoot();
                if (!StringUtil.isEmptyList(root01)){
                    SelectOptionDto dto01 = root01.get(0);
                    //曲线代码
                    valFundMarketInfo001dto01.setCurveCode(dto01.getId());
                }
                //曲线类型
                valFundMarketInfo001dto01.setCurveType(DictCodeListExt2.曲线类别_利率互换曲线.getValue());
                // 日期【2】
                if (StringUtil.isNotEmpty(rowData.get(2))){
                    valFundMarketInfo001dto01.setCurveDate(DateUtil.parse(rowData.get(2).toString()));
                }
                // 标准期限【3】
                if (StringUtil.isNotEmpty(rowData.get(3))){
                    valFundMarketInfo001dto01.setTermTypeIrs(rowData.get(3).toString());
                }
                // 报买%【4】 收益率%
                if (StringUtil.isNotEmpty(rowData.get(4))){
                    valFundMarketInfo001dto01.setYield(new BigDecimal(rowData.get(4).toString()));
                }
                comRateSwapInformationDtoList01.add(valFundMarketInfo001dto01);
            }
            //均值% 03
            if (StringUtil.isNotEmpty(rowData.get(5))){
                ComRateSwapInformationDto valFundMarketInfo001dto03 = new ComRateSwapInformationDto();
                Map<String, Object> pageParams = new HashMap<>();
                // 品种【0】
                if (StringUtil.isNotEmpty(rowData.get(0))) {
                    if (StringUtil.equals("SHIBOR3M", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBOR3M.IR");
                    } else if (StringUtil.equals("SHIBORON", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBORON.IR");
                    } else {
                        pageParams.put("baseIntCode", rowData.get(0).toString());
                    }
                }
                // 曲线类型【1】 确定 定盘/收盘 [01:定盘曲线  02:收盘曲线]
                if (StringUtil.isNotEmpty(rowData.get(1))) {
                    pageParams.put("curveIntType", this.getCode(rowData.get(1).toString()));
                }
                // 均值% 03
                pageParams.put("priceType", DictCodeListExt2.价格类型_均值.getValue());
                Page<SelectOptionDto> selectOptionDtoPage03 = this.getCurveType(pageParams);
                List<SelectOptionDto> root03 = selectOptionDtoPage03.getRoot();
                if (!StringUtil.isEmptyList(root03)){
                    SelectOptionDto dto03 = root03.get(0);
                    //曲线代码
                    valFundMarketInfo001dto03.setCurveCode(dto03.getId());
                }
                //曲线类型
                valFundMarketInfo001dto03.setCurveType(DictCodeListExt2.曲线类别_利率互换曲线.getValue());
                // 日期【2】
                if (StringUtil.isNotEmpty(rowData.get(2))){
                    valFundMarketInfo001dto03.setCurveDate(DateUtil.parse(rowData.get(2).toString()));
                }
                // 标准期限【3】
                if (StringUtil.isNotEmpty(rowData.get(3))){
                    valFundMarketInfo001dto03.setTermTypeIrs(rowData.get(3).toString());
                }
                // 均值%【5】 收益率%
                if (StringUtil.isNotEmpty(rowData.get(5))){
                    valFundMarketInfo001dto03.setYield(new BigDecimal(rowData.get(5).toString()));
                }
                comRateSwapInformationDtoList03.add(valFundMarketInfo001dto03);
            }
            //报卖% 02
            if (StringUtil.isNotEmpty(rowData.get(6))){
                ComRateSwapInformationDto valFundMarketInfo001dto02 = new ComRateSwapInformationDto();
                Map<String, Object> pageParams = new HashMap<>();
                // 品种【0】
                if (StringUtil.isNotEmpty(rowData.get(0))) {
                    if (StringUtil.equals("SHIBOR3M", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBOR3M.IR");
                    } else if (StringUtil.equals("SHIBORON", rowData.get(0).toString())) {
                        pageParams.put("baseIntCode", "SHIBORON.IR");
                    } else {
                        pageParams.put("baseIntCode", rowData.get(0).toString());
                    }
                }
                // 曲线类型【1】 确定 定盘/收盘 [01:定盘曲线  02:收盘曲线]
                if (StringUtil.isNotEmpty(rowData.get(1))) {
                    pageParams.put("curveIntType", this.getCode(rowData.get(1).toString()));
                }
                // 报卖% 02
                pageParams.put("priceType", DictCodeListExt2.价格类型_报卖.getValue());
                Page<SelectOptionDto> selectOptionDtoPage02 = this.getCurveType(pageParams);
                List<SelectOptionDto> root02 = selectOptionDtoPage02.getRoot();
                if (!StringUtil.isEmptyList(root02)){
                    SelectOptionDto dto02 = root02.get(0);
                    //曲线代码
                    valFundMarketInfo001dto02.setCurveCode(dto02.getId());
                }
                //曲线类型
                valFundMarketInfo001dto02.setCurveType(DictCodeListExt2.曲线类别_利率互换曲线.getValue());
                // 日期【2】
                if (StringUtil.isNotEmpty(rowData.get(2))){
                    valFundMarketInfo001dto02.setCurveDate(DateUtil.parse(rowData.get(2).toString()));
                }
                // 标准期限【3】
                if (StringUtil.isNotEmpty(rowData.get(3))){
                    valFundMarketInfo001dto02.setTermTypeIrs(rowData.get(3).toString());
                }
                // 报卖%【6】 收益率%
                if (StringUtil.isNotEmpty(rowData.get(6))){
                    valFundMarketInfo001dto02.setYield(new BigDecimal(rowData.get(6).toString()));
                }
                comRateSwapInformationDtoList02.add(valFundMarketInfo001dto02);
            }
        }

        comRateSwapInformationDtoList.addAll(comRateSwapInformationDtoList01);
        comRateSwapInformationDtoList.addAll(comRateSwapInformationDtoList02);
        comRateSwapInformationDtoList.addAll(comRateSwapInformationDtoList03);
        dto.setSelectedItem(comRateSwapInformationDtoList);
        return dto;
    }

    /**
     * 取空格前的数据
     * @param str
     * @return
     */
    private String getCode(String str) {
        return str.split(" ")[0];
    }
    /**
     *
     * @param param
     * @return
     */
    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ComRateSwapInformationDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ComRateSwapInformationDto> selectedItem = dto.getSelectedItem();
        List<ComRateSwapInformationDto>  operateData = new ArrayList<>();
        List<ComRateSwapInformationDto>  existData = new ArrayList<>();
        List<ComRateSwapInformationDto>  newData = new ArrayList<>();
        // 曲线代码-曲线日期
        Map<String, List<ComRateSwapInformationDto>> collect = selectedItem.stream().collect(
                Collectors.groupingBy(
                        score -> score.getCurveCode() + '-' + score.getCurveDate()
                ));
        if (!collect.isEmpty()) {
            for (Map.Entry<String, List<ComRateSwapInformationDto>> entry : collect.entrySet()) {
                ComRateSwapInformationDto tmpDto =  new ComRateSwapInformationDto();
                String curveCodeAndCurveDate = entry.getKey();
                List<ComRateSwapInformationDto> comRateSwapInformationDtos = entry.getValue();
                if (!StringUtil.isEmptyList(comRateSwapInformationDtos)){
                    tmpDto.setCurveCode(comRateSwapInformationDtos.get(0).getCurveCode());
                    tmpDto.setCurveDate(comRateSwapInformationDtos.get(0).getCurveDate());
                    tmpDto.setTermTypeIrs(comRateSwapInformationDtos.get(0).getTermTypeIrs());
                    tmpDto.setYield(comRateSwapInformationDtos.get(0).getYield());
                    tmpDto.setCurveType(comRateSwapInformationDtos.get(0).getCurveType());
                }
                tmpDto.setComRateSwapInformationDtoList(comRateSwapInformationDtos);
                operateData.add(tmpDto);
            }
        }
        for (ComRateSwapInformationDto temp:operateData
        ) {

            boolean isExists = this.check(temp);
            if (isExists){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (ComRateSwapInformationDto existDataDto : existData
        ) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (ComRateSwapInformationDto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }

}

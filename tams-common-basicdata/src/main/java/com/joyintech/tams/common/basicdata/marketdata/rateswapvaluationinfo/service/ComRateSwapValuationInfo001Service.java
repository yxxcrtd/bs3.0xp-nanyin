/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;

/**
 * 利率互换估值信息
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComRateSwapValuationInfo001Service
 * @since 1.0
 */
@Service("comRateSwapValuationInfo001Service")
public class ComRateSwapValuationInfo001Service extends CommonExportService<ComRateSwapValuationInfo001Dto> {

    @Autowired ComRateSwapValuationInfo002Service comRateSwapValuationInfo002Service;
    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<ComRateSwapValuationInfo001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/findPage",
    			params, ComRateSwapValuationInfo001Dto.class);
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ComRateSwapValuationInfo001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/queryAllList",
                params, ComRateSwapValuationInfo001Dto.class);
    }

	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComRateSwapValuationInfo001/getFinprodId2",
                params, SelectOptionDto.class);
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
        List<List<String>> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 5) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要5列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);
                    //交易编号 必填
                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("交易编号 必填！");
                    }
                    //估值日期 必填
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("估值日期 必填！");
                    }
                    //估值 必填
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("估值 必填！");
                    }
                    //币种 必填
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("币种 必填！");
                    }
                    //估值来源 必填
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("估值来源 必填！");
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
                        //交易编号、估值日期、数据来源，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0))
                                && StringUtil.equals(list1.get(1), list2.get(1))
                                && StringUtil.equals(list1.get(4), list2.get(4))
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
     * 利率互换估值信息
     * @param tempList
     * @return
     * @throws IOException 
     */
    public ComRateSwapValuationInfo002Dto getValNetAssetQuotationInfoListData(String tempList) throws IOException {
        ComRateSwapValuationInfo002Dto dto = new ComRateSwapValuationInfo002Dto();
        // 行情信息列表
        List<ComRateSwapValuationInfo002Dto> valRateSwapValuationInfo002DtoList = new ArrayList<>();
        dto.setSelectedItem(valRateSwapValuationInfo002DtoList);
        List<String> objects = JsonUtils.json2List(tempList,String.class);

        for (int i = 0; i < objects.size(); i++) {
//            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
        	List<String> rowData = JsonUtils.json2List(objects.get(i),String.class);
        	ComRateSwapValuationInfo002Dto valRateSwapValuationInfo002Dto = new ComRateSwapValuationInfo002Dto();
            // 交易编号【0】
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                valRateSwapValuationInfo002Dto.setFinprodId2(rowData.get(0).toString());
            }
            // 估值日期【1】
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                valRateSwapValuationInfo002Dto.setValDate(DateUtil.format(DateUtil.parse(rowData.get(1).toString())));
            }
            // 估值【2】
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                valRateSwapValuationInfo002Dto.setValuation(new BigDecimal(rowData.get(2).toString()));
            }
            // 币种【3】
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                valRateSwapValuationInfo002Dto.setCcy(this.getCode(rowData.get(3).toString()));
            }
            // 估值来源【4】
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                valRateSwapValuationInfo002Dto.setDatasourceType(this.getCode(rowData.get(4).toString()));
            }
            valRateSwapValuationInfo002Dto.setDatasourceTypeOld(valRateSwapValuationInfo002Dto.getDatasourceType());
            // 维护方式【默认为手工录入 {"id":"01","text":"手工录入","col":"INPUT_TYPE"}】
            valRateSwapValuationInfo002Dto.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
            valRateSwapValuationInfo002DtoList.add(valRateSwapValuationInfo002Dto);
        }
        dto.setSelectedItem(valRateSwapValuationInfo002DtoList);
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

    public AjaxResult importFile(Map<String, Object> param) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ComRateSwapValuationInfo002Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ComRateSwapValuationInfo002Dto> selectedItem = dto.getSelectedItem();
        List<ComRateSwapValuationInfo002Dto>  existData = new ArrayList<>();
        List<ComRateSwapValuationInfo002Dto>  newData = new ArrayList<>();
        for (ComRateSwapValuationInfo002Dto temp:selectedItem
        ) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("finprodId2", temp.getFinprodId2());
            map.put("datasourceType", temp.getDatasourceType());
            map.put("valDate", temp.getValDate());
            ComRateSwapValuationInfo002Dto entity = comRateSwapValuationInfo002Service.findByPrimaryKey(map);
            //数据已存在 【交易编号、估值日期、估值来源】，做更新操作。
            if (StringUtil.isNotEmpty(entity)){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (ComRateSwapValuationInfo002Dto existDataDto : existData
        ) {
            comRateSwapValuationInfo002Service.doComplete(existDataDto, OperationType.MODIFY);
        }
        //插入操作
        for (ComRateSwapValuationInfo002Dto newDataDto : newData
        ) {
            comRateSwapValuationInfo002Service.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }

}

package com.joyintech.tams.common.basicdata.marketdata.mstrateassetquotationinfo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 文件名：MstNetAssetQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.mstrateassetquotationinfo.dto.ComRateAssetQuotationInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 计划类（利率型 ）资产估值行情信息查询service
 * 
 * @author liuwei
 * @version 1.0 2020年10月20日
 * @see ComRateAssetQuotationInfo001Service
 * @since 1.0
 */

@Service
@Transactional
public class ComRateAssetQuotationInfo001Service extends CommonExportService<ComRateAssetQuotationInfo001Dto>
    implements BaseProcessService<ComRateAssetQuotationInfo001Dto> {


    /**
     * 查询按钮点击事件 通过条件查询数据
     * 
     * @param pageParam 页面参数
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ComRateAssetQuotationInfo001Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("PROFITTYPE_VALUE_02", DictCodeList.收益类型_收益型.getValue());
        // 估值不接入finprod_type2，故用估值的金融产品类型做判断
        pageParam.put("TYPE_GUZHI_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return restTemplate.postForPage(
            ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/selectForPage", pageParam,
                ComRateAssetQuotationInfo001Dto.class);
    }


    @Override
    public void doComplete(ComRateAssetQuotationInfo001Dto entity, OperationType operationType) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "delete";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/" + action, entity,
                Integer.class);
    }

    @Override
    public void doInsert(ComRateAssetQuotationInfo001Dto entity, OperationType type) {

    }

    @Override
    public void doUpdate(ComRateAssetQuotationInfo001Dto entity) {

    }

    @Override
    public void doDelete(String workflowId) {

    }

    public List<SuggestDto> getAssetCode(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/getAssetCode", map,
                SuggestDto.class);
    }

    public List<SuggestDto> getAssetName(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/getAssetName", map,
                SuggestDto.class);
    }

    public ComRateAssetQuotationInfo001Dto selectMstRateAsset(Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/selectMstRateAsset",
                param, ComRateAssetQuotationInfo001Dto.class);
    }

    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/getAssetCodeList", param,
                SelectOptionDto.class);
    }

    public ComRateAssetQuotationInfo001Dto getFinProdNameAndFinprodId(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                        + "/ComRateAssetQuotationInfo/getFinProdNameAndFinprodId",
                map, ComRateAssetQuotationInfo001Dto.class);
    }

    public List<ComRateAssetQuotationInfo001Dto> selectByIdAndDate(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/selectByIdAndDate", param,
                ComRateAssetQuotationInfo001Dto.class);
    }

    public List<ComRateAssetQuotationInfo001Dto> selectForExport(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComRateAssetQuotationInfo/selectForExport", param,
                ComRateAssetQuotationInfo001Dto.class);
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
            if (null == objects.get(0) || objects.get(0).size() != 6) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要6列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);
                    //资产代码 必填
                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("资产代码 必填！");
                    }
                    //估值日期 必填
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("估值日期 必填！");
                    }
                    //估值 必填
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("估值 必填！");
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
                        //资产代码、估值日期，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)) && StringUtil.equals(list1.get(2), list2.get(2)))) {
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
     * 计划类净值/货币资产估值行情
     * @param tempList
     * @return
     */
    public ComRateAssetQuotationInfo001Dto getValNetAssetQuotationInfoListData(String tempList) {
        ComRateAssetQuotationInfo001Dto dto = new ComRateAssetQuotationInfo001Dto();
        // 行情信息列表
        List<ComRateAssetQuotationInfo001Dto> valNetAssetQuotationInfoList = new ArrayList<>();
        dto.setSelectedItem(valNetAssetQuotationInfoList);
        List<Object> objects;
		try {
			objects = JsonUtils.json2List(tempList, Object.class);
	        for (int i = 0; i < objects.size(); i++) {
	            List<String> rowData = JsonUtils.json2List(JsonUtils.object2Json(objects.get(i)), String.class);  
	            ComRateAssetQuotationInfo001Dto mstRateAssetQuotationInfo001Dto = new ComRateAssetQuotationInfo001Dto();
	
	            // 资产代码【0】
	            if (StringUtil.isNotEmpty(rowData.get(0))) {
	                mstRateAssetQuotationInfo001Dto.setFinprodId(rowData.get(0).toString());
	            }
	            // 资产简称【1】
	            if (StringUtil.isNotEmpty(rowData.get(1))) {
	                mstRateAssetQuotationInfo001Dto.setFinProdName(rowData.get(1).toString());
	            }
	            // 估值日期【2】
	            if (StringUtil.isNotEmpty(rowData.get(2))) {
	                mstRateAssetQuotationInfo001Dto.setValDate(DateUtil.parse(rowData.get(2).toString()));
	            }
	            // 估值【3】
	            if (StringUtil.isNotEmpty(rowData.get(3))) {
	                mstRateAssetQuotationInfo001Dto.setValuation(new BigDecimal(rowData.get(3).toString()));
	            }
	            // 导入日【4】
	            if (StringUtil.isNotEmpty(rowData.get(4))) {
	                mstRateAssetQuotationInfo001Dto.setPublishDate(DateUtil.parse(rowData.get(4).toString()));
	            }
	            // 备注【5】
	            if (StringUtil.isNotEmpty(rowData.get(5))) {
	                mstRateAssetQuotationInfo001Dto.setRemark(rowData.get(5).toString());
	            }
	            Map<String, Object> paramMap = new HashMap<>();
	            paramMap.put("finProdMarketId", mstRateAssetQuotationInfo001Dto.getFinprodId());
	            paramMap.put("profitType", DictCodeList.收益类型_收益型.getValue());
	            ComRateAssetQuotationInfo001Dto tmpDto = getFinProdNameAndFinprodId(paramMap);
	            if (StringUtil.isNotEmpty(tmpDto)){
	                mstRateAssetQuotationInfo001Dto.setFinprodType2(tmpDto.getFinprodType2());
	                mstRateAssetQuotationInfo001Dto.setFinprodType(tmpDto.getFinprodType());
	            }
	            valNetAssetQuotationInfoList.add(mstRateAssetQuotationInfo001Dto);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dto.setSelectedItem(valNetAssetQuotationInfoList);
        return dto;
        
        
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ComRateAssetQuotationInfo001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ComRateAssetQuotationInfo001Dto> selectedItem = dto.getSelectedItem();
        List<ComRateAssetQuotationInfo001Dto>  existData = new ArrayList<>();
        List<ComRateAssetQuotationInfo001Dto>  newData = new ArrayList<>();
        for (ComRateAssetQuotationInfo001Dto temp:selectedItem
        ) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("finProdMarketId", temp.getFinprodId());
            paramMap.put("profitType", DictCodeList.收益类型_收益型.getValue());
            ComRateAssetQuotationInfo001Dto tmpDto = getFinProdNameAndFinprodId(paramMap);

            Map<String, Object> map = new HashMap<>();
            map.put("finprodId",temp.getFinprodId());
            if (StringUtil.isNotEmpty(tmpDto)){
                map.put("finprodType2", tmpDto.getFinprodType2());
                /** 按照估值类型来查询 modify by yanya on 20210329 **/
                map.put("finprodType", tmpDto.getFinprodType());
            }
            map.put("valDate",DateUtil.format(temp.getValDate()));
            List<ComRateAssetQuotationInfo001Dto> mstRateAssetQuotationInfo001DtoList = this.selectByIdAndDate(map);
            //数据已存在 【资产代码、估值日 相同】，做更新操作。
            if (!StringUtil.isEmptyList(mstRateAssetQuotationInfo001DtoList)){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (ComRateAssetQuotationInfo001Dto existDataDto : existData
        ) {
            existDataDto.setFinProdMarketId(existDataDto.getFinprodId());
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (ComRateAssetQuotationInfo001Dto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }
}

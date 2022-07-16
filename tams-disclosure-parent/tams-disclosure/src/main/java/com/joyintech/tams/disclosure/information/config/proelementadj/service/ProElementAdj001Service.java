package com.joyintech.tams.disclosure.information.config.proelementadj.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProElementAdj001Service extends CommonExportService<ProElementAdj001Dto> implements BaseProcessService<ProElementAdj001Dto> {


    public Page<ProElementAdj001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/findPage",
                pageParam, ProElementAdj001Dto.class);
    }

    @Override
    public void doComplete(ProElementAdj001Dto proElementAdj001Dto, OperationType operationType) {
        proElementAdj001Dto.setOperateType(operationType.toString());
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/doComplete",
                proElementAdj001Dto, Integer.class);

    }

    public ProElementAdj001Dto selectByPrimaryKey(String prodRegistNo) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/selectByPrimaryKey", prodRegistNo,
                ProElementAdj001Dto.class);
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/proelementadj/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<ProElementAdj001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    public Page<SelectOptionDto> getRegistNo(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ProElementAdj/getRegistNo",map,SelectOptionDto.class);
    }


    public Page<SelectOptionDto> getProdId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ProElementAdj/getProdId",map,SelectOptionDto.class);
    }
    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
//        List<String> findList =  this.queryFinprod(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 50000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "单次最多导入50000条。");
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
            if (null == objects.get(0) || objects.get(0).size() != 15) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要15列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);

                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("数据状态 必填！");
                    }
//                    else if(!findList.contains(rowData.get(0))){
//                        errorMsg.append("基金代码不存在！");
//                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("产品登记编码 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("子产品代码 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("是否有代销机构 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("此产品要素调整描述文字1 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5))){
                        errorMsg.append("此产品要素调整描述文字2 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6))){
                        errorMsg.append("调整要素 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(7))){
                        errorMsg.append("调整前 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(8))){
                        errorMsg.append("调整后 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(9))){
                        errorMsg.append("生效时间 必填！");
                    }else if(!this.isDateStr(rowData.get(9))){
                        errorMsg.append("生效时间 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(10))){
                        errorMsg.append("调整此要素（补充说明文1） 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(11))){
                        errorMsg.append("调整此要素（补充说明文2） 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(12))){
                        errorMsg.append("报告发布日期 必填！");
                    }else if(!this.isDateStr(rowData.get(12))){
                        errorMsg.append("报告发布日期 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(13))){
                        errorMsg.append("更新时间 必填！");
                    }else if(!this.isDateStr(rowData.get(13))){
                        errorMsg.append("更新时间 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(14))){
                        errorMsg.append("更新人 必填！");
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
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //产品登记编码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(1), list2.get(1)))) {
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

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }


    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ProElementAdj001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProElementAdj001Dto> selectedItem = dto.getSelectedItem();
        List<ProElementAdj001Dto>  existData = new ArrayList<>();
        List<ProElementAdj001Dto>  newData = new ArrayList<>();
        for (ProElementAdj001Dto temp:selectedItem) {
            //数据已存在 【产品登记编码 相同】，做更新操作。
            if (StringUtil.isNotEmpty(this.selectByProdRegistNo(temp.getProdRegistNo()))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (ProElementAdj001Dto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (ProElementAdj001Dto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public ProElementAdj001Dto getValNetAssetQuotationInfoListData(String tempList) {
        ProElementAdj001Dto dto = new ProElementAdj001Dto();
        // 行情信息列表
        List<ProElementAdj001Dto> proElementAdj001DtoList = new ArrayList<>();
        dto.setSelectedItem(proElementAdj001DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            ProElementAdj001Dto proElementAdj001Dto = new ProElementAdj001Dto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                proElementAdj001Dto.setDataStatus(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                proElementAdj001Dto.setProdRegistNo(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                proElementAdj001Dto.setProdId(rowData.get(2).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                proElementAdj001Dto.setIsSalesAgency(rowData.get(3).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                proElementAdj001Dto.setProdAdjRemark(rowData.get(4).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                proElementAdj001Dto.setProdAdjRemark2(rowData.get(5).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                proElementAdj001Dto.setAdjField(rowData.get(6).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                proElementAdj001Dto.setPreAdjust(rowData.get(7).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(8))) {
                proElementAdj001Dto.setAfterAdjust(rowData.get(8).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(9))) {
                proElementAdj001Dto.setEffDate(DateUtil.parse(rowData.get(9).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(10))) {
                proElementAdj001Dto.setFieldAdjRemark(rowData.get(10).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(11))) {
                proElementAdj001Dto.setFieldAdjRemark2(rowData.get(11).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(12))) {
                proElementAdj001Dto.setRptIssueDate(DateUtil.parse(rowData.get(12).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(13))) {
                proElementAdj001Dto.setUpdateTime(DateUtil.parse(rowData.get(13).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(14))) {
                proElementAdj001Dto.setUpdateUser(rowData.get(14).toString());
            }
            proElementAdj001DtoList.add(proElementAdj001Dto);
        }
        dto.setSelectedItem(proElementAdj001DtoList);
        return dto;
    }


    public String selectByProdRegistNo(String prodRegistNo) {
        Map<String,Object>map=new HashMap<>();
        map.put("prodRegistNo", prodRegistNo);
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/selectByProdRegistNo",
                map, String.class);
    }
}

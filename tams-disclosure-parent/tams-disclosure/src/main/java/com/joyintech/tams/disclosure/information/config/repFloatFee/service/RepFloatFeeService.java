package com.joyintech.tams.disclosure.information.config.repFloatFee.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dao.RepFloatFee001Mapper;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RepFloatFeeService extends CommonExportService<RepFloatFeeDto> implements BaseProcessService<RepFloatFeeDto> {
    @Autowired
    private RepFloatFee001Mapper repFloatFee001Mapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
//        return repFloatFee001Mapper.getProdId(map);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> repFloatFee001Mapper.getProdId(map));
        return result;
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repFloatFee001Mapper.getProdId2(map);
    }

    public Page<RepFloatFeeDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepFloatFeeDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repFloatFee001Mapper.selectInfo(param));
        return result;
    }
    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepFloatFeeDto selectData(Map<String, Object> param) {
        return repFloatFee001Mapper.selectData(param);
    }

    @Override
    public void doComplete(RepFloatFeeDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repFloatFee001Mapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repFloatFee001Mapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {

            repFloatFee001Mapper.delete(entity);
        }
    }

    public Map<String,String> selectProdName(String prodId) {
        Map<String, String> map = repFloatFee001Mapper.selectProdName(prodId);
        if (map==null){
            map = new HashMap<>();
            map.put("PROD_NAME", "");
        }
        return map;
    }
    public RepFloatFeeDto selectByProdIdAndDate(RepFloatFeeDto dto) {
        return repFloatFee001Mapper.selectByProdIdAndDate(dto);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repFloatFee/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepFloatFeeDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }

    public Page<RepFloatFeeDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepFloatFeeDto> result = queryForPage(()
                -> repFloatFee001Mapper.selectInfo(param));
        return result;
    }


    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
//        if (objects.size() - 1 > 50000) {
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("errorMsg", "单次最多导入50000条。");
//            AjaxResult wr = new AjaxResult();
//            wr.setData(resultMap);
//            return wr;
//        }
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

                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("子产品代码 必填！");
                    }else if(!this.getProdId(rowData.get(0))){
                        errorMsg.append("子产品代码未在系统中维护！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("产品名称 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("确认日 必填！");
                    }else if(!this.isDateStr(rowData.get(2))){
                        errorMsg.append("确认日 格式不正确！");
                    }else if(rowData.get(2).compareTo(DateUtil.format(DateUtil.platDateFromCache())) > 0){
                        errorMsg.append("确认日 只能小于等于平台日！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("业务代码 必填！");
                    }else if(!rowData.get(3).equals("赎回")){
                        errorMsg.append("业务代码 默认赎回！");
                    }
                    if(!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("确认份额 必填！");
                    }else if(!this.isBigDecimal(rowData.get(4))){
                        errorMsg.append("确认份额 格式不正确！");
                    }else if(new BigDecimal(rowData.get(4)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("确认份额需要大于0!");
                    }
                    if(!StringUtil.isNotEmpty(rowData.get(5))){
                        errorMsg.append("确认金额 必填！");
                    }else if(!this.isBigDecimal(rowData.get(5))){
                        errorMsg.append("确认金额 格式不正确！");
                    }else if(new BigDecimal(rowData.get(5)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("确认金额需要大于0!");
                    }
                    if(!StringUtil.isNotEmpty(rowData.get(6))){
                        errorMsg.append("超额报酬 必填！");
                    }else if(!this.isBigDecimal(rowData.get(6))){
                        errorMsg.append("超额报酬 格式不正确！");
                    }else if(new BigDecimal(rowData.get(6)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("超额报酬需要大于0!");
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
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))
                                &&(StringUtil.equals(list1.get(2), list2.get(2)))) {
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
        String tempList = (String) resultMap.get("correctData");
        RepFloatFeeDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepFloatFeeDto> selectedItem = dto.getSelectedItem();
        List<RepFloatFeeDto>  existData = new ArrayList<>();
        for (RepFloatFeeDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("affirmDate",temp.getAffirmDate());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }
        }
        if(existData.size() > 0){
            resultMap.put("confirmMsg2", "有重复信息，是否覆盖？");
        }
        int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null?50000:Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
        if (objects.size() - 1 > maxCount) {
            resultMap.put("confirmMsg3", "导入数据超过"+maxCount+"条，可能导致系统较慢，是否确认导入？");
        }
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    public Boolean getProdId(String prodId) {
        String r = repFloatFee001Mapper.selectByProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
    public static boolean isBigDecimal(String str) {
        Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher m = NUMERIC_PATTERN.matcher(str);
        if (m.find()) {
            return true;
        }else{
            return false;
        }
    }

    public RepFloatFeeDto getValNetAssetQuotationInfoListData(String tempList) {
        RepFloatFeeDto dto = new RepFloatFeeDto();
        List<RepFloatFeeDto> repFloatFeeDtoList = new ArrayList<>();
        dto.setSelectedItem(repFloatFeeDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            RepFloatFeeDto repFloatFeeDto = new RepFloatFeeDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                repFloatFeeDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                repFloatFeeDto.setProdName(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                repFloatFeeDto.setAffirmDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                repFloatFeeDto.setBusinessCode("03");
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                repFloatFeeDto.setAffirmNum(new BigDecimal(rowData.get(4).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                repFloatFeeDto.setAffirmAmt(new BigDecimal(rowData.get(5).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                repFloatFeeDto.setAboveAmt(new BigDecimal(rowData.get(6).toString()));
            }
            repFloatFeeDto.setStatus("E");
            repFloatFeeDtoList.add(repFloatFeeDto);
        }
        dto.setSelectedItem(repFloatFeeDtoList);
        return dto;
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        RepFloatFeeDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepFloatFeeDto> selectedItem = dto.getSelectedItem();
        List<RepFloatFeeDto>  existData = new ArrayList<>();
        List<RepFloatFeeDto>  newData = new ArrayList<>();
        for (RepFloatFeeDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("affirmDate",temp.getAffirmDate());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (RepFloatFeeDto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }

        //插入操作
        for (RepFloatFeeDto newDataDto : newData) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }

    public RepFloatFeeDto checkExistData(RepFloatFeeDto dto) {
        return repFloatFee001Mapper.checkExistData(dto);
    }
}

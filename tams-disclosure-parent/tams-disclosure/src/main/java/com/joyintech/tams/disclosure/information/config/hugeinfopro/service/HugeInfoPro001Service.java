package com.joyintech.tams.disclosure.information.config.hugeinfopro.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dao.HugeInfoProMapper;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HugeInfoPro001Service extends CommonExportService<HugeInfoPro001Dto> implements BaseProcessService<HugeInfoPro001Dto> {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HugeInfoProMapper hugeInfoProMapper;

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;
    public Page<HugeInfoPro001Dto> selectForPage(Map<String, Object> param) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String prodRegistNo = (String)param.get("prodRegistNo");
        if(StringUtil.isNotEmpty(prodRegistNo)){
            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodRegistNo));
        }
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Page<HugeInfoPro001Dto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> hugeInfoProMapper.select(param));
        return result;
    }

    public HugeInfoPro001Dto selectByPrimaryKey(String prodRegistNo) {
        return hugeInfoProMapper.selectByPrimaryKey(prodRegistNo);
    }


    public String selectByProdRegistNo(Map<String, Object> map) {
        return hugeInfoProMapper.selectByProdRegistNo(map);
    }


    @Override
    public void doComplete(HugeInfoPro001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            hugeInfoProMapper.deleteByKey(dto.getRedemptionId());
        }
        else if (StringUtil.equals(type, "NEW")) {
            HugeInfoPro001Dto hugeInfoPro001Dto = new HugeInfoPro001Dto();
            BeanUtil.copyPropertiesIgnoreNull(hugeInfoPro001Dto,dto);
            hugeInfoPro001Dto.setDataStatus("01");
            hugeInfoProMapper.insert(hugeInfoPro001Dto);
        }
        else {
            dto.setDataStatus("01");
            hugeInfoProMapper.updateByKey(dto);
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/hugeinfopro/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<HugeInfoPro001Dto> selectPageData(Map<String, Object> selectParam) {
        return queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> hugeInfoProMapper.select(selectParam));
    }



    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<String> findProdRegistNoList =  proElementAdjMapper.getRegistNo(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
        List<String> findProdList =  proElementAdjMapper.getProdId(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());        List<List<String>> objects = ExcelUtil.getTempList(fileName);
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
            if (null == objects.get(0) || objects.get(0).size() != 9) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要9列。");
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
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("产品登记编码 必填！");
                    }
                    else if(!findProdRegistNoList.contains(rowData.get(1))){
                        errorMsg.append("产品登记编码未在产品基本信息表中维护！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("子产品代码 必填！");
                    }
                    else if(!findProdList.contains(rowData.get(2))){
                        errorMsg.append("子产品代码未在产品基本信息表中维护！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("巨额赎回日期 必填！");
                    }else if(!this.isDateStr(rowData.get(3))){
                        errorMsg.append("巨额赎回日期 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("触发的情形说明文字 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5))){
                        errorMsg.append("拒绝赎回说明（文字） 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6))){
                        errorMsg.append("报告发布日期 必填！");
                    }else if(!this.isDateStr(rowData.get(6))){
                        errorMsg.append("报告发布日期 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(7))){
                        errorMsg.append("更新时间 必填！");
                    }else if(!this.isDateStr(rowData.get(7))){
                        errorMsg.append("更新时间 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(8))){
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
        HugeInfoPro001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<HugeInfoPro001Dto> selectedItem = dto.getSelectedItem();
        List<HugeInfoPro001Dto>  existData = new ArrayList<>();
        List<HugeInfoPro001Dto>  newData = new ArrayList<>();
        for (HugeInfoPro001Dto temp:selectedItem) {
//            数据已存在 【产品登记编码 相同】，做更新操作。
//            if (StringUtil.isNotEmpty(this.selectByProdRegistNo(temp.getProdRegistNo()))){
//                existData.add(temp);
//            }else {
//                newData.add(temp);
//            }
            temp.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
            temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
            temp.setRedemptionId(IDGenerator.generate(36));
            newData.add(temp);
        }
        //更新操作
//        for (HugeInfoPro001Dto existDataDto : newData) {
//            this.doComplete(existDataDto,OperationType.MODIFY);
//        }
        //插入操作
        for (HugeInfoPro001Dto newDataDto : newData
        ) {

            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public HugeInfoPro001Dto getValNetAssetQuotationInfoListData(String tempList) {
        HugeInfoPro001Dto dto = new HugeInfoPro001Dto();
        // 行情信息列表
        List<HugeInfoPro001Dto> hugeInfoPro001DtoList = new ArrayList<>();
        dto.setSelectedItem(hugeInfoPro001DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            HugeInfoPro001Dto hugeInfoPro001Dto = new HugeInfoPro001Dto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                hugeInfoPro001Dto.setDataStatus(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                hugeInfoPro001Dto.setProdRegistNo(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                hugeInfoPro001Dto.setProdId(rowData.get(2).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                hugeInfoPro001Dto.setDealDate(DateUtil.parse(rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                hugeInfoPro001Dto.setTriggerRemark(rowData.get(4).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                hugeInfoPro001Dto.setRejectRemark(rowData.get(5).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                hugeInfoPro001Dto.setRptIssueDate(DateUtil.parse(rowData.get(6).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                hugeInfoPro001Dto.setUpdateTime(DateUtil.parse(rowData.get(7).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(8))) {
                hugeInfoPro001Dto.setUpdateUser(rowData.get(8).toString());
            }
            hugeInfoPro001DtoList.add(hugeInfoPro001Dto);
        }
        dto.setSelectedItem(hugeInfoPro001DtoList);
        return dto;
    }


    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->hugeInfoProMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->hugeInfoProMapper.getProdIdlist(map));
    }
}
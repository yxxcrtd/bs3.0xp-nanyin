package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.service;


import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dao.SvrProdFeeDealInterfMapper;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SvrProdFeeDealInterfService extends CommonExportService<SvrProdFeeDealInterfDto> implements BaseProcessService<SvrProdFeeDealInterfDto> {

    @Autowired
    private SvrProdFeeDealInterfMapper svrProdFeeDealInterfMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @param
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        return svrProdFeeDealInterfMapper.getProdInsId(map);
    }

    /**
     * 产品信息数据操作 <功能详细描述>
     *
     * @param entity 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Override
    public void doComplete(SvrProdFeeDealInterfDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            svrProdFeeDealInterfMapper.insertPrdFeeDeal(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            svrProdFeeDealInterfMapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {

            svrProdFeeDealInterfMapper.delete(entity);
        }
    }

    /**
     * 产品交易信息查询 <功能详细描述>
     *
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SvrProdFeeDealInterfDto> selectInfo(Map<String, Object> param) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SvrProdFeeDealInterfDto> result = queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> svrProdFeeDealInterfMapper.selectInfo(param));
        return result;
    }

    public Page<SvrProdFeeDealInterfDto> selectForExport(Map<String, Object> param) {
//        List<SvrProdFeeDealInterfDto> entityList = new ArrayList<>();
//        if (param.containsKey("selectData")) {
//            String keyList = param.get("selectData").toString();
//            List<SvrProdFeeDealInterfDto> investList = null;
//            try {
//                investList = JsonUtils.json2List(keyList,
//                        SvrProdFeeDealInterfDto.class);
//                entityList = svrProdFeeDealInterfMapper.selectForExport(investList);
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        } else {
//            entityList = svrProdFeeDealInterfMapper.selectInfo(param);
//        }
//        return entityList;
        Page<SvrProdFeeDealInterfDto> result = queryForPage(() -> svrProdFeeDealInterfMapper.selectInfo(param));
        return result;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/prodfeedealInterf/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<SvrProdFeeDealInterfDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }
    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public SvrProdFeeDealInterfDto selectData(Map<String, Object> param) {
        return svrProdFeeDealInterfMapper.selectData(param);
    }
    public SvrProdFeeDealInterfDto selectByProdID(SvrProdFeeDealInterfDto dto) {
        return svrProdFeeDealInterfMapper.selectByProdID(dto);
    }
    public Map<String,String> selectProdName(String prodId) {
        Map<String, String> map = svrProdFeeDealInterfMapper.selectProdName(prodId);
        if (map==null){
            map = new HashMap<>();
            map.put("PROD_NAME", "");
        }
        return map;
    }



    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
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

                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("产品主代码 必填！");
                    }else if(!this.getProdId(rowData.get(0))){
                        errorMsg.append("产品主代码未在系统中维护！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("费用类型 必填！");
                    }else if((rowData.get(1).trim().equals("申购费") ||
                            rowData.get(1).trim().equals("赎回费") ||
                            rowData.get(1).trim().equals("客户收益")) &&
                            (!StringUtil.isNotEmpty(rowData.get(2)))){
                        errorMsg.append("当费用类型为“申购费”“赎回费”“客户收益”时费用金额不能为空!");
                    }else if((rowData.get(1).trim().equals("托管费") ||
                            rowData.get(1).trim().equals("管理费（固定）") ||
                            rowData.get(1).trim().equals("管理费（超额）")||
                            rowData.get(1).trim().equals("销售费")) &&
                            (!StringUtil.isNotEmpty(rowData.get(3)))){
                        errorMsg.append("当费用类型为“托管费”“管理费（固定）”“管理费（赎回）”“销售费”时费用金额不能为空!");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("支付日 必填！");
                    }else if(!this.isDateStr(rowData.get(4))){
                        errorMsg.append("支付日 格式不正确！");
                    }else if(rowData.get(4).compareTo(DateUtil.format(DateUtil.platDateFromCache())) > 0){
                        errorMsg.append("支付日 只能小于等于平台日！");
                    }
                    if(StringUtil.isNotEmpty(rowData.get(3))){
                        if(!this.isBigDecimal(rowData.get(3))){
                            errorMsg.append("自成立以来发生额（贷方） 格式不正确！");
                        }
                    }
                    if(StringUtil.isNotEmpty(rowData.get(2))){
                        if(!this.isBigDecimal(rowData.get(2))){
                            errorMsg.append("费用金额 格式不正确！");
                        }
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
                        //产品主代码，费用类型,支付日相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))
                             &&(StringUtil.equals(list1.get(1), list2.get(1)))
                                &&(StringUtil.equals(list1.get(4), list2.get(4)))) {
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
//        if (repeatNum > 0){
//            totalMsg += "导入文件存在"+String.valueOf(repeatNum)+"条数据重复，重复数据不导入，是否确认？";
//        }
        Map<String, Object> resultMap = new HashMap<>();
//        if (StringUtil.isNotBlank(totalMsg)) {
//            resultMap.put("confirmMsg", totalMsg);
//        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        String tempList = (String) resultMap.get("correctData");
        SvrProdFeeDealInterfDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<SvrProdFeeDealInterfDto> selectedItem = dto.getSelectedItem();
        List<SvrProdFeeDealInterfDto>  existData = new ArrayList<>();
        List<SvrProdFeeDealInterfDto>  newData = new ArrayList<>();
        for (SvrProdFeeDealInterfDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("feeType",temp.getFeeType());
            map.put("payDate",temp.getPayDate());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        if(existData.size() > 0){
            resultMap.put("confirmMsg", "有重复信息，是否覆盖？");
        }

        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
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


    public Boolean getProdId(String prodId) {
        String r = svrProdFeeDealInterfMapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        Boolean flag = (Boolean) param.get("flag");
        SvrProdFeeDealInterfDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<SvrProdFeeDealInterfDto> selectedItem = dto.getSelectedItem();
        List<SvrProdFeeDealInterfDto>  existData = new ArrayList<>();
        List<SvrProdFeeDealInterfDto>  newData = new ArrayList<>();
        for (SvrProdFeeDealInterfDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("feeType",temp.getFeeType());
            map.put("payDate",temp.getPayDate());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        if(flag){
            for (SvrProdFeeDealInterfDto existDataDto : existData) {
                this.doComplete(existDataDto,OperationType.MODIFY);
            }
        }

        //插入操作
        for (SvrProdFeeDealInterfDto newDataDto : newData) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }

    public SvrProdFeeDealInterfDto getValNetAssetQuotationInfoListData(String tempList) {
        SvrProdFeeDealInterfDto dto = new SvrProdFeeDealInterfDto();
        List<SvrProdFeeDealInterfDto> svrProdFeeDealInterfDtoList = new ArrayList<>();
        dto.setSelectedItem(svrProdFeeDealInterfDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            SvrProdFeeDealInterfDto svrProdFeeDealInterfDto = new SvrProdFeeDealInterfDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                svrProdFeeDealInterfDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                svrProdFeeDealInterfDto.setFeeType(this.getFeeTYPE(rowData.get(1).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                svrProdFeeDealInterfDto.setPaidFee(new BigDecimal(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                svrProdFeeDealInterfDto.setAccrual(new BigDecimal(rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                svrProdFeeDealInterfDto.setPayDate(DateUtil.parse(rowData.get(4).toString()));
            }
            svrProdFeeDealInterfDto.setStatus("E");
            svrProdFeeDealInterfDtoList.add(svrProdFeeDealInterfDto);
        }
        dto.setSelectedItem(svrProdFeeDealInterfDtoList);
        return dto;
    }

    public String getFeeTYPE(String feetype) {
        return svrProdFeeDealInterfMapper.getFeeTYPE(feetype);
    }
}

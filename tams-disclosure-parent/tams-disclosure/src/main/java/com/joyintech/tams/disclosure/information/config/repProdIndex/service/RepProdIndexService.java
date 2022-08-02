package com.joyintech.tams.disclosure.information.config.repProdIndex.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dao.RepProdIndexSMapper;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
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
public class RepProdIndexService extends CommonExportService<RepProdIndexDto> implements BaseProcessService<RepProdIndexDto> {

    @Autowired
    private RepProdIndexSMapper repProdIndexSMapper;
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @param
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
//        return repProdIndexSMapper.getProdId(map);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> repProdIndexSMapper.getProdId(map));
        return result;
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repProdIndexSMapper.getProdId2(map);
    }

    public Page<RepProdIndexDto> selectInfo(Map<String, Object> param) {
        Page<RepProdIndexDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repProdIndexSMapper.selectInfo(param));
        return result;
    }

    public Page<RepProdIndexDto> selectForExport(Map<String, Object> param) {
        Page<RepProdIndexDto> result = queryForPage( ()
                -> repProdIndexSMapper.selectInfo(param));
        return result;
    }

    @Override
    public void doComplete(RepProdIndexDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if(StringUtil.isEmpty(entity.getCreateDept())){
            entity.setCreateDept("");
        }
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repProdIndexSMapper.insert(entity);
            repProdIndexSMapper.insert2(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repProdIndexSMapper.update(entity);
            repProdIndexSMapper.update2(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            repProdIndexSMapper.delete(entity);
            int r = repProdIndexSMapper.selectExits(entity);
            if(r == 0){
                repProdIndexSMapper.delete2(entity);
            }
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repProdIndex/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepProdIndexDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }


    public RepProdIndexDto selectProdInfo(String prodId) {
        return repProdIndexSMapper.selectProdInfo(prodId);
    }

    public RepProdIndexDto selectByProdIDAndValueDate(RepProdIndexDto dto) {
        return repProdIndexSMapper.selectByProdIDAndValueDate(dto);
    }

    public RepProdIndexDto selectPProdInfo(RepProdIndexDto dto) {
        return repProdIndexSMapper.selectPProdInfo(dto);
    }
    public RepProdIndexDto selectPProd(RepProdIndexDto dto) {
        return repProdIndexSMapper.selectPProd(dto);
    }

    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepProdIndexDto selectData(Map<String, Object> param) {
        return repProdIndexSMapper.selectData(param);
    }

    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
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
        }else {
            if (null == objects.get(0) || objects.get(0).size() != 16) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要16列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    RepProdIndexDto dto1 = new RepProdIndexDto();
                    RepProdIndexDto dto2 = new RepProdIndexDto();
                    List<String> rowData = objects.get(i);
                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("产品子代码 必填！");
                    }else if(!this.selectProdId(rowData.get(0))){
                        errorMsg.append("产品子代码未在系统中维护！");
                    }else{
                        dto1 = selectProdInfo(rowData.get(0));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("产品主代码 必填！");
                    }else if(!this.selectProdId(rowData.get(1))){
                        errorMsg.append("产品主代码未在系统中维护！");
                    }else if(StringUtil.isNotEmpty(dto1.getpProdId()) && !(dto1.getpProdId().equals(rowData.get(1)))){
                        errorMsg.append("产品主代码不是产品子代码的母产品代码！");
                    }else{
                        dto2.setpProdId(rowData.get(1));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("日期 必填！");
                    }else if(!this.isDateStr(rowData.get(2))){
                        errorMsg.append("日期 格式不正确！");
                    }else{
                        dto2.setValueDate(DateUtil.parse(rowData.get(2)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("子产品资产净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(3))){
                        errorMsg.append("子产品资产净值 格式不正确！");
                    }else if(new BigDecimal(rowData.get(3)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("子产品资产净值需要大于0!");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("子产品实收资本 必填！");
                    }else if(!this.isBigDecimal(rowData.get(4))){
                        errorMsg.append("子产品实收资本 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5))){
                        errorMsg.append("子产品单位净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(5))){
                        errorMsg.append("子产品单位净值 格式不正确！");
                    }else{
                        if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("M")){
                            if(new BigDecimal(rowData.get(5)).compareTo(new BigDecimal(1)) != 0){
                                errorMsg.append("产品类型为“现管类产品”时，子产品单位净值默认为1.0000!");
                            }
                        }
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6))){
                        errorMsg.append("子产品单位累计净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(6))){
                        errorMsg.append("子产品单位累计净值 格式不正确！");
                    }else{
                        if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("M")){
                            if(new BigDecimal(rowData.get(6)).compareTo(new BigDecimal(1)) != 0){
                                errorMsg.append("产品类型为“现管类产品”时，子产品单位累计净值默认为1.0000!");
                            }
                        }
                    }
                    if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("M")){
                        if (!StringUtil.isNotEmpty(rowData.get(7))){
                            errorMsg.append("子产品万份收益 必填！");
                        }else if(!this.isBigDecimal(rowData.get(7))){
                            errorMsg.append("子产品万份收益 格式不正确！");
                        }
                        if (!StringUtil.isNotEmpty(rowData.get(8))){
                            errorMsg.append("子产品七日年化收益率 必填！");
                        }else if(!this.isBigDecimal(rowData.get(8))){
                            errorMsg.append("子产品七日年化收益率 格式不正确！");
                        }
                    }else if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("N")){
                        if (StringUtil.isNotEmpty(rowData.get(7))){
                            errorMsg.append("子产品万份收益 不应填值！");
                        }
                        if (StringUtil.isNotEmpty(rowData.get(8))){
                            errorMsg.append("子产品七日年化收益率 不应填值！");
                        }
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(9))){
                        errorMsg.append("母产品总资产 必填！");
                    }else if(!this.isBigDecimal(rowData.get(9))){
                        errorMsg.append("母产品总资产 格式不正确！");
                    }else if(new BigDecimal(rowData.get(9)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("母产品总资产需要大于0!");
                    }else{
                        dto2.setpAssets(new BigDecimal(rowData.get(9)));
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(10))){
                        errorMsg.append("母产品资产净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(10))){
                        errorMsg.append("母产品资产净值 格式不正确！");
                    }else if(new BigDecimal(rowData.get(10)).compareTo(new BigDecimal(0))<=0){
                        errorMsg.append("母产品资产净值需要大于0!");
                    }else{
                        dto2.setpAssetsValue(new BigDecimal(rowData.get(10)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(11))){
                        errorMsg.append("母产品实收资本 必填！");
                    }else if(!this.isBigDecimal(rowData.get(11))){
                        errorMsg.append("母产品实收资本 格式不正确！");
                    }else{
                        dto2.setpCapital(new BigDecimal(rowData.get(11)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(12))){
                        errorMsg.append("母产品单位净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(12))){
                        errorMsg.append("母产品单位净值 格式不正确！");
                    }else{
                        dto2.setpUnitValue(new BigDecimal(rowData.get(12)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(13))){
                        errorMsg.append("母产品单位累计净值 必填！");
                    }else if(!this.isBigDecimal(rowData.get(13))){
                        errorMsg.append("母产品单位累计净值 格式不正确！");
                    }else{
                        dto2.setpUnitValueT(new BigDecimal(rowData.get(13)));
                    }
                    if(StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("02")){
                        if(StringUtil.isNotEmpty(rowData.get(10)) && StringUtil.isNotEmpty(rowData.get(3))){
                            if(new BigDecimal(rowData.get(10)).compareTo(new BigDecimal(rowData.get(3))) != 0){
                                errorMsg.append("子产品资产净值应与母产品资产净值相等!");
                            }
                        }

                        if(StringUtil.isNotEmpty(rowData.get(11)) && StringUtil.isNotEmpty(rowData.get(4))){
                            if(new BigDecimal(rowData.get(11)).compareTo(new BigDecimal(rowData.get(4))) != 0){
                                errorMsg.append("子产品实收资本应与母产品实收资本相等!");
                            }
                        }

                        if(StringUtil.isNotEmpty(rowData.get(12)) && StringUtil.isNotEmpty(rowData.get(5))){
                            if(new BigDecimal(rowData.get(12)).compareTo(new BigDecimal(rowData.get(5))) != 0){
                                errorMsg.append("子产品单位净值应与母产品单位净值相等!");
                            }
                        }
                        if(StringUtil.isNotEmpty(rowData.get(13)) && StringUtil.isNotEmpty(rowData.get(6))){
                            if(new BigDecimal(rowData.get(13)).compareTo(new BigDecimal(rowData.get(6))) != 0){
                                errorMsg.append("子产品单位累计净值应与母产品单位累计净值相等!");
                            }
                        }

                    }else{
                        if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("M")){
                            if(new BigDecimal(rowData.get(12)).compareTo(new BigDecimal(1)) != 0){
                                errorMsg.append("产品类型为“现管类产品”时，母产品单位净值默认为1.0000!");
                            }
                            if(new BigDecimal(rowData.get(13)).compareTo(new BigDecimal(1)) != 0){
                                errorMsg.append("产品类型为“现管类产品”时，母产品单位累计净值默认为1.0000!");
                            }
                        }
                    }
                    if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("M")){
                        if(StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("02")){
                            if (!StringUtil.isNotEmpty(rowData.get(14))){
                                errorMsg.append("母产品万份收益 必填！");
                            }else if(!this.isBigDecimal(rowData.get(14))){
                                errorMsg.append("母产品单位累计净值 格式不正确！");
                            }else if(new BigDecimal(rowData.get(14)).compareTo(new BigDecimal(rowData.get(7))) != 0){
                                errorMsg.append("子产品万份收益应与母产品万份收益应相等!");
                            }else{
                                dto2.setpWYield(new BigDecimal(rowData.get(14)));
                            }
                            if (!StringUtil.isNotEmpty(rowData.get(15))){
                                errorMsg.append("母产品七日年化收益率 必填！");
                            }else if(!this.isBigDecimal(rowData.get(15))){
                                errorMsg.append("母产品单位累计净值 格式不正确！");
                            }else if(new BigDecimal(rowData.get(15)).compareTo(new BigDecimal(rowData.get(8))) != 0){
                                errorMsg.append("子产品七日年化收益率应与母产品七日年化收益率应相等!");
                            }else{
                                dto2.setpYield7(new BigDecimal(rowData.get(15)));
                            }
                        }else{
                            if (!StringUtil.isNotEmpty(rowData.get(14))){
                                errorMsg.append("母产品万份收益 必填！");
                            }else if(!this.isBigDecimal(rowData.get(14))){
                                errorMsg.append("母产品单位累计净值 格式不正确！");
                            }else{
                                dto2.setpWYield(new BigDecimal(rowData.get(14)));
                            }
                            if (!StringUtil.isNotEmpty(rowData.get(15))){
                                errorMsg.append("母产品七日年化收益率 必填！");
                            }else if(!this.isBigDecimal(rowData.get(15))){
                                errorMsg.append("母产品单位累计净值 格式不正确！");
                            }else{
                                dto2.setpYield7(new BigDecimal(rowData.get(15)));
                            }
                        }
                    }else if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("N")){
                        if (StringUtil.isNotEmpty(rowData.get(7))){
                            errorMsg.append("母产品万份收益 不应填值！");
                        }
                        if (StringUtil.isNotEmpty(rowData.get(8))){
                            errorMsg.append("母产品七日年化收益率 不应填值！");
                        }
                    }
                    if(StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("01")){
                        RepProdIndexDto repProdIndexDto2 = this.selectPProdInfo(dto2);
                        RepProdIndexDto repProdIndexDto3 = this.selectPProd(dto2);
                        if(repProdIndexDto2 == null && repProdIndexDto3 != null){
                            errorMsg.append("对于分级产品，每个子产品数据同一天的“母产品总资产“母产品资产净值”“母产品实收资本”“母产品单位净值”“母产品单位累计净值”“母产品万份收益”“母产品七日年化收益率”需要分别相等!！");
                        }                    }
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
        RepProdIndexDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepProdIndexDto> selectedItem = dto.getSelectedItem();
        List<RepProdIndexDto>  existData = new ArrayList<>();
        for (RepProdIndexDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("valueDate",temp.getValueDate());
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


    public Boolean selectProdId(String prodId) {
        String r = repProdIndexSMapper.selectProdId(prodId);
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

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        RepProdIndexDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepProdIndexDto> selectedItem = dto.getSelectedItem();
        List<RepProdIndexDto>  existData = new ArrayList<>();
        List<RepProdIndexDto>  newData = new ArrayList<>();
        for (RepProdIndexDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("valueDate",temp.getValueDate());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (RepProdIndexDto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }

        //插入操作
        for (RepProdIndexDto newDataDto : newData) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }

    public RepProdIndexDto getValNetAssetQuotationInfoListData(String tempList) {
        RepProdIndexDto dto = new RepProdIndexDto();
        List<RepProdIndexDto> repProdIndexDtoList = new ArrayList<>();
        dto.setSelectedItem(repProdIndexDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            RepProdIndexDto repProdIndexDto = new RepProdIndexDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                repProdIndexDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                repProdIndexDto.setpProdId(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                repProdIndexDto.setValueDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                repProdIndexDto.setAssetsValue(new BigDecimal(rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                repProdIndexDto.setCapital(new BigDecimal(rowData.get(4).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                repProdIndexDto.setUnitValue(new BigDecimal(rowData.get(5).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                repProdIndexDto.setUnitValueT(new BigDecimal(rowData.get(6).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                repProdIndexDto.setwYield(new BigDecimal(rowData.get(7).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(8))) {
                repProdIndexDto.setYield7(new BigDecimal(rowData.get(8).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(9))) {
                repProdIndexDto.setpAssets(new BigDecimal(rowData.get(9).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(10))) {
                repProdIndexDto.setpAssetsValue(new BigDecimal(rowData.get(10).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(11))) {
                repProdIndexDto.setpCapital(new BigDecimal(rowData.get(11).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(12))) {
                repProdIndexDto.setpUnitValue(new BigDecimal(rowData.get(12).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(13))) {
                repProdIndexDto.setpUnitValueT(new BigDecimal(rowData.get(13).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(14))) {
                repProdIndexDto.setpWYield(new BigDecimal(rowData.get(14).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(15))) {
                repProdIndexDto.setpYield7(new BigDecimal(rowData.get(15).toString()));
            }
            repProdIndexDto.setStatus("E");
            repProdIndexDtoList.add(repProdIndexDto);
        }
        dto.setSelectedItem(repProdIndexDtoList);
        return dto;
    }
}

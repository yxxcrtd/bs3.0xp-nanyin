/*
 * 文件名：ComNetAssetQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.tams.common.basicdata.util.ComBasicDataExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 计划类（净值型）资产估值行情信息查询service
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ComNetAssetQuotationInfo001Service
 * @since 1.0
 */

@Service
@Transactional
public class ComNetAssetQuotationInfo001Service extends CommonExportService<ComNetAssetQuotationInfo001Dto>
    implements BaseProcessService<ComNetAssetQuotationInfo001Dto> {

    /**
     * 查询按钮点击事件 通过条件查询数据
     * 
     * @param pageParam 页面参数
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ComNetAssetQuotationInfo001Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        pageParam.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        /** modify by yanya on 20210209 改为用估值侧类型过滤 **/
        //pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        return restTemplate.postForPage(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/findPage", pageParam,
            ComNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 资产名称联想文本框 可以通过输入的资产名称模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetName(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/getAssetName", map,
            SuggestDto.class);
    }

    /**
     * 查询界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetCode(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/getAssetCode", map,
            SuggestDto.class);
    }

    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/getAssetCodeList", map,
            SelectOptionDto.class);
    }

    /**
     * 通过资产代码查询数据 完成新建、编辑界面和双击查看界面初始化
     * 
     * @param param 资产代码
     * @return dto
     * @see
     * @since 1.0
     */
    public ComNetAssetQuotationInfo001Dto selectMstNetAsset(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/selectMstNetAsset",
            param, ComNetAssetQuotationInfo001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ComNetAssetQuotationInfo001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComNetAssetQuotationInfo/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();
        param.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        /** modify by yanya on 20210209 改为用估值侧类型过滤 **/
        //param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        exportParam.config(
                "/com/joyintech/tams/common/basicdata/marketdata/mstnetassetquotationinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("valDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    @Override
    public Page<ComNetAssetQuotationInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 通过资产代码和估值日期查询数据 查询插入数据的主键是否与数据库中的相同
     * 
     * @param finprodId 金融产品代码
     * @param valDate 估值日期
     * @return dto
     * @see
     * @since 1.0
     *//*
       * public ComNetAssetQuotationInfo001Dto selectByIdAndDate(String finprodId, String valDate)
       * { return mstnetassetquotationinfo001mapper.selectByIdAndDate(finprodId,valDate); }
       */

    /**
     * 通过资产代码查询资产名称和管理人 完成资产代码画面联动
     * 
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    public ComNetAssetQuotationInfo001Dto selectNameAndFundManager(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                          + "/ComNetAssetQuotationInfo/selectNameAndFundManager",
            map, ComNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 
     * 检验validate
     * <功能详细描述>
     * @param map 金融产品代码和估值日
     * @return dto
     * @see
     * @since 1.0
     */
    public ComNetAssetQuotationInfo001Dto selectByIdAndDate(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                                       + "/ComNetAssetQuotationInfo/selectByIdAndDate",
            map, ComNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 根据条件查询所有数据
     * @param selectParam
     * @return
     */
    public List<ComNetAssetQuotationInfo001Dto> selectAllList(Map<String, Object> selectParam) {
        return restTemplate.postForList(ServiceApplication.CommonBasicDataService
                        + "/ComNetAssetQuotationInfo/selectAllList",
                selectParam,ComNetAssetQuotationInfo001Dto.class);
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
                    //资产代码 必填
                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("资产代码 必填！");
                    }
                    //资产名称 必填
                    /*if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("资产名称 必填！");
                    }*/
                    //估值日期 必填
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("估值日期 必填！");
                    }
                    //万份收益/净值 必填
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("万份收益/净值 必填！");
                    }
                    //管理人 必填
                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("管理人 必填！");
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
     * @throws IOException 
     */
    public ComNetAssetQuotationInfo001Dto getValNetAssetQuotationInfoListData(String tempList) throws IOException {
        ComNetAssetQuotationInfo001Dto dto = new ComNetAssetQuotationInfo001Dto();
        // 行情信息列表
        List<ComNetAssetQuotationInfo001Dto> valNetAssetQuotationInfoList = new ArrayList<>();
        dto.setSelectedItem(valNetAssetQuotationInfoList);
        List<String> objects = JsonUtils.json2List(tempList,String.class);

        for (int i = 0; i < objects.size(); i++) {
//            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
        	List<String> rowData = JsonUtils.json2List(objects.get(i),String.class);
            ComNetAssetQuotationInfo001Dto valNetAssetQuotationInfo001Dto = new ComNetAssetQuotationInfo001Dto();
            // 资产代码【0】
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                valNetAssetQuotationInfo001Dto.setFinprodId(rowData.get(0).toString());
            }
            // 资产名称【1】
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                valNetAssetQuotationInfo001Dto.setFinProdName(rowData.get(1).toString());
            }
            // 估值日期【2】
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                valNetAssetQuotationInfo001Dto.setValDate(DateUtil.parse(rowData.get(2).toString()));
            }
            // 万份收益/净值【3】
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                valNetAssetQuotationInfo001Dto.setValuation(new BigDecimal(rowData.get(3).toString()));
            }
            // 管理人【4】
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                valNetAssetQuotationInfo001Dto.setFundManager(rowData.get(4).toString());
            }
            valNetAssetQuotationInfo001Dto.setFinProdMarketId(valNetAssetQuotationInfo001Dto.getFinprodId());
            valNetAssetQuotationInfoList.add(valNetAssetQuotationInfo001Dto);
        }
        dto.setSelectedItem(valNetAssetQuotationInfoList);
        return dto;
    }

    public AjaxResult importFile(Map<String, Object> param) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ComNetAssetQuotationInfo001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ComNetAssetQuotationInfo001Dto> selectedItem = dto.getSelectedItem();
        List<ComNetAssetQuotationInfo001Dto>  existData = new ArrayList<>();
        List<ComNetAssetQuotationInfo001Dto>  newData = new ArrayList<>();
        for (ComNetAssetQuotationInfo001Dto temp:selectedItem
        ) {
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId",temp.getFinprodId());
            map.put("valDate",DateUtil.format(temp.getValDate()));
            ComNetAssetQuotationInfo001Dto dto1 = this.selectByIdAndDate(map);
            //数据已存在 【资产代码、估值日 相同】，做更新操作。
            if (StringUtil.isNotEmpty(dto1)){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (ComNetAssetQuotationInfo001Dto existDataDto : existData
        ) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (ComNetAssetQuotationInfo001Dto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }
}

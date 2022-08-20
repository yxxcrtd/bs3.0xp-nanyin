package com.joyintech.tams.disclosure.information.config.repInvestAccount.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dao.RepInvestAccount001Mapper;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepInvestAccountService extends CommonExportService<RepInvestAccountDto> implements BaseProcessService<RepInvestAccountDto> {

    @Autowired
    private RepInvestAccount001Mapper repInvestAccountMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
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
//        return repInvestAccountMapper.getProdInsId(map);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> repInvestAccountMapper.getProdInsId(map));
        return result;
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repInvestAccountMapper.getProdInsId2(map);
    }
    /**
     * 投资账户信息查询 <功能详细描述>
     *
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<RepInvestAccountDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepInvestAccountDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repInvestAccountMapper.selectInfo(param));
        return result;
    }

    /**
     * 投资账户数据操作 <功能详细描述>
     *
     * @param entity 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Override
    public void doComplete(RepInvestAccountDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
//        entity.setUpdateUser(CommonUtil.getUserName());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repInvestAccountMapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repInvestAccountMapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            repInvestAccountMapper.delete(entity);
        }
    }

    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepInvestAccountDto selectData(Map<String, Object> param) {
        return repInvestAccountMapper.selectData(param);
    }

    public Map<String,String> selectProdName(String prodId) {
        Map<String, String> map = repInvestAccountMapper.selectProdName(prodId);
        if (map==null){
            map = new HashMap<>();
            map.put("PROD_NAME", "");
        }
        return map;
    }

    public RepInvestAccountDto selectByProdID(RepInvestAccountDto dto) {
        return repInvestAccountMapper.selectByProdID(dto);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repInvestAccount/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepInvestAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }

    public Page<RepInvestAccountDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepInvestAccountDto> result = queryForPage(() -> repInvestAccountMapper.selectInfo(param));
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
            if (null == objects.get(0) || objects.get(0).size() != 4) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要4列。");
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
                        errorMsg.append("账号 必填！");
                    }else if(rowData.get(1).length() > 20){
                        errorMsg.append("账号不得超过20个字符！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("账户名称 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("开户单位 必填！");
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
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
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
        RepInvestAccountDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepInvestAccountDto> selectedItem = dto.getSelectedItem();
        List<RepInvestAccountDto>  existData = new ArrayList<>();
        for (RepInvestAccountDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
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
        String r = repInvestAccountMapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        RepInvestAccountDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepInvestAccountDto> selectedItem = dto.getSelectedItem();
        List<RepInvestAccountDto>  existData = new ArrayList<>();
        List<RepInvestAccountDto>  newData = new ArrayList<>();
        for (RepInvestAccountDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            if (StringUtil.isNotEmpty(this.selectData(map))){
                existData.add(temp);
            }else {
                newData.add(temp);
            }
        }
        //更新操作
        for (RepInvestAccountDto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }

        //插入操作
        for (RepInvestAccountDto newDataDto : newData) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public RepInvestAccountDto getValNetAssetQuotationInfoListData(String tempList) {
        RepInvestAccountDto dto = new RepInvestAccountDto();
        List<RepInvestAccountDto> repInvestAccountDtoList = new ArrayList<>();
        dto.setSelectedItem(repInvestAccountDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            RepInvestAccountDto repInvestAccountDto = new RepInvestAccountDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                repInvestAccountDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                repInvestAccountDto.setAccountNum(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                repInvestAccountDto.setAccountName(rowData.get(2).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                repInvestAccountDto.setAccountOpener(rowData.get(3).toString());
            }
            repInvestAccountDto.setAccountType("A01");
            repInvestAccountDto.setStatus("E");
            repInvestAccountDtoList.add(repInvestAccountDto);
        }
        dto.setSelectedItem(repInvestAccountDtoList);
        return dto;
    }

    public RepInvestAccountDto checkExistData(RepInvestAccountDto dto) {
        return repInvestAccountMapper.checkExistData(dto);
    }
}

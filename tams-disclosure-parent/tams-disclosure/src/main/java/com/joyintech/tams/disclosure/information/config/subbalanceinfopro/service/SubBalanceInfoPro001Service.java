package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.service;

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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dao.ProFailureInfoMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dao.SubBalanceInfoProMapper;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubBalanceInfoPro001Service extends CommonExportService<SubBalanceInfoPro001Dto> implements BaseProcessService<SubBalanceInfoPro001Dto> {

    @Autowired
    private SubBalanceInfoProMapper subBalanceInfoProMapper;

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;

    public Page<SubBalanceInfoPro001Dto> selectForPage(Map<String, Object> param) {
        String booksetId = (String)param.get("booksetId");
        if(StringUtil.isNotEmpty(booksetId)){
            param.put("booksetIdList", StringUtil.splitToList(GlobalConstant.COMMA,booksetId));
        }
        String subjectNo = (String)param.get("subjectNo");
        if(StringUtil.isNotEmpty(subjectNo)){
            param.put("subjectNoList", StringUtil.splitToList(GlobalConstant.COMMA,subjectNo));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SubBalanceInfoPro001Dto> result = queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> subBalanceInfoProMapper.select(param));
        return result;
    }

    @Override
    public void doComplete(SubBalanceInfoPro001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            subBalanceInfoProMapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            SubBalanceInfoPro001Dto subBalanceInfoPro001Dto = new SubBalanceInfoPro001Dto();
            BeanUtil.copyPropertiesIgnoreNull(subBalanceInfoPro001Dto,dto);
            subBalanceInfoPro001Dto.setStatus("E");
            subBalanceInfoProMapper.insert(subBalanceInfoPro001Dto);
        }
        else {
            dto.setStatus("E");
            dto.setErrormsg("");
            subBalanceInfoProMapper.updateByKey(dto);
        }

    }



    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/subbalanceinfopro/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<SubBalanceInfoPro001Dto> selectPageData(Map<String, Object> selectParam) {
        return queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> subBalanceInfoProMapper.select(selectParam));
    }

    public SubBalanceInfoPro001Dto selectByPrimaryKey(HashMap map) {
        return subBalanceInfoProMapper.selectByPrimaryKey(map);
    }




    public List<SelectOptionDto> getRegistNo(Map<String, Object> map) {
        return subBalanceInfoProMapper.getRegistNo(map);
    }

    public String selectByProdRegistNo(String prodRegistNo) {
        return subBalanceInfoProMapper.selectByProdRegistNo(prodRegistNo);
    }

//    public Page<SelectOptionDto> getProdId(Map map) {
//        Map<String, Object> pageParam = RequestUtil.getPageParam();
//        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->subBalanceInfoProMapper.getProdId(map));
//    }

    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return subBalanceInfoProMapper.findProdId(map);
    }


    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->subBalanceInfoProMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->subBalanceInfoProMapper.getProdIdlist(map));
    }
    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<String> findList =  proElementAdjMapper.getProdId(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
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
                    if (!StringUtil.isNotEmpty(rowData.get(0).trim())){
                        errorMsg.append("产品主代码 必填！");
                    }
                    else if(!findList.contains(rowData.get(0).trim())){
                        errorMsg.append("产品主代码 在产品基本信息表中没有维护！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1).trim())){
                        errorMsg.append("余额日期 必填！");
                    }else if(!this.isDateStr(rowData.get(1).trim())){
                        errorMsg.append("余额日期 格式不正确！");
                    }
                    else if (rowData.get(1).compareTo(DateUtil.format(DateUtil.platDateFromCache())) > 0){
                        errorMsg.append("余额日期 不能大于平台日！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2).trim())){
                        errorMsg.append("科目号 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3).trim())){
                        errorMsg.append("原币币种 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4).trim())){
                        errorMsg.append("原币余额 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5).trim())){
                        errorMsg.append("原币发生额（借方） 必填！");
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
        Date sysDate = DateUtil.platDateFromCache();
        String tempList = (String) param.get("tempList");
        SubBalanceInfoPro001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<SubBalanceInfoPro001Dto> selectedItem = dto.getSelectedItem();
        List<SubBalanceInfoPro001Dto>  existData = new ArrayList<>();
        List<SubBalanceInfoPro001Dto>  newData = new ArrayList<>();
        for (SubBalanceInfoPro001Dto temp:selectedItem) {
            //数据已存在 【校验产品主代码+科目号+余额日期 相同】，做更新操作。
            //todo 校验产品主代码+科目号+余额日期
            if (StringUtil.isNotEmpty(this.selectByProdRegistNo(temp.getSubjectNo()))){
                existData.add(temp);
            }else {
                temp.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                temp.setUpdateTime(sysDate);
                temp.setUpdateUser(CommonUtil.getUserName());
                newData.add(temp);
            }
        }
        //更新操作
        for (SubBalanceInfoPro001Dto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (SubBalanceInfoPro001Dto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public SubBalanceInfoPro001Dto getValNetAssetQuotationInfoListData(String tempList) {
        SubBalanceInfoPro001Dto dto = new SubBalanceInfoPro001Dto();
        // 科目余额信息列表
        List<SubBalanceInfoPro001Dto> subBalanceInfoPro001DtoList = new ArrayList<>();
        dto.setSelectedItem(subBalanceInfoPro001DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            SubBalanceInfoPro001Dto subBalanceInfoPro001Dto = new SubBalanceInfoPro001Dto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                subBalanceInfoPro001Dto.setBooksetId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                subBalanceInfoPro001Dto.setBalanceDate(DateUtil.parse(rowData.get(1).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                subBalanceInfoPro001Dto.setSubjectNo(rowData.get(2).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                subBalanceInfoPro001Dto.setoCcy(rowData.get(3).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                subBalanceInfoPro001Dto.setoAmt(new BigDecimal(rowData.get(4).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                subBalanceInfoPro001Dto.setTdyODAmt(new BigDecimal(rowData.get(5).toString()));
            }
            subBalanceInfoPro001DtoList.add(subBalanceInfoPro001Dto);
        }
        dto.setSelectedItem(subBalanceInfoPro001DtoList);
        return dto;
    }


    public List<SelectOptionDto> getRePord(Map<String, Object> pageParam) {
        return subBalanceInfoProMapper.getRePord(pageParam);
    }


    public HashMap findProdName(String booksetId) {
        return subBalanceInfoProMapper.findProdName(booksetId);
    }

    public List<SelectOptionDto> findSubjectNo(Map<String, Object> pageParam) {
        return subBalanceInfoProMapper.findSubjectNo(pageParam);
    }
}

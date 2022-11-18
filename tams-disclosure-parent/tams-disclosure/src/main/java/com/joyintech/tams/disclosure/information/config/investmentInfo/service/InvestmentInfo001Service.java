package com.joyintech.tams.disclosure.information.config.investmentInfo.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dao.InvestmentInfo001Mapper;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvestmentInfo001Service extends CommonExportService<InvestmentInfo001Dto> implements BaseProcessService<InvestmentInfo001Dto> {

    @Autowired
    private InvestmentInfo001Mapper investmentInfo001Mapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<InvestmentInfo001Dto> selectForPage(Map<String, Object> param) {
        String booksetId = (String)param.get("booksetId");
        if(StringUtil.isNotEmpty(booksetId)){
            param.put("booksetIdList", StringUtil.splitToList(GlobalConstant.COMMA,booksetId));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<InvestmentInfo001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> investmentInfo001Mapper.select(param));
        return result;
    }

    //flag 判断是否导入  false否  true是
    @Override
    public void doComplete(InvestmentInfo001Dto dto, OperationType operationType) {

        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        dto.setProportionShare(dto.getProportionShare().divide(new BigDecimal("100")).setScale(4,BigDecimal.ROUND_HALF_UP));
        Map<String,Object> map =new HashMap();
        if (StringUtil.equals(type, "DELETE")) {
            map.put("id",dto.getId());
            map.put("booksetId",dto.getBooksetId());
            map.put("indexType",dto.getIndexType());
            map.put("indexYear",dto.getIndexYear());
            map.put("tzRank",dto.getTzRank());
            map.put("tzType",dto.getTzType());
            InvestmentInfo001Dto dto1 = investmentInfo001Mapper.selectByPrimaryKey(map);
            dto1.setUpdateUser(LoginUserUtil.getUserName());
            dto1.setStatus("D");
            investmentInfo001Mapper.insertHis(dto1);
            investmentInfo001Mapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            map.put("booksetId",dto.getBooksetId());
            map.put("indexType",dto.getIndexType());
            map.put("indexYear",dto.getIndexYear());
            map.put("tzRank",dto.getTzRank());
            map.put("tzType",dto.getTzType());
            if (StringUtil.isNotEmpty(dto.getId())){
                map.put("id",dto.getId());
                InvestmentInfo001Dto dto1 = investmentInfo001Mapper.selectByPrimaryKey(map);
                if (dto1!=null && !StringUtil.equals(dto1.getStatus(),"D")){
                    dto.setStatus("E");
                    dto.setUpdateUser(CommonUtil.getUserName());
                    investmentInfo001Mapper.updateByKey(dto);
                }
            } else {
                InvestmentInfo001Dto investmentInfo001Dto = new InvestmentInfo001Dto();
                BeanUtil.copyPropertiesIgnoreNull(investmentInfo001Dto, dto);
                investmentInfo001Dto.setId(UUID.randomUUID().toString());
                investmentInfo001Dto.setStatus("E");
                investmentInfo001Dto.setUpdateUser(CommonUtil.getUserName());
                investmentInfo001Mapper.insert(investmentInfo001Dto);
            }
        }
        else {
            dto.setStatus("E");
            dto.setUpdateUser(CommonUtil.getUserName());
            investmentInfo001Mapper.updateByKey(dto);

        }
    }

    public InvestmentInfo001Dto selectByPrimaryKey(Map map) {
        return investmentInfo001Mapper.selectByPrimaryKey(map);
    }

    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return investmentInfo001Mapper.findProdId(map);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/investmentInfo/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<InvestmentInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }

    public AjaxResult checkImportData(String fileName) {
        AjaxResult webResult = new AjaxResult();
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> tempList = ExcelUtil.getTempList(fileName);
        if (StringUtil.isEmptyList(tempList) || CollectionUtils.isEmpty(tempList)) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        if (null == tempList.get(0) || tempList.get(0).size() != 7) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 5), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT") == null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<InvestmentInfo001Dto> entities = this.convertToBean(dataList);
            List<InvestmentInfo001Dto> data = this.selectTopInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<InvestmentInfo001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getBooksetId()
                                        + GlobalConstant.UNDER_LINE + x.getIndexYear()
                                        + GlobalConstant.UNDER_LINE + x.getIndexType()
                                        + GlobalConstant.UNDER_LINE + x.getTzRank()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (InvestmentInfo001Dto dto : data) {
                    List<InvestmentInfo001Dto> listTemp = map.get(dto.getBooksetId()
                            + GlobalConstant.UNDER_LINE + dto.getIndexYear()
                            + GlobalConstant.UNDER_LINE + dto.getIndexType()
                            + GlobalConstant.UNDER_LINE + dto.getTzRank());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001_002",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
            }else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;

    }

    private List<InvestmentInfo001Dto> selectTopInfoByKey(List<InvestmentInfo001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return investmentInfo001Mapper.selectTopInfoList(dataList);
        }
        // 导入大于1000时，返回值
        List<InvestmentInfo001Dto> equtiyList = new ArrayList<>();

        List<InvestmentInfo001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<InvestmentInfo001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = investmentInfo001Mapper.selectTopInfoList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = investmentInfo001Mapper.selectTopInfoList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    private List<InvestmentInfo001Dto> convertToBean(List<List<String>> dataList) {
        List<InvestmentInfo001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            InvestmentInfo001Dto entity = new InvestmentInfo001Dto();
            entity.setIndexYear(" ");
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setIndexYear(list.get(0));
            }
            entity.setIndexType(" ");
            if (StringUtil.isNotEmpty(list.get(1))) {
                if (DictCodeListExt3.前十大投资者信息指标类型_年报.getCodeName().equals(list.get(1))){
                    entity.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_年报.getValue());
                }
                if (DictCodeListExt3.前十大投资者信息指标类型_半年报.getCodeName().equals(list.get(1))){
                    entity.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_半年报.getValue());
                }
            }
            entity.setTzRank(" ");
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setTzRank(list.get(2));
            }
            entity.setBooksetId(" ");
            if (StringUtil.isNotEmpty(list.get(3))){
                entity.setBooksetId(list.get(3));
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<InvestmentInfo001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = checkToEntitys(dataList, entities);
            if (!checkResult.isEmpty()) {
                return checkResult;
            }
            convertToEntitys(dataList,entities);
            entities.forEach(item -> {
                item.setFlag(true);
                this.doComplete(item, OperationType.NEW);
            });
            return new ArrayList<>();
        } else {
            List<ExcelValidDto> checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }
    }

    private void convertToEntitys(List<List<String>> dataList, List<InvestmentInfo001Dto> entities) {
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            entities.add(this.convertToEntity(rowData));
        }
    }

    private List<ExcelValidDto> checkToEntitys(List<List<String>> dataList,
                                                 List<InvestmentInfo001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList =  investmentInfo001Mapper.getProdId1(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {

            List<String> rowData = dataList.get(i);
            // 验证消息
            List<String> message = new ArrayList<>();

            if (StringUtil.isEmpty(rowData.get(0).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0036"));
            }
            if (StringUtil.isEmpty(rowData.get(1).trim()) ||
                (!DictCodeListExt3.前十大投资者信息指标类型_半年报.getCodeName().equals(rowData.get(1).trim())
                        && !DictCodeListExt3.前十大投资者信息指标类型_年报.getCodeName().equals(rowData.get(1).trim()))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0037"));
            }
            if (StringUtil.isEmpty(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0038"));
            }
            if (StringUtil.isEmpty(rowData.get(4)) ||
                    (!DictCodeListExt3.前十大投资者信息类别_个人.getCodeName().equals(rowData.get(4)) &&
            !DictCodeListExt3.前十大投资者信息类别_机构.getCodeName().equals(rowData.get(4)))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0039"));
            }
            if (!findList.contains(rowData.get(3).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0033"));
            }
            BigDecimal holdShare = new BigDecimal("0");
            try {
                holdShare = new BigDecimal(rowData.get(5));
            } catch (Exception e) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0034"));
            }
            BigDecimal proportionShare = new BigDecimal("0");
            try {
                proportionShare = new BigDecimal(rowData.get(6));
            } catch (Exception e) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0035"));
            }

            /*if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3), rowData);*/
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(2)
                        + GlobalConstant.UNDER_LINE + rowData.get(3),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
        }
        return checkResult;
    }

    private InvestmentInfo001Dto convertToEntity(List<String> rowData) {
        InvestmentInfo001Dto investmentInfo001Dto = new InvestmentInfo001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            investmentInfo001Dto.setIndexYear(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            if (DictCodeListExt3.前十大投资者信息指标类型_半年报.getCodeName().equals(rowData.get(1))){
                investmentInfo001Dto.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_半年报.getValue());
            }
            if (DictCodeListExt3.前十大投资者信息指标类型_年报.getCodeName().equals(rowData.get(1))){
                investmentInfo001Dto.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_年报.getValue());
            }
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            investmentInfo001Dto.setTzRank(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            investmentInfo001Dto.setBooksetId(rowData.get(3));
        }

        if (StringUtil.isNotEmpty(rowData.get(4))) {
            if (DictCodeListExt3.前十大投资者信息类别_个人.getCodeName().equals(rowData.get(4))){
                investmentInfo001Dto.setTzType(DictCodeListExt3.前十大投资者信息类别_个人.getValue());
            }
            if (DictCodeListExt3.前十大投资者信息类别_机构.getCodeName().equals(rowData.get(4))){
                investmentInfo001Dto.setTzType(DictCodeListExt3.前十大投资者信息类别_机构.getValue());
            }
        }
        if (StringUtil.isNotEmpty(rowData.get(5))){
            investmentInfo001Dto.setHoldShare(new BigDecimal(rowData.get(5)).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))){
            investmentInfo001Dto.setProportionShare(new BigDecimal(rowData.get(6)).setScale(4,BigDecimal.ROUND_HALF_DOWN));
        }
        return investmentInfo001Dto;
    }

}

package com.joyintech.tams.disclosure.information.config.performance.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.performance.dao.Performance001Mapper;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Performance001Service extends CommonExportService<Performance001Dto> implements BaseProcessService<Performance001Dto> {
    @Autowired
    private Performance001Mapper performance001Mapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<Performance001Dto> selectForPage(Map<String, Object> param) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Page<Performance001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> performance001Mapper.select(param));
        Page<Performance001Dto> result1= new Page<>();
        List<Performance001Dto> root = result.getRoot();
        Map<String, List<Performance001Dto>> collect = root.stream().collect(
                Collectors.groupingBy(item -> item.getProdId() + "_"+DateUtil.format(item.getEffDate())+"_"+ DateUtil.format(item.getExpDate())));
        List<Performance001Dto> rootList = new LinkedList<>();
        collect.forEach((k,v)->{
            Performance001Dto dto =new Performance001Dto();
            String[] s = k.split("_");
            dto.setProdId(s[0]);
            dto.setEffDate(DateUtil.parse(s[1]));
            dto.setExpDate(DateUtil.parse(s[2]));
            dto.setStatus(v.get(0).getStatus());

            for (Performance001Dto e : v) {
                if (StringUtil.equals("01",e.getDataType())) {
                    dto.setHs300(e.getProportion());
                    continue;
                }
                if (StringUtil.equals("02",e.getDataType())) {
                    dto.setZz500(e.getProportion());
                    continue;
                }
                if (StringUtil.equals("03",e.getDataType())) {
                    dto.setZzcf(e.getProportion());
                    continue;
                }
                if (StringUtil.equals("04",e.getDataType())) {
                    dto.setZzqj(e.getProportion());
                    continue;
                }
            }
            rootList.add(dto);
        });
        result1.setRoot(rootList);
        result1.setRecords(result.getRecords()/4);
        return result1;
    }

    @Override
    public void doComplete(Performance001Dto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            performance001Mapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            dto.setGroupId(IDGenerator.generate(36));
            //分四条记录插入
            Performance001Dto dto1 =new Performance001Dto();
            dto1.setUuid(IDGenerator.generate(36));
            dto1.setGroupId(dto.getGroupId());
            dto1.setProdId(dto.getProdId());
            dto1.setDataType("01");
            dto1.setProportion(dto.getZzqj()+"%");
            dto1.setEffDate(dto.getEffDate());
            dto1.setExpDate(dto.getExpDate());
            dto1.setStatus("E");
            performance001Mapper.insert(dto1);

            Performance001Dto dto2 =new Performance001Dto();
            dto2.setUuid(IDGenerator.generate(36));
            dto2.setGroupId(dto.getGroupId());
            dto2.setProdId(dto.getProdId());
            dto2.setDataType("02");
            dto2.setProportion(dto.getHs300()+"%");
            dto2.setEffDate(dto.getEffDate());
            dto2.setExpDate(dto.getExpDate());
            dto2.setStatus("E");
            performance001Mapper.insert(dto2);

            Performance001Dto dto3 =new Performance001Dto();
            dto3.setUuid(IDGenerator.generate(36));
            dto3.setGroupId(dto.getGroupId());
            dto3.setProdId(dto.getProdId());
            dto3.setDataType("03");
            dto3.setProportion(dto.getZzcf()+"%");
            dto3.setEffDate(dto.getEffDate());
            dto3.setExpDate(dto.getExpDate());
            dto3.setStatus("E");
            performance001Mapper.insert(dto3);

            Performance001Dto dto4 =new Performance001Dto();
            dto4.setUuid(IDGenerator.generate(36));
            dto4.setGroupId(dto.getGroupId());
            dto4.setProdId(dto.getProdId());
            dto4.setDataType("04");
            dto4.setProportion(dto.getZz500()+"%");
            dto4.setEffDate(dto.getEffDate());
            dto4.setExpDate(dto.getExpDate());
            dto4.setStatus("E");
            performance001Mapper.insert(dto4);
        }
        else {
            dto.setStatus("E");
            performance001Mapper.updateByKey(dto);
        }
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/performance/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<Performance001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }

//    public Page<SelectOptionDto> getProdIdlist(Map map) {
//        Map<String, Object> pageParam = RequestUtil.getPageParam();
//        return this.queryForPageForSelectReturn(Integer.parseInt(pageParam.get("page").toString()), Integer.parseInt(pageParam.get("length").toString()),()->performance001Mapper.getProdIdlist(map));
//    }

    public List getProdIdlist(Map map) {
        return performance001Mapper.getProdIdlist(map);
    }
    public List<Performance001Dto> selectByPrimaryKey(Map map) {
        return performance001Mapper.selectByPrimaryKey(map);
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
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 7), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > 5000) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<Performance001Dto> entities = this.convertToBean(dataList);
            List<Performance001Dto> data = this.selectPerformanceInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<Performance001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getEffDateStr()
                                        + GlobalConstant.UNDER_LINE + x.getExpDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (Performance001Dto dto : data) {
                    List<Performance001Dto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getEffDateStr()
                            + GlobalConstant.UNDER_LINE + dto.getExpDateStr());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;
    }

    private List<Performance001Dto> selectPerformanceInfoByKey(List<Performance001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return performance001Mapper.selectPerformanceInfoList(dataList);
        }
        // 导入大于1000时，返回值
        List<Performance001Dto> equtiyList = new ArrayList<>();

        List<Performance001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<Performance001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = performance001Mapper.selectPerformanceInfoList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = performance001Mapper.selectPerformanceInfoList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }


    /**
     * excel数据转为java对象
     * @return
     */
    private List<Performance001Dto> convertToBean(List<List<String>> dataList) {
        List<Performance001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            Performance001Dto entity = new Performance001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0).trim());
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setEffDateStr(list.get(1).trim());
            } else {
                entity.setEffDateStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setExpDateStr(list.get(2).trim());
            } else {
                entity.setExpDateStr(" ");
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
            List<Performance001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("effDate", item.getEffDate());
                    map.put("expDate", item.getExpDate());
                    item.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    item.setUpdateTime(DateUtil.now());
                    item.setUpdateUser(CommonUtil.getUserName());
//                    Performance001Dto dto = this.selectByPrimaryKey(map);
                    List<Performance001Dto> dto = this.selectByPrimaryKey(map);
                    if (dto != null && dto.size()>0) {
                        performance001Mapper.updateByKey(item);
                    } else {
                        item.setUuid(IDGenerator.generate(36));
                        this.doComplete(item, OperationType.NEW);
                    }
                });
            }
            return checkResult;
        } else {
            List<ExcelValidDto> checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }
    }

    
    private List<ExcelValidDto> convertToEntitys(List<List<String>> dataList,
                                                 List<Performance001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> prodList =  performance001Mapper.getProdIdlist(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 7) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 7)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
         
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if (!prodList.contains(rowData.get(0).trim())){
                message.add("子产品代码未在产品基本信息表中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业绩比较基准生效日"));
            } else if (!isDateStr(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "业绩比较基准生效日"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业绩比较基准生效截止日"));
            } else if (!isDateStr(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "业绩比较基准生效截止日"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "中债新综合全价指数收益率【系数】"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "中债新综合财富指数（1-3年）收益率【系数】"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "中证500指数收益率【系数】"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "沪深300指数收益率【系数】"));
            }
            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(2),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }



    private Performance001Dto convertToEntity(List<String> rowData) {
        Performance001Dto Performance001Dto = new Performance001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            Performance001Dto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            Performance001Dto.setEffDate(DateUtil.parse(rowData.get(1)));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            Performance001Dto.setExpDate(DateUtil.parse(rowData.get(2)));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            Performance001Dto.setZzqj(rowData.get(3));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            Performance001Dto.setZzcf(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            Performance001Dto.setZz500(rowData.get(5));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            Performance001Dto.setHs300(rowData.get(6));
        }
        return Performance001Dto;
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



    public List<Performance001Dto> selectPerformanceByProdId(Map<String, Object> param) {
        param.put("status",DictCodeListExt1.维护数据状态_有效.getValue());
        return performance001Mapper.selectPerformanceByProdId(param);
    }

    public List<Performance001Dto> getWorklowData(String prodId) {
        List<ProcessMark> processMarkList = performance001Mapper.getWorklowData(prodId);
        List<Performance001Dto> list = new ArrayList<>();
        if (processMarkList != null && processMarkList.size() > 0){
            for (ProcessMark processMark : processMarkList){
                Performance001Dto performance001Dto = this.getBussinessEntity(processMark);
                list.add(performance001Dto);
            }

        }
        return list;
    }

}

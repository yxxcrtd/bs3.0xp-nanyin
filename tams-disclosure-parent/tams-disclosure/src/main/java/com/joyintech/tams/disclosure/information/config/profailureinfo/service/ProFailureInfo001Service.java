package com.joyintech.tams.disclosure.information.config.profailureinfo.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dao.ProFailureInfoMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
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
public class ProFailureInfo001Service extends CommonExportService<ProFailureInfo001Dto> implements BaseProcessService<ProFailureInfo001Dto> {

    @Autowired
    private ProFailureInfoMapper proFailureInfoMapper;

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
    public Page<ProFailureInfo001Dto> selectForPage(Map<String, Object> param) {
        String prodId = (String)param.get("prodRegistNo");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<ProFailureInfo001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> proFailureInfoMapper.select(param));
        return result;
    }
    @Override
    public void doComplete(ProFailureInfo001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            proFailureInfoMapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            ProFailureInfo001Dto dto1 = proFailureInfoMapper.isRepeated2(dto);
            if (dto1!=null){
                if( StringUtil.equals(dto1.getDataStatus(),"D")){
                    dto.setDataStatus("E");
                    proFailureInfoMapper.updateByKey(dto);
                }
            }else {
            ProFailureInfo001Dto proFailureInfo001Dto = new ProFailureInfo001Dto();
            BeanUtil.copyPropertiesIgnoreNull(proFailureInfo001Dto,dto);
            proFailureInfo001Dto.setDataStatus("E");
            proFailureInfoMapper.insert(proFailureInfo001Dto);
            }
        }
        else {
            dto.setDataStatus("E");
            proFailureInfoMapper.delete(dto);
            proFailureInfoMapper.insert(dto);
        }

    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/profailureinfo/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProFailureInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }

    public ProFailureInfo001Dto selectByPrimaryKey(String prodRegistNo) {
        return proFailureInfoMapper.selectByPrimaryKey(prodRegistNo);
    }

    public ProFailureInfo001Dto selectOne(String prodRegistNo,String nocreateReason,String date,String dataStatus) {
        return proFailureInfoMapper.selectOne(prodRegistNo,nocreateReason,date,dataStatus);
    }




    public List<SelectOptionDto> getRegistNo(Map<String, Object> map) {
        return proFailureInfoMapper.getRegistNo(map);
    }

    public String selectByProdRegistNo(String prodRegistNo) {
        return proFailureInfoMapper.selectByProdRegistNo(prodRegistNo);
    }

//    public Page<SelectOptionDto> getProdId(Map map) {
//        Map<String, Object> pageParam = RequestUtil.getPageParam();
//        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getProdId(map));
//    }

    public List<SelectOptionDto> getProdId(Map<String, Object> map) {
        return proFailureInfoMapper.getProdId(map);
    }


    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getProdIdlist(map));
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
        if (null == tempList.get(0) || tempList.get(0).size() != 3) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 3), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<ProFailureInfo001Dto> entities = this.convertToBean(dataList);
            List<ProFailureInfo001Dto> data = this.selectFailureInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<ProFailureInfo001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdRegistNo() + GlobalConstant.UNDER_LINE + x.getReportIssueDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (ProFailureInfo001Dto dto : data) {
                    List<ProFailureInfo001Dto> listTemp = map.get(dto.getProdRegistNo()
                            + GlobalConstant.UNDER_LINE + dto.getReportIssueDateStr());
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

    /**
     * excel数据转为java对象
     * @return
     */
    private List<ProFailureInfo001Dto> convertToBean(List<List<String>> dataList) {
        List<ProFailureInfo001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            ProFailureInfo001Dto entity = new ProFailureInfo001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdRegistNo(list.get(0));
            } else {
                entity.setProdRegistNo(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setReportIssueDateStr(list.get(2));
            } else {
                entity.setReportIssueDateStr(" ");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }

    /**
     * 分批查询
     * @param dataList
     * @return
     */
    private List<ProFailureInfo001Dto> selectFailureInfoByKey(List<ProFailureInfo001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return proFailureInfoMapper.selectFailureInfoList(dataList);
        }
        // 导入大于1000时，返回值
        List<ProFailureInfo001Dto> equtiyList = new ArrayList<>();

        List<ProFailureInfo001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<ProFailureInfo001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = proFailureInfoMapper.selectFailureInfoList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = proFailureInfoMapper.selectFailureInfoList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<ProFailureInfo001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    if (this.isRepeated(item).size() > 0) {
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
                        item.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                        item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                        item.setUpdateTime(DateUtil.now());
                        item.setUpdateUser(CommonUtil.getUserName());
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
                                                 List<ProFailureInfo001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList = proElementAdjMapper.getRegistNo(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 3) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 3)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            } else if (!findList.contains(rowData.get(0))) {
                message.add("产品登记编码不存在。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "不成立原因"));
            }else if (rowData.get(1).length()>1000){
                message.add("不成立原因超过限制字数");
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "报告发布日期"));
            } else if (!isDateStr(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "报告发布日期"));
            }
            if (map.containsKey(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(2),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private ProFailureInfo001Dto convertToEntity(List<String> rowData) {
        ProFailureInfo001Dto proFailureInfo001Dto = new ProFailureInfo001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            proFailureInfo001Dto.setProdRegistNo(rowData.get(0).toString());
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            proFailureInfo001Dto.setNocreateReason(rowData.get(1).toString());
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            proFailureInfo001Dto.setReportIssueDate(DateUtil.parse(rowData.get(2).toString()));
        }
        return proFailureInfo001Dto;
    }

    public List<SelectOptionDto> getRePord(Map<String, Object> pageParam) {
        return proFailureInfoMapper.getRePord(pageParam);
    }

    public int hasRepeatedData(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.hasRepeatedData(dto);
    }

    public List<ProFailureInfo001Dto> isRepeated(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.isRepeated(dto);
    }

    public ProFailureInfo001Dto isRepeated2(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.isRepeated2(dto);
    }
}

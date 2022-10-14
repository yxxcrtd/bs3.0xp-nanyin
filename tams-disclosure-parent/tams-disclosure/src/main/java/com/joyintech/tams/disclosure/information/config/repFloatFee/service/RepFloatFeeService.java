package com.joyintech.tams.disclosure.information.config.repFloatFee.service;

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
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dao.RepFloatFee001Mapper;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
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
import java.text.SimpleDateFormat;
import java.util.*;
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
//        Page<RepFloatFeeDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
//                -> repFloatFee001Mapper.selectInfo(param));
        Page<RepFloatFeeDto> result = super.queryForPageForSelectReturn(
                Integer.parseInt(param.get("page").toString()),
                Integer.parseInt(param.get("length").toString())
                , () -> repFloatFee001Mapper.selectInfo(param));
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
            entity.setUpdateUser(CommonUtil.getUserName());
            repFloatFee001Mapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            entity.setUpdateUser(CommonUtil.getUserName());
            repFloatFee001Mapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            entity.setUpdateUser(CommonUtil.getUserName());
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
        /*param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepFloatFeeDto> result = queryForPage(()
                -> repFloatFee001Mapper.selectInfo(param));
        this.selectInfo(param);*/
        return this.selectInfo(param);
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
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<RepFloatFeeDto> entities = this.convertToBean(dataList);
            List<RepFloatFeeDto> data = this.selectBonusPlanByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<RepFloatFeeDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId() + GlobalConstant.UNDER_LINE + x.getAffirmDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (RepFloatFeeDto dto : data) {
                    List<RepFloatFeeDto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getAffirmDateStr());
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
    private List<RepFloatFeeDto> convertToBean(List<List<String>> dataList) {
        List<RepFloatFeeDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            RepFloatFeeDto entity = new RepFloatFeeDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setAffirmDateStr(list.get(2));
            } else {
                entity.setAffirmDateStr(" ");
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
    private List<RepFloatFeeDto> selectBonusPlanByKey(List<RepFloatFeeDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return repFloatFee001Mapper.selectFloatFeeList(dataList);
        }
        // 导入大于1000时，返回值
        List<RepFloatFeeDto> equtiyList = new ArrayList<>();

        List<RepFloatFeeDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<RepFloatFeeDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = repFloatFee001Mapper.selectFloatFeeList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = repFloatFee001Mapper.selectFloatFeeList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
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
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
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

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<RepFloatFeeDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("affirmDate", item.getAffirmDate());
                    if (StringUtil.isNotEmpty(this.selectData(map))) {
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
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
                                                 List<RepFloatFeeDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 7) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 7)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if (!this.getProdId(rowData.get(0))) {
                message.add("子产品代码未在系统中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品名称"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "确认日"));
            } else if (!isDateStr(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "确认日"));
            } else if (rowData.get(2).compareTo(DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_ANOTHER_FORMAT)) > 0) {
                message.add("确认日只能小于等于平台日。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业务代码"));
            } else if (!rowData.get(3).equals("赎回")) {
                message.add("业务代码默认赎回。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "确认份额"));
            } else if (!isBigDecimal(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "确认份额"));
            } else if (new BigDecimal(rowData.get(4)).compareTo(BigDecimal.ZERO) <= 0) {
                message.add("确认份额需要大于0。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "确认金额"));
            } else if (!isBigDecimal(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "确认金额"));
            } else if (new BigDecimal(rowData.get(5)).compareTo(BigDecimal.ZERO) <= 0) {
                message.add("确认金额需要大于0。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "超额报酬"));
            } else if (!isBigDecimal(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "超额报酬"));
            } else if (new BigDecimal(rowData.get(6)).compareTo(BigDecimal.ZERO) <= 0) {
                message.add("超额报酬需要大于0。");
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

    private RepFloatFeeDto convertToEntity(List<String> rowData) {
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
        return repFloatFeeDto;
    }

    public RepFloatFeeDto checkExistData(RepFloatFeeDto dto) {
        return repFloatFee001Mapper.checkExistData(dto);
    }
}

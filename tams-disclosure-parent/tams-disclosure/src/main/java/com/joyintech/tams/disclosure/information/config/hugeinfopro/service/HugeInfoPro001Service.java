package com.joyintech.tams.disclosure.information.config.hugeinfopro.service;

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
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dao.HugeInfoProMapper;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
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
        Page<HugeInfoPro001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
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
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            hugeInfoProMapper.deleteByKey(dto.getRedemptionId());
        }
        else if (StringUtil.equals(type, "NEW")) {
            HugeInfoPro001Dto hugeInfoPro001Dto = new HugeInfoPro001Dto();
            BeanUtil.copyPropertiesIgnoreNull(hugeInfoPro001Dto,dto);
            hugeInfoPro001Dto.setDataStatus("E");
            hugeInfoProMapper.insert(hugeInfoPro001Dto);
        }
        else {
            dto.setDataStatus("E");
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
        if (null == tempList.get(0) || tempList.get(0).size() != 6) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 6), MessageType.错误消息);
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
            List<HugeInfoPro001Dto> entities = this.convertToBean(dataList);
            List<HugeInfoPro001Dto> data = this.selectHugeInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<HugeInfoPro001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdRegistNo()
                                        + GlobalConstant.UNDER_LINE + x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getDealDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (HugeInfoPro001Dto dto : data) {
                    List<HugeInfoPro001Dto> listTemp = map.get(dto.getProdRegistNo()
                            + GlobalConstant.UNDER_LINE + dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getDealDateStr());
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
    private List<HugeInfoPro001Dto> convertToBean(List<List<String>> dataList) {
        List<HugeInfoPro001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            HugeInfoPro001Dto entity = new HugeInfoPro001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdRegistNo(list.get(0));
            } else {
                entity.setProdRegistNo(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setProdId(list.get(1));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setDealDateStr(list.get(2));
            } else {
                entity.setDealDateStr(" ");
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
    public List<HugeInfoPro001Dto> selectHugeInfoByKey(List<HugeInfoPro001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return hugeInfoProMapper.selectHugeInfoList(dataList);
        }
        // 导入大于1000时，返回值
        List<HugeInfoPro001Dto> equtiyList = new ArrayList<>();

        List<HugeInfoPro001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<HugeInfoPro001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = hugeInfoProMapper.selectHugeInfoList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = hugeInfoProMapper.selectHugeInfoList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public HugeInfoPro001Dto selectByBusPK(Map map) {
        return hugeInfoProMapper.selectByBusPK(map);
    }

    public static boolean isDateStr(String str) {
        boolean result=true;
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
        try {
            format.setLenient(false);
            format.parse(str);
            result=true;

        } catch (Exception var3) {
            result=false;
        }
        String yearStr=str.split("/")[0];
        if(yearStr.startsWith("0")||yearStr.length()!=4){
            result=false;
        }
        return result;
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<HugeInfoPro001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("prodRegistNo", item.getProdRegistNo());
                    map.put("dealDate", item.getDealDate());
                    item.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    item.setUpdateTime(DateUtil.now());
                    item.setUpdateUser(CommonUtil.getUserName());
                    HugeInfoPro001Dto dto = this.selectByBusPK(map);
                    if (dto != null) {
                        hugeInfoProMapper.updateByBusKey(item);
                    } else {
                        item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                        item.setRedemptionId(IDGenerator.generate(36));
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
                                                 List<HugeInfoPro001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> registNoList =  proElementAdjMapper.getRegistNo(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        List<String> prodList =  proElementAdjMapper.getProdId(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 6) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 6)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            } else if (!registNoList.contains(rowData.get(0).trim())){
                message.add("产品登记编码未在产品基本信息表中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if (!prodList.contains(rowData.get(1).trim())){
                message.add("子产品代码未在产品基本信息表中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "巨额赎回日期"));
            } else if (!isDateStr(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "巨额赎回日期"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "触发的情形说明文字"));
            } else if (rowData.get(3).length() > 500) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "触发的情形说明文字"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "拒绝赎回说明（文字）"));
            } else if (rowData.get(4).length() > 500) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "拒绝赎回说明（文字）"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "报告发布日期"));
            }else if(!isDateStr(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "报告发布日期"));
            }
            if(hugeInfoProMapper.isRelate(rowData.get(0), rowData.get(1)) == 0){
                message.add( "产品登记编码与产品子代码不存在联系。");
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

    private HugeInfoPro001Dto convertToEntity(List<String> rowData) {
        HugeInfoPro001Dto hugeInfoPro001Dto = new HugeInfoPro001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            hugeInfoPro001Dto.setProdRegistNo(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            hugeInfoPro001Dto.setProdId(rowData.get(1));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            hugeInfoPro001Dto.setDealDate(DateUtil.parse(rowData.get(2)));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            hugeInfoPro001Dto.setTriggerRemark(rowData.get(3));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            hugeInfoPro001Dto.setRejectRemark(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            hugeInfoPro001Dto.setRptIssueDate(DateUtil.parse(rowData.get(5)));
        }
        return hugeInfoPro001Dto;
    }

    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->hugeInfoProMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->hugeInfoProMapper.getProdIdlist(map));
    }


    public List<SelectOptionDto> getProdId(Map<String, Object> map) {
        return hugeInfoProMapper.getProdId(map);
    }
}
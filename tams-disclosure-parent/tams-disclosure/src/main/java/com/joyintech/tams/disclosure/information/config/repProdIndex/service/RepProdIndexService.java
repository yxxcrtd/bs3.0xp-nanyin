package com.joyintech.tams.disclosure.information.config.repProdIndex.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dao.RepProdIndexSMapper;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum002Mapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency002Mapper;
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
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RepProdIndexService extends CommonExportService<RepProdIndexDto> implements BaseProcessService<RepProdIndexDto> {

    @Autowired
    private RepProdIndexSMapper repProdIndexSMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
    @Autowired
    private ReportContentEnum002Mapper reportContentEnum002Mapper;

    @Autowired
    private SaleAgency002Mapper saleAgency002Mapper;
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
        if(map.containsKey("initValue")){
            String str=map.get("initValue").toString();
            List<String> prodIdList=Arrays.asList(str.split(","));
            map.put("prodIdList",prodIdList);
        }
        Integer page=1;
        if(StringUtil.isNotEmpty(pageParam.get("page"))){
            page=Integer.parseInt((String) pageParam.get("page"));
        }
        Integer length=100;
        if(StringUtil.isNotEmpty(pageParam.get("length"))){
            length=Integer.parseInt((String) pageParam.get("length"));
        }
        Page<SelectOptionDto> result = queryForPageForSelectReturn(page, length, ()
                -> repProdIndexSMapper.getProdId(map));
        return result;
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repProdIndexSMapper.getProdId2(map);
    }

    public Page<RepProdIndexDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepProdIndexDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repProdIndexSMapper.selectInfo(param));
        return result;
    }

    public Page<RepProdIndexDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepProdIndexDto> result = queryForPage( Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()),
                () -> repProdIndexSMapper.selectInfo(param));
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
            entity.setUpdateUser(CommonUtil.getUserName());
            repProdIndexSMapper.insert(entity);
            repProdIndexSMapper.insert2(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            entity.setUpdateUser(CommonUtil.getUserName());
            repProdIndexSMapper.update(entity);
            repProdIndexSMapper.update2(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            entity.setUpdateUser(CommonUtil.getUserName());
            repProdIndexSMapper.delete(entity);
            int r = repProdIndexSMapper.selectExits(entity);
            if(r == 0){
                repProdIndexSMapper.delete2(entity);
            }
        }
    }

    @Override
    public void doInsert(RepProdIndexDto dto, OperationType type) {
        // 数据存入留痕表备份 流程作废方便恢复
        dto.setStatus(dto.getDataStatus());
        repProdIndexSMapper.saveHisData(dto);

        //进入流程，数据状态改为 待复核
        RepProdIndexDto repProdIndexDto = new RepProdIndexDto();
        BeanUtil.copyPropertiesIgnoreNull(repProdIndexDto,dto);
        repProdIndexDto.setStatus("C");
        repProdIndexSMapper.updateByPrimaryKey(repProdIndexDto);

    }

    @Override
    public void doDelete(String workflowId) {
        //查留痕表对应workflowId的数据，并还原数据状态
        RepProdIndexDto dto = repProdIndexSMapper.selectHisData(workflowId);
        RepProdIndexDto repProdIndexDto = new RepProdIndexDto();
        BeanUtil.copyPropertiesIgnoreNull(repProdIndexDto,dto);
        repProdIndexSMapper.updateStatus(repProdIndexDto);
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
        if (null == tempList.get(0) || tempList.get(0).size() != 16) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 16), MessageType.错误消息);
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
            List<RepProdIndexDto> entities = this.convertToBean(dataList);
            List<RepProdIndexDto> data = this.selectProdIndexByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<RepProdIndexDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId() + GlobalConstant.UNDER_LINE + x.getValueDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (RepProdIndexDto dto : data) {
                    List<RepProdIndexDto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getValueDateStr());
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
    private List<RepProdIndexDto> convertToBean(List<List<String>> dataList) {
        List<RepProdIndexDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            RepProdIndexDto entity = new RepProdIndexDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setValueDateStr(list.get(2));
            } else {
                entity.setValueDateStr(" ");
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
    private List<RepProdIndexDto> selectProdIndexByKey(List<RepProdIndexDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return repProdIndexSMapper.selectProdIndexList(dataList);
        }
        // 导入大于1000时，返回值
        List<RepProdIndexDto> equtiyList = new ArrayList<>();

        List<RepProdIndexDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<RepProdIndexDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = repProdIndexSMapper.selectProdIndexList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = repProdIndexSMapper.selectProdIndexList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public Boolean selectProdId(String prodId) {
        String r = repProdIndexSMapper.selectProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public Boolean selectpProdId(String pProdId) {
        List<String>  list = repProdIndexSMapper.selectpProdId(pProdId);
        if(CollectionUtils.isNotEmpty(list)){
            return true;
        }else{
            return false;
        }
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
            List<RepProdIndexDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("valueDate", item.getValueDate());
                    RepProdIndexDto repProdIndexDto = this.selectData(map);
                    if (StringUtil.isNotEmpty(repProdIndexDto) && !StringUtil.equals(repProdIndexDto.getStatus(),"D")) {
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
                                                 List<RepProdIndexDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 16) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 16)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            RepProdIndexDto dto1 = new RepProdIndexDto();
            RepProdIndexDto dto2 = new RepProdIndexDto();
            RepProdIndexDto dto=null;
            if (!StringUtil.isNotEmpty(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "日期"));
            } else if (!isDateStr(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "日期"));
            } else {
                dto = repProdIndexSMapper.selectCfsj(rowData.get(0),rowData.get(2));
                dto2.setValueDate(DateUtil.parse(rowData.get(2)));
            }
            if(dto!=null) {
                if (StringUtil.equals(dto.getStatus(), "C")) {
                    message.add("导入文件中有与待复核数据重复的数据,不允许导入");
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if (!this.selectProdId(rowData.get(0))){
                message.add("产品子代码未在系统中维护。");
            } else {
                dto1 = selectProdInfo(rowData.get(0));
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            }else if(!this.selectpProdId(StringUtil.isNotBlank(rowData.get(1))?rowData.get(1).trim():"")){
                message.add("产品主代码未在系统中维护。");
            }else if(StringUtil.isNotEmpty(dto1.getpProdId()) && !(dto1.getpProdId().equals(rowData.get(1)))){
                message.add("产品主代码不是产品子代码的母产品代码。");
            }else{
                dto2.setpProdId(rowData.get(1));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品资产净值"));
            }else if(!isBigDecimal(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品资产净值"));
            } else if (new BigDecimal(rowData.get(3)).compareTo(new BigDecimal(0)) <= 0) {
                message.add("子产品资产净值需要大于0。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品实收资本"));
            } else if (!isBigDecimal(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品实收资本"));
            }else if (new BigDecimal(rowData.get(4)).compareTo(new BigDecimal(0)) <= 0) {
                message.add("子产品实收资本需要大于0。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品单位净值"));
             } else if (!isBigDecimal(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品单位净值"));
            } else {
                if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("04")) {
                    if (new BigDecimal(rowData.get(5)).compareTo(new BigDecimal(1)) != 0) {
                        message.add("产品类型为“现管类产品”时，子产品单位净值默认为1.0000。");
                    }
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品单位累计净值"));
            }else if(!isBigDecimal(rowData.get(6))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品单位累计净值"));
            }else{
                if(StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("04")){
                    if(new BigDecimal(rowData.get(6)).compareTo(new BigDecimal(1)) != 0){
                        message.add("产品类型为“现管类产品”时，子产品单位累计净值默认为1.0000。");
                    }
                }
            }
            if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("04")) {
                if (!StringUtil.isNotEmpty(rowData.get(7))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品万份收益"));
                } else if (!isBigDecimal(rowData.get(7))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品万份收益"));
                }
                if (!StringUtil.isNotEmpty(rowData.get(8))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品七日年化收益率"));
                } else if (!isBigDecimal(rowData.get(8))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "子产品七日年化收益率"));
                }
            } else if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("01")) {
                if (StringUtil.isNotEmpty(rowData.get(7))) {
                    message.add("产品类型是净值型，子产品万份收益不应填值。");
                }
                if (StringUtil.isNotEmpty(rowData.get(8))) {
                    message.add("产品类型是净值型，子产品七日年化收益率不应填值。");
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(9))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品总资产"));
            } else if (!isBigDecimal(rowData.get(9))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品总资产"));
            } else if (new BigDecimal(rowData.get(9)).compareTo(new BigDecimal(0)) <= 0) {
                message.add("母产品总资产需要大于0。");
            } else {
                dto2.setpAssets(new BigDecimal(rowData.get(9)));
            }

            if (!StringUtil.isNotEmpty(rowData.get(10))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品资产净值"));
            } else if (!isBigDecimal(rowData.get(10))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品资产净值"));
            } else if (new BigDecimal(rowData.get(10)).compareTo(new BigDecimal(0)) <= 0) {
                message.add("母产品资产净值需要大于0。");
            } else {
                dto2.setpAssetsValue(new BigDecimal(rowData.get(10)));
            }
            if (!StringUtil.isNotEmpty(rowData.get(11))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品实收资本"));
            } else if (!isBigDecimal(rowData.get(11))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品实收资本"));
            } else if (new BigDecimal(rowData.get(11)).compareTo(new BigDecimal(0)) <= 0) {
                message.add("母产品实收资本需要大于0。");
            } else {
                dto2.setpCapital(new BigDecimal(rowData.get(11)));
            }
            if (!StringUtil.isNotEmpty(rowData.get(12))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品单位净值"));
            } else if (!isBigDecimal(rowData.get(12))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品单位净值"));
            } else {
                dto2.setpUnitValue(new BigDecimal(rowData.get(12)));
            }
            if (!StringUtil.isNotEmpty(rowData.get(13))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品单位累计净值"));
            } else if (!isBigDecimal(rowData.get(13))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品单位累计净值"));
            } else {
                dto2.setpUnitValueT(new BigDecimal(rowData.get(13)));
            }
            if (StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("02")) {
                if (StringUtil.isNotEmpty(rowData.get(10)) && StringUtil.isNotEmpty(rowData.get(3))) {
                    if (new BigDecimal(rowData.get(10)).compareTo(new BigDecimal(rowData.get(3))) != 0) {
                        message.add("子产品资产净值应与母产品资产净值相等。");
                    }
                }

                if (StringUtil.isNotEmpty(rowData.get(11)) && StringUtil.isNotEmpty(rowData.get(4))) {
                    if (new BigDecimal(rowData.get(11)).compareTo(new BigDecimal(rowData.get(4))) != 0) {
                        message.add("子产品实收资本应与母产品实收资本相等。");
                    }
                }

                if (StringUtil.isNotEmpty(rowData.get(12)) && StringUtil.isNotEmpty(rowData.get(5))) {
                    if (new BigDecimal(rowData.get(12)).compareTo(new BigDecimal(rowData.get(5))) != 0) {
                        message.add("子产品单位净值应与母产品单位净值相等。");
                    }
                }
                if (StringUtil.isNotEmpty(rowData.get(13)) && StringUtil.isNotEmpty(rowData.get(6))) {
                    if (new BigDecimal(rowData.get(13)).compareTo(new BigDecimal(rowData.get(6))) != 0) {
                        message.add("子产品单位累计净值应与母产品单位累计净值相等。");
                    }
                }

            } else {
                if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("04")) {
                    if (new BigDecimal(rowData.get(12)).compareTo(new BigDecimal(1)) != 0) {
                        message.add("产品类型为“现管类产品”时，母产品单位净值默认为1.0000。");
                    }
                    if (new BigDecimal(rowData.get(13)).compareTo(new BigDecimal(1)) != 0) {
                        message.add("产品类型为“现管类产品”时，母产品单位累计净值默认为1.0000。");
                    }
                }
            }
            if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("04")) {
                if (StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("02")) {
                    if (!StringUtil.isNotEmpty(rowData.get(14))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品万份收益"));
                    } else if (!isBigDecimal(rowData.get(14))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品单位累计净值"));
                    } else if (new BigDecimal(rowData.get(14)).compareTo(new BigDecimal(rowData.get(7))) != 0) {
                        message.add("子产品万份收益应与母产品万份收益应相等。");
                    } else {
                        dto2.setpWYield(new BigDecimal(rowData.get(14)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(15))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品七日年化收益率"));
                    } else if (!isBigDecimal(rowData.get(15))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品单位累计净值"));
                    } else if (new BigDecimal(rowData.get(15)).compareTo(new BigDecimal(rowData.get(8))) != 0) {
                        message.add("子产品七日年化收益率应与母产品七日年化收益率应相等。");
                    } else {
                        dto2.setpYield7(new BigDecimal(rowData.get(15)));
                    }
                } else {
                    if (!StringUtil.isNotEmpty(rowData.get(14))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品万份收益"));
                    } else if (!isBigDecimal(rowData.get(14))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品单位累计净值"));
                    } else {
                        dto2.setpWYield(new BigDecimal(rowData.get(14)));
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(15))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "母产品七日年化收益率"));
                    } else if (!isBigDecimal(rowData.get(15))) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "母产品七日年化收益率"));
                    } else {
                        dto2.setpYield7(new BigDecimal(rowData.get(15)));
                    }
                }
            } else if (StringUtil.isNotEmpty(dto1.getProdType()) && dto1.getProdType().equals("01")) {
                if (StringUtil.isNotEmpty(rowData.get(7))) {
                    message.add("产品类型是净值型，母产品万份收益不应填值。");
                }
                if (StringUtil.isNotEmpty(rowData.get(8))) {
                    message.add("产品类型是净值型，母产品七日年化收益率不应填值。");
                }
            }
            if (StringUtil.isNotEmpty(dto1.getIsGrade()) && dto1.getIsGrade().equals("01")) {
                RepProdIndexDto repProdIndexDto2 = this.selectPProdInfo(dto2);
                RepProdIndexDto repProdIndexDto3 = this.selectPProd(dto2);
                if (repProdIndexDto2 != null && repProdIndexDto3 != null) {
                    message.add("产品类型是净值型，对于分级产品，每个子产品数据同一天的“母产品总资产“母产品资产净值”“母产品实收资本”“母产品单位净值”“母产品单位累计净值”“母产品万份收益”“母产品七日年化收益率”需要分别相等。");
                }
            }
            if (map.containsKey(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            boolean flag =true;
            for (int j = 1; j < dataList.size(); j++ ) {
                List<String> rowDataJ = dataList.get(j);
                if (StringUtil.equals(rowData.get(1),rowDataJ.get(1)) && StringUtil.equals(rowData.get(2),rowDataJ.get(2))) {

                    if (!StringUtil.equals(rowData.get(9), rowDataJ.get(9))) {
                        flag=false;
                    }
                    if (!StringUtil.equals(rowData.get(10), rowDataJ.get(10))) {
                        flag=false;
                    }
                    if (!StringUtil.equals(rowData.get(11), rowDataJ.get(11))) {
                        flag=false;
                    }
                    if (!StringUtil.equals(rowData.get(12), rowDataJ.get(12))) {
                        flag=false;
                    }
                    if (!StringUtil.equals(rowData.get(13), rowDataJ.get(13))) {
                        flag=false;
                    }
                }
            }
            if (!flag) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(2),
                        String.valueOf(i + 2), "产品类型是净值型，对于分级产品，每个子产品数据同一天的“母产品总资产“母产品资产净值”“母产品实收资本”“母产品单位净值”“母产品单位累计净值”“母产品万份收益”“母产品七日年化收益率”需要分别相等。"));
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

    private RepProdIndexDto convertToEntity(List<String> rowData) {
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
        return repProdIndexDto;
    }

    public RepProdIndexDto checkExistData(RepProdIndexDto dto) {
        return repProdIndexSMapper.checkExistData(dto);
    }

    /**
     * 查询产品树结构
     * @param
     * @return
     */
    public List<TreeNode> getProductTree() {
        List<TreeNode> result = new ArrayList<TreeNode>();
        //获取父节点
        result.addAll(this.getParentNodes());
        //查询当前所有产品，作为子节点
        List<TreeNode> prodInfoNodes=reportContentEnum002Mapper.selectProdInfo2();
        result.addAll(prodInfoNodes);
        return result;
    }

    /**
     * 组装除产品子节点以外的父节点
     * @return
     */
    public List<TreeNode> getParentNodes(){
        List<DataDictItem> prodStatusList = new ArrayList<DataDictItem>();//产品状态
        List<DataDictItem> issueTypeList = new ArrayList<DataDictItem>();//发行方式
        List<String> prodSeriesList = new ArrayList<String>();//产品系列
        List<TreeNode> parentNodes = new ArrayList<TreeNode>();
        prodStatusList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.信披_产品状态.getValue());
        issueTypeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.发行方式_信批.getValue());
        //一级：产品状态
        if (prodStatusList!=null && prodStatusList.size() > 0) {
            for (DataDictItem map_1 : prodStatusList) {
                //查询产品系列的条件
                Map<String,Object> param=new HashMap<>();
                TreeNode treeDto = new TreeNode();
                treeDto.setId(map_1.getItemCode());
                treeDto.setName(map_1.getItemValue());
                treeDto.setpId("");
                treeDto.setOpen(true);
                parentNodes.add(treeDto);
                param.put("prodStatus",map_1.getItemCode());
                //二级：发行方式
                for (DataDictItem map_2 : issueTypeList) {
                    TreeNode treeDto_2 = new TreeNode();
                    treeDto_2.setId(map_1.getItemCode() + map_2.getItemCode());
                    treeDto_2.setName(map_2.getItemValue());
                    treeDto_2.setpId(map_1.getItemCode());
                    treeDto_2.setOpen(true);
                    parentNodes.add(treeDto_2);
                    param.put("issueType",map_2.getItemCode());
                    //三级：产品系列：产品系列为文本框输入，不是字典值，需要从产品基本信息表中查询所有的产品系列
                    prodSeriesList = saleAgency002Mapper.selectAllProdSeries(param);
                    if(prodSeriesList!=null && prodSeriesList.size()>0){
                        for (String map_3 : prodSeriesList) {
                            TreeNode treeDto_3 = new TreeNode();
                            treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
                            treeDto_3.setName(map_3);
                            treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                            treeDto_3.setOpen(true);
                            parentNodes.add(treeDto_3);
                        }
                    }else{
                        TreeNode treeDto_3 = new TreeNode();
                        treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + "无数据");
                        treeDto_3.setName("无数据");
                        treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                        treeDto_3.setOpen(true);
                        parentNodes.add(treeDto_3);
                    }

                }
            }
        }
        return parentNodes;
    }
}

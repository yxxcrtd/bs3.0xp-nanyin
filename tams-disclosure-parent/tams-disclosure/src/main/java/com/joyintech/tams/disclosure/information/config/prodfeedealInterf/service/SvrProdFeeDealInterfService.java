package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.service;


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
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dao.SvrProdFeeDealInterfMapper;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SvrProdFeeDealInterfService extends CommonExportService<SvrProdFeeDealInterfDto> implements BaseProcessService<SvrProdFeeDealInterfDto> {

    @Autowired
    private SvrProdFeeDealInterfMapper svrProdFeeDealInterfMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    @Autowired
    private IPlatformDate iPlatformDate;

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
//        return svrProdFeeDealInterfMapper.getProdInsId(map);
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
                -> svrProdFeeDealInterfMapper.getProdInsId(map));
        return result;
    }

    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return svrProdFeeDealInterfMapper.getProdInsId2(map);
    }

    /**
     * 产品信息数据操作 <功能详细描述>
     *
     * @param entity 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Override
    public void doComplete(SvrProdFeeDealInterfDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        //entity.setUpdateUser(CommonUtil.getUserName());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            svrProdFeeDealInterfMapper.insertPrdFeeDeal(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            svrProdFeeDealInterfMapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {

            svrProdFeeDealInterfMapper.delete(entity);
        }
    }

    /**
     * 产品交易信息查询 <功能详细描述>
     *
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SvrProdFeeDealInterfDto> selectInfo(Map<String, Object> param) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SvrProdFeeDealInterfDto> result = queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> svrProdFeeDealInterfMapper.selectInfo(param));
        return result;
    }

    public Page<SvrProdFeeDealInterfDto> selectForExport(Map<String, Object> param) {
//        List<SvrProdFeeDealInterfDto> entityList = new ArrayList<>();
//        if (param.containsKey("selectData")) {
//            String keyList = param.get("selectData").toString();
//            List<SvrProdFeeDealInterfDto> investList = null;
//            try {
//                investList = JsonUtils.json2List(keyList,
//                        SvrProdFeeDealInterfDto.class);
//                entityList = svrProdFeeDealInterfMapper.selectForExport(investList);
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        } else {
//            entityList = svrProdFeeDealInterfMapper.selectInfo(param);
//        }
//        return entityList;
        Page<SvrProdFeeDealInterfDto> result = queryForPage( Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()),
                () -> svrProdFeeDealInterfMapper.selectInfo(param));
        return result;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/prodfeedealInterf/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<SvrProdFeeDealInterfDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }
    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public SvrProdFeeDealInterfDto selectData(Map<String, Object> param) {
        return svrProdFeeDealInterfMapper.selectData(param);
    }
    public SvrProdFeeDealInterfDto selectByProdID(SvrProdFeeDealInterfDto dto) {
        return svrProdFeeDealInterfMapper.selectByProdID(dto);
    }
    public Map<String,String> selectProdName(String prodId) {
        Map<String, String> map = svrProdFeeDealInterfMapper.selectProdName(prodId);
        if (map==null){
            map = new HashMap<>();
            map.put("PROD_NAME", "");
        }
        return map;
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
        if (null == tempList.get(0) || tempList.get(0).size() != 5) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 5), MessageType.错误消息);
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
            List<SvrProdFeeDealInterfDto> entities = this.convertToBean(dataList);
            List<SvrProdFeeDealInterfDto> data = this.selectFeeDealByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<SvrProdFeeDealInterfDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getFeeType()
                                        + GlobalConstant.UNDER_LINE + x.getPayDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (SvrProdFeeDealInterfDto dto : data) {
                    List<SvrProdFeeDealInterfDto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getFeeType()
                            + GlobalConstant.UNDER_LINE + dto.getPayDateStr());
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
    private List<SvrProdFeeDealInterfDto> convertToBean(List<List<String>> dataList) {
        List<SvrProdFeeDealInterfDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            SvrProdFeeDealInterfDto entity = new SvrProdFeeDealInterfDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setFeeType(this.getFeeTYPE(list.get(1)));
            } else {
                entity.setFeeType(" ");
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                entity.setPayDateStr(list.get(4));
            } else {
                entity.setPayDateStr(" ");
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
    private List<SvrProdFeeDealInterfDto> selectFeeDealByKey(List<SvrProdFeeDealInterfDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return svrProdFeeDealInterfMapper.selectFeeDealList(dataList);
        }
        // 导入大于1000时，返回值
        List<SvrProdFeeDealInterfDto> equtiyList = new ArrayList<>();

        List<SvrProdFeeDealInterfDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<SvrProdFeeDealInterfDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = svrProdFeeDealInterfMapper.selectFeeDealList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = svrProdFeeDealInterfMapper.selectFeeDealList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
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


    public Boolean getProdId(String prodId) {
        String r = svrProdFeeDealInterfMapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
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
            List<SvrProdFeeDealInterfDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("feeType", item.getFeeType());
                    map.put("payDate", item.getPayDate());
                    item.setUpdateUser(CommonUtil.getUserName());
                    if (null != this.selectData(map)){
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
                        item.setCreateUser(CommonUtil.getUserName());
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
                                                 List<SvrProdFeeDealInterfDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 5) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 5)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            }else if(!this.getProdId(rowData.get(0))) {
                message.add("产品主代码未在系统中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "费用类型"));
            } else if (!StringUtil.isNotEmpty(this.getFeeTYPE(rowData.get(1)))) {
                message.add(this.getMessage("费用类型值不正确。"));
            } else if (rowData.get(1).trim().equals("客户收益") &&
                    (!StringUtil.isNotEmpty(rowData.get(2)))) {
                message.add("当费用类型为“客户收益”时费用金额不能为空。");
            } else if ((rowData.get(1).trim().equals("托管费") ||
                    rowData.get(1).trim().equals("管理费（固定）") ||
                    rowData.get(1).trim().equals("管理费（超额）")||
                    rowData.get(1).trim().equals("销售费")||
                    rowData.get(1).trim().equals("申购费")||
                    rowData.get(1).trim().equals("赎回费")
            ) &&
                    (!StringUtil.isNotEmpty(rowData.get(3)))) {
                message.add("当费用类型为“托管费”“管理费（固定）”“管理费（超额）”“销售费”“申购费”“赎回费”时自成立以来发生额（贷方）不可为空。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "支付日"));
            } else if (!isDateStr(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "支付日"));
            } else if (rowData.get(4).compareTo(DateUtil.format(iPlatformDate.getSysDate(),DateUtil.DEFAULT_ANOTHER_FORMAT)) > 0) {
                message.add("支付日只能小于等于平台日。");
            }
            if(StringUtil.isNotEmpty(rowData.get(3)) && !isBigDecimal(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "自成立以来发生额（贷方）"));
            }
            if(StringUtil.isNotEmpty(rowData.get(2)) && !isBigDecimal(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "费用金额"));
            }
            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(4), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(4),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private SvrProdFeeDealInterfDto convertToEntity(List<String> rowData) {
        SvrProdFeeDealInterfDto svrProdFeeDealInterfDto = new SvrProdFeeDealInterfDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            svrProdFeeDealInterfDto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            svrProdFeeDealInterfDto.setFeeType(this.getFeeTYPE(rowData.get(1)));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            svrProdFeeDealInterfDto.setPaidFee(new BigDecimal(rowData.get(2)));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            svrProdFeeDealInterfDto.setAccrual(new BigDecimal(rowData.get(3)));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            svrProdFeeDealInterfDto.setPayDate(DateUtil.parse(rowData.get(4)));
        }
        svrProdFeeDealInterfDto.setStatus("E");
        return svrProdFeeDealInterfDto;
    }

    public String getFeeTYPE(String feetype) {
        return svrProdFeeDealInterfMapper.getFeeTYPE(feetype);
    }

    public SvrProdFeeDealInterfDto checkExistData(SvrProdFeeDealInterfDto dto) {
        return svrProdFeeDealInterfMapper.checkExistData(dto);
    }
}

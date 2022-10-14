package com.joyintech.tams.disclosure.information.config.repRelDeal.service;

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
import com.joyintech.tams.disclosure.information.config.repRelDeal.dao.RepRelDeal001Mapper;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
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
import java.util.stream.Collectors;

@Service
public class RepRelDealService extends CommonExportService<RepRelDealDto> implements BaseProcessService<RepRelDealDto> {
    @Autowired
    private RepRelDeal001Mapper repRelDeal001Mapper;

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
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->repRelDeal001Mapper.getProdInsId(map));
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repRelDeal001Mapper.getProdInsId(map);
    }

    public Page<RepRelDealDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepRelDealDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repRelDeal001Mapper.selectInfo(param));
        return result;
    }

    public Page<RepRelDealDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepRelDealDto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()),
                () -> repRelDeal001Mapper.selectInfo(param));
        return result;
    }

    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepRelDealDto selectData(Map<String, Object> param) {
        return repRelDeal001Mapper.selectData(param);
    }

    @Override
    public void doComplete(RepRelDealDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        entity.setUpdateUser(CommonUtil.getUserName());
        if(StringUtil.isEmpty(entity.getCreateDept())){
            entity.setCreateDept("");
        }
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repRelDeal001Mapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repRelDeal001Mapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            repRelDeal001Mapper.delete(entity);
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repRelDeal/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepRelDealDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }

    public List<RepRelDealDto> selectExist(RepRelDealDto dto) {
        List<RepRelDealDto> result = repRelDeal001Mapper.selectExist(dto);
        return result;
    }

    public RepRelDealDto selectData2(Map<String, Object> param) {
        return repRelDeal001Mapper.selectData2(param);
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
        if (null == tempList.get(0) || tempList.get(0).size() != 10) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 10), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage( this.getMessage("REPORT_COMMON_FILE_E_003C",
                        maxCount), MessageType.错误消息);
                return webResult;
            }
            List<RepRelDealDto> entities = this.convertToBean(dataList);
            List<RepRelDealDto> data = this.selectRelDealByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<RepRelDealDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getEffdateStr()
                                        + GlobalConstant.UNDER_LINE + x.getEffdelidateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (RepRelDealDto dto : data) {
                    List<RepRelDealDto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getEffdateStr()
                            + GlobalConstant.UNDER_LINE + dto.getEffdelidateStr());
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
    private List<RepRelDealDto> convertToBean(List<List<String>> dataList) {
        List<RepRelDealDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            RepRelDealDto entity = new RepRelDealDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(8))) {
                entity.setEffdateStr(list.get(8));
            } else {
                entity.setEffdateStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(9))) {
                entity.setEffdelidateStr(list.get(9));
            } else {
                entity.setEffdelidateStr(" ");
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
    private List<RepRelDealDto> selectRelDealByKey(List<RepRelDealDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return repRelDeal001Mapper.selectRelDealList(dataList);
        }
        // 导入大于1000时，返回值
        List<RepRelDealDto> equtiyList = new ArrayList<>();

        List<RepRelDealDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<RepRelDealDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = repRelDeal001Mapper.selectRelDealList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = repRelDeal001Mapper.selectRelDealList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public Boolean selectPProdId(String prodId) {
        String r = repRelDeal001Mapper.selectPProdId(prodId);
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
            List<RepRelDealDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    repRelDeal001Mapper.importInfo(item);
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
                                                 List<RepRelDealDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 10) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 10)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            } else if (!this.selectPProdId(rowData.get(0).trim())) {
                message.add("产品主代码未在系统中维护。");
            }

            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额"));
            } else if (!isBigDecimal(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "投资于关联方承销的证券交易金额"));
            } else if (!isBigDecimal(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "投资于关联方承销的证券交易金额"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "与关联方作为交易对手开展的投融资业务交易金额"));
            } else if (!isBigDecimal(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "与关联方作为交易对手开展的投融资业务交易金额"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "投资关联方作为管理人的资产管理产品交易金额"));
            } else if (!isBigDecimal(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "投资关联方作为管理人的资产管理产品交易金额"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "支付关联方托管费"));
            } else if (!isBigDecimal(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "支付关联方托管费"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "支付关联方代销费"));
            } else if (!isBigDecimal(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "支付关联方代销费"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(7))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "发生其他关联交易金额"));
            } else if (!isBigDecimal(rowData.get(7))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "发生其他关联交易金额"));
            }

            Date platDate = DateUtil.platDateFromCache();
            if (!StringUtil.isNotEmpty(rowData.get(8))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "生效日"));
            } else if (!isDateStr(rowData.get(8).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "生效日"));
            } else if (DateUtil.compareDate(DateUtil.parse(rowData.get(8)), platDate) < 0) {
                //message.add("生效日不能小于平台日。");
            }

            if (!StringUtil.isNotEmpty(rowData.get(9))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "生效截止日期"));
            } else if (!this.isDateStr(rowData.get(9).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "生效截止日期"));
            } else if (DateUtil.compareDate(DateUtil.parse(rowData.get(9)), platDate) < 0) {
                //message.add("生效截止日期不能小于平台日。");
            }

            if (StringUtil.isNotEmpty(rowData.get(8)) && StringUtil.isNotEmpty(rowData.get(9))) {
                if (isDateStr(rowData.get(8)) && isDateStr(rowData.get(9))) {
                    Date effdate = DateUtil.parse(rowData.get(8));
                    Date effdelidate = DateUtil.parse(rowData.get(9));
                    if (DateUtil.compareDate(effdelidate, effdate) < 0) {
                        message.add("生效截止日期不能小于生效日。");
                    }
                }
            }

            if (StringUtil.isNotEmpty(rowData.get(0)) && StringUtil.isNotEmpty(rowData.get(8)) && StringUtil.isNotEmpty(rowData.get(9))
                    && isDateStr(rowData.get(8).trim()) && isDateStr(rowData.get(9).trim())) {

                Date effdate = DateUtil.parse(rowData.get(8).toString().trim());
                Date effdelidate = DateUtil.parse(rowData.get(9).toString().trim());
                if (DateUtil.compareDate(effdelidate, effdate) >= 0) {
                    RepRelDealDto ser = new RepRelDealDto();
                    ser.setProdId(rowData.get(0).trim());
                    ser.setEffdate(effdate);
                    ser.setEffdelidate(effdelidate);
                    List<RepRelDealDto> dtos = repRelDeal001Mapper.selectExistInfo(ser);
                    for (RepRelDealDto dto : dtos) {
                        if (!((DateUtil.compareDate(effdate, dto.getEffdate()) < 0 && DateUtil.compareDate(effdelidate, dto.getEffdate()) < 0)
                                || (DateUtil.compareDate(effdate, dto.getEffdelidate()) > 0 && DateUtil.compareDate(effdelidate, dto.getEffdelidate()) > 0)
                                || (DateUtil.compareDate(effdate, dto.getEffdate()) == 0 && DateUtil.compareDate(effdelidate, dto.getEffdelidate()) == 0)
                        )) {
                            message.add("生效区间重叠，系统中已存在的生效区间:" + DateUtil.format(dto.getEffdate(), "yyyy-MM-dd")
                                    + "-" + DateUtil.format(dto.getEffdelidate(), "yyyy-MM-dd"));
                            break;
                        }
                    }
                }
                if (map.containsKey(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(8)
                        + GlobalConstant.UNDER_LINE + rowData.get(9))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
                }
                map.put(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(8)
                        + GlobalConstant.UNDER_LINE + rowData.get(9), rowData);
                if (CollectionUtils.isNotEmpty(message)) {
                    checkResult.add(new ExcelValidDto(rowData.get(0)
                            + GlobalConstant.UNDER_LINE + rowData.get(8)
                            + GlobalConstant.UNDER_LINE + rowData.get(9),
                            String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
                }
                else {
                    RepRelDealDto relDealDto = this.convertToEntity(rowData);
                    relDealDto.setExcelNum(String.valueOf(i + 2));
                    entities.add(relDealDto);
                }
            }else {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(8)
                        + GlobalConstant.UNDER_LINE + rowData.get(9),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
        }
        if (checkResult.isEmpty()) {
            List<String> message = new ArrayList<>();
            Map<String, List<RepRelDealDto>> realmap = entities.stream().collect(Collectors.groupingBy(RepRelDealDto::getProdId));
            for (String key : realmap.keySet()) {
                List<RepRelDealDto> repRelDealDtoList = realmap.get(key);
                if (repRelDealDtoList.size() > 1) {
                    loop:
                    for (int m = 0; m < repRelDealDtoList.size() - 1; m++) {
                        RepRelDealDto dto1 = repRelDealDtoList.get(m);
                        for (int n = m + 1; n < repRelDealDtoList.size(); n++) {
                            RepRelDealDto dto2 = repRelDealDtoList.get(n);
                            if (!((DateUtil.compareDate(dto2.getEffdate(), dto1.getEffdate()) < 0 && DateUtil.compareDate(dto2.getEffdelidate(), dto1.getEffdate()) < 0)
                                    || (DateUtil.compareDate(dto2.getEffdate(), dto1.getEffdelidate()) > 0 && DateUtil.compareDate(dto2.getEffdelidate(), dto1.getEffdelidate()) > 0)
                                    || (DateUtil.compareDate(dto2.getEffdate(), dto1.getEffdate()) == 0 && DateUtil.compareDate(dto2.getEffdelidate(), dto1.getEffdelidate()) == 0))) {
                                message.add("文件中同一产品生效区间有重叠：第" + dto1.getExcelNum() + "行和第" + dto2.getExcelNum() + "行");
                                break loop;
                            }
                        }
//                        if (CollectionUtils.isNotEmpty(message)) {
//                            checkResult.add(new ExcelValidDto(dto1.getEffdateStr()+GlobalConstant.UNDER_LINE+dto1.getEffdelidateStr(), String.valueOf(dto1.getExcelNum()+ 2), StringUtil.joinString(message, "<br>")));
//                        }
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(realmap.entrySet().stream().findFirst().get().getValue().get(0).getEffdateStr()+GlobalConstant.UNDER_LINE+realmap.entrySet().stream().findFirst().get().getValue().get(0).getEffdelidateStr(), String.valueOf(realmap.entrySet().stream().findFirst().get().getValue().get(0).getExcelNum()), StringUtil.joinString(message, "<br>")));
            }
        }
        return checkResult;
    }

    private RepRelDealDto convertToEntity(List<String> rowData) {
        RepRelDealDto repRelDealDto = new RepRelDealDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            repRelDealDto.setProdId(rowData.get(0).toString().trim());
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            repRelDealDto.setCreditorAmt(new BigDecimal(rowData.get(1).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            repRelDealDto.setBondAmt(new BigDecimal(rowData.get(2).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            repRelDealDto.setInvestAmt(new BigDecimal(rowData.get(3).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            repRelDealDto.setAssetAmt(new BigDecimal (rowData.get(4).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            repRelDealDto.setTrustFee(new BigDecimal (rowData.get(5).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            repRelDealDto.setSellFee(new BigDecimal (rowData.get(6).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            repRelDealDto.setOtherFee(new BigDecimal (rowData.get(7).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(8))) {
            repRelDealDto.setEffdate(DateUtil.parse(rowData.get(8).toString().trim()));
        }
        if (StringUtil.isNotEmpty(rowData.get(9))) {
            repRelDealDto.setEffdelidate(DateUtil.parse(rowData.get(9).toString().trim()));
        }
        repRelDealDto.setStatus("E");
        repRelDealDto.setUuid(IDGenerator.generate(36));
        return repRelDealDto;
    }
}

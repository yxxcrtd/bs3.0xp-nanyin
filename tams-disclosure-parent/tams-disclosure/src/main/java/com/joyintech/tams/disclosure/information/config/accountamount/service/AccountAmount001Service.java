package com.joyintech.tams.disclosure.information.config.accountamount.service;

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
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.accountamount.dao.AccountAmountMapper;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
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
public class AccountAmount001Service extends CommonExportService<AccountAmount001Dto> implements BaseProcessService<AccountAmount001Dto> {

    @Autowired
    private AccountAmountMapper accountAmountMapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<AccountAmount001Dto> selectForPage(Map<String, Object> param) {
        String booksetId = (String)param.get("booksetId");
        if(StringUtil.isNotEmpty(booksetId)){
            param.put("booksetIdList", StringUtil.splitToList(GlobalConstant.COMMA,booksetId));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<AccountAmount001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> accountAmountMapper.select(param));
        return result;
    }
    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<AccountAmount001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("booksetId",item.getBooksetId());
                    map.put("dateStart",item.getDateStartStr());
                    map.put("dateEnd",item.getDateEndStr());
                    map.put("subjectNo",item.getSubjectNo());
                    AccountAmount001Dto dto = this.selectByPrimaryKey(map);
                    if (null !=dto && !StringUtil.equals(dto.getStatus(),"D")){
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
                        this.doComplete(item,OperationType.NEW);
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
                                                 List<AccountAmount001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList =  accountAmountMapper.getProdId1(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
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
            } else if (!findList.contains(rowData.get(0).trim())) {
                message.add("产品主代码在产品基本信息表中没有维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "开始日期"));
            } else if (!isDateStr(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "开始日期"));
            }else{
                if(!DateS(rowData.get(1))){
                    message.add("开始日期只能是xxxx/01/01。");
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "结束日期"));
            } else if (!isDateStr(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "结束日期"));
            }else {
                if(!DateE(rowData.get(2))){
                    message.add("结束日期只能是xxxx/06/30或者是xxxx/12/31。");
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "科目号"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "科目发生额"));
            } else if (!isBigDecimal(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "科目发生额"));
            }
            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(2)
                        + GlobalConstant.UNDER_LINE + rowData.get(3),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    @Override
    public void doComplete(AccountAmount001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            HashMap map =new HashMap();
            map.put("booksetId",dto.getBooksetId());
            map.put("dateStart",dto.getDateStartStr());
            map.put("dateEnd",dto.getDateEndStr());
            map.put("subjectNo",dto.getSubjectNo());
            AccountAmount001Dto dto1 = accountAmountMapper.selectByPrimaryKey(map);
            dto1.setUpdateUser(LoginUserUtil.getUserName());
            dto1.setStatus("D");
            accountAmountMapper.insertHis(dto1);
            accountAmountMapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            HashMap map =new HashMap();
            map.put("booksetId",dto.getBooksetId());
            map.put("dateStart",dto.getDateStartStr());
            map.put("dateEnd",dto.getDateEndStr());
            map.put("subjectNo",dto.getSubjectNo());
            AccountAmount001Dto dto1 = accountAmountMapper.selectByPrimaryKey(map);
            if (dto1!=null && !StringUtil.equals(dto1.getStatus(),"D")){
                dto.setStatus("E");
                dto.setErrormsg("");
                dto.setUpdateUser(CommonUtil.getUserName());
                accountAmountMapper.updateByKey(dto);
            }
            else {
                AccountAmount001Dto accountAmount001Dto = new AccountAmount001Dto();
                BeanUtil.copyPropertiesIgnoreNull(accountAmount001Dto, dto);
                accountAmount001Dto.setoCcy("CNY");
                accountAmount001Dto.setStatus("E");
                accountAmount001Dto.setUpdateUser(CommonUtil.getUserName());
                accountAmountMapper.insert(accountAmount001Dto);
            }
        }
        else {
            dto.setStatus("E");
            dto.setErrormsg("");
            dto.setUpdateUser(CommonUtil.getUserName());
            accountAmountMapper.updateByKey(dto);
        }

    }



    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/accountamount/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<AccountAmount001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }



    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return accountAmountMapper.findProdId(map);
    }



    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->accountAmountMapper.getProdIdlist(map));
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
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT") == null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<AccountAmount001Dto> entities = this.convertToBean(dataList);
            List<AccountAmount001Dto> data = this.selectBalanceByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<AccountAmount001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getBooksetId()
                                        + GlobalConstant.UNDER_LINE + x.getDateStartStr()
                                        + GlobalConstant.UNDER_LINE + x.getDateEndStr()
                                        + GlobalConstant.UNDER_LINE + x.getSubjectNo()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (AccountAmount001Dto dto : data) {
                    List<AccountAmount001Dto> listTemp = map.get(dto.getBooksetId()
                            + GlobalConstant.UNDER_LINE + dto.getDateStartStr()
                            + GlobalConstant.UNDER_LINE + dto.getDateEndStr()
                            + GlobalConstant.UNDER_LINE + dto.getSubjectNo());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_1111_1",
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
    private List<AccountAmount001Dto> convertToBean(List<List<String>> dataList) {
        List<AccountAmount001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            AccountAmount001Dto entity = new AccountAmount001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setBooksetId(list.get(0));
            } else {
                entity.setBooksetId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setDateStartStr(list.get(1));
            } else {
                entity.setDateStartStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setDateEndStr(list.get(2));
            } else {
                entity.setDateEndStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                entity.setSubjectNo(list.get(3));
            } else {
                entity.setSubjectNo(" ");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }

    /**
     * 分批查询
     * @param
     * @return
     */
    private List<AccountAmount001Dto> selectBalanceByKey(List<AccountAmount001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return accountAmountMapper.selectBalanceList(dataList);
        }
        // 导入大于1000时，返回值
        List<AccountAmount001Dto> equtiyList = new ArrayList<>();

        List<AccountAmount001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<AccountAmount001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = accountAmountMapper.selectBalanceList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = accountAmountMapper.selectBalanceList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
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

    public static boolean DateS(String str) {
        boolean result=false;
        String monthStr=str.split("/")[1];
        String dayStr=str.split("/")[2];
        if((StringUtil.equals(monthStr,"01")||StringUtil.equals(monthStr,'1'))&&(StringUtil.equals(dayStr,"01")||StringUtil.equals(dayStr,'1'))){
            result=true;
        }
        return result;
    }

    public static boolean DateE(String str) {
        boolean result=false;
        String monthStr=str.split("/")[1];
        String dayStr=str.split("/")[2];
        if(((StringUtil.equals(monthStr,"06")||StringUtil.equals(monthStr,'6'))&&StringUtil.equals(dayStr,"30"))||(StringUtil.equals(monthStr,"12")&&StringUtil.equals(dayStr,"31"))){
            result=true;
        }
        return result;
    }





    private AccountAmount001Dto convertToEntity(List<String> rowData) {
        AccountAmount001Dto accountAmount001Dto = new AccountAmount001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            accountAmount001Dto.setBooksetId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            accountAmount001Dto.setDateStart(DateUtil.parse(rowData.get(1)));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            accountAmount001Dto.setDateEnd(DateUtil.parse(rowData.get(2)));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            accountAmount001Dto.setSubjectNo(rowData.get(3));
        }

        if (StringUtil.isNotEmpty(rowData.get(4))) {
            accountAmount001Dto.setTdyODAmt(new BigDecimal(rowData.get(4)));
        }
        return accountAmount001Dto;
    }
    public AccountAmount001Dto selectByPrimaryKey(Map map) {
        return accountAmountMapper.selectByPrimaryKey(map);
    }
}
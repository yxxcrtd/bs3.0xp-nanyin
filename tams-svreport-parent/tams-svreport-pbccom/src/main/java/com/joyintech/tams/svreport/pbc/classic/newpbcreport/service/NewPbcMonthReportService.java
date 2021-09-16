package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelFileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.ExcelOperationUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.service.PbcDatUtil;
import com.joyintech.tams.svreport.pbc.common.enums.PbcDictCodeEnums;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewPbcMonthReportService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String TIPS_SUFFIX_ERROR = "只支持2003版(文件后缀为.xls)的excel导入，请修改文件重新导入";
    private static final String TIPS_FILE_NAME_ERROR = "文件名称不规范，前八位必须为年月日且必须是每月的最后一天或者每年的1月1号";

    private static final Set<String> sheetNames = new HashSet<>();
    /**
     * 人行导出模版sheet页名称
     */
    private final static String SHEET_1_1 = "表1-1资管产品募集余额";

    private final static String SHEET_1_2 = "表1-2募集及兑付发生额";

    private final static String SHEET_1_3 = "表1-3只数情况";

    private final static String SHEET_1_4 = "表1-4境内募集余额分地区";

    private final static String SHEET_1_5 = "表1-5提前及延期兑付";

    private final static String SHEET_1_6 = "表1-6资管产品到期未兑付";

    private final static String SHEET_2_1 = "表2-1资产负债";

    private final static String SHEET_2_2 = "表2-2除回购和拆借外贷款按行业、企业";

    private final static String SHEET_2_3 = "表2-3除回购和拆借外贷款分地区";

    private final static String SHEET_2_4 = "表2-4资产收益权按基础资产投向";

    private final static String SHEET_2_5 = "表2-5企业债券按行业、企业";

    private final static String SHEET_3_1 = "表3-1存续产品分期限募集余额";

    private final static String SHEET_3_2 = "表3-2新发产品分期限募集金额";

    private final static String SHEET_3_3 = "表3-3资产负债剩余期限";

    static {
        sheetNames.add(SHEET_1_1);
        sheetNames.add(SHEET_1_2);
        sheetNames.add(SHEET_1_3);
        sheetNames.add(SHEET_1_4);
        sheetNames.add(SHEET_1_5);
        sheetNames.add(SHEET_1_6);
        sheetNames.add(SHEET_2_1);
        sheetNames.add(SHEET_2_2);
        sheetNames.add(SHEET_2_3);
        sheetNames.add(SHEET_2_4);
        sheetNames.add(SHEET_2_5);
        sheetNames.add(SHEET_3_1);
        sheetNames.add(SHEET_3_2);
        sheetNames.add(SHEET_3_3);
    }

    @Autowired
    private List<IDealNewPbcMonthReport> iDealNewPbcMonthReports;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    private static final Map<String,IDealNewPbcMonthReport> allNewPbcMonthReportsMap = new ConcurrentHashMap<>();

    /**
     *
     * 业务导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult checkMonthReport(String fileName) {
        AjaxResult ajaxResult = new AjaxResult();
        if(!fileName.endsWith(".xls")){
            ValidMessage message = new ValidMessage();
            message.setMessage(TIPS_SUFFIX_ERROR);
            ajaxResult.setErrorList(Arrays.asList(message));
            return ajaxResult;
        }
        try {
            List<ValidMessage> validMessages1 = checkFileName(fileName);
            if (CollectionUtil.isNotEmpty(validMessages1)) {
                ajaxResult.setErrorList(validMessages1);
                return ajaxResult;
            }
            Workbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
            List<ValidMessage> validMessages = checkSheetName(workbook);
            if (CollectionUtil.isNotEmpty(validMessages)) {
                for (ValidMessage validMessage : validMessages) {
                    ajaxResult.setMessage(validMessage.getMessage());
                }
                ajaxResult.setErrorList(validMessages);
                ajaxResult.setData("TEMP_ERROR_MSG");
                ajaxResult.setMessageType(MessageType.错误消息);
                ajaxResult.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ajaxResult;
            }
            String name = FileNameUtil.getName(fileName);
            String[] strName = name.split("_");
            if (strName.length == 0) {
                ValidMessage message = new ValidMessage();
                message.setMessage(TIPS_FILE_NAME_ERROR);
                ajaxResult.setErrorList(Arrays.asList(message));
                return ajaxResult;
            }
            String fName = strName[0];
            String statusVdate = fName.substring(0, 4).concat(fName.substring(4, 6));
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("statusVdate", statusVdate);
            paramMap.put("datZipType", "MI");
            //数据权限模式（01：银行模式，02：联社模式）
            String authorityType = CacheUtil.getSysParam("AUTHORITY_TYPE");
            if("02".equals(authorityType)){
                //机构
                paramMap.put("reportOrgName",SecurityUtil.getLoginUser().getOrgCode());
            }
            List<PbcSendZipDatFile> pbcSendZipDatFiles = pbcSendZipDatFileMapper.selectByZipTypeAndStatusVdate(paramMap);
            Set<String> sets = new HashSet<>();
            for (PbcSendZipDatFile pbcSendZipDatFile : pbcSendZipDatFiles) {
                if(pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_接口报送.getValue())
                        || pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_手工处理.getValue())){
                    sets.add(getSheetName(pbcSendZipDatFile.getDatZipDatType()));
                }
            }
            List<ValidMessage> validMessageList = new ArrayList<>();
            if (sets.size() > 0) {
                for (String set : sets) {
                    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
                    while (sheetIterator.hasNext()) {
                        Sheet sheet = sheetIterator.next();
                        String sheetName = sheet.getSheetName();
                        if (sheetName.equals(set)) {
                            ValidMessage message = new ValidMessage();
                            message.setMessage(set);
                            validMessageList.add(message);
                        }
                    }
                }
                if(CollectionUtil.isNotEmpty(validMessageList)){
                    //do not ask me why,god know it!
                    ajaxResult.setData("CONFIRM_MSG");
                    ajaxResult.setErrorList(validMessageList);
                    ajaxResult.setMessageType(MessageType.确认消息);
                    ajaxResult.setStatuCode(HttpStatus.OK.value());
                    return ajaxResult;
                }
            }
        }catch (Exception e){
            logger.error("检查规范异常",e);
            return new AjaxResult("检查规范异常", MessageType.错误消息);
        }
        ajaxResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
        return ajaxResult;

    }

    public List<ExcelValidDto> importMonthReport(String fileName) {
        List<ExcelValidDto> errorList = new ArrayList<>();
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            String name = FileNameUtil.getName(fileName);
            String[] strName = name.split("_");
            if(strName.length == 0){
                ExcelValidDto dto = new ExcelValidDto();
                dto.setErrorInfo(TIPS_FILE_NAME_ERROR);
                errorList.add(dto);
                return errorList;
            }
            String fName = strName[0];
            String year = fName.substring(0, 4);
            String mon = fName.substring(4, 6);
            String day = fName.substring(6, 8);
            String statusVdate1;
            String statusVdate;
            if("01".equals(mon) && "01".equals(day)){
                int lastYear = Integer.parseInt(year) - 1;
                statusVdate1 = String.valueOf(lastYear).concat("-").concat("12").concat("-").concat("31");
                statusVdate = String.valueOf(lastYear).concat("12");//.concat("-").concat(fName.substring(6,8));
            }else{
                statusVdate1 = year.concat("-").concat(fName.substring(4,6)).concat("-").concat(fName.substring(6,8));
                statusVdate = year.concat(fName.substring(4,6));//.concat("-").concat(fName.substring(6,8));
            }
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("statusVdate",statusVdate);
            paramMap.put("datZipType","MI");
            //数据权限模式（01：银行模式，02：联社模式）
            String authorityType = CacheUtil.getSysParam("AUTHORITY_TYPE");
            logger.info("数据权限模式:{}",authorityType);
            String orgCode = SecurityUtil.getLoginUser().getOrgCode();
            if("02".equals(authorityType)){
                //机构
                paramMap.put("reportOrgName",orgCode);
            }
            List<PbcSendZipDatFile> pbcSendZipDatFiles = pbcSendZipDatFileMapper.selectByZipTypeAndStatusVdate(paramMap);
            ReportDealParamDto paramDto = new ReportDealParamDto();
            paramDto.setOrgCode(orgCode);
            paramDto.setStatusVdate(statusVdate);
            Set<String> noUpdateReport = new HashSet<>();
            Map<String,PbcSendZipDatFile> map = new HashMap<>();
            if(CollectionUtil.isNotEmpty(pbcSendZipDatFiles)){
                for (PbcSendZipDatFile pbcSendZipDatFile : pbcSendZipDatFiles) {
                    if(pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_接口报送.getValue())
                     || pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_手工处理.getValue())){
                        noUpdateReport.add(getSheetName(pbcSendZipDatFile.getDatZipDatType()));
                    }
                    map.put(getSheetName(pbcSendZipDatFile.getDatZipDatType()),pbcSendZipDatFile);
                }

            }
            while(sheetIterator.hasNext()){
                HSSFSheet sheet = (HSSFSheet)sheetIterator.next();
                paramDto.setHssfSheet(sheet);
                String sheetName = sheet.getSheetName();
                if(noUpdateReport.contains(sheetName)){
                    continue;
                }
                if(map.containsKey(sheetName)){
                    PbcSendZipDatFile sendZipDatFile = map.get(sheetName);
                    paramDto.setReportUUID(sendZipDatFile.getReportuuid());
                    paramDto.setDatZipCode(sendZipDatFile.getDatZipCode());
                    paramDto.setHasRecord(true);
                }else{
                    paramDto.setReportUUID(UUID.randomUUID().toString());
                    paramDto.setDatZipCode(PbcDatUtil.genZipCode(DictCodeListExt2.人行报送类型_统计模板数据.getValue(),DateUtil.parse(statusVdate1, com.joyintech.framework.util.DateUtil.DEFAULT_DATE_FORMAT_EN),1,orgCode));
                    paramDto.setHasRecord(false);
                }
                //use cache,god knows me!
                if(allNewPbcMonthReportsMap.containsKey(sheetName)){
                    errorList.addAll(allNewPbcMonthReportsMap.get(sheetName).dealExcelData(paramDto));
                }else{
                    for (IDealNewPbcMonthReport iDealNewPbcMonthReport : iDealNewPbcMonthReports) {
                        if(iDealNewPbcMonthReport.sheetName().contains(sheetName)){
                            allNewPbcMonthReportsMap.put(sheetName,iDealNewPbcMonthReport);
                            errorList.addAll(iDealNewPbcMonthReport.dealExcelData(paramDto));
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("检查规范异常",e);
            ExcelValidDto dto = new ExcelValidDto();
            dto.setErrorInfo("检查规范异常");
            errorList.add(dto);
        }
        return errorList;
    }

    public List<ValidMessage> checkSheetName(Workbook workbook){
        List<ValidMessage> validMessages = new ArrayList<>();
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet next = sheetIterator.next();
            String sheetName = next.getSheetName();
            if(!sheetNames.contains(sheetName)){
                ValidMessage message = new ValidMessage();
                message.setMessage("sheet名称:[" + sheetName + "]不规范!");
                validMessages.add(message);
                ValidMessage message1 = new ValidMessage();
                message1.setMessage("14种规范sheet名称如下:");
                validMessages.add(message1);
                for (String name : sheetNames) {
                    ValidMessage message2 = new ValidMessage();
                    message2.setMessage("[" + name + "]");
                    validMessages.add(message2);
                }
                break;
            }
        }
        return validMessages;
    }

    public List<ValidMessage> checkFileName(String fileName){
        List<ValidMessage> validMessages = new ArrayList<>();
        try {
            String name = FileNameUtil.getName(fileName);
            String[] strName = name.split("_");
            if(strName.length == 0){
                ValidMessage dto = new ValidMessage();
                dto.setMessage(TIPS_FILE_NAME_ERROR);
                validMessages.add(dto);
                return validMessages;
            }
            String dateStr = strName[0];
            String year = dateStr.substring(0, 4);
            String month = dateStr.substring(4, 6);
            String day = dateStr.substring(6, 8);
            DateTime dateTime = DateUtil.parse(year.concat("-").concat(month).concat("-").concat(day), "yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);
            if("01".equals(month)){
                int day1 = calendar.get(Calendar.DAY_OF_MONTH);
                if(!(day1 == 1 || day1 == 31)){
                    ValidMessage dto = new ValidMessage();
                    dto.setMessage(TIPS_FILE_NAME_ERROR);
                    validMessages.add(dto);
                }
            }else{
                if(!(calendar.get(Calendar.DAY_OF_MONTH) == calendar
                        .getActualMaximum(Calendar.DAY_OF_MONTH))){
                    ValidMessage dto = new ValidMessage();
                    dto.setMessage(TIPS_FILE_NAME_ERROR);
                    validMessages.add(dto);
                }
            }
        }catch (Exception e){
            logger.error("检查文件名称格式异常",e);
            ValidMessage dto = new ValidMessage();
            dto.setMessage(TIPS_FILE_NAME_ERROR);
            validMessages.add(dto);
        }
        return validMessages;
    }

    public String getSheetName(String reportType){
        String replace = reportType.replace("_", "-");
        for (String sheetName : sheetNames) {
            if(sheetName.contains(replace)){
                return sheetName;
            }
        }
        return null;
    }


}

package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelFileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.ExcelOperationUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
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

    private static final String TIPS_SUFFIX_ERROR = "只支持2003版的excel导入，请修改文件重新导入";
    private static final String TIPS_SHEET_NAME_ERROR = "只支持规定的14种模板导入，请修改文件后重新导入";
    private static final String TIPS_FILE_NAME_ERROR = "文件名称不规范，请确认文件名称后，重新导入。";

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
            Workbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
            List<ValidMessage> validMessages = checkSheetName(workbook);
            if(CollectionUtil.isNotEmpty(validMessages)){
                ajaxResult.setErrorList(validMessages);
                return ajaxResult;
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
            String statusVdate = fName.substring(0,4).concat("-").concat(fName.substring(4,6));
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("statusVdate",statusVdate);
            paramMap.put("datZipType","MI");
            List<PbcSendZipDatFile> pbcSendZipDatFiles = pbcSendZipDatFileMapper.selectByZipTypeAndStatusVdate(paramMap);
            ReportDealParamDto paramDto = new ReportDealParamDto();
            paramDto.setOrgCode(SecurityUtil.getLoginUser().getOrgCode());
            paramDto.setStatusVdate(statusVdate);
            if(CollectionUtil.isNotEmpty(pbcSendZipDatFiles)){
                PbcSendZipDatFile pbcSendZipDatFile = pbcSendZipDatFiles.get(0);
                paramDto.setReportUUID(pbcSendZipDatFile.getReportuuid());
                paramDto.setDatZipCode(pbcSendZipDatFile.getDatZipCode());
                paramDto.setHasRecord(true);
            }else{
                paramDto.setReportUUID(UUID.randomUUID().toString());
                paramDto.setDatZipCode("001");
                paramDto.setHasRecord(false);
            }
            while(sheetIterator.hasNext()){
                HSSFSheet sheet = (HSSFSheet)sheetIterator.next();
                paramDto.setHssfSheet(sheet);
                String sheetName = sheet.getSheetName();
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
                message.setMessage(TIPS_SHEET_NAME_ERROR);
                validMessages.add(message);
                break;
            }
        }
        return validMessages;
    }
}

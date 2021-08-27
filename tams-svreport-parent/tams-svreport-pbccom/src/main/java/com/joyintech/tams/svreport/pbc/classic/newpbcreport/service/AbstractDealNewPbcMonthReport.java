package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import com.joyintech.tams.svreport.pbc.common.enums.PbcDictCodeEnums;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Date;
import java.util.Map;

public abstract class AbstractDealNewPbcMonthReport implements IDealNewPbcMonthReport {

    /**
     * 人行导出模版sheet页名称
     */
    public final static String SHEET_1_1 = "表1-1资管产品募集余额";

    public final static String SHEET_1_2 = "表1-2募集及兑付发生额";

    public final static String SHEET_1_3 = "表1-3只数情况";

    public final static String SHEET_1_4 = "表1-4境内募集余额分地区";

    public final static String SHEET_1_5 = "表1-5提前及延期兑付";

    public final static String SHEET_1_6 = "表1-6资管产品到期未兑付";

    public final static String SHEET_2_1 = "表2-1资产负债";

    public final static String SHEET_2_2 = "表2-2除回购和拆借外贷款按行业、企业";

    public final static String SHEET_2_3 = "表2-3除回购和拆借外贷款分地区";

    public final static String SHEET_2_4 = "表2-4资产收益权按基础资产投向";

    public final static String SHEET_2_5 = "表2-5企业债券按行业、企业";

    public final static String SHEET_3_1 = "表3-1存续产品分期限募集余额";

    public final static String SHEET_3_2 = "表3-2新发产品分期限募集金额";

    public final static String SHEET_3_3 = "表3-3资产负债剩余期限";
    /**
     * 发行机构代码
     */
    public final static String ISSUE_PARTYID = "issuePartyId";

    /**
     * 发行机构名称
     */
    public final static String ISSUE_PARTY_NAME = "issuePartyName";

    /**
     * 填报机构名称
     */
    public final static String REPORT_ORG_NAME = "reportOrgName";

    /**
     * 社会信用代码
     */
    public final static String SOCIAL_CREDIT_CODE = "socialCreditCode";

    /**
     * 金融机构编码
     */
    public final static String FININSTITUTION_CODE = "finInstitutionCode";

    /**
     * 产品品种
     */
    public final static String PROD_VARIETY = "prodVariety";

    /**
     * 责任人
     */
    public final static String PERSON_LIABLE = "personLiable";

    /**
     * 联系方式
     */
    public final static String CONTACT = "contact";

    /**
     * 制表日期
     */
    public final static String REPORT_DATE = "reportDate";
    /**
     * 制表日期
     */
    public final static String DATA_DATE = "dataDate";

    public PbcSendZipDatFile newPbcSendZipDatFile(ReportDealParamDto paramDto, int size) throws Exception{
        PbcSendZipDatFile sendZipDatFile = new PbcSendZipDatFile();
        Map<String, String> baseMap = excelBaseInfo();
        //发行机构代码
        String issuePartyId = getCellValue(paramDto.getHssfSheet(), baseMap.get(ISSUE_PARTYID),false);
        sendZipDatFile.setIssuePartyId(issuePartyId);
        //发行机构名称
        String issuePartyName = getCellValue(paramDto.getHssfSheet(), baseMap.get(ISSUE_PARTY_NAME),false);
        sendZipDatFile.setIssuePartyName(issuePartyName);
        //填报机构名称
        String reportOrgName = getCellValue(paramDto.getHssfSheet(), baseMap.get(REPORT_ORG_NAME),false);
        sendZipDatFile.setReportOrgName(reportOrgName);
        //社会信用代码
        String socialCreditCode = getCellValue(paramDto.getHssfSheet(), baseMap.get(SOCIAL_CREDIT_CODE),false);
        sendZipDatFile.setSocialCreditCode(socialCreditCode);
        //金融机构编码
        String finInstitutionCode = getCellValue(paramDto.getHssfSheet(), baseMap.get(FININSTITUTION_CODE),false);
        sendZipDatFile.setFinInstitutionCode(finInstitutionCode);
        //产品品种
        String prodVariety = getCellValue(paramDto.getHssfSheet(), baseMap.get(PROD_VARIETY),false);
        sendZipDatFile.setProdVariety(PbcDictCodeEnums.getCodeByName(prodVariety));
        //责任人
        String personLiable = getCellValue(paramDto.getHssfSheet(), baseMap.get(PERSON_LIABLE),false);
        sendZipDatFile.setPersonLiable(personLiable);
        //联系方式
        String contact = getCellValue(paramDto.getHssfSheet(), baseMap.get(CONTACT),false);
        sendZipDatFile.setContact(contact);
        //制表日期
        try {
            /*String reportDate = getCellValue(paramDto.getHssfSheet(), baseMap.get(REPORT_DATE),true);
            if(StrUtil.isNotEmpty(reportDate)){
                DateTime dateTime = DateUtil.parse(reportDate, "yyyy-MM-dd");
                sendZipDatFile.setReportDate(dateTime);
            }*/
            //制表日期取系统时间,以前是取导入表格中的制表日期
            sendZipDatFile.setReportDate(new Date());
        }catch (Exception e){
            throw new Exception(sheetName()+"制表日期格式不正确!正确格式为:yyyy-MM-dd");
        }
        //数据日期
        try {
            /*
            String dataDate = getCellValue(paramDto.getHssfSheet(), baseMap.get(DATA_DATE),true);
            if(StrUtil.isNotEmpty(dataDate)){
                DateTime dateTime = DateUtil.parse(dataDate, "yyyy-MM-dd");
                sendZipDatFile.setEnddate(dateTime);
            }*/
            //以前是取导入表格中的制表日期
            String statusVdate = paramDto.getStatusVdate();
            DateTime dateTime = DateUtil.parse(statusVdate, "yyyyMM");
            DateTime endOfMonth = DateUtil.endOfMonth(dateTime);
            sendZipDatFile.setEnddate(endOfMonth);
        }catch (Exception e){
            throw new Exception(sheetName()+"数据日期格式不正确!正确格式为:yyyy-MM-dd");
        }
        sendZipDatFile.setRegistSeqno(UUID.randomUUID().toString());
        sendZipDatFile.setDatZipCode(paramDto.getDatZipCode());
        sendZipDatFile.setDatZipPath(paramDto.getDatZipPath());
        sendZipDatFile.setDatZipType("MI");
        sendZipDatFile.setDatZipStatus("01");
        sendZipDatFile.setStatusVdate(paramDto.getStatusVdate());
        sendZipDatFile.setReportuuid(paramDto.getReportUUID());
        sendZipDatFile.setPibDetailCnt(1);
        sendZipDatFile.setZipSendStatus("01");
        sendZipDatFile.setOrgCode(paramDto.getOrgCode());
        return sendZipDatFile;
    }

    private String getCellValue(HSSFSheet hssfSheet,String index,boolean isDate){
        if(StrUtil.isNotEmpty(index)){
            String[] split = index.split(",");
            int rowNumber = Integer.parseInt(split[0]);
            int cellNumber = Integer.parseInt(split[1]);
            HSSFRow hssfRow = hssfSheet.getRow(rowNumber);
            HSSFCell cell = hssfRow.getCell(cellNumber);
            if(isDate){
                CellType cellType = cell.getCellType();
                if(cellType.equals(CellType.STRING)){
                    return cell.getStringCellValue();
                }else{
                    return DateUtil.format(cell.getDateCellValue(),"yyyy-MM-dd");
                }
            }
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
        return null;
    }
}

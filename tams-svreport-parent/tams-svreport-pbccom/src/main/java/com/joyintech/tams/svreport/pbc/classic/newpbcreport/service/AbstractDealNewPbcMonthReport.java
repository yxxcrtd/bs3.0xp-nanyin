package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;

import java.util.Date;

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

    public PbcSendZipDatFile newPbcSendZipDatFile(ReportDealParamDto paramDto, int size){
        PbcSendZipDatFile sendZipDatFile = new PbcSendZipDatFile();
        sendZipDatFile.setRegistSeqno(UUID.randomUUID().toString());
        sendZipDatFile.setDatZipCode(paramDto.getDatZipCode());
        sendZipDatFile.setDatZipPath(null);
        sendZipDatFile.setDatZipType("MI");
        sendZipDatFile.setDatZipStatus("01");
        sendZipDatFile.setStatusVdate(paramDto.getStatusVdate());
        sendZipDatFile.setReportuuid(paramDto.getReportUUID());
        sendZipDatFile.setPibDetailCnt(size);
        sendZipDatFile.setSubmitTime(new Date());
        sendZipDatFile.setZipSendStatus("01");
        sendZipDatFile.setOrgCode(paramDto.getOrgCode());
        return sendZipDatFile;
    }

}

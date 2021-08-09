package com.joyintech.tams.svreport.pbc.common.enums;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 人行报表枚举类
 * @author Jason.Jin
 */
public enum PbcReportGroupEnums {
    /**
     * 1-1资管产品募集余额统计模板
     */
    PBC_REPORT_1_1("1_1",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1, PbcReportProdDatDto.class),

    /**
     * 1-2募集及兑付发生额
     */
    /*PBC_REPORT_1_2("1_2",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 1-3只数情况
     *//*
    PBC_REPORT_1_3("1_3",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 1-4境内募集余额分地区
     *//*
    PBC_REPORT_1_4("1_4",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 1-5提前及延期兑付
     *//*
    PBC_REPORT_1_5("1_5",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 1-6到期未兑付报表
     *//*
    PBC_REPORT_1_6("1_6",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 2-1资管产品资产负债统计
     *//*
    PBC_REPORT_2_1("2_1",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 2-2除回购和拆借外贷款分行业、企业规模统计
     *//*
    PBC_REPORT_2_2("2_2",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 2-3除回购和拆借外贷款分行业、企业规模统计
     *//*
    PBC_REPORT_2_3("2_3",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 2-4资产收益权按基础资产投向分类统计
     *//*
    PBC_REPORT_2_4("2_4",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 2-5企业债券分行业、企业规模统计
     *//*
    PBC_REPORT_2_5("2_5",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 3-1存续产品分期限募集余额
     *//*
    PBC_REPORT_3_1("3_1",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),

    *//**
     * 3-2新发产品分期限募集金额
     *//*
    PBC_REPORT_3_2("3_2", PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),
    *//**
     * 3-3资管产品资产负债剩余期限
     *//*
    PBC_REPORT_3_3("3_3",PbcMonthReportGroupConstants.PBC_REPORT_1_1_1,PbcReportProdDatDto.class),*/
    ;
    private String reportType;

    private String reporyGroup;

    private Class<?> entityClazz;

    PbcReportGroupEnums(String reportType, String reporyGroup, Class<?> entityClazz) {
        this.reportType = reportType;
        this.reporyGroup = reporyGroup;
        this.entityClazz = entityClazz;
    }

    public String getReportType() {
        return reportType;
    }

    public String getReporyGroup() {
        return reporyGroup;
    }

    public Class<?> getEntityClazz() {
        return entityClazz;
    }

    public static List<PbcReportGroupEnums> getReportGroupInfo(String reportType){
        List<PbcReportGroupEnums> groupEnums = new ArrayList<>();
        for (PbcReportGroupEnums enums : PbcReportGroupEnums.values()) {
            if(enums.reportType.equals(reportType)){
                groupEnums.add(enums);
            }
        }
        return groupEnums;
    }
}

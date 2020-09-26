/*
 * 文件名：ValExcelImpFileSheetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * excel导入文件单个sheet信息dto
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelImpFileSheetInfoDto
 * @since 1.0
 */
public class ValExcelImpFileSheetInfoDto implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 导入文件sheet序号
     */
    private Integer impFileSheetNo;

    /**
     * 导入文件sheet名
     */
    private String impFileSheetName;

    /**
     * 导入文件sheet数据列表
     */
    private List<List<String>> sheetDataList;

    /**
     * excel导入规则
     */
    private ValExcelRuleDto valExcelRuleDto;

    /**
     * 获取 impFileSheetNo
     *
     * @return impFileSheetNo.
     */
    public Integer getImpFileSheetNo() {
        return impFileSheetNo;
    }

    /**
     * 设置 impFileSheetNo
     *
     * @param impFileSheetNo impFileSheetNo
     */
    public void setImpFileSheetNo(Integer impFileSheetNo) {
        this.impFileSheetNo = impFileSheetNo;
    }

    /**
     * 获取 impFileSheetName
     *
     * @return impFileSheetName.
     */
    public String getImpFileSheetName() {
        return impFileSheetName;
    }

    /**
     * 设置 impFileSheetName
     *
     * @param impFileSheetName impFileSheetName
     */
    public void setImpFileSheetName(String impFileSheetName) {
        this.impFileSheetName = impFileSheetName;
    }

    /**
     * 获取 sheetDataList
     *
     * @return sheetDataList.
     */
    public List<List<String>> getSheetDataList() {
        return sheetDataList;
    }

    /**
     * 设置 sheetDataList
     *
     * @param sheetDataList sheetDataList
     */
    public void setSheetDataList(List<List<String>> sheetDataList) {
        this.sheetDataList = sheetDataList;
    }

    /**
     * 获取 valExcelRuleDto
     * @return valExcelRuleDto.
     */
    public ValExcelRuleDto getValExcelRuleDto() {
        return valExcelRuleDto;
    }

    /**
     * 设置 valExcelRuleDto
     * @param valExcelRuleDto valExcelRuleDto
     */
    public void setValExcelRuleDto(ValExcelRuleDto valExcelRuleDto) {
        this.valExcelRuleDto = valExcelRuleDto;
    }
    
    
}

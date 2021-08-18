package com.joyintech.tams.svreport.pbc.common.dto;

import com.joyintech.tams.report.common.dto.ExcelValidDto;

import java.util.List;

public class ExcelEntity<T> {

    private List<T> dataList;

    private List<ExcelValidDto> excelValidDtos;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<ExcelValidDto> getExcelValidDtos() {
        return excelValidDtos;
    }

    public void setExcelValidDtos(List<ExcelValidDto> excelValidDtos) {
        this.excelValidDtos = excelValidDtos;
    }
}

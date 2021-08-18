package com.joyintech.tams.model.valuation.batch.calculation.entity;

import com.joyintech.tams.model.valuation.batch.calculation.constant.CurveType;

import java.time.LocalDate;
import java.util.List;

/**
 * 曲线信息
 * @author zznn
 * @date 2021/7/1
 */
public class CurveInfoDto {

    /**
     * 曲线代码
     */
    private String curveCode;

    /**
     * 曲线日期
     */
    private LocalDate calDate;

    /**
     * 曲线类型
     */
    protected CurveType curveType;

    /**
     * 关键年限点信息
     */
    private List<KeyPointDto> keyPointDtoList;

    public String getCurveCode() {
        return curveCode;
    }

    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    public LocalDate getCalDate() {
        return calDate;
    }

    public void setCalDate(LocalDate calDate) {
        this.calDate = calDate;
    }

    public CurveType getCurveType() {
        return curveType;
    }

    public void setCurveType(CurveType curveType) {
        this.curveType = curveType;
    }

    public List<KeyPointDto> getKeyPointDtoList() {
        return keyPointDtoList;
    }

    public void setKeyPointDtoList(List<KeyPointDto> keyPointDtoList) {
        this.keyPointDtoList = keyPointDtoList;
    }
}


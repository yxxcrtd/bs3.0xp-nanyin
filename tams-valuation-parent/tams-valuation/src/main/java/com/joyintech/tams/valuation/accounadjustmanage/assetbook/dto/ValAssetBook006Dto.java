package com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class ValAssetBook006Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String booksetId;

    private String elementId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    private String paramName;

    private String paramCode;

    private String paramValue;

    private String remark;

    private String objParamValue;

    private String booksetTypeCode;

    public String getBooksetTypeCode() {
        return booksetTypeCode;
    }

    public void setBooksetTypeCode(String booksetTypeCode) {
        this.booksetTypeCode = booksetTypeCode;
    }

    public String getObjParamValue() {
        return objParamValue;
    }

    public void setObjParamValue(String objParamValue) {
        this.objParamValue = objParamValue;
    }

    private List<ValAssetBook006Dto> valAssetBook006DtoList;

    public List<ValAssetBook006Dto> getValAssetBook006DtoList() {
        return valAssetBook006DtoList;
    }

    public void setValAssetBook006DtoList(List<ValAssetBook006Dto> valAssetBook006DtoList) {
        this.valAssetBook006DtoList = valAssetBook006DtoList;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

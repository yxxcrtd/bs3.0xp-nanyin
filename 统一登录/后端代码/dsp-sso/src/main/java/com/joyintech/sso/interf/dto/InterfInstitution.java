package com.joyintech.sso.interf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 机构信息
 * @auther zhangnian
 * @date 2021/5/12
 */
@ApiModel(value="机构信息",description="机构信息")
public class InterfInstitution {

//    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required=false,name="institutionCode",value="机构代码",example="")
    private String institutionCode;

    @ApiModelProperty(required=false,name="systemCode",value="系统代码",example="")
    private String systemCode;

    @ApiModelProperty(required=false,name="institutionName",value="机构名称",example="")
    private String institutionName;

    @ApiModelProperty(required=false,name="institutionAbbr",value="机构地址",example="")
    private String institutionAbbr;

    @ApiModelProperty(required=false,name="institutionType",value="机构类型",example="")
    private String institutionType;

    @ApiModelProperty(required=false,name="upperInstitutionCode",value="上级机构代码",example="")
    private String upperInstitutionCode;

    @ApiModelProperty(required=false,name="insitituteCode",value="组织机构代码",example="")
    private String insitituteCode;

    @ApiModelProperty(required=false,name="assPeople",value="联系人",example="")
    private String assPeople;

    @ApiModelProperty(required=false,name="assPhone",value="联系电话",example="")
    private String assPhone;

    @ApiModelProperty(required=false,name="grdInfo",value="层级信息",example="")
    private String grdInfo;

    @ApiModelProperty(required=false,name="province",value="省",example="")
    private String province;

    @ApiModelProperty(required=false,name="city",value="市",example="")
    private String city;

    @ApiModelProperty(required=false,name="counties",value="县区",example="")
    private String counties;

    @ApiModelProperty(required=false,name="other",value="其他",example="")
    private String other;

    @ApiModelProperty(required=false,name="remark",value="备注",example="")
    private String remark;

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionAbbr() {
        return institutionAbbr;
    }

    public void setInstitutionAbbr(String institutionAbbr) {
        this.institutionAbbr = institutionAbbr;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getUpperInstitutionCode() {
        return upperInstitutionCode;
    }

    public void setUpperInstitutionCode(String upperInstitutionCode) {
        this.upperInstitutionCode = upperInstitutionCode;
    }

    public String getInsitituteCode() {
        return insitituteCode;
    }

    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode;
    }

    public String getAssPeople() {
        return assPeople;
    }

    public void setAssPeople(String assPeople) {
        this.assPeople = assPeople;
    }

    public String getAssPhone() {
        return assPhone;
    }

    public void setAssPhone(String assPhone) {
        this.assPhone = assPhone;
    }

    public String getGrdInfo() {
        return grdInfo;
    }

    public void setGrdInfo(String grdInfo) {
        this.grdInfo = grdInfo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounties() {
        return counties;
    }

    public void setCounties(String counties) {
        this.counties = counties;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}


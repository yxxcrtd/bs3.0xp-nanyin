package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCompintroduction extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 公司ID
     */
    private String compId;

    /**
     * 公司名称
     */
    private String compName;

    /**
     * 公司中文简称
     */
    private String compSname;

    /**
     * 英文名称
     */
    private String compNameEng;

    /**
     * 英文名称缩写
     */
    private String compSnameeng;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 办公地址
     */
    private String office;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 公司网址
     */
    private String website;

    /**
     * 工商登记号
     */
    private String registernumber;

    /**
     * 法人代表
     */
    private String chairman;

    /**
     * 总经理
     */
    private String president;

    /**
     * 信息披露人
     */
    private String discloser;

    /**
     * 注册资本（万元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regcapital;

    /**
     * 货币代码
     */
    private String currencycode;

    /**
     * 成立日期
     */
    private String founddate;

    /**
     * 公司终止日期
     */
    private String enddate;

    /**
     * 公司简介
     */
    private String briefing;

    /**
     * 公司类型
     */
    private String compType;

    /**
     * 企业性质
     */
    private String compProperty;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 国籍
     */
    private String country;

    /**
     * 经营范围
     */
    private String businessscope;

    /**
     * 公司类别
     */
    private String companyType;

    /**
     * 员工总数（人）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sInfoTotalemployees;

    /**
     * 主要产品及业务
     */
    private String mainBusiness;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 公司ID
     * @return 公司ID
     */
    public String getCompId() {
        return compId;
    }

    /**
     * 设置 公司ID
     * @param compId 公司ID
     */
    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    /**
     * 取得 公司名称
     * @return 公司名称
     */
    public String getCompName() {
        return compName;
    }

    /**
     * 设置 公司名称
     * @param compName 公司名称
     */
    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    /**
     * 取得 公司中文简称
     * @return 公司中文简称
     */
    public String getCompSname() {
        return compSname;
    }

    /**
     * 设置 公司中文简称
     * @param compSname 公司中文简称
     */
    public void setCompSname(String compSname) {
        this.compSname = compSname == null ? null : compSname.trim();
    }

    /**
     * 取得 英文名称
     * @return 英文名称
     */
    public String getCompNameEng() {
        return compNameEng;
    }

    /**
     * 设置 英文名称
     * @param compNameEng 英文名称
     */
    public void setCompNameEng(String compNameEng) {
        this.compNameEng = compNameEng == null ? null : compNameEng.trim();
    }

    /**
     * 取得 英文名称缩写
     * @return 英文名称缩写
     */
    public String getCompSnameeng() {
        return compSnameeng;
    }

    /**
     * 设置 英文名称缩写
     * @param compSnameeng 英文名称缩写
     */
    public void setCompSnameeng(String compSnameeng) {
        this.compSnameeng = compSnameeng == null ? null : compSnameeng.trim();
    }

    /**
     * 取得 省份
     * @return 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置 省份
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 取得 城市
     * @return 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 取得 注册地址
     * @return 注册地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 注册地址
     * @param address 注册地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 取得 办公地址
     * @return 办公地址
     */
    public String getOffice() {
        return office;
    }

    /**
     * 设置 办公地址
     * @param office 办公地址
     */
    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
    }

    /**
     * 取得 邮编
     * @return 邮编
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 设置 邮编
     * @param zipcode 邮编
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     * 取得 电话
     * @return 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 取得 传真
     * @return 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置 传真
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 取得 电子邮件
     * @return 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 电子邮件
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 取得 公司网址
     * @return 公司网址
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置 公司网址
     * @param website 公司网址
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * 取得 工商登记号
     * @return 工商登记号
     */
    public String getRegisternumber() {
        return registernumber;
    }

    /**
     * 设置 工商登记号
     * @param registernumber 工商登记号
     */
    public void setRegisternumber(String registernumber) {
        this.registernumber = registernumber == null ? null : registernumber.trim();
    }

    /**
     * 取得 法人代表
     * @return 法人代表
     */
    public String getChairman() {
        return chairman;
    }

    /**
     * 设置 法人代表
     * @param chairman 法人代表
     */
    public void setChairman(String chairman) {
        this.chairman = chairman == null ? null : chairman.trim();
    }

    /**
     * 取得 总经理
     * @return 总经理
     */
    public String getPresident() {
        return president;
    }

    /**
     * 设置 总经理
     * @param president 总经理
     */
    public void setPresident(String president) {
        this.president = president == null ? null : president.trim();
    }

    /**
     * 取得 信息披露人
     * @return 信息披露人
     */
    public String getDiscloser() {
        return discloser;
    }

    /**
     * 设置 信息披露人
     * @param discloser 信息披露人
     */
    public void setDiscloser(String discloser) {
        this.discloser = discloser == null ? null : discloser.trim();
    }

    /**
     * 取得 注册资本（万元）
     * @return 注册资本（万元）
     */
    public BigDecimal getRegcapital() {
        return regcapital;
    }

    /**
     * 设置 注册资本（万元）
     * @param regcapital 注册资本（万元）
     */
    public void setRegcapital(BigDecimal regcapital) {
        this.regcapital = regcapital;
    }

    /**
     * 取得 货币代码
     * @return 货币代码
     */
    public String getCurrencycode() {
        return currencycode;
    }

    /**
     * 设置 货币代码
     * @param currencycode 货币代码
     */
    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode == null ? null : currencycode.trim();
    }

    /**
     * 取得 成立日期
     * @return 成立日期
     */
    public String getFounddate() {
        return founddate;
    }

    /**
     * 设置 成立日期
     * @param founddate 成立日期
     */
    public void setFounddate(String founddate) {
        this.founddate = founddate == null ? null : founddate.trim();
    }

    /**
     * 取得 公司终止日期
     * @return 公司终止日期
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 公司终止日期
     * @param enddate 公司终止日期
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    /**
     * 取得 公司简介
     * @return 公司简介
     */
    public String getBriefing() {
        return briefing;
    }

    /**
     * 设置 公司简介
     * @param briefing 公司简介
     */
    public void setBriefing(String briefing) {
        this.briefing = briefing == null ? null : briefing.trim();
    }

    /**
     * 取得 公司类型
     * @return 公司类型
     */
    public String getCompType() {
        return compType;
    }

    /**
     * 设置 公司类型
     * @param compType 公司类型
     */
    public void setCompType(String compType) {
        this.compType = compType == null ? null : compType.trim();
    }

    /**
     * 取得 企业性质
     * @return 企业性质
     */
    public String getCompProperty() {
        return compProperty;
    }

    /**
     * 设置 企业性质
     * @param compProperty 企业性质
     */
    public void setCompProperty(String compProperty) {
        this.compProperty = compProperty == null ? null : compProperty.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 null
     * @param opdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 null
     * @param opmode null
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }

    /**
     * 取得 国籍
     * @return 国籍
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置 国籍
     * @param country 国籍
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 取得 经营范围
     * @return 经营范围
     */
    public String getBusinessscope() {
        return businessscope;
    }

    /**
     * 设置 经营范围
     * @param businessscope 经营范围
     */
    public void setBusinessscope(String businessscope) {
        this.businessscope = businessscope == null ? null : businessscope.trim();
    }

    /**
     * 取得 公司类别
     * @return 公司类别
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置 公司类别
     * @param companyType 公司类别
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    /**
     * 取得 员工总数（人）
     * @return 员工总数（人）
     */
    public BigDecimal getsInfoTotalemployees() {
        return sInfoTotalemployees;
    }

    /**
     * 设置 员工总数（人）
     * @param sInfoTotalemployees 员工总数（人）
     */
    public void setsInfoTotalemployees(BigDecimal sInfoTotalemployees) {
        this.sInfoTotalemployees = sInfoTotalemployees;
    }

    /**
     * 取得 主要产品及业务
     * @return 主要产品及业务
     */
    public String getMainBusiness() {
        return mainBusiness;
    }

    /**
     * 设置 主要产品及业务
     * @param mainBusiness 主要产品及业务
     */
    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness == null ? null : mainBusiness.trim();
    }
}
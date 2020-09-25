package com.joyintech.tams.common.base.entity;

public class ChbSysInstitution extends ChbSysInstitutionKey {
    /**
     * 组织机构代码
     */
    private String insitituteCode;

    /**
     * 机构名称
     */
    private String institutionName;

    /**
     * 机构类型
     */
    private String institutionType;

    /**
     * 上级机构代码
     */
    private String directInstitutionCode;

    /**
     * 联系人
     */
    private String assPeople;

    /**
     * 联系电话
     */
    private String assPhone;

    /**
     * 层级信息 参照数据字典
     */
    private String grdInfo;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 省份 参照数据字典
     */
    private String province;

    /**
     * 城市 参照数据字典
     */
    private String city;

    /**
     * 县区 参照数据字典
     */
    private String counties;

    /**
     * 其他
     */
    private String other;

    /**
     * 省份 参照数据字典
     */
    private String provinceRh;

    /**
     * 城市 参照数据字典
     */
    private String cityRh;

    /**
     * 县区 参照数据字典
     */
    private String countiesRh;

    /**
     * 其他
     */
    private String otherRh;

    /**
     * 取得 组织机构代码
     * @return 组织机构代码
     */
    public String getInsitituteCode() {
        return insitituteCode;
    }

    /**
     * 设置 组织机构代码
     * @param insitituteCode 组织机构代码
     */
    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode == null ? null : insitituteCode.trim();
    }

    /**
     * 取得 机构名称
     * @return 机构名称
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * 设置 机构名称
     * @param institutionName 机构名称
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getInstitutionType() {
        return institutionType;
    }

    /**
     * 设置 机构类型
     * @param institutionType 机构类型
     */
    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType == null ? null : institutionType.trim();
    }

    /**
     * 取得 上级机构代码
     * @return 上级机构代码
     */
    public String getDirectInstitutionCode() {
        return directInstitutionCode;
    }

    /**
     * 设置 上级机构代码
     * @param directInstitutionCode 上级机构代码
     */
    public void setDirectInstitutionCode(String directInstitutionCode) {
        this.directInstitutionCode = directInstitutionCode == null ? null : directInstitutionCode.trim();
    }

    /**
     * 取得 联系人
     * @return 联系人
     */
    public String getAssPeople() {
        return assPeople;
    }

    /**
     * 设置 联系人
     * @param assPeople 联系人
     */
    public void setAssPeople(String assPeople) {
        this.assPeople = assPeople == null ? null : assPeople.trim();
    }

    /**
     * 取得 联系电话
     * @return 联系电话
     */
    public String getAssPhone() {
        return assPhone;
    }

    /**
     * 设置 联系电话
     * @param assPhone 联系电话
     */
    public void setAssPhone(String assPhone) {
        this.assPhone = assPhone == null ? null : assPhone.trim();
    }

    /**
     * 取得 层级信息 参照数据字典
     * @return 层级信息 参照数据字典
     */
    public String getGrdInfo() {
        return grdInfo;
    }

    /**
     * 设置 层级信息 参照数据字典
     * @param grdInfo 层级信息 参照数据字典
     */
    public void setGrdInfo(String grdInfo) {
        this.grdInfo = grdInfo == null ? null : grdInfo.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 省份 参照数据字典
     * @return 省份 参照数据字典
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置 省份 参照数据字典
     * @param province 省份 参照数据字典
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 取得 城市 参照数据字典
     * @return 城市 参照数据字典
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 城市 参照数据字典
     * @param city 城市 参照数据字典
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 取得 县区 参照数据字典
     * @return 县区 参照数据字典
     */
    public String getCounties() {
        return counties;
    }

    /**
     * 设置 县区 参照数据字典
     * @param counties 县区 参照数据字典
     */
    public void setCounties(String counties) {
        this.counties = counties == null ? null : counties.trim();
    }

    /**
     * 取得 其他
     * @return 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置 其他
     * @param other 其他
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 取得 省份 参照数据字典
     * @return 省份 参照数据字典
     */
    public String getProvinceRh() {
        return provinceRh;
    }

    /**
     * 设置 省份 参照数据字典
     * @param provinceRh 省份 参照数据字典
     */
    public void setProvinceRh(String provinceRh) {
        this.provinceRh = provinceRh == null ? null : provinceRh.trim();
    }

    /**
     * 取得 城市 参照数据字典
     * @return 城市 参照数据字典
     */
    public String getCityRh() {
        return cityRh;
    }

    /**
     * 设置 城市 参照数据字典
     * @param cityRh 城市 参照数据字典
     */
    public void setCityRh(String cityRh) {
        this.cityRh = cityRh == null ? null : cityRh.trim();
    }

    /**
     * 取得 县区 参照数据字典
     * @return 县区 参照数据字典
     */
    public String getCountiesRh() {
        return countiesRh;
    }

    /**
     * 设置 县区 参照数据字典
     * @param countiesRh 县区 参照数据字典
     */
    public void setCountiesRh(String countiesRh) {
        this.countiesRh = countiesRh == null ? null : countiesRh.trim();
    }

    /**
     * 取得 其他
     * @return 其他
     */
    public String getOtherRh() {
        return otherRh;
    }

    /**
     * 设置 其他
     * @param otherRh 其他
     */
    public void setOtherRh(String otherRh) {
        this.otherRh = otherRh == null ? null : otherRh.trim();
    }
}
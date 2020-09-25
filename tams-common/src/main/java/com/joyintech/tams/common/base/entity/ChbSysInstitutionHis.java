package com.joyintech.tams.common.base.entity;

public class ChbSysInstitutionHis extends ChbSysInstitutionHisKey {
    /**
     * ������
     */
    private String insitituteCode;

    /**
     * ����
     */
    private String institutionName;

    /**
     * ����
     */
    private String institutionType;

    /**
     * ������
     */
    private String directInstitutionCode;

    /**
     * ���
     */
    private String assPeople;

    /**
     * ����
     */
    private String assPhone;

    /**
     * ���� ������
     */
    private String grdInfo;

    /**
     * �� ������
     */
    private String province;

    /**
     * �� ������
     */
    private String city;

    /**
     * �� ������
     */
    private String counties;

    /**
     * ��
     */
    private String other;

    /**
     * null
     */
    private String provinceRh;

    /**
     * null
     */
    private String cityRh;

    /**
     * null
     */
    private String countiesRh;

    /**
     * null
     */
    private String otherRh;

    /**
     * �����
     */
    private String workflowId;

    /**
     * ��
     */
    private String remark;

    /**
     * 取得 ������
     * @return ������
     */
    public String getInsitituteCode() {
        return insitituteCode;
    }

    /**
     * 设置 ������
     * @param insitituteCode ������
     */
    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode == null ? null : insitituteCode.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * 设置 ����
     * @param institutionName ����
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getInstitutionType() {
        return institutionType;
    }

    /**
     * 设置 ����
     * @param institutionType ����
     */
    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType == null ? null : institutionType.trim();
    }

    /**
     * 取得 ������
     * @return ������
     */
    public String getDirectInstitutionCode() {
        return directInstitutionCode;
    }

    /**
     * 设置 ������
     * @param directInstitutionCode ������
     */
    public void setDirectInstitutionCode(String directInstitutionCode) {
        this.directInstitutionCode = directInstitutionCode == null ? null : directInstitutionCode.trim();
    }

    /**
     * 取得 ���
     * @return ���
     */
    public String getAssPeople() {
        return assPeople;
    }

    /**
     * 设置 ���
     * @param assPeople ���
     */
    public void setAssPeople(String assPeople) {
        this.assPeople = assPeople == null ? null : assPeople.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getAssPhone() {
        return assPhone;
    }

    /**
     * 设置 ����
     * @param assPhone ����
     */
    public void setAssPhone(String assPhone) {
        this.assPhone = assPhone == null ? null : assPhone.trim();
    }

    /**
     * 取得 ���� ������
     * @return ���� ������
     */
    public String getGrdInfo() {
        return grdInfo;
    }

    /**
     * 设置 ���� ������
     * @param grdInfo ���� ������
     */
    public void setGrdInfo(String grdInfo) {
        this.grdInfo = grdInfo == null ? null : grdInfo.trim();
    }

    /**
     * 取得 �� ������
     * @return �� ������
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置 �� ������
     * @param province �� ������
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 取得 �� ������
     * @return �� ������
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 �� ������
     * @param city �� ������
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 取得 �� ������
     * @return �� ������
     */
    public String getCounties() {
        return counties;
    }

    /**
     * 设置 �� ������
     * @param counties �� ������
     */
    public void setCounties(String counties) {
        this.counties = counties == null ? null : counties.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置 ��
     * @param other ��
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProvinceRh() {
        return provinceRh;
    }

    /**
     * 设置 null
     * @param provinceRh null
     */
    public void setProvinceRh(String provinceRh) {
        this.provinceRh = provinceRh == null ? null : provinceRh.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCityRh() {
        return cityRh;
    }

    /**
     * 设置 null
     * @param cityRh null
     */
    public void setCityRh(String cityRh) {
        this.cityRh = cityRh == null ? null : cityRh.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCountiesRh() {
        return countiesRh;
    }

    /**
     * 设置 null
     * @param countiesRh null
     */
    public void setCountiesRh(String countiesRh) {
        this.countiesRh = countiesRh == null ? null : countiesRh.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOtherRh() {
        return otherRh;
    }

    /**
     * 设置 null
     * @param otherRh null
     */
    public void setOtherRh(String otherRh) {
        this.otherRh = otherRh == null ? null : otherRh.trim();
    }

    /**
     * 取得 �����
     * @return �����
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 �����
     * @param workflowId �����
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 ��
     * @param remark ��
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
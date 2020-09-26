package com.joyintech.tams.report.system.systempbccapassetinfo.dto;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.report.common.base.TableRow;

import java.util.Date;

/**
 * 人行资金信托代码管理Dto
 *
 * @author kx
 */
public class PbcCapAssetInfo001Dto extends TableRow {

    /**
     * 主键，自动生成
     */
    private String assetUuid;

    /**
     * 资产代码
     */
    private String assetid;

    /**
     * 资产名称
     */
    private String assetname;

    /**
     * 人行资金信托代码
     */
    private String pobcassetid;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser = LoginUserUtil.getUserName();

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 前端展示资产代码
     */
    private String assetCode;

    /**
     * 前端展示资产名称
     */
    private String assetName;

    /**
     * 资管计划成立日期
     */
    private Date planSdate;

    /**
     * 资管计划终止日期
     */
    private Date planEdate;

    /**
     * excel 列
     */
    private String excelNum;

    /**
     * 取得 主键，自动生成
     *
     * @return 主键，自动生成
     */
    public String getAssetUuid() {
        return assetUuid;
    }

    /**
     * 设置 主键，自动生成
     *
     * @param assetUuid 主键，自动生成
     */
    public void setAssetUuid(String assetUuid) {
        this.assetUuid = assetUuid == null ? null : assetUuid.trim();
    }

    /**
     * 取得 资产代码
     *
     * @return 资产代码
     */
    public String getAssetid() {
        return assetid;
    }

    /**
     * 设置 资产代码
     *
     * @param assetid 资产代码
     */
    public void setAssetid(String assetid) {
        this.assetid = assetid == null ? null : assetid.trim();
    }

    /**
     * 取得 资产名称
     *
     * @return 资产名称
     */
    public String getAssetname() {
        return assetname;
    }

    /**
     * 设置 资产名称
     *
     * @param assetname 资产名称
     */
    public void setAssetname(String assetname) {
        this.assetname = assetname == null ? null : assetname.trim();
    }

    /**
     * 取得 人行资金信托代码
     *
     * @return 人行资金信托代码
     */
    public String getPobcassetid() {
        return pobcassetid;
    }

    /**
     * 设置 人行资金信托代码
     *
     * @param pobcassetid 人行资金信托代码
     */
    public void setPobcassetid(String pobcassetid) {
        this.pobcassetid = pobcassetid == null ? null : pobcassetid.trim();
    }

    /**
     * 取得 机构代码
     *
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     *
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Date getPlanSdate() {
        return planSdate;
    }

    public void setPlanSdate(Date planSdate) {
        this.planSdate = planSdate;
    }

    public Date getPlanEdate() {
        return planEdate;
    }

    public void setPlanEdate(Date planEdate) {
        this.planEdate = planEdate;
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
}

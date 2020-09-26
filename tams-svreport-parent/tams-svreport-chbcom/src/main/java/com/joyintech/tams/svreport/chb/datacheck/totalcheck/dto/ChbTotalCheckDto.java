package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.report.common.base.TableRow;

/**
 * 描述:CHB_TOTALCHECK表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-19
 */
public class ChbTotalCheckDto extends TableRow {
    /**
     * 主键:自动生成uuid
     */
    private String seqno;

    /**
     * 统计日期:跑批日期
     */
    private Date cdate;

    /**
     * 检查类型:01:存续余额同月度统计表对比 02:资产未注册 03:募集总量登记明细 04:月末底层持仓报送情况 05:资产持仓为负的产品 06:交易价格偏差过大的资产 07:资管计划底层金额偏差过大 08:
     */
    private String checkType;

    /**
     * 合格率:计算结果保留俩位小数
     */
    private BigDecimal yield;

    /**
     * 机构代码:
     */
    private String orgCode;

    /**
     * 创建人:
     */
    private String createUser;

    /**
     * 创建部门:
     */
    private String createDept;

    /**
     * 创建时间:
     */
    private Date createTime;

    /**
     * 更新人:
     */
    private String updateUser;

    /**
     * 更新时间:
     */
    private Date updateTime;
    
    /**
     * 提示信息
     */
    private String tips;

    /**
     * 主键:自动生成uuid
     * @return SEQNO 主键:自动生成uuid
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 主键:自动生成uuid
     * @param seqno 主键:自动生成uuid
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 统计日期:跑批日期
     * @return CDATE 统计日期:跑批日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 统计日期:跑批日期
     * @param cdate 统计日期:跑批日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 检查类型:01:存续余额同月度统计表对比 02:资产未注册 03:募集总量登记明细 04:月末底层持仓报送情况 05:资产持仓为负的产品 06:交易价格偏差过大的资产 07:资管计划底层金额偏差过大 08:
     * @return CHECK_TYPE 检查类型:01:存续余额同月度统计表对比 02:资产未注册 03:募集总量登记明细 04:月末底层持仓报送情况 05:资产持仓为负的产品 06:交易价格偏差过大的资产 07:资管计划底层金额偏差过大 08:
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * 检查类型:01:存续余额同月度统计表对比 02:资产未注册 03:募集总量登记明细 04:月末底层持仓报送情况 05:资产持仓为负的产品 06:交易价格偏差过大的资产 07:资管计划底层金额偏差过大 08:
     * @param checkType 检查类型:01:存续余额同月度统计表对比 02:资产未注册 03:募集总量登记明细 04:月末底层持仓报送情况 05:资产持仓为负的产品 06:交易价格偏差过大的资产 07:资管计划底层金额偏差过大 08:
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    /**
     * 合格率:计算结果保留俩位小数
     * @return YIELD 合格率:计算结果保留俩位小数
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 合格率:计算结果保留俩位小数
     * @param yield 合格率:计算结果保留俩位小数
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 机构代码:
     * @return ORG_CODE 机构代码:
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码:
     * @param orgCode 机构代码:
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建人:
     * @return CREATE_USER 创建人:
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人:
     * @param createUser 创建人:
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门:
     * @return CREATE_DEPT 创建部门:
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门:
     * @param createDept 创建部门:
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间:
     * @return CREATE_TIME 创建时间:
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间:
     * @param createTime 创建时间:
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人:
     * @return UPDATE_USER 更新人:
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人:
     * @param updateUser 更新人:
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间:
     * @return UPDATE_TIME 更新时间:
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间:
     * @param updateTime 更新时间:
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
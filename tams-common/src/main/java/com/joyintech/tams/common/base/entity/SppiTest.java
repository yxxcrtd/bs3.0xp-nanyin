package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SppiTest extends BaseEntity {
    /**
     * SPPI测试ID
     */
    private String sppiTestId;

    /**
     * 金融产品代码（标的代码）
     */
    private String finprodId;

    /**
     * SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     */
    private String sppiTmpType;

    /**
     * 测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)

     */
    private String sppiTmpNo;

    /**
     * 测算日期，默认采用平台日
     */
    private Date testDate;

    /**
     * SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过
     */
    private String testStatus;

    /**
     * 债务工具介绍
     */
    private String debtToolDesc;

    /**
     * 债务分层情况
     */
    private String debtLayerDesc;

    /**
     * 投资类型
     */
    private String investTypeDesc;

    /**
     * 交易对手名称及其对该资产的管理方式
     */
    private String manageTypeDesc;

    /**
     * 备注
     */
    private String remark;

    /**
     * 隐藏的HTML元素ID，多个用逗号隔开
     */
    private String hideIds;

    /**
     * 取得 SPPI测试ID
     * @return SPPI测试ID
     */
    public String getSppiTestId() {
        return sppiTestId;
    }

    /**
     * 设置 SPPI测试ID
     * @param sppiTestId SPPI测试ID
     */
    public void setSppiTestId(String sppiTestId) {
        this.sppiTestId = sppiTestId == null ? null : sppiTestId.trim();
    }

    /**
     * 取得 金融产品代码（标的代码）
     * @return 金融产品代码（标的代码）
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码（标的代码）
     * @param finprodId 金融产品代码（标的代码）
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     * @return SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     */
    public String getSppiTmpType() {
        return sppiTmpType;
    }

    /**
     * 设置 SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     * @param sppiTmpType SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     */
    public void setSppiTmpType(String sppiTmpType) {
        this.sppiTmpType = sppiTmpType == null ? null : sppiTmpType.trim();
    }

    /**
     * 取得 测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)

     * @return 测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)

     */
    public String getSppiTmpNo() {
        return sppiTmpNo;
    }

    /**
     * 设置 测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)

     * @param sppiTmpNo 测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)

     */
    public void setSppiTmpNo(String sppiTmpNo) {
        this.sppiTmpNo = sppiTmpNo == null ? null : sppiTmpNo.trim();
    }

    /**
     * 取得 测算日期，默认采用平台日
     * @return 测算日期，默认采用平台日
     */
    public Date getTestDate() {
        return testDate;
    }

    /**
     * 设置 测算日期，默认采用平台日
     * @param testDate 测算日期，默认采用平台日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    /**
     * 取得 SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过
     * @return SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过
     */
    public String getTestStatus() {
        return testStatus;
    }

    /**
     * 设置 SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过
     * @param testStatus SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过
     */
    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus == null ? null : testStatus.trim();
    }

    /**
     * 取得 债务工具介绍
     * @return 债务工具介绍
     */
    public String getDebtToolDesc() {
        return debtToolDesc;
    }

    /**
     * 设置 债务工具介绍
     * @param debtToolDesc 债务工具介绍
     */
    public void setDebtToolDesc(String debtToolDesc) {
        this.debtToolDesc = debtToolDesc == null ? null : debtToolDesc.trim();
    }

    /**
     * 取得 债务分层情况
     * @return 债务分层情况
     */
    public String getDebtLayerDesc() {
        return debtLayerDesc;
    }

    /**
     * 设置 债务分层情况
     * @param debtLayerDesc 债务分层情况
     */
    public void setDebtLayerDesc(String debtLayerDesc) {
        this.debtLayerDesc = debtLayerDesc == null ? null : debtLayerDesc.trim();
    }

    /**
     * 取得 投资类型
     * @return 投资类型
     */
    public String getInvestTypeDesc() {
        return investTypeDesc;
    }

    /**
     * 设置 投资类型
     * @param investTypeDesc 投资类型
     */
    public void setInvestTypeDesc(String investTypeDesc) {
        this.investTypeDesc = investTypeDesc == null ? null : investTypeDesc.trim();
    }

    /**
     * 取得 交易对手名称及其对该资产的管理方式
     * @return 交易对手名称及其对该资产的管理方式
     */
    public String getManageTypeDesc() {
        return manageTypeDesc;
    }

    /**
     * 设置 交易对手名称及其对该资产的管理方式
     * @param manageTypeDesc 交易对手名称及其对该资产的管理方式
     */
    public void setManageTypeDesc(String manageTypeDesc) {
        this.manageTypeDesc = manageTypeDesc == null ? null : manageTypeDesc.trim();
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
     * 取得 隐藏的HTML元素ID，多个用逗号隔开
     * @return 隐藏的HTML元素ID，多个用逗号隔开
     */
    public String getHideIds() {
        return hideIds;
    }

    /**
     * 设置 隐藏的HTML元素ID，多个用逗号隔开
     * @param hideIds 隐藏的HTML元素ID，多个用逗号隔开
     */
    public void setHideIds(String hideIds) {
        this.hideIds = hideIds == null ? null : hideIds.trim();
    }
}
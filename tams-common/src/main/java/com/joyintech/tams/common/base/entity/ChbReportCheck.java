package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbReportCheck extends BaseEntity {
    /**
     * 检查序列号
     */
    private String checkSeqno;

    /**
     * 报表ID
     */
    private String repCode;

    /**
     * 报表名称
     */
    private String repName;

    /**
     * 唯一标识
     */
    private String validKey;

    /**
     * 检查结果
     */
    private String checkResult;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 数据日期
     */
    private Date dataTime;

    /**
     * 是否逻辑校验
     */
    private String isLogicCheck;
    
    /**
     * 检查序列号
     */
    private String refseqno;
    /**
     * 检查日期
     */
    private String checkTime;
    /**
     * 检查唯一标示：技术使用
     */
    private String uniqueKey;

    /**
     * 取得 检查序列号
     * @return 检查序列号
     */
    public String getCheckSeqno() {
        return checkSeqno;
    }

    /**
     * 设置 检查序列号
     * @param checkSeqno 检查序列号
     */
    public void setCheckSeqno(String checkSeqno) {
        this.checkSeqno = checkSeqno == null ? null : checkSeqno.trim();
    }

    /**
     * 取得 报表ID
     * @return 报表ID
     */
    public String getRepCode() {
        return repCode;
    }

    /**
     * 设置 报表ID
     * @param repCode 报表ID
     */
    public void setRepCode(String repCode) {
        this.repCode = repCode == null ? null : repCode.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getRepName() {
        return repName;
    }

    /**
     * 设置 报表名称
     * @param repName 报表名称
     */
    public void setRepName(String repName) {
        this.repName = repName == null ? null : repName.trim();
    }

    /**
     * 取得 唯一标识
     * @return 唯一标识
     */
    public String getValidKey() {
        return validKey;
    }

    /**
     * 设置 唯一标识
     * @param validKey 唯一标识
     */
    public void setValidKey(String validKey) {
        this.validKey = validKey == null ? null : validKey.trim();
    }

    /**
     * 取得 检查结果
     * @return 检查结果
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 设置 检查结果
     * @param checkResult 检查结果
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * 设置 数据日期
     * @param dataTime 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 取得 是否逻辑校验
     * @return 是否逻辑校验
     */
    public String getIsLogicCheck() {
        return isLogicCheck;
    }

    /**
     * 设置 是否逻辑校验
     * @param isLogicCheck 是否逻辑校验
     */
    public void setIsLogicCheck(String isLogicCheck) {
        this.isLogicCheck = isLogicCheck == null ? null : isLogicCheck.trim();
    }

	public String getRefseqno() {
		return refseqno;
	}

	public void setRefseqno(String refseqno) {
		this.refseqno = refseqno == null ? null : refseqno.trim();
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
	    this.checkTime = checkTime == null ? null : checkTime.trim();
	}

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    
}
package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity;

public class RepRuleEntity {
	/**
     * 规则编号
     */
    private String id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 报表名称
     */
    private String repName;

    /**
     * 报表模板
     */
    private String repId;

    /**
     * 披露频率类型
     */
    private String rcfType;

    /**
     * 频率频率值
     */
    private String rcfValue;

    /**
     * 基准日类型
     */
    private String basisDateType;

    /**
     * 生成时点类型
     */
    private String makeType;

    /**
     * 生成时点值
     */
    private String makeValue;

    /**
     * 生成时点日期类型
     */
    private String makeDateType;

    /**
     * 生成时点时间
     */
    private String makeTime;

    /**
     * 披露时点类型
     */
    private String revealType;

    /**
     * 披露时点值
     */
    private String revealValue;

    /**
     * 披露时点日期类型
     */
    private String revealDateType;

    /**
     * 披露时点时间
     */
    private String revealTime;

    /**
     * 报表数据类型
     */
    private String dataType;

    /**
     * 取数范围
     */
    private String dataRange;

    /**
     * 取数开始日
     */
    private String dataBegin;

    /**
     * 取数结束日
     */
    private String dataEnd;

    /**
     * 取数范围值
     */
    private String dataValue;

    /**
     * 输出格式
     */
    private String fileOutType;

    /**
     * 披露渠道
     */
    private String revealChannel;

    /**
     * 文件输出地址
     */
    private String ftpId;

    /**
     * 是否自动披露
     */
    private String isReveal;

    /**
     * 是否自动确认
     */
    private String isConfirm;

    /**
     * 说明
     */
    private String detail;
    /**
     * 报表类型
     */
    private String reportCategory;
    /**
     * 取得 规则编号
     * @return 规则编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 规则编号
     * @param id 规则编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 规则名称
     * @return 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置 规则名称
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
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
     * 取得 报表模板
     * @return 报表模板
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表模板
     * @param repId 报表模板
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 披露频率类型
     * @return 披露频率类型
     */
    public String getRcfType() {
        return rcfType;
    }

    /**
     * 设置 披露频率类型
     * @param rcfType 披露频率类型
     */
    public void setRcfType(String rcfType) {
        this.rcfType = rcfType == null ? null : rcfType.trim();
    }

    /**
     * 取得 频率频率值
     * @return 频率频率值
     */
    public String getRcfValue() {
        return rcfValue;
    }

    /**
     * 设置 频率频率值
     * @param rcfValue 频率频率值
     */
    public void setRcfValue(String rcfValue) {
        this.rcfValue = rcfValue == null ? null : rcfValue.trim();
    }

    /**
     * 取得 基准日类型
     * @return 基准日类型
     */
    public String getBasisDateType() {
        return basisDateType;
    }

    /**
     * 设置 基准日类型
     * @param basisDateType 基准日类型
     */
    public void setBasisDateType(String basisDateType) {
        this.basisDateType = basisDateType == null ? null : basisDateType.trim();
    }

    /**
     * 取得 生成时点类型
     * @return 生成时点类型
     */
    public String getMakeType() {
        return makeType;
    }

    /**
     * 设置 生成时点类型
     * @param makeType 生成时点类型
     */
    public void setMakeType(String makeType) {
        this.makeType = makeType == null ? null : makeType.trim();
    }

    /**
     * 取得 生成时点值
     * @return 生成时点值
     */
    public String getMakeValue() {
        return makeValue;
    }

    /**
     * 设置 生成时点值
     * @param makeValue 生成时点值
     */
    public void setMakeValue(String makeValue) {
        this.makeValue = makeValue == null ? null : makeValue.trim();
    }

    /**
     * 取得 生成时点日期类型
     * @return 生成时点日期类型
     */
    public String getMakeDateType() {
        return makeDateType;
    }

    /**
     * 设置 生成时点日期类型
     * @param makeDateType 生成时点日期类型
     */
    public void setMakeDateType(String makeDateType) {
        this.makeDateType = makeDateType == null ? null : makeDateType.trim();
    }

    /**
     * 取得 生成时点时间
     * @return 生成时点时间
     */
    public String getMakeTime() {
        return makeTime;
    }

    /**
     * 设置 生成时点时间
     * @param makeTime 生成时点时间
     */
    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime == null ? null : makeTime.trim();
    }

    /**
     * 取得 披露时点类型
     * @return 披露时点类型
     */
    public String getRevealType() {
        return revealType;
    }

    /**
     * 设置 披露时点类型
     * @param revealType 披露时点类型
     */
    public void setRevealType(String revealType) {
        this.revealType = revealType == null ? null : revealType.trim();
    }

    /**
     * 取得 披露时点值
     * @return 披露时点值
     */
    public String getRevealValue() {
        return revealValue;
    }

    /**
     * 设置 披露时点值
     * @param revealValue 披露时点值
     */
    public void setRevealValue(String revealValue) {
        this.revealValue = revealValue == null ? null : revealValue.trim();
    }

    /**
     * 取得 披露时点日期类型
     * @return 披露时点日期类型
     */
    public String getRevealDateType() {
        return revealDateType;
    }

    /**
     * 设置 披露时点日期类型
     * @param revealDateType 披露时点日期类型
     */
    public void setRevealDateType(String revealDateType) {
        this.revealDateType = revealDateType == null ? null : revealDateType.trim();
    }

    /**
     * 取得 披露时点时间
     * @return 披露时点时间
     */
    public String getRevealTime() {
        return revealTime;
    }

    /**
     * 设置 披露时点时间
     * @param revealTime 披露时点时间
     */
    public void setRevealTime(String revealTime) {
        this.revealTime = revealTime == null ? null : revealTime.trim();
    }

    /**
     * 取得 报表数据类型
     * @return 报表数据类型
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 报表数据类型
     * @param dataType 报表数据类型
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 取得 取数范围
     * @return 取数范围
     */
    public String getDataRange() {
        return dataRange;
    }

    /**
     * 设置 取数范围
     * @param dataRange 取数范围
     */
    public void setDataRange(String dataRange) {
        this.dataRange = dataRange == null ? null : dataRange.trim();
    }

    /**
     * 取得 取数开始日
     * @return 取数开始日
     */
    public String getDataBegin() {
        return dataBegin;
    }

    /**
     * 设置 取数开始日
     * @param dataBegin 取数开始日
     */
    public void setDataBegin(String dataBegin) {
        this.dataBegin = dataBegin == null ? null : dataBegin.trim();
    }

    /**
     * 取得 取数结束日
     * @return 取数结束日
     */
    public String getDataEnd() {
        return dataEnd;
    }

    /**
     * 设置 取数结束日
     * @param dataEnd 取数结束日
     */
    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd == null ? null : dataEnd.trim();
    }

    /**
     * 取得 取数范围值
     * @return 取数范围值
     */
    public String getDataValue() {
        return dataValue;
    }

    /**
     * 设置 取数范围值
     * @param dataValue 取数范围值
     */
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    /**
     * 取得 输出格式
     * @return 输出格式
     */
    public String getFileOutType() {
        return fileOutType;
    }

    /**
     * 设置 输出格式
     * @param fileOutType 输出格式
     */
    public void setFileOutType(String fileOutType) {
        this.fileOutType = fileOutType == null ? null : fileOutType.trim();
    }

    /**
     * 取得 披露渠道
     * @return 披露渠道
     */
    public String getRevealChannel() {
        return revealChannel;
    }

    /**
     * 设置 披露渠道
     * @param revealChannel 披露渠道
     */
    public void setRevealChannel(String revealChannel) {
        this.revealChannel = revealChannel == null ? null : revealChannel.trim();
    }

    /**
     * 取得 文件输出地址
     * @return 文件输出地址
     */
    public String getFtpId() {
        return ftpId;
    }

    /**
     * 设置 文件输出地址
     * @param ftpId 文件输出地址
     */
    public void setFtpId(String ftpId) {
        this.ftpId = ftpId == null ? null : ftpId.trim();
    }

    /**
     * 取得 是否自动披露
     * @return 是否自动披露
     */
    public String getIsReveal() {
        return isReveal;
    }

    /**
     * 设置 是否自动披露
     * @param isReveal 是否自动披露
     */
    public void setIsReveal(String isReveal) {
        this.isReveal = isReveal == null ? null : isReveal.trim();
    }

    /**
     * 取得 是否自动确认
     * @return 是否自动确认
     */
    public String getIsConfirm() {
        return isConfirm;
    }

    /**
     * 设置 是否自动确认
     * @param isConfirm 是否自动确认
     */
    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm == null ? null : isConfirm.trim();
    }

    /**
     * 取得 说明
     * @return 说明
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置 说明
     * @param detail 说明
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}
    
}

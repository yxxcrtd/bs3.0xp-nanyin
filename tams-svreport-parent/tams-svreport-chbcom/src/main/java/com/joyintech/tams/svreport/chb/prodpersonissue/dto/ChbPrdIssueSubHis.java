package com.joyintech.tams.svreport.chb.prodpersonissue.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
/**
 * 描述:CHB_PRD_ISSUE_SUB_HIS表的实体类
 * @version
 * @author:  jiangxiao
 * @创建时间: 2019-05-23
 */
public class ChbPrdIssueSubHis {
    /**
     * 登记序列
     */
    private String registSeqno;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 产品起始日期
     */
    private Date prdVdate;

    /**
     * 产品终止日期
     */
    private Date prdMdate;

    /**
     * 是否为结构化（分级）产品
     */
    private String isConstracte;

    /**
     * 层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    private String gradeType;

    /**
     * 预计客户最低年收益率 %
     */
    private BigDecimal expMinYield;

    /**
     * 预计客户最高年收益率 %
     */
    private BigDecimal expMaxYield;

    /**
     * 平均预计客户年收益率%
     */
    private BigDecimal expAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String yieldComment;

    /**
     * 开放模式      01 有规律开放/02 无规律开放
     */
    private String openMode;

    /**
     * 规律开放周期
     */
    private String openCircle;

    /**
     * 其他规律开放周期 (天)
     */
    private BigDecimal openCircleDays;

    /**
     * 无规律开放说明
     */
    private String ruleComment;

    /**
     * 首次开放周期起始日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放            01 是/02 否
     */
    private String holidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    private BigDecimal avgOpenCount;

    /**
     * 开放期业务
     */
    private String openBuss;

    /**
     * 开放期业务说明
     */
    private String openBussRemark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败
     */
    private String submitFeedbackStatus;

    /**
     * 报送状态      未报送/接口报送/手工处理
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 数据状态，      新建/修改/删除
     */
    private String status;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 创建人
     */
    private String createUser;

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
     * 报送日期
     */
    private Date submitTime;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 报送截止日
     */
    private Date submitDeadline;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 报送流水号
     */
    private String sumitSeq;
    
    /**
     * 管理方式
     */
    private String manageType;

    /**
     * 业绩比较基准下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldLow;

    /**
     * 业绩比较基准上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldHigh;

    /**
     * 业绩比较基准说明
     */
    private String yieldRemark;

    /**
     * 资金托管账号
     */
    private String busiAcctNo;

    /**
     * 资金托管账户
     */
    private String acctName;
    

    /**
     * 登记序列
     * @return REGIST_SEQNO 登记序列
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 登记序列
     * @param registSeqno 登记序列
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 产品登记编码
     * @return PROD_REGIST_CODE 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 理财产品代码
     * @return PROD_ID 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 募集起始日期
     * @return RAISE_VDATE 募集起始日期
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    /**
     * 募集结束日期
     * @return RAISE_ENDDATE 募集结束日期
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 募集结束日期
     * @param raiseEnddate 募集结束日期
     */
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 产品起始日期
     * @return PRD_VDATE 产品起始日期
     */
    public Date getPrdVdate() {
        return prdVdate;
    }

    /**
     * 产品起始日期
     * @param prdVdate 产品起始日期
     */
    public void setPrdVdate(Date prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 产品终止日期
     * @return PRD_MDATE 产品终止日期
     */
    public Date getPrdMdate() {
        return prdMdate;
    }

    /**
     * 产品终止日期
     * @param prdMdate 产品终止日期
     */
    public void setPrdMdate(Date prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 是否为结构化（分级）产品
     * @return IS_CONSTRACTE 是否为结构化（分级）产品
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 是否为结构化（分级）产品
     * @param isConstracte 是否为结构化（分级）产品
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     * @return GRADE_TYPE 层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     * @param gradeType 层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 预计客户最低年收益率 %
     * @return EXP_MIN_YIELD 预计客户最低年收益率 %
     */
    public BigDecimal getExpMinYield() {
        return expMinYield;
    }

    /**
     * 预计客户最低年收益率 %
     * @param expMinYield 预计客户最低年收益率 %
     */
    public void setExpMinYield(BigDecimal expMinYield) {
        this.expMinYield = expMinYield;
    }

    /**
     * 预计客户最高年收益率 %
     * @return EXP_MAX_YIELD 预计客户最高年收益率 %
     */
    public BigDecimal getExpMaxYield() {
        return expMaxYield;
    }

    /**
     * 预计客户最高年收益率 %
     * @param expMaxYield 预计客户最高年收益率 %
     */
    public void setExpMaxYield(BigDecimal expMaxYield) {
        this.expMaxYield = expMaxYield;
    }

    /**
     * 平均预计客户年收益率%
     * @return EXP_AVG_YIELD 平均预计客户年收益率%
     */
    public BigDecimal getExpAvgYield() {
        return expAvgYield;
    }

    /**
     * 平均预计客户年收益率%
     * @param expAvgYield 平均预计客户年收益率%
     */
    public void setExpAvgYield(BigDecimal expAvgYield) {
        this.expAvgYield = expAvgYield;
    }

    /**
     * 收益率分档情况说明
     * @return YIELD_COMMENT 收益率分档情况说明
     */
    public String getYieldComment() {
        return yieldComment;
    }

    /**
     * 收益率分档情况说明
     * @param yieldComment 收益率分档情况说明
     */
    public void setYieldComment(String yieldComment) {
        this.yieldComment = yieldComment == null ? null : yieldComment.trim();
    }

    /**
     * 开放模式      01 有规律开放/02 无规律开放
     * @return OPEN_MODE 开放模式      01 有规律开放/02 无规律开放
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 开放模式      01 有规律开放/02 无规律开放
     * @param openMode 开放模式      01 有规律开放/02 无规律开放
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 规律开放周期
     * @return OPEN_CIRCLE 规律开放周期
     */
    public String getOpenCircle() {
        return openCircle;
    }

    /**
     * 规律开放周期
     * @param openCircle 规律开放周期
     */
    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle == null ? null : openCircle.trim();
    }

    /**
     * 其他规律开放周期 (天)
     * @return OPEN_CIRCLE_DAYS 其他规律开放周期 (天)
     */
    public BigDecimal getOpenCircleDays() {
        return openCircleDays;
    }

    /**
     * 其他规律开放周期 (天)
     * @param openCircleDays 其他规律开放周期 (天)
     */
    public void setOpenCircleDays(BigDecimal openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    /**
     * 无规律开放说明
     * @return RULE_COMMENT 无规律开放说明
     */
    public String getRuleComment() {
        return ruleComment;
    }

    /**
     * 无规律开放说明
     * @param ruleComment 无规律开放说明
     */
    public void setRuleComment(String ruleComment) {
        this.ruleComment = ruleComment == null ? null : ruleComment.trim();
    }

    /**
     * 首次开放周期起始日
     * @return FIRST_OPEN_DAY 首次开放周期起始日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 首次开放周期起始日
     * @param firstOpenDay 首次开放周期起始日
     */
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 节假日是否开放            01 是/02 否
     * @return HOLIDAY_ON_FLAG 节假日是否开放            01 是/02 否
     */
    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    /**
     * 节假日是否开放            01 是/02 否
     * @param holidayOnFlag 节假日是否开放            01 是/02 否
     */
    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag == null ? null : holidayOnFlag.trim();
    }

    /**
     * 平均开放次数（年化）
     * @return AVG_OPEN_COUNT 平均开放次数（年化）
     */
    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    /**
     * 平均开放次数（年化）
     * @param avgOpenCount 平均开放次数（年化）
     */
    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    /**
     * 开放期业务
     * @return OPEN_BUSS 开放期业务
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 开放期业务
     * @param openBuss 开放期业务
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss == null ? null : openBuss.trim();
    }

    /**
     * 开放期业务说明
     * @return OPEN_BUSS_REMARK 开放期业务说明
     */
    public String getOpenBussRemark() {
        return openBussRemark;
    }

    /**
     * 开放期业务说明
     * @param openBussRemark 开放期业务说明
     */
    public void setOpenBussRemark(String openBussRemark) {
        this.openBussRemark = openBussRemark == null ? null : openBussRemark.trim();
    }

    /**
     * 工作流代码
     * @return WORKFLOW_ID 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败
     * @return SUBMIT_FEEDBACK_STATUS 反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败
     * @param submitFeedbackStatus 反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 报送状态      未报送/接口报送/手工处理
     * @return SEND_STATUS 报送状态      未报送/接口报送/手工处理
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态      未报送/接口报送/手工处理
     * @param sendStatus 报送状态      未报送/接口报送/手工处理
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 数据状态，      新建/修改/删除
     * @return STATUS 数据状态，      新建/修改/删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态，      新建/修改/删除
     * @param status 数据状态，      新建/修改/删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 中债报送截止日
     * @return CHB_SUBMIT_DEADLINE 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 报送截止日
     * @return SUBMIT_DEADLINE 报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 报送截止日
     * @param submitDeadline 报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 报送流水号
     * @return SUMIT_SEQ 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 报送流水号
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }
    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式
     * @param manageType 管理方式
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 业绩比较基准下限
     * @return 业绩比较基准下限
     */
    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    /**
     * 设置 业绩比较基准下限
     * @param yieldLow 业绩比较基准下限
     */
    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    /**
     * 取得 业绩比较基准上限
     * @return 业绩比较基准上限
     */
    public BigDecimal getYieldHigh() {
        return yieldHigh;
    }

    /**
     * 设置 业绩比较基准上限
     * @param yieldHigh 业绩比较基准上限
     */
    public void setYieldHigh(BigDecimal yieldHigh) {
        this.yieldHigh = yieldHigh;
    }

    /**
     * 取得 业绩比较基准说明
     * @return 业绩比较基准说明
     */
    public String getYieldRemark() {
        return yieldRemark;
    }

    /**
     * 设置 业绩比较基准说明
     * @param yieldRemark 业绩比较基准说明
     */
    public void setYieldRemark(String yieldRemark) {
        this.yieldRemark = yieldRemark == null ? null : yieldRemark.trim();
    }

    /**
     * 取得 资金托管账号
     * @return 资金托管账号
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 资金托管账号
     * @param busiAcctNo 资金托管账号
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo == null ? null : busiAcctNo.trim();
    }

    /**
     * 取得 资金托管账户
     * @return 资金托管账户
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金托管账户
     * @param acctName 资金托管账户
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }
}
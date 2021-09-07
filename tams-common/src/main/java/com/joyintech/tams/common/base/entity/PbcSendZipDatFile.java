package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class PbcSendZipDatFile extends BaseEntity {
    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 压缩包编号
     */
    private String datZipCode;

    /**
     * 压缩包放置路径
     */
    private String datZipPath;

    /**
     * 报送类型
     */
    private String datZipType;

    /**
     * 报送明细类型
     */
    private String datZipDatType;

    /**
     * zip包状态
     */
    private String datZipStatus;

    /**
     * 压缩包下载时间
     */
    private Date zipDownloadTime;

    /**
     * 数据业务日期
     */
    private String statusVdate;

    /**
     * REPORT主键
     */
    private String reportuuid;

    /**
     * DAT明细数
     */
    private Integer pibDetailCnt;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 报送状态
     */
    private String zipSendStatus;

    /**
     * 反馈状态
     */
    private String returnZipSendStatus;

    /**
     * 反馈文件编号
     */
    private String returnZipCode;

    /**
     * 反馈时间
     */
    private Date returnTime;

    /**
     * 反馈消息
     */
    private String returnMsg;

    /**
     * DAT反馈退回数
     */
    private Integer returnErrorCnt;

    /**
     * DAT反馈通过数
     */
    private Integer returnPassCnt;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;

    /**
     * 填报机构名称
     */
    private String reportOrgName;

    /**
     * 社会信用代码
     */
    private String socialCreditCode;

    /**
     * 金融机构编码
     */
    private String finInstitutionCode;

    /**
     * 产品品种
     */
    private String prodVariety;

    /**
     * 责任人
     */
    private String personLiable;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 制表日期
     */
    private Date reportDate;
    /**
     * 结束日期
     */
    private Date enddate;

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getRegistSeqno() {
        return registSeqno;
    }
    /**
     * 取得 报送类型
     * @return 报送类型
     */
    private String  datZipTypeStr;
    /**
     * 取得 报送明细类型
     * @return 报送明细类型
     */
    private String  datZipDatTypeStr;
    /**
     * 报送状态
     */
    private String zipSendStatusStr;

    /**
     * 反馈状态
     */
    private String returnZipSendStatusStr;

    /**
     * zip包状态str
     */
    private String datZipStatusStr;

    /**
     * 设置 主键uuid
     * @param registSeqno 主键uuid
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 压缩包编号
     * @return 压缩包编号
     */
    public String getDatZipCode() {
        return datZipCode;
    }

    /**
     * 设置 压缩包编号
     * @param datZipCode 压缩包编号
     */
    public void setDatZipCode(String datZipCode) {
        this.datZipCode = datZipCode == null ? null : datZipCode.trim();
    }

    /**
     * 取得 压缩包放置路径
     * @return 压缩包放置路径
     */
    public String getDatZipPath() {
        return datZipPath;
    }

    /**
     * 设置 压缩包放置路径
     * @param datZipPath 压缩包放置路径
     */
    public void setDatZipPath(String datZipPath) {
        this.datZipPath = datZipPath == null ? null : datZipPath.trim();
    }

    /**
     * 取得 报送类型
     * @return 报送类型
     */
    public String getDatZipType() {
        return datZipType;
    }

    /**
     * 设置 报送类型
     * @param datZipType 报送类型
     */
    public void setDatZipType(String datZipType) {
        this.datZipType = datZipType == null ? null : datZipType.trim();
    }

    /**
     * 取得 报送明细类型
     * @return 报送明细类型
     */
    public String getDatZipDatType() {
        return datZipDatType;
    }

    /**
     * 设置 报送明细类型
     * @param datZipDatType 报送明细类型
     */
    public void setDatZipDatType(String datZipDatType) {
        this.datZipDatType = datZipDatType == null ? null : datZipDatType.trim();
    }

    /**
     * 取得 zip包状态
     * @return zip包状态
     */
    public String getDatZipStatus() {
        return datZipStatus;
    }

    /**
     * 设置 zip包状态
     * @param datZipStatus zip包状态
     */
    public void setDatZipStatus(String datZipStatus) {
        this.datZipStatus = datZipStatus == null ? null : datZipStatus.trim();
    }

    /**
     * 取得 压缩包下载时间
     * @return 压缩包下载时间
     */
    public Date getZipDownloadTime() {
        return zipDownloadTime;
    }

    /**
     * 设置 压缩包下载时间
     * @param zipDownloadTime 压缩包下载时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setZipDownloadTime(Date zipDownloadTime) {
        this.zipDownloadTime = zipDownloadTime;
    }

    /**
     * 取得 数据业务日期
     * @return 数据业务日期
     */
    public String getStatusVdate() {
        return statusVdate;
    }

    /**
     * 设置 数据业务日期
     * @param statusVdate 数据业务日期
     */
    public void setStatusVdate(String statusVdate) {
        this.statusVdate = statusVdate == null ? null : statusVdate.trim();
    }

    /**
     * 取得 REPORT主键
     * @return REPORT主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 REPORT主键
     * @param reportuuid REPORT主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 DAT明细数
     * @return DAT明细数
     */
    public Integer getPibDetailCnt() {
        return pibDetailCnt;
    }

    /**
     * 设置 DAT明细数
     * @param pibDetailCnt DAT明细数
     */
    public void setPibDetailCnt(Integer pibDetailCnt) {
        this.pibDetailCnt = pibDetailCnt;
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getZipSendStatus() {
        return zipSendStatus;
    }

    /**
     * 设置 报送状态
     * @param zipSendStatus 报送状态
     */
    public void setZipSendStatus(String zipSendStatus) {
        this.zipSendStatus = zipSendStatus == null ? null : zipSendStatus.trim();
    }

    /**
     * 取得 反馈状态
     * @return 反馈状态
     */
    public String getReturnZipSendStatus() {
        return returnZipSendStatus;
    }

    /**
     * 设置 反馈状态
     * @param returnZipSendStatus 反馈状态
     */
    public void setReturnZipSendStatus(String returnZipSendStatus) {
        this.returnZipSendStatus = returnZipSendStatus == null ? null : returnZipSendStatus.trim();
    }

    /**
     * 取得 反馈文件编号
     * @return 反馈文件编号
     */
    public String getReturnZipCode() {
        return returnZipCode;
    }

    /**
     * 设置 反馈文件编号
     * @param returnZipCode 反馈文件编号
     */
    public void setReturnZipCode(String returnZipCode) {
        this.returnZipCode = returnZipCode == null ? null : returnZipCode.trim();
    }

    /**
     * 取得 反馈时间
     * @return 反馈时间
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * 设置 反馈时间
     * @param returnTime 反馈时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * 取得 反馈消息
     * @return 反馈消息
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * 设置 反馈消息
     * @param returnMsg 反馈消息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    /**
     * 取得 DAT反馈退回数
     * @return DAT反馈退回数
     */
    public Integer getReturnErrorCnt() {
        return returnErrorCnt;
    }

    /**
     * 设置 DAT反馈退回数
     * @param returnErrorCnt DAT反馈退回数
     */
    public void setReturnErrorCnt(Integer returnErrorCnt) {
        this.returnErrorCnt = returnErrorCnt;
    }

    /**
     * 取得 DAT反馈通过数
     * @return DAT反馈通过数
     */
    public Integer getReturnPassCnt() {
        return returnPassCnt;
    }

    /**
     * 设置 DAT反馈通过数
     * @param returnPassCnt DAT反馈通过数
     */
    public void setReturnPassCnt(Integer returnPassCnt) {
        this.returnPassCnt = returnPassCnt;
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
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 发行机构名称
     * @return 发行机构名称
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 设置 发行机构名称
     * @param issuePartyName 发行机构名称
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName == null ? null : issuePartyName.trim();
    }

    /**
     * 取得 填报机构名称
     * @return 填报机构名称
     */
    public String getReportOrgName() {
        return reportOrgName;
    }

    /**
     * 设置 填报机构名称
     * @param reportOrgName 填报机构名称
     */
    public void setReportOrgName(String reportOrgName) {
        this.reportOrgName = reportOrgName == null ? null : reportOrgName.trim();
    }

    /**
     * 取得 社会信用代码
     * @return 社会信用代码
     */
    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    /**
     * 设置 社会信用代码
     * @param socialCreditCode 社会信用代码
     */
    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode == null ? null : socialCreditCode.trim();
    }

    /**
     * 取得 金融机构编码
     * @return 金融机构编码
     */
    public String getFinInstitutionCode() {
        return finInstitutionCode;
    }

    /**
     * 设置 金融机构编码
     * @param finInstitutionCode 金融机构编码
     */
    public void setFinInstitutionCode(String finInstitutionCode) {
        this.finInstitutionCode = finInstitutionCode == null ? null : finInstitutionCode.trim();
    }

    /**
     * 取得 产品品种
     * @return 产品品种
     */
    public String getProdVariety() {
        return prodVariety;
    }

    /**
     * 设置 产品品种
     * @param prodVariety 产品品种
     */
    public void setProdVariety(String prodVariety) {
        this.prodVariety = prodVariety == null ? null : prodVariety.trim();
    }

    /**
     * 取得 责任人
     * @return 责任人
     */
    public String getPersonLiable() {
        return personLiable;
    }

    /**
     * 设置 责任人
     * @param personLiable 责任人
     */
    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable == null ? null : personLiable.trim();
    }

    /**
     * 取得 联系方式
     * @return 联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置 联系方式
     * @param contact 联系方式
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 取得 制表日期
     * @return 制表日期
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * 设置 制表日期
     * @param reportDate 制表日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }


    /**
     * 取得 报送类型
     * @return 报送类型
     */
    public String getDatZipTypeStr() {
        if(StringUtil.isNotEmpty(this.getDatZipType())){
            return SysDataUtil.getNameFromDataDict(DictCode.人行报送类型, this.getDatZipType());
        }
        return "" ;
    }
    /**
     * 取得 报送明细类型
     * @return 报送明细类型
     */
    public String getDatZipDatTypeStr() {
        if(StringUtil.isNotEmpty(this.getDatZipDatType())){
            String str=SysDataUtil.getNameFromDataDict(DictCode.人行报送明细类型, this.getDatZipDatType());
            return str;
        }
        return "" ;
    }
    /**
     * 取得 发送状态
     * @return 发送状态
     */
    public String getZipSendStatusStr() {
        if(StringUtil.isNotEmpty(this.getZipSendStatus())){
            return SysDataUtil.getNameFromDataDict(DictCode.报送状态, this.getZipSendStatus());
        }
        return "" ;
    }
    /**
     * 取得 反馈状态
     * @return 反馈状态
     */
    public String getReturnZipSendStatusStr() {
        if(StringUtil.isNotEmpty(this.getReturnZipSendStatus())){
            return SysDataUtil.getNameFromDataDict(DictCode.人行报送反馈状态, this.getReturnZipSendStatus());
        }
        return "" ;
    }
    /**
     * zip包状态
     * @return  zip包状态
     */
    public String getDatZipStatusStr() {
        if(StringUtil.isNotEmpty(this.getDatZipStatus())){
            return SysDataUtil.getNameFromDataDict(DictCode.zip包状态, this.getDatZipStatus());
        }
        return "" ;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
/*
 * 文件名：ValFinprodTradeQuery001Dto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：估值核算系统V2.0 修改人：weipeng 修改时间：2018年5月3日
 * 修改内容：新建 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportconfirm.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品资产查询Dto
 * 
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Dto
 * @since 1.0
 */
@SuppressWarnings("unused")
@PageId("ReportConfirmManage001")
public class ReportConfirmManage001Dto extends RepReveal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    // @PrimaryKey(value=Field.PK_1)
    private String seqno;

    // 业务主键为空，则从data的json字符串（选中的row）中取 zhangyf 20190111 start
    @PrimaryKey(value = Field.PK_1)
    String getPk()
        throws Exception {

        if (StringUtil.isNotEmpty(seqno)) {
            return seqno;
        }
        List<ReportConfirmManage001Dto> dtoList = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        if (!StringUtil.isEmptyList(dtoList) && dtoList.get(0) != null && StringUtil.isNotEmpty(dtoList.get(0).getSeqno())) {
            return dtoList.get(0).getSeqno();
        }
        throw new Exception("seqno or data.seqno Must not be empty !");
    }
    // 业务主键为空，则从data的json字符串（选中的row）中取 zhangyf 20190111 end

    private String confirmStatusName;

    private String revealStatusName;

    private String createTimeStr;

    private String updateTypeFlag;// 更新标识

    private String elementId;

    private String revealDateStr;  // 披露日
    // add by guozhongbo 报表披露管理 20181204 start

    private String revealChannelName; // 披露渠道

    private String isConfirmName;  // 确认方式

    private String isRevealName;  // 披露方式
    // add by guozhongbo 报表披露管理 20181204 end

    /**
     * 勾选的数据json字符串
     */
    private String data;

    private String confirmDateStr;

    private String uploadTime; // 上传时间

    private String reportCategoryName; // 报表类别名称

    private String reportDate; // 报告期

    private String revealEndDateStr; // 披露截至日

    public String getSeqno() {
        return this.getId();
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 获取 confirmDateStr
     * 
     * @return confirmDateStr.
     */
    public String getConfirmDateStr() {
        return confirmDateStr;
    }

    /**
     * 设置 confirmDateStr
     * 
     * @param confirmDateStr confirmDateStr
     */
    public void setConfirmDateStr(String confirmDateStr) {
        this.confirmDateStr = confirmDateStr;
    }

    /**
     * 获取 revealStatusName
     * 
     * @return revealStatusName.
     */
    public String getRevealStatusName() {
        return this.revealStatusName;
    }

    /**
     * 设置 revealStatusName
     * 
     * @param revealStatusName revealStatusName
     */
    public void setRevealStatusName(String revealStatusName) {
        this.revealStatusName = revealStatusName;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ReportConfirmManage001Dto [prodName=" + this.getProdName() + ", prodid=" + this.getProdid() + ", sysFilepath="
               + this.getSysFilepath() + ", filename=" + this.getFilename() + "]";
    }

    /**
     * 获取 createTimeStr
     * 
     * @return createTimeStr.
     */
    public String getCreateTimeStr() {
        return DateUtil.format(DateUtil.parse(createTimeStr), "yyyy-MM-dd");
    }

    /**
     * 设置 createTimeStr
     * 
     * @param createTimeStr createTimeStr
     */
    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    /**
     * 获取 updateTypeFlag
     * 
     * @return updateTypeFlag.
     */
    public String getUpdateTypeFlag() {
        return updateTypeFlag;
    }

    /**
     * 设置 updateTypeFlag
     * 
     * @param updateTypeFlag updateTypeFlag
     */
    public void setUpdateTypeFlag(String updateTypeFlag) {
        this.updateTypeFlag = updateTypeFlag;
    }

    /**
     * 获取 data
     * 
     * @return data.
     */
    public String getData() {
        return data;
    }

    /**
     * 设置 data
     * 
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 confirmStatusName
     * 
     * @return confirmStatusName.
     */
    public String getConfirmStatusName() {

        return this.getConfirmStatus();
    }

    /**
     * 设置 confirmStatusName
     * 
     * @param confirmStatusName confirmStatusName
     */
    public void setConfirmStatusName(String confirmStatusName) {
        this.confirmStatusName = confirmStatusName;
    }

    public String getRevealDateStr() {
        return DateUtil.format(this.getRevealDate(), "yyyy-MM-dd");
    }

    public String getRevealChannelName() {
        return revealChannelName;
    }

    public void setRevealChannelName(String revealChannelName) {
        this.revealChannelName = revealChannelName;
    }

    public String getIsConfirmName() {
        return this.isConfirmName;
    }

    public void setIsConfirmName(String isConfirmName) {
        this.isConfirmName = isConfirmName;
    }

    public String getIsRevealName() {
        return this.isRevealName;
    }

    public void setIsRevealName(String isRevealName) {
        this.isRevealName = isRevealName;
    }

    public void setRevealDateStr(String revealDateStr) {
        this.revealDateStr = revealDateStr;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getReportCategoryName() {
        return reportCategoryName;
    }

    public void setReportCategoryName(String reportCategoryName) {
        this.reportCategoryName = reportCategoryName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getRevealEndDateStr() {
        return revealEndDateStr;
    }

    public void setRevealEndDateStr(String revealEndDateStr) {
        this.revealEndDateStr = revealEndDateStr;
    }

}

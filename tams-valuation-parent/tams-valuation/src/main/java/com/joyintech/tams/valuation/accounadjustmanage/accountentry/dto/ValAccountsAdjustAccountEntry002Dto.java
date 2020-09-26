/*
 * 文件名：AccountsAdjustAccountEntry001Dto.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights
 * Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;

/**
*
* 文件名：AccountsAdjustAccountEntry002Dto.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：核算管理-会计分录配置新建 画面用dto
* 系统名称：估值核算系统V1.0
*/
public class ValAccountsAdjustAccountEntry002Dto extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 父节点继承
     */
    private Boolean isExtends;

    /**
     * 科目
     */
    private String subjectName;

    /**
     * 借贷元素名称
     */
    private String dictName;

    /**
     * 记账网点
     */
    private String accountNet;

    /**
     * 借贷方向名称
     */
    @SuppressWarnings("unused")
    private String cdFlagName;

    /**
     * 科目代码
     */
    @NotEmpty(fieldName = "accountsadjust.accountentry.label.subject")
    private String subjectId;

    /**
     * 金额元素
     */
    @NotEmpty(fieldName = "accountsadjust.accountentry.label.formEle")
    private String formEle;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新时间
     */
    private Long updateTimeLong;

    public Long getUpdateTimeLongOther() {
        return this.updateTimeLong;
    }

    /**
     * 记账网点名称
     * 
     * @return 记账网点名称
     */
    @JsonProperty("accountNetName")
    public String getAccountNetName() {
        //TODO
//        DataDictItem from = accountNetCodeList.getDataDictItem(DictCode.记账网点, accountNet);
//        if (accountNetCodeList != null) {
//            return accountNetCodeList.getCodeName();
//        }
        return StringUtil.EMPTY;
    }

    /**
     * 更新时间
     * 
     * @return 更新时间
     */
    public long getUpdateTimeLong() {
        if (updateTime == null) {
            return 0L;
        }
        return updateTime.getTime();
    }

    /**
     * 是否禁用编辑
     * 
     * @return 0 不禁用，2 禁用
     * @see
     * @since 1.0
     */
    @JsonProperty("disCbxCol")
    public int getDisCbxCol() {
        if (isExtends) {
            return 2;
        }
        else {
            return 0;
        }
    }

    /**
     * 获取 isExtends
     * 
     * @return isExtends.
     */
    public Boolean getIsExtends() {
        return isExtends;
    }

    /**
     * 设置 isExtends
     * 
     * @param isExtends isExtends
     */
    public void setIsExtends(Boolean isExtends) {
        this.isExtends = isExtends;
    }

    /**
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 subjectName
     * 
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 dictName
     * 
     * @return dictName.
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置 dictName
     * 
     * @param dictName dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取 cdFlagName
     * 
     * @return cdFlagName.
     */
    public String getCdFlagName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
            SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
    }

    /**
     * 设置 cdFlagName
     * 
     * @param cdFlagName cdFlagName
     */
    public void setCdFlagName(String cdFlagName) {
        this.cdFlagName = cdFlagName;
    }

    /**
     * 获取 subjectId
     * 
     * @return subjectId.
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 subjectId
     * 
     * @param subjectId subjectId
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取 formEle
     * 
     * @return formEle.
     */
    public String getFormEle() {
        return formEle;
    }

    /**
     * 设置 formEle
     * 
     * @param formEle formEle
     */
    public void setFormEle(String formEle) {
        this.formEle = formEle;
    }

    /**
     * 获取 cdFlag
     * 
     * @return cdFlag.
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 cdFlag
     * 
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 获取 updateTime
     * 
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateTime
     * 
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设置 updateTimeLong
     * 
     * @param updateTimeLong updateTimeLong
     */
    public void setUpdateTimeLong(Long updateTimeLong) {
        this.updateTimeLong = updateTimeLong;
    }

    /**
     * 获取 accountNet
     * 
     * @return accountNet.
     */
    public String getAccountNet() {
        return accountNet;
    }

    /**
     * 设置 accountNet
     * 
     * @param accountNet accountNet
     */
    public void setAccountNet(String accountNet) {
        this.accountNet = accountNet;
    }

}

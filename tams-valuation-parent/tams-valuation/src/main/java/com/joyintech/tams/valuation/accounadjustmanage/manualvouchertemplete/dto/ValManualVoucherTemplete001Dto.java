/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.validator.ValManualVoucherTemplete001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 手工凭证查询dto
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete001Dto
 * @since 2.0
 */
@ValidLogic(targetClass = ValManualVoucherTemplete001Validator.class)
public class ValManualVoucherTemplete001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 模板编号
     */
    @PrimaryKey(value = Field.PK_1)
    private String tmpCode;

    /**
     * 模板名称
     */
    private String tmpName;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷方向(余额方向)
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    private BigDecimal oHappenAmt;

    /**
     * 数量
     */
    private BigDecimal happenNumber;

    /**
     * 业务类别
     */
    private String busiType;

    /**
     * 业务类别
     */
    private String busiTypeFmt;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 摘要
     */
    private String createUser;

    /**
     * 分录类型
     */
    private String recordType;

    /**
     * 处理操作
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<ValManualVoucherTemplete001Dto> selectedItem;

    /**
     * 取得 模板编号
     *
     * @return 模板编号
     */
    public String getTmpCode() {
        return tmpCode;
    }

    /**
     * 设置 模板编号
     *
     * @param tmpCode 模板编号
     */
    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    /**
     * 取得 模板名称
     *
     * @return 模板名称
     */
    public String getTmpName() {
        return tmpName;
    }

    /**
     * 设置 模板名称
     *
     * @param tmpName 模板名称
     */
    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    /**
     * 取得 科目号
     *
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     *
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 取得 科目名称
     *
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     *
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 借贷方向
     *
     * @return 借贷方向
     */
    public String getCdFlag() {
        return cdFlag;
    }

    public String getCdFlagFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
                SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
    }

    /**
     * 设置 借贷方向
     *
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 取得 币种
     *
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 币种Fmt
     *
     * @return 币种
     */
    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }

    /**
     * 取得 金额
     *
     * @return 金额
     */
    public BigDecimal getHappenAmt() {
        return  null != oHappenAmt ? oHappenAmt : BigDecimal.ZERO;
    }

    /**
     * 设置 金额
     *
     * @param oHappenAmt 金额
     */
    public void setHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }

    /**
     * 取得 数量
     *
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return null != happenNumber ? happenNumber : BigDecimal.ZERO;
    }

    /**
     * 设置 数量
     *
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    /**
     * 获取 业务类型
     *
     * @return 业务类型.
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 获取 业务类型Fmt
     *
     * @return 业务类型.
     */
    public String getBusiTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, busiType))?
                SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, busiType):busiType;
    }

    /**
     * 设置 业务类型
     *
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public void setBusiTypeFmt(String busiTypeFmt) {
        this.busiTypeFmt = busiTypeFmt;
    }

    /**
     * 获取 createUser
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 createUser
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<ValManualVoucherTemplete001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ValManualVoucherTemplete001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}

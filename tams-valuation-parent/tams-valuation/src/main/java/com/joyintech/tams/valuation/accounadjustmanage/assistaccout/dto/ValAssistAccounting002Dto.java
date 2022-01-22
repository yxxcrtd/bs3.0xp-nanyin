package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class ValAssistAccounting002Dto extends BaseEntity implements ProcessEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String booksetId;
    /**
     * 账套名称
     */
    private String booksetName;
    /**
     * 内部科目编码
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    private String subjectInternalCode;
    /**
     * 内部科目名称
     */
    private String subjectInternalName;


    /**
     * 辅助核算项集合
     */

    List<ValAssistHandDto> hands;

    /**
     * 辅助核算项集合
     */
    private String handjson;

    /**
     * 科目级别
     */
    private String subjectLevel;
    /**
     * 科目类型
     */
    private String subjectType;
    /**
     * 科目类型名称
     */
    private String subjectTypeName;
    /**
     * 是否核算项
     */
    private String accFlag;
    /**
     * 是否核算项 名称
     */
    private String accFlagName;
    /**
     * 底层科目
     */
    private String genDetsubRule;

    public String getHandjson() {
        return handjson;
    }

    public void setHandjson(String handjson) {
        this.handjson = handjson;
    }

    public String getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(String subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getAccFlag() {
        return accFlag;
    }

    public void setAccFlag(String accFlag) {
        this.accFlag = accFlag;
    }

    public String getGenDetsubRule() {
        return genDetsubRule;
    }

    public void setGenDetsubRule(String genDetsubRule) {
        this.genDetsubRule = genDetsubRule;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getSubjectInternalCode() {
        return subjectInternalCode;
    }

    public void setSubjectInternalCode(String subjectInternalCode) {
        this.subjectInternalCode = subjectInternalCode;
    }

    public String getSubjectInternalName() {
        return subjectInternalName;
    }

    public void setSubjectInternalName(String subjectInternalName) {
        this.subjectInternalName = subjectInternalName;
    }

    public void setHands(List<ValAssistHandDto> hands) {
        this.hands = hands;
    }

    public List<ValAssistHandDto> getHands() {
        return hands;
    }

    public String getSubjectTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.科目类型,  this.subjectType);
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public String getAccFlagName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否,  this.accFlag);
    }

    public void setAccFlagName(String accFlagName) {
        this.accFlagName = accFlagName;
    }
}

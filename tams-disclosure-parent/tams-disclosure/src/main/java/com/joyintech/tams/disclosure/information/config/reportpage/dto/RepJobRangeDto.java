/*
 * 文件名：RepJobRangeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportpage.dto;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;

/**
 * 规则数据
 * 
 * @author wanghb
 * @version 1.0 2019年3月13日
 * @see RepJobRangeDto
 * @since 1.0
 */
public class RepJobRangeDto extends RepJobRange {

    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735461L;

    private String ruleType1Name;

    /**
     * 规则2名称
     */
    private String ruleType2Name;

    /**
     * 规则3名称
     */
    private String ruleType3Name;

    public String getRuleType1Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.作业规则1,
            this.getRuleType1())) ? SysDataUtil.getNameFromDataDict(DictCode.作业规则1,
                this.getRuleType1()) : this.getRuleType1();
    }

    public void setRuleType1Name(String ruleType1Name) {
        this.ruleType1Name = ruleType1Name;
    }

    public String getRuleType2Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.作业规则2,
            this.getRuleType2())) ? SysDataUtil.getNameFromDataDict(DictCode.作业规则2,
                this.getRuleType2()) : this.getRuleType2();
    }

    public void setRuleType2Name(String ruleType2Name) {
        this.ruleType2Name = ruleType2Name;
    }

    public String getRuleType3Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.作业规则3,
            this.getRuleType3())) ? SysDataUtil.getNameFromDataDict(DictCode.作业规则3,
                this.getRuleType3()) : this.getRuleType3();
    }

    public void setRuleType3Name(String ruleType3Name) {
        this.ruleType3Name = ruleType3Name;
    }

    public String getRuleType4Name() {
        String value = this.getRuleType4();
        String key = this.getRuleType1();

        if (DictCode.收益类型.getValue().equals(key)) {
            value = backCode(DictCode.收益类型, this.getRuleType4());

        }
        else if (DictCode.募集方式.getValue().equals(key)) {
            value = backCode(DictCode.募集方式, this.getRuleType4());

        }
        else if (DictCode.发行方式.getValue().equals(key)) {
            value = backCode(DictCode.发行方式, this.getRuleType4());

        }
        else if (DictCode.开放模式.getValue().equals(key)) {
            value = backCode(DictCode.开放模式, this.getRuleType4());

        }
        else if (StringUtil.equals(DictCode.发行状态.getValue(), key)) {
            value = backCode(DictCode.发行状态, this.getRuleType4());

        }
        else if ("IS_INDEPE_ACC".equals(key)) {
            value = backCode(DisclosureDictCode.是否, this.getRuleType4());

        }
        else if (DisclosureDictCode.产品类型.getValue().equals(key)) {
            value = backCode(DisclosureDictCode.产品类型, this.getRuleType4());
        }
        else if (DisclosureDictCode.理财类型.getValue().equals(key)) {
            value = backCode(DisclosureDictCode.理财类型, this.getRuleType4());
        }
        else if (DisclosureDictCode.产品期限类型.getValue().equals(key)) {
            value = backCode(DisclosureDictCode.产品期限类型, this.getRuleType4());
        }
        return value;
    }

    public String backCode(BaseDictCode dictCode, String value) {
        return value = StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(dictCode,
            value)) ? SysDataUtil.getNameFromDataDict(dictCode, value) : value;
    }

    public void setRuleType4Name(String ruleType3Name) {
        this.ruleType3Name = ruleType3Name;
    }

}

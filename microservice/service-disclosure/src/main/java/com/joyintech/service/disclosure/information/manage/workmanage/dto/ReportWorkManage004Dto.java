package com.joyintech.service.disclosure.information.manage.workmanage.dto;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;

/**
 * 规则表
 * 
 * @author wanghaibin
 * @version 1.0 2018年12月12日
 * @see ReportWorkManage004Dto
 * @since 1.0
 */
public class ReportWorkManage004Dto extends RepJobRange {

    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735161L;

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
        return this.ruleType1Name;
    }

    public void setRuleType1Name(String ruleType1Name) {
        this.ruleType1Name = ruleType1Name;
    }

    public String getRuleType2Name() {
        return this.ruleType2Name;
    }

    public void setRuleType2Name(String ruleType2Name) {
        this.ruleType2Name = ruleType2Name;
    }

    public String getRuleType3Name() {
        return this.ruleType3Name;
    }

    public void setRuleType3Name(String ruleType3Name) {
        this.ruleType3Name = ruleType3Name;
    }

}

package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokMatchRlat extends BaseEntity {
    /**
     * 匹配类型：任务代码_业务类型匹配、任务代码_科目性质匹配、任务代码_会计场景匹配等
     */
    private String matchType;

    /**
     * 会计准则：企业、基金等
     */
    private String acctStandard;

    /**
     * 源：任务代码等
     */
    private String source;

    /**
     * 目标：业务类型、基础科目性质、会计场景等
     */
    private String target;

    /**
     * 取得 匹配类型：任务代码_业务类型匹配、任务代码_科目性质匹配、任务代码_会计场景匹配等
     * @return 匹配类型：任务代码_业务类型匹配、任务代码_科目性质匹配、任务代码_会计场景匹配等
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * 设置 匹配类型：任务代码_业务类型匹配、任务代码_科目性质匹配、任务代码_会计场景匹配等
     * @param matchType 匹配类型：任务代码_业务类型匹配、任务代码_科目性质匹配、任务代码_会计场景匹配等
     */
    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    /**
     * 取得 会计准则：企业、基金等
     * @return 会计准则：企业、基金等
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    /**
     * 设置 会计准则：企业、基金等
     * @param acctStandard 会计准则：企业、基金等
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard == null ? null : acctStandard.trim();
    }

    /**
     * 取得 源：任务代码等
     * @return 源：任务代码等
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 源：任务代码等
     * @param source 源：任务代码等
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 取得 目标：业务类型、基础科目性质、会计场景等
     * @return 目标：业务类型、基础科目性质、会计场景等
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置 目标：业务类型、基础科目性质、会计场景等
     * @param target 目标：业务类型、基础科目性质、会计场景等
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }
}
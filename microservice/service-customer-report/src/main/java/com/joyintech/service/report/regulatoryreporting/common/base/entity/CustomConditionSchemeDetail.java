package com.joyintech.service.report.regulatoryreporting.common.base.entity;

public class CustomConditionSchemeDetail extends CustomConditionSchemeDetailKey {
    /**
     * 自定义条件查询参数
     */
    private String queryPara;

    /**
     * 取得 自定义条件查询参数
     * @return 自定义条件查询参数
     */
    public String getQueryPara() {
        return queryPara;
    }

    /**
     * 设置 自定义条件查询参数
     * @param queryPara 自定义条件查询参数
     */
    public void setQueryPara(String queryPara) {
        this.queryPara = queryPara == null ? null : queryPara.trim();
    }
}
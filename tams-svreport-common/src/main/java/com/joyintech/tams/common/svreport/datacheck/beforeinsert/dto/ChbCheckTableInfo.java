/**
 * 
 */
package com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto;

/**
 * 中债校验用的表名
 * 
 * @author dubaojun
 * @since 2020-10-13
 */
public enum ChbCheckTableInfo {
	从业人员登记表("CHB_EMPLOYEE_INFO_SUB", true, "", new String[] {"EMPLOYEE_TYPE","IDENTIFY_NUMBER"}),
    个人发行登记表("CHB_PRD_ISSUE_SUB", true, "PROD_ID", new String[] {"PROD_ID"}),
    募集总量登记("CHB_PRD_RAISETOT_SUB", true, "PROD_ID", new String[] {"PROD_ID"}),
    投资交易表登记("CHB_INVEST_TRADE_SUB", true, null, new String[] {"PROD_ID", "TRADE_CODE", "ASSET_CODE", "CUR_ASSET_TYPE"}),
    资产要素登记表("CHB_ASSET_INFO_SUB", true, "ASSET_CODE", new String[] {"ASSET_CODE"}),
    资产估值信息登记表("CHB_AST_VAL_SUB", true, null, new String[] {"ASSET_CODE", "VAL_DATE"}),
    个人产品申报登记("CHB_PRD_DECLARE_SUB", true, "PROD_INS_ID", new String[] {"PROD_INS_ID"}),
    产品存续登记表("CHB_PRD_DEAL_SUB", true, null, new String[] {"PROD_ID", "MDATE"}),
    底层资产持仓信息登记表("CHB_BOT_AST_POS_SUB", true, null, new String[] {"POS_DATE", "ASSET_CODE", "PLAN_ASSET_ID"}),
    终止登记("CHB_PRD_END_SUB", true, "PROD_ID", new String[] {"PROD_ID"}),

    资产要素登记表INFO("CHB_ASSET_INFO", true, "ASSET_CODE", new String[] {"ASSET_CODE"}),
    个人发行登记表INFO("CHB_PRD_BASEINFO", true, "PROD_ID", new String[] {"PROD_ID"}),
    个人产品申报登记INFO("CHB_PRD_DECLARE", true, "PROD_INS_ID", new String[] {"PROD_INS_ID"});
    /**
     * 表名
     */
    private final String tableName;
    /**
     * 是否需要代码校验
     */
    private final boolean needCodeCheck;
    /**
     * 业务主键
     */
    private final String bussinessKey;
    /**
     * 校验数据重复的字段
     */
    private final String[] checkEuplicatedKeys;

    /**
     * 私有化构造器
     * 
     * @param tableName 表名
     * @param needCodeCheck 是否需要代码校验
     * @param bussinessKey 业务主键
     * @param checkEuplicatedKeys 校验数据重复的字段
     */
    private ChbCheckTableInfo(String tableName, boolean needCodeCheck, String bussinessKey, String[] checkEuplicatedKeys) {
        this.tableName = tableName;
        this.needCodeCheck = needCodeCheck;
        this.bussinessKey = bussinessKey;
        this.checkEuplicatedKeys = checkEuplicatedKeys;
    }

    /**
     * 获取 tableName
     * 
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 获取 needCodeCheck
     * 
     * @return needCodeCheck
     */
    public boolean isNeedCodeCheck() {
        return needCodeCheck;
    }

    /**
     * 获取 bussinessKey
     * 
     * @return bussinessKey
     */
    public String getBussinessKey() {
        return bussinessKey;
    }

    /**
     * 获取 checkEuplicatedKeys
     * 
     * @return checkEuplicatedKeys
     */
    public String[] getCheckEuplicatedKeys() {
        return checkEuplicatedKeys;
    }
}

package com.joyintech.component.svrreport.chb.contant;

import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 直连发送各登记报表类型定义
 * @author zhangliang
 * 20210522
 */
public enum SubTableType {

    产品申报登记("01", "101", "CHB_PRD_DECLARE_SUB", "产品申报登记"),
    
    产品发行登记("02", "103", "CHB_PRD_ISSUE_SUB", "产品发行登记"),
    
    机构和私人银行产品发行登记("03", "102", "CHB_PRD_ISSUE_SUB", "机构和私人银行产品发行登记"),
    
    募集总量登记("04", "104", "CHB_PRD_RAISETOT_SUB", "募集总量登记"),
    
    资管计划底层持仓信息登记("05", "110", "CHB_BOT_AST_POS_SUB", "资管计划底层持仓信息登记"),
    
    产品存续期登记("06", "105", "CHB_PRD_DEAL_SUB", "产品存续期登记"),
    
    产品终止登记("07", "111", "CHB_PRD_END_SUB", "产品终止登记"),
    
    从业人员登记("09", "112", "CHB_EMPLOYEE_INFO_SUB", "从业人员登记"),
    
    资产要素信息登记("10", "106", "CHB_ASSET_INFO_SUB", "资产要素信息登记"),
    
    资产交易信息登记("11", "108", "CHB_INVEST_TRADE_SUB", "资产交易信息登记"),
    
    资产估值信息登记("12", "109", "CHB_AST_VAL_SUB", "资产估值信息登记"),

    /*投资者身份信息登记("14", "201", "CHB_EQU_SUB", "投资者身份信息登记"),

    投资者持有信息登记("16", "202", "CHB_INVESTHOLD_INFO_SUB", "投资者持有信息登记"),

    投资者明细信息登记("26","203", "CHB_EQUDET_SUB", "投资者明细信息登记")*/;

    public static SubTableType genSubTableTypeBySubmitType(String submitType){
        SubTableType[] subTableTypeArr = SubTableType.values();
        for (SubTableType code : subTableTypeArr) {
            if (StringUtil.equals(submitType, code.getSubmitType())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 报送类型
     */
    private String submitType;

    /**
     * 文件接口类型
     */
    private String fileType;

    /**
     * 表格Id
     */
    private String tableId;

    /**
     * 表格名称
     */
    private String tableName;


    SubTableType(String submitType,String fileType,String tableId, String tableName){
        this.submitType = submitType;
        this.fileType = fileType;
        this.tableId = tableId;
        this.tableName = tableName;
    }

    public String getSubmitType() {
        return submitType;
    }

    public String getFileType() {
        return fileType;
    }

    public String getTableId(){
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

}

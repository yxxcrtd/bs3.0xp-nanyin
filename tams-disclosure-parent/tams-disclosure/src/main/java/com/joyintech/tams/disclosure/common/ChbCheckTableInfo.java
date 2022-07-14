/**
 * 
 */
package com.joyintech.tams.disclosure.common;

import com.joyintech.framework.util.StringUtil;

/**
 * 信披 校验用的表名
 * 
 * @author dubaojun
 * @since 2020-10-13
 */
public enum ChbCheckTableInfo {

    产品计划排期("DIS_PROD_PLAN", true, "", new String[] {"OPEN_START_DATE","PROD_ID"}, "");
    /**
     * 根据tableName获取枚举对象
     * @param tableName 表名
     * @return 枚举对象
     */
    public static ChbCheckTableInfo genChbCheckTableInfo(String tableName){
        ChbCheckTableInfo[] values = ChbCheckTableInfo.values();
        for (ChbCheckTableInfo code : values) {
            if (StringUtil.equals(tableName, code.getTableName())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 根据tableName获取枚举对象
     * @param tableName 表名
     * @return 校验对象
     */
    public static String genDataType(String tableName){
    	/***【统一数据维护】监管区域客户组别导入时，报空指针异常 leiepeng 20210511***/
        ChbCheckTableInfo tableInfo = genChbCheckTableInfo(tableName);
        return null!=tableInfo? 
        		(StringUtil.isNotEmpty(tableInfo.getDataType()) ? tableInfo.getDataType() : tableInfo.getTableName()):tableName;
    }

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
     * TMP使用，指向SUB校验
     */
    private String dataType;

    /**
     * 私有化构造器
     * 
     * @param tableName 表名
     * @param needCodeCheck 是否需要代码校验
     * @param bussinessKey 业务主键
     * @param checkEuplicatedKeys 校验数据重复的字段
     */
    private ChbCheckTableInfo(String tableName, boolean needCodeCheck, String bussinessKey, String[] checkEuplicatedKeys, String dataType) {
        this.tableName = tableName;
        this.needCodeCheck = needCodeCheck;
        this.bussinessKey = bussinessKey;
        this.checkEuplicatedKeys = checkEuplicatedKeys;
        this.dataType = dataType;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

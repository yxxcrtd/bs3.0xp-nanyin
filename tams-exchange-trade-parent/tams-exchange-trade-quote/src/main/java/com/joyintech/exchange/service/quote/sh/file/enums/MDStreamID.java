package com.joyintech.exchange.service.quote.sh.file.enums;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 10:47 2021/8/10
 */
public enum MDStreamID {
    /**
     * 这四个均存在于股票行情文件中 mktdt00.txt
     */
    INDEX("MD001", "指数类产品"),
    STOCK("MD002", "股票（A、B 股）类产品"),
    BOND_PRODUCT("MD003", "债券类产品"),
    FUND("MD004", "基金类产品"),
    /**
     * 单独行情文件 mktdt02.txt
     */
    BOND("MD201", "债券行情"),
    /**
     * 单独行情文件 mktdt03.txt
     */
    OPTION("MD301", "期权类产品"),

    /**
     *
     */
    GUO_ZHAI_YU_FA_XING("MD101", "国债预发行产品"),
    /**
     * 单独行情文件 mktdt01.txt
     */
    PAN_HOU_GU_DING_JIA_GE("MD102", "盘后固定价格");

    public String value;
    public String mean;

    MDStreamID(String value, String mean) {
        this.value = value;
        this.mean = mean;
    }
}

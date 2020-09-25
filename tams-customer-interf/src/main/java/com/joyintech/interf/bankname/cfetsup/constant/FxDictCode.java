package com.joyintech.interf.bankname.cfetsup.constant;

/**
 * 上行接口数据字典枚举
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-08
 */
public enum FxDictCode {

    /**
     * 交易方向
     */
    交易方向("PS"),
    /**
     *  结算方式
     */
    结算方式("BUYCLEARINGFORM"),
    /**
     *  清算类型
     */
    清算类型("SETTLETYPE"),
    /**
     *  本方角色
     */
    本方角色("HOMEROLE"),
    /**
     *  交易状态
     */
    交易状态("TRADESTATUS"),
    /**
     *  发送状态
     */
    发送状态("SENDSTATUS"),
    /**
     *  有效状态
     */
    有效状态("EFFECTFLAG"),
    /**
     * 共通业务类型
     */
    共通业务类型("COMMON_BUSINESS_TYPE"),

    /**
     * 交易方向_现券买卖
     */
    交易方向_现券("PS_XQ"),

    /**
     * 发送类型
     */
    发送类型("SEND_TYPE"),

    /**
     *  清算类型_现券买卖
     */
    清算类型_现券("SETTLETYPE_XQ"),

    /**
     *  回复报价_操作类型
     */
    回复报价_操作类型("REPLY_TYPE"),
    ;
    private String code;

    FxDictCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

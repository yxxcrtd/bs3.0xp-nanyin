package com.joyintech.invest.interf.common.core.contants;

/**
 * 接口列字段枚举
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InterfColumns
 * @since 1.0
 */
public enum InterfColumns {

    /**
     * 系统代码
     */
    COM_SYSCODE(
            "std400aqid", 4
    ),

    /**
     * 发送日期
     */
    COM_TRANDATE(
            "std400rbdt", 10
    ),

    /**
     * 发送时间
     */
    COM_TRANTIME(
            "std400rtim", 9
    ),

    /**
     * 信息类型
     */
    COM_STD400MSGT(
            "std400msgt", 1
    ),

    /**
     * 接口ID
     */
    COM_INFID(
            "stdprocode", 6
    ),
    /**
     * 返回码
     */
    COM_RTNCODE(
            "std400mgid", 7
    ),

    /**
     * 返回消息
     */
    COM_RTNMSG(
            "stdrtninfo", 1024
    ),
    /**
     * 客户端IP
     */
    COM_IP(
            "consumerIP", 128
    ),
    /**
     * 用户端口号
     */
    COM_PORT(
            "consumerPort", 5
    ), COM_OPTYPE(
            "", 1
    ),

    /**
     * 系统标识
     */
    COM_SYS_FLAG(
            "sysFlag", 2
    ),


    /**
     * 平台时间
     */
    COM_SYSDATE(
            "sysDate", 8
    );



    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private Class<?> fieldType;

    /**
     * 字段长度
     */
    private int fieldSize;

    private InterfColumns(String name, Class<?> type, int size) {
        this.fieldName = name;
        this.fieldType = type;
        this.fieldSize = size;
    }

    private InterfColumns(String name, int length) {
        this(name, String.class, length);
    }

    /**
     * 取得 字段名称
     *
     * @return 字段名称
     * @see
     * @since 1.0
     */
    public String fieldName() {
        return fieldName;
    }

    /**
     * 取得 字段类型
     *
     * @return 字段类型
     * @see
     * @since 1.0
     */
    public Class<?> fieldType() {
        return fieldType;
    }

    /**
     * 取得 字段长度
     *
     * @return 字段长度
     * @see
     * @since 1.0
     */
    public int fieldSize() {
        return fieldSize;
    }
}

/*
 * 文件名：DictCodeInterfList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

public enum DictCodeInterfList {
    
    /**
     * 接口发送接收状态_发送
     */
    接口发送接收状态_发送(DictCodeInterf.接口发送接收状态, "S", "发送"),
    
    /**
     * 接口发送接收状态_接收
     */
    接口发送接收状态_接收(DictCodeInterf.接口发送接收状态, "R", "接收"),
    
    /**
     * 接口对接系统ID_资管
     */
    接口对接系统ID_资管(DictCodeInterf.接口对接系统ID, "ZG", "资管系统"),
    
    /**
     * 接口服务器类型_FTP
     */
    接口服务器类型_FTP(DictCodeInterf.接口服务器类型, "01", "FTP"),
    
    /**
     * 接口服务器类型_SFTP
     */
    接口服务器类型_SFTP(DictCodeInterf.接口服务器类型, "02", "SFTP"),
    
    /**
     * 接口服务器类型_FTPS
     */
    接口服务器类型_FTPS(DictCodeInterf.接口服务器类型, "03", "FTPS"),
    
    /**
     * 接口服务器类型_SFTP_IDENTITY
     */
    接口服务器类型_SFTP_IDENTITY(DictCodeInterf.接口服务器类型, "04", "SFTP_IDENTITY"),
    /**
     * 接口服务器类型_SFTP_IDENTITY
     */
    接口服务器类型_DB(DictCodeInterf.接口服务器类型, "05", "DB"),
    /**
     * 接口服务器类型_SFTP_IDENTITY
     */
    接口服务器类型_LOCALHOST(DictCodeInterf.接口服务器类型, "06", "LOCALHOST"),
    /**
     * 文件解析功能启动源_TASK
     */
    文件解析功能启动源_TASK(DictCodeInterf.文件解析功能启动源,"1","CALL_SOURCE_TASK"),
    /**
     * 文件解析功能启动源_INTERF
     */
    文件解析功能启动源_INTERF(DictCodeInterf.文件解析功能启动源,"2","CALL_SOURCE_INTERF"),
    /**
     * 文件解析功能启动源_PAGE
     */
    文件解析功能启动源_PAGE(DictCodeInterf.文件解析功能启动源,"3","CALL_SOURCE_PAGE"),
    /**
     * 接口文件类型_XML
     */
    接口文件类型_XML(DictCodeInterf.接口文件类型,"01","XML"),
    /**
     * 接口文件类型_TXT
     */
    接口文件类型_TXT(DictCodeInterf.接口文件类型,"02","TXT"),
    /**
     * 接口文件类型_EXCEL
     */
    接口文件类型_EXCEL(DictCodeInterf.接口文件类型,"03","EXCEL"),
    /**
     * 接口文件类型_DBF
     */
    接口文件类型_DBF(DictCodeInterf.接口文件类型,"04","DBF"),
    
    /**
     * 接口文件类型_DB
     */
    接口文件类型_DB(DictCodeInterf.接口文件类型,"05","DB"),
    
    /**
     * 接口文件类型_STTXT
     */
    接口文件类型_STTXT(DictCodeInterf.接口文件类型,"06","STTXT"),//add by dongjun on 20181025
    
    /**
	 * 接口文件类型_DAT
	 */
	接口文件类型_DAT(DictCodeInterf.接口文件类型,"07","DAT"),//add by rendongliang on 20181219
    
    /**
     * 接口执行状态_未执行
     */
    接口执行状态_未执行(DictCodeInterf.接口执行状态, "00", "未执行"),
    
    /**
     * 接口执行状态_执行中
     */
    接口执行状态_执行中(DictCodeInterf.接口执行状态, "01", "执行中"),
    /**
     * 接口执行状态_执行成功
     */
    接口执行状态_执行成功(DictCodeInterf.接口执行状态, "02", "执行成功"),
    /**
     * 接口执行状态_执行失败
     */
    接口执行状态_执行失败(DictCodeInterf.接口执行状态, "03", "执行失败"),
    /**
     * 接口执行状态_手动停止
     */
    接口执行状态_手动停止(DictCodeInterf.接口执行状态, "04", "手动停止"),
    /**
     * 中债估值交易场所_银行间
     */
    中债估值交易场所_银行间(DictCodeInterf.中债估值交易场所,"XF00","银行间"),
    /**
     * 中债估值交易场所_柜台
     */
    中债估值交易场所_柜台(DictCodeInterf.中债估值交易场所,"XF01","柜台"),
    /**
     * 中债估值交易场所_上交所
     */
    中债估值交易场所_上交所(DictCodeInterf.中债估值交易场所,"XF02","上交所"),
    /**
     * 中债估值交易场所_深交所
     */
    中债估值交易场所_深交所(DictCodeInterf.中债估值交易场所,"XF03","深交所"),
    /**
     * 中债估值交易场所_其他
     */
    中债估值交易场所_其他(DictCodeInterf.中债估值交易场所,"XF04","其他"),
    
;
    


    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCodeInterf dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeInterfList(DictCodeInterf dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.codeName = codeName;
    }

    /**
     * 获得数据字典详细类
     *
     * @param value 数据字典值
     * @param dictCode 数据字典DictCode
     * @return DictCodeList
     * @see
     * @since 1.0
     */
    public static DictCodeInterfList from(DictCodeInterf dictCode, String value) {
        DictCodeInterfList[] values = DictCodeInterfList.values();
        for (DictCodeInterfList code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取数据字典明细List
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<SelectOptionDto> fromDictCode(DictCodeInterf dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeInterfList[] values = DictCodeInterfList.values();
        for (DictCodeInterfList code : values) {
            if (code.getDictCode() == dictCode) {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(code.getValue());
                option.setText(code.getCodeName());
                result.add(option);
            }
        }
        return result;
    }

    /**
     * 获取数据字典明细valueList
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<String> valuesfromDictCode(DictCodeInterf dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeInterfList[] values = DictCodeInterfList.values();
        for (DictCodeInterfList code : values) {
            if (code.getDictCode() == dictCode) {
                result.add(code.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 DictCode
     *
     * @return value.
     */
    public DictCodeInterf getDictCode() {
        return dictCode;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }
    /**
     * 获取 codeName
     *
     * @return codeName.
     */
    public String getCodeName() {
        return codeName;
    }
    /**
     * 获取 带有引号的名称
     *
     * @return 带有引号的名称.
     */
    public String getQuotesCodeName() {
        return "“" + codeName + "”";
    }
    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public Integer getIntegerCodeName() {
        Integer result = null;
        try {
            result = Integer.parseInt(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }
    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public BigDecimal getBigDecimalCodeName() {
        BigDecimal result = null;
        try {
            result = new BigDecimal(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

}

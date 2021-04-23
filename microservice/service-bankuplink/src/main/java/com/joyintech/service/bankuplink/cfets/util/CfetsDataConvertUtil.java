/*
 * 文件名：CfetsDataConvertUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cfets.util;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.background.component.uplink.interbank.common.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * cfets关联接口转换util
 * @author etliu
 * @version 1.0 2021年1月12日
 * @see CfetsDataConvertUtil
 * @since 1.0
 */
public class CfetsDataConvertUtil {

    /**
     * 根据原操作类型，获取失败报文对应的操作类型
     */
    private static Map<String, String> EXETYPE_FOR_FAILMSG_CONVERT_MAP = new HashMap<String, String>();

    static {
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方新增发送.getValue(),
            DictCodeList.银行间上行操作类型_本方新增发送失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方修改发送.getValue(),
            DictCodeList.银行间上行操作类型_本方修改发送失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方报价撤回.getValue(),
            DictCodeList.银行间上行操作类型_本方报价撤回失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方回复成交.getValue(),
            DictCodeList.银行间上行操作类型_本方回复成交失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方回复拒绝.getValue(),
            DictCodeList.银行间上行操作类型_本方回复拒绝失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方新增请求报价发送.getValue(),
            DictCodeList.银行间上行操作类型_本方新增请求报价发送失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方请求报价撤回.getValue(),
            DictCodeList.银行间上行操作类型_本方请求报价撤回失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方请求回复成交.getValue(),
            DictCodeList.银行间上行操作类型_本方请求回复成交失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方交谈修改发送.getValue(),
            DictCodeList.银行间上行操作类型_本方交谈修改发送失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方交谈发送.getValue(),
            DictCodeList.银行间上行操作类型_本方交谈发送失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方回复成交.getValue(),
            DictCodeList.银行间上行操作类型_本方回复成交失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方回复拒绝.getValue(),
            DictCodeList.银行间上行操作类型_本方回复拒绝失败.getValue());
        EXETYPE_FOR_FAILMSG_CONVERT_MAP.put(DictCodeList.银行间上行操作类型_本方报价撤回.getValue(),
            DictCodeList.银行间上行操作类型_本方报价撤回失败.getValue());
    }
    
    /**
     * 根据原操作类型，获取失败报文对应的操作类型
     * @param orgExeType 原操作类型
     * @return 失败报文对应的操作类型
     */
    public static String genExeTypeForFailMsg(String orgExeType) {
        if (StringUtil.isBlank(orgExeType)) {
            return null;
        }
        if (EXETYPE_FOR_FAILMSG_CONVERT_MAP.containsKey(orgExeType)) {
            return EXETYPE_FOR_FAILMSG_CONVERT_MAP.get(orgExeType);
        }
        return orgExeType;
    }
    
    /**
     * 转换回购交易方向
     * @param orgPs 原方向
     * @return 转换后方向
     */
    public static String convertRepoPs(String orgPs) {
        if (StringUtil.equals(BussinessDictCodeList.回购拆借方向_正回购.getValue(), orgPs)) {
            return BussinessDictCodeList.回购拆借方向_逆回购.getValue();
        }

        return BussinessDictCodeList.回购拆借方向_正回购.getValue();
    }
    
    /**
     * 转换现券交易方向
     * @param orgPs 原方向
     * @return 转换后方向
     */
    public static String convertBondPs(String orgPs) {
        if (StringUtil.equals(BussinessDictCodeList.交易方向_买入.getValue(), orgPs)) {
            return BussinessDictCodeList.交易方向_卖出.getValue();
        }

        return BussinessDictCodeList.交易方向_买入.getValue();
    }
}

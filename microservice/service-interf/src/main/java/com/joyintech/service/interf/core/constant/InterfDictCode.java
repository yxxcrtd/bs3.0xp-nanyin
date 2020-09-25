/*
 * 文件名：InterfDictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;

/**
 * 接口数据字典
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月1日
 * @see InterfDictCode
 * @since 1.0
 */
public enum InterfDictCode implements BaseDictCode {
    /**
     * 文件操作类型
     */
    文件操作类型(
        "EXCUTE_TYPE"
    ),
    /**
     * 执行状态
     */
    执行状态(
        "TASK_STATUS"
    ),
    /**
     * 交易类型
     */
    交易类型(
        "TRADE_TYPE"
    ),
    /**
     * 涨幅
     */
    涨幅(
        "DAY_YEAR_UP"
    ),
    /**
     * 周期值
     */
    周期值(
        "TERM_TYPE"
    ),
    /**
     * WIND市场
     */
    WIND市场(
        "MARKET"
    ),
    /**
     * WIND计息基准
     */
    WIND计息基准(
        "RATEBASIC"
    ),
    /**
     * WIND利率分配方式
     */
    WIND利率分配方式(
        "INTPAYRULE"
    ),
    /**
     * 出入方向
     */
    出入方向(
        "SEND_RECEIVE"
    ),
    /**
     * 产品状态
     */
    产品状态(
        "PRDSTATUS"
    ),
    /**
     * 销售部门
     */
    销售部门(
        "MKTDEPTNO"
    ),
    /**
     * 产品处理模式
     */
    产品处理模式(
        "PRODMODE"
    ),
    /**
     * 滚动模式
     */
    滚动模式(
        "ROLLMODE"
    ),
    /**
     * 发行方式
     */
    发行方式(
        "ISSUETYPE"
    ),
    /**
     * 付息周期
     */
    付息周期(
        "INTPAYCYCL"
    ),
    /**
     * OPICS导入状态
     */
    OPICS导入状态(
        "SENDFLAG"
    ),
    /**
     * ESB发送状态
     */
    ESB发送状态(
        "SEND_STATUS"
    ),
    /**
     * OPICS交易方式
     */
    OPICS交易方式(
        "TRAN_TYPE"
    ),
    /**
     * COST有效标识
     */
    COST有效标识(
        "EFFECTFLAG"
    ),
    /**
     * WIND节假日标志
     */
    WIND节假日标志(
        "HOLIDAY_EFFECTFLAG"
    ),
    /**
     * 转账方式
     */
    转账方式(
        "TRANSFER_MODE"
    ),
    /**
     * 附件标志
     */
    附件标志(
        "AFFIX_FLAG"
    ),
    /**
     * 转账类型
     */
    转账类型(
        "TRANSFER_TYPE"
    ),
    /**
     * 业务场景
     */
    业务场景(
        "BUSINESSTYPE"
    ),
    /**
     * 信贷币种
     */
    信贷币种(
        "CMSCCY"
    ),
    /**
     * 信贷有效标识
     */
    信贷有效标识(
        "CMS_EFFECTFLAG"
    ),
    /**
     * murex业务类型
     */
    murex业务类型(
        "MUREX_BUSS_TYPE"
    ),
    /**
     * murex事件类型
     */
    murex事件类型(
        "MUREX_EVENT_TYPE"
    ),
    /**
     * murex交易类型
     */
    murex交易类型(
        "MUREX_DEAL_TYPE"
    ),
    /**
     * 邮件发送状态
     */
    邮件发送状态(
        "SENDSTATE"
    ),
    /**
     * murex回购拆借方向
     */
    murex回购拆借方向(
        "MUREX_BACK_LEND_DIRECTION"
    ),
    /**
     * 评级类型
     */
    评级类型(
        "RATINGTYPE"
    ),
    /**
     * 长期短期
     */
    长期短期(
        "RATINGSUBTYPE"
    ),
    /**
     * 收益率曲线类型
     */
    收益率曲线类型(
        "CURVE_TYPE"
    ),
    /**
     * 费用种类
     */
    费用种类(
        "FEE_TYPE"
    ),
    /**
     * 收费模式
     */
    收费模式(
        "CHARGE_MODE"
    ),
    /**
     * 托管指令处理状态
     */
    托管指令处理状态(
        "STATUS_DESC"
    ),
    /**
     * 零售客户等级
     */
    零售客户等级(
        "CUSTOMER_LEVEL"
    );

    /**
     * 对应于数据字典code
     */
    private String value;

    InterfDictCode(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static BussinessDictCode get(String value) {
        BussinessDictCode[] values = BussinessDictCode.values();
        for (BussinessDictCode code : values) {
            if (StringUtil.equals(value, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static BussinessDictCode fromValue(String value) {
        for (BussinessDictCode dictCode : BussinessDictCode.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }

}

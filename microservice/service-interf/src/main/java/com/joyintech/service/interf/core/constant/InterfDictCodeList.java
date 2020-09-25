/*
 * 文件名：InterfDictCodeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

/**
 * 接口数据字典详细
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月1日
 * @see InterfDictCodeList
 * @since 1.0
 */
public enum InterfDictCodeList implements BaseDictCodeList {
    /**
     * 文件操作类型_上传
     */
    文件操作类型_上传(
        InterfDictCode.文件操作类型, "01", "上传"
    ),
    /**
     * 文件操作类型_下载
     */
    文件操作类型_下载(
        InterfDictCode.文件操作类型, "02", "下载"
    ),
    /**
     * 执行状态_成功
     */
    执行状态_成功(
        InterfDictCode.执行状态, "01", "成功"
    ),
    /**
     * 执行状态_失败
     */
    执行状态_失败(
        InterfDictCode.执行状态, "02", "失败"
    ),
    /**
     * 交易类型_成立
     */
    交易类型_成立(
        InterfDictCode.交易类型, "01", "成立"
    ),
    /**
     * 交易类型_存续
     */
    交易类型_存续(
        InterfDictCode.交易类型, "02", "存续"
    ),
    /**
     * 交易类型_到期
     */
    交易类型_到期(
        InterfDictCode.交易类型, "03", "到期"
    ),
    /**
     * 涨幅_日涨幅
     */
    涨幅_日涨幅(
        InterfDictCode.涨幅, "Z", "日涨幅"
    ),
    /**
     * 涨幅_年涨幅
     */
    涨幅_年涨幅(
        InterfDictCode.涨幅, "Y", "年涨幅"
    ),
    /**
     * 涨幅_历史涨幅
     */
    涨幅_历史涨幅(
        InterfDictCode.涨幅, "L", "历史涨幅"
    ),
    /**
     * 周期值_日
     */
    周期值_日(
        InterfDictCode.周期值, "1", "日"
    ),
    /**
     * 周期值_周
     */
    周期值_周(
        InterfDictCode.周期值, "7", "周"
    ),
    /**
     * 周期值_月
     */
    周期值_月(
        InterfDictCode.周期值, "30", "月"
    ),
    /**
     * 周期值_季
     */
    周期值_季(
        InterfDictCode.周期值, "90", "季"
    ),
    /**
     * 周期值_半年
     */
    周期值_半年(
        InterfDictCode.周期值, "180", "半年"
    ),
    /**
     * 周期值_年
     */
    周期值_年(
        InterfDictCode.周期值, "360", "年"
    ),
    /**
     * 周期值_历史
     */
    周期值_历史(
        InterfDictCode.周期值, "0", "历史"
    ),
    /**
     * WIND市场_场外
     */
    WIND市场_场外(
        InterfDictCode.WIND市场, "OF", "场外"
    ),
    /**
     * WIND市场_银行间
     */
    WIND市场_银行间(
        InterfDictCode.WIND市场, "IB", "银行间"
    ),
    /**
     * WIND市场_上交所
     */
    WIND市场_上交所(
        InterfDictCode.WIND市场, "SH", "上交所"
    ),
    /**
     * WIND市场_深交所
     */
    WIND市场_深交所(
        InterfDictCode.WIND市场, "SZ", "深交所"
    ),
    /**
     * WIND市场_柜台
     */
    WIND市场_柜台(
        InterfDictCode.WIND市场, "BC", "柜台"
    ),
    /**
     * WIND计息基准_A_365
     */
    WIND计息基准_A_365(
        InterfDictCode.WIND计息基准, "A/365", "A/365"
    ),
    /**
     * WIND计息基准_A_360
     */
    WIND计息基准_A_360(
        InterfDictCode.WIND计息基准, "A/360", "A/360"
    ),
    /**
     * WIND计息基准_A_A
     */
    WIND计息基准_A_A(
        InterfDictCode.WIND计息基准, "A/A", "A/A"
    ),
    /**
     * WIND利率分配方式_平均分配
     */
    WIND利率分配方式_平均分配(
        InterfDictCode.WIND利率分配方式, "D", "平均分配"
    ),
    /**
     * WIND利率分配方式_按实际天数分配
     */
    WIND利率分配方式_按实际天数分配(
        InterfDictCode.WIND利率分配方式, "A", "按实际天数分配"
    ),
    /**
     * 出入方向_发送ESB
     */
    出入方向_发送ESB(
        InterfDictCode.出入方向, "send", "发送ESB"
    ),
    /**
     * 出入方向_接收ESB
     */
    出入方向_接收ESB(
        InterfDictCode.出入方向, "receive", "接收ESB"
    ),
    /**
     * 产品状态_删除无效
     */
    产品状态_删除无效(
        InterfDictCode.产品状态, "D", "删除无效"
    ),
    /**
     * 产品状态_新建
     */
    产品状态_新建(
        InterfDictCode.产品状态, "A", "新建"
    ),
    /**
     * 产品状态_有效存续中
     */
    产品状态_有效存续中(
        InterfDictCode.产品状态, "E", "有效存续中"
    ),
    /**
     * 销售部门_零售产品管理部
     */
    销售部门_零售产品管理部(
        InterfDictCode.销售部门, "L", "零售产品管理部"
    ),
    /**
     * 销售部门_金融同业部
     */
    销售部门_金融同业部(
        InterfDictCode.销售部门, "T", "金融同业部"
    ),
    /**
     * 销售部门_对公产品和现金管理部
     */
    销售部门_对公产品和现金管理部(
        InterfDictCode.销售部门, "G", "对公产品和现金管理部"
    ),
    /**
     * 产品处理模式_普通类型
     */
    产品处理模式_普通类型(
        InterfDictCode.产品处理模式, "G", "普通类型"
    ),
    /**
     * 产品处理模式_日添利
     */
    产品处理模式_日添利(
        InterfDictCode.产品处理模式, "R", "日添利"
    ),
    /**
     * 产品处理模式_分层型产品
     */
    产品处理模式_分层型产品(
        InterfDictCode.产品处理模式, "F", "分层型产品"
    ),
    /**
     * 产品处理模式_阶梯型产品
     */
    产品处理模式_阶梯型产品(
        InterfDictCode.产品处理模式, "J", "阶梯型产品"
    ),
    /**
     * 产品处理模式_净值型产品
     */
    产品处理模式_净值型产品(
        InterfDictCode.产品处理模式, "N", "净值型产品"
    ),
    /**
     * 产品处理模式_滚动N天型产品
     */
    产品处理模式_滚动N天型产品(
        InterfDictCode.产品处理模式, "Z", "滚动N天型产品"
    ),
    /**
     * 产品处理模式_开放式阶梯型
     */
    产品处理模式_开放式阶梯型(
        InterfDictCode.产品处理模式, "K", "开放式阶梯型"
    ),
    /**
     * 产品处理模式_货币型
     */
    产品处理模式_货币型(
        InterfDictCode.产品处理模式, "H", "货币型"
    ),
    /**
     * 产品处理模式_7乘24日添利
     */
    产品处理模式_7乘24日添利(
        InterfDictCode.产品处理模式, "D", "7*24日添利"
    ),
    /**
     * 滚动模式_一次性产品
     */
    滚动模式_一次性产品(
        InterfDictCode.滚动模式, "Y", "一次性产品"
    ),
    /**
     * 滚动模式_定期滚动型产品
     */
    滚动模式_定期滚动型产品(
        InterfDictCode.滚动模式, "D", "定期滚动型产品"
    ),
    /**
     * 滚动模式_日添利型
     */
    滚动模式_日添利型(
        InterfDictCode.滚动模式, "B", "日添利型"
    ),
    /**
     * 滚动模式_N天每日滚动型
     */
    滚动模式_N天每日滚动型(
        InterfDictCode.滚动模式, "F", "N天每日滚动型"
    ),
    /**
     * 滚动模式_按天计收益全开放型
     */
    滚动模式_按天计收益全开放型(
        InterfDictCode.滚动模式, "G", "按天计收益全开放型"
    ),
    /**
     * 滚动模式_净值型
     */
    滚动模式_净值型(
        InterfDictCode.滚动模式, "H", "净值型"
    ),
    /**
     * 滚动模式_定开债
     */
    滚动模式_定开债(
        InterfDictCode.滚动模式, "I", "定开债"
    ),
    /**
     * 发行方式_单一
     */
    发行方式_单一(
        InterfDictCode.发行方式, "D", "单一"
    ),
    /**
     * 发行方式_集合
     */
    发行方式_集合(
        InterfDictCode.发行方式, "J", "集合"
    ),
    /**
     * 付息周期_利随本清
     */
    付息周期_利随本清(
        InterfDictCode.付息周期, "PC", "利随本清"
    ),
    /**
     * OPICS导入状态_已落地
     */
    OPICS导入状态_已落地(
        InterfDictCode.OPICS导入状态, "-1", "已落地"
    ),
    /**
     * OPICS导入状态_待处理
     */
    OPICS导入状态_待处理(
        InterfDictCode.OPICS导入状态, "-2", "待处理"
    ),
    /**
     * OPICS导入状态_待导入
     */
    OPICS导入状态_待导入(
        InterfDictCode.OPICS导入状态, "-3", "待导入"
    ),
    /**
     * OPICS导入状态_导入中
     */
    OPICS导入状态_导入中(
        InterfDictCode.OPICS导入状态, "-4", "导入中"
    ),
    /**
     * OPICS导入状态_失败
     */
    OPICS导入状态_失败(
        InterfDictCode.OPICS导入状态, "-5", "失败"
    ),
    /**
     * OPICS导入状态_待修复
     */
    OPICS导入状态_待修复(
        InterfDictCode.OPICS导入状态, "-6", "待修复"
    ),
    /**
     * OPICS导入状态_跑批中
     */
    OPICS导入状态_跑批中(
        InterfDictCode.OPICS导入状态, "-8", "跑批中"
    ),
    /**
     * OPICS导入状态_成功
     */
    OPICS导入状态_成功(
        InterfDictCode.OPICS导入状态, "0", "成功"
    ),
    /**
     * ESB发送状态_成功
     */
    ESB发送状态_成功(
        InterfDictCode.ESB发送状态, "S", "成功"
    ),
    /**
     * ESB发送状态_失败
     */
    ESB发送状态_失败(
        InterfDictCode.ESB发送状态, "F", "失败"
    ),
    /**
     * OPICS交易方式_FX
     */
    OPICS交易方式_FX(
        InterfDictCode.OPICS交易方式, "FX", "FX"
    ),
    /**
     * COST有效标识_删除
     */
    COST有效标识_删除(
        InterfDictCode.COST有效标识, "D", "删除"
    ),
    /**
     * COST有效标识_新建
     */
    COST有效标识_新建(
        InterfDictCode.COST有效标识, "A", "新建"
    ),
    /**
     * COST有效标识_有效
     */
    COST有效标识_有效(
        InterfDictCode.COST有效标识, "E", "有效"
    ),
    /**
     * WIND节假日标志_非节假日
     */
    WIND节假日标志_非节假日(
        InterfDictCode.WIND节假日标志, "W", "非节假日"
    ),
    /**
     * WIND节假日标志_节假日
     */
    WIND节假日标志_节假日(
        InterfDictCode.WIND节假日标志, "H", "节假日"
    ),
    /**
     * 转账方式_行内转账
     */
    转账方式_行内转账(
        InterfDictCode.转账方式, "Y", "行内转账"
    ),
    /**
     * 转账方式_行外转账
     */
    转账方式_行外转账(
        InterfDictCode.转账方式, "N", "行外转账"
    ),
    /**
     * 附件标志_有
     */
    附件标志_有(
        InterfDictCode.附件标志, "Y", "有"
    ),
    /**
     * 附件标志_没有
     */
    附件标志_没有(
        InterfDictCode.附件标志, "N", "没有"
    ),
    /**
     * 转账类型_托管户转上清所
     */
    转账类型_托管户转上清所(
        InterfDictCode.转账类型, "01", "托管户转上清所"
    ),
    /**
     * 转账类型_托管户转中债登
     */
    转账类型_托管户转中债登(
        InterfDictCode.转账类型, "02", "托管户转中债登"
    ),
    /**
     * 转账类型_上清所转托管户
     */
    转账类型_上清所转托管户(
        InterfDictCode.转账类型, "03", "上清所转托管户"
    ),
    /**
     * 转账类型_上清所转中债登
     */
    转账类型_上清所转中债登(
        InterfDictCode.转账类型, "04", "上清所转中债登"
    ),
    /**
     * 转账类型_中债登转托管户
     */
    转账类型_中债登转托管户(
        InterfDictCode.转账类型, "05", "中债登转托管户"
    ),
    /**
     * 转账类型_中债登转上清所
     */
    转账类型_中债登转上清所(
        InterfDictCode.转账类型, "06", "中债登转上清所"
    ),
    /**
     * 转账类型_外汇缴费
     */
    转账类型_外汇缴费(
        InterfDictCode.转账类型, "07", "外汇缴费"
    ),
    /**
     * 转账类型_上清所缴费
     */
    转账类型_上清所缴费(
        InterfDictCode.转账类型, "08", "上清所缴费"
    ),
    /**
     * 转账类型_中债登缴费
     */
    转账类型_中债登缴费(
        InterfDictCode.转账类型, "09", "中债登缴费"
    ),
    /**
     * 业务场景_债券
     */
    业务场景_债券(
        InterfDictCode.业务场景, "BOND", "债券"
    ),
    /**
     * 业务场景_回购
     */
    业务场景_回购(
        InterfDictCode.业务场景, "REPO", "回购"
    ),
    /**
     * 信贷币种_人民币
     */
    信贷币种_人民币(
        InterfDictCode.信贷币种, "RMB", "人民币"
    ),
    /**
     * 信贷有效标识_有效
     */
    信贷有效标识_有效(
        InterfDictCode.信贷有效标识, "E", "有效"
    ),
    /**
     * 信贷有效标识_新建
     */
    信贷有效标识_新建(
        InterfDictCode.信贷有效标识, "A", "新建"
    ),
    /**
     * murex业务类型_交易
     */
    murex业务类型_交易(
        InterfDictCode.murex业务类型, "DEAL", "交易"
    ),
    /**
     * murex业务类型_事件
     */
    murex业务类型_事件(
        InterfDictCode.murex业务类型, "EVENT", "事件"
    ),
    /**
     * murex交易类型_贵金属期货
     */
    murex交易类型_贵金属期货(
        InterfDictCode.murex交易类型, "comFuture", "贵金属期货"
    ),
    /**
     * murex交易类型_贵金属即远期
     */
    murex交易类型_贵金属即远期(
        InterfDictCode.murex交易类型, "comSpotFwd", "贵金属即远期"
    ),
    /**
     * murex交易类型_外汇即远期
     */
    murex交易类型_外汇即远期(
        InterfDictCode.murex交易类型, "fxspotfwd", "外汇即远期"
    ),
    /**
     * murex交易类型_外汇掉期
     */
    murex交易类型_外汇掉期(
        InterfDictCode.murex交易类型, "fxswap", "外汇掉期"
    ),
    /**
     * murex交易类型_外汇障碍式期权
     */
    murex交易类型_外汇障碍式期权(
        InterfDictCode.murex交易类型, "fxOption", "外汇障碍式期权"
    ),
    /**
     * murex交易类型_外汇香草期权
     */
    murex交易类型_外汇香草期权(
        InterfDictCode.murex交易类型, "fxOption", "外汇香草期权"
    ),
    /**
     * murex交易类型_债券
     */
    murex交易类型_债券(
        InterfDictCode.murex交易类型, "bond", "债券"
    ),
    /**
     * murex交易类型_利率互换
     */
    murex交易类型_利率互换(
        InterfDictCode.murex交易类型, "interestRateSwap", "利率互换"
    ),
    /**
     * murex交易类型_交叉货币互换
     */
    murex交易类型_交叉货币互换(
        InterfDictCode.murex交易类型, "currencySwap", "交叉货币互换"
    ),
    /**
     * murex交易类型_利率上下限
     */
    murex交易类型_利率上下限(
        InterfDictCode.murex交易类型, "capfloor", "利率上下限"
    ),
    /**
     * murex交易类型_标准债券远期
     */
    murex交易类型_标准债券远期(
        InterfDictCode.murex交易类型, "irdFuture", "标准债券远期"
    ),
    /**
     * murex交易类型_债券借贷
     */
    murex交易类型_债券借贷(
        InterfDictCode.murex交易类型, "bondLendingBorrowing", "债券借贷"
    ),
    /**
     * murex交易类型_拆借
     */
    murex交易类型_拆借(
        InterfDictCode.murex交易类型, "loanAndDeposit", "拆借"
    ),
    /**
     * murex交易类型_利率互换期权
     */
    murex交易类型_利率互换期权(
        InterfDictCode.murex交易类型, "swaption", "利率互换期权"
    ),
    /**
     * murex交易类型_质押式回购
     */
    murex交易类型_质押式回购(
        InterfDictCode.murex交易类型, "basketRepos", "质押式回购"
    ),
    /**
     * murex交易类型_买断式回购
     */
    murex交易类型_买断式回购(
        InterfDictCode.murex交易类型, "irdRepoRepo", "买断式回购"
    ),
    /**
     * murex交易类型_总收益互换
     */
    murex交易类型_总收益互换(
        InterfDictCode.murex交易类型, "totalReturnSwap", "总收益互换"
    ),
    /**
     * murex交易类型_单位现金流
     */
    murex交易类型_单位现金流(
        InterfDictCode.murex交易类型, "simpleCashFlows", "单位现金流"
    ),
    /**
     * murex交易类型_债券基金
     */
    murex交易类型_债券基金(
        InterfDictCode.murex交易类型, "eqdEquity", "债券基金"
    ),
    /**
     * murex交易类型_信用违约互换
     */
    murex交易类型_信用违约互换(
        InterfDictCode.murex交易类型, "cdswap", "信用违约互换"
    ),
    /**
     * murex交易类型_贵金属掉期
     */
    murex交易类型_贵金属掉期(
        InterfDictCode.murex交易类型, "comSwap", "贵金属掉期"
    ),
    /**
     * murex交易类型_保本理财中结构化产品
     */
    murex交易类型_保本理财中结构化产品(
        InterfDictCode.murex交易类型, "IRSRange", "保本理财中结构化产品"
    ),
    /**
     * murex交易类型_市场数据
     */
    murex交易类型_市场数据(
        InterfDictCode.murex交易类型, "MARKET", "市场数据"
    ),
    /**
     * murex事件类型_修改
     */
    murex事件类型_修改(
        InterfDictCode.murex事件类型, "cancelAndReissue", "修改"
    ),
    /**
     * murex事件类型_冲销
     */
    murex事件类型_冲销(
        InterfDictCode.murex事件类型, "cancel", "冲销"
    ),
    /**
     * murex事件类型_冲销事件
     */
    murex事件类型_冲销事件(
        InterfDictCode.murex事件类型, "cancelEvent", "冲销事件"
    ),
    /**
     * murex事件类型_触碰
     */
    murex事件类型_触碰(
        InterfDictCode.murex事件类型, "knock", "触碰"
    ),
    /**
     * murex事件类型_期权弃权
     */
    murex事件类型_期权弃权(
        InterfDictCode.murex事件类型, "expiry", "期权弃权"
    ),
    /**
     * murex事件类型_期权行权
     */
    murex事件类型_期权行权(
        InterfDictCode.murex事件类型, "exercise", "期权行权"
    ),
    /**
     * murex事件类型_UNWIND
     */
    murex事件类型_UNWIND(
        InterfDictCode.murex事件类型, "unwind", "UNWIND"
    ),
    /**
     * 邮件发送状态_成功
     */
    邮件发送状态_成功(
        InterfDictCode.邮件发送状态, "0", "成功"
    ),
    /**
     * 邮件发送状态_失败
     */
    邮件发送状态_失败(
        InterfDictCode.邮件发送状态, "1", "失败"
    ),
    /**
     * murex回购拆借方向_拆入
     */
    murex回购拆借方向_拆入(
        InterfDictCode.murex回购拆借方向, "borrow", "拆入"
    ),
    /**
     * murex回购拆借方向_拆出
     */
    murex回购拆借方向_拆出(
        InterfDictCode.murex回购拆借方向, "lend", "拆出"
    ),
    /**
     * 评级类型_主体评级
     */
    评级类型_主体评级(
        InterfDictCode.评级类型, "C", "主体评级"
    ),
    /**
     * 评级类型_主体评级
     */
    评级类型_债项评级(
        InterfDictCode.评级类型, "I", "债项评级"
    ),
    /**
     * 长期短期_短期
     */
    长期短期_短期(
        InterfDictCode.长期短期, "S", "短期"
    ),
    /**
     * 长期短期_长期
     */
    长期短期_长期(
        InterfDictCode.长期短期, "L", "长期"
    ),
    /**
     * 收益率曲线类型_即期
     */
    收益率曲线类型_即期(
        InterfDictCode.收益率曲线类型, "SP", "即期"
    ),
    /**
     * 收益率曲线类型_远期
     */
    收益率曲线类型_远期(
        InterfDictCode.收益率曲线类型, "MA", "远期"
    ),
    /**
     * 收益率曲线类型_远期的即期
     */
    收益率曲线类型_远期的即期(
        InterfDictCode.收益率曲线类型, "FS", "远期的即期"
    ),
    /**
     * 收益率曲线类型_远期的到期
     */
    收益率曲线类型_远期的到期(
        InterfDictCode.收益率曲线类型, "FM", "远期的到期"
    ),
    /**
     * 费用种类_交易费
     */
    费用种类_交易费(
        InterfDictCode.费用种类, "0", "交易费"
    ),
    /**
     * 费用种类_托管费
     */
    费用种类_托管费(
        InterfDictCode.费用种类, "1", "托管费"
    ),
    /**
     * 费用种类_管理费
     */
    费用种类_管理费(
        InterfDictCode.费用种类, "2", "管理费"
    ),
    /**
     * 费用种类_浮动管理费
     */
    费用种类_浮动管理费(
        InterfDictCode.费用种类, "3", "浮动管理费"
    ),
    /**
     * 费用种类_其他费用
     */
    费用种类_其他费用(
        InterfDictCode.费用种类, "4", "其他费用"
    ),
    /**
     * 费用种类_销售费
     */
    费用种类_销售费(
        InterfDictCode.费用种类, "6", "销售费"
    ),
    /**
     * 收费模式_固定金额
     */
    收费模式_固定金额(
        InterfDictCode.收费模式, "0", "固定金额"
    ),
    /**
     * 收费模式_费率
     */
    收费模式_费率(
        InterfDictCode.收费模式, "1", "费率"
    ),
    /**
     * 托管指令处理状态_成功
     */
    托管指令处理状态_成功(
        InterfDictCode.托管指令处理状态, "90", "成功"
    ),
    /**
     * 托管指令处理状态_失败
     */
    托管指令处理状态_失败(
        InterfDictCode.托管指令处理状态, "91", "失败"
    ),
    /**
     * 托管指令处理状态_查询无记录
     */
    托管指令处理状态_查询无记录(
        InterfDictCode.托管指令处理状态, "93", "查询无记录"
    ),
    /**
     * 托管指令处理状态_处理中
     */
    托管指令处理状态_处理中(
        InterfDictCode.托管指令处理状态, "80", "处理中"
    ),
    /**
     * 零售客户等级_普通
     */
    零售客户等级_普通(
        InterfDictCode.零售客户等级, "1", "普通"
    ),
    /**
     * 零售客户等级_财富投资
     */
    零售客户等级_财富投资(
        InterfDictCode.零售客户等级, "2", "财富投资"
    ),
    /**
     * 零售客户等级_私人银行
     */
    零售客户等级_私人银行(
        InterfDictCode.零售客户等级, "3", "私人银行"
    );

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private BaseDictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    InterfDictCodeList(BaseDictCode dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.setCodeName(codeName);
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
    public static InterfDictCodeList from(BaseDictCode dictCode, String value) {
        InterfDictCodeList[] values = InterfDictCodeList.values();
        for (InterfDictCodeList code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode().equals(dictCode)) {
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
    public static List<SelectOptionDto> fromDictCode(BaseDictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        InterfDictCodeList[] values = InterfDictCodeList.values();
        for (InterfDictCodeList code : values) {
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
    public static List<String> valuesfromDictCode(BaseDictCode dictCode) {
        List<String> result = new ArrayList<>();
        InterfDictCodeList[] values = InterfDictCodeList.values();
        for (InterfDictCodeList code : values) {
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
    @Override
    public BaseDictCode getDictCode() {
        return dictCode;
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
     * 获取 codeName
     *
     * @return codeName.
     */
    @Override
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

    /**
     * 设置 codeName
     *
     * @param codeName codeName
     */
    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}

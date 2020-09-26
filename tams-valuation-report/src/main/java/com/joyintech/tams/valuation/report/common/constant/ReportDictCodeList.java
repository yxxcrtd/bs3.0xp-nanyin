package com.joyintech.tams.valuation.report.common.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

/**
 * 数据字典明细枚举
 *
 * @author chenzhuo
 * @version 1.0 2016年11月12日
 * @see ReportDictCodeList
 * @since 1.0
 */
public enum ReportDictCodeList implements BaseDictCodeList {
    /**
     * 模块名称_共通参数
     */
    模块名称_共通参数(ReportDictCode.模块名称, "01", "共通参数"),
    /**
     * 机构类型_总行
     */
    机构类型_总行(ReportDictCode.机构类型, "01", "总行"),
    /**
     * 机构类型_分行
     */
    机构类型_分行(ReportDictCode.机构类型, "02", "分行"),
    /**
     * 机构类型_分支
     */
    机构类型_分支(ReportDictCode.机构类型, "03", "分支机构"),
    /**
     * 共通业务类型_机构管理
     */
    共通业务类型_机构管理(ReportDictCode.共通业务类型, "125", "机构管理"),
    /**
     * 共通业务类型_职能部门管理
     */
    共通业务类型_职能部门管理(ReportDictCode.共通业务类型, "126", "职能部门管理"),
    /**
     * 共通业务类型_用户管理
     */
    共通业务类型_用户管理(ReportDictCode.共通业务类型, "127", "用户管理"),
    /**
     * 共通业务类型_角色权限管理
     */
    共通业务类型_角色权限管理(ReportDictCode.共通业务类型, "128", "角色权限管理"),
    /**
     * 共通业务类型_角色分配
     */
    共通业务类型_角色分配(ReportDictCode.共通业务类型, "130", "角色分配"),
    /**
     * 是否 是
     */
    是否_是(ReportDictCode.是否, "01", "是"),
    /**
     * 是否 否
     */
    是否_否(ReportDictCode.是否, "02", "否"),
    /**
     * 子系统_系统
     */
    子系统_系统(ReportDictCode.子系统, "02", "系统"),
    /**
     * 子系统_接口
     */
    子系统_接口(ReportDictCode.子系统, "07", "接口"),
//    /**
//     * 流程状态_新建
//     */
//    流程状态_新建(ReportDictCode.流程状态, "01", "新建"),
//    /**
//     * 流程状态_流程中
//     */
//    流程状态_流程中(ReportDictCode.流程状态, "02", "流程中"),
//    /**
//     * 流程状态_已退回
//     */
//    流程状态_已退回(ReportDictCode.流程状态, "03", "已退回"),
//    /**
//     * 流程状态_流程结束
//     */
//    流程状态_流程结束(ReportDictCode.流程状态, "04", "流程结束"),
    /**
     * 报送状态_未报送
     */
    报送状态_未报送(ReportDictCode.报送状态, "01", "未报送"),
    /**
     * 报送状态_接口报送
     */
    报送状态_接口报送(ReportDictCode.报送状态, "02", "接口报送"),
    /**
     * 报送状态_手工报送
     */
    报送状态_手工报送(ReportDictCode.报送状态, "03", "手工报送"),
    /**
     * 募集期认购
     */
    业务种类_募集期认购(ReportDictCode.业务种类,"01","募集期认购"),

    /**
     * 存续期申购
     */
    业务种类_存续期申购(ReportDictCode.业务种类,"02","存续期申购"),

    /**
     * 存续期赎回
     */
    业务种类_存续期赎回(ReportDictCode.业务种类,"03","存续期赎回"),

    /**
     * 收益分配
     */
    业务种类_收益分配(ReportDictCode.业务种类,"04","收益分配"),

    /**
     * 收益再投资
     */
    业务种类_收益再投资(ReportDictCode.业务种类,"05","收益再投资"),

    /**
     * 质押融资首期
     */
    业务种类_质押融资首期(ReportDictCode.业务种类,"06","质押融资首期"),

    /**
     * 质押融资到期
     */
    业务种类_质押融资到期(ReportDictCode.业务种类,"07","质押融资到期"),

    /**
     * 冻结
     */
    业务种类_冻结(ReportDictCode.业务种类,"08","冻结"),

    /**
     * 解冻
     */
    业务种类_解冻(ReportDictCode.业务种类,"09","解冻"),

    /**
     * 交易过户转入
     */
    业务种类_交易过户转入(ReportDictCode.业务种类,"10","交易过户转入"),

    /**
     * 交易过户转出
     */
    业务种类_交易过户转出(ReportDictCode.业务种类,"11","交易过户转出"),

    /**
     * 非交易过户转入
     */
    业务种类_非交易过户转入(ReportDictCode.业务种类,"12","非交易过户转入"),

    /**
     * 非交易过户转出
     */
    业务种类_非交易过户转出(ReportDictCode.业务种类,"13","非交易过户转出"),

    /**
     * 份额强增
     */
    业务种类_份额强增(ReportDictCode.业务种类,"14","份额强增"),

    /**
     * 份额强减
     */
    业务种类_份额强减(ReportDictCode.业务种类,"15","份额强减"),

    /**
     * 到期兑付
     */
    业务种类_到期兑付(ReportDictCode.业务种类,"16","到期兑付"),

    /**
     * 存款类资产
     */
    业务分类_存款类资产(ReportDictCode.业务分类, "01", "存款类资产" ),
    /**
     * 债券类资产
     */
    业务分类_债券类资产(ReportDictCode.业务分类, "02", "债券类资产" ),
    /**
     * 项目类资产
     */
    业务分类_项目类资产(ReportDictCode.业务分类, "03", "项目类资产" ),
    /**
     * 计划类资产
     */
    业务分类_计划类资产(ReportDictCode.业务分类, "04", "计划类资产" ),
    /**
     * 基金类资产
     */
    业务分类_基金类资产(ReportDictCode.业务分类, "05", "基金类资产" ),
    /**
     * 债券质押式回购
     */
    业务分类_债券质押式回购(ReportDictCode.业务分类, "06", "债券质押式回购" ),
    /**
     * 拆借交易
     */
    业务分类_拆借交易(ReportDictCode.业务分类, "07", "拆借交易" ),
    /**
     * 外汇交易
     */
    业务分类_外汇交易(ReportDictCode.业务分类, "08", "外汇交易" ),
    /**
     * 利率互换
     */
    业务分类_利率互换(ReportDictCode.业务分类, "09", "利率互换" ),
    /**
     * 其他
     */
    业务分类_其他(ReportDictCode.业务分类, "10", "其他" ),
    /**
     * 购买地_其他国家或地区
     */
    购买地_其他国家或地区(ReportDictCode.购买地,"900000","其他国家或地区"),
    /**
     * 人民币(CNY)
     */
    币种_人民币(ReportDictCode.币种, "01", "人民币(CNY)"),
    /**
     * 港元(HKD)
     */
    币种_港元(ReportDictCode.币种, "02", "港元(HKD)"),
    /**
     * 美元(USD)
     */
    币种_美元(ReportDictCode.币种, "03", "美元(USD)"),
    /**
     * 欧元(EUR)
     */
    币种_欧元(ReportDictCode.币种, "04", "欧元(EUR)"),
    /**
     * 日元(JPY)
     */
    币种_日元(ReportDictCode.币种, "05", "日元(JPY)"),
    /**
     * 澳门元(MOP)
     */
    币种_澳门元(ReportDictCode.币种, "06", "澳门元(MOP)"),
    /**
     * 瑞士法郎(CHF)
     */
    币种_瑞士法郎(ReportDictCode.币种, "07", "瑞士法郎(CHF)"),
    /**
     * 英镑(GBP)
     */
    币种_英镑(ReportDictCode.币种, "08", "英镑(GBP)"),
    /**
     * 俄罗斯卢布(RUB)
     */
    币种_俄罗斯卢布(ReportDictCode.币种, "09", "俄罗斯卢布(RUR)"),
    /**
     * 加拿大元(CAD)
     */
    币种_加拿大元(ReportDictCode.币种, "10", "加拿大元(CAD)"),
    /**
     * 丹麦克朗(DKK)
     */
    币种_丹麦克朗(ReportDictCode.币种, "11", "丹麦克朗(DKK)"),
    /**
     * 澳大利亚元(AUD)
     */
    币种_澳大利亚元(ReportDictCode.币种, "12", "澳大利亚元(AUD)"),
    /**
     * 瑞典克朗(SEK)
     */
    币种_瑞典克朗(ReportDictCode.币种, "13", "瑞典克朗(SEK)"),
    /**
     * 新加坡元（SGD）
     */
    币种_新加坡元(ReportDictCode.币种, "14", "新加坡元（SGD）"),
    /**
     * 新台币（TWD）
     */
    币种_新台币(ReportDictCode.币种, "15", "新台币（TWD）"),
    /**
     * 币种_其他
     */
    币种_其他(ReportDictCode.币种, "99", "其他"),
    /**
     * 中债币种_人民币
     */
    中债币种_人民币(ReportDictCode.中债币种, "CNY", "人民币"),
    /**
     * 报表名称_个人产品申报登记
     */
    报表名称_个人产品申报登记(ReportDictCode.报表名称, "01", "个人产品申报登记" ),
    /**
     * 报表名称_个人产品发行登记
     */
    报表名称_个人产品发行登记(ReportDictCode.报表名称, "02", "个人产品发行登记" ),
    /**
     * 报表名称_机构和私人银行产品发行登记
     */
    报表名称_机构和私人银行产品发行登记(ReportDictCode.报表名称, "03", "机构和私人银行产品发行登记" ),
    /**
     * 报表名称_募集期总量登记
     */
    报表名称_募集期总量登记(ReportDictCode.报表名称, "04", "募集期总量登记" ),
    /**
     * 报表名称_产品存续期登记
     */
    报表名称_产品存续期登记(ReportDictCode.报表名称, "05", "产品存续期登记" ),
    /**
     * 报表名称_资产要素登记
     */
    报表名称_资产要素登记(ReportDictCode.报表名称, "06", "资产要素登记" ),
    /**
     * 报表名称_交易信息登记
     */
    报表名称_交易信息登记(ReportDictCode.报表名称, "07", "交易信息登记" ),
    /**
     * 报表名称_资产估值信息登记
     */
    报表名称_资产估值信息登记(ReportDictCode.报表名称, "08", "资产估值信息登记" ),
    /**
     * 报表名称_产品终止登记
     */
    报表名称_产品终止登记(ReportDictCode.报表名称, "09", "产品终止登记" ),
    /**
     * 报表名称_从业人员登记
     */
    报表名称_从业人员登记(ReportDictCode.报表名称, "10", "从业人员登记" ),
    /**
     * 报表名称_银行月度统计表
     */
    报表名称_银行月度统计表(ReportDictCode.报表名称, "11", "银行月度统计表" ),
    /**
     * 报表名称_资管计划底层资产信息
     */
    报表名称_资管计划底层资产信息(ReportDictCode.报表名称, "12", "资管计划底层资产信息" ),
    /**
     * 分类总量信息登记
     */
    报表名称_分类总量信息登记(ReportDictCode.报表名称,"13","分类总量信息登记"),
    /**
     * 报表名称_投资者身份信息登记
     */
    报表名称_投资者身份信息登记(ReportDictCode.报表名称,"14","投资者身份信息登记"),
    /**
     * 报表名称_投资者持有信息登记
     */
    报表名称_投资者持有信息登记(ReportDictCode.报表名称,"16","投资者持有信息登记"),
    /**
     * 报表名称_权益人业务明细信息登记
     */
    报表名称_业务明细信息登记(ReportDictCode.报表名称,"15","业务明细信息登记"),
    /**
     * 报送数据状态_新建
     */
    报送数据状态_新建(ReportDictCode.报送数据状态,"01","新建"),
    /**
     * 报送数据状态_修改
     */
    报送数据状态_修改(ReportDictCode.报送数据状态,"02","修改"),
    /**
     * 报送数据状态_删除
     */
    报送数据状态_删除(ReportDictCode.报送数据状态,"03","删除"),
    /**
     * 维护数据状态_有效
     */
    维护数据状态_有效(ReportDictCode.维护数据状态,"E","有效"),
    /**
     * 维护数据状态_删除
     */
    维护数据状态_删除(ReportDictCode.维护数据状态,"D","删除"),
    /**
     * 维护数据状态_待处理
     */
    维护数据状态_待处理(ReportDictCode.维护数据状态,"A","待处理"),
    /**
     * 维护数据状态_待删除
     */
    维护数据状态_待删除(ReportDictCode.维护数据状态,"P","待删除"),
    /**
     * 接口执行状态_成功
     */
    接口执行状态_成功(ReportDictCode.接口执行状态, "01", "成功" ),
    /**
     * 接口执行状态_失败
     */
    接口执行状态_失败(ReportDictCode.接口执行状态, "02", "失败" ),
    /**
     * 接口执行状态_部分成功
     */
    接口执行状态_部分成功(ReportDictCode.接口执行状态, "03", "部分成功" ),
    /**
     * 预警标示_预警
     */
    预警标示_预警(ReportDictCode.预警标示, "0", "预警" ),
    /**
     * 预警标示_不预警
     */
    预警标示_不预警(ReportDictCode.预警标示, "1", "不预警" ),
    /**
     * 接口类型_开始
     */
    接口类型_开始(ReportDictCode.接口类型, "00", "开始" ),
    /**
     * 接口类型_读文件
     */
    接口类型_读文件(ReportDictCode.接口类型, "01", "读文件" ),
    /**
     * 接口类型_文件转置
     */
    接口类型_文件转置(ReportDictCode.接口类型, "02", "文件转置" ),
    /**
     * 接口类型_上传ftp
     */
    接口类型_上传ftp(ReportDictCode.接口类型, "03", "上传ftp" ),
    /**
     * 接口类型_ftp下载
     */
    接口类型_ftp下载(ReportDictCode.接口类型, "04", "ftp下载 " ),
    /**
     * 接口类型_发送联机交易
     */
    接口类型_发送联机交易(ReportDictCode.接口类型, "05", "发送联机交易" ),
    /**
     * 接口类型_登陆接口表
     */
    接口类型_登陆接口表(ReportDictCode.接口类型, "06", "登陆接口表" ),
    /**
     * 接口类型_登陆业务表
     */
    接口类型_登陆业务表(ReportDictCode.接口类型, "07", "登陆业务表" ),
    /**
     * 接口类型_备份文件
     */
    接口类型_备份文件(ReportDictCode.接口类型, "08", "备份文件" ),
    /**
     * 接口类型_检索数据
     */
    接口类型_检索数据(ReportDictCode.接口类型, "09", "检索数据" ),
    /**
     * 接口类型_写文件
     */
    接口类型_写文件(ReportDictCode.接口类型, "10", "写文件" ),
    /**
     * 接口类型_启动流程
     */
    接口类型_启动流程(ReportDictCode.接口类型, "11", "启动流程" ),
    /**
     * 接口类型_调用他系统service
     */
    接口类型_调用他系统service(ReportDictCode.接口类型, "12", "调用他系统service" ),
    /**
     * 接口类型_结束
     */
    接口类型_结束(ReportDictCode.接口类型, "99", "结束" ),
    /**
     * 接口系统ID_中债直连
     */
    接口系统ID_中债直连(ReportDictCode.接口系统ID, "11", "中债直连"),
    /**
     * 接口系统ID_直连结果分析
     */
    接口系统ID_直连结果分析(ReportDictCode.接口系统ID, "12", "直连结果分析"),
    /**
     * 接口系统ID_获取产品登记编码
     */
    接口系统ID_获取产品登记编码(ReportDictCode.接口系统ID, "13", "获取产品登记编码"),
    /**
     * 接口类型_报文转化
     */
    接口类型_报文转化(ReportDictCode.接口类型, "13", "报文转化"),
    /**
     * 反馈状态_发送成功
     */
    反馈状态_发送成功(ReportDictCode.反馈状态, "01", "发送成功"),
    /**
     * 审核文件下载失败
     */
    反馈状态_审核文件下载失败(ReportDictCode.反馈状态, "05", "审核文件下载失败"),
    /**
     * 反馈状态_发送失败
     */
    反馈状态_发送失败(ReportDictCode.反馈状态, "02", "发送失败"),

    /**
     * 反馈状态_审核通过
     */
    反馈状态_审核通过(ReportDictCode.反馈状态, "03", "审核通过"),

    /**
     * 反馈状态_审核退回
     */
    反馈状态_审核退回(ReportDictCode.反馈状态, "04", "审核退回"),
    /**
     * 数据来源_新建
     */
    数据来源_系统新建(ReportDictCode.数据来源, "01" , "系统新建" ),
    /**
     * 数据来源_EXCEL导入
     */
    数据来源_EXCEL导入(ReportDictCode.数据来源, "02" , "EXCEL导入" ),
    /**
     * 数据来源_资管系统
     */
    数据来源_资管系统(ReportDictCode.数据来源, "11" , "资管系统" ),
    /**
     * 数据来源_销售系统
     */
    数据来源_销售系统(ReportDictCode.数据来源, "12" , "销售系统" ),
    /**
     * 数据来源_万得系统
     */
    数据来源_万得系统(ReportDictCode.数据来源, "13" , "万得系统" ),
    /**
     * 数据来源_客户关系管理系统
     */
    数据来源_客户关系管理系统(ReportDictCode.数据来源, "14" , "客户关系管理系统" ),
    /**
     * 数据来源_核心系统
     */
    数据来源_核心系统(ReportDictCode.数据来源, "15" , "核心系统" ),
    /**
     * 批处理任务状态_未执行
     */
    批处理任务状态_未执行(ReportDictCode.批处理任务状态, "UNR", "未执行"),
    /**
     * 批处理任务状态_执行中
     */
    批处理任务状态_执行中(ReportDictCode.批处理任务状态, "RUN", "执行中"),
    /**
     * 批处理任务状态_执行结束
     */
    批处理任务状态_执行结束(ReportDictCode.批处理任务状态, "END", "执行结束"),
    /**
     * 批处理任务状态_中断
     */
    批处理任务状态_中断(ReportDictCode.批处理任务状态, "BRK", "中断"),
    /**
     * 批处理执行结果_正常结束
     */
    批处理执行结果_正常结束(ReportDictCode.批处理执行结果, "NOR", "正常结束"),
    /**
     * 批处理执行结果_异常结束
     */
    批处理执行结果_异常结束(ReportDictCode.批处理执行结果, "EXP", "异常结束"),
    /**
     * 报送时点类型_每周
     */
    报送时点类型_每周(ReportDictCode.报送时点类型, "01", "每周 "),
    /**
     * 报送时点类型_每月
     */
    报送时点类型_每月(ReportDictCode.报送时点类型, "02", "每月"),
    /**
     *  报送时点类型_固定格式
     */
    报送时点类型_固定格式(ReportDictCode.报送时点类型, "03", "固定格式"),
    /**
     *  交易市场_中国法定节假日
     */
    交易市场_中国法定节假日(ReportDictCode.交易市场, "01", "中国法定节假日"),
    /**
     *  推送方式_前
     */
    推送方式_前(ReportDictCode.推送方式, "01", "前"),
    /**
     *  推送方式_后
     */
    推送方式_后(ReportDictCode.推送方式, "02", "后"),
    /**
     * 报送反馈状态_中债退回
     */
    报送反馈状态_中债退回(ReportDictCode.报送反馈状态,"04","中债退回"),
    /**
     * 权益人数据类型_新增
     */
    权益人数据类型_新增(ReportDictCode.权益人数据类型,"01","新增"),
    /**
     * 权益人数据类型_识别标识变更
     */
    权益人数据类型_识别标识变更(ReportDictCode.权益人数据类型,"02","识别标识变更"),
    /**
     * 权益人数据类型_重要信息变更
     */
    权益人数据类型_重要信息变更增(ReportDictCode.权益人数据类型,"03","重要信息变更"),
    /**
     * 权益人数据类型_其他信息变更
     */
    权益人数据类型_其他信息变更(ReportDictCode.权益人数据类型,"04","其他信息变更"),
    /**
     * 产品风险等级_一级
     */
    产品风险等级_一级(ReportDictCode.产品风险等级, "01" , "一级（低）"),
    /**
     * 产品风险等级_二级
     */
    产品风险等级_二级(ReportDictCode.产品风险等级, "02" , "二级（中低）"),
    /**
     * 产品风险等级_三级
     */
    产品风险等级_三级(ReportDictCode.产品风险等级, "03" , "三级（中）"),
    /**
     * 产品风险等级_四级
     */
    产品风险等级_四级(ReportDictCode.产品风险等级, "04" , "四级（中高）"),
    /**
     * 产品风险等级_五级
     */
    产品风险等级_五级(ReportDictCode.产品风险等级, "05" , "五级（高）"),
    /**
     * 投资者风险偏好_保守型
     */
    投资者风险偏好_保守型(ReportDictCode.投资者风险偏好, "01" , "保守型"),
    /**
     * 投资者风险偏好_稳健型
     */
    投资者风险偏好_稳健型(ReportDictCode.投资者风险偏好, "02" , "稳健型"),
    /**
     * 投资者风险偏好_平衡型
     */
    投资者风险偏好_平衡型(ReportDictCode.投资者风险偏好, "03" , "平衡型"),
    /**
     * 投资者风险偏好_成长型
     */
    投资者风险偏好_成长型(ReportDictCode.投资者风险偏好, "04" , "成长型"),
    /**
     * 投资者风险偏好_进取型
     */
    投资者风险偏好_进取型(ReportDictCode.投资者风险偏好, "05" , "进取型"),
    /**
     * 持有信息数据来源_业务明细
     */
    持有信息数据来源_业务明细(ReportDictCode.持有信息数据来源, "01" , "业务明细"),
    /**
     * 持有信息数据来源_持有信息
     */
    持有信息数据来源_持有信息(ReportDictCode.持有信息数据来源, "02" , "持有信息"),
    /**
     * 检查结果_检查通过
     */
    检查结果_检查通过(ReportDictCode.检查结果,"01","检查通过"),
    /**
     * 检查结果_检查不通过
     */
    检查结果_检查不通过(ReportDictCode.检查结果,"02","检查不通过"),

    /**
     * 上游接口文件读取处理状态_处理中
     */
    上游接口文件读取处理状态_处理中(ReportDictCode.上游接口文件读取处理状态, "01", "处理中"),
    /**
     * 上游接口文件读取处理状态_正常结束
     */
    上游接口文件读取处理状态_正常结束(ReportDictCode.上游接口文件读取处理状态, "02", "正常结束"),
    /**
     * 上游接口文件读取处理状态_异常结束
     */
    上游接口文件读取处理状态_异常结束(ReportDictCode.上游接口文件读取处理状态, "03", "异常结束"),
    /**
     * 上游接口文件类型_投资者身份信息
     */
    上游接口文件类型_投资者身份信息(ReportDictCode.上游接口文件类型, "01", "投资者身份信息"),
    /**
     * 上游接口文件类型_投资者持有信息
     */
    上游接口文件类型_投资者持有信息(ReportDictCode.上游接口文件类型, "02", "投资者持有信息"),
    /**
     * 上游接口文件类型_汇率行情信息
     */
    上游接口文件类型_汇率行情信息(ReportDictCode.上游接口文件类型, "03", "汇率行情信息"),
    /**
     * 上游接口文件类型_节假日信息
     */
    上游接口文件类型_节假日信息(ReportDictCode.上游接口文件类型, "04", "节假日信息"),
    /**
     * 上游接口文件类型_产品基本要素信息
     */
    上游接口文件类型_产品基本要素信息(ReportDictCode.上游接口文件类型, "05", "产品基本要素信息"),
    /**
     * 权益人境内外标识_境内
     */
    权益人境内外标识_境内(ReportDictCode.权益人境内外标识, "01", "境内"),
    /**
     * 权益人境内外标识_境外
     */
    权益人境内外标识_境外(ReportDictCode.权益人境内外标识, "02", "境外"),
    /**
     * 确认状态_已确认
     */
    确认状态_已确认(ReportDictCode.确认状态, "01", "已确认"),
    /**
     * 确认状态_未确认
     */
    确认状态_未确认(ReportDictCode.确认状态, "02", "未确认"),   
    /**
     * 披露状态_已披露
     */
	披露状态_已披露(ReportDictCode.披露状态, "01", "已披露"),
    /**
     * 披露状态_未披露
     */
	披露状态_未披露(ReportDictCode.披露状态, "02", "未披露"),
	/**
     * 信息披露状态_已披露
     */
	信息披露状态_已披露(ReportDictCode.信息披露状态, "01", "已披露"),
	/**
     * 信息披露状态_未披露
     */
	信息披露状态_未披露(ReportDictCode.信息披露状态, "02", "未披露"),
	/**
     * 报表启用状态_已启用
     */
	报表启用状态_已启用(ReportDictCode.披露状态, "Y", "已启用"),
	/**
     * 报表启用状态_未启用
     */
	报表启用状态_未启用(ReportDictCode.披露状态, "N", "未启用"),
    /**
     * 信息披露业务类型
     */
    理财资管业务类型_报表披露管理(
        ReportDictCode.共通业务类型, "V02", "报表披露管理"),
    理财资管业务类型_报表确认管理(
            ReportDictCode.共通业务类型, "V01", "报表确认管理"),
    /**
     * 循环类型_日历
     */
    循环类型_日历(ReportDictCode.循环类型, "CALENDAR", "日历"),
    /**
     * 循环类型_产品成立
     */
    循环类型_产品成立(ReportDictCode.循环类型, "PRODUCTEST", "产品成立"), 
    /**
     * 循环类型_产品到期
     */
    循环类型_产品到期(ReportDictCode.循环类型, "PRODUCTEXP", "产品到期"),
    /**
     * 循环类型_产品开发
     */
    循环类型_产品开发(ReportDictCode.循环类型, "PRODUCTOPEN", "产品开放"),
    /**
     * 运行状态_待运行
     */
    运行状态_待运行(ReportDictCode.运行状态, "WAIT", "待运行"), 
    /**
     * 运行状态_正在运行
     */
    运行状态_正在运行(ReportDictCode.运行状态, "RUN", "正在运行"), 
    
    报表类型_日报(ReportDictCode.报表类型, "01", "日报"), 
    
    报表类型_周报(ReportDictCode.报表类型, "02", "周报"), 
    
    报表类型_月报(ReportDictCode.报表类型, "03", "月报"), 
    
    报表类型_季报(ReportDictCode.报表类型, "04", "季报"), 
    
    报表类型_半年报(ReportDictCode.报表类型, "05", "半年报"),
    
    报表类型_年报(ReportDictCode.报表类型, "06", "年报"), 
    /**
     * 批处理信息披露状态_未执行
     */
    批处理信息披露状态_未执行(ReportDictCode.批处理信息披露状态, "01", "未执行"), 
    /**
     * 批处理信息披露状态_已执行
     */
    批处理信息披露状态_已执行(ReportDictCode.批处理信息披露状态, "02", "已执行"), 
    /**
     * 批处理信息披露状态_已确认
     */
    批处理信息披露状态_已确认(ReportDictCode.批处理信息披露状态, "03", "已确认"),
    /**
     * 批处理信息披露状态_未披露
     */
    批处理信息披露状态_未披露(ReportDictCode.批处理信息披露状态, "04", "未披露"), 
    /**
     * 批处理信息披露状态_已生成
     */
    批处理信息披露状态_已生成(ReportDictCode.批处理信息披露状态, "05", "已生成"),
    /**
     * 信息披露报表类型_报表
     */
    信息披露报表类型_报表(ReportDictCode.批处理信息披露状态, "01", "报表"),
    /**
     * 信息披露报表类型_集合
     */
    信息披露报表类型_集合(ReportDictCode.批处理信息披露状态, "02", "集合"), 
    
    
    披露渠道_官网(ReportDictCode.披露渠道,"01","官网"),
    披露渠道_点对点(ReportDictCode.披露渠道,"02","点对点"),
    披露渠道_其他(ReportDictCode.披露渠道,"03","其他"),
    
    执行结果_成功(ReportDictCode.执行结果,"01","成功"),
    执行结果_失败(ReportDictCode.执行结果,"02","失败"),
    执行结果_部分成功(ReportDictCode.执行结果,"03","部分成功"),
    
    /**
     * 报表类别__销售文件信息披露
     */
    报表类别_销售文件信息披露(ReportDictCode.报表类别, "01", "销售文件信息披露"),
    /**
     * 报表类别__成立公告
     */
    报表类别_成立公告(ReportDictCode.报表类别, "02", "成立公告"),
    /**
     * 报表类别__重大事项公告
     */
    报表类别_重大事项公告(ReportDictCode.报表类别, "03", "重大事项公告"),
    /**
     * 报表类别__开放式产品净值披露
     */
    报表类别_开放式产品净值披露(ReportDictCode.报表类别, "04", "开放式产品净值披露"),
    /**
     * 报表类别__封闭式产品净值披露
     */
    报表类别_封闭式产品净值披露(ReportDictCode.报表类别, "05", "封闭式产品净值披露"),
    /**
     * 报表类别__临时性信息披露
     */
    报表类别_临时性信息披露(ReportDictCode.报表类别, "06", "临时性信息披露"),
    /**
     * 报表类别__到期公告
     */
    报表类别_到期公告(ReportDictCode.报表类别, "07", "到期公告"),
    /**
     * 报表类别__理财产品定期公告
     */
    报表类别_理财产品定期公告(ReportDictCode.报表类别, "08", "理财产品定期公告"),
    /**
     * 报表类别__理财业务报告
     */
    报表类别_理财业务报告(ReportDictCode.报表类别, "09", "理财业务报告"),
    /**
     * 报表类别__其他公告
     */
    报表类别_其他公告(ReportDictCode.报表类别, "10", "其他公告"),

    披露频率_每工作日(ReportDictCode.披露频率,"A01","每工作日"),
    披露频率_每自然日(ReportDictCode.披露频率,"A02","每自然日"),
    披露频率_每周(ReportDictCode.披露频率,"A03","每周"),
    披露频率_每月(ReportDictCode.披露频率,"A04","每月"),
    披露频率_每季度(ReportDictCode.披露频率,"A05","每季度"),
    披露频率_每半年(ReportDictCode.披露频率,"A06","每半年"),
    披露频率_每年(ReportDictCode.披露频率,"A07","每年"),
    披露频率_每开放周期(ReportDictCode.披露频率,"B01","每开放周期"),
    披露频率_业务发生时(ReportDictCode.披露频率,"C02","业务发生时"),
    
    /**
     * 暂不实现
     * 披露频率_指定日期(ReportDictCode.披露频率,"C01","指定日期"),
     */

    基准日_平台日(ReportDictCode.基准日,"01","平台日"),
    基准日_募集起始日(ReportDictCode.基准日,"RAISE_STRDATE","募集起始日"),
    基准日_实际到期日(ReportDictCode.基准日,"ACT_MDATE","实际到期日"),
    基准日_开放周期结束日(ReportDictCode.基准日,"04","开放周期结束日"),
    //add by longhao 2018/12/12 start
    基准日_起息日(ReportDictCode.基准日,"05","起息日"),
    基准日_到期日(ReportDictCode.基准日,"06","到期日"),
    基准日_成立日(ReportDictCode.基准日,"07","成立日"),
    基准日_周期开始日(ReportDictCode.基准日,"08","周期开始日"),
    基准日_周期结束日(ReportDictCode.基准日,"09","周期结束日"),
    基准日_清盘日(ReportDictCode.基准日,"10","清盘日"),
    基准日_募集结束日(ReportDictCode.基准日,"11","募集结束日"),
    
    
    

    日期类型_工作日(ReportDictCode.日期类型,"01","工作日"),
    日期类型_自然日(ReportDictCode.日期类型,"02","自然日"),
    
    报表数据类型_时点报表(ReportDictCode.报表数据类型,"01","时点报表"),
    报表数据类型_区间报表(ReportDictCode.报表数据类型,"02","区间报表"),

    取数范围_上一工作日(ReportDictCode.取数范围,"01","上一工作日"),
    取数范围_上一自然日(ReportDictCode.取数范围,"02","上一自然日"),
    取数范围_上周(ReportDictCode.取数范围,"03","上周"),
    取数范围_本周(ReportDictCode.取数范围,"04","本周"),
    取数范围_上月(ReportDictCode.取数范围,"05","上月"),
    取数范围_上月末最后一自然日(ReportDictCode.取数范围,"06","上月末最后一自然日"),
    取数范围_上月末最后一工作日(ReportDictCode.取数范围,"07","上月末最后一工作日"),
    取数范围_本月(ReportDictCode.取数范围,"08","本月"),
    取数范围_本月末最后一自然日(ReportDictCode.取数范围,"09","本月末最后一自然日"),
    取数范围_本月末最后一工作日(ReportDictCode.取数范围,"10","本月末最后一工作日"),
    取数范围_上季度(ReportDictCode.取数范围,"11","上季度"),
    取数范围_上半年(ReportDictCode.取数范围,"12","上半年"),
    取数范围_上年(ReportDictCode.取数范围,"13","上年"),
    取数范围_近N个自然日(ReportDictCode.取数范围,"14","近N个自然日"),
    取数范围_近N个工作日(ReportDictCode.取数范围,"15","近N个工作日"),

    文件类型_PDF(ReportDictCode.文件类型,"pdf","PDF"),
    文件类型_Excle(ReportDictCode.文件类型,"xlsx","Excle"),
    文件类型_Word(ReportDictCode.文件类型,"docx","Word"),
    
    日志状态_生成成功(ReportDictCode.日志状态,"01","生成成功"),
    日志状态_生成失败(ReportDictCode.日志状态,"02","生成失败"),
    日志状态_上传文件成功(ReportDictCode.日志状态,"03","上传文件成功"),
    日志状态_上传文件失败(ReportDictCode.日志状态,"04","上传文件失败"),
    
    生成时点类型_前(ReportDictCode.生成时点类型,"01","前"),
    生成时点类型_后(ReportDictCode.生成时点类型,"02","后"),
    
    
//add by longhao 2018/12/04  start 
    时点类型_基准日前(ReportDictCode.时点类型,"01","基准日前"),
    时点类型_基准日后(ReportDictCode.时点类型,"01","基准日后"),
    取数开始日_周一(ReportDictCode.取数开始日,"01","周一"),
    取数开始日_周二(ReportDictCode.取数开始日,"02","周二"),
    取数开始日_周三(ReportDictCode.取数开始日,"03","周三"),
    取数开始日_周四(ReportDictCode.取数开始日,"04","周四"),
    取数开始日_周五(ReportDictCode.取数开始日,"05","周五"),
    取数开始日_周六(ReportDictCode.取数开始日,"06","周六"),
    取数开始日_周日(ReportDictCode.取数开始日,"07","周日"),
    
    确认方式_手动(ReportDictCode.确认方式,"01","手动"),
    确认方式_自动(ReportDictCode.确认方式,"02","自动"),
  //add by longhao 2018/12/04  end 
    //add by whb 2018/12/04  start 
    /**
     * 作业规则1_产品收益类型
     */
    作业规则1_产品收益类型(ReportDictCode.作业规则1,"PROFIT_TYPE","产品收益类型"),
    /**
     * 作业规则1_起息日
     */
    作业规则1_起息日(ReportDictCode.作业规则1,"VDATE","起息日"),
    /**
     * 作业规则1_到期日
     */
    作业规则1_到期日(ReportDictCode.作业规则1,"MDATE","到期日"),
    /**
     * 作业规则1_募集方式
     */
    作业规则1_募集方式(ReportDictCode.作业规则1,"RAISE_TYPE","募集方式"),
    /**
     * 作业规则1_发行方式
     */
    作业规则1_发行方式(ReportDictCode.作业规则1,"ISSUE_MODE","发行方式"),
    /**
     * 作业规则1_发行状态
     */
    作业规则1_发行状态(ReportDictCode.作业规则1,"ISSUE_STAUTS","发行状态"),
    /**
     * 作业规则1_清盘日
     */
    作业规则1_清盘日(ReportDictCode.作业规则1,"WINDUP_DATE","清盘日"),
    /**
     * 作业规则1_开放模式
     */
    作业规则1_开放模式(ReportDictCode.作业规则1,"OPEN_MODE","开放模式"),
    /**
     * 作业规则1_是否独立开户
     */
    作业规则1_是否独立开户(ReportDictCode.作业规则1,"IS_INDEPE_ACC","是否独立开户"),
    /**
     * 募集方式_公募
     */
    募集方式_公募(ReportDictCode.募集方式,"1","公募"),
    /**
     * 募集方式_私募
     */
    募集方式_私募(ReportDictCode.募集方式,"2","私募"),
    /**
     * 收益类型_净值
     */
    收益类型_净值(ReportDictCode.收益类型,"01","净值"),
    /**
     * 收益类型_预期收益
     */
    收益类型_预期收益(ReportDictCode.收益类型,"02","预期收益"),
    /**
     * 收益类型_货币
     */
    收益类型_货币(ReportDictCode.收益类型,"03","货币"),
    /**
     * 规则类型_作业表
     */
    规则类型_作业表(ReportDictCode.规则类型,"01","作业表"),
    /**
     * 规则类型_指标值
     */
    规则类型_指标值(ReportDictCode.规则类型,"02","指标值"),
    /**
     * 指标类型_录入型
     */
    指标类型_录入型(ReportDictCode.指标类型,"01","录入型"),
    /**
     * 指标类型_附件型
     */
    指标类型_附件型(ReportDictCode.指标类型,"02","附件型"),
    
  //add by whb 2018/12/04  end 
    
  //add by chendong 2018/12/07  start
    /**
     * 数据状态_有效
     */
    数据状态_有效(ReportDictCode.数据状态,"E","有效"),
    /**
     * 数据状态_删除
     */
    数据状态_删除(ReportDictCode.数据状态,"D","删除"),
    /**
     * 数据状态_待处理
     */
    数据状态_待处理(ReportDictCode.数据状态,"A","待处理"),
    /**
     * 数据状态_待删除
     */
    数据状态_待删除(ReportDictCode.数据状态,"P","待删除"),
    ;
	//add by chendong 2018/12/07  end
    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private ReportDictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    ReportDictCodeList(ReportDictCode dictCode, String value, String codeName) {
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
    public static ReportDictCodeList from(ReportDictCode dictCode, String value) {
        ReportDictCodeList[] values = ReportDictCodeList.values();
        for (ReportDictCodeList code : values) {
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
    public static List<SelectOptionDto> fromDictCode(ReportDictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        ReportDictCodeList[] values = ReportDictCodeList.values();
        for (ReportDictCodeList code : values) {
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
    public static List<String> valuesfromDictCode(ReportDictCode dictCode) {
        List<String> result = new ArrayList<>();
        ReportDictCodeList[] values = ReportDictCodeList.values();
        for (ReportDictCodeList code : values) {
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
    public ReportDictCode getDictCode() {
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

    /**
     * 获取 带有引号的名称
     *
     * @return 带有引号的名称.
     */
    public String getQuotesCodeName() {
        return "“" + codeName + "”";
    }
}

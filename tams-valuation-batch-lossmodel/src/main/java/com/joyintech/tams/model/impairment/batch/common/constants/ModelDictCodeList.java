package com.joyintech.tams.model.impairment.batch.common.constants;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典明细枚举
 *
 * @author chenzhuo
 * @version 1.0 2016年11月12日
 * @see ModelDictCodeList
 * @since 1.0
 */
public enum ModelDictCodeList implements BaseDictCodeList {
    批处理执行结果_警告(ModelDictCode.批处理执行结果,"WARN","警告"),
    批处理执行结果_正常结束(ModelDictCode.批处理执行结果,"NOR","成功"),
    批处理执行结果_异常结束(ModelDictCode.批处理执行结果,"EXP","失败"),

    模型_映射类型_资产分类映射(ModelDictCode.模型_映射类型,"01","资产分类映射"),
    模型_证券品种_债券(ModelDictCode.模型_证券品种,"F01","债券"),
    模型_证券品种_项目类资产(ModelDictCode.模型_证券品种,"F18","项目类资产"),
    模型_证券品种_存款类资产(ModelDictCode.模型_证券品种,"F07","存款类资产"),
    模型_证券细分类_本行发行的同业存单(ModelDictCode.模型_证券细分类,"001","本行发行的同业存单"),
    模型_证券细分类_他行发行的同业存单(ModelDictCode.模型_证券细分类,"002","他行发行的同业存单"),
    模型_证券细分类_国债(ModelDictCode.模型_证券细分类,"003","国债"),
    模型_证券细分类_地方政府债(ModelDictCode.模型_证券细分类,"004","地方政府债"),
    模型_证券细分类_央票(ModelDictCode.模型_证券细分类,"005","央票"),
    模型_证券细分类_政策性金融债(ModelDictCode.模型_证券细分类,"006","政策性金融债"),
    模型_证券细分类_政策性金融次级债(ModelDictCode.模型_证券细分类,"158","政策性金融次级债"),
    模型_证券细分类_铁道债(ModelDictCode.模型_证券细分类,"007","铁道债"),
    模型_证券细分类_金融债(ModelDictCode.模型_证券细分类,"008","金融债"),
    模型_证券细分类_金融机构次级债(ModelDictCode.模型_证券细分类,"009","金融机构次级债"),
    模型_证券细分类_企业债(ModelDictCode.模型_证券细分类,"010","企业债"),
    模型_证券细分类_公司债(ModelDictCode.模型_证券细分类,"012","公司债"),
    模型_证券细分类_交易所私募债(ModelDictCode.模型_证券细分类,"014","交易所私募债"),
    模型_证券细分类_短融(ModelDictCode.模型_证券细分类,"015","短融"),
    模型_证券细分类_超短融(ModelDictCode.模型_证券细分类,"016","超短融"),
    模型_证券细分类_中票(ModelDictCode.模型_证券细分类,"017","中票"),
    模型_证券细分类_银行间私募债(ModelDictCode.模型_证券细分类,"018","银行间私募债"),
    模型_证券细分类_交易商协会ABN(ModelDictCode.模型_证券细分类,"139","交易商协会ABN"),
    模型_证券细分类_银保监会主管ABS(ModelDictCode.模型_证券细分类,"140","银保监会主管ABS"),
    模型_证券细分类_证监会主管ABS(ModelDictCode.模型_证券细分类,"141","证监会主管ABS"),
    模型_证券细分类_外国债券(ModelDictCode.模型_证券细分类,"020","外国债券"),
    模型_证券细分类_理财直接融资工具(ModelDictCode.模型_证券细分类,"021","理财直接融资工具"),
    模型_证券细分类_其他理财直接融资工具(ModelDictCode.模型_证券细分类,"022","其他理财直接融资工具"),
    模型_证券细分类_可交换债券(ModelDictCode.模型_证券细分类,"011","可交换债券"),
    模型_证券细分类_可转债(ModelDictCode.模型_证券细分类,"013","可转债"),
    模型_证券细分类_上市银行优先股(ModelDictCode.模型_证券细分类,"023","上市银行优先股"),
    模型_证券细分类_非上市银行优先股(ModelDictCode.模型_证券细分类,"024","非上市银行优先股"),
    模型_证券细分类_新增可投资资产(ModelDictCode.模型_证券细分类,"08","新增可投资资产"),
    模型_证券细分类_非标准化债权类资产(ModelDictCode.模型_证券细分类,"09","非标准化债权类资产"),
    资产五级分类_优先股(ModelDictCode.资产五级分类,"023","优先股"),
    模型_估值模型名称_现金流贴现法(ModelDictCode.模型_估值模型名称,"01","现金流贴现法"),
    模型_估值模型名称_收益率法(ModelDictCode.模型_估值模型名称,"02","收益率法"),
    模型_估值模型名称_二叉树(ModelDictCode.模型_估值模型名称,"03","二叉树"),
    现金流类型_中间收息(ModelDictCode.现金流类型,"01","中间收息"),
    现金流类型_中间还本(ModelDictCode.现金流类型,"02","中间还本"),
    现金流类型_到期还本(ModelDictCode.现金流类型,"03","到期还本"),
    现金流类型_到期收息(ModelDictCode.现金流类型,"04","到期收息"),
    现金流类型_费用支付(ModelDictCode.现金流类型,"05","费用支付"),
    模型_评级类型_主体评级(ModelDictCode.模型_评级类型,"01","主体评级"),
    模型_评级类型_债项评级(ModelDictCode.模型_评级类型,"02","债项评级"),
    模型_评级类型_融资人(ModelDictCode.模型_评级类型,"03","融资人"),
    模型_评级类型_担保人(ModelDictCode.模型_评级类型,"04","担保人"),
    模型_评级类型_较高值(ModelDictCode.模型_评级类型,"05","较高值"),
    模型_评级类型_较低值(ModelDictCode.模型_评级类型,"06","较低值"),
    模型_评级来源类型_外部(ModelDictCode.模型_评级来源类型,"01","外部"),
    模型_评级来源类型_内部(ModelDictCode.模型_评级来源类型,"02","内部"),
    模型_评级来源类型_系统计算(ModelDictCode.模型_评级来源类型,"03","系统计算"),
    息票品种_附息(ModelDictCode.息票品种,"01","附息"),
    息票品种_贴现(ModelDictCode.息票品种,"02","贴现"),
    息票品种_零息(ModelDictCode.息票品种,"03","零息"),
    息票品种_利随本清(ModelDictCode.息票品种,"04","利随本清"),
    推算方式_起息日向后(ModelDictCode.推算方式,"01","起息日向后"),
    推算方式_到期日向前(ModelDictCode.推算方式,"02","到期日向前"),
    推算方式_首次支付日(ModelDictCode.推算方式,"03","首次支付日"),
    模型_上市状态_上市(ModelDictCode.模型_上市状态,"01","上市"),
    模型_上市状态_未上市(ModelDictCode.模型_上市状态,"02","未上市"),
    模型_市场乘数方法_市净率法(ModelDictCode.模型_市场乘数方法,"01","市净率法"),
    模型_市场乘数方法_市盈率法(ModelDictCode.模型_市场乘数方法,"02","市盈率法"),
    估值来源_专家法_EXP(ModelDictCode.估值来源,"EXP","专家法(EXP)"),
    估值来源_模型法_MOD(ModelDictCode.估值来源,"MOD","模型法(MOD)"),
    计息基础_A_365(ModelDictCode.计息基础,"A/365","A/365"),
    计息基础_A_360(ModelDictCode.计息基础,"A/360","A/360"),
    计息基础_A_A_平均分配(ModelDictCode.计息基础,"A/A_B_COUP","A/A(平均分配)"),
    计息基础_A_A_按实际天数分配(ModelDictCode.计息基础,"A/A_B_DAY","A/A(按实际天数分配)"),
    计息基础_A_365F(ModelDictCode.计息基础,"A/365F","A/365F"),
    计息基础_30_360(ModelDictCode.计息基础,"30/360","30/360"),
    计息基础_A_A(ModelDictCode.计息基础,"A/A","A/A"),
    计息基础_30U_360(ModelDictCode.计息基础,"30U/360","330/360 US"),
    频率_日(ModelDictCode.频率,"01","日"),
    频率_周(ModelDictCode.频率,"02","周"),
    频率_月(ModelDictCode.频率,"03","月"),
    频率_季(ModelDictCode.频率,"04","季"),
    频率_年(ModelDictCode.频率,"05","年"),
    频率_半年(ModelDictCode.频率,"06","半年"),
    频率_到期一次(ModelDictCode.频率,"07","到期一次"),
    频率_不规则(ModelDictCode.频率,"08","不规则"),
    现金流二级类型_本金(ModelDictCode.现金流二级类型,"C01","本金"),
    现金流二级类型_利息(ModelDictCode.现金流二级类型,"C02","利息"),
    现金流二级类型_固定管理费(ModelDictCode.现金流二级类型,"C03","固定管理费"),
    现金流二级类型_浮动管理费(ModelDictCode.现金流二级类型,"C04","浮动管理费"),
    现金流二级类型_托管费(ModelDictCode.现金流二级类型,"C05","托管费"),
    现金流二级类型_销售费(ModelDictCode.现金流二级类型,"C06","销售费"),
    现金流二级类型_税额(ModelDictCode.现金流二级类型,"C07","税额"),
    现金流二级类型_资产推荐费(ModelDictCode.现金流二级类型,"C15","资产推荐费"),
    现金流二级类型_外包服务费(ModelDictCode.现金流二级类型,"C09","外包服务费"),
    现金流二级类型_代销费(ModelDictCode.现金流二级类型,"C10","代销费"),
    现金流二级类型_通道管理费(ModelDictCode.现金流二级类型,"C12","通道管理费"),
    现金流二级类型_通道托管费(ModelDictCode.现金流二级类型,"C13","通道托管费"),
    现金流二级类型_通道其他费(ModelDictCode.现金流二级类型,"C14","通道其他费"),
    现金流二级类型_回补(ModelDictCode.现金流二级类型,"C11","回补"),
    现金流二级类型_罚息(ModelDictCode.现金流二级类型,"C19","罚息"),
    模型_平滑区间_一个月(ModelDictCode.模型_平滑区间,"01","1个月"),
    模型_平滑区间_三个月(ModelDictCode.模型_平滑区间,"02","3个月"),
    评级机构_资管内部(ModelDictCode.评级机构,"98","资管内部"),
    收益曲线类型_SP即期(ModelDictCode.收益曲线类型,"01","SP即期"),
    收益曲线类型_MA远期(ModelDictCode.收益曲线类型,"02","MA远期"),
    收益曲线类型_FS远期的即期(ModelDictCode.收益曲线类型,"03","FS远期的即期"),
    收益曲线类型_FM远期的到期(ModelDictCode.收益曲线类型,"04","FM远期的到期"),
    投资目的_公允价值变动计入当期损益(ModelDictCode.投资目的,"FVTPL","以出售该金融资产为目的"),
    投资目的_摊余成本类(ModelDictCode.投资目的,"AC","以收取该金融资产合同现金流量为目的"),
    投资目的_公允价值变动计入其他综合收益(ModelDictCode.投资目的,"FVOCI","以收取该合同现金流量及出售金融资产为目的"),
    交易方向_产品认购(ModelDictCode.交易方向,"C1","产品认购"),
    交易方向_产品红利转投(ModelDictCode.交易方向,"C10","产品红利转投"),
    交易方向_产品付费(ModelDictCode.交易方向,"C11","产品付费"),
    交易方向_税费缴税(ModelDictCode.交易方向,"C12","税费缴税"),
    交易方向_税费退税(ModelDictCode.交易方向,"C13","税费退税"),
    交易方向_产品到期分红(ModelDictCode.交易方向,"C14","产品到期分红"),
    交易方向_赎回收益确认(ModelDictCode.交易方向,"C15","赎回收益确认"),
    交易方向_产品申购(ModelDictCode.交易方向,"C2","产品申购"),
    交易方向_份额强增(ModelDictCode.交易方向,"C3","份额强增"),
    交易方向_产品赎回(ModelDictCode.交易方向,"C4","产品赎回"),
    交易方向_产品到期(ModelDictCode.交易方向,"C5","产品到期"),
    交易方向_份额强减(ModelDictCode.交易方向,"C6","份额强减"),
    交易方向_中间付息(ModelDictCode.交易方向,"C7","中间付息"),
    交易方向_产品除权登记(ModelDictCode.交易方向,"C8","产品除权登记"),
    交易方向_产品现金分红(ModelDictCode.交易方向,"C9","产品现金分红"),
    交易方向_银证转账(ModelDictCode.交易方向,"HK01","银证转账"),
    交易方向_证银转账(ModelDictCode.交易方向,"HK02","证银转账"),
    交易方向_托管户_上清DVP(ModelDictCode.交易方向,"HK03","托管户-上清DVP"),
    交易方向_托管户_中债DVP(ModelDictCode.交易方向,"HK04","托管户-中债DVP"),
    交易方向_上清DVP_托管户(ModelDictCode.交易方向,"HK05","上清DVP-托管户"),
    交易方向_中债DVP_托管户(ModelDictCode.交易方向,"HK06","中债DVP-托管户"),
    交易方向_上清DVP_中债DVP(ModelDictCode.交易方向,"HK07","上清DVP-中债DVP"),
    交易方向_中债DVP_上清DVP(ModelDictCode.交易方向,"HK08","中债DVP-上清DVP"),
    交易方向_通道划款(ModelDictCode.交易方向,"HK09","通道划款"),
    交易方向_通道回款(ModelDictCode.交易方向,"HK10","通道回款"),
    交易方向_买入(ModelDictCode.交易方向,"T01","买入"),
    交易方向_卖出(ModelDictCode.交易方向,"T02","卖出"),
    交易方向_行权_回售(ModelDictCode.交易方向,"T03","行权-回售"),
    交易方向_申购(ModelDictCode.交易方向,"T04","申购"),
    交易方向_申购确认(ModelDictCode.交易方向,"T05","申购确认"),
    交易方向_赎回(ModelDictCode.交易方向,"T06","赎回"),
    交易方向_到期(ModelDictCode.交易方向,"T07","到期"),
    交易方向_还本(ModelDictCode.交易方向,"T08","还本"),
    交易方向_收息到账(ModelDictCode.交易方向,"T22","收息到账"),
    交易方向_还本到账(ModelDictCode.交易方向,"T23","还本到账"),
    交易方向_赎回到账(ModelDictCode.交易方向,"T24","赎回到账"),
    交易方向_应收款项转入(ModelDictCode.交易方向,"T25","应收款项转入"),
    交易方向_应收款项转出(ModelDictCode.交易方向,"T26","应收款项转出"),
    交易方向_行权_赎回(ModelDictCode.交易方向,"T39","行权-赎回"),
    交易方向_债券派息(ModelDictCode.交易方向,"T27","债券派息"),
    交易方向_资金调配(ModelDictCode.交易方向,"C21","资金调配"),
    模型_减值过程_数据加载(ModelDictCode.模型_减值过程,"01","数据加载"),
    模型_减值过程_减值阶段判断(ModelDictCode.模型_减值过程,"02","减值阶段判断"),
    模型_减值过程_EAD计算(ModelDictCode.模型_减值过程,"03","EAD计算"),
    模型_减值过程_PD计算(ModelDictCode.模型_减值过程,"04","PD计算"),
    模型_减值过程_ECL计算(ModelDictCode.模型_减值过程,"05","ECL计算"),
    模型_减值配置类型_资产大类评级配置(ModelDictCode.模型_减值配置类型,"01","资产大类评级配置"),
    模型_减值配置类型_特殊资产评级配置(ModelDictCode.模型_减值配置类型,"02","特殊资产评级配置"),
    模型_减值配置类型_评级PD配置(ModelDictCode.模型_减值配置类型,"03","评级PD配置"),
    模型_减值配置类型_前瞻因子配置(ModelDictCode.模型_减值配置类型,"04","前瞻因子配置"),
    模型_减值配置类型_资产大类LGD配置(ModelDictCode.模型_减值配置类型,"05","资产大类LGD配置"),
    模型_减值配置类型_特殊资产LGD配置(ModelDictCode.模型_减值配置类型,"06","特殊资产LGD配置"),
    是_否_是(ModelDictCode.是_否,"Y","是"),
    是_否_否(ModelDictCode.是_否,"N","否"),

    //modify by chufengjie I9主产品代码迁移：减值相关 20210603 start
    减值测算方式_外部评估(ModelDictCode.减值测算方式,"01","外部评估"),
    减值测算方式_预期信用损失模型(ModelDictCode.减值测算方式,"02","预期信用损失模型"),
    减值录入方式_按比例减值(ModelDictCode.减值录入方式,"01","按比例减值"),
    共通业务类型_项目类_计划类利率型行情信息(ModelDictCode.共通业务类型,"FINASSET66","项目类_计划类利率型行情信息"),
    //委托类型新增 yjm 20200317 start 
    委托类型_债券一级指令(ModelDictCode.委托类型,"ET01","债券一级指令"),
    委托类型_债券二级指令(ModelDictCode.委托类型,"ET02","债券二级指令"),
    委托类型_立项指令(ModelDictCode.委托类型,"ET03","立项指令"),
    委托类型_质押式回购指令(ModelDictCode.委托类型,"ET04","质押式回购指令"),
    委托类型_存款指令(ModelDictCode.委托类型,"ET05","存款指令"),
    委托类型_拆借指令(ModelDictCode.委托类型,"ET06","拆借指令"),
    委托类型_基金指令(ModelDictCode.委托类型,"ET07","基金指令"),
    委托类型_净值型计划指令(ModelDictCode.委托类型,"ET08","净值型计划指令"),
    委托类型_利率型计划指令(ModelDictCode.委托类型,"ET09","利率型计划指令"),
    委托类型_货币性计划指令(ModelDictCode.委托类型,"ET10","货币性计划指令"),
    委托类型_固收类指令(ModelDictCode.委托类型,"ET11","固收类指令"),
    委托类型_买断式回购指令(ModelDictCode.委托类型,"ET12","买断式回购指令"),
    委托类型_债券借贷指令(ModelDictCode.委托类型,"ET13","债券借贷指令"),
    委托类型_股票指令(ModelDictCode.委托类型,"ET14","股票指令"),
    委托类型_利率互换指令(ModelDictCode.委托类型,"ET15","利率互换指令"),
    委托类型_即期指令(ModelDictCode.委托类型,"ET16","即期指令"),
    委托类型_远期指令(ModelDictCode.委托类型,"ET17","远期指令"),
    委托类型_掉期指令(ModelDictCode.委托类型,"ET18","掉期指令"),
    委托类型_可转债申购指令(ModelDictCode.委托类型,"ET19","可转债申购指令"),
    委托类型_可转债转股指令(ModelDictCode.委托类型,"ET20","可转债转股指令"),
    委托类型_场外期权指令(ModelDictCode.委托类型,"ET21","场外期权指令"),
    平安业务类型_计划类资产_净值型_买入(ModelDictCode.平安业务类型,"151","计划类资产（净值型）买入"),
    平安业务类型_计划类资产_净值型_卖出(ModelDictCode.平安业务类型,"152","计划类资产（净值型）卖出"),
    平安业务类型_计划类资产_货币型_买入(ModelDictCode.平安业务类型,"153","计划类资产（货币型）买入"),
    平安业务类型_计划类资产_货币型_卖出(ModelDictCode.平安业务类型,"154","计划类资产（货币型）卖出"),
    质押标的_无(ModelDictCode.质押标的,"01","无"),
    质押标的_利率债(ModelDictCode.质押标的,"02","利率债"),
    质押标的_信用债(ModelDictCode.质押标的,"03","信用债"),
    质押标的_同业存单(ModelDictCode.质押标的,"04","同业存单"),
    减值处理方式_减值(ModelDictCode.减值处理方式,"01","减值"),
    减值处理方式_转回(ModelDictCode.减值处理方式,"02","转回"),
    减值处理方式_减值使用(ModelDictCode.减值处理方式,"03","减值使用"),
    减值处理方式_清理核销(ModelDictCode.减值处理方式,"04","清理核销"),
    阶段_初始买入时_P1(ModelDictCode.减值阶段,"P1","一阶段"),
    阶段_买入后风险显著提升_P2(ModelDictCode.减值阶段,"P2","二阶段"),
    阶段_买入后出现减值_P3(ModelDictCode.减值阶段,"P3","三阶段"),
    交易方向_清理核销(ModelDictCode.交易方向,"T75","清理核销"),
    交易方向_其他应收款本金清理核销(ModelDictCode.交易方向,"T76","其他应收款本金-清理核销"),
    交易方向_其他应收款利息清理核销(ModelDictCode.交易方向,"T77","其他应收款利息-清理核销"),
    共通业务类型_资产减值清理(ModelDictCode.共通业务类型,"TRD76","资产减值清理"),

    本位币差额处理方式_利息收入(ModelDictCode.本位币差额处理方式,"01","利息收入"),
    本位币差额处理方式_减值准备(ModelDictCode.本位币差额处理方式,"02","减值准备"),

    还本收息冲抵减值准备处理日_理论日(ModelDictCode.还本收息冲抵减值准备处理日,"01","理论日"),
    还本收息冲抵减值准备处理日_资金到账日(ModelDictCode.还本收息冲抵减值准备处理日,"02","资金到账日"),

    卖出冲抵减值准备处理方式_数量占比(ModelDictCode.卖出冲抵减值准备处理方式,"01","数量占比"),
    卖出冲抵减值准备处理方式_金额占比(ModelDictCode.卖出冲抵减值准备处理方式,"02","金额占比"),
    卖出冲抵减值准备处理方式_原金额占比(ModelDictCode.卖出冲抵减值准备处理方式,"03","原金额占比"),

    收息冲抵减值准备处理方式_数量占比(ModelDictCode.收息冲抵减值准备处理方式,"01","数量占比"),
    收息冲抵减值准备处理方式_金额占比(ModelDictCode.收息冲抵减值准备处理方式,"02","金额占比"),
    收息冲抵减值准备处理方式_原金额占比(ModelDictCode.收息冲抵减值准备处理方式,"03","原金额占比"),

    减值生成方式_计提批处理(ModelDictCode.减值生成方式,"01","计提批处理"),
    减值生成方式_交易批处理(ModelDictCode.减值生成方式,"02","交易批处理"),
    减值生成方式_减值计提批处理(ModelDictCode.减值生成方式,"03","减值计提批处理"),

    平安业务类型_资产减值清理_清理核销(ModelDictCode.平安业务类型,"180","清理核销"),
    共通业务类型_清理核销(ModelDictCode.共通业务类型,"TRD75","清理核销"),
    减值测算频率_年末(ModelDictCode.减值测算频率,"01","年末"),
    减值测算频率_季末(ModelDictCode.减值测算频率,"02","季末"),
    减值测算频率_月末(ModelDictCode.减值测算频率,"03","月末"),
    减值测算频率_周(ModelDictCode.减值测算频率,"04","周"),
    减值测算频率_日(ModelDictCode.减值测算频率,"05","日"),
    减值测算频率_每次指定交易(ModelDictCode.减值测算频率,"06","每次指定交易"),
    减值测算频率_每个开放日(ModelDictCode.减值测算频率,"07","每个开放日"),
    减值测算频率_不规则(ModelDictCode.减值测算频率,"08","不规则"),

    减值资产第三阶段利息计提摊销差额计入科目_减值准备(ModelDictCode.减值资产第三阶段利息计提摊销差额计入科目,"01","减值准备"),
    减值资产第三阶段利息计提摊销差额计入科目_原科目(ModelDictCode.减值资产第三阶段利息计提摊销差额计入科目,"02","原科目"),
    减值资产第三阶段是否重新计算实际利率_不重算(ModelDictCode.减值资产第三阶段是否重新计算实际利率,"01","不重算"),
    减值资产第三阶段是否重新计算实际利率_重算(ModelDictCode.减值资产第三阶段是否重新计算实际利率,"02","重算"),

    /**add by wujie on 20201130 start remark:累计科目余额**/
    累计余额_部分(ModelDictCode.是否累计余额,"03","部分更新"),
    /**add by wujie on 20201130 end remark:累计科目余额**/
    顶层资产类别_优先级(ModelDictCode.顶层资产类别,"01","优先级"),
    顶层资产类别_次优级(ModelDictCode.顶层资产类别,"02","次优级"),
    顶层资产类别_劣后级(ModelDictCode.顶层资产类别,"03","劣后级"),
    底层资产性质_委托贷款_委托债权(ModelDictCode.底层资产性质,"01","委托贷款/委托债权"),
    底层资产性质_北金所债权融资计划(ModelDictCode.底层资产性质,"02","北金所债权融资计划"),
    底层资产性质_同业存款_同业借款(ModelDictCode.底层资产性质,"03","同业存款/同业借款"),
    底层资产性质_两融收益权(ModelDictCode.底层资产性质,"04","两融收益权"),
    底层资产性质_租赁资产(ModelDictCode.底层资产性质,"05","租赁资产"),
    底层资产性质_优先股(ModelDictCode.底层资产性质,"06","优先股"),
    底层资产性质_股权性融资(ModelDictCode.底层资产性质,"07","股权性融资"),
    底层资产性质_其他资产(ModelDictCode.底层资产性质,"08","其他资产"),
    底层资产利率_收益性质_固定利率_收益(ModelDictCode.底层资产利率_收益性质,"01","固定利率/收益"),
    底层资产利率_收益性质_浮动利率_浮动收益_利率或收益调整条款(ModelDictCode.底层资产利率_收益性质,"02","浮动利率/浮动收益/利率或收益调整条款"),
    底层基础资产管理方式_静态(ModelDictCode.底层基础资产管理方式,"01","静态"),
    底层基础资产管理方式_动态(ModelDictCode.底层基础资产管理方式,"02","动态"),
    远期回购_差额补偿提供方为_原始权益人(ModelDictCode.远期回购_差额补偿提供方为,"01","原始权益人"),
    远期回购_差额补偿提供方为_第三方(ModelDictCode.远期回购_差额补偿提供方为,"02","第三方"),
    //modify by chufengjie I9主产品代码迁移：减值相关 20210603 end
    /* add by xutong 20210608 I9 start */
    业务模式_出售金融资产(ModelDictCode.业务模式,"01","出售金融资产"),
    业务模式_收取合同现金流量(ModelDictCode.业务模式,"02","收取合同现金流量"),
    业务模式_混合类(ModelDictCode.业务模式,"03","混合类"),

    //add by chufengjie I9改造：资产重分类 20210623 start
    定价模式_市价(ModelDictCode.定价模式,"01","市价"),
    定价模式_转出方价值(ModelDictCode.定价模式,"02","转出方价值"),
    //add by chufengjie I9改造：资产重分类 20210623 end
    /* add by xutong 20210608 I9 end */
    /* add by xutong 20210624 I9减值 start */
    模型数据来源手工(ModelDictCode.模型数据来源,"02","手工"),
    模型数据来源_模型(ModelDictCode.模型数据来源,"01","模型"),
    /* add by xutong 20210624 I9减值 end */
    共通业务类型_资产重分类交易(ModelDictCode.共通业务类型,"TRD77","资产重分类交易"),
    共通业务类型_资产大类评级配置(ModelDictCode.共通业务类型,"MOD01","资产大类评级配置"),
    共通业务类型_特殊资产评级配置(ModelDictCode.共通业务类型,"MOD02","特殊资产评级配置"),
    共通业务类型_LGD违约损失率维护(ModelDictCode.共通业务类型,"MOD05","LGD违约损失率维护"),
    共通业务类型_现金流折现模型曲线配置(ModelDictCode.共通业务类型,"MOD06","现金流折现模型曲线配置"),
    共通业务类型_二叉树模型参数配置(ModelDictCode.共通业务类型,"MOD09","二叉树模型参数配置"),
    /* add by zhangfei start 减值阶段管理 2021年6月28日15:43:53*/
    要素绝对性_绝对(ModelDictCode.要素绝对性,"01","绝对"),
    要素绝对性_相对(ModelDictCode.要素绝对性,"02","相对"),

    减值要素_本金逾期超过天数(ModelDictCode.减值要素,"01","本金逾期超过天数（含）"),
    减值要素_利息逾期超过天数(ModelDictCode.减值要素,"02","利息逾期超过天数（含）"),
    减值要素_公司评级信息低于(ModelDictCode.减值要素,"03","公司评级信息低于（含）"),
    减值要素_风险五级分类(ModelDictCode.减值要素,"04","风险五级分类"),
    减值要素_公司评级信息下调级数(ModelDictCode.减值要素,"05","公司评级信息下调级数"),

    五级分类_正常(ModelDictCode.五级分类,"01","正常"),
    五级分类_关注(ModelDictCode.五级分类,"02","关注"),
    五级分类_次级(ModelDictCode.五级分类,"03","次级"),
    五级分类_可疑(ModelDictCode.五级分类,"04","可疑"),
    五级分类_损失(ModelDictCode.五级分类,"05","损失"),
    /* add by zhangfei end  2021年6月28日15:43:53*/


    /* add by zhangfei 2021年6月29日14:55:22*/

    /* add by xutong 2021-06-30 估值模型 start */
    评级来源_模型_外部评级(ModelDictCode.评级来源_模型,"01","外部评级"),
    评级来源_模型_内部评级(ModelDictCode.评级来源_模型,"02","内部评级"),
    评级来源_模型_系统计算(ModelDictCode.评级来源_模型,"03","系统计算"),
    /* add by xutong 2021-06-30 估值模型 end */
    /* add by yuzj 2021-06-30 减值模型 start */
    共通业务类型_评级PD维护信息(ModelDictCode.共通业务类型,"MOD07","评级PD维护信息"),
    共通业务类型_特殊资产LGD维护信息(ModelDictCode.共通业务类型,"MOD08","特殊资产LGD维护信息"),
    /* add by yuzj 2021-06-30 减值模型 end */
    上市状态_上市(ModelDictCode.上市状态,"01","上市"),
    上市状态_未上市(ModelDictCode.上市状态,"02","未上市"),
    市场乘数方法_市净率法(ModelDictCode.市场乘数方法,"01","市净率法"),
    市场乘数方法_市盈率法(ModelDictCode.市场乘数方法,"02","市盈率法"),

    /* add by liuhuaming 2021-07-06 减值模型 start */
    共通业务类型_内外部评级映射管理(ModelDictCode.共通业务类型,"MOD03","内外部评级映射管理"),
    /* add by liuhuaming 2021-07-06 减值模型 end */

    /* add by liuhuaming 2021-07-08 估值模型 start */
    平滑区间_1个月(ModelDictCode.平滑区间,"01","1个月"),
    平滑区间_3个月(ModelDictCode.平滑区间,"02","3个月"),
    收益率曲线类型_SP即期(ModelDictCode.收益率曲线类型,"01","SP即期"),
    收益率曲线类型_MA远期(ModelDictCode.收益率曲线类型,"02","MA远期"),
    /* add by liuhuaming 2021-07-08 估值模型 end */

    /* add by xutong 2021-07-10 start */
    共通业务类型_资产减值管理(ModelDictCode.共通业务类型,"MOD11","资产减值管理"),
    共通业务类型_风险溢价调整配置(ModelDictCode.共通业务类型,"MOD12","风险溢价调整配置"),
    /* add by xutong 2021-07-10 end */

    估值市价_模型法(ModelDictCode.估值市价,"MOD","模型法"),
    /**
     * A/A
     */
    计息基础_A_A_B_COUP(ModelDictCode.计息基础, "A/A_B_COUP", "A/A_B_COUP"),
    /**
     * A/A
     */
    计息基础_A_A_B_DAY(ModelDictCode.计息基础, "A/A_B_DAY", "A/A_B_DAY"),
    季度_第一季度(ModelDictCode.季度,"01","第一季度"),
    季度_第二季度(ModelDictCode.季度,"02","第二季度"),
    季度_第三季度(ModelDictCode.季度,"03","第三季度"),
    季度_第四季度(ModelDictCode.季度,"04","第四季度"),
    曲线代码_中债国债收益率曲线(ModelDictCode.曲线代码,"1231","中债国债收益率曲线"),
    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private ModelDictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    ModelDictCodeList(ModelDictCode dictCode, String value, String codeName) {
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
    public static ModelDictCodeList from(ModelDictCode dictCode, String value) {
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public static List<SelectOptionDto> fromDictCode(ModelDictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public static List<String> valuesfromDictCode(ModelDictCode dictCode) {
        List<String> result = new ArrayList<>();
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public ModelDictCode getDictCode() {
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

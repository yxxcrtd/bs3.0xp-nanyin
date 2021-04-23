/*
 * 文件名：AssetLiabilityStatementEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 资产负债表枚举 （科目类型， 科目号， 资产名，科目名）
 * 
 * @author zhangwei
 * @version 1.0 2018年9月19日
 * @see AssetLiabilityStatementEnum
 * @since 1.0
 */
public enum AssetLiabilityStatementEnum implements Serializable {

	//借方
	资产类_银行存款_银行存款(DictCodeList.科目类型_资产类,"1002","ZC_01","银行存款","银行存款"),
	资产类_银行存款_资金同业款(DictCodeList.科目类型_资产类,"1003","ZC_01","银行存款","资金同业款"),

	资产类_结算备付金_结算备付金(DictCodeList.科目类型_资产类,"1021","ZC_02","结算备付金","结算备付金"),

	资产类_存出保证金_存出保证金(DictCodeList.科目类型_资产类,"1031","ZC_03","存出保证金","存出保证金"),

	资产类_衍生金融资产_外汇买卖(DictCodeList.科目类型_资产类,"3101","ZC_04","衍生金融资产","外汇买卖"),
	资产类_衍生金融资产_外汇结售汇(DictCodeList.科目类型_资产类,"3102","ZC_04","衍生金融资产","外汇结售汇"),
	资产类_衍生金融资产_金融掉期(DictCodeList.科目类型_资产类,"3103","ZC_04","衍生金融资产","金融掉期"),
	资产类_衍生金融资产_远期投资(DictCodeList.科目类型_资产类,"3104","ZC_04","衍生金融资产","远期投资"),
	资产类_衍生金融资产_股票期权(DictCodeList.科目类型_资产类,"310601","ZC_04","衍生金融资产","股票期权"),
	资产类_衍生金融资产_买入个股期权(DictCodeList.科目类型_资产类,"310602","ZC_04","衍生金融资产","买入个股期权"),
	资产类_衍生金融资产_买入指数期权(DictCodeList.科目类型_资产类,"310603","ZC_04","衍生金融资产","买入指数期权"),
	资产类_衍生金融资产_买入期货期权(DictCodeList.科目类型_资产类,"310604","ZC_04","衍生金融资产","买入期货期权"),
	资产类_衍生金融资产_其他衍生工具(DictCodeList.科目类型_资产类,"3111","ZC_04","衍生金融资产","其他衍生工具"),
	资产类_衍生金融资产_期货投资(DictCodeList.科目类型_资产类,"3105","ZC_04","衍生金融资产","期货投资"),
	资产类_衍生金融资产_套期工具(DictCodeList.科目类型_资产类,"3201","ZC_04","衍生金融资产","套期工具"),
	资产类_衍生金融资产_被套期项目(DictCodeList.科目类型_资产类,"3202","ZC_04","衍生金融资产","被套期项目"),

	资产类_买入返售金融资产_买入返售金融资产(DictCodeList.科目类型_资产类,"1202","ZC_05","买入返售金融资产","买入返售金融资产"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资(DictCodeList.科目类型_资产类,"1108","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","公允价值损益类_股票投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资(DictCodeList.科目类型_资产类,"1103","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","以公允价值计量且其变动计入当期损益类债券投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益的其他债权投资(DictCodeList.科目类型_资产类,"1308","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","以公允价值计量且其变动计入当期损益的其他债权投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资(DictCodeList.科目类型_资产类,"1105","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","公允价值损益类_基金投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_净值型_资产投资(DictCodeList.科目类型_资产类,"1107","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","公允价值损益类_计划类_净值型_资产投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类_货币型_资产投资(DictCodeList.科目类型_资产类,"1110","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","公允价值损益类_计划类_货币型_资产投资"),
	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_境内计划类_利率型_资产投资(DictCodeList.科目类型_资产类,"1111","ZC_06","以公允价值计量且其变动计入当期损益的金融资产","公允价值损益类_境内计划类_利率型_资产投资"),

	资产类_其中_权益投资_公允价值损益类_股票投资(DictCodeList.科目类型_资产类,"1108","ZC_07","其中：权益投资","公允价值损益类_股票投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益类债券投资(DictCodeList.科目类型_资产类,"1103","ZC_08","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债权投资","以公允价值计量且其变动计入当期损益类债券投资"),
	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入当期损益的其他债权投资(DictCodeList.科目类型_资产类,"1308", "ZC_08","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债权投资","以公允价值计量且其变动计入当期损益的其他债权投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_基金投资(DictCodeList.科目类型_资产类,"1105","ZC_09","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","公允价值损益类_基金投资"),
	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_净值型_资产投资(DictCodeList.科目类型_资产类,"1107","ZC_09","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","公允价值损益类_计划类_净值型_资产投资"),
	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_计划类_货币型_资产投资(DictCodeList.科目类型_资产类,"1110","ZC_09","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","公允价值损益类_计划类_货币型_资产投资"),
	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_公允价值损益类_境内计划类_利率型_资产投资(DictCodeList.科目类型_资产类,"1111","ZC_09","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","公允价值损益类_境内计划类_利率型_资产投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无(DictCodeList.科目类型_资产类,"999901","ZC_10","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他","暂无"),

	资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值且其变动计入其他综合收益类债券投资(DictCodeList.科目类型_资产类,"1104","ZC_11","以公允价值计量且其变动计入其他综合收益的金融资产","以公允价值且其变动计入其他综合收益类债券投资"),
	资产类_以公允价值计量且其变动计入其他综合收益的金融资产_以公允价值计量且其变动计入其他综合收益的其他债权投资(DictCodeList.科目类型_资产类,"1309","ZC_11","以公允价值计量且其变动计入其他综合收益的金融资产","以公允价值计量且其变动计入其他综合收益的其他债权投资"),
	资产类_以公允价值计量且其变动计入其他综合收益的金融资产_综合收益类_计划类资产投资(DictCodeList.科目类型_资产类,"1109","ZC_11","以公允价值计量且其变动计入其他综合收益的金融资产","综合收益类_计划类资产投资"),

	资产类_其中_权益投资_暂无(DictCodeList.科目类型_资产类,"999902","ZC_12","其中：权益投资","暂无"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值且其变动计入其他综合收益类债券投资(DictCodeList.科目类型_资产类,"1104","ZC_13","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债权投资","以公允价值且其变动计入其他综合收益类债券投资"),
	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_债权投资_以公允价值计量且其变动计入其他综合收益的其他债权投资(DictCodeList.科目类型_资产类,"1309","ZC_13","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债权投资","以公允价值计量且其变动计入其他综合收益的其他债权投资"),


	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_综合收益类_计划类资产投资(DictCodeList.科目类型_资产类,"1109","ZC_14","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","综合收益类_计划类资产投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_1(DictCodeList.科目类型_资产类,"","ZC_15","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他","暂无"),

	资产类_以摊余成本计量的金融资产产_摊余成本类债券投资(DictCodeList.科目类型_资产类,"1102","ZC_16","以摊余成本计量的金融投资","摊余成本类债券投资"),
	资产类_以摊余成本计量的金融资产产_以摊余成本计量的其他债权投资(DictCodeList.科目类型_资产类,"1310","ZC_16","以摊余成本计量的金融投资","以摊余成本计量的其他债权投资"),
	资产类_以摊余成本计量的金融资产产_摊余成本类_计划类资产投资(DictCodeList.科目类型_资产类,"1106","ZC_16","以摊余成本计量的金融投资","摊余成本类_计划类资产投资"),

	资产类_其中_债权投资_摊余成本类债券投资(DictCodeList.科目类型_资产类,"1102","ZC_17","其中：债权投资","摊余成本类债券投资"),
	资产类_其中_债权投资_以摊余成本计量的其他债权投资(DictCodeList.科目类型_资产类,"1310","ZC_17","其中：债权投资","以摊余成本计量的其他债权投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_结构化主体投资_摊余成本类_计划类资产投资(DictCodeList.科目类型_资产类,"1106","ZC_18","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结构化主体投资","摊余成本类_计划类资产投资"),

	资产类_nbsp_nbsp_nbsp_nbsp_nbsp_nbsp_其他_暂无_2(DictCodeList.科目类型_资产类,"999903","ZC_19","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他","暂无"),

	资产类_应收红利_应收红利(DictCodeList.科目类型_资产类,"1203","ZC_20","应收红利","应收红利"),

	资产类_应收其他投资收入_暂无(DictCodeList.科目类型_资产类,"999904","ZC_21","应收其他投资收入","暂无"),

	资产类_应收证券清算款_证券清算款(DictCodeList.科目类型_资产类,"3003","ZC_22","应收证券清算款","证券清算款"),

	资产类_应收申购款_应收申购款(DictCodeList.科目类型_资产类,"1207","ZC_23","应收申购款","应收申购款"),
	资产类_应收申购款_产品申购款(DictCodeList.科目类型_资产类,"1601","ZC_23","应收申购款","产品申购款"),

	资产类_其他资产_其他应收款(DictCodeList.科目类型_资产类,"1221","ZC_24","其他资产","其他应收款"),
	资产类_其他资产_应收逾期款(DictCodeList.科目类型_资产类,"1304","ZC_24","其他资产","应收逾期款"),
	资产类_其他资产_待摊费用(DictCodeList.科目类型_资产类,"1501","ZC_24","其他资产","待摊费用"),
	资产类_其他资产_坏账准备(DictCodeList.科目类型_资产类,"1303","ZC_24","其他资产","坏账准备"),
	资产类_其他资产_应付账款(DictCodeList.科目类型_资产类,"3004","ZC_24","其他资产","应付账款"),

	//资产类_资产总计(DictCodeList.科目类型_资产类,"","ZC_25","资产总计",""),
	//资产类_负债和所有者权益(DictCodeList.科目类型_资产类,"","ZC_26","负债和所有者权益",""),
	//贷方
	//负债类
	负债类_同业借款_拆入款项(DictCodeList.科目类型_负债类,"2001","FZ_01","同业借款","拆入款项"),

	负债类_卖出回购金融资产款_卖出回购金融资产款(DictCodeList.科目类型_负债类,"2202","FZ_02","卖出回购金融资产款","卖出回购金融资产款"),

	负债类_以公允价值计量且其变动计入当期损益的金融负债_以公允价值计量且其变动计入当期损益类金融负债(DictCodeList.科目类型_负债类,"2101","FZ_03","以公允价值计量且其变动计入当期损益的金融负债","以公允价值计量且其变动计入当期损益类金融负债"),

	负债类_衍生金融负债_外汇买卖(DictCodeList.科目类型_负债类,"3101","FZ_04","衍生金融负债","外汇买卖"),
	负债类_衍生金融负债_外汇结售汇(DictCodeList.科目类型_负债类,"3102","FZ_04","衍生金融负债","外汇结售汇"),
	负债类_衍生金融负债_金融掉期(DictCodeList.科目类型_负债类,"3103","FZ_04","衍生金融负债","金融掉期"),
	负债类_衍生金融负债_远期投资(DictCodeList.科目类型_负债类,"3104","FZ_04","衍生金融负债","远期投资"),
	负债类_衍生金融负债_期货投资(DictCodeList.科目类型_负债类,"3105","FZ_04","衍生金融负债","期货投资"),
	资产类_衍生金融负债_股票期权(DictCodeList.科目类型_资产类,"310601","FZ_04","衍生金融负债","股票期权"),
	资产类_衍生金融负债_卖出个股期权(DictCodeList.科目类型_资产类,"310605","FZ_04","衍生金融负债","卖出个股期权"),
	资产类_衍生金融负债_卖出指数期权(DictCodeList.科目类型_资产类,"310606","FZ_04","衍生金融负债","卖出指数期权"),
	资产类_衍生金融负债_卖出期货期权(DictCodeList.科目类型_资产类,"310607","FZ_04","衍生金融负债","卖出期货期权"),
	负债类_衍生金融负债_其他衍生工具(DictCodeList.科目类型_负债类,"3111","FZ_04","衍生金融负债","其他衍生工具"),
	负债类_衍生金融负债_套期工具(DictCodeList.科目类型_负债类,"3201","FZ_04","衍生金融负债","套期工具"),
	负债类_衍生金融负债_被套期项目(DictCodeList.科目类型_负债类,"3202","FZ_04","衍生金融负债","被套期项目"),

	负债类_应付证券清算款_证券清算款(DictCodeList.科目类型_负债类,"3003","FZ_05","应付证券清算款","证券清算款"),

	负债类_应付赎回款_应付赎回款(DictCodeList.科目类型_负债类,"2203","FZ_06","应付赎回款","应付赎回款"),

	负债类_应付投资顾问费_应付投顾费(DictCodeList.科目类型_负债类,"2205","FZ_07","应付投资顾问费","应付投顾费"),

	负债类_应付管理人报酬_应付管理人报酬(DictCodeList.科目类型_负债类,"2206","FZ_08","应付管理人报酬","应付管理人报酬"),

	负债类_应付托管费_应付托管费(DictCodeList.科目类型_负债类,"2207","FZ_09","应付托管费","应付托管费"),

	负债类_应付销售服务费_应付销售服务费(DictCodeList.科目类型_负债类,"2208","FZ_10","应付销售服务费","应付销售服务费"),

	负债类_应付交易费用_应付交易费用(DictCodeList.科目类型_负债类,"2209","FZ_11","应付交易费用","应付交易费用"),

	负债类_应交税金_应交税费(DictCodeList.科目类型_负债类,"2221","FZ_12","应交税金","应交税费"),
	负债类_应交税金_合作方应交税费(DictCodeList.科目类型_负债类,"2213","FZ_12","应交税金","合作方应交税费"),

	负债类_应付利润_应付利润(DictCodeList.科目类型_负债类,"2232","FZ_13","应付利润","应付利润"),

	负债类_其他负债_应付赎回费(DictCodeList.科目类型_负债类,"2204","FZ_14","其他负债","应付赎回费"),
	负债类_其他负债_应付代销费(DictCodeList.科目类型_负债类,"2210","FZ_14","其他负债","应付代销费"),
	负债类_其他负债_应付外包服务费(DictCodeList.科目类型_负债类,"2211","FZ_14","其他负债","应付外包服务费"),
	负债类_其他负债_应付推介费(DictCodeList.科目类型_负债类,"2212","FZ_14","其他负债","应付推介费"),
	负债类_其他负债_应付账款(DictCodeList.科目类型_负债类,"3004","FZ_14","其他负债","应付账款"),
	负债类_其他负债_待清算资金(DictCodeList.科目类型_负债类,"2233","FZ_14","其他负债","待清算资金"),
	负债类_其他负债_其他应付款(DictCodeList.科目类型_负债类,"2241","FZ_14","其他负债","其他应付款"),
	负债类_其他负债_预提费用(DictCodeList.科目类型_负债类,"2501","FZ_14","其他负债","预提费用"),

	//负债类_负债总计(DictCodeList.科目类型_负债类,"","FZ_15","负债总计",""),
	//权益类
	权益类_实收理财资金_实收资本(DictCodeList.科目类型_所有者权益类,"4001","SYZQY_01","实收理财资金","实收资本"),

	权益类_其他综合收益_其他综合收益(DictCodeList.科目类型_所有者权益类,"4003","SYZQY_02","其他综合收益","其他综合收益"),

	权益类_未分配利润_利息收入(DictCodeList.科目类型_所有者权益类,"6011","SYZQY_03","未分配利润","利息收入"),
	权益类_未分配利润_公允价值变动损益(DictCodeList.科目类型_所有者权益类,"6101","SYZQY_03","未分配利润","公允价值变动损益"),

	权益类_未分配利润_投资收益(DictCodeList.科目类型_所有者权益类,"6111","SYZQY_03","未分配利润","投资收益"),
	权益类_未分配利润_汇兑损益(DictCodeList.科目类型_所有者权益类,"6112","SYZQY_03","未分配利润","汇兑损益"),
	权益类_未分配利润_其他收入(DictCodeList.科目类型_所有者权益类,"6302","SYZQY_03","未分配利润","其他收入"),
	权益类_未分配利润_管理人报酬(DictCodeList.科目类型_所有者权益类,"6403","SYZQY_03","未分配利润","管理人报酬"),
	权益类_未分配利润_托管费(DictCodeList.科目类型_所有者权益类,"6404","SYZQY_03","未分配利润","托管费"),
	权益类_未分配利润_销售服务费(DictCodeList.科目类型_所有者权益类,"6406","SYZQY_03","未分配利润","销售服务费"),
	权益类_未分配利润_代销费(DictCodeList.科目类型_所有者权益类,"6409","SYZQY_03","未分配利润","代销费"),
	权益类_未分配利润_外包服务费(DictCodeList.科目类型_所有者权益类,"6410","SYZQY_03","未分配利润","外包服务费"),
	权益类_未分配利润_交易费用(DictCodeList.科目类型_所有者权益类,"6407","SYZQY_03","未分配利润","交易费用"),
	权益类_未分配利润_应交税费支出(DictCodeList.科目类型_所有者权益类,"6408","SYZQY_03","未分配利润","应交税费支出"),
	权益类_未分配利润_利息支出(DictCodeList.科目类型_所有者权益类,"6411","SYZQY_03","未分配利润","利息支出"),
	权益类_未分配利润_资产减值损失(DictCodeList.科目类型_所有者权益类,"6412","SYZQY_03","未分配利润","资产减值损失"),
	权益类_未分配利润_推介费支出(DictCodeList.科目类型_所有者权益类,"6413","SYZQY_03","未分配利润","推介费支出"),
	权益类_未分配利润_合作方应交税费支出(DictCodeList.科目类型_所有者权益类,"6414","SYZQY_03","未分配利润","合作方应交税费支出"),
	权益类_未分配利润_利息支出_利率互换(DictCodeList.科目类型_所有者权益类,"6416","SYZQY_03","未分配利润","利息支出_利率互换"),
	权益类_未分配利润_其他费用(DictCodeList.科目类型_所有者权益类,"6605","SYZQY_03","未分配利润","其他费用"),
	权益类_未分配利润_其他支出(DictCodeList.科目类型_所有者权益类,"6702","SYZQY_03","未分配利润","其他支出"),
	权益类_未分配利润_以前年度损益调整(DictCodeList.科目类型_所有者权益类,"6901","SYZQY_03","未分配利润","以前年度损益调整"),
	权益类_未分配利润_损益平准金(DictCodeList.科目类型_所有者权益类,"4011","SYZQY_03","未分配利润","损益平准金"),
	权益类_未分配利润_本期利润(DictCodeList.科目类型_所有者权益类,"4103","SYZQY_03","未分配利润","本期利润"),
	权益类_未分配利润_利润分配(DictCodeList.科目类型_所有者权益类,"4104","SYZQY_03","未分配利润","利润分配"),

	//权益类_所有者权益总计(DictCodeList.科目类型_所有者权益类,"","SYZQY_04","所有者权益总计",""),
	//权益类_负债和所有者权益总计(DictCodeList.科目类型_所有者权益类,"","SYZQY_05","负债和所有者权益总计",""),
	;

	/**
	 * 对应于元素Code
	 */
    private String assetCode;

    /**
     * 对应于数据字典code
     */
	private String value;

	/**
	 * 数据字典枚举
	 */
	private DictCodeList dictCodeList;

	/**
	 * 资产名称
	 */
	private String assetName;

	/**
	 * 科目名称
	 */
	private String subjectName;


    /** update by yanya on 20190929 增加邮储模板类型
	AssetLiabilityStatementEnum(DictCodeList dictCodeList, String value, String assetName, String subjectName) {
		this.dictCodeList = dictCodeList;
		this.value = value;
		this.assetName = assetName;
		this.subjectName = subjectName;
	}**/
	AssetLiabilityStatementEnum(DictCodeList dictCodeList, String value, String assetCode, String assetName, String subjectName) {
	    this.dictCodeList = dictCodeList;
	    this.value = value;
	    this.assetCode = assetCode;
	    this.assetName = assetName;
	    this.subjectName = subjectName;
	}

	/**
	 * 获取科目代码valueList modify by yanya on 20190929 按照系统参数模板类型进行
	 *
	 * @param dictCode
	 *            DictCode
	 * @return List
	 * @see
	 * @since 1.0
	 */
	public static List<String> valuesfromDictCode(DictCodeList... dictCodeLists) {
		List<String> result = new ArrayList<>();
		AssetLiabilityStatementEnum[] values = AssetLiabilityStatementEnum.values();
		for (AssetLiabilityStatementEnum item : values) {
			for (DictCodeList dictCodeList : dictCodeLists) {
			    /**modify by yanya on 20190927 增加根据财务四表模板类型查询配置 start**/
				if (item.getDictCodeList() == dictCodeList) {
				/**modify by yanya on 20190927 增加根据财务四表模板类型查询配置 end**/
					result.add(item.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * 获取枚举 通过科目号value
	 *
	 * @param dictCode
	 *            DictCode
	 * @return List&lt;SelectOptionDto&gt;
	 * @see
	 * @since 1.0
	 */
	public static List<AssetLiabilityStatementEnum> enumsfromValue(String value) {
		List<AssetLiabilityStatementEnum> list = Lists.newArrayList();
		for (AssetLiabilityStatementEnum item : values()) {
			if (StringUtils.equals(item.getValue(), value)) {
				list.add(item);
			}
		}
		return list;
		// throw new RuntimeException("没有找到该枚举类型！");
	}

	public String getValue() {
		return value;
	}

	public DictCodeList getDictCodeList() {
		return dictCodeList;
	}

	public String getAssetName() {
		return assetName;
	}

	public String getSubjectName() {
		return subjectName;
	}


    /**
     * 获取 元素Code
     * @return 元素Code
     */
    public String getAssetCode() {
        return assetCode;
    }


    /**
     * 设置 元素Code
     * @param assetCode 元素Code
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }



    
}

/*
 * 文件名：AssetLiabilityStatementEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatement.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产负债表枚举 （科目类型， 科目号， 资产名，科目名）
 *
 * @author zhangwei
 * @version 1.0 2018年9月19日
 * @see AssetLiabilityStatementEnum
 * @since 1.0
 */
public enum AssetLiabilityStatementEnum implements Serializable {

	资产类_银行存款(DictCodeList.科目类型_资产类, "1002", "银行存款", "银行存款"),

	资产类_结算备付金(DictCodeList.科目类型_资产类, "1021", "结算备付金", "结算备付金"),

	资产类_存出保证金(DictCodeList.科目类型_资产类, "1031", "存出保证金", "存出保证金"),

	资产类_存放同业款项(DictCodeList.科目类型_资产类, "100301", "存放同业款项", "存放同业-一般款项"),

	资产类_拆出资金(DictCodeList.科目类型_资产类, "100302", "拆出资金", "拆放同业-同业借款"),

	资产类_买入返售金融资产(DictCodeList.科目类型_资产类, "1202", "买入返售金融资产", "买入返售金融资产"),

	资产类_衍生金融资产_外汇买卖(DictCodeList.科目类型_资产类, "3101", "衍生金融资产", "外汇买卖"),

	资产类_衍生金融资产_外汇结售汇(DictCodeList.科目类型_资产类, "3102", "衍生金融资产", "外汇结售汇"),

	资产类_衍生金融资产_金融掉期(DictCodeList.科目类型_资产类, "3103", "衍生金融资产", "金融掉期"),

	资产类_衍生金融资产_远期投资(DictCodeList.科目类型_资产类, "3104", "衍生金融资产", "远期投资"),

	资产类_衍生金融资产_其他衍生工具(DictCodeList.科目类型_资产类, "3111", "衍生金融资产", "其他衍生工具"),

	资产类_衍生金融资产_套期工具(DictCodeList.科目类型_资产类, "3201", "衍生金融资产", "套期工具"),

	资产类_衍生金融资产_被套期项目(DictCodeList.科目类型_资产类, "3202", "衍生金融资产", "被套期项目"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类债券投资(DictCodeList.科目类型_资产类, "1103", "以公允价值计量且其变动计入当期损益的金融资产",
			"以公允价值计量且其变动计入当期损益类债券投资"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_基金投资(DictCodeList.科目类型_资产类, "1105", "以公允价值计量且其变动计入当期损益的金融资产", "公允价值损益类-基金投资"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_计划类资产投资(DictCodeList.科目类型_资产类, "1107", "以公允价值计量且其变动计入当期损益的金融资产",
			"公允价值损益类-计划类资产投资"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_公允价值损益类_股票投资(DictCodeList.科目类型_资产类, "1108", "以公允价值计量且其变动计入当期损益的金融资产", "公允价值损益类-股票投资"),

	资产类_以公允价值计量且其变动计入当期损益的金融资产_以公允价值计量且其变动计入当期损益类股票投资(DictCodeList.科目类型_资产类, "1110", "以公允价值计量且其变动计入当期损益的金融资产",
			"以公允价值计量且其变动计入当期损益类股票投资"),

	资产类_以公允价值计量且其变动计入其他综合收益的金融资产(DictCodeList.科目类型_资产类, "1104", "以公允价值计量且其变动计入其他综合收益的金融资产", "以公允价值且其变动计入其他综合收益类债券投资"),

	资产类_以摊余成本计量的金融资产_摊余成本类债券投资(DictCodeList.科目类型_资产类, "1102", "以摊余成本计量的金融资产", "摊余成本类债券投资"),

	资产类_以摊余成本计量的金融资产_摊余成本类_计划类资产投资(DictCodeList.科目类型_资产类, "1106", "以摊余成本计量的金融资产", "摊余成本类-计划类资产投资"),

	资产类_应收利息_应收利息(DictCodeList.科目类型_资产类, "1204", "应收利息", "应收利息"),

	资产类_应收利息_非上市债券应收利息(DictCodeList.科目类型_资产类, "1205", "应收利息", "非上市债券应收利息"),

	资产类_应收证券清算款(DictCodeList.科目类型_资产类, "3003", "应收证券清算款", "证券清算款"),

	资产类_应收红利(DictCodeList.科目类型_资产类, "1203", "应收红利", "应收红利"),

	资产类_应收申购款(DictCodeList.科目类型_资产类, "1207", "应收申购款", "应收申购款"),

	资产类_其他资产_非上市债券成本(DictCodeList.科目类型_资产类, "1109", "其他资产", "非上市债券成本"),

	资产类_其他资产_其他应收款(DictCodeList.科目类型_资产类, "1221", "其他资产", "其他应收款"),

	资产类_其他资产_资产减值准备(DictCodeList.科目类型_资产类, "1302", "其他资产", "资产减值准备"),

	资产类_其他资产_坏账准备(DictCodeList.科目类型_资产类, "1303", "其他资产", "坏账准备"),

	资产类_其他资产_应收逾期款(DictCodeList.科目类型_资产类, "1304", "其他资产", "应收逾期款"),

	资产类_其他资产_待摊费用(DictCodeList.科目类型_资产类, "1501", "其他资产", "待摊费用"),

	资产类_其他资产_应付账款(DictCodeList.科目类型_资产类, "3004", "其他资产", "应付账款"),

	负债类_拆入资金(DictCodeList.科目类型_负债类, "2001", "拆入资金", "拆入款项"),

	负债类_以公允价值计量且其变动计入当期损益的金融负债(DictCodeList.科目类型_负债类, "2101", "以公允价值计量且其变动计入当期损益的金融负债", "以公允价值计量且其变动计入当期损益类金融负债"),

	负债类_衍生金融负债_外汇买卖(DictCodeList.科目类型_负债类, "3101", "衍生金融负债", "外汇买卖"),

	负债类_衍生金融负债_外汇结售汇(DictCodeList.科目类型_负债类, "3102", "衍生金融负债", "外汇结售汇"),

	负债类_衍生金融负债_金融掉期(DictCodeList.科目类型_负债类, "3103", "衍生金融负债", "金融掉期"),

	负债类_衍生金融负债_远期投资(DictCodeList.科目类型_负债类, "3104", "衍生金融负债", "远期投资"),

	负债类_衍生金融负债_其他衍生工具(DictCodeList.科目类型_负债类, "3111", "衍生金融负债", "其他衍生工具"),

	负债类_衍生金融负债_套期工具(DictCodeList.科目类型_负债类, "3201", "衍生金融负债", "套期工具"),

	负债类_衍生金融负债_被套期项目(DictCodeList.科目类型_负债类, "3202", "衍生金融负债", "被套期项目"),

	负债类_卖出回购金融资产款(DictCodeList.科目类型_负债类, "2202", "卖出回购金融资产款", "卖出回购金融资产款"),

	负债类_应付管理人报酬(DictCodeList.科目类型_负债类, "2206", "应付管理人报酬", "应付管理人报酬"),

	负债类_应付交易费用(DictCodeList.科目类型_负债类, "2209", "应付交易费用", "应付交易费用"),

	负债类_应付托管费(DictCodeList.科目类型_负债类, "2207", "应付托管费", "应付托管费"),

	负债类_应付销售服务费(DictCodeList.科目类型_负债类, "2208", "应付销售服务费", "应付销售服务费"),

	负债类_应交税费(DictCodeList.科目类型_负债类, "2221", "应交税费", "应交税费"),

	负债类_应付利息(DictCodeList.科目类型_负债类, "2231", "应付利息", "应付利息"),

	负债类_应付赎回款(DictCodeList.科目类型_负债类, "2203", "应付赎回款", "应付赎回款"),

	负债类_应付证券清算款(DictCodeList.科目类型_负债类, "3003", "应付证券清算款", "证券清算款"),

	负债类_应付利润(DictCodeList.科目类型_负债类, "2232", "应付利润", "应付利润"),

	负债类_其他负债_应付赎回费(DictCodeList.科目类型_负债类, "2204", "其他负债", "应付赎回费"),

	负债类_其他负债_应付代销费(DictCodeList.科目类型_负债类, "2210", "其他负债", "应付代销费"),

	负债类_其他负债_应付外包服务费(DictCodeList.科目类型_负债类, "2211", "其他负债", "应付外包服务费"),

	负债类_其他负债_应付投顾费(DictCodeList.科目类型_负债类, "2205", "其他负债", "应付投顾费"),

	负债类_其他负债_应付推介费(DictCodeList.科目类型_负债类, "2212", "其他负债", "应付推介费"),

	负债类_其他负债_合作方应交税费(DictCodeList.科目类型_负债类, "2213", "其他负债", "合作方应交税费"),

//	负债类_其他负债_应交税费(DictCodeList.科目类型_负债类, "2221", "其他负债", "应交税费"),

	负债类_其他负债_应付账款(DictCodeList.科目类型_负债类, "3004", "其他负债", "应付账款"),

	负债类_其他负债_待清算资金(DictCodeList.科目类型_负债类, "2233", "其他负债", "待清算资金"),

	负债类_其他负债_其他应付款(DictCodeList.科目类型_负债类, "2241", "其他负债", "其他应付款"),

	负债类_其他负债_预提费用(DictCodeList.科目类型_负债类, "2501", "其他负债", "预提费用"),

	所有者权益类_实收资本(DictCodeList.科目类型_所有者权益类, "4001", "实收资本", "实收资本"),

	所有者权益类_资本公积(DictCodeList.科目类型_所有者权益类, "4002", "资本公积", "资本公积"),

	所有者权益类_其他综合收益(DictCodeList.科目类型_所有者权益类, "4003", "其他综合收益", "其他综合收益"),

	所有者权益类_未分配利润_利息收入(DictCodeList.科目类型_所有者权益类, "6011", "未分配利润", "利息收入"),

	所有者权益类_未分配利润_公允价值变动损益(DictCodeList.科目类型_所有者权益类, "6101", "未分配利润", "公允价值变动损益"),

	所有者权益类_未分配利润_投资收益(DictCodeList.科目类型_所有者权益类, "6111", "未分配利润", "投资收益"),

	所有者权益类_未分配利润_汇兑损益(DictCodeList.科目类型_所有者权益类, "6112", "未分配利润", "汇兑损益"),

	所有者权益类_未分配利润_其他收入(DictCodeList.科目类型_所有者权益类, "6302", "未分配利润", "其他收入"),

	所有者权益类_未分配利润_管理人报酬(DictCodeList.科目类型_所有者权益类, "6403", "未分配利润", "管理人报酬"),

	所有者权益类_未分配利润_托管费(DictCodeList.科目类型_所有者权益类, "6404", "未分配利润", "托管费"),

	所有者权益类_未分配利润_销售服务费(DictCodeList.科目类型_所有者权益类, "6406", "未分配利润", "销售服务费"),

	所有者权益类_未分配利润_代销费(DictCodeList.科目类型_所有者权益类, "6409", "未分配利润", "代销费"),

	所有者权益类_未分配利润_外包服务费(DictCodeList.科目类型_所有者权益类, "6410", "未分配利润", "外包服务费"),

	所有者权益类_未分配利润_交易费用(DictCodeList.科目类型_所有者权益类, "6407", "未分配利润", "交易费用"),

	所有者权益类_未分配利润_税费支出(DictCodeList.科目类型_所有者权益类, "6408", "未分配利润", "税费支出"),

	所有者权益类_未分配利润_利息支出(DictCodeList.科目类型_所有者权益类, "6411", "未分配利润", "利息支出"),

	所有者权益类_未分配利润_资产减值损失(DictCodeList.科目类型_所有者权益类, "6412", "未分配利润", "资产减值损失"),

	所有者权益类_未分配利润_推介费支出(DictCodeList.科目类型_所有者权益类, "6413", "未分配利润", "推介费支出"),

	所有者权益类_未分配利润_合作方应交税费支出(DictCodeList.科目类型_所有者权益类, "6414", "未分配利润", "合作方应交税费支出"),

	所有者权益类_未分配利润_其他费用(DictCodeList.科目类型_所有者权益类, "6605", "未分配利润", "其他费用"),

	所有者权益类_未分配利润_其他支出(DictCodeList.科目类型_所有者权益类, "6702", "未分配利润", "其他支出"),

	所有者权益类_未分配利润_以前年度损益调整(DictCodeList.科目类型_所有者权益类, "6901", "未分配利润", "以前年度损益调整"),

	所有者权益类_未分配利润_实收资本(DictCodeList.科目类型_所有者权益类, "4011", "未分配利润", "损益平准金"),

	所有者权益类_未分配利润_本期利润(DictCodeList.科目类型_所有者权益类, "4103", "未分配利润", "本期利润"),

	所有者权益类_未分配利润_利润分配(DictCodeList.科目类型_所有者权益类, "4104", "未分配利润", "利润分配"),

	;

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

	AssetLiabilityStatementEnum(DictCodeList dictCodeList, String value, String assetName, String subjectName) {
		this.dictCodeList = dictCodeList;
		this.value = value;
		this.assetName = assetName;
		this.subjectName = subjectName;
	}

	/**
	 * 获取科目代码valueList
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
				if (item.getDictCodeList() == dictCodeList) {
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
}

/*
 * 文件名：AssetLiabilityStatementEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;

/**
 * 资产负债表枚举 （科目类别， 科目号， 资产名，科目名）
 * 
 * @author zhangwei
 * @version 1.0 2018年9月19日
 * @see AssetLiabilityStatementEnum
 * @since 1.0
 */
public enum DisAssetLiabilityStatementEnum implements Serializable {

	资产类_银行存款(DictCodeList.科目类别_资产类, "1002", "银行存款", "银行存款"),

	资产类_结算备付金(DictCodeList.科目类别_资产类, "0", "结算备付金", "结算备付金"),

	资产类_存出保证金(DictCodeList.科目类别_资产类, "1031", "存出保证金", "存出保证金"),

	资产类_存放同业款项(DictCodeList.科目类别_资产类, "1052", "存放同业款项", "存放同业-一般款项"),

	资产类_拆出资金(DictCodeList.科目类别_资产类, "1051", "拆出资金", "拆放同业-同业借款"),
	
	
	资产类_买入返售金融资产_返售证券(DictCodeList.科目类别_资产类, "1202", "买入返售金融资产", "返售证券"),
	
	资产类_买入返售金融资产_返售票据(DictCodeList.科目类别_资产类, "1212", "买入返售金融资产", "返售票据"),

	资产类_买入返售金融资产_返售其他资产(DictCodeList.科目类别_资产类, "1213", "买入返售金融资产", "返售其他资产"),

	资产类_衍生金融资产_外汇买卖(DictCodeList.科目类别_资产类, "3201", "衍生金融资产", "外汇买卖"),

	资产类_衍生金融资产_远期投资(DictCodeList.科目类别_资产类, "3101", "衍生金融资产", "远期投资"),

	资产类_衍生金融资产_其他衍生工具(DictCodeList.科目类别_资产类, "3102", "衍生金融资产", "其他衍生工具"),

	资产类_交易性金融资产(DictCodeList.科目类别_资产类, "1502", "交易性金融资产", "交易性金融资产"),
	
	资产类_可供出售金融资产(DictCodeList.科目类别_资产类, "1505", "可供出售金融资产", "可供出售金融资产"),

	资产类_可供出售国家债券投资减值准备(DictCodeList.科目类别_资产类, "1505100005", "可供出售金融资产", "可供出售国家债券投资减值准备"),

	资产类_可供出售中央银行债券投资减值准备(DictCodeList.科目类别_资产类, "1505200005", "可供出售金融资产", "可供出售中央银行债券投资减值准备"),

	资产类_可供出售政策性银行债券投资减值准备(DictCodeList.科目类别_资产类, " 1505300005", "可供出售金融资产", "可供出售政策性银行债券投资减值准备"),

	资产类_可供出售其他金融债券投资减值准备(DictCodeList.科目类别_资产类, " 1505400005", "可供出售金融资产", "可供出售其他金融债券投资减值准备"),

	资产类_可供出售其他债券减值准备(DictCodeList.科目类别_资产类, " 1505500005", "可供出售金融资产", "可供出售其他债券减值准备"),
	
	资产类_可供出售股权投资减值准备(DictCodeList.科目类别_资产类, " 1505600005", "可供出售金融资产", "可供出售股权投资减值准备"),
	
	资产类_可供出售非标准化债权投资减值准备(DictCodeList.科目类别_资产类, " 1505700005", "可供出售金融资产", "可供出售非标准化债权投资减值准备"),

	资产类_可供出售其他金融资产减值准备(DictCodeList.科目类别_资产类, " 1505990005", "可供出售金融资产", "可供出售其他金融资产减值准备"),

	资产类_应收款项类投资(DictCodeList.科目类别_资产类, "1506", "应收款项类投资", "应收款项类投资"),
	
	资产类_持有至到期投资(DictCodeList.科目类别_资产类, "1503", "持有至到期投资", "持有至到期投资"),

	资产类_应收利息(DictCodeList.科目类别_资产类, "1204", "应收利息", "应收利息"),

	资产类_应收证券清算款(DictCodeList.科目类别_资产类, "0", "应收证券清算款", "证券清算款"),

	资产类_应收股利(DictCodeList.科目类别_资产类, "1203", "应收股利", "应收股利"),

	资产类_应收申购款(DictCodeList.科目类别_资产类, "0", "应收申购款", "应收申购款"),

	资产类_递延所得税资产(DictCodeList.科目类别_资产类, "0", "递延所得税资产", "递延所得税资产"),
	
	资产类_其他资产_其他应收款(DictCodeList.科目类别_资产类, "1221", "其他资产", "其他应收款"),

	资产类_其他资产_待摊费用(DictCodeList.科目类别_资产类, "1501", "其他资产", "待摊费用"),

	资产类_其他资产_持有至到期投资减值准备(DictCodeList.科目类别_资产类, "1504", "其他资产", "持有至到期投资减值准备"),

	资产类_其他资产_应收款项金融资产减值准备(DictCodeList.科目类别_资产类, "1507", "其他资产", "应收款项金融资产减值准备"),

	资产类_其他资产_应付账款(DictCodeList.科目类别_资产类, "3004", "其他资产", "应付账款"),

	负债类_拆入资金(DictCodeList.科目类别_负债类, "2003", "拆入资金", "拆入款项"),

	负债类_交易性金融负债(DictCodeList.科目类别_负债类, "2101", "交易性金融负债", "交易性金融负债"),

	负债类_衍生金融负债_外汇买卖(DictCodeList.科目类别_负债类, "3201", "衍生金融负债", "外汇买卖"),

	负债类_衍生金融负债_远期投资(DictCodeList.科目类别_负债类, "3101", "衍生金融负债", "远期投资"),

	负债类_衍生金融负债_其他衍生工具(DictCodeList.科目类别_负债类, "3102", "衍生金融负债", "其他衍生工具"),

	负债类_卖出回购金融资产款_证券款(DictCodeList.科目类别_负债类, "2202", "卖出回购金融资产款", "证券款"),

	负债类_卖出回购金融资产款_票据款(DictCodeList.科目类别_负债类, "2212", "卖出回购金融资产款", "票据款"),

	负债类_卖出回购金融资产款_其他资产(DictCodeList.科目类别_负债类, "2213", "卖出回购金融资产款", "其他资产"),

	负债类_应付管理人报酬(DictCodeList.科目类别_负债类, "2206", "应付管理人报酬", "应付管理人报酬"),

	负债类_应付交易费用(DictCodeList.科目类别_负债类, "2209", "应付交易费用", "应付交易费用"),

	负债类_应付托管费(DictCodeList.科目类别_负债类, "2207", "应付托管费", "应付托管费"),

	负债类_应付销售服务费(DictCodeList.科目类别_负债类, "2208", "应付销售服务费", "应付销售服务费"),
	
	负债类_应交税费(DictCodeList.科目类别_负债类, "2222", "应交税费", "应交税费"),

	负债类_应付利息(DictCodeList.科目类别_负债类, "2231", "应付利息", "应付利息"),

	负债类_应付赎回款(DictCodeList.科目类别_负债类, "2203", "应付赎回款", "应付赎回款"),

	负债类_应付证券清算款(DictCodeList.科目类别_负债类, "0", "应付证券清算款", "证券清算款"),

	负债类_应付利润(DictCodeList.科目类别_负债类, "2232", "应付利润", "应付利润"),
	
	负债类_递延所得税负债(DictCodeList.科目类别_负债类, "0", "递延所得税负债", "递延所得税负债"),

	负债类_其他负债_应付赎回费(DictCodeList.科目类别_负债类, "2204", "其他负债", "应付赎回费"),

	负债类_其他负债_预提费用(DictCodeList.科目类别_负债类, "2501", "预提费用", "预提费用"),

	负债类_其他负债_其他应付款(DictCodeList.科目类别_负债类, "2241", "其他负债", "其他应付款"),
	
	负债类_其他负债_应付账款(DictCodeList.科目类别_负债类, "3004", "其他负债", "应付账款"),


	所有者权益类_实收资本(DictCodeList.科目类别_所有者权益类, "4001", "实收资本", "实收资本"),

	所有者权益类_资本公积(DictCodeList.科目类别_所有者权益类, "4002", "资本公积", "资本公积"),

	所有者权益类_未分配利润_本期利润(DictCodeList.科目类别_所有者权益类, "4103", "未分配利润", "本期利润"),

	所有者权益类_未分配利润_利润分配(DictCodeList.科目类别_所有者权益类, "4104", "未分配利润", "利润分配"),
	
	所有者权益类_未分配利润_利息收入(DictCodeList.科目类别_所有者权益类, "6011", "未分配利润", "利息收入"),

	所有者权益类_未分配利润_公允价值变动损益(DictCodeList.科目类别_所有者权益类, "6101", "未分配利润", "公允价值变动损益"),

	所有者权益类_未分配利润_投资收益(DictCodeList.科目类别_所有者权益类, "6111", "未分配利润", "投资收益"),

	所有者权益类_未分配利润_汇兑损益(DictCodeList.科目类别_所有者权益类, "6112", "未分配利润", "汇兑损益"),

	所有者权益类_未分配利润_其他收入(DictCodeList.科目类别_所有者权益类, "6302", "未分配利润", "其他收入"),

	所有者权益类_未分配利润_管理人报酬(DictCodeList.科目类别_所有者权益类, "6403", "未分配利润", "管理人报酬"),

	所有者权益类_未分配利润_托管费(DictCodeList.科目类别_所有者权益类, "6404", "未分配利润", "托管费"),

	所有者权益类_未分配利润_销售服务费(DictCodeList.科目类别_所有者权益类, "6406", "未分配利润", "销售服务费"),

	所有者权益类_未分配利润_交易费用(DictCodeList.科目类别_所有者权益类, "6407", "未分配利润", "交易费用"),

	所有者权益类_未分配利润_利息支出(DictCodeList.科目类别_所有者权益类, "6411", "未分配利润", "利息支出"),

	所有者权益类_未分配利润_资产损失(DictCodeList.科目类别_所有者权益类, "6501", "未分配利润", "资产损失"),

	所有者权益类_未分配利润_其他费用(DictCodeList.科目类别_所有者权益类, "6605", "未分配利润", "其他费用"),

	所有者权益类_未分配利润_以前年度损益调整(DictCodeList.科目类别_所有者权益类, "6901", "未分配利润", "以前年度损益调整"),

	所有者权益类_未分配利润_税金及附加(DictCodeList.科目类别_所有者权益类, "6701", "税金及附加", "税金及附加"),
	
	所有者权益类_其他综合收益(DictCodeList.科目类别_所有者权益类, "4003", "其他综合收益", "其他综合收益"),

	


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

	DisAssetLiabilityStatementEnum(DictCodeList dictCodeList, String value, String assetName, String subjectName) {
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
		DisAssetLiabilityStatementEnum[] values = DisAssetLiabilityStatementEnum.values();
		for (DisAssetLiabilityStatementEnum item : values) {
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
	public static List<DisAssetLiabilityStatementEnum> enumsfromValue(String value) {
		List<DisAssetLiabilityStatementEnum> list = Lists.newArrayList();
		for (DisAssetLiabilityStatementEnum item : values()) {
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

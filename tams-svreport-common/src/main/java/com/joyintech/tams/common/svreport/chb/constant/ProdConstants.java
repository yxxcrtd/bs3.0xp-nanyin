package com.joyintech.tams.common.svreport.chb.constant;
/**
 * 描述:产品要素常量类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-22
 */
public class ProdConstants {
	//募集方式
	public final static String RAISEMODE_GM="01"; //公募
	public final static String RAISEMODE_SM="02"; //私募
	
	//投资者类型
	public final static String IVSTORIENTATION_GDSY="01"; //固定收益类
	public final static String IVSTORIENTATION_QYL="02"; //权益类
	public final static String IVSTORIENTATION_SPJRP="03";  //03商品及金融衍生品类
	public final static String IVSTORIENTATION_HHL="04"; //04 混合类
	
	//产品运行模式:01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  
	public final static String OPERATEMODE_FBSJZX="01"; // 01 封闭式净值型
	public final static String OPERATEMODE_FBSFJZX="02"; //02 封闭式非净值型
	public final static String OPERATEMODE_KFSJZX="03"; //03 开放式净值型
	public final static String OPERATEMODE_KFSFJZX="04"; //04 开放式非净值型	

	//产品收益标识:01 保证收益/02 保本浮动收益/03 非保本浮动收益
	public final static String PROFITTYPE_BZSY="01"; //01 保证收益
	public final static String PROFITTYPE_BBFDSY="02"; //02 保本浮动收益
	public final static String PROFITTYPE_FBBFDSY="03"; //03 非保本浮动收益	
	
	//目标客户类型
	public final static String SALETARGET_SRYHKH="01";	//01 私人银行客户专属
	public final static String SALETARGET_JGKH="02";	//02 机构客户专属
	public final static String SALETARGET_YBGRKH="03";	//03 一般个人客户
	public final static String SALETARGET_JRTY="04";	//04 金融同业专属
	public final static String SALETARGET_GZCJZ="05";	//05 高资产净值客户

	//从母行划转的产品
	public final static String SALETARGET_CMHHZCP="01";	//01 从母行划转的产品

	//是否养老理财产品
	public final static String IS_YLLCCP="01";	//01


	//期限品种:01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
	public final static String TERMTYPE_U1D="01";	//01 T+0
	public final static String TERMTYPE_U7D="02";	//02 7天（含）以内
	public final static String TERMTYPE_U1M="03";	//03 7天-1个月（含）
	public final static String TERMTYPE_U3M="04";	//04 1-3个月（含）
	public final static String TERMTYPE_U6M="05";	//05 3-6 25个月(含)
	public final static String TERMTYPE_U12M="06";	//06 6-12个月（含）
	public final static String TERMTYPE_U3Y="08";	//07 1-3年（含）--> 08 1-3年（含）
	public final static String TERMTYPE_B3Y="09";	//08 3年以上 --> 09 3年以上
	
	//存续状态
	public final static String DURATIONFLAG_HOLD="01";	//01 存续
	public final static String DURATIONFLAG_TEMINATION="02";	//02 终止
	public final static String DURATIONFLAG_PUB="03";	//03 未起息
	
	//投资者类别 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构/05保险业金融机构/06信托公司/07证券公司/08基金公司/09金融机构资产管理产品
	public final static String ORDIDENTIFIED_ZRR="01";
	public final static String ORDIDENTIFIED_FJRJG="02";
	public final static String ORDIDENTIFIED_YHJRJG="03";
	public final static String ORDIDENTIFIED_QTJRJG="04";
	public final static String ORDIDENTIFIED_BXJRJG="05";
	public final static String ORDIDENTIFIED_XTGS="06";
	public final static String ORDIDENTIFIED_ZQGS="07";
	public final static String ORDIDENTIFIED_JJGS="08";
	public final static String ORDIDENTIFIED_JRJGZCGLCP="09";


	//币种
	public final static String CCY_CNY="CNY";
	public final static String CCY_USD="USD";
	public final static String CCY_EUR="EUR";
	public final static String CCY_OTH="OTH";
	
}

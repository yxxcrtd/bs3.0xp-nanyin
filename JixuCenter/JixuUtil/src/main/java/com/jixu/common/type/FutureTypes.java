package com.jixu.common.type;

import java.util.HashMap;

public class FutureTypes {
	
	//期货交易所
	public final static String SHANGHAI_FUTURES_EXCHANGE="EXG0006";        //上海期货交易所
	public final static String ZHENGZHOU_COMMODITY_EXCHANGE="EXG0007";     //郑州商品交易所
	public final static String DALIAN_COMMODITY_EXCHANGE="EXG0008";        //大连商品交易所
	public final static String CHINA_FINANCIAL_FUTURES_EXCHANGE="EXG0009"; //中国金融期货交易所
	
	//期货类型
	public final static int STOCK_INDEX_FUTURE=11; //股指期货
	public final static int BOND_FUTURE=12;	      //国债期货
	public final static int COMMODITY_FUTURE=13;   //商品期货
	
	
	//合约标的
	public final static int HS_300_INDEX=101;  //沪深300股指
	public final static int ZZ_500_INDEX=102;  //中证500股指
	public final static int SZ_50_INDEX=103;   //上证50股指
	
	public final static int TWO_YEARS_BOND=201;  //2年期国债
	public final static int FIVE_YEARS_BOND=202; //5年期国债
	public final static int TEN_YEARS_BOND=203;  //10年期国债
	
	public final static int COMMODITY_CN=301;   //商品期货铜
	public final static int COMMODITY_AL=302;   //商品期货铝
	public final static int COMMODITY_ZN=303;   //商品期货锌
	public final static int COMMODITY_PB=304;   //商品期货铅
	public final static int COMMODITY_NI=305;   //商品期货镍
	public final static int COMMODITY_SN=306;   //商品期货锡
	public final static int COMMODITY_AU=307;   //商品期货黄金
	public final static int COMMODITY_AG=308;   //商品期货白银
	public final static int COMMODITY_RB=309;   //商品期货螺纹钢
	public final static int COMMODITY_WR=310;   //商品期货线材
	public final static int COMMODITY_HC=311;   //商品期货热轧卷板
	public final static int COMMODITY_SC=312;   //商品期货原油
	public final static int COMMODITY_FU=313;   //商品期货燃料油
	public final static int COMMODITY_BU=314;   //商品期货沥青
	public final static int COMMODITY_RU=315;   //商品期货天然橡胶
	public final static int COMMODITY_SP=316;   //商品期货纸浆
	public final static int COMMODITY_WH=317;   //商品期货强麦
	public final static int COMMODITY_PM=318;   //商品期货普麦
	public final static int COMMODITY_CF=319;   //商品期货棉花
	public final static int COMMODITY_SR=320;   //商品期货白糖
	public final static int COMMODITY_OI=321;   //商品期货菜籽油
	public final static int COMMODITY_RI=322;   //商品期货早籼稻
	public final static int COMMODITY_RS=323;   //商品期货油菜籽
	public final static int COMMODITY_RM=324;   //商品期货菜籽粕
	public final static int COMMODITY_JR=325;   //商品期货粳稻
	public final static int COMMODITY_CY=326;   //商品期货棉纱
	public final static int COMMODITY_AP=327;   //商品期货苹果
	public final static int COMMODITY_CJ=328;   //商品期货红枣
	public final static int COMMODITY_PTA=329;  //商品期货精对苯二甲酸
	public final static int COMMODITY_MA=330;   //商品期货甲醇
	public final static int COMMODITY_FG=331;   //商品期货玻璃
	public final static int COMMODITY_ZC=332;   //商品期货动力煤
	public final static int COMMODITY_SF=333;   //商品期货硅铁
	public final static int COMMODITY_SM=334;   //商品期货锰硅
	public final static int COMMODITY_C=335;    //商品期货玉米
	public final static int COMMODITY_CS=336;   //商品期货玉米淀粉
	public final static int COMMODITY_A=337;    //商品期货黄大豆1
	public final static int COMMODITY_B=338;    //商品期货黄大豆2
	public final static int COMMODITY_M=339;    //商品期货豆粕
	public final static int COMMODITY_Y=340;    //商品期货豆油
	public final static int COMMODITY_P=341;    //商品期货棕榈油
	public final static int COMMODITY_FB=342;   //商品期货纤维板
	public final static int COMMODITY_BB=343;   //商品期货胶合板
	public final static int COMMODITY_JD=344;   //商品期货鸡蛋
	public final static int COMMODITY_I=345;    //商品期货聚乙烯
	public final static int COMMODITY_V=346;    //商品期货聚氯乙烯
	public final static int COMMODITY_PP=347;   //商品期货聚丙烯
	public final static int COMMODITY_J=348;    //商品期货焦炭
	public final static int COMMODITY_JM=349;   //商品期货焦煤
	public final static int COMMODITY_EG=350;   //商品期货乙二醇
	
	
	public static HashMap<String, String> exchangeHM=new HashMap<String, String>();
	
	public final static HashMap<String, String> getExchangeTypes() {
		exchangeHM.put(CHINA_FINANCIAL_FUTURES_EXCHANGE, "中国金融期货交易所");
		exchangeHM.put(SHANGHAI_FUTURES_EXCHANGE, "上海期货交易所");
		exchangeHM.put(ZHENGZHOU_COMMODITY_EXCHANGE, "郑州商品交易所");
		exchangeHM.put(DALIAN_COMMODITY_EXCHANGE, "大连商品交易所");
		return exchangeHM;
	}
	
	
	public final static HashMap<Integer, String> getFutureTypes(String exchange) {
		HashMap<Integer, String> futureTypesHM=new HashMap<Integer, String>();
		if(exchange.equals(CHINA_FINANCIAL_FUTURES_EXCHANGE)){
			futureTypesHM.put(STOCK_INDEX_FUTURE, "股指期货");
			futureTypesHM.put(BOND_FUTURE, "国债期货");
		}else{
			futureTypesHM.put(COMMODITY_FUTURE, "商品期货");
		}
		return futureTypesHM;
	}
	
	
	public final static HashMap<Integer, String> getFutureTargets(String exchange,int futureType) {
		HashMap<Integer, String> futureTargetsHM=new HashMap<Integer, String>();
		switch(futureType){
		case STOCK_INDEX_FUTURE:
			futureTargetsHM.put(HS_300_INDEX, "沪深300股指");
			futureTargetsHM.put(ZZ_500_INDEX, "中证500股指");
			futureTargetsHM.put(SZ_50_INDEX, "上证50股指");
			break;
		case BOND_FUTURE:
			futureTargetsHM.put(TWO_YEARS_BOND, "2年期国债");
			futureTargetsHM.put(FIVE_YEARS_BOND, "5年期国债");
			futureTargetsHM.put(TEN_YEARS_BOND, "10年期国债");
			break;
		case COMMODITY_FUTURE:
			if(exchange.equals(SHANGHAI_FUTURES_EXCHANGE)){
				futureTargetsHM.put(COMMODITY_CN, "铜");
				futureTargetsHM.put(COMMODITY_AL, "铝");
				futureTargetsHM.put(COMMODITY_ZN, "锌");
				futureTargetsHM.put(COMMODITY_PB, "铅");
				futureTargetsHM.put(COMMODITY_NI, "镍");
				futureTargetsHM.put(COMMODITY_SN, "锡");
				futureTargetsHM.put(COMMODITY_AU, "黄金");
				futureTargetsHM.put(COMMODITY_AG, "白银");
				futureTargetsHM.put(COMMODITY_RB, "螺纹钢");
				futureTargetsHM.put(COMMODITY_WR, "线材");
				futureTargetsHM.put(COMMODITY_HC, "热轧卷板");
				futureTargetsHM.put(COMMODITY_SC, "原油");
				futureTargetsHM.put(COMMODITY_FU, "燃料油");
				futureTargetsHM.put(COMMODITY_BU, "沥青");
				futureTargetsHM.put(COMMODITY_RU, "天然橡胶");
				futureTargetsHM.put(COMMODITY_SP, "纸浆");
			}else if(exchange.equals(ZHENGZHOU_COMMODITY_EXCHANGE)){
				futureTargetsHM.put(COMMODITY_WH, "强麦");
				futureTargetsHM.put(COMMODITY_PM, "普麦");
				futureTargetsHM.put(COMMODITY_CF, "棉花");
				futureTargetsHM.put(COMMODITY_SR, "白糖");
				futureTargetsHM.put(COMMODITY_OI, "菜籽油");
				futureTargetsHM.put(COMMODITY_RI, "早籼稻");
				futureTargetsHM.put(COMMODITY_RS, "油菜籽");
				futureTargetsHM.put(COMMODITY_RM, "菜籽粕");
				futureTargetsHM.put(COMMODITY_JR, "粳稻");
				futureTargetsHM.put(COMMODITY_CY, "棉纱");
				futureTargetsHM.put(COMMODITY_AP, "苹果");
				futureTargetsHM.put(COMMODITY_CJ, "红枣");
				futureTargetsHM.put(COMMODITY_PTA, "精对苯二甲酸");
				futureTargetsHM.put(COMMODITY_MA, "甲醇");
				futureTargetsHM.put(COMMODITY_FG, "玻璃");
				futureTargetsHM.put(COMMODITY_ZC, "动力煤");
				futureTargetsHM.put(COMMODITY_SF, "硅铁");
				futureTargetsHM.put(COMMODITY_SM, "锰硅");
			}else if(exchange.equals(DALIAN_COMMODITY_EXCHANGE)){
				futureTargetsHM.put(COMMODITY_C, "玉米");
				futureTargetsHM.put(COMMODITY_CS, "玉米淀粉");
				futureTargetsHM.put(COMMODITY_A, "黄大豆1");
				futureTargetsHM.put(COMMODITY_B, "黄大豆2");
				futureTargetsHM.put(COMMODITY_M, "豆粕");
				futureTargetsHM.put(COMMODITY_Y, "豆油");
				futureTargetsHM.put(COMMODITY_P, "棕榈油");
				futureTargetsHM.put(COMMODITY_FB, "纤维板");
				futureTargetsHM.put(COMMODITY_BB, "胶合板");
				futureTargetsHM.put(COMMODITY_JD, "鸡蛋");
				futureTargetsHM.put(COMMODITY_I, "聚乙烯");
				futureTargetsHM.put(COMMODITY_V, "聚氯乙烯");
				futureTargetsHM.put(COMMODITY_PP, "聚丙烯");
				futureTargetsHM.put(COMMODITY_J, "焦炭");
				futureTargetsHM.put(COMMODITY_JM, "焦煤");
				futureTargetsHM.put(COMMODITY_EG, "乙二醇");
			}
		}
		return futureTargetsHM;
	}
}

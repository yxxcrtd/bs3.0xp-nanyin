package com.joyintech.tams.svreport.chb.transmonthreport.dto;

public class ChbTransMonthReportPrdConstants {
	// 产品端理财产品转型情况月度统计表字段枚举值
    public final static String GMLCCP="GMLCCP";  //公募理财产品
    public final static String SMLCCP="SMLCCP";  //私募理财产品
    public final static String GDSYL="GDSYL";       //固定收益类
    public final static String QYL="QYL";  //    2.2 权益类
    public final static String SPJJRYSPL="SPJJRYSPL";  //    2.3 商品及金融衍生品类
    public final static String HHL="HHL";  //    2.4 混合类
    public final static String FBSJZX="FBSJZX";  //    3.1.1 封闭式净值型
    public final static String KFSJZX="KFSJZX";  //    3.1.2 开放式净值型
    public final static String XJGLLCP="XJGLLCP";  //    其中：3.1.2.a 现金管理类产品
    public final static String FBQZBNYSDDQKFCP="FBQZBNYSDDQKFCP";  //          3.1.2.b 封闭期在半年以上的定期开放式产品
    public final static String FBSFJZX="FBSFJZX";  //    3.2.1 封闭式非净值型
    public final static String KFSFJZX="KFSFJZX";  //    3.2.2 开放式非净值型
    public final static String FBSSGYYN="FBSSGYYN";  //    4.1.1 3个月（含）以内
    public final static String FBSSLGY="FBSSLGY";  //    4.1.2 3-6个月(含)
    public final static String FBSLSGY="FBSLSGY";  //    4.1.3 6-12个月（含）
    public final static String FBSYSN="FBSYSN";  //    4.1.4 1-3年（含）
    public final static String FBSSNYS="FBSSNYS";  //    4.1.5 3年以上
    public final static String KFSMR="KFSMR";  //    4.2.1 每日
    public final static String KFSQT="KFSQT";  //    4.2.2 7天（含）以内
    public final static String KFSQTYGY="KFSQTYGY";  //    4.2.3 7天-1个月（含）
    public final static String KFSYSY="KFSYSY";  //    4.2.4 1-3个月（含）
    public final static String KFSSLY="KFSSLY";  //    4.2.5 3-6个月(含)
    public final static String KFSLSY="KFSLSY";  //    4.2.6 6-12个月（含）
    public final static String KFSYSN="KFSYSN";  //    4.2.7 1-3年（含）
    public final static String KFSSNYS="KFSSNYS";  //    4.2.8 3年以上
    
    
    public final static String SUM_MJFS="SUM_MJFS";      //合计:1.按募集方式划分
    public final static String SUM_TZXZ="SUM_TZXZ";      //合计:2.按投资性质划分
    public final static String SUM_YZMS="SUM_YZMS";      //合计:3.按产品运作模式划分
    public final static String SUM_FBSCP="SUM_FBSCP";    //合计:4.1 封闭式产品
    public final static String SUM_KFSCP="SUM_KFSCP";    //合计:4.2 开放式产品
    public final static String SUM_QX="SUM_QX";          //合计:4.按期限划分
    
    // 资产端理财产品转型情况月度统计表字段枚举值
    public final static String AMT1_1="AMT1_1";//1.1合计
    public final static String AMT1_1_A="AMT1_1_a";//1.1.a信用债
    public final static String AMT1_1_A_A="AMT1_1_a_a";//1.1.a.a PPN等低流动性债券
    public final static String AMT1_1_B="AMT1_1_b";//1.1.b 利率债
    public final static String AMT1_1_C="AMT1_1_c";//1.1.c 固定收益类公募基金
    public final static String AMT1_2="AMT1_2";//1.2 加权剩余期限
    
    
    public final static String AMT2_1="AMT2_1";//2.1 通过信托贷款和信托产品
    public final static String AMT2_2="AMT2_2";//2.2 通过证券期货经营机构资管产品
    public final static String AMT2_3="AMT2_3";//2.3 通过保险资管产品
    public final static String AMT2_4="AMT2_4";//2.4 通过委托贷款
    public final static String AMT2_5="AMT2_5";//2.5 通过债权融资类产品
    public final static String AMT2_6="AMT2_6";//2.6 通过其他形式
    public final static String AMT2_7="AMT2_7";//2.7 直接投资
    public final static String AMT2_8="AMT2_8";//2.8 合计
    public final static String AMT2_9="AMT2_9";//2.9 加权剩余期限
    
    public final static String AMT3_1="AMT3_1";//3.1 股票类资产合计
    
    public final static String AMT4_1="AMT4_1";//4.1 合计
    public final static String AMT4_1_A="AMT4_1_a";//4.1.a 政府出资产业投资基金
    public final static String AMT4_1_B="AMT4_1_b";//4.1.b 创业投资基金
    public final static String AMT4_2="AMT4_2";//4.2 加权剩余期限
    
    public final static String AMT5_1="AMT5_1";//5.1 合计
    public final static String AMT5_1_A="AMT5_1_a";//5.1.a 优先股
    public final static String AMT5_1_B="AMT5_1_b";//5.1.b 永续债（债性）
    public final static String AMT5_1_C="AMT5_1_c";//5.1.c 永续债（股性）
    public final static String AMT5_1_D="AMT5_1_d";//5.1.d 二级资本债
    public final static String AMT5_2="AMT5_2";//5.2 加权剩余期限
    
    
    public final static String AMT6_1="AMT6_1";//6.1 合计
    public final static String AMT6_1_A="AMT6_1_a";//6.1.a 拆放同业及买入返售
    public final static String AMT6_1_B="AMT6_1_b";//6.1.b 现金及银行存款
    public final static String AMT6_1_C="AMT6_1_c";//6.1.c 其他公募基金
    public final static String AMT6_1_D="AMT6_1_d";//6.1.d 商品及金融衍生产品
    public final static String AMT6_1_E="AMT6_1_e";//6.1.e QDII
     public final static String AMT6_2="AMT6_2";//6.2 加权剩余期限
    
     public final static String AMT7="AMT7";//7 合计
}

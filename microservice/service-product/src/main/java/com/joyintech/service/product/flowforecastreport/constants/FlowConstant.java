/*
 * 文件名：FlowForecastConstant.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.flowforecastreport.constants;


/**
 * 常量定义
 * @author weipeng
 * @version 1.0 2019年8月13日
 * @see FlowConstant
 * @since 1.0
 */
public class FlowConstant {
    /**
     * 金融产品代码-日日升
     */
    public static final String FINPROD_ID_RRS = "F16_0900099C93";
    /**
     * 金融产品代码-月月升
     */
    public static final String FINPROD_ID_YYS = "F16_0800099C91";
    /**
     * 金融产品代码-鑫鑫向荣
     */
    public static final String FINPROD_ID_XXXR = "F16_1100099C88";
    /**
     * 金融产品代码-鑫鑫向荣B款
     */
    public static final String FINPROD_ID_XXXR_B = "F16_1300099C89";
    /**
     * 金融产品代码-理财宝
     */
    public static final String FINPROD_ID_LCB = "F16_1801LB0001";
    /**
     * 金融产品代码-其他
     */
    public static final String FINPROD_ID_QT = "QT";
    
    /**
     * 金融产品类型-逆回购
     */
    public static final String FINPROD_TYPE_REPON = "01";
    
    /**
     * 金融产品类型-正回购
     */
    public static final String FINPROD_TYPE_REPO = "02";
    
    /**
     * 现金流信息用金融产品类型-债券
     */
    public static final String FINPROD_TYPE_SEC = "01";
    
    /**
     * 区间下标_区间1
     */
    public static final String areaIndex_1 = "01";
    /**
     * 区间下标_区间2
     */
    public static final String areaIndex_2 = "02";
    /**
     * 区间下标_区间3
     */
    public static final String areaIndex_3 = "03";
    /**
     * 区间下标_区间4
     */
    public static final String areaIndex_4 = "04";
    /**
     * 区间下标_区间5
     */
    public static final String areaIndex_5 = "05";
    /**
     * 区间下标_区间6
     */
    public static final String areaIndex_6 = "06";
    /**
     * 目标客户类型_个人
     */
    public static final String 目标客户类型_个人 = "GR";
    /**
     * 目标客户类型_机构
     */
    public static final String 目标客户类型_机构 = "JG";
    
    //==================================树形结构NODE节点值定义start==============================
    
    /**
     * ROOT根节点
     */
    public static final String NODE_ROOT = "root";
    
    /**
     * 一级流动性备付合计
     */
    public static final String NODE_一级流动性备付合计 = "0101";
    /**
     * 现金
     */
    public static final String NODE_现金 = "010101";
    /**
     * 债券逆回购净额
     */
    public static final String NODE_债券逆回购净额 = "010102";
    /**
     * 债券逆回购余额
     */
    public static final String NODE_债券逆回购余额 = "01010201";
    /**
     * 债券正回购余额
     */
    public static final String NODE_债券正回购余额 = "01010202";
    /**
     * 货基
     */
    public static final String NODE_货基 = "010103";
    
    /**
     * 二级流动性备付合计
     */
    public static final String NODE_二级流动性备付合计 = "0102";
    /**
     * 二级流动性备付合计-国债
     */
    public static final String NODE_国债 = "010201";
    /**
     * 二级流动性备付合计-政策性金融债净额
     */
    public static final String NODE_政策性金融债净额 = "010202";
    /**
     * 二级流动性备付合计-政策性金融债净额-政策性金融债余额
     */
    public static final String NODE_政策性金融债余额 = "01020201";
    /**
     * 二级流动性备付合计-政策性金融债净额-已质押政策性金融债
     */
    public static final String NODE_已质押政策性金融债 = "01020202";
    /**
     * 二级流动性备付合计-同业存单净额
     */
    public static final String NODE_同业存单净额 = "010203";
    /**
     * 二级流动性备付合计-同业存单净额-同业存单余额
     */
    public static final String NODE_同业存单余额 = "01020301";
    /**
     * 二级流动性备付合计-同业存单净额-已质押同业存单
     */
    public static final String NODE_已质押同业存单 = "01020302";
    
    /**
     * 一二级流动性备付合计
     */
    public static final String NODE_一二级流动性备付合计 = "0103";
    /**
     * 产品规模
     */
    public static final String NODE_产品规模 = "0104";
    /**
     * 一级流动性备付占比
     */
    public static final String NODE_一级流动性备付占比 = "0105";
    /**
     * 二级流动性备付占比
     */
    public static final String NODE_二级流动性备付占比 = "0106";
    
    /**
     * 资金总流入
     */
    public static final String NODE_资金总流入 = "0201";
    /**
     * 资金总流入-资金到期回款
     */
    public static final String NODE_资金到期回款 = "020101";
    /**
     * 资金总流入-资金到期回款-非标资产
     */
    public static final String NODE_非标资产 = "02010101";
    /**
     * 资金总流入-资金到期回款-逆回购到期
     */
    public static final String NODE_逆回购到期 = "02010102";
    /**
     * 资金总流入-资金到期回款-自主投资债券
     */
    public static final String NODE_自主投资债券 = "02010103";
    /**
     * 资金总流入-资金到期回款-其他
     */
    public static final String NODE_其他 = "02010104";
    /**
     * 资金总流入-开放式产品净申赎预测
     */
    public static final String NODE_开放式产品净申赎预测 = "020102";
    /**
     * 资金总流入-开放式产品净申赎预测-日日升
     */
    public static final String NODE_开放式产品净申赎预测_日日升 = "02010201";
    /**
     * 资金总流入-开放式产品净申赎预测-月月升
     */
    public static final String NODE_开放式产品净申赎预测_月月升 = "02010202";
    /**
     * 资金总流入-开放式产品净申赎预测-鑫鑫向荣
     */
    public static final String NODE_开放式产品净申赎预测_鑫鑫向荣 = "02010203";
    /**
     * 资金总流入-开放式产品净申赎预测-鑫鑫向荣B
     */
    public static final String NODE_开放式产品净申赎预测_鑫鑫向荣B = "02010204";
    /**
     * 资金总流入-开放式产品净申赎预测-理财宝
     */
    public static final String NODE_开放式产品净申赎预测_理财宝 = "02010205";
    /**
     * 资金总流入-开放式产品净申赎预测-净值型产品开放期净申赎
     */
    public static final String NODE_净值型产品开放期净申赎 = "02010206";
    /**
     * 资金总流入-期次型/定开净值型产品募集预测
     */
    public static final String NODE_期次型定开净值型产品募集预测 = "020103";
    /**
     * 资金总流入-期次型/定开净值型产品募集预测-个人期次型产品
     */
    public static final String NODE_个人期次型产品 = "02010301";
    /**
     * 资金总流入-期次型/定开净值型产品募集预测-机构期次型产品
     */
    public static final String NODE_机构期次型产品 = "02010302";
    /**
     * 资金总流入-期次型/定开净值型产品募集预测-净值型产品（新发）
     */
    public static final String NODE_净值型产品_新发 = "02010303";
    /**
     * 资金总流出
     */
    public static final String NODE_资金总流出 = "0202";
    /**
     * 资金总流出-封闭式产品到期
     */
    public static final String NODE_封闭式产品到期 = "020201";
    /**
     * 资金总流出-封闭式产品到期-个人
     */
    public static final String NODE_封闭式产品到期_个人 = "02020101";
    /**
     * 资金总流出-封闭式产品到期-机构
     */
    public static final String NODE_封闭式产品到期_机构 = "02020102";
    /**
     * 资金总流出-正回购到期
     */
    public static final String NODE_正回购到期 = "020202";
    /**
     * 资金盈余/缺口
     */
    public static final String NODE_资金盈余缺口 = "0203";
    /**
     * 新增走款安排
     */
    public static final String NODE_新增走款安排 = "0204";
    /**
     * 新增走款安排-投融资处
     */
    public static final String NODE_投融资处 = "020401";
    /**
     * 新增走款安排-固收处
     */
    public static final String NODE_固收处 = "020402";
    /**
     * 新增走款安排-组合投资处
     */
    public static final String NODE_组合投资处 = "020403";
    /**
     * 走款后资金盈余/缺口
     */
    public static final String NODE_走款后资金盈余缺口 = "0205";
    
    //==================================树形结构NODE节点值定义end==============================
}

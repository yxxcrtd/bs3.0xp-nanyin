package com.joyintech.tams.svreport.pbc.assetposition.constants;

/**
 * 功能:人行报表资产头寸常量类
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月11日
 * @修改记录：
 */
public class PbcAssetPositionConstants {
     /**
     * BS2.0对人行资产类型映射
     */
    public static final String MAPPINGCODE_PBCASSETTYPE = "MAPPINGCODE_PBCASSETTYPE";
    /**
     * BS2.0对人行资产类型映射-融资人/发行人属性
     */
    public static final String PBCASSETTYPE_FAIN = "PBCASSETTYPE_FAIN";
    /**
     * BS2.0对人行资产类型映射-收受益权标识
     */
    public static final String PBCASSETTYPE_REPP = "PBCASSETTYPE_REPP";
    /**
     * BS2.0对人行资产类型映射-交易对手二级分类
     */
    public static final String PBCASSETTYPE_COUNTER = "PBCASSETTYPE_COUNTER";
    /**
     * BS2.0对人行资产类型映射-是否中央交易对手
     */
    public static final String PBCASSETTYPE_CENTER = "PBCASSETTYPE_CENTER";
    /**
    * BS2.0对人行资产类型映射-境内\境外
    */
   public static final String PBCASSETTYPE_COUNTRY = "PBCASSETTYPE_COUNTRY";
    /**
     * 映射类型-科目号 add by weiyuqiang 2019-03-12
     */
    public static final String MAPPINGCODE_SUBJECTNO = "MAPPINGCODE_SUBJECTNO";
    
    /**
     * TERM_TYPE_ONE-期限品种固定值(1)
     */
    public static final String TERM_TYPE_ONE = "TERM_TYPE_ONE";
    
    /**
     * TERM_TYPE_PROD-期限品种取产品剩余期限
     */
    public static final String TERM_TYPE_PROD = "TERM_TYPE_PROD";
    
    /**
     * TERM_TYPE_NULL-期限品种固定值(0)
     */
    public static final String TERM_TYPE_NULL = "TERM_TYPE_NULL";
    
    /**
     * 借贷方向_贷
     */
    public static final String BLG_TYPE_D = "D";
    
    /**
     * 借贷方向_借
     */
    public static final String BLG_TYPE_C = "C";
    
    /**
     * 头寸类型_资产：01
     */
    public static final String POSITION_TYPE_ZC = "01";
    
    /**
     * 头寸类型_负债：02
     */
    public static final String POSITION_TYPE_FZ = "02";
    
    /**
     * 头寸类型_科目余额：03
     */
    public static final String POSITION_TYPE_KMYE = "03";
    
    /**
     * 币种：其他
     */
    public static final String CCY_OTH = "OTH";
    
    /**
     * 资产一级类型:资本公积金
     */
    public static final String PBC_ASSETTYPE1_ZBGJJ = "ZBGJJ";
    
    /**
     * 资产一级类型:未分配理论
     */
    public static final String PBC_ASSETTYPE1_WFPLR = "WFPLR";
    
    public static final String PBC_ASSETTYPE1_P01= "P01";//现金
    
    public static final String PBC_ASSETTYPE1_P02= "P02";//存款

    public static final String PBC_ASSETTYPE1_P03= "P03";//可转让存单

    public static final String PBC_ASSETTYPE1_P04= "P04";//债务证券

    public static final String PBC_ASSETTYPE1_P05= "P05";//贷款

    public static final String PBC_ASSETTYPE1_P06= "P06";//资产收益权

    public static final String PBC_ASSETTYPE1_P07= "P07";//股权及特定目的载体份额

    public static final String PBC_ASSETTYPE1_P08= "P08";//金融衍生品

    public static final String PBC_ASSETTYPE1_P09= "P09";//黄金
    
    public static final String PBC_ASSETTYPE1_P10= "P10";//非金融资产
    
    public static final String PBC_ASSETTYPE1_P11= "P11";//应收账款
    
    public static final String PBC_ASSETTYPE1_P12= "P12";//应付账款
    
    public static final String PBC_ASSETTYPE1_P13= "P13";//其他负债
    
    public static final String PBC_ASSETTYPE2_X01= "X01";//现金
    
    public static final String PBC_ASSETTYPE2_C01= "C01";//境内存款
    
    public static final String PBC_ASSETTYPE2_C02= "C02";//境外存款
    
    public static final String PBC_ASSETTYPE2_K01= "K01";//同业存单
    
    public static final String PBC_ASSETTYPE2_K02= "K02";//大额存单
    
    public static final String PBC_ASSETTYPE2_S01= "S01";//政府债券
    
    public static final String PBC_ASSETTYPE2_S02= "S02";//中央银行债券
    
    public static final String PBC_ASSETTYPE2_S03= "S03";//政策性金融债券
    
    public static final String PBC_ASSETTYPE2_S04= "S04";//其他金融债券
    
    public static final String PBC_ASSETTYPE2_S05= "S05";//企业债券
    
    public static final String PBC_ASSETTYPE2_S06= "S06";//票据
    
    public static final String PBC_ASSETTYPE2_S07= "S07";//资产支持证券
    
    public static final String PBC_ASSETTYPE2_S08= "S08";//境外债券
    
    public static final String PBC_ASSETTYPE2_R01= "R01";//拆借和回购
    
    public static final String PBC_ASSETTYPE2_R02= "R02";//其他
    
    public static final String PBC_ASSETTYPE2_Q01= "Q01";//贷款收益权
    
    public static final String PBC_ASSETTYPE2_Q02= "Q02";//债务证券收益权
    
    public static final String PBC_ASSETTYPE2_Q03= "Q03";//应收账款收益权
    
    public static final String PBC_ASSETTYPE2_Q04= "Q04";//其他债权收益权
    
    public static final String PBC_ASSETTYPE2_Q05= "Q05";//股票收益权
    
    public static final String PBC_ASSETTYPE2_Q06= "Q06";//其他股权收益权
    
    public static final String PBC_ASSETTYPE2_Q07= "Q07";//物权资产收益权
    
    public static final String PBC_ASSETTYPE2_T01= "T01";//股票

    public static final String PBC_ASSETTYPE2_T02= "T02";//特定目的载体份额

    public static final String PBC_ASSETTYPE2_T03= "T03";//其他股权

    public static final String PBC_ASSETTYPE2_T04= "T04";//境外股权
    
    public static final String PBC_ASSETTYPE2_J01= "J01";//境内
    
    public static final String PBC_ASSETTYPE2_J02= "J02";//境外
    
    public static final String PBC_ASSETTYPE2_H01= "H01";//黄金
    
    public static final String PBC_ASSETTYPE2_F01= "F01";//非金融资产
    
    public static final String PBC_ASSETTYPE2_P11= "P11";//应收账款
    
    public static final String PBC_ASSETTYPE2_P12= "P12";//应付账款
    
    public static final String PBC_ASSETTYPE2_P13= "P13";//其他负债

    public static final String PBC_ASSETTYPE3_X01= "X01";//现金
    
    public static final String PBC_ASSETTYPE3_C01= "C01";//境内存款
    
    public static final String PBC_ASSETTYPE3_C02= "C02";//境外存款
    
    public static final String PBC_ASSETTYPE3_K01= "K01";//同业存单
    
    public static final String PBC_ASSETTYPE3_K02= "K02";//大额存单
    
    public static final String PBC_ASSETTYPE3_S01= "S01";//政府债券
    
    public static final String PBC_ASSETTYPE3_S02= "S02";//中央银行债券
    
    public static final String PBC_ASSETTYPE3_S03= "S03";//政策性金融债券
    
    public static final String PBC_ASSETTYPE3_S04= "S04";//其他金融债券
    
    public static final String PBC_ASSETTYPE3_S05= "S05";//企业债券
    
    public static final String PBC_ASSETTYPE3_S06= "S06";//票据
    
    public static final String PBC_ASSETTYPE3_S07= "S07";//资产支持证券
    
    public static final String PBC_ASSETTYPE3_S08= "S08";//境外债券
    
    public static final String PBC_ASSETTYPE3_L01= "L01";//住户
    
    public static final String PBC_ASSETTYPE3_L02= "L02";//广义政府
    
    public static final String PBC_ASSETTYPE3_L03= "L03";//非金融企业  
    
    public static final String PBC_ASSETTYPE3_L04= "L04";//银行业存款类金融机构
    
    public static final String PBC_ASSETTYPE3_L05= "L05";//银行业非存款类金融机构
    
    public static final String PBC_ASSETTYPE3_L06= "L06";//非银行业金融机构
    
    public static final String PBC_ASSETTYPE3_L07= "L07";//非银行业金融机构-中央交易对手
    
    public static final String PBC_ASSETTYPE3_L08= "L08";//银行非保本理财
    
    public static final String PBC_ASSETTYPE3_L09= "L09";//信托公司资管产品
    
    public static final String PBC_ASSETTYPE3_L10= "L10";//证券公司及其子公司资管产品
    
    public static final String PBC_ASSETTYPE3_L11= "L11";//基金管理公司及其子公司专户
    
    public static final String PBC_ASSETTYPE3_L12= "L12";//期货公司及其子公司资管产品
    
    public static final String PBC_ASSETTYPE3_L13= "L13";//保险资管产品
    
    public static final String PBC_ASSETTYPE3_L14= "L14";//金融资产投资公司资管产品
    
    public static final String PBC_ASSETTYPE3_L15= "L15";//公募基金
    
    public static final String PBC_ASSETTYPE3_L16= "L16";//私募机构私募基金
    
    public static final String PBC_ASSETTYPE3_L99= "L99";//其他特定目的载体
    
    public static final String PBC_ASSETTYPE3_L98= "L98";//境外
    
    public static final String PBC_ASSETTYPE3_R02= "R02";//其他
    
    public static final String PBC_ASSETTYPE3_Q01= "Q01";//贷款收益权
    
    public static final String PBC_ASSETTYPE3_Q02= "Q02";//债务证券收益权
    
    public static final String PBC_ASSETTYPE3_Q03= "Q03";//应收账款收益权
    
    public static final String PBC_ASSETTYPE3_Q04= "Q04";//其他债权收益权
    
    public static final String PBC_ASSETTYPE3_Q05= "Q05";//股票收益权
    
    public static final String PBC_ASSETTYPE3_Q06= "Q06";//其他股权收益权
    
    public static final String PBC_ASSETTYPE3_Q07= "Q07";//物权资产收益权
    
    public static final String PBC_ASSETTYPE3_G01= "G01";//非金融企业股票
    
    public static final String PBC_ASSETTYPE3_G02= "G02";//金融机构股票
    
    public static final String PBC_ASSETTYPE3_M01= "M01";//银行非保本理财
    
    public static final String PBC_ASSETTYPE3_M02= "M02";//信托公司资管产品
    
    public static final String PBC_ASSETTYPE3_M03= "M03";//证券公司及其子公司资管产品
    
    public static final String PBC_ASSETTYPE3_M04= "M04";//基金管理公司及其子公司专户
    
    public static final String PBC_ASSETTYPE3_M05= "M05";//期货公司及其子公司资管产品
    
    public static final String PBC_ASSETTYPE3_M06= "M06";//保险资管产品
    
    public static final String PBC_ASSETTYPE3_M07= "M07";//金融资产投资公司资管产品
    
    public static final String PBC_ASSETTYPE3_M08= "M08";//公募基金
    
    public static final String PBC_ASSETTYPE3_M09= "M09";//私募机构私募基金
    
    public static final String PBC_ASSETTYPE3_M99= "M99";//其他特定目的载体
    
    public static final String PBC_ASSETTYPE3_O01= "O01";//非金融企业股权
    
    public static final String PBC_ASSETTYPE3_O02= "O02";//金融机构股权
    
    public static final String PBC_ASSETTYPE3_T04= "T04";//境外股权
    
    public static final String PBC_ASSETTYPE3_J01= "J01";//境内
    
    public static final String PBC_ASSETTYPE3_J02= "J02";//境外
    
    public static final String PBC_ASSETTYPE3_H01= "H01";//黄金
    
    public static final String PBC_ASSETTYPE3_F01= "F01";//非金融资产
    
    public static final String PBC_ASSETTYPE3_P11= "P11";//应收账款
    
    public static final String PBC_ASSETTYPE3_P12= "P12";//应付账款
    
    public static final String PBC_ASSETTYPE3_P13= "P13";//其他负债

    public static final String BS_ASSETTYPE1_F08= "F08";//拆借
    
    public static final String BS_ASSETTYPE1_F07= "F07";//质押式回购
    
    public static final String BS_ASSETTYPE1_F10= "F10";//买断式回购

    public static final String BS_ASSETTYPE1_F05= "F05";//存款类资产

    public static final String BS_ASSETTYPE1_F02= "F02";//基金类资产

    public static final String BS_ASSETTYPE1_F04= "F04";//计划类（净值型）

    public static final String BS_ASSETTYPE1_F03= "F03";//计划类（利率型）

    public static final String BS_ASSETTYPE1_F06= "F06";//项目类资产

    public static final String BS_ASSETTYPE1_F01= "F01";//债券

    public static final String BS_ASSETTYPE1_F09= "F09";//计划类（货币型）
    
    public static final String BS_ASSETTYPE2_D01= "D01";//同业借款
    
    public static final String BS_ASSETTYPE2_D02= "D02";//存放同业
    
    public static final String BS_ASSETTYPE2_F01= "F01";//货币基金
    
    public static final String BS_ASSETTYPE2_F02= "F02";//非货币基金

    public static final String BS_ASSETTYPE2_N01= "N01";//券商资产管理计划
    
    public static final String BS_ASSETTYPE2_N02= "N02";//信托产品
    
    public static final String BS_ASSETTYPE2_N03= "N03";//基金资产管理计划
    
    public static final String BS_ASSETTYPE2_N04= "N04";//保险资产管理计划
    
    public static final String BS_ASSETTYPE2_N05= "N05";//期货资产管理计划
    
    public static final String BS_ASSETTYPE2_N06= "N06";//其他资产管理计划

    public static final String BS_ASSETTYPE2_P01= "P01";//非标债权

    public static final String BS_ASSETTYPE2_P02= "P02";//结构化证券
    
    public static final String BS_ASSETTYPE2_P03= "P03";//其他权益类投资
    
    public static final String BS_ASSETTYPE2_P04= "P04";//其他产业投资基金
    
    public static final String BS_ASSETTYPE2_P05= "P05";//股票定向增发投资
    
    public static final String BS_ASSETTYPE2_P06= "P06";//基础设施基金
    
    public static final String BS_ASSETTYPE2_P07= "P07";//券商债权收益权投资
    
    public static final String BS_ASSETTYPE2_P08= "P08";//信贷资产流转项目
    
    public static final String BS_ASSETTYPE2_P09= "P09";//券商收益凭证
    
    public static final String BS_ASSETTYPE2_P10= "P10";//股票质押式回购
    
    public static final String BS_ASSETTYPE2_P11= "P11";//理财直接融资工具
    
    public static final String BS_ASSETTYPE2_B01= "B01";//国债
    
    public static final String BS_ASSETTYPE2_B02= "B02";//地方政府债
    
    public static final String BS_ASSETTYPE2_B03= "B03";//央行票据
    
    public static final String BS_ASSETTYPE2_B05= "B05";//金融债
    
    public static final String BS_ASSETTYPE2_B06= "B06";//企业债

    public static final String BS_ASSETTYPE2_B07= "B07";//公司债

    public static final String BS_ASSETTYPE2_B08= "B08";//中期票据

    public static final String BS_ASSETTYPE2_B09= "B09";//短期融资券

    public static final String BS_ASSETTYPE2_B10= "B10";//定向工具

    public static final String BS_ASSETTYPE2_B11= "B11";//国际机构债
    
    public static final String BS_ASSETTYPE2_B12= "B12";//政府支持机构债

    public static final String BS_ASSETTYPE2_B04= "B04";//同业存单
    
    public static final String BS_ASSETTYPE2_B13= "B13";//资产支持证券

    public static final String BS_ASSETTYPE2_B14= "B14";//可转债

    public static final String BS_ASSETTYPE2_B15= "B15";//可交换债

    public static final String BS_ASSETTYPE2_B16= "B16";//可分离转债存债

    public static final String BS_ASSETTYPE2_B17= "B17";//优先股
    
    public static final String BS_ASSETTYPE3_1205= "1205";//收/受益权
    
    public static final String BS_ASSETTYPE3_C0402= "C0402";//非银行业金融机构
    
    public static final String MAPPINGCODE_PBCASSETDATA= "MAPPINGCODE_PBCASSETDATA";//人行资产池dat类型映射
    
    public static final String MAPPINGCODE_PBCPRD_UPDATETYPE= "MAPPINGCODE_PBCPRD_UPDATETYPE";//人行产品类型修改映射
    
    public static final String MAPPINGCODE_PBCREPORT_DAT_REQUEST_NAME= "PBCREPORT_DAT_REQUEST_NAME";//dat文件中各表对应申请单名称
    
    public static final String MAPPINGCODE_DEPT_CODE_CHBTOPBC= "MAPPINGCODE_DEPT_CODE_CHBTOPBC";//境内托管机构代码中债人行映射
    /*
     * 应付账款剩余期限配置参数(人行表3-3，01：产品/资产的剩余期限，02：无固定期限)
     */
    public static final String ACCOUNTS_RECEIVABLE_END_DAYS_FLAG = "ACCOUNTS_RECEIVABLE_END_DAYS_FLAG";
    
}

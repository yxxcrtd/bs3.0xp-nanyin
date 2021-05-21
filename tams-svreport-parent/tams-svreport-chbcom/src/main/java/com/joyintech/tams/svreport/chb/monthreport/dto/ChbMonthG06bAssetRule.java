package com.joyintech.tams.svreport.chb.monthreport.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

import java.util.List;

/**
 * @author WanShiWei
 * @date 2021年04月28日 16:05
 */
public enum ChbMonthG06bAssetRule {
    现金及银行存款拆放同业等("AMT2_1_1","其中：2.1.1 现金及银行存款、拆放同业等","ASSETTYPE2","1001|1107|1108|1504"),
    固定收益类证券("AMT2_1_2","2.1.2 固定收益类证券","ASSETTYPE2","2501|2502|1501|1503|1101|1110|1109|1111|1102|1112|1113|1114|1115|1116|1117"),
    固定收益类证券01("AMT2_1_2","2.1.2 固定收益类证券","ISFBZB","01"),
    其他标准化债权类资产("AMT2_1_3","2.1.3 其他标准化债权类资产","",""),
    非标准化债权类资产("AMT2_1_4","2.1.4 非标准化债权类资产","",""),
    融资主体外部信用评级AA含以上("AMT2_1_4_a","其中：2.1.4.a 融资主体外部信用评级AA+（含）以上","RATING,ASSETTYP2","01|02|03|04,1201|1210|1202|1203|1204|1205|1206|1207|1208|1209|1299|2201|2202|2101|2299"),
    融资主体外部信用评级AA以下及未评级("AMT2_1_4_b","2.1.4.b 融资主体外部信用评级AA+以下及未评级","",""),
    抵押质押类("AMT2_1_4_b_1","其中：2.1.4.b(1) 抵押、质押类","RATING,ASSETTYP2,GUA_TYPE","05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31,1201|1210|1202|1203|1204|1205|1206|1207|1208|1209|1299|2201|2202|2101|2299,03|04"),
    保证类("AMT2_1_4_b_2","2.1.4.b(2) 保证类","RATING,ASSETTYP2,GUATYPE","05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31,1201|1210|1202|1203|1204|1205|1206|1207|1208|1209|1299|2201|2202|2101|2299,02"),
    信用类("AMT2_1_4_b_3","2.1.4.b(3) 信用类","RATING,ASSETTYP2,GUATYPE","05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31,1201|1210|1202|1203|1204|1205|1206|1207|1208|1209|1299|2201|2202|2101|2299,01|05|99|null"),
    股票("AMT2_1_5","2.1.5 股票","ASSETTYPE2","1305|1302|1502"),
    未上市企业股权("AMT2_1_6","2.1.6 未上市企业股权","ASSETTYPE2","1301"),
    衍生产品("AMT2_1_7","2.1.7 衍生产品","",""),
    符合标准化金融工具特征的衍生产品("AMT2_1_7_a","其中：2.1.7.a 符合标准化金融工具特征的衍生产品","ASSETTYPE2,TRADEMARKET","1401|1402|1403|1404|1405|1406|1499,03|04|06|07|08|09|10|17"),
    其他衍生产品("AMT2_1_7_b","2.1.7.b 其他衍生产品","ASSETTYPE2,TRADEMARKET","1401|1402|1403|1404|1405|1406|1499,01|02|05|11|12|13|14|15|16|18|99"),
    商品类资产("AMT2_1_8","2.1.8 商品类资产","ASSETTYPE2","1601|1602|1699"),
    另类资产("AMT2_1_9","2.1.9 另类资产","ASSETTYPE2","2301|2302|2303|2304|2305|1603"),
    公募证券投资基金("AMT2_1_10","2.1.10 公募证券投资基金","ASSETTYPE2","1106|2401|1303|2403|2402|2499"),
    其他("AMT2_1_11","2.1.11 其他","ASSETTYPE2","1399|1501|1504|1506|1507|1508|1509|1505|1502|1503|1599"),

    附加风险资本("AMT2_2","2.2 附加风险资本","",""),
    跨境投资资产("AMT2_2_1","其中：2.2.1 跨境投资资产","ASSETTYPE2","1599"),
    本公司分级理财产品投资资产("AMT2_2_2","2.2.2 本公司分级理财产品投资资产","ISCONSTRACTE","01"),

    合计("AMT2_3","2.3 合计","","")
    ;
    /**
     * 属性代码
     */
    private String propertyCode;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性映射规则列表，关联chb_month_asset_position中的字段名,以逗号隔开
     */
    private List<String> ruleCodeList;

    /**
     * 属性映射规则值，与属性映射规则列表中对应的取值
     */
    private List<String> ruleValueList;

    ChbMonthG06bAssetRule(String propertyCode,String propertyName,String ruleCodeList,String ruleValueList){
        this.propertyCode = propertyCode;
        this.propertyName = propertyName;
        this.ruleCodeList = StringUtil.splitToList(GlobalConstant.COMMA, ruleCodeList);
        this.ruleValueList = StringUtil.splitToList(GlobalConstant.COMMA, ruleValueList);
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String prepertyName) {
        this.propertyName = prepertyName;
    }

    public List<String> getRuleCodeList() {
        return ruleCodeList;
    }

    public void setRuleCodeList(List<String> ruleCodeList) {
        this.ruleCodeList = ruleCodeList;
    }

    public List<String> getRuleValueList() {
        return ruleValueList;
    }

    public void setRuleValueList(List<String> ruleValueList) {
        this.ruleValueList = ruleValueList;
    }

}

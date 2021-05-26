package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import java.util.List;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;

public enum ChbPeriodicG06AssetTypeRule {

    中债定期报告G06a资产分类_现金及银行存款(DictCode.中债定期报告G06a资产分类, "现金及银行存款","AMT1_1_1,AMT1_1_2,AMT1_1_3,AMT1_1_4,AMT1_1_5"),
    中债定期报告G06a资产分类_同业存单(DictCode.中债定期报告G06a资产分类, "同业存单","AMT1_2_1,AMT1_2_2"),
    中债定期报告G06a资产分类_拆放同业及债券买入返售(DictCode.中债定期报告G06a资产分类, "拆放同业及债券买入返售","AMT1_3_1,AMT1_3_2"),
    中债定期报告G06a资产分类_债券(DictCode.中债定期报告G06a资产分类, "债券","AMT1_4_1,AMT1_4_2,AMT1_4_3,AMT1_4_4,AMT1_4_5,AMT1_4_6,AMT1_4_7,AMT1_4_8,AMT1_4_9,AMT1_4_10,AMT1_4_11,AMT1_4_12,AMT1_4_13"),
    中债定期报告G06a资产分类_非标准化债券类资产(DictCode.中债定期报告G06a资产分类, "非标准化债券类资产","AMT1_5_1,AMT1_5_2,AMT1_5_3,AMT1_5_4,AMT1_5_5,AMT1_5_6,AMT1_5_7,AMT1_5_8,AMT1_5_9,AMT1_5_10,AMT1_5_11,AMT1_5_12,AMT1_5_13,AMT1_5_14,AMT1_5_15,AMT1_5_16"),
    中债定期报告G06a资产分类_权益类投资(DictCode.中债定期报告G06a资产分类, "权益类投资","AMT1_6_1,AMT1_6_2,AMT1_6_3,AMT1_6_4"),
    中债定期报告G06a资产分类_金融衍生品(DictCode.中债定期报告G06a资产分类, "金融衍生品","AMT1_7_1,AMT1_7_2,AMT1_7_3,AMT1_7_4,AMT1_7_5,AMT1_7_6,AMT1_7_7"),
    中债定期报告G06a资产分类_代客境外理财投资QDII(DictCode.中债定期报告G06a资产分类, "代客境外理财投资QDII","AMT1_8_1,AMT1_8_2,AMT1_8_3,AMT1_8_4,AMT1_8_5,AMT1_8_6,AMT1_8_7,AMT1_8_8,AMT1_8_9,AMT1_8_10"),
    中债定期报告G06a资产分类_商品类资产(DictCode.中债定期报告G06a资产分类, "商品类资产","AMT1_9_1,AMT1_9_2,AMT1_9_3"),
    中债定期报告G06a资产分类_另类资产(DictCode.中债定期报告G06a资产分类, "另类资产","AMT1_10_1,AMT1_10_2,AMT1_10_3,AMT1_10_4,AMT1_10_5,AMT1_10_6"),
    中债定期报告G06a资产分类_公募基金(DictCode.中债定期报告G06a资产分类, "公募基金","AMT1_11_1,AMT1_11_2,AMT1_11_3,AMT1_11_4,AMT1_11_5,AMT1_11_6"),
    中债定期报告G06a资产分类_私募基金(DictCode.中债定期报告G06a资产分类, "私募基金","AMT1_12_1,AMT1_12_2,AMT1_12_3,AMT1_12_4,AMT1_12_5,AMT1_12_6,AMT1_12_7,AMT1_12_8"),
    中债定期报告G06a资产分类_资产管理产品(DictCode.中债定期报告G06a资产分类, "资产管理产品","AMT1_13_1,AMT1_13_2,AMT1_13_3,AMT1_13_4,AMT1_13_5,AMT1_13_6"),
    中债定期报告G06a资产分类_委外投资_协议方式(DictCode.中债定期报告G06a资产分类, "委外投资——协议方式","AMT1_14_1,AMT1_14_2,AMT1_14_3,AMT1_14_4,AMT1_14_5,AMT1_14_6,AMT1_14_7"),

    ;
    /**
     * 属性代码
     */
    private DictCode propertyCode;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性映射规则列表,以 | 隔开
     */
    private List<String> ruleCodeList;


    ChbPeriodicG06AssetTypeRule(DictCode propertyCode,String propertyName,String ruleCodeList){
        this.propertyCode = propertyCode;
        this.propertyName = propertyName;
        this.ruleCodeList = StringUtil.splitToList(GlobalConstant.COMMA, ruleCodeList);
    }

    public DictCode getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(DictCode propertyCode) {
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
}

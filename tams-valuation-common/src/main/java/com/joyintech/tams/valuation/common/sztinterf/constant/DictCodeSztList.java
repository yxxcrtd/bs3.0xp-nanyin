/*
 * 文件名：DictCodeSztList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.sztinterf.constant;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DictCodeSztList implements BaseDictCodeList {

    /** 深圳通页面调用接口参数字段 start **/
    深圳通页面调用接口参数字段_产品代码(DictCodeSzt.深圳通接口查询参数字段, "prodIds", "产品代码"),
    深圳通页面调用接口参数字段_发送时间(DictCodeSzt.深圳通接口查询参数字段, "cDate", "发送时间"),
    深圳通页面调用接口参数字段_接口类型(DictCodeSzt.深圳通接口查询参数字段, "interfType", "接口类型"),
    深圳通页面调用接口参数字段_是否发送四级科目号(DictCodeSzt.深圳通接口查询参数字段, "detailFlag", "是否发送四级科目号"),
    /** 深圳通页面调用接口参数字段 end **/

    /** 深圳通接口查询参数字段 start **/
    深圳通接口查询参数字段_科目类型_资产类(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_01", "科目类型_资产类"),
    深圳通接口查询参数字段_科目类型_负债类(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_02", "科目类型_负债类"),
    深圳通接口查询参数字段_科目类型_资产负债共同类(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_03", "科目类型_资产负债共同类"),
    深圳通接口查询参数字段_科目类型_所有者权益类(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_04", "科目类型_所有者权益类"),
    深圳通接口查询参数字段_科目类型_损益类(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_05", "科目类型_损益类"),
    深圳通接口查询参数字段_科目类型LIST(DictCodeSzt.深圳通接口查询参数字段, "SUBJECT_TYPE_LIST", "科目类型LIST"),
    深圳通接口查询参数字段_产品代码(DictCodeSzt.深圳通接口查询参数字段, "prodId", "产品代码"),
    深圳通接口查询参数字段_发送日期(DictCodeSzt.深圳通接口查询参数字段, "cDate", "发送日期"),
    深圳通接口查询参数字段_是否发送四级科目(DictCodeSzt.深圳通接口查询参数字段, "detailFlag", "是否发送四级科目"),
    深圳通接口查询参数字段_接口类型(DictCodeSzt.深圳通接口查询参数字段, "interType", "接口类型"),
    深圳通接口查询参数字段_金融产品类型(DictCodeSzt.深圳通接口查询参数字段, "finprodType", "金融产品类型"),
    深圳通接口查询参数字段_行情标志(DictCodeSzt.深圳通接口查询参数字段, "fHqbz", "行情标志"),

    /** 深圳通接口查询参数字段 end **/

    /** 深圳通接口报文公共接口字段 start **/
    深圳通接口报文公共接口字段_报文头(DictCodeSzt.深圳通接口报文公共接口字段, "IN", "报文头"),
    深圳通接口报文公共接口字段_数据行(DictCodeSzt.深圳通接口报文公共接口字段, "Record", "数据行"),
    深圳通接口报文公共接口字段_文件类型(DictCodeSzt.深圳通接口报文公共接口字段, "FILE_TYPE", "文件类型"),
    深圳通接口报文公共接口字段_组合代码(DictCodeSzt.深圳通接口报文公共接口字段, "FUND_ID", "组合代码"),
    深圳通接口报文公共接口字段_报表类型(DictCodeSzt.深圳通接口报文公共接口字段, "REPORT_TYPE", "报表类型"),
    深圳通接口报文公共接口字段_开始日期(DictCodeSzt.深圳通接口报文公共接口字段, "BEGIN_DATE", "开始日期"),
    深圳通接口报文公共接口字段_结束日期(DictCodeSzt.深圳通接口报文公共接口字段, "END_DATE", "结束日期"),
    深圳通接口报文公共接口字段_公司代码(DictCodeSzt.深圳通接口报文公共接口字段, "DEPT_CODE", "公司代码"),
    深圳通接口报文公共接口字段_证书代码(DictCodeSzt.深圳通接口报文公共接口字段, "CERT_ID", "证书代码"),
    深圳通接口报文公共接口字段_业务流水号(DictCodeSzt.深圳通接口报文公共接口字段, "SERIAL_NO", "业务流水号"),
    /** 深圳通接口报文公共接口字段 end **/

    /**  深圳通科目信息接口字段 start **/
    深圳通接口科目信息报文字段_科目代码(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctCode", "科目代码"),
    深圳通接口科目信息报文字段_科目名称(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctName", "科目名称"),
    深圳通接口科目信息报文字段_科目级别(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctLevel", "科目级别"),
    深圳通接口科目信息报文字段_上级科目代码(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctParent", "上级科目代码"),
    深圳通接口科目信息报文字段_是否明细科目(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctDetail", "是否明细科目"),
    深圳通接口科目信息报文字段_科目类别(DictCodeSzt.深圳通接口科目信息报文字段, "FAcctClass", "科目类别"),
    深圳通接口科目信息报文字段_余额方向(DictCodeSzt.深圳通接口科目信息报文字段, "FBalDC", "余额方向"),
    /**  深圳通接口科目信息报文字段 end **/

    /**  深圳通接口估值报文字段 start **/
    深圳通接口估值报文字段_科目编码(DictCodeSzt.深圳通接口估值报文字段, "FKmbm", "科目编码"),
    深圳通接口估值报文字段_科目名称(DictCodeSzt.深圳通接口估值报文字段, "FKmmc", "科目名称"),
    深圳通接口估值报文字段_行情价格(DictCodeSzt.深圳通接口估值报文字段, "FHqjg", "行情价格"),
    深圳通接口估值报文字段_行情标志(DictCodeSzt.深圳通接口估值报文字段, "FHqbz", "行情标志"),
    深圳通接口估值报文字段_证券数量(DictCodeSzt.深圳通接口估值报文字段, "FZqsl", "证券数量"),
    深圳通接口估值报文字段_成本(DictCodeSzt.深圳通接口估值报文字段, "FZqcb", "成本"),
    深圳通接口估值报文字段_市值总市值(DictCodeSzt.深圳通接口估值报文字段, "FZqsz", "市值（总市值）"),
    深圳通接口估值报文字段_估值增值(DictCodeSzt.深圳通接口估值报文字段, "FGz_zz", "估值增值"),
    深圳通接口估值报文字段_成本占净值比例(DictCodeSzt.深圳通接口估值报文字段, "FCb_Jz_bl", "成本占净值比例"),
    深圳通接口估值报文字段_市值占净值比例(DictCodeSzt.深圳通接口估值报文字段, "FSz_Jz_bl", "市值占净值比例"),
    深圳通接口估值报文字段_是否明细类数据(DictCodeSzt.深圳通接口估值报文字段, "FIsDetail", "是否明细类数据"),
    /**  深圳通接口估值报文字段 end **/

    /**  深圳通接口科目余额报文字段 start **/
    深圳通接口科目余额报文字段_科目代码(DictCodeSzt.深圳通接口科目余额报文字段, "FAcctCode", "科目代码"),
    深圳通接口科目余额报文字段_币种代码(DictCodeSzt.深圳通接口科目余额报文字段, "FCurCode", "币种代码"),
    深圳通接口科目余额报文字段_期初余额原币(DictCodeSzt.深圳通接口科目余额报文字段, "FStartBal", "期初余额原币"),
    深圳通接口科目余额报文字段_本期借方发生额原币(DictCodeSzt.深圳通接口科目余额报文字段, "FDebit", "本期借方发生额原币"),
    深圳通接口科目余额报文字段_本期贷方发生额原币(DictCodeSzt.深圳通接口科目余额报文字段, "FCredit", "本期贷方发生额原币"),
    深圳通接口科目余额报文字段_期末余额原币(DictCodeSzt.深圳通接口科目余额报文字段, "FEndBal", "期末余额原币"),
    深圳通接口科目余额报文字段_期初余额本位币(DictCodeSzt.深圳通接口科目余额报文字段, "FBStartBal", "期初余额本位币"),
    深圳通接口科目余额报文字段_本期借方发生额本位币(DictCodeSzt.深圳通接口科目余额报文字段, "FBDebit", "本期借方发生额本位币"),
    深圳通接口科目余额报文字段_本期贷方发生额本位币(DictCodeSzt.深圳通接口科目余额报文字段, "FBCredit", "本期贷方发生额本位币"),
    深圳通接口科目余额报文字段_期末余额本位币(DictCodeSzt.深圳通接口科目余额报文字段, "FBEndBal", "期末余额本位币"),
    深圳通接口科目余额报文字段_期初余额数量(DictCodeSzt.深圳通接口科目余额报文字段, "FAStartBal", "期初余额数量"),
    深圳通接口科目余额报文字段_本期借方发生额数量(DictCodeSzt.深圳通接口科目余额报文字段, "FADebit", "本期借方发生额数量"),
    深圳通接口科目余额报文字段_本期贷方发生额数量(DictCodeSzt.深圳通接口科目余额报文字段, "FACredit", "本期贷方发生额数量"),
    深圳通接口科目余额报文字段_期末余额数量(DictCodeSzt.深圳通接口科目余额报文字段, "FAEndBal", "期末余额数量"),
    深圳通接口科目余额报文字段_是否最明细科目(DictCodeSzt.深圳通接口科目余额报文字段, "FIsDetail", "是否最明细科目"),
    深圳通接口科目余额报文字段_借方累计发生额(DictCodeSzt.深圳通接口科目余额报文字段, "F_J_TOLTAL_AMOUNT", "借方累计发生额"),
    深圳通接口科目余额报文字段_贷方累计发生额(DictCodeSzt.深圳通接口科目余额报文字段, "F_D_TOTAL_AMOUNT", "贷方累计发生额"),
    /**  深圳通接口科目余额报文字段 end **/

    /**  深圳通接口报文返回字段 start **/
    深圳通接口报文返回字段_解析报文头(DictCodeSzt.深圳通接口报文返回字段, "OUT", "解析报文头"),
    深圳通接口报文返回字段_对帐结果(DictCodeSzt.深圳通接口报文返回字段, "RESULT", "对帐结果"),
    深圳通接口报文返回字段_关联流水号(DictCodeSzt.深圳通接口报文返回字段, "REF_NO", "关联流水号"),
    深圳通接口报文返回字段_本方项目代码(DictCodeSzt.深圳通接口报文返回字段, "FXMDMB", "本方项目代码"),
    深圳通接口报文返回字段_对方项目代码(DictCodeSzt.深圳通接口报文返回字段, "FXMDMD", "对方项目代码"),
    深圳通接口报文返回字段_本方金额(DictCodeSzt.深圳通接口报文返回字段, "BJE", "本方金额"),
    深圳通接口报文返回字段_对方金额(DictCodeSzt.深圳通接口报文返回字段, "DJE", "对方金额"),
    深圳通接口报文返回字段_本方数量(DictCodeSzt.深圳通接口报文返回字段, "BSL", "本方数量"),
    深圳通接口报文返回字段_对方数量(DictCodeSzt.深圳通接口报文返回字段, "DSL", "对方数量"),
    深圳通接口报文返回字段_附言(DictCodeSzt.深圳通接口报文返回字段, "NOTE", "附言"),
    深圳通接口报文返回字段_对账类型(DictCodeSzt.深圳通接口报文返回字段, "CHECK_FLAG", "对账类型"),
    深圳通接口报文返回字段_处理人(DictCodeSzt.深圳通接口报文返回字段, "DEALER", "处理人"),
    深圳通接口报文返回字段_时间(DictCodeSzt.深圳通接口报文返回字段, "TIME", "时间"),
    深圳通接口报文返回字段_本方金额一(DictCodeSzt.深圳通接口报文返回字段, "BJEO", "本方金额一"),
    深圳通接口报文返回字段_对方金额一(DictCodeSzt.深圳通接口报文返回字段, "DJEO", "对方金额一"),
    深圳通接口报文返回字段_本方金额二(DictCodeSzt.深圳通接口报文返回字段, "BJET", "本方金额二"),
    深圳通接口报文返回字段_对方金额二(DictCodeSzt.深圳通接口报文返回字段, "DJET", "对方金额二"),
    深圳通接口报文返回字段_本方金额三(DictCodeSzt.深圳通接口报文返回字段, "BJETH", "本方金额三"),
    深圳通接口报文返回字段_对方金额三(DictCodeSzt.深圳通接口报文返回字段, "DJETH", "对方金额三"),
    深圳通接口报文返回字段_本方金额四(DictCodeSzt.深圳通接口报文返回字段, "BJEF", "本方金额四"),
    深圳通接口报文返回字段_对方金额四(DictCodeSzt.深圳通接口报文返回字段, "DJEF", "对方金额四"),
    深圳通接口报文返回字段_本方金额五(DictCodeSzt.深圳通接口报文返回字段, "BJEFI", "本方金额五"),
    深圳通接口报文返回字段_对方金额五(DictCodeSzt.深圳通接口报文返回字段, "DJEFI", "对方金额五"),
    深圳通接口报文返回字段_本方备用1(DictCodeSzt.深圳通接口报文返回字段, "BBY1", "本方备用1"),
    深圳通接口报文返回字段_对方备用1(DictCodeSzt.深圳通接口报文返回字段, "DBY1", "对方备用1"),

    /**  深圳通接口报文返回字段 end **/

    /**  深圳通接口发送结果 start add by yanya on 20200311**/
    深圳通接口发送结果_成功(DictCodeSzt.深圳通接口发送结果, "0", "MR2_ERRCODE_OK"),
    深圳通接口发送结果_MR2_ERRCODE_PARAMERR(DictCodeSzt.深圳通接口发送结果, "-1", "MR2_ERRCODE_PARAMERR"),
    深圳通接口发送结果_MR2_ERRCODE_CONNERR(DictCodeSzt.深圳通接口发送结果, "-2", "MR2_ERRCODE_CONNERR"),
    深圳通接口发送结果_MR2_ERRCODE_TIMEEXPIRED(DictCodeSzt.深圳通接口发送结果, "-3", "MR2_ERRCODE_TIMEEXPIRED"),
    深圳通接口发送结果_MR2_ERRCODE_TIMEOUT(DictCodeSzt.深圳通接口发送结果, "-4", "MR2_ERRCODE_TIMEOUT"),
    深圳通接口发送结果_MR2_ERRCODE_NOMSG(DictCodeSzt.深圳通接口发送结果, "-5", "MR2_ERRCODE_NOMSG"),
    深圳通接口发送结果_MR2_ERRCODE_BUFTOOSHORT(DictCodeSzt.深圳通接口发送结果, "-6", "MR2_ERRCODE_BUFTOOSHORT"),
    深圳通接口发送结果_MR2_ERRCODE_BUFTOOBIG(DictCodeSzt.深圳通接口发送结果, "-7", "MR2_ERRCODE_BUFTOOBIG"),
    深圳通接口发送结果_MR2_ERRCODE_SYSERROR(DictCodeSzt.深圳通接口发送结果, "-8", "MR2_ERRCODE_SYSERROR"),
    深圳通接口发送结果_MR2_ERRCODE_COMMU_NOTALLOW(DictCodeSzt.深圳通接口发送结果, "-9", "MR2_ERRCODE_COMMU_NOTALLOW"),
    深圳通接口发送结果_MR2_ERRCODE_DEST_NOTONLINE(DictCodeSzt.深圳通接口发送结果, "-10", "MR2_ERRCODE_DEST_NOTONLINE"),
    深圳通接口发送结果_MR2_ERRCODE_DEST_FULL(DictCodeSzt.深圳通接口发送结果, "-11", "MR2_ERRCODE_DEST_FULL"),
    深圳通接口发送结果_INVALID_MSG_BODY(DictCodeSzt.深圳通接口发送结果, "INVALID_MSG_BODY", "INVALID_MSG_BODY"),
    深圳通接口发送结果_FAILED_TO_SEND_SZTMESSAGE(DictCodeSzt.深圳通接口发送结果, "INVALID_MSG_BODY", "FAILED_TO_SEND_SZTMESSAGE"),
    /**  深圳通接口发送结果 end add by yanya on 20200311**/
    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCodeSzt dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeSztList(DictCodeSzt dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.setCodeName(codeName);
    }

    /**
     * 获得数据字典详细类
     *
     * @param value 数据字典值
     * @param dictCode 数据字典DictCode
     * @return DictCodeList
     * @see
     * @since 1.0
     */
    public static DictCodeSztList from(DictCodeSzt dictCodeSzt, String value) {
        DictCodeSztList[] values = DictCodeSztList.values();
        for (DictCodeSztList code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCodeSzt) {
                return code;
            }
        }
        return null;
    }

    /**
     * 转换成DICTCODELIST
     * @param dictCodeListExt1
     * @return
     */
    public static Map<String,Object> fromDictCodeList(DictCodeSztList dictCodeSztList){
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("codeName",dictCodeSztList.codeName);
        retMap.put("value",dictCodeSztList.value);
        retMap.put("dictCode",dictCodeSztList.dictCode);
        return retMap;
    }

    /**
     * 获取数据字典明细List
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<SelectOptionDto> fromDictCode(DictCodeSzt dictCodeSzt) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeSztList[] values = DictCodeSztList.values();
        for (DictCodeSztList code : values) {
            if (code.getDictCode() == dictCodeSzt) {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(code.getValue());
                option.setText(code.getCodeName());
                result.add(option);
            }
        }
        return result;
    }

    /**
     * 获取数据字典明细valueList
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<String> valuesfromDictCode(DictCodeSzt dictCodeSzt) {
        List<String> result = new ArrayList<>();
        DictCodeSztList[] values = DictCodeSztList.values();
        for (DictCodeSztList code : values) {
            if (code.getDictCode() == dictCodeSzt) {
                result.add(code.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 DictCode
     *
     * @return value.
     */
    @Override
    public DictCodeSzt getDictCode() {
        return dictCode;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 codeName
     *
     * @return codeName.
     */
    @Override
    public String getCodeName() {
        return codeName;
    }

    /**
     * 获取 带有引号的名称
     *
     * @return 带有引号的名称.
     */
    public String getQuotesCodeName() {
        return "“" + codeName + "”";
    }

    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public Integer getIntegerCodeName() {
        Integer result = null;
        try {
            result = Integer.parseInt(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public BigDecimal getBigDecimalCodeName() {
        BigDecimal result = null;
        try {
            result = new BigDecimal(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * 设置 codeName
     *
     * @param codeName codeName
     */
    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

}

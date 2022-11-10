package com.joyintech.tams.constant;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典明细枚举
 *
 * @author zhengxiaolian
 * @version 1.0 2022年07月20日
 * @see DictCodeListExt5
 * @since 1.0
 */
public enum DictCodeListExt5 implements BaseDictCodeList {

    发行方式_开放式(DictCodeExt1.发行方式_信批, "01", "开放式"),

    发行方式_封闭式(DictCodeExt1.发行方式_信批, "02", "封闭式"),

    信批产品类别_母产品(DictCodeExt1.信批产品类别, "01", "母产品"),

    信批产品类别_普通产品(DictCodeExt1.信批产品类别, "02", "普通产品"),

    信批产品类别_子产品(DictCodeExt1.信批产品类别, "03", "子产品"),

    产品运作方式_开放式净值型(DictCodeExt1.信披_产品运作方式, "01", "开放式净值型"),
    产品运作方式_开放式非净值型(DictCodeExt1.信披_产品运作方式, "02", "开放式非净值型"),
    产品运作方式_封闭式净值型(DictCodeExt1.信披_产品运作方式, "03", "封闭式净值型"),
    产品运作方式_封闭式非净值型(DictCodeExt1.信披_产品运作方式, "04", "封闭式非净值型"),

    信披产品状态产_运行中(DictCodeExt1.信披_产品状态, "01", "运行中"),
    信披产品状态_已到期(DictCodeExt1.信披_产品状态, "02", "已到期"),
    信披产品状态_已销户(DictCodeExt1.信披_产品状态, "03", "已销户"),

    /*信披是否是分级产品_是(DictCodeExt1.信披_是否是分级产品, "01", "是"),
    信披_是否是分级产品_否(DictCodeExt1.信披_是否是分级产品, "02", "否"),*/

    信披募集方式_公募(DictCodeExt1.信披_募集方式,"01","公募"),
    信披募集方式_私募(DictCodeExt1.信披_募集方式,"02","私募"),

    信披产品类型_净值型产品(DictCodeExt1.信披_产品类型,"01","净值型产品"),
    信披产品类型_现管类产品(DictCodeExt1.信披_产品类型,"04","现管类产品"),

    信披投资性质_固定收益类(DictCodeExt1.信披_投资性质,"01","固定收益类"),
    信披投资性质_权益类(DictCodeExt1.信披_投资性质,"02","权益类"),
    信披投资性质_商品及金融衍生品类(DictCodeExt1.信披_投资性质,"03","商品及衍生品类"),
    信披投资性质_混合类(DictCodeExt1.信披_投资性质,"04","混合类"),

    发行状态_发行成功(DictCodeExt1.信披_发行状态,"01","发行成功"),
    发行状态_发行失败(DictCodeExt1.信披_发行状态,"02","发行失败"),
    发行状态_确认发行(DictCodeExt1.信披_发行状态,"03","确认发行"),

    行情代码_中证500指数收益率(DictCodeExt1.行情代码,"04","中证500指数收益率"),
    行情代码_中债新综合全价指数收益率(DictCodeExt1.行情代码,"01","中债新综合全价指数收益率"),
    行情代码_沪深300指数收益率(DictCodeExt1.行情代码,"02","沪深300指数收益率"),
    行情代码_中债新综合财富指数(DictCodeExt1.行情代码,"03","中债新综合财富指数（1-3年）收益率"),

    维护类型_代销机构(DictCodeExt1.信披_代销机构_维护类型,"01","代销机构"),
    维护类型_理财产品(DictCodeExt1.信披_代销机构_维护类型,"02","理财产品"),


    开放频率_日开(DictCodeExt1.开放频率,"01","日开"),
    开放频率_频率在半年以内(DictCodeExt1.开放频率,"02","频率在半年（不含）以内"),
    开放频率_频率在半年以上(DictCodeExt1.开放频率,"03","频率在半年以上"),


    信披报表类别_发行公告(DictCodeExt1.信披报表类别,"01","发行公告"),
    信披报表类别_运行公告(DictCodeExt1.信披报表类别,"02","运行公告"),
    信披报表类别_到期公告(DictCodeExt1.信披报表类别,"03","到期公告"),
    信披报表类别_临时公告(DictCodeExt1.信披报表类别,"04","临时公告"),
    信披报表类别_销售公告(DictCodeExt1.信披报表类别,"05","销售公告"),
    信披报表类别_托管公告(DictCodeExt1.信披报表类别,"06","托管公告"),

    信披报表类别_其他公告(DictCodeExt1.信披报表类别,"99","其他公告"),

    信披数据校验比较符_小于(DictCodeExt1.信披数据校验比较符,"01","小于"),
    信披数据校验比较符_小于等于(DictCodeExt1.信披数据校验比较符,"02","小于等于"),
    信披数据校验比较符_大于(DictCodeExt1.信披数据校验比较符,"03","大于"),
    信披数据校验比较符_大于等于(DictCodeExt1.信披数据校验比较符,"04","大于等于"),
    信披数据校验比较符_范围控制_不包含临界值(DictCodeExt1.信披数据校验比较符,"05","范围控制（不包含临界值）"),
    信披数据校验比较符_范围控制_包含较小的临界值(DictCodeExt1.信披数据校验比较符,"06","范围控制（包含较小的临界值）"),
    信披数据校验比较符_范围控制_包含较大的临界值(DictCodeExt1.信披数据校验比较符,"07","范围控制（包含较大的临界值）"),
    信披数据校验比较符_等于(DictCodeExt1.信披数据校验比较符,"08","等于"),

    信披数据校验比较符_范围控制_同时包含较小和较大的临界值(DictCodeExt1.信披数据校验比较符,"09","范围控制（同时包含较小和较大的临界值）"),
    
    指标年度_2020(DictCodeExt1.指标年度,"2020","2020"),
    指标年度_2021(DictCodeExt1.指标年度,"2021","2021"),
    指标年度_2022(DictCodeExt1.指标年度,"2022","2022"),
    指标年度_2023(DictCodeExt1.指标年度,"2023","2023"),
    指标年度_2024(DictCodeExt1.指标年度,"2024","2024"),
    指标年度_2025(DictCodeExt1.指标年度,"2025","2025"),
    指标年度_2026(DictCodeExt1.指标年度,"2026","2026"),
    指标年度_2027(DictCodeExt1.指标年度,"2027","2027"),
    指标年度_2028(DictCodeExt1.指标年度,"2028","2028"),
    指标年度_2029(DictCodeExt1.指标年度,"2029","2029"),
    
    指标周期_一季度(DictCodeExt1.指标周期,"Q1","一季度"),
    指标周期_二季度(DictCodeExt1.指标周期,"Q2","二季度"),
    指标周期_三季度(DictCodeExt1.指标周期,"Q3","三季度"),
    指标周期_四季度(DictCodeExt1.指标周期,"Q4","四季度"),
    指标周期_半年(DictCodeExt1.指标周期,"HY","半年"),
    指标周期_全年(DictCodeExt1.指标周期,"TY","全年"),


    复核状态_待复核(DictCodeExt1.复核状态,"01","待复核"),
    复核状态_已推送披露(DictCodeExt1.复核状态,"02","已推送披露"),
    复核状态_已推送托管(DictCodeExt1.复核状态,"03","已推送托管"),
    复核状态_被托管退回(DictCodeExt1.复核状态,"04","被托管退回"),
    复核状态_被披露退回(DictCodeExt1.复核状态,"05","被披露退回"),
    复核状态_被退回确认(DictCodeExt1.复核状态,"06","退回确认"),

    托管复核状态_待复核(DictCodeExt1.托管复核状态,"01","待复核"),
    托管复核状态_已推送披露(DictCodeExt1.托管复核状态,"02","已推送披露"),
    托管复核状态_被披露退回(DictCodeExt1.托管复核状态,"03","被披露退回"),

    主页分类_日报(DictCodeExt1.主页分类,"01","日报"),

    主页分类_周报(DictCodeExt1.主页分类,"02","周报"),

    主页分类_发行公告(DictCodeExt1.主页分类,"03","发行公告"),

    主页分类_到期公告(DictCodeExt1.主页分类,"04","到期公告"),

    主页分类_周期型报告(DictCodeExt1.主页分类,"05","周期型报告"),

    主页分类_季度报告(DictCodeExt1.主页分类,"06","季度报告"),

    主页分类_半_年度报告(DictCodeExt1.主页分类,"07","（半）年度报告"),

    主页分类_开放分红公告(DictCodeExt1.主页分类,"08","开放分红公告"),

    主页分类_合计(DictCodeExt1.主页分类,"99","合计"),
    ;
    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCodeExt1 dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeListExt5(DictCodeExt1 dictCode, String value, String codeName) {
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
    public static DictCodeListExt4 from(DictCode dictCode, String value) {
        DictCodeListExt4[] values = DictCodeListExt4.values();
        for (DictCodeListExt4 code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    /**
     * 转换成DICTCODELIST
     * @param dictCodeListExt5
     * @return
     */
    public static Map<String,Object> fromDictCodeList(DictCodeListExt5 dictCodeListExt5){
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("codeName",dictCodeListExt5.codeName);
        retMap.put("value",dictCodeListExt5.value);
        retMap.put("dictCode",dictCodeListExt5.dictCode);
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
    public static List<SelectOptionDto> fromDictCode(DictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeListExt4[] values = DictCodeListExt4.values();
        for (DictCodeListExt4 code : values) {
            if (code.getDictCode() == dictCode) {
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
    public static List<String> valuesfromDictCode(DictCodeExt1 dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeListExt5[] values = DictCodeListExt5.values();
        for (DictCodeListExt5 code : values) {
            if (code.getDictCode() == dictCode) {
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
    public DictCodeExt1 getDictCode() {
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

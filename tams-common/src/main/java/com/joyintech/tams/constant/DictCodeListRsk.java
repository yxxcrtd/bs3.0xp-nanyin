package com.joyintech.tams.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

/**
 * 数据字典明细枚举
 *
 * @author chenzhuo
 * @version 1.0 2016年11月12日
 * @see DictCodeListRsk
 * @since 1.0
 */
public enum DictCodeListRsk implements BaseDictCodeList {
	共通业务类型_流动性资产设置(DictCode.共通业务类型,"RISK10","流动性资产设置"),
	流动性资产分类_一级流动性资产(
            DictCode.流动性资产分类, "01", "一级流动性资产"
    ),
	流动性资产分类_二级流动性资产(
            DictCode.流动性资产分类, "02", "二级流动性资产"
    ),
	资产分类来源代码_SOURCE_TYPE(
            DictCode.资产分类来源代码, "T01", "SOURCE_TYPE"
    ),
	资产分类来源代码_PARAM_VALUE(
            DictCode.资产分类来源代码, "T02", "PARAM_VALUE"
    ),
	参数类型_数值(
            DictCode.参数类型, "01", "数值"
    ),
    参数类型_文本(
            DictCode.参数类型, "02", "文本"
    ),
    参数类型_日期(
            DictCode.参数类型, "03", "日期"
    ),
    参数类型_数据字典(
            DictCode.参数类型, "04", "数据字典"
    ),
    风险因子_因子启停(
        DictCode.风险因子管理_因子启停, "RSK01", "因子启停"
        ),
    流动性风险因子_产品赎回比例(
        DictCode.流动性风险因子, "PrdScl01002", "产品赎回比例(%)"
        ),
    流动性风险因子_申赎净流出增加比例(
        DictCode.流动性风险因子, "PrdScl01001", "申赎净流出增加比例(%)"
        ),
    流动性风险因子_二级流动性资产变化率(
        DictCode.流动性风险因子, "AssLqu01001", "二级流动性资产变化率(%)"
        ),
    压力测试指标类型_产品指标(
        DictCode.压力测试指标类型, "01", "产品指标"
        ),
    压力测试指标类型_承压指标(
        DictCode.压力测试指标类型, "02", "承压指标"
        ),
    压力测试指标类型_产品明细指标(
            DictCode.压力测试指标类型, "03", "产品明细指标"
            ),
    情景风险分类_市场性风险(
            DictCode.情景风险分类, "02", "市场性风险"
            ),
    情景风险分类_流动性风险(
        DictCode.情景风险分类, "03", "流动性风险"
        ),
    压力测试_执行状态_已完成(
        DictCode.压力测试_执行状态, "01", "已完成"
        ),
    压力测试_执行状态_执行中(
        DictCode.压力测试_执行状态, "02", "执行中"
        ),
    压力测试_执行状态_异常(
        DictCode.压力测试_执行状态, "03", "异常"
        ),
    风险因子管理_因子值结构_单一值(
        DictCode.风险因子管理_因子值结构, "01", "单一值"
        ),
    风险因子管理_因子值结构_轻中重(
        DictCode.风险因子管理_因子值结构, "02", "轻中重"
        ),
    风险因子管理_因子值结构_曲线(
            DictCode.风险因子管理_因子值结构, "03", "曲线"
            ),
    情景类型_历史情景(
        DictCode.情景类型, "01", "历史情景"
        ),
    情景启用状态_启用(
        DictCode.情景启用状态, "01", "启用"
        ),
    情景启用状态_停用(
            DictCode.情景启用状态, "02", "停用"
            ),
    情景类型_假设情景(
            DictCode.情景类型, "02", "假设情景"
            ),
    因子风险分类_流动性风险(
        DictCode.因子风险分类, "02", "流动性风险"
        ),
    风险因子管理_流动性风险因子分类_其他(
        DictCode.风险因子管理_流动性风险因子分类, "01", "其他"
        ),
    风险因子管理_风险分类_流动性风险(
        DictCode.风险因子管理_风险分类, "02", "流动性风险"
        ),
    //wangkang
    //wangkang 20190919
    压力测试业务类型_情景(
            DictCode.压力测试业务类型, "01", "情景"
            ),
    压力测试业务类型_事件(
            DictCode.压力测试业务类型, "02", "事件"
            ),
    压力测试业务类型_压测模板(
            DictCode.压力测试业务类型, "03", "压测模板"
            ),
    压力测试业务类型_压力测试(
            DictCode.压力测试业务类型, "04", "压力测试"
            ),
    //wangkang 20191012
    风险因子管理_流动性风险因子分类_产品规模变化(
        DictCode.风险因子管理_流动性风险因子分类, "Lqu01", "产品规模变化"
        ),
    风险因子管理_流动性风险因子分类_资产变现能力(
        DictCode.风险因子管理_流动性风险因子分类, "Lqu02", "资产变现能力"
        ),
  //sunhui 20191206
    风险因子的异常处理_置为1(
        DictCode.风险因子的异常处理, "01", "风险因子的历史数据为空，风险因子变化置为0"
        ),
    风险因子的异常处理_置为null(
        DictCode.风险因子的异常处理, "02", "风险因子的历史数据为空，风险因子变化置为null"
        ),
    名单属性_白名单(DictCode.名单属性,"01","白名单"),
    名单属性_黑名单(DictCode.名单属性,"02","黑名单"),
    名单级别_一级库(DictCode.名单级别,"01","一级库"),
    名单级别_二级库(DictCode.名单级别,"02","二级库"),
    估值来源_行内(DictCode.估值来源,"BAK","行内-BAK"),

    授信占用类别_预占(DictCode.授信占用类别,"01","预占"),
    授信占用类别_实占(DictCode.授信占用类别,"02","实占"),
    授信占用来源_画面(DictCode.授信占用类别,"03","画面"),
    授信占用来源_接口(DictCode.授信占用类别,"04","接口"),
    
	;
    /**
     * 对应于数据字典code
     */
    private String value;

    public void setDictCode(DictCode dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 数据字典枚举
     */
    private DictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeListRsk(DictCode dictCode, String value, String codeName) {
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
    public static DictCodeListRsk from(DictCode dictCode, String value) {
        DictCodeListRsk[] values = DictCodeListRsk.values();
        for (DictCodeListRsk code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
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
        DictCodeListRsk[] values = DictCodeListRsk.values();
        for (DictCodeListRsk code : values) {
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
    public static List<String> valuesfromDictCode(DictCode dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeListRsk[] values = DictCodeListRsk.values();
        for (DictCodeListRsk code : values) {
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
    public DictCode getDictCode() {
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

package com.joyintech.tams.constant;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典明细枚举
 *
 * @author chenzhuo
 * @version 1.0 2016年11月12日
 * @see ModelDictCodeList
 * @since 1.0
 */
public enum ModelDictCodeList implements BaseDictCodeList {
    批处理执行结果_警告(ModelDictCode.批处理执行结果,"WARN","警告"),
    批处理执行结果_正常结束(ModelDictCode.批处理执行结果,"NOR","成功"),
    批处理执行结果_异常结束(ModelDictCode.批处理执行结果,"EXP","失败"),
    模型_映射类型_资产分类映射(ModelDictCode.模型_映射类型,"01","资产分类映射"),


    模型_估值模型名称_现金流贴现法(ModelDictCode.模型_估值模型名称,"01","现金流贴现法"),
    模型_估值模型名称_收益率法(ModelDictCode.模型_估值模型名称,"02","收益率法"),
    模型_估值模型名称_二叉树(ModelDictCode.模型_估值模型名称,"03","二叉树"),

    模型_评级类型_主体评级(ModelDictCode.模型_评级类型,"01","主体评级"),
    模型_评级类型_债项评级(ModelDictCode.模型_评级类型,"02","债项评级"),
    模型_评级类型_融资人(ModelDictCode.模型_评级类型,"03","融资人"),
    模型_评级类型_担保人(ModelDictCode.模型_评级类型,"04","担保人"),
    模型_评级类型_较高值(ModelDictCode.模型_评级类型,"05","较高值"),
    模型_评级类型_较低值(ModelDictCode.模型_评级类型,"06","较低值"),
    模型_评级类型_存借款机构(ModelDictCode.模型_评级类型,"07","较低值"),
    模型_评级类型_交易对手(ModelDictCode.模型_评级类型,"08","较低值"),
    模型_评级类型_发行人(ModelDictCode.模型_评级类型,"09","发行人"),

    模型_评级来源类型_外部(ModelDictCode.模型_评级来源类型,"01","外部"),
    模型_评级来源类型_内部(ModelDictCode.模型_评级来源类型,"02","内部"),
    模型_评级来源类型_系统计算(ModelDictCode.模型_评级来源类型,"03","系统计算"),

    模型_上市状态_上市(ModelDictCode.模型_上市状态,"01","上市"),
    模型_上市状态_未上市(ModelDictCode.模型_上市状态,"02","未上市"),
    模型_市场乘数方法_市净率法(ModelDictCode.模型_市场乘数方法,"01","市净率法"),
    模型_市场乘数方法_市盈率法(ModelDictCode.模型_市场乘数方法,"02","市盈率法"),

    估值来源_专家法_EXP(ModelDictCode.估值来源,"EXP","专家法(EXP)"),
    估值来源_模型法_MOD(ModelDictCode.估值来源,"MOD","模型法(MOD)"),

    模型_平滑区间_一个月(ModelDictCode.模型_平滑区间,"01","1个月"),
    模型_平滑区间_三个月(ModelDictCode.模型_平滑区间,"02","3个月"),

    评级机构_资管内部(ModelDictCode.评级机构,"98","资管内部"),

    模型_减值过程_数据加载(ModelDictCode.模型_减值过程,"01","数据加载"),
    模型_减值过程_减值阶段判断(ModelDictCode.模型_减值过程,"02","减值阶段判断"),
    模型_减值过程_EAD计算(ModelDictCode.模型_减值过程,"03","EAD计算"),
    模型_减值过程_PD计算(ModelDictCode.模型_减值过程,"04","PD计算"),
    模型_减值过程_ECL计算(ModelDictCode.模型_减值过程,"05","ECL计算"),

    模型_减值配置类型_资产大类评级配置(ModelDictCode.模型_减值配置类型,"01","资产大类评级配置"),
    模型_减值配置类型_特殊资产评级配置(ModelDictCode.模型_减值配置类型,"02","特殊资产评级配置"),
    模型_减值配置类型_评级PD配置(ModelDictCode.模型_减值配置类型,"03","债券、其他债权评级PD配置"),
    模型_减值配置类型_前瞻因子配置(ModelDictCode.模型_减值配置类型,"04","前瞻因子配置"),
    模型_减值配置类型_资产大类LGD配置(ModelDictCode.模型_减值配置类型,"05","资产大类LGD配置"),
    模型_减值配置类型_特殊资产LGD配置(ModelDictCode.模型_减值配置类型,"06","特殊资产LGD配置"),
    模型_减值配置类型_回购评级PD配置(ModelDictCode.模型_减值配置类型,"07","回购评级PD配置"),
    模型_减值配置类型_存款评级PD配置(ModelDictCode.模型_减值配置类型,"08","存款评级PD配置"),

    阶段_初始买入时_P1(ModelDictCode.减值阶段,"P1","一阶段"),
    阶段_买入后风险显著提升_P2(ModelDictCode.减值阶段,"P2","二阶段"),
    阶段_买入后出现减值_P3(ModelDictCode.减值阶段,"P3","三阶段"),



    累计余额_部分(ModelDictCode.是否累计余额,"03","部分更新"),

    模型数据来源_手工(ModelDictCode.模型数据来源,"01","手工"),
    模型数据来源_WIND(ModelDictCode.模型数据来源,"02","wind"),
    模型数据来源_中债(ModelDictCode.模型数据来源,"03","中债"),
    模型数据来源_模型(ModelDictCode.模型数据来源,"04","模型"),

    评级来源_模型_外部评级(ModelDictCode.评级来源_模型,"01","外部评级"),
    评级来源_模型_内部评级(ModelDictCode.评级来源_模型,"02","内部评级"),
    评级来源_模型_系统计算(ModelDictCode.评级来源_模型,"03","系统计算"),

    上市状态_上市(ModelDictCode.上市状态,"01","上市"),
    上市状态_未上市(ModelDictCode.上市状态,"02","未上市"),
    市场乘数方法_市净率法(ModelDictCode.市场乘数方法,"01","市净率法"),
    市场乘数方法_市盈率法(ModelDictCode.市场乘数方法,"02","市盈率法"),

    平滑区间_1个月(ModelDictCode.平滑区间,"01","1个月"),
    平滑区间_3个月(ModelDictCode.平滑区间,"02","3个月"),
    季度_第一季度(ModelDictCode.季度,"01","第一季度"),
    季度_第二季度(ModelDictCode.季度,"02","第二季度"),
    季度_第三季度(ModelDictCode.季度,"03","第三季度"),
    季度_第四季度(ModelDictCode.季度,"04","第四季度"),
    曲线代码_中债国债收益率曲线(ModelDictCode.曲线代码,"1231","中债国债收益率曲线"),
    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private ModelDictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    ModelDictCodeList(ModelDictCode dictCode, String value, String codeName) {
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
    public static ModelDictCodeList from(ModelDictCode dictCode, String value) {
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public static List<SelectOptionDto> fromDictCode(ModelDictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public static List<String> valuesfromDictCode(ModelDictCode dictCode) {
        List<String> result = new ArrayList<>();
        ModelDictCodeList[] values = ModelDictCodeList.values();
        for (ModelDictCodeList code : values) {
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
    public ModelDictCode getDictCode() {
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

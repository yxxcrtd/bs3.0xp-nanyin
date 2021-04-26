package com.joyintech.tams.valuation.basicdata.business.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.MstBusinessIdRule;
import com.joyintech.tams.valuation.basicdata.business.dao.ValBaseGetBussinessIdMapper;
import com.joyintech.tams.valuation.basicdata.business.dto.BussinessCodeParameter;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseGetBussinessIdService
 * @Description: 根据规则生成ID
 * @Author yanya
 * @Date 2021/3/2 0002 
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseGetBussinessIdService {

    /**
     * 业务编号生成规则mapper
     */
    @Autowired
    private ValBaseGetBussinessIdMapper valBaseGetBussinessIdMapper;

//    /**
//     *
//     */
//    @Autowired(required=false)
//    private IPlatformDate pPlatformDate;

    @Autowired(required=false)
    private BaseCommonService baseCommonService;

    /**
     * 生成业务编号
     * @param parameter
     * @return
     */
    public String buildBussinessCode(BussinessCodeParameter parameter) {
        // 生成的业务编号
        String bussinessCode = null;
        // 多条规则时直接取第一条 modify by renping 20190924 start
        List<MstBusinessIdRule> ruleList = valBaseGetBussinessIdMapper.selectRuleByParameter(parameter);
        MstBusinessIdRule rule = null;
        if (!StringUtil.isEmptyList(ruleList)) {
            rule = ruleList.get(0);
        }
        // 多条规则时直接取第一条 modify by renping 20190924 end
        if (StringUtil.isNotEmpty(rule) && StringUtil.isNotEmpty(rule.getBusinessIdRuleType())) {
            /*
             * 01:UUID 02:序列号 03:前缀+后缀 04:前缀+日期+后缀 05:独特规则
             */
            switch (rule.getBusinessIdRuleType()) {
                case "01":
                    // 直接获取
                    bussinessCode = IDGenerator.generate();
                    break;
                case "02":
                    // 通过序列号获取
                    bussinessCode = buildCodeByType2(rule);
                    break;
                case "03":
                    // 前缀+后缀
                    bussinessCode = buildCodeByType3(rule);
                    break;
                case "04":
                    // 前缀+日期+后缀
                    bussinessCode = buildCodeByType4(rule);
                    break;
                case "05":
                    // 自定义
                    bussinessCode = buildCodeByType5(rule);
                    break;
            }
        }
        return bussinessCode == null ? IDGenerator.generate(20) : bussinessCode;
    }

    /**
     * 类型2：创建方式根据序列生成 业务编号生成规则为【02:序列号】的时候，按照上面取得的前缀生成用序列名称生成序列号，作为业务编号返回给调用端
     *
     * @param rule 规则
     * @return 生成的code值
     * @see
     * @since 1.0
     */
    private String buildCodeByType2(MstBusinessIdRule rule) {
        // 前缀生成用序列名称是否为空
        if (StringUtil.isNotEmpty(rule.getPrefixSequenceName())) {
            // 根据名字获取序列号
            String nextVal = valBaseGetBussinessIdMapper.getNextValBySequenceName(
                    rule.getPrefixSequenceName());
            String bussinessCode = getFormatCode(nextVal, rule.getPrefixSequenceLen().toString());
            return bussinessCode;
        }
        return null;
    }

    /**
     * 类型3：前缀+后缀 业务编号生成规则为【03:前缀+后缀】的时候，按照下面规则生成业务编号返回给调用端
     *
     * @param rule 规则
     * @return 生成的code值
     * @see
     * @since 1.0
     */
    private String buildCodeByType3(MstBusinessIdRule rule) {
        // 根据名字获取前缀序列号
        String prefixSequence = null;
        // 后缀生成用序列名称
        String postfixSequence = null;
        // 前缀生成用序列名称不为空
        if (StringUtil.isNotEmpty(rule.getPrefixSequenceName())) {
            // 根据名字获取前缀序列号
            prefixSequence = getFormatCode(
                    valBaseGetBussinessIdMapper.getNextValBySequenceName(rule.getPrefixSequenceName()),
                    rule.getPrefixSequenceLen().toString());
        }
        // 后缀生成用序列名称不为空
        if (StringUtil.isNotEmpty(rule.getPostfixSequenceName())) {
            // 根据名字获取后缀序列号
            postfixSequence = getFormatCode(
                    valBaseGetBussinessIdMapper.getNextValBySequenceName(rule.getPostfixSequenceName()),
                    rule.getPostfixSequenceLen().toString());
        }
        // 生成的业务编号
        StringBuffer bussinessCode = new StringBuffer();
        // 前缀名 + 前缀生成用序列名称采番的序列号 + 后缀名 + 后缀生成用序列名称采番的序列号
        bussinessCode.append(nvl(rule.getPrefixName())).append(nvl(prefixSequence)).append(
                nvl(rule.getPostfixName())).append(nvl(postfixSequence));
        return bussinessCode.toString();
    }

    /**
     * 类型4：前缀+日期+后缀 业务编号生成规则为【04:前缀+日期+后缀】的时候，按照下面规则生成业务编号返回给调用端
     *
     * @param rule 规则
     * @return 生成的code值
     * @see
     * @since 1.0
     */
    private String buildCodeByType4(MstBusinessIdRule rule) {
        Date sysDate = null;
        switch (rule.getDateRuleType()) {
            case "01":
                // 平台日期
                if(baseCommonService!=null){
                    sysDate = baseCommonService.getSysDate();
                }
                else{
                    sysDate = DateUtil.now();
                }
                break;
            case "02":
                // 系统日期（AP服务器日期）
                sysDate = DateUtil.now();
                break;
            case "03":
                // 系统日期（DB日期）
                if(baseCommonService!=null){
                    sysDate = baseCommonService.getSysDate();
                }
                else{
                    sysDate = DateUtil.now();
                }
                break;
        }
        // 根据名字获取前缀序列号
        String prefixSequence = null;
        // 后缀生成用序列名称
        String postfixSequence = null;
        // 前缀生成用序列名称不为空
        if (StringUtil.isNotEmpty(rule.getPrefixSequenceName())) {
            // 根据名字获取前缀序列号
            prefixSequence = getFormatCode(
                    valBaseGetBussinessIdMapper.getNextValBySequenceName(rule.getPrefixSequenceName()),
                    rule.getPrefixSequenceLen().toString());
        }
        // 后缀生成用序列名称不为空
        if (StringUtil.isNotEmpty(rule.getPostfixSequenceName())) {
            // 根据名字获取后缀序列号
            postfixSequence = getFormatCode(
                    valBaseGetBussinessIdMapper.getNextValBySequenceName(rule.getPostfixSequenceName()),
                    rule.getPostfixSequenceLen().toString());
        }
        // 生成的业务编号
        StringBuffer bussinessCode = new StringBuffer();
        // 前缀名 + 前缀生成用序列名称采番的序列号 + 上面取得的日期 + 后缀名 + 后缀生成用序列名称采番的序列号
        bussinessCode.append(nvl(rule.getPrefixName())).append(nvl(prefixSequence)).append(
                DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT)).append(
                nvl(rule.getPostfixName())).append(nvl(postfixSequence));
        return bussinessCode.toString();
    }

    /**
     * 类型5：创建方式根据序列生成 <功能详细描述>
     *
     * @param rule 规则
     * @return 生成的code值
     * @throws Exception
     * @see
     * @since 1.0
     */
    private String buildCodeByType5(MstBusinessIdRule rule) {
        if (StringUtil.isNotEmpty(rule.getBusinessIdServiceName())
                && StringUtil.isNotEmpty(rule.getBusinessIdMethodName())) {
            Class<?> clazz;
            try {
                clazz = Class.forName(rule.getBusinessIdServiceName());
                Method method = clazz.getMethod(rule.getBusinessIdMethodName());
                return (String)method.invoke(clazz.newInstance());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据当前值和长度生成格式化长度
     *
     * @param code 待格式化数据
     * @param rule 格式化长度
     * @return 结果
     * @see
     * @since 1.0
     */
    private String getFormatCode(String code, String rule) {
        rule = "%0" + rule + "d";
        return String.format(rule, Long.valueOf(code));
    }

    /**
     * 获取默认字符串 <功能详细描述>
     *
     * @param code 目标值
     * @param def 默认值
     * @return 结果
     * @see
     * @since 1.0
     */
    private String nvl(String code, String def) {
        return StringUtil.isNotEmpty(code) ? code : def != null ? def : "";
    }

    /**
     * 默认非空方法 <功能详细描述>
     *
     * @param code 非空字段
     * @return 返回值
     * @see
     * @since 1.0
     */
    private String nvl(String code) {
        return nvl(code, "");
    }
}

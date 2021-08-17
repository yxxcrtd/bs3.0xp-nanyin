package com.joyintech.service.risk.rskbotdetailreport.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.risk.rskbotdetailreport.dao.RskValBotDetailReportMapper;
import com.joyintech.service.risk.rskbotdetailreport.dto.RskValBotDetailReportDto;
import com.joyintech.tams.common.base.entity.ChbMonthAssetRule;
import com.joyintech.tams.common.util.ChbMonthAssetRuleUtil;
import com.joyintech.tams.constant.DictCode;

import cn.hutool.core.date.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("backRskValBotDetailReportService")
public class RskValBotDetailReportService extends BaseService<RskValBotDetailReportDto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RskValBotDetailReportMapper rskValBotDetailReportMapper;

    @Autowired
    private ChbMonthAssetRuleUtil chbMonthAssetRuleUtil;


    public Page<RskValBotDetailReportDto> selectForPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {
    	String valdate = (String) pageParam.get("valDate");
    	pageParam.put("valDate", DateUtil.parse(valdate));
    	Page<RskValBotDetailReportDto>  pg = super.queryForPage(page, length, () -> rskValBotDetailReportMapper.queryForPage(pageParam));
        if(null != pg && !StringUtil.isEmptyList(pg.getRoot())){
            //根据返回的数据处理G06二级分类名称以及二次分类大归类
            List<RskValBotDetailReportDto> result = parseG06AssetType2(pg.getRoot());
            //对部分数据进行处理
            calDetailList(result);
            //按照资产二级分类升序排序
            result = result.stream().sorted(Comparator.comparing(RskValBotDetailReportDto::getAssettype2)).collect(Collectors.toList());
            pg.setRoot(result);
            pg.setRecords(result.size());
            Double totle = ArithmeticUtil.divide(new BigDecimal(result.size()),new BigDecimal(length)).doubleValue();
            pg.setTotal((int) Math.ceil(totle));
        }
        return pg;
    }

    /**
     *
     * @param list
     */
    private void calDetailList(List<RskValBotDetailReportDto> list) {
        list.forEach(x->{
            //【估值表内数量】列银行存款，负债、回购应显示为空
            if("1001".equals(x.getAssettype2())
                ||"2000".equals(x.getAssettype2())
                ||"1108".equals(x.getAssettype2())
                ||"1902".equals(x.getAssettype2())){
                x.sethColumn(null);
                x.setiColumn(null);
            }
            //add by yjp ZGBSEI-292 20210817 估值表内应收利息/股利：其他负债和回购显示为空 start
            if("2000".equals(x.getAssettype2())
                    ||"1108".equals(x.getAssettype2())
                    ||"1902".equals(x.getAssettype2())){
                x.setkColumn(null);
            }
            //add by yjp ZGBSEI-292 20210817 估值表内应收利息/股利：其他负债和回购显示为空 end
            //最新债项评级
            x.setpColumn(SysDataUtil.getNameFromDataDict(DictCode.评级结果, x.getpColumn()));
            //最新主体评级
            x.setqColumn(SysDataUtil.getNameFromDataDict(DictCode.评级结果, x.getqColumn()));
            //J列 = L列 - K列
            BigDecimal lAmt = new BigDecimal(null == x.getlColumn()?"0":x.getlColumn());
            BigDecimal kAmt = new BigDecimal(null == x.getkColumn()?"0":x.getkColumn());
            x.setjColumn(lAmt.subtract(kAmt).toString());

            //M列 = E列 / D列 * L列
            BigDecimal eAmt = new BigDecimal(null == x.geteColumn()?"0":x.geteColumn());
            BigDecimal dAmt = new BigDecimal(null == x.getdColumn()?"0":x.getdColumn());
            if(dAmt.compareTo(BigDecimal.ZERO) != 0){
                x.setmColumn((eAmt.divide(dAmt,14,RoundingMode.HALF_UP).multiply(lAmt).setScale(2,RoundingMode.HALF_UP)).toString());
            }
        });
    }

    /**
     * 根据头寸以及资产要素属性对资产二级分类进行归类(同银行月报规则)
     * @param list
     */
    private List<RskValBotDetailReportDto> parseG06AssetType2(List<RskValBotDetailReportDto> list) {
        List<RskValBotDetailReportDto> resultList = new ArrayList<>();
        // 规则项检查
        if (!chbMonthAssetRuleUtil.checkRuleIsRight(true)) {
            return resultList;
        }
        String xyz = "1112,1113,1114,1115,1116,1117";
        for (RskValBotDetailReportDto position : list) {
            if (null == position.getAssettype2()) {
                continue;
            }
            for (ChbMonthAssetRule singleRule : ChbMonthAssetRule.values()) {
                // 合计值单独处理
                if (singleRule.getPropertyName().contains("合计")) {
                    continue;
                }
                //add by yjp  ZGBSEI-286  20210817 start
                if (singleRule.getPropertyCode().contains("AMT3_1_1") || singleRule.getPropertyCode().contains("AMT3_1_2")
                        ||singleRule.getPropertyCode().contains("AMT3_1_3")
                        ||singleRule.getPropertyCode().contains("AMT3_2_1") ||singleRule.getPropertyCode().contains("AMT3_2_2")
                        ||singleRule.getPropertyCode().contains("AMT3_2_3") ||singleRule.getPropertyCode().contains("AMT3_2_4")
                        ||singleRule.getPropertyCode().contains("AMT3_2_5") ||singleRule.getPropertyCode().contains("AMT3_2_6")
                        ||singleRule.getPropertyCode().contains("AMT3_3_1") ||singleRule.getPropertyCode().contains("AMT3_3_2")
                        ||singleRule.getPropertyCode().contains("AMT3_3_3") ||singleRule.getPropertyCode().contains("AMT3_3_4")
                        ||singleRule.getPropertyCode().contains("AMT3_3_5") ||singleRule.getPropertyCode().contains("AMT3_3_6")
                        ||singleRule.getPropertyCode().contains("AMT3_3_7") ||singleRule.getPropertyCode().contains("AMT3_3_8")
                        ||singleRule.getPropertyCode().contains("AMT3_3_9")) {
                    continue;
                }
                //add by yjp  ZGBSEI-286  20210817 end
                // 根据配置规则代码匹配资产头寸中相应属性
                if (!StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        && !StringUtil.isEmptyList(singleRule.getRuleValueList())) {
                    // 该笔头寸记录是否匹配配置规则
                    boolean isPattern = true;
                    // 判断是否多条件交集处理
                    for (int i = 0; i < singleRule.getRuleCodeList().size(); i++) {
                        // 重新初始化是否匹配配置规则标记
                        isPattern = true;
                        // 债券信用评级
                        if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGAA+")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() == null || chbMonthAssetRuleUtil.compareRating(position.getRating(), singleRule.getRuleValueList().get(i)) < 0
                            ) {
                                isPattern = false;
                                break;
                            }
                        } else if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGAA-")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() == null || chbMonthAssetRuleUtil.compareRating(position.getRating(), singleRule.getRuleValueList().get(i)) >= 0
                            ) {
                                isPattern = false;
                                break;
                            }
                        } else if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGNULL")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() != null) {
                                isPattern = false;
                                break;
                            }
                        } else { // 非债券信用评级
                            // 判断是否多条件任取其一，即rulecode和value一一对应以“|”隔开，格式为code1|code2,value1|value2
                            if (singleRule.getRuleCodeList().get(i).contains(GlobalConstant.VERTICAL_LINE)
                                    || singleRule.getRuleValueList().get(i).contains(GlobalConstant.VERTICAL_LINE)) {

                                List<String> ruleCodeList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                        singleRule.getRuleCodeList().get(i));
                                List<String> valueList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                        singleRule.getRuleValueList().get(i));
                                isPattern = false;
                                String ruleCode = ruleCodeList.get(0);
                                for (int k = 0; k < valueList.size(); k++) {
                                    // 如果ruleCode相同但是多条件，则支持只输入一个code，如：code1,value1|value2|value3
                                    // 否则需一一对应以“|”隔开，格式为code1|code2,value1|value2
                                    if (ruleCodeList.size() > 1) {
                                        ruleCode = ruleCodeList.get(k);
                                    }
                                    // 支持反向判断！value1
                                    if (valueList.get(k).startsWith("!")) {
                                        // 只要有一条满足则该条头寸不计入
                                        if (!valueList.get(k).substring(1, valueList.get(k).length())
                                                .equals((String) chbMonthAssetRuleUtil.getGetMethod(position, ruleCode))) {
                                            isPattern = true;
                                            break;
                                        }
                                    } else {
                                        // 只有多个条件都不满足时该头寸才不计入
                                        if (valueList.get(k).equals((String) chbMonthAssetRuleUtil.getGetMethod(position, ruleCode))) {
                                            isPattern = true;
                                            break;
                                        }
                                    }
                                }
                                if (!isPattern) {
                                    break;
                                }
                            } else {// 多条件同时满足才计入
                                // 支持反向判断！value1
                                if (singleRule.getRuleValueList().get(i).startsWith("!")) {
                                    // 只要有一条满足则该条头寸不计入
                                    if (singleRule.getRuleValueList().get(i).substring(1, singleRule.getRuleValueList().get(i).length())
                                            .equals((String) chbMonthAssetRuleUtil.getGetMethod(position,
                                                    singleRule.getRuleCodeList().get(i)))) {
                                        isPattern = false;
                                        break;
                                    }
                                } else {
                                    // 只要有一条不满足则该条头寸不计入
                                    if (!singleRule.getRuleValueList().get(i)
                                            .equals((String) chbMonthAssetRuleUtil.getGetMethod(position, singleRule.getRuleCodeList()
                                                    .get(i)))) {
                                        isPattern = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (isPattern) {
                        RskValBotDetailReportDto dto = new RskValBotDetailReportDto();
                        try {
                            BeanUtil.copyProperties(dto, position);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        //G06二级分类
                        dto.setgColumn(singleRule.getPropertyName());
                        //G06二级分类明细归类
                        dto.setoColumn(singleRule.getPropertyCategoied());
                        resultList.add(dto);
                    }
                }
            }
        }
        return resultList;
    }
}

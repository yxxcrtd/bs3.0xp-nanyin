package com.joyintech.tams.svreport.chb.monthreportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.report.interf.common.constant.InterfMappingConstants;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.MstCustomerInfoDetail;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.position.dto.ChbAssetPosition;
import com.joyintech.tams.svreport.position.service.AssetPositionService;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class ChbMonthReportDetailAssetUtil {

    @Autowired
    private ChbMonthReportDetailAssetMapper chbMonthReportDetailAssetMapper;


    /**
     * 映射配置mapper
     */
    @Autowired
    private ChbTypeMappingExMapper chbTypeMappingExMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;


    private static List<String> code = Arrays.asList("1201","1210","1202","1203","1204","1205","1206","1207","1208","1209","1299","2201","2202","2101","2299");

    private static List<String> notexistsList = Arrays.asList("AMT1_4_a","AMT1_4_b","AMT1_4_c","AMT1_13_1","AMT1_13_2","AMT1_13_3","AMT1_13_4","AMT1_13_5","AMT1_13_6","AMT1_12_9_a","AMT1_12_2"
            ,"AMT1_15_a","AMT1_15_b","AMT1_5_17_a","AMT1_13_7_a","AMT1_15_c","AMT1_12_1");


    /**
     * 数据赋值
     * @param position
     * @param prodInfo
     * @param chbAssetInfo
     * @param reportDetail
     * @return
     */
    public static ChbMonthAssetPositiondetail buildAssetDetail(ChbMonthAssetPosition position, ChbPrdBaseinfo prodInfo, ChbAssetInfo chbAssetInfo, ChbMonthReportDetail reportDetail){
        ChbMonthAssetPositiondetail chbMonthAssetPositiondetail = new ChbMonthAssetPositiondetail();

        //需要屏蔽的数据类型
        if(reportDetail.getPropertycode().startsWith("AMT3")||
                notexistsList.contains(reportDetail.getPropertycode())){
            return null;
        }

        //相同内容字段直接赋值
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,prodInfo);

        if(null!=chbAssetInfo){
//            BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,chbAssetInfo);
            chbMonthAssetPositiondetail.setOrgAssetType1(chbAssetInfo.getOrgAssetType1());
            chbMonthAssetPositiondetail.setOrgAssetType2(chbAssetInfo.getOrgAssetType2());
            chbMonthAssetPositiondetail.setOrgAssetType3(chbAssetInfo.getOrgAssetType3());
            chbMonthAssetPositiondetail.setRate(chbAssetInfo.getAssetYield());
            chbMonthAssetPositiondetail.setCcy(chbAssetInfo.getCcy());
        }
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,position);
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,reportDetail);

        if(null!=chbAssetInfo){
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getOptionFlag())){
                chbMonthAssetPositiondetail.setOpenplan(chbAssetInfo.getExerciseDate());
            }
            chbMonthAssetPositiondetail.setSubjectName(doSubjectName(chbAssetInfo,chbMonthAssetPositiondetail.getOrgAssetType2(),chbMonthAssetPositiondetail.getOrgAssetType1()));
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecPrefer())){
                chbMonthAssetPositiondetail.setIsYhbcgj(DictCodeListExt3.中债月度明细_银行资本补充工具_优先股.getValue());
            }
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecPerpetual())){
                chbMonthAssetPositiondetail.setIsYhbcgj((null!=chbMonthAssetPositiondetail.getIsYhbcgj()?chbMonthAssetPositiondetail.getIsYhbcgj()+",":"")+DictCodeListExt3.中债月度明细_银行资本补充工具_永续债.getValue());
            }
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecCapital())){
                chbMonthAssetPositiondetail.setIsYhbcgj((null!=chbMonthAssetPositiondetail.getIsYhbcgj()?chbMonthAssetPositiondetail.getIsYhbcgj()+",":"")+DictCodeListExt3.中债月度明细_银行资本补充工具_二级资本债.getValue());
            }
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecKzzq())){
                chbMonthAssetPositiondetail.setIsYhbcgj((null!=chbMonthAssetPositiondetail.getIsYhbcgj()?chbMonthAssetPositiondetail.getIsYhbcgj()+",":"")+DictCodeListExt3.中债月度明细_银行资本补充工具_可转债.getValue());
            }
        }

        //非标转标字段塞值
        if(reportDetail.getPropertycode().startsWith("AMT1_5")){
            String isFbhy = null;
            if(ruleFlag(ChbMonthAssetRule.农林牧渔业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_农林牧渔业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.制造业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_制造业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.采矿业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_采矿业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.建筑业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_建筑业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.基础设施建设,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_基础设施建设.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.房地产业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_房地产业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.金融业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_金融业.getValue();
            }else if (ruleFlag(ChbMonthAssetRule.其他服务业,position)){
                isFbhy =DictCodeListExt3.中债月度明细_非标行业_其他服务业.getValue();
            }
            chbMonthAssetPositiondetail.setIsFbhy(isFbhy);
        }
        chbMonthAssetPositiondetail.setDetailno(reportDetail.getPropertycode());
        chbMonthAssetPositiondetail.setTerm(reportDetail.getTermid());
        chbMonthAssetPositiondetail.setOldAssetCode(position.getOldAssetCode());
        //生成主键
        chbMonthAssetPositiondetail.setPosuuid(IDGenerator.generate(36));

        return chbMonthAssetPositiondetail;
    }

    /**
     * 	债券：发行人
     * 	存款类资产：存/借款机构，接口表中叫资金存入银行
     * 	资产支持证券：发行人
     * 	基金：管理人，接口表中叫基金公司
     * 	项目类：融资人
     * 	计划类：管理人
     * 	股票：公司名称
     * 	质押式回购、买断式回购、金融衍生品：交易对手
     * 	用科目展示的资产项，没有具体主体，展示为空
     * @param assetInfo
     * @param assetType2
     * @param assetType
     * @return
     */
    public static String doSubjectName(ChbAssetInfo assetInfo,String assetType2,String assetType){
        String  SubjectName = null;
        if(DictCodeListStl.资产二级分类_资产支持证券.getValue().equals(assetType2)){
            SubjectName = assetInfo.getChbIssuerId();
        }else if(DictCodeListExt3.中债_上游资产一级分类_债券.getValue().equals(assetType)){
            SubjectName = assetInfo.getChbIssuerId();
        }else if(DictCodeListExt3.中债_上游资产一级分类_存款类资产.getValue().equals(assetType)){
            if("D02".equals(assetType2)){
                SubjectName = assetInfo.getBankName();
            }
            if("D01".equals(assetType2)){
                SubjectName = assetInfo.getRivalParty();
            }
        }else if(DictCodeListExt3.中债_上游资产一级分类_基金类资产.getValue().equals(assetType)){
            SubjectName = assetInfo.getFundEnpeName();
        }else if(DictCodeListExt3.中债_上游资产一级分类_项目类资产.getValue().equals(assetType)){
            SubjectName = assetInfo.getChbFinancierId();
        }else if(DictCodeListExt3.中债_上游资产一级分类_计划类利率型.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_计划类净值型.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_计划类货币型.getValue().equals(assetType)){
            SubjectName = assetInfo.getChbManagerId();
        }else if(DictCodeListExt3.中债_上游资产一级分类_股票.getValue().equals(assetType)){
            SubjectName = assetInfo.getAssetName();
        }else if(DictCodeListExt3.中债_上游资产一级分类_质押式回购.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_买断式回购.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_外汇即期.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_外汇远期.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_外汇掉期.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_利率互换.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_债券借贷.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_拆借.getValue().equals(assetType)
                ||DictCodeListExt3.中债_上游资产一级分类_金融衍生品.getValue().equals(assetType)){


            SubjectName = assetInfo.getRivalParty();
        }


        return SubjectName;
    }

    public List<ChbMonthAssetPositiondetail>  insertBefore(List<ChbMonthAssetPositiondetail> prdPositionList,Date batchDate){

        List<ChbMonthAssetPositiondetail> postionList = new ArrayList<>();

        /****需要特殊处理的科目*/
        Map<String, ChbMonthAssetPositiondetail>  detail1001 = prdPositionList.stream().filter(detail ->
               (detail.getDetailno().equals("AMT1_4_2")||detail.getDetailno().equals("AMT1_4_2_a")
                       ||detail.getDetailno().equals("AMT1_5_1")||detail.getDetailno().equals("AMT1_5_1_a")
                       ||detail.getDetailno().equals("AMT1_5_16")||detail.getDetailno().equals("AMT1_5_16_a")||detail.getDetailno().equals("AMT1_5_16_b")
               )).collect(Collectors.toMap(ChbMonthAssetPositiondetail::getDetailCode,Positiondetail -> Positiondetail));

        /**展示产品购买资产首次买入交易的交易对手名称***/
        Map<String,Object> paraMap = new HashMap<>();
        Set<String> codeList = prdPositionList.stream().map(ChbMonthAssetPositiondetail::getCounterListCode).collect(Collectors.toSet());
        paraMap.put("keysList",codeList);

        //展示产品购买资产首次买入交易的交易对手名称
        List<SelectOptionDto> oDto = chbMonthReportDetailAssetMapper.getCounterList(paraMap);
        Map<String,String> CounterMap = oDto.stream().collect(JoyinCollectors.toMap(x -> x.getId(), x -> x.getText()));
        Map<String,String> CounterTypeMap = oDto.stream().collect(JoyinCollectors.toMap(x -> x.getId(), x -> x.getParentId()));
        paraMap.put("customerList", oDto.stream().map(item->item.getCol()).collect(Collectors.toList()));

        //展示资产对应主体（没有主体的找交易对手）在市场机构中的行业一级分类
        List<MstCustomerInfoDetail> customerList = chbMonthReportDetailAssetMapper.getCustomerList(null);
        Map<String,MstCustomerInfoAdd> customerMap = customerList.stream().collect(Collectors.toMap(MstCustomerInfoDetail::getCustomerId,a -> a, (k1, k2) -> k1));
        Map<String,MstCustomerInfoAdd> customerNameMap = customerList.stream().collect(Collectors.toMap(MstCustomerInfoDetail::getCustomerName,a -> a, (k1, k2) -> k1));

        for(ChbMonthAssetPositiondetail dto : prdPositionList) {
            //如果币种为空.默认塞值人民币
            if(null!=dto.getCcy()){
                dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            }

            //先赋值行业  先用编码.再用code最后用交易对手
            MstCustomerInfoAdd info = null != customerMap.get(dto.getSubjectName()) ? customerMap.get(dto.getSubjectName()) :
                    null != customerNameMap.get(dto.getSubjectName()) ? customerNameMap.get(dto.getSubjectName()) : customerMap.get(CounterTypeMap.get(dto.getCounterListCode()));
            //展示资产对应主体（没有主体的找交易对手）在市场机构中的行业一级分类
            if (null != info) {
                dto.setIndustry1(info.getIndustry());
                dto.setIndustry2(info.getIndustry2());
                dto.setIndustry3(info.getIndustry3());
                dto.setIndustry4(info.getIndustry4());
            }

            //再赋值交易对手名称  展示产品购买资产首次买入交易的交易对手名称
            dto.setRivalParty(CounterMap.get(dto.getProdId() + "_" + dto.getAssetCode()));

            /***1.4.2 地方政府债券特殊处理**/
            if (dto.getDetailno().equals("AMT1_4_2")) {
                //转换为AMT1_4_2_b
                dto.setDetailno("AMT1_4_2_b");
                //如果包含专项债券
                if (null != detail1001.get(dto.getCounterListCode() + "_AMT1_4_2_a")) {
                    if (null != dto.getAssetvalueA()) {
                        dto.setAssetvalueA(dto.getAssetvalueA().subtract(
                                null != detail1001.get(dto.getCounterListCode() + "_AMT1_4_2_a") ? detail1001.get(dto.getCounterListCode() + "_AMT1_4_2_a").getAssetvalueA() : BigDecimal.ZERO));
                    }
                    if (null != dto.getAssetvalueB()) {
                        dto.setAssetvalueB(dto.getAssetvalueB().subtract(null != detail1001.get(dto.getCounterListCode() + "_AMT1_4_2_a") ?
                                detail1001.get(dto.getCounterListCode() + "_AMT1_4_2_a").getAssetvalueB() : BigDecimal.ZERO));
                    }
                }
                if((null==dto.getAssetvalueA()||dto.getAssetvalueA().compareTo(BigDecimal.ZERO)!=0) &&(null==dto.getAssetvalueB()||dto.getAssetvalueB().compareTo(BigDecimal.ZERO)!=0)){
                    postionList.add(dto);
                }
            } else if (dto.getDetailno().equals("AMT1_5_1")) {
                //转换为AMT1_4_2_b
                dto.setDetailno("AMT1_5_1_b");
                //如果包含专项债券
                if (null != detail1001.get(dto.getCounterListCode() + "_AMT1_5_1_a")) {
                    if (null != dto.getAssetvalueA()) {
                        dto.setAssetvalueA(dto.getAssetvalueA().subtract(
                                null != detail1001.get(dto.getCounterListCode() + "_AMT1_5_1_a") ? detail1001.get(dto.getCounterListCode() + "_AMT1_5_1_a").getAssetvalueA() : BigDecimal.ZERO));
                    }
                    if (null != dto.getAssetvalueB()) {
                        dto.setAssetvalueB(dto.getAssetvalueB().subtract(null != detail1001.get(dto.getCounterListCode() + "_AMT1_5_1_a") ?
                                detail1001.get(dto.getCounterListCode() + "_AMT1_5_1_a").getAssetvalueB() : BigDecimal.ZERO));
                    }

                }
                if((null==dto.getAssetvalueA()||dto.getAssetvalueA().compareTo(BigDecimal.ZERO)!=0) &&(null==dto.getAssetvalueB()||dto.getAssetvalueB().compareTo(BigDecimal.ZERO)!=0)){
                    postionList.add(dto);
                }
            }else if (dto.getDetailno().equals("AMT1_5_16")) {
                    //转换为AMT1_5_16_b
                    dto.setDetailno("AMT1_5_16_c");
                    BigDecimal assetValueA = dto.getAssetvalueA();
                    BigDecimal assetValueB = dto.getAssetvalueB();

                    //如果包含专项债券
                    if (null != detail1001.get(dto.getCounterListCode() + "_AMT1_5_16_a")) {
                        ChbMonthAssetPositiondetail adto = detail1001.get(dto.getCounterListCode() + "_AMT1_5_16_a");
                        assetValueA = assetValueA.subtract(null != adto.getAssetvalueA() ? adto.getAssetvalueA() : BigDecimal.ZERO);
                        assetValueB = assetValueB.subtract(null != adto.getAssetvalueB() ? adto.getAssetvalueB() : BigDecimal.ZERO);
                    }
                    if (null != detail1001.get(dto.getCounterListCode() + "_AMT1_5_16_b")) {
                        ChbMonthAssetPositiondetail adto = detail1001.get(dto.getCounterListCode() + "_AMT1_5_16_b");
                        assetValueA = assetValueA.subtract(null != adto.getAssetvalueA() ? adto.getAssetvalueA() : BigDecimal.ZERO);
                        assetValueB = assetValueB.subtract(null != adto.getAssetvalueB() ? adto.getAssetvalueB() : BigDecimal.ZERO);
                    }

                    dto.setAssetvalueA(assetValueA);
                    dto.setAssetvalueB(assetValueB);
                    if((null==dto.getAssetvalueA()||dto.getAssetvalueA().compareTo(BigDecimal.ZERO)!=0) &&(null==dto.getAssetvalueB()||dto.getAssetvalueB().compareTo(BigDecimal.ZERO)!=0)){
                        postionList.add(dto);
                    }
                }else{
                    postionList.add(dto);
                }
            }


        return postionList;
    }




    public static boolean ruleFlag (ChbMonthAssetRule singleRule, ChbMonthAssetPosition position){
        boolean isPattern = true;
        String xyz="1112,1113,1114,1115,1116,1117";
        // 判断是否多条件交集处理
        for (int i = 0; i < singleRule.getRuleCodeList().size(); i++){
            // 重新初始化是否匹配配置规则标记
            isPattern = true;
            // 债券信用评级
            if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGAA+")){
                if (!xyz.contains(position.getAssettype2())||position.getRating() == null||compareRating(position.getRating(),singleRule.getRuleValueList().get(i)) < 0
                ){
                    isPattern = false;
                    break;
                }
            } else if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGAA-")){
                if (!xyz.contains(position.getAssettype2())||position.getRating() == null||compareRating(position.getRating(),singleRule.getRuleValueList().get(i)) >= 0
                ){
                    isPattern = false;
                    break;
                }
            } else if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGNULL")){
                if (!xyz.contains(position.getAssettype2())||position.getRating() != null){
                    isPattern = false;
                    break;
                }
            }  else { // 非债券信用评级
                // 判断是否多条件任取其一，即rulecode和value一一对应以“|”隔开，格式为code1|code2,value1|value2
                if (singleRule.getRuleCodeList().get(i).contains(GlobalConstant.VERTICAL_LINE)
                        || singleRule.getRuleValueList().get(i).contains(GlobalConstant.VERTICAL_LINE)){

                    List<String> ruleCodeList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                            singleRule.getRuleCodeList().get(i));
                    List<String> valueList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                            singleRule.getRuleValueList().get(i));
                    isPattern = false;
                    String ruleCode = ruleCodeList.get(0);
                    for (int k = 0; k < valueList.size() ; k++){
                        // 如果ruleCode相同但是多条件，则支持只输入一个code，如：code1,value1|value2|value3
                        // 否则需一一对应以“|”隔开，格式为code1|code2,value1|value2
                        if (ruleCodeList.size() > 1){
                            ruleCode = ruleCodeList.get(k);
                        }
                        // 支持反向判断！value1
                        if (valueList.get(k).startsWith("!")){
                            // 只要有一条满足则该条头寸不计入
                            if (!valueList.get(k).substring(1,valueList.get(k).length())
                                    .equals((String)getGetMethod(position,ruleCode))){
                                isPattern = true;
                                break;
                            }
                        } else {
                            // 只有多个条件都不满足时该头寸才不计入
                            if (valueList.get(k).equals((String)getGetMethod(position,ruleCode))){
                                isPattern = true;
                                break;
                            }
                        }
                    }
                    if (!isPattern){
                        break;
                    }
                }else {// 多条件同时满足才计入
                    // 支持反向判断！value1
                    if (singleRule.getRuleValueList().get(i).startsWith("!")){
                        // 只要有一条满足则该条头寸不计入
                        if (singleRule.getRuleValueList().get(i).substring(1,
                                singleRule.getRuleValueList().get(i).length())
                                .equals((String)getGetMethod(position,
                                        singleRule.getRuleCodeList().get(i)))){
                            isPattern = false;
                            break;
                        }
                    } else {
                        // 只要有一条不满足则该条头寸不计入
                        if (!singleRule.getRuleValueList().get(i)
                                .equals((String)getGetMethod(position,singleRule.getRuleCodeList()
                                        .get(i)))){
                            isPattern = false;
                            break;
                        }
                    }
                }
            }
        }
        return isPattern;
    }


    /**
     * 功能:判断评级信息大小
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月11日
     * @param s1 待比较评级结果
     * @param s2 评级比较标准    S2固定只能为AA+或AA-
     * @return >0 s1>s2 =0 s1 = s2 <0 s1<s2
     */
    private static int compareRating(String s1,String s2){
        if(s1.equals(s2)){
            return 0;
        }
        if(s2.equals("AA+")){
            if(s1.length()<s2.length()){
                return -1;
            }else{
                if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")){
                    return 1;
                }else{
                    return -1;
                }

            }
        }else{
            if(s1.length()<s2.length()){
                return -1;
            }else{
                if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")||s1.substring(0, 3).equals("AA+")){
                    return 1;
                }else{
                    return -1;
                }

            }
        }
    }

    /**
     * 根据属性，获取get方法(不区分大小写)
     * @param ob 对象
     * @param name 属性名
     * @return
     */
    private static Object getGetMethod(Object ob , String name){
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                try {
                    return m[i].invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * 估值表现金查询方式拆分
     * @param assetPositionListAdd
     * @param param
     */
    public void buildPrdProstion(List<ChbMonthAssetPositiondetail> assetPositionListAdd,Map<String, Object> param){
        // 查询当日的头寸数据
        String monthReportCalWay = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ASSETPOSITION_CALWAY);
        String isBtm = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_CHL_BTM);
        String iscal= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ASSETPOSITION_CASHCALWAY);
        String subjectno = null;
        //如果是移动平均法,且取值不是科目余额表中.则跳出获取估值科目信息的方法
        if(!SVReportGlobalConstant.ASSETPOSITION_CALWAY_AVERAGE
                .equals(monthReportCalWay)&&DictCodeList.是否_中债_是.getValue().equals(iscal)){
            return;
        }else if(SVReportGlobalConstant.ASSETPOSITION_CALWAY_MARKETPRICE
                .equals(monthReportCalWay)){
            subjectno = InterfMappingConstants.MAPPINGCODE_CASH_SUBJECTNO_MKT;

        }else if(!SVReportGlobalConstant.ASSETPOSITION_CALWAY_AVERAGE.equals(monthReportCalWay)){
            subjectno = InterfMappingConstants.MAPPINGCODE_CASH_SUBJECTNO_AVG;
        }
        // 通过系统参数判断是查询一体化还是中债子系统
        String isSubSystem = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SUB_SYSTEM);
        // 查询科目余额
        List<String> subjectNoList = new ArrayList<String>();
        List<ChbTypeMappingEx>  subjectNoMappingList = chbTypeMappingExMapper
                .selectByMappingcode(subjectno);
        for(ChbTypeMappingEx mapping: subjectNoMappingList){
            // 获取源属性1：科目号
            subjectNoList.add(mapping.getSrcfield1());
        }
        param.put("subjectNoList", subjectNoList);

        if (DictCodeList.是否_中债_是.getValue().equals(isSubSystem)){
            assetPositionListAdd.addAll(chbMonthReportDetailAssetMapper.selectBalanceDataSubSystem(param));
        } else if (DictCodeList.是否_中债_否.getValue().equals(isSubSystem)){
            // 读取是否穿透开关
            param.put("isBtm", isBtm);
            assetPositionListAdd.addAll(chbMonthReportDetailAssetMapper.selectBalanceData(param));
        } else {
            throw new RuntimeException("系统参数中：SVR_IS_SUB_SYSTEM配置错误！");
        }

    }





    /**
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<ChbMonthAssetPositiondetail> prdPositionList,String term,Date batchDate){

        if(null!=prdPositionList&&prdPositionList.size()==0){
            return 0;
        }
        //部分数据的统一处理
        this.insertBefore(prdPositionList,batchDate);

        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        chbMonthReportDetailAssetMapper.deleteDetail(term);


        //插入list大小
        int dataCount = prdPositionList.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            dataCount = chbMonthReportDetailAssetMapper.insertBatch(prdPositionList);
            return dataCount;
        }

        List<ChbMonthAssetPositiondetail> newDataList = new ArrayList<ChbMonthAssetPositiondetail>();
        newDataList.addAll(prdPositionList);

        // 分批存入
        int part = dataCount / maxDataCountOneTime; // 分批数
        int insertCount = 0;
        for (int i = 0; i < part; i++ ) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList);
        }
        return insertCount;
    }
}

package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 18:31
 */

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinRecommentMapper;
import com.joyintech.service.valuation.basicdata.base.dto.*;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductRecommentMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseFinProductBaseInfoService
 * @Description: 金融产品基础信息操作共通
 * @Author Administrator
 * @Date 2021/3/4 0004
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductBaseInfoService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductBaseInfoService {
    /**
     * 自动生成标的类金融产品表mapper对象
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 自动生成标的类金融产品附属表mapper对象
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;

    /**
     * 自动生成金融产品分类表mapper对象
     */
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    // 增加推荐方与推荐费率 add by renping 20191014 start
    /**
     * 自动生成标的类金融产品推荐表mapper对象
     */
    @Autowired
    private FinProductRecommentMapper finProductRecommentMapper;

    /**
     * 标的类金融产品推荐mapper对象
     */
    @Autowired(required = false)
    private BaseFinRecommentMapper baseFinRecommentMapper;

    /**
     * 金融产品现金流信息组件
     */
    @Autowired(required = false)
    private BaseFinProductCashInfoService baseFinProductCashInfoService;

    /**
     * 金融产品现金流支付规则组件
     */
    @Autowired(required = false)
    private BaseFinProductPayRuleService baseFinProductPayRuleService;

    /**
     * 金融产品现金流支付计划组件
     */
    @Autowired(required = false)
    private BaseFinProductCashPayPlanService baseFinProductCashPayPlanService;

    /**
     * 现金流计算规则组件
     */
    @Autowired(required = false)
    private BaseFinProductCashCalcRuleService baseFinProductCashCalcRuleService;

    /**
     * 现金流计提规则组件
     */
    @Autowired(required = false)
    private BaseFinProductCashCountingRuleService baseFinProductCashCountingRuleService;

    /**
     * 现金流利率规则信息组件
     */
    @Autowired(required = false)
    private BaseFinProductCashRateRuleService baseFinProductCashRateRuleService;

    /**
     * 金融产品现金流行权规则组件
     */
    @Autowired(required = false)
    private BaseFinProductExerciseRuleService baseFinProductExerciseRuleService;

    /**
     * 金融产品现金流行权计划组件
     */
    @Autowired(required = false)
    private BaseFinProductExercisePlanService baseFinProductExercisePlanService;

    /**
     * 开放计划组件
     */
    @Autowired(required = false)
    private BaseFinProductOpenPlanService baseFinProductOpenPlanService;

    /**
     * 开放规则组件
     */
    @Autowired(required = false)
    private BaseFinProductOpenRuleService baseFinProductOpenRuleService;


    public boolean insert(List<BaseFinProductBaseInfoDto> list) {
        if (list != null && list.size() > 0) {
            // 循环遍历list里的dto
            for (BaseFinProductBaseInfoDto dto : list) {
                // 插入标的类金融产品表
                if (dto.getFinProduct() != null) {
//                    // 一体化对应etliu start
//                    ValItemValueGenUtil.genFinprodTypeForFinProduct(dto.getFinProduct(), dto.getFinProductType());
                    // 一体化对应etliu end

                    finProductMapper.insert(dto.getFinProduct());
                }
                // 插入标的类金融产品附属表
                if (dto.getFinProductAdd() != null) {
                    finProductAddMapper.insert(dto.getFinProductAdd());
                }
                // 插入金融产品分类表
                if (dto.getFinProductType() != null) {
//                    // 一体化对应etliu start
//                    ValItemValueGenUtil.genTypeForFinProductType(dto.getFinProduct(), dto.getFinProductType());
                    // 一体化对应etliu end

                    finProductTypeMapper.insert(dto.getFinProductType());
                }
                // 增加推荐方与推荐费率 add by renping 20191014 start
                if (!StringUtil.isEmptyList(dto.getFinProductRecommentList())) {
                    for (FinProductRecomment finProductRecomment : dto.getFinProductRecommentList()) {
                        finProductRecomment.setRecfeeRate(ArithmeticUtil.divide(
                                finProductRecomment.getRecfeeRate(), new BigDecimal(100), 6));
                        finProductRecommentMapper.insert(finProductRecomment);
                    }
                }
                // 增加推荐方与推荐费率 add by renping 20191014 end
                // 调用接口插入金融产品现金流信息表
                if (null != baseFinProductCashInfoService && dto.getFinCashInfoList()!=null && !dto.getFinCashInfoList().isEmpty() ) {
                    baseFinProductCashInfoService.insertList(dto.getFinCashInfoList());
                }
                // 调用接口插入金融产品现金流支付规则表
                if (null != baseFinProductPayRuleService && dto.getFinCashPayRuleList()!=null
                        && !dto.getFinCashPayRuleList().isEmpty() ) {
                    baseFinProductPayRuleService.insert(dto.getFinCashPayRuleList());
                }
                // 调用接口插入金融产品现金流计提规则表
                if (null != baseFinProductCashCountingRuleService && dto.getFinCashCountingRuleList()!=null
                        && !dto.getFinCashCountingRuleList().isEmpty() ) {
                    baseFinProductCashCountingRuleService.insert(dto.getFinCashCountingRuleList());
                }
                // 调用接口插入金融产品现金流支付支付计划表
                if (null != baseFinProductCashPayPlanService && dto.getFinCashPlanList()!=null
                        && !dto.getFinCashPlanList().isEmpty() ) {
                    baseFinProductCashPayPlanService.insertList(dto.getFinCashPlanList());
                }
                // 调用接口插入金融产品现金流计算规则表
                if (null != baseFinProductCashCalcRuleService && dto.getFinCashCalcRuleList()!=null
                        && !dto.getFinCashCalcRuleList().isEmpty() ) {
                    baseFinProductCashCalcRuleService.insert(dto.getFinCashCalcRuleList());
                }
                // 调用接口插入金融产品现金流利率信息表
                if (null != baseFinProductCashRateRuleService && dto.getFinRateInfoList()!=null
                        && !dto.getFinRateInfoList().isEmpty() ) {
                    baseFinProductCashRateRuleService.insert(dto.getFinRateInfoList());
                }
                // 调用接口插入金融产品现金流行权规则表
                if (null != baseFinProductExerciseRuleService && dto.getFinExerciseRuleList()!=null
                        && !dto.getFinExerciseRuleList().isEmpty() ) {
                    baseFinProductExerciseRuleService.insert(dto.getFinExerciseRuleList());
                }
                // 调用接口插入金融产品现金流行权计划表
                if (null != baseFinProductExercisePlanService && dto.getFinExercisePlanList()!=null
                        && !dto.getFinExercisePlanList().isEmpty() ) {
                    baseFinProductExercisePlanService.insert(dto.getFinExercisePlanList());
                }
                // 调用接口插入开放计划表
                if (null != baseFinProductOpenPlanService && dto.getFinOpenPlanList()!=null && !dto.getFinOpenPlanList().isEmpty() ) {
                    baseFinProductOpenPlanService.insert(dto.getFinOpenPlanList());
                }
                // 调用接口插入开放规则表
                if (null != baseFinProductOpenRuleService && dto.getFinOpenRuleList()!=null && !dto.getFinOpenRuleList().isEmpty() ) {
                    baseFinProductOpenRuleService.insert(dto.getFinOpenRuleList());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 删除标的类金融产品基本信息
     */
    public boolean delete(List<String> finprodIdList) {
        // 判断接口是否为空
        if (finprodIdList != null && finprodIdList.size() > 0) {
            // 遍历金融代码集合
            if (finprodIdList != null && finprodIdList.size() > 0) {
                for (String finprodId : finprodIdList) {
                    // 通过金融代码删除金融产品现金流信息表
                    FinCashInfo finCashInfo = new FinCashInfo();
                    // 先获取现金流ID
                    List<String> cashIdList = new ArrayList<>();
                    if (null != baseFinProductCashInfoService) {
                        finCashInfo.setFinprodId(finprodId);
                        List<FinCashInfo> finCashInfoList = baseFinProductCashInfoService.select(
                                finCashInfo);
                        for (FinCashInfo entity : finCashInfoList) {
                            cashIdList.add(entity.getCashId());
                        }
                        for (String cashId : cashIdList) {
                            finCashInfo.setCashId(cashId);
                            baseFinProductCashInfoService.deleteByPrimaryKey(finCashInfo);
                        }
                    }
                    else {
                        continue;
                    }


//                    // 增加推荐方与推荐费率 add by renping 20191014 start
//                    baseFinRecommentMapper.deleteByProductId(finprodId);
//                    // 增加推荐方与推荐费率 add by renping 20191014 end

                    // 通过金融代码删除金融产品现金流支付规则表
                    if (null != baseFinProductPayRuleService) {
                        FinCashPayRule finCashPayRule = new FinCashPayRule();
                        finCashPayRule.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            finCashPayRule.setCashId(cashId);
                            baseFinProductPayRuleService.delete(finCashPayRule);
                        }
                    }
                    // 通过金融代码删除金融产品现金流支付计划表
                    if (null != baseFinProductCashPayPlanService) {
                        FinCashPlan finCashPlan = new FinCashPlan();
                        finCashPlan.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            finCashPlan.setCashId(cashId);
                            baseFinProductCashPayPlanService.deleteByPrimaryKey(finCashPlan);
                        }
                    }
                    // 通过金融代码删除金融产品现金流计算规则表
                    if (null != baseFinProductCashCalcRuleService) {
                        BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                        baseFinProductCashCalcRuleDto.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            baseFinProductCashCalcRuleDto.setCashId(cashId);
                            baseFinProductCashCalcRuleDto.setIsInitial(DictCodeList.是否_是.getValue());
                            baseFinProductCashCalcRuleDto.setFinprodId(finprodId);
                            //modify by yanya on 20210319 根据现金流id，产品代码去删除现金流计算规则表
                            //baseFinProductCashCalcRuleService.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
                            baseFinProductCashCalcRuleService.deleteBaseFinProductCashCalcRuleByCondition(baseFinProductCashCalcRuleDto);
                        }
                    }
                    // 通过金融代码删除金融产品现金流计提规则表
                    if (null != baseFinProductCashCountingRuleService) {
                        BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto = new BaseFinProductCashCountingRuleDto();
                        baseFinProductCashCountingRuleDto.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            baseFinProductCashCountingRuleDto.setCashId(cashId);
                            baseFinProductCashCountingRuleService.deleteByBaseFinProductCashCountingRuleDto(
                                    baseFinProductCashCountingRuleDto);
                        }
                    }
                    // 通过金融代码删除金融产品现金流利率规则信息表
                    if (null != baseFinProductCashRateRuleService) {
                        BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
                        baseFinProductCashRateRuleDto.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            baseFinProductCashRateRuleDto.setCashId(cashId);
                            baseFinProductCashRateRuleService.deleteByBaseFinProductCashRateRuleDto(
                                    baseFinProductCashRateRuleDto);
                        }
                    }
                    // 通过金融代码删除金融产品现金流行权规则表
                    if (null != baseFinProductExerciseRuleService) {
                        BaseFinProductExerciseRuleDto baseFinProductExerciseRuleDto = new BaseFinProductExerciseRuleDto();
                        baseFinProductExerciseRuleDto.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            baseFinProductExerciseRuleDto.setCashId(cashId);
                            baseFinProductExerciseRuleService.delete(
                                    baseFinProductExerciseRuleDto);
                        }
                    }
                    // 通过金融代码删除金融产品现金流行权计划表
                    if (null != baseFinProductExercisePlanService) {
                        FinExercisePlan finExercisePlan = new FinExercisePlan();
                        finExercisePlan.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            finExercisePlan.setCashId(cashId);
                            baseFinProductExercisePlanService.deleteByKey(finExercisePlan);
                        }
                    }
                    // 通过金融代码删除开放计划表
                    if (null != baseFinProductOpenPlanService) {
                        BaseFinProductOpenPlanDto baseFinProductOpenPlanDto = new BaseFinProductOpenPlanDto();
                        baseFinProductOpenPlanDto.setFinprodId(finprodId);
                        for (String cashId : cashIdList) {
                            baseFinProductOpenPlanDto.setCashId(cashId);
                            baseFinProductOpenPlanService.deleteByFinOpenPlan(
                                    baseFinProductOpenPlanDto);
                        }
                    }
                    // 通过现金流代码删除开放规则表
                    if (null != baseFinProductCashInfoService) {
                        BaseFinProductOpenRuleDto baseFinProductOpenRuleDto = new BaseFinProductOpenRuleDto();
                        for (String cashId : cashIdList) {
                            baseFinProductOpenRuleDto.setCashId(cashId);
                            baseFinProductOpenRuleService.delete(baseFinProductOpenRuleDto);
                        }
                    }
                    // 通过金融代码删除标的类金融产品表
                    finProductMapper.deleteByPrimaryKey(finprodId);
                    // 通过金融代码删除标的类金融产品附属表表
                    finProductAddMapper.deleteByPrimaryKey(finprodId);
                    // 通过金融代码删除金融产品分类表
                    FinProductTypeKey key = new FinProductTypeKey();
                    //Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
                    Long branch = Long.parseLong("0");
                    key.setFinprodId(finprodId);
                    key.setBranch(branch);
                    finProductTypeMapper.deleteByPrimaryKey(key);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 修改标的类金融产品基本信息
     */
    public boolean update(List<String> finprodIdList, List<BaseFinProductBaseInfoDto> list) {
        if (finprodIdList != null && finprodIdList.size() > 0 && list != null && list.size() > 0) {
            if (this.delete(finprodIdList)) {
                return this.insert(list);
            }
        }
        return false;
    }

    /**
     * 查询标的类金融产品基本信息
     */
    public List<BaseFinProductBaseInfoDto> select(List<String> finprodIdList) {
        // 判断集合是否为空
        if (finprodIdList != null && finprodIdList.size() > 0) {
            List<BaseFinProductBaseInfoDto> resultList = new ArrayList<BaseFinProductBaseInfoDto>();
            BaseFinProductBaseInfoDto dto = new BaseFinProductBaseInfoDto();
            for (String finprodId : finprodIdList) {
                // 通过金融产品代码查询标的类金融产品表
                dto.setFinProduct(finProductMapper.selectByPrimaryKey(finprodId));
                // 通过金融产品代码查询标的类金融产品附属表
                dto.setFinProductAdd(finProductAddMapper.selectByPrimaryKey(finprodId));
                // 通过金融产品代码查询金融产品分类表
                FinProductTypeKey key = new FinProductTypeKey();
                key.setFinprodId(finprodId);
                key.setBranch((long)0);
                dto.setFinProductType(finProductTypeMapper.selectByPrimaryKey(key));
                // 通过金融产品代码查询金融产品现金流信息表
                if (null != baseFinProductCashInfoService) {
                    FinCashInfo finCashInfo = new FinCashInfo();
                    finCashInfo.setFinprodId(finprodId);
                    dto.setFinCashInfoList(baseFinProductCashInfoService.select(finCashInfo));
                }
                // 通过金融产品代码查询金融产品现金流支付规则表
                if (null != baseFinProductPayRuleService) {
                    FinCashPayRule finCashPayRule = new FinCashPayRule();
                    finCashPayRule.setFinprodId(finprodId);
                    dto.setFinCashPayRuleList(baseFinProductPayRuleService.select(finCashPayRule));
                }
                // 通过金融产品代码查询金融产品现金流支付计划表
                if (null != baseFinProductCashPayPlanService) {
                    FinCashPlan finCashPlan = new FinCashPlan();
                    finCashPlan.setFinprodId(finprodId);
                    dto.setFinCashPlanList(baseFinProductCashPayPlanService.select(finCashPlan));
                }
                // 通过金融产品代码查询金融产品现金流计算规则表
                if (null != baseFinProductCashCalcRuleService) {
                    BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                    baseFinProductCashCalcRuleDto.setFinprodId(finprodId);
                    dto.setFinCashCalcRuleList(
                            baseFinProductCashCalcRuleService.selectByBaseFinProductCashCalcRuleDto(
                                    baseFinProductCashCalcRuleDto));
                }
                // 通过金融产品代码查询金融产品现金流计算规则表
                if (null != baseFinProductCashCountingRuleService) {
                    BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto = new BaseFinProductCashCountingRuleDto();
                    baseFinProductCashCountingRuleDto.setFinprodId(finprodId);
                    dto.setFinCashCountingRuleList(
                            baseFinProductCashCountingRuleService.selectByBaseFinProductCashCountingRuleDto(
                                    baseFinProductCashCountingRuleDto));
                }
                // 通过金融产品代码查询金融产品现金流利率规则信息表
                if (null != baseFinProductCashRateRuleService) {
                    BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
                    baseFinProductCashRateRuleDto.setFinprodId(finprodId);
                    dto.setFinRateInfoList(
                            baseFinProductCashRateRuleService.selectByBaseFinProductCashRateRuleDto(
                                    baseFinProductCashRateRuleDto));
                }
                // 通过金融产品代码查询金融产品现金流计提规则信息表
                if (null != baseFinProductCashRateRuleService) {
                    BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
                    baseFinProductCashRateRuleDto.setFinprodId(finprodId);
                    dto.setFinRateInfoList(
                            baseFinProductCashRateRuleService.selectByBaseFinProductCashRateRuleDto(
                                    baseFinProductCashRateRuleDto));
                }
                // 通过金融产品代码查询金融产品现金流行权规则表
                if (null != baseFinProductExerciseRuleService) {
                    BaseFinProductExerciseRuleDto baseFinProductExerciseRuleDto = new BaseFinProductExerciseRuleDto();
                    baseFinProductExerciseRuleDto.setFinprodId(finprodId);
                    dto.setFinExerciseRuleList(
                            baseFinProductExerciseRuleService.select(baseFinProductExerciseRuleDto));
                }
                // 通过金融产品代码查询金融产品现金流行权计划表
                if (null != baseFinProductExercisePlanService) {
                    FinExercisePlan finExercisePlan = new FinExercisePlan();
                    finExercisePlan.setFinprodId(finprodId);
                    dto.setFinExercisePlanList(
                            baseFinProductExercisePlanService.select(finExercisePlan));
                }
                // 通过金融产品代码查询开放计划表
                if (null != baseFinProductOpenPlanService) {
                    BaseFinProductOpenPlanDto baseFinProductOpenPlanDto = new BaseFinProductOpenPlanDto();
                    baseFinProductOpenPlanDto.setFinprodId(finprodId);
                    dto.setFinOpenPlanList(baseFinProductOpenPlanService.selectByFinOpenPlan(
                            baseFinProductOpenPlanDto));
                }
                // 通过现金流代码查询开发规则表
                if (null != baseFinProductOpenRuleService
                        && null != baseFinProductCashInfoService) {
                    List<FinCashInfo> finCashInfoList = dto.getFinCashInfoList();
                    List<FinOpenRule> finOpenRuleList = new ArrayList<>();
                    if (finCashInfoList != null && finCashInfoList.size() > 0) {
                        BaseFinProductOpenRuleDto baseFinProductOpenRuleDto = new BaseFinProductOpenRuleDto();
                        for (FinCashInfo entity : finCashInfoList) {
                            baseFinProductOpenRuleDto.setCashId(entity.getCashId());
                            List<FinOpenRule> onpenrulelist = baseFinProductOpenRuleService.search(
                                    baseFinProductOpenRuleDto);
                            finOpenRuleList.addAll(onpenrulelist);
                        }
                    }
                    dto.setFinOpenRuleList(finOpenRuleList);
                }
                resultList.add(dto);
            }
            return resultList;
        }
        return null;
    }


}

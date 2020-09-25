package com.joyintech.service.product.messageadjust.finproductquotation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.finproductquotation.dao.FinProductQuotation001Mapper;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.dao.FinFloatCountingRuleMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.common.base.dao.FinRateInfoMapper;
import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.dao.PrdLayerParamMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProductValuationKey;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashCalcRuleDto;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashRateRuleDto;
import com.joyintech.tams.component.base.core.impl.BaseFinProductCashCalcRuleServiceImpl;
import com.joyintech.tams.component.base.core.impl.BaseFinProductCashRateRuleServiceImpl;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;

/**
 * @author : wangkang
 * @version V1.0
 * @Project: microservice
 * @date Date : 2018年10月13日
 */
@Service("backFinProductQuotation001Service")
@Transactional(rollbackFor = Exception.class)
public class FinProductQuotation001Service extends BaseService<FinProductQuotation001Dto> {

    /**
     * 微服务产品报价管理mapper
     */
    @Autowired
    private FinProductQuotation001Mapper finProductQuotation001Mapper;

    /**
     * 场外金融产品估值mapper
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;

    /**
     * 标的类金融产品mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 现金流计算规则mapper
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    /**
     * 理财产品分层参数mapper
     */
    @Autowired
    private PrdLayerParamMapper prdLayerParamMapper;

    /**
     * 现金流信息mapper
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 现金流支付规则表mapper
     */
    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    /**
     * 现金流计提规则表mapper
     */
    @Autowired
    private FinFloatCountingRuleMapper finCashCountingRuleMapper;

    /**
     * 现金流利率信息表mapper
     */
    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;

    // 一体化对应etliu start
    /**
     * ValuationInterfService
     */
    @Autowired
    private ValuationInterfService valuationInterfService;

    /**
     * {@linkplain}
     */
    @Autowired
    private PaFinProductMapper paFinProductMapper;

    /**
     * 产品代码联想框
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getFinprodId(Map<String, Object> param) {
        return finProductQuotation001Mapper.getFinprodId(param);
    }
    
    
    /**
     * 产品款型联想框
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getProdType(Map<String, Object> param) {
        String finprodId = (String)param.get("finprodId");
        if(finprodId != null&&finprodId != ""){
            
        }
        List<SelectOptionDto> SelectOptionDto = finProductQuotation001Mapper.getProdType(param);
        return SelectOptionDto;
    }

    /**
     * 产品代码联想下拉框
     *
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> param) {
        param.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("profitType", DictCodeList.收益类型_收益型.getValue());
        return finProductQuotation001Mapper.queryProdIdInfoIssueSuc(param);
    }

    /**
     * 产品报价管理-查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Page<FinProductQuotation001Dto> selectForPage(int page, int length,
                                                         Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("YESORNOY", DictCodeList.是否_是.getValue());
        pageParam.put("YESORNON", DictCodeList.是否_否.getValue());
        return super.queryForPage(page, length,
            () -> finProductQuotation001Mapper.selectForPage(pageParam));
    }

    /**
     * 删除产品报价相关业务表
     *
     * @param dto
     * @return
     */
    public void delete(FinProductQuotation001Dto dto) {
        List<FinProductQuotation001Dto> list = dto.getSelectedItem();
        // 判断是否是批量删除
        if (list != null && list.size() > 0) {
            for (FinProductQuotation001Dto selet : list) {
                String profitType = selet.getProfitType();
                if (profitType.equals(DictCodeList.收益类型_收益型.getValue())) {
                    // 删除金融产品现金流计算规则
                    BaseFinProductCashCalcRuleServiceImpl cashCaleRule = new BaseFinProductCashCalcRuleServiceImpl();
                    BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                    baseFinProductCashCalcRuleDto.setCashId(selet.getCashId());
                    baseFinProductCashCalcRuleDto.setEffDate(selet.getEffDate());
                    cashCaleRule.deleteByBaseFinProductCashCalcRuleDto(
                        baseFinProductCashCalcRuleDto);
                    // 删除金融产品现金流利率规则
                    BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
                    baseFinProductCashRateRuleDto.setCashId(selet.getCashId());
                    baseFinProductCashRateRuleDto.setEffDate(selet.getEffDate());
                    BaseFinProductCashRateRuleServiceImpl cashRateRule = new BaseFinProductCashRateRuleServiceImpl();
                    cashRateRule.deleteByBaseFinProductCashRateRuleDto(
                        baseFinProductCashRateRuleDto);
                }
                else if (profitType.equals(DictCodeList.收益类型_净值型.getValue())
                         || profitType.equals(DictCodeList.收益类型_货币型.getValue())) {
                    // 删除表场外金融产品估值
                    FinProductValuationKey key = new FinProductValuationKey();
                    key.setFinprodId(selet.getFinprodId());
                    key.setValDate(selet.getEffDate());
                    finProductValuationMapper.deleteByPrimaryKey(key);
                }
            }
        }
        else {
            String profitType = dto.getProfitType();
            if (profitType.equals(DictCodeList.收益类型_收益型.getValue())) {
                // 删除金融产品现金流计算规则
                BaseFinProductCashCalcRuleServiceImpl cashCaleRule = new BaseFinProductCashCalcRuleServiceImpl();
                BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
                baseFinProductCashCalcRuleDto.setCashId(dto.getCashId());
                baseFinProductCashCalcRuleDto.setEffDate(dto.getEffDate());
                cashCaleRule.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
                // 删除金融产品现金流利率规则
                BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
                baseFinProductCashRateRuleDto.setCashId(dto.getCashId());
                baseFinProductCashRateRuleDto.setEffDate(dto.getEffDate());
                BaseFinProductCashRateRuleServiceImpl cashRateRule = new BaseFinProductCashRateRuleServiceImpl();
                cashRateRule.deleteByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);
            }
            else if (profitType.equals(DictCodeList.收益类型_净值型.getValue())
                     || profitType.equals(DictCodeList.收益类型_货币型.getValue())) {
                // 删除表场外金融产品估值
                FinProductValuationKey key = new FinProductValuationKey();
                key.setFinprodId(dto.getFinprodId());
                key.setValDate(dto.getEffDate());
                finProductValuationMapper.deleteByPrimaryKey(key);
            }
        }

    }

    /**
     * 得到金融产品类型
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getFinprodId2(Map<String, Object> param) {
        return finProductQuotation001Mapper.getFinprodId(param);
    }

    /**
     * 根据产品代码查询产品基本信息
     *
     * @param param 参数
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    public FinProductQuotation001Dto queryProdInfoByFinprodId(Map<String, Object> param) {
        return finProductQuotation001Mapper.queryProdInfoByFinprodId(param);
    }

    /**
     * 净值、货币产品生效日判断 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public int getValueDate(Map<String, Object> param) {
        return finProductQuotation001Mapper.getValueDate(param);
    }

    /**
     * 检查是否存在生效日的报价
     *
     * @param param 入参
     * @return 报价存在个数
     * @see
     * @since 1.0
     */
    public int checkEffDateQuoteExists(Map<String, Object> param) {
        param.put("YESORNON", DictCodeList.是否_否.getValue());
        param.put("YESORNOY", DictCodeList.是否_是.getValue());
        return finProductQuotation001Mapper.checkEffDateQuoteExists(param);
    }

    /**
     * 市场代码得到起息日和到期日 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinProductQuotation001Dto getVdateAndMdate(String param) {
        return finProductQuotation001Mapper.getVdateAndMdate(param);
    }

    /**
     * 根据金融产品代码和报价日查询报价信息
     *
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    public FinProductQuotation002Dto queryQuoteInfoByFinprodIdEffDate(Map<String, Object> param) {
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("YESORNON", DictCodeList.是否_否.getValue());
        param.put("YESORNOY", DictCodeList.是否_是.getValue());
        return finProductQuotation001Mapper.queryQuoteInfoByFinprodIdEffDate(param);
    }

    /**
     * 流程后的db操作
     * 插入
     *
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doInsert(FinProductQuotation002Dto entity)
        throws Exception {
        if (StringUtil.equals(entity.getIsLay(), DictCodeList.是否_是.getValue())) {
        	for (PrdLayerParam prdLayerParam : entity.getLayTypeInfoList()) {
        		/*
                // 理财产品分层参数表
                prdLayerParam.setVdate(entity.getEffDate());
                prdLayerParam.setRemark(entity.getRemark());
                prdLayerParam.setYield(ArithmeticUtil.divide(prdLayerParam.getYield(), 100, 14));
                prdLayerParamMapper.updateByPrimaryKeySelective(prdLayerParam);
        		 */
                // 登录母产品的现金流计算规则表
                FinCashCalcRuleKey key = new FinCashCalcRuleKey();
                key.setCashId(prdLayerParam.getCashId());
                key.setEffDate(entity.getVdate());
                FinCashCalcRule parentFinCashCalcRule = finCashCalcRuleMapper.selectByPrimaryKey(key);

                FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                BeanUtil.copyProperties(finCashCalcRule, parentFinCashCalcRule);
                finCashCalcRule.setEffDate(entity.getEffDate());
    			// 一体化对应etliu
                // finCashCalcRule.setRelaeseDate(entity.getReleaseDate());
                finCashCalcRule.setYield(prdLayerParam.getYield() != null ? ArithmeticUtil.divide(
                		prdLayerParam.getYield(), 100, 14) : prdLayerParam.getYield());
                finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
                finCashCalcRule.setRemark(entity.getRemark());
                finCashCalcRuleMapper.insert(finCashCalcRule);
        	}

            /*// 查询标的类金融产品表
            FinProduct parentFinProduct = finProductMapper.selectByPrimaryKey(
                entity.getFinprodId());
            // 平安标的扩展
            PaFinProduct paFinProduct = paFinProductMapper.selectByPrimaryKey(entity.getFinprodId());
            // 查询现金流信息
            FinCashInfo parentFinCashInfo = finCashInfoMapper.selectByPrimaryKey(
                entity.getCashId());
            // 查询现金流支付规则表
            FinCashPayRuleKey finCashPayRuleKey = new FinCashPayRuleKey();
            finCashPayRuleKey.setCashId(entity.getCashId());
            finCashPayRuleKey.setEffDate(entity.getVdate());
            FinCashPayRule parentFinCashPay = finCashPayRuleMapper.selectByPrimaryKey(
                finCashPayRuleKey);
            // 现金流利率信息表
            FinRateInfoKey finRateInfoKey = new FinRateInfoKey();
            finRateInfoKey.setCashId(entity.getCashId());
            finRateInfoKey.setEffDate(entity.getVdate());
            FinRateInfo parentFinRateInfo = finRateInfoMapper.selectByPrimaryKey(finRateInfoKey);

            FinCashCountingRuleKey finCashCountingRuleKey = new FinCashCountingRuleKey();
            finCashCountingRuleKey.setCashId(entity.getCashId());
            finCashCountingRuleKey.setEffDate(entity.getVdate());
            FinCashCountingRule parentFinCashCountingRule = finCashCountingRuleMapper.selectByPrimaryKey(finCashCountingRuleKey);

            for (PrdLayerParam prdLayerParam : entity.getLayTypeInfoList()) {
                // 理财产品分层参数表
                prdLayerParam.setpFinprodId(entity.getFinprodId());
                prdLayerParam.setVdate(entity.getEffDate());
                prdLayerParam.setRemark(entity.getRemark());
                prdLayerParam.setYield(ArithmeticUtil.divide(prdLayerParam.getYield(), 100, 14));
                prdLayerParamMapper.insert(prdLayerParam);

                List<BaseFinProductBaseInfoDto> baseInfoDtoList = new ArrayList<BaseFinProductBaseInfoDto>();
                // 标的类金融产品基本信息基础组件
                BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
                // 现金流信息dtolist
                List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
                // 现金流计算规则dtolist
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                // 现金流支付规则dtolist
                List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
                // 现金流利率信息表dtolist
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                // 现金流计划表dtolist
                List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
                // 现金流计提规则表dtolist
                List<FinCashCountingRule> finCashCountingRuleList = new ArrayList<FinCashCountingRule>();

                // 标的类金融产品基本信息
                FinProduct finProduct = new FinProduct();
                BeanUtil.copyProperties(finProduct, parentFinProduct);
                finProduct.setFinprodId(prdLayerParam.getFinprodId());
                finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                // 画面.产品简称+"-"+画面.生效日+"-"+分层设置.客户等级名称+"-"+分层设置.金额下限+"-"+分层设置.金额上限+"-"+分层设置.期限下限+"-"+分层设置.期限上限
                StringBuffer sb = new StringBuffer();
                if (StringUtil.isNotEmpty(prdLayerParam.getCustomLevel())) {
                    sb.append(prdLayerParam.getCustomLevel());
                    sb.append("-");
                }
                if (StringUtil.isNotEmpty(prdLayerParam.getAmtMin())) {
                    sb.append(prdLayerParam.getAmtMin());
                    sb.append("-");
                }
                if (StringUtil.isNotEmpty(prdLayerParam.getAmtMax())) {
                    sb.append(prdLayerParam.getAmtMax());
                    sb.append("-");
                }
                if (StringUtil.isNotEmpty(prdLayerParam.getTermMin())) {
                    sb.append(prdLayerParam.getTermMin());
                    sb.append("-");
                }
                if (StringUtil.isNotEmpty(prdLayerParam.getTermMax())) {
                    sb.append(prdLayerParam.getTermMax());
                    sb.append("-");
                }
                finProduct.setFinprodAbbr(
                    parentFinProduct.getFinprodAbbr() + "-" + DateUtil.format(entity.getEffDate())
                                          + "-" + sb.substring(0, sb.length() - 1));
                finProduct.setFinprodName(
                    parentFinProduct.getFinprodName() + "-" + DateUtil.format(entity.getEffDate())
                                          + "-" + sb.substring(0, sb.length() - 1));
                finProduct.setVdate(entity.getEffDate());
                finProduct.setTermDays(DateUtil.getBetweenDayNumber(entity.getEffDate(),
                    parentFinProduct.getMdate()));
                finProduct.setpFinprodId(entity.getFinprodId());
                finProduct.setRemark(entity.getRemark());
                baseFinProductBaseInfoDto.setFinProduct(finProduct);
                // 一体化对应etliu start
                // 产品报价，调用估值接口
                ProdOtherInfoInitInputDto inputDto = new ProdOtherInfoInitInputDto();
                inputDto.setPageId("FinProductQuotation002");
                inputDto.setFinProduct(finProduct);

                valuationInterfService.prodOtherInfoInit(inputDto);
                // 一体化对应etliu end
                // 现金流信息表
                FinCashInfo finCashInfo = new FinCashInfo();
                BeanUtil.copyProperties(finCashInfo, parentFinCashInfo);
                // 根据规则生成现金流代码
                String cashId = CommonUtil.generateCashFlowCode(prdLayerParam.getFinprodId(),
                    DictCodeListExt1.现金流业务类型_金融产品.getValue(), parentFinCashInfo.getBranch(),
                    DictCodeListExt1.现金流二级类型_利息.getValue());
                finCashInfo.setCashId(cashId);
                finCashInfo.setFinprodId(prdLayerParam.getFinprodId());
                this.checkDealModeIsCalc(finCashInfo, paFinProduct.getDealMode());
                finCashInfoList.add(finCashInfo);

                // 现金流计划规则表
                FinCashCalcRule sonFinCashCalcRule = new FinCashCalcRule();
                sonFinCashCalcRule.setCashId(cashId);
                sonFinCashCalcRule.setEffDate(entity.getEffDate());
                sonFinCashCalcRule.setRelaeseDate(entity.getReleaseDate());
                sonFinCashCalcRule.setCalcType(parentFinRateInfo.getRateType());
                sonFinCashCalcRule.setBasis(parentFinRateInfo.getBasis());
                sonFinCashCalcRule.setYield(prdLayerParam.getYield());
                sonFinCashCalcRule.setIsInitial(DictCodeList.是否_是.getValue());
                sonFinCashCalcRule.setFinprodId(prdLayerParam.getFinprodId());
                sonFinCashCalcRule.setBranch(parentFinCashInfo.getBranch());
                sonFinCashCalcRule.setFinprodType2(
                    DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                sonFinCashCalcRule.setRemark(entity.getRemark());
                finCashCalcRuleList.add(sonFinCashCalcRule);

                // 现金流支付规则表
                FinCashPayRule finCashPayRule = new FinCashPayRule();
                BeanUtil.copyProperties(finCashPayRule, parentFinCashPay);
                finCashPayRule.setCashId(cashId);
                finCashPayRule.setEffDate(entity.getEffDate());
                finCashPayRule.setFinprodId(prdLayerParam.getFinprodId());
                finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                finCashPayRule.setRemark(entity.getRemark());
                finCashPayRuleList.add(finCashPayRule);

                FinCashCountingRule finCashCountingRule = new FinCashCountingRule();
                BeanUtil.copyProperties(finCashCountingRule, parentFinCashCountingRule);
                finCashCountingRule.setCashId(cashId);
                finCashCountingRule.setEffDate(entity.getEffDate());
                finCashCountingRuleList.add(finCashCountingRule);

                // 现金流利率信息表
                FinRateInfo finRateInfo = new FinRateInfo();
                BeanUtil.copyProperties(finRateInfo, parentFinRateInfo);
                finRateInfo.setCashId(cashId);
                finRateInfo.setEffDate(entity.getEffDate());
                finRateInfo.setRate(prdLayerParam.getYield());
                finRateInfo.setFinprodId(prdLayerParam.getFinprodId());
                finRateInfo.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                finRateInfo.setRemark(entity.getRemark());
                finRateInfoList.add(finRateInfo);

                // 现金流计划表
                FinCashPlan finCashPlan = new FinCashPlan();
                finCashPlan.setCashId(cashId);
                finCashPlan.setCashNum(new Long(1));
                finCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                finCashPlan.setVdateUnadjust(entity.getEffDate());
                finCashPlan.setVdate(entity.getEffDate());
                finCashPlan.setMdateUnadjust(parentFinProduct.getMdate());
                finCashPlan.setMdate(parentFinProduct.getMdate());
                finCashPlan.setPayDate(parentFinProduct.getMdate());
                finCashPlan.setPayDateUnadjust(parentFinProduct.getMdate());
                finCashPlan.setFinprodId(prdLayerParam.getFinprodId());
                finCashPlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                finCashPlan.setBranch(parentFinCashInfo.getBranch());
                finCashPlan.setRemark(entity.getRemark());
                finCashPlanList.add(finCashPlan);

                baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
                baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
                baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRuleList);
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);
                baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
                baseFinProductBaseInfoDto.setFinCashCountingRuleList(finCashCountingRuleList);
                baseInfoDtoList.add(baseFinProductBaseInfoDto);

                baseFinProductBaseInfoService.insert(baseInfoDtoList);
            }*/
        }
        else{
            // 登录母产品的现金流计算规则表
            FinCashCalcRuleKey key = new FinCashCalcRuleKey();
            key.setCashId(entity.getCashId());
            key.setEffDate(entity.getVdate());
            FinCashCalcRule parentFinCashCalcRule = finCashCalcRuleMapper.selectByPrimaryKey(key);

            FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
            BeanUtil.copyProperties(finCashCalcRule, parentFinCashCalcRule);
            finCashCalcRule.setEffDate(entity.getEffDate());
			// 一体化对应etliu
            // finCashCalcRule.setRelaeseDate(entity.getReleaseDate());
            finCashCalcRule.setYield(entity.getYield() != null ? ArithmeticUtil.divide(
                entity.getYield(), 100, 14) : entity.getYield());
            finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
            finCashCalcRule.setRemark(entity.getRemark());
            finCashCalcRuleMapper.insert(finCashCalcRule);
        }
    }

    /**
     * 流程后的db操作
     * 删除
     *
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doDelete(FinProductQuotation002Dto entity) {
		if (StringUtil.equals(entity.getIsLay(), DictCodeList.是否_是.getValue())) {
			for (PrdLayerParam prdLayerParam : entity.getLayTypeInfoList()) {
				FinCashCalcRuleKey finCashCalcRuleKey2 = new FinCashCalcRuleKey();
				finCashCalcRuleKey2.setCashId(prdLayerParam.getCashId());
				finCashCalcRuleKey2.setEffDate(entity.getEffDate());
				finCashCalcRuleMapper.deleteByPrimaryKey(finCashCalcRuleKey2);
			}
		} else {
			// 登录母产品的现金流计算规则表
			FinCashCalcRuleKey finCashCalcRuleKey = new FinCashCalcRuleKey();
			finCashCalcRuleKey.setCashId(entity.getCashId());
			finCashCalcRuleKey.setEffDate(entity.getEffDate());
			finCashCalcRuleMapper.deleteByPrimaryKey(finCashCalcRuleKey);

		}
        /*
        List<PrdLayerParam> layerDetailList =  this.queryLayerDetailByFinprodId(entity.getFinprodId(), entity.getEffDateStr());
        if (!StringUtil.isEmptyList(layerDetailList)){
            List<String> finprodIdList = new ArrayList<String>();
            for (PrdLayerParam prdLayerParam : layerDetailList) {
                // 理财产品分层参数表
                prdLayerParamMapper.deleteByPrimaryKey(prdLayerParam.getFinprodId());

                finprodIdList.add(prdLayerParam.getFinprodId());
            }
            baseFinProductBaseInfoService.delete(finprodIdList);
        }
        */
    	
    }

    /**
     * 流程后的db操作
     * 修改
     *
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doUpdate(FinProductQuotation002Dto entity) throws Exception{
        Date date = new Date();
        date = entity.getEffDate();
        //删除旧的生效日
        entity.setEffDate(entity.getEffDateOld());
        this.doDelete(entity);
        //插入新的生效日
        entity.setEffDate(date);
        this.doInsert(entity);

    }

    /**
     * 根据金融产品代码查询分层信息
     *
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分层信息
     */
    public List<PrdLayerParam> queryLayerDetailByFinprodId(String finprodId, String effDate) {
        List<PrdLayerParam> layDetailList = finProductQuotation001Mapper.queryLayerDetailByFinprodId(
            finprodId, effDate);
        if (!StringUtil.isEmptyList(layDetailList)) {
            for (PrdLayerParam prdLayerParam : layDetailList) {
                prdLayerParam.setYield(
                    ArithmeticUtil.multiply(prdLayerParam.getYield(), new BigDecimal(100)));
            }
        }

        return layDetailList;
    }

    /** 根据处理模式设置现金流信息是否参与计算属性
     * @param finCashInfo 现金流信息
     * @param dealMode 处理模式
     */
    private void checkDealModeIsCalc(FinCashInfo finCashInfo, String dealMode) {
        if (StringUtil.equals(dealMode, BussinessDictCodeList.处理模式_日添利.getValue())
            || StringUtil.equals(dealMode, BussinessDictCodeList.处理模式_智能日添利.getValue())
            || StringUtil.equals(dealMode, BussinessDictCodeList.处理模式_货币型.getValue())
            || StringUtil.equals(dealMode, BussinessDictCodeList.处理模式_净值型.getValue())) {
            finCashInfo.setIsCalc(DictCodeList.是否_是.getValue());
        }
        else {
            finCashInfo.setIsCalc(DictCodeList.是否_否.getValue());
        }
    }
    /**
     * 根据金融产品代码和报价日查询分层报价信息
     *
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    public List<FinProductQuotation003Dto> queryQuoteLayDetailInfoList(Map<String, Object> param) {
        return finProductQuotation001Mapper.queryQuoteLayDetailInfoList(param);
    }
}

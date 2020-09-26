/*
 * 文件名：ValAssetsFundQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/***
 * 基金类资产查询
 * 
 * @author zhangwei
 * @version 1.0 2018年7月25日
 * @see ValAssetsFundQuery002Service
 * @since 1.0
 */
@Service
public class ValAssetsFundQuery002Service extends CommonExportService<ValAssetsFundQuery002Dto> implements BaseProcessService<ValAssetsFundQuery002Dto> {

//    @Autowired
//    private ValAssetsFundQuery002Mapper valAssetsFundQuery002Mapper;
//
//    @Autowired
//    private ValAssetsFundQuery001Mapper valAssetsFundQuery001Mapper;
//
//    /**
//     * 标的类金融产品表
//     */
//    @Autowired
//    private FinProductMapper finProductMapper;
//
//    /**
//     * 标的类金融产品扩展表
//     */
//    @Autowired
//    private FinProductAddMapper finProductAddMapper;
//
//    /**
//     * 金融产品分类表
//     */
//    @Autowired
//    private FinProductTypeMapper finProductTypeMapper;
//
//    /**
//     * 现金流信息表
//     */
//    @Autowired
//    private FinCashInfoMapper finCashInfoMapper;
//
//    /**
//     * 现金流支付规则表
//     */
//    @Autowired
//    private FinCashPayRuleMapper finCashPayRuleMapper;

    // /**
    // * 现金流计划表
    // */
    // @Autowired
    // private FinCashPlanMapper finCashPlanMapper;

    /**
     * 根据查询finprodId单个详细信息
     * 
     * @param finprodId
     * @return 单个详细信息
     * @see
     * @since 1.0
     */
    public ValAssetsFundQuery002Dto findByfinprodId(String finprodId) {
    	
    	ValAssetsFundQuery002Dto valAssetsFundQuery002Dto =restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery002/findByfinprodId?finprodId="+finprodId,
                ValAssetsFundQuery002Dto.class);
//        ValAssetsFundQuery002Dto valAssetsFundQuery002Dto = valAssetsFundQuery002Mapper.selectByfinprodId(
//            finprodId);
//        List<ValCashPlanDto> planList = new ArrayList<>();
//        if ("01".equals(valAssetsFundQuery002Dto.getFundType())) {// 01货币基金才显示分红计划
//            planList = valAssetsFundQuery002Mapper.selectCashPlanByFinprodId(finprodId);
//        }
//        valAssetsFundQuery002Dto.setPlanList(planList);

        return valAssetsFundQuery002Dto;
    }

    /**
     * 新建、修改
     * 
     * @param dto 账套dto
     * @return int
     * @see
     * @since 1.0
     */
//    public int insert(ValAssetsFundQuery002Dto dto) {
//        // 分支序号
//        Long branch = new Long(0);
//        // 金融产品类型_基金
//        String finprodTypeFund = DictCodeListExt1.金融产品类型_基金.getValue();
//        // 现金流代码=InterfHelper.getMd5CashId_(资产代码,数据字典：金融产品类型.基金,数据字典：现金流二级类型.利息）
//        String cashId = InterfHelper.getMd5CashId_(dto.getFinprodId(), finprodTypeFund,
//            DictCodeListExt1.现金流二级类型_利息.getValue());
//
//        // 判断是新建操作还是修改操作
//        if (dto.getOperateType().equals("edit")) {
//            // 修改操作
//            Map<String, Object> params = new HashMap<>();
//            params.put("finprodId", dto.getFinprodId());
//            boolean flag = valAssetsFundQuery001Mapper.checkFindprodId(params);
//            // 如果该资产存在交易，资产代码、基金类型和货币均不可修改
//            if (flag) {
//                // 更新标的类金融产品表信息
//                FinProduct finProduct = newFinProduct(dto, finprodTypeFund);
//                valAssetsFundQuery002Mapper.updateFinProduct2(finProduct);
//            }
//            else {
//                // 更新标的类金融产品表信息
//                FinProduct finProduct = newFinProduct(dto, finprodTypeFund);
//                valAssetsFundQuery002Mapper.updateFinProduct(finProduct);
//            }
//
//            return this.update(dto, finprodTypeFund, cashId, branch);
//
//        }
//        else {
//            // 新建操作
//            /**
//             * 标的类金融产品表入库
//             */
//            FinProduct finProduct = newFinProduct(dto, finprodTypeFund);
//            finProduct.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//            finProduct.setCreateTime(new Date());
//            finProduct.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//            finProduct.setUpdateTime(new Date());
//            finProductMapper.insert(finProduct);
//
//            /**
//             * 标的类金融产品扩展表入库
//             */
//            FinProductAdd finProductAdd = newFinProductAdd(dto, finprodTypeFund);
//            finProductAdd.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//            finProductAdd.setCreateTime(new Date());
//            finProductAdd.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//            finProductAdd.setUpdateTime(new Date());
//            if (dto.getFundType().equals(DictCodeList.基金类型_货币基金.getValue())) {
//                /**
//                 * 标的类金融产品扩展表入库
//                 */
//                // 是否按日复利-是否按日复利（货币基金）
//            	finProductAdd.setIsCompoundInt(dto.getIsCompoundInt());
//
//                /**
//                 * 现金流信息表入库（基金类型为“货币基金”的分红规则信息入库）
//                 */
//                FinCashInfo finCashInfo = newFinCashInfo(dto, finprodTypeFund, cashId, branch);
//                finCashInfo.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashInfo.setCreateTime(new Date());
//                finCashInfo.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashInfo.setUpdateTime(new Date());
//                finCashInfoMapper.insert(finCashInfo);
//
//                /**
//                 * 现金流支付规则表入库（基金类型为“货币基金”的分红规则信息入库）
//                 */
//                FinCashPayRule finCashPayRule = newFinCashPayRule(dto, finprodTypeFund, cashId,
//                    branch);
//                finCashPayRule.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashPayRule.setCreateTime(new Date());
//                finCashPayRule.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashPayRule.setUpdateTime(new Date());
//                finCashPayRuleMapper.insert(finCashPayRule);
//
//                // /**
//                // * 现金流计划表入库（基金类型为“货币基金”的分红计划信息入库）
//                // */
//                // FinCashPlan finCashPlan = newFinCashPlan(dto, finprodTypeFund, cashId, branch);
//                // finCashPlan.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                // finCashPlan.setCreateTime(new Date());
//                // finCashPlan.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                // finCashPlan.setUpdateTime(new Date());
//                // finCashPlanMapper.insert(finCashPlan);
//
//            }
//
//            /**
//             * 标的类金融产品扩展表入库
//             */
//            finProductAddMapper.insert(finProductAdd);
//
//            /**
//             * 金融产品分类表入库
//             */
//            FinProductType finProductType = newFinProductType(dto, finprodTypeFund);
//            return finProductTypeMapper.insert(finProductType);
//
//        }
//
//    }

    /**
     * 创建标的类金融产品表对象
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public FinProduct newFinProduct(ValAssetsFundQuery002Dto dto, String finprodTypeFund) {
//
//        FinProduct finProduct = new FinProduct();
//        // 金融产品代码-资产代码
//        finProduct.setFinprodId(dto.getFinprodId());
//        // 金融产品类型-F06基金
//        finProduct.setFinprodType(finprodTypeFund);
//        // 金融产品简称-资产名称
//        finProduct.setFinprodAbbr(dto.getFinprodAbbr());
//        // 金融产品全称-资产名称
//        finProduct.setFinprodName(dto.getFinprodAbbr());
//        // 市场代码-市场代码
//        finProduct.setFinprodMarketId(dto.getFinprodMarketId());
//        // 收益类型-数据字典：收益类型
//        finProduct.setProfitType(dto.getProfitType());
//        // 币种-币种
//        finProduct.setCcy(dto.getCcy());
//        // 发行日-发行日
//        finProduct.setIdate(dto.getIdate());
//        // 交易日历-交易日历
//        finProduct.setCalendarId(dto.getCalendarId());
//        // 境内外-境内外
//        finProduct.setBlnArea(dto.getBlnArea());
//        // 原金融产品代码-资产代码
//        finProduct.setoFinprodId(dto.getFinprodId());
//        
//      //【一体化修改】：begin========================================================================= yangjian 2018/1/4
//      //【一体化修改】：针对估值一体化变更【59：标的类金融产品表原字段【原金融产品类型】删除】
//        
//        // 原金融产品类型-F06基金
//        // finProduct.setoFinprodType(finprodTypeFund);
//
//      //【一体化修改】：end========================================================================= yangjian 2018/1/4
//
//        
//        // 交易场所-交易场所
//        finProduct.setTradeMarket(dto.getTradeMarket());
//        // 发行人-管理人
//        finProduct.setIssuer(dto.getIssuer());
//        // 本金收益特征-本金收益特征
//        finProduct.setCapiIncomeFeature(dto.getCapiIncomeFeature());
//
//        return finProduct;
//    }

    /**
     * 创建标的类金融产品扩展表对象
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public FinProductAdd newFinProductAdd(ValAssetsFundQuery002Dto dto, String finprodTypeFund) {
//    	FinProductAdd finProductAdd = new FinProductAdd();
//        // 金融产品代码-资产代码
//    	finProductAdd.setFinprodId(dto.getFinprodId());
//        // 申购确认速度-申购确认速度，
//    	finProductAdd.setPurSpeed(dto.getPurSpeed());
//        // 赎回确认速度-赎回确认速度
//    	finProductAdd.setRedSpeed(dto.getRedSpeed());
//        return finProductAdd;
//
//    }

//    /**
//     * 创建金融产品分类表对象
//     * 
//     * @param dto
//     * @param finprodTypeFund
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public FinProductType newFinProductType(ValAssetsFundQuery002Dto dto, String finprodTypeFund) {
//        FinProductType finProductType = new FinProductType();
//        // 金融产品代码-资产代码
//        finProductType.setFinprodId(dto.getFinprodId());
//
//        
//      //【一体化修改】：begin========================================================================= yangjian 2018/1/4
//      //【一体化修改】：针对估值一体化变更【58：金融产品分类表原金融产品类型删除】
//        // 金融产品类型-F06：基金
//        // finProductType.setFinprodType(finprodTypeFund);
//
//      //【一体化修改】：end========================================================================= yangjian 2018/1/4
//        
//        // 分类1-基金类型
//        finProductType.setType1(dto.getFundType());
//        return finProductType;
//    }

    /**
     * 创建现金流信息表对象
     * 
     * @param dto
     * @param finprodTypeFund
     * @return
     * @see
     * @since 1.0
     */
//    public FinCashInfo newFinCashInfo(ValAssetsFundQuery002Dto dto, String finprodTypeFund,
//                                      String cashId, Long branch) {
//
//        FinCashInfo finCashInfo = new FinCashInfo();
//        // 现金流代码-现金流代码
//        finCashInfo.setCashId(cashId);
//        // 现金流一级类型-现金流一级类型.利息（数据字典）
//        finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
//        // 现金流二级类型-现金流二级类型.利息（数据字典）
//        finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
//        // 金融产品代码-资产代码
//        finCashInfo.setFinprodId(dto.getFinprodId());
//        // 金融产品类型-金融产品类型.基金（数据字典）
//        finCashInfo.setFinprodType(finprodTypeFund);
//        // 分支序号-设置全局变量(默认为0）
//        finCashInfo.setBranch(branch);
//        return finCashInfo;
//    }

    /**
     * 创建现金流支付规则表对象
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public FinCashPayRule newFinCashPayRule(ValAssetsFundQuery002Dto dto, String finprodTypeFund,
//                                            String cashId, Long branch) {
//
//        FinCashPayRule finCashPayRule = new FinCashPayRule();
//        // 现金流代码-现金流代码
//        finCashPayRule.setCashId(cashId);
//        // 生效日期-分红日
//        finCashPayRule.setEffDate(dto.getSdate());
//        // 支付频率-分红频率
//        finCashPayRule.setPayCycle(dto.getPayCycle());
//        // 营业日准则-营业日准则
//        // finCashPayRule.setTdayRule(dto.getTdayRule());
//        finCashPayRule.setEndTdayRule(dto.getEndTdayRule());
//        finCashPayRule.setPayTdayRule(dto.getPayTdayRule());
//        // 现金流单位-02：总额
//        finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
//        // 金融产品代码-资产代码
//        finCashPayRule.setFinprodId(dto.getFinprodId());
//        // 金融产品类型-金融产品类型.基金（数据字典）
//        finCashPayRule.setFinprodType(finprodTypeFund);
//        // 推算方式 - 推算方式
//        finCashPayRule.setCalcRule(dto.getCalcRule());
//        // 分支序号-设置全局变量(默认为0）
//        finCashPayRule.setBranch(branch);
//        return finCashPayRule;
//
//    }

    /**
     * 创建现金流计划表对象
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public FinCashPlan newFinCashPlan(ValAssetsFundQuery002Dto dto, String finprodTypeFund,
//                                      String cashId, Long branch) {
//
//        FinCashPlan finCashPlan = new FinCashPlan();
//        // 现金流代码 - 现金流代码
//        finCashPlan.setCashId(cashId);
//        
//        
//      //【一体化修改】：begin========================================================================= yangjian 2018/1/4
//      //【一体化修改】：针对估值一体化变更【61：现金流计划表原字段【现金流序号】类型由原来的【NUMERIC(30, 4)】修改为【NUMERIC(10)】】
//        // 现金流序号-设为1
//        //finCashPlan.setCashNum(BigDecimal.ONE);
//        finCashPlan.setCashNum(new Long(0));
//
//      //【一体化修改】：end========================================================================= yangjian 2018/1/4
//        
//
//        
//        
//        // 调整后的计算开始日-计息开始日
//        finCashPlan.setVdate(dto.getVdate());
//        // 调整后的计算结束日- 计息结束日
//        finCashPlan.setMdate(dto.getMdate());
//        // 调整后的计划支付日-分红日（分红计划）
//        finCashPlan.setPayDate(dto.getPayDate());
//        // 现金流单位-02：总额
//        finCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
//        // 金融产品代码-资产代码
//        finCashPlan.setFinprodId(dto.getFinprodId());
//        // 金融产品类型-金融产品类型.基金（数据字典）
//        finCashPlan.setFinprodType(finprodTypeFund);
//        // 分支序号-设置全局变量(默认为0）
//        finCashPlan.setBranch(branch);
//        return finCashPlan;
//    }

    /**
     * 修改
     * 
     * @param dto
     * @param finprodTypeFund
     * @param cashId
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
//    public int update(ValAssetsFundQuery002Dto dto, String finprodTypeFund, String cashId,
//                      Long branch) {
//        /**
//         * 更新标的类金融产品扩展表
//         */
//        FinProductAdd finProductAdd = newFinProductAdd(dto, finprodTypeFund);
//        // 是否按日复利-是否按日复利（货币基金）
//        finProductAdd.setIsCompoundInt(dto.getIsCompoundInt());
//        valAssetsFundQuery002Mapper.updateFinProductExt(finProductAdd);
//
//        if (dto.getFundType().equals(DictCodeList.基金类型_货币基金.getValue())) {
//            /**
//             * 更新现金流信息表（货币基金）
//             */
//            FinCashInfo finCashInfo = newFinCashInfo(dto, finprodTypeFund, cashId, branch);
//            valAssetsFundQuery002Mapper.updateFinCashInfo(finCashInfo);
//
//            /**
//             * 更新现金流支付规则表（货币基金）
//             */
//            FinCashPayRule finCashPayRule = newFinCashPayRule(dto, finprodTypeFund, cashId,
//                branch);
//            valAssetsFundQuery002Mapper.updateFinCashPayRule(finCashPayRule);
//
//            // /**
//            // * 更新现金流计划表（货币基金）
//            // */
//            // FinCashPlan finCashPlan = newFinCashPlan(dto, finprodTypeFund, cashId, branch);
//            // valAssetsFundQuery002Mapper.updateFinCashPlan(finCashPlan);
//        }
//
//        /**
//         * 更新金融产品分类表
//         */
//        FinProductType finProductType = newFinProductType(dto, finprodTypeFund);
//        return valAssetsFundQuery002Mapper.updateFinProductType(finProductType);
//
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValAssetsFundQuery002Dto entity, OperationType type) {
    	
    	String action = StringUtil.EMPTY;
    	 if (OperationType.DELETE.equals(type)) {
             action = "delete";
         }
         else if (OperationType.NEW.equals(type)) {
             action = "insert";
         }
         else if(OperationType.MODIFY.equals(type)){
             action = "update";
         }
    	  restTemplate.postForObject(
    	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery002/" + action, entity,
    	            Integer.class);
    	 
    	 
    	 
//        // 分支序号
//        Long branch = new Long(0);
//        // 金融产品类型_基金
//        String finprodTypeFund = DictCodeListExt1.金融产品类型_基金.getValue();
//        // 现金流代码=InterfHelper.getMd5CashId_(资产代码,数据字典：金融产品类型.基金,数据字典：现金流二级类型.利息）
//        String cashId = InterfHelper.getMd5CashId_(entity.getFinprodId(), finprodTypeFund,
//            DictCodeListExt1.现金流二级类型_利息.getValue());
//        // 新建操作
//        if (OperationType.NEW.equals(type)) {
//            // 新建操作
//            /**
//             * 标的类金融产品表入库
//             */
//            FinProduct finProduct = newFinProduct(entity, finprodTypeFund);
//            finProduct.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//            finProduct.setCreateTime(new Date());
//            finProduct.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//            finProduct.setUpdateTime(new Date());
//            finProductMapper.insert(finProduct);
//
//            /**
//             * 标的类金融产品扩展表入库
//             */
//            FinProductAdd finProductAdd = newFinProductAdd(entity, finprodTypeFund);
//            finProductAdd.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//            finProductAdd.setCreateTime(new Date());
//            finProductAdd.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//            finProductAdd.setUpdateTime(new Date());
//            if (entity.getFundType().equals(DictCodeList.基金类型_货币基金.getValue())) {
//                /**
//                 * 标的类金融产品扩展表入库
//                 */
//                // 是否按日复利-是否按日复利（货币基金）
//            	finProductAdd.setIsCompoundInt(entity.getIsCompoundInt());
//
//                /**
//                 * 现金流信息表入库（基金类型为“货币基金”的分红规则信息入库）
//                 */
//                FinCashInfo finCashInfo = newFinCashInfo(entity, finprodTypeFund, cashId, branch);
//                finCashInfo.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashInfo.setCreateTime(new Date());
//                finCashInfo.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashInfo.setUpdateTime(new Date());
//                finCashInfoMapper.insert(finCashInfo);
//
//                /**
//                 * 现金流支付规则表入库（基金类型为“货币基金”的分红规则信息入库）
//                 */
//                FinCashPayRule finCashPayRule = newFinCashPayRule(entity, finprodTypeFund, cashId,
//                    branch);
//                finCashPayRule.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashPayRule.setCreateTime(new Date());
//                finCashPayRule.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                finCashPayRule.setUpdateTime(new Date());
//                finCashPayRuleMapper.insert(finCashPayRule);
//
//                // /**
//                // * 现金流计划表入库（基金类型为“货币基金”的分红计划信息入库）
//                // */
//                // FinCashPlan finCashPlan = newFinCashPlan(dto, finprodTypeFund, cashId, branch);
//                // finCashPlan.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//                // finCashPlan.setCreateTime(new Date());
//                // finCashPlan.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//                // finCashPlan.setUpdateTime(new Date());
//                // finCashPlanMapper.insert(finCashPlan);
//
//            }
//
//            /**
//             * 标的类金融产品扩展表入库
//             */
//            finProductAddMapper.insert(finProductAdd);
//
//            /**
//             * 金融产品分类表入库
//             */
//            FinProductType finProductType = newFinProductType(entity, finprodTypeFund);
//            finProductTypeMapper.insert(finProductType);
//        } // 修改
//        if (OperationType.MODIFY.equals(type)) {
//            // 修改操作
//            Map<String, Object> params = new HashMap<>();
//            params.put("finprodId", entity.getFinprodId());
//            boolean flag = valAssetsFundQuery001Mapper.checkFindprodId(params);
//            // 如果该资产存在交易，资产代码、基金类型和货币均不可修改
//            if (flag) {
//                // 更新标的类金融产品表信息
//                FinProduct finProduct = newFinProduct(entity, finprodTypeFund);
//                valAssetsFundQuery002Mapper.updateFinProduct2(finProduct);
//            }
//            else {
//                // 更新标的类金融产品表信息
//                FinProduct finProduct = newFinProduct(entity, finprodTypeFund);
//                valAssetsFundQuery002Mapper.updateFinProduct(finProduct);
//            }
//
//            this.update(entity, finprodTypeFund, cashId, branch);
//        }
//        // 删除
//        if (OperationType.DELETE.equals(type)) {
//        	
//        	 action = "delete";
//          String finprodId = entity.getFinprodId();
//          String fundType = entity.getFundType();
//          // 删除标的类金融产品扩展表信息
//          valAssetsFundQuery001Mapper.deleteByFindprodId2(finprodId);
//          // 删除金融产品分类表
//          valAssetsFundQuery001Mapper.deleteByFindprodId3(finprodId);
//          if (fundType.equals(DictCodeList.基金类型_货币基金.getValue())) {
//              // 删除现金流信息表
//              valAssetsFundQuery001Mapper.deleteByFindprodId4(finprodId);
//              // 删除现金流支付规则表
//              valAssetsFundQuery001Mapper.deleteByFindprodId5(finprodId);
//              // // 删除现金流计划表
//              // valAssetsFundQuery001Mapper.deleteByFindprodId6(finprodId);
//          }
//
//          // 删除标的类金融产品表信息
//          valAssetsFundQuery001Mapper.deleteByFindprodId(finprodId);
//        }

    }
    
    
public boolean checkFindprodId(String finprodId) {

    	
    	
    	boolean flag= restTemplate.getForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery002/checkFindprodId?finprodId="+finprodId,
                boolean.class);
    	 
        return flag;}

}

package com.joyintech.service.valuation.assets.posit.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.assets.posit.dao.ValDespositAssetQuery001Mapper;
import com.joyintech.service.valuation.assets.posit.dao.ValDespositAssetQuery002Mapper;
import com.joyintech.service.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPayRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 存款类交易
 * 
 * @author HuangJian
 * @version 1.0 2018年10月30日
 * @see ValDespositAssetQuery002Service
 * @since 1.0
 */
@Service(value="serviceValDespositAssetQuery002Service")

public class ValDespositAssetQuery002Service extends CommonExportService<ValDespositAssetQuery002Dto> implements 
        BaseProcessService<ValDespositAssetQuery002Dto> {

    /**
     * 标的类金融产品
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 金融产品分类表
     */
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    /**
     * 现金流信息表
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 现金流计算规则表
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    /**
     * 现金流支付规则表
     */
    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    /**
     * 现金流计划表
     */
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * valDespositAssetQuery001Mapper
     */
    @Autowired
    private ValDespositAssetQuery001Mapper valDespositAssetQuery001Mapper;

    /**
     * valDespositAssetQuery002Mapper
     */
    @Autowired
    private ValDespositAssetQuery002Mapper valDespositAssetQuery002Mapper;

    /**
     * DespositAssetQuery003Service
     */
    @Autowired
    private ValDespositAssetQuery001Service valDespositAssetQuery001Service;

    /**
     * 根据查询finprodId单个详细信息
     * 
     * @param map Map<String, Object>
     * @return ValDespositAssetQuery002Dto
     * @see
     * @since 1.0
     */
    public ValDespositAssetQuery002Dto fingByfinprodId(Map<String, Object> map) {
        return valDespositAssetQuery002Mapper.selectByfinprodId(map);
    }

    /*
     * public List<ValIntRateAdjustmentDto> getRat(Map<String,Object> map){ return
     * valDespositAssetQuery002Mapper.selectIntRateAdjustmentByFinprodId(map); } public
     * List<ValPayIntPlanDto> getPay(Map<String,Object> map){ return
     * valDespositAssetQuery002Mapper.selectValPayIntPlanDtoByFinprodId(map); }
     */

    /**
     * 利率表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    public List<DateTableDto> getTable1(String finprodId) {
        return valDespositAssetQuery002Mapper.getTable1(finprodId);
    }

    /**
     * 付息计划表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    /** modify by dongjun on 20181203 start**/
    public List<DateTableDto> getTable2(Map<String, Object> map) {
        return valDespositAssetQuery002Mapper.getTable2(map);
    }

    /**
     * 还本计划表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    public List<DateTableDto> getTable3(Map<String, Object> map) {
        return valDespositAssetQuery002Mapper.getTable3(map);
    }
    /** modify by dongjun on 20181203 end**/
    
    /**
     * 提交commit
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return int
     * @see
     * @since 1.0
     */
    public int doCommit(ValDespositAssetQuery002Dto dto) {
        // 利率表
        List<DateTableDto> table1 = dto.getTable1();
        // 付息计划表
        List<DateTableDto> table2 = dto.getTable2();
        /**modify by dongjun on 20181203 start **/
        //还本计划表
        List<DateTableDto> table3 = dto.getTable3();
        // 进行升序排列
        Collections.sort(table3, new Comparator<DateTableDto>() {
            
            public int compare(DateTableDto o1, DateTableDto o2) {
                return o1.getRepayDate().compareTo(o2.getRepayDate());
            }
        });
        /**modify by dongjun on 20181203 end **/
        // 进行升序排列
        Collections.sort(table2, new Comparator<DateTableDto>() {

            public int compare(DateTableDto o1, DateTableDto o2) {
                return o1.getVdate().compareTo(o2.getVdate());
            }
        });
        
        int count = 0;
        // 新建
        if (DictCodeList.是否_是.getValue().equals(dto.getIsEdit())) {

            count += finProductMapper.insertSelective(finProduct(dto));
            count += finProductTypeMapper.insertSelective(finProductType(dto));
            count += finCashInfoMapper.insertSelective(finCashInfo(dto));
            count += finCashPayRuleMapper.insertSelective(finCashPayRule(dto));
            for (DateTableDto item : table1) {
                count += finCashCalcRuleMapper.insertSelective(finCashCalcRule(dto, item));
            }
            for (int i = 0; i < table2.size(); i++ ) {
                count += finCashPlanMapper.insertSelective(finCashPlan(dto, table2.get(i), i));
            }
            /** modify by dongjun on 20181203 start **/
            count += finCashInfoMapper.insertSelective(rePayfinCashInfo(dto));
            count += finCashPayRuleMapper.insertSelective(rePayFinCashPayRule(dto));
            for(int i = 0; i < table3.size(); i++ ){
                count += finCashPlanMapper.insertSelective(rePayfinCashPlan(dto, table3.get(i), i));
            }
            /** modify by dongjun on 20181203 end **/

        }
        // 修改
        else if (DictCodeList.是否_否.getValue().equals(dto.getIsEdit())) {
            /** modify by dongjun on 20181204 remark:兼容接口推送过来不存在还本付息计划的存款资产 start**/
            //利息现金流
            FinCashInfo finCashInfo = finCashInfo(dto);
            //本金现金流
            FinCashInfo prinFinCashInfo = rePayfinCashInfo(dto);
            /** modify by huangjian on 20190103 remark:标的类金融产品表到期赎回价字段删除 start**/
//            count += valDespositAssetQuery002Mapper.updateFinProduct(finProduct(dto));
            count +=finProductMapper.updateByPrimaryKeySelective(finProduct(dto));
            /** modify by huangjian on 20190103 remark:标的类金融产品表到期赎回价字段删除 end**/
            count += valDespositAssetQuery002Mapper.updateFinProductType(finProductType(dto));
            //count += valDespositAssetQuery002Mapper.updateFinCashInfo(finCashInfo(dto));
            
            if(valDespositAssetQuery002Mapper.queryFinCashInfoeByCashId(finCashInfo.getCashId()) == 1){
                count += valDespositAssetQuery002Mapper.updateFinCashInfo(finCashInfo);
            } else {
                count += finCashInfoMapper.insertSelective(finCashInfo);
            }
            
            if(valDespositAssetQuery002Mapper.queryFinCashPayRuleByCashId(finCashInfo.getCashId()) == 1){
                count += valDespositAssetQuery002Mapper.updateFinCashPayRule(finCashPayRule(dto));
            } else {
                count += finCashPayRuleMapper.insertSelective(finCashPayRule(dto));
            }
            // for (DateTableDto item : table1) {
            // count += valDespositAssetQuery002Mapper.updateFinCashCalcRule(
            // FinCashCalcRule(dto, item));
            // }
            // for (int i = 0; i < table2.size(); i++ ) {
            // count += valDespositAssetQuery002Mapper.updateFinCashPlan(
            // FinCashPlan(dto, table2.get(i), i));
            // }
            String finprodType = DictCodeListExt1.金融产品类型_存款.getValue();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", dto.getFinprodId());
            map.put("finprodType", finprodType);
            count += valDespositAssetQuery001Mapper.deleteFinCashCalcRule(map);
            count += valDespositAssetQuery001Mapper.deleteFinCashPlan(map);
            for (DateTableDto item : table1) {
                count += finCashCalcRuleMapper.insertSelective(finCashCalcRule(dto, item));
            }
            for (int i = 0; i < table2.size(); i++ ) {
                count += finCashPlanMapper.insertSelective(finCashPlan(dto, table2.get(i), i));
            }
            /** modify by dongjun on 20181203 start **/
            if(DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getType1())){
                finCashInfoMapper.deleteByPrimaryKey(prinFinCashInfo.getCashId());
                FinCashPayRuleKey key = new FinCashPayRuleKey();
                key.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(),
                    dto.getFinprodType(), DictCodeListExt1.现金流二级类型_本金.getValue()));
                key.setEffDate(dto.getVdate());
                finCashPayRuleMapper.deleteByPrimaryKey(key);
            }else{
                
                if(valDespositAssetQuery002Mapper.queryFinCashInfoeByCashId(prinFinCashInfo.getCashId()) == 1){
                    count += valDespositAssetQuery002Mapper.updateFinCashInfo(prinFinCashInfo);
                } else {
                    count += finCashInfoMapper.insertSelective(prinFinCashInfo);
                }
                
                if(valDespositAssetQuery002Mapper.queryFinCashPayRuleByCashId(prinFinCashInfo.getCashId()) == 1){
                    count += valDespositAssetQuery002Mapper.updateFinCashPayRule(rePayFinCashPayRule(dto));
                } else {
                    count += finCashPayRuleMapper.insertSelective(rePayFinCashPayRule(dto));
                }
                /** modify by dongjun on 20181204 remark:兼容接口推送过来不存在还本付息计划的存款资产 end**/
                for (int i = 0; i < table3.size(); i++ ) {
                    count += finCashPlanMapper.insertSelective(rePayfinCashPlan(dto, table3.get(i), i));
                }
                /** modify by dongjun on 20181203 end **/
            }
        }
        return count;

    }

    /**
     * 标的类金融产品
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinProduct
     * @see
     * @since 1.0
     */
    public FinProduct finProduct(ValDespositAssetQuery002Dto dto) {
        FinProduct finProduct = new FinProduct();
        finProduct.setFinprodId(dto.getFinprodId());
        finProduct.setFinprodType(dto.getFinprodType());
        finProduct.setFinprodAbbr(dto.getFinprodName());
        finProduct.setFinprodName(dto.getFinprodName());
        finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
        finProduct.setFinprodMarketId(dto.getFinprodId());
        finProduct.setIssueAmt(dto.getIssueAmt());
        finProduct.setCcy(dto.getCcy());
        finProduct.setBlnArea(dto.getBlnArea());
        // 交易日历
        finProduct.setCalendarId(dto.getCalendarId());
        // finProduct.setCalendarId(interfCommonService.getCalendarId(dto.getCalendarId()));
        finProduct.setTradeMarket(dto.getTradeMarket());
        finProduct.setIssuer(dto.getIssuer());
        finProduct.setIdate(dto.getVdate());
        finProduct.setVdate(dto.getVdate());
        finProduct.setMdate(dto.getMdate());
        finProduct.setActmdate(dto.getMdate());
        finProduct.setpFinprodId(dto.getFinprodId());
        finProduct.setoFinprodId(dto.getFinprodId());
        
      //【一体化修改】：begin========================================================================= yangjian 2018/1/4
      //【一体化修改】：针对估值一体化变更【59：标的类金融产品表原字段【原金融产品类型】删除】
        //        finProduct.setoFinprodType(dto.getFinprodType());

      //【一体化修改】：end========================================================================= yangjian 2018/1/4
        

        finProduct.setCapiIncomeFeature(dto.getCapiIncomeFeature());
        finProduct.setCreateUser(dto.getCreateUser());
        finProduct.setCreateTime(new Date());
        finProduct.setUpdateUser(dto.getUpdateUser());
        finProduct.setUpdateTime(new Date());
        return finProduct;
    }

    /**
     * 金融产品分类表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinProductType
     * @see
     * @since 1.0
     */
    public FinProductType finProductType(ValDespositAssetQuery002Dto dto) {
        FinProductType finProductType = new FinProductType();
        finProductType.setFinprodId(dto.getFinprodId());
        
        
        //【一体化修改】：begin========================================================================= yangjian 2018/1/4
        //【一体化修改】：针对估值一体化变更【58：金融产品分类表金融产品类型删除】
        //finProductType.setFinprodType(dto.getFinprodType());

        //【一体化修改】：end========================================================================= yangjian 2018/1/4
        
        finProductType.setType1(dto.getType1());
        FinProductClass finProductClass = new FinProductClass();
        finProductClass.setFinprodType(dto.getFinprodType());
        finProductClass.setAssetType(dto.getType1());
        finProductClass.setTradeMarket(dto.getTradeMarket());
        finProductClass.setBlnArea(dto.getBlnArea());
        finProductClass.setAccountingClass(dto.getType2());
        // finProductType.setType2(interfCommonService.getFinanceProductClass(finProductClass));
        finProductType.setType2(dto.getType2());
        finProductType.setType3(interfCommonService.getFinanceProductTaxClass(finProductClass));
        return finProductType;

    }

    /**
     * 现金流信息表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinCashInfo
     * @see
     * @since 1.0
     */
    public FinCashInfo finCashInfo(ValDespositAssetQuery002Dto dto) {
        FinCashInfo finCashInfo = new FinCashInfo();
        finCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(), dto.getFinprodType(),
            DictCodeListExt1.现金流二级类型_利息.getValue()));
        finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        finCashInfo.setFinprodId(dto.getFinprodId());
        finCashInfo.setFinprodType(dto.getFinprodType());
        finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
        finCashInfo.setCreateUser(dto.getCreateUser());
        finCashInfo.setCreateTime(new Date());
        finCashInfo.setUpdateUser(dto.getUpdateUser());
        finCashInfo.setUpdateTime(new Date());
        return finCashInfo;
    }
    
    /**
     * 还本现金流信息表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinCashInfo
     * @see
     * @since 1.0
     */
    /** modify by dongjun on 20181203 start**/
    public FinCashInfo rePayfinCashInfo(ValDespositAssetQuery002Dto dto) {
        FinCashInfo finCashInfo = new FinCashInfo();
        finCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(), dto.getFinprodType(),
            DictCodeListExt1.现金流二级类型_本金.getValue()));
        finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
        finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
        finCashInfo.setFinprodId(dto.getFinprodId());
        finCashInfo.setFinprodType(dto.getFinprodType());
        finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
        finCashInfo.setCreateUser(dto.getCreateUser());
        finCashInfo.setCreateTime(new Date());
        finCashInfo.setUpdateUser(dto.getUpdateUser());
        finCashInfo.setUpdateTime(new Date());
        return finCashInfo;
    }
    /** modify by dongjun on 20181203 end**/
    
    /**
     * 现金流计算规则表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @param table DateTableDto
     * @return FinCashCalcRule
     * @see
     * @since 1.0
     */
    public FinCashCalcRule finCashCalcRule(ValDespositAssetQuery002Dto dto, DateTableDto table) {
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        finCashCalcRule.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(),
            dto.getFinprodType(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        //
        finCashCalcRule.setEffDate(DateUtil.parse(table.getEffDate()));
        finCashCalcRule.setCalcType(dto.getCalcType());
        //
        finCashCalcRule.setYield(ArithmeticUtil.divide(table.getYield(), new BigDecimal(100)));
        finCashCalcRule.setBasis(dto.getBasis());
        finCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        finCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        finCashCalcRule.setFinprodId(dto.getFinprodId());
        finCashCalcRule.setFinprodType(dto.getFinprodType());
        finCashCalcRule.setRemark(dto.getRemark());
        finCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        finCashCalcRule.setCreateUser(dto.getCreateUser());
        finCashCalcRule.setCreateTime(new Date());
        finCashCalcRule.setUpdateUser(dto.getUpdateUser());
        finCashCalcRule.setUpdateTime(new Date());
        return finCashCalcRule;
    }

    /**
     * 现金流支付规则表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinCashPayRule
     * @see
     * @since 1.0
     */
    public FinCashPayRule finCashPayRule(ValDespositAssetQuery002Dto dto) {
        FinCashPayRule finCashPayRule = new FinCashPayRule();
        finCashPayRule.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(),
            dto.getFinprodType(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        finCashPayRule.setEffDate(dto.getVdate());
        finCashPayRule.setPayCycle(dto.getPayCycle());
        //finCashPayRule.setTdayRule(dto.getTdayRule());
        finCashPayRule.setEndTdayRule(dto.getEndTdayRule());
        finCashPayRule.setPayTdayRule(dto.getPayTdayRule());
        finCashPayRule.setCalcRule(dto.getCalcRule());
        finCashPayRule.setFirstPayDate(dto.getFirstPayDate());
        finCashPayRule.setFinprodId(dto.getFinprodId());
        finCashPayRule.setFinprodType(dto.getFinprodType());
        finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        finCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        finCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
        finCashPayRule.setCreateUser(dto.getCreateUser());
        finCashPayRule.setCreateTime(new Date());
        finCashPayRule.setUpdateUser(dto.getUpdateUser());
        finCashPayRule.setUpdateTime(new Date());
        return finCashPayRule;
    }

    /**
     * 还本现金流支付规则表
     * 
     * @param dto ValDespositAssetQuery002Dto
     * @return FinCashPayRule
     * @see
     * @since 1.0
     */
    public FinCashPayRule rePayFinCashPayRule(ValDespositAssetQuery002Dto dto) {
        FinCashPayRule finCashPayRule = new FinCashPayRule();
        finCashPayRule.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(),
            dto.getFinprodType(), DictCodeListExt1.现金流二级类型_本金.getValue()));
        finCashPayRule.setEffDate(dto.getVdate());
        finCashPayRule.setPayCycle(dto.getPayCycle());
        // finCashPayRule.setTdayRule(dto.getTdayRule());
        finCashPayRule.setEndTdayRule(dto.getEndTdayRule());
        finCashPayRule.setPayTdayRule(dto.getPayTdayRule());
        finCashPayRule.setCalcRule(dto.getCalcRule());
        finCashPayRule.setFirstPayDate(dto.getFirstPayDate());
        finCashPayRule.setFinprodId(dto.getFinprodId());
        finCashPayRule.setFinprodType(dto.getFinprodType());
        finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        finCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        finCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
        finCashPayRule.setCreateUser(dto.getCreateUser());
        finCashPayRule.setCreateTime(new Date());
        finCashPayRule.setUpdateUser(dto.getUpdateUser());
        finCashPayRule.setUpdateTime(new Date());
        return finCashPayRule;
    }
    
    /**
     * 现金流计划表
     * 
     * @param dto DateTableDto
     * @param table DateTableDto
     * @param i Int
     * @return FinCashPlan
     * @see
     * @since 1.0
     */
    public FinCashPlan finCashPlan(ValDespositAssetQuery002Dto dto, DateTableDto table, int i) {
        FinCashPlan finCashPlan = new FinCashPlan();
        finCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(), dto.getFinprodType(),
            DictCodeListExt1.现金流二级类型_利息.getValue()));
        
        
        //【一体化修改】：begin========================================================================= yangjian 2018/1/4
        //【一体化修改】：针对估值一体化变更【61：现金流计划表原字段【现金流序号】类型由原来的【NUMERIC(30, 4)】修改为【NUMERIC(10)】】
        //finCashPlan.setCashNum(new BigDecimal(i + 1));
        finCashPlan.setCashNum(new Long(i + 1));

        //【一体化修改】：end========================================================================= yangjian 2018/1/4

        finCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        finCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
        finCashPlan.setVdate(DateUtil.parse(table.getVdate()));
        finCashPlan.setMdate(DateUtil.parse(table.getMdate()));
        finCashPlan.setPayDate(DateUtil.parse(table.getPayDate()));
        // finCashPlan.setIntAmt(ArithmeticUtil.divide(table.getIntAmt(),new BigDecimal(100)));
        // finCashPlan.setCashAmt(ArithmeticUtil.divide(table.getIntAmt(),new BigDecimal(100)));
        finCashPlan.setIntAmt(table.getIntAmt());
        finCashPlan.setCashAmt(table.getIntAmt());

        finCashPlan.setFinprodId(dto.getFinprodId());
        finCashPlan.setFinprodType(dto.getFinprodType());
        finCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        finCashPlan.setCreateUser(dto.getCreateUser());
        finCashPlan.setCreateTime(new Date());
        finCashPlan.setUpdateUser(dto.getUpdateUser());
        finCashPlan.setUpdateTime(new Date());
        return finCashPlan;
    }

    /**
     * 还本现金流计划表
     * 
     * @param dto DateTableDto
     * @param table DateTableDto
     * @param i Int
     * @return FinCashPlan
     * @see
     * @since 1.0
     */
    /** modify by dongjun on 20181203 start**/
    public FinCashPlan rePayfinCashPlan(ValDespositAssetQuery002Dto dto, DateTableDto table, int i) {
        FinCashPlan finCashPlan = new FinCashPlan();
        finCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getFinprodId(), dto.getFinprodType(),
            DictCodeListExt1.现金流二级类型_本金.getValue()));
        
        
        //【一体化修改】：begin========================================================================= yangjian 2018/1/4
        //【一体化修改】：针对估值一体化变更【61：现金流计划表原字段【现金流序号】类型由原来的【NUMERIC(30, 4)】修改为【NUMERIC(10)】】
        //finCashPlan.setCashNum(new BigDecimal(i + 1));
        finCashPlan.setCashNum(new Long(i + 1));

        //【一体化修改】：end========================================================================= yangjian 2018/1/4

        
        // 未调整的计算结束日 = table.还本日
        finCashPlan.setMdateUnadjust(DateUtil.parse(table.getRepayDate()));
        // 未调整的计划支付日 = table.支付日
        finCashPlan.setPayDateUnadjust(DateUtil.parse(table.getPayDate()));
        // 调整后的计算结束日 = table.还本日
        finCashPlan.setMdate(DateUtil.parse(table.getRepayDate()));
        // 调整后的计划支付日 = table.支付日
        finCashPlan.setPayDate(DateUtil.parse(table.getPayDate()));
        // 计划支付本金  = table.还本金额
        finCashPlan.setPrinAmt(table.getPrinAmt());
        // 计息本金 = table.还本金额
        finCashPlan.setIntPrinAmt(table.getPrinAmt());
        // 是否支付利息 = 利息金额>0 --> 是
        // 利息金额<=0 --> 否
        if(!ArithmeticUtil.equals(table.getIntAmt(), BigDecimal.ZERO)){
            finCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
        }else {
            finCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
        }
        // 计划支付利息 = E.利息金额
        finCashPlan.setIntAmt(table.getIntAmt());
        // 计划支付现金流 = E.还本金额+E.利息金额        
        finCashPlan.setCashAmt(ArithmeticUtil.doAdd(2,RoundingMode.UP, table.getPrinAmt(),table.getIntAmt()));        
        finCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        finCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
        finCashPlan.setFinprodId(dto.getFinprodId());
        finCashPlan.setFinprodType(dto.getFinprodType());
        finCashPlan.setBranch(InterfCommonConstant.分支序号_默认);

        finCashPlan.setCreateUser(dto.getCreateUser());
        finCashPlan.setCreateTime(new Date());
        finCashPlan.setUpdateUser(dto.getUpdateUser());
        finCashPlan.setUpdateTime(new Date());

        return finCashPlan;
    }
    /** modify by dongjun on 20181203 start**/
    
    /**
     * 表格新建
     * 
     * @param table2 List<DateTableDto>
     * @param dto DateTableDto
     * @return DateTableDto
     * @see
     * @since 1.0
     */
    public DateTableDto setDateTableDto(List<DateTableDto> table2,
                                        ValDespositAssetQuery002Dto dto) {
        DateTableDto table = new DateTableDto();
        // if(StringUtils.isEmpty(table2)){
        // table.setVdate(dto.getVdate());
        //
        // }else{
        // table.setVdate(table2.get(table2.size()-1).getMdate());
        // }
        return table;

    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * 
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++ ) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            }
            else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValDespositAssetQuery002Dto entity, OperationType type) {
        // 删除
        if (OperationType.DELETE.equals(type)) {
            valDespositAssetQuery001Service.delete(entity.getFinprodId());
        }
        else {
            this.doCommit(entity);
        }
    }

}
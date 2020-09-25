/*
 * 文件名：MstTradeOpponent002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.msttradeopponent.service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinRateInfoMapper;
import com.joyintech.tams.common.base.dao.MstCounterAcctMapper;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.dao.RepAssetMngPlanMapper;
import com.joyintech.tams.common.base.dao.StlFinProductAcctMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductTypeKey;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.MstCounterAcct;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent001Mapper;
import com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent002Mapper;
import com.joyintech.service.basicdata.msttradeopponent.dto.FinCashDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.StlCapacctDto;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;

/**
 * 交易对手管理Service
 *
 * @author fanshuanghu
 * @version 1.0 2018年8月23日
 * @see BasicMstTradeOpponentService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class BasicMstTradeOpponentService extends BaseService<MstTradeOpponentDto>{

    /**
     * 交易对手表mapper
     */
    @Autowired
    private BasicMstTradeOpponent002Mapper basicMstTradeOpponent002Mapper;
    /**
     * 交易对手mapper
     */
    @Autowired
    private BasicMstTradeOpponent001Mapper basicMstTradeOpponent001Mapper;

    /**
     * 现金流mapper
     */
    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;

    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    @Autowired
    private FinProductMapper finProductMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private RepAssetMngPlanMapper repAssetMngPlanMapper;

    @Autowired
    private MstCounterAcctMapper mstCounterAcctMapper;

    @Autowired
    private PaFinProductMapper paFinProductMapper;

    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    @Autowired
    private StlFinProductAcctMapper stlFinProductAcctMapper;
    /**
     *
     * 工作流终结操作
     * <功能详细描述>
     * @param entity 业务实体
     * @param type 类型
     * @see
     * @since 1.0
     */
    public void doComplete(MstTradeOpponentDto entity, String type) {
        if (StringUtil.equals(type, "NEW")) {
            // 调用父类的主键插入方法
            entity.setFinprodType(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
            insertAount(entity);
            entity.setLinkId(entity.getCustomerId());
//            if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())) {
//                entity.setLinkId(entity.getFinprodId());
//                List<FinCashDto> finDto = entity.getSelectFinCash();
//                mstTradeOpponent002Mapper.insertFinProduct(entity);
//                mstTradeOpponent002Mapper.insertFinProductType(entity);
//                mstTradeOpponent002Mapper.insertRepAssetMngPlan(entity);
//                for (FinCashDto dto : finDto) {
//                    dto.setRate(dto.getRate().divide(new BigDecimal("100")));
//                    System.out.println(dto.getCashType2());
//                    dto.setFinprodId(entity.getFinprodId());
//                    dto.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
//                    dto.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
//                    Map<String,Object> feeMap = new HashMap<String,Object>();
//                    feeMap.put("feeId", dto.getFeeId());
//                    if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道托管费.getValue())){
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道托管费.getValue());
//                    }else if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道管理费.getValue())){
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道管理费.getValue());
//                    }else{
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道其他费.getValue());
//                    }
//                    String cashId = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), 0L, dto.getCashType2());
//                    dto.setCashId(cashId);
////                    dto.setCashId(md5(entity.getFinprodId()+
////                        DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue()+dto.getCashType2()));
//                    mstTradeOpponent002Mapper.insertFinCashCalcRule(dto);
//                    mstTradeOpponent002Mapper.insertFinCashInfo(dto);
//                    mstTradeOpponent002Mapper.insertFinRateInfo(dto);
//                }
//                if(StringUtil.isNotEmpty(entity.getBankTrustCode())){
//                    mstTradeOpponent002Mapper.insertPaFinProduct(entity);
//                }
//            }
            this.insertCashFlowInfo(entity);
        }
        else {
//            mstTradeOpponent001Mapper.deleteCounterAcct(entity);
//            mstTradeOpponent001Mapper.deleteMstCounterParty(entity);
//            entity.setLinkId(entity.getCustomerId());
//            if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())) {
//                entity.setLinkId(entity.getFinprodId());
//                FinProduct finProduct = new FinProduct();
//                finProductMapper.deleteByPrimaryKey(entity.getFinprodId());
//
//
//                mstTradeOpponent001Mapper.deleteFinCashCalcRule(entity);
//                mstTradeOpponent001Mapper.deleteFinCashInfo(entity);
//                mstTradeOpponent001Mapper.deleteFinCashPlan(entity);
//                mstTradeOpponent001Mapper.deleteFinProduct(entity);
//                mstTradeOpponent001Mapper.deleteFinProductType(entity);
//                mstTradeOpponent001Mapper.deleteRepAssetMngPlan(entity);
//                if(StringUtil.isNotEmpty(entity.getBankTrustCode())){
//                    mstTradeOpponent001Mapper.deletePaFinProduct(entity);
//                }
//            }
//            entity.setFinprodType(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
////            entity.setLinkId(entity.getCustomerId());
//            mstCounterPartyMapper.insertSelective(entity);
//            List<StlCapacctDto> dtos = entity.getSelectStlCapacct();
//            if(dtos!=null){
//                for (StlCapacctDto dto : dtos) {
//                    dto.setCounterId(entity.getCounterId());
//                    String[] noAndCcy = dto.getAcctNo().split("_");
//                    dto.setAcctNo(noAndCcy[0]);
//                    dto.setCcy(noAndCcy[1]);
//                    mstTradeOpponent002Mapper.insertCounterAcct(dto);
//                }
//            }
//            if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())) {
//                List<FinCashDto> finDto = entity.getSelectFinCash();
//
//                FinProduct finProduct = new FinProduct();
//                BeanUtil.copyPropertiesIgnoreNull(finProduct, entity);
//                finProduct.setIssuer(entity.getManagerId());
//                finProduct.setFinprodType2(entity.getFinprodType());
//                finProduct.setFinprodType("");
//                finProductMapper.insertSelective(finProduct);
//
//                FinProductType finProductType = new FinProductType();
//                finProductType.setType6(entity.getType1());
//                finProductType.setType7(entity.getType2());
//                finProductType.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
//
//                RepAssetMngPlan repAssetMngPlan = new RepAssetMngPlan();
//                BeanUtil.copyPropertiesIgnoreNull(repAssetMngPlan, entity);
//                repAssetMngPlan.setRepFinprodId(entity.getFinprodId());
//
////                mstTradeOpponent002Mapper.insertFinProduct(entity);
////                mstTradeOpponent002Mapper.insertFinProductType(entity);
////                mstTradeOpponent002Mapper.insertRepAssetMngPlan(entity);
//                for (FinCashDto dto : finDto) {
//                    dto.setRate(dto.getRate().divide(new BigDecimal("100")));
//                    dto.setFinprodId(entity.getFinprodId());
//                    dto.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
//                    dto.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
//                    Map<String,Object> feeMap = new HashMap<String,Object>();
//                    feeMap.put("feeId", dto.getFeeId());
////                    dto.setCashType2(mstTradeOpponent002Mapper.selectCashType2(feeMap));
//
////                    dto.setCashId(md5(entity.getFinprodId()+
////                        DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue()+dto.getCashType2()));
////                    dto.setCashType2(mstTradeOpponent002Mapper.selectCashType2(feeMap));
//                    if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道托管费.getValue())){
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道托管费.getValue());
//                    }else if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道管理费.getValue())){
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道管理费.getValue());
//                    }else{
//                        dto.setCashType2(DictCodeListExt1.现金流二级类型_通道其他费.getValue());
//                    }
//                    String cashId = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), 0L, dto.getCashType2());
//                    dto.setCashId(cashId);
//                    FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
//                    BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, dto);
//                    finCashCalcRule.setBaseType(dto.getBasicBill());
//                    finCashCalcRule.setEffDate(new Date());
//                    finCashCalcRule.setFinprodType2(dto.getFinprodType());
//                    finCashCalcRule.setFinprodType("");
//                    finCashCalcRule.setYield(dto.getRate());
//                    finCashCalcRuleMapper.insertSelective(finCashCalcRule);
//
//                    FinCashInfo finCashInfo = new FinCashInfo();
//                    BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
//                    finCashInfo.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
//                    finCashInfoMapper.insertSelective(finCashInfo);
//
//                    FinRateInfo finRateInfo = new FinRateInfo();
//                    BeanUtil.copyPropertiesIgnoreNull(finRateInfo, dto);
//                    finRateInfo.setEffDate(new Date());
//                    finRateInfo.setFinprodType2(dto.getFinprodType());
//                    finRateInfo.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
//                    finRateInfoMapper.insertSelective(finRateInfo);
//
//
////                    mstTradeOpponent002Mapper.insertFinCashCalcRule(dto);
////                    mstTradeOpponent002Mapper.insertFinCashInfo(dto);
////                    mstTradeOpponent002Mapper.insertFinRateInfo(dto);
//                }
//                if(StringUtil.isNotEmpty(entity.getBankTrustCode())){
//                    PaFinProduct paFinProduct = new PaFinProduct();
//                    BeanUtil.copyPropertiesIgnoreNull(paFinProduct, entity);
//                    paFinProductMapper.insertSelective(paFinProduct);
//
////                    mstTradeOpponent002Mapper.insertPaFinProduct(entity);
//                }
//            }
//
//        }
            this.deleteCounterInfo(entity);
            this.insertAount(entity);
            this.insertCashFlowInfo(entity);
        }
    }


    public void insertCashFlowInfo(MstTradeOpponentDto entity){
        if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())) {
            List<FinCashDto> finDto = entity.getSelectFinCash();

            FinProduct finProduct = new FinProduct();
            BeanUtil.copyPropertiesIgnoreNull(finProduct, entity);
            finProduct.setIssuer(entity.getManagerId());
            finProduct.setFinprodType2(entity.getFinprodType());
            finProduct.setFinprodType("");

            // 一体化对应etliu start
            FinProductType finProductType = new FinProductType();
            BeanUtil.copyPropertiesIgnoreNull(finProductType, entity);
            finProductType.setType6(entity.getType1());
            finProductType.setType7(entity.getType2());
            finProductType.setBranch(entity.getBranch());

            ValItemValueGenUtil.genFinprodTypeForFinProduct(finProduct, finProductType);
            // 一体化对应etliu end

            finProductMapper.insertSelective(finProduct);

         // 一体化对应etliu start
//            FinProductType finProductType = new FinProductType();
//            BeanUtil.copyPropertiesIgnoreNull(finProductType, entity);
//            finProductType.setType6(entity.getType1());
//            finProductType.setType7(entity.getType2());
//            finProductType.setBranch(entity.getBranch());

            ValItemValueGenUtil.genTypeForFinProductType(finProduct, finProductType);
         // 一体化对应etliu end
            finProductTypeMapper.insertSelective(finProductType);

            RepAssetMngPlan repAssetMngPlan = new RepAssetMngPlan();
            BeanUtil.copyPropertiesIgnoreNull(repAssetMngPlan, entity);
            repAssetMngPlan.setRepFinprodId(entity.getFinprodId());
            repAssetMngPlan.setRepBuyStruct(entity.getRepBuySruct());
            repAssetMngPlan.setRepIsCtrlBottom(entity.getRepIsCtrlBttom());
            repAssetMngPlan.setRepRemark(entity.getRepRemarks());
            repAssetMngPlan.setRepTradeFeeRate(ArithmeticUtil.divide(repAssetMngPlan.getRepTradeFeeRate(), new BigDecimal("100"), 5));
            repAssetMngPlan.setRepAgencyFeeRate(ArithmeticUtil.divide(repAssetMngPlan.getRepAgencyFeeRate(), new BigDecimal("100"), 5));
            repAssetMngPlan.setRepOtherFeeRate(ArithmeticUtil.divide(repAssetMngPlan.getRepOtherFeeRate(), new BigDecimal("100"), 5));
            repAssetMngPlanMapper.insertSelective(repAssetMngPlan);

//            mstTradeOpponent002Mapper.insertFinProduct(entity);
//            mstTradeOpponent002Mapper.insertFinProductType(entity);
//            mstTradeOpponent002Mapper.insertRepAssetMngPlan(entity);
            for (FinCashDto dto : finDto) {
                dto.setRate(dto.getRate().divide(new BigDecimal("100")));
                dto.setFinprodId(entity.getFinprodId());
                dto.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
                dto.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
                Map<String,Object> feeMap = new HashMap<String,Object>();
                feeMap.put("feeId", dto.getFeeId());
//                dto.setCashType2(mstTradeOpponent002Mapper.selectCashType2(feeMap));

//                dto.setCashId(md5(entity.getFinprodId()+
//                    DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue()+dto.getCashType2()));
//                dto.setCashType2(mstTradeOpponent002Mapper.selectCashType2(feeMap));
                if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道托管费.getValue())){
                    dto.setCashType2(DictCodeListExt1.现金流二级类型_通道托管费.getValue());
                }else if(StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_通道管理费.getValue())){
                    dto.setCashType2(DictCodeListExt1.现金流二级类型_通道管理费.getValue());
                }else{
                    dto.setCashType2(DictCodeListExt1.现金流二级类型_通道其他费.getValue());
                }
                String cashId = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), 0L, dto.getCashType2());
                dto.setCashId(cashId);
                FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, dto);
                finCashCalcRule.setBaseType(dto.getBasicBill());
                finCashCalcRule.setEffDate(entity.getRepSetDate());
                finCashCalcRule.setFinprodType2(dto.getFinprodType());
                finCashCalcRule.setFinprodType("");
                finCashCalcRule.setYield(dto.getRate());
                finCashCalcRule.setBranch(entity.getBranch());
                finCashCalcRuleMapper.insertSelective(finCashCalcRule);

                FinCashInfo finCashInfo = new FinCashInfo();
                BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
                finCashInfo.setBranch(entity.getBranch());
                finCashInfoMapper.insertSelective(finCashInfo);

                FinRateInfo finRateInfo = new FinRateInfo();
                BeanUtil.copyPropertiesIgnoreNull(finRateInfo, dto);
                finRateInfo.setEffDate(entity.getRepSetDate());
                finRateInfo.setFinprodType2(dto.getFinprodType());
                finRateInfo.setBranch(entity.getBranch());
                finRateInfoMapper.insertSelective(finRateInfo);


//                mstTradeOpponent002Mapper.insertFinCashCalcRule(dto);
//                mstTradeOpponent002Mapper.insertFinCashInfo(dto);
//                mstTradeOpponent002Mapper.insertFinRateInfo(dto);
            }
            if(StringUtil.isNotEmpty(entity.getBankTrustCode())){
                PaFinProduct paFinProduct = new PaFinProduct();
                BeanUtil.copyPropertiesIgnoreNull(paFinProduct, entity);
                paFinProductMapper.insertSelective(paFinProduct);

//                mstTradeOpponent002Mapper.insertPaFinProduct(entity);
            }
        }

    }

    /**
     * 删除
     * @param entity 交易对手实体类
     * @see
     * @since 1.0
     */
    public void deleteCounterInfo(MstTradeOpponentDto entity){
        basicMstTradeOpponent001Mapper.deleteCounterAcct(entity);


//      mstTradeOpponent001Mapper.deleteMstCounterParty(entity);
      mstCounterPartyMapper.deleteByPrimaryKey(entity.getCounterId());
      if(DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())){
          basicMstTradeOpponent001Mapper.deleteFinProductAcct(entity);
          basicMstTradeOpponent001Mapper.deleteFinCashCalcRule(entity);
          basicMstTradeOpponent001Mapper.deleteFinCashInfo(entity);
          basicMstTradeOpponent001Mapper.deleteFinCashPlan(entity);

//          mstTradeOpponent001Mapper.deleteFinProduct(entity);
          finProductMapper.deleteByPrimaryKey(entity.getFinprodId());
//          mstTradeOpponent001Mapper.deleteRepAssetMngPlan(entity);
          repAssetMngPlanMapper.deleteByPrimaryKey(entity.getFinprodId());
          FinProductTypeKey finProductTypeKey = new FinProductTypeKey();
          finProductTypeKey.setFinprodId(entity.getFinprodId());
          finProductTypeKey.setBranch(entity.getBranch());
          finProductTypeMapper.deleteByPrimaryKey(finProductTypeKey);
//          mstTradeOpponent001Mapper.deleteFinProductType(entity);

          if(StringUtil.isNotEmpty(entity.getBankTrustCode())){
//              mstTradeOpponent001Mapper.deletePaFinProduct(dto);
              paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
          }
      }
    }
    /**
     * @param entity 交易对手实体类
     * @see
     * @since 1.0
     */
    public void insertAount(MstTradeOpponentDto entity) {
        mstCounterPartyMapper.insertSelective(entity);
        List<StlCapacctDto> dtos = entity.getSelectStlCapacct();
        if(dtos!=null){
            for (StlCapacctDto dto : dtos) {
            	if(!dto.getAcctNo().equals("_") && !dto.getAcctNo().equals("_undefined")){
                StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
                BeanUtil.copyPropertiesIgnoreNull(stlFinProductAcct, entity);
                dto.setCounterId(entity.getCounterId());
                String[] noAndCcy = dto.getAcctNo().split("_");
                dto.setAcctNo(noAndCcy[0]);
                dto.setCcy(noAndCcy[1]);
                if(StringUtil.equals(DictCodeListExt1.交易对手类型_通道计划.getValue(), entity.getCounterType())){
                    stlFinProductAcct.setAcctNo(dto.getAcctNo());
                    stlFinProductAcct.setCcy(dto.getCcy());
                    stlFinProductAcct.setAcctType(dto.getAccountType());
                    stlFinProductAcct.setFinprodType2(entity.getFinprodType());
                    stlFinProductAcct.setFinprodType("");
                    stlFinProductAcctMapper.insertSelective(stlFinProductAcct);
                }

                MstCounterAcct mstCounterAcct = new MstCounterAcct();
                BeanUtil.copyPropertiesIgnoreNull(mstCounterAcct, dto);
                mstCounterAcct.setAcctType(dto.getAccountType());
                mstCounterAcctMapper.insertSelective(mstCounterAcct);
//                mstTradeOpponent002Mapper.insertCounterAcct(dto);
            }
            }
        }
    }
    /**
     * 查找交易对手信息
     * @param map 交易对手代码
     * @return 交易对手实体类
     * @see
     * @since 1.0
     */
    public MstTradeOpponentDto selectCounterInfo(Map<String, Object> map) {
    	map.put("SVR_YES_OR_NO_YES", DictCodeList.是否_中债_是.getValue());
    	map.put("SVR_YES_OR_NO_NO", DictCodeList.是否_中债_否.getValue());
    	map.put("YES_OR_NO_YES", DictCodeList.是否_是.getValue());
    	map.put("YES_OR_NO_NO", DictCodeList.是否_否.getValue());
        return basicMstTradeOpponent002Mapper.selectCounterInfo(map);
    }
    /**
     * 交易对手账号信息
     * @param map 交易对手账号
     * @return 交易对手账号实体类
     * @see
     * @since 1.0
     */
    public List<StlCapacctDto> selectStlCapacctInfo(Map<String, Object> map) {
        return basicMstTradeOpponent002Mapper.selectStlCapacctInfo(map);
    }
    /**
     *
     * 现金流信息
     * @param map 现金流代码
     * @return 现金流实体类集合
     * @see
     * @since 1.0
     */
    public List<FinCashDto> selectFinCashInfo(Map<String, Object> map) {
        return basicMstTradeOpponent002Mapper.selectFinCashInfo(map);
    }

    public int checkCounterId(String counterId){
        return basicMstTradeOpponent002Mapper.checkCounterId(counterId);
    }
    //检查交易对手名字是否重复
    public List<String> checkcounterName(Map<String,Object> map){
        return basicMstTradeOpponent002Mapper.checkcounterName(map);
    }
    public int checkCounterName(String linkId){
        return basicMstTradeOpponent002Mapper.checkCounterName(linkId);
    }

    /**
     * 校验交易对手名字 是否重复
     * 重写方法  联社模式下会走拦截器。
     * @param linkId
     * @return
     */
    public int checkCounterName1(String counterName){
    	List<String> list =  basicMstTradeOpponent002Mapper.checkCounterName1(counterName);
        if(list == null){
        	return 0;
        }
        return list.size();
    }

    /**
     * 获取交易对手账户信息（组合资金账户）
     * @param map 请求参数
     * @return 账户信息
     * @see
     * @since 1.0
     */
    public List<StlCapacctInfo> getCountInfo(Map<String,Object> map){
        return basicMstTradeOpponent002Mapper.getCountInfo(map);
    }
    /**
     * 加密算法
     * @param plainText 加密文件
     * @return 加密后的文件
     * @see
     * @since 1.0
     */
//    public static String md5(String plainText) {
//        byte[] secretBytes = null;
//        try {
//            secretBytes = MessageDigest.getInstance("md5").digest(
//                    plainText.getBytes());
//        }
//        catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("没有md5这个算法！");
//        }
//        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
//        // 如果生成数字未满32位，需要前面补0
//        for (int i = 0; i < 32 - md5code.length(); i++) {
//            md5code = "0" + md5code;
//        }
//        return md5code;
//    }

    /**
     * 查询交易对手是否使用该资金账户做过交易
     * @param entity
     * @return
     */
    public int isExistHistoryDeal(Map<String,Object> map){
    	return basicMstTradeOpponent002Mapper.isExistHistoryDeal(map);
    }
}

/*
 * 文件名：PrdMstTradeOpponentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.PrdMstCaptialAccountMapper;
import com.joyintech.service.product.common.dao.PrdMstTradeOpponent001Mapper;
import com.joyintech.service.product.common.dto.FinCashDto;
import com.joyintech.service.product.common.dto.MstCaptialAccountDto;
import com.joyintech.service.product.common.dto.MstTradeOpponentDto;
import com.joyintech.service.product.common.dto.StlCapacctDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;

/**
 * 交易对手管理Service
 *
 * @author fanshuanghu
 * @version 1.0 2018年8月23日
 * @see PrdMstTradeOpponentService
 * @since 1.0
 */
@Service("prdMstTradeOpponentService")
@Transactional(rollbackFor=Exception.class)
public class PrdMstTradeOpponentService extends BaseService<MstTradeOpponentDto>{
//
//    /**
//     * 交易对手表mapper
//     */
//    @Autowired
//    private MstTradeOpponent002Mapper mstTradeOpponent002Mapper;
    /**
     * 交易对手mapper
     */
    @Autowired
    private PrdMstTradeOpponent001Mapper prdMstTradeOpponent001Mapper;

    /**
     * 现金流mapper
     */
    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;

    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

//    @Autowired
//    private FinCashPlanMapper finCashPlanMapper;

    @Autowired
    private FinProductMapper finProductMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private RepAssetMngPlanMapper repAssetMngPlanMapper;

    @Autowired
    private MstCounterAcctMapper mstCounterAcctMapper;

    @Autowired
    private MstCounterSecurityAcctMapper mstCounterSecurityAcctMapper;

    @Autowired
    private PaFinProductMapper paFinProductMapper;

    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    @Autowired
    private StlFinProductAcctMapper stlFinProductAcctMapper;

    /**
     * 资金账户管理mapper(自动生成)
     */
    @Autowired
    private StlCapacctInfoMapper stlCapacctInfoMapper;

    /**
     * 资金账户管理mapper
     */
    @Autowired
    private PrdMstCaptialAccountMapper prdMstCaptialAccountMapper;
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

        //add by yejiejie 2019-4-2 删除资金账户表中的信息
    	if(StringUtil.equals("1", entity.getFlage())){
        List<StlCapacctDto> dtos = entity.getSelectStlCapacct();
        for (StlCapacctDto dto : dtos) {
        	//add by yejiejie 2019-4-8 start
        	//先根据交易对手id查找对应的资金账户
        	MstCounterAcct mstCounterAcct2 = new MstCounterAcct();
        	mstCounterAcct2.setCounterId(entity.getCounterId());
        	List<MstCounterAcct> mstCounterAcctList1 = prdMstCaptialAccountMapper.selectMstCounterAcctByCounterId(mstCounterAcct2);
        	for(int i=0;i<mstCounterAcctList1.size();i++){
        		//先判断该账户是否跟其他的交易对手绑定,没绑定就可以删除
            	MstCounterAcct mstCounterAcct1 = new MstCounterAcct();
            	mstCounterAcct1.setAcctNo(mstCounterAcctList1.get(i).getAcctNo());
            	mstCounterAcct1.setCcy(mstCounterAcctList1.get(i).getCcy());
            	mstCounterAcct1.setAcctType(mstCounterAcctList1.get(i).getAcctType());
            	List<MstCounterAcct> mstCounterAcctList = prdMstCaptialAccountMapper.selectMstCounterAcct(mstCounterAcct1);
            	if(mstCounterAcctList != null){
            		if(mstCounterAcctList.size()<2){
            			//需要删除
            			//删除资金列表中的资金账户数据
                    	Map<String, Object> map = new HashMap<String, Object>();
                        map.put("acctNo",mstCounterAcctList1.get(i).getAcctNo());
                        //map.put("acctType",mstCounterAcctList1.get(i).getAcctType());
                        map.put("ccy",mstCounterAcctList1.get(i).getCcy());
                        List<MstCaptialAccountDto> list = prdMstCaptialAccountMapper.selectStlCapAcctInfo(map);
                        if(list != null){
                        	//存在就删除
                        	for (MstCaptialAccountDto mstCaptialAccountDto : list) {
                        		if(StringUtil.isNotEmpty(mstCaptialAccountDto.getBusiAcctNo())
                        				&& StringUtil.isNotEmpty(mstCaptialAccountDto.getLargePayno())){
                        			prdMstCaptialAccountMapper.deleteByPrimaryKey1(mstCaptialAccountDto);
                        		}
                        	}
                        }
            		}
            	}
        	}
        }
    	}
        //add by yejiejie 2019-4-2 删除资金账户表中的信息

        prdMstTradeOpponent001Mapper.deleteCounterAcct(entity);
        //add by yejiejie 2019-2-26 删除证券账户
        prdMstTradeOpponent001Mapper.deleteCounterSecurityAcct(entity);

//      mstTradeOpponent001Mapper.deleteMstCounterParty(entity);
      mstCounterPartyMapper.deleteByPrimaryKey(entity.getCounterId());
      if(DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())){
          prdMstTradeOpponent001Mapper.deleteFinProductAcct(entity);
          prdMstTradeOpponent001Mapper.deleteFinCashCalcRule(entity);
          prdMstTradeOpponent001Mapper.deleteFinCashInfo(entity);
          prdMstTradeOpponent001Mapper.deleteFinCashPlan(entity);

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
                StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
                BeanUtil.copyPropertiesIgnoreNull(stlFinProductAcct, entity);
                dto.setCounterId(entity.getCounterId());
                String[] noAndCcy = dto.getAcctNo().split("_");
                dto.setAcctNo(noAndCcy[0]);
                if(StringUtil.equals("", noAndCcy[1]) || StringUtil.equals("undefined", noAndCcy[1])){//add by yejiejie 2019-4-2
                	dto.setCcy("CNY");
                }else{
                	dto.setCcy(noAndCcy[1]);
                }
              if(StringUtil.equals(DictCodeListExt1.交易对手类型_通道计划.getValue(), entity.getCounterType())){
                stlFinProductAcct.setAcctNo(dto.getAcctNo());
                if(StringUtil.equals("", dto.getCcy()) || StringUtil.equals("undefined", dto.getCcy())){//add by yejiejie 2019-4-2
                	stlFinProductAcct.setCcy(dto.getCcy());
                }else{
                	stlFinProductAcct.setCcy("CNY");
                }
                stlFinProductAcct.setAcctType(dto.getAccountType());
                stlFinProductAcct.setFinprodType2(entity.getFinprodType());
                stlFinProductAcct.setFinprodType("");
                if(stlFinProductAcct.getFinprodId() != null && !"".equals(stlFinProductAcct.getFinprodId())){
                	stlFinProductAcctMapper.insertSelective(stlFinProductAcct);
                }
				}

                MstCounterAcct mstCounterAcct = new MstCounterAcct();
                BeanUtil.copyPropertiesIgnoreNull(mstCounterAcct, dto);
                mstCounterAcct.setAcctType(dto.getAccountType());
                mstCounterAcctMapper.insertSelective(mstCounterAcct);
//                mstTradeOpponent002Mapper.insertCounterAcct(dto);

                //add by yejiejie 2019-4-2 把资金账户插入到表中 start
                if(StringUtil.equals("1", entity.getFlage())){
	                //开户网点/大额支付行号+帐号+币种数据不能重复(BusiAcctNo,LargePayno,ccy);
	                Map<String, Object> map = new HashMap<String, Object>();
	                map.put("acctName", "");
	                map.put("acctNo",dto.getAcctNo());
	                map.put("largePayno",dto.getLargePayno());
	                map.put("ccy",dto.getCcy());
	                List<MstCaptialAccountDto> list = prdMstCaptialAccountMapper.selectStlCapAcctInfo(map);
	                if(list != null){
		                if(list.size() == 0){
			                MstCaptialAccountDto mstCaptialAccountDto = new MstCaptialAccountDto();
			                BeanUtil.copyPropertiesIgnoreNull(mstCaptialAccountDto, dto);
			                stlCapacctInfoMapper.insert(mstCaptialAccountDto);
		                }
	                }
                }
              }
                //add by yejiejie 2019-4-2 把资金账户插入到表中 end
//            }
        }
        //add by yejiejie 2019-1-18 start 插入证券账户
        List<MstCounterSecurityAcct> securityDtos = entity.getSelectMstCounterSecurityAcct();
        if(securityDtos != null){
        	for(MstCounterSecurityAcct securityDto : securityDtos){
            	securityDto.setCounterId(entity.getCounterId());
            	mstCounterSecurityAcctMapper.insertSelective(securityDto);
            	//insertCounterSecurityAcct(securityDto);
            }
        }
        //add by yejiejie 2019-1-18 end 插入证券账户

    }
    /**
     * 查找交易对手信息
     * @param map 交易对手代码
     * @return 交易对手实体类
     * @see
     * @since 1.0
     */
//    public MstTradeOpponentDto selectCounterInfo(Map<String, Object> map) {
//        return mstTradeOpponent002Mapper.selectCounterInfo(map);
//    }
    /**
     * 交易对手账号信息
     * @param map 交易对手账号
     * @return 交易对手账号实体类
     * @see
     * @since 1.0
     */
//    public List<StlCapacctDto> selectStlCapacctInfo(Map<String, Object> map) {
//        return mstTradeOpponent002Mapper.selectStlCapacctInfo(map);
//    }
    /**
     * 交易对手证券账号信息
     * @param map 交易对手账号
     * @return 交易对手证券账号实体类
     * @see
     * @since 1.0
     */
//    public List<MstCounterSecurityAcct> selectSecurityAcctByCounterId(Map<String, Object> map) {
//        return mstTradeOpponent002Mapper.selectSecurityAcctByCounterId(map);
//    }
    /**
     *
     * 现金流信息
     * @param map 现金流代码
     * @return 现金流实体类集合
     * @see
     * @since 1.0
     */
//    public List<FinCashDto> selectFinCashInfo(Map<String, Object> map) {
//        return mstTradeOpponent002Mapper.selectFinCashInfo(map);
//    }

//    public int checkCounterId(String counterId){
//        return mstTradeOpponent002Mapper.checkCounterId(counterId);
//    }
//    public int checkCounterName(String counterName){
//        return mstTradeOpponent002Mapper.checkCounterName(counterName);
//    }

    /**
     * 获取交易对手账户信息（组合资金账户）
     * @param map 请求参数
     * @return 账户信息
     * @see
     * @since 1.0
     */
//    public List<StlCapacctInfo> getCountInfo(Map<String,Object> map){
//        return mstTradeOpponent002Mapper.getCountInfo(map);
//    }
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

}

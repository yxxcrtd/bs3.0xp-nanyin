/*
 * 文件名：FinNCProductBuild001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.finncproductbuild.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.finncproductbuild.dao.FinNCProductBuild001Mapper;
import com.joyintech.service.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.service.valuation.finncproductbuild.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.tams.common.base.dao.BokAcctLayeringInfoMapper;
import com.joyintech.tams.common.base.dao.BokAcctSubjectInfoMapper;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.BokBooksetExtMapper;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleDetailMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinFloatCountingRuleMapper;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.PrdValParamMapper;
import com.joyintech.tams.common.base.dao.StlFinProductAcctMapper;
import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.BooksetInitResult;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherParamsDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ValFinProductExt;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 净值型、货币型产品信息Service
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild001Service
 * @since 1.0
 */
@Service(value="serviceFinNCProductBuild001Service")
public class FinNCProductBuild001Service extends CommonExportService<FinNCProductBuild001Dto> implements BaseProcessService<FinNCProductBuild001Dto>{
	
    /**
     * FinNCProductBuild001Mapper
     */
    @Autowired
    private FinNCProductBuild001Mapper mapper;
    
    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    /**
     * 标的类金融产品表
     */
    @Autowired
    private FinProductMapper finProductMapper;
    
    /**
     * 标的类金融产品附属表
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    
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
     * 现金流信息表
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    
    /**
     * 业绩报酬计提规则
     */
    @Autowired
    private FinFloatCountingRuleMapper finFloatCountingRuleMapper;
    
    /**
     * 业绩报酬计算规则
     */
    @Autowired
    private FinFloatCalcRuleMapper finFloatCalcRuleMapper;
    
    /**
     * 业绩报酬计算规则明细
     */
    @Autowired
    private FinFloatCalcRuleDetailMapper finFloatCalcRuleDetailMapper;
    
    /**
     * 业绩基准信息
     */
    @Autowired
    private FinBaseRuleInfoMapper finBaseRuleInfoMapper;
    
    /**
     * 理财产品估值参数
     */
    @Autowired
    private PrdValParamMapper prdValParamMapper;
    
    /**
     * 业绩报酬计提计划
     */
    @Autowired
    private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
    
    @Autowired
    private BokBooksetMapper bokBooksetMapper;
    
    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;
    
    @Autowired
    private BokBooksetExtMapper bokBooksetExtMapper;
    
    @Autowired
    private BokAcctSubjectInfoMapper bokAcctSubjectInfoMapper;
    
    @Autowired
    private BokAcctLayeringInfoMapper bokAcctLayeringInfoMapper;
    
    @Autowired
    private StlFinProductAcctMapper stlFinProductAcctMapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<FinNCProductBuild001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
        return super.queryForPage(page,length,()->this.findAll(params));
    }
    
    /**
     * 根据条件获取信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public FinNCProductBuild001Dto findByFinprodId(Map<String, Object> params) {
        return this.findAll(params).get(0);
    }

    /**
     * 下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page,int length,Map<String,Object> map) {
        return super.queryForPageForSelectReturn(page,length,()->mapper.getFinprodId(map));
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public FinNCProductBuild001Dto queryProductByfinprodId(Map<String, Object> map) {
        String finprodId = (String)map.get("finprodId");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        map.put("finprodIdArr", finprodIdArr);
        List<FinNCProductBuild001Dto> list=mapper.queryPageLists(map);
        FinNCProductBuild001Dto dto=new FinNCProductBuild001Dto();
        if(list.size()>0){
            dto=list.get(0);
        }
        return dto;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoFeeDto> queryFeeList(Map<String, Object> pageParam) {
    	List<ValProductInfoFeeDto> resurt = new ArrayList<>();
    	String finprodId = (String) pageParam.get("finprodId");
    	if(finprodId != "" && finprodId != null){
    		resurt = mapper.queryFeeList(pageParam);
    	}
        return resurt;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<FinNCProductBuild001Dto> findAll(Map<String, Object> params){
      //获取获取理财产品类型
        String profitType = (String)params.get("profitType");
        String raiseType = (String)params.get("raiseType");
        String operationType = (String)params.get("operationType");
        String investType = (String)params.get("investType");
        String finprodId = (String)params.get("finprodId");
        String[] profitTypeArr = null;
        if(StringUtil.isNotEmpty(profitType)){
            profitTypeArr = profitType.split(",");
        }
        String[] raiseTypeArr = null;
        if(StringUtil.isNotEmpty(raiseType)){
            raiseTypeArr = raiseType.split(",");
        }
        String[] operationTypeArr = null;
        if(StringUtil.isNotEmpty(operationType)){
        	operationTypeArr = operationType.split(",");
        }
        String[] investTypeArr = null;
        if(StringUtil.isNotEmpty(investType)){
            investTypeArr = investType.split(",");
        }
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        params.put("profitTypeArr", profitTypeArr);
        params.put("raiseTypeArr", raiseTypeArr);
        params.put("operationTypeArr", operationTypeArr);
        params.put("investTypeArr", investTypeArr);
        params.put("finprodIdArr", finprodIdArr);
        params.put("finprodTypeF16", DictCodeListExt1.金融产品类型_理财产品.getValue());
        params.put("finprodTypeF55", DictCodeListExt3.金融产品类型_券商集合计划.getValue());
        params.put("profitType01", DictCodeList.收益类型_净值型.getValue());
        params.put("profitType03", DictCodeList.收益类型_货币型.getValue());
        return mapper.queryPageList(params);
    }

    /**
     * 查询资金账户信息
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getFinprodAcct(Map<String, Object> map) {
    	List<ValProductInfoAcctDto> resurt = new ArrayList<>();
    	String finprodId = (String) map.get("finprodId");
    	String acctName = (String) map.get("acctName");
    	if((finprodId != "" && finprodId != null) || (acctName != "" && acctName != null)){
    		resurt = mapper.getFinprodAcct(map);
    	}
        return resurt;
    }
    
    /**
     * 查询资金账户名称列表
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctName(Map<String, Object> map) {
        return mapper.getAcctName(map);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getSecAccount(Map<String, Object> map) {
        return mapper.getSecAccount(map);
    }
    
    /**
     * 查看费用调节 - 分页
     * @param pageParam
     * @return
     * @throws Exception 
     */
	public List<ValProductInfoAcctDto> findBankdesc(Map<String, Object> map) {
  	     return mapper.findBankdesc(map);
      
  }    
    
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList(Map<String, Object> pageParam) {
		return mapper.queryfloatFeeList(pageParam);
	}

    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList1(Map<String, Object> pageParam) {
    	List<ValProductInfoFeeDto> resurt = new ArrayList<>();
    	String finprodId = (String) pageParam.get("finprodId");
    	if(finprodId != "" && finprodId != null){
    		resurt = mapper.queryfloatFeeList1(pageParam);
    	}
        return resurt;
	}
	
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList2(Map<String, Object> pageParam) {
    	List<ValProductInfoFeeDto> resurt = new ArrayList<>();
    	String finprodId = (String) pageParam.get("finprodId");
    	if(finprodId != "" && finprodId != null){
    		resurt = mapper.queryfloatFeeList2(pageParam);
    	}
        return resurt;
	}
	
	/**
     * 查询账套配置表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public List<BokBooksetExt> queryBooksetExt(Map<String, Object> map){
        return mapper.queryBooksetExt(map);
    }
    
    /**
     * 通过主键 查询账套配置表
     * <一句话功能简述>
     * @param finprodId
     * @param paramCode
     * @return
     */
	public List<BokBooksetExt>  queryBooksetExtByKey(Map<String, Object> map) {
		return mapper.queryBooksetExtByKey(map);
	}
	
    /**
     * 删除check
     * <一句话功能简述>
     * @param finprodId
     * @return List
     */
	public List<FinNCProductBuild001Dto> deleteCheck(String finprodId) {
		return mapper.deleteCheck(finprodId);
	}
	
	@Override
	public void doComplete(FinNCProductBuild001Dto entity, OperationType type) {
		if(OperationType.NEW.equals(type)){
			entity.setFinprodId(entity.getFinprodType()+"_"+entity.getFinprodId());
		}
		if(OperationType.DELETE.equals(type)) {
	    	  finProductMapper.deleteByPrimaryKey(entity.getFinprodId());
	    	  finProductAddMapper.deleteByPrimaryKey(entity.getFinprodId());
	      }
		else{
			//标的类金融产品表;
			FinProduct finProduct = this.getFinProduct(entity);
			//标的类金融产品附属表
			FinProductAdd finProductAdd = this.getFinProductAdd(entity);
			//标的类金融产品附属表
			PrdValParam prdValParam = this.getPrdValParam(entity);
			//固定费用
			this.getFixFee(entity);
//			//浮动费用
//			this.getFloatFee(entity);
			//账套初始化
			ValFinProductExt valFinProductExt = new ValFinProductExt();
			valFinProductExt.setIsCalc(entity.getIsCountingCurInt());
			OtherParamsDto otherParamsDto = new OtherParamsDto();
			otherParamsDto.setTmp_booksetId("00000000000000000002");
			Map<String,String> sysParams = new HashMap<>();
			sysParams.put("SHARE_DEC_DIGITS", entity.getShareDecDigits());
			sysParams.put("SHARE_DEC_TYPE", entity.getShareDecType());
			otherParamsDto.setSysParams(sysParams);
			BooksetInitResult result = interfCommonService.booksetInit(finProduct,valFinProductExt,otherParamsDto);
			BokBookset resValBokBookset = result.getValBokBookset();
			BokBooksetDate resValBokBooksetDate = result.getValBokBooksetDate();
			List<BokBooksetExt> resValBokBooksetExt = result.getListValBokBooksetExt();
			BokAcctSubjectInfo resValBokAcctSubjectInfo = result.getAcctSubjectInfo();
			BokAcctLayeringInfo bokAcctLayeringInfo = result.getAcctLayeringInfo();
			
			//浮动费用
			if(entity.getIsAccrFloManageFee().equals(DictCodeListExt1.是否_是.getValue())){
				this.getFloatFee(entity, type);
			}
			
			//资金账户
			this.insertAccount(entity);

			if(OperationType.NEW.equals(type)){
				finProductMapper.insert(finProduct);
				finProductAddMapper.insert(finProductAdd);
				prdValParamMapper.insert(prdValParam);
				
				bokBooksetMapper.insert(resValBokBookset);
				bokBooksetDateMapper.insert(resValBokBooksetDate);
				for(BokBooksetExt item : resValBokBooksetExt){
					bokBooksetExtMapper.insert(item);
				}
				bokAcctSubjectInfoMapper.insert(resValBokAcctSubjectInfo);
				bokAcctLayeringInfoMapper.insert(bokAcctLayeringInfo);
				
			}else if(OperationType.MODIFY.equals(type)){
				finProductMapper.updateByPrimaryKey(finProduct);
				finProductAddMapper.updateByPrimaryKey(finProductAdd);
				prdValParamMapper.updateByPrimaryKey(prdValParam);
	      }
		}
	}
	
    /**
     * 标的类金融产品表
     * <一句话功能简述>
     * @param entity
     * @return finProduct
     */
	public FinProduct getFinProduct(FinNCProductBuild001Dto entity) {
		FinProduct finProduct = new FinProduct();
		finProduct.setFinprodId(entity.getFinprodId());
		finProduct.setFinprodType(entity.getFinprodType());
		finProduct.setFinprodAbbr(entity.getFinprodAbbr());
		finProduct.setFinprodName(entity.getFinprodName());
		finProduct.setIssueType(entity.getIssueType());
		finProduct.setProfitType(entity.getProfitType());
		finProduct.setVdate(DateUtil.parse(entity.getVdate()));
		finProduct.setMdate(DateUtil.parse(entity.getMdate()));
		finProduct.setIsSus(entity.getIsSus());
		finProduct.setActmdate(DateUtil.parse(entity.getActmdate()));
		finProduct.setLiquidationDate(DateUtil.parse(entity.getLiquidationDate()));
		finProduct.setTermDays(Long.parseLong(entity.getTerm()));
		finProduct.setCcy(entity.getCcy());
		finProduct.setCalendarId("03");
		finProduct.setTrusteeId(entity.getTrusteeId());
		finProduct.setOperationType(entity.getOperationType());
		finProduct.setpFinprodId(entity.getpFinprodId());
		return finProduct;
	}
	
    /**
     * 理财产品估值参数
     * <一句话功能简述>
     * @param entity
     * @return prdValParam
     */
	public PrdValParam getPrdValParam(FinNCProductBuild001Dto entity) {
		//理财产品估值参数
		PrdValParam prdValParam = new PrdValParam();
		prdValParam.setFinprodId(entity.getFinprodId());
		if(entity.getMilionProfitDec() != "" && entity.getMilionProfitDec() != null){
			prdValParam.setMilionProfitDec(Long.parseLong(entity.getMilionProfitDec()));
		}
		prdValParam.setMilionProfitDecMode(entity.getMilionProfitDecMode());
		if(entity.getYearYieldDec() != "" && entity.getYearYieldDec() != null){
			prdValParam.setYearYieldDec(Long.parseLong(entity.getYearYieldDec()));
		}
		prdValParam.setYearYieldDecMode(entity.getYearYieldDecMode());
		if(entity.getUnitvalueDec() != "" && entity.getUnitvalueDec() != null){
			prdValParam.setUnitvalueDec(Long.parseLong(entity.getUnitvalueDec()));
		}
		prdValParam.setUnitvalueMode(entity.getUnitvalueMode());
		prdValParam.setIsCountingCurInt(entity.getIsCountingCurInt());
		return prdValParam;
	}
	
    /**
     * 标的类金融产品表附属表
     * <一句话功能简述>
     * @param entity
     * @return finProductAdd
     */
	public FinProductAdd getFinProductAdd(FinNCProductBuild001Dto entity) {
		FinProductAdd finProductAdd = new FinProductAdd();
		finProductAdd.setFinprodId(entity.getFinprodId());
		finProductAdd.setProdRegistCode(entity.getProdRegistCode());
		finProductAdd.setProfitFlag(entity.getProfitFlag());
		finProductAdd.setIsCycle(entity.getIsCycle());
		finProductAdd.setIsReconciliation(entity.getIsReconciliation());
		finProductAdd.setCreateOrg(entity.getCreateOrg());
		finProductAdd.setValOrg(entity.getValOrg());
		return finProductAdd;
	}
	
    /**
     * 固定费用
     * <一句话功能简述>
     * @param entity
     */
	public void getFixFee(FinNCProductBuild001Dto entity) {
		List<ValProductInfoFeeDto> list = entity.getListValProductInfoFeeDto_GD();
		for(ValProductInfoFeeDto item : list){
			//现金流信息表
			FinCashInfo finCashInfo = new FinCashInfo();
			String cashId = FinNCProductBuild001Service.generateCashFlowCode(entity.getFinprodId(), 
	                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
	                InterfCommonConstant.分支序号_默认,  item.getCashType2());
			finCashInfo.setCashId(cashId);
			finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
			finCashInfo.setCashType2(item.getCashType2());
			finCashInfo.setFinprodId(entity.getFinprodId());
			finCashInfo.setFinprodType(entity.getFinprodType());
			finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
			//现金流计算规则表
			FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
			finCashCalcRule.setCashId(cashId);
			finCashCalcRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
			BigDecimal num= new BigDecimal("100"); 
			finCashCalcRule.setYield(item.getYield().divide(num));
			finCashCalcRule.setBasis(item.getBasis());
			finCashCalcRule.setBaseType(item.getBaseType());
			finCashCalcRule.setBaseDateType(item.getBaseDateType());
			finCashCalcRule.setFinprodId(entity.getFinprodId());
			finCashCalcRule.setFinprodType(entity.getFinprodType());
			finCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
			//现金流支付规则表
			FinCashPayRule finCashPayRule = new FinCashPayRule();
			finCashPayRule.setCashId(cashId);
			finCashPayRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
			finCashPayRule.setFinprodId(entity.getFinprodId());
			finCashPayRule.setFinprodType(entity.getFinprodType());
			finCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
			finCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
			//业绩报酬计提规则
			FinFloatCountingRule finFloatCountingRule = new FinFloatCountingRule();
			finFloatCountingRule.setCashId(cashId);
			finFloatCountingRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finFloatCountingRule.setfAccrDate(DateUtil.parse(item.getEffDate()));
			
			ValProductInfoFeeDto checkDto = mapper.findByCashId(cashId);
			if (checkDto != null) {
				finCashInfoMapper.updateByPrimaryKey(finCashInfo);
				finCashCalcRuleMapper.updateByPrimaryKey(finCashCalcRule);
				finCashPayRuleMapper.updateByPrimaryKey(finCashPayRule);
				finFloatCountingRuleMapper.updateByPrimaryKey(finFloatCountingRule);
			}else{
				finCashInfoMapper.insert(finCashInfo);
				finCashCalcRuleMapper.insert(finCashCalcRule);
				finCashPayRuleMapper.insert(finCashPayRule);
				finFloatCountingRuleMapper.insert(finFloatCountingRule);
			}
		}
	}
	
    /**
     * 浮动费用
     * <一句话功能简述>
     * @param entity
     */
	public void getFloatFee(FinNCProductBuild001Dto entity, OperationType type) {
		List<ValProductInfoFeeDto> list = entity.getListValProductInfoFeeDto_FD2();
		List<ValProductInfoFeeDto> list1 = entity.getListValProductInfoFeeDto_FD1();
		for(ValProductInfoFeeDto item : list){
			//现金流信息表
			FinCashInfo finCashInfo = new FinCashInfo();
			String cashId = FinNCProductBuild001Service.generateCashFlowCode(entity.getFinprodId(), 
	                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
	                InterfCommonConstant.分支序号_默认, 
	                DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
			finCashInfo.setCashId(cashId);
			finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
			finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
			finCashInfo.setFinprodId(entity.getFinprodId());
			finCashInfo.setFinprodType(entity.getFinprodType());
			finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
			//现金流支付规则表
			FinCashPayRule finCashPayRule = new FinCashPayRule();
			finCashPayRule.setCashId(cashId);
			finCashPayRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
			finCashPayRule.setFinprodId(entity.getFinprodId());
			finCashPayRule.setFinprodType(entity.getFinprodType());
			finCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
			finCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
			//业绩基准
			FinBaseRuleInfo finBaseRuleInfo = new FinBaseRuleInfo();
			finBaseRuleInfo.setBaseRuleId(entity.getFinprodId());
			finBaseRuleInfo.setEffDate(DateUtil.parse(entity.getVdate()));
			finBaseRuleInfo.setResetType(entity.getActRule());
			finBaseRuleInfo.setFinprodId(entity.getFinprodId());
			finBaseRuleInfo.setBranch(InterfCommonConstant.分支序号_默认);
			//业绩报酬计算规则
			FinFloatCalcRule finFloatCalcRule = new FinFloatCalcRule();
			finFloatCalcRule.setCashId(cashId);
			finFloatCalcRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finFloatCalcRule.setBaseType(entity.getBaseType());
			finFloatCalcRule.setActRule(entity.getActRule());
			finFloatCalcRule.setAstVdateType(entity.getActVdateType());
			//业绩报酬计提规则
			FinFloatCountingRule finFloatCountingRule = new FinFloatCountingRule();
			finFloatCountingRule.setCashId(cashId);
			finFloatCountingRule.setEffDate(DateUtil.parse(entity.getVdate()));
			finFloatCountingRule.setAccrCycleUnit(entity.getAccrCycle());
//			finFloatCountingRule.setIntervalRule(entity.getAccrCycle());
			finFloatCountingRule.setfAccrDate(DateUtil.parse(entity.getVdate()));
			finFloatCountingRule.seteAccrDate(DateUtil.parse(entity.getEndCalculaDate()));
			//业绩报酬计算规则明细
			FinFloatCalcRuleDetail finFloatCalcRuleDetail = new FinFloatCalcRuleDetail();
			finFloatCalcRuleDetail.setCashId(cashId);
			finFloatCalcRuleDetail.setEffDate(DateUtil.parse(entity.getVdate()));
			finFloatCalcRuleDetail.setLev(Long.parseLong(item.getBasicLevel()));
			finFloatCalcRuleDetail.setBaseRuleId(item.getBaseRuleId());
			finFloatCalcRuleDetail.setCoefficient(item.getCoefficient());
			finFloatCalcRuleDetail.setSpreadRate(item.getSpread());
			BigDecimal num= new BigDecimal("100"); 
			finFloatCalcRuleDetail.setChargeRatio(item.getBankRatio().divide(num));
			
			if(OperationType.NEW.equals(type)){
				finCashInfoMapper.insert(finCashInfo);
				finCashPayRuleMapper.insert(finCashPayRule);
				finBaseRuleInfoMapper.insert(finBaseRuleInfo);
				finFloatCalcRuleMapper.insert(finFloatCalcRule);
				finFloatCountingRuleMapper.insert(finFloatCountingRule);
				finFloatCalcRuleDetailMapper.insert(finFloatCalcRuleDetail);
			}else if(OperationType.MODIFY.equals(type)){
				finCashInfoMapper.updateByPrimaryKey(finCashInfo);
				finCashPayRuleMapper.updateByPrimaryKey(finCashPayRule);
				finBaseRuleInfoMapper.updateByPrimaryKey(finBaseRuleInfo);
				finFloatCalcRuleMapper.updateByPrimaryKey(finFloatCalcRule);
				finFloatCountingRuleMapper.updateByPrimaryKey(finFloatCountingRule);
				finFloatCalcRuleDetailMapper.updateByPrimaryKey(finFloatCalcRuleDetail);
	      }
		}
		for(ValProductInfoFeeDto item : list1){
			String cashId = FinNCProductBuild001Service.generateCashFlowCode(entity.getFinprodId(), 
	                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
	                InterfCommonConstant.分支序号_默认, 
	                DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
			//业绩报酬计提计划
			FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
			finFloatAccrualSche.setCashId(cashId);
			finFloatAccrualSche.setAccrDate(DateUtil.parse(item.getJtDate()));
			finFloatAccrualSche.setAccrStrDate(DateUtil.parse(item.getJtStrDate()));
			finFloatAccrualSche.setAccrEndDate(DateUtil.parse(item.getJtEndDate()));
			
			ValProductInfoFeeDto checkDto = mapper.checkForUpdate(cashId,item.getJtDate());
			if (checkDto != null) {
				finFloatAccrualScheMapper.updateByPrimaryKey(finFloatAccrualSche);
			}else{
				finFloatAccrualScheMapper.insert(finFloatAccrualSche);
			}
		}
	}
	
    /**
     * 资金账户
     * <一句话功能简述>
     * @param entity
     */
	public void insertAccount(FinNCProductBuild001Dto entity) {
		List<ValProductInfoAcctDto> list = entity.getListValProductInfoAcctDto_ZJ();
		mapper.deleteAccount(entity.getFinprodId());
		for(ValProductInfoAcctDto item : list){
			//Stl_Fin_Product_Acct
			StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
			stlFinProductAcct.setFinprodId(entity.getFinprodId());
			stlFinProductAcct.setFinprodType(entity.getFinprodType());
			stlFinProductAcct.setAcctNo(item.getAcctNo());
			stlFinProductAcct.setCcy(item.getCcy());
			stlFinProductAcct.setAcctType(item.getAcctType());
			stlFinProductAcctMapper.insert(stlFinProductAcct);
		}
	}
	
    /**
     * 费率调整
     * <一句话功能简述>
     * @param dto
     */
	public void adjustRate(ValProductInfoFeeDto dto) {
		//现金流计算规则表
		FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
		String cashId = FinNCProductBuild001Service.generateCashFlowCode(dto.getFinprodId(), 
                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                InterfCommonConstant.分支序号_默认,  dto.getCashType2());
		finCashCalcRule.setCashId(cashId);
		finCashCalcRule.setEffDate(DateUtil.parse(dto.getEffDate()));
		finCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
		BigDecimal num= new BigDecimal("100"); 
		finCashCalcRule.setYield(dto.getYield().divide(num));
		finCashCalcRule.setBasis(dto.getBasis());
		finCashCalcRule.setBaseType(dto.getBaseType());
		finCashCalcRule.setBaseDateType(dto.getBaseDateType());
		finCashCalcRule.setFinprodId(dto.getFinprodId());
		finCashCalcRule.setFinprodType(dto.getFinprodType());
		finCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
		
		ValProductInfoFeeDto checkDto = mapper.adjustRate(cashId,dto.getEffDate());
		if (checkDto != null) {
			finCashCalcRuleMapper.updateByPrimaryKey(finCashCalcRule);
		}else{
			finCashCalcRuleMapper.insert(finCashCalcRule);
		}

	}
	
    /**
     * 现金流代码生成
     * @param busiNo 业务代码
     * @param busiType 业务类型
     * @param branch 分支序号
     * @param busiType2 现金流二级类型
     * @return 现金流代码
     */
    public static String generateCashFlowCode(String busiNo, String busiType, long branch,
                                              String busiType2) {
        final String sepChar = String.valueOf((char)256);

        String tmpStr = StringUtil.joinString(
            Arrays.asList(StringUtil.defaultString(busiNo), StringUtil.defaultString(busiType),
                String.valueOf(branch), StringUtil.defaultString(busiType2)),
            sepChar);

        return EncryptUtil.getInstance().md5(tmpStr);
    }
	
}

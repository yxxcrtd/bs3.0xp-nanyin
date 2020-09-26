package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.BokDetailIdMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductBarterMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
//import com.joyintech.tams.common.base.dao.TrdProductDealExtMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetailKey;
import com.joyintech.tams.common.base.entity.BokDealAccountingKey;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.BokDetailIdKey;
import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdFinProductBarterKey;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
//import com.joyintech.tams.common.base.entity.TrdProductDealExt;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dao.ValAssetReclassification001Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001ValidDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetReclassification001ValidService extends CommonExportService<ValAssetReclassification001ValidDto> implements BaseProcessService<ValAssetReclassification001ValidDto>{
	
	@Autowired
	private ValAssetReclassification001Mapper valAssetReclassification001Mapper;
	
	
	/**
	 * 金融产品交易
	 */
	@Autowired
	private TrdProductDealMapper trdProductDealMapper;
	
	/**
	 * 金融产品交易扩展表
	 */
	//一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
	@Autowired
	private TrdProductDealAddMapper trdProductDealExtMapper;
	
	/**
	 * 金融产品实物交易
	 */
	@Autowired
	private TrdFinProductBarterMapper trdFinProductBarterMapper;
	
	/**
	 * 入账信息表
	 */
	@Autowired
	private BokDealAccountingMapper bokDealAccountingMapper;
	
	/**
	 * 账务明细代码表
	 */
	@Autowired
	private BokDetailIdMapper bokDetailIdMapper;
	
	/**
	 * 入账账务明细表
	 */
	@Autowired
	private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;
	
	/**
	 * 账套
	 */
	@Autowired
	private BokBooksetMapper bokBooksetMapper;
	
	
	@Autowired
	private InterfCommonService interfCommonService;
	
		
	/**
	 * 
	 * 获取账套编号
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getBookSetId(){
	    return valAssetReclassification001Mapper.getBookSetId(Maps.newHashMap());
	}
	
	/**
	 * 
	 * 获取资产代码
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getFinProduct(){
        return valAssetReclassification001Mapper.getFinProduct(Maps.newHashMap());
    }
	
	
	/**
	 * 
	 * 界面查询
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<ValAssetReclassification001Dto> selectReclassList(Map<String, Object> params){
	    //获取界面的账套编号
	    if(StringUtil.isNotEmpty(params.get("booksetId"))){
	        params.put("booksets", params.get("booksetId").toString().split(","));
	    }
	    //获取界面的资产代码
        if(StringUtil.isNotEmpty(params.get("assetCode"))){
            params.put("assetCodes", params.get("assetCode").toString().split(","));
        }
	    
	    return valAssetReclassification001Mapper.selectReclassList(params);
	}
	
	/**
	 * 
	 * 新增方法
	 * 将界面中的数据入库
	 * @param dto
	 * @see
	 * @since 1.0
	 */
	public void save(ValAssetReclassification001ValidDto dto){
	    
	    if(null!=dto){
	        List<ValAssetReclassification001Dto> dtoList = dto.getValAssetReclassification001DtoList();
	        for (ValAssetReclassification001Dto valAssetReclassification001Dto : dtoList) {
	            
	            if(valAssetReclassification001Dto.getInvAim().equals(valAssetReclassification001Dto.getNewInvAim())){
	                break;
	            }
	            
	            
	            //交易编号
	            String tradeId = interfCommonService.getTradeid(DictCodeList.交易方向_资产重分类.getValue(), valAssetReclassification001Dto.getBooksetDate(), "A");
	            
	            //金融产品交易数据组装
	            TrdProductDeal trdProductDeal = new TrdProductDeal();
    	            //交易编号                         自动生成                                                                        
    	            trdProductDeal.setTradeId(tradeId);
    	            //业务类型                         数据字典.业务类型.资产重分类 
    	            trdProductDeal.setBusiType(DictCodeList.业务类型_资产重分类.getValue());
    	            //金融产品代码                           选择行.资产代码    
    	            trdProductDeal.setFinprodId(valAssetReclassification001Dto.getFinprodId());
    	            //金融产品类型                           数据字典.金融产品类型.债券                          
    	            trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
    	            //分支序号                         0
    	            trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
    	            //交易日                          画面.重分类日期        
    	            trdProductDeal.setTradeDate(valAssetReclassification001Dto.getBooksetDate());
    	            //单位面值                         参考【标的类共通：百元券面查询】，发生日=重分类日期-1        
    	            trdProductDeal.setParValue(this.getFaceValue(
    	                valAssetReclassification001Dto.getFinprodId(), DateUtil.addDay(valAssetReclassification001Dto.getBooksetDate(), -1)));
    	            //交易方向                         数据字典.交易方向.资产重分类
                    trdProductDeal.setPs(DictCodeList.交易方向_资产重分类.getValue());
    	            //投资目的                           选择行.原会计分类
    	            trdProductDeal.setInvAim(valAssetReclassification001Dto.getInvAim());
    	            //交易状态                           数据字典.交易状态.初始        
                    trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                    //币种 
                    trdProductDeal.setCcy(valAssetReclassification001Dto.getCyy());
                    
                //金融产品交易扩展表
     //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
                    TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd(); 
                    //交易编号                          生成的交易编号                                                                     
                    trdProductDealExt.setTradeId(tradeId);
                    BokBookset bok = bokBooksetMapper.selectByPrimaryKey(valAssetReclassification001Dto.getBooksetId());
                    
                    if(bok.getAcctSubjectType().equals(DictCodeListExt1.金融产品类型_投资组合.getValue())){
                      //投资组合代码                            账套.核算主体类别=“组合”时，投资组合代码=核算主体代码   
                        trdProductDealExt.setPortfolioId(bok.getAcctSubjectId());
                    }else if(bok.getAcctSubjectType().equals(DictCodeListExt1.金融产品类型_理财产品.getValue())){
                    	
                    	//理财产品代码                            账套.核算主体类别=“理财产品”时，理财产品代码=核算主体代码
                        //trdProductDealExt.setProdId(bok.getAcctSubjectId()); //【一体化注释：金融产品交易附属表TRD_PRODUCT_DEAL_ADD去除对PROD_ID字段】
                    }
                    //生成方式                          数据字典.生成方式.画面录入
                    trdProductDealExt.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
                    
                //金融产品实物交易
                TrdFinProductBarter trdFinProductBarter = new TrdFinProductBarter();
                    //交易编号                          生成的交易编号                                                                     
                    trdFinProductBarter.setTradeId(tradeId);
                    //金融产品代码                            选择行.资产代码            
                    trdFinProductBarter.setFinprodId(valAssetReclassification001Dto.getFinprodId());
                    //金融产品类型                            数据字典.金融产品类型.债券      
                    trdFinProductBarter.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                    //分支序号                          0
                    trdFinProductBarter.setBranch(InterfCommonConstant.分支序号_默认);
                    //投资目的                          选择行.新会计分类           
                    trdFinProductBarter.setInvAim(valAssetReclassification001Dto.getNewInvAim());
                //入账信息表
                BokDealAccounting bokDealAccounting = new BokDealAccounting();
                    //账套代码                          选择行.账套代码                                                                        
                    bokDealAccounting.setBooksetId(valAssetReclassification001Dto.getBooksetId());
                    //业务编号                          生成的交易编号             
                    bokDealAccounting.setBusiNo(tradeId);
                    //业务表                           数据字典.业务表.金融产品表                          
                    bokDealAccounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
                    //录入日                           画面.重分类日期                
                    bokDealAccounting.setBooksetDate(valAssetReclassification001Dto.getBooksetDate());
                    //入账日                           画面.重分类日期                
                    bokDealAccounting.setBookDate(valAssetReclassification001Dto.getBooksetDate());
                    //实际发生日                         画面.重分类日期                
                    bokDealAccounting.setHappenDate(valAssetReclassification001Dto.getBooksetDate());
                    //是否已入账                         N
                    bokDealAccounting.setIsAccounted("N");
                    //投资目的                          选择行.原会计分类                                                                       
                    bokDealAccounting.setInvAim(valAssetReclassification001Dto.getInvAim());
                    
                //账务明细代码表
                BokDetailId bokDetail = new BokDetailId();
                    //账套代码                          选择行.账套代码                                                                        
                    bokDetail.setBooksetId(valAssetReclassification001Dto.getBooksetId());
                    String bokDetailId = interfCommonService.getBokDetailId(valAssetReclassification001Dto.getFinprodId(), null, 
                        DictCodeListExt1.金融产品类型_债券.getValue(), valAssetReclassification001Dto.getNewInvAim(),0L, null).getBokDetailId();
                    //账务明细代码                            共通方法    
                    bokDetail.setBokDetailId(bokDetailId);
                    //业务明细代码                            选择行.资产代码                
                    bokDetail.setBusiId(valAssetReclassification001Dto.getFinprodId());
                    bokDetail.setBranch(BookConstant.DEFALUT_BRANCH);
                    //业务明细类型                            数据字典.金融产品类型.债券                          
                    bokDetail.setBusiDetailType(DictCodeListExt1.金融产品类型_债券.getValue());
                    //投资目的                          选择行.新会计分类                                                                       
                    bokDetail.setInvAim(valAssetReclassification001Dto.getNewInvAim());
                    //四级科目尾号                            选择行.资产代码                                                                        
                    bokDetail.setDetailSubjectNoTail(valAssetReclassification001Dto.getFinprodId());
                    //四级科目尾号名称                          选择行.资产名称                                                                        
                    bokDetail.setDetailSubnameTail(valAssetReclassification001Dto.getFinprodName());
                    
                //入账账务明细表
                BokDealAccountingDetail bokDealAccountingDetail = new BokDealAccountingDetail();
                    //账套代码                          画面.账套代码                                                                     
                    bokDealAccountingDetail.setBooksetId(valAssetReclassification001Dto.getBooksetId());
                    //业务编号                          生成的交易编号         
                    bokDealAccountingDetail.setBusiNo(tradeId);
                    //业务表                           数据字典.业务表.金融产品表          
                    bokDealAccountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
                    //业务类型                          数据字典.业务类型.资产重分类         
                    bokDealAccountingDetail.setBusiType(DictCodeList.业务类型_资产重分类.getValue());
                    //账务明细代码                            账务明细代码表.账务明细代码                                                                      
                    bokDealAccountingDetail.setBokDetailId(valAssetReclassification001Dto.getBokDetailId());
                
                //查询是否有相同的重分类交易,如果有删除
                deleteSameValue(valAssetReclassification001Dto);
                    
                    
                //金融产品交易
	            trdProductDealMapper.insert(trdProductDeal);
	            //金融产品交易扩展表
                trdProductDealExtMapper.insert(trdProductDealExt);
                //金融产品实物交易
                trdFinProductBarterMapper.insert(trdFinProductBarter);
                //入账信息表
                bokDealAccountingMapper.insert(bokDealAccounting);
                
                //需判断是否已存在相同主键的数据，如已存在则跳过
                BokDetailIdKey key = new BokDetailIdKey();
                key.setBokDetailId(bokDetailId);
                key.setBooksetId(valAssetReclassification001Dto.getBooksetId());
                if(!StringUtil.isNotEmpty(bokDetailIdMapper.selectByPrimaryKey(key))){
                  //账务明细代码表
                    bokDetailIdMapper.insert(bokDetail);
                }
                
                //入账账务明细表
                bokDealAccountingDetailMapper.insert(bokDealAccountingDetail);
            }
	    }
	}
	
	/**
	 * 
	 * 重复数据删除操作
	 * <功能详细描述>
	 * @param valAssetReclassification001Dto
	 * @param oldTradeId
	 * @see
	 * @since 1.0
	 */
	public void deleteSameValue(ValAssetReclassification001Dto valAssetReclassification001Dto){
	    
	    valAssetReclassification001Dto.setBusiType(DictCodeList.业务类型_资产重分类.getValue());
	    valAssetReclassification001Dto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
	  //查询是否有相同的重分类交易
        String  oldTradeId = valAssetReclassification001Mapper.getSameDeal(valAssetReclassification001Dto);
        //如果有同样的交易直接删除
        if(StringUtil.isNotEmpty(oldTradeId)){
            /**金融产品交易删除*/
            trdProductDealMapper.deleteByPrimaryKey(oldTradeId);
            /**金融产品交易扩展表删除*/
            trdProductDealExtMapper.deleteByPrimaryKey(oldTradeId);
            
            /**金融产品实物交易删除*/
            TrdFinProductBarterKey trdFinProductBarterKey = new TrdFinProductBarterKey();
            trdFinProductBarterKey.setTradeId(oldTradeId);
            trdFinProductBarterKey.setFinprodId(valAssetReclassification001Dto.getFinprodId());
            trdFinProductBarterKey.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
            trdFinProductBarterMapper.deleteByPrimaryKey(trdFinProductBarterKey);
            
            /**入账信息表删除*/
            BokDealAccountingKey bokDealAccountingKey = new BokDealAccountingKey();
            bokDealAccountingKey.setBooksetId(valAssetReclassification001Dto.getBooksetId());
            bokDealAccountingKey.setBusiNo(oldTradeId);
            bokDealAccountingKey.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            bokDealAccountingMapper.deleteByPrimaryKey(bokDealAccountingKey);
            
            /**入账账务明细表删除*/
            BokDealAccountingDetailKey bokDealAccountingDetailKey = new BokDealAccountingDetailKey();
            bokDealAccountingDetailKey.setBooksetId(valAssetReclassification001Dto.getBooksetId());
            bokDealAccountingDetailKey.setBusiNo(oldTradeId);
            bokDealAccountingDetailKey.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            bokDealAccountingDetailMapper.deleteByPrimaryKey(bokDealAccountingDetailKey);
        }
	    
	    
	}
	
	/**
	 * 
	 * 参考【标的类共通：百元券面查询】，发生日=重分类日期-1
	 * @param finprodId
	 * @param happenDate
	 * @return
	 * @see
	 * @since 1.0
	 */
	public BigDecimal getFaceValue( String finprodId, Date happenDate){
	    BigDecimal faceValue = new BigDecimal("100");
        BigDecimal payedFaceValue = new BigDecimal("0");
        
        Map<String, Object> paMap = new HashMap<String, Object>();
        paMap.put("finprodId", finprodId);// 标的代码
        paMap.put("cashType1", DictCodeListExt1.现金流一级类型_本金.getValue());// 现金流一级类型
        
        // 获取现金流计划结果集
        List<TargetCashPlan> listDto = (List<TargetCashPlan>)valAssetReclassification001Mapper.getTargetCashPlanList(paMap);
        for (TargetCashPlan plan : listDto) {
            // 计息结束日 <= 发生日
            if (DateUtil.compareDate(plan.getMdate(), happenDate) <= 0) {
                payedFaceValue = ArithmeticUtil.doAdd(payedFaceValue, plan.getPrinAmt());
            }
        }
        faceValue = ArithmeticUtil.doSub(faceValue, payedFaceValue);
        return faceValue;
	}
	

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(ValAssetReclassification001ValidDto entity, OperationType type) {
    	if(OperationType.NEW.equals(type)){
//          this.save(entity);
      	restTemplate.postForObject(
                  ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetReclassification001/save",
                  entity, Integer.class);
      }
    }

}

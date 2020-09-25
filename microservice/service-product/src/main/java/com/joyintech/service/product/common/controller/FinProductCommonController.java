package com.joyintech.service.product.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.common.service.FinProductCommonService;

/**
 * 产品端共通服务定义
 *
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @Description: TODO
 * @date Date : 2018年09月19日 10:02
 */
@RestController("backFinProductCommonController_dtx")
@RequestMapping("/FinProductCommon")
public class FinProductCommonController {

    /**
     * {@linkplain}
     */
	@Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getProdId")
    public List<SuggestDto> getProdId(@RequestBody Map<String, Object> param) {
        return finProductCommonService.getProdId(param);
    }

    @PostMapping("getGroupId")
    public List<SuggestDto> getGroupId(@RequestBody Map<String, Object> param) {
        return finProductCommonService.getGroupId(param);
    }


    @GetMapping("queryFinProductByFinprodId")
    public FinProduct queryFinProductByFinprodId(String finprodId) {
        return finProductCommonService.queryFinProductByFinprodId(finprodId);
    }
	
	/**
	 * 查询分行下拉框
	 * 
	 * @param param 参数map
	 * @return 分行一览
	 * @see
	 * @since 1.0
	 */
    @PostMapping("queryCounterpartyInfo/{page}/{length}")
	public Page<SelectOptionDto> queryCounterpartyInfo(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
		return finProductCommonService.queryCounterpartyInfo(param,page,length);
	}
	
	/**
	 * 交易日历的下拉框
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("queryCalendarInfoList/{page}/{length}")
	public Page<SelectOptionDto> queryCalendarInfoList(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
		return finProductCommonService.queryCalendarInfoList(param,page,length);
	}
    /**
     * 产品代码的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) {
        return finProductCommonService.queryProdIdInfo(param);
    }
    
    /**
     * 产品代码的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryProdIdInfoIssueSuc/{page}/{length}")
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductCommonService.queryProdIdInfoIssueSuc(page, length,param);
    }
    
    /**
     * 费用账户的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryFeeAccountList/{page}/{length}")
    public Page<SelectOptionDto> queryFeeAccountList(@RequestBody Map<String, Object> param ,
    		@PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductCommonService.queryFeeAccountList(page, length,param);
    }
    
    /**
     * 费用代码联想下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryFeeInfoList/{page}/{length}")
    public Page<SelectOptionDto> queryFeeInfoList(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductCommonService.queryFeeInfoList(page, length,param);
    }
    
    /**
     * 托管名称联想下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryTrusterCustomerName")
    public List<SelectOptionDto> queryTrusterCustomerName(@RequestBody Map<String, Object> param) {
        return finProductCommonService.queryTrusterCustomerName(param);
    }
	
	/**
     * 插入标的类金融产品基本信息共通
     * 
     * @param finProductCommonInfoDto 标的类金融产品基本信息共通dto
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
	@PostMapping("insertFinProductCommon")
	public void insertFinProductCommon(@RequestBody FinProductCommonInfoDto finProductCommonInfoDto) throws Exception{
	    finProductCommonService.insertFinProductCommon(finProductCommonInfoDto);
	}
	
	/**
     * 插入标的类金融产品基本信息共通
     * 
     * @param finProductCommonInfoDto 标的类金融产品基本信息共通dto
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateFinProductCommon")
    public void updateFinProductCommon(@RequestBody FinProductCommonInfoDto finProductCommonInfoDto) throws Exception{
        finProductCommonService.updateFinProductCommon(finProductCommonInfoDto);
    }
    
    /**
     * 插入标的类金融产品基本信息共通
     * 
     * @param finProductCommonInfoDto 标的类金融产品基本信息共通dto
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinProductCommon")
    public void deleteFinProductCommon(String finprodId) throws Exception{
        finProductCommonService.deleteFinProductCommon(finprodId);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    @GetMapping("isInFlowDataExisted")
    public Boolean isInFlowDataExisted(String tradeId) {
        return finProductCommonService.isInFlowDataExisted(tradeId);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    @GetMapping("isAlreadyRevoked")
    public Boolean isAlreadyRevoked(String tradeId) {
        return finProductCommonService.isAlreadyRevoked(tradeId);
    }
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    @GetMapping("queryStlPayOrdersByTradeId")
    public List<StlPayOrders> queryStlPayOrdersByTradeId(String tradeId){
        return finProductCommonService.queryStlPayOrdersByTradeId(tradeId);
    }
    
    /** 根据汇总交易编号查询交易信息
     * @param tradeId 汇总交易编号
     * @return 交易信息
     */
    @GetMapping("queryTrdProductDealByTotleTradeId")
    public List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId){
        return finProductCommonService.queryTrdProductDealByTotleTradeId(tradeId);
    }
    
    /** 根据汇总交易编号查询交易附属信息
     * @param tradeId 汇总交易编号
     * @return 交易附属信息
     */
    @GetMapping("queryTrdProductDealAddByTotleTradeId")
    public List<TrdProductDealAdd> queryTrdProductDealAddByTotleTradeId(String tradeId){
        return finProductCommonService.queryTrdProductDealAddByTotleTradeId(tradeId);
    }
    
    /** 根据汇总交易编号查询查询金融产品交易扩展
     * @param tradeId 汇总交易编号
     * @return 金融产品交易扩展
     */
    @GetMapping("queryPaTrdProductDealByTotleTradeId")
    public List<PaTrdProductDeal> queryPaTrdProductDealByTotleTradeId(String tradeId){
        return finProductCommonService.queryPaTrdProductDealByTotleTradeId(tradeId);
    }
    
    /** 根据汇总交易编号查询组合交易信息
     * @param tradeId 汇总交易编号
     * @return 组合交易信息
     */
    @GetMapping("queryPtlTradeByTotleTradeId")
    public List<PtlTrade> queryPtlTradeByTotleTradeId(String tradeId){
        return finProductCommonService.queryPtlTradeByTotleTradeId(tradeId);
    }
    
    /** 根据汇总交易编号查询理财分行销售明细
     * @param tradeId 汇总交易编号
     * @return 理财分行销售明细
     */
    @GetMapping("queryPaBranchSaleDetailByTotleTradeId")
    public List<PaBranchSaleDetail> queryPaBranchSaleDetailByTotleTradeId(String tradeId){
        return finProductCommonService.queryPaBranchSaleDetailByTotleTradeId(tradeId);
    }
    
    /** 根据汇总交易编号修改金融产品交易的撤销状态
     * @param tradeId 汇总交易编号
     * @return DB
     */
    @Transactional
    @DTXTransaction
    @GetMapping("updateTrdProductDealByTotleTradeId")
    public int updateTrdProductDealByTotleTradeId(String tradeId){
        return finProductCommonService.updateTrdProductDealByTotleTradeId(tradeId);
    }
    
    /**
     * 根据汇总交易编号查询费用明细列表
     * @param tradeId
     * @return
     */
    @GetMapping("queryTrdTradeFeeDetailByTotleTradeId")
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTotleTradeId(String tradeId){
        return finProductCommonService.queryTrdTradeFeeDetailByTotleTradeId(tradeId);
    }
    
    @PostMapping("queryTrdFinProductDealTotalByModel")
    public List<TrdFinProductDealTotal> queryTrdFinProductDealTotalByModel(@RequestBody TrdFinProductDealTotal trdFinProductDealTotal){
        return finProductCommonService.queryTrdFinProductDealTotalByModel(trdFinProductDealTotal);
    }
    
    @PostMapping("queryPrdMdateAdjustByModel")
    public List<PrdMdateAdjust> queryPrdMdateAdjustByModel(@RequestBody PrdMdateAdjust prdMdateAdjust){
        return finProductCommonService.queryPrdMdateAdjustByModel(prdMdateAdjust);
    }
    @Transactional
    @DTXTransaction
    @GetMapping("deleteBokBookSet")
    public int deleteBokBookSet(String finprodId){
		return finProductCommonService.deleteBokBookSet(finprodId);
	}
    
    @PostMapping("getUserName/{page}/{length}")
    public Page<SelectOptionDto> getUserName(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
//        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("roleName", DictCodeListExt2.角色岗位_产品设计岗.getValue());
        return finProductCommonService.selectUserName(param,page,length);
    }
  //新建产品经理只查本机构
    @PostMapping("getUserName1/{page}/{length}")
    public Page<SelectOptionDto> getUserName1(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
//        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("roleName", DictCodeListExt2.角色岗位_产品设计岗.getValue());
        return finProductCommonService.selectUserName1(param,page,length);
    }
    
    /**
     * 需求发起人下拉框
     * @param param
     * @param page
     * @param length
     * @return
     */
    @PostMapping("getProductUserName/{page}/{length}")
    public Page<SelectOptionDto> getProductUserName(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
//        Map<String, Object> param = RequestUtil.getPageParam();
        return finProductCommonService.getProductUserName(param,page,length);
    }

    @PostMapping("/getCustomerName/{page}/{length}")
    public Page<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> map,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("length") int length) {
//    	Map<String, Object> param = RequestUtil.getPageParam();
    	List<String> customerType = new ArrayList<String>();
    	customerType.add(DictCodeList.客户类型_法人.getValue());
        map.put("customerType", customerType);
        return finProductCommonService.getCustomerName(map,page,length);
     }
    /**
     * 获取行内标识码
     * @param map
     * @return
     */
    @PostMapping("/queryRepFinprodInsId")
    public List<SelectOptionDto> queryRepFinprodInsId(@RequestBody Map<String, Object> map) {
        map.put("repoperatemode01", DictCodeListExt2.产品运作模式_封闭式净值型.getValue());
        map.put("repoperatemode02", DictCodeListExt2.产品运作模式_封闭式非净值型.getValue());
        map.put("repoperatemode03", DictCodeListExt2.产品运作模式_开放式净值型.getValue());
        map.put("repoperatemode04", DictCodeListExt2.产品运作模式_开放式非净值型.getValue());
        return finProductCommonService.queryRepFinprodInsId(map);
    }

    /**
     * 获取产品款型下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getProdStyle/{page}/{length}")
    public Page<SelectOptionDto> getProdStyle(@RequestBody Map<String, Object> param,
                                              @PathVariable int page,@PathVariable int length) {
        return finProductCommonService.getProdStyle(param,page,length);
    }
    /**
     * 查询机构
     *
     * @param dto dto
     * @return 结     
     * @since 1.0
     */
    @PostMapping("queryTrusteeId/{page}/{length}")
    public Page<SelectOptionDto> queryTrusteeId(@RequestBody Map<String, Object> map,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
    	return finProductCommonService.queryTrusteeId(map,page,length);
    }
}

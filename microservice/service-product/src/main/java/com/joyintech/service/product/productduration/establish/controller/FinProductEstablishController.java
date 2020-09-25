/**
 *
 */
package com.joyintech.service.product.productduration.establish.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishBranchDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishLayerDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishShowDto;
import com.joyintech.service.product.productduration.establish.service.FinProductEstablishService;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;

/**
 * @author zhangliang
 */
@RestController("backFinProductEstablishController_dtx")
@RequestMapping("/FinProductEstablish")
public class FinProductEstablishController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductEstablishService finProductEstablishService;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;

    /**
     * 产品成立信息查询
     *
     * @param param 参数
     * @param page 分页
     * @param length 展示个数
     * @return 成立信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductEstablishDto> productEstablishInfoQuery(@RequestBody Map<String, Object> param,
                                                                  @PathVariable("page") int page,
                                                                  @PathVariable("length") int length)
        throws Exception {
        return finProductEstablishService.productEstablishInfoQuery(page, length, param);
    }

    /**
     * 根据金融产品代码查询费用列表
     * @param map 金融产品代码
     * @return 费用列表
     */
    @PostMapping("queryFeeInfoByFinprodId")
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryFeeInfoByFinprodId(map);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     * @param map 参数
     * @return 分层金额明细
     */
    @PostMapping("queryLayAmtDetailByFinprodId")
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryLayAmtDetailByFinprodId(map);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     * @param bussId 业务编号
     * @return 分层金额明细
     */
    @GetMapping("queryLayAmtDetailByBussId")
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByBussId(String bussId){
        return finProductEstablishService.queryLayAmtDetailByBussId(bussId);
    }

    /**
     * 根据金融产品代码查询募集金额明细
     * @param map 参数
     * @return 募集金额明细
     */
    @PostMapping("queryRaiseAmtInfoByFinprodId")
    public PaTrdFinProductDealTotal queryRaiseAmtInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryRaiseAmtInfoByFinprodId(map);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     * @param map 参数
     * @return 分层金额明细
     */
    @PostMapping("queryBranchRaiseInfoByFinprodId")
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryBranchRaiseInfoByFinprodId(map);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     * @param bussId 交易编号
     * @return 分层金额明细
     */
    @GetMapping("queryBranchRaiseInfoByBussId")
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByBussId(String bussId){
        return finProductEstablishService.queryBranchRaiseInfoByBussId(bussId);
    }

    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    @PostMapping("queryBranchInfoIdList")
    public List<SelectOptionDto> queryBranchInfoIdList(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryBranchInfoIdList(map);
    }

    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    @PostMapping("queryBranchInfoNameList")
    public List<SelectOptionDto> queryBranchInfoNameList(@RequestBody Map<String, Object> map){
        return finProductEstablishService.queryBranchInfoNameList(map);
    }

    /**
     * 插入现金流信息和计算规则信息
     * @param baseFinProductBaseInfoDto 基础组件dto
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertFinProductComm")
    public void insertFinProductComm(@RequestBody BaseFinProductBaseInfoDto baseFinProductBaseInfoDto){
        List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
        list.add(baseFinProductBaseInfoDto);
        baseFinProductBaseInfoService.insert(list);
    }

    /** 删除交易类金融产品
     * @param map 参数
     * @return DB
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteFinTradeProductByFinprodId")
    public int deleteFinTradeProductByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.deleteFinTradeProductByFinprodId(map);
    }

    /** 删除现金流信息
     * @param map 参数
     * @return DB
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteFinCashInfoByFinprodId")
    public int deleteFinCashInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.deleteFinCashInfoByFinprodId(map);
    }

    /** 删除现金流计算规则信息
     * @param map 参数
     * @return DB
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteFinCashCalcRuleByFinprodId")
    public int deleteFinCashCalcRuleByFinprodId(@RequestBody Map<String, Object> map){
        return finProductEstablishService.deleteFinCashCalcRuleByFinprodId(map);
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
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                         @PathVariable("length") int length) {
        return finProductEstablishService.queryProdIdInfoIssueSuc(page, length,param);
    }

    /**
    * 根据金融产品代码查询产品信息
    * @param map 参数
    * @return 产品信息
    */
   @PostMapping("queryProdInfoByFinprodId")
   public FinProductCommonInfoDto queryProdInfoByFinprodId(@RequestBody Map<String, Object> map){
       return finProductEstablishService.queryProdInfoByFinprodId(map);
   }

   /**
    * 根据金融产品代码查询产品信息
    * @param map 参数
    * @return 产品信息
    */
   @PostMapping("selectProdInfoByFinprodId")
   public FinProductEstablishShowDto selectProdInfoByFinprodId(@RequestBody Map<String, Object> map){
       return finProductEstablishService.selectProdInfoByFinprodId(map);
   }

   /**
    * 查询分支序号是否存在
    * @param map
    * @return
    */
   @PostMapping("selectIsBranch")
   public int selectIsBranch(@RequestBody Map<String, Object> map){
       return finProductEstablishService.selectIsBranch(map);
   }

   /**
    * 查询交易是否存在
    * @param map
    * @return
    */
   @PostMapping("selectIsTrade")
   public int selectIsTrade(@RequestBody Map<String, Object> map){
       return finProductEstablishService.selectIsTrade(map);
   }
   /**
    * 查询是否存在其他交易
    * @param finprodId
    * @return
    */
   @PostMapping("selectDealAdd")
   public int selectDealAdd(@RequestBody Map<String, Object> map){
	   return finProductEstablishService.selectDealAdd(map);
   }
   /**
    * 查询是否存在其他交易
    * @param finprodId
    * @return
    */
   @PostMapping("selectOtherSurDeal")
   public int selectOtherSurDeal(@RequestBody Map<String, Object> map){
	   return finProductEstablishService.selectOtherSurDeal(map);
   }
}

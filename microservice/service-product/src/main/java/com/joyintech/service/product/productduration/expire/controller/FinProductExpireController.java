/**
 * 
 */
package com.joyintech.service.product.productduration.expire.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpire001Dto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireBranchInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireFeeInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireMInfoDto;
import com.joyintech.service.product.productduration.expire.service.FinProductExpireService;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;

/**
 * @author zhangliang
 */
@RestController("backFinProductExpireController")
@RequestMapping("/FinProductExpire")
public class FinProductExpireController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductExpireService finProductExpireService;

    /**
     * 查询产品费用支付信息
     * 
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return 费用计划信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductExpire001Dto> selectForPage(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return finProductExpireService.selectForPage(page, length, param);
    }

    /**
     * 根据金融产品代码查询到期信息
     * 
     * @param map 参数
     * @return 到期信息
     */
    @PostMapping("queryMInfoListByFinprodId")
    public List<FinProductExpireMInfoDto> queryMInfoListByFinprodId(@RequestBody Map<String, Object> map) {
        return finProductExpireService.queryMInfoListByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询费用信息
     * 
     * @param map 参数
     * @return 费用信息
     */
    @PostMapping("queryFeeInfoListByFinprodId")
    public List<FinProductExpireFeeInfoDto> queryFeeInfoListByFinprodId(@RequestBody Map<String, Object> map){
        return finProductExpireService.queryFeeInfoListByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询分行信息
     * 
     * @param map 参数
     * @return 分行信息
     */
    @PostMapping("queryBranchInfoListByFinprodId")
    public List<FinProductExpireBranchInfoDto> queryBranchInfoListByFinprodId(@RequestBody Map<String, Object> map){
        return finProductExpireService.queryBranchInfoListByFinprodId(map);
    }
    
    /** 根据交易编号查询到期信息
     * @param tradeId 交易编号
     * @return 到期信息
     */
    @GetMapping("queryMInfoListByTradeId")
    public List<FinProductExpireMInfoDto> queryMInfoListByTradeId(String tradeId){
        return finProductExpireService.queryMInfoListByTradeId(tradeId);
    }
    
    /** 根据交易编号查询费用信息
     * @param tradeId 交易编号
     * @return 费用信息
     */
    @GetMapping("queryFeeInfoListByTradeId")
    public List<FinProductExpireFeeInfoDto> queryFeeInfoListByTradeId(String tradeId){
        return finProductExpireService.queryFeeInfoListByTradeId(tradeId);
    }
    
    /** 根据交易编号查询分行信息
     * @param tradeId 交易编号
     * @return 分行信息
     */
    @GetMapping("queryBranchInfoListByTradeId")
    public List<FinProductExpireBranchInfoDto> queryBranchInfoListByTradeId(String tradeId){
        return finProductExpireService.queryBranchInfoListByTradeId(tradeId);
    }
    
    @PostMapping("queryProdInfoByMdate")
    public FinProductCommonInfoDto queryProdInfoByMdate(@RequestBody Map<String, Object> map){
        return finProductExpireService.queryProdInfoByMdate(map);
    }
    
    @PostMapping("queryInterest")
    public List<ReduceInfoDto> queryInterest(@RequestBody Map<String, Object> map) {
        return finProductExpireService.queryInterest(map);
    }
    /**
     * 根据到期汇总交易编号查询到期交易
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryProdExpireByTotleTradeId")
    public FinProductExpire001Dto queryProdExpireByTotleTradeId(String tradeId){
        return finProductExpireService.queryProdExpireByTotleTradeId(tradeId);
    }
    /**
     * 查询是否有到期交易
     * @param map
     * @return
     */
    @PostMapping("isExpireTradeByFinprodId")
    public Integer isExpireTradeByFinprodId(@RequestBody Map<String, Object> map) {
        return finProductExpireService.isExpireTradeByFinprodId(map);
    }
}

/**
 * 
 */
package com.joyintech.service.product.messageadjust.finmdateadjustment.controller;

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

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustmentDto;
import com.joyintech.service.product.messageadjust.finmdateadjustment.service.FinMdateAdjustmentService;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * @author zhangliang
 *
 */
@RestController("backFinMdateAdjustmentController_dtx")
@RequestMapping("/FinMdateAdjustment")
public class FinMdateAdjustmentController {

    /**
     * {@linkplain}
     */
    @Autowired(required=false)
    private FinMdateAdjustmentService finMdateAdjustmentService;
    
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param page page
     * @param length length
     * @param param param
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    @PostMapping("queryAdjustMdateList/{page}/{length}")
    public Page<FinMdateAdjustmentDto> queryAdjustMdateList(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finMdateAdjustmentService.queryAdjustMdateList(page, length, param);
    }
    
    /**
     * 到期日调整新建产品代码下拉框检索逻辑
     * @param param 入参
     * @return 产品代码下拉框
     */
    @PostMapping("queryAdjustProdIdList")
    public List<SelectOptionDto> queryAdjustProdIdList(@RequestBody Map<String, Object> param){
        return finMdateAdjustmentService.queryAdjustProdIdList(param);
    }
    
    /**
     * 根据金融产品代码查询到期日调整信息
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     */
    @GetMapping("queryAdjustInfoByFinprodId")
    public FinMdateAdjustmentDto queryAdjustInfoByFinprodId(String finprodId){
        return finMdateAdjustmentService.queryAdjustInfoByFinprodId(finprodId);
    }
    
    /**
     * 查询组合到期日
     * @param finprodId 金融产品代码
     * @return 组合到期日
     */
    @GetMapping("queryPortFolioMdateByFinprodId")
    public String queryPortFolioMdateByFinprodId(String finprodId){
        return finMdateAdjustmentService.queryPortFolioMdateByFinprodId(finprodId);
    }
    
    /**
     * 查询是否有后续交易
     * @param finprodId 金融产品代码
     * @param mdate 调整后到期日
     * @return 交易个数
     */
    @GetMapping("queryDealCountByFinprodId")
    public int queryDealCountByFinprodId(String finprodId, String mdate){
        return finMdateAdjustmentService.queryDealCountByFinprodId(finprodId, mdate);
    }
    
    /**
     *  更新计划相关信息
     * @param finProductCommonInfoDto 更新数据
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateProdPlanList")
    public void updateProdPlanList(@RequestBody FinProductCommonInfoDto finProductCommonInfoDto) throws Exception{
        finMdateAdjustmentService.updateProdPlanList(finProductCommonInfoDto);
    }
    
    @GetMapping("queryRelateInfoList")
    public List<FinMdateAdjustmentDto> queryRelateInfoList(String finprodId){
        return finMdateAdjustmentService.queryRelateInfoList(finprodId);
    }
    
    /**
     * 查询产品的最大调整批次
     * @return
     */
    @GetMapping("getMaxAdjustNum") 
    public int getMaxAdjustNum(String finprodId){
    	return finMdateAdjustmentService.getMaxAdjustNum(finprodId);
    }
    
    /**
     * 查询是否是存续产品
     * @return
     */
    @GetMapping("queryProductByProdId") 
    public FinMdateAdjustmentDto queryProductByProdId(String finprodId){
    	return finMdateAdjustmentService.queryProductByProdId(finprodId);
    }
    /**
     * 母产品代码查询分层子产品信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryLayeredChildProductByProdid") 
    public List<FinProduct> queryLayeredChildProductByProdid(String finprodId){
        return finMdateAdjustmentService.queryLayeredChildProductByProdid(finprodId);
    }
}

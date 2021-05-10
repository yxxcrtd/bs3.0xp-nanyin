/*
 * 文件名：TrdDepoDurationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：丁志鹏
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdassetlossclear.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdassetlossclear.dto.TrdAssetLossClearDto;
import com.joyintech.service.period.trdassetlossclear.service.TrdAssetLossClearService;
import com.joyintech.service.period.trddepoduration.dto.TrdDepoDurationDto;
import com.joyintech.service.period.trddepoduration.service.TrdDepoDurationService;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 存款存续期微服务Controller 〈功能详细描述〉
 *
 * @author 丁志鹏
 * @version 1.0 2018年10月11日
 * @see TrdAssetLossClearController
 * @since 1.0
 */
@RestController("TrdAssetLossClearCtr_dtx")
@RequestMapping("/TrdAssetLossClear")
public class TrdAssetLossClearController {

    /**
     * 存款存续期service
     */
    @Autowired
    private TrdDepoDurationService trdDepoDurationService;
    /**
     * 存款存续期service
     */
    @Autowired
    private TrdAssetLossClearService trdAssetLossClearService;

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @param param 界面参数
     * @param page 分页用参数
     * @param length 分页用参数
     * @return 查询结果集
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdAssetLossClearDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdAssetLossClearService.findPage(page, length, param);
    }
    
    /**
     * 查询待清理的数据
     *
     * @param param 界面参数
     * @param page 分页用参数
     * @param length 分页用参数
     * @return 查询结果集
     */
    @PostMapping("findNewPage/{page}/{length}")
    public Page<TrdAssetLossClearDto> findNewPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdAssetLossClearService.findNewPage(page, length, param);
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    @PostMapping("findNewList")
    public List<TrdAssetLossClearDto> findNewList(@RequestBody Map<String, Object> param){
        return trdAssetLossClearService.findNewList(param);
    }

    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody TrdAssetLossClearDto trdAssetLossClearDto) {
         trdAssetLossClearService.doComplete(trdAssetLossClearDto,trdAssetLossClearDto.getOperateType());
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */   
    @PostMapping("selectByKey/{page}/{length}")
    public Page<TrdAssetLossClearDto> selectByKey(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdAssetLossClearService.selectByKey(page, length, param);
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    @PostMapping("selectDetail")
    public List<TrdAssetLossClearDto> selectDetail(@RequestBody Map<String, Object> param){
        return trdAssetLossClearService.selectDetail(param);
    }
    /**
     * 撤销交易，插入流出表
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdDepoDurationDto durationDto) {
        return trdDepoDurationService.doRevoke(durationDto);
    }

    /**
     *
     * 画面查询方法-组合代码
     * <功能详细描述>
     * @param param 参数
     * @param page 分页参数
     * @param length  分页参数
     * @return
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("finprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        return trdAssetLossClearService.finprodMarketId(page, length, param);
    }
    
    /**
    *
    * 导出查询
    * <功能详细描述>
    * @param
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("/selectForExport")
   public List<TrdAssetLossClearDto>  selectForExport(@RequestBody Map<String, Object> param) {
       return trdAssetLossClearService.selectForExport(param);
   }
   
   /**
    * 策略代码生成
    * @see
    * @since 1.0
    */
   @PostMapping("createStrategyId")
   public String createStrategyId() {
       return trdAssetLossClearService.createStrategyId();
   }
   
   /**
    * 策略代码生成
    * @see
    * @since 1.0
    */
   @PostMapping("selectAredyLossAsset")
   public int selectAredyLossAsset(@RequestBody Map<String, Object> param) {
       return trdAssetLossClearService.selectAredyLossAsset(param);
   }
   

}

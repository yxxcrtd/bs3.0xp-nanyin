/*
 * 文件名：AssetCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductSppiInfo;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonController
 * @since 1.0
 */
@RestController("backAssetCommonController")
@RequestMapping("/AssetCommon")
public class AssetCommonController {

    @Autowired
    private AssetCommonService assetCommonService;
    
    @GetMapping("getAssetId")
    public List<SuggestDto> getAssetId(String search,String finprodType2,String profitType, String child) {
        Map<String, Object> param = new HashMap<>();
        param.put("search",search);
        param.put("finprodType2",finprodType2);
        param.put("profitType",profitType);
        param.put("child",child);
         return assetCommonService.getAssetId(param);
    }
    
    @PostMapping("/getAssetIdOption/{page}/{length}")
    public Page<SelectOptionDto> getAssetIdOption(@RequestBody Map<String, Object> param,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return assetCommonService.getAssetIdOption(page,length,param);
    }
    
    /**
     * 交易日历的下拉框
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryCalendarInfoList")
    public List<SelectOptionDto> queryCalendarInfoList(@RequestBody Map<String, Object> param) {
        return assetCommonService.queryCalendarInfoList(param);
    }
    
    /**
     * 资产提供行下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getTradeOpponent")
    public List<SelectOptionDto> getTradeOpponent(@RequestBody Map<String, Object> param) {
        return assetCommonService.getTradeOpponent(param);
    }
    
    /**
     * 客户下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCustomerName")
    public List<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param) {
        return assetCommonService.getCustomerName(param);
    }
    
    /**
     * 市场机构客户信息下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getMarketInstitutionCustomerName")
    public List<SelectOptionDto> getMarketInstitutionCustomerName(@RequestBody Map<String, Object> param) {
        return assetCommonService.getMarketInstitutionCustomerName(param);
    }
    
    /**
     *  利率调整(取资产的利率类型、息票品种)
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetInfoToRateAdj")
    public AssetCommonInfoDto getAssetInfoToRateAdj(@RequestBody Map<String, Object> param) {
        return assetCommonService.getAssetInfoToRateAdj(param);
    }
    
    /**
     * 获取所有标的信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAllAsset")
    public List<FinProduct> selectAllAsset(){
        return assetCommonService.selectAllAsset();
    }
    /**
     * 
     * 查询批复号
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getPflshList/{page}/{length}")
    public Page<SelectOptionDto> getPflshList(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length) {
        return assetCommonService.getPflshList(page, length,param);
    }
    /**
     * 
     * 查询实际授信主体
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getActualCreditProvid/{page}/{length}")
    public Page<SelectOptionDto> getActualCreditProvid(@RequestBody  Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length) {
        return assetCommonService.getActualCreditProvid(page, length,param);
    }
    
    /**
     * 资产是否重复
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("isRepeated")
    public int isRepeated(@RequestBody Map<String, Object> param){
        return assetCommonService.isRepeated(param);
    }
    
    /**
     * 发行人下拉框联动带出中债信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getRepInfoByIssuer")
    public MstCustomerInfoAdd getRepInfoByIssuer(@RequestBody Map<String, Object> param) {
        return assetCommonService.getRepInfoByIssuer(param);
    }

    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    @GetMapping("getMdateByFinproId")
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return  assetCommonService.getMdateByFinproId(finprodId);
    }

    // 根据资产三级分类检索五级分类  add by yjm 2019-10-08 start
    /**
     * 资产五级分类联动数据取得
     * @param param 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetThreeType")
    public FinProductTypeConfig getAssetThreeType(@RequestBody FinProductTypeConfig param) {
        return  assetCommonService.getAssetThreeType(param);
    }
    // 根据资产三级分类检索五级分类  add by yjm 2019-10-08 end
    /**
     * 投管接口（SPPI测试信息输入接口）
     * 
     * @param entity 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertSppiInfo")
    public String  insertSppiInfo(@RequestBody FinProductSppiInfo sppiInfo) {
		return assetCommonService.insertSppiInfo(sppiInfo);
    	
    }


    @RequestMapping("getSysDictCode/{page}/{length}")
    public Page<SelectOptionDto> getSysDictCode(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length) {
        CommonUtil.setInstitutionMapByType_ms(param);
        return assetCommonService.getSysDictCode(page, length, param);
    }
}

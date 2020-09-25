/*
 * 文件名：FinBondTargetAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.assets.bond.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.bond.dto.ValBondTargetAssetDto;
import com.joyintech.service.valuation.assets.bond.service.ValBondTargetAssetService;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.service.valuation.component.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.InterfCbonddescription;
import com.joyintech.tams.common.base.entity.InterfFloatcouprate;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecoption;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecrating;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecschedule;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.constant.DictCodeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetController
 * @since 1.0
 */
@RestController("ValBondTargetAssetController_dtx")
@RequestMapping("/ValBondTargetAsset")
public class ValBondTargetAssetController {

    @Autowired
    private ValBondTargetAssetService finBondTargetAssetService;
    
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValBondTargetAssetDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finBondTargetAssetService.findPage(page, length, param);
    }
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody ValBondTargetAssetDto dto) throws Exception {
        finBondTargetAssetService.insert(dto);
    }
    
    /**
     * 修改
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody ValBondTargetAssetDto dto) throws Exception{
        finBondTargetAssetService.update(dto);
    }
    
    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody ValBondTargetAssetDto dto) throws Exception{
        finBondTargetAssetService.delete(dto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getBasicInfo")
    public ValBondTargetAssetDto getBasicInfo(String finprodId) throws Exception {
        return finBondTargetAssetService.getBasicInfo(finprodId);
    }
    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * 获取基本信息--查询各个组件业务表数据
     * @param entity FinBondTargetAssetDto
     * @return entity
     * @see
     * @since 1.0
     */
    @PostMapping("searchData")
    public ValBondTargetAssetDto searchData(@RequestBody ValBondTargetAssetDto entity){
        finBondTargetAssetService.searchData(entity);
        return entity;
    }
    // 债券新建新增快捷录入功能 add by renping 20191010 end
    /**
     * 市场代码是否重复
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectCountByfinprodMarketId")
    public int selectCountByfinprodMarketId(String finprodMarketId) {
        return finBondTargetAssetService.selectCountByfinprodMarketId(finprodMarketId);
    }

    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     * 
     * @author wangguiqi
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return finBondTargetAssetService.getfinprodIdForAdd(page,length,param);
    }
    
    @GetMapping("isHqTrade")
    public int isHqTrade(String finprodId) {
		return finBondTargetAssetService.isHqTrade(finprodId);
	}
    
    
    
    /**
     * 获取wind资产信息(债券) <功能详细描述>
     *
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondAssetBySecId")
    public InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(String secid) {
        return finBondTargetAssetService.selectWindBondAssetBySecId(secid);
    }
    
    /**
     * 获取wind行权信息(债券) <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondExercise")
    public List<InterfPayhsrcsecoption> selectWindBondExercise(String secid) {
        return finBondTargetAssetService.selectWindBondExercise(secid);
    }
    
    /**
     * 获取wind付息计划(债券) <功能详细描述>
     *
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondSchedule")
    public List<InterfPayhsrcsecschedule> selectWindBondSchedule(String secid) {
        return finBondTargetAssetService.selectWindBondSchedule(secid);
    }
    
    /**
     * 获取WIND_浮息债报价表(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfFloatingcouponsrate")
    public List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String sInfoWindcode) {
        return finBondTargetAssetService.selectInterfFloatingcouponsrate(sInfoWindcode);
    }
    /**
     * 获取中国债券基本资料(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfCbonddescription")
    public InterfCbonddescription selectInterfCbonddescription(String sInfoWindcode) {
        return finBondTargetAssetService.selectInterfCbonddescription(sInfoWindcode);
    }
    
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getMstCalendarInfo")
    public List<MstCalendarInfo>  getMstCalendarInfo(){
        return finBondTargetAssetService.getMstCalendarInfo();
    }
    /**
     * 根据外部代码1获取客户代码（用于自动加券）
     * <一句话功能简述>
     * <功能详细描述>
     * @param linkId1
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getIssuer")
    public String getIssuer(String linkId1, String type){
        return finBondTargetAssetService.getIssuer(linkId1, type);
    }
    
    /**
     * 查询资讯_债券评级中主体评级结果
     *
     * @param secid
     * @param date
     * @return
     */
    @GetMapping("selectRating")
    public List<InterfPayhsrcsecrating> selectRating(String secid, String date) {
        return finBondTargetAssetService.selectRating(secid, date);
    }
    /**
     * 查询客户信息附属表list-债券中债
     *
     * @param customerid
     * @return
     */
    @GetMapping("selectMstCustomerInfoAdd")
    public List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(String customerid) {
        return finBondTargetAssetService.selectMstCustomerInfoAdd(customerid);
    }
    /**
     * 查询资讯_债券评级信息表
     *
     * @param secid
     * @return
     */
    @GetMapping("selectInterfPayhsrcsecrating")
    public List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(String secid) {
        return finBondTargetAssetService.selectInterfPayhsrcsecrating(secid);
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
        return  finBondTargetAssetService.getAssetThreeType(param);
    }
	/**
	 * 债券下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@PostMapping("/getBondId")
	public List<SelectOptionDto> getBondId(@RequestBody Map<String, Object> params) {
		return finBondTargetAssetService.getBondId(params);
	}
    /**
     * 发行人
     * 
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getIssuerId/{page}/{length}")
    public Page<SelectOptionDto> getIssuer(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        param.put("IS_ISSUER", DictCodeList.是否_是.getValue());
        return finBondTargetAssetService.getCustomer(page, length, param);
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
		return finBondTargetAssetService.queryCalendarInfoList(param,page,length);
	}
}

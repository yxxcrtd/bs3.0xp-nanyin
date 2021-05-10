/*
 * 文件名：EtrInvestBondInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.service.TrdAssetLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产减值管理微服务controller
 * 〈功能详细描述〉
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss001Controller
 * @since 1.0
 */
@RestController("TrdAssetLossCtr_dtx")
@RequestMapping("/TrdAssetLoss")
public class TrdAssetLoss001Controller {

    /**
     * 资产减值管理微服务service
     */
    @Autowired
    private TrdAssetLossService trdAssetLossService;

    /**
     *
     * 资产减值管理分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdAssetLoss001Dto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{

        if(StringUtil.isNotEmpty(param.get("finprodId"))){
            param.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,param.get("finprodId").toString()));
        }
        return trdAssetLossService.selectForPage(page,length,param);
    }
    /**
     *
     * 资产名称联想下拉框框
     * <功能详细描述>
     * @param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        Page<SelectOptionDto> temp = trdAssetLossService.getfinprodIdForAdd(page,length,param);
        return temp;
    }
    /**
     *
     * 资产信息检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getAssetInfo")
    public List<TrdAssetLoss001Dto>  getAssetInfo(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.getAssetInfo(param);
    }
    /**
     *
     * 债券资产信息检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getBondAssetInfo")
    public List<TrdAssetLoss001Dto>  getBondAssetInfo(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.getBondAssetInfo(param);
    }
    /**
     *
     * 减值行情日检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getAssetExerciseDate")
    public List<TrdAssetLoss001Dto>  getAssetExerciseDate(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.getAssetExerciseDate(param);
    }
    /**
     *
     * 资产信息检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByKey")
    public List<TrdAssetLoss001Dto>  selectByKey(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.selectByKey(param);
    }
    /**
     *
     * 工作流完成方法
     * <功能详细描述>
     * @param dto
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody TrdAssetLoss001Dto dto) throws IllegalAccessException, InvocationTargetException{
        trdAssetLossService.doComplete(dto, dto.getOperateType());
    }
    /**
     *
     * 资产减值 （新建--预览） 分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPagePreView/{page}/{length}")
    public Page<TrdAssetLoss001Dto> selectForPagePreView(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return trdAssetLossService.selectForPagePreView(page,length,param);
    }
    /**
     *
     * 资产信息检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByFinprodId")
    public List<TrdAssetLoss001Dto>  selectByFinprodId(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.selectByFinprodId(param);
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
    public List<TrdAssetLoss001Dto>  selectForExport(@RequestBody Map<String, Object> param) {
        if(StringUtil.isNotEmpty(param.get("finprodId"))){
            param.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,param.get("finprodId").toString()));
        }
        return trdAssetLossService.selectForExport(param);
    }
    /**
     *
     * 当日资产不予许重复减值 查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByFinprodIdAndValDate")
    public List<TrdAssetLoss001Dto>  selectByFinprodIdAndValDate(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.selectByFinprodIdAndValDate(param);
    }
    /**
     *
     * 当日资产不予许重复减值 查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectRepeatByFinprodIdAndValDate")
    public List<TrdAssetLoss001Dto>  selectRepeatByFinprodIdAndValDate(@RequestBody Map<String, Object> param) {
        return trdAssetLossService.selectRepeatByFinprodIdAndValDate(param);
    }

}

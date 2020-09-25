package com.joyintech.service.product.messageadjust.finproductquotation.controller;

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
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.service.FinProductQuotation001Service;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.invest.common.component.base.core.impl.BaseGetBussinessIdimpl;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * 信息调整管理-产品报价管理-查询
 *
 * @author : wangkang
 * @version V1.0
 * @Project: microservice
 * @date Date : 2018年10月9日
 */
@RestController("backFinProductQuotation001Controller_dtx")
@RequestMapping("/FinProductQuotation001")
public class FinProductQuotation001Controller {
	/**
	 * 微服务端产品报价service
	 */
	@Autowired
    private FinProductQuotation001Service finProductQuotation001Service;
    /**
     * 产品代码联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param) {
    	return finProductQuotation001Service.getFinprodId(param);
    }
    
    
    /**
     * 产品款型联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProdType")
    public List<SelectOptionDto> getProdType(@RequestBody Map<String, Object> param) {
        return finProductQuotation001Service.getProdType(param);
    }

    /**
     * 产品代码的下拉框
     *
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryProdIdInfoIssueSuc")
    public List<SelectOptionDto> queryProdIdInfoIssueSuc(@RequestBody Map<String, Object> param) {
        return finProductQuotation001Service.queryProdIdInfoIssueSuc(param);
    }

    /**
     * 产品报价管理-查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductQuotation001Dto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
    		throws Exception{
    	return finProductQuotation001Service.selectForPage(page, length, param);
    }
    /**
     * 删除产品报价相关业务表
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
	public void update(@RequestBody FinProductQuotation001Dto entity){
		finProductQuotation001Service.delete(entity);
	}
    /**
     * 产品报价新建的产品代码联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */


    @PostMapping("getFinprodId2")
    public List<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> param) {
    	return finProductQuotation001Service.getFinprodId2(param);
    }
    /**
     * 根据产品代码查询产品基本信息
     *
     * @param param 参数
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryProdInfoByFinprodId")
    public FinProductQuotation001Dto queryProdInfoByFinprodId(@RequestBody Map<String, Object> param){
    	return finProductQuotation001Service.queryProdInfoByFinprodId(param);
    }

    /**
     *净值、货币产品生效日判断
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getValueDate")
    public int getValueDate(@RequestBody Map<String, Object> param){
        return finProductQuotation001Service.getValueDate(param);
    }

    /**
     *
     * 检查是否存在生效日的报价
     *
     * @param param 入参
     * @return 报价存在个数
     * @see
     * @since 1.0
     */
    @PostMapping("checkEffDateQuoteExists")
    public int checkEffDateQuoteExists(@RequestBody Map<String, Object> param){
        return finProductQuotation001Service.checkEffDateQuoteExists(param);
    }
    /**
     * 市场代码得到起息日和到期日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getVdateAndMdate")
    public FinProductQuotation001Dto getVdateAndMdate(String finprodId){
        return finProductQuotation001Service.getVdateAndMdate(finprodId);
    }
    /**
     * 获取业务编号
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getBussinessId")
    public String getBussinessId(@RequestBody BussinessCodeParameter parameter){
        return new BaseGetBussinessIdimpl().buildBussinessCode(parameter);
    }

    /**
     * 新建
     *
     * @param dto 操作对象
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody FinProductQuotation002Dto dto) throws Exception{
        finProductQuotation001Service.doInsert(dto);
    }

    /**
     * 修改
     *
     * @param dto 操作对象
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doUpdate")
    public void doUpdate(@RequestBody FinProductQuotation002Dto dto) throws Exception{
        finProductQuotation001Service.doUpdate(dto);
    }

    /**
     * 删除
     *
     * @param dto 操作对象
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public void doDelete(@RequestBody FinProductQuotation002Dto dto) throws Exception{
        finProductQuotation001Service.doDelete(dto);
    }

    /**
     * 根据金融产品代码和报价日查询报价信息
     *
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryQuoteInfoByFinprodIdEffDate")
    public FinProductQuotation002Dto queryQuoteInfoByFinprodIdEffDate(@RequestBody Map<String,Object> param){
        return finProductQuotation001Service.queryQuoteInfoByFinprodIdEffDate(param);
    }

    /**
     * 根据金融产品代码查询分层信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分层信息
     */
    @GetMapping("queryLayerDetailByFinprodId")
    public List<PrdLayerParam> queryLayerDetailByFinprodId(String finprodId, String effDate){
        return finProductQuotation001Service.queryLayerDetailByFinprodId(finprodId, effDate);
    }
    /**
     * 根据金融产品代码和报价日查询分层报价信息
     *
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryQuoteLayDetailInfoList")
    public List<FinProductQuotation003Dto> queryQuoteLayDetailInfoList(@RequestBody Map<String,Object> param){
        return finProductQuotation001Service.queryQuoteLayDetailInfoList(param);
    }
    
}

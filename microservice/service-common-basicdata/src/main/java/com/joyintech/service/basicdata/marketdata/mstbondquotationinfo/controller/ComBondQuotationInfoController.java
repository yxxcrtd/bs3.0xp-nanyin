/*
 * 文件名：MstBondQuotationInfoController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangzehui
 * 修改时间：2018年7月17日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.dto.ComBondQuotationInfoDto;
import com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.service.ComBondQuotationInfoService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see ComBondQuotationInfoController
 * @since 1.0
 */
@RestController("serviceComBondQuotationInfoController")
@RequestMapping("/ComBondQuotationInfo")
public class ComBondQuotationInfoController {

    /**
     * 
     */
    @Autowired
    private ComBondQuotationInfoService comBondQuotationInfoService;

    /**
     * 债券代码下拉选择框
     * 
     * @return list结果集
     * @throws Exception 异常
     */
    @GetMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String secId)
        throws Exception {
        return comBondQuotationInfoService.getSecIdOption(secId);
    }

    /**
     * 新建页面查询资产代码
     * 
     * @return list
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecIdToNew")
    public List<SuggestDto> getSecIdToNew(String secId)
        throws Exception {
        return comBondQuotationInfoService.getSecIdToNew(secId);
    }

    /**
     * 债券简称的下拉框
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("getFinProdAbbr")
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr)
        throws Exception {
        return comBondQuotationInfoService.getFinProdAbbr(finprodAbbr);
    }

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ComBondQuotationInfoDto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return comBondQuotationInfoService.selectForPage(page, length, param);
    }

    /**
     * 获取金融产品简称
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @PostMapping("getFinprodAbbrOption/{page}/{length}")
    public Page<SelectOptionDto> getFinprodAbbrOption(@RequestBody Map<String, Object> map, @PathVariable("page") int page,@PathVariable("length") int length)
        throws Exception {
        return comBondQuotationInfoService.getFinprodAbbrOption(map,page,length);
    }

    /**
     * 删除
     * 
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondQuotationInfoService.delete(dto);
    }

    /**
     * 插入
     * 
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondQuotationInfoService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondQuotationInfoService.update(dto);
    }

    /**
     * 根据债券代码查询
     * 
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("getBySecId")
    public MstBondValuationInfo getBySecId(String secId)
        throws Exception {
        return comBondQuotationInfoService.getBySecId(secId);
    }

    /**
     * 根据债券代码查询
     * 
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("selectByKey")
    public MstBondValuationInfo selectByKey(String secId, String valueDate, String valueSource)
        throws Exception {
        return comBondQuotationInfoService.selectByKey(secId, valueDate, valueSource);
    }


    /**
     * 查询所有符合条件的数据
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectAllList")
    public List<ComBondQuotationInfoDto> selectAllList(@RequestBody Map<String, Object> param)
            throws Exception {
        return comBondQuotationInfoService.selectAllList(param);
    }


   /**
    *
    * 插入数据
    * @param equityList 参数
    * @see
    * @since 1.0
    */
   @Transactional
   @DTXTransaction
   @PostMapping("/mergeInfoList")
   public void mergeInfoList(@RequestBody List<ComBondQuotationInfoDto> equityList){
	   comBondQuotationInfoService.mergeInfoList(equityList);
   }

}

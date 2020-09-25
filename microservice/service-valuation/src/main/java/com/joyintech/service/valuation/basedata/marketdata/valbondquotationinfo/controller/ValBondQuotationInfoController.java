/*
 * 文件名：ValBondQuotationInfoController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangzehui
 * 修改时间：2018年7月17日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dto.ValBondQuotationInfoDto;
import com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.service.ValBondQuotationInfoService;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see ValBondQuotationInfoController
 * @since 1.0
 */
@RestController("ValBondQuotationInfoCtr_dtx")
@RequestMapping("/ValBondQuotationInfo")
public class ValBondQuotationInfoController {

    /**
     * 
     */
    @Autowired
    private ValBondQuotationInfoService valBondQuotationInfoService;

    /**
     * 债券代码下拉选择框
     * 
     * @return list结果集
     * @throws Exception 异常
     */
    @GetMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String secId)
        throws Exception {
        return valBondQuotationInfoService.getSecIdOption(secId);
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
        return valBondQuotationInfoService.getSecIdToNew(secId);
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
        return valBondQuotationInfoService.getFinProdAbbr(finprodAbbr);
    }

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValBondQuotationInfoDto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return valBondQuotationInfoService.selectForPage(page, length, param);
    }

    /**
     * 获取金融产品简称
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getFinprodAbbrOption")
    public List<SelectOptionDto> getFinprodAbbrOption(@RequestBody Map<String, Object> map)
        throws Exception {
        return valBondQuotationInfoService.getFinprodAbbrOption(map);
    }

    /**
     * 删除
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return valBondQuotationInfoService.delete(dto);
    }

    /**
     * 插入
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return valBondQuotationInfoService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return valBondQuotationInfoService.update(dto);
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
        return valBondQuotationInfoService.getBySecId(secId);
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
        return valBondQuotationInfoService.selectByKey(secId, valueDate, valueSource);
    }

}

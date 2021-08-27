/*
 * 文件名：FinPlanAssetCController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.plan.profitproject.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.plan.plancur.dto.FinPlanAssetCDto;
import com.joyintech.service.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import com.joyintech.service.valuation.basicdata.asset.plan.profitproject.service.ValProfitprojectService;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 非标债权及利率型资产
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see ValProfitprojectController
 * @since 1.0
 */
@RestController(value="serviceValProfitprojectController")
@RequestMapping("/ValProfitproject")
public class ValProfitprojectController {
    @Autowired
    private ValProfitprojectService valProfitprojectService;
    /**
     * 画面查询方法
     * 
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValProfitprojectDto> findPage(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valProfitprojectService.selectForPage(page, length, param);
    }

    @PostMapping("selectForList")
    public List<ValProfitprojectDto> selectForList(@RequestBody Map<String, Object> param) throws Exception {
        return valProfitprojectService.selectForList(param);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ValProfitprojectDto dto) throws Exception {
        return valProfitprojectService.delete(dto);
    }

    /**
     * 插入
     * 页面选中的数据
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ValProfitprojectDto dto) throws Exception {
        return valProfitprojectService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody ValProfitprojectDto dto) throws Exception {
        return valProfitprojectService.update(dto);
    }
    /**
     * 初始化查询数据
     * 
     * @param param 画面参数
     * @return 查询结果
     * @throws Exception 异常
     */
    @PostMapping("getBasicInfoByFinprodId")
    public ValProfitprojectDto getBasicInfoByFinprodId(@RequestBody Map<String, Object> param) throws Exception {
        return valProfitprojectService.getBasicInfoByFinprodId(param);
    }
    /**
     * 初始化查询数据
     *
     * @param param 画面参数
     * @return 查询结果
     * @throws Exception 异常
     */
    @PostMapping("isDealCalendarHoliday")
    public Integer isDealCalendarHoliday(@RequestBody Map<String, Object> param) throws Exception {
        return valProfitprojectService.isDealCalendarHoliday(param);
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
    @GetMapping("getFinprodId")
    public String getFinprodId(String finprodMarketId){
        return valProfitprojectService.getFinprodId(finprodMarketId);
    }
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/
    /*增加资产代码重复校验 add by zhuyongkang 20191021 start*/
    /**
     * 查询市场代码
     * @param finprodMarketId 市场代码
     */
    @GetMapping("selectFinprodMarketId")
    public int selectFinprodMarketId( String finprodMarketId){
        return valProfitprojectService.selectFinprodMarketId(finprodMarketId);
    }
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
    /**
     * 发行人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getIssuer/{page}/{length}")
    public Page<SelectOptionDto> getIssuer(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        param.put("IS_ISSUER", DictCodeList.是否_是.getValue());
        return valProfitprojectService.getCustomer(page, length, param);
    }
    /**
     * 管理人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getManager/{page}/{length}")
    public Page<SelectOptionDto> getManager(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        param.put("IS_ASSTE_MANAGER", DictCodeList.是否_是.getValue());
        return valProfitprojectService.getCustomer(page, length, param);
    }

    /**
     * 托管人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getTruster/{page}/{length}")
    public Page<SelectOptionDto> getTruster(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        param.put("IS_TRUSTER", DictCodeList.是否_是.getValue());
        return valProfitprojectService.getCustomer(page, length, param);
    }

    /**
     * 融资人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getFinancier/{page}/{length}")
    public Page<SelectOptionDto> getFinancier(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        param.put("IS_FINANCIER", DictCodeList.是否_是.getValue());
        return valProfitprojectService.getCustomer(page, length, param);
    }

    /**
     * 担保人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getGuarantee/{page}/{length}")
    public Page<SelectOptionDto> getGuarantee(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        param.put("IS_GUARANTEE", DictCodeList.是否_是.getValue());
        return valProfitprojectService.getCustomer(page, length, param);
    }

    @GetMapping("getAssetId")
    public List<SuggestDto> getAssetId(String search, String finprodType2, String profitType, String child) {
        Map<String, Object> param = new HashMap<>();
        param.put("search",search);
        param.put("finprodType2",finprodType2);
        param.put("profitType",profitType);
        param.put("child",child);
        return valProfitprojectService.getAssetId(param);
    }

    /**
     * 资产下拉框
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getAssetSelect/{page}/{length}")
    public Page<SelectOptionDto> getAssetSelect(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        return valProfitprojectService.getAssetSelect(page, length, param);
    }

}

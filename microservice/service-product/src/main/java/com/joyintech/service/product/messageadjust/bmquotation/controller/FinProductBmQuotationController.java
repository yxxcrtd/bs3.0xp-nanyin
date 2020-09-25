/*
 * 文件名：FinProductBmQuotationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.bmquotation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.service.product.messageadjust.bmquotation.service.FinProductBmQuotationService;

/**
 * 微服务-产品业绩基准报价管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationController
 * @since 1.0
 */
@RestController("backFinProductBmQuotationController_dtx")
@RequestMapping("/FinProductBmQuotation")
public class FinProductBmQuotationController {
    @Autowired
    private FinProductBmQuotationService finProductBmQuotationService;
    /**
     * 产品代码联想框 
     * @param param
     * @return
     */
    @PostMapping("/getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(@RequestBody Map<String, Object> param) {
        return finProductBmQuotationService.getFinprodMarketIdList(param);
    }
    
    /**
     * 新建产品代码联想框 
     * @param param
     * @return
     */
    @PostMapping("/getFinprodMarketIdToNew")
    public List<SelectOptionDto> getFinprodMarketIdToNew(@RequestBody Map<String, Object> param) {
        return finProductBmQuotationService.getFinprodMarketIdToNew(param);
    }
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinProductBmQuotationDto> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProductBmQuotationService.selectForPage(page, length, param);
    }
    

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectForList")
    public List<FinProductBmQuotationDto> selectForList(@RequestBody Map<String, Object> param) throws Exception {
        return finProductBmQuotationService.selectForList(param);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinProductBmQuotationDto dto) throws Exception {
        return finProductBmQuotationService.delete(dto);
    }
    
    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinProductBmQuotationDto dto) throws Exception {
        return finProductBmQuotationService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinProductBmQuotationDto dto) throws Exception {
        return finProductBmQuotationService.update(dto);
    }
    /**
     * 根据金融产品代码和生效日获得页面展示元素
     * <功能详细描述>
     * @param param param
     * @return FinProductBmQuotationDto
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByFinprodId")
    public FinProductBmQuotationDto selectByFinprodId(@RequestBody Map<String, Object> param) throws Exception {
        return finProductBmQuotationService.selectByFinprodId(param);
    }       
    /**
     * 根据基准代码和生效日查询数据
     * <功能详细描述>
     * @param dto dto
     * @return list
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByFinprodIdAndEffDate")
    public List<FinProductBmQuotationDto> selectByFinprodIdAndEffDate(@RequestBody FinProductBmQuotationDto dto) throws Exception {
        return finProductBmQuotationService.selectByFinprodIdAndEffDate(dto);
    }
    /**
     * 根据基准代码和生效日查询数据
     * <功能详细描述>
     * @param finprodId finprodId
     * @return list
     * @see
     * @since 1.0
     */
    @GetMapping("/getMdate")
    public FinProductBmQuotationDto getMdate(String finprodId) throws Exception {
        return finProductBmQuotationService.getMdate(finprodId);
    }
    /**
     * 判断生效日后是否发生了浮动管理费计提、支付交易
     * <功能详细描述>
     * @param dto dto
     * @return Integer Integer
     * @see
     * @since 1.0
     */
     @PostMapping("/getCount")
     public Integer getCount(@RequestBody FinProductBmQuotationDto dto) throws Exception {
         return finProductBmQuotationService.getCount(dto);
     }
}

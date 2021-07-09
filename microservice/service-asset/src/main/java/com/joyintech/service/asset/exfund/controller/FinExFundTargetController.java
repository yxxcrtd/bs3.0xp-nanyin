/*
 * 文件名：FinExFundTargetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V3.0
 * 修改人：meifaxin
 * 修改时间：2021年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.exfund.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.exfund.dto.BussExFinIntPayDto;
import com.joyintech.service.asset.exfund.dto.BussRepExFundInfoDto;
import com.joyintech.service.asset.exfund.dto.FinExFundTargetDto;
import com.joyintech.service.asset.exfund.service.FinExFundTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 场内基金类标的信息的微服务controller
 * 〈功能详细描述〉
 *
 * @author meifaxin
 * @version 1.0 2021年7月8日
 * @see FinExFundTargetController
 * @since 1.0
 */
@RestController("FinExFundTargetController_dtx")
@RequestMapping("/FinExFundTarget")
public class FinExFundTargetController {
    @Autowired
    private FinExFundTargetService finExFundTargetService;

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinExFundTargetDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        Page<FinExFundTargetDto> page1 = finExFundTargetService.selectForPage(page, length, param);
        return page1;
    }

    /**
     * 查询资产代码
     * <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId)
            throws Exception {
        return finExFundTargetService.getFinprodMarketIdList(finprodMarketId);
    }

    /**
     * 查询基金管理人
     * <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getIssuer")
    public List<SelectOptionDto> getIssuer()
            throws Exception {
        return finExFundTargetService.getIssuer();
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException    IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody FinExFundTargetDto dto) throws IllegalAccessException, InvocationTargetException {
        finExFundTargetService.doInsert(dto);
    }

    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinExFundTargetDto dto) throws Exception {
        return finExFundTargetService.delete(dto);
    }

    /**
     * 插入
     *
     * @param dto 页面选中的数据
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinExFundTargetDto dto) throws Exception {
        return finExFundTargetService.insert(dto);
    }

    /**
     * 更新
     *
     * @param dto 页面选中的数据
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinExFundTargetDto dto) throws Exception {
        return finExFundTargetService.update(dto);
    }

    /**
     * 通过金融产品代码查询dto
     * <功能详细描述>
     *
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("selectByFinprodId")
    public FinExFundTargetDto selectByFinprodId(String finprodId)
            throws Exception {
        return finExFundTargetService.selectByFinprodId(finprodId);
    }

    /**
     * 通过市场代码查询dto
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findEntity")
    public FinExFundTargetDto findEntity(@RequestBody Map<String, Object> param)
            throws Exception {
        return finExFundTargetService.findEntity(param);
    }

    /**
     * 给分红计划页面赋值
     * <功能详细描述>
     *
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("/getIntPayByFinprodId")
    public BussExFinIntPayDto getIntPayByFinprodId(String finprodId)
            throws Exception {
        return finExFundTargetService.getIntPayByFinprodId(finprodId);
    }

    /**
     * 给基金中债报送页面赋值
     * <功能详细描述>
     *
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("/getFundInfoByFinprodId")
    public BussRepExFundInfoDto getFundInfoByFinprodId(String finprodId)
            throws Exception {
        return finExFundTargetService.getFundInfoByFinprodId(finprodId);
    }

    @PostMapping("getInfoByIssuer")
    public BussRepExFundInfoDto getInfoByIssuer(@RequestBody Map<String, Object> pageParam) {
        return finExFundTargetService.getInfoByIssuer(pageParam);
    }

}

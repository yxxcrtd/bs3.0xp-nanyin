/*
 * 文件名：FinFuturesController.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.service.asset.futures.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.futures.dto.FinFuturesDto;
import com.joyintech.service.asset.futures.service.FinFuturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 期货类标的信息的微服务controller 〈功能详细描述〉
 *
 * @author 余飞
 * @version 1.0 2020年11月09日
 * @see FinFuturesController
 * @since 1.0
 */
@RestController("FinFuturesController_dtx")
@RequestMapping("/FinFutures")
public class FinFuturesController {

    @Autowired
    private FinFuturesService finFuturesService;

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinFuturesDto> findPage(
        @RequestBody
            Map<String, Object> param,
        @PathVariable("page")
            int page,
        @PathVariable("length")
            int length)
        throws Exception {
        return finFuturesService.selectForPage(page, length, param);
    }

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId)
        throws Exception {
        return finFuturesService.getFinprodMarketIdList(finprodMarketId);
    }

    /**
     * 通过金融产品代码查询dto <功能详细描述>
     *
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("selectByFinprodId")
    public FinFuturesDto selectByFinprodId(String finprodId)
        throws Exception {
        return finFuturesService.selectByFinprodId(finprodId);
    }

    /**
     * 通过市场代码查询dto <一句话功能简述> <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findEntity")
    public FinFuturesDto findEntity(
        @RequestBody
            Map<String, Object> param)
        throws Exception {
        return finFuturesService.findEntity(param);
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
    public int delete(
        @RequestBody
            FinFuturesDto dto)
        throws Exception {
        return finFuturesService.delete(dto);
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
    public int insert(
        @RequestBody
            FinFuturesDto dto)
        throws Exception {
        return finFuturesService.insert(dto);
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
    public int update(
        @RequestBody
            FinFuturesDto dto)
        throws Exception {
        return finFuturesService.update(dto);
    }
}

/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribboutrepo.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo001Dto;
import com.joyintech.service.entrust.etribboutrepo.service.EtrIbBoutRepo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 场内交易委托
 * 
 * @author zhangnian
 * @version 1.0 2019年10月24日
 * @see EtrIbBoutRepo001Controller
 * @since 1.0
 */
@RestController("EtrIbBoutRepo001Ctr_dtx")
@RequestMapping("EtrIbBoutRepo001")
public class EtrIbBoutRepo001Controller {
    @Autowired
    private EtrIbBoutRepo001Service etrIbBoutRepo001Service;

    /**
     * 检索产品指令管理分页数据。 <功能详细描述>
     * 
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findZlglPage/{page}/{length}")
    public Page<EtrIbBoutRepo001Dto> findZlglPage(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return etrIbBoutRepo001Service.findZlglPage(page, length, pageParam);
    }
    
    /**
     * 检索质押券管理分页数据。
     * 
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findZlglPositionPage/{page}/{length}")
    public Page<EtrIbBoutRepo001Dto> findZlglPositionPage(@RequestBody Map<String, Object> pageParam,
                                                          @PathVariable("page") int page,
                                                          @PathVariable("length") int length)
                    throws Exception {
        return etrIbBoutRepo001Service.findZlglPositionPage(page, length, pageParam);
    }

}
/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.service.entrust.etribrepo.service.EtrIbRepo001Service;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondSearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 场内交易委托
 * 
 * @author zhangnian
 * @version 1.0 2019年10月24日
 * @see EtrIbRepo001Controller
 * @since 1.0
 */
@RestController("EtrIbRepo001Ctr_dtx")
@RequestMapping("EtrIbRepo001")
public class EtrIbRepo001Controller {
    @Autowired
    private EtrIbRepo001Service etrIbRepo001Service;

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
    public Page<EtrIbRepo001Dto> findZlglPage(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return etrIbRepo001Service.findZlglPage(page, length, pageParam);
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
    public Page<EtrIbRepo001Dto> findZlglPositionPage(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page,
            @PathVariable("length") int length)
                    throws Exception {
        return etrIbRepo001Service.findZlglPositionPage(page, length, pageParam);
    }

    @PostMapping("/getSecManageAcctId/{page}/{length}")
    public Page<SelectOptionDto> getSecManageAcctId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return etrIbRepo001Service.getSecManageAcctId(page, length, param);
    }
    
    /**
     * 获得质押券库
     * @param pageParam
     * @return
     */
    @PostMapping("getAssetName")
    public List<EtrIbRepoRk004Dto> getAssetName(@RequestBody Map<String, Object> pageParam) {
        return etrIbRepo001Service.getAssetName(pageParam);
    }
    
    /**
     * 质押券出库,删除即可
     *
     * @param param 参数
     * @return outSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("outSave")
    public Long outSave(@RequestBody EtrIbRepo001Dto dto) throws Exception {
        return etrIbRepo001Service.outSave(dto);
    }
    
}
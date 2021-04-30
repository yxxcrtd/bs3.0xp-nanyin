/*
 * 文件名：EntrustCommonPersonSelectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.common.service.EntrustCommonPersonSelectService;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 投资委托人员信息
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月9日
 * @see EntrustCommonPersonSelectController
 * @since 1.0
 */
@RestController
@RequestMapping("/common/EntrustCommonPersonSelect")
public class EntrustCommonPersonSelectController {

    /**
     * 投资委托人员信息service
     */
    @Autowired
    private EntrustCommonPersonSelectService entrustCommonPersonSelectService;
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterId/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getCounterId(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return entrustCommonPersonSelectService.getCounterId(page,length,map);
    }
    
    /**
     * 交易对手(市场机构)
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getCounterIdDepat/{page}/{length}")
    public Page<SelectOptionDto> getCounterIdDepat(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length){
        return entrustCommonPersonSelectService.getCounterIdDepat(page, length,param);
    }
    /**
     * 
     * 查询本方交易员
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getTrader/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getTrader(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return entrustCommonPersonSelectService.getTrader(page,length,map);
    }
    
    @GetMapping("getUser")
    @ResponseBody
    public Map<String, Object> getUser(String userName){
        return entrustCommonPersonSelectService.getUser(userName);
    }

    @PostMapping("/findSameNameCounterId")
    public List<String> findSameNameCounterId(@RequestBody Map<String, Object> map) {
        return entrustCommonPersonSelectService.findSameNameCounterId(map);
    }
    
    /**
     * 查询本方交易员列表
     * 
     * @param param 入参
     * @param page 页数
     * @param length 步长
     * @return 委托列表
     * @see
     * @since 1.0
     */
    @PostMapping("getCfetsTrader/{page}/{length}")
    public Page<SelectOptionDto> getCfetsTrader(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length){
        return entrustCommonPersonSelectService.getCfetsTrader(page,length,param);
    }
    
    /**
     * 
     * 查询交易对手_银行间
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterIdCfets/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getCounterIdCfets(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return entrustCommonPersonSelectService.getCounterIdCfets(page,length,map);
    }
}

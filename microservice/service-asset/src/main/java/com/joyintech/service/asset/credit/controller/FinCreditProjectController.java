/*
 * 文件名：FinCreditProjectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.credit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.credit.dto.InterfCreditItemInfoDto;
import com.joyintech.service.asset.credit.service.FinCreditProjectService;

/**
 * 信贷项目信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月15日
 * @see FinCreditProjectController
 * @since 1.0
 */
@RestController("FinCreditProjectController_dtx")
@RequestMapping("/FinCreditProject")
public class FinCreditProjectController {

    @Autowired
    private FinCreditProjectService finCreditProjectService;
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<InterfCreditItemInfoDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finCreditProjectService.selectForPage(page, length, param);
    }
    
    /**
     * 信贷项目撤销
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody InterfCreditItemInfoDto dto ){
        return finCreditProjectService.update(dto);
    }
    
    
    @GetMapping("getCashOutNo")
    public List<SuggestDto> getCashOutNo(String search){
        return finCreditProjectService.getCashOutNo(search);
    }
}

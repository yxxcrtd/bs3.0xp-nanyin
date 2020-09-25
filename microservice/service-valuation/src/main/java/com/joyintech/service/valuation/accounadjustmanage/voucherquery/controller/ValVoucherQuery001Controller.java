/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.voucherquery.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQueryPrintDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.service.ValVoucherQuery001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 模板示例<br>
 * 凭证查询
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Controller
 * @since 2.0
 */
@RestController(value="serviceValVoucherQuery001Controller")
@RequestMapping("ValVoucherQuery001")
public class ValVoucherQuery001Controller extends BaseController {
    
    /**
     * ValVoucherQuery001Service
     */
    @Autowired
    private ValVoucherQuery001Service valVoucherQuery001Service;

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @GetMapping("/getBooksetId")
    public List<SelectOptionDto> getBooksetId() {
        return valVoucherQuery001Service.getBooksetId();
    }

    /**
     * 业务类别下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @GetMapping("/getBusiClass")
    public List<SelectOptionDto> getBusiClass() {
        return valVoucherQuery001Service.getBusiClass();
    }

    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValVoucherQuery001Dto> findPage(@RequestBody Map<String , ValVoucherQuery001SearchDto> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        ValVoucherQuery001SearchDto dto = param.get("dto");
        return valVoucherQuery001Service.findPage(page,length,dto);

    }

    @PostMapping("/selectAll")
    public List<ValVoucherQuery001Dto> selectAll(@RequestBody ValVoucherQuery001SearchDto dto) {
        return valVoucherQuery001Service.selectAll(dto);
        
    }

    /**
     * 查询凭证打印信息
     * @param param
     * @return
     */
    @PostMapping("/findPrintInfo")
    public ValVoucherQueryPrintDto findPrintInfo(@RequestBody Map<String , Object> param) {
        return valVoucherQuery001Service.findPrintInfo(param);

    }
}

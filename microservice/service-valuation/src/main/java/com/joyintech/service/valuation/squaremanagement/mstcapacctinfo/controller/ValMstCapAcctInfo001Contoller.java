/*
 * 文件名：ValMstCapAcctInfo001Contoller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0

 */
 
package com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;
import com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.service.ValMstCapAcctInfoService;

@RestController("valMstCapAcctInfo001Ctl_dtx")
@RequestMapping("/ValMstCapAcctInfo001")
public class ValMstCapAcctInfo001Contoller extends BaseController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValMstCapAcctInfoService valMstCapAcctInfoService;
//    /**
//     * 重写init方法，加载页面
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        return super.init(model);
//    }

//    @ResponseBody
//    @RequestMapping("findPage")
//    public Page<ValMstCapAcctInfoDto> queryPage()
//        throws Exception {
//        Page<ValMstCapAcctInfoDto> page = valMstCapAcctInfoService.findPage(RequestUtil.getParameters(getRequest()));
//        return page;
//    }
    
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValMstCapAcctInfoDto> queryPage(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valMstCapAcctInfoService.findPage(page, length, param);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public boolean delete(@RequestBody ValMstCapAcctInfoDto dto) 
		throws Exception{
        return valMstCapAcctInfoService.deleteValMatAcctInfoByAcctno(dto);
    }

}

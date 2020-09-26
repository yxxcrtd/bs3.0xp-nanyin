/*
 * 文件名：ValMstCapAcctInfo001Contoller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0

 */
 
package com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.service.ValMstCapAcctInfoService;

@Controller
@PageId("ValMstCapAcctInfo001")
@RequestMapping("/valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo001")
public class ValMstCapAcctInfo001Contoller extends BaseController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValMstCapAcctInfoService valMstCapAcctInfoService;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValMstCapAcctInfoDto> queryPage()
        throws Exception {
        Page<ValMstCapAcctInfoDto> page = valMstCapAcctInfoService.findPage(RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ValMstCapAcctInfoDto dto) 
		throws Exception{
    	String msg = "";
    	boolean flag = valMstCapAcctInfoService.deleteValMatAcctInfoByAcctno(dto);
    	if(flag){
    		msg = "删除成功！";
    	}else{
    		msg = "删除失败！";
    	}
        return new AjaxResult(msg);
    }

}

/*
 * 文件名：FinCreditProjectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.asset.credit.dto.InterfCreditItemInfoDto;
import com.joyintech.tams.invest.asset.credit.service.FinCreditProjectService;

/**
 *  信贷项目信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月15日
 * @see FinCreditProjectController
 * @since 1.0
 */
@Controller
@PageId("FinCreditProject001")
@RequestMapping("/invest/asset/credit/FinCreditProject001")
public class FinCreditProject001Controller extends BaseController{

    @Autowired
    private FinCreditProjectService finCreditProjectService;
    @Override
    public String init(Model model){
        return super.init(model);
    }
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<InterfCreditItemInfoDto> selectAll()
        throws Exception {
        Page<InterfCreditItemInfoDto> page = finCreditProjectService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    
    /**
     * 出账编号下拉文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCashOutNo")
    public List<SuggestDto> getCashOutNo(){
        String search = this.getParam("param")==null?StringUtil.EMPTY:this.getParam("param");
        return finCreditProjectService.getCashOutNo(search);
    }
    
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finCreditProjectService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    
}

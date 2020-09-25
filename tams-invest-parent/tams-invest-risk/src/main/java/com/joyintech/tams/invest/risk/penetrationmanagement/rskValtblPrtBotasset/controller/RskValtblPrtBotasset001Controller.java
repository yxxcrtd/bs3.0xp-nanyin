/*
 * 文件名：RskPortfolioasset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;

/**
 *  底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月2日
 * @see RskPortfolioasset001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValtblPrtBotasset001")
@RequestMapping("/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset001")
public class RskValtblPrtBotasset001Controller extends BaseController{
  
    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, new DictCode[] {DictCode.映射状态});
        model.addAttribute("mappingStatInit", DictCodeList.映射状态_未映射.getValue());
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
    public Page<RskValtblPrtBotassetMapDto> selectAll()
        throws Exception {
        Page<RskValtblPrtBotassetMapDto> page = rskValtblPrtBotassetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
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
        return rskValtblPrtBotassetService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
}

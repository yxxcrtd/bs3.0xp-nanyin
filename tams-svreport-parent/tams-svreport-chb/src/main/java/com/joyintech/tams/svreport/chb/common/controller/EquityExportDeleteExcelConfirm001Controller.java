/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：导出删除模板共通页面
 * 修改人：Administrator
 * 修改时间：2019年5月30日
 * 修改内容：新建
 * 系统名称：中债报送监管系统
 */
 
package com.joyintech.tams.svreport.chb.common.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.common.service.EquityExportDeleteExcelConfirmMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * 导出删除模板共通逻辑
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see EquityExportDeleteExcelConfirm001Controller
 * @since 1.0
 */
@Controller
@PageId("EquityExportDeleteExcelConfirm001")
@RequestMapping("/chb/common/EquityExportDeleteExcelConfirm001")
public class EquityExportDeleteExcelConfirm001Controller extends BaseController {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private EquityExportDeleteExcelConfirmMService equityExportDeleteExcelConfirmMService;
    
    
    /**
     * 初始化
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 原页面pageId
        String orgPageId = this.getParam("orgPageId");
        // 选中数据
        String expKeys = this.getParam("expKeys");
        if (StringUtil.isNotEmpty(orgPageId)
                && !"undefined".equals(orgPageId)) {
            model.addAttribute("orgPageId",orgPageId);
        }
        if (StringUtil.isNotEmpty(expKeys)
                && !"undefined".equals(expKeys)) {
            model.addAttribute("expKeys",expKeys);
        }
        String paramKeys = this.getParam("paramKeys");
        model.addAttribute("paramKeys",paramKeys);

        String keyList = this.getParam("keyList");
        model.addAttribute("keyList",keyList);
        return super.init(model);
    }
    
    /**
     * 功能:导出删除excel
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @return 
     * ResponseEntity<byte[]>
     */
    @RequestMapping(value = "/exportDel")
    public ResponseEntity<byte[]> exportDel() {
        // 查询参数
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        pageParam.put("pageId",pageParam.get("orgPageId"));
        return equityExportDeleteExcelConfirmMService.export(pageParam);
    }

}

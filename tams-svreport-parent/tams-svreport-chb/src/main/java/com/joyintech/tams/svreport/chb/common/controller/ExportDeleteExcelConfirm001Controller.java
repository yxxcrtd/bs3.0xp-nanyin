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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.common.service.ExportDeleteExcelConfirm001Service;
import com.joyintech.tams.svreport.chb.common.service.ExportDeleteExcelConfirmMService;


/**
 * 导出删除模板共通逻辑
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see ExportDeleteExcelConfirm001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrExportDeleteExcelConfirm001")
@RequestMapping("/chb/common/ExportDeleteExcelConfirm001")
public class ExportDeleteExcelConfirm001Controller extends BaseController {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ExportDeleteExcelConfirmMService exportDeleteExcelConfirmMService;
    
    
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
        if (pageParam.containsKey("expKeys")){
            pageParam.put("export_method_flg", "exportSelect");
        } else {
            pageParam.put("export_method_flg", "export");
        }
        // 原页面id
        String pageId = (String) pageParam.get("orgPageId");
        return exportDeleteExcelConfirmMService.export(pageId,pageParam);
    }

}

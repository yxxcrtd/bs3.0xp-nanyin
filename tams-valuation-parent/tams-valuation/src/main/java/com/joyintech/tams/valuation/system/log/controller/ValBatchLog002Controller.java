/*
 * 文件名：ValBatchLog001Controller2.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ww1
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.system.log.controller;

import java.io.File;
import java.util.Date;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.system.log.dto.BokBatchMarketwarningInfoDto;
import com.joyintech.tams.valuation.system.log.dto.ValBatchLog001Dto;
import com.joyintech.tams.valuation.system.log.service.ValBatchLog002Service;

/**
 * 警告信息
 * 
 * @author 
 * @version 1.0 2018年3月14日
 * @see ValBatchLog002Controller
 * @since 1.0
 */
@Controller
@PageId("ValBatchLog002")
@RequestMapping("/valuation/system/log/ValBatchLog002")
public class ValBatchLog002Controller extends BaseController {

	   @Autowired
	    private ValBatchLog002Service valBatchLog002Service;
	   
    /**
     * {@inheritDoc}
     */
   @RequestMapping(PAGE_INIT)
    public String init(Model model) {
    	 model.addAttribute("batchExedetailSeqNo", this.getParam("batchExedetailSeqNo"));
    	 model.addAttribute("taskId", this.getParam("taskId"));
        return super.init(model);
    }

    /**
     * 明细表格数据
     * 
     * @return 表格信息
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "findPage")
    public Page<BokBatchMarketwarningInfoDto> findPage() throws Exception {
        return valBatchLog002Service.findWarnLogDetail(RequestUtil.getPageParam());
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        List<BokBatchMarketwarningInfoDto> dtoList = valBatchLog002Service.selectForExport(RequestUtil.getPageParam());
        File outFile = valBatchLog002Service.makeFile(
            "/com/joyintech/tams/valuation/system/log/conf/export_valBatchLog002query.xml", dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}

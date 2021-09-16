package com.joyintech.service.pbc.classic.prdbaseinfosubdat.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.service.PbcPrdBaseinfoSubDatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 产品基本信息 服务
 * 〈功能详细描述〉
 * @author jinyangynang
 * @version 1.0 2021年7月27日
 * @see PbcPrdBaseinfoSubDatController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcPrdBaseInfoSubDat")
public class PbcPrdBaseinfoSubDatController {

    /**
     * 产品基本信息service
     */
    @Autowired
    private PbcPrdBaseinfoSubDatService pbcPrdBaseinfoSubDatService;

    /**
     *
     * 业务画面导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkBaseInfoSubDat")
    public AjaxResult checkBaseInfoSubDat(String fileName) {
        return pbcPrdBaseinfoSubDatService.checkBaseInfoSubDat(fileName);
    }

    /**
     *
     * 业务画面导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("importBaseInfoSubDat")
    public List<ExcelValidDto> importBaseInfoSubDat(String fileName) {
        return pbcPrdBaseinfoSubDatService.importBaseInfoSubDat(fileName);
    }
}

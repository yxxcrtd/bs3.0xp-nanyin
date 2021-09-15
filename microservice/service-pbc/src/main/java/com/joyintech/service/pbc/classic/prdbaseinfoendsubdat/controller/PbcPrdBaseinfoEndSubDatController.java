package com.joyintech.service.pbc.classic.prdbaseinfoendsubdat.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.service.PbcPrdBaseinfoEndSubDatService;
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
 * @author Jason.Jin
 * @see PbcPrdBaseinfoEndSubDatController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcPrdBaseInfoEndSubDat")
public class PbcPrdBaseinfoEndSubDatController {

    /**
     * 产品基本信息service
     */
    @Autowired
    private PbcPrdBaseinfoEndSubDatService pbcPrdBaseinfoEndSubDatService;

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
        return pbcPrdBaseinfoEndSubDatService.checkBaseInfoSubDat(fileName);
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
        return pbcPrdBaseinfoEndSubDatService.importBaseInfoSubDat(fileName);
    }
}

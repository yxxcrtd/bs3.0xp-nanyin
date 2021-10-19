package com.joyintech.tams.business.common.controller;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.business.common.constant.Constant;
import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.CfgDataTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/config/dataTable")
@Api(tags = "数据表格设置")
public class CfgDataTableController {

    private static final Logger logger = LoggerFactory.getLogger(CfgDataTableController.class);

    @Autowired
    private CfgDataTableService cfgDataTableService;

    @ApiOperation(value = "查询数据表格设置", notes = "查询数据表格设置")
    @GetMapping("/getDataTable/{pageCode}")
    public Message<Map<String, List<CfgDataTableShow>>> getDataTable(@PathVariable("pageCode") String pageCode) {
        try {
            if (StringUtil.isEmpty(pageCode)) {
                return new Message<Map<String, List<CfgDataTableShow>>>().setMessage(Constant.MESSAGE_PAGECODE_NULL);
            }
            return new Message<>(cfgDataTableService.getByPageCode(pageCode));
        } catch (Exception e) {
            logger.error("查询数据表格设置", e);
            return new Message<Map<String, List<CfgDataTableShow>>>().fail();
        }
    }

    @ApiOperation(value = "根据pageCode和dtCode查询数据表格式设置", notes = "根据pageCode和dtCode查询数据表格式设置")
    @GetMapping("/getDataTable/{pageCode}/{dtCode}")
    public Message<List<CfgDataTableShow>> getByPageCodeAndDtCode(@PathVariable("pageCode") String pageCode,
                                                                               @PathVariable("dtCode") String dtCode) {
        try {
            if (StringUtil.isEmpty(pageCode) || StringUtil.isEmpty(dtCode)) {
                return new Message<List<CfgDataTableShow>>().setMessage(Constant.MESSAGE_PAGECODE_NULL);
            }
            return new Message<>(cfgDataTableService.getByPageCodeAndDtCode(pageCode,dtCode));
        } catch (Exception e) {
            logger.error("查询数据表格设置", e);
            return new Message<List<CfgDataTableShow>>().fail();
        }
    }

    @ApiOperation(value = "数据表格设置", notes = "数据表格设置")
    @PostMapping("/setDataTable")
    public Message<String> setDataTable(@RequestBody List<CfgDataTableShow> dataTableShowList) {
        try {
            cfgDataTableService.setDataTable(dataTableShowList);
            return new Message<String>().success();
        } catch (Exception e) {
            logger.error("数据表格设置", e);
            return new Message<String>().fail();
        }
    }
}

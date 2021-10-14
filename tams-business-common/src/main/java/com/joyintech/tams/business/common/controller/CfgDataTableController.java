package com.joyintech.tams.business.common.controller;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.CfgDataTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/config/dataTable")
@Api(tags = "数据表格设置")
public class CfgDataTableController {

    private static final Logger logger = LoggerFactory.getLogger(CfgDataTableController.class);

    @Autowired
	private CfgDataTableService cfgDataTableService;

    @ApiOperation(value = "查询配置分组分页列表", notes = "查询配置分组分页列表")
    @GetMapping("/pageCode/{pageCode}")
    public Message<Map<String, List<CfgDataTableShow>>> getInfo(@PathVariable("pageCode") String pageCode) {
    	try {
	    	if(StringUtil.isEmpty(pageCode)) {
	    		return new Message<Map<String, List<CfgDataTableShow>>>().setMessage("页面编码不能为空！");
	    	}
	        return new Message<>(cfgDataTableService.getByPageCode(pageCode));
    	}catch (Exception e) {
    		logger.error("获取配置分组详细信息失败",e);
			return new Message<Map<String, List<CfgDataTableShow>>>().fail();
		}
    }
}

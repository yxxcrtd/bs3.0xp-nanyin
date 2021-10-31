package com.joyintech.tams.cfets.etrib.controller;

import com.joyintech.background.component.common.pack.common.util.ArithmeticUtil;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.business.common.util.ExportCsvUtil;
import com.joyintech.tams.business.common.util.HttpUtil;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiPage001RequestDto;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfoService;
import com.joyintech.tams.cfets.etrib.service.TempService;
import com.joyintech.tams.cfets.etrib.socket.PositionSocketClient;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/cfets/etrib/tempController")
@Api(tags = "临时controller")
public class TempController {
    private static final Logger logger = LoggerFactory.getLogger(TempController.class);

    @Autowired
    private CfetsEtrIbBondinfoService cfetsEtrIbBondinfoService;
    
    @Autowired
    private TempService tempService;
    
    @Autowired
    private PositionSocketClient positionSocketClient;
    
    
    @ApiOperation(value = "test", notes = "test")
    @PostMapping("/test")
    public Message<AjaxResult> test() {
        try {
            tempService.test();
            return new Message().success().setData(null);
        }catch (Exception e) {
            logger.error("查询头寸信息分页列表异常",e);
            return new Message().fail().setMessage("查询头寸信息分页列表异常");
        }
    }
}

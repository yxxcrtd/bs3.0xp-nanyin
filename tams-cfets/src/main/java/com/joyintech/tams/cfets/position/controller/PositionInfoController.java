package com.joyintech.tams.cfets.position.controller;

import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiInfoRequestDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class PositionInfoController {

    @ApiOperation(value = "头寸信息查询", notes = "头寸信息查询")
    @PostMapping("/queryPositionInfo")
    @ResponseBody
    public Message<List<BaseMap>> queryPositionInfo(@RequestBody CfetsEtrIbBondPosiInfoRequestDto requestDto) {
//        List<BaseMap> m = cfetsEtrIbBondinfoService.queryPositionInfo(requestDto);
        return new Message<List<BaseMap>>().success().setData(null);
    }
    
}

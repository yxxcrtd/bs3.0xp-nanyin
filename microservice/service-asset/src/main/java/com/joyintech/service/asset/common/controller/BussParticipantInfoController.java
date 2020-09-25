/*
 * 文件名：BussParticipantInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.dto.BussParticipantInfoDto;
import com.joyintech.service.asset.common.service.BussParticipantInfoService;

/**
 *  参与方信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussParticipantInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/BussParticipantInfo")
public class BussParticipantInfoController {

    @Autowired
    private BussParticipantInfoService bussParticipantInfoService;
    
    @PostMapping("queryBussParticipantInfo")
    public List<BussParticipantInfoDto> queryBussParticipantInfo(@RequestBody Map<String,Object> paramMap) throws Exception {
        return bussParticipantInfoService.queryBussParticipantInfo(paramMap);
    }
    
    @PostMapping("queryAllinfoByfinprodId")
    public BussParticipantInfoDto queryAllinfoByfinprodId(@RequestBody Map<String,Object> paramMap) throws Exception {
        return bussParticipantInfoService.queryAllinfoByfinprodId(paramMap);
    }
    
    @PostMapping("selectAcctNoByCounterId")
    public List<SelectOptionDto> selectAcctNoByCounterId(@RequestBody Map<String,Object> paramMap){
        return bussParticipantInfoService.selectAcctNoByCounterId(paramMap);
    }
}

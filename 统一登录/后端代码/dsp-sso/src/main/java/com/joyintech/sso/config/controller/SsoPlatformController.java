package com.joyintech.sso.config.controller;

import com.github.pagehelper.PageInfo;
import com.joyintech.sso.common.BaseController;
import com.joyintech.sso.common.Message;
import com.joyintech.sso.config.dto.SsoPlatformDto;
import com.joyintech.sso.config.pojo.SsoPlatform;
import com.joyintech.sso.config.service.SsoPlatformService;
import com.joyintech.sso.config.service.SsoUserPlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sso/ssoplatform")
@Api(tags = "平台管理信息")
public class SsoPlatformController extends BaseController {

    @Resource
    private SsoPlatformService ssoPlatformService;

    @Resource
    private SsoUserPlatformService ssoUserPlatformService;

    @ApiOperation(value = "平台管理信息分页查询" ,notes = "平台管理信息分页查询")
    @RequestMapping(value = "/pageSsoPlatform",method = RequestMethod.POST)
    public Message<PageInfo<?>> pageSsoPlatform(@RequestBody SsoPlatformDto ssoPlatformDto) {
        try {
            startPage();
            List<SsoPlatform> ssoPlatformList = ssoPlatformService.listSsoPlatform(ssoPlatformDto);
            return pageMessage(ssoPlatformList);
        }
        catch (Exception e) {
            logger.error("平台管理信息分页查询失败",e);
            return new Message<PageInfo<?>>().fail();
        }

    }

    @ApiOperation(value = "根据id查询平台管理信息" ,notes = "根据id查询平台管理信息")
    @RequestMapping(value = "/selectSsoPlatfromById/{id}",method = RequestMethod.POST)
    public Message<SsoPlatform> selectSsoPlatfromById(@ApiParam(value = "平台代码", required = false)@PathVariable String id){
        try {
            SsoPlatform ssoPlatform = ssoPlatformService.selectSsoPlatfromById(id);
            return new Message<SsoPlatform>().setData(ssoPlatform).success();
        }
        catch (Exception e) {
            logger.error("根据id查询平台管理信息失败",e);
            return new Message<SsoPlatform>().fail();
        }
    }
    @ApiOperation(value = "新增平台管理信息" ,notes = "新增平台管理信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Message<String> insert(@RequestBody SsoPlatform ssoPlatform) {
        try {
            return ssoPlatformService.insertSsoPlatform(ssoPlatform);
        }
        catch (Exception e) {
            logger.error("新增平台管理信息失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "删除平台管理信息" ,notes = "删除平台管理信息")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public Message<String> delete(@ApiParam(value = "平台代码", required = false)@PathVariable String id) {
        try {
            ssoPlatformService.deleteSsoPlatformById(id);
            //删除用户的授权信息
            ssoUserPlatformService.deleteSsoUserPlatFormByPlatformid(id);
            return new Message<String>().success();
        }
        catch (Exception e) {
            logger.error("删除平台管理信息失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "更新平台管理信息" ,notes = "更新平台管理信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Message<String> update(@RequestBody SsoPlatform ssoPlatform) {
        try {
            return ssoPlatformService.updateSsoPlatform(ssoPlatform);
        }
        catch (Exception e) {
            logger.error("更新平台管理信息失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "启用/禁用平台信息" ,notes = "启用/禁用平台信息")
    @RequestMapping(value = "/updatestatus",method = RequestMethod.POST)
    public Message<String> updatestatus(@RequestBody SsoPlatform ssoPlatform) {
        try {
            return ssoPlatformService.updatestatus(ssoPlatform);
        }
        catch (Exception e) {
            logger.error("启用/禁用平台信息失败",e);
            return new Message<String>().fail();
        }
    }
}

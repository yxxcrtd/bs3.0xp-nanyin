package com.joyintech.sso.config.controller;

import com.github.pagehelper.PageInfo;
import com.joyintech.sso.common.BaseController;
import com.joyintech.sso.common.Message;
import com.joyintech.sso.common.dto.TreeNode;
import com.joyintech.sso.config.dto.GrantAuthorizationDto;
import com.joyintech.sso.config.dto.SsoUserPlatFormDto;
import com.joyintech.sso.config.pojo.SsoUserPlatForm;
import com.joyintech.sso.config.service.SsoUserPlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sso/ssouserplatform")
@Api(tags = "用户授权平台信息")
public class SsoUserPlatformController extends BaseController {

    @Resource
    private SsoUserPlatformService ssoUserPlatformService;

    @ApiOperation(value = "用户授权平台信息分页查询" ,notes = "用户授权平台信息分页查询")
    @RequestMapping(value = "/pageSsoPlatform",method = RequestMethod.POST)
    public Message<PageInfo<?>> pageSsoUserPlatForm(@RequestBody SsoUserPlatFormDto ssoUserPlatFormDto) {
        try {
            startPage();
            Map<String,Object> map = new HashMap<>();
            map.put("userid",ssoUserPlatFormDto.getUserid());
            map.put("platformid",ssoUserPlatFormDto.getPlatformid());
            map.put("platformcode",ssoUserPlatFormDto.getPlatformcode());
            map.put("platformname",ssoUserPlatFormDto.getPlatformname());
            List<SsoUserPlatForm> ssoUserPlatForm = ssoUserPlatformService.listSsoUserPlatForm(map);
            return pageMessage(ssoUserPlatForm);
        }
        catch (Exception e) {
            logger.error("用户授权平台信息分页查询失败",e);
            return new Message<PageInfo<?>>().fail();
        }

    }

    @ApiOperation(value = "根据id查询用户授权平台信息" ,notes = "根据id查询用户授权平台信息")
    @RequestMapping(value = "/selectSsoPlatfromById/{userid}/{platformid}",method = RequestMethod.POST)
    public Message<SsoUserPlatForm> selectSsoPlatfromById(@ApiParam(value = "用户编码", required = false)@PathVariable String userid,
                                                          @ApiParam(value = "平台代码", required = false)@PathVariable String platformid){
        try {
            SsoUserPlatForm ssoUserPlatForm = ssoUserPlatformService.selectSsoUserPlatFormById(userid,platformid);
            return new Message<SsoUserPlatForm>(ssoUserPlatForm);
        }
        catch (Exception e) {
            logger.error("根据id查询用户授权平台信息失败",e);
            return new Message<SsoUserPlatForm>().fail();
        }
    }
    @ApiOperation(value = "新增用户授权平台信息" ,notes = "新增用户授权平台信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Message<String> insert(@RequestBody SsoUserPlatForm ssoUserPlatForm) {
        try {
            return ssoUserPlatformService.insertSsoUserPlatForm(ssoUserPlatForm);
        }
        catch (Exception e) {
            logger.error("新增用户授权平台信息失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "删除用户授权平台信息" ,notes = "删除用户授权平台信息")
    @RequestMapping(value = "/delete/{userid}/{platformid}",method = RequestMethod.POST)
    public Message<String> delete(@ApiParam(value = "用户代码", required = false)@PathVariable String userid,
                                  @ApiParam(value = "平台代码", required = false)@PathVariable String platformid) {
        try {
            return ssoUserPlatformService.deleteSsoUserPlatFormById(userid,platformid);
        }
        catch (Exception e) {
            logger.error("删除用户授权平台信息失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "批量删除用户授权平台信息" ,notes = "批量删除用户授权平台信息")
    @RequestMapping(value = "/batchDelete/{userid}/{platformids}",method = RequestMethod.POST)
    public Message<String> delete(@ApiParam(value = "用户代码", required = false)@PathVariable String userid,
                                  @ApiParam(value = "平台代码", required = false)@PathVariable String[] platformids) {
        try {
            return ssoUserPlatformService.deleteSsoUserPlatFormByPlatformids(userid,platformids);
        }
        catch (Exception e) {
            logger.error("批量删除用户授权平台信息",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "机构用户分类列表" ,notes = "机构用户分类列表")
    @RequestMapping(value = "/listDeptAndUser",method = RequestMethod.POST)
    public Message<List<TreeNode>> listDeptAndUser(@RequestBody Map<String,String> map){
        try{
            List<TreeNode> treeNodeList = ssoUserPlatformService.getDeptAndUserTree(map);
            return new Message<List<TreeNode>>(treeNodeList);
        }catch (Exception e) {
            logger.error("查询用户列表异常",e);
            return new Message<List<TreeNode>>().fail();
        }
    }


    @ApiOperation(value = "用户授权" ,notes = "用户授权")
    @RequestMapping(value = "/grantAuthorizationForUser",method = RequestMethod.POST)
    public Message<String> grantAuthorizationForUser(@RequestBody GrantAuthorizationDto grantAuthorizationDto) {
        try {
            return ssoUserPlatformService.grantAuthorizationForUser(grantAuthorizationDto);
        }
        catch (Exception e) {
            logger.error("用户授权失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "平台授权" ,notes = "平台授权")
    @RequestMapping(value = "/grantAuthorizationForPlatForm",method = RequestMethod.POST)
    public Message<String> grantAuthorizationForPlatForm(@RequestBody GrantAuthorizationDto grantAuthorizationDto) {
        try {
            return ssoUserPlatformService.grantAuthorizationForPlatForm(grantAuthorizationDto);
        }
        catch (Exception e) {
            logger.error("平台授权失败",e);
            return new Message<String>().fail();
        }
    }

    @ApiOperation(value = "平台已授权用户信息" ,notes = "平台已授权用户信息")
    @RequestMapping(value = "/selectSsoUserListPlatFormById/{platformid}",method = RequestMethod.POST)
    public Message<List<String>> selectSsoUserListPlatFormById( @PathVariable String platformid) {
        try {
            return new Message<List<String>>().setData(ssoUserPlatformService.selectSsoUserListPlatFormById(platformid)).success();
        }
        catch (Exception e) {
            logger.error("平台已授权用户信息失败",e);
            return new Message<List<String>>().fail();
        }
    }
}

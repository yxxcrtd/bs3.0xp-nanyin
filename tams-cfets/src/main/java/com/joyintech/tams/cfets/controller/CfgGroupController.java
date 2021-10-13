package com.joyintech.tams.cfets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("${dsp.url.datarouter:datarouter}/cfgGroup")
@Api(tags = "分组管理")
public class CfgGroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(CfgGroupController.class);

//	@Autowired
//    private CfgGroupService cfgGroupService;
//	@Autowired
//	private UserInfoInterface userInfoInterface;
//	@Autowired
//	private SysCfgParamService sysCfgParamService;
//	
//	/**
//     * 查询配置分组类型
//     */
//    @ApiOperation(value = "查询配置分组类型", notes = "查询配置分组类型")
//    @RequestMapping(value = "/getGroupType", method = RequestMethod.GET)
//    public Message<String> getGroupType()
//    {
//    	try {
//			List<SysCfgParam> list = sysCfgParamService.listRouterGroupType();
//			if(list!=null && list.size()>0) {
//				SysCfgParam sysCfgParam = list.get(0);
//				return new Message<String>(sysCfgParam.getSpvalue());
//			}
//			return new Message<String>(DataRouterMessageCode.FAIL_GROUP_TYPE_NULL);
//    	}catch (Exception e) {
//    		logger.error("查询配置分组类型",e);
//			return new Message<String>().fail();
//		}
//    }
//
//    /**
//     * 查询配置分组分页列表
//     */
//    @ApiOperation(value = "查询配置分组分页列表", notes = "查询配置分组分页列表")
//    @RequestMapping(value = "/pageCfgGroup", method = RequestMethod.POST)
//    public Message<PageInfo<?>> pageCfgGroup(@RequestBody CfgGroup cfgGroup)
//    {
//    	try {
//	    	startPage();
//	        List<CfgGroup> list = cfgGroupService.list(cfgGroup);
//	        return pageMessage(list);
//    	}catch (Exception e) {
//    		logger.error("查询配置分组分页列表失败",e);
//			return new Message<PageInfo<?>>().fail();
//		}
//    }
//
//    /**
//     * 获取配置分组详细信息
//     */
//    @ApiOperation(value = "获取配置分组详细信息", notes = "获取配置分组详细信息")
//    @GetMapping(value = "/getInfo/{gpcode}")
//    public Message<CfgGroup> getInfo(@PathVariable("gpcode") String gpcode)
//    {
//    	try {
//	    	if(StringUtil.isEmpty(gpcode)) {
//	    		return new Message<CfgGroup>(DataRouterMessageCode.FAIL_GPCODE_NULL);
//	    	}
//	        return new Message<CfgGroup>(cfgGroupService.getCfgGroupByGpcode(gpcode));
//    	}catch (Exception e) {
//    		logger.error("获取配置分组详细信息失败",e);
//			return new Message<CfgGroup>().fail();
//		}
//    }
//
//    /**
//     * 新增配置分组
//     */
//    @ApiOperation(value = "新增配置分组", notes = "新增配置分组")
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    public Message<String> insert(@RequestBody CfgGroup cfgGroup)
//    {
//    	try {
//    		if(StringUtil.isEmpty(cfgGroup.getGpname())) {
//	    		return new Message<String>(DataRouterMessageCode.FAIL_GPNAME_NULL);
//	    	}
//    		if(StringUtil.isEmpty(cfgGroup.getGpcode())) {
//	    		return new Message<String>(DataRouterMessageCode.FAIL_GPCODE_NULL);
//	    	}
//    		CfgGroup cg = cfgGroupService.getCfgGroupByGpcode(cfgGroup.getGpcode());
//    		if(cg!=null) {
//    			return new Message<String>(DataRouterMessageCode.FAIL_GPCODE_EXIST);
//    		}
//	    	cfgGroup.setStatus(StringConstant.active_status);
//	    	cfgGroup.setMduser(userInfoInterface.getUsername());
//	    	cfgGroup.setMdtime(DateConstant.YYYYMMDDHHMMSS.format());
//	        cfgGroupService.insertCfgGroup(cfgGroup);
//        	return new Message<String>().success();
//    	}catch (Exception e) {
//    		logger.error("新增配置分组失败",e);
//			return new Message<String>().fail();
//		}
//    }
//
//    /**
//     * 修改配置分组
//     */
//    @ApiOperation(value = "修改配置分组", notes = "修改配置分组")
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Message<String> update(@RequestBody CfgGroup cfgGroup)
//    {
//    	try {
//    		if(StringUtil.isEmpty(cfgGroup.getGpcode())) {
//	    		return new Message<String>(DataRouterMessageCode.FAIL_GPCODE_NULL);
//	    	}
//    		if(StringUtil.isEmpty(cfgGroup.getGpname())) {
//    			return new Message<String>(DataRouterMessageCode.FAIL_GPNAME_NULL);
//    		}
//    		CfgGroup cg = cfgGroupService.getCfgGroupByGpcode(cfgGroup.getGpcode());
//    		if(cg==null) {
//    			return new Message<String>(DataRouterMessageCode.FAIL_GPCODE_NOT_EXIST);
//    		}
//	    	cfgGroup.setMduser(userInfoInterface.getUsername());
//	    	cfgGroup.setMdtime(DateConstant.YYYYMMDDHHMMSS.format());
//	    	cfgGroupService.updateCfgGroup(cfgGroup);
//	        return new Message<String>().success();
//    	}catch (Exception e) {
//    		logger.error("修改配置分组失败",e);
//			return new Message<String>().fail();
//		}
//    }
//
//    /**
//     * 删除配置分组
//     */
//    @ApiOperation(value = "删除配置分组", notes = "删除配置分组")
//	@RequestMapping(value = "delete/{gpcodes}", method = RequestMethod.GET)
//    public Message<String> delete(@PathVariable String[] gpcodes)
//    {
//    	try {
//    		if(gpcodes==null) {
//	    		return new Message<String>(DataRouterMessageCode.FAIL_GPCODE_NULL);
//	    	}
//        	return cfgGroupService.deleteCfgGroupByIds(gpcodes);
//    	}catch (Exception e) {
//    		logger.error(" 删除配置分组失败",e);
//			return new Message<String>().fail();
//		}
//    }
//
//	/**
//	 * 修改分组排序
//	 */
//	@ApiOperation(value = "修改分组排序", notes = "修改分组排序")
//	@RequestMapping(value = "/updateGroupSort", method = RequestMethod.POST)
//	public Message<String> updateCfgRouterClassifySort(@RequestBody GroupNodeSort groupNodeSort){
//		try {
//			return cfgGroupService.updateGroupSort(groupNodeSort);
//		}catch (Exception e) {
//			logger.error("修改分组排序异常",e);
//			return new Message<String>().fail();
//		}
//	}

}

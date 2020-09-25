package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.RPSecType;

@Controller
public class AssetTypeController extends ExceptionControl{
	
	@Resource(name="configService")
    private ConfigService configService;
	
	@RequestMapping(value="/assetType.do", method = RequestMethod.GET, name="管理-资产类型-页面显示")
	public String bank() {
		return "assetType";
	}
	
	@RequestMapping(value = "/config/assetTypeList.do", method = RequestMethod.POST, name="管理-资产类型-查询")
	public void assetTypeList(HttpServletResponse response) {
		HashMap<Integer, String> result = configService.loadAssetTypeFather();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/assetTypeDetail.do", method = RequestMethod.POST)
	public void assetTypeDetail(
			@RequestParam(value = "id", required = true) int id,
			HttpServletResponse response) {
		
        
        List<Object> data = new ArrayList<Object>();
        
        List<RPSecType> assetList=configService.loadAssetTypeList(id);
        
        for(RPSecType rtype:assetList){
        	HashMap<String, Object> lvl1 = new HashMap<String, Object>();
            lvl1.put("id", rtype.getId());
            lvl1.put("name", rtype.getTypename());
            lvl1.put("parent", rtype.getFathernode());
            data.add(lvl1);
        }
        
        JsonUtil.writeObjToResponse(response, data);
	}
	
	 @RequestMapping(value="/config/assetTypeUpdate.do", method = RequestMethod.POST, name="管理-资产类型-更新")
     public void assetTypeUpdate(@RequestParam(value = "id", required = true) String id,
             				    @RequestParam(value = "params", required = true) String params,
             			     HttpServletResponse response){
        List<Object> param_list = JsonUtil.stringToList(params);
        configService.updateAssetType(param_list);
        JsonUtil.writeObjToResponse(response, "");
    }
	 
	//给出最末级的ID，返回资产主分类和次分类
	@RequestMapping(value = "/config/asset.do", method = RequestMethod.POST)
	public void asset(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		RPSecTypeLevelModel rpTypeModel=configService.loadAssetType(id);
        data.put("major", rpTypeModel.getLevel1name());
        data.put("minor", rpTypeModel.getLevel2name());
        JsonUtil.writeObjToResponse(response, data);
	}
	
	//给出最末级的ID，返回资产主分类
	@RequestMapping(value = "/config/newMajorAsset.do", method = RequestMethod.POST)
	public void newMajorAsset(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) {
		HashMap<Integer, Object> result = new HashMap<Integer, Object>();
		RPSecTypeLevelModel rpTypeModel=configService.loadAssetType(id);
		result.put(rpTypeModel.getLevel1id(), rpTypeModel.getLevel1name());
        JsonUtil.writeObjToResponse(response, result);
	}
	
	//给出最末级的ID，返回资产次分类
	@RequestMapping(value = "/config/newMinorAsset.do", method = RequestMethod.POST)
	public void newMinorAsset(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) {
		HashMap<Integer, Object> result = new HashMap<Integer, Object>();
		RPSecTypeLevelModel rpTypeModel = configService.loadAssetType(id);
		result.put(rpTypeModel.getLevel2id(), rpTypeModel.getLevel2name());
		JsonUtil.writeObjToResponse(response, result);
	}
		
	//给出所有最末级的ID
	@RequestMapping(value = "/config/assets.do", method = RequestMethod.POST)
	public void industryType(HttpServletResponse response) {
        HashMap<Integer, String> data = configService.loadAssetType();
        JsonUtil.writeObjToResponse(response, data);
	}
	
	//根据资产类型给出最末级的ID
	@RequestMapping(value = "/config/newAssets.do", method = RequestMethod.POST)
	public void newIndustryType(@RequestParam(value = "assetID", required = true) String assetID,
			HttpServletResponse response) {
		HashMap<Integer, String> result = configService.loadAssetTypeByAssetID(assetID);
		JsonUtil.writeObjToResponse(response, result);
	}
}

package com.joyintech.tams.svreport.chb.assetinforeg.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoRegistMService
 * @since 1.0
 */
@Service
public class AssetInfoRegistMService  extends BaseService<AssetInfoRegist001Dto>{
	
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
	public Page<AssetInfoRegist001Dto> searchForPage(Map<String, Object> param) {
		
		//获取当前登录用户的机构编码
		param.put("orgCode", CommonUtil.getTreeInstCode());
		
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/findPage",param,
        		AssetInfoRegist001Dto.class);
	}

    
    /**
     * 手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @PostMapping("updateSumStaus")
    public void updateSumStaus(Map<String,Object> map) {
    	restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/updateSumStaus",map,
        		Integer.class);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
 	public boolean cancelMannul(Map<String, Object> map) {
 		 return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/cancelMannul",map,
        		boolean.class);
 	}
     
    
    /**
     * 资产代码下拉框
     * @return 产品名称列表
     */
    public List<SelectOptionDto> queryAssetCodeList(Map<String, Object> map) {
        return restTemplate.postForList(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/queryAssetCodeList",map,
        		SelectOptionDto.class);
    }
    
    public List<ExcelValidDto> reportCheck(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/reportCheck", pageParam,
                ExcelValidDto.class);
    }

	/**start  中债一二期直连代码  add  by  wangzehui  20201012*/
	public Map<String,Object> submitBeforeCheck(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoRegist/submitBeforeCheck", param,
				Map.class);
	}
	/**end  中债一二期直连代码  add  by  wangzehui  20201012*/
}

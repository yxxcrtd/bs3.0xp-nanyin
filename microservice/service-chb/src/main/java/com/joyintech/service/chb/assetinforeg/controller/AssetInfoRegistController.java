package com.joyintech.service.chb.assetinforeg.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegist001Service;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/AssetInfoRegist")
public class AssetInfoRegistController {

	/**
	 * 资产要素service
	 */
    @Autowired
    private AssetInfoRegist001Service assetInfoRegist001Service;
    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
    
	
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @PostMapping("findPage/{page}/{length}")
	public Page<AssetInfoRegist001Dto> searchForPage(@RequestBody Map<String, Object> param) {
		return assetInfoRegist001Service.searchForPage(param);
	}

    
    /**
     * 手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String,Object> map) {
    	assetInfoRegist001Service.updateSumStaus(map);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
 	@PostMapping("cancelMannul")
 	public boolean cancelMannul(@RequestBody Map<String, Object> map) {
    	 return assetInfoRegist001Service.cancelMannul(map);
 	}
     
    
    /**
     * 资产代码下拉框
     * @return 产品名称列表
     */
    @PostMapping("queryAssetCodeList")
    public List<SelectOptionDto> queryAssetCodeList(@RequestBody Map<String, Object> map) {
        return assetInfoRegist001Service.queryAssetCodeList(map);
    }
    
    @PostMapping("selectAssetInfoByPage")
    public List<AssetInfoRegist001Dto> selectAssetInfoByPage(@RequestBody Map<String, Object> param){
    	return assetInfoRegist001Service.selectAssetInfoByPage(param);
    }
    
    @PostMapping("selectExportSelectListSeq")
    public List<AssetInfoRegist001Dto> selectExportSelectListSeq(@RequestBody Map<String, Object> map){
    	return assetInfoRegist001Service.selectExportSelectListSeq(map);
    }

    /**start  中债一二期报送直连  add by  wangzehui  20201012*/
    /**
     * 报送检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("submitBeforeCheck")
    public Map<String,Object> submitBeforeCheck(@RequestBody Map<String,Object> map) {
        return assetInfoRegist001Service.submitBeforeCheck(map);
    }

    /**end  中债一二期报送直连  add by  wangzehui  20201012*/
}

/*
 * 文件名：ValAssetBookParameter001Controller.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.service.ValAssetBookParameter001Service;

@RestController(value="serviceValAssetBookParameter001Controller")
@RequestMapping("ValAssetBookParameter001")
public class ValAssetBookParameter001Controller extends BaseController {

    /**
     * ValAssetBookParameter001Service
     */
    @Autowired
    private ValAssetBookParameter001Service valAssetBookParameter001Service;

    /**
     * 获取查询结果
     * 
     * @return List<ValAssetBookParameter002Dto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/getAllAssetbookPage/{page}/{length}")
    public Page<ValAssetBookParameter002Dto> getAllAssetbookPage(@RequestBody Map<String,Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAssetBookParameter001Service.getAllAssetbookPage(page,length, params);
    }
    
    /**
     * 列表查询
     * @param params
     * @return
     */
    @PostMapping("queryAllList")
    public List<ValAssetBookParameter002Dto> queryAllList(@RequestBody Map<String, Object> param){
        return valAssetBookParameter001Service.queryAllList(param);
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    @PostMapping("/getBooksetIdNew/{page}/{length}")
    public Page<SelectOptionDto> getBooksetIdNew(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetBookParameter001Service.getBooksetIdNew(page,length,pageParam);
    }
    
	/**
	 * 新建check
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("checkfornew")
	public List<ValAssetBookParameter002Dto> checkfornew(@RequestBody String booksetId) throws Exception {
		return valAssetBookParameter001Service.checkfornew(booksetId);
	}
}

package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo001Service;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 11:21
 */
@RestController(value="serviceComFxValuztionInfo001Controller")
@RequestMapping("/ComFxValuztionInfo001")
public class ComFxValuztionInfo001Controller extends BaseController {

    @Autowired
    private ComFxValuztionInfo001Service comFxValuztionInfo001Service;
    
    
    /**
     * 交易编号获取
     * @param param
     *
     * @return 返回的数据
     */
    @PostMapping("getFinprodId2")
	public List<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> param){
		return comFxValuztionInfo001Service.getFinprodId2(param);
	}

    /**
     * 列表查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ComFxValuztionInfo001Dto> queryPageList(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length){
        return comFxValuztionInfo001Service.queryPageList(page,length,params);
    }

    /**
     * 获取增值税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("queryAllList")
    public List<ComFxValuztionInfo001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return comFxValuztionInfo001Service.queryAllList(param);
    }
    
    /**
     * 
     * 插入数据
     * @param equityList 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/mergeInfoList")
    public void mergeInfoList(@RequestBody List<ComFxValuztionInfo001Dto> equityList){
    	comFxValuztionInfo001Service.mergeInfoList(equityList);
    }
}

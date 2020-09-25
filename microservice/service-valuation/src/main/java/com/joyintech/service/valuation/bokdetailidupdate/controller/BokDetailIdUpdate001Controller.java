package com.joyintech.service.valuation.bokdetailidupdate.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate001Dto;
import com.joyintech.service.valuation.bokdetailidupdate.service.BokDetailIdUpdate001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 账务明细代码更新
 *
 * @author tanxuan
 * @createTime 2019-10-10
 */
@RestController(value="serviceBokDetailIdUpdate001Controller")
@RequestMapping("/BokDetailIdUpdate001")
public class BokDetailIdUpdate001Controller extends BaseController {

    @Autowired
    private BokDetailIdUpdate001Service bokDetailIdUpdate001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.投资目的);
        this.setSelectOptionToModel(model, DictCode.币种);
        return super.init(model);
    }
    
    @PostMapping("/checkExecute")
    public int checkExecute(){
        return bokDetailIdUpdate001Service.checkExecute();
    }
    
    /**
     * 查询接口任务
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    @PostMapping("/runAs")
    public int checkInterLog(){
    	return bokDetailIdUpdate001Service.checkInterLog();
            
    }

//    /**
//     * 账套代码获取
//     * @param params 
//     * @return 返回的数据
//     */
//    @PostMapping("getBooksetId")
//	public List<SelectOptionDto> getBooksetId(@RequestBody Map<String, Object> param){
//    	
//		return bokDetailIdUpdate001Service.getBooksetId(param);
//	}
    
    /**
     * 账务明细代码获取
     * @param params 
     * @return 返回的数据
     */
    @PostMapping("getBokDetailId")
	public List<SelectOptionDto> getBokDetailId(@RequestBody Map<String, Object> param){
    	
		return bokDetailIdUpdate001Service.getBokDetailId(param);
	}

    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<BokDetailIdUpdate001Dto> queryPageList(@RequestBody Map<String, Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length){
        return bokDetailIdUpdate001Service.queryPageList(page,length,params);
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("queryAllList")
    public List<BokDetailIdUpdate001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return bokDetailIdUpdate001Service.queryAllList(param);
    }
    
    /**
     * 更新
     * @param params
     * @return
     * @throws ParseException
     */
    @PostMapping("/update")
    public String update(@RequestBody List<BokDetailIdUpdate001Dto> params) throws Exception {
        return bokDetailIdUpdate001Service.update(params);  
    }
}

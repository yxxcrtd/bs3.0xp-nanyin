package com.joyintech.service.chb3.queryanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dto.EquityShareCheck001Dto;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.service.EquityShareCheck001Service;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;
import com.joyintech.tams.report.queryanalysis.prodsharecount.service.ProdShareCount001Service;

/**
 * 投资者份额检查服务
 * @author yuluan
 * @version 1.0 2019年12月4日
 * @see EquityShareCheckController
 * @since 1.0
 */ 
@RestController
@RequestMapping("/EquityShareCheck")
public class EquityShareCheckController extends BaseController{
    /**
     * 产品份额统计servive
     */
    @Autowired
    private EquityShareCheck001Service equityShareCheck001Service;


    /**
     *
     * 业务画面查询事件
     *
     * @return 结果列表
     * @see
     * @since 1.0
     */
    @PostMapping("searchPage/{page}/{length}")
    public Page<EquityShareCheck001Dto> searchPage(@RequestBody Map<String, Object> param) {
        Page<EquityShareCheck001Dto> page = equityShareCheck001Service.searchPage(param);
        return page;
    }

    /**
	 * 查询识别标识
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
    @PostMapping("queryIndentifyMarkList")
    public List<SelectOptionDto> queryIndentifyMarkList(@RequestBody Map<String, Object> param) {
    	return equityShareCheck001Service.queryIndentifyMarkList(param);
    }
    /**
	 * 查询产品名称
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
   @PostMapping("queryPrdNameList")
   public List<SelectOptionDto> queryPrdNameList(@RequestBody Map<String, Object> param) {
   	return equityShareCheck001Service.queryPrdNameList(param);
   }
}

/*
 * 文件名：MstBondFirstMarketCalendar001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhaodongdong
 * 修改时间：2019年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstbondfirstmarketcalendar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstbondfirstmarketcalendar.dto.MstBondFirstMarketCalendar001Dto;
import com.joyintech.service.basicdata.mstbondfirstmarketcalendar.service.MstBondFirstMarketCalendar001Service;

/**
 * 银行间交易费用
 * 〈功能详细描述〉
 * @author zhaodongdong
 * @version 1.0 2019年11月16日
 * @see MstBondFirstMarketCalendar001Controller
 * @since 1.0
 */
@RestController("MstBondFirstMarketCalendar001_dtx")
@RequestMapping("/MstBondFirstMarketCalendar001")
public class MstBondFirstMarketCalendar001Controller {

	/**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
	@Autowired
	private MstBondFirstMarketCalendar001Service mstBondFirstMarketCalendar001Service;
	@PostMapping("findPage/{page}/{length}")
    public Page<MstBondFirstMarketCalendar001Dto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return mstBondFirstMarketCalendar001Service.selectForPage(page, length, param);
    }
	/**
	 * 查询finprodId
	 * @param finprodId
	 * @return
	 */
	@GetMapping("getFinprodId")
    public String  getFinprodId( String finprodId){
        return mstBondFirstMarketCalendar001Service.getFinprodId(finprodId);
    }
}

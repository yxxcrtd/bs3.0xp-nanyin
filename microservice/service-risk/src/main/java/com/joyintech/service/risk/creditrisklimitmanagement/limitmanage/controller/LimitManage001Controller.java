/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service.LimitManage001Service;



/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage001Controller
 * @since 1.0
 */
@RestController("LimitManage001Ctl_dtx")
@RequestMapping("/LimitManage001")
public class LimitManage001Controller {

	/**
	 * limitManage001Service
	 */
	@Autowired
	private LimitManage001Service limitManage001Service;
	
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getCustomerId/{page}/{length}")
	public Page<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return limitManage001Service.getCustomerId(map, page, length);
	}
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<LimitManageInfoDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return limitManage001Service.selectForPage(page, length, param);
    }

/*	*//**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     *//*
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
        return limitManage001Service.delete(dto);
    }*/
}

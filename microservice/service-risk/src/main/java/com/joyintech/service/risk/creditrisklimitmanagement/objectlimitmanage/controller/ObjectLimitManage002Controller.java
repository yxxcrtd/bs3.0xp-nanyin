/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.service.ObjectLimitManage002Service;


/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see ObjectLimitManage002Controller
 * @since 1.0
 */
@RestController("ObjectLimitManage002Ctl_dtx")
@RequestMapping("/ObjectLimitManage002")
public class ObjectLimitManage002Controller {

	/**
	 * objectLimitManageService
	 */
	@Autowired
	private ObjectLimitManage002Service objectLimitManageService;

    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody ObjectLimitManageInfoDto dto) throws Exception {
    	objectLimitManageService.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody ObjectLimitManageInfoDto dto) throws Exception {
    	objectLimitManageService.update(dto);
    }
	/**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ObjectLimitManageInfoDto dto) throws Exception {
        return objectLimitManageService.delete(dto);
    }
	
	
    @PostMapping("queryCeLineBySeqno")
	public ObjectLimitManageInfoDto queryCeLineBySeqno(@RequestBody Map<String, Object> map) {
		return objectLimitManageService.queryCeLineBySeqno(map);
	}
	
}

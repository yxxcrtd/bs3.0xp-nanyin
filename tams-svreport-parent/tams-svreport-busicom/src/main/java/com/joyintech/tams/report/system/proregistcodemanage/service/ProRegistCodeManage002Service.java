/*
 * 文件名：ProRegistCodeManage001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.proregistcodemanage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper;

/**
 * 产品登记编码管理002service
 * 〈功能详细描述〉
 * @author tangliang
 * @version 1.0 2017年9月20日
 * @see ProRegistCodeManage002Service
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class ProRegistCodeManage002Service extends BaseService {
    @Autowired
	private ProRegistCodeManage001Mapper proRegistCodeManage001Mapper;
    /**
     * 产品下拉框
     * @param paraMap 查询参数
     * @return 产品下拉框
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public Page<SelectOptionDto> queryProdList(Map<String, Object> paraMap) {
    	int pagenum = Integer.parseInt(paraMap.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(paraMap.get(GlobalConstant.PARAM_LENGTH).toString());
        return super.queryForPage(
        		pagenum,legth,()->proRegistCodeManage001Mapper.queryProdList(paraMap));
    }
}

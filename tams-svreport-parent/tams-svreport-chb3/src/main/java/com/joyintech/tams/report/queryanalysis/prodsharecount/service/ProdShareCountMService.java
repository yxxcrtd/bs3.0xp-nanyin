/*
 * 文件名：ProdShareCount001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.queryanalysis.prodsharecount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;

/**
 * 产品份额统计
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ProdShareCountMService
 * @since 1.0
 */
@Service
public class ProdShareCountMService extends CommonExportService<ProdShareCount001Dto>{

    /**
     *
     * 业务画面查询事件
     *
     * @return 结果列表
     * @see
     * @since 1.0
     */
    public Page<ProdShareCount001Dto> searchPage(Map<String, Object> param) {
    	return restTemplate.postForPage(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/ProdShareCount/findPage",param,
        		ProdShareCount001Dto.class);
	}

    /**
     *
     * 业务画面导出事件
     * @return 結果
     * @see
     * @since 1.0
     */
    public List<ProdShareCount001Dto> setDataList(Map<String, Object> param) {
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/ProdShareCount/setDataList",param,
        		ProdShareCount001Dto.class);
     
    }
}

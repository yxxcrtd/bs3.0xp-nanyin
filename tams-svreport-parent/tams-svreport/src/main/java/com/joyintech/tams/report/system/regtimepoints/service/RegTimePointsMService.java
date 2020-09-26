// ******************************************************************************
// Copyright (C) 2016 Joyintech, All Rights Reserved.
// ******************************************************************************

package com.joyintech.tams.report.system.regtimepoints.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.regtimepoints.dto.RegTimePoints001Dto;


/**
 * 〈一句话功能简述〉
 * 报送时点管理查询
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see RegTimePointsMService
 * @since 1.0
 */
@Service
public class RegTimePointsMService extends BaseService<RegTimePoints001Dto> {

    /**
     * 查询按钮
     * @return 查询结果list
     * @see
     * @since 1.0
     */
    public Page<RegTimePoints001Dto> searchPage(Map<String,Object> paramMap) {
        Page<RegTimePoints001Dto> pageList= restTemplate.postForPage(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/RegTimePoints/findPage",paramMap,
          		RegTimePoints001Dto.class);
        return pageList;
    }
    
    /**
     * 查询check
     *
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public int findRegTimePointsCount(Map<String,Object> paramMap){
        	return restTemplate.postForObject(
              		SvrServiceApplication.COMMON_SERVICE_NAME + "/RegTimePoints/findRegTimePointsCount",paramMap,
              		Integer.class);
    }
    

    /**
     * 保存编辑后的参数
     *
     * @param dto 报送时点dto
     * @return 
     */
    public void updateRegTimePoints(RegTimePoints001Dto dto) {
    	restTemplate.postForObject(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/RegTimePoints/save",dto,
          		Integer.class);
    }
    /**
     * 保存编辑后的参数
     *
     * @param dto 报送时点dto
     * @return 结果
     */
    public  void updateAllRegTimePoints(Map<String,Object> paramMap) {
    	restTemplate.postForObject(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/RegTimePoints/updateAllRegTimePoints",paramMap,
          		Integer.class);
    }
    
    /**
     * 查询报送时点
     * 
     * @return 数据list
     * @see
     * @since 1.0
     */
    public List<RegTimePoints001Dto> selectPoint() {
        return restTemplate.getForList(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/RegTimePoints/selectPoint",
          		RegTimePoints001Dto.class);
    }
}

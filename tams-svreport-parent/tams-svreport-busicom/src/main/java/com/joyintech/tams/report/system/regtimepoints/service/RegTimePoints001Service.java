/*
 * 文件名：RegTimePoints001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.regtimepoints.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.report.common.service.SvrBaseCommonService;
import com.joyintech.tams.report.system.regtimepoints.dao.RegTimePointsDtoMapper;
import com.joyintech.tams.report.system.regtimepoints.dto.RegTimePoints001Dto;


/**
 * 〈一句话功能简述〉
 * 报送时点管理查询
 * @author tangliang
 * @version 1.0 2017年9月12日
 * @see RegTimePoints001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegTimePoints001Service extends BaseService<RegTimePoints001Dto>{

    /**
     * 报送时点管理mapper
     */
    @Autowired
    private RegTimePointsDtoMapper regTimePointsDtoMapper;
    /**
     * commonService
     */
    @Autowired
    private SvrBaseCommonService commonService;

//    /**
//     * 报送时点管理表mapper
//     */
//    @Autowired
//    private RegTimePointsDtoMapper regTimePointsDtoMapper;

    /**
     * 报送代码 查询按钮
     * @param map 检索参数
     * @return 查询结果list
     * @see
     * @since 1.0
     */
    public Page<RegTimePoints001Dto> searchPage(Map<String, Object> map) {
    	return super.queryForPage(
    			0, 60,()->regTimePointsDtoMapper.searchPage(map));
    }
//    /**
//     * 获取报表名称
//     * 
//     * @param reportCode String
//     * @return List
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> searchReportCode(String reportCode) {
//        return regTimePointsDtoMapper.searchReportCode(reportCode);
//    }
    /**
     * 报送时点数目
     * 
     * @param paramMap 查询参数Map
     * @return 报送时点数目
     */
    public int findRegTimePointsCount(Map<String, Object> paramMap) {
        return this.regTimePointsDtoMapper.findRegTimePointsCount(paramMap);
    }
    /**
     * 报表名称+细分类型 数目
     * 
     * @param  reportCode 
     * @param  typeDetail 
     * @return 报表名称+细分类型 数目
     */
    public int findCount(String reportCode, String typeDetail) {
        return regTimePointsDtoMapper.findCount(reportCode, typeDetail);
    }
    /**
     * 保存编辑后的参数
     *
     * @param dto 报送时点dto
     * @return 
     */
    public  void updateRegTimePoints(RegTimePoints001Dto dto) {
        regTimePointsDtoMapper.updateRegTimePoints(dto);
    }
    /**
     * 更新所有的报送时点
     * <功能详细描述>
     * @param map 数据
     * @see
     * @since 1.0
     */
    public void updateAllRegTimePoints(Map<String , Object> map) {
        map.put("updateTime", commonService.getSysDate());
        map.put("updateUser", SecurityUtil.getLoginUser().getUserName());
        regTimePointsDtoMapper.updateAllRegTimePoints(map);
        
    }
    /**
     * 查询报送时点
     * 
     * @return 数据list
     * @see
     * @since 1.0
     */
    public List<RegTimePoints001Dto> selectPoint() {
        return regTimePointsDtoMapper.selectPoint();
    }
}

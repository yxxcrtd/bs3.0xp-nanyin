/*
 * 文件名：PaRskCustomerRatingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstperformancebenchmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstperformancebenchmark.dao.MstPerformanceBenchMarkMapper;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
/**
 * 
 * 基准成分Serivice
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月20日
 * @see ComponentInfoService
 * @since 1.0
 */
@Service
public class ComponentInfoService extends BaseService<ComponentInfoDto>{
    
    /**
     * 基准信息mapper注入
     */
    @Autowired
    private MstPerformanceBenchMarkMapper mstPerformanceBenchMark001Mapper;
    
    /**
     * 
     * 编辑基准信息表查询分页
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page<PaRskCustomerRating>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ComponentInfoDto> selectComponentInfoPage(Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(() -> mstPerformanceBenchMark001Mapper.selectComponentInfoPage(pageParam));
    }
    
    /**
     * 
     * 通过基准编号查询表格信息 
     * <功能详细描述>
     * @param indexId String
     * @return List<PaRskCustomerRating>
     * @see
     * @since 1.0
     */
    public List<ComponentInfoDto> selectComponentInfoByPrimaryKey(String indexId){
        return mstPerformanceBenchMark001Mapper.selectComponentInfoByPrimaryKey(indexId);
    }
}

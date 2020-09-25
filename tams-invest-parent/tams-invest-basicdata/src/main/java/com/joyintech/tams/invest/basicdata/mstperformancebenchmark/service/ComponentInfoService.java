/*
 * 文件名：PaRskCustomerRatingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
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
public class ComponentInfoService extends CommonExportService<ComponentInfoDto>{
    
    /**
     * 基准信息service注入
     */
    @Autowired
    private MstPerformanceBenchMarkService mstPerformanceBenchMarkService;
    
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
    public List<ComponentInfoDto> selectComponentInfoPage(Map<String, Object> pageParam) throws Exception {
        String workflowId=(String)pageParam.get("workflowId");
        //进入代办之后数据需要用workflowId找值，数据此时并没有加到数据库里面
        if(StringUtil.isNotEmpty(workflowId)){
            MstPerformanceBenchMarkDto mstPerformanceBenchMarkDto =mstPerformanceBenchMarkService.getBussinessEntityByWorkflowId(
                workflowId);
            List<ComponentInfoDto> page=new ArrayList<>();
            page=mstPerformanceBenchMarkDto.getComponentInfo();
//            page.setRecords(StringUtil.isNotEmpty(
//                mstPerformanceBenchMarkDto.getComponentInfo()) ? mstPerformanceBenchMarkDto.getComponentInfo().size() : 0);
            return page;
        }
        else{
            return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectComponentInfoPage",
                pageParam, ComponentInfoDto.class);
        }
       
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
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectComponentInfoByPrimaryKey",
            indexId, ComponentInfoDto.class);
    }
}

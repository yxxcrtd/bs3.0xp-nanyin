/*
 * 文件名：MstCaptialAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;
/**
 * 
 * 基准信息Serivice
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月20日
 * @see MstPerformanceBenchMarkService
 * @since 1.0
 */
@Service
public class MstPerformanceBenchMarkService extends CommonExportService<MstPerformanceBenchMarkDto> 
                            implements BaseProcessService<MstPerformanceBenchMarkDto> {

    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     */
    public Page<MstPerformanceBenchMarkDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectForPage",
            pageParam, MstPerformanceBenchMarkDto.class);
    }

    /**
     * 联想文本框service层实现
     *
     * @param map Map<String,Object>
     * @return SuggestDto
     */
    public List<SuggestDto> getIndexName(Map<String, String> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/getIndexName",
            map, SuggestDto.class);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param indexId String
     * @return MstPerformanceBenchMarkDto
     * @see
     * @since 1.0
     */
    public MstPerformanceBenchMarkDto selectDetail(String indexId) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectDetail",
            indexId, MstPerformanceBenchMarkDto.class);
    }

    /**
     * 增删改查
     * 
     * @param entity MstPerformanceBenchMarkDto
     * @param type OperationType
     * @see
     * @since 1.0
     */
    public void doComplete(MstPerformanceBenchMarkDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/doComplete", entity, Integer.class);
    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstPerformanceBenchMarkDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/checkCodeIsUse",
            dto, Integer.class);
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkAllCodeIsUse(List<MstPerformanceBenchMarkDto> item) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/checkAllCodeIsUse",
            item, Integer.class);
    }

    /**
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/mstperformancebenchmark/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<MstPerformanceBenchMarkDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 新增
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see 
     */
    public int insert(MstPerformanceBenchMarkDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/insert",
            dto, Integer.class);
    }

    /**
     * 通过indexName查询
     * <功能详细描述>
     * @param dto 数据条件
     * @return 结果
     * @see
     * @since 1.0
     */
    public int selectByIndexName(MstPerformanceBenchMarkDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectByIndexName",
            dto, Integer.class);
    }

    /**
     * 通过主键查询
     * <功能详细描述>
     * @param mstIndexComponent 条件
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int selectByPrimaryKey(MstIndexComponent mstIndexComponent) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstPerformanceBenchMark/selectByPrimaryKey",
            mstIndexComponent, Integer.class);
    }
}

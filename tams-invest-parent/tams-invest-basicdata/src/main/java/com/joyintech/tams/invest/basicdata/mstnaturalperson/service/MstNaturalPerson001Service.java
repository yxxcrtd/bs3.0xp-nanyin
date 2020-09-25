/*
 * 文件名：MstNaturalPerson001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstnaturalperson.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 自然人管理
 * 
 * @author xiexinting
 * @version 1.0 2018年8月14日
 * @see MstNaturalPerson001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MstNaturalPerson001Service extends CommonExportService<MstNaturalPerson001Dto> implements BaseProcessService<MstNaturalPerson001Dto> {
    /**
     * 查询 <功能详细描述>
     * 
     * @param pageParam 查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<MstNaturalPerson001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/selectForPage",
            pageParam, MstNaturalPerson001Dto.class);
    }

    /**
     * 
     * 客户名称联想文本框service层实现
     * <功能详细描述>
     * @param map 联想文本框查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getcName(Map<String, String> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/getcName",
            map, SuggestDto.class);
    }

    /**
     * 客户身份证联想文本框service层实现
     *
     * @param param 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> getcID(String param) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/getcID?param="+
            param, SuggestDto.class);
    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstNaturalPerson001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/checkCodeIsUse",
            dto, Integer.class);
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public int checkAllCodeIsUse(List<MstNaturalPerson001Dto> item) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/checkAllCodeIsUse",
            item, Integer.class);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param customerId 查询条件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public MstNaturalPerson001Dto selectDetail(String customerId) {
        return restTemplate.getForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/selectDetail?customerId="
            +customerId, MstNaturalPerson001Dto.class);
    }

    /**
     * 实现工作流doComplete方法
     */
    @Override
    public void doComplete(MstNaturalPerson001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/doComplete", entity, Integer.class);
    }

    /**
     * 
     * 删除自然人
     * <功能详细描述>
     * @param dto  前台数据
     * @return 返回成功/失败
     * @see
     * @since 1.0
     */
    public boolean delete(MstNaturalPerson001Dto dto){
        List<MstNaturalPerson001Dto> dtolist = dto.getSelectedItem();
        List<String> customerIdList = new ArrayList<>();
        if(!dtolist.isEmpty()){
            for(MstNaturalPerson001Dto entity : dtolist){
                customerIdList.add(entity.getCustomerId());
            }
            return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/delete", customerIdList, boolean.class);
        }
        return false;
    }
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/mstnaturalperson/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<MstNaturalPerson001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 
     * 设置导出自定义单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName, "sex")){
            cell.setCellValue(SysDataUtil.getNameFromDataDict(DictCode.性别,(String)value));
        }
    }
    /**
     * 
     * 新建验证
     * <功能详细描述>
     * @param dto 实体类
     * @return 查询出的个数
     * @see
     * @since 1.0
     */
    public int selectByPersonId(MstNaturalPerson001Dto dto){
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/selectByPersonId",
            dto, Integer.class);
    }
    /**
     * 
     * 修改验证
     * <功能详细描述>
     * @param dto 实体类
     * @return 查询出的个数
     * @see
     * @since 1.0
     */
    public int selectByPidandCid(MstNaturalPerson001Dto dto){
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNaturalPerson/selectByPidandCid",
            dto, Integer.class);
    }
}

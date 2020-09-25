/*
 * 文件名：SystemBatchLog001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月2日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.common.system.log.batch.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbSysTask;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.StartMode;
import com.joyintech.tams.disclosure.common.system.log.batch.dao.SystemBatchLog001Mapper;
import com.joyintech.tams.disclosure.common.system.log.batch.dto.SystemBatchLog001Dto;



/**
 * 批处理日志Service
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月2日
 * @see SystemBatchLog001Service
 * @since 1.0
 */
@Service(value="disSystemBatchLog001Service")
public class SystemBatchLog001Service extends CommonExportService<SystemBatchLog001Dto> {

    /**
     * SystemBatchLog001Mapper
     */
    @Autowired
    private SystemBatchLog001Mapper systemBatchLog001Mapper;

    /**
     * 任务组列表取得
     * 
     * @return 任务组列表
     */
    public List<ChbSysTask> queryTask() {
        return systemBatchLog001Mapper.queryTask();
    }

    /**
     * 查询批处理日志列表
     * 
     * @param param 查询参数
     * @return 批处理日志列表
     * @throws Exception 异常
     */
    public Page<SystemBatchLog001Dto> searchForPage(Map<String, Object> param)
        throws Exception {
        Page<SystemBatchLog001Dto> page = super.queryForPage(SystemBatchLog001Mapper.class,
            "searchForPage", param);
        format(page.getRoot());
        return page;
    }

    /**
     * 导出批处理日志
     * 
     * @param pageParam 查询参数
     * @return 批处理日志列表
     */
    public List<SystemBatchLog001Dto> queryReportList(Map<String, Object> pageParam) {
        String keys = (String)pageParam.get("keys");
        if (StringUtil.isNotEmpty(keys)) {
            pageParam.put("batchIdList", StringUtil.splitToList(GlobalConstant.COMMA, keys));
        }
        List<SystemBatchLog001Dto> result = systemBatchLog001Mapper.searchForPage(pageParam);
        format(result);
        return result;
    }

    /**
     * 格式化
     * 
     * @param dtoList 批处理日志列表
     */
    private void format(List<SystemBatchLog001Dto> dtoList) {
        if (null != dtoList) {
            dtoList.forEach(item -> {
                item.setStartModule(StartMode.fromCode(item.getStartModule()).name());
                item.setTaskStatus(
                    SysDataUtil.getNameFromDataDict(DictCode.批处理任务状态, item.getTaskStatus()));
                item.setTaskResult(
                    SysDataUtil.getNameFromDataDict(DictCode.批处理执行结果, item.getTaskResult()));
            });
        }
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "startTime") || StringUtil.equals(propName, "endTime")
            || StringUtil.equals(propName, "createTime")) {
            cell.setCellValue(DateUtil.format((Date)value, DateUtil.DEFAULT_DATETIME_FORMAT));
        }
        else if (StringUtil.equals(propName, "busiStart")
                 || StringUtil.equals(propName, "busiEnd")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
        super.setCellValue(beanName, cell, propName, value);
    }

}

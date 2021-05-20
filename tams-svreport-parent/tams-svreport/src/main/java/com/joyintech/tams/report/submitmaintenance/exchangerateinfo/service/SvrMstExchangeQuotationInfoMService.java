/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 汇率行情service 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see SvrMstExchangeQuotationInfoMService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMstExchangeQuotationInfoMService extends CommonExportService<SvrMstExchangeQuotationInfoDto> 
    implements BaseProcessService<SvrMstExchangeQuotationInfoDto> {

    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstExchangeQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/selectForPage",
            pageParam, SvrMstExchangeQuotationInfoDto.class);
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SvrMstExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/selectAllList",
            pageParam, SvrMstExchangeQuotationInfoDto.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(SvrMstExchangeQuotationInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/doComplete",
            entity, Integer.class);
    }

    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<SvrMstExchangeQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

	public List<SvrMstExchangeQuotationInfoDto> selectForExport(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/selectForExport",
                param, SvrMstExchangeQuotationInfoDto.class);
	}

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("tempList", ExcelUtil.getTempList(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/importData",
                param,
                ExcelValidDto.class);
    }


    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstExchangeQuotationInfo/checkConfirmMsgForImportData",
                param,
                SvrAjaxResult.class);
    }
}

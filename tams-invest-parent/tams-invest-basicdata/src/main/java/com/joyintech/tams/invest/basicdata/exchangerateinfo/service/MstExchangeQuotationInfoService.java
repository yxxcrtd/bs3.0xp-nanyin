/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.exchangerateinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.exchangerateinfo.dto.MstExchangeQuotationInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 汇率行情service 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see MstExchangeQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstExchangeQuotationInfoService extends CommonExportService<MstExchangeQuotationInfoDto> 
    implements BaseProcessService<MstExchangeQuotationInfoDto> {

    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<MstExchangeQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectForPage",
            pageParam, MstExchangeQuotationInfoDto.class);
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<MstExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectAllList",
            pageParam, MstExchangeQuotationInfoDto.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(MstExchangeQuotationInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/doComplete",
            entity, Integer.class);
    }

    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/exchangerateinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<MstExchangeQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "tradeDate")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/checkConfirmMsgForImportData",
                param,
                InvAjaxResult.class);
    }



    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("tempList", ExcelUtil.getTempList1(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/importData",
                param,
                ExcelValidDto.class);
    }
}

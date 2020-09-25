package com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.dto.MstInterestRateQuotationInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 利率行情信息service
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see MstInterestRateQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional
public class MstInterestRateQuotationInfoService extends CommonExportService<MstInterestRateQuotationInfoDto> 
    implements BaseProcessService<MstInterestRateQuotationInfoDto> {

    /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<MstInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstInterestRateQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstInterestRateQuotationInfo/selectForPage",
            pageParam, MstInterestRateQuotationInfoDto.class);
    };

    /**
     * 根据主键查询
     * @param map 查询条件
     * @return MstInterestRateQuotationInfoDto 实体类
     */
    public MstInterestRateQuotationInfoDto selectByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstInterestRateQuotationInfo/selectByPrimaryKey",
            map, MstInterestRateQuotationInfoDto.class);
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstinterestratequotationinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    /**
     * 导出
     */
    @Override
    public Page<MstInterestRateQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("relaeseDate".equals(propName) || "effDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    /**
     * 流程实现方法 {@inheritDoc}
     */
    @Override
    public void doComplete(MstInterestRateQuotationInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setIntRate(entity.getIntRate().divide(new BigDecimal("100")));
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstInterestRateQuotationInfo/doComplete",
            entity, Integer.class);
    }
    
  //hsgexinghua   add by zhangxing1 20200220 start

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
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstInterestRateQuotationInfo/checkConfirmMsgForImportData",
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
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstInterestRateQuotationInfo/importData",
                param,
                ExcelValidDto.class);
    }

}

/*
 * 文件名：MstNetAssetQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstnetassetquotationinfo.service;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.basicdata.mstnetassetquotationinfo.dto.MstNetAssetQuotationInfo001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 计划类（净值型）资产估值行情信息查询service
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see MstNetAssetQuotationInfo001Service
 * @since 1.0
 */

@Service
@Transactional
public class MstNetAssetQuotationInfo001Service extends CommonExportService<MstNetAssetQuotationInfo001Dto> 
    implements BaseProcessService<MstNetAssetQuotationInfo001Dto> {

    /**
     * 查询按钮点击事件 通过条件查询数据
     * 
     * @param pageParam 页面参数
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstNetAssetQuotationInfo001Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        pageParam.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/findPage", pageParam,
            MstNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 资产名称联想文本框 可以通过输入的资产名称模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetName(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/getAssetName", map,
            SuggestDto.class);
    }

    /**
     * 查询界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetCode(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/getAssetCode", map,
            SuggestDto.class);
    }

    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/getAssetCodeList", map,
            SelectOptionDto.class);
    }

    /**
     * 通过资产代码查询数据 完成新建、编辑界面和双击查看界面初始化
     * 
     * @param param 资产代码
     * @return dto
     * @see
     * @since 1.0
     */
    public MstNetAssetQuotationInfo001Dto selectMstNetAsset(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/selectMstNetAsset",
            param, MstNetAssetQuotationInfo001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MstNetAssetQuotationInfo001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();
        param.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstnetassetquotationinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("valDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    @Override
    public Page<MstNetAssetQuotationInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 通过资产代码和估值日期查询数据 查询插入数据的主键是否与数据库中的相同
     * 
     * @param finprodId 金融产品代码
     * @param valDate 估值日期
     * @return dto
     * @see
     * @since 1.0
     *//*
       * public MstNetAssetQuotationInfo001Dto selectByIdAndDate(String finprodId, String valDate)
       * { return mstnetassetquotationinfo001mapper.selectByIdAndDate(finprodId,valDate); }
       */

    /**
     * 通过资产代码查询资产名称和管理人 完成资产代码画面联动
     * 
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    public MstNetAssetQuotationInfo001Dto selectNameAndFundManager(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                          + "/MstNetAssetQuotationInfo/selectNameAndFundManager",
            map, MstNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 
     * 检验validate
     * <功能详细描述>
     * @param map 金融产品代码和估值日
     * @return dto
     * @see
     * @since 1.0
     */
    public MstNetAssetQuotationInfo001Dto selectByIdAndDate(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                       + "/MstNetAssetQuotationInfo/selectByIdAndDate",
            map,MstNetAssetQuotationInfo001Dto.class);
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
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/checkConfirmMsgForImportData",
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
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstNetAssetQuotationInfo/importData",
                param,
                ExcelValidDto.class);
    }
	
	
}

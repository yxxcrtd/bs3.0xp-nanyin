/*
 * 文件名：MstSharePriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstshareprice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.common.basicdata.util.ComBasicDataExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 股票行情Service
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePriceService
 * @since 1.0
 */
@Service
public class ComSharePriceService extends CommonExportService<ComSharePriceDto> implements BaseProcessService<ComSharePriceDto>{

    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<ComSharePriceDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.CommonBasicDataService + "/ComSharePrice/selectForPage",
            pageParam, ComSharePriceDto.class);
    }
    
    /**
     * 获取下拉选资产代码
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService
                                       + "/ComSharePrice/getFinprodIdForAdd",map,SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/common/basicdata/marketdata/mstshareprice/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 
     * 导出数据查询
     */
    @Override
    public Page<ComSharePriceDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(ComSharePriceDto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.CommonBasicDataService+"/ComSharePrice/doComplete/" + type, entity, Integer.class);
    }

    /**
     * 通过id获取最近指定日期的股票行情信息   
     * @param pageParam
     * @return
     */
	public ComSharePriceDto getLatelyShareById(Map<String, Object> pageParam) {
		return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                + "/ComSharePrice/getLatelyShareById",pageParam, ComSharePriceDto.class);
}

	/**
	 * 获取所有查询结果list
	 * @param pageParam
	 * @return
	 */
	public List<ComSharePriceDto> selectAllList(Map<String, Object> pageParam) {
		return restTemplate.postForList(ServiceApplication.CommonBasicDataService
                + "/ComSharePrice/selectAllList",pageParam, ComSharePriceDto.class);
	}
    

	/**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ComBasicDataExcelUtil.getDataListLocal(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.CommonBasicDataService + "/ComSharePrice/checkConfirmMsgForImportData",
                param,
                AjaxResult.class);
    }



    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ComBasicDataExcelUtil.getDataListLocal(fileName));
        param.put("tempList", ComBasicDataExcelUtil.getTempListLocal(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComSharePrice/importData",
                param,
                ExcelValidDto.class);
    }

//    /**
//     * 检查证券账户账号是否用过
//     * @param dto MstSecuritiesAccountDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int checkBySecAcctId(MstSecuritiesAccountDto dto) {
//        int count=0;
//        count= restTemplate.postForObject(ServiceApplication.CommonBasicDataService
//            + "/MstSecuritiesAccountInfo/checkBySecAcctId",
//            dto,Integer.class);
//        return count;
//    }
//    /**
//     * 检查证券账户名称是否用过
//     * @param dto MstSecuritiesAccountDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int checkBySecAcctName(MstSecuritiesAccountDto dto) {
//        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/checkBySecAcctName",
//                                         dto,
//                                         Integer.class);
//    }
//    /**
//     * 检查资金结算账户是否存在
//     * @param dto MstSecuritiesAccountDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int checkByTrdAcctId(MstSecuritiesAccountDto dto) {
//        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/checkByTrdAcctId",
//                                         dto,
//                                         Integer.class);
//    }
//    /**
//     * 删除时检查数据是否被使用过
//     * @param dto MstSecuritiesAccountDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int checkCodeIsUse(MstSecuritiesAccountDto dto) {
//        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/checkCodeIsUse",
//                                         dto,
//                                         Integer.class);
//    }
//    /**
//     * 批量删除时检查数据是否被使用过
//     * @param item List<MstSecuritiesAccountDto>
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public List<MstSecuritiesAccountDto> checkAllCodeIsUse(List<MstSecuritiesAccountDto> item) {
//        return restTemplate.postForList(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/checkAllCodeIsUse",
//                                         item,
//                                         MstSecuritiesAccountDto.class);
//    }
//    /**
//     * 查询上传的文件
//     * @param businessId String
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public List<FileDto> selectFileByBusinessId(String businessId) {
//        return restTemplate.getForList(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/selectFileByBusinessId?businessId="
//                                         +businessId,
//                                         FileDto.class);
//    }
//    /**
//     * 插入附件文件
//     * @param dto FileDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int insertIntoFile(FileDto dto) {
//        return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/insertIntoFile",
//                                         dto,
//                                         Integer.class);
//    }
//    /**
//     * 删除附件文件
//     * @param businessId String
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int deleteFileByBusinessId(String businessId) {
//        return restTemplate.getForObject(ServiceApplication.CommonBasicDataService
//                                         + "/MstSecuritiesAccountInfo/deleteFileByBusinessId?businessId="
//                                         +businessId,
//                                         Integer.class);
//    }
   
}

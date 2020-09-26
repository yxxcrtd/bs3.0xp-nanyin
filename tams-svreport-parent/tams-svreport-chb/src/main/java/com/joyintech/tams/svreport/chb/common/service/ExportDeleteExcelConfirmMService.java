/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：导出删除模板共通页面
 * 修改人：Administrator
 * 修改时间：2019年5月30日
 * 修改内容：新建
 * 系统名称：中债报送监管系统
 */
 
package com.joyintech.tams.svreport.chb.common.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.common.dto.ChbDeleteExcelExportDto;


/**
 * 导出删除模板共通逻辑
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see ExportDeleteExcelConfirmMService
 * @since 1.0
 */
@Service
public class ExportDeleteExcelConfirmMService extends BaseService<ChbDeleteExcelExportDto> {
    @Autowired
	private ExportDeleteExcelConfirm001Service  exportDeleteExcelConfirm001Service;

    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> param) {
        // 处理查询参数
        Map<String, Object> selectParam = exportDeleteExcelConfirm001Service.dealSearchParam(pageId,param);
        // 删除原因
        String deleteReason = (String) param.get("remark");
        // 删除信息
        ChbDeleteExcelExportDto dataInfo =  (ChbDeleteExcelExportDto) selectParam.get("deleteInfo");
        // 不选数据导出全部（根据查询条件查询）
        if (dataInfo == null){
            // TODO：各自实现自己的导出方法
            dataInfo = searchByCondition(pageId,selectParam,deleteReason);
        }
        Map<String, Object> dataParam = new HashMap<String, Object>();
        // 删除信息为空则直接返回空数据
        if (dataInfo != null){
            // 发行机构
            dataParam.put("issuePartyId", dataInfo.getIssuePartyId());
            // 删除类型
            dataParam.put("deleteType", dataInfo.getDeletType());
            // 明细数据
            if (CollectionUtils.isNotEmpty(dataInfo.getDetaiList())){
                dataParam.put("detail", dataInfo.getDetaiList());
            }
        }
        File outFile = TisUtil.createFile(dataParam, "ChbDeletInfoReport", pageId);
        // 导出文件名
        String exportFileName = pageId;
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(pageId);
        if(null != page) {
            exportFileName = SpringContextUtil.getTextValue(pageId)+ GlobalConstant.SPLIT
                    + "删除数据"+ GlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                    + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT +GlobalConstant.FILE_POSTFIX_XLS;
        }
        // 是否更新报送状态为手工报送
        String isUpdateSendStatus = (String) param.get("flag");
        if (DictCodeList.是否_中债_是.getValue().equals(isUpdateSendStatus)){
            // TODO：各自实现自己的手工处理方法
            dealDeleteDataSendStatus(dataInfo,param);
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }
    
    
    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public ChbDeleteExcelExportDto searchByCondition(String pageId,Map<String,Object> selectParam,String deleteReason) {
    	selectParam.put("pageId", pageId);
    	selectParam.put("deleteReason", deleteReason);
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ExportDeleteExcelConfirm/searchByCondition",selectParam,ChbDeleteExcelExportDto.class);
    }
    
    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public ChbDeleteExcelExportDto dealDeleteDataSendStatus(ChbDeleteExcelExportDto dto,Map<String, Object> param) {
    	param.put("param_dto", dto);
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/ExportDeleteExcelConfirm/dealDeleteDataSendStatus",param,ChbDeleteExcelExportDto.class);
    }
}

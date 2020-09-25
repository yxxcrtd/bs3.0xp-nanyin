/*
 * 文件名：DocumentManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.document.documentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月16日
 * @see DocumentManagementService
 * @since 1.0
 */
@Service
public class DocumentManagementService  extends CommonExportService<DocumentManagementDto>
implements BaseProcessService<DocumentManagementDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(DocumentManagementDto entity, OperationType type) {
        // TODO Auto-generated method stub

    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/getPortfolioId" ,param,SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getDeptInstitutionCode(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/getDeptInstitutionCode" ,pageParam,SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<DocumentManagementDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/selectForPage" ,pageParam,DocumentManagementDto.class);
    }

    /**
     * 附件详情查询
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<DocumentManagementDto> selectForDetails(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/selectForDetails" ,pageParam,DocumentManagementDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public int insert(DocumentManagementDto dto) {
        return restTemplate.postForObject(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/insert" ,dto,Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public int delete(DocumentManagementDto dto) {
        return restTemplate.postForObject(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/delete" ,dto,Integer.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/document/documentmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<DocumentManagementDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusinessType(Map<String, Object> pageParam) {
        Page<SelectOptionDto> pageList = restTemplate.postForPage(ServiceApplication.DOCUMENT_SERVICE_NAME
            + "/DocumentManagement/getBusinessType" ,pageParam,SelectOptionDto.class);
        List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
        if(pageList != null && pageList.getRoot() != null && pageList.getRoot().size()>0){
            for(SelectOptionDto select:pageList.getRoot()){
                SelectOptionDto s = new SelectOptionDto();
                s.setId(select.getId());
                s.setText(select.getText());
                list.add(s);
            }
            pageList.setRoot(list);
        }
        return pageList;
    }

}

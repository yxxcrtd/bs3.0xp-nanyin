package com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * @author wangzehui
 */
@Service
public class FinDisclosureModuleService extends CommonExportService<FinDisclosureModuleDto> implements BaseProcessService<FinDisclosureModuleDto> {
    /**
     * 文件附件处理接口
     */
    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;
    /**
     * 根据文件类型查询文件名
     * 
     * @param param 页面参数
     * @return list集合
     */
    public List<SuggestDto> getFileNameOption(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinDisclosureModule/getFileNameOption",
            param,SuggestDto.class);
    }

    /**
     * 画面查询方法
     * 
     * @param pageParam 页面参数
     * @return 查询结果
     */
    public Page<FinDisclosureModuleDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinDisclosureModule/selectForPage",
            pageParam,FinDisclosureModuleDto.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        //流程作废时删除文件信息表
        FinDisclosureModuleDto dto = getBussinessEntityByWorkflowId(workflowId);
        if(!StringUtil.equals(dto.getOpType(), "delete")){
            fileAttachmentProcess.remove(dto.getFileId());
        }
    }
    @Override
    public void doComplete(FinDisclosureModuleDto dto, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            fileAttachmentProcess.remove(dto.getFileId());
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinDisclosureModule/" + action, dto,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/productelement/findisclosuremodule/findisclosuremodule/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinDisclosureModuleDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 通过披露文档代码查询数据 
     * 
     * @param map 披露文档代码
     * @return dto
     * @see
     * @since 1.0
     */
    public FinDisclosureModuleDto selectByRevealFileId(Map<String, Object> map) {
        // 查询信息披露
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinDisclosureModule/selectByRevealFileId",
            map,FinDisclosureModuleDto.class);
    }

    /**
     * @param revealFileId revealFileId
     * @return String
     * @see
     * @since 1.0
     */
    public String isUsed(String revealFileId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinDisclosureModule/isUsed?revealFileId="
            +revealFileId,String.class);
    }

}

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetBook006Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * add by yangwenjun 20201130 账套参数修改功能
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetBook006Service extends CommonExportService<ValAssetBook006Dto> implements BaseProcessService<ValAssetBook006Dto> {

    @Autowired
    private ValAssetBook006Service valAssetBook006Service;

    public  Page<ValAssetBook006Dto> findPage(Map<String, Object> map) {
        String workflowId = (String)map.get("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            ValAssetBook006Dto valAssetBook006Dto = valAssetBook006Service.getBussinessEntityByWorkflowId(workflowId);
            Page<ValAssetBook006Dto> page = new Page<>();
            page.setRoot(valAssetBook006Dto.getValAssetBook006DtoList());
            page.setRecords(valAssetBook006Dto.getValAssetBook006DtoList().size());
            return page;
        }
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook006/findPage", map,
                ValAssetBook006Dto.class);
    }

    @Override
    public void doComplete(ValAssetBook006Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook006/doComplete", entity, Integer.class);
    }

    public List<SuggestDto> getParamCode(Map<String, String> pageParam) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook006/getParamCode",
                pageParam, SuggestDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/valuation/accounadjustmanage/assetbook/config/export_param.xml").dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<ValAssetBook006Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

}

package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 名单管理--履历导出
 * @author wangzhuangzhuang
 * @version 1.0 2019年07月23日
 * @see AssetNameManagementRecordService
 * @since 1.0
 */
@Service
public class AssetNameManagementRecordService extends CommonExportService<RskAccessListLogDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/namelistmanage/assetnamelist/conf/export_recordConf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 导出模板
     */
    @Override
    public Page<RskAccessListLogDto> selectPageData(Map<String, Object> selectParam) {
        selectParam.put("pageId","AssetNameManagement003");
        Page<RskAccessListLogDto> page = new Page<>();
        List<RskAccessListLogDto> list= restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/AssetNameManagementDetail/selectAssetListDetailLog",
            selectParam, RskAccessListLogDto.class);
        for (RskAccessListLogDto rskAccessListLogDto : list) {
            if(StringUtil.isNotEmpty(rskAccessListLogDto.getWorkflowId())){
                rskAccessListLogDto.setWorkflowId("生效流程中");
            }else{
                rskAccessListLogDto.setWorkflowId("生效");
            }
        }
        
        page.setRoot(list);
        return page;
    }
}

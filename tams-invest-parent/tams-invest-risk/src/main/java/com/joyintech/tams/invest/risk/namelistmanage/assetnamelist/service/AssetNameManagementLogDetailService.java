package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 名单管理--明细导出
 * @author wangzhuangzhuang
 * @version 1.0 2019年07月23日
 * @see AssetNameManagementLogDetailService
 * @since 1.0
 */
@Service
public class AssetNameManagementLogDetailService extends CommonExportService<RskAccessListLogDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/namelistmanage/assetnamelist/conf/export_logDetailConf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 导出模板
     */
    @Override
    public Page<RskAccessListLogDto> selectPageData(Map<String, Object> selectParam) {
        Page<RskAccessListLogDto> page = new Page<>();
        List<RskAccessListLogDto> list= restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/AssetNameManagementDetail/selectNameListLogDetail",
            selectParam, RskAccessListLogDto.class);
        page.setRoot(list);
        return page;
    }
    
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"operateType")){
            cell.setCellValue(SysDataUtil.getNameFromDataDict(DictCode.名单操作方式, String.valueOf(value)));
        }
        if(StringUtil.equals(propName,"operatePs")){
            cell.setCellValue(SysDataUtil.getNameFromDataDict(DictCode.名单操作方向, String.valueOf(value)));
        }
    }

}

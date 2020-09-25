package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dao.FxImixMenInfoMapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dto.FxImixMenInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 银行间交易员信息查询
 *
 * @author zcf
 * @version 1.0
 * @date 2019-09-29
 */
@Service
public class FxImixMenInfo001Service extends CommonExportService<FxImixMenInfoDto> {

    @Autowired
    private FxImixMenInfoMapper fxImixMenInfoMapper;

    /**
     * 设置导出模板
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com.joyintech/interf/bankname/cfetsup/imixbasicinfo/imixmeninfo/export/export_list.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 分页查询
     * @param para
     * @return
     */
    @Override
    public Page<FxImixMenInfoDto > selectPageData(Map<String, Object> para) {
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()->fxImixMenInfoMapper.selectPageData(para));
    }

}

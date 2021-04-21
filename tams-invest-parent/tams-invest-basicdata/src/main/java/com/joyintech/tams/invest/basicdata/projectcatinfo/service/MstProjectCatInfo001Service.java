/*
 * 文件名：MstProjectCatInfo001Service.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.274
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.tams.invest.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.framework.select.SelectOptionDto;
import java.util.List;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Date;

/**
 * 项目类资产估值行情信息
 *
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.274
 * @see MstProjectCatInfo001Service
 * @since 1.0
 */
@Service
public class MstProjectCatInfo001Service extends CommonExportService<MstProjectCatInfoDto> {

    /**
     * 列表查询
     * 
     * @param pageParam 画面查询条件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<MstProjectCatInfoDto> selectForPage(Map<String, Object> pageParam) {
        Page<MstProjectCatInfoDto> page = restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstProjectCatInfo/findPage", pageParam,
            MstProjectCatInfoDto.class);
        return page;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/projectcatinfo/conf/MstProjectCatInfo001export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<MstProjectCatInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        // 指定单元格格式设置
        if (StringUtil.equals(propName, "valDate") || StringUtil.equals(propName, "importDate")) {
            if (value instanceof Date) {
                cell.setCellValue(DateUtil.format((Date)value));
            }
            else if (value instanceof Long) {
                cell.setCellValue(DateUtil.format(new Date((Long)value)));
            }
        }
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
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstProjectCatInfo/getAssetCode", map,
                SuggestDto.class);
    }

}

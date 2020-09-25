package com.joyintech.tams.invest.period.trdirsduration.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration002ProcessDto;

/**
 * 利率互换存续期
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.service
 * @Description: 利率互换存续期管理-查询
 * @date Date : 2019年08月10日
 */
@Service
public class TrdIRSDuration001Service extends CommonExportService<TrdIRSDuration002ProcessDto> {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<TrdIRSDuration002ProcessDto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                    StringUtil.splitToList(",", (String) params.get("statusValid")));
        } else {
            params.put("statusValid", new ArrayList<>());
        }
        if (params.get("portfolioId") != null) {
            params.put("portfolioId",
                    StringUtil.splitToList(",", (String) params.get("portfolioId")));
        } else {
            params.put("portfolioId", new ArrayList<>());
        }
        // 利率互换存续期类型数据字典转为投管业务类型
        if (StringUtil.isNotEmpty(params.get("pridBussType"))) {
            if (StringUtil.equals(params.get("pridBussType"),
                DictCodeListExt2.利率互换存续期类型_中间收付息.getValue())) {
                params.put("tgBussType", DictCodeListExt2.平安业务类型_利率互换中间收付息.getValue());
            }
            else if (StringUtil.equals(params.get("pridBussType"),
                DictCodeListExt2.利率互换存续期类型_到期.getValue())) {
                params.put("tgBussType", DictCodeListExt2.平安业务类型_利率互换到期.getValue());
            }
            else {
                params.put("tgBussType", DictCodeListExt2.平安业务类型_利率互换提前终止.getValue());
            }
        }
        params.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/findPage", params,
                TrdIRSDuration002ProcessDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam
                .config("/com/joyintech/tams/invest/period/trdirsduration/config/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdIRSDuration002ProcessDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 获取所有交易编号（业务编号）
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/getBusiCode",
                map, SelectOptionDto.class);
    }

    /**
     * 获取所有金融产品代码（交易编号）
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getTradeId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/getFinprodId", map,
                SelectOptionDto.class);
    }
}

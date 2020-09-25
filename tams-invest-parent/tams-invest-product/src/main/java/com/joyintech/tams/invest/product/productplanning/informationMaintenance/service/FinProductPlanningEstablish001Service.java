package com.joyintech.tams.invest.product.productplanning.informationMaintenance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/**
 * 产品规划信息维护
 *  * @author 代国富
 *  * @version 1.0 2019年12月12日
 */
@Service
public class FinProductPlanningEstablish001Service extends CommonExportService<FinProductPlanning001Dto> implements BaseProcessService<FinProductPlanning001Dto> {

    @Override
    public Page<FinProductPlanning001Dto> selectPageData(Map<String, Object> selectParam) {
        selectParam.put("validStatus","");
        return this.findPageAll(selectParam);
    }


    /**
     * 下拉框展示
     *  @return 下拉框列
     *   * @throws Exception Exception
     */
    public Page<SelectOptionDto> querySeriesName(Map<String, Object> param)
            throws Exception {
        param.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/querySeriesName", param,
                SelectOptionDto.class);
    }
    /**
     * 查询页面根据条件查询
     * @param pageParam
     * @return
     */
    public Page<FinProductPlanning001Dto> findPageAll(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findPageAll", pageParam,
                FinProductPlanning001Dto.class);
    }

    @Override
    public void doComplete(FinProductPlanning001Dto entity, OperationType type) {
        String planningNo = entity.getPlanningNo();
        String planningSeriesNo = entity.getPlanningSeriesNo();
        Map<String,Object> params = new HashMap<>();
        params.put("planningNo",planningNo);
        params.put("planningSeriesNo",planningSeriesNo);
        //根据编号删除该规划info 和规划detail
        restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/deleteByPrimary",
                params,
                Integer.class
        );
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/product/productplanning/informationMaintenance/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

}

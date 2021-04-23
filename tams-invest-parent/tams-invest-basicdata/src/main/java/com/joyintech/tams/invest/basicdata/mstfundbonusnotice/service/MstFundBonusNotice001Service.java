package com.joyintech.tams.invest.basicdata.mstfundbonusnotice.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNotice001Service
 * @Description 基金分红公告
 * @author zhuhanlu 
 * @date 2020/8/7 14:21 
 * @version 1.0 
 */
@Service
public class MstFundBonusNotice001Service extends CommonExportService<MstFundBonusNotice001Dto> implements BaseProcessService<MstFundBonusNotice001Dto> {


    //资产代码下拉框
    public List<SelectOptionDto>  getFundId(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/MstFundBonusNotice/getFundId",map,SelectOptionDto.class);
    }



    //分页查询
    public Page<MstFundBonusNotice001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFundBonusNotice/findPage",
                pageParam,MstFundBonusNotice001Dto.class);
    }


    /**
     *
     * 导出数据查询
     */
    @Override
    public Page<MstFundBonusNotice001Dto> selectPageData(Map<String, Object> selectParam) {
        Page<MstFundBonusNotice001Dto> pageTemp = selectForPage(selectParam);
        return pageTemp;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/basicdata/mstfundbonusnotice/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }


    /**
     *
     * @Description 获取符合目标的所有数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto>
     * @version 1.0
     * @see
    **/
    public List<MstFundBonusNotice001Dto> selectAllList(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/MstFundBonusNotice/selectAllList",pageParam,MstFundBonusNotice001Dto.class);
    }

    /**
     *
     * @Description 流程回调函数 删除 新建 修改
     * @Param [entity, type]
     * @return void
     * @version 1.0
     * @see
    **/
    @Override
    public void doComplete(MstFundBonusNotice001Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME+"/MstFundBonusNotice/doComplete/" + type, entity, Integer.class);
    }
}

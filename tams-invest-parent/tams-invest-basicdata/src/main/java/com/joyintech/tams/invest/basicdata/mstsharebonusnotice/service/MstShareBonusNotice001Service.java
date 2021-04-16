package com.joyintech.tams.invest.basicdata.mstsharebonusnotice.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstshareprice.dto.MstSharePriceDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname zhuhanlu
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/8/6 16:35 
 * @version 1.0
 *
 */
@Service
public class MstShareBonusNotice001Service extends CommonExportService<MstShareBonusNotice001Dto> implements BaseProcessService<MstShareBonusNotice001Dto>{


    /**
     *
     * @Description 分页查询
     * @Param [pageParam]
     * @return com.joyintech.framework.grid.Page<com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto>
     * @version 1.0
     * @see
    **/
    public Page<MstShareBonusNotice001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstShareBonusNotice/findPage",
                pageParam,MstShareBonusNotice001Dto.class);
    }

    //资产代码下拉框
    public List<SelectOptionDto> getShareId(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/MstShareBonusNotice/getShareId",map,SelectOptionDto.class);
    }

    /**
     *
     * 导出数据查询
     */
    @Override
    public Page<MstShareBonusNotice001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }


    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/basicdata/mstsharebonusnotice/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     *
     * @Description 流程回调函数
     * @Param [entity, type]
     * @return void
     * @version 1.0
     * @see
    **/
    @Override
    public void doComplete(MstShareBonusNotice001Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME+"/MstShareBonusNotice/doComplete/" + type, entity, Integer.class);
    }


    /**
     *
     * @Description 获取符合目标的所有数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto>
     * @version 1.0
     * @see
    **/
    public List<MstShareBonusNotice001Dto> selectAllList(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                + "/MstShareBonusNotice/selectAllList",pageParam,MstShareBonusNotice001Dto.class);
    }
}

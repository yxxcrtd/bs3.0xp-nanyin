package com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.dto.StandardCouponReConversionRateDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class StandardCouponReConversionRateService extends CommonExportService<StandardCouponReConversionRateDto>
        implements BaseProcessService<StandardCouponReConversionRateDto>{


    /**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<StandardCouponReConversionRateDto> selectForPage(Map<String, Object> pageParam){
//        if(StringUtil.isNotEmpty(pageParam.get("brokerId"))){
//            List<String> brokerIdList = StringUtil.splitToList(",",(String)pageParam.get("brokerId"));
//            pageParam.put("brokerIdList", brokerIdList);
//        }
//
//        if(StringUtil.isNotEmpty(pageParam.get("accountType"))){
//            List<String> accountTypeList = StringUtil.splitToList(",",(String)pageParam.get("accountType"));
//            pageParam.put("accountTypeList", accountTypeList);
//        }
        return restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/StandardCouponReConversionRate/selectForPage",
                pageParam, StandardCouponReConversionRateDto.class);
    }



    /**
     * 券商 <功能详细描述>
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/StandardCouponReConversionRate/getMstCustomerInfo", map,
                SelectOptionDto.class);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询
     *
     * @param
     *
     * @return MstSaleDepartmentDto
     * @see
     * @since 1.0
     */
    public StandardCouponReConversionRateDto selectByPrimaryKey(StandardCouponReConversionRateDto dto) {
        return restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/StandardCouponReConversionRate/selectByPrimaryKey", dto,
                StandardCouponReConversionRateDto.class);
    }

    /**
     * 导出查询
     */
    @Override
    public Page<StandardCouponReConversionRateDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/standardcouponreconversionrate/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    @Override
    public void doComplete(StandardCouponReConversionRateDto entity,
                           OperationType type) {
        entity.setOperateType(type.toString());
        String action = StringUtil.EMPTY;
        if (entity.getOperateType().equals("DELETE")) {
            action = "delete";
        } else if (entity.getOperateType().equals("NEW")) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/StandardCouponReConversionRate/" + action, entity, Integer.class);

    }


    /**
     * 查询组合资金账户
     * @return
     */
    public List<SelectOptionDto> getPtlSecAcctInfo(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME
                        + "/StandardCouponReConversionRate/getPtlSecAcctInfo", map,
                SelectOptionDto.class);
    }

    public List<SelectOptionDto> brokerIdChange(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                        + "/StandardCouponReConversionRate/brokerIdChange", map,
                SelectOptionDto.class);
    }
}

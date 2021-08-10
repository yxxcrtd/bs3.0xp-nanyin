package com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.exchangerateinfo.dto.MstExchangeQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstFuturesPriceInfoService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/26 17:42 
 * @version 1.0 
 */
@Service
public class MstFuturesPriceInfoService extends CommonExportService<MstFuturesPriceInfoDto>
        implements BaseProcessService<MstFuturesPriceInfoDto> {

    /**
     *
     * @Description //Wind代码下拉框
     * @Param [parameterMap]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    public  List<SelectOptionDto> getWindCode(Map<String, Object> parameterMap) {
        return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFuturesPriceInfo/getWindCode",
                parameterMap, SelectOptionDto.class);
    }



    /**
     *
     * @Description //获得合约代码
     * @Param [parameterMap]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    public  List<SelectOptionDto> getContractId(Map<String, Object> parameterMap) {
        return restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFuturesPriceInfo/getContractId",
                parameterMap, SelectOptionDto.class);
    }


    /**
     *
     * @Description //分页查询
     * @Param [parameterMap]
     * @return com.joyintech.framework.grid.Page<com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto>
     * @version 1.0
     * @see
    **/
    public Page<MstFuturesPriceInfoDto> findPage(Map<String, Object> parameterMap) {
        Page<MstFuturesPriceInfoDto> result = restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFuturesPriceInfo/findPage",
                parameterMap, MstFuturesPriceInfoDto.class);
        if (result != null && !StringUtil.isEmptyList(result.getRoot())) {
            for (MstFuturesPriceInfoDto resultDto : result.getRoot()) {
                resultDto.setFuturesIdWithMarket(resultDto.getFuturesId());
                if (StringUtil.isNotBlank(resultDto.getFuturesId()) && StringUtil.contains(resultDto.getFuturesId(), ".")) {
                    resultDto.setFuturesId(StringUtil.splitToList(".", resultDto.getFuturesId()).get(0));
                }
            }
        }
        
        return result;
    }




    @Override
    public void doComplete(MstFuturesPriceInfoDto mstFuturesPriceInfoDto, OperationType operationType) {
        restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFuturesPriceInfo/doComplete/"+operationType,
                mstFuturesPriceInfoDto, Integer.class);
    }



    public MstFuturesPriceInfoDto selectByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFuturesPriceInfo/selectByKey",
                map, MstFuturesPriceInfoDto.class);
    }


    /**
     *
     * 导出数据查询
     */
    @Override
    public Page<MstFuturesPriceInfoDto> selectPageData(Map<String, Object> selectParam) {
        Page<MstFuturesPriceInfoDto> pageTemp = findPage(selectParam);
        return pageTemp;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/basicdata/mstfuturespriceinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
}

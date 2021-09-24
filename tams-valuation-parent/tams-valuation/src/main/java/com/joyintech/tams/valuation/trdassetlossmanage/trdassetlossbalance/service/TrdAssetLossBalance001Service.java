package com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrdAssetLossBalance001Service  extends CommonExportService<TrdAssetLossBalance001Dto> {

    /**
     * 查询画面
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TrdAssetLossBalance001Dto> queryPageList(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/findPage",
                params, TrdAssetLossBalance001Dto.class);
    }

    /**
     * 明细查询
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TrdAssetLossBalance002Dto> queryDetailList(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance002/findPage",
                params, TrdAssetLossBalance002Dto.class);
    }

    /**
     * 查询资产代码
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> params){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getFinprodId",
                params, SelectOptionDto.class);
    }

    /**
     * 查询账套代码
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> params){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getBooksetId",
                params, SelectOptionDto.class);
    }

    /**
     * 导出数据查询
     */
    public List<TrdAssetLossBalance001Dto> getAllList(Map<String, Object> params){
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getAllList",
                params, TrdAssetLossBalance001Dto.class);
    }

    public Page<SelectOptionDto> getAssetInfoSelectOption(Map<String, Object> param) {
        //根据系统参数带入需要查询的数据  带入系统参数 01毕马威 02普华
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        if(StringUtil.equals(DictCodeListExt3.减值方案类型_普华.getValue(),lossType)){
            return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/LossModCommon/getModAssetInfoPagePH", param, SelectOptionDto.class);
        }else{
            return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/LossModCommon/getModAssetInfoPageBMW", param, SelectOptionDto.class);
        }

    }

}

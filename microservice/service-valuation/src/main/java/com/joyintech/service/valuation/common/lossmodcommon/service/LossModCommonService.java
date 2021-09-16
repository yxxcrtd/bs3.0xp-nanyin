package com.joyintech.service.valuation.common.lossmodcommon.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.common.lossmodcommon.dao.LossModCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 资产信息查询共通Service
 */
@Service("seriveLossModCommonService")
public class LossModCommonService extends BaseService {

    @Autowired
    private LossModCommonMapper lossModCommonMapper;

    /**
     * 返回资产信息下拉框List对象
     * @param params
     * @return
     */
    public List<SelectOptionDto> getAssetInfoOptionList(Map<String,Object> params){
        return lossModCommonMapper.getAssetInfoOption(params);
    }

    /**
     * 返回资产信息下拉框List对象
     * @param params
     * @return
     */
    public Page<SelectOptionDto> getAssetInfoOptionPage(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> lossModCommonMapper.getAssetInfoOption(params));
    }

    /**
     * 返回减值模型普华方案资产信息下拉框List对象
     * @param params
     * @return
     */
    public Page<SelectOptionDto> getModAssetInfoOptionPagePH(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> lossModCommonMapper.getModAssetInfoOptionPH(params));
    }


    /**
     * 返回减值模型普华方案资产信息下拉框List对象
     * @param params
     * @return
     */
    public Page<SelectOptionDto> getModAssetInfoOptionPageBMW(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> lossModCommonMapper.getModAssetInfoOptionBMW(params));
    }

    /**
     * 返回减值模型产品信息下拉框List对象
     * @param params
     * @return
     */
    public Page<SelectOptionDto> getModProInfoPage(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> lossModCommonMapper.getModProInfoPage(params));
    }
}

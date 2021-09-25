package com.joyintech.service.valuation.common.lossmodcommon.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.valuation.common.lossmodcommon.service.LossModCommonService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 减值模型共通微服务Controller
 *
 * @author weipeng
 * @createTime 20210910
 */
@RestController(value="serviceLossModCommonController")
@RequestMapping("/LossModCommon")
public class LossModCommonController extends BaseController {

    @Autowired
    private LossModCommonService lossModCommonService;
    
    /**
     * 返回资产信息下拉框List对象
     * @param params：
     * @return
     */
    @PostMapping("/getAssetInfoOptionList")
    public List<SelectOptionDto> getAssetInfoOptionList(@RequestBody Map<String,Object> params){
        return lossModCommonService.getAssetInfoOptionList(params);
    }

    /**
     * 返回资产信息下拉框Page - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("getAssetInfoOptionPage/{page}/{length}")
    public Page<SelectOptionDto> getAssetInfoOptionPage(@RequestBody Map<String, Object> params,
                                                            @PathVariable("page") int page, @PathVariable("length") int length){
        return lossModCommonService.getAssetInfoOptionPage(page, length, params);
    }


    /**
     * 减值模型-普华方案资产信息下拉框Page - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("getModAssetInfoPagePH/{page}/{length}")
    public Page<SelectOptionDto> getModAssetInfoPagePH(@RequestBody Map<String, Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length){
            return lossModCommonService.getModAssetInfoOptionPagePH(page, length, params);
    }

    /**
     * 减值模型-毕马威方案资产信息下拉框Page - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("getModAssetInfoPageBMW/{page}/{length}")
    public Page<SelectOptionDto> getModAssetInfoPageBMW(@RequestBody Map<String, Object> params,
                                                             @PathVariable("page") int page, @PathVariable("length") int length){
        return lossModCommonService.getModAssetInfoOptionPageBMW(page, length, params);
    }

    /**
     * 减值模型-资产信息下拉框Page - 分页 根据系统参数方案处理
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("getModAssetInfoPage/{page}/{length}")
    public Page<SelectOptionDto> getModAssetInfoPage(@RequestBody Map<String, Object> params,
                                                       @PathVariable("page") int page, @PathVariable("length") int length){
        String lossType = CacheUtil.getSysParam("LOSS_TYPE");
        if(DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)){
            return lossModCommonService.getModAssetInfoOptionPagePH(page, length, params);
        }
        else{
            return lossModCommonService.getModAssetInfoOptionPageBMW(page, length, params);
        }

    }

    /**
     * 减值模型-产品信息下拉框Page - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("getModProInfoPage/{page}/{length}")
    public Page<SelectOptionDto> getModProInfoPage(@RequestBody Map<String, Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length){
        return lossModCommonService.getModProInfoPage(page, length, params);
    }

}

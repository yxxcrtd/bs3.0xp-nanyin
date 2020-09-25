package com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.dto.ValRateSwapInformationDto;
import com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.service.ValRateSwapInformationService;
import com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ValRateSwapInformation")
public class ValRateSwapInformationController {

    @Autowired
    private ValRateSwapInformationService valRateSwapInformationService;

    /**
     *@title: doComplete
     *@description: 工作流完成方法-insert
     *@author: daizhiwei
     *@date: 2019/06/04 11:08
     *@param: * @param dto 1
     *@return: void
     *@throws:
     */
    @PostMapping("insert")
    public void insert(@RequestBody ValRateSwapInformationDto dto) throws IllegalAccessException, InvocationTargetException {
        valRateSwapInformationService.insert(dto, dto.getOperateType());
    }

    /**
     *@title: doComplete
     *@description: 工作流完成方法-delete
     *@author: daizhiwei
     *@date: 2019/06/04 11:08
     *@param: * @param dto 1
     *@return: void
     *@throws:
     */
    @PostMapping("delete")
    public void delete(@RequestBody ValRateSwapInformationDto dto) throws IllegalAccessException, InvocationTargetException {
        valRateSwapInformationService.delete(dto, dto.getOperateType());
    }

    /**
     *@title: doComplete
     *@description: 工作流完成方法-update
     *@author: daizhiwei
     *@date: 2019/06/04 11:08
     *@param: * @param dto 1
     *@return: void
     *@throws:
     */
    @PostMapping("update")
    public void update(@RequestBody ValRateSwapInformationDto dto) throws IllegalAccessException, InvocationTargetException {
        valRateSwapInformationService.update(dto, dto.getOperateType());
    }

    /**
     *@title: check
     *@description: 操作前校验
     *@author: daizhiwei
     *@date: 2019/07/22 11:04
     *@param: * @param dto 1
     *@return: java.util.List<com.joyintech.framework.validation.ValidMessage>
     *@throws:
     */
    @PostMapping("check")
    public List<ValYieldCurveInfoDto> check(@RequestBody ValRateSwapInformationDto dto){
        return valRateSwapInformationService.check(dto);
    }

    /**
     *@title: check
     *@description: 操作前校验
     *@author: daizhiwei
     *@date: 2019/07/22 11:04
     *@param: * @param dto 1
     *@return: java.util.List<com.joyintech.framework.validation.ValidMessage>
     *@throws:
     */
    @PostMapping("selectByParams")
    public List<ValRateSwapInformationDto> selectByParams(@RequestBody ValRateSwapInformationDto dto){
        return valRateSwapInformationService.selectByParams(dto);
    }

    /**
     *@title: getCurveType
     *@description: 获取利率互换曲线类型（名称）
     *@author: 王贵奇
     *@date: 2019/09/27
     *@return: Page<SelectOptionDto>
     *@throws:
     */
    @PostMapping("getCurveType/{page}/{length}")
    public Page<SelectOptionDto> getCurveType(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return valRateSwapInformationService.getCurveType(page,length,param);
    }
}

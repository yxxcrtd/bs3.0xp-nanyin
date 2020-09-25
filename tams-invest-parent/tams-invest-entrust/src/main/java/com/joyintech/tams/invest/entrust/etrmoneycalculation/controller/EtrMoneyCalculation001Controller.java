/*
 * 文件名：EtrMoneyCalculation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：可投金额试算Controller
 * 修改人：nixiukuan
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrmoneycalculation.controller;


import com.joyintech.framework.grid.Page;
import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.etrmoneycalculation.dto.EtrMoneyCalculationDto;
import com.joyintech.tams.invest.entrust.etrmoneycalculation.service.EtrMoneyCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 可投金额试算查询
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年9月26日
 * @see EtrMoneyCalculation001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrMoneyCalculation001")
@RequestMapping("/invest/entrust/etrmoneycalculation/EtrMoneyCalculation001")
public class EtrMoneyCalculation001Controller extends BaseController{
	
	/**
	 * service
	 */
	@Autowired
	private EtrMoneyCalculationService etrMoneyCalculationService;
	
	/**
	 * 页面初始化
	 */
	@Override
    public String init(Model model) {
       //资产类型（债券/基金/股票）
        List<SelectOptionDto> codeList =new ArrayList<SelectOptionDto>();
        SelectOptionDto selectOptionDto=new SelectOptionDto();
        selectOptionDto.setId(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        selectOptionDto.setText(DictCodeListExt1.金融产品类型_投管用_债券.getCodeName());
        codeList.add(selectOptionDto);
        SelectOptionDto selectOptionDtoTwo=new SelectOptionDto();
        selectOptionDtoTwo.setId(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        selectOptionDtoTwo.setText(DictCodeListExt1.金融产品类型_投管用_基金.getCodeName());
        codeList.add(selectOptionDtoTwo);
        SelectOptionDto selectOptionDtoThree=new SelectOptionDto();
        selectOptionDtoThree.setId(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        selectOptionDtoThree.setText(DictCodeListExt1.金融产品类型_投管用_股票.getCodeName());
        codeList.add(selectOptionDtoThree);

        List<SelectOptionDto> finprodList = etrMoneyCalculationService.getFinprodByType(
                "F01");
        model.addAttribute("FINPROD", JsonUtil.objToString(finprodList));
        model.addAttribute("FINPROD_TYPE", JsonUtil.objToString(codeList));
        model.addAttribute("FINPROD_TYPE_DEFUALT", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return super.init(model);
    }
	
	/**
     * 
     * 可投金额试算
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("calculation")
    public Page<EtrMoneyCalculationDto> calculation(){
        Page<EtrMoneyCalculationDto> calculation = etrMoneyCalculationService.calculation(
                RequestUtil.getPageParam());
           return calculation;
    }

    /**
     *
     * 可投金额试算-> 选择资产代码后查询出相关属性项内容
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodMsg")
    public EtrMoneyCalculationDto getFinprodMsg(EtrMoneyCalculationDto dto){
        String finprodType=  RequestUtil.getPageParam().get("finprodType").toString();
        String finprodId=  RequestUtil.getPageParam().get("finprodId").toString();
        return  etrMoneyCalculationService.getFinprodMsg(
                finprodId,finprodType);

    }

    /**
     *
     * 可投金额试算-> 根据资产类型查询对应的资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodByType")
    public  List<SelectOptionDto> getFinprodByType(){
        String finprodType=  RequestUtil.getPageParam().get("finprodType").toString();
        return  etrMoneyCalculationService.getFinprodByType(
                finprodType);

    }
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(EtrMoneyCalculationDto dto)
        throws Exception {
        return etrMoneyCalculationService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}

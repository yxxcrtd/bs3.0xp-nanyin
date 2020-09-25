/*
 * 文件名：NetWorthMarketInfoController002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstfloatfeevarieties.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 新建费用品种信息
 *
 * @author huwei
 * @version 1.0 2018年7月24日
 * @see MstFloatFeeVarieties002Controller
 * @since 1.0
 */
@Controller
@PageId("MstFloatFeeVarieties002")
@RequestMapping("/invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002")
public class MstFloatFeeVarieties002Controller extends BaseController {
    /**
     * 开放计划组件
     */
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
  	setSelectOptionToModel(model,new DictCode[] { DictCode.频率,DictCode.计费基数_费用品种, DictCode.计息基础,
  	    DictCode.业绩报酬计算规则, DictCode.基数日期类型,DictCode.营业日准则,DictCode.计提类型,DictCode.重置规则, DictCode.利率类型});
      // 一体化对应etliu end
        model.addAttribute("vdate",this.getParam("vdate"));
        model.addAttribute("mdate",this.getParam("mdate"));
        model.addAttribute("feeList",this.getParam("feeList"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
       // 父画面收益类型
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        model.addAttribute("PROD_STYLE", this.getParam("PROD_STYLE"));
        model.addAttribute("prdinfo", this.getParam("prdinfo"));
        model.addAttribute("INVESTMENT_CLCYE", this.getParam("INVESTMENT_CLCYE"));
        model.addAttribute("dealMode", this.getParam("dealMode"));
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
      // 浮动利率下拉框（含利率行情、复合基准四个部分）
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
      // 利率行情
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for (SelectOptionDto dto : selectDto.getOptions()) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_利率);
            listFloatRate.add(dto1);
        }
      // 复合基准
        List<SelectOptionDto> finBaseRuleInfoList = bussBenchMarketInfoService.getFinBaseRuleInfo();
        for (SelectOptionDto dto : finBaseRuleInfoList) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_复合);
            listFloatRate.add(dto1);
        }
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));

      //非生效的数据点击修改
      // add 详情查看 yzg 20191111
      if("edit".equals(this.getParam("operateType")) || "reference".equals(this.getParam("operateType"))) {
    	  BussFinProductFloatFeeDto entity = (BussFinProductFloatFeeDto)JsonUtils.json2Object(
                  this.getParam("data"), BussFinProductFloatFeeDto.class);
    	  model.addAttribute(GlobalConstant.ENTITY, entity);
      }
//      return "/invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002";
      model.addAttribute("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
      model.addAttribute("entityNameFloat", "bussBenchMarkFloatInfoDto");
      return super.init(model);
    }

    /**
     * 设置页面参数
     *
     * @param model model
     * @see
     * @since 1.0
     */
    private void setEntity(Model model) {
    	// 初始化开放计划组件的画面元素
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.重置规则, DictCode.利率类型});        
        model.addAttribute("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        model.addAttribute("entityNameFloat", "bussBenchMarkFloatInfoDto");
        //model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        //model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面处理模式Id
        //model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        //model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        //model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        //model.addAttribute("dealModeElement", StringUtil.defaultString(this.getParam("dealModeElement")));
        //model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        
        this.setBenchEntity(model);
    }
    
    /**
     * 设置页面参数
     *
     * @param model model
     * @see
     * @since 1.0
     */
    private void setBenchEntity(Model model) {
        // 在工作流中打开
        String workFlowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String finprodId = this.getParam("finprodId");
        if (StringUtil.isNotEmpty(workFlowId)) {
            BussBenchMarkInfoDto param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workFlowId, "bussBenchMarkFloatInfoDto", BussBenchMarkInfoDto.class);
            model.addAttribute("entity_float", param);
        }
        else if (StringUtil.isNotEmpty(finprodId)){
            // 编辑和双击查看时
            String effDate = this.getParam("effDate");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", finprodId);
            param.put("effDate", effDate);
            param.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
            param.put("publishType", DictCodeListExt1.披露类型_单值披露.getValue());
            if (StringUtil.isNotEmpty(finprodId) && null != bussBenchMarketInfoService) {
                BussBenchMarkInfoDto dto = bussBenchMarketInfoService.getByFinprodIdAndEffDate(
                    param);
                model.addAttribute("entity_float", dto);
            }
        }
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @throws IOException 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated BussFinProductFloatFeeDto dto, BindingResult result) throws IOException {
    	//业绩基准相关校验
    	List<ValidMessage> validList = new ArrayList<>();
        if (StringUtil.isNotEmpty(dto.getRateType())) {
            if (StringUtil.equals(dto.getRateType(),
                DictCodeList.利率类型_固定利率.getValue())) {
                if (!StringUtil.isNotEmpty(dto.getRate())) {
                    validList.add(new ValidMessage("rate",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                }
            }
            else if (StringUtil.equals(dto.getRateType(),
                DictCodeList.利率类型_浮动利率.getValue())) {
                if (!StringUtil.isNotEmpty(dto.getIndexId())) {
                    validList.add(new ValidMessage("indexId", getMessage("BUSS_COM_E_0038",
                        getMessage("BussBenchMarkInfo.indexId"))));
                }
                if (!StringUtil.isNotEmpty(dto.getCoefficient())) {
                    validList.add(new ValidMessage("coefficient", getMessage("BUSS_COM_E_0038",
                        getMessage("BussBenchMarkInfo.msg.coefficient"))));
                }
                if (!StringUtil.isNotEmpty(dto.getSpreadRate())) {
                    validList.add(new ValidMessage("spreadRate", getMessage("BUSS_COM_E_0038",
                        getMessage("BussBenchMarkInfo.msg.spreadRate"))));
                }
                //固定利率浮动规则不可填 yzg 20191115 start
                if(StringUtil.isEmpty(dto.getResetType())){
                    validList.add(new ValidMessage("resetType",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                }
                //固定利率浮动规则不可填 yzg 20191115 end
            }
        }
        else{
        	if (!StringUtil.isNotEmpty(dto.getRateType())) {
                validList.add(new ValidMessage("rateType",
                    getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateType"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getDealMode()) && (StringUtil.equals(dto.getDealMode(),
                BussinessDictCodeList.处理模式_分档.getValue()))  && StringUtil.isEmptyList(dto.getFinFloatCalcRuleDetailDtoList())) {

            validList.add(new ValidMessage("floatManageFee",
                    getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.floatManageFee"))));
        }
        if(!StringUtil.isEmptyList(validList)){
        	if(result.hasErrors()){
        		AjaxResult ar = new AjaxResult(result);
                List<ValidMessage> errorList = ar.getErrorList();
                errorList.addAll(validList);
                return ar;
        	}
        	else{
        		  AjaxResult ar = new AjaxResult();
                  ar.setErrorList(validList);
                  return ar;
        	}
        }
    	
		return  commonCheck(result, "save");
    }

}

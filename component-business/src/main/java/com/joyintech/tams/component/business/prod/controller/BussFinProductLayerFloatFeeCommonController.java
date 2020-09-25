/*
 * 文件名：BussFinProductFloatFeeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 分档产品浮动管理费
 * 
 * @author 孟宏伟
 * @version 1.0 2019年06月24日
 * @see BussFinProductLayerFloatFeeCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductLayerFloatFeeCommon")
public class BussFinProductLayerFloatFeeCommonController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFloatFeeService bussFinProductFloatFeeService;

    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model,new DictCode[] { DictCode.频率,DictCode.计费基数_费用品种, DictCode.计息基础,
    	    DictCode.业绩报酬计算规则, DictCode.基数日期类型,DictCode.营业日准则,DictCode.重置规则,DictCode.计提类型});
        // 一体化对应etliu end
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面收益类型
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        // 父画面生效日期
        model.addAttribute("effDate", StringUtil.defaultString(this.getParam("effDate")));
        model.addAttribute("mdate", this.getParam("mdate"));
        model.addAttribute("vdate", this.getParam("vdate"));
        model.addAttribute("productBuildForm", this.getParam("productBuildForm"));
        model.addAttribute("feeList", this.getParam("feeList"));
        model.addAttribute("dealModeElement", this.getParam("dealModeElement"));
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        //add by wm 2019-10-09 start 增加产品处理模式-周期型
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        //add by wm 2019-10-09 end 增加产品处理模式-周期型
        model.addAttribute("periodElement", StringUtil.defaultString(this.getParam("periodElement")));
        model.addAttribute("TERM_TYPE_02", DictCodeListExt2.期限类型_多固定期限.getValue());
        return "component/prod/BussFinProductLayerFloatFeeCommon";
    }

   /**
              * 获取浮动管理费计划
    * 
    * @return 浮动管理费计划
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("getFloatFeeInfoList")
   public List<BussFinProductFloatFeeDto> getFloatFeeInfoList(){
	  // 金融产品代码
      String finprodId = this.getParam("finprodId");
      // 工作流代码
      String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
      if (StringUtil.isNotEmpty(workflowId)) {
    	  BussFinProductFloatFeeDto bussFinProductFloatFeeDto = bussFinProductFloatFeeService.productFloatFeeInfoQueryByWflId(
              workflowId, StringUtil.defaultString(this.getParam("entityName"),
                  BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
    	  if(bussFinProductFloatFeeDto != null && !StringUtil.isEmptyList(bussFinProductFloatFeeDto.getBussFinProductFloatFeeInfoDtoList())){
    	      return bussFinProductFloatFeeDto.getBussFinProductFloatFeeInfoDtoList();
    	  }
      }
      else if (StringUtil.isNotEmpty(finprodId)) {//查询库中的数据
          List<BussFinProductFloatFeeDto> list = bussFinProductFloatFeeService.queryLayerFloatFeeInfoByFinprodId(finprodId, this.getParam("effDate"));
          if(!StringUtil.isEmptyList(list)){
              return list;
          }
      }
      return Collections.emptyList();
   }
   
}

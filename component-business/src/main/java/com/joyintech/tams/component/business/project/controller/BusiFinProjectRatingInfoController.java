/*
 * 文件名：BusiFinBondRatingInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.bond.BusiFinBondRatingInfoService;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.project.BusiFinProjectRatingInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  项目类评级信息
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2020年11月17日
 * @see BusiFinProjectRatingInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/project/BusiFinProjectRatingInfo")
public class BusiFinProjectRatingInfoController extends BaseController{
    
    @Autowired
    private BusiFinProjectRatingInfoService busiFinProjectRatingInfoService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BusiFinBondRatingInfoService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityName", entityName);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.评级对象显示类型,null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index--) {
                SelectOptionDto option = options.get(index);
                if (StringUtil.equals(option.getId(), DictCodeList.评级类型_债项评级.getValue())) {
                    options.remove(option);
                }
            }
            model.addAttribute("SETUP_RATE_TYPE", JsonUtils.objectArray2Json(options.toArray()));
        }
        this.setSelectOptionToModel(model, DictCode.评级结果);
        this.setSelectOptionToModel(model, DictCode.评级展望);
        this.setSelectOptionToModel(model, DictCode.变动方向);
        this.setSelectOptionToModel(model, DictCode.评级机构);
        return "component/project/BusiFinProjectRatingInfo";
    }
    
    /**
     * 初始化评级信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initInfo")
    @ResponseBody
    public List<MstBondCreditRating> initInfo() {
        List<MstBondCreditRating> mstBondCreditRatingList = new ArrayList<MstBondCreditRating>();
        String finprodId = this.getParam("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
	        if(!WorkFlowUtil.isNotFromToDoList()){
	            String workFlowId = this.getParam("workflowId");
	            String entityName = StringUtil.defaultString(this.getParam("entityName"),
	                BusiFinBondRatingInfoService.DEFAULT_ENTITY_NAME);
	            //根据工作流与留痕处理提供的共通方法获取业务实体
	            BusiFinBondRatingInfoDto busiFinBondRatingInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BusiFinBondRatingInfoDto.class);
	            if(null!=busiFinBondRatingInfoDto){
	                mstBondCreditRatingList = busiFinBondRatingInfoDto.getMstBondCreditRatingList();
	            }
	        }else{
	            Map<String,Object> param = new HashMap<String,Object>();
	            param.put("finprodId", finprodId);
	            mstBondCreditRatingList = busiFinProjectRatingInfoService.getmstBondCreditRatingByProductId(param);
	            Map<String,Object> ztparam = new HashMap<String,Object>();
	            //主体为发行人（债券类资产）
	            if(StringUtil.isNotEmpty(this.getParam("issuer"))) {
	            	ztparam.put("oCode", this.getParam("issuer"));
		            mstBondCreditRatingList.addAll(busiFinProjectRatingInfoService.getmstBondCreditRatingByOcode(ztparam));
	            }
	            //主体为融资人（项目类资产）
	            else if(StringUtil.isNotEmpty(this.getParam("financier"))) {
//	            	ztparam.put("oCode", this.getParam("financier"));
//		            mstBondCreditRatingList.addAll(busiFinProjectRatingInfoService.getmstBondCreditRatingByOcode(ztparam));
	            }
	        }
        }
        return mstBondCreditRatingList;
    }
}

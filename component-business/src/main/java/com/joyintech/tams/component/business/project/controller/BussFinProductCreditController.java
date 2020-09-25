package com.joyintech.tams.component.business.project.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.bond.BusiFinBondRatingInfoService;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.project.BussFinProductCreditService;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
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
 *  增信信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/project/BussFinProductCredit")
public class BussFinProductCreditController extends BaseController {

    @Autowired
    private BussFinProductCreditService bussFinProductCreditService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
                BussFinProductCreditService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityName", entityName);
        this.setSelectOptionToModel(model, DictCode.增信方式);
        model.addAttribute("customerType",DictCodeList.客户类型_法人.getValue());
        return "component/project/BussFinProductCredit";
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
    public List<FinProductCredit> initInfo() {
        List<FinProductCredit> finProductCreditList = new ArrayList<FinProductCredit>();
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        if(StringUtil.isNotEmpty(workFlowId)){
            String entityName = StringUtil.defaultString(this.getParam("entityName"),
                    BussFinProductCreditService.DEFAULT_ENTITY_NAME);
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussFinProductCreditDto bussFinProductCreditDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussFinProductCreditDto.class);
            if(null!=bussFinProductCreditDto){
                finProductCreditList = bussFinProductCreditDto.getFinProductCreditList();
            }
        }else{
            if(StringUtil.isNotEmpty(finprodId)){
                Map<String,Object> param = new HashMap<String,Object>();
                param.put("finprodId", finprodId);
                finProductCreditList = bussFinProductCreditService.getfinProductCreditByProductId(param);
            }
        }
        return finProductCreditList;
    }

    @ResponseBody
    @RequestMapping("getMarketInstitutionCustomerName")
    public List<SelectOptionDto> getMarketInstitutionCustomerName(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return bussFinProductCreditService.getMarketInstitutionCustomerName(param);
    }

}

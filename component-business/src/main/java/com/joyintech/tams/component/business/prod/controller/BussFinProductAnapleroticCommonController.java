package com.joyintech.tams.component.business.prod.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductAnapleroticService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductAnapleroticInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 回补信息组件
 *
 * @author 李俊
 * @version 1.0 2019年4月21日
 * @see BussFinProductAnapleroticCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductAnapleroticCommon")
public class BussFinProductAnapleroticCommonController extends BaseController {
    @Autowired
    private BussFinProductAnapleroticService bussFinProductAnapleroticService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("entityName",StringUtil.defaultString(this.getParam("entityName"),BussFinProductAnapleroticService.DEFAULT_ENTITY_NAME));
        model.addAttribute("mdateElement", this.getParam("mdateElement"));
        model.addAttribute("vdateElement", this.getParam("vdateElement"));
        model.addAttribute("finprodMarketIdElement", this.getParam("finprodMarketIdElement"));
        model.addAttribute("dealCalIdElement", this.getParam("dealCalIdElement"));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("pageId", this.getParam("pageId"));
        model.addAttribute("workflowId", this.getParam("workflowId"));
        model.addAttribute("isAnapleroticElement",this.getParam("isAnapleroticElement"));
        model.addAttribute("operationTypeElement",this.getParam("operationTypeElement"));
        //业绩回补增加分档初始化参数增加 2019-12-09 start
        model.addAttribute("dealModeElement",this.getParam("dealModeElement"));
        model.addAttribute("anapleroticDealMode11",DictCodeList.处理模式_分档.getValue());
        //业绩回补增加分档初始化参数增加 2019-12-09 end

        model.addAttribute("openModeElement",this.getParam("openModeElement"));

        return "component/prod/BussFinProductAnapleroticCommon";
    }
    /**
     * 查询回补信息数据
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAnapleroticInfo")
    public List<BussFinProductAnapleroticInfoDto> getAnapleroticInfo(){
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String finprodId = this.getParam("finprodId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
                BussFinProductAnapleroticService.DEFAULT_ENTITY_NAME);
        String ptlId = this.getParam("ptlId");
        // 快速录入标志
        String quickFlag = this.getParam("quickFlag");
        if (StringUtil.isNotEmpty(ptlId)){
            return bussFinProductAnapleroticService.queryFinProductAnapleroticInfoById(ptlId);
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && !StringUtil.equals(quickFlag, "true")) {
            BussFinProductAnapleroticInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    workflowId, entityName, BussFinProductAnapleroticInfoDto.class);
            if (dto != null){
                return dto.getFinProductAnapleroticTabInfoDtoList();
            }
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            return bussFinProductAnapleroticService.queryFinProductAnapleroticInfoById(finprodId);
        }
        return Collections.emptyList();
    }
}
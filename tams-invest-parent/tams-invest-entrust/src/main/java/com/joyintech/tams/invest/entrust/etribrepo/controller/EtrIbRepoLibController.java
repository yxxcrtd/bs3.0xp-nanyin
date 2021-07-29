package com.joyintech.tams.invest.entrust.etribrepo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoLibDto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoLibService;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@PageId("EtrIbRepoLib")
@RequestMapping("/invest/entrust/etribrepo/EtrIbRepoLib")
@Controller
public class EtrIbRepoLibController extends BaseController{

	@Autowired
	private EtrIbRepoLibService etrIbRepoLibService;
	
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    public String init(Model model) {

        EtrIbRepoLibDto dto = new EtrIbRepoLibDto();
        dto.setPortfolioId(this.getParam("portfolioId"));
        dto.setTradePlace(this.getParam("tradePlace"));
        dto.setSecAcctId(this.getParam("secAcctId"));
        model.addAttribute("entity", dto);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.资产二级分类);
        this.setSelectOptionToModel(model, DictCode.评级结果);
        return super.init(model);
    }
    

    /**
     * 根据画面条件检索可质押券信息
     *
     * @param params 画面参数
     * @return List<TrdStandardBond002Dto>
     */
    @RequestMapping("selectAvaPledgeBondList")
    @ResponseBody
    public List<EtrIbRepoLibDto> selectAvaPledgeBondList(@RequestParam Map<String, Object> params) {
    	List<EtrIbRepoLibDto> list = etrIbRepoLibService.selectAvaPledgeBondList(params);
        return list;
    }

    /**
     * 根据画面条件检索已入库债券信息
     *
     * @param params 画面参数
     * @return List<TrdStandardBond002Dto>
     */
    @RequestMapping("selectPledgeBondList")
    @ResponseBody
    public List<EtrIbRepoLibDto> selectPledgeBondList(@RequestParam Map<String, Object> params) {
        return etrIbRepoLibService.selectPledgeBondList(params);
    }
    
    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("check")
    @ResponseBody
    AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody EtrIbRepoLibDto dto, BindingResult result)
            throws Exception {
        return commonCheck(result, null);
    }

    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("save")
    AjaxResult save(@Validated({ValidateGroupEdit.class}) @RequestBody EtrIbRepoLibDto dto, BindingResult result)
            throws Exception {
        if (result != null && result.hasErrors()) {
            return commonCheck(result, null);
        }
        if(StringUtil.isBlank(dto.getTradeId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
                codeParameter.setSeqNo("0521");
                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            } else {
                dto.setTradeId(IDGenerator.generate());
            }
        }
        ProcessParameter<EtrIbRepoLibDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<EtrIbRepoLibDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        } else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        dto.setBranch((Long) Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment("银行间质押券入库"+"_"+getMessage("组合代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getPortfolioId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("证券账户") + GlobalConstant.OPEN_PARENTHESIS +  entity.getPledgeBondList().get(0).getSecAcctId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("债券代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getFinprodMarketId() + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = etrIbRepoLibService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}

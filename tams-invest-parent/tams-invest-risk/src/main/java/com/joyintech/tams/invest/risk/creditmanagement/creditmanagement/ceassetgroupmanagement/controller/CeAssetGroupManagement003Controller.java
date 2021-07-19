/**
 * 
 */
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement002Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月8日
 * @see CeAssetGroupManagement003Controller
 * @since 1.0
 */
@Controller
@PageId("CeAssetGroupManagement003")
@RequestMapping("/invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003")
public class CeAssetGroupManagement003Controller extends BaseController {
    /**
     * CeAssetGroupManagement002Service
     */
    @Autowired
    private CeAssetGroupManagement002Service ceAssetGroupManagement002Service;
    /**
     * CeAssetGroupManagement003Service
     */
    @Autowired
    private CeAssetGroupManagement003Service ceAssetGroupManagement003Service;

    @Override
    public String init(Model model) {
        String workflowId = this.getParam("workflowId");
        String seqno = this.getParam("seqno");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            model.addAttribute("workflowId", workflowId);
            CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto = ceAssetGroupManagement003Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", ceAssetGroupManagement002Dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(seqno)) {
            CeAssetGroupManagement001Dto assetPoolInfoBySeqno = ceAssetGroupManagement002Service.getBusinessInfoBySeqno(seqno);
            model.addAttribute("entity", assetPoolInfoBySeqno);
        }
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 资产明细查询
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<CeAssetGroupManagement002Dto> findPage() {
        String workflowId = this.getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto = ceAssetGroupManagement003Service.getBussinessEntityByWorkflowId(
                workflowId);
            List<CeAssetGroupManagement002Dto> selectedItem = ceAssetGroupManagement002Dto.getSelectedItem();
            //修改模式下，点击查询，查出相应资产
            if (StringUtil.equals(this.getParam("pageModel"),
                GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
                && this.getParam("elementIdForLog").contains("search")) {
                List<CeAssetGroupManagement002Dto> selectAssetInfo = ceAssetGroupManagement003Service.selectAssetInfo(RequestUtil.getPageParam());
                return selectAssetInfo;
            }
            //判断selectedItem是否为空，不为空则为提交的数据，为空则为删除的数据
            if (StringUtil.isEmptyList(selectedItem)) {
                List<CeAssetGroupManagement002Dto> list = new ArrayList<>();
                list.add(ceAssetGroupManagement002Dto);
                return list;
            }
            return selectedItem;
        }
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        //通过系列代码查询系列下已有的资产明细
        List<CeAssetGroupManagement002Dto> list = ceAssetGroupManagement003Service.getExistAssetBySeqno(param);
        List<CeAssetGroupManagement002Dto> selectAssetInfo = ceAssetGroupManagement003Service.selectAssetInfo(param);
        //过滤系列已存在的资产
        for (int i = selectAssetInfo.size()-1; i>=0; i--) {
            for (int j = 0; j < list.size(); j++ ) {
                if (StringUtil.equals(list.get(j).getFinprodId(),
                    selectAssetInfo.get(i).getFinprodId())
                    && StringUtil.equals(list.get(j).getFinprodName(),
                        selectAssetInfo.get(i).getFinprodName())) {
                    selectAssetInfo.remove(i);
                    break;
                }
            }
        }
        return selectAssetInfo;
    }
    /**
     * <一句话功能简述> 查询资产代码联想框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAssetInfo")
    public List<SelectOptionDto> queryAssetInfo() {
        return ceAssetGroupManagement003Service.queryAssetInfo(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * <一句话功能简述>
     * 保存按钮按下时check
     * @param dto
     * @param result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody CeAssetGroupManagement002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return 保存处理结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody CeAssetGroupManagement002Dto dto,BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return new AjaxResult(result);
        }
        ProcessParameter<CeAssetGroupManagement002Dto> processParameter = new ProcessParameter<>();
        // 工作流参数设置
        WorkFlowParameter<CeAssetGroupManagement002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        //设置业务编号
        String businessCode = dto.getSeqno();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        //设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeAssetGroupManagement001.seqno"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroupManagement003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  CeAssetGroupManagement002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //设置系列名称
        dto.setAssetpoolName(ceAssetGroupManagement002Service.getBusinessInfoBySeqno(dto.getSeqno()).getAssetpoolName());
        for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto : dto.getSelectedItem()) {
            ceAssetGroupManagement002Dto.setAssetpoolName(dto.getAssetpoolName());
            ceAssetGroupManagement002Dto.setSeqno(dto.getSeqno());
        }
        ProcessParameter<CeAssetGroupManagement002Dto> processParameter = new ProcessParameter<CeAssetGroupManagement002Dto>();
        WorkFlowParameter<CeAssetGroupManagement002Dto> params = new WorkFlowParameter<CeAssetGroupManagement002Dto>();
        params.setPageId("CeAssetGroupManagement003");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeAssetGroupManagement001.seqno"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("CeAssetGroupManagement002.name"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getAssetpoolName(), GlobalConstant.CLOSE_PARENTHESIS, GlobalConstant.OPEN_PARENTHESIS,
                getMessage("RSK_W_0044"), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroupManagement003Service.start(processParameter, dto.getSelectedItem().toArray(
          new CeAssetGroupManagement002Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
}

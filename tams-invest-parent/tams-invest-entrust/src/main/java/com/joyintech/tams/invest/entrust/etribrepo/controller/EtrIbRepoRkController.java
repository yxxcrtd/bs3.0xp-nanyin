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
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk005Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRkDto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoRk002Service;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoRk004Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.framework.select.TreeNode;

/**
 * 〈一句话功能简述〉 资产系列管理
 * 
 * @author wl
 * @version 1.0 2021年2月7日
 * @see EtrIbRepoRkController
 * @since 1.0
 */

@Controller
@PageId("EtrIbRepoRk")
@RequestMapping("/invest/entrust/etribrepo/EtrIbRepoRk")
public class EtrIbRepoRkController extends BaseController {
	
	@Autowired
    private EtrIbRepoRk004Service etrIbRepoRk004Service;
	@Autowired
    private EtrIbRepoRk002Service etrIbRepoRk002Service;
	

    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model, DictCode.是否,DictCode.行内资产二级分类,
                DictCode.行内资产三级分类,DictCode.评级结果);
            model.addAttribute("NO",DictCodeListExt1.是否_否.getValue());
            model.addAttribute("YES",DictCodeListExt1.是否_是.getValue());
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetName")
    public String getAssetName(Model model) {
        Map<String, Object> param = RequestUtil.getPageParam();
        model.addAttribute("list", etrIbRepoRk004Service.getAssetName(param));
        return "invest/entrust/etribrepo/EtrIbRepoRk004";
    }
    
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
    	Map<String,Object> map = RequestUtil.getPageParam();
    	if(StringUtil.equals("select", getParam("pledgeStockId"))){
    		map.put("pledgeStockId", "");
    	}else{
    		map.put("pledgeStockId", getParam("pledgeStockId"));
    	}
    	map.put("portfolioId", getParam("portfolioId"));
    	List<TreeNode> treeList = etrIbRepoRk004Service.getTreeNodeList(map);
    	return treeList;
    }
    
    /**
     * 
     * <一句话功能简述>
     * 页面查询
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrIbRepoRk005Dto> findPage() {
        if (StringUtil.isNotEmpty(this.getParam("pledgeStockId"))) {
            return etrIbRepoRk004Service.getAssetDetailBySeqno(RequestUtil.getParameters(getRequest()));
        }
        return new Page<EtrIbRepoRk005Dto>();
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody EtrIbRepoRk004Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //查询系列名称
        EtrIbRepoRk004Dto businessInfoBySeqno = etrIbRepoRk002Service.getBusinessInfoByPledgeStockId(dto.getPledgeStockId());
        dto.setPledgeStockName(businessInfoBySeqno.getPledgeStockName());
        ProcessParameter<EtrIbRepoRk004Dto> processParameter = new ProcessParameter<EtrIbRepoRk004Dto>();
        WorkFlowParameter<EtrIbRepoRk004Dto> params = new WorkFlowParameter<EtrIbRepoRk004Dto>();
        params.setPageId("EtrIbRepoRk002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getPledgeStockId());
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("质押库代码"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getPledgeStockId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("质押库名称"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getPledgeStockName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = etrIbRepoRk002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkq")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) EtrIbRepoRkDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 批量提交
     *
     * @param RepoInfoDto EtrInvestRepoInfoDto
     * @param br msg
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrIbRepoRk004Dto RepoInfoDto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 临时主键
        BussinessCodeParameter param = new BussinessCodeParameter();
        param.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
        EtrIbRepoRk004Dto name = etrIbRepoRk004Service.getName(RepoInfoDto.getPledgeStockId());
        RepoInfoDto.setPledgeStockName(name.getPledgeStockName());
        String generatedId = IDGenerator.generate();
        for (int i = 0; i < RepoInfoDto.getEtrSecStockConfig().size(); i++) {
            RepoInfoDto.getEtrSecStockConfig().get(i).setConfigId(IDGenerator.generate());
        }
        for (int i = 0; i < RepoInfoDto.getPtlSecStock().size(); i++) {
            RepoInfoDto.getPtlSecStock().get(i).setPledgeStockName(RepoInfoDto.getPledgeStockName());
        }
        ProcessParameter<EtrIbRepoRk004Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<EtrIbRepoRk004Dto> params = new WorkFlowParameter<>();
        params.setPageId("EtrIbRepoRk");
        params.setElementId(RepoInfoDto.getElementId());
        params.setBusinessCode(generatedId);
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("质押库代码"),
                GlobalConstant.OPEN_PARENTHESIS, RepoInfoDto.getPledgeStockId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("质押库名称"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getPledgeStockName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);

        String msg = etrIbRepoRk004Service.start(processParameter, RepoInfoDto);
        return new AjaxResult(msg);
    }

}

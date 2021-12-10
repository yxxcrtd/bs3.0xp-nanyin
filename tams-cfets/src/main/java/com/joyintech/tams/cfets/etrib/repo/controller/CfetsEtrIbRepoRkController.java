package com.joyintech.tams.cfets.etrib.repo.controller;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoRkDto;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrRepoPledgeAssetDetailRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsTreePortfolioRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsTreePortfolioResponse;
import com.joyintech.tams.cfets.etrib.repo.service.CfetsEtrIbRepoRKService;
import com.joyintech.tams.cfets.etrib.repo.service.CfetsEtrIbRepoinfoService;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 资产系列管理
 * 
 * @author wl
 * @version 1.0 2021年2月7日
 * @see CfetsEtrIbRepoRkController
 * @since 1.0
 */

@Controller
@PageId("CfetsEtrIbRepoRk")
@RequestMapping("/cfets/etrib/cfetsEtrIbRepoRk")
@Api(tags = "银行间回购质押券库")
public class CfetsEtrIbRepoRkController extends BaseController {
	
	@Autowired
    private CfetsEtrIbRepoRKService cfetsEtrIbRepoRKService;
	
	
	@Autowired
    private CfetsEtrIbRepoinfoService cfetsEtrIbRepoinfoService;
	
    
    /**
     * BaseGetBusinessId
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
	

    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model, DictCode.是否,DictCode.WIND一级分类_含债券一级分类等,
                DictCode.WIND二级分类_含债券二级分类等,DictCode.评级结果);
            model.addAttribute("NO",DictCodeListExt1.是否_否.getValue());
            model.addAttribute("YES",DictCodeListExt1.是否_是.getValue());
        return super.init(model);
    }

    /**
     * 组合树
     * @return
     */
    @PostMapping("/getInterGroup")
    @ResponseBody
    @ApiOperation(value = "组合树", notes = "组合树")
    public Message<List<CfetsTreePortfolioResponse>> getInterGroup(@RequestBody CfetsTreePortfolioRequest cfetsTreePortfolioRequest){
    	Map<String,Object> map = BeanUtil.object2Map(cfetsTreePortfolioRequest);
    	if(StringUtil.equals("select", getParam("pledgeStockId"))){
    		map.put("pledgeStockId", "");
    	}else{
    		map.put("pledgeStockId", getParam("pledgeStockId"));
    	}
    	map.put("portfolioId", getParam("portfolioId"));
    	List<CfetsTreePortfolioResponse> treeList = cfetsEtrIbRepoinfoService.getTreeNodeList(map);
    	return new Message<List<CfetsTreePortfolioResponse>>(treeList).success();
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
    @PostMapping("findPage")
    @ApiOperation(value = "质押券库查询", notes = "质押券库查询")
    public Message<Page<EtrSecStockConfig>> findPage(@RequestBody CfetsEtrRepoPledgeAssetDetailRequest cfetsEtrRepoPledgeAssetDetailRequest) {
        if (StringUtil.isNotEmpty(cfetsEtrRepoPledgeAssetDetailRequest.getPledgeStockId())) {
            return new Message<Page<EtrSecStockConfig>>(cfetsEtrIbRepoinfoService.getAssetDetailBySeqno(cfetsEtrRepoPledgeAssetDetailRequest)).success();
        }
        return new Message<Page<EtrSecStockConfig>>().success();
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(ACTION_DELETE)
    @ApiOperation(value = "delete", notes = "delete")
    public Message<AjaxResult> delete(@RequestBody CfetsEtrIbRepoRkDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new Message<AjaxResult>().setData(new AjaxResult(br));
        }
        //查询系列名称
        CfetsEtrIbRepoRkDto businessInfoBySeqno = cfetsEtrIbRepoRKService.getBusinessInfoByPledgeStockId(dto.getPledgeStockId());
        dto.setPledgeStockName(businessInfoBySeqno.getPledgeStockName());
        ProcessParameter<CfetsEtrIbRepoRkDto> processParameter = new ProcessParameter<CfetsEtrIbRepoRkDto>();
        WorkFlowParameter<CfetsEtrIbRepoRkDto> params = new WorkFlowParameter<CfetsEtrIbRepoRkDto>();
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
        String msg = cfetsEtrIbRepoRKService.start(processParameter, dto);
        return new Message<AjaxResult>().setData(new AjaxResult(msg));
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
    @PostMapping("checkq")
    @ApiOperation(value = "保存按钮点击时的check", notes = "保存按钮点击时的check")
    public Message<AjaxResult> check(@RequestBody @Validated({
            ValidateGroupEdit.class}) CfetsEtrIbRepoRkDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new Message<AjaxResult>().setData(new AjaxResult(result));
        }
        return new Message<AjaxResult>().setData(commonCheck(result, dto.getOperateType()));
    }
    
    /**
     * 批量提交
     *
     * @param RepoInfoDto EtrInvestRepoInfoDto
     * @param br msg
     * @throws Exception 异常
     */
    @ResponseBody
    @PostMapping("save")
    @ApiOperation(value = "批量提交", notes = "批量提交")
    public Message<AjaxResult> save(@RequestBody CfetsEtrIbRepoRkDto RepoInfoDto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new Message<AjaxResult>().setData(new AjaxResult(br));
        }
        // 临时主键
//        BussinessCodeParameter param = new BussinessCodeParameter();
//        param.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
        // 业务代码生成参数
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setSeqNo("0666");
        RepoInfoDto.setPledgeStockId(baseGetBusinessId.buildBussinessCode(parameter));
        String generatedId = IDGenerator.generate();
        for (int i = 0; i < RepoInfoDto.getEtrSecStockConfig().size(); i++) {
            RepoInfoDto.getEtrSecStockConfig().get(i).setConfigId(IDGenerator.generate());
        }
        for (int i = 0; i < RepoInfoDto.getPtlSecStock().size(); i++) {
            RepoInfoDto.getPtlSecStock().get(i).setPledgeStockName(RepoInfoDto.getPledgeStockName());
        }
        ProcessParameter<CfetsEtrIbRepoRkDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<CfetsEtrIbRepoRkDto> params = new WorkFlowParameter<>();
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
        String msg = cfetsEtrIbRepoRKService.start(processParameter, RepoInfoDto);
        return new Message<AjaxResult>().setData(new AjaxResult(msg));
    }

}

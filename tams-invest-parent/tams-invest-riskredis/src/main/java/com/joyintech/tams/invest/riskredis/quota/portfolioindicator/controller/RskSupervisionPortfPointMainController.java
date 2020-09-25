package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;

import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint002Service;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 组合关联监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPointMainController
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionPortfPointMain")
@RequestMapping("/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPointMain")
public class RskSupervisionPortfPointMainController extends BaseController {
	
	/**
     * RskSupervisionPortfPoint001Service
     */
    @Autowired(required = false)
    private RskSupervisionPortfPoint001Service rskSupervisionPortfPoint001Service;
    
    /**
     * RskSupervisionPortfPoint002Service
     */
    @Autowired(required = false)
    private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;
    
    /**
     * 初始化的查询条件
     */
   private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.指标类型,
	        BussinessDictCodeRds.监督类型, BussinessDictCodeRds.组合层次,
	        BussinessDictCodeRds.分类维度
	       };

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        SelectDto selectZhccDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.组合层次, null);
        List<SelectOptionDto> optionZhcc = selectZhccDto.getOptions();
        Map<String,List<SelectOptionDto>> mapZhcc = optionZhcc.stream().collect(JoyinCollectors.groupingBy(x->x.getId().toString()));
        model.addAttribute("mapZhcc",mapZhcc);
        return super.init(model);
    }
  
    @RequestMapping("initInd")
    public String initInd(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        return "invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPointInd001";
    }
    
    /**
     * 指标树画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskPortfolioIndicator002Dto> selectTableIndAll()
        throws Exception {
        return rskSupervisionPortfPoint001Service.selectTableIndForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 获取指标树结构数据
     * @return 画面元素
     * @throws Exception Exception
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
	@ResponseBody
    @RequestMapping("/getTreeIndicatorByIndType")
    public List<TreeNode> getTreeIndicatorByIndType(@RequestParam(name="type", required=false) String type)
        throws Exception {
        return rskSupervisionPortfPoint001Service.getTreeIndicatorByIndType(type);
        
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskPortfolioIndicator002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskPortfolioIndicator002Dto> processParameter = new ProcessParameter<RskPortfolioIndicator002Dto>();
        WorkFlowParameter<RskPortfolioIndicator002Dto> params = new WorkFlowParameter<RskPortfolioIndicator002Dto>();
        params.setPageId("RskSupervisionPortfPoint002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_限额组合关联监督指标管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionPortfPoint002.portfolioId"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getPortfolioId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionPortfPoint002.portfolioName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getPortfolioName(), GlobalConstant.CLOSE_PARENTHESIS,getMessage("RskSupervisionPortfPoint001.portfolioLevel"), GlobalConstant.OPEN_PARENTHESIS,
                SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.组合层次, entity.getPortfolioLevel()), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionPortfPoint002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new RskPortfolioIndicator002Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return rskSupervisionPortfPoint001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }

}

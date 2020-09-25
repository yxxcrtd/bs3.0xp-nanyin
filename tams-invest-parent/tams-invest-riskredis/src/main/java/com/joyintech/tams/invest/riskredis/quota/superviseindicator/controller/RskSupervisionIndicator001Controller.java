package com.joyintech.tams.invest.riskredis.quota.superviseindicator.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;



/**
 * 监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicator001Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndicator001")
@RequestMapping("/invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001")
public class RskSupervisionIndicator001Controller extends BaseController {
	
	/**
     * RskSupervisionIndicator001Service
     */
    @Autowired(required = false)
    private RskSupervisionIndicator001Service rskSupervisionIndicator001Service;
    
    /**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.指标类型,BussinessDictCodeRds.单位,
        BussinessDictCodeRds.监督类型,BussinessDictCodeRds.连接符,BussinessDictCodeRds.运算符,BussinessDictCodeRds.监督时分,BussinessDictCodeRds.金融产品类型,
        BussinessDictCodeRds.WIND一级分类_含债券一级分类等,BussinessDictCodeRds.基金类资产二级类型,BussinessDictCodeRds.申万行业一级类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("USE_STATUS_01", BussinessDictCodeListRds.使用状态_启用.getValue());
        model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue()); 
        return super.init(model);
    }
  
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskSupervisionIndicator002Dto> selectAll()
        throws Exception {
        return rskSupervisionIndicator001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
	 * 启用或停用
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "playOrstop")
	public AjaxResult playOrstop(@RequestBody RskSupervisionIndicator002Dto dto) {
		List<RskSupervisionIndicator002Dto> jtList = dto.getSelectedItem();
		String isEnabled="play".equals(dto.getOperateType())?isEnabled=BussinessDictCodeListRds.使用状态_启用.getValue():BussinessDictCodeListRds.使用状态_停用.getValue();
		try {
		    for (RskSupervisionIndicator002Dto dtoTemp : jtList) {
		    	dtoTemp.setIsEnabled(isEnabled);
			}
		    rskSupervisionIndicator001Service.playOrstop(jtList);
			return "play".equals(dto.getOperateType())?new AjaxResult(getMessage("BUSS_COM_I_0033",getMessage("RskSupervisionIndicator001.play"))):new AjaxResult(getMessage("BUSS_COM_I_0033",getMessage("RskSupervisionIndicator001.stop")));
		} catch (Exception e) {
			return new AjaxResult(e.getMessage());
		}
	}
	
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskSupervisionIndicator002Dto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
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
        ValidateGroupDelete.class}) RskSupervisionIndicator002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndicator002Dto> processParameter = new ProcessParameter<RskSupervisionIndicator002Dto>();
        WorkFlowParameter<RskSupervisionIndicator002Dto> params = new WorkFlowParameter<RskSupervisionIndicator002Dto>();
        params.setPageId("RskSupervisionIndicator002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getIndId());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_限额监督指标管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicator001.indId"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getIndId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicator002.indName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getIndName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicator001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new RskSupervisionIndicator002Dto[dto.getSelectedItem().size()]));
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
        return rskSupervisionIndicator001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}

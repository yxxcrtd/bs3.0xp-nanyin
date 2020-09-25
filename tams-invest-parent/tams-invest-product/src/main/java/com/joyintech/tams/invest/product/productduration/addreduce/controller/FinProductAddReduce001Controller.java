package com.joyintech.tams.invest.product.productduration.addreduce.controller;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce001Dto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;
import com.joyintech.tams.invest.product.productduration.addreduce.service.FinProductAddReduce001Service;
import com.joyintech.tams.invest.product.productduration.addreduce.service.FinProductAddReduce002Service;
import com.joyintech.tams.invest.product.productduration.addreduce.service.FinProductAddReduceWorkflowService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *
 */
@Controller
@PageId("FinProductAddReduce001")
@RequestMapping("/invest/product/productduration/addreduce/FinProductAddReduce001")
public class FinProductAddReduce001Controller extends BaseController {

    @Autowired
    private FinProductAddReduce001Service finProductAddReduce001Service;

//    @Autowired
//    private FinProductAddReduce002Service finProductAddReduce002Service;

    @Autowired
    private FinProductAddReduceWorkflowService revokeWorkflowService;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.交易方向,DictCode.有效状态};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("PAGE_DATA_STATUS_05", DictCodeListExt1.画面数据状态_有效.getValue());
        model.addAttribute("PAGE_DATA_STATUS_06", DictCodeListExt1.画面数据状态_撤销.getValue());
        return super.init(model);
    }

    /**
     * 查询数据
     *
     * @return 数据page
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductAddReduce001Dto> selectForPage()
        throws Exception {
        return finProductAddReduce001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductAddReduce001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(FinProductAddReduce002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductAddReduce002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductAddReduce002Dto> params = new WorkFlowParameter<>();
        
        // 一体化对应etliu start
//        params.setPageId(this.getPageId());
        params.setPageId("FinProductAddReduce002");
        // 一体化对应etliu end
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理);

        params.setBusinessCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        // update by dgf 20200117  提前赎回的业务编号没有展示
        // 设置流程业务说明, entity即传入的flowDto
        /*params.setComment(entity -> WorkFlowUtil.concatComment(entity.getFinprodMarketId(),
            GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketId() + GlobalConstant.CLOSE_PARENTHESIS, entity.getPsString(),
            "业务编号：" + entity.getBusiCode() + "确认日：" + entity.getSettleDate(), "金额：" + entity.getTradeAmt()));*/
        params.setComment(entity -> WorkFlowUtil.concatComment("业务编号(" + entity.getBusiCode() + ")确认日(" + entity.getSettleDate()+
                ")金额(" + entity.getTradeAmt() + ")元"));
        processParameter.setWorkFlowParameter(params);
        processParameter.setOperationType(OperationType.REVOKE);
        dto.setTotleTradeId(dto.getBusiCode());
        String msg = revokeWorkflowService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Validated({ValidateGroupDelete.class}) FinProductAddReduce002Dto dto,
                                  BindingResult result) {
    	AjaxResult rst = new AjaxResult();
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		if (!DictCodeListExt1.有效状态_有效.getValue().equals(dto.getEffectStatus())) {
			errorList.add(new ValidMessage("只有有效的交易才能撤销!"));
		}
		if (StringUtils.isNotEmpty(dto.getSettleDate()) &&
				dto.getSettleDate().compareTo(DateUtil.getSysDate()) < 0) {
			ValidMessage addRst = new ValidMessage("仅允许撤销确认日大于等于平台日期的交易!");
			errorList.add(addRst);
		}
		if (errorList != null && errorList.size() > 0) {
			rst.setErrorList(errorList);
			return rst;
		}

		return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 获取所有产品代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdId01")
    public Page<SelectOptionDto> getProdId01()
        throws Exception {
        return finProductAddReduce001Service.getProdId01(RequestUtil.getPageParam());
    }

}

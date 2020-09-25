package com.joyintech.tams.invest.settle.netting.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersManagement001Dto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.netting.dto.NettingManagement001Dto;
import com.joyintech.tams.invest.settle.netting.dto.NettingProcessDto;
import com.joyintech.tams.invest.settle.netting.service.NettingManagement001Service;
import com.joyintech.tams.invest.settle.netting.service.NettingManagement002Service;
import com.joyintech.tams.invest.settle.netting.service.TransferMessageService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-05 15:57
 */
@Controller
@PageId("NettingManagement001")
@RequestMapping("/invest/settle/netting/NettingManagement001")
public class NettingManagement001Controller extends BaseController {

    @Autowired
    public NettingManagement001Service nettingManagement001Service;

    @Autowired
    public NettingManagement002Service nettingManagement002Service;


    @Autowired
    public TransferMessageService transferMessageService;
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.登记托管机构, DictCode.平安业务类型,
            DictCode.处理状态, DictCode.成功失败标志,DictCode.指令处理状态, DictCode.成功失败标志,DictCode.发送状态,DictCode.处理模式, DictCode.交易方向, DictCode.有效状态,DictCode.币种};



    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("DISPOSE_STATUS_01", DictCodeList.处理状态_已处理.getValue());
        model.addAttribute("PAGE_STATUS_05", DictCodeListExt1.画面数据状态_有效.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_09", DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_10", DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_11", DictCodeListExt1.平安业务类型_债券类资产行权.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_19", DictCodeListExt1.平安业务类型_逆回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_19", DictCodeListExt1.平安业务类型_逆回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_52", DictCodeListExt1.平安业务类型_正回购到期.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_51", DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_74", DictCodeListExt1.平安业务类型_买断式正回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_75", DictCodeListExt1.平安业务类型_买断式逆回购.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_77", DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
        model.addAttribute("PINAN_BUSINESS_TYPE_76", DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findNettingTableData")
    public Page<NettingManagement001Dto> findNettingTableData() {
        Map map = RequestUtil.getPageParam();
        //String portfolioId = (String)map.get("portfolioId1");
        //System.out.println(portfolioId);
        Page<NettingManagement001Dto> page = nettingManagement001Service.findNettingTableData(RequestUtil.getPageParam());
        return page;
    }

    @ResponseBody
    @RequestMapping("findTransferTableData")
    public Page<TransferMessageDto> findTransferMessage() {
        Map map = RequestUtil.getPageParam();
        //String portfolioId = (String)map.get("portfolioId1");
        //System.out.println(portfolioId);
        return nettingManagement001Service.findTransferMessage(RequestUtil.getPageParam());
    }



    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> searchParams = RequestUtil.getPageParam();
        String fileName = "划款信息查询" + "(" + iPlatformDate.getSysDateStr() + ").xls";

        if (searchParams.get("isDvp") != null && Boolean.parseBoolean((String)searchParams.get("isDvp"))) {
            fileName = "产品端轧差管理" + "(" + iPlatformDate.getSysDateStr() + ").xls";
            return nettingManagement001Service.export(fileName, RequestUtil.getParameters(getRequest()));
        }

        return transferMessageService.export(fileName, RequestUtil.getParameters(getRequest()));
    }


    /**
     * 撤销前check add by libaisong 2019-10-14
     *
     * @param inputDto 画面输入参数dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody NettingProcessDto inputDto,
                            BindingResult result) {
        System.out.println(inputDto.getActOrderId());
        return commonCheck(result, inputDto.getOperateType());
    }


    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    public AjaxResult doRevoke(@RequestBody NettingProcessDto inputDto)
            throws Exception {
      //  System.out.println(inputDto.getOrderId());
        ProcessParameter<NettingProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<NettingProcessDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setPageId("NettingManagement002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("NettingManagement002");
        workFlowParameter.setElementId(inputDto.getElementId());
        workFlowParameter.setBusinessCode(inputDto.getActOrderId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_产品端轧差划款.getValue());
        workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                "轧差指令撤销, 指令编号：" + inputDto.getActOrderId()));
        processParameter.setWorkFlowParameter(workFlowParameter);
        String msg = nettingManagement002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
}

package com.joyintech.tams.invest.period.trdbondlendtargetduration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.dto.TrdBondLendTargetDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.service.TrdBondLendTargetDuration001Service;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.service.TrdBondLendTargetDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.controller
 * @Description: 债券存续期 查询
 * @date Date : 2018年09月21日 13:48
 */
@Controller
@PageId("TrdBondLendTargetDuration001")
@RequestMapping("/invest/period/trdbondlendtargetduration/TrdBondLendTargetDuration001")
public class TrdBondLendTargetDuration001Controller extends BaseController {

    @Autowired
    private TrdBondLendTargetDuration001Service trdBondLendTargetDuration001Service;

    @Autowired
    private TrdBondLendTargetDuration002Service trdBondLendTargetDuration002Service;

    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.平安业务类型, DictCode.画面数据状态};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodMarketId", (String)map.get("finprodId"));
            model.addAttribute("remType", map.get("remType"));
            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
            	model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
            model.addAttribute("tradeType", DictCodeListExt3.平安业务类型_融出债券付息还本_收.getValue());
        }
        return super.init(model);
    }

    /**
     * 获取所有交易编号
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdBondLendTargetDuration001Service.getBusiCode();
    }

    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdBondLendTargetDuration002ProcessDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(param.get("busiCode")) && DictCodeListExt1.画面数据状态_待处理.getValue().equals(param.get("statusValid"))) {
            return new Page<>();
        }
        return trdBondLendTargetDuration001Service.findPage(param);
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
        return trdBondLendTargetDuration001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Validated({ValidateGroupDelete.class}) TrdBondLendTargetDuration002ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdBondLendTargetDuration002ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondLendTargetDuration002ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());

        params.setBusinessCode(dto.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdBondLendTargetDuration002Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        TrdBondLendTargetDuration002ProcessDto entity = trdBondLendTargetDuration002Service.findEffectiveData(BeanUtil.object2Map(dto));
        entity.setRevokeReason(dto.getRevokeReason());
        entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getTotleTradeId()));
        entity.setTradeInfoDto(trdBondLendTargetDuration002Service.findFinProductTradeDto(entity.getTotleTradeId()));
        String msg = trdBondLendTargetDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Validated({ValidateGroupDelete.class}) TrdBondLendTargetDuration002ProcessDto dto,
                                  BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}

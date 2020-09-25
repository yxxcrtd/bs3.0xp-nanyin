package com.joyintech.tams.invest.period.trdplanintduration.controller;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdplanintduration.dto.TrdPlanInterestDurationProcessDto;
import com.joyintech.tams.invest.period.trdplanintduration.service.TrdPlanInterestDuration001Service;
import com.joyintech.tams.invest.period.trdplanintduration.service.TrdPlanInterestDuration002Service;

import static com.joyintech.tams.invest.period.trdplanintduration.service.TrdPlanInterestDuration002Service.genParams;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.controller
 * @Description: 计划类资产利率型 存续期查询
 * @date Date : 2018年09月21日 13:48
 */
@Controller
@PageId("TrdPlanInterestDuration001")
@RequestMapping("/invest/period/trdplanintduration/TrdPlanInterestDuration001")
public class TrdPlanInterestDuration001Controller extends BaseController {

    @Autowired
    private TrdPlanInterestDuration001Service trdPlanInterestDuration001Service;

    @Autowired
    private TrdPlanInterestDuration002Service trdPlanInterestDuration002Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

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
        model.addAttribute("PAGE_DATA_STATUS05", BussinessDictCodeList.画面数据状态_有效.getValue());

        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("statusValid", "01,03");
            model.addAttribute("remType", map.get("remType"));
            String quaryDate = (String)map.get("quaryDate");
            //add by dingjunfeng begin
            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
                model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
        }
        return super.init(model);
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
        return trdPlanInterestDuration001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Validated({ValidateGroupDelete.class}) TrdPlanInterestDurationProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdPlanInterestDurationProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdPlanInterestDurationProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdPlanInterestDuration002Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        TrdPlanInterestDurationProcessDto entity = trdPlanInterestDuration002Service.findEffectiveData(BeanUtil.object2Map(dto));
        entity.setBussTrdTransferOrder(periodCommonService.transformOrderDtoBack(periodCommonService.selectPayOrders(dto.getBusiCode())));
        entity.setFinAssetIntInfoList(trdPlanInterestDuration002Service.findAssetIntInfo(genParams(entity)));
        entity.setRevokeReason(dto.getRevokeReason());
        String msg = trdPlanInterestDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Validated({ValidateGroupDelete.class}) TrdPlanInterestDurationProcessDto dto,
                                  BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdPlanInterestDuration001Service.getFinprodMarketId();
    }
    
    /**
     * 分页查询 母资管计划市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getParentMarketId")
    public Page<SelectOptionDto> getParentMarketId() {
    	return trdPlanInterestDuration001Service.getParentMarketId();
    }

    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdPlanInterestDurationProcessDto> findPage() {
        return trdPlanInterestDuration001Service.findPage(RequestUtil.getPageParam());
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
        return trdPlanInterestDuration001Service.getBusiCode();
    }

}

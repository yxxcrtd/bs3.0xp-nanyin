package com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.service.MstCaptialAccountService;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.service.MstFuturesPriceInfoService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstFuturesPriceInfo002Controller
 * @Description 期货行情信息
 * @author zhuhanlu 
 * @date 2020/11/26 17:41 
 * @version 1.0 
 */
@Controller
@PageId("MstFuturesPriceInfo002")
@RequestMapping("/invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002")
public class MstFuturesPriceInfo002Controller extends BaseController {

    @Autowired
    MstFuturesPriceInfoService mstFuturesPriceInfoService;


    @Override
    public String init(Model model) {
        String futuresId = getParam("futuresId");
        String tradeDate = getParam("tradeDate");
        String workflowId = getParam("workflowId");
        if (StringUtil.isNotEmpty(futuresId)) {
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("futuresId", futuresId);
            pageParam.put("tradeDate", tradeDate);
            MstFuturesPriceInfoDto mstFuturesPriceInfoDto = mstFuturesPriceInfoService.selectByPrimaryKey(pageParam);
            model.addAttribute("entity", mstFuturesPriceInfoDto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
            MstFuturesPriceInfoDto mstFuturesPriceInfoDto = mstFuturesPriceInfoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", mstFuturesPriceInfoDto);
        }

        return super.init(model);
    }


    /**
     *
     * @Description //新建修改check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
     **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) MstFuturesPriceInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

   /**
    *
    * @Description //根据主键查询Dto
    * @Param [map]
    * @return com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto
    * @version 1.0
    * @see
   **/
    public MstFuturesPriceInfoDto selectByPrimaryKey(Map<String,Object> map){
        return mstFuturesPriceInfoService.selectByPrimaryKey(map);
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody MstFuturesPriceInfoDto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<MstFuturesPriceInfoDto> params = new WorkFlowParameter<MstFuturesPriceInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFuturesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_期货行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstFuturesPriceInfo002.futuresId")+"("+entity.getFuturesId()+")"+SpringContextUtil.getTextValue("MstFuturesPriceInfo002.tradeDate")+"("+ DateUtil.format(entity.getTradeDate())+")"+")"
        );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<MstFuturesPriceInfoDto> parameter = new ProcessParameter<MstFuturesPriceInfoDto>();
        parameter.setWorkFlowParameter(params);
        String msg = mstFuturesPriceInfoService.start(parameter, dto);
        return new AjaxResult(msg);


    }
}

package com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.service.MstFuturesPriceInfoService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import java.util.Map;

/**
 * @Classname MstFuturesPriceInfo001Controller
 * @Description 期货行情信息
 * @author zhuhanlu 
 * @date 2020/11/26 17:41 
 * @version 1.0 
 */
@Controller
@PageId("MstFuturesPriceInfo001")
@RequestMapping("/invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo001")
public class MstFuturesPriceInfo001Controller extends BaseController {


    @Autowired
    MstFuturesPriceInfoService mstFuturesPriceInfoService;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     *
     * @Description //获得Wind代码
     * @Param []
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("/getWindCode")
    public List<SelectOptionDto> getWindCode(){
        return mstFuturesPriceInfoService.getWindCode(RequestUtil.getParameters(getRequest()));
    }


    /**
     *
     * @Description //获得合约代码
     * @Param []
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("/getContractId")
    public List<SelectOptionDto> getContractId(){
        return mstFuturesPriceInfoService.getContractId(RequestUtil.getParameters(getRequest()));
    }


    /**
     *
     * @Description //分页查询
     * @Param []
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstFuturesPriceInfoDto> findPage(){
        return mstFuturesPriceInfoService.findPage(RequestUtil.getParameters(getRequest()));
    }



    /**
     *
     * @Description //删除check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstFuturesPriceInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto MstFuturesPriceInfoDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  MstFuturesPriceInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstFuturesPriceInfoDto> processParameter = new ProcessParameter<MstFuturesPriceInfoDto>();
        WorkFlowParameter<MstFuturesPriceInfoDto> params = new WorkFlowParameter<MstFuturesPriceInfoDto>();
        params.setPageId("MstFuturesPriceInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFuturesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_期货行情信息.getValue());
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstFuturesPriceInfo002.futuresId")+"("+entity.getFuturesId()+")"+SpringContextUtil.getTextValue("MstFuturesPriceInfo002.tradeDate")+"("+ DateUtil.format(entity.getTradeDate())+")"+")"
        );
        processParameter.setWorkFlowParameter(params);
        String msg = mstFuturesPriceInfoService.start(processParameter,
                dto.getSelectedItem().toArray(
                        new MstFuturesPriceInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }


    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return mstFuturesPriceInfoService.export(this.getPageId(),param);

    }


}

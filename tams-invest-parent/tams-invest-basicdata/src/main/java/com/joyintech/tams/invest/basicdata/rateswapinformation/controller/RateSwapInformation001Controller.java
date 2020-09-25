package com.joyintech.tams.invest.basicdata.rateswapinformation.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.rateswapinformation.dto.RateSwapInformationDto;
import com.joyintech.tams.invest.basicdata.rateswapinformation.service.RateSwapInformationService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
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

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 利率行情互换信息
 * 〈功能详细描述〉
 * @author daizhiwei
 * @version 1.0 2019年7月19日
 * @see RateSwapInformation001Controller
 * @since 1.0
 */
@PageId("RateSwapInformation001")
@RequestMapping("/invest/basicdata/rateswapinformation/RateSwapInformation001")
@Controller
public class RateSwapInformation001Controller extends BaseController{

    @Autowired
    private RateSwapInformationService rateSwapInformationService;

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;


    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.利率行情);
        this.setSelectOptionToModel(model, DictCode.期限品种_利率互换);
        this.setSelectOptionToModel(model, DictCode.利率互换曲线类型);
        return super.init(model);
    }

    /**
     *@title: selectForPage
     *@description:
     *@author: junYin
     *@date: 2019/07/23 13:57
     *@param: * @param  1
     *@return:
     *@throws:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RateSwapInformationDto> selectForPage(){
        return rateSwapInformationService.selectByParams(RequestUtil.getPageParam());
    }

    /**
     *@title: export
     *@description: 文件导出
     *@author: junYin
     *@date: 2019/06/04 10:08
     *@param: * @param  1
     *@return: org.springframework.http.ResponseEntity<byte[]>
     *@throws:
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return rateSwapInformationService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) RateSwapInformationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) RateSwapInformationDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //从删除列表中获取利率行情代码和日期
        RateSwapInformationDto newDto = dto.getRateSwapInformationDtoList().get(0);
        dto.setCurveDate(newDto.getCurveDate());
        dto.setCurveCode(newDto.getCurveCode());

        if(StringUtil.isNotEmpty(dto.getCurveCode())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_利率互换行情信息));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
            dto.setBusinessId(code);
        }
        ProcessParameter<RateSwapInformationDto> processParameter = new ProcessParameter<RateSwapInformationDto>();
        WorkFlowParameter<RateSwapInformationDto> params = new WorkFlowParameter<RateSwapInformationDto>();
        params.setPageId("RateSwapInformation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBusinessId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(newDto.getCurveCode() != null){
            params.setComment(
                entity -> this.getMessage("RateSwapInformation002.curveCode") + "(" + dto.getCurveCode() + ")("
                    + this.getMessage("RateSwapInformation002.curveDate") + ":" + DateUtil.format(dto.getCurveDate())
                    + ")" +
                    "");
        }
        processParameter.setWorkFlowParameter(params);
        String msg = rateSwapInformationService.start(processParameter,dto);
        return new AjaxResult(msg);

    }
}

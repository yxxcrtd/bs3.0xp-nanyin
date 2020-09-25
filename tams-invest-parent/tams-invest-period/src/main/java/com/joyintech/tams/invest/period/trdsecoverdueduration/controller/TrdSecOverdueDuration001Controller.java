package com.joyintech.tams.invest.period.trdsecoverdueduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration002Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration004Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TrdSecOverdueDurationController001
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2019/12/4 0004
 * @Version V1.0
 **/
@Controller
@PageId("TrdSecOverdueDuration001")
@RequestMapping("/invest/period/trdsecoverdueduration/TrdSecOverdueDuration001")
public class TrdSecOverdueDuration001Controller  extends BaseController {

    @Autowired
    private IPlatformDate platformDateImpl;
    @Autowired
    private TrdSecOverdueDuration001Service trdSecOverdueDurationService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, new IDictCode[]{DictCode.平安资产类型, DictCode.现金流类型, DictCode.画面数据状态});
        model.addAttribute("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        return super.init(model);
    }
    /** @MethodName: initRecePositionDetail
    * @Description: TODO 持仓明细入口
    * @Param: [model]
    * @Return: java.lang.String
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @RequestMapping("initRecePositionDetail")
    public String initRecePositionDetail(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("finprodId",map.get("finprodId").toString());
        if(map.containsKey("bjBranch")){
            model.addAttribute("bjBranch",Integer.valueOf(map.get("bjBranch").toString()));
        }
        if(map.containsKey("liBranch")){
            model.addAttribute("liBranch",Integer.valueOf(map.get("liBranch").toString()));
        }
        model.addAttribute("secAcctId",map.get("secAcctId").toString());
        return "invest/period/trdsecoverdueduration/TrdSecOverdueReceDetail";
    }
    @RequestMapping("initRecePositionDetailByPortfolioId")
    public String initRecePositionDetailByPortfolioId(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("finprodId",map.get("finprodId").toString());
        if(map.containsKey("bjBranch")){
            model.addAttribute("bjBranch",Integer.valueOf(map.get("bjBranch").toString()));
        }
        if(map.containsKey("liBranch")){
            model.addAttribute("liBranch",Integer.valueOf(map.get("liBranch").toString()));
        }
        model.addAttribute("secAcctId",map.get("secAcctId").toString());
        model.addAttribute("portfolioId",map.get("portfolioId").toString());
        return "invest/period/trdsecoverdueduration/TrdSecOverdueReceDetailByPortfolioId";
    }
    /** @MethodName: initIntertstDetail
    * @Description: TODO 收息明细信息入口
    * @Param: [model]
    * @Return: java.lang.String
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @RequestMapping("initIntertstDetail")
    public String initIntertstDetail(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNoneBlank(map.get("mdate").toString())){
            if(StringUtil.equals(map.get("mdate").toString(),"undefined")){
                model.addAttribute("mdate",platformDateImpl.getSysDate());
            }else{
                model.addAttribute("mdate", map.get("mdate").toString());
            }
        }else{
            model.addAttribute("mdate",platformDateImpl.getSysDate());
        }
        model.addAttribute("finprodId",map.get("finprodId").toString());
        model.addAttribute("secAcctId",map.get("secAcctId").toString());
        if(map.containsKey("bjBranch")){
            model.addAttribute("bjBranch",Integer.valueOf(map.get("bjBranch").toString()));
        }
        if(map.containsKey("liBranch")){
            model.addAttribute("liBranch",Integer.valueOf(map.get("liBranch").toString()));
        }
        model.addAttribute("payType",map.get("payType").toString());
        model.addAttribute("theoryPenaltyInt",map.get("theoryPenaltyInt"));
        return "invest/period/trdsecoverdueduration/TrdSecOverdueIntertstDetail";
    }
    /** @MethodName: theoryFindPage
    * @Description: TODO 逾期未结清，逾期已结清
    * @Param: []
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/5 0005
    **/
    @ResponseBody
    @RequestMapping("selectPageData")
    public Page<TrdSecOverdueDuration001Dto> selectPageData() {
        return trdSecOverdueDurationService.selectPageData(RequestUtil.getPageParam());
    }

    /** @MethodName: recePositionDetailFindPage
    * @Description: TODO 持仓明细信息
    * @Param: []
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @ResponseBody
    @RequestMapping("recePositionDetailFindPage")
    public Page<TrdSecOverdueDuration001Dto> recePositionDetailFindPage() {
        List<Long> branchList  = new ArrayList<>();
        Map<String, Object> param = RequestUtil.getPageParam();
        if(param.containsKey("liBranch")){
            branchList.add(Long.valueOf(param.get("liBranch").toString()));
        }
        if(param.containsKey("bjBranch")){
            branchList.add(Long.valueOf(param.get("bjBranch").toString()));
        }
        param.put("branchList",branchList);
        return trdSecOverdueDurationService.recePositionDetailFindPage(param);
    }

    @ResponseBody
    @RequestMapping("findRecePositionDetailByPortfolioId")
    public Page<TrdSecOverdueDuration001Dto> findRecePositionDetailByPortfolioId() {
        List<Long> branchList  = new ArrayList<>();
        Map<String, Object> param = RequestUtil.getPageParam();
        if(param.containsKey("liBranch")){
            branchList.add(Long.valueOf(param.get("liBranch").toString()));
        }
        if(param.containsKey("bjBranch")){
            branchList.add(Long.valueOf(param.get("bjBranch").toString()));
        }
        param.put("branchList",branchList);
        return trdSecOverdueDurationService.findRecePositionDetailByPortfolioId(param);
    }

    /** @MethodName: intertstDetailFindPage
    * @Description: TODO 收息明细信息
    * @Param: []
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @ResponseBody
    @RequestMapping("intertstDetailFindPage")
    public Page<TrdSecOverdueDuration001Dto> intertstDetailFindPage() {
        List<Long> branchList  = new ArrayList<>();
        Map<String, Object> param = RequestUtil.getPageParam();
        if(param.containsKey("liBranch")){
            branchList.add(Long.valueOf(param.get("liBranch").toString()));
        }
        if(param.containsKey("bjBranch")){
            branchList.add(Long.valueOf(param.get("bjBranch").toString()));
        }
        param.put("branchList",branchList);
        return trdSecOverdueDurationService.intertstDetailFindPage(param);
    }

    /** @MethodName: exportConversion
    * @Description: TODO 未结清，已结清导出
    * @Param: []
    * @Return: org.springframework.http.ResponseEntity<byte[]>
    * @Author: wangxuelei
    * @Date: 2019/12/12 0012
    **/
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        String pageId = "";
        Map<String, Object> parameters = RequestUtil.getParameters(getRequest());
        if(StringUtil.equals(parameters.get("exportType").toString(), DictCodeList.是否_是.getValue())){//已结清
            pageId = "TrdSecOverdueDuration001";
        }else{
            pageId = "TrdSecOverdueDuration010";
        }
        return trdSecOverdueDurationService.export(pageId, parameters);
    }

    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdSecOverdueDurationService.getFinprodMarketId();
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration001Dto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    @ResponseBody
    @RequestMapping("checkZr")
    public AjaxResult checkZr(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration004Dto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}

package com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.service.MstSaleDepartmentService;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.dto.StandardCouponReConversionRateDto;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.service.StandardCouponReConversionRateService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang.StringUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("StandardCouponReConversionRate001")
@RequestMapping("/invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001")
public class StandardCouponReConversionRate001Controller extends BaseController {

    @Autowired
    private StandardCouponReConversionRateService standardCouponReConversionRateService;

    /**
     *
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.资金账户类型,DictCode.申报单位,DictCode.特殊处理业务,
                DictCode.柜台接口类型,DictCode.是或否});
        List<SelectOptionDto> brokerIds = new ArrayList<SelectOptionDto> ();
        List<SelectOptionDto> accountTypeList = new ArrayList<SelectOptionDto> ();

//        SelectOptionDto aop = new SelectOptionDto ();
//        aop.setId ("00000000000000000");
//        aop.setText ("所有");





        try {
            brokerIds = getMstCustomerInfo();
//            brokerIds.add (0,aop);
            model.addAttribute("brokerIds", JsonUtils.objectArray2Json(brokerIds.toArray()));

            accountTypeList = getPtlSecAcctInfo();
//            accountTypeList.add (0,aop);
            model.addAttribute("accountTypeList", JsonUtils.objectArray2Json(accountTypeList.toArray()));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.init(model);
    }

    /**
     *
     * 页面查询，表格分页
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<StandardCouponReConversionRateDto> selectAllMstCaptialAccount()
            throws Exception {
        Page<StandardCouponReConversionRateDto> page = standardCouponReConversionRateService.selectForPage(RequestUtil.getPageParam());
        return page;
    }


    public List<SelectOptionDto> getPtlSecAcctInfo(){
        String param = super.getRequest().getParameter("param");
        Map<String, Object> map = new HashMap<String, Object> ();
        if(StringUtils.isNotEmpty(param)){
            map.put("accountType", param);
        }
        List<SelectOptionDto> temp = new ArrayList<SelectOptionDto> ();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.所有, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            temp.add (dto);
        }
        List<SelectOptionDto> tempList = standardCouponReConversionRateService.getPtlSecAcctInfo(map);
        for (int i = 0; i < tempList.size (); i++){
            temp.add (tempList.get (i));
        }
        return temp;
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
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) StandardCouponReConversionRateDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) StandardCouponReConversionRateDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<StandardCouponReConversionRateDto> processParameter = new ProcessParameter<StandardCouponReConversionRateDto>();
        WorkFlowParameter<StandardCouponReConversionRateDto> params = new WorkFlowParameter<StandardCouponReConversionRateDto>();
        params.setPageId("StandardCouponReConversionRate002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode("SCRCR002");
        params.setBusinessType(DictCodeListExt3.共通业务类型_标准券再折算率.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("资金账户类型") + "("+ entity.getAccountType () + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = standardCouponReConversionRateService.start(processParameter,
                dto.getSelectedItem().toArray(new StandardCouponReConversionRateDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }



    /**
     * 券商下拉框
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getMstCustomerInfo()
            throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, Object> map = new HashMap<String, Object> ();
        if(StringUtils.isNotEmpty(param)){
            map.put("brokerId", param);
        }
        List<SelectOptionDto> temp = new ArrayList<SelectOptionDto> ();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.所有, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            temp.add (dto);
        }
        List<SelectOptionDto> tempList = standardCouponReConversionRateService.getMstCustomerInfo(map);
        for (int i = 0; i < tempList.size (); i++){
            temp.add (tempList.get (i));
        }


        return temp;
    }


    /**
     * 文件导出 <功能详细描述>
     *
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return standardCouponReConversionRateService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}

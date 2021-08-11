package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto.*;
import com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.service.ValBalanceStatementQuery002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Controller
@PageId("ValBalanceStatementQuery002")
@RequestMapping("/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002")
public class ValBalanceStatementQuery002Controller extends BaseController {

    @Autowired
    private ValBalanceStatementQuery002Service valBalanceStatementQuery002Service;

    @Override
    public String init(Model model){
        Map<String,Object> map= RequestUtil.getPageParam();
        model.addAttribute("booksetName", map.get("booksetName"));
        model.addAttribute("name", map.get("name"));
        model.addAttribute("oCcyFmt", map.get("oCcyFmt"));
        model.addAttribute("booksetId", map.get("booksetId"));
        model.addAttribute("id", map.get("id"));
        //根据科目查询对应资产信息
        BokBussDetsub bokBussDetsub=valBalanceStatementQuery002Service.getSubnameTail(map);
        //判断是否为空，若为空则根据账套查询对应信息
        if(null==bokBussDetsub){
            FinProductDto finProductDto = valBalanceStatementQuery002Service.getFinprodByBoksetId(map);
            model.addAttribute("detailSubnameTail", finProductDto.getFinprodAbbr());
            model.addAttribute("detailSubjectNoTail", finProductDto.getFinprodId());
        }else {
            model.addAttribute("detailSubnameTail", bokBussDetsub.getDetailSubnameTail());
            model.addAttribute("detailSubjectNoTail", bokBussDetsub.getDetailSubjectNoTail());
        }
        Object dates=map.get("valDate");
        String dateString=(String) dates;
        Long valDates = Long.valueOf(dateString);
        Date valDate=new Date(valDates);
        String formatDate= DateUtil.format(valDate, "yyyy-MM-dd");
        String sysDateRange=formatDate + " - " + formatDate;
        model.addAttribute("sysDateRange",sysDateRange);
        model.addAttribute("formatDate",formatDate);
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValBalanceStatementQuery002Dto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        return ajaxResult;
    }

    /**
     * 联动获取记帐日期
     * @param valdateDes
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("getJiZhangDate")
    public AjaxResult getJiZhangDate(@RequestBody @Valid ValdateDes valdateDes, BindingResult result ) throws ParseException {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date valDate=sdf.parse(valdateDes.getValDates());
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(valDate);
        calendar.add(Calendar.DATE,-valdateDes.getDesDate());
        Date jiZhangDate=calendar.getTime();
        String jiZhangDates=sdf.format(jiZhangDate);
        String jiZhangDateString=jiZhangDates+" - "+jiZhangDates;
        ajaxResult.setData(jiZhangDateString);
        return ajaxResult;
    }

    /**
     * 余额流水信息表查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage1")
    public Page<ValBalanceStatementQuery002Dto> findPage1() throws Exception {
        Page<ValBalanceStatementQuery002Dto> page = valBalanceStatementQuery002Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 凭证信息信息表查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage2")
    public Page<ValBalanceStatementQuery003Dto> findPage2() throws Exception {
        Page<ValBalanceStatementQuery003Dto> page = valBalanceStatementQuery002Service.selectForVouchPage(RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 交易信息表查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage3")
    public Page<ValBalanceStatementQuery004Dto> findPage3() throws Exception {
        Page<ValBalanceStatementQuery004Dto> page = valBalanceStatementQuery002Service.selectForTradePage(RequestUtil.getParameters(getRequest()));
        return page;
    }
}

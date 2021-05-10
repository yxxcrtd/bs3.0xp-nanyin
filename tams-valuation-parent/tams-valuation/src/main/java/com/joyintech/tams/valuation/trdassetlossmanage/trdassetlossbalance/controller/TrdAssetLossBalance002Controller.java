package com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.service.TrdAssetLossBalance001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@PageId("TrdAssetLossBalance002")
@RequestMapping("/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance002")
public class TrdAssetLossBalance002Controller extends BaseController {

    @Autowired
    private TrdAssetLossBalance001Service trdAssetLossBalance001Service;

    @Override
    public String init(Model model) {
        Map<String, Object> parameters = RequestUtil.getParameters(getRequest());
        TrdAssetLossBalance002Dto dto = new TrdAssetLossBalance002Dto();
        dto.setBusiId((String) parameters.get("busiId"));
        dto.setBooksetId((String)parameters.get("booksetId"));
        dto.setBokDetailId((String)parameters.get("bokDetailId"));
//        Date bookDate = DateUtil.parse((String) parameters.get("bookDate"));
        Long time = new Long((String) parameters.get("bookDate"));
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String str = sdf.format(time);
        Date bookDate = null;
        try {
            bookDate =sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dto.setBookDate(bookDate);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAssetLossBalance002Dto> findPage() throws Exception {
        Page<TrdAssetLossBalance002Dto> page= trdAssetLossBalance001Service.queryDetailList(
                RequestUtil.getParameters(getRequest()));
        return page;
    }
}

package com.joyintech.tams.disclosure.infoCompliance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("InfoCompliance003")
@RequestMapping("/disclosure/infoCompliance/infoCompliance003")
public class InfoCompliance003Controller extends BaseController {

    @Autowired
    private InfoCompliance002Service infoCompliance002Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.开发状态);
        String seqno = getParam("seqno");
        model.addAttribute("seqno",seqno);
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    AjaxResult save(@RequestBody InfoComplianceDto dto){
        try{
            String seqnosStr = dto.getSeqno();
            if(StringUtil.isNotEmpty(seqnosStr)){
                String[] seqnos = seqnosStr.split(",");
                for(String seqno : seqnos){
                    dto.setSeqno(seqno);
                    infoCompliance002Service.updateInfoCompliance(dto);
                }
            }

        }catch (Exception e){
            return new AjaxResult("修改失败");
        }
        return new AjaxResult("修改成功");
    }

}

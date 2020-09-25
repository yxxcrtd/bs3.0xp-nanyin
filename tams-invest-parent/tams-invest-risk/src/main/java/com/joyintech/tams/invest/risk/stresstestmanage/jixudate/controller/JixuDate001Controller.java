/*
 * 文件名：JixuDate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.jixudate.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.MessageBox;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.tams.invest.risk.stresstestmanage.jixudate.service.JixuDate001Service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Controller
 * @since 1.0
 */
@Controller
@PageId("JixuDate001")
@RequestMapping({"/invest/risk/stresstestmanage/jixudate/JixuDate001"})
public class JixuDate001Controller extends BaseController{

    @Autowired(required = false)
    private JixuDate001Service jixuDate001Service;
    
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        // jixu日期
        
        model.addAttribute("jixuDate", DateUtil.format(jixuDate001Service.getJixuDate()));
        return super.init(model);
    }
    
    @PostMapping({"update"})
    public String update(Date newDate, RedirectAttributes redirectAttributes) {
        if (null == newDate) {
            MessageBox.error(getMessage("FW_E_0006", new Object[] { "新日期" }), redirectAttributes);
        }
//        else if(DateUtil.compareDate(jixuDate001Service.getJixuDate(), newDate) != -1){
//            MessageBox.info("jixu新日期不能小于旧日期!", redirectAttributes);
//        }
        else {
                
            int result = this.jixuDate001Service.updateJixuDate(newDate);
            if (result > 0) {
                MessageBox.info(getMessage("COMMON_E_0014", new Object[] { "压测数据接入设置" }), redirectAttributes);
            }else {
          
                MessageBox.error(getMessage("COMMON_E_0015", new Object[] { "压测数据接入设置" }), redirectAttributes);
            } 
      } 
        return "redirect:/invest/risk/stresstestmanage/jixudate/JixuDate001/init";
    }
       
}

///*
// * 文件名：ActiveRemind001Controller.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：ww
// * 修改时间：2019年02月27日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
// 
//package com.joyintech.tams.valuation.system.remind.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.joyintech.framework.annotation.PageId;
//import com.joyintech.framework.base.BaseController;
//import com.joyintech.framework.base.service.ActiveReminderSubPage;
//import com.joyintech.framework.grid.Page;
//import com.joyintech.framework.util.RequestUtil;
//import com.joyintech.tams.valuation.system.remind.dto.ActiveRemind001Dto;
//import com.joyintech.tams.valuation.system.remind.service.ActiveRemind001Service;
//
///**
// * 主动提醒
// * 〈功能详细描述〉
// * @author ww
// * @version 1.0 2019年02月27日
// * @see ActiveRemind001Controller
// * @since 1.0
// */
//@Controller
//@PageId("ActiveRemind001")
//@RequestMapping("/valuation/system/remind/ActiveRemind001")
//public class ActiveRemind001Controller extends BaseController implements ActiveReminderSubPage{
//    
//    /**
//     * 获取service对象
//     */
//    @Autowired
//    private ActiveRemind001Service activeRemind001Service;
//    
//    /**
//     * 实现主动提醒页面关联接口
//     */
//    @Override
//    public String getMyActiveReminderPage() {
//        String url = "/valuation/system/remind/ActiveRemind001/init";
//        return url;
//    }
//    /**
//     * 主动提醒页面初始化
//     */
//    @Override
//    public String init(Model model) {
//        return super.init(model);
//    }
//    
//    /**
//     * 
//     * 查询详细
//     * <功能详细描述>
//     * @param param 传入查询参数
//     * @return 返回查询结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("findDetail")
//    public Page<ActiveRemind001Dto> findDetail() throws Exception {
//    	return activeRemind001Service.findDetail(RequestUtil.getPageParam());
//    }
//    
//    
//}

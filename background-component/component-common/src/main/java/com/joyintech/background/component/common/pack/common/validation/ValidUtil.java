package com.joyintech.background.component.common.pack.common.validation;
///*
// * 文件名：ValidUtil.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：何阳阳
// * 修改时间：2018年9月7日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.background.component.common.pack.common.validation;
//
//import org.springframework.validation.BindingResult;
//
//import com.joyintech.background.component.common.pack.common.ajax.AjaxResult;
//import com.joyintech.background.component.common.pack.common.util.SpringContextUtil;
//import com.joyintech.background.component.common.pack.common.util.StringUtil;
//
///**
// * @author 何阳阳
// * @version 1.0 2018年9月7日
// * @see ValidUtil
// * @since 1.0
// */
//public final class ValidUtil {
//
//    public static final String OPERATE_TYPE_SAVE = "save";
//
//    public static final String OPERATE_TYPE_SUBMIT = "submit";
//
//    public static final String OPERATE_TYPE_REVOKE = "revoke";
//
//    private static final String MESSAGE_CODE = "COMMON_C_0001";
//
//    /**
//     * 保存或提交之前的check处理，并将提示消息设置到WebResult的data属性中
//     *
//     * @param result check结果
//     * @param operateType 操作类型 submit, save
//     * @return WebResult
//     * @see
//     * @since 1.0
//     */
//    public static AjaxResult commonCheck(BindingResult result, String operateType) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        AjaxResult wr = new AjaxResult();
//        String btnName = "操作";
//        if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, operateType)) {
//            btnName = "SysCommon.save";
//        }
//        else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, operateType)) {
//            btnName = "SysCommon.submit";
//        }
//        else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_REVOKE, operateType)) {
//            btnName = "SysCommon.revoke";
//        }
//        wr.setData(
//            SpringContextUtil.getTextValue(MESSAGE_CODE, SpringContextUtil.getTextValue(btnName)));
//        return wr;
//    }
//}

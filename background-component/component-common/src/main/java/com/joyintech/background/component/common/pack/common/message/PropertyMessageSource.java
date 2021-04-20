package com.joyintech.background.component.common.pack.common.message;
///*
// * 文件名：PropertyMessageSource.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：何阳阳
// * 修改时间：2018年2月1日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.background.component.common.pack.common.message;
//
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.Set;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.support.ResourceBundleMessageSource;
//
///**
// * MessageSource
// *
// * @author 何阳阳
// * @version 1.0 2018年2月1日
// * @see PropertyMessageSource
// * @since 1.0
// */
//public class PropertyMessageSource extends ResourceBundleMessageSource {
//
//    public Map<String, String> getAllMessage(String system, String msgType, Locale locale) {
//        Map<String, String> messages = new HashMap<>();
//        Set<String> basenames = getBasenameSet();
//        if (null == locale) {
//            locale = Locale.getDefault();
//        }
//        for (String basename : basenames) {
//            String[] names = StringUtils.split(basename, "/");
//            if (names.length > 0) {
//                String name = names[0];
//                if (StringUtils.isNotEmpty(system)) {
//                    if (names.length > 1) {
//                        name = names[names.length - 1];
//                    }
//                    if (!StringUtils.startsWithIgnoreCase(name, system)) {
//                        continue;
//                    }
//                }
//                if (StringUtils.isNotEmpty(msgType)) {
//                    if (names.length > 1) {
//                        name = names[1];
//                    }
//                    if (!StringUtils.equalsIgnoreCase(name, msgType)) {
//                        continue;
//                    }
//                }
//            }
//
//            ResourceBundle bundle = getResourceBundle(basename, locale);
//            if (bundle != null) {
//
//                Enumeration<String> keys = bundle.getKeys();
//
//                while (keys.hasMoreElements()) {
//                    String key = keys.nextElement();
//                    messages.put(key, bundle.getString(key));
//                }
//            }
//        }
//        return messages;
//    }
//}

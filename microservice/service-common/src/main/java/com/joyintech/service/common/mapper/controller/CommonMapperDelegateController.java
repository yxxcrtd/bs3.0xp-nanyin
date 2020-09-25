///*
// * 文件名：CommonMapperDelegateController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights
// * Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月16日 修改内容：新建 系统名称：理财资管管理系统V2.0
// */
//package com.joyintech.service.common.mapper.controller;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.joyintech.framework.util.BeanUtil;
//import com.joyintech.framework.util.SpringContextUtil;
//import com.joyintech.framework.util.StringUtil;
//import com.joyintech.service.common.mapper.dao.CommonMapperDelegateMapper;
//import com.joyintech.service.common.mapper.service.CommonMapperDelegateService;
//
//@RestController("backCommonMapperDelegateController")
//@RequestMapping("/CommonMapperDelegate")
//public class CommonMapperDelegateController {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private CommonMapperDelegateMapper commonMapperDelegateMapper;
//
//    @Autowired
//    private CommonMapperDelegateService commonMapperDelegateService;
//
//    /**
//     * 单表检索
//     *
//     * @param param 检索用Entity
//     * @return 记录
//     */
//    @RequestMapping("select/{mapperClassName}/{methodName}/{key}/default")
//    public Object selectByPrimaryKey(@PathVariable("mapperClassName") String mapperClassName,
//                                     @PathVariable("methodName") String methodName,
//                                     @PathVariable("key") String key,
//                                     @RequestBody Map<String, Object> param) {
//        try {
//            return commonMapperDelegateService.selectByPrimaryKey(mapperClassName, methodName, key,
//                    param);
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 单表检索
//     *
//     * @param param 检索用Entity
//     * @return 记录
//     */
//    @RequestMapping("select/{mapperClassName}/{methodName}")
//    public Object selectByPrimaryKey(@PathVariable("mapperClassName") String mapperClassName,
//                                     @PathVariable("methodName") String methodName,
//                                     @RequestBody Map<String, Object> param) {
//        try {
//            return commonMapperDelegateService.selectByPrimaryKey(mapperClassName, methodName,
//                    null, param);
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 单表操作
//     *
//     * @param param 目标Entity
//     * @return 影响的记录数，-1:出错
//     */
//    @PostMapping("insert/{mapperClassName}/{methodName}")
//    public int insert(@PathVariable("mapperClassName") String mapperClassName,
//                      @PathVariable("methodName") String methodName,
//                      @RequestBody Map<String, Object> param) {
//        try {
//            Integer ret = commonMapperDelegateService.modify(mapperClassName, methodName, null,
//                    param);
//            if (ret != null) {
//                return ret;
//            }
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//            logger.error("数据库操作失败", e);
//            return -1;
//        }
//        return 0;
//    }
//
//    /**
//     * 单表操作
//     *
//     * @param param 目标Entity
//     * @return 影响的记录数，-1:出错
//     */
//    @PostMapping("modify/{mapperClassName}/{methodName}/{key}/default")
//    public int modify(@PathVariable("mapperClassName") String mapperClassName,
//                      @PathVariable("methodName") String methodName,
//                      @PathVariable("key") String key, @RequestBody Map<String, Object> param) {
//        try {
//            Integer ret = commonMapperDelegateService.modify(mapperClassName, methodName, key,
//                    param);
//            if (ret != null) {
//                return ret;
//            }
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//            logger.error("数据库操作失败", e);
//            return -1;
//        }
//        return 0;
//    }
//
//    /**
//     * 单表操作
//     *
//     * @param param 目标Entity
//     * @return 影响的记录数，-1:出错
//     */
//    @PostMapping("modify/{mapperClassName}/{methodName}")
//    public int modify(@PathVariable("mapperClassName") String mapperClassName,
//                      @PathVariable("methodName") String methodName,
//                      @RequestBody Map<String, Object> param) {
//        try {
//            Integer ret = commonMapperDelegateService.modify(mapperClassName, methodName, null,
//                    param);
//            if (ret != null) {
//                return ret;
//            }
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//            logger.error("数据库操作失败", e);
//            return -1;
//        }
//        return 0;
//    }
//
//    /**
//     * 批量操作
//     *
//     * @param param 标识符与操作参数的Map
//     * @return 每一个标识符对应的目标函数返回值，-1:出错
//     */
//    @RequestMapping("batchModify/{methodName}")
//    public Map<String, Object> batchModify(@PathVariable("methodName") String methodName,
//                                           @RequestBody Map<String, Object> param)
//            throws Exception {
//        Map<String, Object> retMap = new HashMap<>();
//        for (Map.Entry<String, Object> entry : param.entrySet()) {
//            Map<String, Object> methodParam = (Map<String, Object>)entry.getValue();
//            String mapperClassName = (String)methodParam.get("mapperClassName");
//            String entityClassName = (String)methodParam.get("entityClassName");
//            Map<String, Object> entityMap = (Map<String, Object>)methodParam.get("entity");
//            try {
//                Class<?> mapper = Class.forName(mapperClassName);
//                Class<?> type = Class.forName(entityClassName);
//                Object object = BeanUtil.mapToObject(entityMap, type);
//                Object bean = SpringContextUtil.getBean(mapper);
//                boolean exists = false;
//                for (Method method : mapper.getDeclaredMethods()) {
//                    if (StringUtil.equals(method.getName(), methodName)) {
//                        Object ret = method.invoke(bean, object);
//                        retMap.put(entry.getKey(), ret);
//                        exists = true;
//                        break;
//                    }
//                }
//                if (!exists) {
//                    retMap.put(entry.getKey(), -1);
//                }
//            }
//            catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//                retMap.put(entry.getKey(), -1);
//                logger.error("操作数据库失败", e);
//                throw new Exception(e);
//            }
//        }
//        return retMap;
//    }
//
//    /**
//     * 单表指定键删除
//     *
//     * @param conditions 约束条件
//     * @return 影响的记录数
//     */
//    @RequestMapping("deleteBySpecificKey/{tableName}")
//    public int deleteBySpecificKey(@PathVariable("tableName") String tableName,
//                                   @RequestBody Map<String, Object> conditions) {
//        if (conditions.keySet().size() == 0) {
//            return -1;
//        }
//        HashMap<String, Object> param = new HashMap<>();
//        param.put("tableName", tableName);
//        param.put("conditions", conditions);
//        return commonMapperDelegateMapper.deleteBySpecificKey(param);
//    }
//}

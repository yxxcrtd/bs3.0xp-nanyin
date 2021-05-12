package com.joyintech.tams.component.business.common.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-17 15:32
 **/
@Service
public class BussCommonMapperDelegateService extends BaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> T selectBySinglePrimaryKey(Class<?> mapper, String key, Class<T> responseType) {
        Object bean = SpringContextUtil.getBean(mapper);
        Object ret;
        try {
            Method selectByPrimaryKey = null;
            Method[] methods = mapper.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("selectByPrimaryKey")) {
                    selectByPrimaryKey = method;
                    break;
                }
            }
            if (selectByPrimaryKey != null) {
                ret = selectByPrimaryKey.invoke(bean, key);
                if (ret != null) {
                    T result = responseType.newInstance();
                    if (result.getClass().isAssignableFrom(ret.getClass())) {
                        return (T) ret;
                    }
                    BeanUtil.copyPropertiesIgnoreNull(result, ret);
                    return result;
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InvocationTargetException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T selectByMultiPrimaryKey(Class<?> mapper, Object param, Class<T> responseType) {
        Object bean = SpringContextUtil.getBean(mapper);
        Object ret;
        try {
            Method selectByPrimaryKey = null;
            Method[] methods = mapper.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("selectByPrimaryKey")) {
                    selectByPrimaryKey = method;
                    break;
                }
            }
            if (selectByPrimaryKey != null) {
                ret = selectByPrimaryKey.invoke(bean, param);
                if (ret != null) {
                    T result = responseType.newInstance();
                    if (result.getClass().isAssignableFrom(ret.getClass())) {
                        return (T) ret;
                    }
                    BeanUtil.copyPropertiesIgnoreNull(result, ret);
                    return result;
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InvocationTargetException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * simpleRestTemplate
     */
    @Autowired
    @Qualifier("simpleRestTemplate")
    private JoyinRestTemplate simpleRestTemplate;
    private ConcurrentHashMap<Class<?>, Class<?>> entity2MapDict = new ConcurrentHashMap<>();
    public Class<?> getMapperClass(Class<?> entityClass){
        if(!entity2MapDict.containsKey(entityClass)){
            String mapperName = StringUtil.replaceOnce(entityClass.getName(),
                    "com.joyintech.common.base.entity.", "com.joyintech.common.base.dao.") + "Mapper";
            try {
                Class<?> mapperClass = Class.forName(mapperName);
                entity2MapDict.put(entityClass, mapperClass);
            } catch (ClassNotFoundException e) {
                entity2MapDict.put(entityClass, null);
            }
        }
        return entity2MapDict.get(entityClass);
    }

    public <T> T selectByPrimaryKey(Class<?> mapper, BaseEntity entity, Class<T> targetClass) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/select/" + mapper.getName()
                        + "/selectByPrimaryKey/",
                entity, targetClass);
    }

    public <T> T selectByPrimaryKey(Class<?> mapper, String key, Class<T> targetClass) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/select/{mapperClassName}/selectByPrimaryKey/{key}/default",
                new BaseEntity(), targetClass, mapper.getName(), key);
    }

    public int insert(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/insert/" + mapper.getName()
                        + "/insert/",
                entity, Integer.class);
    }

    public int insertSelective(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/insert/" + mapper.getName()
                        + "/insertSelective/",
                entity, Integer.class);
    }

    public int insertBasicByBasicInfo(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/insert/" + mapper.getName()
                        + "/insertBasicByBasicInfo/",
                entity, Integer.class);
    }

    public int updateByPrimaryKeySelective(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/modify/" + mapper.getName()
                        + "/updateByPrimaryKeySelective",
                entity, Integer.class);
    }

    public int updateByPrimaryKey(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/modify/" + mapper.getName()
                        + "/updateByPrimaryKey/",
                entity, Integer.class);
    }

    public int deleteByPrimaryKey(Class<?> mapper, BaseEntity entity) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/modify/" + mapper.getName()
                        + "/deleteByPrimaryKey/",
                entity, Integer.class);
    }

    public int deleteByPrimaryKey(Class<?> mapper, String key) {
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/modify/{mapperClassName}/deleteByPrimaryKey/{key}/default",
                new BaseEntity(), Integer.class, mapper.getName(), key);
    }

    /**
     * 批量插入
     *
     * @param vargs [entity1, entity2, [entity3, entity4], entity5...]
     * @return [ret1,ret2,ret3,ret4,ret5...]
     */
    public List<Integer> insertAutoClassifyBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapAutoClassifyArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/insert",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量插入
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> insertBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/insert",
                param, Map.class);
        return wrapReturn(map);
    }

    /**
     * 批量插入
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> insertBatchmokuai(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/insertmokuai",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量插入
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> insertSelectiveBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/insertSelective",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量插入
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> insertBasicByBasicInfoBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/insertBasicByBasicInfo",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量更新
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> updateByPrimaryKeySelectiveBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/updateByPrimaryKeySelective",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量更新
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> updateByPrimaryKeyBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/updateByPrimaryKey",
                param, Map.class);
        return wrapReturn(map);
    }
    /**
     * 批量删除
     *
     * @param vargs [mapper1, entity1, mapper2, entity2...]
     * @return [ret1,ret2...]
     */
    public List<Integer> deleteByPrimaryKeyBatch(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = wrapArgs(vargs);
        Map<String, Integer> map = simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/batchModify/deleteByPrimaryKey",
                param, Map.class);
        return wrapReturn(map);
    }

    private List<Integer> wrapReturn(Map<String, Integer> ret){
        return ret.entrySet().stream().sorted(
                (Comparator<Map.Entry<String, Integer>>)(o1, o2) -> new BigDecimal(o1.getKey()).compareTo(
                        new BigDecimal(o1.getKey()))).map(item -> item.getValue()).collect(Collectors.toList());
    }
    private Class<?> getBaseEntityClass(Object obj){
        for(Class<?> clz = obj.getClass(); clz != Object.class; clz = clz.getSuperclass()){
            if(clz.getName().startsWith("com.joyintech.common.base.entity.")){
                return clz;
            }
        }
        return Object.class;
    }
    private HashMap<String, Object> wrapArgs(Class<?> mapperClass, Class<?> entityClass, Object entity){
        HashMap<String, Object> target = new HashMap<>();
        target.put("mapperClassName", mapperClass.getName());
        target.put("entityClassName", getBaseEntityClass(entity));
        target.put("entity", entity);
        return target;
    }
    private HashMap<String, HashMap<String, Object>> wrapAutoClassifyArgs(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = new HashMap<>();
        for (int i = 0; i < vargs.length; i++ ) {
            if(vargs[i] instanceof List){
                for(int j=0; j< ((List)vargs[i]).size(); j++){
                    Object entity = ((List)vargs[i]).get(j);
                    Class<?> entityClass = getBaseEntityClass(entity);
                    Class<?> mapperClass = getMapperClass(entityClass);
                    if(mapperClass != null){
                        HashMap<String, Object> target = wrapArgs(mapperClass, entityClass, entity);
                        param.put(String.format("%d.%08d", i, j), target);
                    }
                }
            }
            else {
                Class<?> entityClass = getBaseEntityClass(vargs[i]);
                Class<?> mapperClass = getMapperClass(entityClass);
                if(mapperClass != null){
                    HashMap<String, Object> target = wrapArgs(mapperClass, entityClass, vargs[i]);
                    param.put(String.valueOf(i), target);
                }
            }
        }
        return param;
    }
    private HashMap<String, HashMap<String, Object>> wrapArgs(Object... vargs) {
        HashMap<String, HashMap<String, Object>> param = new HashMap<>();
        for (int i = 0; i < vargs.length / 2; i++ ) {
            HashMap<String, Object> target = wrapArgs(((Class)vargs[i * 2]),
                    getBaseEntityClass(vargs[i * 2 + 1]), vargs[i * 2 + 1]);
            param.put(String.valueOf(i), target);
        }
        return param;
    }

    /**
     * 按照指定键删除数据
     * @param tableName 表名
     * @param conditions [column1, value1, column2, value2...]
     * @return
     */
    public int deleteBySpecificKey(String tableName, String... conditions) {
        HashMap<String, Object> conditionMap = new HashMap<>();
        for (int i = 0; i < conditions.length / 2; i++ ) {
            conditionMap.put(conditions[i * 2], conditions[i * 2 + 1]);
        }
        return simpleRestTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonMapperDelegate/deleteBySpecificKey/"
                        + tableName,
                conditionMap, Integer.class);
    }
}

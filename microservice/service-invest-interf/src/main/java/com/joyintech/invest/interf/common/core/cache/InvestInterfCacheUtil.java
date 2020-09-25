package com.joyintech.invest.interf.common.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.common.core.entity.InterfDataDictInfo;

/**
 * @description 接口缓存工具
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InvestInterfCacheUtil
 * @since 1.0
 */
@Component
public class InvestInterfCacheUtil {

    /**
     * 接口系统参数缓存
     */
    private static Cache interfCache;

    /**
     * 接口缓存Key
     */
    private static final String INTERF_CACHE = "INTERF_CACHE";

    /**
     * 数据字典转换缓存
     */
    private static final String DICTLIST_LINK = "DICTLIST_LINK";

    /**
     * 平台日期Key
     */
    public static final String SYS_DATE = "SYS_DATE";

    /**
     * 密码解密
     */
    private static StringEncryptor stringEncryptor;

    /**
     * 设置缓存管理者
     *
     * @param cacheManager 缓存管理者
     * @see
     * @since 1.0
     */
    @Autowired
    protected void setCacheManager(CacheManager cacheManager) {
        if (null == cacheManager) {
            throw new RuntimeException("请配置CacheManager.");
        }
        else {
            interfCache = cacheManager.getCache("appCache");
        }
    }

    /**
     * 设置密码解密工具
     *
     * @param stringEncryptor 密码解密工具
     */
    @Autowired
    protected void setStringEncryptor(@Qualifier("stringEncryptor") StringEncryptor stringEncryptor){
        InvestInterfCacheUtil.stringEncryptor = stringEncryptor;
    }

    /**
     * 将系统参数存入缓存
     *
     * @param value 参数值
     * @since 1.0
     */
    public static void putInterfCache(Map<String, Object> value) {
    	if (value == null) {
    		interfCache.put(INTERF_CACHE, new HashMap<>());
    	} else {
    		interfCache.put(INTERF_CACHE, value);
    	}
        
    }

    /**
     * 将数据字典转换关系存入缓存
     *
     * @param dictList 数据字典转换关系
     * @since 1.0
     */
    public static void putDictListCache(List<InterfDataDictInfo> dictList) {

        interfCache.put(DICTLIST_LINK, dictList);
       // isErrorDictCode("PROFIT_TYPE","05");
    }


    /**
     * 获取所有数据字典缓存
     *
     * @return 数据字典
     */
    @SuppressWarnings("unchecked")
    public static List<InterfDataDictInfo> getAllDictCode() {

       return interfCache.get(DICTLIST_LINK, ArrayList.class);

    }


    /**
     * 数据字典转换
     *
     * @param dict 数据字典代码
     * @param code 数据字典明细代码
     * @return 转换后数据字典明细代码
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static String changeDictCode(IDictCode dict, String code) {
        List<InterfDataDictInfo> dictList = interfCache.get(DICTLIST_LINK, List.class);
        if (dict == null || StringUtil.isEmptyList(dictList)) {
            return code;
        }
        for (InterfDataDictInfo info : dictList) {
            String dictCode = info.getDctCode();
            String itemCode = info.getItmCode();
            if (StringUtil.equals(dictCode, dict.getValue())
                    && StringUtil.equals(itemCode, code)) {
                return info.getLnkItem();
            }
        }
        return code;
    }

    /**
     * 获取系统参数的值
     *
     * @param key 系统参数Code
     * @return 系统参数的值
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static String getSysParam(String key) {
        Map<String, Object> map = interfCache.get(INTERF_CACHE, Map.class);
        String value = map == null ? StringUtil.EMPTY : (String)map.get(key);
        return StringUtil.startsWith(value, "ENC(") && StringUtil.endsWith(value, ")")
                ? stringEncryptor.decrypt(StringUtil.substring(value, 4, value.length() - 1))
                : value;
    }

    /**
     * 获取平台日期
     *
     * @return 平台日期 yyyyMMdd
     * @see
     * @since 1.0
     */
    public static String getSysDate() {
        return getSysParam(SYS_DATE);
    }
}

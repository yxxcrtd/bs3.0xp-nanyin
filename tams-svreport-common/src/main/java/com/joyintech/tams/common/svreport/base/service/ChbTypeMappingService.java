/*
 * 文件名：ChbDeadlineService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.common.svreport.base.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;

/**
 * 类型映射
 * @author chenxc
 * @version 1.0 2019年02月10日
 * @see ChbTypeMappingService
 * @since 1.0
 */
@Service
public class ChbTypeMappingService extends BaseService<ChbMstExchangeInfo>{

    public final static String CACHEKEY="SVR_TYPEMAPPING"; 

    @SuppressWarnings("unchecked")
    public ChbTypeMappingEx getMapping(String mappingCode, String... fields){
        Map<String,ChbTypeMappingEx> map = CacheUtil.getAppCache(CACHEKEY,Map.class);
        
        ChbTypeMappingEx obj = map.get(getKey(mappingCode,fields));
        return obj;
    }
    
    public String getMappingValue(String mappingCode, String... fields) {
        ChbTypeMappingEx tm = getMapping(mappingCode,fields);
        if ( tm!=null )
            return tm.getObjfield();
        return null;
    }
    
    private String getKey(String mappingCode, String... fields) {
        StringBuilder sb = new StringBuilder();
      sb.append(mappingCode+"|");
      for (String s : fields) {
          if (!StringUtil.isEmpty(s)){
              sb.append(String.format("%s|",s));
          }
      }
      
      return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    public ChbTypeMappingEx getMappingBySrcField(String mappingCode,String... fields){
        Map<String,ChbTypeMappingEx> map = CacheUtil.getAppCache(CACHEKEY,Map.class);
        ChbTypeMappingEx obj = map.get(getKeyBySrcField(mappingCode,fields)); 
        return obj; 
    }  

    public String getMappingValueBySrcField(String mappingCode, String... fields) {
        ChbTypeMappingEx tm = getMappingBySrcField(mappingCode, fields);
        if (tm != null)
            return tm.getObjfield();
        return null;
    }
    
    private String getKeyBySrcField(String mappingCode, String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append(mappingCode + "|");
        for (String s : fields) {
            if (!StringUtil.isEmpty(s)) {
                sb.append(String.format("%s|", s));
            } else {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}

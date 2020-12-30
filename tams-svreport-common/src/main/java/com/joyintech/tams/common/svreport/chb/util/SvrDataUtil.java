/*
 * 文件名：CommonUtils.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2016年11月18日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.common.svreport.chb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.service.SystemToolService;

/**
 * 共通工具类<br>
 * 数据字典工具类
 * @author 何阳阳
 * @version 1.0 2016年11月18日
 * @see SvrDataUtil
 * @since 1.0
 */
@Component
public class SvrDataUtil {
	
	private static String isMicroService;

	@Value("${application.isMicroService}")
	public void setIsMicroService(String isMicroService) {
		SvrDataUtil.isMicroService = isMicroService;
	}

	private static SystemToolService systemToolService;
	
    @Autowired
    public SvrDataUtil(SystemToolService systemToolService) {
    	SvrDataUtil.systemToolService = systemToolService;
    }
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrDataUtil.class);

//    /**
//     * SysDatadictListMapper
//     */
//    private static ChbSysDatadictListMapper sysDatadictListMapper;

//    /**
//     * 注入SysDatadictListMapper
//     *
//     * @param sysDatadictListMapper SysDatadictListMapper
//     */
//    @Autowired(required=false)
//    public static void setSysDatadictListMapper(ChbSysDatadictListMapper sysDatadictListMapper) {
//        SysDataUtil.sysDatadictListMapper = sysDatadictListMapper;
//    }

    /**
     * 从数据字典中获取参数值
     *
     * @param dictCode 数据字典
     * @param itemCode 数据代码
     * @return 参数值
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional
    public static String getNameFromDataDict(final DictCode dictCode, String dictItemCode) {
    	if("microservice".equals(isMicroService)){
    		return systemToolService.getNameFromDataDict(dictCode, dictItemCode);
    	}
        if (StringUtil.isNotEmpty(dictCode) && StringUtil.isNotEmpty(dictItemCode)) {
            final String itemCode = StringUtil.trim(dictItemCode);
            // 从缓存中获取所有数据字典
            Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();
            String code = dictCode.getValue();
            if (null != dataDictMap) {
            	DataDict dto = dataDictMap.get(code);

                if (null != dto) {
                    // modify by etliu 20180819 start
//                    DataDictItem dictItem = dto.getItems().stream().filter(item -> {
//                        return StringUtil.equals(itemCode, item.getItemCode())
//                                && StringUtil.equals(code, item.getDictCode());
//                    }).collect(Collectors.toList()).get(0);
                    List<DataDictItem> dictItemList = dto.getItems().stream().filter(item -> {
                        return StringUtil.equals(itemCode, item.getItemCode())
                               && StringUtil.equals(code, item.getDictCode());
                    }).collect(Collectors.toList());

                    DataDictItem dictItem = null;
                    if (!StringUtil.isEmptyList(dictItemList)) {
                        dictItem = dictItemList.get(0);
                    }
                    // modify by etliu 20180819 end

                    if (null != dictItem) {
                        return dictItem.getItemValue();
                    }
                }
                else {
                    logger.warn("数据字典{}未定义", dictCode);
                }
            }
//            else {
//                ChbSysDatadictListKey key = new ChbSysDatadictListKey();
//                key.setDictCode(dictCode.getValue());
//                key.setItemCode(itemCode);
//                key.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
//                ChbSysDatadictList sysDatadictList = sysDatadictListMapper.selectByPrimaryKey(key);
//                if (null != sysDatadictList) {
//                    return sysDatadictList.getItemValue();
//                }
//            }

        }
        return null;
    }

    /**
     * 从数据字典中获取参数值
     *
     * @param dictCode 数据字典
     * @param itemName 数据代码名称
     * @return 参数值
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional
    public static String getCodeFromDataDictByName(final DictCode dictCode, String dictItemName) {
    	if("microservice".equals(isMicroService)){
    		return systemToolService.getCodeFromDataDictByName(dictCode, dictItemName);
    	}
        if (StringUtil.isNotEmpty(dictCode) && StringUtil.isNotEmpty(dictItemName)) {
            final String itemName = StringUtil.trim(dictItemName);
            // 从缓存中获取所有数据字典
            Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();
            String code = dictCode.getValue();
            if (null != dataDictMap) {
            	DataDict dto = dataDictMap.get(code);

                if (null != dto) {
                	DataDictItem dictItem = dto.getItems().stream().filter(item -> {
                		return StringUtil.equals(itemName, item.getItemValue())
                                && StringUtil.equals(code, item.getDictCode());
                	}).collect(Collectors.toList()).get(0);
                    if (null != dictItem) {
                        return dictItem.getItemCode();
                    }
                }
                else {
                    logger.warn("数据字典{}未定义", dictCode);
                }
            }

        }
        return null;
    }

    /**
     * 获取数据字典map
     *
     * @param dictCode 数据字典
     * @return 数据字典map
     * @see
     * @since 1.0
     */
    public static Map<String, String> getSysDictDataMap(final DictCode dictCode) {
        Map<String, String> resultMap = new HashMap<String, String>();

        if (StringUtil.isNotEmpty(dictCode)) {
            // 从缓存中获取所有数据字典
            Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();;

            String code = dictCode.getValue();

            DataDict dto = dataDictMap.get(code);

            if (null != dto) {
                List<? extends DataDictItem> items = dto.getItems();
                for (DataDictItem item : items) {
                    resultMap.put(item.getItemCode(), item.getItemValue());
                }
            }
        }

        return resultMap;
    }

    /**
     * 获取中债格式化后的数据字典（数据字典代码+空格+数据字典名称） 如果传过来的是用“,”分割的字符串，则得到用“,”分割的格式化后的数据字典
     *
     * @param dictCode 数据字段
     * @param code 数据字典明细代码
     * @return 格式化后的数据
     * @see
     * @since 1.0
     */
    public static String getZZDictCodeAndName(DictCode dictCode, final String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        List<String> codeList = StringUtil.splitToList(GlobalConstant.COMMA, code);
        List<String> nameList = new ArrayList<>();
        for (String string : codeList) {
            nameList.add(
                string + StringUtil.SPACE + SvrDataUtil.getNameFromDataDict(dictCode, string));
        }
        return StringUtil.joinString(nameList, GlobalConstant.COMMA);
    }

    /**
     *
     * 获取中债格式化后的数据字典值，从（数据字典代码+空格+数据字典名称）格式中解析出数据字典CODE
     * @param dictCode 数据字典
     * @param formatStr 格式化后的字符串
     * @return 数据字典代码
     */
    public static String getZZDictCodeFromName(DictCode dictCode, final String formatStr) {
        if (StringUtil.isEmpty(formatStr)) {
            return null;
        }
        String[] codeList = StringUtil.split(formatStr, StringUtil.SPACE);
        return codeList[0];
    }
    /**
     * 从数据字典中获取参数值
     *
     * @param dictCode 数据字典
     * @param itemCode 数据代码
     * @return 参数值
     * @see
     * @since 1.0
     */
    public static String getParentCodeFromDataDict(final DictCode dictCode, String dictItemCode) {
        if (StringUtil.isNotEmpty(dictCode) && StringUtil.isNotEmpty(dictItemCode)) {
            final String itemCode = StringUtil.trim(dictItemCode);
            // 从缓存中获取所有数据字典
            Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();
            String code = dictCode.getValue();
            if (null != dataDictMap) {
                DataDict dto = dataDictMap.get(code);

                if (null != dto) {
                    List<DataDictItem> dictItemList = dto.getItems().stream().filter(item -> {
                        return StringUtil.equals(itemCode, item.getItemCode())
                               && StringUtil.equals(code, item.getDictCode());
                    }).collect(Collectors.toList());

                    DataDictItem dictItem = null;
                    if (!StringUtil.isEmptyList(dictItemList)) {
                        dictItem = dictItemList.get(0);
                    }

                    if (null != dictItem) {
                        if (dictItem != null && StringUtil.isNotEmpty(dictItem.getParentId())){
                            String parentCode = dictItem.getParentId().substring(dictItem.getParentId()
                                    .lastIndexOf(GlobalConstant.POINT,dictItem.getParentId().length()) + 1);
                            return parentCode;
                        }
                    }
                }
                else {
                    logger.warn("数据字典{}未定义", dictCode);
                }
            }

        }
        return null;
    }
    

    public static String getSysParam(String paramKey) {
    	if("microservice".equals(isMicroService)){
            String result =  systemToolService.getSysParamValue(null, paramKey);
            return result;
    	}
        return CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, paramKey);
    }
    

    public static String getAppParamCache(String pattern, String paramKey) {
    	if("microservice".equals(isMicroService)){
            String result =  systemToolService.getSysParamValue(null, paramKey);
            return result;
    	}
    	return CacheUtil.getAppParamCache(pattern,paramKey);
    }
    
    /**
     * 获取数据字典List
     *
     * @param dictCode 数据字典
     * @return 数据字典List
     * @see
     * @since 1.0
     */
    public static List<String> getSysDictDataList(final DictCode dictCode) {
        List<String> resultList = new ArrayList<String>();

        if (StringUtil.isNotEmpty(dictCode)) {
            // 从缓存中获取所有数据字典
            Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();;

            String code = dictCode.getValue();

            DataDict dto = dataDictMap.get(code);

            if (null != dto) {
                List<? extends DataDictItem> items = dto.getItems();
                for (DataDictItem item : items) {
                	resultList.add(item.getItemCode());
                }
            }
        }

        return resultList;
    }
}

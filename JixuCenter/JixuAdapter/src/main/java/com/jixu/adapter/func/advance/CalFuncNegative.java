/*
 * 文件名：CalFuncNegative.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.joyintech.framework.util.StringUtil;

/**
 * 去掉定长报文字段中，负数前面的0
 * @author wangqidong
 * @version 1.0 2019年7月29日
 * @see CalFuncNegative
 * @since 1.0
 */
@Component("calFuncNegative")
public class CalFuncNegative extends AdditionFunc{
    private String funcname="负数截取";
    /**
     * {@inheritDoc}
     */
    @Override
    public String getFuncName() {
       
        return funcname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuncType() {
        return InterfaceConfig.advanceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateFiled(Object field) {
        
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap)
        throws Exception {
    	String fieldsStr=genConvertFormula(inputValue, colcfgMap);
    	if(StringUtil.isNotEmpty(fieldsStr) && fieldsStr.contains("-")){
        	return fieldsStr.substring(fieldsStr.indexOf("-"),fieldsStr.length());
        }
        return fieldsStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fundUsefulDesc() {
        
        return "去掉定长报文字段中，负数前面的0  \r\n 样例 : [A]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean filterField(Object field, String compareField)
        throws Exception {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String groupCal(String key, List<HashMap<String, Object>> sourceData)
        throws Exception {
        return null;
    }

}

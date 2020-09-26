package com.joyintech.tams.common.svreport.datacheck.dto;

import java.util.List;

/**
 * 描述:CHB_DATACHECK_RULE表的实体类
 * @version
 * @author:  chenxc
 * @创建时间: 2019-04-24
 */
public class CheckRule extends CheckElement{
    /**
     * 规则标示
     */
    private String checkruleid;

    /**
     * 检查的对象类型
     */
    private String datatype;

    /**
     * 检查类
     */
    private String checkclass;
    
    /**
     * 前置条件
     */
    private List<CheckCondition> listConditions;
    
    public String getCheckruleid() {
        return checkruleid;
    }
    
    public void setCheckruleid(String checkruleid) {
        this.checkruleid = checkruleid;
    }
    
    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    
    public String getCheckclass() {
        return checkclass;
    }

    public void setCheckclass(String checkclass) {
        this.checkclass = checkclass;
    }
    
    public List<CheckCondition> getListConditions() {
        return listConditions;
    }
    
    public void setListConditions(List<CheckCondition> listConditions) {
        this.listConditions = listConditions;
    }

}
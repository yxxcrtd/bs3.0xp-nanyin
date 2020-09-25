
package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor003Dto
 * @since 1.0
 */

public class RiskFactor003Dto extends CreditAssetpool implements ITableRow  , ProcessEntity {
//type1,type2,b.ftrid,c.ftrname,c.ftrvaluestruct,c.valueexetype,b.serdate,b.value,b.keyvalue,b.data
    private String type1;
    private String type2;
    private String ftrtype2;
    private String ftrid;
    private String ftrname;
  
    private String ftrvaluestruct;
   
    private String valueexetype;
    private String data;
    private String keyvalue;
    private String   yvalue;
    private String   serdate;

    public String getYvalue() {
        return yvalue;
    }

    public void setYvalue(String yvalue) {
        this.yvalue = yvalue;
    }

    /**
     * 获取 type1
     * @return type1.
     */
    public String getType1() {
        return type1;
    }
    
    /**
     * 设置 type1
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }
    
    /**
     * 获取 type2
     * @return type2.
     */
    public String getType2() {
        return type2;
    }
    
    /**
     * 设置 type2
     * @param type2 type2
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getFtrtype2() {
        return ftrtype2;
    }

    public void setFtrtype2(String ftrtype2) {
        this.ftrtype2 = ftrtype2;
    }

    public String getFtrType2Name(){
        return SysDataUtil.getNameFromDataDict(DictCode.因子二级分类,  this.ftrtype2);
    }

    /**
     * 获取 ftrid
     * @return ftrid.
     */
    public String getFtrid() {
        return ftrid;
    }
    
    /**
     * 设置 ftrid
     * @param ftrid ftrid
     */
    public void setFtrid(String ftrid) {
        this.ftrid = ftrid;
    }
    
    /**
     * 获取 ftrname
     * @return ftrname.
     */
    public String getFtrname() {
        return ftrname;
    }
    
    /**
     * 设置 ftrname
     * @param ftrname ftrname
     */
    public void setFtrname(String ftrname) {
        this.ftrname = ftrname;
    }
    
    /**
     * 获取 ftrvaluestruct
     * @return ftrvaluestruct.
     */
    public String getFtrvaluestruct() {
        return ftrvaluestruct;
    }
    
    /**
     * 设置 ftrvaluestruct
     * @param ftrvaluestruct ftrvaluestruct
     */
    public void setFtrvaluestruct(String ftrvaluestruct) {
        this.ftrvaluestruct = ftrvaluestruct;
    }
    
    /**
     * 获取 valueexetype
     * @return valueexetype.
     */
    public String getValueexetype() {
        return valueexetype;
    }
    
    /**
     * 设置 valueexetype
     * @param valueexetype valueexetype
     */
    public void setValueexetype(String valueexetype) {
        this.valueexetype = valueexetype;
    }
    
    /**
     * 获取 data
     * @return data.
     */
    public String getData() {
        return data;
    }
    
    /**
     * 设置 data
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * 获取 keyvalue
     * @return keyvalue.
     */
    public String getKeyvalue() {
        return keyvalue;
    }
    
    /**
     * 设置 keyvalue
     * @param keyvalue keyvalue
     */
    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }
    

    
    /**
     * 获取 serdate
     * @return serdate.
     */
    public String getSerdate() {
        return serdate;
    }
    
    /**
     * 设置 serdate
     * @param serdate serdate
     */
    public void setSerdate(String serdate) {
        this.serdate = serdate;
    }
    
   
  
}

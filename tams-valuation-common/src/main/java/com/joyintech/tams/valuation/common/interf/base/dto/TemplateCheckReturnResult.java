/*
 * 文件名：TempResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.common.interf.base.dto;
import java.util.List;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * @author wangwei1
 * @version 1.0 2018年6月25日
 * @param <T>
 * @see TemplateCheckReturnResult
 * @since 1.0
 */
public class TemplateCheckReturnResult<T> {
    
    private List<T> t;
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog;
    
    /**
     * 获取 t
     * @return t.
     */
    public <T>List<T> getT(Class<T> classT) {
        return (List<T>)t;
    }
    
    /**
     * 设置 t
     * @param t t
     */
    public void setT(List<T> t) {
        this.t = t;
    }

    
    /**
     * 获取 valInterflog
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    
    /**
     * 设置 valInterflog
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    
    /**
     * 获取 t
     * @return t.
     */
    public List<T> getT() {
        return t;
    }
    

}

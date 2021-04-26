package com.joyintech.tams.valuation.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ValInterfCommonConstant
 * @Description: 估值接口公共 常量定义
 * @Author yanya
 * @Date 2021/3/18
 * @Version V1.0
 **/
@Component
public class ValInterfCommonConstant {

    /**
     * 估值系统来源
     */
    private static String valDataSource;

    public static String getValDataSource() {
        return valDataSource;
    }

    //为static的变量赋值，需要用get set
    @Value("${interf.properties.valDataSource:03}")
    public void setValDataSource(String valDataSource) {
        this.valDataSource = valDataSource;
    }

}

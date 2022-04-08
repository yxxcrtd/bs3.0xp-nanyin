/**
 * 
 */
package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.tams.common.base.dao.BaseCommonCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 生成报送业务数据前共通检查
 * 
 * @author dubaojun
 * @since 2020-10-13
 */
@Service
public class BaseCommonCheck   {

    @Autowired
    private BaseCommonCheckMapper baseCommonCheckMapper;

    /**
     * 数据校验入口
     * 
     * @param datas 需要校验的数据
     * @param TableName 表名
     * @return 校验后的数据
     */
    public <T> List<T> checkData(List<T> datas, String TableName) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return datas;
        }
        // 查询数据库表信息
        List<T> tableMessage = new ArrayList<>();


        return null;
    }


    /**
     * 数据校验入口  业务
     *
     * @param datas 需要校验的数据
     * @param
     * @return 校验后的数据
     */
    public <T> List<T> checkDataYW(List<T> datas) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return datas;
        }



        return null;
    }
}

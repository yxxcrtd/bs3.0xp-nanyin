/*
 * 文件名：DeltRightCurCalAmortMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.deltrightcurcalamort.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月29日
 * @see DeltRightCurCalAmortMapper
 * @since 1.0
 */
@Repository
public interface DeltRightCurCalAmortMapper {

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param param
     * @return
     */
    public List<DrawBaseInfoSelectComDTO> selectDrawBaseInfoFromHis(Map<String, Object> param);

}

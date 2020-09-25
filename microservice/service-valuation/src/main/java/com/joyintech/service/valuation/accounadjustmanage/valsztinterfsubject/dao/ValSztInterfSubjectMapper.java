/*
 * 文件名：ValSztInterfCommon001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valsztinterfsubject.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.valsztinterfsubject.dto.ValSztInterfSubjectDto;

/**
 * 
 * 深证通科目信息查询方法，从xybranch20190115版本合并过来
 * 〈功能详细描述〉
 * @author yanya
 * @version 1.0 2019年11月2日
 * @see ValSztInterfSubjectMapper
 * @since 1.0
 */
@Repository
public interface ValSztInterfSubjectMapper {
    
    /**
     * 深证通科目信息页面table查询 从xybranch20190115版本合并过来
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ValSztInterfSubjectDto> queryPageList(Map<String, Object> params);
    
}

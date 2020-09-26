/*
 * 文件名：ValSubjectWaterQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import com.joyintech.tams.common.base.entity.BokSubject;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年7月10日
 * @see ValSubjectWaterQuery001Mapper
 * @since 1.0
 */
public interface ValSubjectWaterQuery001Mapper {
    
    /**
     * 科目号下拉框获取
     * @param params 
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<BokSubject> getSubjectNo(Map<String, Object> params);
    
//    /**
//     * 账套下拉框获取
//     * @return
//     * @see
//     * @since 1.0
//     */
//    List<ValBokBookset> queryBookSet(); 
    
    /**
     * 查询所有账套
     * <br/> 用于所属账套控件初期化
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findBooksets();
    
    /**
     * 
     * 科目流水查询
     * @param vouchNum 凭证编号
     * @param bookDate 入账日期
     * @return 科目流水List
     * @see
     * @since 1.0
     */
    List<ValSubjectWaterQuery001Dto> getSubjectWater(ValSubjectWaterQuery001SearchDto dto);
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValSubjectWaterQuery001Dto> queryPageList(ValSubjectWaterQuery001SearchDto dto);
    

}

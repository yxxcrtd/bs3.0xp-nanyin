/*
 * 文件名：ValUnfairlendingQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dto.ValUnfairlendingQuery001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValUnfairlendingQuery001Mapper")
public interface ValUnfairlendingQuery001Mapper {
    
    
    /**
     * 查询所有账套
     * <br/> 用于所属账套控件初期化
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findBooksets();
    

    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
	public List<ValUnfairlendingQuery001Dto> queryPageList(ValUnfairlendingQuery001Dto dto);


}

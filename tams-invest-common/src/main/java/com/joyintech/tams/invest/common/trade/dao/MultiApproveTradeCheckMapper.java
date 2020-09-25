/*
 * 文件名：DateCalcMapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：何阳阳 修改时间：2018年9月21日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.trade.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 交易及存续期交易，保存进待办，点批量审批时检查共通Mapper
 *
 * @author chenzhuo
 * @version 1.0 2019年4月2日
 * @see MultiApproveTradeCheckMapper
 * @since 1.0
 */
@Repository
public interface MultiApproveTradeCheckMapper {


	List<String> getWorkflowTradeId(Map<String,Object> map);

}

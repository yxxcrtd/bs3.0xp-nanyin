/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workrule.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepRule;


/**
 * 
 * 作业规则新建
 * @author longhao
 * @version 1.0 2018年12月05日
 * @see ReportRule002Mapper
 * @since 1.0
 */
public interface ReportRule002Mapper {
    
 
/**
 * 查询报表名称
 * @return
 */
List<RepRule> getReportInfo();
/**
 * 报表名称查重
 * @param repName
 * @return
 */
public	int getRepRule(String repName);
/**
 * 报表规则查重
 * @param ruleName
 * @return
 */
public int getRuleName(String ruleName);

/**
 * 判断报表类型
 * @param repId
 * @return
 */
public int  selectRepType(String repId);

}

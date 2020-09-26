package com.joyintech.tams.svreport.pbc.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 描述:人行单产品报表生成登记数据Service
 * @version 1.7
 * @author:  jiangxiao
 * @创建时间: 2019-06-04
 */
@Service
public abstract class PbcClassicsSubService <T extends ITableRow> extends CommonExportService<T>  {
	
	/**
     * @Description 生成登记数据
     */
	public abstract void genReport(Date executeDate,List<String> prodIdList);
	
}

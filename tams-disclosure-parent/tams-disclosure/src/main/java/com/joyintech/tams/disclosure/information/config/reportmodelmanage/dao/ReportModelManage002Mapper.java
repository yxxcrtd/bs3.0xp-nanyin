/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.reportmodelmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;



/**
 * 报表模板管理新建mapper 
 * 
 * @author longhao
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Mapper
 * @since 1.0
 */
@Repository
public interface ReportModelManage002Mapper {
	/**
	 * 获取数据源
	 * @return
	 */
	String getDataSource();
	/**
	 * 获取生成方式
	 * @return
	 */
	String getCreateWay();
	/**
	 * 
	 * @return
	 */
	String getReportWarehouse();
	/**
	 * 获取参数
	 * @param keyId
	 * @return
	 */
	List<String> findParamPage(String keyId);
	/**
	 * 通过repId查询报表模板
	 * @param repId
	 * @return
	 */
	ReportModelManage002Dto getReportModelManage002Dto(String code);
	/**
	 * 查询模板是否已存在
	 * @param dto
	 * @return
	 */
	int selectRepIdAndUri(ReportModelManage002Dto dto);
	/**
	 * 数据入库
	 * @param dto
	 */
	void insert(ReportModelManage002Dto dto);
	/**
	 * 设置主键
	 * @return
	 */
	String setId();
	/**
	 * 跟新数据
	 * @param dto
	 */
	void update(ReportModelManage002Dto dto);
	/**
	 * 删除
	 * @param param
	 */
	void deleteModle(Map<String, Object> param);
	

	

}

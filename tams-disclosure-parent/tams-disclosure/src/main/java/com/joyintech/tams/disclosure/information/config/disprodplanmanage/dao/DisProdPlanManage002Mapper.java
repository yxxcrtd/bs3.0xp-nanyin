/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;




/**
 * 报表模板管理新建mapper 
 * 
 * @author longhao
 * @version 1.0 2018年11月7日
 * @see
 * @since 1.0
 */
@Repository
public interface DisProdPlanManage002Mapper {
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
	 * @param
	 * @return
	 */
	DisProdPlanManage002Dto getDisProdPlanManage002Dto(String code);
	/**
	 * 查询模板是否已存在
	 * @param dto
	 * @return
	 */
	int selectRepIdAndUri(DisProdPlanManage002Dto dto);
	/**
	 * 数据入库
	 * @param dto
	 */
	void insert(DisProdPlanManage002Dto dto);
	/**
	 * 设置主键
	 * @return
	 */
	String setId();
	/**
	 * 跟新数据
	 * @param dto
	 */
	void update(DisProdPlanManage002Dto dto);
	/**
	 * 删除
	 * @param param
	 */
	void delete(Map<String, Object> param);

	List<SelectOptionDto> getMontherAndCommonProdId(Map<String, Object> paraMap);

	List<DisProdPlanManage002Dto> checkInfo(DisProdPlanManage002Dto dto);

	int delete1(DisProdPlanManage002Dto entity);

	ProdInfoDto selectByProdStatus(@Param("prodId") String prodId, @Param("status") String status);

	int hasRepeatedData(DisProdPlanManage002Dto dto);
}

/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.svreport.chb.astvalueregist.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAstValue;
import com.joyintech.tams.common.base.entity.ChbAstValueSub;



/**
 * 资产估值登记自动生成批处理mapper
 * @author 张忠良
 * @version 1.0 2019年1月28日
 * @see ChbAstValCBMapper
 * @since 1.0
 */
@Repository
public interface ChbAstValCBMapper {
	/**
	 * 查询出所有需要生成持仓登记的数据
	 * @param generateDate
	 * @return
	 */
	List<ChbAstValueSub> queryGenerateList(@Param("generateDate")String generateDate);

	int queryGenerateListAndInsert(@Param("generateDate")String generateDate,@Param("statusDate")Date statusDate,
			@Param("chbSubmitDeadline")Date chbSubmitDeadline,@Param("submitDeadline")Date submitDeadline,@Param("InstitutionCode")String InstitutionCode);

	
	
	
	/**
	 * 查询出删除和修改的持仓登记的数据
	 * @param generateDate
	 * @return
	 */
	List<ChbAstValueSub> queryUpdateOrDelList(@Param("generateDate")String generateDate,@Param("InstitutionCode")String InstitutionCode);
	
	/**
	 * 查询出已登记的数据
	 * @param generateDate
	 * @return
	 */
	List<ChbAstValueSub> queryRegistedList(@Param("generateDate")String generateDate);
	

	
	/**
	 * 批量插入登记表
	 * @param investHoldList
	 * @return
	 */
	int insertRegistBatchList(@Param("chbAstValSubList") List<ChbAstValueSub> chbAstValSubList);
	
	/**
	 * 删除登记表数据
	 * @param investHoldList
	 * @return
	 */
	int deleteRegist(Map<String,Object> map);
	
	/**
	 * 批量插入信息表
	 * @param investHoldList
	 * @return
	 */
	int insertInfoBatchList(@Param("chbAstValList") List<ChbAstValue> chbAstValList);
	
	/**
	 * 查询需要重新生成登记的日期
	 * @param generateDate
	 * @return
	 */
	List<Date> queryUpdateDate(@Param("generateDate")String generateDate,@Param("isBtm")String isBtm);
	
	int insertSub(ChbAstValueSub record);

}

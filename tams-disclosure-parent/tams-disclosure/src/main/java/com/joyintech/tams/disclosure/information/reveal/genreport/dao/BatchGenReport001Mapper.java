/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.reveal.genreport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.TreeNode;
import com.joyintech.tams.common.base.dao.DisProdAgencyMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author chenxc
 * @version 1.0 2022年7月12日
 * @see BatchGenReport001Mapper
 * @since 1.0
 */
@Repository
public interface BatchGenReport001Mapper extends DisProdAgencyMapper {

	/**
	 * 查询母产品或者单产品的系列名称
	 * @param param
	 * @return
	 */
    List<String> selectAllProdSeries(Map<String,Object> param);

    /**
     * 查找单产品或者子产品的上一级产品名称：单产品为本身产品的名称，子产品为其母产品名称
     * @param param
     * @return
     */
    List<String> selectAllProdSeriesSon(Map<String,Object> param);
    
    /**
     * 查找所有单产品和母产品
     * @return 节点名称：产品名称
     */
	List<TreeNode> selectProdInfo();
	
	/**
	 * 查询所有单产品和子产品
	 * @return 节点名称：子产品代码+子产品名称
	 */
	List<TreeNode> selectProdInfoSon();
	
	/**
	 * 通过拼接的条件查询茶品基本信息
	 * @param sqlVal 拼接处的条件
	 * @return 返回列表
	 */
	List<ProdInfoListEntity> selectProdList(@Param("sqlVal")String sqlVal, @Param("jobId")String jobId);
}

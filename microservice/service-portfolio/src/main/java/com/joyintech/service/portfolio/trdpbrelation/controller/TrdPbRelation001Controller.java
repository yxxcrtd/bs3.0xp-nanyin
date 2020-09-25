/*
 * 文件名：TrdPbRelation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：caoxue
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.portfolio.trdpbrelation.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.service.portfolio.trdpbrelation.service.TrdPbRelation001Service;

/**
 * PB编号管理管理 〈功能详细描述〉
 * 
 * @author caoxue
 * @version 1.0 2019年12月25日
 * @see TrdPbRelation001Controller
 * @since 1.0
 *
 */
@RestController("TrdPbRelation001Controller")
@RequestMapping("/TrdPbRelation001")
public class TrdPbRelation001Controller {
	@Autowired
	private TrdPbRelation001Service trdPbRelation001Service;

	/**
	 * 画面查询方法
	 * 
	 * @param param
	 *            请求参数
	 * @param page
	 *            页面
	 * @param length
	 *            长度
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<TrdPbRelation001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		if (StringUtil.isNotEmpty(pageParam.get("prdId"))) {
			pageParam.put("prdId", StringUtil.splitToList(null, pageParam.get("prdId").toString()));
		}else{
			pageParam.remove("prdId");
		}
			
		if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
			pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
		}else{
			pageParam.remove("portfolioId");
		}
		return trdPbRelation001Service.selectForPage(page, length, pageParam);
	}
	
	/**
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	@PostMapping("delete")
	@Transactional
	@DTXTransaction
	public Integer delete(@RequestBody TrdPbRelation001Dto entity) throws Exception {
		return trdPbRelation001Service.delete(entity);
	}
	
	/**
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	@PostMapping("insert")
	@Transactional
	@DTXTransaction
	public Integer insert(@RequestBody TrdPbRelation001Dto entity) throws Exception {
		return trdPbRelation001Service.insert(entity);
	}

	/**
	 * 查询PB和产品/子组合关联信息
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("getTrdPbRelation")
	public TrdPbRelation001Dto getTrdPbRelation(@RequestBody TrdPbRelation001Dto entity) {
		return trdPbRelation001Service.getTrdPbRelation(entity);
	}

	/**
	 * excel导出查询
	 * 
	 * @param tradeId
	 * @return
	 */
	@PostMapping("selectForExport")
	public List<TrdPbRelation001Dto> selectForExport(@RequestBody Map<String, Object> pageParam) {
		if (StringUtil.isNotEmpty(pageParam.get("prdId"))) {
			pageParam.put("prdId", StringUtil.splitToList(null, pageParam.get("prdId").toString()));
		}
		if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
			pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
		}
		return trdPbRelation001Service.selectForExport(pageParam);
	}
	
	/**
     * 查询产品/子组合代码
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    @PostMapping("queryPrdPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> queryPrdPortfolioId(@RequestBody Map<String, Object> pageParam,
                                               @PathVariable("page") int page, @PathVariable("length") int length) {
        return trdPbRelation001Service.queryPrdPortfolioId(page, length, pageParam);
    }
}

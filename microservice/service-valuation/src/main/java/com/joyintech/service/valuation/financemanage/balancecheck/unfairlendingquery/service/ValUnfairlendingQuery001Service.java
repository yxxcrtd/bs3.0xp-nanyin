/*
 * 文件名：ValUnfairlendingQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年01月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dao.ValUnfairlendingQuery001Mapper;
import com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dto.ValUnfairlendingQuery001Dto;

/**
 * 借贷不平账务查询
 * 
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Service
 * @since 1.0
 */
@Service
public class ValUnfairlendingQuery001Service extends CommonExportService<ValUnfairlendingQuery001Dto> {

	@Autowired
	private ValUnfairlendingQuery001Mapper valUnfairlendingQuery001Mapper;

	/**
	 * 查询所有账套 <br/>
	 * 用于所属账套控件初期化
	 * 
	 * @return SelectOptionDto list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> findBooksets() {
		return valUnfairlendingQuery001Mapper.findBooksets();
	}

	/**
	 * 格式化创建SelectOptionDto对象 <功能详细描述>
	 * 
	 * @param selectId
	 *            下拉Id值
	 * @param text
	 *            下拉文本值
	 * @return dto
	 * @see
	 * @since 1.0
	 */
	public SelectOptionDto createSelect(String selectId, String text) {
		SelectOptionDto dto = new SelectOptionDto();
		dto.setId(selectId);
		dto.setText(text);
		return dto;
	}

	/**
	 * 根据条件获取表格信息
	 * @param length 
	 * @param page 
	 * 
	 * @param params
	 *            筛选条件
	 * @return 表格信息
	 * @throws Exception
	 */
	public Page<ValUnfairlendingQuery001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
		ValUnfairlendingQuery001Dto dto = new ValUnfairlendingQuery001Dto();
		// 账套代码
		String booksetId = (String) params.get("booksetId");
		String[] booksetIdArr = null;
		if (StringUtil.isNotEmpty(booksetId)) {
			booksetIdArr = booksetId.split(",");
		}
		dto.setBooksetIdArr(booksetIdArr);
		// 资产/负债代码
		String busiId = (String) params.get("busiId");
		dto.setBusiId(busiId);
		// 获取记账日期
		String bookDateBegin = (String) params.get("bookDateBegin");
		String bookDateEnd = (String) params.get("bookDateEnd");
		if (StringUtil.isNotEmpty(bookDateBegin)) {
			dto.setBookDateBegin(DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
		}
		if (StringUtil.isNotEmpty(bookDateEnd)) {
			dto.setBookDateEnd(DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
		}
		// 没有选择展示维度
		List<ValUnfairlendingQuery001Dto> list=valUnfairlendingQuery001Mapper.queryPageList(dto);
		int beginIndex = (page - 1) * length;
		int endIndex = page * length;
		int count = list.size();
		if (beginIndex > count) {
			beginIndex = 0;
		}
		if (endIndex > count) {
			endIndex = count;
		}
		list = list.subList(beginIndex, endIndex);
		Page<ValUnfairlendingQuery001Dto> reslist = new Page<>();
		reslist.setRoot(list);
		reslist.setRecords(count);
	    return reslist;
	//return super.queryForPage(page, length,() -> valUnfairlendingQuery001Mapper.queryPageList(dto));
	}

	public List<ValUnfairlendingQuery001Dto> queryUnfairlendingList(ValUnfairlendingQuery001Dto dto) {
		return valUnfairlendingQuery001Mapper.queryPageList(dto);
	}

}

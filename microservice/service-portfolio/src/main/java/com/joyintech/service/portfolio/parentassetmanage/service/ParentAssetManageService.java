/*
 * 文件名：ParentAssetManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.portfolio.parentassetmanage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.parentassetmanage.dao.ParentAssetManageMapper;
import com.joyintech.service.portfolio.parentassetmanage.dto.ParentAssetManageDto;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 母资管计划查询服务
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年12月11日
 * @see ParentAssetManageService
 * @since 1.0
 */
@Service
public class ParentAssetManageService extends BaseService<ParentAssetManageDto> {

	@Autowired
	private ParentAssetManageMapper parentAssetManageMapper;

	/**
	 * 获取母资管计划或子计划的集合
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<ParentAssetManageDto> getParentInfos(Map<String, Object> param){
		// 判断是否是子计划
		if(StringUtil.isNotEmpty(param.get("parentId")) && !StringUtil.equals(param.get("parentId"),"root")){
			// 返回子计划集合
			return getChildList(param);
		}else{
			// 返回母计划集合
			return getParentList(param);
		}
	}
	
	/**
	 * 获取子计划集合
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	private List<ParentAssetManageDto> getChildList(Map<String, Object> param){
		// 声明返回集合
		List<ParentAssetManageDto> list = new ArrayList<ParentAssetManageDto>();
		// 获取全部子计划
		List<ParentAssetManageDto> childs = parentAssetManageMapper.getParentPlanInfos(param);
		// 循环子计划
		for (ParentAssetManageDto dto : childs) {
			// 判断该计划是否有效
			if (dto.getAssetAmt() != null) {
				// 返回集合添加有效数据
				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * 获取母资管计划集合
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	private List<ParentAssetManageDto> getParentList(Map<String, Object> param){
		// 母资管计划查询条件
    	param.put("FINPROD_TYPE2_F27", DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue());
    	// 查询母资管计划
		List<ParentAssetManageDto> parentPlanInfos = parentAssetManageMapper.getParentPlanInfos(param);
		// 获取母资管计划交易总额或导出EXCEL文档
		return parentOprate(parentPlanInfos,param);
	}
	
	/**
	 * 母资管计划交易总额或导出EXCEL文档
	 * <功能详细描述>
	 * @param parentPlanInfos
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	private List<ParentAssetManageDto> parentOprate(List<ParentAssetManageDto> parentPlanInfos,Map<String, Object> param){
		// 声明返回集合
		List<ParentAssetManageDto> list = new ArrayList<ParentAssetManageDto>();
		// 声明母计划返回集合
		List<ParentAssetManageDto> parents = new ArrayList<ParentAssetManageDto>();
		// 循环母资管计划
		for (ParentAssetManageDto dto : parentPlanInfos) {
			// 返回集合添加母资管计划
			list.add(dto);
			// 查询子计划参数
			param.put("parentId", dto.getAssetCode());
			// 查询子计划集合
			List<ParentAssetManageDto> childList = getChildList(param);
			// 判断子计划是否为空
			if (childList != null && childList.size() > 0) {
				// 声明母资管计划交易总额
				BigDecimal total = new BigDecimal(0);
				// 循环子计划集合
				for (ParentAssetManageDto child : childList) {
					// 判断交易是否有效
					if (child.getAssetAmt() != null) {
						total = total.add(child.getAssetAmt());
						list.add(child);
					}
				}
				// 保存母计划交易总额
				dto.setAssetAmt(total);
				// 判断母计划交易总额是否有效
				if (total.compareTo(new BigDecimal(0)) == 1) {
					// 返回母计划集合添加有效数据
					parents.add(dto);
				}
			}else{
				continue;
			}
		}
		// 判断是否是导出请求
		if (param.get("export") != null && (boolean) param.get("export")) {
			// 导出请求返回母计划与子计划
			return list;
		}else{
			// 非导出请求只返回母计划
			return parents;
		}
	}
}

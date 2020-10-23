package com.joyintech.service.disclosure.information.manage.prodfilemanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.service.ReportProdFileManage001Service;

/**
 * 产品文件管理Controller层
 * -微服务
 * @author hpp 
 *
 */
@RestController("backReportProdFileManage001Controller")
@RequestMapping("/ReportProdFileManage001")
public class ReportProdFileManage001Controller extends BaseController{

	@Autowired
	private ReportProdFileManage001Service reportProdFileManage001Service;
	
	/**
	 * 分页查询条件-产品代码下拉框
	 * @return
	 */
	@PostMapping("/getPrdInfoSelectPF")
    public List<SelectOptionDto> getPrdInfoSelectPF() {
         return reportProdFileManage001Service.getPrdInfoSelectPF(RequestUtil.getPageParam());
    }
	

	/**
	 * 分页查询条件-产品系列下拉框
	 * @return
	 */
	@PostMapping("/getProdSeriesSelectPF")
    public List<SelectOptionDto> getProdSeriesSelectPF() {
         return reportProdFileManage001Service.getProdSeriesSelectPF(RequestUtil.getPageParam());
    }
	/**
	 * 分页查询事件
	 * @param param
	 * @return
	 */
	@PostMapping("/searchPage/{page}/{length}")
	public Page<ReportProdFileManage001Dto> searchPage(@RequestBody Map<String, Object> param){
		return reportProdFileManage001Service.searchPage(param);
	}
	
	/**
	 * 根据主键查询产品文件产品要素信息
	 * @param param
	 * @return
	 */
	@PostMapping("/selectByPrimaryKey")
	public ReportProdFileManage001Dto selectByPrimaryKey(@RequestBody Map<String, Object> param){
		return reportProdFileManage001Service.selectByPrimaryKey(param);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	@PostMapping("/doSave")
	public void doSave(@RequestBody ReportProdFileManage001Dto dto){
		reportProdFileManage001Service.doSave(dto);
	}
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 */
	@PostMapping("/doDelete")
	public void doDelete(@RequestBody ReportProdFileManage001Dto dto){
		reportProdFileManage001Service.doDelete(dto);
	}
	
	/**
     * 查询费用品种信息
     *
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
	@PostMapping("/queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map){
		return reportProdFileManage001Service.queryFeeInfoList(map);
    }
	
	/**
	 * 查询费用信息列表
	 * @param prodId
	 * @return
	 */
	@PostMapping("/queryFeeInfoPF")
	public List<ReportProdFileManage001Dto> queryFeeInfoPF(@RequestBody String prodId){
		return reportProdFileManage001Service.queryFeeInfoPF(prodId);
	}
	/**
	 * 查询投资比例区间信息列表
	 * @param prodId
	 * @return
	 */
	@PostMapping("/queryInvestProporPF")
	public List<ReportProdFileManage001Dto> queryInvestProporPF(@RequestBody String prodId){
		return reportProdFileManage001Service.queryInvestProporPF(prodId);
	}
	
}

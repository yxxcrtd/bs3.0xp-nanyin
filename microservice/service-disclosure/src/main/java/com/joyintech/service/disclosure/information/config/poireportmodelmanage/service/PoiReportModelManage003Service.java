package com.joyintech.service.disclosure.information.config.poireportmodelmanage.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.dao.PoiReportModelManage003Mapper;
import com.joyintech.tams.common.base.dao.DisPoiTabMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.dao.ReportDealMapper;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 报表模板管理-导出报表
 * 
 * @author zhangyf
 * @version 2.0 2020年2月21日
 * @see PoiReportModelManage003Service
 * @since 2.0
 */
@Service(value = "servicePoiReportModelManage003Service")
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage003Service extends CommonExportService<RepInfo> {

	@Autowired
	private PoiReportModelManage003Mapper poiReportModelManage003Mapper;

	@Autowired
	private RepProdInfoMapper repProdInfoMapper;

	@Autowired
	private DisPoiTabMapper disPoiTabMapper;

	@Autowired
	private RepRevealMapper repRevealMapper;
	
	@Autowired
    private RepInstallLogDetailMapper repInstallLogDetailMapper;
	
	@Autowired
	private ReportDealMapper reportDealMapper;

	/**
	 * 创建信息披露数据
	 * 
	 * @param params
	 * @return
	 */
	public int createRepReveal(Map<String, Object> params) {
		// 产品基本信息
		RepProdInfo repProdInfo = new RepProdInfo();
		if (StringUtil.isNotEmpty(params.get("PROD_ID"))) {
			repProdInfo = repProdInfoMapper.selectByPrimaryKey(params.get("PROD_ID").toString());
		}
		// 报表基本信息
		DisPoiTab disPoiTab = new DisPoiTab();
		String tabNum = "";
		if (StringUtil.isNotEmpty(params.get("tabNum"))) {
			tabNum = params.get("tabNum").toString();
			disPoiTab = disPoiTabMapper.selectByPrimaryKey(tabNum);
		}
		// 披露信息
		RepReveal repReveal = new RepReveal();
		repReveal.setId(IDGenerator.generate());
		repReveal.setRepId(tabNum);
		repReveal.setReportName(disPoiTab.getTabName());
		repReveal.setReportCategory(null);
		repReveal.setProdName(repProdInfo.getProdName());
		repReveal.setProdid(repProdInfo.getProdId());
		if (StringUtil.isNotEmpty(params.get("END_DATE"))) {
			repReveal.setEndDate(DateUtil.parse(params.get("END_DATE").toString()));
		}
		if (StringUtil.isNotEmpty(params.get("START_DATE"))) {
			repReveal.setStartDate(DateUtil.parse(params.get("START_DATE").toString()));
		}
		if (StringUtil.isNotEmpty(params.get("fileName"))) {
			repReveal.setSysFilename(params.get("fileName").toString());// 文件名，不带路径
		}
		if (StringUtil.isNotEmpty(params.get("uploadFile_fileNo"))) {
			repReveal.setSysFilepath(params.get("uploadFile_fileNo").toString());
		}
		repReveal.setSysFilenameDate(new Date());
		repReveal.setFilepath(null);
		repReveal.setTime(null);
		repReveal.setFilename(null);//手动上传
		repReveal.setConfirmStatus(DictCodeList.确认状态_未确认.getValue());
		repReveal.setConfirmUser(repReveal.getUpdateUser());
		repReveal.setConfirmDate(null);
		repReveal.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
		repReveal.setRevealDate(null);
		repReveal.setRevealUser(null);
		repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
		repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
		repReveal.setDetail(null);
		repReveal.setRevealChannel(repProdInfo.getRevealChannel());
		repReveal.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
		
		// 更新相同报表数据数据状态为D(删除)
		reportDealMapper.updateDataStatus(repReveal);
		
		// 披露信息存表
		int i = repRevealMapper.insert(repReveal);
		
		// 插入日志
		// 将待组装数据成日志信息
    	RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();
        //主键 id
    	repInstallLogDetail.setId(IDGenerator.generate());
    	//执行状态
    	repInstallLogDetail.setExecStatus(DisclosureDictCodeList.日志状态_生成成功.getValue());
    	//报表编号
    	repInstallLogDetail.setRepId(repReveal.getRepId());
    	//报表名称  
    	repInstallLogDetail.setRepName(repReveal.getReportName());
    	//产品编号
    	repInstallLogDetail.setProdid(repReveal.getProdid());
    	//文件类型
    	repInstallLogDetail.setFileType(repReveal.getSysFilename().substring(repReveal.getSysFilename().lastIndexOf(".") + 1));
    	//产品名称 
    	repInstallLogDetail.setProdName(repReveal.getProdName());
    	//开始日
    	repInstallLogDetail.setStartDate(repReveal.getStartDate());
    	//结束日日
    	repInstallLogDetail.setEndDate(repReveal.getEndDate());
    	//披露渠道 
    	repInstallLogDetail.setRevealChannel(repReveal.getRevealChannel());
    	//披露截止日
    	repInstallLogDetail.setRevealEndDate(repReveal.getRevealEndDate());
    	//是否自动确认
    	repInstallLogDetail.setIsConfirm(repReveal.getIsConfirm());
    	//是否自动披露
    	repInstallLogDetail.setIsReveal(repReveal.getIsConfirm());
    	//设置数据状态
    	repInstallLogDetail.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
    	//更新日志表
		repInstallLogDetailMapper.insert(repInstallLogDetail);
		return i;
	}

}

/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.disprodplanmanage.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage002Mapper;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dao.ReportModelManage002Mapper;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.tams.common.base.entity.RepInfo;


/**
 * 报表模板管理新建service
 * 
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage002Service
 * @since 2.0
 */
@Service(value="serviceDisProdPlanManage002Service")
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage002Service extends CommonExportService<RepInfo> {
    
    
    
    @Autowired
    private DisProdPlanManage002Mapper disProdPlanManage002Mapper;
    
    @Autowired
	private ProgressBarService progressBarService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Service.class);
    /**
     * 获取数据源
     * @return
     */
	public String getDataSource() {
			return disProdPlanManage002Mapper.getDataSource();
		}
	/**
	 * 获取生成方式
	 * @return
	 */
	public String getCreateWay() {
		return disProdPlanManage002Mapper.getCreateWay();
	}
	public String getReportWarehouse() {
		
		return disProdPlanManage002Mapper.getReportWarehouse();
	}
	/**
	 * 查询模板参数
	 * @param param keyId
	 * @return
	 */
	/*public List<DisProdPlanManage002Dto> findParamPage(String repId) {
		List<String> paramList= disProdPlanManage002Mapper.findParamPage(repId);
		List<DisProdPlanManage002Dto> list=new ArrayList<>();
			for(String param:paramList){
				if(StringUtil.isNotEmpty(param)){
					//去掉参数字符串的{}
					String params=param.substring(1, param.length()-1);
					//去掉参数字符串的''
					String params2=params.replace("'", "");
					//分割参数字符串
					String[] params3=params2.split(",");
					int i=1;
					for(String para:params3){
						DisProdPlanManage002Dto dto=new DisProdPlanManage002Dto();
						//设置参数编号
						dto.setParamNum(i++);
						//设置参数名称
						dto.setParamName(para.split(":")[0]);
						switch (dto.getParamName()){
							case "portnum":dto.setName("产品名称");break;
							case "startDate":dto.setName("开始日期");break;
							case "endDate":dto.setName("结束日期");break;
							default: break;
						}
						//设置参数类型
						dto.setParamType(para.split(":")[1]);
						list.add(dto);
					}
				}
				
				
				
			
		}
	
		return list;
	}*/
	/**
	 * 保存check
	 * @param dto
	 * @return
	 */
	public AjaxResult check(DisProdPlanManage002Dto dto) {
		//todo check
		/*Set<String> message = new HashSet<String>();
		if(StringUtil.isEmpty(dto.getReportDesc())){
            message.add("请输入中文描述");
        }
		if(StringUtil.isEmpty(dto.getFolder())){
            message.add("请输入文件夹");
        }
		if(StringUtil.isEmpty(dto.getReportWarehouse())){
			message.add("请输入报表仓库");
		}
		AjaxResult result = new AjaxResult();
		if(CollectionUtils.isNotEmpty(message)){
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action->{
                errorList.add(new ValidMessage(action));
                });
            result.setErrorList(errorList);
        }
		return result;*/
		AjaxResult result = new AjaxResult();

		return  result ;
	}
	//初始化编辑页面数据
	public DisProdPlanManage002Dto getDisProdPlanManage002Dto(String code) {
		DisProdPlanManage002Dto dto = disProdPlanManage002Mapper.getDisProdPlanManage002Dto(code);
		return dto;
	}
	 /**
	 * 移除进度条
	 * @param request
	 * @return
	 */
	public void removePercent(HttpServletRequest request)
	{
		progressBarService.removePercent("DisProdPlanManage002", ProgressBarService.FILE_UPLOAD_ID);
	}
	/**
	 * 读取文件
	 * @param request
	 * @return
	 */
	public void readFile(HttpServletRequest request)
	{
		progressBarService.updatePercent("DisProdPlanManage002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}
	/**
	 * 查询是否存在相同模板
	 * @param dto
	 * @return
	 */
	public int selectRepIdAndUri(DisProdPlanManage002Dto dto) {
		int i=disProdPlanManage002Mapper.selectRepIdAndUri(dto);
		return i;
	}
	/**
	 * 插入数据
	 * @param dto
	 */
	public void insert(DisProdPlanManage002Dto dto) {
		dto.setProdPlanId(IDGenerator.generate(36));
		disProdPlanManage002Mapper.insert(dto);
		
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public void update(DisProdPlanManage002Dto dto) {
		
		disProdPlanManage002Mapper.update(dto);
	}
	/**
	 * 删除
	 * @param param
	 */
	public void delete(Map<String, Object> param) {
		disProdPlanManage002Mapper.delete( param);

	}
}

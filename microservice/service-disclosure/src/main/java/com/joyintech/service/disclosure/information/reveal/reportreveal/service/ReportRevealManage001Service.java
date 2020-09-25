/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportreveal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage001Mapper;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceReportRevealManage001Service")
public class ReportRevealManage001Service extends CommonExportService<ReportConfirmManage001Dto> implements BaseProcessService<ReportConfirmManage001Dto> {
	
	/**
     *  报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH="REPORT_DOWNLOAD_PATH";
    
    @Autowired
    private ReportConfirmManage001Mapper mapper;
    
    
    @Autowired
    private FtpDetailService ftpDetailService;
    
    /**
     * 日志
     */
    @SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(ReportRevealManage001Service.class);
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ReportConfirmManage001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        
        //获取获取理财产品类型
        String finprodId = (String)params.get("finprodId");
        String[] finprodIdArr = null;
        String[] revealChanelArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        params.put("finprodIdArr", finprodIdArr);
        
        String repType = (String)params.get("repType");
        String[] repTypeArr = null;
        if(StringUtil.isNotEmpty(repType)){
        	repTypeArr = repType.split(",");
        }
        String revealChanel=(String)params.get("revealChanel");
        if(StringUtil.isNotEmpty(revealChanel)){
        	revealChanelArr = revealChanel.split(",");
        }
        params.put("repTypeArr", repTypeArr);
        params.put("revealChanelArr", revealChanelArr);
        params.put("confirmStatus", DictCodeList.确认状态_已确认.getValue());
        
        //用于流程-我的待办-点击查看
        params.put("finprodIdForFlow", (String)params.get("finprodIdForFlow"));
        params.put("valDateForFlow", (String)params.get("valDateForFlow"));
        params.put("valBalFlagCodeForFlow", (String)params.get("valBalFlagCodeForFlow"));
        // 披露界面的查询
        params.put("confrom", "confrom");
        return super.queryForPage(page, length, ()-> mapper.queryPageList(params));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String,Object> map) {
        List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
        List<ReportConfirmManage001Dto> listBookset = mapper.getFinprodId(map);
        listBookset.forEach(item -> {
            SelectOptionDto dto=new SelectOptionDto();
//            dto.setId(item.getFinprodId());
//            dto.setText(item.getFinprodAbbr());
            listSelectDto.add(dto);
        });
        return listSelectDto;
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ReportConfirmManage001Dto queryProductByfinprodId(Map<String, Object> map) {
        return mapper.queryProductByfinprodId(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportConfirmManage001Dto> queryPageList(Map<String, Object> params) {
    	//获取获取理财产品类型
        String finprodId = (String)params.get("finprodId");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        params.put("finprodIdArr", finprodIdArr);
        
        String repType = (String)params.get("repType");
        String[] repTypeArr = null;
        if(StringUtil.isNotEmpty(repType)){
        	repTypeArr = repType.split(",");
        }
        params.put("repTypeArr", repTypeArr);
        
        params.put("confirmStatus", DictCodeList.确认状态_已确认.getValue());
        
        //用于流程-我的待办-点击查看
        params.put("finprodIdForFlow", (String)params.get("finprodIdForFlow"));
        params.put("valDateForFlow", (String)params.get("valDateForFlow"));
        params.put("valBalFlagCodeForFlow", (String)params.get("valBalFlagCodeForFlow"));
        // 披露界面的查询
        params.put("confrom", "confrom");
        return mapper.queryPageList(params);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public int updateConfirmStatus(List<ReportConfirmManage001Dto> listdto) {
        int counts=0;
        for(ReportConfirmManage001Dto dto:listdto){
            counts=counts+mapper.updateConfirmStatusById(dto);
        }
        return counts;
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ReportConfirmManage001Dto entity, OperationType type){ 
        if(entity!=null){
            //估值表余额表状态表 - 确认状态更新
//            if("QR".equals(entity.getConfirmOrNotice())){
//                mapper.updateConfirmStatus(entity);
//            }else{
        /*		//估值表余额表状态表 - 下发状态更新	
				mapper.updateNoticeStatus(entity);
				
    			String bankName = CacheUtil.getSysParam("NET_SEND");
        		if("PABANK".equals(bankName)){
        			
        			//组装页面信息
//        			ValNetValueConfirmDto dto = new ValNetValueConfirmDto();
//        			BeanUtil.copyPropertiesIgnoreNull(dto, entity);
        			
	    			//调用净值下发接口
//					  int flag  = sendNetValueInterfServices.execute(dto);
        			int flag = 1;
        			
					  if(flag==0)
						  //回滚当前事务
						  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        	}*/
//            }    
                entity.setRevealStatus(DictCodeListExt2.披露状态_已披露.getValue());
                entity.setRevealDate(new Date());
//                entity.setRevealUser(SecurityUtil.getLoginUser().getUserName());
//            	mapper.updateRevealStatusById(entity);
            	
            	// add by guozhongbao 披露时上传文件 20181210 start
            	FtpDetailDto ftpDetailDto = new FtpDetailDto();
            	ftpDetailDto.setSysFilepath(entity.getSysFilepath());
    			ftpDetailDto.setProdId(entity.getProdid());
    			ftpDetailDto.setReportId(entity.getRepId());
    			ftpDetailDto.setReportCategory(entity.getReportCategory()); // 获取ftp路径用
    			ftpDetailDto.setOption(DictCodeListExt2.披露方式_手动.getValue()); //手工操作
    			if(StringUtil.isNotEmpty(entity.getStartDate())) {
    				ftpDetailDto.setStatrDate(entity.getStartDate());
    			}
    			if(StringUtil.isNotEmpty(entity.getEndDate())) {
    				ftpDetailDto.setEndDate(entity.getEndDate());
    			}
    			ftpDetailDto.setRevealChannel(entity.getRevealChannel());
    			ftpDetailDto.setRevealId(entity.getId());
    			
            	Boolean checkAndCreate = ftpDetailService.doReveal(ftpDetailDto);
            	if(checkAndCreate) {
            	    entity.setUpdateUser(LoginUserUtil.getUserName());
            		mapper.updateRevealStatusById(entity);
            	}
            	// add by guozhongbao 披露时上传文件 20181210 end
            }
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws IOException 
     * @see
     * @since 1.0
     */
    public boolean updateRevealStatus(ReportConfirmManageParamDto dto) throws IOException {
        String data = dto.getData();
        List<ReportConfirmManage001Dto> rDtoList = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        for (ReportConfirmManage001Dto dto2 : rDtoList) {
            dto2.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
            dto2.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
            mapper.updateRevealStatusById(dto2);
        }
        return true;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws IOException 
     * @see
     * @since 1.0
     */
    public boolean updateConfirmStatusWithGoBack(ReportConfirmManageParamDto dto) throws IOException {
        String data = dto.getData();
        List<ReportConfirmManage001Dto> rDtoList = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        for (ReportConfirmManage001Dto dto2 : rDtoList) {
            dto2.setConfirmStatus(DictCodeList.确认状态_未确认.getValue());
            dto2.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
            dto2.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
            mapper.updateConfirmStatusById(dto2);
        }
        return true;
    }
}

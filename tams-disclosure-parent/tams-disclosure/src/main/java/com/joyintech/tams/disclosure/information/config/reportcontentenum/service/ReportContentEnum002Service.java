/*
 * 文件名：ReportContentEnum002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.reportcontentenum.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepIndexSubMapper;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum002Mapper;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
/**
 * 右侧表格service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月7日
 * @see ReportContentEnum002Service
 * @since 1.0
 */
@Service
public class ReportContentEnum002Service  extends CommonExportService<ReportContentEnum002Dto>{
	
	@Autowired
	private ReportContentEnum002Mapper  reportContentEnum002Mapper;
	
	@Autowired
	private RepIndexSubMapper repIndexSubMapper;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private ProgressBarService progressBarService;
	
//	@Autowired
//	private ReportContentEnum001Mapper reportContentEnum001Mapper;
//	/**
//	 */
//	@Autowired
//	private RepJobRangeMapper repJobRangeMapper;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(ReportContentEnum002Service.class);
	
    /**
     * 右侧栏查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @throws Exception
     */
	public Page<ReportContentEnum002Dto> findIndexDetailByPage(Map<String, Object> pageParam) throws Exception {
		return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/findIndexIdDetail",
                pageParam, ReportContentEnum002Dto.class);
//		return super.queryForPage(()-> reportContentEnum002Mapper.selectIndexDetail(pageParam));
	}
	
	
	/**
	 * 导出查询所有
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 */
	public  List<ReportContentEnum002Dto> findIndexDetail(Map<String, Object> pageParam){
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/findAll",
                pageParam, ReportContentEnum002Dto.class);
//		return reportContentEnum002Mapper.selectIndexDetail(pageParam);
		
		
		
	}
    /**
     * 通过id查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param indexId
     * @return
     */
	public List<ReportContentEnum002Dto> findbyIndexId(String indexName) {
		// TODO Auto-generated method stub
		 return reportContentEnum002Mapper.selectByindexName(indexName); 
	}
	/**
	 * 插入插入报表作业屏蔽产品，作业范围数据
	 * 
	 * @param dto
	 */
//	public void doinsertOrUpdate(ReportContentEnum002Dto dto) {
//		// 根据作业编号删除作业范围数据
//		reportContentEnum001Mapper.deleteRangeInfo(dto.getId());
//		// 插入作业范围数据
//		List<ReportContentEnum003Dto> settedRuleList = dto.getSettedRule();
//		if (!StringUtil.isEmptyList(settedRuleList)) {
//			for (ReportContentEnum003Dto manage3Dto : settedRuleList) {
//				manage3Dto.setJobId(dto.getId());
//				manage3Dto.setFlag(DisclosureDictCodeList.规则类型_指标值.getValue());
//				manage3Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
//				repJobRangeMapper.insertSelective(manage3Dto);
//			}
//		}
//	}
	/**
	 * 右侧栏表格新建
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void insert(ReportContentEnum002Dto dto) {
		//插入插入报表作业屏蔽产品，作业范围数据
//		doinsertOrUpdate(dto);
//		repIndexSubMapper.insert(dto);
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/insert",
				dto,Integer.class);
		
	}
	
	/**
	 * 右侧栏表格修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ReportContentEnum002Dto dto) {
		//插入插入报表作业屏蔽产品，作业范围数据
//		doinsertOrUpdate(dto);
//		repIndexSubMapper.updateByPrimaryKeySelective(dto);
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/updateByPrimaryKeySelective",
				dto,Integer.class);
	}
	
	/**
	 *  读取文件并上传到文件服务器
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @throws JSchException 
	 */
	public void readFile(HttpServletRequest request) throws Exception {
        // 文件本地路径
//		String filePath = request.getParameter("fileName");
//		/*
//		//上传文件名称
//		String name = request.getParameter("fileName");*/
//		// 文件名称
//		String fileName = FileNameUtil.formatFileName(filePath);
//
//		File file = new File(filePath);
//		boolean rec = file.renameTo(new File(fileName));
//		if (rec) {
//			logger.info("处理成功");
//		}
//
//		String Sysdate = baseCommonService.getSysDateString();
//		if (Sysdate == null || "".equals(Sysdate)) {
//			Sysdate = DateUtil.format(new Date());
//		}

//		// 主机IP
//		String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");
//
//		// 主机端口
//		Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));
//
//		// 主机用户名
//		String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");
//
//		// 主机密码
//		String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");
//
//		// 主机远程路径
//		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/" + "ZB" + "/"
//				+ DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT);
//
//		SftpTools sftpTools = new SftpTools();
//		// sftp登录
//		sftpTools.login(username, password, ip, port);
//
//		sftpTools.putFile(fileName, remotePath);
//		// 关闭登录
//		sftpTools.close();

		progressBarService.updatePercent("ReportContentEnum003", ProgressBarService.FILE_UPLOAD_ID, 1D, false);

	}
	
	/**
	 * 附件类上传
	 * @param request
	 * @return
	 */
	public int insertFileTransferInfo(HttpServletRequest request) {
		ReportContentEnum002Dto dto = new ReportContentEnum002Dto();
		
		// 系统时间
		String Sysdate = baseCommonService.getSysDateString();
		if (Sysdate == null || "".equals(Sysdate)) {
			Sysdate = DateUtil.format(new Date());
		}
		//id
		if (StringUtils.isEmpty(dto.getId())) {
			dto.setId(IDGenerator.generate());
		}
		
		//生效日期
		String indexDate = request.getParameter("indexDate");
		dto.setIndexDate(DateUtil.parse(indexDate));
		//指标名称
		String indexName = request.getParameter("fileNameOnly");
		dto.setIndexName(indexName);
		
		String priId =request.getParameter("priId");
		dto.setPriId(priId);
		
		//指标值
		String indexValue = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/" + "SC" + "/"
				+ DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT);
		dto.setIndexValue(indexValue);
		//指标类型
		String indexType = request.getParameter("indexType");
		dto.setIndexType(indexType);
		
		return repIndexSubMapper.insert(dto);
	}
	 /**
	  *  右侧通过id获取修改时的值
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param id
	  * @return
	  */
	public ReportContentEnum002Dto findById(String id) {
//		return reportContentEnum002Mapper.selectById(id);
		return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/findById?id="+id,
				ReportContentEnum002Dto.class);
	}
	
	/**
	 * 右侧栏删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id) {
		if(StringUtil.isNotEmpty(id)){
//			//从REP_JOB_RANGE表删除数据
//			reportContentEnum001Mapper.deleteRangeInfo(id);
//			//从REP_INDEX_SUB表删除数据
//			return repIndexSubMapper.deleteByPrimaryKey(id);
			return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/deleteindexDetial?id="+id, Integer.class);
		}
		return 0;
	}
	/**
	 * 根据作业编号查询报表作业范围
	 * 
	 * @param id
	 * @return
	 */
	public List<ReportContentEnum003Dto> findRulePage(String id) {
//		return reportContentEnum002Mapper.findRulePage(id);
		
		return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/findRulePage?id="+id, ReportContentEnum003Dto.class);
		
		
		
	}

    /**
     * 检查是否被引用
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportContentEnum002Dto> checkIsQuote(ReportContentEnum001Dto dto) {
        String idString =  dto.getId();
        Map<String,Object> map = new HashMap<>();
//        String[] idsarr = idString.split(",");
        map.put("idsarr", idString);
        //dto.getId()可能存的是指标库的id
        if(StringUtil.isNotEmpty(idString)){
//            List<ReportContentEnum002Dto> list = reportContentEnum002Mapper.findIndexIsQuoteById(idsarr);
//            return list;
            return restTemplate.postForList(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/checkIsQuote",
                    map, ReportContentEnum002Dto.class);
            
            
            
        }
        return null;
    }
}

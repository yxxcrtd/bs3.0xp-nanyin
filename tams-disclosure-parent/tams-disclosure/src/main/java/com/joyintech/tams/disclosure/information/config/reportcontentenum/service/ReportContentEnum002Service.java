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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.dao.RepIndexSubMapper;
import com.joyintech.tams.common.base.dao.RepJobRangeMapper;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum001Mapper;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum002Mapper;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency002Mapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
	private ReportContentEnum001Mapper reportContentEnum001Mapper;

	@Autowired
	private ReportContentEnum002Mapper  reportContentEnum002Mapper;
	
	@Autowired
	private RepIndexSubMapper repIndexSubMapper;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private ProgressBarService progressBarService;

	@Autowired
	private SaleAgency002Mapper saleAgency002Mapper;

	@Autowired
	private RepJobRangeMapper repJobRangeMapper;


	
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
	public Page<ReportContentEnum002Dto> findIndexDetailByPage(Map<String, Object> pageParam) {
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
     * @param indexName
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
	public void doinsertOrUpdate(ReportContentEnum002Dto dto) {
		// 根据作业编号删除作业范围数据
		reportContentEnum001Mapper.deleteRangeInfo(dto.getId());
		// 插入作业范围数据
		List<ReportContentEnum003Dto> settedRuleList = dto.getSettedRule();
		if (!StringUtil.isEmptyList(settedRuleList)) {
			for (ReportContentEnum003Dto manage3Dto : settedRuleList) {
				manage3Dto.setJobId(dto.getId());
				manage3Dto.setFlag(DictCodeListExt2.规则类型_指标值.getValue());
				manage3Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
				repJobRangeMapper.insertSelective(manage3Dto);
			}
		}
	}
	/**
	 * 右侧栏表格新建
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void insert(ReportContentEnum002Dto dto) {
		//插入插入报表作业屏蔽产品，作业范围数据
		doinsertOrUpdate(dto);
		dto.setAuditStatus("E");
		repIndexSubMapper.insert(dto);
		/*restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/insert",
				dto,Integer.class);**/
		
	}
	
	/**
	 * 右侧栏表格修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ReportContentEnum002Dto dto) {
		//插入插入报表作业屏蔽产品，作业范围数据
		doinsertOrUpdate(dto);
		dto.setAuditStatus("E");
		repIndexSubMapper.updateByPrimaryKeySelective(dto);
		/*restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/updateByPrimaryKeySelective",
				dto,Integer.class);*/
	}
	
	/**
	 *  读取文件并上传到文件服务器
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @throws JSchException 
	 */
	public void readFile(HttpServletRequest request) {
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
		String indexDate = request.getParameter("indexDate")==null?"":request.getParameter("indexDate");
		dto.setIndexDate(DateUtil.parse(indexDate));
		//指标名称
		String indexName = request.getParameter("fileNameOnly")==null?"":request.getParameter("fileNameOnly");
		dto.setIndexName(indexName);
		
		String priId =request.getParameter("priId")==null?"":request.getParameter("priId");
		dto.setPriId(priId);
		
		//指标值
		String indexValue = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/" + "SC" + "/"
				+ DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT);
		dto.setIndexValue(indexValue);
		//指标类型
		String indexType = request.getParameter("indexType")==null?"":request.getParameter("indexType");
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
		return reportContentEnum002Mapper.selectById(id);
		/*return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum003/findById?id="+id,
				ReportContentEnum002Dto.class);*/
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

	public void copyindexDetial(ReportContentEnum002Dto dto){
		RepIndexSub subDto = repIndexSubMapper.selectByPrimaryKey(dto.getId());
		List<ReportContentEnum003Dto> ruleList = reportContentEnum002Mapper.findRulePage(subDto.getId());
		ReportContentEnum002Dto dto2 = new ReportContentEnum002Dto();
		BeanUtil.copyPropertiesIgnoreNull(dto2,subDto);
		//复制后的指标重新生成ID
		dto2.setId(IDGenerator.generate());
		Date sysDate = new Date();
		dto2.setUpdateTime(sysDate);
		dto2.setCreateTime(sysDate);
		///复制后的规则关联到新的指标id
		for(ReportContentEnum003Dto ruleDto:ruleList){
			ruleDto.setId(dto2.getId());
		}
		dto2.setSettedRule(ruleList);
		doinsertOrUpdate(dto2);
		repIndexSubMapper.insert(dto2);

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

	/**
	 * 查询产品树结构
	 * @param
	 * @return
	 */
	public List<TreeNode> getProductTree() {
		List<TreeNode> result = new ArrayList<TreeNode>();
		//获取父节点
		result.addAll(this.getParentNodes());
		//查询当前所有产品，作为子节点
		List<TreeNode> prodInfoNodes=reportContentEnum002Mapper.selectProdInfo();
		result.addAll(prodInfoNodes);
		return result;
	}

	/**
	 * 组装除产品子节点以外的父节点
	 * @return
	 */
	public List<TreeNode> getParentNodes(){
		List<DataDictItem> prodStatusList = new ArrayList<DataDictItem>();//产品状态
		List<DataDictItem> issueTypeList = new ArrayList<DataDictItem>();//发行方式
		List<String> prodSeriesList = new ArrayList<String>();//产品系列
		List<TreeNode> parentNodes = new ArrayList<TreeNode>();
		prodStatusList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.信披_产品状态.getValue());
		issueTypeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.发行方式_信批.getValue());
		//一级：产品状态
		if (prodStatusList!=null && prodStatusList.size() > 0) {
			for (DataDictItem map_1 : prodStatusList) {
				//查询产品系列的条件
				Map<String,Object> param=new HashMap<>();
				TreeNode treeDto = new TreeNode();
				treeDto.setId(map_1.getItemCode());
				treeDto.setName(map_1.getItemValue());
				treeDto.setpId("");
				treeDto.setOpen(true);
				parentNodes.add(treeDto);
				param.put("prodStatus",map_1.getItemCode());
				//二级：发行方式
				for (DataDictItem map_2 : issueTypeList) {
					TreeNode treeDto_2 = new TreeNode();
					treeDto_2.setId(map_1.getItemCode() + map_2.getItemCode());
					treeDto_2.setName(map_2.getItemValue());
					treeDto_2.setpId(map_1.getItemCode());
					treeDto_2.setOpen(true);
					parentNodes.add(treeDto_2);
					param.put("issueType",map_2.getItemCode());
					//三级：产品系列：产品系列为文本框输入，不是字典值，需要从产品基本信息表中查询所有的产品系列
					prodSeriesList = saleAgency002Mapper.selectAllProdSeries(param);
					if(prodSeriesList!=null && prodSeriesList.size()>0){
						for (String map_3 : prodSeriesList) {
							TreeNode treeDto_3 = new TreeNode();
							treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
							treeDto_3.setName(map_3);
							treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
							treeDto_3.setOpen(true);
							parentNodes.add(treeDto_3);
						}
					}

				}
			}
		}
		return parentNodes;
	}

	public boolean isOnlyData(ReportContentEnum002Dto dto) {
		boolean flag=true;
		List<ReportContentEnum002Dto>list=reportContentEnum002Mapper.selectExistsData(dto);
		if(CollectionUtils.isNotEmpty(list)){
			if(!StringUtils.isNotEmpty(dto.getIndexDetail())){
				flag=false;
			}else{
				for (ReportContentEnum002Dto tmp:list) {
					if(!StringUtils.isNotEmpty(tmp.getIndexDetail())){
						flag=false;
						break;
					}else{
						String s1=StringUtil.substring(dto.getIndexDetail(),dto.getIndexDetail().indexOf("=")+1,dto.getIndexDetail().indexOf(";"));
						String s2=StringUtil.substring(tmp.getIndexDetail(),tmp.getIndexDetail().indexOf("=")+1,tmp.getIndexDetail().indexOf(";"));
						List<String>list1=StringUtil.splitToList(",",s1);
						List<String>list2=StringUtil.splitToList(",",s2);
						List<String> existsList = list1.stream().filter(list2::contains).collect(Collectors.toList());
						if(CollectionUtils.isNotEmpty(existsList)){
							flag=false;
							break;
						}
					}
				}
			}

		}
		return flag;
		//return reportContentEnum002Mapper.isOnlyData(dto);
	}
	
	/**
	 * 判断指标是否重复，重复条件：相同年份、相同指标周期、相同的指标类型、相同的指标PRI_ID，且审核状态相同
	 * @param dto
	 * @return
	 */
	public boolean isDuplicatedIndex(ReportContentEnum002Dto dto) {
		boolean ret = false;
		//为了防止破坏前端传入的参数，根据需要查询的条件，重建参数dto
		ReportContentEnum002Dto para = new ReportContentEnum002Dto();
		para.setPriId(dto.getPriId());
		para.setIndexType(dto.getIndexType()==null?"01":dto.getIndexType());
		para.setAuditStatus(dto.getAuditStatus()==null?"E":dto.getAuditStatus());
		para.setIndexYear(dto.getIndexYear());
		para.setIndexPeriod(dto.getIndexPeriod());
		List<ReportContentEnum002Dto>list=reportContentEnum002Mapper.selectRepIndex(para);
		if ( list!=null && list.size()>0 )
			ret = true;
		return ret;
	}
}

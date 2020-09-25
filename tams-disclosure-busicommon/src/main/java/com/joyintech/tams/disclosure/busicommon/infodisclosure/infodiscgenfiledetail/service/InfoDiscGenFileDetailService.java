package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepBatchDateStatusMapper;
import com.joyintech.tams.common.base.dao.RepInfoMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogMapper;
import com.joyintech.tams.common.base.entity.RepBatchDateStatus;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepInstallFile;
import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.dao.InfoDiscGenFileDetailMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.entity.InfoDisclosureReadAndGenEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDiscGenFileDetailService {
	/**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InfoDisclosureReadAndGenMapper infoDisclosureReadAndGenMapper;
	@Autowired
	private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;
	@Autowired
	private RepBatchDateStatusMapper repBatchDateStatusMapper;
	@Autowired
	private InfoDiscGenFileDetailMapper infoDiscGenFileDetailMapper;
	@Autowired
	private RepInstallLogMapper repInstallLogMapper;
	
	@Autowired
    private RepInfoMapper repInfoMapper;
	/**
	 * 生成截止日公共类
	 */
	@Autowired
	private RevealDeadLineService revealDeadLineService;
	/**
	 * 生成待处理详细
	 * @param executeDateStr 跑批日期
	 * @throws Exception 异常
	 */
	public boolean genInstallFileDetail(InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity, Map<String,Date> dateMap, Date executeDateStr) throws Exception{
		boolean  flag = false;
		//规则表信息
		RepRuleEntity repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(infoDisclosureReadAndGenEntity.getRuleId());
		if(repRule == null){
			return false;
		}
		//基准日类型为平台日时的披露截止日
		Date ptDate = null;
		
		//判断作业是否与产品有关.有关则查询产品信息.无关则直接生成一条数据
		if(infoDisclosureReadAndGenEntity.getIsProdRelated().equals(DictCodeList.是否_是.getValue())){
			//获取需要披露的产品列表
			List<ProdInfoListEntity> list = this.getRuleProdList(infoDisclosureReadAndGenEntity, repRule, executeDateStr, ptDate, dateMap);
			
			ptDate = dateMap.get("ptDate");
			if(CollectionUtils.isEmpty(list)){
				RepInstallLog repInstallLog = new RepInstallLog();
				repInstallLog.setId(infoDisclosureReadAndGenEntity.getId());
				repInstallLog.setExecStatus(DictCodeListExt2.执行状态_已执行.getValue());
				repInstallLog.setExecResult(DictCodeListExt2.执行结果_无符合要求执行数据.getValue());
				if(StringUtil.equals(infoDisclosureReadAndGenEntity.getExecFlag(), DictCodeListExt2.执行标志_首次执行.getValue())){
					repInstallLog.setStartDate(DateUtil.now());
					repInstallLog.setEndDate(DateUtil.now());
				}
				repInstallLog.setExecDetail("无满足条件的产品   ");
				repInstallLog.setStatus(DictCodeListExt2.数据状态_有效.getValue());
				repInstallLogMapper.updateByPrimaryKeySelective(repInstallLog);
				return true;
			}
			//执行插操作
			flag = this.doInsert(list, infoDisclosureReadAndGenEntity, repRule, dateMap, false);
			
		//数据无关.直接送一个空产品    leipeng 20190305 add
		}else if(infoDisclosureReadAndGenEntity.getIsProdRelated().equals(DictCodeList.是否_否.getValue())){
			
			 //获取开始日结束日方法
		    infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, executeDateStr, dateMap);
			//获取披露截止日
			ptDate = revealDeadLineService.getRevDeadlineByMark(executeDateStr, repRule, dateMap);
			dateMap.put("ptDate", ptDate);
			
			ProdInfoListEntity entity = new ProdInfoListEntity();
			entity.setRevDeadDate(ptDate);
			RepInstallFile repInstallFile = this.doRepInstallFile(entity, dateMap, infoDisclosureReadAndGenEntity, repRule);
			List<RepInstallFile> revealList = new ArrayList<>();
			revealList.add(repInstallFile);
		    infoDisclosureReadAndGenDetailService.insertRepInstallFile(revealList);

			flag = true;
		}
		//数据无关.直接送一个空产品    leipeng 20190305 end
		
		return flag;
	}
	/**
	 * 获取需要披露的产品列表
	 * @param repJob 作业任务
	 * @param repRule 规则
	 * @param executeDateStr 传入日期
	 * @param ptDate  披露截止日
	 * @param dateMap 存储日期
	 * @return
	 * @throws Exception
	 */
	public List<ProdInfoListEntity> getRuleProdList(InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity, RepRuleEntity repRule, Date executeDateStr, 
			Date ptDate, Map<String,Date> dateMap) throws Exception{
		//获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(infoDisclosureReadAndGenEntity.getJobId());
		//通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenDetailService.getRepJobRangeByJobid(repJobRangeList, infoDisclosureReadAndGenEntity.getJobId(),executeDateStr);
		//当无满足条件的产品时，返回
		if(CollectionUtils.isEmpty(prodList)){
			logger.info("无满足条件的产品");
			return new ArrayList<ProdInfoListEntity>();
		}
		List<ProdInfoListEntity> list = new ArrayList<ProdInfoListEntity>();
		if(StringUtil.equals(DictCodeListExt2.基准日_平台日.getValue(), repRule.getBasisDateType())){
		    //获取开始日结束日方法
		    infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, executeDateStr, dateMap);
			//获取披露截止日
			ptDate = revealDeadLineService.getRevDeadlineByMark(executeDateStr, repRule, dateMap);
			dateMap.put("ptDate", ptDate);
			list.addAll(prodList);
		}
		else if(StringUtil.equals(DictCodeListExt2.基准日_开放周期开始日.getValue(), repRule.getBasisDateType())||
				StringUtil.equals(DictCodeListExt2.基准日_开放周期结束日.getValue(), repRule.getBasisDateType())||
				StringUtil.equals(DictCodeListExt2.基准日_开放周期起息日.getValue(), repRule.getBasisDateType())){
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
//				int days = Integer.parseInt(repRule.getMakeValue());
//				if(StringUtil.equals(ReportDictCodeList.生成时点类型_后.getValue(), repRule.getRevealType())){
//					days = -1*days;
//				}
				//基准日
				Date openDate = revealDeadLineService.isCanGenOpen(repRule, dateMap, executeDateStr, prodInfoListEntity.getProdId());
				if(openDate!=null){
					prodInfoListEntity.setBasisDate(openDate);
					 //获取开始日结束日方法
		            infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					//获取披露截止日		            
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(openDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}
		else if(StringUtil.equals(DictCodeListExt2.基准日_费率调整日.getValue(), repRule.getBasisDateType())){
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				//基准日
				Date openDate = revealDeadLineService.isCanGenAdjust(repRule, dateMap, executeDateStr, prodInfoListEntity.getProdId());
				if(openDate!=null){
					prodInfoListEntity.setBasisDate(openDate);
					 //获取开始日结束日方法
		            infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					//获取披露截止日		            
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(openDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}
		
	
		else if(StringUtil.equals(DictCodeListExt3.基准日_巨额赎回调整日.getValue(), repRule.getBasisDateType())){
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				//基准日
				Date openDate = revealDeadLineService.isCanGenJESH(repRule, dateMap, executeDateStr, prodInfoListEntity.getProdId());
				if(openDate!=null){
					prodInfoListEntity.setBasisDate(openDate);
					 //获取开始日结束日方法
		            infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					//获取披露截止日		            
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(openDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}
		else if(StringUtil.equals(DictCodeListExt3.基准日_募集上线调整日.getValue(), repRule.getBasisDateType())){
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				//基准日
				Date openDate = revealDeadLineService.isCanGenMJTZ(repRule, dateMap, executeDateStr, prodInfoListEntity.getProdId());
				if(openDate!=null){
					prodInfoListEntity.setBasisDate(openDate);
					 //获取开始日结束日方法
		            infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					//获取披露截止日		            
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(openDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}
		else{
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				Date revDeadDate = infoDisclosureReadAndGenDetailService.getBasisDate(prodInfoListEntity, repRule);
				int days = Integer.parseInt(repRule.getMakeValue());
				// update by leipeng 规则 前后更改
				if(StringUtil.equals(DictCodeListExt2.时点类型_基准日后.getValue(), repRule.getRevealType())){
					days = -1*days;
				}
				boolean isGen = false;
				if(StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getMakeDateType())){
					if(DateUtil.getBetweenDayNumber(executeDateStr, revDeadDate )==days){
						isGen = true;
					}
				}
				else if(revealDeadLineService.isWorkDay(executeDateStr, DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
					isGen = revealDeadLineService.isCanGen(revDeadDate, repRule, dateMap, executeDateStr);
				}
				prodInfoListEntity.setBasisDate(revDeadDate);
				if(isGen){
                    infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, revDeadDate, dateMap);
				  //获取披露截止日                    
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(revDeadDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}		
		return list;
	}
	/**
	 * 执行插入操作
	 * @param list 产品列表
	 * @param ptDate 披露截止日
	 * @param repJob 工作
	 * @param repRule 规则
	 * @param executeDateStr 传入日期
	 * @param runAgainFlag  是否重跑标志
	 * @return
	 * @throws Exception
	 */
	public boolean doInsert(List<ProdInfoListEntity> list, InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity,RepRuleEntity repRule,
			Map<String, Date> dateMap, boolean runAgainFlag) throws Exception{
	    
	    boolean flag = false;
		List<RepInstallFile> revealList = new ArrayList<RepInstallFile>();
//		String seq = IDGenerator.generate();
		for (ProdInfoListEntity prodInfoListEntity : list) {
			
			RepInstallFile repInstallFile = doRepInstallFile(prodInfoListEntity,dateMap,infoDisclosureReadAndGenEntity,repRule);
			
			//定期公告必须满足起息日距离结束日大于90天，结束日离实际到期日大于90天
			if(StringUtil.equals(DictCodeListExt2.报表类别_理财产品定期公告.getValue(), repRule.getReportCategory())){
				if(repInstallFile.getEndDate()!=null&&!(DateUtil.getBetweenDayNumber(prodInfoListEntity.getVdate(), repInstallFile.getEndDate())<90
						||DateUtil.getBetweenDayNumber(repInstallFile.getEndDate(), prodInfoListEntity.getActMdate())<90)
						&&DateUtil.compareDate(prodInfoListEntity.getActMdate(), repInstallFile.getEndDate())>0){
					revealList.add(repInstallFile);
				}
			}
			else if(StringUtil.equals(DictCodeListExt2.报表类别_净值披露.getValue(), repRule.getReportCategory())){
				if(repInstallFile.getEndDate()!=null&&DateUtil.compareDate(prodInfoListEntity.getActMdate(), repInstallFile.getEndDate())>0
						&&DateUtil.compareDate(repInstallFile.getEndDate(), prodInfoListEntity.getVdate())>=0){
					revealList.add(repInstallFile);
				}
			}
			else{
				revealList.add(repInstallFile);
			}
			
		}

		//批量插入组装信息,分批次插入
		if(revealList.size()>0){
		    flag = true;
		    infoDisclosureReadAndGenDetailService.insertRepInstallFile(revealList);
		}
		return flag;
	}
	
	/**
	 * 组装数据
	 * @param prodInfoListEntity
	 * @param dateMap
	 * @param infoDisclosureReadAndGenEntity
	 * @param repRule
	 * @return
	 */
	private RepInstallFile doRepInstallFile(ProdInfoListEntity prodInfoListEntity,Map<String, Date> dateMap,InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity,RepRuleEntity repRule){

		RepInstallFile repInstallFile = new RepInstallFile();
		//id
		repInstallFile.setId(IDGenerator.generate());
		//产品编号
		repInstallFile.setProdid(prodInfoListEntity.getProdId());
		//产品名称
		repInstallFile.setProdName(prodInfoListEntity.getProdName());
		//披露截止日
		if(StringUtil.equals(DictCodeListExt2.基准日_平台日.getValue(), repRule.getBasisDateType())){
			repInstallFile.setRevealEndDate(dateMap.get("ptDate"));
		}
		else{
			repInstallFile.setRevealEndDate(prodInfoListEntity.getRevDeadDate());
		}
		//作业编号
		repInstallFile.setJobId(infoDisclosureReadAndGenEntity.getJobId());
		//作业流水号
		repInstallFile.setJobSeq(infoDisclosureReadAndGenEntity.getJobSeq());
		//报表类型
		repInstallFile.setRepType(DictCodeListExt2.信息披露报表类型_报表.getValue());
		//报表编号
		repInstallFile.setRepId(repRule.getRepId());
		//报表名称
		repInstallFile.setRepName(repRule.getRepName());
		//生产文件类型
		repInstallFile.setFileType(repRule.getFileOutType());
		
		//TODO后期要改造
		//披露渠道
		repInstallFile.setRevealChannel(prodInfoListEntity.getRevealChannel());
		//是否自动确认
		repInstallFile.setIsConfirm(repRule.getIsConfirm());
		
		//是否自动披露
		repInstallFile.setIsReveal(repRule.getIsReveal());
//		if(StringUtil.isNotEmpty(prodInfoListEntity.getRevealChannel())&&prodInfoListEntity.getRevealChannel().equals(DisclosureDictCodeList.披露渠道_官网.getValue())){
//		    //是否自动披露
//            repInstallFile.setIsReveal(repRule.getIsReveal());
//		}else{
//		    repInstallFile.setIsReveal(DisclosureDictCodeList.披露方式_手动.getValue());
//		}
		
		//数据状态
		repInstallFile.setStatus(DictCodeListExt2.数据状态_有效.getValue());
		//基准日
//		Date basicDate;
//		if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
//			basicDate = executeDateStr;
//		}
//		else{
//			basicDate = prodInfoListEntity.getBasisDate();
//		}
//		//开始日与结束日赋值
//		Map<String,Date> map = infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, basicDate);
		if(dateMap.get("startDate")!=null){
			repInstallFile.setStartDate(dateMap.get("startDate"));
		}
		if(dateMap.get("endDate")!=null){
			repInstallFile.setEndDate(dateMap.get("endDate"));
		}
		
		//TODO 添加根据报表类型直接更改报告期的方法
		RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repInstallFile.getRepId());
		if(StringUtil.isNotEmpty(repInfo)&&StringUtil.isNotEmpty(repInfo.getTemplateId())){
            String temllateId = repInfo.getTemplateId();
            if(temllateId.equals("CLGG_zx")){
                repInstallFile.setEndDate(prodInfoListEntity.getVdate());
            }else if(temllateId.equals("DQGGHB_zx")||temllateId.equals("DQGGJZ_zx")||temllateId.equals("DQGGYQ_zx")){
                repInstallFile.setEndDate(prodInfoListEntity.getMdate());
            }
        }
		return repInstallFile;
		
	}
	
	
	/**
	 * 查看适配器数据是否已准备好
	 * @param executeDateStr 传入日期
	 * @return true准备好 false未准备好
	 */
	public boolean isReady(String executeDateStr){
		boolean flag =false;
		String sourceId = CacheUtil.getSysParam(ReportGlobalConstant.REP_DATA_SOURCE_ID);
		int id = infoDiscGenFileDetailMapper.queryMaxIdBySourceId(Integer.parseInt(sourceId));
		//获取库中最大的id 取值方法待确定
		RepBatchDateStatus repBatchDateStatus = infoDiscGenFileDetailMapper.queryStatusMaxIdBySourceId(sourceId);
		if(repBatchDateStatus !=null){
			if(id>Integer.parseInt(repBatchDateStatus.getId())){
				flag = true;
				String zgDate = infoDiscGenFileDetailMapper.queryMaxIdByFromZG();
				RepBatchDateStatus repBatchDateStatus1 = new RepBatchDateStatus();
				repBatchDateStatus1.setId(String.valueOf(id));
				repBatchDateStatus1.setProdid(DateUtil.parse(zgDate));
				repBatchDateStatus1.setSourid(sourceId);
				repBatchDateStatus1.setStatus(DictCodeListExt2.数据状态_有效.getValue());
				repBatchDateStatusMapper.insert(repBatchDateStatus1);
			}
			else if(id == Integer.parseInt(repBatchDateStatus.getId())
					&&DateUtil.compareDate(DateUtil.format(repBatchDateStatus.getProdid()),executeDateStr)==0){
				flag = true;
			}
		}else{
			if(id>0){
				flag = true;
                String zgDate = infoDiscGenFileDetailMapper.queryMaxIdByFromZG();
				RepBatchDateStatus repBatchDateStatus1 = new RepBatchDateStatus();
				repBatchDateStatus1.setId(String.valueOf(id));
				repBatchDateStatus1.setProdid(DateUtil.parse(zgDate));
				repBatchDateStatus1.setSourid(sourceId);
				repBatchDateStatus1.setStatus(DictCodeListExt2.数据状态_有效.getValue());
				repBatchDateStatusMapper.insert(repBatchDateStatus1);
			}
			else{
				flag = false;
			}
		}
		
		return flag;
	}
	
	
	/**
     * 当日是否已经就绪
     * @param executeDateStr 传入日期
     * @return true准备好 false未准备好
     */
    public boolean checkDateisReady(String executeDateStr){
        boolean flag =false;
        if(StringUtil.isNotEmpty(executeDateStr)){
            int day = -1;
            String da = CacheUtil.getSysParam(ReportGlobalConstant.REP_BATCH_DAY);
            if(StringUtil.isNotEmpty(da)){
                day = Integer.parseInt(da);
            }
            String execflag = CacheUtil.getSysParam(ReportGlobalConstant.REP_DATA_SOURCE_ID);
            if(!StringUtil.isNotEmpty(execflag)){
                execflag="1042";  
            }
            
            
            int data = infoDiscGenFileDetailMapper.queryAdptDateisReady(DateUtil.formatDate(DateUtil.addDay(executeDateStr, day)),execflag);
            if(data>0){
                flag = true;
            }
        }
        
        return flag;
    }
    
    /**
     * 当日是否已经就绪
     * @param executeDateStr 传入日期
     * @return true准备好 false未准备好
     */
    public boolean checkDateisReadyNew(InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity,String executeDateStr){
    	//从系统参数中获取数据源配置方式 01 数据源 02 文件
    	String adapterSourceType = CacheUtil.getSysParam(ReportGlobalConstant.ADAPTER_SOURCE_TYPE);
        boolean flag =false;
        int data = 0;
        
        int day = -1;
        String da = CacheUtil.getSysParam(ReportGlobalConstant.REP_BATCH_DAY);
        if(StringUtil.isNotEmpty(da)){
            day = Integer.parseInt(da);
        }
        
        if(StringUtil.isNotEmpty(executeDateStr)){
        	if("01".equals(adapterSourceType)){
                String execflag = CacheUtil.getSysParam(ReportGlobalConstant.REP_DATA_SOURCE_ID);
                if(!StringUtil.isNotEmpty(execflag)){
                    execflag="1042";  
                }
                data = infoDiscGenFileDetailMapper.queryAdptDateisReady(DateUtil.formatDate(DateUtil.addDay(executeDateStr, day)),execflag);
        	}
            //文件适配器
            else if("02".equals(adapterSourceType)){
            	if(StringUtil.equals(DictCodeListExt2.报表类别_托管公告.getValue(), infoDisclosureReadAndGenEntity.getReportCategory())){
            		data = infoDiscGenFileDetailMapper.queryAdptDateisReadyFile(DateUtil.addDay(executeDateStr, day),"41");
            	}
            	else{
            		data = infoDiscGenFileDetailMapper.queryAdptDateisReadyFile(DateUtil.addDay(executeDateStr, day),"1");
            	}
            	//添加不需要验证数据
            }else if("03".equals(adapterSourceType)){
            	data = 1;
            }
        }
        if(data>0){
            flag = true;
        }
        return flag;
    }
    
	/**
	 * 判断 宁波四类报表当天是否有数据
	 * @param infoDisclosureReadAndGenEntity 传入基础数据
	 * @return true 生成 false 不生成
	 */
//	private boolean isGen(InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity, Date executeDateStr){
//		boolean flag = true;
//		int count  = 0;
//		//被动超限
//		if(StringUtil.equals("BDCGGPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryCXCount(executeDateStr);
//		}
//		//信贷类资产
//		else if(StringUtil.equals("XDZCSYQPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryXDCount(executeDateStr);
//		}
//		//关联公告
//		else if(StringUtil.equals("GLJYPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGLCount(executeDateStr);
//		}
//		//非标
//		else if(StringUtil.equals("FBZHZQLZCPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryFBCount(executeDateStr);
//		}//公募活期
//		else if(StringUtil.equals("GMHQ", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGMHQCount(executeDateStr);
//		}
//		//公募产品披露公告
//		else if(StringUtil.equals("GMCPPLGG", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGMCPPLGGCount(executeDateStr);
//		}
//		//封闭式产品披露公告
//		else if(StringUtil.equals("FBSCPPLGG", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryFBSCPPLGGCount(executeDateStr);
//		}
//		//公募天天开放
//		else if(StringUtil.equals("GMTTKF", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGMTTKFCount(executeDateStr);
//		}
//		//公募定开
//		else if(StringUtil.equals("GMDKCP", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGMDKCPCount(executeDateStr);
//		}
//		//公募封闭式产品
//		else if(StringUtil.equals("GMFBSCP", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.queryGMFBSCPCount(executeDateStr);
//		}
//		//私募封闭式产品
//		else if(StringUtil.equals("SMFBSCP", infoDisclosureReadAndGenEntity.getTemplateId())){
//			count = infoDisclosureReadAndGenMapper.querySMFBSCPCount(executeDateStr);
//		}
//		
//		//巨额赎回产品
//				else if(StringUtil.equals("JESHSXPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//					count = infoDisclosureReadAndGenMapper.queryJESHCPCount(executeDateStr);
//				}
//		//募集金额调整产品
//				else if(StringUtil.equals("MJJESXTZPL_nb", infoDisclosureReadAndGenEntity.getTemplateId())){
//					count = infoDisclosureReadAndGenMapper.queryMJJETZCount(executeDateStr);
//				}
//		if(count<=0){
//			flag = false;
//		}
//		return flag;
//	}
	
	
}
